package br.com.vivo.catalogoPRS.pageflows.shared.form;

public class MatrizOfertaArquivoForm implements java.io.Serializable {
	

	private static final long serialVersionUID = 1L;
	
	private Long paginaSolicitada;
	private Long idStatusArquivoImportacao;
	private String dscStatusImportacao;
	private String nmArquivo;
	private String userImportacao;
	private String periodoInicio;
	private String periodoFim;
	
	private Long idMatrizOfertaAquivo;

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

}
