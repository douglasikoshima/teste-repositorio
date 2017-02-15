package com.indracompany.catalogo.datalayer;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "LOCALIDADE", schema = "CATALOGOPRS_OW")
@NamedQueries({
    @NamedQuery(name = "Localidade.findByIdCidade", query = "select l from Localidade l where l.cidade.idCidade = :idCidade order by l.cdLocalidade"),
    @NamedQuery(name = "Localidade.countAll", query = "select count(l) from Localidade l")
})
@SequenceGenerator(name = "LOCALIDADE_SQ", sequenceName = "CATALOGOPRS_OW.LOCALIDADE_SQ", allocationSize = 1)	
public class Localidade implements Serializable{

	private static final long serialVersionUID = -2203143367072339760L;
	
	@Id
	@Column(name = "IDLOCALIDADE")
	private Long idLocalidade;
	
	@Column(name = "CDLOCALIDADE")
	private String cdLocalidade;
	
	@Column(name = "NMLOCALIDADE")
	private String nmLocalidade;
    
    @ManyToOne
    @JoinColumn(name = "IDCIDADE")
    private Cidade cidade;

	public String getCdLocalidade() {
		return cdLocalidade;
	}

	public void setCdLocalidade(String cdLocalidade) {
		this.cdLocalidade = cdLocalidade;
	}

	public Long getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(Long idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getNmLocalidade() {
		return nmLocalidade;
	}

	public void setNmLocalidade(String nmLocalidade) {
		this.nmLocalidade = nmLocalidade;
	}

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
	
}
