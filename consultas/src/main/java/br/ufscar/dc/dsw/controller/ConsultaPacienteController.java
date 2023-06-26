package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import br.ufscar.dc.dsw.utils.Erro;
import br.ufscar.dc.dsw.dao.ConsultaDAO;
import br.ufscar.dc.dsw.domain.Consulta;
import br.ufscar.dc.dsw.domain.Paciente;
import br.ufscar.dc.dsw.dao.MedicoDAO;
//import br.ufscar.dc.dsw.domain.Medico;
import br.ufscar.dc.dsw.domain.Usuario;


@WebServlet(urlPatterns = "/consultas-paciente/*")

public class ConsultaPacienteController extends HttpServlet {
    
    private static final long serialVersionUID = 1L;

    private ConsultaDAO daoConsulta;
    private MedicoDAO daoMedico;

    @Override
    public void init() {
        daoConsulta = new ConsultaDAO();
        daoMedico = new MedicoDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
        Erro erros = new Erro();

        if (usuario == null) {
            response.sendRedirect(request.getContextPath());
            return;
        } else if (!usuario.getPapel().equals("PACIENTE")) { 
            erros.add("Acesso não autorizado!");
            erros.add("Apenas Papel [PACIENTE] tem acesso a essa página.");
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
                case "/cadastro":
                    apresentaFormCadastroConsulta(request, response);
                    break;
                case "/insercao":
                    insereConsulta(request, response);
                    break;
                case "/remocao":
                    //removeConsulta(request, response);
                    break;
                case "/edicao":
                    //apresentaFormEdicaoConsulta(request, response);
                    break;
                case "/atualizacao":
                    //atualizeConsulta(request, response);
                    break;
                default:
                    listaConsulta(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }
    }

    private void apresentaFormCadastroConsulta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Usuario> listaMedicos = daoMedico.getAll();
        request.setAttribute("listaMedicos", listaMedicos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/paciente/consultas-paciente/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void insereConsulta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        //Long id = Long.parseLong(request.getParameter("id"));
        String crmMedico = request.getParameter("crm");

        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String papel = request.getParameter("papel");
        String cpf = request.getParameter("cpf");
        String telefone = request.getParameter("telefone");
        String sexo = request.getParameter("sexo");
        String dataNascimento = request.getParameter("dataNascimento");
        
        //Paciente paciente = new Paciente(id, nome, email, senha, papel, cpf, telefone, sexo, dataNascimento);
        Paciente paciente = new Paciente(nome, email, senha, papel, cpf, telefone, sexo, dataNascimento);
        //dao.insert(paciente);
        response.sendRedirect("lista");
    }

    private void listaConsulta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Consulta> listaConsultas = daoConsulta.getAll();
        request.setAttribute("listaConsultas", listaConsultas);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/paciente/consultas-paciente/lista.jsp");
        dispatcher.forward(request, response);
    }
}
