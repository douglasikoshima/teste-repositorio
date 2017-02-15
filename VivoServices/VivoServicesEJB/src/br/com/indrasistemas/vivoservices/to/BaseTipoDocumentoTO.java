package br.com.indrasistemas.vivoservices.to;

import br.com.indrasistemas.framework.service.BaseTransferObject;

public abstract class BaseTipoDocumentoTO extends BaseTransferObject {

	private String nome;

	/**
	 * Este  atributo contem sujeira que vem dos legados Atlys e NGIN e não deve ser
	 * utilizado na exibição. Para tal vamos utilizar o atributo classificacao.
	 * Deve ser utilizado em pesquisas, pois é chave única tembém.
	 */
	private String sigla;

	/**
	 * Deve ser utilizado no lugar da sigla, pois ele contem o texto que dever ser
	 * exibido. Mas ocorrem repetencias que devem ser sanadas na pesquisa utilizando o
	 * campo prioridade para recuperar o menor.
	 */
	private String classificacao;

	private Long id;

	public String getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
