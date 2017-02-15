package admsistemas.parametrizacoes.motivos;

import org.apache.struts.action.ActionForm;

import admsistemas.parametrizacoes.motivos.MotivosController.Submotivo;

public class SubmotivoForm extends ActionForm {

	private static final long serialVersionUID = -1627148964758891824L;

	private Long codigo;
	private Long pai;
	private String nome;
	private String inativo;
	private String nomePai;

	private Submotivo[] submotivos;

	public Submotivo[] getSubmotivos() {
		if (submotivos == null) {
			submotivos = new Submotivo[0];
		}
		return submotivos;
	}

	public void setSubmotivos(Submotivo[] submotivos) {
		this.submotivos = submotivos;
	}

	public Long getPai() {
		return pai;
	}

	public void setPai(Long pai) {
		this.pai = pai;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomePai() {
		return nomePai;
	}

	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}

	public String getInativo() {
		return inativo;
	}

	public void setInativo(String inativo) {
		this.inativo = inativo;
	}
}