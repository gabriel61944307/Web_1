package br.ufscar.dc.dsw.dao;

//import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import br.ufscar.dc.dsw.domain.Medico;
import br.ufscar.dc.dsw.domain.Paciente;
//import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.Usuario;

public class PacienteDAO extends UsuarioDAO {


    public void insert(Paciente paciente) {
        try {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            // Inserção com a definição adequada do papel deste usuário.
            long idUsuario = usuarioDAO.insert(paciente, "PACIENTE");

            // Inserir na tabela Paciente
            String pacienteSql = "INSERT INTO Paciente (id, cpf, telefone, sexo, data_nascimento) VALUES (?, ?, ?, ?, ?)";
            Connection conn = this.getConnection();
            PreparedStatement pacienteStatement = conn.prepareStatement(pacienteSql);

            // Dúvida: será que precisa verificar email ou cpf repetido?
            pacienteStatement.setLong(1, idUsuario);
            pacienteStatement.setString(2, paciente.getCpf());
            pacienteStatement.setString(3, paciente.getTelefone());
            pacienteStatement.setString(4, paciente.getSexo());
            // OBS SOBRE A DATA DE NASCIMENTO: tem que ser no formato ano-mes-dia tipo 1990-01-01 se não dá ruim, tentei dar um jeito de arrumar isso em outros casos mas não consegui
            pacienteStatement.setString(5, paciente.getDataNascimento());
            pacienteStatement.executeUpdate();

            pacienteStatement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*
    public List<Paciente> getAll() {
        List<Paciente> listaPacientes = new ArrayList<>();

        String sql = "SELECT" + 
        " Usuario.id, Usuario.nome, Usuario.email, Usuario.senha, Usuario.papel, Paciente.cpf, Paciente.telefone, Paciente.sexo, Paciente.data_nascimento" +
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
                Paciente paciente = new Paciente(id, nome, email, senha, papel, cpf, telefone, sexo, dataNascimento);
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
    */

    public List<Usuario> getAll() {
        List<Usuario> listaUsuarios = new ArrayList<>();

        String sql = "SELECT" + 
        " Usuario.id, Usuario.nome, Usuario.email, Usuario.senha, Usuario.papel, Paciente.cpf, Paciente.telefone, Paciente.sexo, Paciente.data_nascimento" +
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
                Paciente paciente = new Paciente(id, nome, email, senha, papel, cpf, telefone, sexo, dataNascimento);
                listaUsuarios.add(paciente); // Adiciona como Usuario, fazendo um cast
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaUsuarios;
    }


    public void delete(Paciente paciente) {

        String pacienteSql = "DELETE FROM Paciente WHERE id = ?";
        String usuarioSql = "DELETE FROM Usuario WHERE id = ?";

        try {
            Connection conn = this.getConnection();

            // Excluir o paciente da tabela Paciente
            PreparedStatement pacienteStatement = conn.prepareStatement(pacienteSql);
            pacienteStatement.setLong(1, paciente.getId());
            pacienteStatement.executeUpdate();

            // Excluir o usuário correspondente da tabela Usuario
            PreparedStatement usuarioStatement = conn.prepareStatement(usuarioSql);
            usuarioStatement.setLong(1, paciente.getId());
            usuarioStatement.executeUpdate();

            pacienteStatement.close();
            usuarioStatement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Paciente paciente) {
        /*
        String sql = "UPDATE Paciente SET email = ?, senha = ?, cpf = ?, nome = ?, telefone = ?, sexo = ?, data_nascimento = ?";
        sql += " WHERE id = ?";
        */

        String sql = "UPDATE Paciente AS p " +
                     "INNER JOIN Usuario AS u ON p.id = u.id " + 
                     "SET u.email = ?, " +
                     "u.senha = ?, " +
                     "p.cpf = ?, " +
                     "u.nome = ?, " +
                     "p.telefone = ?, " +
                     "p.sexo = ?, " +
                     "p.data_nascimento = ? " +
                     "WHERE p.id = ?;";

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
        " Usuario.id, Usuario.nome, Usuario.email, Usuario.senha, Usuario.papel, Paciente.cpf, Paciente.telefone, Paciente.sexo, Paciente.data_nascimento" +
        " FROM Usuario" +
        " JOIN Paciente ON Usuario.id = Paciente.id" +
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
                String cpf = resultSet.getString("cpf");
                String nome = resultSet.getString("nome");
                String telefone = resultSet.getString("telefone");
                String sexo = resultSet.getString("sexo");
                String dataNascimento = resultSet.getString("data_nascimento");
                paciente = new Paciente(id, nome, email, senha, papel, cpf, telefone, sexo, dataNascimento);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return paciente;
    }

    public Paciente getByCPF(String cpf) {
        Paciente paciente = null;

        String sql = "SELECT " +
                " Usuario.id, Usuario.nome, Usuario.email, Usuario.senha, Usuario.papel, Paciente.cpf, Paciente.telefone, " +
                " Paciente.sexo, Paciente.data_nascimento FROM Usuario" +
                " JOIN Paciente ON Usuario.id = Paciente.id" +
                " WHERE Paciente.cpf = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, cpf);
            ResultSet resultSet = statement.executeQuery();


            if (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String papel = resultSet.getString("papel");
                String nome = resultSet.getString("nome");
                String telefone = resultSet.getString("telefone");
                String sexo = resultSet.getString("sexo");
                String dataNascimento = resultSet.getString("data_nascimento");
                //medico = new Medico(id, nome, email, senha, papel, crm, especialidade);

                paciente = new Paciente(id, nome, email, senha, papel, cpf, telefone, sexo, dataNascimento);
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
