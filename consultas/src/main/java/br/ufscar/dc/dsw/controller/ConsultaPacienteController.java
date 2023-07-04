package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.utils.EmailService;
import br.ufscar.dc.dsw.utils.Erro;
import br.ufscar.dc.dsw.dao.ConsultaDAO;
import br.ufscar.dc.dsw.domain.Consulta;
import br.ufscar.dc.dsw.domain.Medico;
import br.ufscar.dc.dsw.domain.Paciente;
import br.ufscar.dc.dsw.dao.MedicoDAO;
import br.ufscar.dc.dsw.dao.PacienteDAO;
import br.ufscar.dc.dsw.domain.Usuario;

import java.io.UnsupportedEncodingException;
import javax.mail.internet.InternetAddress;

@WebServlet(urlPatterns = "/consultas-paciente/*")

public class ConsultaPacienteController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ConsultaDAO daoConsulta;
    private MedicoDAO daoMedico;

    @Override
    public void init() {
        daoConsulta = new ConsultaDAO();
        daoMedico = new MedicoDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
        Erro erros = new Erro();

        request.setAttribute("medicoSelecionado", request.getParameter("crm"));
        request.setAttribute("dataSelecionada", request.getParameter("data"));
        request.setAttribute("horaSelecionada", request.getParameter("hora"));

        if (usuario == null) {
            response.sendRedirect(request.getContextPath());
            return;
        } else if (!usuario.getPapel().equals("PACIENTE")) {
            erros.add("Acesso não autorizado!");
            erros.add("Apenas Papel [PACIENTE] tem acesso a essa página.");
            request.setAttribute("mensagens", erros);
            RequestDispatcher rd = request.getRequestDispatcher("/noAuth.jsp");

            rd.forward(request, response);
            return;
        }

        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "/cadastro":
                    apresentaFormCadastroConsulta(request, response);
                    break;
                case "/insercao":
                    insereConsulta(request, response);
                    break;
                case "/remocao":
                    removeConsulta(request, response);
                    break;
                case "/edicao":
                    apresentaFormEdicaoConsulta(request, response);
                    break;
                case "/atualizacao":
                    atualizaConsulta(request, response);
                    break;
                default:
                    listaConsulta(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }
    }

    private void apresentaFormCadastroConsulta(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Usuario> listaMedicos = daoMedico.getAll();
        request.setAttribute("listaMedicos", listaMedicos);
        RequestDispatcher dispatcher = request
                .getRequestDispatcher("/logado/paciente/consultas-paciente/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void insereConsulta(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");

        Paciente paciente = (Paciente) request.getSession().getAttribute("usuarioLogado");
        String cpfPaciente = paciente.getCpf();

        String crmMedico = request.getParameter("crm");
        String data = request.getParameter("data");
        String hora = request.getParameter("hora");
        // :00 Necessário pois o banco está voltando o horario com segundos no final
        String dataHora = data + " " + hora + ":00";

        Consulta consulta = new Consulta(cpfPaciente, crmMedico, dataHora);

        boolean disponivel = daoConsulta.verificaDisponibilidade(cpfPaciente, crmMedico, dataHora);

        if (disponivel) {
            daoConsulta.insert(consulta);

        // --- ENVIO DE EMAIL ---
        
        EmailService service = new EmailService();
		
		InternetAddress from = new InternetAddress("gabrielrodriguesmalaquias1111@gmail.com", "Admin");
        PacienteDAO DAOpac = new PacienteDAO();
        Paciente pac = DAOpac.getByCPF(cpfPaciente);
        String nome = pac.getNome();
        String email = pac.getEmail();
		InternetAddress to = new InternetAddress(email, nome);
				
		String subject = "Sua consulta foi marcada com sucesso";
		String body = "Olá " + nome + ", este email tem como objetivo lhe informar que sua consulta foi marcada com sucesso para " +
                        consulta.getDataHora() + "\n Atenciosamente administrador do sistema do hospital.";

		service.send(from, to, subject, body);

        MedicoDAO DAOmed = new MedicoDAO();
        Medico med = DAOmed.getByCRM(crmMedico);
        nome = med.getNome();
        email = med.getEmail();
        to = new InternetAddress(email, nome);

        subject = "Você possui uma nova consulta agendada";
		body = "Olá " + nome + ", este email tem como objetivo lhe informar que foi marcada uma consulta com você na seguinte data: " +
                        consulta.getDataHora() + "\n Atenciosamente administrador do sistema do hospital.";

		service.send(from, to, subject, body);

        // --- ENVIO DE EMAIL ---

            response.sendRedirect("lista");
        } else {
            request.setAttribute("disponibilidade", false);
            apresentaFormCadastroConsulta(request, response); // chama novamente o form de cadastro de consulta para
                                                              // mais uma tentativa caso esteja indisponível.
        }

    }

    private void listaConsulta(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtém o usuário atualmente logado na sessão
        Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");

        Paciente paciente = (Paciente) usuarioLogado;
        String cpfPaciente = paciente.getCpf();

        List<Consulta> listaConsultas = daoConsulta.getConsultasByCpfPaciente(cpfPaciente);
        request.setAttribute("listaConsultas", listaConsultas);

        List<String> listaNomes = daoConsulta.getNomeMedico(cpfPaciente);
        request.setAttribute("listaNomes", listaNomes);

        // criação de uma lista de datas formatadas corretamente para a exibição na
        // lista
        List<String> listaData = daoConsulta.getDatasFormatadas(listaConsultas);
        request.setAttribute("listaData", listaData);

        // criação de uma lista de horas formatadas corretamente para a exibição na lista
        List<String> listaHora = daoConsulta.getHorasFormatadas(listaConsultas);
        request.setAttribute("listaHora", listaHora);

        RequestDispatcher dispatcher = request
                .getRequestDispatcher("/logado/paciente/consultas-paciente/lista.jsp");
        dispatcher.forward(request, response);

    }

    private void removeConsulta(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));

        System.out.println(id);

        Consulta consulta = daoConsulta.get(id);
        daoConsulta.delete(consulta);

        // --- ENVIO DE EMAIL ---
        
        EmailService service = new EmailService();
		
		InternetAddress from = new InternetAddress("gabrielrodriguesmalaquias1111@gmail.com", "Admin");
        PacienteDAO DAOpac = new PacienteDAO();
        Paciente pac = DAOpac.getByCPF(consulta.getCpfPaciente());
        String nome = pac.getNome();
        String email = pac.getEmail();
		InternetAddress to = new InternetAddress(email, nome);
				
		String subject = "Sua consulta foi desmarcada com sucesso";
		String body = "Olá " + nome + ", este email tem como objetivo lhe informar que sua consulta do dia " +
                        consulta.getDataHora() + " foi desmarcada." + "\n Atenciosamente administrador do sistema do hospital.";

		service.send(from, to, subject, body);

        MedicoDAO DAOmed = new MedicoDAO();
        Medico med = DAOmed.getByCRM(consulta.getCrmMedico());
        nome = med.getNome();
        email = med.getEmail();
        to = new InternetAddress(email, nome);

        subject = "Uma de suas consultas foi desmarcada";
		body = "Olá " + nome + ", este email tem como objetivo lhe informar que sua consulta do dia: " +
                        consulta.getDataHora() + " foi desmarcada." + "\n Atenciosamente administrador do sistema do hospital.";

		service.send(from, to, subject, body);

        // --- ENVIO DE EMAIL ---

        response.sendRedirect("lista");
    }

    private void apresentaFormEdicaoConsulta(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Usuario> listaMedicos = daoMedico.getAll();
        request.setAttribute("listaMedicos", listaMedicos);

        Long id = Long.parseLong(request.getParameter("id"));
        Consulta consulta = daoConsulta.get(id);
        request.setAttribute("consulta", consulta);

        RequestDispatcher dispatcher = request
                .getRequestDispatcher("/logado/paciente/consultas-paciente/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void atualizaConsulta(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        Long id = Long.parseLong(request.getParameter("id"));

        Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");
        Paciente paciente = (Paciente) usuarioLogado;
        String cpfPaciente = paciente.getCpf();

        String crmMedico = request.getParameter("crm");
        String data = request.getParameter("data");
        String hora = request.getParameter("hora");
        String dataHora = data + " " + hora + ":00";

        Consulta consulta = new Consulta(id, cpfPaciente, crmMedico, dataHora);

        boolean disponivel = daoConsulta.verificaDisponibilidade(cpfPaciente, crmMedico, dataHora);

        if (disponivel) {
            daoConsulta.update(consulta);
            response.sendRedirect("lista");
        } else {
            request.setAttribute("disponibilidade", false);
            apresentaFormEdicaoConsulta(request, response); // chama novamente o form de edição de consulta para mais
                                                            // uma tentativa caso esteja indisponível.
        }
    }
}
