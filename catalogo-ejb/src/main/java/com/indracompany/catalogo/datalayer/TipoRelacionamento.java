package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * @author Luiz Pereira
 *
 */
@Entity
@Table(name="TIPORELACIONAMENTO", schema = "CATALOGOPRS_OW")
public class TipoRelacionamento implements Serializable {
	private static final long serialVersionUID = 1L;

    public TipoRelacionamento() {}
	
	public TipoRelacionamento(Integer idTipoRelacionamento) {
        this.idTipoRelacionamento = idTipoRelacionamento;
    }

    @Id
	@Column(name = "IDTIPORELACIONAMENTO")
	private Integer idTipoRelacionamento;

	@Column(name = "DSCTIPORELACIONAMENTO")
	private String dscTipoRelacionamento;

	@Column(name = "SGTIPORELACIONAMENTO")
	private String sgTipoRelacionamento;

    @Column(name = "INFIXA")
    private String inFixa;
    
	//bi-directional many-to-one association to Planoservico
	@OneToMany(mappedBy="tipoRelacionamento")
	private List<PlanoServico> planoServicoList;

	//bi-directional many-to-one association to Servicoservico
	@OneToMany(mappedBy="tipoRelacionamento")
	private List<ServicoServico> servicoServicoList;

	public String getDscTipoRelacionamento() {
		return dscTipoRelacionamento;
	}

	public void setDscTipoRelacionamento(String dscTipoRelacionamento) {
		this.dscTipoRelacionamento = dscTipoRelacionamento;
	}

	public Integer getIdTipoRelacionamento() {
		return idTipoRelacionamento;
	}

	public void setIdTipoRelacionamento(Integer idTipoRelacionamento) {
		this.idTipoRelacionamento = idTipoRelacionamento;
	}

	public List<PlanoServico> getPlanoServicoList() {
		return planoServicoList;
	}

	public void setPlanoServicoList(List<PlanoServico> planoServicoList) {
		this.planoServicoList = planoServicoList;
	}

	public List<ServicoServico> getServicoServicoList() {
		return servicoServicoList;
	}

	public void setServicoServicoList(List<ServicoServico> servicoServicoList) {
		this.servicoServicoList = servicoServicoList;
	}

	public String getSgTipoRelacionamento() {
		return sgTipoRelacionamento;
	}

	public void setSgTipoRelacionamento(String sgTipoRelacionamento) {
		this.sgTipoRelacionamento = sgTipoRelacionamento;
	}

    public String getInFixa() {
        return inFixa;
    }

    public void setInFixa(String inFixa) {
        this.inFixa = inFixa;
    }
}