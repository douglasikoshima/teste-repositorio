package admsistemas.parametrizacoes.motivos;

import org.apache.struts.action.ActionForm;

import admsistemas.parametrizacoes.motivos.MotivosController.Motivo;

public class MotivoForm extends ActionForm {

	private static final long serialVersionUID = -7240728919456160102L;

	private Long codigo;

	private String nome;
	private String inativo;

	private Motivo[] motivos;

	public Motivo[] getMotivos() {
		if (motivos == null) {
			motivos = new Motivo[0];
		}
		return motivos;
	}

	public void setMotivos(Motivo[] motivos) {
		this.motivos = motivos;
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

	public String getInativo() {
		return inativo;
	}

	public void setInativo(String inativo) {
		this.inativo = inativo;
	}
}