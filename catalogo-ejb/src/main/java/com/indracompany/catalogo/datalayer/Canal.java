package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.List;

/**
 * @author Luiz Pereira
 *
 */
@Entity
@NamedQuery(name = "Canal.findAll", query = "SELECT c FROM Canal c WHERE c.inDisponivel = 'S' order by c.nmCanal")
@Table(name="CANAL", schema = "CATALOGOPRS_OW")
public class Canal implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public Canal() {}
	
	public Canal(Integer idCanal) {
		this.idCanal = idCanal;
	}
	
	@Id
	@Column(name = "IDCANAL")
	private Integer idCanal;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTCRIACAO")
	private Date dtCriacao;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTFINAL")
	private Date dtFinal;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTINICIAL")
	private Date dtInicial;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTULTIMAALTERACAO")
	private Date dtUltimaAlteracao;

    @Column(name = "INDISPONIVEL")
	private String inDisponivel;

    @Column(name = "NMCANAL")
	private String nmCanal;

    @Column(name = "NMUSUARIOALTERACAO")
	private String nmUsuarioAlteracao;

    @Column(name = "NMUSUARIOCRIACAO")
	private String nmUsuarioCriacao;

    @Column(name = "SGCANAL")
	private String sgCanal;

    @Column(name = "SGVIVONET")
	private String sgVivoNet;

	//bi-directional many-to-one association to Centro
	@OneToMany(mappedBy="canal")
	private List<Centro> centroList;

	//bi-directional many-to-one association to Comboofertacanal
	@OneToMany(mappedBy="canal")
	private List<ComboOfertaCanal> comboOfertaCanaList;

	//bi-directional many-to-one association to Descontocondpagto
	@OneToMany(mappedBy="canal")
	private List<DescontoCondPagto> descontoCondPagtoList;

	//bi-directional many-to-one association to Formapagtocanalparam
	@OneToMany(mappedBy="canal")
	private List<FormaPagtoCanalParam> formaPagtoCanalParamList;

	//bi-directional many-to-one association to Formapagtoparcelaminima
	@OneToMany(mappedBy="canal")
	private List<FormaPagtoParcelaMinima> formaPagtoParcelaMinimaList;

	//bi-directional many-to-one association to Grupoprodutorestricoe
	@OneToMany(mappedBy="canal")
	private List<GrupoProdutoRestricoes> grupoProdutoRestricoesList;

	//bi-directional many-to-one association to Matrizofertaitempreco
	@OneToMany(mappedBy="canal")
	private List<MatrizOfertaItemPreco> matrizOfertaItemPrecoList;

	//bi-directional many-to-one association to Matrizofertaitemprecoaudit
	@OneToMany(mappedBy="canal")
	private List<MatrizOfertaItemPrecoAudit> matrizOfertaItemPrecoAuditList;

	//bi-directional many-to-one association to Precoproduto
	@OneToMany(mappedBy="canal")
	private List<PrecoProduto> precoProdutoList;

	//bi-directional many-to-one association to Produtocanal
	@OneToMany(mappedBy="canal")
	private List<ProdutoCanal> produtoCanaList;

	//bi-directional many-to-one association to Tipoprodutocondpagto
	@OneToMany(mappedBy="canal")
	private List<TipoProdutoCondPagto> tipoProdutoCondPagtoList;
	
	@OneToMany(mappedBy="canal")
	private List<CanalAtendimentoCanal> canalAtendimentoCanalList;
	
	public List<CanalAtendimentoCanal> getCanalAtendimentoCanalList() {
		return canalAtendimentoCanalList;
	}

	public void setCanalAtendimentoCanalList(
			List<CanalAtendimentoCanal> canalAtendimentoCanalList) {
		this.canalAtendimentoCanalList = canalAtendimentoCanalList;
	}

	public List<Centro> getCentroList() {
		return centroList;
	}

	public void setCentroList(List<Centro> centroList) {
		this.centroList = centroList;
	}

	public List<ComboOfertaCanal> getComboOfertaCanaList() {
		return comboOfertaCanaList;
	}

	public void setComboOfertaCanaList(List<ComboOfertaCanal> comboOfertaCanaList) {
		this.comboOfertaCanaList = comboOfertaCanaList;
	}

	public List<DescontoCondPagto> getDescontoCondPagtoList() {
		return descontoCondPagtoList;
	}

	public void setDescontoCondPagtoList(
			List<DescontoCondPagto> descontoCondPagtoList) {
		this.descontoCondPagtoList = descontoCondPagtoList;
	}

	public Date getDtCriacao() {
		return dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public Date getDtFinal() {
		return dtFinal;
	}

	public void setDtFinal(Date dtFinal) {
		this.dtFinal = dtFinal;
	}

	public Date getDtInicial() {
		return dtInicial;
	}

	public void setDtInicial(Date dtInicial) {
		this.dtInicial = dtInicial;
	}

	public Date getDtUltimaAlteracao() {
		return dtUltimaAlteracao;
	}

	public void setDtUltimaAlteracao(Date dtUltimaAlteracao) {
		this.dtUltimaAlteracao = dtUltimaAlteracao;
	}

	public List<FormaPagtoCanalParam> getFormaPagtoCanalParamList() {
		return formaPagtoCanalParamList;
	}

	public void setFormaPagtoCanalParamList(
			List<FormaPagtoCanalParam> formaPagtoCanalParamList) {
		this.formaPagtoCanalParamList = formaPagtoCanalParamList;
	}

	public List<FormaPagtoParcelaMinima> getFormaPagtoParcelaMinimaList() {
		return formaPagtoParcelaMinimaList;
	}

	public void setFormaPagtoParcelaMinimaList(
			List<FormaPagtoParcelaMinima> formaPagtoParcelaMinimaList) {
		this.formaPagtoParcelaMinimaList = formaPagtoParcelaMinimaList;
	}

	public List<GrupoProdutoRestricoes> getGrupoProdutoRestricoesList() {
		return grupoProdutoRestricoesList;
	}

	public void setGrupoProdutoRestricoesList(
			List<GrupoProdutoRestricoes> grupoProdutoRestricoesList) {
		this.grupoProdutoRestricoesList = grupoProdutoRestricoesList;
	}

	public Integer getIdCanal() {
		return idCanal;
	}

	public void setIdCanal(Integer idCanal) {
		this.idCanal = idCanal;
	}

	public String getInDisponivel() {
		return inDisponivel;
	}

	public void setInDisponivel(String inDisponivel) {
		this.inDisponivel = inDisponivel;
	}

	public List<MatrizOfertaItemPrecoAudit> getMatrizOfertaItemPrecoAuditList() {
		return matrizOfertaItemPrecoAuditList;
	}

	public void setMatrizOfertaItemPrecoAuditList(
			List<MatrizOfertaItemPrecoAudit> matrizOfertaItemPrecoAuditList) {
		this.matrizOfertaItemPrecoAuditList = matrizOfertaItemPrecoAuditList;
	}

	public List<MatrizOfertaItemPreco> getMatrizOfertaItemPrecoList() {
		return matrizOfertaItemPrecoList;
	}

	public void setMatrizOfertaItemPrecoList(
			List<MatrizOfertaItemPreco> matrizOfertaItemPrecoList) {
		this.matrizOfertaItemPrecoList = matrizOfertaItemPrecoList;
	}

	public String getNmCanal() {
		return nmCanal;
	}

	public void setNmCanal(String nmCanal) {
		this.nmCanal = nmCanal;
	}

	public String getNmUsuarioAlteracao() {
		return nmUsuarioAlteracao;
	}

	public void setNmUsuarioAlteracao(String nmUsuarioAlteracao) {
		this.nmUsuarioAlteracao = nmUsuarioAlteracao;
	}

	public String getNmUsuarioCriacao() {
		return nmUsuarioCriacao;
	}

	public void setNmUsuarioCriacao(String nmUsuarioCriacao) {
		this.nmUsuarioCriacao = nmUsuarioCriacao;
	}

	public List<PrecoProduto> getPrecoProdutoList() {
		return precoProdutoList;
	}

	public void setPrecoProdutoList(List<PrecoProduto> precoProdutoList) {
		this.precoProdutoList = precoProdutoList;
	}

	public List<ProdutoCanal> getProdutoCanaList() {
		return produtoCanaList;
	}

	public void setProdutoCanaList(List<ProdutoCanal> produtoCanaList) {
		this.produtoCanaList = produtoCanaList;
	}

	public String getSgCanal() {
		return sgCanal;
	}

	public void setSgCanal(String sgCanal) {
		this.sgCanal = sgCanal;
	}

	public String getSgVivoNet() {
		return sgVivoNet;
	}

	public void setSgVivoNet(String sgVivoNet) {
		this.sgVivoNet = sgVivoNet;
	}

	public List<TipoProdutoCondPagto> getTipoProdutoCondPagtoList() {
		return tipoProdutoCondPagtoList;
	}

	public void setTipoProdutoCondPagtoList(
			List<TipoProdutoCondPagto> tipoProdutoCondPagtoList) {
		this.tipoProdutoCondPagtoList = tipoProdutoCondPagtoList;
	}
}