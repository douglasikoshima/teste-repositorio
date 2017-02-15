package admsistemas.AdmFormularios;

import java.io.Serializable;

import br.com.vivo.fo.constantes.ConstantesCRM;

public class Campo implements Serializable {

	private static final long serialVersionUID = 7416639440828278251L;

	private String nmCampo = ConstantesCRM.SVAZIO;
	private String tpCampo = ConstantesCRM.SVAZIO;
	private String idCampo = ConstantesCRM.SVAZIO;
	private Integer orderm     = new Integer(0);
	private String apCampo = ConstantesCRM.SVAZIO;
	private boolean requerido = false;
	private String layoutApresentacao = ConstantesCRM.SVAZIO;
	private String lbCampo = ConstantesCRM.SVAZIO;
	private String mascaraApresentacao = ConstantesCRM.SVAZIO;

	private Dominio[] dominio = new Dominio[0];

	public Campo() {
	}

	public Campo(String idCampo) {
		this.idCampo = idCampo;
	}

	public Campo(String idCampo, String nmCampo, String apCampo) {
		this.nmCampo = nmCampo;
		this.idCampo = idCampo;
		this.apCampo = apCampo;
	}

	public Campo(String idCampo, String nmCampo, String apCampo, Integer ordem) {
		this.nmCampo = nmCampo;
		this.idCampo = idCampo;
		this.apCampo = apCampo;
		this.orderm = ordem;
	}

	public void setNmCampo(String nmCampo){
		this.nmCampo = nmCampo;
	}

	public String getNmCampo(){
		return this.nmCampo;
	}

	public void setTpCampo(String tpCampo){
		this.tpCampo = tpCampo;
	}

	public String getTpCampo(){
		return this.tpCampo;
	}

	public void setDominio(Dominio[] dominio){
		this.dominio = dominio;
	}

	public Dominio[] getDominio(){
		return this.dominio;
	}

	public String getIdCampo() {
		return this.idCampo;
	}

	public void setIdCampo(String idCampo) {
		this.idCampo = idCampo;
	}

	public Integer getOrderm() {
		return this.orderm;
	}

	public void setOrderm(Integer orderm) {
		this.orderm = orderm;
	}

	public String getApCampo() {
		return this.apCampo;
	}

	public void setApCampo(String apCampo) {
		this.apCampo = apCampo;
	}

	public void setLayoutApresentacao(String layoutApresentacao) {
		this.layoutApresentacao = layoutApresentacao;
	}

	public String getLayoutApresentacao() {
		return this.layoutApresentacao;
	}

	public void setRequerido(String requerido) {
		if(ConstantesCRM.SONE.equals(requerido)) {
			this.requerido = true;
		} else {
			this.requerido = false;
		}
	}

	public boolean isRequerido() {
		return this.requerido;
	}

	public void setLbCampo(String lbCampo) {
		this.lbCampo = lbCampo;
	}

	public String getLbCampo() {
		return this.lbCampo;
	}

	public void setMascaraApresentacao(String mascaraApresentacao) {
		this.mascaraApresentacao = mascaraApresentacao;
	}

	public String getMascaraApresentacao() {
		return this.mascaraApresentacao;
	}

	public String getFuncaoValidacao() {

		if(mascaraApresentacao.equalsIgnoreCase("cpf")) {
			return "onKeyDown=\"formatarCPF(this,event)\" onKeyPress=\"return numeros(event)\" onBlur=\"CPFFormatado(this)\" ";
		} else if(mascaraApresentacao.equalsIgnoreCase("data")) {
			return "OnKeyPress=\"mascaraData(this);\" ";
		} else if(mascaraApresentacao.equalsIgnoreCase("telefone")) {
			return "OnKeyPress=\"Mascara_Telefone(this);\" ";
		} else if(mascaraApresentacao.equalsIgnoreCase("datahora")) {
			return "OnKeyPress=\"Mascara_DataHora(event, this);\" ";
		} else if(mascaraApresentacao.equalsIgnoreCase("moeda")) {
			return "OnKeyPress=\"return(Mascara_Moeda(this, event));\" ";
		} else if(mascaraApresentacao.equalsIgnoreCase("numero")) {
			return "OnKeyPress=\"return numeros(event);\" " ;
		} else if(mascaraApresentacao.equalsIgnoreCase("inteiro")) {
			return "OnKeyPress=\"return inteiro(event);\" " ;
		} else if(mascaraApresentacao.equalsIgnoreCase("hora")) {
			return "OnKeyPress=\"valida_horas(this);\" ";
		} else {
			return ConstantesCRM.SVAZIO;
		}
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();

		sb.append("idCampo=[").append(idCampo).append("] ");
		sb.append("nmCampo=[").append(nmCampo).append("] ");
		sb.append("tpCampo=[").append(tpCampo).append("] ");
		sb.append("orderm=[").append(orderm).append("] ");
		sb.append("apCampo=[").append(apCampo).append("] ");
		sb.append("mascaraApresentacao=[").append(mascaraApresentacao).append("] ");
		sb.append("obrigatorio=[").append(requerido).append("] ");

		return sb.toString();
	}
}