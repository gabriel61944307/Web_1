package br.ufscar.dc.dsw.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.dao.UsuarioDAO;

// aqui teria que ser admin...? ou faz uma classe admin? aí teria que tirar aquela parada de admin do dao, mas aí teria que ter uma tabela de admin? e como ficaria, considerando que seria a mesma coisa que Usuario?
@WebServlet(urlPatterns = "/usuarios/*")

public class UsuarioController extends HttpServlet {
    
    private static final long serialVersionUID = 1L;

    private UsuarioDAO dao;

    @Override
    public void init() {
        dao = new UsuarioDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
