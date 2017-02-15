package admsistemas.admCalendario.gerenciarCalendario;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.admsistemas.vo.AdmCalendarioContainerVODocument.AdmCalendarioContainerVO;
import br.com.vivo.fo.admsistemas.vo.AdmFeriadoVODocument.AdmFeriadoVO;
import br.com.vivo.fo.constantes.ConstantesCRM;

public class FormGerenciamento extends ActionForm {

	private static final long serialVersionUID = -4620906422795796882L;

	private String msgError = ConstantesCRM.SVAZIO;
	private String contadorPonte;
	private String indicativoExibeCopia;
	private String contador;
	private String indicativoExibirBotoesPonte;
	private String indicativoExibirPontes;
	private String indicativoExibirMoveis;
	private AdmFeriadoVO[] listaAdmFeriadoVO;
	private AdmCalendarioContainerVO admCalendarioContainerVO;
	private String msgStatus;
	private String anoCopia;
	private String anoBase;

	public FormGerenciamento() {
		listaAdmFeriadoVO = new AdmFeriadoVO[0];
	}

	public void setAnoBase(String anoBase) {
		this.anoBase = anoBase;
	}

	public String getAnoBase() {
		return this.anoBase;
	}

	public void setAnoCopia(String anoCopia) {
		this.anoCopia = anoCopia;
	}

	public String getAnoCopia() {
		return this.anoCopia;
	}

	public void setMsgStatus(String msgStatus) {
		this.msgStatus = msgStatus;
	}

	public String getMsgStatus() {
		return this.msgStatus;
	}

	public void setAdmCalendarioContainerVO(AdmCalendarioContainerVO admCalendarioContainerVO) {
		this.admCalendarioContainerVO = admCalendarioContainerVO;
	}

	public AdmCalendarioContainerVO getAdmCalendarioContainerVO() {
		return this.admCalendarioContainerVO;
	}

	public void setListaAdmFeriadoVO(AdmFeriadoVO[] listaAdmFeriadoVO) {
		this.listaAdmFeriadoVO = listaAdmFeriadoVO;
	}

	public AdmFeriadoVO[] getListaAdmFeriadoVO() {
		return this.listaAdmFeriadoVO;
	}

	public void setIndicativoExibirMoveis(String indicativoExibirMoveis) {
		this.indicativoExibirMoveis = indicativoExibirMoveis;
	}

	public String getIndicativoExibirMoveis() {
		return this.indicativoExibirMoveis;
	}

	public void setIndicativoExibirPontes(String indicativoExibirPontes) {
		this.indicativoExibirPontes = indicativoExibirPontes;
	}

	public String getIndicativoExibirPontes() {
		return this.indicativoExibirPontes;
	}

	public void setIndicativoExibirBotoesPonte(String indicativoExibirBotoesPonte) {
		this.indicativoExibirBotoesPonte = indicativoExibirBotoesPonte;
	}

	public String getIndicativoExibirBotoesPonte() {
		return this.indicativoExibirBotoesPonte;
	}

	public void setContador(String contador) {
		this.contador = contador;
	}

	public String getContador() {
		return this.contador;
	}

	public void setIndicativoExibeCopia(String indicativoExibeCopia) {
		this.indicativoExibeCopia = indicativoExibeCopia;
	}

	public String getIndicativoExibeCopia() {
		return this.indicativoExibeCopia;
	}

	public void setContadorPonte(String contadorPonte) {
		this.contadorPonte = contadorPonte;
	}

	public String getContadorPonte() {
		return this.contadorPonte;
	}

	public void setMsgError(String msgError) {
		this.msgError = msgError;
	}

	public String getMsgError() {
		return this.msgError;
	}
}