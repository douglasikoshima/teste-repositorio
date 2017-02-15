package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import javax.persistence.*;

/**
 * @author Luiz Pereira
 *
 */
@Entity
@Table(name="SERVICOTIPOASSINATURA", schema = "CATALOGOPRS_OW")
public class ServicoTipoAssinatura implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public ServicoTipoAssinatura() {}
	
	@Id
	@Column(name = "IDSERVICOTIPOASSINATURA")
	private Integer idServicoTipoAssinatura;

	@Column(name = "SGTPASSINATURA")
	private String sgTpAssinatura;

	//bi-directional many-to-one association to Servico
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDSERVICO", nullable=false)
	private Servico servico;

	public Integer getIdServicoTipoAssinatura() {
		return idServicoTipoAssinatura;
	}

	public void setIdServicoTipoAssinatura(Integer idServicoTipoAssinatura) {
		this.idServicoTipoAssinatura = idServicoTipoAssinatura;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public String getSgTpAssinatura() {
		return sgTpAssinatura;
	}

	public void setSgTpAssinatura(String sgTpAssinatura) {
		this.sgTpAssinatura = sgTpAssinatura;
	}
    
}