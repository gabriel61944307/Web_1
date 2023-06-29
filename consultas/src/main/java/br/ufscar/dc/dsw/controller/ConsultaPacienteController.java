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

/*
 * OBS: ainda tem um problema no formulário pra edição da consulta: precisa dar um jeito de manter 
 * os valores atuais pré-definidos no formulário pra não precisar inserir todos novamente
 */

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado"); 
        Erro erros = new Erro();

        
       // System.out.println(usuario.getClass());

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

        //System.out.println("CLASSE: "+usuario.getClass());

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
                    removeConsulta(request, response);
                    break;
                case "/edicao":
                    apresentaFormEdicaoConsulta(request, response);
                    break;
                case "/atualizacao":
                    atualizaConsulta(request, response);
                    break;
                default:
                    listaConsulta(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }
    }

    private void apresentaFormCadastroConsulta(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Usuario> listaMedicos = daoMedico.getAll();
        request.setAttribute("listaMedicos", listaMedicos);
        RequestDispatcher dispatcher = request
                .getRequestDispatcher("/logado/paciente/consultas-paciente/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void insereConsulta(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        Paciente paciente = (Paciente) request.getSession().getAttribute("usuarioLogado");
        String cpfPaciente = paciente.getCpf();

        String crmMedico = request.getParameter("crm");
        String data = request.getParameter("data");
        String hora = request.getParameter("hora");
        //:00 Necessário pois o banco está voltando o horario com segundos no final
        String dataHora = data + " " + hora + ":00";

        Consulta consulta = new Consulta(cpfPaciente, crmMedico, dataHora);

        boolean flag = true;
        for(Consulta cst : daoConsulta.getConsultasByCpfPaciente(cpfPaciente))
            if(cst.getDataHora().equals(dataHora)) flag = false;
            
        for(Consulta cst : daoConsulta.getAllbyCRM(crmMedico))
            if(cst.getDataHora().equals(dataHora)) flag = false;

        if(flag){
            daoConsulta.insert(consulta);
            response.sendRedirect("lista");
        }
        else{
            request.setAttribute("disponibilidade", false);
            List<Usuario> listaMedicos = daoMedico.getAll();
            request.setAttribute("listaMedicos", listaMedicos);
            RequestDispatcher dispatcher = request
                .getRequestDispatcher("/logado/paciente/consultas-paciente/formulario.jsp");
        dispatcher.forward(request, response);

            //response.sendRedirect("cadastro");
        }
        
    }

    private void listaConsulta(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtém o usuário atualmente logado na sessão
        Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");

        Paciente paciente = (Paciente) usuarioLogado;
        String cpfPaciente = paciente.getCpf();

        List<Consulta> listaConsultas = daoConsulta.getConsultasByCpfPaciente(cpfPaciente);
        request.setAttribute("listaConsultas", listaConsultas);
        RequestDispatcher dispatcher = request
                .getRequestDispatcher("/logado/paciente/consultas-paciente/lista.jsp");
        dispatcher.forward(request, response);
        
    }

    private void removeConsulta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));

        System.out.println(id);

        Consulta consulta = new Consulta(id);
        daoConsulta.delete(consulta);
        response.sendRedirect("lista");
    }

    private void apresentaFormEdicaoConsulta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Usuario> listaMedicos = daoMedico.getAll();
        request.setAttribute("listaMedicos", listaMedicos);

        Long id = Long.parseLong(request.getParameter("id"));
        Consulta consulta = daoConsulta.get(id);
        request.setAttribute("consulta", consulta);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/paciente/consultas-paciente/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void atualizaConsulta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        Long id = Long.parseLong(request.getParameter("id"));

        // Obtém o usuário atualmente logado na sessão
        Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");

        Paciente paciente = (Paciente) usuarioLogado;
        String cpfPaciente = paciente.getCpf();

        String crmMedico = request.getParameter("crm");
        String data = request.getParameter("data");
        String hora = request.getParameter("hora");
        String dataHora = data + " " + hora;

        Consulta consulta = new Consulta(id, cpfPaciente, crmMedico, dataHora);
        daoConsulta.update(consulta);

        response.sendRedirect("lista");
    }

}
