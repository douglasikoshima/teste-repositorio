package admsistemas.acaoIncentivo;

import java.io.Serializable;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.ctrls.admsistemas.acaoIncentivo.db.Acao;

public class AcaoIncentivoForm extends ActionForm implements Serializable {

	private static final long serialVersionUID = 393195462462950478L;

	private String cdacaoincentivo;
	private String dsacaoincentivo;
	private String dthorainicio;
	private String dthoratermino;
	private String intipoacaoincentivo;
	private String msgantesinicioacao;
	private String msgaposinicioacao;
	private String inativo;

	private String dataInicio;
	private String horaInicio;
	private String dataTermino;
	private String horaTermino;

	public Acao[] acoes;

	private String pesquisaCodigoAcao;
	private String pesquisaDescricao;
	private String pesquisaSituacao;
	private String pesquisaAtivo;

	public String getInativo() {
		return inativo;
	}

	public void setInativo(String inativo) {
		this.inativo = inativo;
	}

	public String getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(String dataTermino) {
		this.dataTermino = dataTermino;
	}

	public String getHoraTermino() {
		return horaTermino;
	}

	public void setHoraTermino(String horaTermino) {
		this.horaTermino = horaTermino;
	}

	public String getCdacaoincentivo() {
		return cdacaoincentivo;
	}

	public void setCdacaoincentivo(String cdacaoincentivo) {
		this.cdacaoincentivo = cdacaoincentivo;
	}

	public String getDsacaoincentivo() {
		return dsacaoincentivo;
	}

	public void setDsacaoincentivo(String dsacaoincentivo) {
		this.dsacaoincentivo = dsacaoincentivo;
	}

	public String getDthorainicio() {
		return dthorainicio;
	}

	public void setDthorainicio(String dthorainicio) {
		this.dthorainicio = dthorainicio;
	}

	public String getDthoratermino() {
		return dthoratermino;
	}

	public void setDthoratermino(String dthoratermino) {
		this.dthoratermino = dthoratermino;
	}

	public String getIntipoacaoincentivo() {
		return intipoacaoincentivo;
	}

	public void setIntipoacaoincentivo(String intipoacaoincentivo) {
		this.intipoacaoincentivo = intipoacaoincentivo;
	}

	public String getMsgantesinicioacao() {
		return msgantesinicioacao;
	}

	public void setMsgantesinicioacao(String msgantesinicioacao) {
		this.msgantesinicioacao = msgantesinicioacao;
	}

	public String getMsgaposinicioacao() {
		return msgaposinicioacao;
	}

	public void setMsgaposinicioacao(String msgaposinicioacao) {
		this.msgaposinicioacao = msgaposinicioacao;
	}

	public Acao[] getAcoes() {
		if (this.acoes == null) {
			this.acoes = new Acao[0];
		}
		return this.acoes;
	}

	public void setAcoes(Acao[] acoes) {
		this.acoes = acoes;
	}

	public String getPesquisaCodigoAcao() {
		return pesquisaCodigoAcao;
	}

	public void setPesquisaCodigoAcao(String pesquisaCodigoAcao) {
		this.pesquisaCodigoAcao = pesquisaCodigoAcao;
	}

	public String getPesquisaDescricao() {
		return pesquisaDescricao;
	}

	public void setPesquisaDescricao(String pesquisaDescricao) {
		this.pesquisaDescricao = pesquisaDescricao;
	}

	public String getPesquisaSituacao() {
		return pesquisaSituacao;
	}

	public void setPesquisaSituacao(String pesquisaSituacao) {
		this.pesquisaSituacao = pesquisaSituacao;
	}

	public String getPesquisaAtivo() {
		return pesquisaAtivo;
	}

	public void setPesquisaAtivo(String pesquisaAtivo) {
		this.pesquisaAtivo = pesquisaAtivo;
	}
}
