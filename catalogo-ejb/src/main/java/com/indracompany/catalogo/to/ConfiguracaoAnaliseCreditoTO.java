package com.indracompany.catalogo.to;

public abstract class ConfiguracaoAnaliseCreditoTO {
	protected static final String SEPARADOR_CHAVE = "_";
	public String chaveConfiguracao;

	public abstract String getChaveConfiguracaoScore();
	public abstract ConfiguracaoAnaliseCreditoTO buildConfiguracaoAnaliseCreditoTO(String chaveConfiguracao);
}