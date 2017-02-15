package br.com.vivo.catalogoPRS.pageflows.shared.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.struts.validator.ValidatorActionForm;

import br.com.vivo.catalogoPRS.ws.catalogoCanalAtendimento.sn.ResultadoBuscarCanalAtendimentoCanalAtendimento;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoBuscarListaCabecalhoListaCabecalhoCabecalho;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoBuscarListaTipoMatrizResultadoListaTipoMatrizTipoMatriz;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoBuscarListaVariaveisListaVariaveisVariaveis;
import br.com.vivo.catalogoPRS.ws.catalogoPlataforma.sn.ResultadoBuscarListaPlataformaPlataforma;
import br.com.vivo.catalogoPRS.ws.catalogoTipoOperacao.sn.ResultadoBuscarListaTipoOperacaoListaTipoOperacaoTipoOperacao;

public class CadastroCabecalhoMatrizOfertaForm extends ValidatorActionForm implements java.io.Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -755607041132367059L;

	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	private Long paginaSolicitada;
	private Long idMatrizOferta;
	private Long idMatrizOfertaTipo;
	private Long idMatrizOfertaVariaveis;
	private Long idTipoCliente;
	private Long[] idsPlataforma;
	private Long[] idsCanal;
	private Long[] idsTipoOperacao;
	private String nmMatrizOferta;
	private String nmMatrizOfertaTipo;
	private String sgCarteira;
	private String sgSegmento;
	private String dtVigenciaInicial;
	private String dtVigenciaFinal;
	private String dtVigencia;
	private String inicialVigencia;
	private String finalVigencia;
	private List<ResultadoBuscarListaCabecalhoListaCabecalhoCabecalho> cabecalhoMatrizOfertaLista;
    private java.util.Calendar dtInicial;
    private java.util.Calendar dtFinal;
    
	private Map<String, String> mapaDataIncial; 
	private Map<String, String> mapaDataFinal; 
	
    private String larguraPopup;
    private String alturaPopup;
    
    private String ckeckbox_plataformaAll;
    private String ckeckbox_canalAll;
    private String ckeckbox_tpOperacaoAll;
    
    
    private ResultadoBuscarListaTipoMatrizResultadoListaTipoMatrizTipoMatriz[] tipoMatrizLista;    
    private ResultadoBuscarListaPlataformaPlataforma[] plataformaLista;                 
    private ResultadoBuscarListaTipoOperacaoListaTipoOperacaoTipoOperacao[] tipoOperacaoLista;    
    private ResultadoBuscarCanalAtendimentoCanalAtendimento[] listaCanal;    
    private ResultadoBuscarListaVariaveisListaVariaveisVariaveis[] listaVariaveis;
    

	public String getFinalVigencia() {
		return finalVigencia;
	}
	public void setFinalVigencia(String finalVigencia) {
		this.finalVigencia = finalVigencia;
	}
	public String getInicialVigencia() {
		return inicialVigencia;
	}
	public void setInicialVigencia(String inicialVigencia) {
		this.inicialVigencia = inicialVigencia;
	}
	public Long getPaginaSolicitada() {
		return paginaSolicitada;
	}
	public void setPaginaSolicitada(Long paginaSolicitada) {
		this.paginaSolicitada = paginaSolicitada;
	}
	public Long getIdMatrizOferta() {
		return idMatrizOferta;
	}
	public void setIdMatrizOferta(Long idMatrizOferta) {
		this.idMatrizOferta = idMatrizOferta;
	}
	public Long getIdMatrizOfertaTipo() {
		return idMatrizOfertaTipo;
	}
	public void setIdMatrizOfertaTipo(Long idMatrizOfertaTipo) {
		this.idMatrizOfertaTipo = idMatrizOfertaTipo;
	}
	public String getNmMatrizOferta() {
		return nmMatrizOferta;
	}
	public void setNmMatrizOferta(String nmMatrizOferta) {
		this.nmMatrizOferta = nmMatrizOferta;
	}
	public String getNmMatrizOfertaTipo() {
		return nmMatrizOfertaTipo;
	}
	public void setNmMatrizOfertaTipo(String nmMatrizOfertaTipo) {
		this.nmMatrizOfertaTipo = nmMatrizOfertaTipo;
	}
	
	public String getDtVigencia() {
		return dtVigencia;
	}
	public void setDtVigencia(String dtVigencia) {
		this.dtVigencia = dtVigencia;
	}
	public Long[] getIdsPlataforma() {
		return idsPlataforma;
	}
	public void setIdsPlataforma(Long[] idsPlataforma) {
		this.idsPlataforma = idsPlataforma;
	}
	public Long[] getIdsCanal() {
		return idsCanal;
	}
	public void setIdsCanal(Long[] idsCanal) {
		this.idsCanal = idsCanal;
	}
	public Long[] getIdsTipoOperacao() {
		return idsTipoOperacao;
	}
	public void setIdsTipoOperacao(Long[] idsTipoOperacao) {
		this.idsTipoOperacao = idsTipoOperacao;
	}
	public String getDtVigenciaFinal() {
		return dtVigenciaFinal;
	}
	public void setDtVigenciaFinal(String dtVigenciaFinal) {
		this.dtVigenciaFinal = dtVigenciaFinal;
	}
	public String getDtVigenciaInicial() {
		return dtVigenciaInicial;
	}
	public void setDtVigenciaInicial(String dtVigenciaInicial) {
		this.dtVigenciaInicial = dtVigenciaInicial;
	}
	public Calendar getDataVigencia() throws ParseException {
		Calendar dataVigencia = Calendar.getInstance();
		if(this.getDtVigencia() != null && !this.getDtVigencia().equalsIgnoreCase("")){
			dataVigencia.setTime(sdf.parse(this.getDtVigencia()));
		}
		return dataVigencia ;
	}
	public Calendar getVigenciaInicial() throws ParseException {
		Calendar calendar = Calendar.getInstance();
		if(this.getDtVigenciaInicial() != null && !this.getDtVigenciaInicial().equalsIgnoreCase("")) {
			calendar.setTime(sdf.parse(this.getDtVigenciaInicial()));
		}
		return calendar;
	}
	public Calendar getVigenciaFinal() throws ParseException {
		Calendar calendar = Calendar.getInstance();
		if(this.getDtVigenciaFinal() != null && !this.getDtVigenciaFinal().equalsIgnoreCase("")) {
			calendar.setTime(sdf.parse(this.getDtVigenciaFinal()));
		}
		return calendar;
	}
	public Long getIdTipoCliente() {
		return idTipoCliente;
	}
	public void setIdTipoCliente(Long idTipoCliente) {
		this.idTipoCliente = idTipoCliente;
	}
	public String getSgCarteira() {
		return sgCarteira;
	}
	public void setSgCarteira(String sgCarteira) {
		this.sgCarteira = sgCarteira;
	}
	public String getSgSegmento() {
		return sgSegmento;
	}
	public void setSgSegmento(String sgSegmento) {
		this.sgSegmento = sgSegmento;
	}
	public Long getIdMatrizOfertaVariaveis() {
		return idMatrizOfertaVariaveis;
	}
	public void setIdMatrizOfertaVariaveis(Long idMatrizOfertaVariaveis) {
		this.idMatrizOfertaVariaveis = idMatrizOfertaVariaveis;
	}
	public List<ResultadoBuscarListaCabecalhoListaCabecalhoCabecalho> getCabecalhoMatrizOfertaLista() {
		return cabecalhoMatrizOfertaLista;
	}
	public void setCabecalhoMatrizOfertaLista(
			List<ResultadoBuscarListaCabecalhoListaCabecalhoCabecalho> cabecalhoMatrizOfertaLista) {
		this.cabecalhoMatrizOfertaLista = cabecalhoMatrizOfertaLista;
	}
	public java.util.Calendar getDtInicial() {
		return dtInicial;
	}
	public void setDtInicial(java.util.Calendar dtInicial) {
		this.dtInicial = dtInicial;
	}
	public java.util.Calendar getDtFinal() {
		return dtFinal;
	}
	public void setDtFinal(java.util.Calendar dtFinal) {
		this.dtFinal = dtFinal;
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
	public ResultadoBuscarListaTipoMatrizResultadoListaTipoMatrizTipoMatriz[] getTipoMatrizLista() {
		return tipoMatrizLista;
	}
	public void setTipoMatrizLista(ResultadoBuscarListaTipoMatrizResultadoListaTipoMatrizTipoMatriz[] tipoMatrizLista) {
		this.tipoMatrizLista = tipoMatrizLista;
	}

	public ResultadoBuscarListaTipoOperacaoListaTipoOperacaoTipoOperacao[] getTipoOperacaoLista() {
		return tipoOperacaoLista;
	}
	public void setTipoOperacaoLista(ResultadoBuscarListaTipoOperacaoListaTipoOperacaoTipoOperacao[] tipoOperacaoLista) {
		this.tipoOperacaoLista = tipoOperacaoLista;
	}
	public ResultadoBuscarListaPlataformaPlataforma[] getPlataformaLista() {
		return plataformaLista;
	}
	public void setPlataformaLista(ResultadoBuscarListaPlataformaPlataforma[] plataformaLista) {
		this.plataformaLista = plataformaLista;
	}
	public String getCkeckbox_plataformaAll() {
		return ckeckbox_plataformaAll;
	}
	public void setCkeckbox_plataformaAll(String ckeckbox_plataformaAll) {
		this.ckeckbox_plataformaAll = ckeckbox_plataformaAll;
	}
	public String getCkeckbox_canalAll() {
		return ckeckbox_canalAll;
	}
	public void setCkeckbox_canalAll(String ckeckbox_canalAll) {
		this.ckeckbox_canalAll = ckeckbox_canalAll;
	}
	public String getCkeckbox_tpOperacaoAll() {
		return ckeckbox_tpOperacaoAll;
	}
	public void setCkeckbox_tpOperacaoAll(String ckeckbox_tpOperacaoAll) {
		this.ckeckbox_tpOperacaoAll = ckeckbox_tpOperacaoAll;
	}
	public ResultadoBuscarCanalAtendimentoCanalAtendimento[] getListaCanal() {
		return listaCanal;
	}
	public void setListaCanal(ResultadoBuscarCanalAtendimentoCanalAtendimento[] listaCanal) {
		this.listaCanal = listaCanal;
	}
	public ResultadoBuscarListaVariaveisListaVariaveisVariaveis[] getListaVariaveis() {
		return listaVariaveis;
	}
	public void setListaVariaveis(ResultadoBuscarListaVariaveisListaVariaveisVariaveis[] listaVariaveis) {
		this.listaVariaveis = listaVariaveis;
	}
	
}
