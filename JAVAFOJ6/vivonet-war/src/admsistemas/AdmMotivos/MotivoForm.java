package admsistemas.AdmMotivos;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.workflow.vo.WFManterMotivosVODocument.WFManterMotivosVO;

public class MotivoForm extends ActionForm {

	private static final long serialVersionUID = -6753194611718383887L;

	private String[] acoesAssocDS;
	private String erroMsg;
	private int[] acoesAssoc;
	private int[] acoesDisp;
	private WFManterMotivosVO wFManterMotivosVO;
	private String dsMotivo;
	private int idMotivo;

	public void setIdMotivo(int idMotivo) {
		this.idMotivo = idMotivo;
	}

	public int getIdMotivo() {
		return this.idMotivo;
	}

	public void setDsMotivo(String dsMotivo) {
		this.dsMotivo = dsMotivo;
	}

	public String getDsMotivo() {
		return this.dsMotivo;
	}

	public void setWFManterMotivosVO(WFManterMotivosVO wFManterMotivosVO) {
		this.wFManterMotivosVO = wFManterMotivosVO;
	}

	public WFManterMotivosVO getWFManterMotivosVO() {
		return this.wFManterMotivosVO;
	}

	public void setAcoesDisp(int[] acoesDisp) {
		this.acoesDisp = acoesDisp;
	}

	public int[] getAcoesDisp() {
		if (this.acoesDisp == null) {
			return new int[0];
		} else {
			return this.acoesDisp;
		}
	}

	public void setAcoesAssoc(int[] acoesAssoc) {
		this.acoesAssoc = acoesAssoc;
	}

	public int[] getAcoesAssoc() {
		if (this.acoesAssoc == null) {
			return new int[0];
		} else {
			return this.acoesAssoc;
		}
	}

	public void setErroMsg(String erroMsg) {
		this.erroMsg = erroMsg;
	}

	public String getErroMsg() {
		return this.erroMsg;
	}

	public void setAcoesAssocDS(String[] acoesAssocDS) {
		this.acoesAssocDS = acoesAssocDS;
	}

	public String[] getAcoesAssocDS() {
		if (this.acoesAssocDS == null || this.acoesAssocDS.length == 0) {
			this.acoesAssocDS = new String[1];
		}
		return this.acoesAssocDS;
	}
}