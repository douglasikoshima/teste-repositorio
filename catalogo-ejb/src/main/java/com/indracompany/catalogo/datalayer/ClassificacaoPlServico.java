package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * @author Luiz Pereira
 *
 */
@Entity
@Table(name="CLASSIFICACAOPLSERVICO", schema = "CATALOGOPRS_OW")
public class ClassificacaoPlServico implements Serializable {
	private static final long serialVersionUID = 1L;

    public ClassificacaoPlServico() {}

	@Id
	@Column(name = "IDCLASSIFICACAOPLSERVICO")
	private Integer idClassificacaoPlServico;

	@Column(name = "DSCLASSIFICACAOPLSERVICO")
	private String dsClassificacaoPlServico;

	//bi-directional many-to-one association to Planoservico
	@OneToMany(mappedBy="classificacaoPlServico")
	private List<PlanoServico> planoServicoList;

	public String getDsClassificacaoPlServico() {
		return dsClassificacaoPlServico;
	}

	public void setDsClassificacaoPlServico(String dsClassificacaoPlServico) {
		this.dsClassificacaoPlServico = dsClassificacaoPlServico;
	}

	public Integer getIdClassificacaoPlServico() {
		return idClassificacaoPlServico;
	}

	public void setIdClassificacaoPlServico(Integer idClassificacaoPlServico) {
		this.idClassificacaoPlServico = idClassificacaoPlServico;
	}

	public List<PlanoServico> getPlanoServicoList() {
		return planoServicoList;
	}

	public void setPlanoServicoList(List<PlanoServico> planoServicoList) {
		this.planoServicoList = planoServicoList;
	}
}