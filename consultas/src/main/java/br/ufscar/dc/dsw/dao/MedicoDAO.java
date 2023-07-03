package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Medico;
import br.ufscar.dc.dsw.domain.Usuario;

public class MedicoDAO extends UsuarioDAO {

    public void insert(Medico medico) {
        try {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            // Inserção com a definição adequada do papel deste usuário.
            long idUsuario = usuarioDAO.insert(medico, "MEDICO");

            // Inserir na tabela Medico
            String medicoSql = "INSERT INTO Medico (id, crm, especialidade) VALUES (?, ?, ?)";
            Connection conn = this.getConnection();
            PreparedStatement medicoStatement = conn.prepareStatement(medicoSql);

            medicoStatement.setLong(1, idUsuario);
            medicoStatement.setString(2, medico.getCrm());
            medicoStatement.setString(3, medico.getEspecialidade());
            medicoStatement.executeUpdate();

            medicoStatement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Usuario> getAll() {
        List<Usuario> listaUsuarios = new ArrayList<>();

        String sql = "SELECT" +
                " Usuario.id, Usuario.nome, Usuario.email, Usuario.senha, Usuario.papel, Medico.crm, Medico.especialidade"
                +
                " FROM Usuario" +
                " JOIN Medico ON Usuario.id = Medico.id";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String papel = resultSet.getString("papel");
                String crm = resultSet.getString("crm");
                String nome = resultSet.getString("nome");
                String especialidade = resultSet.getString("especialidade");
                Medico medico = new Medico(id, nome, email, senha, papel, crm, especialidade);
                listaUsuarios.add(medico); // Adiciona como Usuario, fazendo um cast
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaUsuarios;
    }

    public void delete(Medico medico) {

        String medicoSql = "DELETE FROM Medico WHERE id = ?";
        String usuarioSql = "DELETE FROM Usuario WHERE id = ?";

        try {
            Connection conn = this.getConnection();

            // Excluir o médico da tabela Medico
            PreparedStatement medicoStatement = conn.prepareStatement(medicoSql);
            medicoStatement.setLong(1, medico.getId());
            medicoStatement.executeUpdate();

            // Excluir o usuário correspondente da tabela Usuario
            PreparedStatement usuarioStatement = conn.prepareStatement(usuarioSql);
            usuarioStatement.setLong(1, medico.getId());
            usuarioStatement.executeUpdate();

            medicoStatement.close();
            usuarioStatement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Medico medico) {
        String sql = "UPDATE Medico AS m " +
                "INNER JOIN Usuario AS u ON m.id = u.id " +
                "SET u.email = ?, " +
                "u.senha = ?, " +
                "m.crm = ?, " +
                "u.nome = ?, " +
                "m.especialidade = ? " +
                "WHERE m.id = ?;";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, medico.getEmail());
            statement.setString(2, medico.getSenha());
            statement.setString(3, medico.getCrm());
            statement.setString(4, medico.getNome());
            statement.setString(5, medico.getEspecialidade());
            statement.setLong(6, medico.getId());

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Medico get(Long id) {
        Medico medico = null;

        String sql = "SELECT " +
                " Usuario.id, Usuario.nome, Usuario.email, Usuario.senha, Usuario.papel, Medico.crm, Medico.especialidade"
                +
                " FROM Usuario" +
                " JOIN Medico ON Usuario.id = Medico.id" +
                " WHERE Usuario.id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String papel = resultSet.getString("papel");
                String crm = resultSet.getString("crm");
                String nome = resultSet.getString("nome");
                String especialidade = resultSet.getString("especialidade");
                medico = new Medico(id, nome, email, senha, papel, crm, especialidade);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return medico;
    }

    public Medico getByCRM(String crm) {
        Medico medico = null;

        String sql = "SELECT " +
                " Usuario.id, Usuario.nome, Usuario.email, Usuario.senha, Usuario.papel, Medico.especialidade" +
                " FROM Usuario" +
                " JOIN Medico ON Usuario.id = Medico.id" +
                " WHERE Medico.crm = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, crm);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String papel = resultSet.getString("papel");
                String nome = resultSet.getString("nome");
                String especialidade = resultSet.getString("especialidade");
                medico = new Medico(id, nome, email, senha, papel, crm, especialidade);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return medico;
    }

    public List<Usuario> getByEspecialidade(String especialidade) {
        List<Usuario> listaMedicosEspecialidade = new ArrayList<>();

        String sql = "SELECT " +
                " Usuario.id, Usuario.nome, Usuario.email, Usuario.senha, Usuario.papel, Medico.crm, Medico.especialidade"
                +
                " FROM Usuario" +
                " JOIN Medico ON Usuario.id = Medico.id" +
                " WHERE Medico.especialidade = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, especialidade);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String papel = resultSet.getString("papel");
                String crm = resultSet.getString("crm");
                String nome = resultSet.getString("nome");
                Medico medico = new Medico(id, nome, email, senha, papel, crm, especialidade);
                listaMedicosEspecialidade.add(medico);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaMedicosEspecialidade;
    }

    // Lista com especialidades pré-definidas para o cadastro de médico, para evitar possíveis erros de digitação.
    public List<String> inicializaEspecialidades() {
        List<String> listaEspecialidades = new ArrayList<>();

        listaEspecialidades.add("Cardiologia");
        listaEspecialidades.add("Dermatologia");
        listaEspecialidades.add("Endocrinologia");
        listaEspecialidades.add("Ginecologia");
        listaEspecialidades.add("Neurologia");
        listaEspecialidades.add("Oftalmologia");
        listaEspecialidades.add("Oncologia");
        listaEspecialidades.add("Ortopedia");
        listaEspecialidades.add("Pediatria");
        listaEspecialidades.add("Psiquiatria");

        return listaEspecialidades;
    }
}
