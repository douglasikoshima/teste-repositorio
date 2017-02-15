package fidelizacao.formBeans;

import org.apache.struts.action.ActionForm;
import br.com.vivo.fo.admsistemas.vo.AdmContatoFolhaVODocument.AdmContatoFolhaVO;
import br.com.vivo.fo.workflow.vo.ListaDadosVODocument.ListaDadosVO;

public class CancelarLinhasFormBean extends ActionForm {

    private static final long serialVersionUID = -3511923277834664262L;
    private ListaDadosVO      listaDadosVO;
    private AdmContatoFolhaVO listaPalitagens;

    public void setListaDadosVO(ListaDadosVO listaDadosVO) {
        this.listaDadosVO = listaDadosVO;
    }

    public ListaDadosVO getListaDadosVO() {
        if (this.listaDadosVO == null) {
            this.listaDadosVO = ListaDadosVO.Factory.newInstance();
        }
        return this.listaDadosVO;
    }

    public void setListaPalitagens(AdmContatoFolhaVO arg1) {
        this.listaPalitagens = arg1;
    }

    public AdmContatoFolhaVO getListaPalitagens() {
        if (this.listaPalitagens == null) {
            this.listaPalitagens = AdmContatoFolhaVO.Factory.newInstance();
        }
        return this.listaPalitagens;
    }

}