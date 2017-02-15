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
@Table(name="PRODUTO", schema = "CATALOGOPRS_OW")
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public Produto() {}
	
	@Id
	@Column(name = "IDPRODUTO")
	private Integer idProduto;

	@Column(name = "DSNOTA")
	private String dsNota;

	@Column(name = "DSPRODUTO")
	private String dsProduto;

	@Column(name = "DSSAP")
	private String dsSAP;

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

    @Column(name = "NMPRODUTO")
	private String nmProduto;

    @Column(name = "NMUSUARIOALTERACAO")
	private String nmUsuarioAlteracao;

    @Column(name = "NMUSUARIOCRIACAO")
	private String nmUsuarioCriacao;

    @Column(name = "SGCLASSEAVALIACAO")
	private String sgClasseAvaliacao;

    @Column(name = "SGGRUPOCONTABIL")
	private String sgGrupoContabil;

    @Column(name = "SGSETORATIVIDADE")
	private String sgSetorAtividade;

	//bi-directional many-to-one association to Estoqueprodutocentro
	@OneToMany(mappedBy="produto", fetch=FetchType.LAZY)
	private List<EstoqueProdutoCentro> estoqueProdutoCentroList;

	//bi-directional many-to-one association to Matrizofertaitem
	@OneToMany(mappedBy="produto",fetch=FetchType.LAZY)
	private List<MatrizOfertaItem> matrizOfertaItemList;

	//bi-directional many-to-one association to Precoproduto
	@OneToMany(mappedBy="produto",fetch=FetchType.LAZY)
	private List<PrecoProduto> precoProdutoList;

	//bi-directional many-to-one association to Cor
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCOR")
	private Cor cor;

	//bi-directional many-to-one association to Fabricante
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDFABRICANTE", nullable=false)
	private Fabricante fabricante;

	//bi-directional many-to-one association to Tecnologia
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDTECNOLOGIA")
	private Tecnologia tecnologia;

	//bi-directional many-to-one association to Tipoproduto
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDTIPOPRODUTO", nullable=false)
	private TipoProduto tipoProduto;
	
	// bi-directional many-to-one association to Gama
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDGAMA")
	private Gama gama;

	//bi-directional many-to-one association to Produtocanal
	@OneToMany(mappedBy="produto",fetch=FetchType.LAZY)
	private List<ProdutoCanal> produtoCanaList;

	//bi-directional many-to-one association to Produtodisponibilidadepp
	@OneToMany(mappedBy="produto",fetch=FetchType.LAZY)
	private List<ProdutoDisponibilidadePP> produtoDisponibilidadePPList;

	//bi-directional many-to-one association to Produtomodelovenda
	@OneToMany(mappedBy="produto",fetch=FetchType.LAZY)
	private List<ProdutoModeloVenda> produtoModeloVendaList;

	//bi-directional many-to-one association to Produtoorganizacaovenda
	@OneToMany(mappedBy="produto",fetch=FetchType.LAZY)
	private List<ProdutoOrganizacaoVenda> produtoOrganizacaoVendaList;

	//bi-directional many-to-one association to Produtoplano
	@OneToMany(mappedBy="produto",fetch=FetchType.LAZY)
	private List<ProdutoPlano> produtoPlanoList;

	//bi-directional many-to-one association to Produtoplataforma
	@OneToMany(mappedBy="produto",fetch=FetchType.LAZY)
	private List<ProdutoPlataforma> produtoPlataformaList;

	//bi-directional many-to-one association to Produtoservico
	@OneToMany(mappedBy="produto",fetch=FetchType.LAZY)
	private List<ProdutoServico> produtoServicoList;

	//bi-directional many-to-one association to Sistemaproduto
	@OneToOne(mappedBy="produto",fetch=FetchType.LAZY)
	private SistemaProduto sistemaProduto;

	//bi-directional many-to-one association to Sistemaproduto
	@OneToMany(mappedBy="produto",fetch=FetchType.LAZY)
	private List<ProdutoGrupoProduto> produtoGrupoProdutos;
	
	public Cor getCor() {
		return cor;
	}

	public List<ProdutoGrupoProduto> getProdutoGrupoProdutos() {
		return produtoGrupoProdutos;
	}

	public void setProdutoGrupoProdutos(List<ProdutoGrupoProduto> produtoGrupoProdutos) {
		this.produtoGrupoProdutos = produtoGrupoProdutos;
	}

	public void setCor(Cor cor) {
		this.cor = cor;
	}

	public String getDsNota() {
		return dsNota;
	}

	public void setDsNota(String dsNota) {
		this.dsNota = dsNota;
	}

	public String getDsProduto() {
		return dsProduto;
	}

	public void setDsProduto(String dsProduto) {
		this.dsProduto = dsProduto;
	}

	public String getDsSAP() {
		return dsSAP;
	}

	public void setDsSAP(String dsSAP) {
		this.dsSAP = dsSAP;
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

	public List<EstoqueProdutoCentro> getEstoqueProdutoCentroList() {
		return estoqueProdutoCentroList;
	}

	public void setEstoqueProdutoCentroList(
			List<EstoqueProdutoCentro> estoqueProdutoCentroList) {
		this.estoqueProdutoCentroList = estoqueProdutoCentroList;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public Integer getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}

	public String getInDisponivel() {
		return inDisponivel;
	}

	public void setInDisponivel(String inDisponivel) {
		this.inDisponivel = inDisponivel;
	}

	public List<MatrizOfertaItem> getMatrizOfertaItemList() {
		return matrizOfertaItemList;
	}

	public void setMatrizOfertaItemList(List<MatrizOfertaItem> matrizOfertaItemList) {
		this.matrizOfertaItemList = matrizOfertaItemList;
	}

	public String getNmProduto() {
		return nmProduto;
	}

	public void setNmProduto(String nmProduto) {
		this.nmProduto = nmProduto;
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

	public List<ProdutoDisponibilidadePP> getProdutoDisponibilidadePPList() {
		return produtoDisponibilidadePPList;
	}

	public void setProdutoDisponibilidadePPList(
			List<ProdutoDisponibilidadePP> produtoDisponibilidadePPList) {
		this.produtoDisponibilidadePPList = produtoDisponibilidadePPList;
	}

	public List<ProdutoModeloVenda> getProdutoModeloVendaList() {
		return produtoModeloVendaList;
	}

	public void setProdutoModeloVendaList(
			List<ProdutoModeloVenda> produtoModeloVendaList) {
		this.produtoModeloVendaList = produtoModeloVendaList;
	}

	public List<ProdutoOrganizacaoVenda> getProdutoOrganizacaoVendaList() {
		return produtoOrganizacaoVendaList;
	}

	public void setProdutoOrganizacaoVendaList(
			List<ProdutoOrganizacaoVenda> produtoOrganizacaoVendaList) {
		this.produtoOrganizacaoVendaList = produtoOrganizacaoVendaList;
	}

	public List<ProdutoPlano> getProdutoPlanoList() {
		return produtoPlanoList;
	}

	public void setProdutoPlanoList(List<ProdutoPlano> produtoPlanoList) {
		this.produtoPlanoList = produtoPlanoList;
	}

	public List<ProdutoPlataforma> getProdutoPlataformaList() {
		return produtoPlataformaList;
	}

	public void setProdutoPlataformaList(
			List<ProdutoPlataforma> produtoPlataformaList) {
		this.produtoPlataformaList = produtoPlataformaList;
	}

	public List<ProdutoServico> getProdutoServicoList() {
		return produtoServicoList;
	}

	public void setProdutoServicoList(List<ProdutoServico> produtoServicoList) {
		this.produtoServicoList = produtoServicoList;
	}

	public String getSgClasseAvaliacao() {
		return sgClasseAvaliacao;
	}

	public void setSgClasseAvaliacao(String sgClasseAvaliacao) {
		this.sgClasseAvaliacao = sgClasseAvaliacao;
	}

	public String getSgGrupoContabil() {
		return sgGrupoContabil;
	}

	public void setSgGrupoContabil(String sgGrupoContabil) {
		this.sgGrupoContabil = sgGrupoContabil;
	}

	public String getSgSetorAtividade() {
		return sgSetorAtividade;
	}

	public void setSgSetorAtividade(String sgSetorAtividade) {
		this.sgSetorAtividade = sgSetorAtividade;
	}

	public SistemaProduto getSistemaProduto() {
		return sistemaProduto;
	}

	public void setSistemaProduto(SistemaProduto sistemaProduto) {
		this.sistemaProduto = sistemaProduto;
	}

	public Tecnologia getTecnologia() {
		return tecnologia;
	}

	public void setTecnologia(Tecnologia tecnologia) {
		this.tecnologia = tecnologia;
	}

	public TipoProduto getTipoProduto() {
		return tipoProduto;
	}

	public void setTipoProduto(TipoProduto tipoProduto) {
		this.tipoProduto = tipoProduto;
	}

	public Gama getGama() {
		return gama;
	}

	public void setGama(Gama gama) {
		this.gama = gama;
	}

}