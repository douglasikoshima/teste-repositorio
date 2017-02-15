package admsistemas.admArvoreContato.workflow.abaEntrevista;

import java.io.Serializable;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.admsistemas.vo.AdmArvoreBaixaContainerVODocument.AdmArvoreBaixaContainerVO;
import br.com.vivo.fo.admsistemas.vo.AdmFolhaBaixaVODocument.AdmFolhaBaixaVO;

public class FormArvoreBaixaAssociada extends ActionForm implements Serializable {

	private static final long serialVersionUID = -2575114624339165462L;

	private String fonte;

	private String idBaixa;

	private AdmFolhaBaixaVO[] admFolhaBaixaVO;

	private AdmArvoreBaixaContainerVO arvoreBaixaAssociada;

	private String idContato;

	public void setIdContato(String idContato) {
		this.idContato = idContato;
	}

	public String getIdContato() {
		return this.idContato;
	}

	public void setArvoreBaixaAssociada(AdmArvoreBaixaContainerVO arvoreBaixaAssociada) {
		this.arvoreBaixaAssociada = arvoreBaixaAssociada;
	}

	public AdmArvoreBaixaContainerVO getArvoreBaixaAssociada() {
		// For data binding to be able to post data back, complex types and
		// arrays must be initialized to be non-null. This type doesn't have
		// a default constructor, so Workshop cannot initialize it for you.

		// TODO: Initialize arvoreBaixaAssociada if it is null.
		// if(this.arvoreBaixaAssociada == null)
		// {
		// this.arvoreBaixaAssociada = new AdmArvoreBaixaContainerVO(?);
		// }

		return this.arvoreBaixaAssociada;
	}

	public void setAdmFolhaBaixaVO(AdmFolhaBaixaVO[] admFolhaBaixaVO) {
		this.admFolhaBaixaVO = admFolhaBaixaVO;
	}

	public AdmFolhaBaixaVO[] getAdmFolhaBaixaVO() {
		// For data binding to be able to post data back, complex types and
		// arrays must be initialized to be non-null. This type doesn't have
		// a default constructor, so Workshop cannot initialize it for you.

		// TODO: Initialize admFolhaBaixaVO if it is null or length == 0.
		// if(this.admFolhaBaixaVO == null || this.admFolhaBaixaVO.length == 0)
		// {
		// this.admFolhaBaixaVO = new AdmFolhaBaixaVO[1];
		// this.admFolhaBaixaVO[0] = new AdmFolhaBaixaVO(?);
		// }

		return this.admFolhaBaixaVO;
	}

	public void setIdBaixa(String idBaixa) {
		this.idBaixa = idBaixa;
	}

	public String getIdBaixa() {
		return this.idBaixa;
	}

	public void setFonte(String fonte) {
		this.fonte = fonte;
	}

	public String getFonte() {
		return this.fonte;
	}
}