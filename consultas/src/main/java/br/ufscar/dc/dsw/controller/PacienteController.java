package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.dao.PacienteDAO;
import br.ufscar.dc.dsw.domain.Paciente;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.utils.Erro;

@WebServlet(urlPatterns = "/pacientes/*")
public class PacienteController extends HttpServlet {
    
    private static final long serialVersionUID = 1L;

    private PacienteDAO dao;

    @Override
    public void init() {
        dao = new PacienteDAO();
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
        } else if (!usuario.getPapel().equals("ADMIN")) {
            erros.add("Acesso não autorizado!");
            erros.add("Apenas Papel [ADMIN] tem acesso a essa página.");
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
                    apresentaFormCadastroPaciente(request, response);
                    break;
                case "/insercao":
                    inserePaciente(request, response);
                    break;
                case "/remocao":
                    removePaciente(request, response);
                    break;
                case "/edicao":
                    apresentaFormEdicaoPaciente(request, response);
                    break;
                case "/atualizacao":
                    atualize(request, response);
                    break;
                default:
                    listaPaciente(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }
    }

    private void listaPaciente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Usuario> listaPacientes = dao.getAll();
        request.setAttribute("listaPacientes", listaPacientes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/admin/pacientes/lista.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormEdicaoPaciente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Paciente paciente = dao.get(id);
        request.setAttribute("paciente", paciente);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/admin/pacientes/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void atualize(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Long id = Long.parseLong(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String papel = request.getParameter("papel");
        String cpf = request.getParameter("cpf");
        String telefone = request.getParameter("telefone");
        String sexo = request.getParameter("sexo");
        String dataNascimento = request.getParameter("dataNascimento");
        
        Paciente paciente = new Paciente(id, nome, email, senha, papel, cpf, telefone, sexo, dataNascimento);
        dao.update(paciente);
        response.sendRedirect("lista");
    }

    private void apresentaFormCadastroPaciente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/admin/pacientes/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void inserePaciente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String papel = request.getParameter("papel");
        String cpf = request.getParameter("cpf");
        String telefone = request.getParameter("telefone");
        String sexo = request.getParameter("sexo");
        String dataNascimento = request.getParameter("dataNascimento");
        
        Paciente paciente = new Paciente(nome, email, senha, papel, cpf, telefone, sexo, dataNascimento);
        dao.insert(paciente);
        response.sendRedirect("lista");
    }

    private void removePaciente(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));

        Paciente paciente = new Paciente(id);
        dao.delete(paciente);
        response.sendRedirect("lista");
    }
}
