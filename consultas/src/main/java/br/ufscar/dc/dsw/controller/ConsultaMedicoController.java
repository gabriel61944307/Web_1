package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// import java.text.SimpleDateFormat;
// import java.util.ArrayList;
// import java.util.Date;
// import java.time.LocalDateTime;
// import java.time.format.DateTimeFormatter;
// import java.util.List;
// import java.time.format.DateTimeParseException;

import br.ufscar.dc.dsw.utils.Erro;
import br.ufscar.dc.dsw.dao.ConsultaDAO;
import br.ufscar.dc.dsw.domain.Consulta;
import br.ufscar.dc.dsw.domain.Medico;
//import br.ufscar.dc.dsw.domain.Paciente;
// import br.ufscar.dc.dsw.dao.MedicoDAO;
// import br.ufscar.dc.dsw.dao.PacienteDAO;
//import br.ufscar.dc.dsw.domain.Medico;
import br.ufscar.dc.dsw.domain.Usuario;

@WebServlet(urlPatterns = "/consultas-medico/*")

public class ConsultaMedicoController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ConsultaDAO daoConsulta;
    // private MedicoDAO daoMedico;
    // private PacienteDAO daoPaciente;

    @Override
    public void init() {
        daoConsulta = new ConsultaDAO();
        // daoMedico = new MedicoDAO();
        // daoPaciente = new PacienteDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
        Erro erros = new Erro();

        if (usuario == null) {
            response.sendRedirect(request.getContextPath());
            return;
        } else if (!usuario.getPapel().equals("MEDICO")) {
            erros.add("Acesso não autorizado!");
            erros.add("Apenas Papel [MEDICO] tem acesso a essa página.");
            request.setAttribute("mensagens", erros);
            RequestDispatcher rd = request.getRequestDispatcher("/noAuth.jsp");

            rd.forward(request, response);
            return;
        }

        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "/remocao":
                    removeConsulta(request, response);
                    break;
                default:
                    listaConsulta(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }
    }

    private void listaConsulta(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtém o usuário atualmente logado na sessão
        Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");

        Medico medico = (Medico) usuarioLogado;
        String crmMedico = medico.getCrm();

        List<Consulta> listaConsultas = daoConsulta.getConsultasByCrmMedico(crmMedico);
        request.setAttribute("listaConsultas", listaConsultas);

        List<String> listaNomes = daoConsulta.getNomePaciente(crmMedico);
        request.setAttribute("listaNomes", listaNomes);

        // criação de uma lista de datas formatadas corretamente para a exibição na
        // lista
        List<String> listaData = daoConsulta.getDatasFormatadas(listaConsultas);
        request.setAttribute("listaData", listaData);

        // criação de uma lista de horas formatadas corretamente para a exibição na lista
        List<String> listaHora = daoConsulta.getHorasFormatadas(listaConsultas);
        request.setAttribute("listaHora", listaHora);

        RequestDispatcher dispatcher = request
                .getRequestDispatcher("/logado/medico/consultas-medico/lista.jsp");
        dispatcher.forward(request, response);

    }

    private void removeConsulta(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));

        System.out.println(id);

        Consulta consulta = new Consulta(id);
        daoConsulta.delete(consulta);
        response.sendRedirect("lista");
    }
}
