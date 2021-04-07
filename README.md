<h1 align="center">Projeto referente a disciplina de Mobile, do MBA da FIAP</h1>

<p align="center">
  <a href="#-tecnologias">Tecnologias</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-projeto">Projeto</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-executar">Como executar ?</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</p>

## :rocket: Tecnologias
 
Projeto Android desenvolvido em Java, utilizando como banco de dados o Firebase, fazendo a autenticação de usuarios e gravando dados.
 
## 💻 Projeto
 
Projeto referente a disciplina de Mobile Development, do MBA da [FIAP](https://www.fiap.com.br/mba/mba-em-full-stack-developer-microservices-cloud-e-iot/).
Foi desenvolvido uma solução de cadastramento de viagens, onde permite um CRUD para controles de viagens.
 
## Como executar ?

- Baixar o projeto: `$ git clone https://github.com/diegosg1989/AppFiapViagem.git`

- Importar o projeto na IDE Android Studio

- Aberto a IDE, no menu **tools** clicar em **Firebase**, onde abrirá uma janela com todas as funcionalidades do firebase, utilizaremos duas nesse projeto

  - **Authentication** para validação do usuario e login:
  
    - Clicar em **Authentication** depois em **Connect your app yo Firebase**, feito isso o Android Studio irá se conectar ao Firebase configurado

    - Clicar em **Add Firebase Authentication to you app** onde todas as biblioteclas serão importadas para o projeto

  - **Firestore** onde consta os dados gravado do app:
  
    - Clicar em **Add Cloud Firestore to your app** onde todas as bibliotecas em relação a banco serão importadas para o projeto

- Executar o projeto em `Run 'app'`, e o sistema abrirá em um emulador do Android Studio
