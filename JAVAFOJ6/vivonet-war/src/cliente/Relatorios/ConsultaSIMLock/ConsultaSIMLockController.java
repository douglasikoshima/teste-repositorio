package cliente.Relatorios.ConsultaSIMLock;

import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.cliente.vo.RelatorioSimLockVODocument.RelatorioSimLockVO;
import br.com.vivo.fo.cliente.vo.RelatorioSimLockVODocument.RelatorioSimLockVO.ListaRelatorio;
import br.com.vivo.fo.cliente.vo.TipoDocumentoVODocument.TipoDocumentoVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.cliente.relatorios.consultaSIMLock.ConsultaSIMLockFacade;
import br.com.vivo.fo.log.Logger;

import com.indracompany.actions.AbstractAction;

@SuppressWarnings("unused")
public class ConsultaSIMLockController extends AbstractAction {

	private static final long serialVersionUID = -6704430665736663837L;

	@EJB
	private ConsultaSIMLockFacade controlSIMLock;

	protected global.Global globalApp = new global.Global();

	private LockForm lockForm = new LockForm();

	private final int REGISTROS_POR_PAGINA = 30;
	private transient Logger log = new Logger("cliente");

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("gerarArquivoParametros".equals(mapping.getParameter())) {
			return gerarArquivoParametros(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * @jpf:action
	 * @jpf:forward name="success" path="pesquisarSIMLocks.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		lockForm = new LockForm();
		lockForm.setListaTipoDocumentoVO(null);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public LockForm getLockForm(){
		return (this.lockForm);
	}

	private String getParameter(String param, HttpServletRequest request){
		String result = request.getParameter(param);
		return (result==null?ConstantesCRM.SVAZIO:result.trim());
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="fail" path="mensagem.jsp"
	 */
	public ActionForward gerarArquivoParametros(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		LockForm form = (LockForm) formParam;
		String user      = ConstantesCRM.getCurrentUser(request.getSession());
		String charInv   = "[()./-]";
		String documento = ConstantesCRM.SVAZIO;
		RelatorioSimLockVO relatorio = RelatorioSimLockVO.Factory.newInstance();
		relatorio.addNewFiltro();

		String dtIni  = getParameter("dtIni", request);
		String dtFim  = getParameter("dtFim", request);
		String nrImei = getParameter("nrImei", request);
		String log    = getParameter("log", request);
		String opc    = getParameter("opc", request);
		String doc    = getParameter("doc", request);

		if((!ConstantesCRM.SVAZIO.equals(dtIni)) && (!ConstantesCRM.SVAZIO.equals(dtFim))){
			relatorio.getFiltro().setDtAltIni(dtIni);
			relatorio.getFiltro().setDtAltFim(dtFim);
		}
		if(!ConstantesCRM.SVAZIO.equals(nrImei)){
			relatorio.getFiltro().setNrImei(nrImei);
		}
		if(!ConstantesCRM.SVAZIO.equals(log)){
			relatorio.getFiltro().setNmLogin(log);
		}

		if(!ConstantesCRM.SZERO.equals(opc)){
			documento = doc.replaceAll(charInv,ConstantesCRM.SVAZIO);
			if("LIN".equals(opc)){
				relatorio.getFiltro().setNrArea(documento.substring(0,2));
				relatorio.getFiltro().setNrLinha(documento.substring(2));
			}else{
				if("CPF".equals(opc)){
					relatorio.getFiltro().setNrDoc(documento);
				}
				if("CPJ".equals(opc)){
					relatorio.getFiltro().setNrDoc(documento);
				}
				if("PAS".equals(opc) || "NRG".equals(opc)){
					relatorio.getFiltro().setNrDoc(doc);
				}
			}
		}

		RelatorioSimLockVO relatorioArquivo = RelatorioSimLockVO.Factory.newInstance();
		try{
			relatorioArquivo = controlSIMLock.getRelatorioDownload(ConstantesCRM.getCurrentUser(request.getSession()), relatorio);
		}catch(Exception e){
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("fail");
		}

		if(relatorioArquivo==null){
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("fail");
		}

		response.addHeader("Content-Disposition","attachment; filename=Relatorio_SIMLock_Desbloqueados.csv");
		response.addHeader("Content-Type","application/force-download");
		response.addHeader("Content-Transfer-Encoding","binary");
		response.addHeader("Pragma","no-cache");
		response.addHeader("Expires",ConstantesCRM.SZERO);

		PrintWriter out = response.getWriter();
		StringBuffer relatorioStr = new StringBuffer(32*1024);

		relatorioStr.append("Data/Hora Solicitacao|");
		relatorioStr.append("IMEI|");
		relatorioStr.append("Login|");
		relatorioStr.append("IP|");
		relatorioStr.append("Estado da Consulta|");
		relatorioStr.append("Numero Linha|");
		relatorioStr.append("Tipo Relacionamento|");
		relatorioStr.append("Tipo Documento|");
		relatorioStr.append("Numero Documento|");
		relatorioStr.append("Nome Cliente|");
		relatorioStr.append("\n");

		for(int j=0;j<relatorioArquivo.getListaRelatorioArray().length;j++){
			ListaRelatorio lista = relatorioArquivo.getListaRelatorioArray(j);
			relatorioStr.append(lista.getDtSolic() + "|");
			relatorioStr.append(lista.getImei()+"|");
			relatorioStr.append(lista.getLogin() + "|");
			relatorioStr.append(lista.getIp() + "|");
			relatorioStr.append(lista.getStConsulta() + "|");
			relatorioStr.append(lista.getNrLinha() + "|");
			relatorioStr.append(lista.getTpRelac() + "|");
			relatorioStr.append(lista.getTpDoc() + "|");
			relatorioStr.append(lista.getNrDoc() + "|");
			relatorioStr.append(lista.getNmCli() + "|");
			relatorioStr.append("\n");
		}

		request.setAttribute("dtIni",form.getDtInicial());
		request.setAttribute("dtFim",form.getDtFinal());
		request.setAttribute("nrImei",form.getImei());
		request.setAttribute("log",form.getLogin());
		request.setAttribute("opc",form.getOpcional());
		request.setAttribute("doc",form.getNrDoc());
		lockForm=form;
		out.print(relatorioStr);
		return null;
	}

	public static class LockForm extends ActionForm {

		private static final long serialVersionUID = 2119035051851992355L;

		private String totalPaginas = ConstantesCRM.SVAZIO;
		private String visualizarTabela = ConstantesCRM.SVAZIO;
		private TipoDocumentoVO[] listaTipoDocumentoVO;
		private String msgError = ConstantesCRM.SVAZIO;
		private ListaRelatorio[] listaRelatorio = null;
		private String download = ConstantesCRM.SVAZIO;
		private String nrPag = ConstantesCRM.SVAZIO;
		private String ultimoRegistro = ConstantesCRM.SVAZIO;
		private String primeiroRegistro = ConstantesCRM.SVAZIO;
		private String totalReg = ConstantesCRM.SVAZIO;
		private String totalRegPag = ConstantesCRM.SVAZIO;
		private String nrDoc = ConstantesCRM.SVAZIO;
		private String imei = ConstantesCRM.SVAZIO;
		private String login = ConstantesCRM.SVAZIO;
		private String opcional = ConstantesCRM.SVAZIO;
		private String dtInicial = ConstantesCRM.SVAZIO;
		private String dtFinal = ConstantesCRM.SVAZIO;
		private String vlDocumento = ConstantesCRM.SVAZIO;
		private String idDocumento = ConstantesCRM.SVAZIO;
		private String loginUsuario = ConstantesCRM.SVAZIO;
		private String numeroIMEI = ConstantesCRM.SVAZIO;
		private String dataFinal = ConstantesCRM.SVAZIO;
		private String dataInicial = ConstantesCRM.SVAZIO;

		public LockForm(){
			msgError=ConstantesCRM.SVAZIO;
			this.listaTipoDocumentoVO=new TipoDocumentoVO[0];
			this.listaRelatorio=new ListaRelatorio[0];
			download=ConstantesCRM.SVAZIO;
			nrPag=ConstantesCRM.SZERO;
			ultimoRegistro=ConstantesCRM.SVAZIO;
			primeiroRegistro=ConstantesCRM.SVAZIO;
			totalReg=ConstantesCRM.SZERO;
			totalRegPag=ConstantesCRM.SZERO;
			nrDoc=ConstantesCRM.SVAZIO;
			imei=ConstantesCRM.SVAZIO;
			login=ConstantesCRM.SVAZIO;
			opcional=ConstantesCRM.SVAZIO;
			dtInicial=ConstantesCRM.SVAZIO;
			dtFinal=ConstantesCRM.SVAZIO;
			vlDocumento=ConstantesCRM.SVAZIO;
			idDocumento=ConstantesCRM.SVAZIO;
			loginUsuario=ConstantesCRM.SVAZIO;
			numeroIMEI=ConstantesCRM.SVAZIO;
			dataFinal=ConstantesCRM.SVAZIO;
			dataInicial=ConstantesCRM.SVAZIO;
		}

		public void setDtFinal(String dtFinal){
			this.dtFinal = dtFinal;
		}

		public String getDtFinal(){
			return this.dtFinal;
		}

		public void setDtInicial(String dtInicial){
			this.dtInicial = dtInicial;
		}

		public String getDtInicial(){
			return this.dtInicial;
		}

		public void setOpcional(String opcional){
			this.opcional = opcional;
		}

		public String getOpcional(){
			return this.opcional;
		}

		public void setLogin(String login){
			this.login = login;
		}

		public String getLogin(){
			return this.login;
		}

		public void setImei(String imei){
			this.imei = imei;
		}

		public String getImei(){
			return this.imei;
		}

		public void setNrDoc(String nrDoc){
			this.nrDoc = nrDoc;
		}

		public String getNrDoc(){
			return this.nrDoc;
		}

		public void setTotalRegPag(String totalRegPag){
			this.totalRegPag = totalRegPag;
		}

		public String getTotalRegPag(){
			return this.totalRegPag;
		}

		public void setTotalReg(String totalReg){
			this.totalReg = totalReg;
		}

		public String getTotalReg(){
			return this.totalReg;
		}

		public void setPrimeiroRegistro(String primeiroRegistro){
			this.primeiroRegistro = primeiroRegistro;
		}

		public String getPrimeiroRegistro(){
			return this.primeiroRegistro;
		}

		public void setUltimoRegistro(String ultimoRegistro){
			this.ultimoRegistro = ultimoRegistro;
		}

		public String getUltimoRegistro(){
			return this.ultimoRegistro;
		}

		public void setNrPag(String nrPag){
			this.nrPag = nrPag;
		}

		public String getNrPag(){
			return this.nrPag;
		}

		public void setDownload(String download){
			this.download = download;
		}

		public String getDownload(){
			return this.download;
		}

		public void setListaRelatorio(ListaRelatorio[] listaRelatorio){
			this.listaRelatorio = listaRelatorio;
		}

		public ListaRelatorio[] getListaRelatorio(){
			return this.listaRelatorio;
		}

		public void setMsgError(String msgError){
			this.msgError = msgError;
		}

		public String getMsgError(){
			return this.msgError;
		}

		public void setListaTipoDocumentoVO(TipoDocumentoVO[] listaTipoDocumentoVO){
			this.listaTipoDocumentoVO = listaTipoDocumentoVO;
		}

		public TipoDocumentoVO[] getListaTipoDocumentoVO(){
			return this.listaTipoDocumentoVO;
		}

		public void setVisualizarTabela(String visualizarTabela){
			this.visualizarTabela = visualizarTabela;
		}

		public String getVisualizarTabela(){
			return this.visualizarTabela;
		}

		public void setTotalPaginas(String totalPaginas){
			this.totalPaginas = totalPaginas;
		}

		public String getTotalPaginas(){
			return this.totalPaginas;
		}
	}
}
