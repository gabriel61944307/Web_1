package br.ufscar.dc.dsw;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.ufscar.dc.dsw.dao.IPacienteDAO;
import br.ufscar.dc.dsw.domain.Paciente;
import br.ufscar.dc.dsw.dao.IUsuarioDAO;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.dao.IMedicoDAO;
import br.ufscar.dc.dsw.domain.Medico;
import br.ufscar.dc.dsw.dao.IConsultaDAO;
import br.ufscar.dc.dsw.domain.Consulta;
import br.ufscar.dc.dsw.domain.Especialidade;
import br.ufscar.dc.dsw.dao.IEspecialidadeDAO;

@SpringBootApplication
public class ConsultorioApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsultorioApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(IUsuarioDAO usuarioDAO, IPacienteDAO pacienteDAO, IMedicoDAO medicoDAO, IConsultaDAO consultaDAO, IEspecialidadeDAO especialidadeDAO, BCryptPasswordEncoder encoder) {
		return (args) -> {

			Paciente p1 = new Paciente();
			p1.setNome("Paciente Teste");
			p1.setRole("ROLE_PACIENTE");
			p1.setPassword(encoder.encode("123"));
			p1.setEmail("paciente@email.com");
			p1.setCPF("123.123.123-12");
			p1.setTelefone("(11) 01234-1234");
			p1.setSexo("M");
			p1.setDataNascimento(LocalDate.parse("1990-01-01"));
			p1.setEnabled(true);
			pacienteDAO.save(p1);
			
			Usuario u1 = new Usuario();
			u1.setPassword(encoder.encode("admin"));
			u1.setEmail("admin@email.com");
			u1.setNome("Administrador");
			u1.setRole("ROLE_ADMIN");
			u1.setEnabled(true);
			usuarioDAO.save(u1);

			Medico m1 = new Medico();
			m1.setPassword(encoder.encode("123"));
			m1.setEmail("medico@email.com");
			m1.setNome("Médico Teste");
			m1.setCRM("12.345/SP");
			m1.setEspecialidade("Cardiologia");
			m1.setRole("ROLE_MEDICO");
			m1.setEnabled(true);
			medicoDAO.save(m1);

			Consulta c1 = new Consulta();
			c1.setDataConsulta(LocalDate.of(2023, 8, 2));
			c1.setHoraConsulta(LocalTime.of(14, 30));
			c1.setMedico(m1);
			c1.setPaciente(p1);
			p1.addConsultas(c1);
			consultaDAO.save(c1);

			Especialidade e1 = new Especialidade();
			e1.setEspecialidade("Cardiologia");
			especialidadeDAO.save(e1);
			
			Especialidade e2 = new Especialidade();
			e2.setEspecialidade("Dermatologia");
			especialidadeDAO.save(e2);
			
			Especialidade e3 = new Especialidade();
			e3.setEspecialidade("Neurologia");
			especialidadeDAO.save(e3);
			
			Especialidade e4 = new Especialidade();
			e4.setEspecialidade("Psiquiatria");
			especialidadeDAO.save(e4);
			
			Especialidade e5 = new Especialidade();
			e5.setEspecialidade("Endocrinologia");
			especialidadeDAO.save(e5);
			
			Especialidade e6 = new Especialidade();
			e6.setEspecialidade("Ortopedia");
			especialidadeDAO.save(e6);
			
			Especialidade e7 = new Especialidade();
			e7.setEspecialidade("Oftalmologia");
			especialidadeDAO.save(e7);
			
			Especialidade e8 = new Especialidade();
			e8.setEspecialidade("Anestesiologia");
			especialidadeDAO.save(e8);
			
			Especialidade e9 = new Especialidade();
			e9.setEspecialidade("Ginecologia");
			especialidadeDAO.save(e9);
			
			Especialidade e10 = new Especialidade();
			e10.setEspecialidade("Pediatria");
			especialidadeDAO.save(e10);
			
			Especialidade e11 = new Especialidade();
			e11.setEspecialidade("Urologia");
			especialidadeDAO.save(e11);

			Especialidade e12 = new Especialidade();
			e12.setEspecialidade("Dentista");
			especialidadeDAO.save(e12);

			Especialidade e13 = new Especialidade();
			e13.setEspecialidade("Oncologia");
			especialidadeDAO.save(e13);

			Especialidade e14 = new Especialidade();
			e14.setEspecialidade("Gastroenterologia");
			especialidadeDAO.save(e14);

			Especialidade e15 = new Especialidade();
			e15.setEspecialidade("Radiologia");
			especialidadeDAO.save(e15);
		};
	}
}
