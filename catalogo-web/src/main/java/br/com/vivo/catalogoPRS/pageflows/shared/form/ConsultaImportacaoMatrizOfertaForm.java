package br.com.vivo.catalogoPRS.pageflows.shared.form;

import java.util.List;

import org.apache.struts.validator.ValidatorActionForm;

import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoBuscarListaDetalhesImportacaoListaDetalhesImportacaoDetalhesImportacao;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoImportacaoResultadoImportacao;

public class ConsultaImportacaoMatrizOfertaForm extends ValidatorActionForm  implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long paginaSolicitada;
	private Long idStatusArquivoImportacao;
	private String dscStatusImportacao;
	private String nmArquivo;
	private String userImportacao;
	private String periodoInicio;
	private String periodoFim;
	
	private Long idMatrizOfertaAquivo;

	private List<ResultadoImportacaoResultadoImportacao> resultadoImportacaoList;
	private List<ResultadoBuscarListaDetalhesImportacaoListaDetalhesImportacaoDetalhesImportacao> detalhesImportacao;

	public Long getPaginaSolicitada() {
		return paginaSolicitada;
	}
	public void setPaginaSolicitada(Long paginaSolicitada) {
		this.paginaSolicitada = paginaSolicitada;
	}
	public String getNmArquivo() {
		return nmArquivo;
	}
	public void setNmArquivo(String nmArquivo) {
		this.nmArquivo = nmArquivo;
	}
	public String getPeriodoFim() {
		return periodoFim;
	}
	public void setPeriodoFim(String periodoFim) {
		this.periodoFim = periodoFim;
	}
	public void setPeriodoInicio(String periodoInicio) {
		this.periodoInicio = periodoInicio;
	}
	public String getDscStatusImportacao() {
		return dscStatusImportacao;
	}
	public void setDscStatusImportacao(String dscStatusImportacao) {
		this.dscStatusImportacao = dscStatusImportacao;
	}
	public Long getIdStatusArquivoImportacao() {
		return idStatusArquivoImportacao;
	}
	public void setIdStatusArquivoImportacao(Long idStatusArquivoImportacao) {
		this.idStatusArquivoImportacao = idStatusArquivoImportacao;
	}
	public String getPeriodoInicio() {
		return periodoInicio;
	}
	public String getUserImportacao() {
		return userImportacao;
	}
	public void setUserImportacao(String userImportacao) {
		this.userImportacao = userImportacao;
	}	
	public Long getIdMatrizOfertaAquivo() {
		return idMatrizOfertaAquivo;
	}
	public void setIdMatrizOfertaAquivo(Long idMatrizOfertaAquivo) {
		this.idMatrizOfertaAquivo = idMatrizOfertaAquivo;
	}		
	public List<ResultadoImportacaoResultadoImportacao> getResultadoImportacaoList() {
		return resultadoImportacaoList;
	}
	public void setResultadoImportacaoList(List<ResultadoImportacaoResultadoImportacao> resultadoImportacaoList) {
		this.resultadoImportacaoList = resultadoImportacaoList;
	}
	public List<ResultadoBuscarListaDetalhesImportacaoListaDetalhesImportacaoDetalhesImportacao> getDetalhesImportacao() {
		return detalhesImportacao;
	}
	public void setDetalhesImportacao(
			List<ResultadoBuscarListaDetalhesImportacaoListaDetalhesImportacaoDetalhesImportacao> detalhesImportacao) {
		this.detalhesImportacao = detalhesImportacao;
	}
}
