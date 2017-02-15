package fidelizacao.Configurar.RelMatrizDescGrupo;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.fidelizacao.manter.RelDescGrupo;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoRelDescGrupoVODocument.FidelizacaoRelDescGrupoVO;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoRelDescGrupoVODocument.FidelizacaoRelDescGrupoVO.ListaMatrizDescAssoc;

import com.indracompany.actions.AbstractAction;

public class RelMatrizDescGrupoController extends AbstractAction {

	private static final long serialVersionUID = -3138373098634108841L;

	protected global.Global globalApp = new global.Global();
	private String user = ConstantesCRM.SVAZIO;
	private AssociarGrupoDescontoForm associarGrupoDescontoForm = new AssociarGrupoDescontoForm();

	@EJB
	private RelDescGrupo relDescGrupo;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("done".equals(mapping.getParameter())) {
			return done(mapping, form, request, response);
		} else if ("carregarListas".equals(mapping.getParameter())) {
			return carregarListas(mapping, form, request, response);
		} else if ("salvar".equals(mapping.getParameter())) {
			return salvar(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 *
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 */
	protected ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			user = ConstantesCRM.getCurrentUser(request.getSession());
			FidelizacaoRelDescGrupoVO f = relDescGrupo.getListaGrupoRetencao(user);
			getAssociarGrupoDescontoForm().setListaGrupoRetencao(f.getListaGrupoRetencaoArray());

			/* REMOVER ESSE TRECHO APOS TESTE FAKE */
			/*
			 * ListaGrupoRetencao[] lista = new ListaGrupoRetencao[4];
			 * lista[0]=ListaGrupoRetencao.Factory.newInstance();
			 * lista[0].setId("1"); lista[0].setDs("Grupo 1");
			 * lista[1]=ListaGrupoRetencao.Factory.newInstance();
			 * lista[1].setId("2"); lista[1].setDs("Grupo 2");
			 * lista[2]=ListaGrupoRetencao.Factory.newInstance();
			 * lista[2].setId("3"); lista[2].setDs("Grupo 3");
			 * lista[3]=ListaGrupoRetencao.Factory.newInstance();
			 * lista[3].setId("4"); lista[3].setDs("Grupo 4");
			 *
			 * getAssociarGrupoDescontoForm().setListaGrupoRetencao(lista);
			 */
			/* FIM TRECHO FAKE */
		} catch (Exception e) {
			request.setAttribute("msgErro", e.getMessage());
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="done" return-action="RelAssociarMatrizGrupoDone"
	 */
	public ActionForward done(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward("done");
	}

	public AssociarGrupoDescontoForm getAssociarGrupoDescontoForm() {
		return associarGrupoDescontoForm;
	}

	/**
	 * This method represents the point of entry into the pageflow
	 *
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 */
	protected ActionForward carregarListas(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AssociarGrupoDescontoForm form = (AssociarGrupoDescontoForm) formParam;
		try {
			user = ConstantesCRM.getCurrentUser(request.getSession());

			FidelizacaoRelDescGrupoVO f = FidelizacaoRelDescGrupoVO.Factory.newInstance();

			f.setIdGrupoRetencao(form.getIdGrupoRetencao());

			f = relDescGrupo.getListasMatrizDesconto(user, f);

			getAssociarGrupoDescontoForm().setIdGrupoRetencao(form.getIdGrupoRetencao());

			if (f.getListaMatrizDescDispArray() != null && f.getListaMatrizDescDispArray().length > 0) {
				getAssociarGrupoDescontoForm().setListaMtzDescDisp(f.getListaMatrizDescDispArray());
			} else {
				getAssociarGrupoDescontoForm().setListaMtzDescDisp(new br.com.vivo.fo.fidelizacao.vo.FidelizacaoRelDescGrupoVODocument.FidelizacaoRelDescGrupoVO.ListaMatrizDescDisp[0]);
			}
			if (f.getListaMatrizDescAssocArray() != null && f.getListaMatrizDescAssocArray().length > 0) {
				getAssociarGrupoDescontoForm().setListaMtzDescAssoc(f.getListaMatrizDescAssocArray());
			} else {
				getAssociarGrupoDescontoForm().setListaMtzDescAssoc(new br.com.vivo.fo.fidelizacao.vo.FidelizacaoRelDescGrupoVODocument.FidelizacaoRelDescGrupoVO.ListaMatrizDescAssoc[0]);
			}
		} catch (Exception e) {
			request.setAttribute("msgErro", e.getMessage());
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);
		}

		/* REMOVER ESSE TRECHO APOS TESTE FAKE */
		/*
		 * getAssociarGrupoDescontoForm().setIdGrupoRetencao(form.getIdGrupoRetencao
		 * ());
		 *
		 * ListaMatrizDescAssoc[] la= new ListaMatrizDescAssoc[3];
		 * la[0]=ListaMatrizDescAssoc.Factory.newInstance(); la[0].setId("1");
		 * la[0].setDs("matriz 1");
		 *
		 * la[1]=ListaMatrizDescAssoc.Factory.newInstance(); la[1].setId("2");
		 * la[1].setDs("matriz 2");
		 *
		 * la[2]=ListaMatrizDescAssoc.Factory.newInstance(); la[2].setId("3");
		 * la[2].setDs("matriz 3");
		 *
		 * ListaMatrizDescDisp[] ld= new ListaMatrizDescDisp[3];
		 * ld[0]=ListaMatrizDescDisp.Factory.newInstance(); ld[0].setId("4");
		 * ld[0].setDs("matriz 4");
		 *
		 * ld[1]=ListaMatrizDescDisp.Factory.newInstance(); ld[1].setId("5");
		 * ld[1].setDs("matriz 5");
		 *
		 * ld[2]=ListaMatrizDescDisp.Factory.newInstance(); ld[2].setId("6");
		 * ld[2].setDs("matriz 6");
		 *
		 * getAssociarGrupoDescontoForm().setListaMtzDescAssoc(la);
		 * getAssociarGrupoDescontoForm().setListaMtzDescDisp(ld);
		 */
		/* FIMM DO TRECHO FAKE */

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);

	}

	/**
	 * This method represents the point of entry into the pageflow
	 *
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 */
	protected ActionForward salvar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AssociarGrupoDescontoForm form = (AssociarGrupoDescontoForm) formParam;
		try {

			user = ConstantesCRM.getCurrentUser(request.getSession());

			FidelizacaoRelDescGrupoVO f = FidelizacaoRelDescGrupoVO.Factory.newInstance();

			f.setIdGrupoRetencao(form.getIdGrupoRetencao());
			/*
			 * if(form.getListaDispGravar()!=null &&
			 * form.getListaDispGravar().length>0){ String [] sd =
			 * form.getListaDispGravar(); for(int i=0;i<sd.length;i++){
			 * ListaMatrizDescDisp ld =
			 * ListaMatrizDescDisp.Factory.newInstance(); ld.setId(sd[i]);
			 * f.addNewListaMatrizDescDisp();
			 * f.setListaMatrizDescDispArray(i,ld); } }
			 */
			if (form.getListaAssocGravar() != null && form.getListaAssocGravar().length > 0) {
				String[] sa = form.getListaAssocGravar();
				for (int j = 0; j < sa.length; j++) {
					ListaMatrizDescAssoc la = ListaMatrizDescAssoc.Factory.newInstance();
					la.setId(sa[j]);
					f.addNewListaMatrizDescAssoc();
					f.setListaMatrizDescAssocArray(j, la);
				}
			}

			relDescGrupo.gravarListaDescAssociado(user, f);
			request.setAttribute("msgErro", "Operação realizada com sucesso");

			// f =FidelizacaoRelDescGrupoVO.Factory.newInstance();

			// f.setIdGrupoRetencao(form.getIdGrupoRetencao());

			// f=relDescGrupo.getListasMatrizDesconto(user,f);

			// getAssociarGrupoDescontoForm().setIdGrupoRetencao(form.getIdGrupoRetencao());

			// if(f.getListaMatrizDescDispArray()!=null &&
			// f.getListaMatrizDescDispArray().length>0){
			// getAssociarGrupoDescontoForm().setListaMtzDescDisp(f.getListaMatrizDescDispArray());
			// }
			// if(f.getListaMatrizDescAssocArray()!=null &&
			// f.getListaMatrizDescAssocArray().length>0){
			// getAssociarGrupoDescontoForm().setListaMtzDescAssoc(f.getListaMatrizDescAssocArray());
			// }

			user = ConstantesCRM.getCurrentUser(request.getSession());
			f = relDescGrupo.getListaGrupoRetencao(user);
			getAssociarGrupoDescontoForm().setListaGrupoRetencao(f.getListaGrupoRetencaoArray());
			getAssociarGrupoDescontoForm().setListaMtzDescAssoc(new br.com.vivo.fo.fidelizacao.vo.FidelizacaoRelDescGrupoVODocument.FidelizacaoRelDescGrupoVO.ListaMatrizDescAssoc[0]);
			getAssociarGrupoDescontoForm().setListaMtzDescDisp(new br.com.vivo.fo.fidelizacao.vo.FidelizacaoRelDescGrupoVODocument.FidelizacaoRelDescGrupoVO.ListaMatrizDescDisp[0]);
			getAssociarGrupoDescontoForm().setIdGrupoRetencao(ConstantesCRM.SVAZIO);

		} catch (Exception e) {
			request.setAttribute("msgErro", e.getMessage());
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class AssociarGrupoDescontoForm extends ActionForm {

		private static final long serialVersionUID = 8214109515148008826L;

		private String[] listaDispGravar;
		private String[] listaAssocGravar;
		private String idGrupoRetencao;
		private br.com.vivo.fo.fidelizacao.vo.FidelizacaoRelDescGrupoVODocument.FidelizacaoRelDescGrupoVO.ListaMatrizDescAssoc[] listaMtzDescAssoc;
		private br.com.vivo.fo.fidelizacao.vo.FidelizacaoRelDescGrupoVODocument.FidelizacaoRelDescGrupoVO.ListaMatrizDescDisp[] listaMtzDescDisp;
		private br.com.vivo.fo.fidelizacao.vo.FidelizacaoRelDescGrupoVODocument.FidelizacaoRelDescGrupoVO.ListaGrupoRetencao[] listaGrupoRetencao;

		public void setListaGrupoRetencao(br.com.vivo.fo.fidelizacao.vo.FidelizacaoRelDescGrupoVODocument.FidelizacaoRelDescGrupoVO.ListaGrupoRetencao[] listaGrupoRetencao) {
			this.listaGrupoRetencao = listaGrupoRetencao;
		}

		public br.com.vivo.fo.fidelizacao.vo.FidelizacaoRelDescGrupoVODocument.FidelizacaoRelDescGrupoVO.ListaGrupoRetencao[] getListaGrupoRetencao() {
			if (this.listaGrupoRetencao == null || this.listaGrupoRetencao.length == 0) {
				this.listaGrupoRetencao = new br.com.vivo.fo.fidelizacao.vo.FidelizacaoRelDescGrupoVODocument.FidelizacaoRelDescGrupoVO.ListaGrupoRetencao[0];
			}

			return this.listaGrupoRetencao;
		}

		public void setListaMtzDescDisp(br.com.vivo.fo.fidelizacao.vo.FidelizacaoRelDescGrupoVODocument.FidelizacaoRelDescGrupoVO.ListaMatrizDescDisp[] listaMtzDescDisp) {
			this.listaMtzDescDisp = listaMtzDescDisp;
		}

		public br.com.vivo.fo.fidelizacao.vo.FidelizacaoRelDescGrupoVODocument.FidelizacaoRelDescGrupoVO.ListaMatrizDescDisp[] getListaMtzDescDisp() {
			if (this.listaMtzDescDisp == null || this.listaMtzDescDisp.length == 0) {
				this.listaMtzDescDisp = new br.com.vivo.fo.fidelizacao.vo.FidelizacaoRelDescGrupoVODocument.FidelizacaoRelDescGrupoVO.ListaMatrizDescDisp[0];
			}

			return this.listaMtzDescDisp;
		}

		public void setListaMtzDescAssoc(br.com.vivo.fo.fidelizacao.vo.FidelizacaoRelDescGrupoVODocument.FidelizacaoRelDescGrupoVO.ListaMatrizDescAssoc[] listaMtzDescAssoc) {
			this.listaMtzDescAssoc = listaMtzDescAssoc;
		}

		public br.com.vivo.fo.fidelizacao.vo.FidelizacaoRelDescGrupoVODocument.FidelizacaoRelDescGrupoVO.ListaMatrizDescAssoc[] getListaMtzDescAssoc() {
			if (this.listaMtzDescAssoc == null || this.listaMtzDescAssoc.length == 0) {
				this.listaMtzDescAssoc = new br.com.vivo.fo.fidelizacao.vo.FidelizacaoRelDescGrupoVODocument.FidelizacaoRelDescGrupoVO.ListaMatrizDescAssoc[0];
			}

			return this.listaMtzDescAssoc;
		}

		public void setIdGrupoRetencao(String idGrupoRetencao) {
			this.idGrupoRetencao = idGrupoRetencao;
		}

		public String getIdGrupoRetencao() {
			return this.idGrupoRetencao;
		}

		public void setListaAssocGravar(String[] listaAssocGravar) {
			this.listaAssocGravar = listaAssocGravar;
		}

		public String[] getListaAssocGravar() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if (this.listaAssocGravar == null || this.listaAssocGravar.length == 0) {
				this.listaAssocGravar = new String[1];
			}

			return this.listaAssocGravar;
		}

		public void setListaDispGravar(String[] listaDispGravar) {
			this.listaDispGravar = listaDispGravar;
		}

		public String[] getListaDispGravar() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if (this.listaDispGravar == null || this.listaDispGravar.length == 0) {
				this.listaDispGravar = new String[1];
			}

			return this.listaDispGravar;
		}
	}
}
