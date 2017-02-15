package workflow.Relatorios;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.admsistemas.vo.AdmArvoreContainerVODocument.AdmArvoreContainerVO;
import br.com.vivo.fo.admsistemas.vo.AdmContatoVODocument.AdmContatoVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.workflow.vo.WFListaArquivosGeradosVODocument.WFListaArquivosGeradosVO;
import br.com.vivo.fo.workflow.vo.WFRelatorioDinamicoVODocument.WFRelatorioDinamicoVO;
import br.com.vivo.fo.workflow.vo.WFRelatoriosFiltrosVODocument.WFRelatoriosFiltrosVO;

public class RelatorioForm extends ActionForm {

	private static final long serialVersionUID = -1123250301986932518L;

	private boolean exportar;
	private LinkedHashMap cabecRelatorios;
	private String idContatoOrigem;

	private String nmContatoOrigem;
	private String dsGrupo;
	private String gestor;
	private String[] estadoUsuarioSel;
	private String mostrarFiltros;
	private String scriptArvore;
	private String contatoSel;
	private String gerarAction;
	private String atualizacao;
	private String uf;
	private int nivel;
	private int alarme;
	private LinkedHashMap htHorariosRelatorios;
	private ArrayList lstHorarios;
	private LinkedHashMap lhmRelatorio;
	private String inicioAction;
	private String detalheScript;
	private String tipoRelatorio;
	private String dtFim;
	private String dtInicio;
	private String detalheAction;
	private String[] valoresDetalhe;
	private String[] colunasDetalhe;
	private WFRelatoriosFiltrosVO filtro;
	private WFRelatorioDinamicoVO wFRelatorioDinamicoVO;
	private WFRelatorioDinamicoVO relatorio;
	private String prazo;
	private String[] quebra;
	private String operadoraSel;
	private String regionalSel;
	private String grupoSel;
	private String[] estadoSel;
	private String representanteSel;
	private String carteiraSel;
	private String segmentoSel;
	private String diretoriaSel;
	private String areaSel;
	private String sessaoSel;
	private String cargoSel;
	private String nivelSel;
	private String ufSel;
	private String pageNumber = ConstantesCRM.SVAZIO;
	private String offSet = ConstantesCRM.SVAZIO;
	private String hasNext = ConstantesCRM.SVAZIO;
	private String idFiltro = ConstantesCRM.SVAZIO;
	private String dsFiltro = ConstantesCRM.SVAZIO;
	private WFListaArquivosGeradosVO wfListaArquivosGeradosVO = WFListaArquivosGeradosVO.Factory
			.newInstance();

	private String flQuantitativo = ConstantesCRM.SVAZIO;
	private String dsLogin = ConstantesCRM.SVAZIO;

	private String[] folhasSelecionadas;
	private AdmArvoreContainerVO arvoreContato;

	private AdmContatoVO[] admContatoVO;

	public void setArvoreContato(AdmArvoreContainerVO arvoreContato) {
		this.arvoreContato = arvoreContato;
	}

	public AdmArvoreContainerVO getArvoreContato() {
		return this.arvoreContato;
	}

	private WFRelatoriosFiltrosVO wfRelatoriosFiltrosVO;

	public String getIdFiltro() {
		return this.idFiltro;
	}

	public void setIdFiltro(String idFiltro) {
		this.idFiltro = idFiltro;
	}

	public String getDsFiltro() {
		return this.dsFiltro;
	}

	public void setDsFiltro(String dsFiltro) {
		this.dsFiltro = dsFiltro;
	}

	public String getOperadoraSel() {
		return this.operadoraSel;
	}

	public void setOperadoraSel(String operadoraSel) {
		this.operadoraSel = operadoraSel;
	}

	public String getDsLogin() {
		return this.dsLogin;
	}

	public void setDsLogin(String dsLogin) {
		this.dsLogin = dsLogin;
	}

	public String getPageNumber() {
		return this.pageNumber;
	}

	public void setPageNumber(String value) {
		this.pageNumber = value;
	}

	public String getHasNext() {
		return this.hasNext;
	}

	public void setHasNext(String value) {
		this.hasNext = value;
	}

	public String getOffSet() {
		return this.offSet;
	}

	public void setOffSet(String value) {
		this.offSet = value;
	}

	public String getRegionalSel() {
		return this.regionalSel;
	}

	public void setRegionalSel(String regionalSel) {
		this.regionalSel = regionalSel;
	}

	public String getGrupoSel() {
		return this.grupoSel;
	}

	public void setGrupoSel(String grupoSel) {
		this.grupoSel = grupoSel;
	}

	public String[] getEstadoSel() {
		return this.estadoSel;
	}

	public void setEstadoSel(String[] estadoSel) {
		this.estadoSel = estadoSel;
	}

	public String[] getFolhasSelecionadas() {
		return this.folhasSelecionadas;
	}

	public void setFolhasSelecionadas(String[] folhasSelecionadas) {
		this.folhasSelecionadas = folhasSelecionadas;
	}

	public AdmContatoVO[] getAdmContatoVO() {
		return this.admContatoVO;
	}

	public void setAdmContatoVO(AdmContatoVO[] admContatoVO) {
		this.admContatoVO = admContatoVO;
	}

	public String getRepresentanteSel() {
		return this.representanteSel;
	}

	public void setRepresentanteSel(String representanteSel) {
		this.representanteSel = representanteSel;
	}

	public String getCarteiraSel() {
		return this.carteiraSel;
	}

	public void setCarteiraSel(String carteiraSel) {
		this.carteiraSel = carteiraSel;
	}

	public String getSegmentoSel() {
		return this.segmentoSel;
	}

	public void setSegmentoSel(String segmentoSel) {
		this.segmentoSel = segmentoSel;
	}

	public String getDiretoriaSel() {
		return this.diretoriaSel;
	}

	public void setDiretoriaSel(String diretoriaSel) {
		this.diretoriaSel = diretoriaSel;
	}

	public String getAreaSel() {
		return this.areaSel;
	}

	public void setAreaSel(String areaSel) {
		this.areaSel = areaSel;
	}

	public String getSessaoSel() {
		return this.sessaoSel;
	}

	public void setSessaoSel(String sessaoSel) {
		this.sessaoSel = sessaoSel;
	}

	public String getCargoSel() {
		return this.cargoSel;
	}

	public void setCargoSel(String cargoSel) {
		this.cargoSel = cargoSel;
	}

	public String getNivelSel() {
		return this.nivelSel;
	}

	public void setNivelSel(String nivelSel) {
		this.nivelSel = nivelSel;
	}

	public String getUfSel() {
		return this.ufSel;
	}

	public void setUfSel(String ufSel) {
		this.ufSel = ufSel;
	}

	public WFRelatoriosFiltrosVO getWFRelatoriosFiltrosVO() {
		return this.wfRelatoriosFiltrosVO;
	}

	public void setWFRelatoriosFiltrosVO(WFRelatoriosFiltrosVO wfRelatoriosFiltrosVO) {
		this.wfRelatoriosFiltrosVO = wfRelatoriosFiltrosVO;
	}

	public WFListaArquivosGeradosVO getWFListaArquivosGeradosVO() {
		return this.wfListaArquivosGeradosVO;
	}

	public void setWFListaArquivosGeradosVO(WFListaArquivosGeradosVO wfListaArquivosGeradosVO) {
		this.wfListaArquivosGeradosVO = wfListaArquivosGeradosVO;
	}

	public void setQuebra(String[] quebra) {
		this.quebra = quebra;
	}

	public String[] getQuebra() {
		if (this.quebra == null || this.quebra.length == 0) {
			this.quebra = new String[1];
		}
		return this.quebra;
	}

	public void setPrazo(String prazo) {
		this.prazo = prazo;
	}

	public String getPrazo() {
		return this.prazo;
	}

	public void setwFRelatorioDinamicoVO(WFRelatorioDinamicoVO wFRelatorioDinamicoVO) {
		this.wFRelatorioDinamicoVO = wFRelatorioDinamicoVO;
	}

	public WFRelatorioDinamicoVO getwFRelatorioDinamicoVO() {
		return this.wFRelatorioDinamicoVO;
	}

	public void setFiltro(WFRelatoriosFiltrosVO filtro) {
		this.filtro = filtro;
	}

	public WFRelatoriosFiltrosVO getFiltro() {
		return this.filtro;
	}

	public void setColunasDetalhe(String[] colunasDetalhe) {
		this.colunasDetalhe = colunasDetalhe;
	}

	public String[] getColunasDetalhe() {
		if (this.colunasDetalhe == null || this.colunasDetalhe.length == 0) {
			this.colunasDetalhe = new String[1];
		}
		return this.colunasDetalhe;
	}

	public void setValoresDetalhe(String[] valoresDetalhe) {
		this.valoresDetalhe = valoresDetalhe;
	}

	public String[] getValoresDetalhe() {
		if (this.valoresDetalhe == null || this.valoresDetalhe.length == 0) {
			this.valoresDetalhe = new String[1];
		}
		return this.valoresDetalhe;
	}

	public void setDetalheAction(String detalheAction) {
		this.detalheAction = detalheAction;
	}

	public String getDetalheAction() {
		return this.detalheAction;
	}

	public void setDtInicio(String dtInicio) {
		this.dtInicio = dtInicio;
	}

	public String getDtInicio() {
		return this.dtInicio;
	}

	public void setDtFim(String dtFim) {
		this.dtFim = dtFim;
	}

	public String getDtFim() {
		return this.dtFim;
	}

	public void setTipoRelatorio(String tipoRelatorio) {
		this.tipoRelatorio = tipoRelatorio;
	}

	public String getTipoRelatorio() {
		return this.tipoRelatorio;
	}

	public void setDetalheScript(String detalheScript) {
		this.detalheScript = detalheScript;
	}

	public String getDetalheScript() {
		return this.detalheScript;
	}

	public void setInicioAction(String inicioAction) {
		this.inicioAction = inicioAction;
	}

	public String getInicioAction() {
		return this.inicioAction;
	}

	public void setHtHorariosRelatorios(LinkedHashMap htHorariosRelatorios) {
		this.htHorariosRelatorios = htHorariosRelatorios;
	}

	public LinkedHashMap getHtHorariosRelatorios() {
		return this.htHorariosRelatorios;
	}

	public void setAlarme(int alarme) {
		this.alarme = alarme;
	}

	public int getAlarme() {
		return this.alarme;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public int getNivel() {
		return this.nivel;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getUf() {
		return this.uf;
	}

	public void setAtualizacao(String atualizacao) {
		this.atualizacao = atualizacao;
	}

	public String getAtualizacao() {
		return this.atualizacao;
	}

	public void setGerarAction(String gerarAction) {
		this.gerarAction = gerarAction;
	}

	public String getGerarAction() {
		return this.gerarAction;
	}

	public void setContatoSel(String contatoSel) {
		this.contatoSel = contatoSel;
	}

	public String getContatoSel() {
		return this.contatoSel;
	}

	public void setScriptArvore(String scriptArvore) {
		this.scriptArvore = scriptArvore;
	}

	public String getScriptArvore() {
		return this.scriptArvore;
	}

	public void setMostrarFiltros(String mostrarFiltros) {
		this.mostrarFiltros = mostrarFiltros;
	}

	public String getMostrarFiltros() {
		return this.mostrarFiltros;
	}

	public void setEstadoUsuarioSel(String[] estadoUsuarioSel) {
		this.estadoUsuarioSel = estadoUsuarioSel;
	}

	public String[] getEstadoUsuarioSel() {
		return this.estadoUsuarioSel;
	}

	public void setGestor(String gestor) {
		this.gestor = gestor;
	}

	public String getGestor() {
		return this.gestor;
	}

	public void setDsGrupo(String dsGrupo) {
		this.dsGrupo = dsGrupo;
	}

	public String getDsGrupo() {
		return this.dsGrupo;
	}

	public void setIdContatoOrigem(String idContatoOrigem) {
		this.idContatoOrigem = idContatoOrigem;
	}

	public String getIdContatoOrigem() {
		return this.idContatoOrigem;
	}

	public void setNmContatoOrigem(String nmContatoOrigem) {
		this.nmContatoOrigem = nmContatoOrigem;
	}

	public String getNmContatoOrigem() {
		return this.nmContatoOrigem;
	}

	public void setFlQuantitativo(String flQuantitativo) {
		this.flQuantitativo = flQuantitativo;
	}

	public String getFlQuantitativo() {
		return this.flQuantitativo;
	}

	public void setCabecRelatorios(LinkedHashMap cabecRelatorios) {
		this.cabecRelatorios = cabecRelatorios;
	}

	public LinkedHashMap getCabecRelatorios() {
		return this.cabecRelatorios;
	}

	public void setExportar(boolean exportar) {
		this.exportar = exportar;
	}

	public boolean isExportar() {
		return this.exportar;
	}
}