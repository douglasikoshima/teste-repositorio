package admsistemas.relatorios.relatorioRechamadaDetalhado;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.cliente.vo.ResultsetDocument.Resultset;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoVODocument.FidelizacaoVO.ListasVO.Lista.Disponivel;

public class RelatorioForm extends ActionForm {

	private static final long serialVersionUID = -3896913326067857930L;

	private Resultset resultset;

	private String dataInicio = ConstantesCRM.SVAZIO;
	private String dataTermino = ConstantesCRM.SVAZIO;
	private String ddd = ConstantesCRM.SVAZIO;
	private String numeroTelefone = ConstantesCRM.SVAZIO;
	private String tipoLinha = ConstantesCRM.SVAZIO;
	private String motivoRecontato = ConstantesCRM.SVAZIO;
	private String segmentacao = ConstantesCRM.SVAZIO;
	private String fornecedor = ConstantesCRM.SVAZIO;
	private String site = ConstantesCRM.SVAZIO;
	private String grupo = ConstantesCRM.SVAZIO;

	private Disponivel listaDDD;
	private Disponivel listaTipoLinha;
	private Disponivel listaMotivoRecontato;
	private Disponivel listaSegmento;
	private Disponivel listaFornecedor;
	private Disponivel listaSite;
	private Disponivel listaGrupo;

	// Inicio Listas
	public void setListaDDD(Disponivel listaDDD) {
		this.listaDDD = listaDDD;
	}

	public Disponivel getListaDDD() {
		return this.listaDDD;
	}

	public void setListaTipoLinha(Disponivel listaTipoLinha) {
		this.listaTipoLinha = listaTipoLinha;
	}

	public Disponivel getListaTipoLinha() {
		return this.listaTipoLinha;
	}

	public void setListaMotivoRecontato(Disponivel listaMotivoRecontato) {
		this.listaMotivoRecontato = listaMotivoRecontato;
	}

	public Disponivel getListaMotivoRecontato() {
		return this.listaMotivoRecontato;
	}

	public void setListaSegmento(Disponivel listaSegmento) {
		this.listaSegmento = listaSegmento;
	}

	public Disponivel getListaSegmento() {
		return this.listaSegmento;
	}

	public void setListaFornecedor(Disponivel listaFornecedor) {
		this.listaFornecedor = listaFornecedor;
	}

	public Disponivel getListaFornecedor() {
		return this.listaFornecedor;
	}

	public void setListaSite(Disponivel listaSite) {
		this.listaSite = listaSite;
	}

	public Disponivel getListaSite() {
		return this.listaSite;
	}

	public void setListaGrupo(Disponivel listaGrupo) {
		this.listaGrupo = listaGrupo;
	}

	public Disponivel getListaGrupo() {
		return this.listaGrupo;
	}

	// Termino Listas

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}

	public String getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(String dataTermino) {
		this.dataTermino = dataTermino;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getNumeroTelefone() {
		return numeroTelefone;
	}

	public void setNumeroTelefone(String numeroTelefone) {
		this.numeroTelefone = numeroTelefone;
	}

	public String getTipoLinha() {
		return tipoLinha;
	}

	public void setTipoLinha(String tipoLinha) {
		this.tipoLinha = tipoLinha;
	}

	public String getMotivoRecontato() {
		return motivoRecontato;
	}

	public void setMotivoRecontato(String motivoRecontato) {
		this.motivoRecontato = motivoRecontato;
	}

	public String getSegmentacao() {
		return segmentacao;
	}

	public void setSegmentacao(String segmentacao) {
		this.segmentacao = segmentacao;
	}

	public String getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public void setResultset(Resultset resultset) {
		this.resultset = resultset;
	}

	public Resultset getResultset() {
		return this.resultset;
	}
}