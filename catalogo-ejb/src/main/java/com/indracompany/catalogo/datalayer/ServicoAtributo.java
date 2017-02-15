package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import javax.persistence.*;

/**
 * @author Luiz Pereira
 *
 */
@Entity
@Table(name="SERVICOATRIBUTO", schema = "CATALOGOPRS_OW")
public class ServicoAtributo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public ServicoAtributo() {}
	
	@Id
	@Column(name = "IDSERVICOATRIBUTO")
	private Integer idServicoAtributo;

	//bi-directional many-to-one association to Atributo
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDATRIBUTO", nullable=false)
	private Atributo atributo;

	//bi-directional many-to-one association to Sistemaservico
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDSISTEMASERVICO", nullable=false)
	private SistemaServico sistemaServico;

	public Atributo getAtributo() {
		return atributo;
	}

	public void setAtributo(Atributo atributo) {
		this.atributo = atributo;
	}

	public Integer getIdServicoAtributo() {
		return idServicoAtributo;
	}

	public void setIdServicoAtributo(Integer idServicoAtributo) {
		this.idServicoAtributo = idServicoAtributo;
	}

	public SistemaServico getSistemaServico() {
		return sistemaServico;
	}

	public void setSistemaServico(SistemaServico sistemaServico) {
		this.sistemaServico = sistemaServico;
	}
	
}