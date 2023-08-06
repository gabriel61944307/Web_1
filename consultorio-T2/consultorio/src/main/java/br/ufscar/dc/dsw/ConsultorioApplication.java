package br.ufscar.dc.dsw;

//import java.math.BigDecimal;
import java.time.LocalDate;
// import java.time.LocalDateTime;
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

@SpringBootApplication
public class ConsultorioApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsultorioApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(IUsuarioDAO usuarioDAO, IPacienteDAO pacienteDAO, IMedicoDAO medicoDAO, IConsultaDAO consultaDAO, BCryptPasswordEncoder encoder) {
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

			//System.out.println("PACIENTES: " + pacienteDAO.findAll());

			// Usuario u2 = new Usuario();
			// u2.setUsername("beltrano");
			// u2.setPassword(encoder.encode("123"));
			// u2.setCPF("985.849.614-10");
			// u2.setName("Beltrano Andrade");
			// u2.setRole("ROLE_USER");
			// u2.setEnabled(true);
			// usuarioDAO.save(u2);
			
			// Usuario u3 = new Usuario();
			// u3.setUsername("fulano");
			// u3.setPassword(encoder.encode("123"));
			// u3.setCPF("367.318.380-04");
			// u3.setName("Fulano Silva");
			// u3.setRole("ROLE_USER");
			// u3.setEnabled(true);
			// usuarioDAO.save(u3);
			
			// Editora e1 = new Editora();
			// e1.setCNPJ("55.789.390/0008-99");
			// e1.setNome("Companhia das Letras");
			// editoraDAO.save(e1);
			
			// Editora e2 = new Editora();
			// e2.setCNPJ("71.150.470/0001-40");
			// e2.setNome("Record");
			// editoraDAO.save(e2);
			
			// Editora e3 = new Editora();
			// e3.setCNPJ("32.106.536/0001-82");
			// e3.setNome("Objetiva");
			// editoraDAO.save(e3);
			
			// Livro l1 = new Livro();
			// l1.setTitulo("Ensaio sobre a Cegueira");
			// l1.setAutor("José Saramago");
			// l1.setAno(1995);
			// l1.setPreco(BigDecimal.valueOf(54.9));
			// l1.setEditora(e1);
			// livroDAO.save(l1);
			
			// Livro l2 = new Livro();
			// l2.setTitulo("Cem anos de Solidão");
			// l2.setAutor("Gabriel Garcia Márquez");
			// l2.setAno(1977);
			// l2.setPreco(BigDecimal.valueOf(59.9));
			// l2.setEditora(e2);
			// livroDAO.save(l2);
			
			// Livro l3 = new Livro();
			// l3.setTitulo("Diálogos Impossíveis");
			// l3.setAutor("Luis Fernando Verissimo");
			// l3.setAno(2012);
			// l3.setPreco(BigDecimal.valueOf(22.9));
			// l3.setEditora(e3);
			// livroDAO.save(l3);

			// Livro l4 = new Livro();
			// l4.setTitulo("Livro Teste");
			// l4.setAutor("Autor Teste");
			// l4.setAno(2023);
			// l4.setPreco(BigDecimal.valueOf(10));
			// l4.setEditora(e3);
			// livroDAO.save(l4);
		};
	}
}
