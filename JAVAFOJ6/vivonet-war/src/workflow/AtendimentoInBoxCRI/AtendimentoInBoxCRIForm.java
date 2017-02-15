package workflow.AtendimentoInBoxCRI;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.workflow.vo.AlertaVODocument.AlertaVO;
import br.com.vivo.fo.workflow.vo.RWFAtendimentosVODocument.RWFAtendimentosVO;
import br.com.vivo.fo.workflow.vo.RetornoWFCTIVODocument.RetornoWFCTIVO;
import br.com.vivo.fo.workflow.vo.WFAcaoVODocument.WFAcaoVO;
import br.com.vivo.fo.workflow.vo.WFAtdNotasVODocument.WFAtdNotasVO;
import br.com.vivo.fo.workflow.vo.WFEstadosVODocument.WFEstadosVO;
import br.com.vivo.fo.workflow.vo.WFGrupoVODocument.WFGrupoVO;
import br.com.vivo.fo.workflow.vo.WFMotivoVODocument.WFMotivoVO;

public class AtendimentoInBoxCRIForm extends ActionForm {

	private static final long serialVersionUID = -4082818042041417535L;

	// inCliqueAba define se o usuario clicou na Aba ou no botao Pesquisar
	private String inCliqueAba;

	public AtendimentoInBoxCRIForm() {
	}

	private AlertaVO[] alertasVO;

	public void setAlertasVO(AlertaVO[] alertasVO) {
		this.alertasVO = alertasVO;
	}

	public AlertaVO[] getAlertasVO() {
		if (this.alertasVO == null || this.alertasVO.length == 0) {
			this.alertasVO = new AlertaVO[1];
			this.alertasVO[0] = AlertaVO.Factory.newInstance();
		}
		return this.alertasVO;
	}

	private String acaoSel = ConstantesCRM.SVAZIO;

	public String getAcaoSel() {
		return this.acaoSel;
	}

	public void setAcaoSel(String acaoSel) {
		this.acaoSel = acaoSel;
	}

	private String motivoSel = ConstantesCRM.SVAZIO;

	public String getMotivoSel() {
		return this.motivoSel;
	}

	public void setMotivoSel(String motivoSel) {
		this.motivoSel = motivoSel;
	}

	private WFMotivoVO[] wFMotivosVO;

	public void setMotivosVO(WFMotivoVO[] wFMotivosVO) {
		this.wFMotivosVO = wFMotivosVO;
	}

	public WFMotivoVO[] getMotivosVO() {
		return this.wFMotivosVO;
	}

	private WFAcaoVO[] wFAcaoVO;

	public void setAcoesVO(WFAcaoVO[] wFAcaoVO) {
		this.wFAcaoVO = wFAcaoVO;
	}

	public WFAcaoVO[] getAcoesVO() {
		if (this.wFAcaoVO == null || this.wFAcaoVO.length == 0) {
			this.wFAcaoVO = new WFAcaoVO[1];
			this.wFAcaoVO[0] = WFAcaoVO.Factory.newInstance();
		}
		return this.wFAcaoVO;
	}

	private WFAtdNotasVO wFAtdNotasVO;

	public void setNotasVO(WFAtdNotasVO wFAtdNotasVO) {
		this.wFAtdNotasVO = wFAtdNotasVO;
	}

	public WFAtdNotasVO getNotasVO() {
		if (this.wFAtdNotasVO == null) {
			this.wFAtdNotasVO = WFAtdNotasVO.Factory.newInstance();
		}
		return this.wFAtdNotasVO;
	}

	private RWFAtendimentosVO rwfAtendimentosVO;

	public void setRwfAtendimentosVO(RWFAtendimentosVO rwfAtendimentosVO) {
		this.rwfAtendimentosVO = rwfAtendimentosVO;
	}

	public RWFAtendimentosVO getRwfAtendimentosVO() {
		if (this.rwfAtendimentosVO == null) {
			this.rwfAtendimentosVO = RWFAtendimentosVO.Factory.newInstance();
		}
		return this.rwfAtendimentosVO;
	}

	private int nrRegistros = 0;

	public void setNrRegistros(int nrRegistros) {
		this.nrRegistros = nrRegistros;
	}

	public int getNrRegistros() {
		return this.nrRegistros;
	}

	private int totalRegistros = 0;

	public void setTotalRegistros(int totalRegistros) {
		this.totalRegistros = totalRegistros;
	}

	public int getTotalRegistros() {
		return this.totalRegistros;
	}

	private int atualizacao = 0;

	public void setAtualizacao(int atualizacao) {
		this.atualizacao = atualizacao;
	}

	public int getAtualizacao() {
		return this.atualizacao;
	}

	private String valorPesquisa = ConstantesCRM.SVAZIO;

	public void setValorPesquisa(String valorPesquisa) {
		this.valorPesquisa = valorPesquisa;
	}

	public String getValorPesquisa() {
		return this.valorPesquisa;
	}

	private String tipoPesquisa = ConstantesCRM.SVAZIO;

	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}

	public String getTipoPesquisa() {
		return this.tipoPesquisa;
	}

	private String inDisponivelWF = ConstantesCRM.SVAZIO;

	public void setInDisponivelWF(String inDisponivelWF) {
		this.inDisponivelWF = inDisponivelWF;
	}

	public String getInDisponivelWF() {
		return this.inDisponivelWF;
	}

	private String documento = ConstantesCRM.SVAZIO;

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getDocumento() {
		return this.documento;
	}

	private String inAbaPesquisa = ConstantesCRM.SVAZIO;

	public void setInAbaPesquisa(String inAbaPesquisa) {
		this.inAbaPesquisa = inAbaPesquisa;
	}

	public String getInAbaPesquisa() {
		return this.inAbaPesquisa;
	}

	private String nmLoginUsuario = ConstantesCRM.SVAZIO;

	public void setNmLoginUsuario(String nmLoginUsuario) {
		this.nmLoginUsuario = nmLoginUsuario;
	}

	public String getNmLoginUsuario() {
		return this.nmLoginUsuario;
	}

	private String prazoSel = ConstantesCRM.SVAZIO;

	public void setPrazoSel(String prazoSel) {
		this.prazoSel = prazoSel;
	}

	public String getPrazoSel() {
		return this.prazoSel;
	}

	private String estadoSel = ConstantesCRM.SVAZIO;

	public void setEstadoSel(String estadoSel) {
		this.estadoSel = estadoSel;
	}

	public String getEstadoSel() {
		return this.estadoSel;
	}

	private String subEstadoSel = ConstantesCRM.SVAZIO;

	public void setSubEstadoSel(String subEstadoSel) {
		this.subEstadoSel = subEstadoSel;
	}

	public String getSubEstadoSel() {
		return this.subEstadoSel;
	}

	private String idContato = ConstantesCRM.SVAZIO;

	public void setIdContato(String idContato) {
		this.idContato = idContato;
	}

	public String getIdContato() {
		return this.idContato;
	}

	private String textoContato = ConstantesCRM.SVAZIO;

	public void setTextoContato(String textoContato) {
		this.textoContato = textoContato;
	}

	public String getTextoContato() {
		return this.textoContato;
	}

	private String dtFechamentoFim = ConstantesCRM.SVAZIO;

	public void setDtFechamentoFim(String dtFechamentoFim) {
		this.dtFechamentoFim = dtFechamentoFim;
	}

	public String getDtFechamentoFim() {
		return this.dtFechamentoFim;
	}

	private String dtFechamentoInicio = ConstantesCRM.SVAZIO;

	public void setDtFechamentoInicio(String dtFechamentoInicio) {
		this.dtFechamentoInicio = dtFechamentoInicio;
	}

	public String getDtFechamentoInicio() {
		return this.dtFechamentoInicio;
	}

	private String dtAberturaFim = ConstantesCRM.SVAZIO;

	public void setDtAberturaFim(String dtAberturaFim) {
		this.dtAberturaFim = dtAberturaFim;
	}

	public String getDtAberturaFim() {
		return this.dtAberturaFim;
	}

	private String dataFim = ConstantesCRM.SVAZIO;

	public void setDataFim(String dataFim) {
		this.dataFim = dataFim;
	}

	public String getDataFim() {
		return this.dataFim;
	}

	private String dataIni = ConstantesCRM.SVAZIO;

	public void setDataIni(String dataIni) {
		this.dataIni = dataIni;
	}

	public String getDataIni() {
		return this.dataIni;
	}

	private String dtAberturaInicio = ConstantesCRM.SVAZIO;

	public void setDtAberturaInicio(String dtAberturaInicio) {
		this.dtAberturaInicio = dtAberturaInicio;
	}

	public String getDtAberturaInicio() {
		return this.dtAberturaInicio;
	}

	private String nrLinha = ConstantesCRM.SVAZIO;

	public void setNrLinha(String nrLinha) {
		this.nrLinha = nrLinha;
	}

	public String getNrLinha() {
		return this.nrLinha;
	}

	private String idAtendimento = ConstantesCRM.SVAZIO;

	public void setIdAtendimento(String idAtendimento) {
		this.idAtendimento = idAtendimento;
	}

	public String getIdAtendimento() {
		return this.idAtendimento;
	}

	private String nrProtocolo = ConstantesCRM.SVAZIO;

	public void setNrProtocolo(String nrProtocolo) {
		this.nrProtocolo = nrProtocolo;
	}

	public String getNrProtocolo() {
		return this.nrProtocolo;
	}

	private int optCliTra = -1;

	public int getOptCliTra() {
		return this.optCliTra;
	}

	public void setOptCliTra(int optCliTra) {
		this.optCliTra = optCliTra;
	}

	private int optCliCon = -1;

	public int getOptCliCon() {
		return this.optCliCon;
	}

	public void setOptCliCon(int optCliCon) {
		this.optCliCon = optCliCon;
	}

	private String optGrpSel = ConstantesCRM.SZERO;

	public String getOptGrpSel() {
		return this.optGrpSel;
	}

	public void setOptGrpSel(String optGrpSel) {
		this.optGrpSel = optGrpSel;
	}

	private String optDocSel = "CPF";

	public String getOptDocSel() {
		return this.optDocSel;
	}

	public void setOptDocSel(String optDocSel) {
		this.optDocSel = optDocSel;
	}

	private String retorno = ConstantesCRM.SVAZIO;

	public void setRetorno(String retorno) {
		this.retorno = retorno;
	}

	public String getRetorno() {
		return this.retorno;
	}

	private WFGrupoVO[] gruposVO;

	public void setGruposVO(WFGrupoVO[] gruposVO) {
		this.gruposVO = gruposVO;
	}

	public WFGrupoVO[] getGruposVO() {
		return this.gruposVO;
	}

	private WFEstadosVO estadosVO;

	public void setEstadosVO(WFEstadosVO estadosVO) {
		this.estadosVO = estadosVO;
	}

	public WFEstadosVO getEstadosVO() {
		return this.estadosVO;
	}

	private RetornoWFCTIVO[] retornoWFCTIVO;

	public void setRetornoWFCTIVO(RetornoWFCTIVO[] retorno) {
		this.retornoWFCTIVO = retorno;
	}

	public RetornoWFCTIVO[] getRetornoWFCTIVO() {
		return this.retornoWFCTIVO;
	}

	public void setInCliqueAba(String inCliqueAba) {
		this.inCliqueAba = inCliqueAba;
	}

	public String getInCliqueAba() {
		if (this.inCliqueAba == null) {
			this.inCliqueAba = ConstantesCRM.SVAZIO;
		}
		return this.inCliqueAba;
	}
}