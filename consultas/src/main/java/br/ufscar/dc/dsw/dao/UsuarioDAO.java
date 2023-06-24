package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Usuario;

public class UsuarioDAO extends GenericDAO {

    // Este insert é necessário no caso da criação de um usuário do tipo ADMIN, de modo que seu atributo papel é definido adequadamente.
    public long insert(Usuario usuario) {
        return insert(usuario, "ADMIN");
    }

    // Para a criação de um paciente, por exemplo, a função insert em PacienteDAO chamaria este método com o atributo "PACIENTE" no lugar de papel.
    public long insert(Usuario usuario, String papel) {
        long userID = 0;
        String sql = "INSERT INTO Usuario (nome, email, senha, papel) VALUES (?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            //statement = conn.prepareStatement(sql);
            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getEmail());
            statement.setString(3, usuario.getSenha());
            statement.setString(4, papel);
            statement.executeUpdate();


            ResultSet rs = statement.getGeneratedKeys();
			if (rs.next()) {
				userID = rs.getLong(1);
			}

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userID;
    }

    public Usuario getbyEmail(String email) {
        Usuario usuario = null;

        String sql = "SELECT * FROM Usuario WHERE email = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String nome = resultSet.getString("nome");
                String senha = resultSet.getString("senha");
                String papel = resultSet.getString("papel");

                usuario = new Usuario(id, nome, email, senha, papel);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return usuario;
    }

    public void update(Usuario usuario) {
        try {
            String sql = "UPDATE Usuario SET nome = ?, email = ?, senha = ? WHERE id = ?";
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getEmail());
            statement.setString(3, usuario.getSenha());
            statement.setLong(4, usuario.getId());

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Usuario usuario) {
        try {
            String sql = "DELETE FROM Usuario WHERE id = ?";
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, usuario.getId());

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Usuario get(Long id) {
        Usuario usuario = null;

        try {
            String sql = "SELECT * FROM Usuario WHERE id = ?";
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String papel = resultSet.getString("papel");

                usuario = new Usuario(id, nome, email, senha, papel);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuario;
    }

    public List<Usuario> getAll() {
        List<Usuario> listaUsuarios = new ArrayList<>();

        try {
            //String sql = "SELECT * FROM Usuario";
            String sql = "SELECT * FROM Usuario WHERE papel = 'ADMIN'";
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String nome = resultSet.getString("nome");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String papel = resultSet.getString("papel");

                Usuario usuario = new Usuario(id, nome, email, senha, papel);
                listaUsuarios.add(usuario);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaUsuarios;
    }

}