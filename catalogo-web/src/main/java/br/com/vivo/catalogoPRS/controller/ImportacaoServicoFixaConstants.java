package br.com.vivo.catalogoPRS.controller;

public final class ImportacaoServicoFixaConstants {
	
	public final static String CABECALHO_RELACIONAMENTO_EXPORT = "Acao;Codigo de servico relacao;Sigla do tipo relacionamento;Codigo do servico relacionado";
	public final static String CABECALHO_RELACIONAMENTO_CRITICA_EXPORT = "Acao;Codigo de servico relacao;Sigla do tipo relacionamento;Codigo do servico relacionado;Critica";
    public final static String CABECALHO_SC_GC_PM_AC_EXPORT = "Acao;Codigo da solicitacao comercial;Codigo do grupo comercial;Codigo do plano minutos;Codigo do area concorrencia";
    public final static String CABECALHO_SC_GC_PM_AC_CRITICA_EXPORT = "Acao;Codigo da solicitacao comercial;Codigo do grupo comercial;Codigo do plano minutos;Codigo do area concorrencia;Critica";
    public final static String CABECALHO_SC_ENC_GC_EXPORT = "Acao;Codigo da solicitacao comercial;Codigo do encargo;Codigo do Plano de Financiamento;Codigo do grupo comercial";
    public final static String CABECALHO_SC_ENC_GC_CRITICA_EXPORT = "Acao;Codigo da solicitacao comercial;Codigo do encargo;Codigo do Plano de Financiamento;Codigo do grupo comercial;Critica";
    public final static String CABECALHO_SC_ENC_GC_PM_AC_EXPORT = "Acao;Codigo da solicitacao comercial;Codigo do encargo;Codigo do Plano de Financiamento;Codigo do grupo comercial;Codigo do plano minutos;Codigo do area concorrencia";
    public final static String CABECALHO_SC_ENC_GC_PM_AC_CRITICA_EXPORT = "Acao;Codigo da solicitacao comercial;Codigo do encargo;Codigo do Plano de Financiamento;Codigo do grupo comercial;Codigo do plano minutos;Codigo do area concorrencia;Critica";
    public final static String CABECALHO_SERVICO_EXPORT = "CODIGO DO SERVICO;NOME DO SERVICO;DISPONIBILIDADE";
    public final static String CABECALHO_SERVICO_EXPORT_CRITICA = "CODIGO DO SERVICO;NOME DO SERVICO;DISPONIBILIDADE;CRITICA";
    public final static String CABECALHO_SERVICO_EXPORT_OFERTA_VENDA_LINHA = "CODIGO DA OFERTA;NOME OFERTA;DATA INICIO VIGENCIA;DATA FIM VIGENCIA;PS LINHA;CODIGO OPERACAO COMERCIAL LINHA;CODIGO OPERACAO COMERCIAL PRE CADASTRO;PS PLANO;CODIGO OPERACAO COMERCIAL PLANO;PORTABILIDADE;FWT;BASE PONTUAL;INADIMPLENTE;FATB;PRODUTO OBRIGATORIO(F = FIBRA/A = ADSL);SPEEDY SOLO(F = FIBRA/A = ADSL)";
    public final static String CABECALHO_SERVICO_EXPORT_OFERTA_COMPLEMENTAR = "CODIGO DA OFERTA;PS SERVICO COMPLEMENTAR;OPERACAO COMERCIAL;PRAZO VIGENCIA";
    public final static String CABECALHO_SERVICO_EXPORT_OFERTA_VENDA_LINHA_CRITICA = "CODIGO DA OFERTA;NOME OFERTA;DATA INICIO VIGENCIA;DATA FIM VIGENCIA;PS LINHA;CODIGO OPERACAO COMERCIAL LINHA;CODIGO OPERACAO COMERCIAL PRE CADASTRO;PS PLANO;CODIGO OPERACAO COMERCIAL PLANO;PORTABILIDADE;FWT;BASE PONTUAL;INADIMPLENTE;FATB;PRODUTO OBRIGATORIO(F = FIBRA/A = ADSL);SPEEDY SOLO(F = FIBRA/A = ADSL);CRITICA";
    public final static String CABECALHO_SERVICO_EXPORT_OFERTA_COMPLEMENTAR_CRITICA = "CODIGO DA OFERTA;PS SERVICO COMPLEMENTAR;OPERACAO COMERCIAL;PRAZO VIGENCIA;CRITICA";    
    public final static String NOME_ARQUIVO_OFERTA_VENDA_LINHA = "OFERTA_VENDA_LINHA";
    public final static String NOME_ARQUIVO_OFERTA_COMPLEMENTAR = "OFERTA_COMPLEMENTAR";
	public final static int RELACIONAMENTO_SERVICO = 1;
	public final static int SC_GC_PM_AC = 2;
	public final static int SC_ENC_GC = 3;
	public final static int SC_ENC_GC_PM_AC = 4;
    public final static int SERVICO = 5;
    public final static int OFERTA_VENDA_LINHA = 6;
    public final static int OFERTA_COMPLEMENTAR = 7;
    
	
}
