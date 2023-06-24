package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.dao.MedicoDAO;
import br.ufscar.dc.dsw.domain.Medico;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.utils.Erro;

@WebServlet(urlPatterns = "/medicos/*")
public class MedicoController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private MedicoDAO dao;

    @Override
    public void init() {
        dao = new MedicoDAO();
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
                    apresentaFormCadastroMedico(request, response);
                    break;
                case "/insercao":
                    insereMedico(request, response);
                    break;
                case "/remocao":
                    removeMedico(request, response);
                    break;
                case "/edicao":
                    apresentaFormEdicaoMedico(request, response);
                    break;
                case "/atualizacao":
                    atualize(request, response);
                    break;
                default:
                    listaMedico(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }
    }

    private void listaMedico(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Usuario> listaMedicos = dao.getAll();
        request.setAttribute("listaMedicos", listaMedicos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/admin/medicos/lista.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormEdicaoMedico(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Medico medico = dao.get(id);
        request.setAttribute("medico", medico);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/admin/medicos/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void atualize(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Long id = Long.parseLong(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String papel = request.getParameter("papel");
        String crm = request.getParameter("crm");
        String especialidade = request.getParameter("especialidade");

        Medico medico = new Medico(id, nome, email, senha, papel, crm, especialidade);
        dao.update(medico);
        response.sendRedirect("lista");
    }

    private void apresentaFormCadastroMedico(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/admin/medicos/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void insereMedico(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String papel = request.getParameter("papel");
        String crm = request.getParameter("crm");
        String especialidade = request.getParameter("especialidade");

        Medico medico = new Medico(nome, email, senha, papel, crm, especialidade);
        dao.insert(medico);
        response.sendRedirect("lista");
    }

    private void removeMedico(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));

        Medico medico = new Medico(id);
        dao.delete(medico);
        response.sendRedirect("lista");
    }
}
