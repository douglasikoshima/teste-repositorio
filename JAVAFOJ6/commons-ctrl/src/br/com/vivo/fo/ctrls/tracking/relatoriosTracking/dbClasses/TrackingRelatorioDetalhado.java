package br.com.vivo.fo.ctrls.tracking.relatoriosTracking.dbClasses;

/**
 * Tabela TRACKING.AUXRELATORIO2
 */
public class TrackingRelatorioDetalhado {

	public long nrRegistro;
	public String dtPedido;
	public long nrPedido;
	public long nrOrdemVenda;
	public String dtOrdemVenda;
	public String dsStatus;
	public String nmResponsavel;
	public String motivoBloqueio;
	public String dtMotivoBloqueio;
	public String sgUF;
	public String dsCanalVenda;
	public int idCanalVenda;
	public int qtdAparelho;
	public long nrFornecimento;
	public String dtFornecimento;
	public long nrPicking;
	public String dtPicking;
	public long nrNotaFiscal;
	public String dtNotaFiscal;
	public int tempoStatus;
	public int tempoDecorrido;
	public String nmResponsavelOV;
	public String dsSegmento;
	public String dtRelatorio;
	public int inTransporte;
	public String dtTransporte;

	public long getNrRegistro() {
		return nrRegistro;
	}

	public void setNrRegistro(long nrRegistro) {
		this.nrRegistro = nrRegistro;
	}

	public String getDsSegmento() {
		return dsSegmento;
	}

	public void setDsSegmento(String dsSegmento) {
		this.dsSegmento = dsSegmento;
	}

	public String getDsStatus() {
		if (dsStatus == null)
			dsStatus = "";
		return dsStatus;
	}

	public void setDsStatus(String dsStatus) {
		this.dsStatus = dsStatus;
	}

	public String getDtFornecimento() {
		if (dtFornecimento == null)
			dtFornecimento = "";
		return dtFornecimento;
	}

	public void setDtFornecimento(String dtFornecimento) {
		this.dtFornecimento = dtFornecimento;
	}

	public String getDtNotaFiscal() {
		if (dtNotaFiscal == null)
			dtNotaFiscal = "";
		return dtNotaFiscal;
	}

	public void setDtNotaFiscal(String dtNotaFiscal) {
		this.dtNotaFiscal = dtNotaFiscal;
	}

	public String getDtOrdemVenda() {
		if (dtOrdemVenda == null)
			dtOrdemVenda = "";
		return dtOrdemVenda;
	}

	public void setDtOrdemVenda(String dtOrdemVenda) {
		this.dtOrdemVenda = dtOrdemVenda;
	}

	public String getDtPedido() {
		if (dtPedido == null)
			dtPedido = "";
		return dtPedido;
	}

	public void setDtPedido(String dtPedido) {
		this.dtPedido = dtPedido;
	}

	public String getDtPicking() {
		if (dtPicking == null)
			dtPicking = "";
		return dtPicking;
	}

	public void setDtPicking(String dtPicking) {
		this.dtPicking = dtPicking;
	}

	public String getDtRelatorio() {
		if (dtRelatorio == null)
			dtRelatorio = "";
		return dtRelatorio;
	}

	public void setDtRelatorio(String dtRelatorio) {
		this.dtRelatorio = dtRelatorio;
	}

	public int getIdCanalVenda() {
		return idCanalVenda;
	}

	public void setIdCanalVenda(int idCanalVenda) {
		this.idCanalVenda = idCanalVenda;
	}

	public String getDsCanalVenda() {
		return dsCanalVenda;
	}

	public void setDsCanalVenda(String dsCanalVenda) {
		this.dsCanalVenda = dsCanalVenda;
	}

	public String getMotivoBloqueio() {
		if (motivoBloqueio == null)
			motivoBloqueio = "";
		return motivoBloqueio;
	}

	public void setMotivoBloqueio(String motivoBloqueio) {
		this.motivoBloqueio = motivoBloqueio;
	}

	public String getDtMotivoBloqueio() {
		if (dtMotivoBloqueio == null)
			dtMotivoBloqueio = "";
		return dtMotivoBloqueio;
	}

	public void setDtMotivoBloqueio(String dtMotivoBloqueio) {
		this.dtMotivoBloqueio = dtMotivoBloqueio;
	}

	public String getNmResponsavel() {
		if (nmResponsavel == null)
			nmResponsavel = "";
		return nmResponsavel;
	}

	public void setNmResponsavel(String nmResponsavel) {
		this.nmResponsavel = nmResponsavel;
	}

	public String getNmResponsavelOV() {
		if (nmResponsavelOV == null)
			nmResponsavelOV = "";
		return nmResponsavelOV;
	}

	public void setNmResponsavelOV(String nmResponsavelOV) {
		this.nmResponsavelOV = nmResponsavelOV;
	}

	public long getNrFornecimento() {
		return nrFornecimento;
	}

	public void setNrFornecimento(long nrFornecimento) {
		this.nrFornecimento = nrFornecimento;
	}

	public long getNrNotaFiscal() {
		return nrNotaFiscal;
	}

	public void setNrNotaFiscal(long nrNotaFiscal) {
		this.nrNotaFiscal = nrNotaFiscal;
	}

	public long getNrOrdemVenda() {
		return nrOrdemVenda;
	}

	public void setNrOrdemVenda(long nrOrdemVenda) {
		this.nrOrdemVenda = nrOrdemVenda;
	}

	public long getNrPedido() {
		return nrPedido;
	}

	public void setNrPedido(long nrPedido) {
		this.nrPedido = nrPedido;
	}

	public long getNrPicking() {
		return nrPicking;
	}

	public void setNrPicking(long nrPicking) {
		this.nrPicking = nrPicking;
	}

	public int getQtdAparelho() {
		return qtdAparelho;
	}

	public void setQtdAparelho(int qtdAparelho) {
		this.qtdAparelho = qtdAparelho;
	}

	public String getSgUF() {
		if (sgUF == null)
			sgUF = "";
		return sgUF;
	}

	public void setSgUF(String sgUF) {
		this.sgUF = sgUF;
	}

	public int getTempoDecorrido() {
		return tempoDecorrido;
	}

	public void setTempoDecorrido(int tempoDecorrido) {
		this.tempoDecorrido = tempoDecorrido;
	}

	public int getTempoStatus() {
		return tempoStatus;
	}

	public void setTempoStatus(int tempoStatus) {
		this.tempoStatus = tempoStatus;
	}

	public int getInTransporte() {
		return inTransporte;
	}

	public void setInTransporte(int inTransporte) {
		this.inTransporte = inTransporte;
	}

	public String getDtTransporte() {
		if (dtTransporte == null)
			dtTransporte = "";
		return dtTransporte;
	}

	public void setDtTransporte(String dtTransporte) {
		this.dtTransporte = dtTransporte;
	}

}
