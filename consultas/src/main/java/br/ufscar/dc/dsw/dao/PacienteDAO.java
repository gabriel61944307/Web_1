package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Paciente;
import br.ufscar.dc.dsw.domain.Usuario;

public class PacienteDAO extends UsuarioDAO {

    public void insert(Paciente paciente, Usuario usuario) {
        
        Long id = super.insert(usuario);


        String sql = "INSERT INTO Paciente (id, cpf, telefone, sexo, data_nascimento) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            
            statement.setLong(1, id);
            statement.setString(2, paciente.getCpf());
            statement.setString(3, paciente.getTelefone());
            statement.setString(4, paciente.getSexo());
            statement.setString(5, paciente.getDataNascimento());

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Paciente> getAll() {
        List<Paciente> listaPacientes = new ArrayList<>();

        String sql = "SELECT" + 
        " Usuario.nome, Usuario.email, Usuario.senha, Usuario.papel, Paciente.cpf, Paciente.telefone, Paciente.sexo, Paciente.data_nascimento" +
        " FROM Usuario" +
        " JOIN Paciente ON Usuario.id = Paciente.id";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String papel = resultSet.getString("papel");
                String cpf = resultSet.getString("cpf");
                String nome = resultSet.getString("nome");
                String telefone = resultSet.getString("telefone");
                String sexo = resultSet.getString("sexo");
                String dataNascimento = resultSet.getString("data_nascimento");
                Paciente paciente = new Paciente(nome, email, senha, papel, cpf, telefone, sexo, dataNascimento);
                listaPacientes.add(paciente);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaPacientes;
    }

    public void delete(Paciente paciente) {
        String sql = "DELETE FROM Paciente WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, paciente.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Paciente paciente) {
        String sql = "UPDATE Paciente SET email = ?, senha = ?, cpf = ?, nome = ?, telefone = ?, sexo = ?, data_nascimento = ?";
        sql += " WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, paciente.getEmail());
            statement.setString(2, paciente.getSenha());
            statement.setString(3, paciente.getCpf());
            statement.setString(4, paciente.getNome());
            statement.setString(5, paciente.getTelefone());
            statement.setString(6, paciente.getSexo());
            statement.setString(7, paciente.getDataNascimento());
            statement.setLong(8, paciente.getId());

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Paciente get(Long id) {
        Paciente paciente = null;

        String sql = "SELECT " +
        " Usuario.nome, Usuario.email, Usuario.senha, Paciente.cpf, Paciente.telefone, Paciente.sexo, Paciente.data_nascimento" +
        " FROM Usuario" +
        " JOIN Paciente ON Usuario.id = Paciente.id" +
        " WHERE Usuario.id = <id_do_usuario>";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String papel = resultSet.getString("papel");
                String cpf = resultSet.getString("cpf");
                String nome = resultSet.getString("nome");
                String telefone = resultSet.getString("telefone");
                String sexo = resultSet.getString("sexo");
                String dataNascimento = resultSet.getString("data_nascimento");
                paciente = new Paciente(nome, email, senha, papel, cpf, telefone, sexo, dataNascimento);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return paciente;
    }
}
