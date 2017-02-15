package fidelizacao.Manter.ManterAparelho;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.fidelizacao.manter.AparelhoFacade;
import br.com.vivo.fo.fidelizacao.vo.AparelhoVODocument.AparelhoVO;
import br.com.vivo.fo.fidelizacao.vo.AparelhosManterGetVODocument.AparelhosManterGetVO;
import br.com.vivo.fo.fidelizacao.vo.AparelhosPesquisaInVODocument.AparelhosPesquisaInVO;
import br.com.vivo.fo.fidelizacao.vo.ArrayListaGeralVODocument.ArrayListaGeralVO;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoListaGeralVODocument.FidelizacaoListaGeralVO;
import br.com.vivo.fo.fidelizacao.vo.ItemListaVODocument;
import br.com.vivo.fo.fidelizacao.vo.ItemListaVODocument.ItemListaVO;
import br.com.vivo.fo.fidelizacao.vo.ListaAparelhoVODocument.ListaAparelhoVO;
import br.com.vivo.fo.log.Logger;
import com.indracompany.actions.AbstractAction;

@SuppressWarnings({"rawtypes","unchecked"})
public class ManterAparelhoController extends AbstractAction {

    private static final long serialVersionUID = 402017488177484198L;

    @EJB
    private AparelhoFacade aparelhoFac;

    private static Logger log = new Logger("Fidelizacao");

    protected global.Global globalApp = new global.Global();

    public ShowAparelhoForm showAparelhoForm = new ShowAparelhoForm();

    public ShowAparelhoForm getShowAparelhoForm() {
        return showAparelhoForm;
    }

    private String idUsuario;
    private ArrayListaGeralVO listasVO;
    private FidelizacaoListaGeralVO listaCoresOriginaisVO;
    private AparelhosPesquisaInVO aparelhosPesquisaInVO;
    private java.util.HashMap mapCoresExcluir;
    private java.util.HashMap mapCoresIncluir;

    public FidelizacaoListaGeralVO listaMarcasVO;
    public FidelizacaoListaGeralVO listaCoresVO;
    public FidelizacaoListaGeralVO listaTipoVO;
    public FidelizacaoListaGeralVO listaCoresSelecionadasVO;
    public ListaAparelhoVO listaAparelhosVO;

    private final String XML_NAME_SPACE = "declare namespace vo=\"fidelizacao.fo.vivo.com.br/vo\";";

    public String getIdUsuario() {
        if (idUsuario == null) {
            idUsuario = ConstantesCRM.getCurrentUser(request.getSession());
        }
        return idUsuario;
    }

    public ArrayListaGeralVO getListasVO() throws Exception {
        if (listasVO == null) {
            listasVO = aparelhoFac.getDadosIniciais(getIdUsuario());
        }
        return listasVO;
    }

    public FidelizacaoListaGeralVO getListaMarcasVO() throws Exception {
        if (listaMarcasVO == null) {
            listaMarcasVO = getListasVO().getFidelizacaoListaGeralVOArray(1);
        }
        return listaMarcasVO;
    }

    public FidelizacaoListaGeralVO getListaCoresVO() throws Exception {
        if (listaCoresVO == null) {
            listaCoresVO = getListasVO().getFidelizacaoListaGeralVOArray(0);
        }
        return listaCoresVO;
    }

    public FidelizacaoListaGeralVO getListaTipoVO() throws Exception {
        if (listaTipoVO == null) {
            listaTipoVO = getListasVO().getFidelizacaoListaGeralVOArray(2);
        }
        return listaTipoVO;
    }

    public FidelizacaoListaGeralVO getListaCoresSelecionadasVO() {
        if (this.listaCoresSelecionadasVO == null) {
            this.listaCoresSelecionadasVO = FidelizacaoListaGeralVO.Factory.newInstance();
        }
        return this.listaCoresSelecionadasVO;
    }

    public FidelizacaoListaGeralVO getListaCoresOriginaisVO() {
        if (this.listaCoresOriginaisVO == null) {
            this.listaCoresOriginaisVO = FidelizacaoListaGeralVO.Factory.newInstance();
        }
        return this.listaCoresOriginaisVO;
    }

    public void setListaCoresSelecionadasVO(FidelizacaoListaGeralVO cores) {
        this.listaCoresSelecionadasVO = cores;
    }

    private void limparListaCoresSelecionadasVO() {
        listaCoresSelecionadasVO = null;
    }

    private void limparListaCoresOriginaisVO() {
        listaCoresOriginaisVO = null;
    }

    public ListaAparelhoVO getListaAparelhosVO() {
        if (listaAparelhosVO == null) {
            listaAparelhosVO = ListaAparelhoVO.Factory.newInstance();
        }
        return listaAparelhosVO;
    }

    public AparelhosPesquisaInVO getAparelhosPesquisaInVO() {
        if (this.aparelhosPesquisaInVO == null) {
            this.aparelhosPesquisaInVO = AparelhosPesquisaInVO.Factory.newInstance();
        }
        return this.aparelhosPesquisaInVO;
    }

    public java.util.HashMap getMapCoresExcluir() {
        if (this.mapCoresExcluir == null) {
            this.mapCoresExcluir = new java.util.HashMap();
        }
        return this.mapCoresExcluir;
    }

    public java.util.HashMap getMapCoresIncluir() {
        if (this.mapCoresIncluir == null) {
            this.mapCoresIncluir = new java.util.HashMap();
        }
        return this.mapCoresIncluir;
    }

    private void buscarAparelhos(String nmModelo, String idMarca, String idCor) throws Exception {
        AparelhosManterGetVO aparelhosVO = AparelhosManterGetVO.Factory.newInstance();
        aparelhosVO.setNmModelo(nmModelo);
        aparelhosVO.setIdMarca(idMarca);
        aparelhosVO.setIdCor(idCor);
        getListaAparelhosVO().set(aparelhoFac.getAparelho(getIdUsuario(), aparelhosVO));
        aparelhosVO = null;
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        this.request = request;
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        if ("begin".equals(mapping.getParameter())) {
            return begin(mapping, form, request, response);
        } else if ("listarAparelhos".equals(mapping.getParameter())) {
            return listarAparelhos(mapping, form, request, response);
        } else if ("adicionarCor".equals(mapping.getParameter())) {
            return adicionarCor(mapping, form, request, response);
        } else if ("removerCor".equals(mapping.getParameter())) {
            return removerCor(mapping, form, request, response);
        } else if ("persistirAparelho".equals(mapping.getParameter())) {
            return persistirAparelho(mapping, form, request, response);
        } else if ("limpar".equals(mapping.getParameter())) {
            return limpar(mapping, form, request, response);
        } else if ("done".equals(mapping.getParameter())) {
            return done(mapping, form, request, response);
        } else if ("editarAparelho".equals(mapping.getParameter())) {
            return editarAparelho(mapping, form, request, response);
        } else if ("excluirAparelho".equals(mapping.getParameter())) {
            return excluirAparelho(mapping, form, request, response);
        }
        return begin(mapping, form, request, response);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="aparelho.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
    protected ActionForward listarAparelhos(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        ShowAparelhoForm form = (ShowAparelhoForm) formParam;
        this.request = request;
        try {
            buscarAparelhos(form.getModelo(), form.getIdMarca(), form.getCorSelecionada());

            limparListaCoresOriginaisVO();
            limparListaCoresSelecionadasVO();

        } catch (Exception e) {
            request.setAttribute("msgErro", e.getMessage());
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SUCCESS);

        } finally {
            form.clear();
            form.setModelo(ConstantesCRM.SVAZIO);
            form.setIsEdit(false);
            showAparelhoForm.setIsEdit(false);
            showAparelhoForm.clear();
            showAparelhoForm.setIdMarca(ConstantesCRM.SVAZIO);
            showAparelhoForm.setCorSelecionada(ConstantesCRM.SVAZIO);
            showAparelhoForm.setValorAparelho(ConstantesCRM.SVAZIO);
            showAparelhoForm.setTipoChip(ConstantesCRM.SVAZIO);
            showAparelhoForm.setCodigoSAP(ConstantesCRM.SVAZIO);
            // showAparelhoForm.setInChipAvulso("2");
            // showAparelhoForm.setInChipPreProgramado("2");
        }
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="aparelho.jsp"
     */
    protected ActionForward adicionarCor(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        ShowAparelhoForm form = (ShowAparelhoForm) formParam;

        this.request = request;
        ItemListaVO itemCorSel = this.getListaCoresSelecionadasVO().addNewItemListaVO();
        itemCorSel.setId(request.getParameter("idCor"));
        itemCorSel.setDescricao(request.getParameter("desCor"));
        itemCorSel.setCodigoSAP(request.getParameter("cdSAP"));
        itemCorSel.setValorAparelho(request.getParameter("vlAparelho"));

        tratarCoresIncluir(request.getParameter("idCor"), request.getParameter("desCor"), request.getParameter("cdSAP"), request.getParameter("vlAparelho"));

        showAparelhoForm.setModelo(form.getModelo());
        showAparelhoForm.setIdMarca(form.getIdMarca());
        showAparelhoForm.setCorSelecionada(form.getCorSelecionada());
        showAparelhoForm.setTipoChip(form.getTipoChip());

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="aparelho.jsp"
     */
    protected ActionForward removerCor(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        ShowAparelhoForm form = (ShowAparelhoForm) formParam;
        this.request = request;

        this.getListaCoresSelecionadasVO().removeItemListaVO(new Integer(0).parseInt(request.getParameter("indexCor")));

        tratarCoresExcluir(request.getParameter("idCor"), request.getParameter("desCor"));

        showAparelhoForm.setIdMarca(form.getIdMarca());
        showAparelhoForm.setCorSelecionada(form.getCorSelecionada());

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="aparelho.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     * @jpf:forward name="success2" path="erroMatriz.jsp"
     */
    protected ActionForward persistirAparelho(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        String resposta = ConstantesCRM.SVAZIO;
        ShowAparelhoForm form = (ShowAparelhoForm) formParam;
        this.request = request;
        try {
            this.getAparelhosPesquisaInVO().setIdMarca(form.getIdMarca());
            this.getAparelhosPesquisaInVO().setDsModelo(form.getModelo());

            // this.getAparelhosPesquisaInVO().setDsManualURL(form.getUrlManual());
            /*
             * if(form.getInChipAvulso().equals("2") && form.getInChipPreProgramado().equals("2")){
             * this.getAparelhosPesquisaInVO().setInTipoAparelho("1"); //CDMA
             * }else if(form.getInChipAvulso().equals("1") && form.getInChipPreProgramado().equals("2")){
             * this.getAparelhosPesquisaInVO().setInTipoAparelho("2"); // Chip Avulso
             * }else if(form.getInChipAvulso().equals("2") && form.getInChipPreProgramado().equals("1")){
             * this.getAparelhosPesquisaInVO().setInTipoAparelho("3"); //Chip Pre-Programado }
             */
            this.getAparelhosPesquisaInVO().setInTipoAparelho(form.getTipoChip());

            if (form.getIsEdit()) {
                getAparelhosPesquisaInVO().setIdAparelho(form.getIdAparelho());
            }

            recuperarCoresIncluirExcluir();

            resposta = form.getIsEdit() ? aparelhoFac.editaAparelho(getIdUsuario(), getAparelhosPesquisaInVO()) : aparelhoFac.addNovoAparelho(getIdUsuario(), getAparelhosPesquisaInVO());

            if (resposta.indexOf("Aparelho jah existe") > 0) {
                form.setInMsgRetorno("true");
                request.setAttribute("erro", "erro");
                request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
                return mapping.findForward("success2");
            }

            buscarAparelhos(null, form.getIdMarca(), null);

        } catch (Exception e) {
            // FormError formError = globalApp.buildFormError(e, request);
            request.setAttribute("msgErro", e.getMessage());
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SUCCESS);

        } finally {
            aparelhosPesquisaInVO = null;
            resposta = null;
            form.clear();
            form.setIsEdit(false);
            showAparelhoForm.clear();
            showAparelhoForm.setIsEdit(false);
            showAparelhoForm.setCorSelecionada(ConstantesCRM.SVAZIO);
            showAparelhoForm.setIdMarca(ConstantesCRM.SVAZIO);
            // showAparelhoForm.setInChipAvulso("2");
            // showAparelhoForm.setInChipPreProgramado("2");
            showAparelhoForm.setModelo(ConstantesCRM.SVAZIO);
            showAparelhoForm.setValorAparelho(ConstantesCRM.SVAZIO);
            showAparelhoForm.setTipoChip(ConstantesCRM.SVAZIO);
            showAparelhoForm.setCodigoSAP(ConstantesCRM.SVAZIO);
            limparListaCoresOriginaisVO();
            limparListaCoresSelecionadasVO();
            mapCoresExcluir = null;
            mapCoresIncluir = null;
        }
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    private void tratarCoresExcluir(String idCor, String desCor) {
        this.getMapCoresIncluir().remove(desCor);
        if (this.getMapCoresExcluir().containsValue(desCor) == false) {
            try {
                //log.debug("getListaCoresOriginaisVO():: "+getListaCoresOriginaisVO().xmlText());
                if (pesquisarItemVO(getListaCoresOriginaisVO(), desCor)) {
                    getMapCoresExcluir().put(desCor, idCor);
                }
            } catch (Exception e) {
                log.debug("tratarCoresExcluir", e);
            }
        }
    }

    private void tratarCoresIncluir(String idCor, String desCor, String sap, String valor) {
        this.getMapCoresExcluir().remove(desCor);
        if (this.getMapCoresIncluir().containsValue(desCor) == false) {
            try {
                //log.debug("getListaCoresOriginaisVO():: "+getListaCoresOriginaisVO().xmlText());
                if (!pesquisarItemVO(getListaCoresOriginaisVO(), desCor)) {
                    getMapCoresIncluir().put(desCor, idCor + "CC" + sap + "CC" + valor);
                }
            } catch (Exception e) {
                log.debug("tratarCoresIncluir", e);
            }
        }
    }

    private void recuperarCoresIncluirExcluir() {
        if (!this.getMapCoresIncluir().isEmpty()) {
            java.util.Iterator iCoresIncluir = this.getMapCoresIncluir().values().iterator();
            while (iCoresIncluir.hasNext()) {
                String[] valores = iCoresIncluir.next().toString().split("CC");
                // this.getAparelhosPesquisaInVO().addNewCoresIncluir().setIdIncluir(iCoresIncluir.next().toString());
                this.getAparelhosPesquisaInVO().addNewCoresIncluir().setIdIncluir(valores[0]);
                this.getAparelhosPesquisaInVO().getCoresIncluirArray(this.getAparelhosPesquisaInVO().getCoresIncluirArray().length - 1).setCodigoSAP(valores[1]);
                this.getAparelhosPesquisaInVO().getCoresIncluirArray(this.getAparelhosPesquisaInVO().getCoresIncluirArray().length - 1).setValorAparelho(valores[2]);

            }
        }
        if (!this.getMapCoresExcluir().isEmpty()) {
            java.util.Iterator iCoresExcluir = this.getMapCoresExcluir().values().iterator();
            while (iCoresExcluir.hasNext()) {
                this.getAparelhosPesquisaInVO().addNewCoresExcluir().setIdExcluir(iCoresExcluir.next().toString());
            }
        }
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="aparelho.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
    protected ActionForward limpar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        ShowAparelhoForm form = (ShowAparelhoForm) formParam;
        this.request = request;
        try {
            limpar(formParam);
        } catch (Exception e) {
            // FormError formError = globalApp.buildFormError(e, request);
            request.setAttribute("msgErro", e.getMessage());
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SUCCESS);
        }
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="aparelho.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
    protected ActionForward editarAparelho(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        int index = 0;
        log.debug("[ManterAparelhoController.editarAparelho] Begin ");
        ShowAparelhoForm form = (ShowAparelhoForm) formParam;
        this.request = request;
        try {
            // String inTipoAparelho = "";
            index = (request.getParameter("index") == null) ? 0 : Integer.parseInt(request.getParameter("index"));

            AparelhoVO aparelhoVO = getListaAparelhosVO().getAparelhoVOArray(index);

            showAparelhoForm.setIdMarca(aparelhoVO.getIdMarca());
            showAparelhoForm.setModelo((aparelhoVO.getModelo()));
            showAparelhoForm.setIdAparelho(aparelhoVO.getIdAparelho());
            showAparelhoForm.setTipoChip(aparelhoVO.getInTipoAparelho());
            showAparelhoForm.setIsEdit(true);

            this.limparListaCoresOriginaisVO();
            this.limparListaCoresSelecionadasVO();

            for (int c = 0; c < aparelhoVO.getCorVOArray().length; c++) {
                ItemListaVO itemCorOrig = getListaCoresOriginaisVO().addNewItemListaVO();
                itemCorOrig.setId(aparelhoVO.getCorVOArray(c).getIdAparelhoCor());
                itemCorOrig.setDescricao(aparelhoVO.getCorVOArray(c).getNmCor());
                itemCorOrig.setCodigoSAP(aparelhoVO.getCorVOArray(c).getCodigoSAP());
                itemCorOrig.setValorAparelho(aparelhoVO.getCorVOArray(c).getValorAparelho());
                

                ItemListaVO itemCorSel = this.getListaCoresSelecionadasVO().addNewItemListaVO();
                itemCorSel.setId(aparelhoVO.getCorVOArray(c).getIdAparelhoCor());
                itemCorSel.setDescricao(aparelhoVO.getCorVOArray(c).getNmCor());
                itemCorSel.setCodigoSAP(aparelhoVO.getCorVOArray(c).getCodigoSAP());
                itemCorSel.setValorAparelho(aparelhoVO.getCorVOArray(c).getValorAparelho());
                
            }

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
     * @jpf:forward name="success" path="aparelho.jsp"
     * @jpf:forward name="success2" path="erroMatriz.jsp"
     * @jpf:forward name="error" path="erroMatriz.jsp"
     */
    protected ActionForward excluirAparelho(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        ShowAparelhoForm form = (ShowAparelhoForm) formParam;
        this.request = request;
        try {
            String idAparelho = request.getParameter("idAparelho");
            String resposta = aparelhoFac.dellAparelho(getIdUsuario(), idAparelho);
            if (resposta.indexOf("Existe Matriz Associada!") > 0) {
                form.setInMsgRetorno("true");
                request.setAttribute("erro", "erroMatriz");
                request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
                return mapping.findForward("success2");
            }
            buscarAparelhos(form.getModelo(), form.getIdMarca(), form.getCorSelecionada());

        } catch (Exception e) {
            request.setAttribute("msgErro", e.getMessage());
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SUCCESS);

        } finally {
            form.setModelo(ConstantesCRM.SVAZIO);
            // form.setUrlManual("");
            form.setIsEdit(false);
            this.limparListaCoresOriginaisVO();
            this.limparListaCoresSelecionadasVO();
        }
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**************************************************************************
     * Fim
     */

    /**
     * @jpf:action
     * @jpf:forward name="success" path="aparelho.jsp"
     */
    protected ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("[ManterAparelhoController.begin] showAparelhoForm " + showAparelhoForm);
        limpar(showAparelhoForm);
        this.request = request;
        showAparelhoForm.setIsEdit(false);
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="done" return-action="ManterAparelhoDone"
     */
    public ActionForward done(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        this.request = request;
        return null;
    }

    private void limpar(ActionForm formParam) throws Exception {

        ShowAparelhoForm form = (ShowAparelhoForm) formParam;
        if (form == null ) {
            return;
        }

        form.setModelo(ConstantesCRM.SVAZIO);
        // form.setUrlManual("");
        form.setIdAparelho(ConstantesCRM.SVAZIO);
        form.setIsEdit(false);
        form.setInChipAvulso(ConstantesCRM.SVAZIO);
        form.setInChipPreProgramado(ConstantesCRM.SVAZIO);
        form.setCodigoSAP(ConstantesCRM.SVAZIO);
        form.setTipoChip(ConstantesCRM.SVAZIO);
        form.setValorAparelho(ConstantesCRM.SVAZIO);
        showAparelhoForm.clear();
        showAparelhoForm.setTipoChip(ConstantesCRM.SVAZIO);
        showAparelhoForm.setCorSelecionada(ConstantesCRM.SVAZIO);
        showAparelhoForm.setIdMarca(ConstantesCRM.SVAZIO);
        showAparelhoForm.setInChipAvulso(ConstantesCRM.STWO);
        showAparelhoForm.setInChipPreProgramado(ConstantesCRM.STWO);
        showAparelhoForm.setValorAparelho(ConstantesCRM.SVAZIO);
        showAparelhoForm.setCodigoSAP(ConstantesCRM.SVAZIO);
        showAparelhoForm.setIsEdit(false);
        showAparelhoForm.setModelo(ConstantesCRM.SVAZIO);
        this.limparListaCoresOriginaisVO();
        this.limparListaCoresSelecionadasVO();
        this.getAparelhosPesquisaInVO().setInTipoAparelho(ConstantesCRM.SVAZIO);
        /*
        try {
            buscarAparelhos(null, null, null);
        } catch (Exception e) {
            log.error("[ManterAparelhocontroller.limpar] Erro " + e);
            throw e;
        }*/
    }
    
    private boolean pesquisarItemVO(FidelizacaoListaGeralVO listaVO, String itemProcurado) {
    	
    	boolean temItem = false;
    	try {
    			
    			if (listaVO.sizeOfItemListaVOArray() > 0 ) {
    				for (int i =0; i < listaVO.getItemListaVOArray().length; i++){
    					ItemListaVO itemVO = (ItemListaVO) listaVO.getItemListaVOArray()[i];
    					log.debug("[ManterAparelhoController.pesquisarItemVO] Item [" + i + "] " + itemVO.getDescricao() + " = " + itemProcurado);
    					if (itemProcurado.equalsIgnoreCase(itemVO.getDescricao())) {
    						return true;
    					}
    				}
    			}
    	} catch (Exception e) {
    		e.printStackTrace();
    		log.error("[ManterAparelhoController.pesquisarItemVO] Erro " + e);
    	}
    	
    	return temItem;
    }

    public static class ShowAparelhoForm extends ActionForm {

        private static final long serialVersionUID = 4266231604448811539L;

        private String valorAparelho;
        private String codigoSAP;
        private String tipoChip;
        private String inChipPreProgramado;
        private String inChipAvulso;
        private String idAcao;
        private ItemListaVO[] marcaArray;
        private ItemListaVODocument marcaId;
        private ItemListaVO[] coresArray;
        private ItemListaVODocument coresId2;
        private String inMsgRetorno;
        private String modelo;
        private String corSelecionada;
        private String urlManual;
        private String marca;
        private String idMarca;
        private String idAparelho;
        private boolean isEdit;

        public void setIsEdit(boolean isEdit) {
            this.isEdit = isEdit;
        }

        public boolean getIsEdit() {
            return this.isEdit;
        }

        public void clear() {
            this.modelo = ConstantesCRM.SVAZIO;
            this.corSelecionada = ConstantesCRM.SVAZIO;
            this.urlManual = ConstantesCRM.SVAZIO;
            this.marca = ConstantesCRM.SVAZIO;
            this.idAparelho = ConstantesCRM.SVAZIO;
            this.idMarca = ConstantesCRM.SVAZIO;
            this.inChipAvulso = ConstantesCRM.SVAZIO;
            this.inChipPreProgramado = ConstantesCRM.SVAZIO;
        }

        public void setIdAparelho(String idAparelho) {
            this.idAparelho = idAparelho;
        }

        public String getIdAparelho() {
            return this.idAparelho;
        }

        public void setIdMarca(String idMarca) {
            this.idMarca = idMarca;
        }

        public String getIdMarca() {
            return this.idMarca;
        }

        public void setMarca(String marca) {
            this.marca = marca;
        }

        public String getMarca() {
            return this.marca;
        }

        public void setUrlManual(String urlManual) {
            this.urlManual = urlManual;
        }

        public String getUrlManual() {
            return this.urlManual;
        }

        public void setCorSelecionada(String corSelecionada) {
            this.corSelecionada = corSelecionada;
        }

        public String getCorSelecionada() {
            return this.corSelecionada;
        }

        public void setModelo(String modelo) {
            this.modelo = modelo;
        }

        public String getModelo() {
            return this.modelo;
        }

        public void setInMsgRetorno(String inMsgRetorno) {
            this.inMsgRetorno = inMsgRetorno;
        }

        public String getInMsgRetorno() {
            return this.inMsgRetorno;
        }

        public void setCoresId(ItemListaVODocument coresId2) {
            this.coresId2 = coresId2;
        }

        public ItemListaVODocument getCoresId() {
            return this.coresId2;
        }

        public void setCoresArray(br.com.vivo.fo.fidelizacao.vo.ItemListaVODocument.ItemListaVO[] coresArray) {
            this.coresArray = coresArray;
        }

        public br.com.vivo.fo.fidelizacao.vo.ItemListaVODocument.ItemListaVO[] getCoresArray() {
            return this.coresArray;
        }

        public void setMarcaId(ItemListaVODocument marcaId) {
            this.marcaId = marcaId;
        }

        public ItemListaVODocument getMarcaId() {
            return this.marcaId;
        }

        public void setMarcaArray(br.com.vivo.fo.fidelizacao.vo.ItemListaVODocument.ItemListaVO[] marcaArray) {
            this.marcaArray = marcaArray;
        }

        public br.com.vivo.fo.fidelizacao.vo.ItemListaVODocument.ItemListaVO[] getMarcaArray() {
            return this.marcaArray;
        }

        public void setIdAcao(String idAcao) {
            this.idAcao = idAcao;
        }

        public String getIdAcao() {
            return this.idAcao;
        }

        public void setInChipAvulso(String inChipAvulso) {
            this.inChipAvulso = inChipAvulso;
        }

        public String getInChipAvulso() {
            return this.inChipAvulso;
        }

        public void setInChipPreProgramado(String inChipPreProgramado) {
            this.inChipPreProgramado = inChipPreProgramado;
        }

        public String getInChipPreProgramado() {
            return this.inChipPreProgramado;
        }

        public void setTipoChip(String tipoChip) {
            this.tipoChip = tipoChip;
        }

        public String getTipoChip() {
            return this.tipoChip;
        }

        public void setCodigoSAP(String codigoSAP) {
            this.codigoSAP = codigoSAP;
        }

        public String getCodigoSAP() {
            return this.codigoSAP;
        }

        public void setValorAparelho(String valorAparelho) {
            this.valorAparelho = valorAparelho;
        }

        public String getValorAparelho() {
            return this.valorAparelho;
        }

        public String toString() {
            return "is Edit: " + isEdit;
        }
    }
}