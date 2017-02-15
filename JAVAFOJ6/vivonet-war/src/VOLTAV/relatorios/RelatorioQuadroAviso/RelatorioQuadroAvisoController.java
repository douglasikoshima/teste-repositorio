package VOLTAV.relatorios.RelatorioQuadroAviso;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.cliente.Pesquisar;
import br.com.vivo.fo.cliente.vo.PaginacaoDocument.Paginacao;
import br.com.vivo.fo.cliente.vo.ResultsetDocument.Resultset;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.VOLTAV.quadroAviso.QuadroAvisoFacade;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoVODocument.FidelizacaoVO.ListasVO.Lista.Disponivel;
import br.com.vivo.fo.fidelizacao.vo.ItDocument.It;
import br.com.vivo.fo.log.Logger;

import com.indracompany.actions.AbstractAction;

public class RelatorioQuadroAvisoController extends AbstractAction {

	private static final long serialVersionUID = 6808730369463683316L;

	protected global.Global globalApp = new global.Global();

	private transient Logger log = new Logger("VOLTAV");

	private RelatorioForm relatorioForm;

	public RelatorioForm getRelatorioForm(){
		if(this.relatorioForm==null) {
			this.relatorioForm = new RelatorioForm();
		}
		return this.relatorioForm;
	}

	@EJB
	private QuadroAvisoFacade quadroAvisoFacade;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("pesquisar".equals(mapping.getParameter())) {
			return pesquisar(mapping, form, request, response);
		} else if ("exportar".equals(mapping.getParameter())) {
			return exportar(mapping, form, request, response);
		} else if ("pesquisaConta".equals(mapping.getParameter())) {
			return pesquisaConta(mapping, form, request, response);
		} else if ("excluirMensagem".equals(mapping.getParameter())) {
			return excluirMensagem(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 */
	protected ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		try {
			this.relatorioForm = new RelatorioForm();
			getRelatorioForm().setListaRegional(getListaUF());
			getRelatorioForm().setListaSegmento(getListaSegmento());
		} catch(Exception e) {
			log.error("RelatorioQuadroAvisoController::begin",e);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	private Disponivel getListaUF(){
		String query = "select iduf, nmuf from apoio.uf where inpreenchelista = 1";
		Pesquisar pesquisar = new Pesquisar();
		Resultset rsVO = pesquisar.executar(query);
		Disponivel listaTpLinha = Disponivel.Factory.newInstance();
		for(int j=0;j<rsVO.getLinhas().getLinhaArray().length;j++){
			Resultset.Linhas.Linha linha = rsVO.getLinhas().getLinhaArray(j);
			It it = listaTpLinha.addNewIt();
			it.setId(linha.getValorArray(0));
			it.setDs(linha.getValorArray(1));
		}
		return listaTpLinha;
	}

	private Disponivel getListaSegmento(){
		String query = "select idsegmentacao, dssegmentacao from apoio.segmentacao";
		Pesquisar pesquisar = new Pesquisar();
		Resultset rsVO = pesquisar.executar(query);
		Disponivel listaTpLinha = Disponivel.Factory.newInstance();
		for(int j=0;j<rsVO.getLinhas().getLinhaArray().length;j++){
			Resultset.Linhas.Linha linha = rsVO.getLinhas().getLinhaArray(j);
			It it = listaTpLinha.addNewIt();
			it.setId(linha.getValorArray(0));
			it.setDs(linha.getValorArray(1));
		}
		return listaTpLinha;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="tabelaRelatorio.jsp"
	 */
	protected ActionForward pesquisar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response){

		RelatorioForm form = (RelatorioForm)formParam;

		try{
			StringBuffer query = new StringBuffer("select mensagem.cdconta,\n" +
					"       apoiouf.sguf,\n" +
					"       apoioseg.dssegmentacao,\n" +
					"       to_char(mensagem.dtinicio, 'dd/mm/yyyy'),\n" +
					"       to_char(mensagem.dtfim, 'dd/mm/yyyy'),\n"+
					"       mensagem.dsmensagem,\n"+
					"       mensagem.dtcancelamento,\n"+
					"       mensagem.idmensagem\n"+
					"  from contatoadm.mensagem       mensagem,\n" +
					"       contatoadm.msguf          msguf,\n" +
					"       contatoadm.msgsegmentacao msgseg,\n" +
					"       apoio.uf                  apoiouf,\n" +
					"       apoio.segmentacao         apoioseg\n" +
					" where mensagem.idmensagem = msguf.idmensagem(+) \n" +
					"   and mensagem.idmensagem = msgseg.idmensagem(+) \n" +
					"   and msguf.iduf = apoiouf.iduf(+) \n" +
			"   and msgseg.idsegmentacao = apoioseg.idsegmentacao(+) ");

			if (form.getConta() != null && form.getConta().length() > 0) {
				query.append(" and mensagem.cdconta = '" + form.getConta()+ "'");
			}

			if (form.getSegmentacao() != null && form.getSegmentacao().length() > 0) {
				query.append(" and msgseg.idsegmentacao = '" + form.getSegmentacao()+ "'");
			}

			if (form.getRegional() != null && form.getRegional().length() > 0) {
				query.append(" and msguf.iduf = '" + form.getRegional()+ "'");
			}


			if (form.getDataInicio() != null && form.getDataTermino() != null && form.getDataInicio().length() > 0 && form.getDataTermino().length() > 0) {

				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");

				query.append(" and to_char(mensagem.dtinicio, 'yyyymmdd') >= '" + sdf2.format(sdf.parse( form.getDataInicio())) + "'");
				query.append(" and to_char(mensagem.dtfim, 'yyyymmdd') <= '" + sdf2.format(sdf.parse( form.getDataTermino())) + "'");

				//query.append(" and mensagem.dtinicio >= to_date('" + form.getDataInicio()+ "','DD/MM/YYYY') ");
				//query.append(" and mensagem.dtfim    <= to_date('" + form.getDataTermino()+ "','DD/MM/YYYY') ");
			}

			log.debug(query);

			Pesquisar pesquisar = new Pesquisar();
			Resultset rsVO = pesquisar.executar(query.toString());

			log.debug("Retorno do relatorio: " + rsVO.getLinhas());

			if(rsVO==null || rsVO.getLinhas()==null){
				rsVO = Resultset.Factory.newInstance();
				rsVO.addNewLinhas();
				log.debug("Adicionada linha ao relatorio");
			}

			log.debug("Colocando ResultSet na Pagina");
			getRelatorioForm().setResultset(rsVO);

			if (rsVO.getLinhas() == null ) {
				request.setAttribute("msgStatus", "Não existem mensagens para visualização");
			}


		}catch(Exception e){
			log.error("RelatorioQuadroAvisoController::pesquisar",e);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 */
	protected ActionForward exportar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response){

		RelatorioForm form = (RelatorioForm)formParam;

		try{


			StringBuffer query = new StringBuffer("select mensagem.cdconta,\n" +
					"       apoiouf.sguf,\n" +
					"       apoioseg.dssegmentacao,\n" +
					"       to_char(mensagem.dtinicio, 'dd/mm/yyyy'),\n" +
					"       to_char(mensagem.dtfim, 'dd/mm/yyyy'),\n"+
					"       mensagem.dsmensagem,\n"+
					"       nvl(mensagem.dtcancelamento, '')\n"+
					"  from contatoadm.mensagem       mensagem,\n" +
					"       contatoadm.msguf          msguf,\n" +
					"       contatoadm.msgsegmentacao msgseg,\n" +
					"       apoio.uf                  apoiouf,\n" +
					"       apoio.segmentacao         apoioseg\n" +
					" where mensagem.idmensagem = msguf.idmensagem(+) \n" +
					"   and mensagem.idmensagem = msgseg.idmensagem(+) \n" +
					"   and msguf.iduf = apoiouf.iduf(+) \n" +
			"   and msgseg.idsegmentacao = apoioseg.idsegmentacao(+) ");

			if (form.getConta() != null && form.getConta().length() > 0) {
				query.append(" and mensagem.cdconta = '" + form.getConta()+ "'");
			}

			if (form.getSegmentacao() != null && form.getSegmentacao().length() > 0) {
				query.append(" and msgseg.idsegmentacao = '" + form.getSegmentacao()+ "'");
			}

			if (form.getRegional() != null && form.getRegional().length() > 0) {
				query.append(" and msguf.iduf = '" + form.getRegional()+ "'");
			}


			if (form.getDataInicio() != null && form.getDataTermino() != null && form.getDataInicio().length() > 0 && form.getDataTermino().length() > 0) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");

				query.append(" and to_char(mensagem.dtinicio, 'yyyymmdd') >= '" + sdf2.format(sdf.parse( form.getDataInicio())) + "'");
				query.append(" and to_char(mensagem.dtfim, 'yyyymmdd') <= '" + sdf2.format(sdf.parse( form.getDataTermino())) + "'");
			}



			Pesquisar pesquisar = new Pesquisar();
			Resultset rsVO = pesquisar.executar(query.toString());
			if(rsVO==null || rsVO.getLinhas()==null){
				rsVO = Resultset.Factory.newInstance();
				rsVO.addNewLinhas();
			}


			response.addHeader("Content-Disposition","attachment; filename=relatorio_quadro_aviso.csv");
			response.addHeader("Content-Type","application/force-download");
			response.addHeader("Content-Transfer-Encoding","binary");
			response.addHeader("Pragma","no-cache");
			response.addHeader("Expires",ConstantesCRM.SZERO);

			PrintWriter out = response.getWriter();
			StringBuffer relatorioStr = new StringBuffer(1024);

			relatorioStr.append("Conta|");
			relatorioStr.append("Regional|");
			relatorioStr.append("Segmentacao|");
			relatorioStr.append("Data Envio|");
			relatorioStr.append("Data Validade|");
			relatorioStr.append("Mensagem|");
			relatorioStr.append("Data Exclusao|");
			relatorioStr.append("\n");

			for(int j=0;j<rsVO.getLinhas().getLinhaArray().length;j++){
				Resultset.Linhas.Linha linha = rsVO.getLinhas().getLinhaArray(j);
				for(int l=0;l<linha.getValorArray().length;l++){
					relatorioStr.append(linha.getValorArray(l) + "|");
				}
				relatorioStr.append("\n");
			}

			out.print(relatorioStr);

			if (rsVO.getLinhas() == null ) {
				request.setAttribute("msgStatus", "Não existem mensagens para visualização");
			}


		}catch(Exception e){
			log.error("RelatorioQuadroAvisoController::exportar",e);
		}
		return null;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 */
	protected ActionForward pesquisaConta(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String msg = ConstantesCRM.SVAZIO;
		String user = ConstantesCRM.getCurrentUser(request.getSession());

		Pesquisar pesquisar = new Pesquisar();

		RelatorioForm form = (RelatorioForm)formParam;

		try {

			String query2 = "select distinct pe.iduf, nvl(psh.idsegmentacao,11)\n" +
			"from customer.conta                      c,\n" +
			"     customer.pessoaconta                pc,\n" +
			"     customer.contaendereco              ce,\n" +
			"     customer.pessoaendereco             pe,\n" +
			"     customer.pessoadepara               pdp,\n" +
			"     customer.pessoasegmentacao          ps,\n" +
			"     customer.pessoasegmentacaohistorico psh\n" +
			"where c.cdconta                  = '" + form.getConta() + "'" +
			"  and pc.idconta                 = c.idconta\n" +
			"  and ce.idconta                 = c.idconta\n" +
			"  and ce.idpessoaendereco        = pe.idpessoaendereco\n" +
			"  and pdp.idpessoa               = pe.idpessoa\n" +
			"  and ps.idpessoadepara(+)       = pdp.idpessoadepara\n" +
			"  and psh.idpessoasegmentacao(+) = ps.idpessoasegmentacao";

			Pesquisar pesquisar2 = new Pesquisar();
			Resultset rsVO2 = pesquisar.executar(query2);

			if (rsVO2.getLinhas().getLinhaArray().length == 0 ) {
				request.setAttribute("msgStatus", "Conta não encontrada.");
				getRelatorioForm().setConta(ConstantesCRM.SVAZIO);
				getRelatorioForm().setSegmentacao(ConstantesCRM.SVAZIO);
				getRelatorioForm().setRegional(ConstantesCRM.SVAZIO);
				getRelatorioForm().setDataInicio(ConstantesCRM.SVAZIO);
				getRelatorioForm().setDataTermino(ConstantesCRM.SVAZIO);
			} else {

				for(int j=0;j<rsVO2.getLinhas().getLinhaArray().length;j++){
					Resultset.Linhas.Linha linha = rsVO2.getLinhas().getLinhaArray(j);
					getRelatorioForm().setConta(form.getConta());
					getRelatorioForm().setSegmentacao(linha.getValorArray(1));
					getRelatorioForm().setRegional(linha.getValorArray(0));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 */
	protected ActionForward excluirMensagem(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String msg  = ConstantesCRM.SVAZIO;

		RelatorioForm form = (RelatorioForm)formParam;

		try {
			quadroAvisoFacade.excluirMensagem(form.getIdmensagem());
		} catch (Exception e) {
			e.printStackTrace();
			msg = "Erro ao excluir a mensagem.";
		}
		msg = "Mensagem excluida com sucesso.";
		request.setAttribute("msgStatus", msg);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class RelatorioForm extends ActionForm {

		private int        regPorPagina;
		private int        pageNumber = 0;
		private String     conta        = ConstantesCRM.SVAZIO;
		private String     regional     = ConstantesCRM.SVAZIO;
		private String     segmentacao  = ConstantesCRM.SVAZIO;
		private String     dataInicio   = ConstantesCRM.SVAZIO;
		private String     dataTermino  = ConstantesCRM.SVAZIO;
		private String     idmensagem   = ConstantesCRM.SVAZIO;

		private Resultset  resultset;
		private Paginacao  paginacao;

		private Disponivel listaRegional;
		private Disponivel listaSegmento;

		public void setIdmensagem(String idmensagem) {
			this.idmensagem = idmensagem;
		}

		public String getIdmensagem() {
			return this.idmensagem;
		}


		public void setListaRegional(Disponivel listaRegional) {
			this.listaRegional = listaRegional;
		}

		public Disponivel getListaRegional() {
			return this.listaRegional;
		}

		public void setListaSegmento(Disponivel listaSegmento) {
			this.listaSegmento = listaSegmento;
		}

		public Disponivel getListaSegmento() {
			return this.listaSegmento;
		}


		public String getConta() {
			return conta;
		}
		public void setConta(String conta) {
			this.conta = conta;
		}
		public String getRegional() {
			return regional;
		}
		public void setRegional(String regional) {
			this.regional = regional;
		}
		public String getSegmentacao() {
			return segmentacao;
		}
		public void setSegmentacao(String segmentacao) {
			this.segmentacao = segmentacao;
		}
		public String getDataInicio() {
			return dataInicio;
		}
		public void setDataInicio(String dataInicio) {
			this.dataInicio = dataInicio;
		}
		public String getDataTermino() {
			return dataTermino;
		}
		public void setDataTermino(String dataTermino) {
			this.dataTermino = dataTermino;
		}

		public void setResultset(Resultset resultset){
			this.resultset = resultset;
		}

		public Resultset getResultset(){
			return this.resultset;
		}

		public void setPaginacao(Paginacao paginacao){
			this.paginacao = paginacao;
		}

		public Paginacao getPaginacao(){
			return this.paginacao;
		}

		public void setPageNumber(int pageNumber){
			this.pageNumber = pageNumber;
		}

		public int getPageNumber(){
			return this.pageNumber;
		}

		public void setRegPorPagina(int regPorPagina){
			this.regPorPagina = regPorPagina;
		}

		public int getRegPorPagina(){
			return this.regPorPagina;
		}
	}
}
