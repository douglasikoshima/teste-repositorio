package br.com.vivo.catalogoPRS.pageflows.shared.form;

import java.io.Serializable;
import java.util.List;

import org.apache.struts.validator.ValidatorActionForm;

import com.indracompany.catalogo.to.PlanoServicoUfRestricaoTO;
import com.indracompany.catalogo.to.UfTO;

public class RestricaoPlanoServicoForm extends ValidatorActionForm implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String tipoPesquisa;
	private Boolean ind4G;
	private String codigo;
	private String nome;
	private List<PlanoServicoUfRestricaoTO> planoServicoList;
	private List<UfTO> ufList;
	private String[] restricoesUf;
	private Long[] semRestricoesUf;
	private String trash;

	public RestricaoPlanoServicoForm() {
		this.tipoPesquisa = "P";
		this.ind4G = true;
		this.trash = "P";
	}

	public String getTrash() {
		return trash;
	}

	public void setTrash(String trash) {
		this.trash = trash;
	}

	public Long[] getSemRestricoesUf() {
		return semRestricoesUf;
	}

	public void setSemRestricoesUf(Long[] semRestricoesUf) {
		this.semRestricoesUf = semRestricoesUf;
	}

	public String[] getRestricoesUf() {
		return restricoesUf;
	}

	public void setRestricoesUf(String[] restricoesUf) {
		this.restricoesUf = restricoesUf;
	}

	public List<PlanoServicoUfRestricaoTO> getPlanoServicoList() {
		return planoServicoList;
	}

	public void setPlanoServicoList(List<PlanoServicoUfRestricaoTO> planoServicoList) {
		this.planoServicoList = planoServicoList;
	}

	public List<UfTO> getUfList() {
		return ufList;
	}

	public void setUfList(List<UfTO> ufList) {
		this.ufList = ufList;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipoPesquisa() {
		return tipoPesquisa;
	}

	public void setTipoPesquisa(String tipoPesquisa) {
		
		if (tipoPesquisa.equals("P") || tipoPesquisa.equals("S")) {
			this.tipoPesquisa = tipoPesquisa;
		} else {
			this.tipoPesquisa = "P";
		}

	}

	public Boolean getInd4G() {
		return ind4G;
	}

	public void setInd4G(Boolean ind4G) {
		this.ind4G = ind4G;
	}
}
