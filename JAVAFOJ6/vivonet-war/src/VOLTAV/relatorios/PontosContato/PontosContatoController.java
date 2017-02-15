package VOLTAV.relatorios.PontosContato;

import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBElement;
import javax.xml.ws.BindingProvider;

import org.apache.axis.utils.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.cliente.vo.ParametroVODocument.ParametroVO;
import br.com.vivo.fo.commons.utils.GetParametro;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.VOLTAV.pontosContato.PontosContatoFacade;
import br.com.vivo.fo.ctrls.VOLTAV.pontosContato.db.RelatorioContatoDB.PontoContato;
import br.com.vivo.fo.log.Logger;

import com.indracompany.actions.AbstractAction;
import com.indracompany.ws.contatoservice.ContatoCorporativoBindingQSService;
import com.indracompany.ws.contatoservice.ContatoCorporativoPortType;
import com.indracompany.ws.contatoservice.ParametrosBuscarListaPontosContato;

public class PontosContatoController extends AbstractAction {

    private static final long  serialVersionUID = 6085486585762070968L;

    @EJB
    public PontosContatoFacade pontosContatoFacade;

    protected global.Global    globalApp        = new global.Global();

    private transient Logger   log              = new Logger("vol");

    private RelatorioForm      relatorioForm;

    public RelatorioForm getRelatorioForm() {
        if (this.relatorioForm == null) {
            this.relatorioForm = new RelatorioForm();
        }
        return this.relatorioForm;
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if ("begin".equals(mapping.getParameter())) {
            return begin(mapping, form, request, response);

        } else if ("done".equals(mapping.getParameter())) {
            return done(mapping, form, request, response);

        } else if ("pesquisar".equals(mapping.getParameter())) {
            return pesquisar(mapping, form, request, response);
        }
        return begin(mapping, form, request, response);
    }

    /**
     * This method represents the point of entry into the pageflow
     * @jpf:action
     * @jpf:forward name="success" path="index.jsp"
     */
    protected ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="done" return-action="SPromocaoDone"
     */
    public ActionForward done(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward("done");
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="tabelaRelatorio.jsp"
     */
    protected ActionForward pesquisar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

        RelatorioForm form = (RelatorioForm) formParam;
        PontoContato[] listaPontoContato;

        log.debug("[PontosContatoController.pesquisar]Inicio pesquisar " + form.getCdCnpjEmpresa());

        try {
            listaPontoContato = consultarPontosContatoWebService(form.getCdCnpjEmpresa(), request);
            if (listaPontoContato == null) {
                listaPontoContato = new PontoContato[1];
                listaPontoContato[0] = new PontoContato();
                getRelatorioForm().setListaPontoContato(listaPontoContato);
                request.setAttribute("msgStatus", "Não existem contatos.");

            } else {
                getRelatorioForm().setListaPontoContato(listaPontoContato);
            }

        } catch (Exception e) {
            log.error("[PontosContatoController.pesquisar] Erro " + e);
        }

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    protected PontoContato[] consultarPontosContatoWebService(String cnpj, HttpServletRequest request) {
        PontoContato[] listaPontoContato = null;

        try {
            log.debug("[PontosContatoController.consultarPontosContatoWebService] INICIO ");

            String user = ConstantesCRM.getCurrentUser(request.getSession());
            GetParametro parametro = GetParametro.getInstace();
            ParametroVO apoioParam = parametro.getParametroVO(user, "URL_CONTATO_CORPORATIVO");

            ContatoCorporativoBindingQSService service = (ContatoCorporativoBindingQSService) new ContatoCorporativoBindingQSService();
            
			//HttpTransportPipe.dump = true;
            com.indracompany.ws.contatoservice.HeaderHandlerResolver handlerResolver = new com.indracompany.ws.contatoservice.HeaderHandlerResolver();
			service.setHandlerResolver(handlerResolver);
			ContatoCorporativoPortType proxy = service.getContatoCorporativoBindingQSPort();
			((BindingProvider)proxy).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, apoioParam.getDsValorParametro());
           
			ParametrosBuscarListaPontosContato parametros = new ParametrosBuscarListaPontosContato();

            parametros.setNumeroDocumentoCliente(cnpj);

            String usuario = ConstantesCRM.SVAZIO;
            String senha = ConstantesCRM.SVAZIO;
            try {
                usuario = br.com.vivo.fo.commons.utils.GetParametro.getInstace().getParametroVO(user, ConstantesCRM.ALSB3SOA_SECURITY_USER).getDsValorParametro();
                senha = br.com.vivo.fo.commons.utils.GetParametro.getInstace().getParametroVO(user, ConstantesCRM.ALSB3SOA_SECURITY_PASSWORD).getDsValorParametro();
            } catch (Exception e) {
            	log.error("PontosContatoController.consultarPontosContatoWebService(" + user + ")Erro ao buscar usuário e senha :", e);
            }
            
	        com.indracompany.ws.contatoservice.Gestores gestores = proxy.buscarListaPontosContato(parametros);
	        log.debug("[PontosContatoController.consultarPontosContatoWebService] Gestores " + gestores.getGestor().size());

            listaPontoContato = new PontoContato[1];
            listaPontoContato[0] = new PontoContato();
            
            for (int i=0; i < gestores.getGestor().size(); i++) {
            	com.indracompany.ws.contatoservice.Gestor gestor = gestores.getGestor().get(i);
            	
            	if ("GerenteSecao".equalsIgnoreCase(gestor.getTipoRelacionamento().getNome().getValue())) {
                    listaPontoContato[0].setDsGerenteSecao(gestor.getNomeCompleto().getValue());
                    JAXBElement<com.indracompany.ws.contatoservice.Contatos> contatos = gestor.getContatos();
                    if (contatos.getValue().getContato().get(0).getTextoContato().length() > 0) {
                        String tel = contatos.getValue().getContato().get(0).getTextoContato();
                        if (!StringUtils.isEmpty(tel)) {
                            listaPontoContato[0].setNrTelefoneGerSecao(tratarTelefone(tel, false));
                        } else {
                        	listaPontoContato[0].setNrTelefoneGerSecao(ConstantesCRM.SVAZIO);
                        }
                    }
                    listaPontoContato[0].setDsEmailGerSecao(contatos.getValue().getContato().get(2).getTextoContato());

                } else if ("GerenteConta".equalsIgnoreCase(gestor.getTipoRelacionamento().getNome().getValue())) {
                    listaPontoContato[0].setNmGerenteContas(gestor.getNomeCompleto().getValue());
                    JAXBElement<com.indracompany.ws.contatoservice.Contatos> contatos = gestor.getContatos();
                    if (contatos.getValue().getContato().get(0).getTextoContato().length() > 0) {
                        String tel = contatos.getValue().getContato().get(0).getTextoContato();
                        if (!StringUtils.isEmpty(tel)) {
                            listaPontoContato[0].setNrTelefoneGerContas(tratarTelefone(tel, false));
                        } else {
                        	listaPontoContato[0].setNrTelefoneGerContas(ConstantesCRM.SVAZIO);
                        }
                    }
                    listaPontoContato[0].setDsEmailGerContas(contatos.getValue().getContato().get(2).getTextoContato());

                } else if ("GerenteDivisao".equalsIgnoreCase(gestor.getTipoRelacionamento().getNome().getValue())) {
                    listaPontoContato[0].setDsGerenteDivisao(gestor.getNomeCompleto().getValue());
                    JAXBElement<com.indracompany.ws.contatoservice.Contatos> contatos = gestor.getContatos();
                    if (contatos.getValue().getContato().get(0).getTextoContato().length() > 0) {
                        String tel = contatos.getValue().getContato().get(0).getTextoContato();
                        if (!StringUtils.isEmpty(tel)) {
                            listaPontoContato[0].setNrTelenoneGerDivisao(tratarTelefone(tel, false));
                        } else {
                        	listaPontoContato[0].setNrTelenoneGerDivisao(ConstantesCRM.SVAZIO);
                        }
                    }
                    listaPontoContato[0].setDsEmailGerDivisao( contatos.getValue().getContato().get(2).getTextoContato());

                } else if ("Parceiro".equalsIgnoreCase(gestor.getTipoRelacionamento().getNome().getValue())) {
                    listaPontoContato[0].setNmDealer(gestor.getNomeCompleto().getValue());
                    JAXBElement<com.indracompany.ws.contatoservice.Contatos> contatos = gestor.getContatos();
                    if (contatos.getValue().getContato().get(0).getTextoContato().length() > 0) {
                        String tel = contatos.getValue().getContato().get(0).getTextoContato();
                        if (!StringUtils.isEmpty(tel)) {
                            listaPontoContato[0].setNrTelef1(tratarTelefone(tel, false));
                        } else {
                        	listaPontoContato[0].setNrTelef1(ConstantesCRM.SVAZIO);
                        }
                    }
                    listaPontoContato[0].setDsEmailDealer(contatos.getValue().getContato().get(2).getTextoContato());
                    listaPontoContato[0].setDsRazaoSocialDealer(gestor.getRazaoSocial().getValue());
                    JAXBElement<com.indracompany.ws.contatoservice.Enderecos> enderecos = gestor.getEnderecos();
                    listaPontoContato[0].setDsCidadeDealer(enderecos.getValue().getEndereco().get(0).getCidade().getValue());

                } else if ("CONSULTOR DE RELACIONAMENTO 1".equalsIgnoreCase(gestor.getTipoRelacionamento().getNome().getValue())) {
                    listaPontoContato[0].setNmConsultor1(gestor.getNomeCompleto().getValue());
                    JAXBElement<com.indracompany.ws.contatoservice.Contatos> contatos = gestor.getContatos();
                    if (contatos.getValue().getContato().get(0).getTextoContato().length() > 0) {
                        String tel1 = contatos.getValue().getContato().get(0).getTextoContato();
                        if (!StringUtils.isEmpty(tel1)) {
                            listaPontoContato[0].setNrTelConsultor1(tratarTelefone(tel1, true));
                        } else {
                        	listaPontoContato[0].setNrTelConsultor1("*8486");
                        }
                    }
                    listaPontoContato[0].setDsEmailConsultor1(contatos.getValue().getContato().get(2).getTextoContato());

                } else if ("CONSULTOR DE RELACIONAMENTO 2".equalsIgnoreCase(gestor.getTipoRelacionamento().getNome().getValue())) {
                    listaPontoContato[0].setNmConsultor2(gestor.getNomeCompleto().getValue());
                    JAXBElement<com.indracompany.ws.contatoservice.Contatos> contatos = gestor.getContatos();
                    if (contatos.getValue().getContato().get(0).getTextoContato().length() > 0) {
                        String tel = contatos.getValue().getContato().get(0).getTextoContato();
                        if (!StringUtils.isEmpty(tel)) {
                            listaPontoContato[0].setNrTelConsultor2(tratarTelefone(tel, true));
                        } else {
                        	listaPontoContato[0].setNrTelConsultor2("*8486");
                        }
                    }
                    listaPontoContato[0].setDsEmailConsultor2(contatos.getValue().getContato().get(2).getTextoContato());

                } else if ("GAM".equalsIgnoreCase(gestor.getTipoRelacionamento().getNome().getValue())) {
                    listaPontoContato[0].setDsGAM(gestor.getNomeCompleto().getValue());
                    JAXBElement<com.indracompany.ws.contatoservice.Contatos> contatos = gestor.getContatos();
                    if (contatos.getValue().getContato().get(0).getTextoContato().length() > 0) {
                        String tel = contatos.getValue().getContato().get(0).getTextoContato();
                        listaPontoContato[0].setNrTelefoneGAM(tratarTelefone(tel, false));
                    } else {
                    	listaPontoContato[0].setNrTelefoneGAM(ConstantesCRM.SVAZIO);
                    }
                    listaPontoContato[0].setDsEmailGAM(contatos.getValue().getContato().get(2).getTextoContato());

                } else if ("Tecnico".equalsIgnoreCase(gestor.getTipoRelacionamento().getNome().getValue())) {
                    listaPontoContato[0].setNmTecnicoResidente(gestor.getNomeCompleto().getValue());
                    JAXBElement<com.indracompany.ws.contatoservice.Contatos> contatos = gestor.getContatos();
                    if (contatos.getValue().getContato().get(0).getTextoContato().length() > 0) {
                        String tel = contatos.getValue().getContato().get(0).getTextoContato();
                        if (!StringUtils.isEmpty(tel)) {
                            listaPontoContato[0].setNrTelefoneTecnico(tratarTelefone(tel, false));
                        } else {
                        	listaPontoContato[0].setNrTelefoneTecnico(ConstantesCRM.SVAZIO);
                        }
                    }
                    listaPontoContato[0].setDsEmailTecnico(contatos.getValue().getContato().get(2).getTextoContato());
                }

            }

        } catch (Exception e) {
            log.error("[PontosContatoController.consultarPontosContatoWebService] Erro " + e);

        } finally {
            log.debug("[PontosContatoController.consultarPontosContatoWebService] FIM ");
        }
        return listaPontoContato;
    }
    
	 private String tratarTelefone(String tel, boolean isConsultor) {
	    	
	    	try {
	    			if (tel.length() >= 10) {
	    				return tel.substring(3,tel.length());
	    			} else if (isConsultor) {
	    				return "*8486";
	    			} else {
	    				return ConstantesCRM.SVAZIO;
	    			}
	    	} catch(Exception e) {
	    		return tel;
	    	}
	    }

    /**
     * This method represents the point of entry into the pageflow
     * @jpf:action
     * @jpf:forward name="success" path="index.jsp"
     */
    protected ActionForward exportar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

        RelatorioForm form = (RelatorioForm) formParam;
        log.debug("[PontosContatoController.pesquisar]Inicio pesquisar " + form.getCdCnpjEmpresa() + " " + form.isExportFilter());

        try {
            PontoContato[] listaPontoContato = null;
            String cdCdnpjEmpresa = form.getCdCnpjEmpresa().replaceAll("[^0-9]", ConstantesCRM.SVAZIO).trim();
            log.debug("[PontosContatoController.pesquisar]cdCdnpjEmpresa " + cdCdnpjEmpresa);
            log.debug("[PontosContatoController.pesquisar]isExportFilter " + form.isExportFilter());

            response.addHeader("Content-Disposition", "attachment; filename=relatorio_pontos_contato_gestor_pj.csv");
            response.addHeader("Content-Type", "application/force-download");
            response.addHeader("Content-Transfer-Encoding", "binary");
            response.addHeader("Pragma", "no-cache");
            response.addHeader("Expires", ConstantesCRM.SZERO);

            PrintWriter out = response.getWriter();
            StringBuffer relatorioStr = new StringBuffer(32768);

            if (form.isExportFilter()) {
                listaPontoContato = pontosContatoFacade.consultarPontosContatoPorCnpj(cdCdnpjEmpresa);
                relatorioStr = gerarBufferRelatorio(listaPontoContato);

            } else {
                int qtdLinhasBloco = 500;
                int inicio = 0;
                int inFim = qtdLinhasBloco;

                // log.debug("[PontosContatoController.pesquisar] inicio " + inicio + " fim " + inFim);
                listaPontoContato = pontosContatoFacade.consultarPontosContatoCompleto(inicio++, inFim);
                // log.debug("[PontosContatoController.pesquisar]listaPontoContato.length " + listaPontoContato.length);
                while (listaPontoContato.length <= qtdLinhasBloco && listaPontoContato.length > 0) {

                    relatorioStr = gerarBufferRelatorio(listaPontoContato);

                    inicio = inFim;
                    inFim = inicio + qtdLinhasBloco;
                    // log.debug("[PontosContatoController.pesquisar]inicio " + inicio++ + " fim " + inFim);
                    listaPontoContato = pontosContatoFacade.consultarPontosContatoCompleto(inicio++, inFim);
                    // log.debug("[PontosContatoController.pesquisar]listaPontoContato " + listaPontoContato.length);
                }
            }
            out.print(relatorioStr);

        } catch (Exception e) {
            log.error("[PontosContatoController.exportar] Erro " + e);
        }
        return null;
    }

    private StringBuffer gerarBufferRelatorio(PontoContato[] listaPontoContato) {
        StringBuffer relatorioStr = new StringBuffer();
        for (int j = 0; j < listaPontoContato.length; j++) {

            PontoContato pontoContato = listaPontoContato[j];
            relatorioStr.append(pontoContato.getIdTipoEmpresa() + "|");
            relatorioStr.append(pontoContato.getCdCnpjEmpresa() + "|");
            relatorioStr.append(pontoContato.getSgUF() + "|");
            relatorioStr.append(pontoContato.getDsConsultorRelacionamento() + "|");
            relatorioStr.append(pontoContato.getNrTelefoneConsultor() + "|");
            relatorioStr.append(pontoContato.getDsEmailConsultor() + "|");
            relatorioStr.append(pontoContato.getNmTecnicoResidente() + "|");
            relatorioStr.append(pontoContato.getNrTelefoneTecnico() + "|");
            relatorioStr.append(pontoContato.getDsEmailTecnico() + "|");
            relatorioStr.append(pontoContato.getNmGerenteContas() + "|");
            relatorioStr.append(pontoContato.getNrTelefoneGerContas() + "|");
            relatorioStr.append(pontoContato.getDsEmailGerContas() + "|");
            relatorioStr.append(pontoContato.getDsGAM() + "|");
            relatorioStr.append(pontoContato.getNrTelefoneGAM() + "|");
            relatorioStr.append(pontoContato.getDsEmailGAM() + "|");
            relatorioStr.append(pontoContato.getDsGerenteSecao() + "|");
            relatorioStr.append(pontoContato.getNrTelefoneGerSecao() + "|");
            relatorioStr.append(pontoContato.getDsEmailGerSecao() + "|");
            relatorioStr.append(pontoContato.getDsGerenteDivisao() + "|");
            relatorioStr.append(pontoContato.getNrTelenoneGerDivisao() + "|");
            relatorioStr.append(pontoContato.getDsEmailGerDivisao() + "|");
            relatorioStr.append(pontoContato.getNmDealer() + "|");
            relatorioStr.append(pontoContato.getDsRazaoSocialDealer() + "|");
            relatorioStr.append(pontoContato.getNrTelef1() + "|");
            relatorioStr.append(pontoContato.getNrTelef2() + "|");
            relatorioStr.append(pontoContato.getDsEmailDealer() + "|");
            relatorioStr.append(pontoContato.getDsCidadeDealer() + "|");
            relatorioStr.append(pontoContato.getNmLoginUsuario() + "|");
            relatorioStr.append(pontoContato.getInAtivaSolicitacaoWEB() + "|");
            relatorioStr.append(pontoContato.getSolicitaLinha() + "|");
            relatorioStr.append("\n");
        }

        return relatorioStr;
    }

    public static class RelatorioForm extends ActionForm {

        private static final long serialVersionUID = -3025504643107244402L;

        private String            cdCnpjEmpresa    = ConstantesCRM.SVAZIO;
        private PontoContato[]    listaPontoContato;
        private String            isExportFilter;

        public void setCdCnpjEmpresa(String cdCnpjEmpresa) {
            this.cdCnpjEmpresa = cdCnpjEmpresa;
        }

        public String getCdCnpjEmpresa() {
            return this.cdCnpjEmpresa;
        }

        public PontoContato[] getListaPontoContato() {
            return this.listaPontoContato;
        }

        public void setListaPontoContato(PontoContato[] listaPontoContato) {
            this.listaPontoContato = listaPontoContato;
        }

        public boolean isExportFilter() {
            return ConstantesCRM.SONE.equals(this.isExportFilter);
        }

        public void setIsExportFilter(String isExportFilter) {
            this.isExportFilter = isExportFilter;
        }

        public String getIsExportFilter() {
            return this.isExportFilter;
        }

    }
}
