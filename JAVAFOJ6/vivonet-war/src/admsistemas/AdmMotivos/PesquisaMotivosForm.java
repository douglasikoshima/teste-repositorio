package admsistemas.AdmMotivos;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.workflow.vo.WFMotivoVODocument.WFMotivoVO;

public class PesquisaMotivosForm extends ActionForm {

	private static final long serialVersionUID = 3900732425276212496L;
	private WFMotivoVO[] pesquisaMotivos;
	private String dsMotivo;

	public void setDsMotivo(String dsMotivo) {
		this.dsMotivo = dsMotivo;
	}

	public String getDsMotivo() {
		return this.dsMotivo;
	}

	public void setPesquisaMotivos(WFMotivoVO[] pesquisaMotivos) {
		this.pesquisaMotivos = pesquisaMotivos;
	}

	public WFMotivoVO[] getPesquisaMotivos() {
		if (this.pesquisaMotivos == null) {
			return new WFMotivoVO[0];
		} else {
			return this.pesquisaMotivos;
		}
	}
}