package admsistemas.admArvoreBaixa;

import java.io.Serializable;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.admsistemas.vo.AdmArvoreBaixaContainerVODocument.AdmArvoreBaixaContainerVO;
import br.com.vivo.fo.admsistemas.vo.AdmComunicacaoBaixaVODocument.AdmComunicacaoBaixaVO;
import br.com.vivo.fo.admsistemas.vo.AdmFolhaBaixaVODocument.AdmFolhaBaixaVO;
import br.com.vivo.fo.admsistemas.vo.AdmIndicadorAnatelVODocument.AdmIndicadorAnatelVO;
import br.com.vivo.fo.admsistemas.vo.AdmMensagemAvisoVODocument.AdmMensagemAvisoVO;
import br.com.vivo.fo.admsistemas.vo.AdmMensagemBaixaVODocument.AdmMensagemBaixaVO;
import br.com.vivo.fo.constantes.ConstantesCRM;

public class FormArvoreBaixa extends ActionForm implements Serializable {

	private static final long serialVersionUID = 162504035457345208L;

	private String descPesquisa;
	private String msgError = ConstantesCRM.SVAZIO;
	private String inDisponibilidade;
	private String inFolha;
	private String[] mensagensAtuais;
	private AdmMensagemAvisoVO[] mensagens;
	private String dsPath;
	private String sgTipoComunicacao;
	private String nmBaixa;
	private String dsTipoComunicacao;
	private String dsMensagem;
	private String idTipoComunicacao;
	private String idBaixaMensagem;
	private String idNomeBaixa;
	private String idBaixaPai;
	private String[] arrayIndicadoresAnatelAssociados;
	private String[] arrayIndicadoresAnatelExistentes;
	private AdmIndicadorAnatelVO[] admIndicadoresAnatelAssociadosVO;
	private AdmIndicadorAnatelVO[] admIndicadoresAnatelExistentesVO;
	private String[] arrayAdmComunicacaoBaixaVO;
	private AdmComunicacaoBaixaVO[] admComunicacaoBaixaVO;
	private String[] arrayAdmMensagemBaixa;
	private AdmMensagemBaixaVO[] admMensagemBaixaVO;
	private AdmFolhaBaixaVO[] admFolhaBaixaVO;
	private AdmArvoreBaixaContainerVO arvoreBaixa;
	private String idBaixa;

	public FormArvoreBaixa() {
		sgTipoComunicacao = ConstantesCRM.SVAZIO;
		nmBaixa = ConstantesCRM.SVAZIO;
		dsTipoComunicacao = ConstantesCRM.SVAZIO;
		dsMensagem = ConstantesCRM.SVAZIO;
		idTipoComunicacao = ConstantesCRM.SVAZIO;
		idBaixaMensagem = ConstantesCRM.SVAZIO;
		idNomeBaixa = ConstantesCRM.SVAZIO;
		idBaixaPai = ConstantesCRM.SVAZIO;
		idBaixa = ConstantesCRM.SVAZIO;
		dsPath = ConstantesCRM.SVAZIO;
		arrayIndicadoresAnatelAssociados = new String[0];
		arrayIndicadoresAnatelExistentes = new String[0];
		arrayAdmComunicacaoBaixaVO = new String[0];
		arrayAdmMensagemBaixa = new String[0];
		admIndicadoresAnatelAssociadosVO = new AdmIndicadorAnatelVO[0];
		admIndicadoresAnatelExistentesVO = new AdmIndicadorAnatelVO[0];
		admComunicacaoBaixaVO = new AdmComunicacaoBaixaVO[0];
		admMensagemBaixaVO = new AdmMensagemBaixaVO[0];
		arvoreBaixa = AdmArvoreBaixaContainerVO.Factory.newInstance();
	}

	public void setIdBaixa(String idBaixa) {
		this.idBaixa = idBaixa;
	}

	public String getIdBaixa() {
		return this.idBaixa;
	}

	public void setArvoreBaixa(AdmArvoreBaixaContainerVO arvoreBaixa) {
		this.arvoreBaixa = arvoreBaixa;
	}

	public AdmArvoreBaixaContainerVO getArvoreBaixa() {
		return this.arvoreBaixa;
	}

	public void setAdmFolhaBaixaVO(AdmFolhaBaixaVO[] admFolhaBaixaVO) {
		this.admFolhaBaixaVO = admFolhaBaixaVO;
	}

	public AdmFolhaBaixaVO[] getAdmFolhaBaixaVO() {
		return this.admFolhaBaixaVO;
	}

	public void setAdmMensagemBaixaVO(AdmMensagemBaixaVO[] admMensagemBaixaVO) {
		this.admMensagemBaixaVO = admMensagemBaixaVO;
	}

	public AdmMensagemBaixaVO[] getAdmMensagemBaixaVO() {
		return this.admMensagemBaixaVO;
	}

	public void setArrayAdmMensagemBaixa(String[] arrayAdmMensagemBaixa) {
		this.arrayAdmMensagemBaixa = arrayAdmMensagemBaixa;
	}

	public String[] getArrayAdmMensagemBaixa() {
		if (this.arrayAdmMensagemBaixa == null || this.arrayAdmMensagemBaixa.length == 0) {
			this.arrayAdmMensagemBaixa = new String[1];
		}
		return this.arrayAdmMensagemBaixa;
	}

	public void setAdmComunicacaoBaixaVO(AdmComunicacaoBaixaVO[] admComunicacaoBaixaVO) {
		this.admComunicacaoBaixaVO = admComunicacaoBaixaVO;
	}

	public AdmComunicacaoBaixaVO[] getAdmComunicacaoBaixaVO() {
		return this.admComunicacaoBaixaVO;
	}

	public void setArrayAdmComunicacaoBaixaVO(String[] arrayAdmComunicacaoBaixaVO) {
		this.arrayAdmComunicacaoBaixaVO = arrayAdmComunicacaoBaixaVO;
	}

	public String[] getArrayAdmComunicacaoBaixaVO() {
		if (this.arrayAdmComunicacaoBaixaVO == null || this.arrayAdmComunicacaoBaixaVO.length == 0) {
			this.arrayAdmComunicacaoBaixaVO = new String[1];
		}
		return this.arrayAdmComunicacaoBaixaVO;
	}

	public void setAdmIndicadoresAnatelExistentesVO(
			AdmIndicadorAnatelVO[] admIndicadoresAnatelExistentesVO) {
		this.admIndicadoresAnatelExistentesVO = admIndicadoresAnatelExistentesVO;
	}

	public AdmIndicadorAnatelVO[] getAdmIndicadoresAnatelExistentesVO() {
		return this.admIndicadoresAnatelExistentesVO;
	}

	public void setAdmIndicadoresAnatelAssociadosVO(
			AdmIndicadorAnatelVO[] admIndicadoresAnatelAssociadosVO) {
		this.admIndicadoresAnatelAssociadosVO = admIndicadoresAnatelAssociadosVO;
	}

	public AdmIndicadorAnatelVO[] getAdmIndicadoresAnatelAssociadosVO() {
		return this.admIndicadoresAnatelAssociadosVO;
	}

	public void setArrayIndicadoresAnatelExistentes(String[] arrayIndicadoresAnatelExistentes) {
		this.arrayIndicadoresAnatelExistentes = arrayIndicadoresAnatelExistentes;
	}

	public String[] getArrayIndicadoresAnatelExistentes() {
		if (this.arrayIndicadoresAnatelExistentes == null
				|| this.arrayIndicadoresAnatelExistentes.length == 0) {
			this.arrayIndicadoresAnatelExistentes = new String[1];
		}

		return this.arrayIndicadoresAnatelExistentes;
	}

	public void setArrayIndicadoresAnatelAssociados(String[] arrayIndicadoresAnatelAssociados) {
		this.arrayIndicadoresAnatelAssociados = arrayIndicadoresAnatelAssociados;
	}

	public String[] getArrayIndicadoresAnatelAssociados() {
		if (this.arrayIndicadoresAnatelAssociados == null
				|| this.arrayIndicadoresAnatelAssociados.length == 0) {
			this.arrayIndicadoresAnatelAssociados = new String[1];
		}

		return this.arrayIndicadoresAnatelAssociados;
	}

	public void setIdBaixaPai(String idBaixaPai) {
		this.idBaixaPai = idBaixaPai;
	}

	public String getIdBaixaPai() {
		return this.idBaixaPai;
	}

	public void setIdNomeBaixa(String idNomeBaixa) {
		this.idNomeBaixa = idNomeBaixa;
	}

	public String getIdNomeBaixa() {
		return this.idNomeBaixa;
	}

	public void setIdBaixaMensagem(String idBaixaMensagem) {
		this.idBaixaMensagem = idBaixaMensagem;
	}

	public String getIdBaixaMensagem() {
		return this.idBaixaMensagem;
	}

	public void setIdTipoComunicacao(String idTipoComunicacao) {
		this.idTipoComunicacao = idTipoComunicacao;
	}

	public String getIdTipoComunicacao() {
		return this.idTipoComunicacao;
	}

	public void setDsMensagem(String dsMensagem) {
		this.dsMensagem = dsMensagem;
	}

	public String getDsMensagem() {
		return this.dsMensagem;
	}

	public void setDsTipoComunicacao(String dsTipoComunicacao) {
		this.dsTipoComunicacao = dsTipoComunicacao;
	}

	public String getDsTipoComunicacao() {
		return this.dsTipoComunicacao;
	}

	public void setNmBaixa(String nmBaixa) {
		this.nmBaixa = nmBaixa;
	}

	public String getNmBaixa() {
		return this.nmBaixa;
	}

	public void setSgTipoComunicacao(String sgTipoComunicacao) {
		this.sgTipoComunicacao = sgTipoComunicacao;
	}

	public String getSgTipoComunicacao() {
		return this.sgTipoComunicacao;
	}

	public void setDsPath(String dsPath) {
		this.dsPath = dsPath;
	}

	public String getDsPath() {
		return this.dsPath;
	}

	public void setMensagens(AdmMensagemAvisoVO[] mensagens) {
		this.mensagens = mensagens;
	}

	public AdmMensagemAvisoVO[] getMensagens() {
		return this.mensagens;
	}

	public void setMensagensAtuais(String[] mensagensAtuais) {
		this.mensagensAtuais = mensagensAtuais;
	}

	public String[] getMensagensAtuais() {
		if (this.mensagensAtuais == null || this.mensagensAtuais.length == 0) {
			this.mensagensAtuais = new String[1];
		}
		return this.mensagensAtuais;
	}

	public void setInFolha(String inFolha) {
		this.inFolha = inFolha;
	}

	public String getInFolha() {
		return this.inFolha;
	}

	public void setInDisponibilidade(String inDisponibilidade) {
		this.inDisponibilidade = inDisponibilidade;
	}

	public String getInDisponibilidade() {
		return this.inDisponibilidade;
	}

	public void setMsgError(String msgError) {
		this.msgError = msgError;
	}

	public String getMsgError() {
		return this.msgError;
	}

	public void setDescPesquisa(String descPesquisa) {
		this.descPesquisa = descPesquisa;
	}

	public String getDescPesquisa() {
		return this.descPesquisa;
	}
}