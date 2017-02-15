package br.com.vivo.catalogoPRS.pageflows.param.matrizOferta.tipoMatrizContrato;

import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.InitialContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.apache.struts.upload.FormFile;

/*import br.com.vivo.catalogoPRS.controls.MatrizOfertaServiceControl;
import br.com.vivo.catalogoPRS.exception.CatalogoPRSException;*/
import br.com.vivo.catalogoPRS.pageflows.shared.baseFlow.BaseMappingDispatchAction;
/*import br.com.vivo.catalogoPRS.util.JMSUtil;
import br.com.vivo.catalogoPRS.util.WebServicesConfiguration;
import br.com.vivo.sn.catalogoMatrizOferta.ImportarMatrizOfertaTipoContratoRequestDocument;
import br.com.vivo.sn.catalogoMatrizOferta.ImportarMatrizOfertaTipoContratoResponseDocument;
import br.com.vivo.sn.catalogoMatrizOferta.ParametrosImportarMatrizOfertaTipoContratoDocument.ParametrosImportarMatrizOfertaTipoContrato;
*/
public class ImportacaoTipoMatrizContratoController extends BaseMappingDispatchAction {
/*	private static final long serialVersionUID = 1L;
	private String mensagem;
	
	@Control
	private MatrizOfertaServiceControl matrizOfertaServiceControl;
	
	@Jpf.Action( forwards = { @Jpf.Forward(name = "success", path = "importacaoTipoMatrizContrato.jsp") })
	public Forward begin(){
		return new Forward("success");
	}


	@Jpf.Action()
	public Forward importarTiposMatrizAndContratos(br.com.vivo.catalogoPRS.pageflows.param.matrizOferta.tipoMatrizContrato.ImportacaoTipoMatrizContratoController.TiposMatrizEndContratoFormBean form) throws Exception {
		String tipoMensagem = "importacao_tipo_matriz_contrato";
		String arquivo = form.getNmArquivo().getFileName();
		String extensao = arquivo.substring(arquivo.lastIndexOf("."), arquivo.length());
		Long idMatrizOfertaTipoContratoArquivo = null;

		if(form.getNmArquivo().getFileSize() > 10485760) {
			throw new CatalogoPRSException("Tamanho de Arquivo Inválido. O limite máximo para o tamanho do arquivo é: 10 MB.");
		}
		if(form.getNmArquivo() == null || form.getNmArquivo().getFileData() == null || form.getNmArquivo().getFileData().length == 0) {
			throw new CatalogoPRSException("Por favor, selecione um Arquivo.");
		}
		if(!extensao.equalsIgnoreCase(".csv")) {
			throw new CatalogoPRSException(" Tipo de Arquivo Inválido.");
		}
		
		try {
			ImportarMatrizOfertaTipoContratoRequestDocument tipoContratoRequestDocument = ImportarMatrizOfertaTipoContratoRequestDocument.Factory.newInstance();
			ParametrosImportarMatrizOfertaTipoContrato parametrosImportarMatrizOfertaTipoContrato = tipoContratoRequestDocument.addNewImportarMatrizOfertaTipoContratoRequest().addNewParametrosImportarMatrizOfertaTipoContrato();
			if(form.getNmArquivo() != null && form.getNmArquivo().getFileData() != null && form.getNmArquivo().getFileData().length > 0) {
				parametrosImportarMatrizOfertaTipoContrato.setNmArquivo(form.getNmArquivo().getFileName());	
			}
			prepareServiceControl(matrizOfertaServiceControl);
			ImportarMatrizOfertaTipoContratoResponseDocument contratoResponseDocument = matrizOfertaServiceControl.importarMatrizOfertaTipoContrato(tipoContratoRequestDocument);
			idMatrizOfertaTipoContratoArquivo = contratoResponseDocument.getImportarMatrizOfertaTipoContratoResponse().getResultadoImportarMatrizOfertaTipoContrato().getIdMatrizOfertaTipoContratoArquivo();
			if(idMatrizOfertaTipoContratoArquivo != null) {
				JMSUtil.enviarArquivo(form.getNmArquivo().getFileData(), idMatrizOfertaTipoContratoArquivo, null, tipoMensagem, new InitialContext());
				this.setMensagem(" Arquivo enviado com Sucesso.");
				String reloadScript = "<input type='text' value='reload_popup_importar_tpmatriz_contrato'/>";
				
				PrintWriter writer = null;
				try {
					writer = this.getResponse().getWriter();
					writer.println(reloadScript);
					writer.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}finally {
					writer.close();
				}
				
				try {
					HttpServletResponse response = getResponse();
					response.setContentType("text/javascript");
					writer = this.getResponse().getWriter();
					writer.println("$('botao_limpar_form_importar_tpmatriz_contrato').click();");
					writer.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}finally {
					writer.close();
				}
			}
			else {
				throw new CatalogoPRSException("Erro ao enviar arquivo para processamento.");
			}
		}
		catch (Exception ex) {
			throw new CatalogoPRSException("Erro ao enviar o arquivo para processamento..");
		}
		
		return null;
	}
	
	@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "popupSucessoUpload.jsp") })
	public Forward popupSucessoUpload() {
		this.getRequest().setAttribute("return", this.getMensagem());
		return new Forward("success");
	}

	@Override
	protected void onCreate() {
		matrizOfertaServiceControl.setEndpointAddress(WebServicesConfiguration.getEndpointAddress(WebServicesConfiguration.MATRIZOFERTA));
	}

	@Override
	protected void onDestroy(HttpSession session) {
	}

	@Jpf.FormBean
	public static class TiposMatrizEndContratoFormBean implements java.io.Serializable {
		private static final long serialVersionUID = 1L;
		private Long paginaSolicitada;
		private FormFile nmArquivo;
		
		public FormFile getNmArquivo() {
			return nmArquivo;
		}
		public void setNmArquivo(FormFile nmArquivo) {
			this.nmArquivo = nmArquivo;
		}
		public Long getPaginaSolicitada() {
			return paginaSolicitada;
		}
		public void setPaginaSolicitada(Long paginaSolicitada) {
			this.paginaSolicitada = paginaSolicitada;
		}
	
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}*/
}