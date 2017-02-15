package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;

/**
 * @author Luiz Pereira
 *
 */
@Entity
@Table(name="TIPOSERVICO", schema = "CATALOGOPRS_OW")
@NamedQueries({
    @NamedQuery(name = "TipoServico.carregarTipoServicoParaOfertafixa", query = "select ts from TipoServico ts where ts.inFixa = 'S' and cdTipoServico not in ('16','21','5','7','10','1','20','22') order by ts.dscTipoServico"),
    @NamedQuery(name = "TipoServico.loadTipoServicos", query = "select ts from TipoServico ts where ts.inFixa = 'S' and cdTipoServico not in ('16','21','5','7','10','1','20','22') order by ts.dscTipoServico")
})
public class TipoServico implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public TipoServico() {}
	
	@Id
	@Column(name = "IDTIPOSERVICO")
	private Integer idTipoServico;

	@Column(name = "DSCTIPOSERVICO")
	private String dscTipoServico;

	@Column(name = "NMTIPOSERVICO")
	private String nmTipoServico;
	
	@Column(name = "CDTIPOSERVICO")
	private String cdTipoServico;
	
	@Column(name = "INFIXA")
	private String inFixa;	
	
	//bi-directional many-to-one association to Servico
	@OneToMany(mappedBy="tipoServico", fetch=FetchType.LAZY)
	private List<Servico> servicoList;

	public String getCdTipoServico() {
		return cdTipoServico;
	}

	public void setCdTipoServico(String cdTipoServico) {
		this.cdTipoServico = cdTipoServico;
	}

	public String getDscTipoServico() {
		return dscTipoServico;
	}

	public void setDscTipoServico(String dscTipoServico) {
		this.dscTipoServico = dscTipoServico;
	}

	public Integer getIdTipoServico() {
		return idTipoServico;
	}

	public void setIdTipoServico(Integer idTipoServico) {
		this.idTipoServico = idTipoServico;
	}

	public String getInFixa() {
		return inFixa;
	}

	public void setInFixa(String inFixa) {
		this.inFixa = inFixa;
	}

	public String getNmTipoServico() {
		return nmTipoServico;
	}

	public void setNmTipoServico(String nmTipoServico) {
		this.nmTipoServico = nmTipoServico;
	}

	public List<Servico> getServicoList() {
		return servicoList;
	}

	public void setServicoList(List<Servico> servicoList) {
		this.servicoList = servicoList;
	}
	

}