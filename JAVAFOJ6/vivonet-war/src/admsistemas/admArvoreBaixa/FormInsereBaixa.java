package admsistemas.admArvoreBaixa;

import java.io.Serializable;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.admsistemas.vo.AdmComunicacaoBaixaVODocument.AdmComunicacaoBaixaVO;
import br.com.vivo.fo.admsistemas.vo.AdmIndicadorAnatelVODocument.AdmIndicadorAnatelVO;
import br.com.vivo.fo.admsistemas.vo.AdmMensagemAvisoVODocument.AdmMensagemAvisoVO;
import br.com.vivo.fo.admsistemas.vo.AdmNomeBaixaVODocument.AdmNomeBaixaVO;

public class FormInsereBaixa extends ActionForm implements Serializable {

	private static final long serialVersionUID = 3235907156052266047L;

	private String descPesquisa;
	private String idMensagemAtual;
	private String inDisponibilidade;
	private String inFolha;
	private AdmMensagemAvisoVO[] mensagens;
	private String dsPath;
	private String idNomeBaixa;
	private String nmBaixaEscolhido;
	private String nmBaixa;
	private AdmComunicacaoBaixaVO[] admComunicacaoBaixaVO;
	private String[] arrayAdmIndicadorAnatelExistente;
	private String[] arrayAdmIndicadorAnatelAssociado;
	private AdmIndicadorAnatelVO[] admIndicadorAnatelAssociadoVO;
	private AdmIndicadorAnatelVO[] admIndicadorAnatelExistenteVO;
	private AdmNomeBaixaVO[] admNomeBaixaVO;
	private String idBaixa;

	public void setIdBaixa(String idBaixa) {
		this.idBaixa = idBaixa;
	}

	public String getIdBaixa() {
		return this.idBaixa;
	}

	public void setAdmNomeBaixaVO(AdmNomeBaixaVO[] admNomeBaixaVO) {
		this.admNomeBaixaVO = admNomeBaixaVO;
	}

	public AdmNomeBaixaVO[] getAdmNomeBaixaVO() {
		return this.admNomeBaixaVO;
	}

	public void setAdmIndicadorAnatelExistenteVO(AdmIndicadorAnatelVO[] admIndicadorAnatelExistenteVO) {
		this.admIndicadorAnatelExistenteVO = admIndicadorAnatelExistenteVO;
	}

	public AdmIndicadorAnatelVO[] getAdmIndicadorAnatelExistenteVO() {
		return this.admIndicadorAnatelExistenteVO;
	}

	public void setAdmIndicadorAnatelAssociadoVO(AdmIndicadorAnatelVO[] admIndicadorAnatelAssociadoVO) {
		this.admIndicadorAnatelAssociadoVO = admIndicadorAnatelAssociadoVO;
	}

	public AdmIndicadorAnatelVO[] getAdmIndicadorAnatelAssociadoVO() {
		return this.admIndicadorAnatelAssociadoVO;
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

	public void setAdmComunicacaoBaixaVO(AdmComunicacaoBaixaVO[] admComunicacaoBaixaVO) {
		this.admComunicacaoBaixaVO = admComunicacaoBaixaVO;
	}

	public AdmComunicacaoBaixaVO[] getAdmComunicacaoBaixaVO() {
		return this.admComunicacaoBaixaVO;
	}

	public void setNmBaixa(String nmBaixa) {
		this.nmBaixa = nmBaixa;
	}

	public String getNmBaixa() {
		return this.nmBaixa;
	}

	public void setNmBaixaEscolhido(String nmBaixaEscolhido) {
		this.nmBaixaEscolhido = nmBaixaEscolhido;
	}

	public String getNmBaixaEscolhido() {
		return this.nmBaixaEscolhido;
	}

	public void setIdNomeBaixa(String idNomeBaixa) {
		this.idNomeBaixa = idNomeBaixa;
	}

	public String getIdNomeBaixa() {
		return this.idNomeBaixa;
	}

	public void setDsPath(String dsPath) {
		this.dsPath = dsPath;
	}

	public String getDsPath() {
		return this.dsPath;
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

	public void setMensagens(AdmMensagemAvisoVO[] mensagens) {
		this.mensagens = mensagens;
	}

	public AdmMensagemAvisoVO[] getMensagens() {
		return this.mensagens;
	}

	public void setIdMensagemAtual(String idMensagemAtual) {
		this.idMensagemAtual = idMensagemAtual;
	}

	public String getIdMensagemAtual() {
		return this.idMensagemAtual;
	}

	public void setDescPesquisa(String descPesquisa) {
		this.descPesquisa = descPesquisa;
	}

	public String getDescPesquisa() {
		return this.descPesquisa;
	}
}