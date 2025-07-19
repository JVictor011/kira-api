package com.jvictor011.kira_api.exception;

public class MensagensErro {
    // Usuário
    public static final String EMAIL_JA_CADASTRADO = "O e-mail informado já está cadastrado em nosso sistema. Tente utilizar outro e-mail.";
    public static final String USUARIO_CADASTRADO = "Já existe um usuário cadastrado com as informações fornecidas. Por favor, verifique os dados e tente novamente.";
    public static final String USUARIO_NAO_ENCONTRADO = "Usuário não encontrado. Verifique os dados informados.";

    // Senha
    public static final String SENHA_NAO_COINCIDE = "As senhas informadas não coincidem. Por favor, digite novamente a senha corretamente.";
    public static final String SENHA_NAO_VALIDA = "A senha fornecida não atende aos requisitos de segurança. Deve conter pelo menos 8 caracteres, uma letra maiúscula, uma minúscula e um caractere especial.";

    // Documento
    public static final String DOCUMENTO_INVALIDO = "O documento informado (CPF ou CNPJ) não é válido. Verifique o número e tente novamente.";
    public static final String CPF_INVALIDO = "O CPF informado não é válido. Verifique o formato e tente novamente.";
    public static final String CNPJ_INVALIDO = "O CNPJ informado não é válido. Verifique o número e tente novamente.";

    // Genérico
    public static final String ERRO_INTERNO = "Ocorreu um erro inesperado no servidor. Estamos trabalhando para resolver o problema. Tente novamente mais tarde.";
    public static final String DADOS_INVALIDOS = "Dados inválidos. Por favor, verifique os campos informados e tente novamente.";
    public static final String CONFLITO_DE_DADOS = "Conflito de dados detectado. A operação não pôde ser concluída.";
}
