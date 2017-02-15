package br.com.vivo.catalogoPRS.pageflows.shared.form;

import java.util.List;
import java.util.Map;

import org.apache.struts.validator.ValidatorActionForm;

import com.indracompany.catalogo.datalayer.OrganizacaoVenda;

import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoBuscarListaItensListaItensItens;

public class ItensMatrizOfertaForm extends ValidatorActionForm implements java.io.Serializable {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -3029962679991121969L;
	private Long paginaSolicitada;
	private Long idMatrizOferta;
	private Long idMatrizOfertaItemPreco;
	private Long idOfertaSap;
	private String valorItemAtual;
	private String valorItemNovo;
	private String valorBase;
	private String valorChip;
	private String valorItemAVistaNovo;
	private String valorItemAvistaAtual;
	private String valorBaseAVista;
	private String valorChipAVista;
	private String cdCodigo;
	private String dsProduto;
	private String cdOfertaSap;
	private String dscOfertaSAP;
	private String sgUtilizacao;
	private String sgOrganizacaoVendas;
	private String canalVendas;
	private String codigoArea;
	private String dtVigenciaForm;
	private String dtInicioAtual;
	private String dtFimAtual;
	private String dtInicioNova;
	private String dtFimNova;
	private String precificacao;
	private String sgCanalDistribuicao;
	private String sgAcao;
	private Long idCanalDistricuicao;
	private Long idAcao;
	private String trash;
	private Long idCanalAtendimento;
	private Long idEscritorioVenda;
	
	private List<OrganizacaoVenda> listOrganizacaoVendasValida;
	
	private String[] organizacoesSelecionadas;
	private String[] dddsSelecionados;
	
	private ResultadoBuscarListaItensListaItensItens[] itensLista;
	
	private Map<String, String> mapaDataIncial; 
	private Map<String, String> mapaDataFinal; 
	
    private String larguraPopup;
    private String alturaPopup;
    
    
    
    public ItensMatrizOfertaForm() {
    	this.precificacao = "Parcelado";    	
    }
	
    public Long getIdEscritorioVenda() {
		return idEscritorioVenda;
	}
	public void setIdEscritorioVenda(Long idEscritorioVenda) {
		this.idEscritorioVenda = idEscritorioVenda;
	}
	public String getCanalVendas() {
		return canalVendas;
	}
	public void setCanalVendas(String canalVendas) {
		this.canalVendas = canalVendas;
	}
	public String getCdCodigo() {
		return cdCodigo;
	}
	public void setCdCodigo(String cdCodigo) {
		this.cdCodigo = cdCodigo;
	}
	public String getCdOfertaSap() {
		return cdOfertaSap;
	}
	public void setCdOfertaSap(String cdOfertaSap) {
		this.cdOfertaSap = cdOfertaSap;
	}
	public String getCodigoArea() {
		return codigoArea;
	}
	public void setCodigoArea(String codigoArea) {
		this.codigoArea = codigoArea;
	}
	public String getDscOfertaSAP() {
		return dscOfertaSAP;
	}
	public void setDscOfertaSAP(String dscOfertaSAP) {
		this.dscOfertaSAP = dscOfertaSAP;
	}
	public String getDsProduto() {
		return dsProduto;
	}
	public void setDsProduto(String dsProduto) {
		this.dsProduto = dsProduto;
	}
	public String getDtFimAtual() {
		return dtFimAtual;
	}
	public void setDtFimAtual(String dtFimAtual) {
		this.dtFimAtual = dtFimAtual;
	}
	public String getDtFimNova() {
		return dtFimNova;
	}
	public void setDtFimNova(String dtFimNova) {
		this.dtFimNova = dtFimNova;
	}
	public String getDtInicioAtual() {
		return dtInicioAtual;
	}
	public void setDtInicioAtual(String dtInicioAtual) {
		this.dtInicioAtual = dtInicioAtual;
	}
	public String getDtInicioNova() {
		return dtInicioNova;
	}
	public void setDtInicioNova(String dtInicioNova) {
		this.dtInicioNova = dtInicioNova;
	}
	public String getDtVigenciaForm() {
		return dtVigenciaForm;
	}
	public void setDtVigenciaForm(String dtVigenciaForm) {
		this.dtVigenciaForm = dtVigenciaForm;
	}
	public Long getIdMatrizOferta() {
		return idMatrizOferta;
	}
	public void setIdMatrizOferta(Long idMatrizOferta) {
		this.idMatrizOferta = idMatrizOferta;
	}
	public Long getIdMatrizOfertaItemPreco() {
		return idMatrizOfertaItemPreco;
	}
	public void setIdMatrizOfertaItemPreco(Long idMatrizOfertaItemPreco) {
		this.idMatrizOfertaItemPreco = idMatrizOfertaItemPreco;
	}
	public Long getIdOfertaSap() {
		return idOfertaSap;
	}
	public void setIdOfertaSap(Long idOfertaSap) {
		this.idOfertaSap = idOfertaSap;
	}
	public Long getPaginaSolicitada() {
		return paginaSolicitada;
	}
	public void setPaginaSolicitada(Long paginaSolicitada) {
		this.paginaSolicitada = paginaSolicitada;
	}
	public String getPrecificacao() {
		return precificacao;
	}
	public void setPrecificacao(String precificacao) {
		this.precificacao = precificacao;
	}
	public String getSgAcao() {
		return sgAcao;
	}
	public void setSgAcao(String sgAcao) {
		this.sgAcao = sgAcao;
	}
	public String getSgCanalDistribuicao() {
		return sgCanalDistribuicao;
	}
	public void setSgCanalDistribuicao(String sgCanalDistribuicao) {
		this.sgCanalDistribuicao = sgCanalDistribuicao;
	}
	public String getSgOrganizacaoVendas() {
		return sgOrganizacaoVendas;
	}
	public void setSgOrganizacaoVendas(String sgOrganizacaoVendas) {
		this.sgOrganizacaoVendas = sgOrganizacaoVendas;
	}
	public String getSgUtilizacao() {
		return sgUtilizacao;
	}
	public void setSgUtilizacao(String sgUtilizacao) {
		this.sgUtilizacao = sgUtilizacao;
	}
	public String getTrash() {
		return trash;
	}
	public void setTrash(String trash) {
		this.trash = trash;
	}
	public String getValorBase() {
		return valorBase;
	}
	public void setValorBase(String valorBase) {
		this.valorBase = valorBase;
	}
	public String getValorBaseAVista() {
		return valorBaseAVista;
	}
	public void setValorBaseAVista(String valorBaseAVista) {
		this.valorBaseAVista = valorBaseAVista;
	}
	public String getValorChip() {
		return valorChip;
	}
	public void setValorChip(String valorChip) {
		this.valorChip = valorChip;
	}
	public String getValorChipAVista() {
		return valorChipAVista;
	}
	public void setValorChipAVista(String valorChipAVista) {
		this.valorChipAVista = valorChipAVista;
	}
	public String getValorItemAtual() {
		return valorItemAtual;
	}
	public void setValorItemAtual(String valorItemAtual) {
		this.valorItemAtual = valorItemAtual;
	}
	public String getValorItemAvistaAtual() {
		return valorItemAvistaAtual;
	}
	public void setValorItemAvistaAtual(String valorItemAvistaAtual) {
		this.valorItemAvistaAtual = valorItemAvistaAtual;
	}
	public String getValorItemAVistaNovo() {
		return valorItemAVistaNovo;
	}
	public void setValorItemAVistaNovo(String valorItemAVistaNovo) {
		this.valorItemAVistaNovo = valorItemAVistaNovo;
	}
	public String getValorItemNovo() {
		return valorItemNovo;
	}
	public void setValorItemNovo(String valorItemNovo) {
		this.valorItemNovo = valorItemNovo;
	}
	public Long getIdAcao() {
		return idAcao;
	}
	public void setIdAcao(Long idAcao) {
		this.idAcao = idAcao;
	}
	public Long getIdCanalDistricuicao() {
		return idCanalDistricuicao;
	}
	public void setIdCanalDistricuicao(Long idCanalDistricuicao) {
		this.idCanalDistricuicao = idCanalDistricuicao;
	}
	public Long getIdCanalAtendimento() {
		return idCanalAtendimento;
	}
	public void setIdCanalAtendimento(Long idCanalAtendimento) {
		this.idCanalAtendimento = idCanalAtendimento;
	}
	public List<OrganizacaoVenda> getListOrganizacaoVendasValida() {
		return listOrganizacaoVendasValida;
	}
	public void setListOrganizacaoVendasValida(List<OrganizacaoVenda> listOrganizacaoVendasValida) {
		this.listOrganizacaoVendasValida = listOrganizacaoVendasValida;
	}
	public String[] getOrganizacoesSelecionadas() {
		return organizacoesSelecionadas;
	}
	public void setOrganizacoesSelecionadas(String[] organizacoesSelecionadas) {
		this.organizacoesSelecionadas = organizacoesSelecionadas;
	}
	public String[] getDddsSelecionados() {
		return dddsSelecionados;
	}
	public void setDddsSelecionados(String[] dddsSelecionados) {
		this.dddsSelecionados = dddsSelecionados;
	}
	public ResultadoBuscarListaItensListaItensItens[] getItensLista() {
		return itensLista;
	}
	public void setItensLista(ResultadoBuscarListaItensListaItensItens[] itensLista) {
		this.itensLista = itensLista;
	}
	public Map<String, String> getMapaDataIncial() {
		return mapaDataIncial;
	}
	public void setMapaDataIncial(Map<String, String> mapaDataIncial) {
		this.mapaDataIncial = mapaDataIncial;
	}
	public Map<String, String> getMapaDataFinal() {
		return mapaDataFinal;
	}
	public void setMapaDataFinal(Map<String, String> mapaDataFinal) {
		this.mapaDataFinal = mapaDataFinal;
	}
	public String getLarguraPopup() {
		return larguraPopup;
	}
	public void setLarguraPopup(String larguraPopup) {
		this.larguraPopup = larguraPopup;
	}
	public String getAlturaPopup() {
		return alturaPopup;
	}
	public void setAlturaPopup(String alturaPopup) {
		this.alturaPopup = alturaPopup;
	}
	
}
