# 🏠 Kira API - Plataforma de Aluguel de Imóveis

Este repositório contém a API backend da **Kira**, uma plataforma digital que conecta locadores e locatários de forma **prática, acessível e interativa**, com foco especial em **pequenas cidades** e **universitários** que buscam imóveis para alugar ou dividir.

---

## 🚀 Sobre o Projeto

A Kira é uma **proptech** que busca facilitar o processo de aluguel de imóveis. A API foi desenvolvida com foco em escalabilidade e segurança, oferecendo suporte ao gerenciamento de usuários, imóveis, anúncios e contratos, além de recursos como chat, favoritos e avaliações.

---

## 🔧 Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot**
- **Spring Data JPA**
- **Spring Security com JWT**
- **PostgreSQL**
- **Maven**
- **Swagger/OpenAPI** (Documentação dos endpoints)
- Arquitetura modular baseada em boas práticas (camadas de domínio, serviço e controle)

---

## 🧩 Funcionalidades Principais

- 🔐 Cadastro e autenticação de usuários (locadores e locatários)
- 🏡 Gerenciamento de imóveis (cadastro, edição, remoção e filtros por localização, tipo e preço)
- 📢 Criação e gerenciamento de anúncios
- ⭐ Avaliação de imóveis e de locadores
- 💬 Chat direto entre locador e locatário
- ❤️ Lista de imóveis favoritos
- 🖼️ Upload e exibição de imagens dos imóveis

---

## 💡 Diferenciais

- Comunicação **direta** e simplificada entre locador e locatário
- Foco em **pequenas cidades e regiões do interior**
- Pensado para **universitários** que buscam imóveis para dividir ou alugar
- Plataforma com **design acessível** e foco na experiência do usuário

---

## 💰 Modelo de Monetização

- Modelo **B2C** com foco em publicidade dentro da plataforma

---

## 🧪 Como rodar o projeto localmente

### Pré-requisitos

- Java 17+
- PostgreSQL
- Maven

### Configuração

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/kira-api.git
   cd kira-api
   ```
2. Configure o application.properties ou application.yml com os dados do seu banco PostgreSQL:
   ```bash
    spring.datasource.url=jdbc:postgresql://localhost:5432/kira
    spring.datasource.username=seu_usuario
    spring.datasource.password=sua_senha
    spring.jpa.hibernate.ddl-auto=update
   ```
3. Execute o projeto:
   ```bash
   ./mvnw spring-boot:run
   ```
4. Acesse a documentação Swagger em:
   ```bash
   http://localhost:8080/swagger-ui.html
   ```

## 👨‍💻 Equipe

- [**João Victor**](https://github.com/JVictor011) – Diretor de Tecnologia  
- [**Victor Wesley**](https://github.com/vctrwesley) – Diretor de Marketing  
- [**Felipe Liet**](https://github.com/FelpLiet) – Diretor Geral  

---

> _"O sucesso é a soma de pequenos esforços repetidos dia após dia."_  
> — **Robert Collier**
