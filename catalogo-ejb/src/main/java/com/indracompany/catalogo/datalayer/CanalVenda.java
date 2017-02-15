package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CANALVENDA", schema = "CATALOGOPRS_OW")
@SequenceGenerator(name = "CANALVENDA_SQ", sequenceName = "CATALOGOPRS_OW.CANALVENDA_SQ", allocationSize = 1)
@NamedQueries({
    @NamedQuery(name = "CanalVenda.findAll", query = "select c from CanalVenda c order by c.nmCanalVenda"),
    @NamedQuery(name = "CanalVenda.countAll", query = "select count(c) from CanalVenda c"),
    @NamedQuery(name = "CanalVenda.findByIdOfertafixa", query = "select offcv.canalVenda from OfertafixaFatorCanalVenda offcv where offcv.ofertafixa.idOfertafixa = :idOfertafixa order by offcv.canalVenda.nmCanalVenda"),
    @NamedQuery(name = "CanalVenda.findByIds", query = "select c from CanalVenda c where c.idCanalVenda in (:idCanalVendaList) order by c.nmCanalVenda"),
    @NamedQuery(name = "CanalVenda.findByIdEps", query = "select c from CanalVenda c inner join c.eps ep where  ep.idEps = :idEps  order by c.nmCanalVenda")
})
public class CanalVenda implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "IDCANALVENDA")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CANALVENDA_SQ")
	private Long idCanalVenda;
	
	@Column(name = "CDCANALVENDA")
	private String cdCanalVenda;
	
	@Column(name = "NMCANALVENDA")
	@OrderBy
	private String nmCanalVenda;
	
	@Column(name = "DSCANALVENDA")
	private String dsCanalVenda;
	
	@Column(name = "INDISPONIVEL")
	private String inDisponivel;

	@Column(name = "INCRIACAOCATALOGO")
	private String inCriacaoCatalogo;
    
    @Column(name = "INALTERACAOCATALOGO")
    private String inAlteracaoCatalogo;
	
	@OneToMany(mappedBy="canalVenda")
	@JoinColumn(name = "IDCANALVENDA")
	private List<CanalVendaSolicitacaoComercial> canalVendaSolicitacaoComercial;
	
	@OneToMany(mappedBy="canalVenda")
	@JoinColumn(name = "IDCANALVENDA")
	private List<CondicaoPagamentoEncargo> condicaoPagamentoEncargoList;
    
    @OneToMany(mappedBy="canalVenda")
    @JoinColumn(name = "IDCANALVENDA")
    private List<ValorPoliticaPromocao> valorPoliticaPromocaoList;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "IDEPS")
    private Eps eps;

	public CanalVenda(){}

	public CanalVenda(Long id){
		this.idCanalVenda = id;
	}
	
	public List<CanalVendaSolicitacaoComercial> getCanalVendaSolicitacaoComercial() {
		return canalVendaSolicitacaoComercial;
	}

	public void setCanalVendaSolicitacaoComercial(
			List<CanalVendaSolicitacaoComercial> canalVendaSolicitacaoComercial) {
		this.canalVendaSolicitacaoComercial = canalVendaSolicitacaoComercial;
	}

	public String getCdCanalVenda() {
		return cdCanalVenda;
	}

	public void setCdCanalVenda(String cdCanalVenda) {
		this.cdCanalVenda = cdCanalVenda;
	}

	public List<CondicaoPagamentoEncargo> getCondicaoPagamentoEncargoList() {
		return condicaoPagamentoEncargoList;
	}

	public void setCondicaoPagamentoEncargoList(
			List<CondicaoPagamentoEncargo> condicaoPagamentoEncargoList) {
		this.condicaoPagamentoEncargoList = condicaoPagamentoEncargoList;
	}

	public String getDsCanalVenda() {
		return dsCanalVenda;
	}

	public void setDsCanalVenda(String dsCanalVenda) {
		this.dsCanalVenda = dsCanalVenda;
	}

	public Long getIdCanalVenda() {
		return idCanalVenda;
	}

	public void setIdCanalVenda(Long idCanalVenda) {
		this.idCanalVenda = idCanalVenda;
	}

	public String getInCriacaoCatalogo() {
		return inCriacaoCatalogo;
	}

	public void setInCriacaoCatalogo(String inCriacaoCatalogo) {
		this.inCriacaoCatalogo = inCriacaoCatalogo;
	}

	public String getInDisponivel() {
		return inDisponivel;
	}

	public void setInDisponivel(String inDisponivel) {
		this.inDisponivel = inDisponivel;
	}

	public String getNmCanalVenda() {
		return nmCanalVenda;
	}

	public void setNmCanalVenda(String nmCanalVenda) {
		this.nmCanalVenda = nmCanalVenda;
	}

    public String getInAlteracaoCatalogo() {
        return inAlteracaoCatalogo;
    }

    public void setInAlteracaoCatalogo(String inAlteracaoCatalogo) {
        this.inAlteracaoCatalogo = inAlteracaoCatalogo;
    }

    public Eps getEps() {
        return eps;
    }

    public void setEps(Eps eps) {
        this.eps = eps;
    }

    public List<ValorPoliticaPromocao> getValorPoliticaPromocaoList() {
        return valorPoliticaPromocaoList;
    }

    public void setValorPoliticaPromocaoList(
            List<ValorPoliticaPromocao> valorPoliticaPromocaoList) {
        this.valorPoliticaPromocaoList = valorPoliticaPromocaoList;
    }
}
