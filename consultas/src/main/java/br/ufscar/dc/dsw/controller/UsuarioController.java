package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.utils.Erro;

@WebServlet(urlPatterns = "/usuarios/*")
public class UsuarioController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UsuarioDAO dao;

    @Override
    public void init() {
        dao = new UsuarioDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
                    apresentaFormCadastroUsuario(request, response);
                    break;
                case "/insercao":
                    insereUsuario(request, response);
                    break;
                case "/remocao":
                    removeUsuario(request, response);
                    break;
                case "/edicao":
                    apresentaFormEdicaoUsuario(request, response);
                    break;
                case "/atualizacao":
                    atualizaUsuario(request, response);
                    break;
                default:
                    listaUsuarios(request, response);
                    break;
            }
        } catch (RuntimeException |IOException |ServletException e) {
            throw new ServletException(e);
        }
    }

    private void listaUsuarios(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Usuario> listaUsuarios = dao.getAll();
        request.setAttribute("listaUsuarios", listaUsuarios);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/admin/usuarios/lista.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormCadastroUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher =  request.getRequestDispatcher("/logado/admin/usuarios/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void insereUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String papel = request.getParameter("papel");

        Usuario usuario = new Usuario(nome, email, senha, papel);
        dao.insert(usuario);

        response.sendRedirect("lista");
    }

    private void removeUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        
        Usuario usuario = new Usuario(id);
        dao.delete(usuario);

        response.sendRedirect("lista");
    }

    private void apresentaFormEdicaoUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Usuario usuario = dao.get(id);
        request.setAttribute("usuario", usuario);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/admin/usuarios/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void atualizaUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        Long id = Long.parseLong(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String papel = request.getParameter("papel");

        Usuario usuario = new Usuario(id, nome, email, senha, papel);
        dao.update(usuario);

        response.sendRedirect("lista");
    }
}
