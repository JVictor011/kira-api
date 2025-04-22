package com.jvictor011.kira_api.exception;

public class MensagensErro {
    public static final String EMAIL_JA_CADASTRADO = "O e-mail informado já está cadastrado em nosso sistema. Tente utilizar outro e-mail.";
    public static final String USUARIO_CADASTRADO = "Já existe um usuário cadastrado com as informações fornecidas. Por favor, verifique os dados e tente novamente.";
    public static final String USUARIO_NAO_ENCONTRADO = "Não conseguimos localizar um usuário com os dados fornecidos. Verifique se o usuário está corretamente cadastrado.";
    public static final String SENHA_NAO_COINCIDE = "As senhas informadas não coincidem. Por favor, digite novamente a senha corretamente.";
    public static final String SENHA_NAO_VALIDA = "A senha fornecida não atende aos requisitos de segurança. Verifique as exigências e tente novamente.";
    public static final String ERRO_INTERNO = "Ocorreu um erro inesperado no servidor. Estamos trabalhando para resolver o problema. Tente novamente mais tarde.";
    public static final String CPF_INVALIDO = "O CPF informado não é válido. Verifique o formato e tente novamente.";
}
