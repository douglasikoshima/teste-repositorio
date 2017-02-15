package admsistemas.admArvoreContato.workflow.PerfilGrupoContatoCRI;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.admsistemas.vo.GrupoVODocument.GrupoVO;
import br.com.vivo.fo.admsistemas.vo.PerfilDocument.Perfil;
import br.com.vivo.fo.admsistemas.vo.PerfilGrupoVODocument.PerfilGrupoVO;
import br.com.vivo.fo.admsistemas.vo.PerfilVariaveisVODocument.PerfilVariaveisVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.workflow.vo.WFAcaoRetornoVODocument.WFAcaoRetornoVO;

import com.indracompany.actions.AbstractAction;

public class PerfilGrupoContatoCRIController extends AbstractAction {

	private static final long serialVersionUID = 580824078388320689L;

	@EJB
	private br.com.vivo.fo.ctrls.admsistemas.perfilGrupoCRIContato.PerfilGrupoCRIContato perfilGrupoCRIContatoFacade;

	private PerfilGrupoContatoCRIForm perfilGrupoContatoCRIForm = null;

	protected global.Global globalApp = new global.Global();

	private String user = ConstantesCRM.SVAZIO;

	private static  transient Logger log = new Logger("admsistemas");

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("efetivarAssociacao".equals(mapping.getParameter())) {
			return efetivarAssociacao(mapping, form, request, response);
		} else if ("desassociaPerfilGrupo".equals(mapping.getParameter())) {
			return desassociaPerfilGrupo(mapping, form, request, response);
		} else if ("associarPerfilGrupo".equals(mapping.getParameter())) {
			return associarPerfilGrupo(mapping, form, request, response);
		} else if ("moverPerfilGrupo".equals(mapping.getParameter())) {
			return moverPerfilGrupo(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="perfilGrupoContatoCRI.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam,HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("PerfilGrupoContatoCRIController:begin(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");
		PerfilGrupoContatoCRIForm form = (PerfilGrupoContatoCRIForm) formParam;
		user = ConstantesCRM.getCurrentUser(request.getSession());

		this.perfilGrupoContatoCRIForm = new PerfilGrupoContatoCRIForm();

		this.perfilGrupoContatoCRIForm.setPerfilVariaveisVO(this.perfilGrupoCRIContatoFacade.getPerfilGrupo(user,"<contato>"+form.getIdContato()+"</contato>"));
		this.perfilGrupoContatoCRIForm.getPerfilVariaveisVO().setGrupoVOArray(this.perfilGrupoCRIContatoFacade.getGrupo(user,"<contato>"+form.getIdContato()+"</contato><inPerfil>1</inPerfil>").getGrupoVOArray());
		this.perfilGrupoContatoCRIForm.getPerfilVariaveisVO().setPerfilArray(this.perfilGrupoCRIContatoFacade.getPerfil(user,"<contato>"+form.getIdContato()+"</contato>").getPerfilArray());

		String[] strGrupoPerfil = new String[this.perfilGrupoContatoCRIForm.getPerfilVariaveisVO().getPerfilGrupoVOArray().length];
		for(int i=0; i<strGrupoPerfil.length;i++){
			strGrupoPerfil[i] = (this.perfilGrupoContatoCRIForm.getPerfilVariaveisVO().getPerfilGrupoVOArray(i).getIdGrupoPerfil());
		}
		this.perfilGrupoContatoCRIForm.setIdGrupoPerfil(strGrupoPerfil);
		verificaGrupoIguais();
		this.perfilGrupoContatoCRIForm.setIdContato(form.getIdContato());

		this.getPerfilGrupoContatoCRIForm().setPerfilIdx(String.valueOf(this.perfilGrupoContatoCRIForm.getPerfilVariaveisVO().getPerfilGrupoVOArray().length));

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}


	/**
	 * @jpf:action
	 * @jpf:forward path="Resultado.jsp" name="success"
	 */
	public ActionForward efetivarAssociacao(ActionMapping mapping, ActionForm formParam,HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("PerfilGrupoContatoCRIController:efetivarAssociacao(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");
		PerfilGrupoContatoCRIForm form = (PerfilGrupoContatoCRIForm) formParam;
		StringBuffer str = new StringBuffer(ConstantesCRM.SVAZIO);

		str.append("<operacao>I</operacao>");
		str.append("<contato>"+form.getIdContato()+"</contato>");

		for(int i=0; i<this.perfilGrupoContatoCRIForm.getIdGrupoPerfil().length;i++){
			str.append("<idGrupoPerfil>"+this.perfilGrupoContatoCRIForm.getIdGrupoPerfil()[i]+"</idGrupoPerfil>");
		}

		int complemento = this.perfilGrupoContatoCRIForm.getPerfilVariaveisVO().getPerfilGrupoVOArray().length - this.perfilGrupoContatoCRIForm.getIdGrupoPerfil().length;

		if( complemento > 0 ){
			for(int i=0; i<complemento;i++){
				str.append("<idGrupoPerfil>0</idGrupoPerfil>");
			}
		}

		for(int i=0; i<this.perfilGrupoContatoCRIForm.getPerfilVariaveisVO().getPerfilGrupoVOArray().length;i++){
			str.append("<PerfilGrupoVO>");
			str.append("<idGrupo>"+this.perfilGrupoContatoCRIForm.getPerfilVariaveisVO().getPerfilGrupoVOArray(i).getGrupoVO().getCodigo()+"</idGrupo>");
			str.append("<idPerfil>"+this.perfilGrupoContatoCRIForm.getPerfilVariaveisVO().getPerfilGrupoVOArray(i).getPerfil().getIdPerfil()+"</idPerfil>");
			str.append("</PerfilGrupoVO>");
		}

		user = ConstantesCRM.getCurrentUser(request.getSession());
		WFAcaoRetornoVO wfRetAcao = this.perfilGrupoCRIContatoFacade.setPerfilGrupoConatato(user, str.toString());

		perfilGrupoContatoCRIForm.setMensagem(wfRetAcao.getMensagem());
		perfilGrupoContatoCRIForm.setIdRetorno(wfRetAcao.getAcaoExecucao());
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}


	/**
	 * @jpf:action
	 * @jpf:forward path="perfilGrupoContatoCRI.jsp" name="success"
	 */
	public ActionForward desassociaPerfilGrupo(ActionMapping mapping, ActionForm formParam,HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("PerfilGrupoContatoCRIController:desassociaPerfilGrupo(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");
		PerfilGrupoContatoCRIForm form = (PerfilGrupoContatoCRIForm) formParam;
		int i = (new Integer(""+request.getParameter("idxGrupoPer"))).intValue();

		if( i<this.perfilGrupoContatoCRIForm.getIdGrupoPerfil().length ){
			StringBuffer str = new StringBuffer(ConstantesCRM.SVAZIO);

			str.append("<operacao>D</operacao>");
			str.append("<contato>"+form.getIdContato()+"</contato>");
			str.append("<idGrupoPerfil>"+this.perfilGrupoContatoCRIForm.getIdGrupoPerfil()[i]+"</idGrupoPerfil>");

			str.append("<PerfilGrupoVO>");
			str.append("<idGrupo>"+request.getParameter("grupoDes")+"</idGrupo>");
			str.append("<idPerfil>"+request.getParameter("perfilDes")+"</idPerfil>");
			str.append("</PerfilGrupoVO>");

			user = ConstantesCRM.getCurrentUser(request.getSession());
			WFAcaoRetornoVO wfRetAcao = this.perfilGrupoCRIContatoFacade.setPerfilGrupoConatato(user, str.toString());

			perfilGrupoContatoCRIForm.setMensagem(wfRetAcao.getMensagem());
			perfilGrupoContatoCRIForm.setIdRetorno(wfRetAcao.getAcaoExecucao());
			request.setAttribute("msgRetornoExclusao",wfRetAcao.getMensagem());
		}

		// this.begin(form);

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward path="perfilGrupoContatoCRI.jsp" name="success"
	 */
	public ActionForward associarPerfilGrupo(ActionMapping mapping, ActionForm formParam,HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("PerfilGrupoContatoCRIController:associarPerfilGrupo(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");
		PerfilGrupoContatoCRIForm form = (PerfilGrupoContatoCRIForm) formParam;
		//Esse implementaçao só é possível se a aplicação não sair
		//desse pageFlow

		//Busca grupo e Perfl selecionado nos combo box,
		//seta na tabela de grupo e perfil associados
		PerfilGrupoVO perfilGrupoAux = PerfilGrupoVO.Factory.newInstance();
		GrupoVO grupoAux = GrupoVO.Factory.newInstance();
		for(int i=0; i<this.getPerfilGrupoContatoCRIForm().getPerfilVariaveisVO().getGrupoVOArray().length; i++){
			if(this.getPerfilGrupoContatoCRIForm().getPerfilVariaveisVO().getGrupoVOArray(i).getCodigo()==Integer.parseInt(form.getGrupo())){
				grupoAux = this.getPerfilGrupoContatoCRIForm().getPerfilVariaveisVO().getGrupoVOArray(i);
				perfilGrupoAux.setGrupoVO(grupoAux);
				//this.getPerfilGrupoContatoCRIForm().getPerfilVariaveisVO().removeGrupoVO(i);
				break;
			}
		}

		Perfil perfilAux = Perfil.Factory.newInstance();
		for(int i=0; i<this.getPerfilGrupoContatoCRIForm().getPerfilVariaveisVO().getPerfilArray().length; i++){
			if(Integer.parseInt(this.getPerfilGrupoContatoCRIForm().getPerfilVariaveisVO().getPerfilArray(i).getIdPerfil())==Integer.parseInt(form.getPerfil())){
				perfilAux = this.getPerfilGrupoContatoCRIForm().getPerfilVariaveisVO().getPerfilArray(i);
				perfilGrupoAux.setPerfil(perfilAux);
				this.getPerfilGrupoContatoCRIForm().getPerfilVariaveisVO().removePerfil(i);
				break;
			}
		}

		verificaGrupoIguais();

		this.getPerfilGrupoContatoCRIForm().getPerfilVariaveisVO().addNewPerfilGrupoVO();
		this.getPerfilGrupoContatoCRIForm().getPerfilVariaveisVO().setPerfilGrupoVOArray(this.getPerfilGrupoContatoCRIForm().getPerfilVariaveisVO().getPerfilGrupoVOArray().length-1,perfilGrupoAux);

		this.getPerfilGrupoContatoCRIForm().setPerfilIdx(String.valueOf(this.perfilGrupoContatoCRIForm.getPerfilVariaveisVO().getPerfilGrupoVOArray().length));

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	private void verificaGrupoIguais(){
		this.getPerfilGrupoContatoCRIForm().setInGrupoIguais(ConstantesCRM.SZERO);
		for(int i=0; i<this.getPerfilGrupoContatoCRIForm().getPerfilVariaveisVO().getPerfilGrupoVOArray().length; i++){
			for(int j=(i+1); j<this.getPerfilGrupoContatoCRIForm().getPerfilVariaveisVO().getPerfilGrupoVOArray().length; j++){
				if(this.getPerfilGrupoContatoCRIForm().getPerfilVariaveisVO().getPerfilGrupoVOArray(i).getGrupoVO().getCodigo() == this.getPerfilGrupoContatoCRIForm().getPerfilVariaveisVO().getPerfilGrupoVOArray(j).getGrupoVO().getCodigo()){
					this.getPerfilGrupoContatoCRIForm().setInGrupoIguais(ConstantesCRM.SONE);
					break;
				}
			}
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward path="perfilGrupoContatoCRI.jsp" name="success"
	 */
	public ActionForward moverPerfilGrupo(ActionMapping mapping, ActionForm formParam,HttpServletRequest request, HttpServletResponse response)
	{
		log.debug("PerfilGrupoContatoCRIController:moverPerfilGrupo(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");
		PerfilGrupoContatoCRIForm form = (PerfilGrupoContatoCRIForm) formParam;
		String idxSelecionado = (request.getParameter("perfilLista") != null?request.getParameter("perfilLista"):ConstantesCRM.SVAZIO);

		PerfilGrupoVO[] perfilGrupoVO = this.getPerfilGrupoContatoCRIForm().getPerfilVariaveisVO().getPerfilGrupoVOArray();
		String str[]=new String[this.getPerfilGrupoContatoCRIForm().getPerfilVariaveisVO().getPerfilGrupoVOArray().length];
		for(int i=0;i<this.getPerfilGrupoContatoCRIForm().getPerfilVariaveisVO().getPerfilGrupoVOArray().length;i++){
			str[i] = this.getPerfilGrupoContatoCRIForm().getPerfilVariaveisVO().getPerfilGrupoVOArray(i).getIdGrupoPerfil();
		}

		for(int i=0;i<this.getPerfilGrupoContatoCRIForm().getPerfilVariaveisVO().getPerfilGrupoVOArray().length;i++){

			//up
			if(idxSelecionado.equals(String.valueOf(i)) && form.getInMover().equals(ConstantesCRM.SONE)){
				perfilGrupoVO[i-1] = this.getPerfilGrupoContatoCRIForm().getPerfilVariaveisVO().getPerfilGrupoVOArray(i);
				str[i-1] = perfilGrupoVO[i-1].getIdGrupoPerfil();
				perfilGrupoVO[i] = this.getPerfilGrupoContatoCRIForm().getPerfilVariaveisVO().getPerfilGrupoVOArray(i-1);
				str[i] = perfilGrupoVO[i].getIdGrupoPerfil();
				perfilGrupoContatoCRIForm.setIdGrupoPerfil(str);
				continue;
			}

			//down
			if(idxSelecionado.equals(String.valueOf(i)) && form.getInMover().equals(ConstantesCRM.SZERO)){
				perfilGrupoVO[i+1] = this.getPerfilGrupoContatoCRIForm().getPerfilVariaveisVO().getPerfilGrupoVOArray(i);
				str[i+1] = perfilGrupoVO[i+1].getIdGrupoPerfil();
				perfilGrupoVO[i] = this.getPerfilGrupoContatoCRIForm().getPerfilVariaveisVO().getPerfilGrupoVOArray(i+1);
				str[i] = perfilGrupoVO[i].getIdGrupoPerfil();
				perfilGrupoContatoCRIForm.setIdGrupoPerfil(str);
				++i;
				continue;
			}

			perfilGrupoVO[i] = this.getPerfilGrupoContatoCRIForm().getPerfilVariaveisVO().getPerfilGrupoVOArray(i);

		}


		this.getPerfilGrupoContatoCRIForm().setIdGrupoPerfil(str);
		this.getPerfilGrupoContatoCRIForm().getPerfilVariaveisVO().setPerfilGrupoVOArray(perfilGrupoVO);

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class PerfilGrupoContatoCRIForm extends ActionForm
	{
		private String perfilIdx;

		private String inMover;



		private String strMensagem = ConstantesCRM.SVAZIO;
		public void setMensagem(String strMensagem){
			this.strMensagem = strMensagem;
		}
		public String getMensagem(){
			return this.strMensagem;
		}

		private String idRetorno = ConstantesCRM.SVAZIO;
		public void setIdRetorno(String idRetorno){
			this.idRetorno = idRetorno;
		}
		public String getIdRetorno(){
			return this.idRetorno;
		}

		private String inGrupoIguais = ConstantesCRM.SZERO;
		public void setInGrupoIguais(String inGrupoIguais){
			this.inGrupoIguais = inGrupoIguais;
		}
		public String getInGrupoIguais(){
			return this.inGrupoIguais;
		}

		private String idContato;
		public void setIdContato(String idContato)
		{
			this.idContato = idContato;
		}
		public String getIdContato()
		{
			return this.idContato;
		}

		private String[] idGrupoPerfil;
		public void setIdGrupoPerfil(String[] idGrupoPerfil)
		{
			this.idGrupoPerfil = idGrupoPerfil;
		}
		public String[] getIdGrupoPerfil()
		{
			return this.idGrupoPerfil;
		}

		private String grupo;
		public void setGrupo(String grupo)
		{
			this.grupo = grupo;
		}
		public String getGrupo()
		{
			return this.grupo;
		}

		private String grupoDes;
		public void setGrupoDes(String grupo)
		{
			this.grupoDes = grupo;
		}
		public String getGrupoDes()
		{
			return this.grupoDes;
		}

		private String perfil;
		public void setPerfil(String perfil)
		{
			this.perfil = perfil;
		}
		public String getPerfil()
		{
			return this.perfil;
		}

		private String perfilDes;
		public void setPerfilDes(String perfil)
		{
			this.perfilDes = perfil;
		}
		public String getPerfilDes()
		{
			return this.perfilDes;
		}

		/*
        private br.com.vivo.fo.admsistemas.vo.PerfilDocument.Perfil[] perfilVO;
        public void setPerfilVO(br.com.vivo.fo.admsistemas.vo.PerfilDocument.Perfil[] perfilVO)
        {
            this.perfilVO = perfilVO;
        }
        public br.com.vivo.fo.admsistemas.vo.PerfilDocument.Perfil[] getPerfilVO()
        {
            return this.perfilVO;
        }


        private br.com.vivo.fo.admsistemas.vo.GrupoVODocument.GrupoVO[] grupoVO;
        public void setGrupoVO(br.com.vivo.fo.admsistemas.vo.GrupoVODocument.GrupoVO[] grupoVO)
        {
            this.grupoVO = grupoVO;
        }
        public br.com.vivo.fo.admsistemas.vo.GrupoVODocument.GrupoVO[] getGrupoVO()
        {
            return this.grupoVO;
        }
		 */


		//Perfil Variaveis
		private PerfilVariaveisVO perfilVariaveisVO;
		public PerfilVariaveisVO getPerfilVariaveisVO() {
			return this.perfilVariaveisVO;
		}
		public void setPerfilVariaveisVO(PerfilVariaveisVO perfilVariaveisVO) {
			this.perfilVariaveisVO = perfilVariaveisVO;
		}

		public PerfilGrupoContatoCRIForm() {

		}

		public void setInMover(String inMover)
		{
			this.inMover = inMover;
		}

		public String getInMover()
		{
			if(this.inMover == null) {
				this.inMover = ConstantesCRM.SVAZIO;
			}

			return this.inMover;
		}

		public void setPerfilIdx(String perfilIdx)
		{
			this.perfilIdx = perfilIdx;
		}

		public String getPerfilIdx()
		{
			if(this.perfilIdx == null) {
				this.perfilIdx = ConstantesCRM.SVAZIO;
			}

			return this.perfilIdx;
		}


	}

	// Getter para o Form GruposProcessosForm
	public PerfilGrupoContatoCRIForm getPerfilGrupoContatoCRIForm () {
		return this.perfilGrupoContatoCRIForm;
	}


}
