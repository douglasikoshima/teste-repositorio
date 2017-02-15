package admsistemas.admArvoreBaixa;

import java.io.Serializable;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.admsistemas.vo.AdmIndicadorAnatelVODocument.AdmIndicadorAnatelVO;
import br.com.vivo.fo.admsistemas.vo.AdmNomeBaixaVODocument.AdmNomeBaixaVO;

public class FormEditaBaixa extends ActionForm implements Serializable {

	private static final long serialVersionUID = 7311337038676580288L;

	private String nmBaixaNovo;
	private String msgError;
	private String descPesquisa;
	private String idNomeBaixaExistente;
	private AdmNomeBaixaVO[] admNomeBaixaVO;
	private String inDisponibilidade;
	private String inFolha;
	private String idMensagemAtual;
	private br.com.vivo.fo.admsistemas.vo.AdmMensagemAvisoVODocument.AdmMensagemAvisoVO[] mensagens;
	private String dsPath;
	private String[] arrayAdmIndicadorAnatelExistente;
	private String[] arrayAdmIndicadorAnatelAssociado;
	private AdmIndicadorAnatelVO[] admIndicadorAnatelExistenteVO;
	private AdmIndicadorAnatelVO[] admIndicadorAnatelAssociadoVO;
	private String idNomeBaixa;
	private String nmBaixa;
	private String idBaixaPai;
	private String idBaixa;

	public void setIdBaixa(String idBaixa) {
		this.idBaixa = idBaixa;
	}

	public String getIdBaixa() {
		return this.idBaixa;
	}

	public void setIdBaixaPai(String idBaixaPai) {
		this.idBaixaPai = idBaixaPai;
	}

	public String getIdBaixaPai() {
		return this.idBaixaPai;
	}

	public void setNmBaixa(String nmBaixa) {
		this.nmBaixa = nmBaixa;
	}

	public String getNmBaixa() {
		return this.nmBaixa;
	}

	public void setIdNomeBaixa(String idNomeBaixa) {
		this.idNomeBaixa = idNomeBaixa;
	}

	public String getIdNomeBaixa() {
		return this.idNomeBaixa;
	}

	public void setAdmIndicadorAnatelAssociadoVO(AdmIndicadorAnatelVO[] admIndicadorAnatelAssociadoVO) {
		this.admIndicadorAnatelAssociadoVO = admIndicadorAnatelAssociadoVO;
	}

	public AdmIndicadorAnatelVO[] getAdmIndicadorAnatelAssociadoVO() {
		return this.admIndicadorAnatelAssociadoVO;
	}

	public void setAdmIndicadorAnatelExistenteVO(AdmIndicadorAnatelVO[] admIndicadorAnatelExistenteVO) {
		this.admIndicadorAnatelExistenteVO = admIndicadorAnatelExistenteVO;
	}

	public AdmIndicadorAnatelVO[] getAdmIndicadorAnatelExistenteVO() {
		return this.admIndicadorAnatelExistenteVO;
	}

	public void setArrayAdmIndicadorAnatelAssociado(String[] arrayAdmIndicadorAnatelAssociado) {
		this.arrayAdmIndicadorAnatelAssociado = arrayAdmIndicadorAnatelAssociado;
	}

	public String[] getArrayAdmIndicadorAnatelAssociado() {
		if (this.arrayAdmIndicadorAnatelAssociado == null
				|| this.arrayAdmIndicadorAnatelAssociado.length == 0) {
			this.arrayAdmIndicadorAnatelAssociado = new String[1];
		}
		return this.arrayAdmIndicadorAnatelAssociado;
	}

	public void setArrayAdmIndicadorAnatelExistente(String[] arrayAdmIndicadorAnatelExistente) {
		this.arrayAdmIndicadorAnatelExistente = arrayAdmIndicadorAnatelExistente;
	}

	public String[] getArrayAdmIndicadorAnatelExistente() {
		if (this.arrayAdmIndicadorAnatelExistente == null
				|| this.arrayAdmIndicadorAnatelExistente.length == 0) {
			this.arrayAdmIndicadorAnatelExistente = new String[1];
		}
		return this.arrayAdmIndicadorAnatelExistente;
	}

	public void setDsPath(String dsPath) {
		this.dsPath = dsPath;
	}

	public String getDsPath() {
		return this.dsPath;
	}

	public void setMensagens(
			br.com.vivo.fo.admsistemas.vo.AdmMensagemAvisoVODocument.AdmMensagemAvisoVO[] mensagens) {
		this.mensagens = mensagens;
	}

	public br.com.vivo.fo.admsistemas.vo.AdmMensagemAvisoVODocument.AdmMensagemAvisoVO[] getMensagens() {
		return this.mensagens;
	}

	public void setIdMensagemAtual(String idMensagemAtual) {
		this.idMensagemAtual = idMensagemAtual;
	}

	public String getIdMensagemAtual() {
		return this.idMensagemAtual;
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

	public void setAdmNomeBaixaVO(AdmNomeBaixaVO[] admNomeBaixaVO) {
		this.admNomeBaixaVO = admNomeBaixaVO;
	}

	public AdmNomeBaixaVO[] getAdmNomeBaixaVO() {
		return this.admNomeBaixaVO;
	}

	public void setIdNomeBaixaExistente(String idNomeBaixaExistente) {
		this.idNomeBaixaExistente = idNomeBaixaExistente;
	}

	public String getIdNomeBaixaExistente() {
		return this.idNomeBaixaExistente;
	}

	public void setDescPesquisa(String descPesquisa) {
		this.descPesquisa = descPesquisa;
	}

	public String getDescPesquisa() {
		return this.descPesquisa;
	}

	public void setMsgError(String msgError) {
		this.msgError = msgError;
	}

	public String getMsgError() {
		return this.msgError;
	}

	public void setNmBaixaNovo(String nmBaixaNovo) {
		this.nmBaixaNovo = nmBaixaNovo;
	}

	public String getNmBaixaNovo() {
		return this.nmBaixaNovo;
	}
}