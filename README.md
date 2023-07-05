Trabalho 1 - Sistema de agendamento de consultas medicas utilizando servlets

1. Execute o tomcat
2. Execute o código sql que está no caminho "db/MySQL/create.sql"
3. Dentro da pasta consultas use o comando "mvn tomcat7:deploy" ou "mvn tomcat7:redeploy"
4. No seu navegador entre no link "localhost:8080/consultas"
5. Foram definidos três usuários para o sistema: administrador, médico e paciente. Usuários pré-definidos são criados quando o banco de dados é inicializados, e podem ser acessados através das seguintes credenciais:
    - Administrador com email `admin@email.com` e senha `admin`
    - Paciente com email `paciente@email.com` e senha `senha`
    - Médico com email `medico@email.com` e senha `senha`
