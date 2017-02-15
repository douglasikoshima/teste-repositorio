package com.indracompany.catalogo.to;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PlanoServicoUfRestricaoTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private String codigo;
	private String in4g;
	private Date dtCriacaoAlteracao;
	private String nmUsuarioCriacaoAlteracao;
	private List<UfTO> ufTOList;
	
	public PlanoServicoUfRestricaoTO() {
		super();
		ufTOList = new ArrayList<UfTO>();
	}

	public Date getDtCriacaoAlteracao() {
		return dtCriacaoAlteracao;
	}

	public void setDtCriacaoAlteracao(Date dtCriacaoAlteracao) {
		this.dtCriacaoAlteracao = dtCriacaoAlteracao;
	}

	public String getNmUsuarioCriacaoAlteracao() {
		return nmUsuarioCriacaoAlteracao;
	}

	public void setNmUsuarioCriacaoAlteracao(String nmUsuarioCriacaoAlteracao) {
		this.nmUsuarioCriacaoAlteracao = nmUsuarioCriacaoAlteracao;
	}

	public String getIn4g() {
		return in4g;
	}

	public void setIn4g(String in4g) {
		this.in4g = in4g;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<UfTO> getUfTOList() {
		return ufTOList;
	}

	public void setUfTOList(List<UfTO> ufTOList) {
		this.ufTOList = ufTOList;
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final PlanoServicoUfRestricaoTO other = (PlanoServicoUfRestricaoTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
