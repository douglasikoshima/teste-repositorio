package admsistemas.admArvoreContato.workflow.abaEntrevista;

import java.io.Serializable;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.admsistemas.vo.AdmArvoreBaixaContainerVODocument.AdmArvoreBaixaContainerVO;
import br.com.vivo.fo.admsistemas.vo.AdmFolhaBaixaVODocument.AdmFolhaBaixaVO;

public class FormArvoreBaixa extends ActionForm implements Serializable {

	private static final long serialVersionUID = -3776133047415844939L;

	private String idBaixa;

	private AdmFolhaBaixaVO[] admFolhaBaixaVO;

	private AdmArvoreBaixaContainerVO arvoreBaixa;

	private String idContato;

	public void setIdContato(String idContato) {
		this.idContato = idContato;
	}

	public String getIdContato() {
		return this.idContato;
	}

	public void setArvoreBaixa(AdmArvoreBaixaContainerVO arvoreBaixa) {
		this.arvoreBaixa = arvoreBaixa;
	}

	public AdmArvoreBaixaContainerVO getArvoreBaixa() {
		// For data binding to be able to post data back, complex types and
		// arrays must be initialized to be non-null. This type doesn't have
		// a default constructor, so Workshop cannot initialize it for you.

		// TODO: Initialize arvoreBaixa if it is null.
		// if(this.arvoreBaixa == null)
		// {
		// this.arvoreBaixa = new AdmArvoreBaixaContainerVO(?);
		// }

		return this.arvoreBaixa;
	}

	public void setAdmFolhaBaixaVO(AdmFolhaBaixaVO[] admFolhaBaixaVO) {
		this.admFolhaBaixaVO = admFolhaBaixaVO;
	}

	public AdmFolhaBaixaVO[] getAdmFolhaBaixaVO() {
		return this.admFolhaBaixaVO;
	}

	public void setIdBaixa(String idBaixa) {
		this.idBaixa = idBaixa;
	}

	public String getIdBaixa() {
		return this.idBaixa;
	}
}