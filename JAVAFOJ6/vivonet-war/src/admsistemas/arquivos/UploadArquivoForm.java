package admsistemas.arquivos;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import br.com.vivo.fo.admsistemas.vo.ArquivoProcessamentoVODocument.ArquivoProcessamentoVO;
import br.com.vivo.fo.constantes.ConstantesCRM;

public class UploadArquivoForm extends ActionForm {

	private static final long serialVersionUID = -8712755583018053996L;

	private String sgFuncionalidade = ConstantesCRM.SVAZIO;
	private String nmFileBad = ConstantesCRM.SVAZIO;
	private FormFile fileUpload;
	private ArquivoProcessamentoVO arquivoProcessamentoVO = ArquivoProcessamentoVO.Factory
			.newInstance();
	private String dtInicial = ConstantesCRM.SVAZIO;
	private String dtFinal = ConstantesCRM.SVAZIO;

	public String getSgFuncionalidade() {
		return sgFuncionalidade;
	}

	public void setSgFuncionalidade(String sgFuncionalidade) {
		this.sgFuncionalidade = sgFuncionalidade;
	}

	public String getNmFileBad() {
		return nmFileBad;
	}

	public void setNmFileBad(String nmFileBad) {
		this.nmFileBad = nmFileBad;
	}

	public void setFileUpload(FormFile fileUpload) {
		this.fileUpload = fileUpload;
	}

	public FormFile getFileUpload() {
		return fileUpload;
	}

	public ArquivoProcessamentoVO getArquivoProcessamentoVO() {
		return arquivoProcessamentoVO;
	}

	public void setArquivoProcessamentoVO(ArquivoProcessamentoVO arquivoProcessamentoVO) {
		this.arquivoProcessamentoVO = arquivoProcessamentoVO;
	}

	public void setDtFinal(String dtFinal) {
		this.dtFinal = dtFinal;
	}

	public String getDtFinal() {
		return dtFinal;
	}

	public void setDtInicial(String dtInicial) {
		this.dtInicial = dtInicial;
	}

	public String getDtInicial() {
		return dtInicial;
	}
}