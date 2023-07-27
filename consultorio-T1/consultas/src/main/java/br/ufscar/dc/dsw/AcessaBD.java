package br.ufscar.dc.dsw;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AcessaBD {

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/Consultorio?useTimezone=true&serverTimezone=UTC", "root" ,"root"
            );
            Statement stmt = con.createStatement();
            String query = "SELECT c.id, p.nome AS nome_paciente, m.nome AS nome_medico, c.data_hora FROM Consulta c " +
                           "INNER JOIN Paciente p ON c.cpf_paciente = p.cpf " +
                           "INNER JOIN Medico m ON c.crm_medico = m.crm";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                System.out.print(rs.getString("nome_paciente"));
                System.out.print(", " + rs.getString("nome_medico"));
                System.out.println(", " + rs.getTimestamp("data_hora"));
            }
            stmt.close();
            con.close();
        } catch (ClassNotFoundException e) {
            System.out.println("A classe do driver de conexão não foi encontrada!");
        } catch (SQLException e) {
            System.out.println("O comando SQL não pode ser executado!");
        }
    }
}
