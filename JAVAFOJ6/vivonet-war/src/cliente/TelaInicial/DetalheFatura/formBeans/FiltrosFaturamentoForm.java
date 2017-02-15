package cliente.TelaInicial.DetalheFatura.formBeans;

import java.util.Date;

import br.com.vivo.fo.constantes.ConstantesCRM;

public class FiltrosFaturamentoForm implements java.io.Serializable {

	public FiltrosFaturamentoForm() {}

	private String dtVencimento;
	private String dtInicio;
	private String dtFim;
	private String[] listaNrTelefoneOrigem;
	private String nrTelefoneOrigem;
	private String nrTelefoneDestino;
	private String idTipoArea;
	private String cdAreaRegistro;
	private String idDetalheChamada;
	private String cdDetalheChamada;
	private String idTipoDestino;
	private String cdTipoDestino;
	private String cdTipoServico;
	private String idTipoLigacao;
	private String cdTipoChamada;
	private String cdFormatoExportacao;
	private String nrLinhaPesquisa;
	private Date dtInicioPadrao;
	private Date dtFimPadrao;

	public String getDtVencimento() {
		if (this.dtVencimento == null) {
			this.dtVencimento = ConstantesCRM.SVAZIO;
		}
		return this.dtVencimento;
	}

	public void setDtVencimento(String arg1) {
		this.dtVencimento = arg1;
	}

	public String getDtInicio() {
		if (this.dtInicio == null) {
			this.dtInicio = ConstantesCRM.SVAZIO;
		}
		return this.dtInicio;
	}

	public void setDtInicio(String arg1) {
		this.dtInicio = arg1;
	}

	public String getDtFim() {
		if (this.dtFim == null) {
			this.dtFim = ConstantesCRM.SVAZIO;
		}
		return this.dtFim;
	}

	public void setDtFim(String arg1) {
		this.dtFim = arg1;
	}

	public String[] getListaNrTelefoneOrigem() {
		if (this.listaNrTelefoneOrigem == null) {
			this.listaNrTelefoneOrigem = new String[0];
		}
		return this.listaNrTelefoneOrigem;
	}

	public void setListaNrTelefoneOrigem(String[] arg1) {
		this.listaNrTelefoneOrigem = arg1;
	}

	public String getNrTelefoneOrigem() {
		if (this.nrTelefoneOrigem == null) {
			this.nrTelefoneOrigem = ConstantesCRM.SVAZIO;
		}
		return this.nrTelefoneOrigem;
	}

	public void setNrTelefoneOrigem(String arg1) {
		this.nrTelefoneOrigem = arg1;
	}

	public String getNrTelefoneDestino() {
		if (this.nrTelefoneDestino == null) {
			this.nrTelefoneDestino = ConstantesCRM.SVAZIO;
		}
		return this.nrTelefoneDestino;
	}

	public void setNrTelefoneDestino(String arg1) {
		this.nrTelefoneDestino = arg1;
	}

	public String getIdTipoArea() {
		if (this.idTipoArea == null) {
			this.idTipoArea = ConstantesCRM.SVAZIO;
		}
		return this.idTipoArea;
	}

	public void setIdTipoArea(String arg1) {
		this.idTipoArea = arg1;
	}

	public String getCdAreaRegistro() {
		if (this.cdAreaRegistro == null) {
			this.cdAreaRegistro = ConstantesCRM.SVAZIO;
		}
		return this.cdAreaRegistro;
	}

	public void setCdAreaRegistro(String arg) {
		this.cdAreaRegistro = arg;
	}

	public String getIdDetalheChamada() {
		if (this.idDetalheChamada == null) {
			this.idDetalheChamada = ConstantesCRM.SVAZIO;
		}
		return this.idDetalheChamada;
	}

	public void setIdDetalheChamada(String arg1) {
		this.idDetalheChamada = arg1;
	}

	public String getCdDetalheChamada() {
		if (this.cdDetalheChamada == null) {
			this.cdDetalheChamada = ConstantesCRM.SVAZIO;
		}
		return this.cdDetalheChamada;
	}

	public void setCdDetalheChamada(String arg) {
		this.cdDetalheChamada = arg;
	}

	public String getIdTipoDestino() {
		if (this.idTipoDestino == null) {
			this.idTipoDestino = ConstantesCRM.SVAZIO;
		}
		return this.idTipoDestino;
	}

	public void setIdTipoDestino(String arg1) {
		this.idTipoDestino = arg1;
	}

	public String getCdTipoDestino() {
		if (this.cdTipoDestino == null) {
			this.cdTipoDestino = ConstantesCRM.SVAZIO;
		}
		return this.cdTipoDestino;
	}

	public void setCdTipoDestino(String arg) {
		this.cdTipoDestino = arg;
	}

	public String getCdTipoServico() {
		if (this.cdTipoServico == null) {
			this.cdTipoServico = ConstantesCRM.SVAZIO;
		}
		return this.cdTipoServico;
	}

	public void setCdTipoServico(String arg1) {
		this.cdTipoServico = arg1;
	}

	public String getIdTipoLigacao() {
		if (this.idTipoLigacao == null) {
			this.idTipoLigacao = ConstantesCRM.SVAZIO;
		}
		return this.idTipoLigacao;
	}

	public void setIdTipoLigacao(String arg1) {
		this.idTipoLigacao = arg1;
	}

	public String getCdTipoChamada() {
		if (this.cdTipoChamada == null) {
			this.cdTipoChamada = ConstantesCRM.SVAZIO;
		}
		return this.cdTipoChamada;
	}

	public void setCdTipoChamada(String arg1) {
		this.cdTipoChamada = arg1;
	}

	public String getCdFormatoExportacao() {
		if (this.cdFormatoExportacao == null) {
			this.cdFormatoExportacao = ConstantesCRM.SVAZIO;
		}
		return this.cdFormatoExportacao;
	}

	public void setCdFormatoExportacao(String arg1) {
		this.cdFormatoExportacao = arg1;
	}

	public String getNrLinhaPesquisa() {
		if (this.nrLinhaPesquisa == null) {
			this.nrLinhaPesquisa = ConstantesCRM.SVAZIO;
		}
		return this.nrLinhaPesquisa;
	}

	public void setNrLinhaPesquisa(String arg) {
		this.nrLinhaPesquisa = arg;
	}

	public Date getDtInicioPadrao() {
		return this.dtInicioPadrao;
	}

	public void setDtInicioPadrao(Date arg) {
		this.dtInicioPadrao = arg;
	}

	public Date getDtFimPadrao() {
		return this.dtFimPadrao;
	}

	public void setDtFimPadrao(Date arg) {
		this.dtFimPadrao = arg;
	}

}