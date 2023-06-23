package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Medico;

public class MedicoDAO extends UsuarioDAO {

    public void insert(Medico medico) {
        String sql = "INSERT INTO Medico (email, senha, crm, nome, especialidade) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, medico.getEmail());
            statement.setString(2, medico.getSenha());
            statement.setString(3, medico.getCrm());
            statement.setString(4, medico.getNome());
            statement.setString(5, medico.getEspecialidade());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Medico> getAll() {
        List<Medico> listaMedicos = new ArrayList<>();

        String sql = "SELECT * FROM Medico";

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
                Medico medico = new Medico(id, email, senha, papel, crm, nome, especialidade);
                listaMedicos.add(medico);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaMedicos;
    }

    public void delete(Medico medico) {
        String sql = "DELETE FROM Medico WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, medico.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Medico medico) {
        String sql = "UPDATE Medico SET email = ?, senha = ?, crm = ?, nome = ?, especialidade = ?";
        sql += " WHERE id = ?";

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

        String sql = "SELECT * FROM Medico WHERE id = ?";

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
                medico = new Medico(id, email, senha, papel, crm, nome, especialidade);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return medico;
    }
}
