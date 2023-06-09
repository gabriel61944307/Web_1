package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.ufscar.dc.dsw.domain.Consulta;

public class ConsultaDAO extends GenericDAO {

    public void insert(Consulta consulta) {

        String sql = "INSERT INTO Consulta (cpf_paciente, crm_medico, data_hora) VALUES (?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, consulta.getCpfPaciente());
            statement.setString(2, consulta.getCrmMedico());
            statement.setString(3, consulta.getDataHora());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Consulta> getAll() {

        List<Consulta> listaConsultas = new ArrayList<>();

        String sql = "SELECT * FROM Consulta ORDER BY data_hora";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String cpfPaciente = resultSet.getString("cpf_paciente");
                String crmMedico = resultSet.getString("crm_medico");
                String dataHora = resultSet.getString("data_hora");
                Consulta consulta = new Consulta(id, cpfPaciente, crmMedico, dataHora);
                listaConsultas.add(consulta);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaConsultas;
    }

    public List<Consulta> getAllbyCRM(String crm) {

        List<Consulta> listaConsultas = new ArrayList<>();

        String sql = "SELECT * FROM Consulta WHERE crm_medico = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, crm);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String cpfPaciente = resultSet.getString("cpf_paciente");
                String crmMedico = resultSet.getString("crm_medico");
                String dataHora = resultSet.getString("data_hora");
                Consulta consulta = new Consulta(id, cpfPaciente, crmMedico, dataHora);
                listaConsultas.add(consulta);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaConsultas;
    }

    public List<Consulta> getConsultasByCpfPaciente(String cpfPaciente) {
        List<Consulta> listaConsultas = new ArrayList<>();

        String sql = "SELECT * FROM Consulta WHERE cpf_paciente = ? ORDER BY data_hora";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, cpfPaciente);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String crmMedico = resultSet.getString("crm_medico");
                String dataHora = resultSet.getString("data_hora");
                Consulta consulta = new Consulta(id, cpfPaciente, crmMedico, dataHora);
                listaConsultas.add(consulta);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaConsultas;
    }

    public List<Consulta> getConsultasByCrmMedico(String crmMedico) {
        List<Consulta> listaConsultas = new ArrayList<>();

        String sql = "SELECT * FROM Consulta WHERE crm_medico = ? ORDER BY data_hora";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, crmMedico);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String cpfPaciente = resultSet.getString("cpf_paciente");
                String dataHora = resultSet.getString("data_hora");
                Consulta consulta = new Consulta(id, cpfPaciente, crmMedico, dataHora);
                listaConsultas.add(consulta);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaConsultas;
    }

    public List<String> getNomeMedico(String cpfPaciente) {
        List<String> resp = new ArrayList<>();
        List<Consulta> lista = getConsultasByCpfPaciente(cpfPaciente);
        for (Consulta con : lista) {
            MedicoDAO daoMedico = new MedicoDAO();
            resp.add(daoMedico.getByCRM(con.getCrmMedico()).getNome());
        }
        return resp;
    }

    public List<String> getNomePaciente(String crmMedico) {
        List<String> resp = new ArrayList<>();
        List<Consulta> lista = getConsultasByCrmMedico(crmMedico);
        for (Consulta con : lista) {
            PacienteDAO daoPaciente = new PacienteDAO();
            resp.add(daoPaciente.getByCPF(con.getCpfPaciente()).getNome());
        }
        return resp;
    }

    public void delete(Consulta consulta) {
        try {
            String sql = "DELETE FROM Consulta WHERE id = ?";
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, consulta.getId());

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Consulta get(Long id) {
        Consulta consulta = null;

        try {
            String sql = "SELECT * FROM Consulta WHERE id = ?";
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String cpfPaciente = resultSet.getString("cpf_paciente");
                String crmMedico = resultSet.getString("crm_medico");
                String dataHora = resultSet.getString("data_hora");

                consulta = new Consulta(id, cpfPaciente, crmMedico, dataHora);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return consulta;
    }

    public void update(Consulta consulta) {
        try {
            String sql = "UPDATE Consulta SET cpf_paciente = ?, crm_medico = ?, data_hora = ? WHERE id = ?";
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, consulta.getCpfPaciente());
            statement.setString(2, consulta.getCrmMedico());
            statement.setString(3, consulta.getDataHora());
            statement.setLong(4, consulta.getId());

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean verificaDisponibilidade(String cpfPaciente, String crmMedico, String dataHora) {
        boolean disponivel = true;

        List<Consulta> consultasPaciente = getConsultasByCpfPaciente(cpfPaciente);
        for (Consulta consulta : consultasPaciente) {
            if (consulta.getDataHora().equals(dataHora)) {
                disponivel = false;
                break;
            }
        }

        if (disponivel) {
            List<Consulta> consultasMedico = getAllbyCRM(crmMedico);
            for (Consulta consulta : consultasMedico) {
                if (consulta.getDataHora().equals(dataHora)) {
                    disponivel = false;
                    break;
                }
            }
        }

        return disponivel;
    }

    public List<String> getDatasFormatadas(List<Consulta> listaConsultas) {
        SimpleDateFormat formatoOriginal = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat formatoDesejado = new SimpleDateFormat("dd/MM/yyyy");

        
        List<String> listaData = new ArrayList<>();

        for (Consulta consulta : listaConsultas) {
            try {
                Date dataHora = formatoOriginal.parse(consulta.getDataHora());
                String dataFormatada = formatoDesejado.format(dataHora);
                listaData.add(dataFormatada);
            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }
        }

        return listaData;
    }

    public List<String> getHorasFormatadas(List<Consulta> listaConsultas) {
        List<String> listaHora = new ArrayList<>();
        DateTimeFormatter formatoHoraOriginal = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter formatoHoraDesejado = DateTimeFormatter.ofPattern("HH:mm");

        for (Consulta consulta : listaConsultas) {
            try {
                LocalDateTime dataHora = LocalDateTime.parse(consulta.getDataHora(), formatoHoraOriginal);
                String horaFormatada = dataHora.format(formatoHoraDesejado);
                listaHora.add(horaFormatada);
            } catch (DateTimeParseException e) {
                e.printStackTrace();
            }
        }

        return listaHora;
    }
}
