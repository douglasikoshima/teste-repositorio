package br.com.vivo.catalogoPRS.pageflows.param.importacaoservicofixa.consulta;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;

import br.com.indrasistemas.framework.service.BusinessException;
import br.com.vivo.catalogoPRS.controller.ImportacaoServicoFixaConstants;
import br.com.vivo.catalogoPRS.delegate.ImportacaoServicoFixaDelegate;
import br.com.vivo.catalogoPRS.exception.CatalogoPRSException;
import br.com.vivo.catalogoPRS.pageflows.shared.form.ImportacaoServicoFixaConsultaForm;

import com.indracompany.catalogo.to.ImportacaoServicoFixaTO;
import com.indracompany.catalogo.to.OfertaVLArqItemTO;
import com.indracompany.catalogo.to.OfertaVLFArqItemTO;
import com.indracompany.catalogo.to.SCEncargoGruComArqItemTO;
import com.indracompany.catalogo.to.ServicoArqItemTO;
import com.indracompany.catalogo.to.ServicoServicoArqItemTO;
import com.indracompany.catalogo.to.SolComGrupoComArqItemTO;
import com.indracompany.catalogo.to.StatusArquivoImportacaoTO;

/**
 * @author equipe Catalogo
 *
 */
public class ImportacaoServicoFixaConsultaAction extends MappingDispatchAction {
	
	private static Logger logger = Logger.getLogger(ImportacaoServicoFixaConsultaAction.class);
    private static final String MENSAGEM_ERRO_DATA = "Data inv&aacute;lida ou fora do padr&atilde;o DD/MM/AAAA";
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private ImportacaoServicoFixaConsultaForm importacaoServicoFixaConsultaForm;
    private List<StatusArquivoImportacaoTO> statusArquivoImportacaoTOList;
    private List<ImportacaoServicoFixaTO> resultadoImportacaoList;
    private List<ServicoServicoArqItemTO> resultadoServicoServicoArqItemList;
    private List<SolComGrupoComArqItemTO> resultadoSolComGrupoComArqItemList;
    private List<SCEncargoGruComArqItemTO> resultadoSCEncargoGruComArqItemList;
    private List<SCEncargoGruComArqItemTO> resultadoSCEncargoGCPMACArqItemList;
    private List<OfertaVLArqItemTO> resultadoOfertaVLArqItemList;
    private List<OfertaVLFArqItemTO> resultadoOfertaVLFArqItemList;
    private List<ServicoArqItemTO> resultadoServicoArqItemList;
	

	
	public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {		
		ImportacaoServicoFixaConsultaForm formulario = (ImportacaoServicoFixaConsultaForm)form;
		carregar(formulario, mapping, form, request, response);
		return mapping.findForward("success");
	}

	
	
	public ActionForward pesquisar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws CatalogoPRSException {
		ImportacaoServicoFixaConsultaForm formulario = (ImportacaoServicoFixaConsultaForm)form;
		carregar(formulario, mapping, form, request, response);		
		ImportacaoServicoFixaDelegate delegate = new ImportacaoServicoFixaDelegate();		
		try {
			if (formulario.getIdTipoImportacao() == ImportacaoServicoFixaConstants.RELACIONAMENTO_SERVICO) {
				logger.info("PESQUISANDO ARQUIVO DE RELACIONAMENTO DE SERVICO");
                this.resultadoImportacaoList = delegate.pesquisarServicoServicoArq(doImportacaoServicoFixaTO(formulario));
			}
			else if (formulario.getIdTipoImportacao() == ImportacaoServicoFixaConstants.SC_GC_PM_AC){
				logger.info("PESQUISANDO ARQUIVO DE SC x GC x PM x AC");
                this.resultadoImportacaoList = delegate.pesquisarSolComGrupoComArq(doImportacaoServicoFixaTO(formulario));
			}			
			else if (formulario.getIdTipoImportacao() == ImportacaoServicoFixaConstants.SC_ENC_GC){
				logger.info("PESQUISANDO ARQUIVO DE SC x ENC x PF x GC");
                this.resultadoImportacaoList = delegate.pesquisarSCEncargoGruComArq(doImportacaoServicoFixaTO(formulario));
			}			
			else if (formulario.getIdTipoImportacao() == ImportacaoServicoFixaConstants.SC_ENC_GC_PM_AC){
				logger.info("PESQUISANDO ARQUIVO DE SC x ENC x PF x GC x PM x AC");
                this.resultadoImportacaoList = delegate.pesquisarSCEncargoGCPMACArq(doImportacaoServicoFixaTO(formulario));
			} else if (formulario.getIdTipoImportacao() == ImportacaoServicoFixaConstants.SERVICO){
                this.resultadoImportacaoList = delegate.pesquisarServicoArq(doImportacaoServicoFixaTO(formulario));                
            }else if (formulario.getIdTipoImportacao() == ImportacaoServicoFixaConstants.OFERTA_VENDA_LINHA){
            	logger.info("PESQUISANDO ARQUIVO DE OFERTA VENDA LINHA");
            	this.resultadoImportacaoList = delegate.pesquisarOfertaVLArq(doImportacaoServicoFixaTO(formulario),"OFERTAVENDALINHA");
            }else if (formulario.getIdTipoImportacao() == ImportacaoServicoFixaConstants.OFERTA_COMPLEMENTAR){
            	logger.info("PESQUISANDO ARQUIVO DE OFERTA COMPLEMENTAR");
            	this.resultadoImportacaoList = delegate.pesquisarOfertaVLArq(doImportacaoServicoFixaTO(formulario),"OFERTACOMPLEMENTAR");
            }
			
			request.setAttribute("resultadoImportacaoList", this.resultadoImportacaoList);
            
		} catch (BusinessException e) {
		    request.setAttribute("erro", e.getMessage());			
        } catch (CatalogoPRSException e) {
            request.setAttribute("erro", e.getMessage());            
        }
			
		return mapping.findForward("success");
	}
	
	public ActionForward pesquisarCriticaServicoServicoArq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws CatalogoPRSException{		
		ImportacaoServicoFixaConsultaForm formulario = (ImportacaoServicoFixaConsultaForm)form;
		logger.info("ImportacaoServicoFixaConsultaForm: " + formulario.toString());
		carregar(formulario, mapping, form, request, response);		
		try {
			logger.info("PESQUISANDO CRITICAS DE RELACIONAMENTO DE SERVICO");
			this.resultadoServicoServicoArqItemList = new ImportacaoServicoFixaDelegate().pesquisarCriticaServicoServicoArq(formulario.getIdArquivo());
			request.setAttribute("resultadoServicoServicoArqItemList", resultadoServicoServicoArqItemList);
            request.setAttribute("resultadoImportacaoList", this.resultadoImportacaoList);
            request.setAttribute("file_name", formulario.getNmArquivoConsulta());
            
		} catch (BusinessException e) {
			request.setAttribute("labelError", e.getMessage());			
		}
			
		return mapping.findForward("success");
	}

    public ActionForward pesquisarCriticaServicoArq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws CatalogoPRSException{
        ImportacaoServicoFixaConsultaForm formulario = (ImportacaoServicoFixaConsultaForm)form;
        logger.info("ImportacaoServicoFixaConsultaForm: " + formulario.toString());
        carregar(formulario, mapping, form, request, response);     
        try {
            logger.info("PESQUISANDO CRITICAS DE SERVICO");
            this.resultadoServicoArqItemList = new ImportacaoServicoFixaDelegate().pesquisarCriticaServicoArq(formulario.getIdArquivo());
            request.setAttribute("resultadoServicoArqItemList", resultadoServicoArqItemList);
            request.setAttribute("resultadoImportacaoList", this.resultadoImportacaoList);
            request.setAttribute("file_name", formulario.getNmArquivoConsulta());
            
        } catch (BusinessException e) {
            request.setAttribute("labelError", e.getMessage());            
        }
             
        return mapping.findForward("success");
    }
    
	public ActionForward pesquisarCriticaSolComGrupoComArq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws CatalogoPRSException {		
		ImportacaoServicoFixaConsultaForm formulario = (ImportacaoServicoFixaConsultaForm)form;
		logger.info("ImportacaoServicoFixaConsultaForm: " + formulario.toString());
		carregar(formulario, mapping, form, request, response);		
		ImportacaoServicoFixaDelegate delegate = new ImportacaoServicoFixaDelegate();		
		try {
			logger.info("PESQUISANDO CRITICAS DE SC x GC x PM x AC");
			this.resultadoSolComGrupoComArqItemList = delegate.pesquisarCriticaSolComGrupoComArq(formulario.getIdArquivo());
			request.setAttribute("resultadoSolComGrupoComArqItemList", this.resultadoSolComGrupoComArqItemList);
			request.setAttribute("resultadoImportacaoList", this.resultadoImportacaoList);
			request.setAttribute("file_name", formulario.getNmArquivoConsulta());
		} catch (BusinessException e) {
			request.setAttribute("labelError", e.getMessage());			
		}
		
		return mapping.findForward("success");
	}

	public ActionForward pesquisarCriticaSCEncargoGruComArq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws CatalogoPRSException {		
		ImportacaoServicoFixaConsultaForm formulario = (ImportacaoServicoFixaConsultaForm)form;
		logger.info("ImportacaoServicoFixaConsultaForm: " + form.toString());
		carregar(formulario, mapping, form, request, response);		
		ImportacaoServicoFixaDelegate delegate = new ImportacaoServicoFixaDelegate();		
		try {
			logger.info("PESQUISANDO CRITICAS DE SC x ENC x PF x GC");
			this.resultadoSCEncargoGruComArqItemList = delegate.pesquisarCriticaSCEncargoGruComArq(formulario.getIdArquivo());
			request.setAttribute("resultadoSCEncargoGruComArqItemList", this.resultadoSCEncargoGruComArqItemList);
			request.setAttribute("resultadoImportacaoList", this.resultadoImportacaoList);
			request.setAttribute("file_name", formulario.getNmArquivoConsulta());
		} catch (BusinessException e) {
			request.setAttribute("labelError", e.getMessage());			
		}
		
		return mapping.findForward("success");
	}

	public ActionForward pesquisarCriticaSCEncargoGCPMACArq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws CatalogoPRSException{
		ImportacaoServicoFixaConsultaForm formulario = (ImportacaoServicoFixaConsultaForm)form;
		logger.info("ImportacaoServicoFixaConsultaForm: " + formulario.toString());		
		carregar(formulario, mapping, form, request, response);		
		ImportacaoServicoFixaDelegate delegate = new ImportacaoServicoFixaDelegate();		
		try {
			logger.info("PESQUISANDO CRITICAS DE SC x ENC x PF x GC x PM x AC");
			this.resultadoSCEncargoGCPMACArqItemList = delegate.pesquisarCriticaSCEncargoGCPMACArq(formulario.getIdArquivo());
			request.setAttribute("resultadoSCEncargoGCPMACArqItemList", this.resultadoSCEncargoGCPMACArqItemList);
            request.setAttribute("resultadoImportacaoList", this.resultadoImportacaoList);
            request.setAttribute("file_name", formulario.getNmArquivoConsulta());
		} catch (BusinessException e) {
			request.setAttribute("labelError", e.getMessage());			
		}
		
		return mapping.findForward("success");
	}
	
	public ActionForward pesquisarCriticaOfertaVLArq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws CatalogoPRSException{
		ImportacaoServicoFixaConsultaForm formulario = (ImportacaoServicoFixaConsultaForm)form;
		logger.info("ImportacaoServicoFixaConsultaForm: " + formulario.toString());
		carregar(formulario, mapping, form, request, response);			
		ImportacaoServicoFixaDelegate delegate = new ImportacaoServicoFixaDelegate();		
		try {
			logger.info("PESQUISANDO CRITICAS DE OFERTA VENDA LINHA");
			this.resultadoOfertaVLArqItemList = delegate.pesquisarCriticaOfertaVLArq(formulario.getIdArquivo());
			request.setAttribute("resultadoOfertaVLArqItemList", this.resultadoOfertaVLArqItemList);
            request.setAttribute("resultadoImportacaoList", this.resultadoImportacaoList);
            request.setAttribute("file_name", formulario.getNmArquivoConsulta());
		} catch (BusinessException e) {
			request.setAttribute("labelError", e.getMessage());			
		}
		
		return mapping.findForward("success");
	}
	
	public ActionForward pesquisarCriticaOfertaVLFArq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws CatalogoPRSException{
		ImportacaoServicoFixaConsultaForm formulario = (ImportacaoServicoFixaConsultaForm)form;
		logger.info("ImportacaoServicoFixaConsultaForm: " + formulario.toString());
		carregar(formulario, mapping, form, request, response);		
		ImportacaoServicoFixaDelegate delegate = new ImportacaoServicoFixaDelegate();		
		try {
			logger.info("PESQUISANDO CRITICAS DE OFERTA COMPLEMENTAR");
			this.resultadoOfertaVLFArqItemList = delegate.pesquisarCriticaOfertaVLFArq(formulario.getIdArquivo());
			request.setAttribute("resultadoOfertaVLFArqItemList", this.resultadoOfertaVLFArqItemList);
            request.setAttribute("resultadoImportacaoList", this.resultadoImportacaoList);
            request.setAttribute("file_name", formulario.getNmArquivoConsulta());
		} catch (BusinessException e) {
			request.setAttribute("labelError", e.getMessage());			
		}
		
		return mapping.findForward("success");
	}	

	public ActionForward exportarCritica(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		ImportacaoServicoFixaConsultaForm importacaoServicoFixaConsultaForm = (ImportacaoServicoFixaConsultaForm)form;
	    this.carregar(importacaoServicoFixaConsultaForm, mapping, form, request, response);
	    this.gerarArquivoExportacao(importacaoServicoFixaConsultaForm, request, response);
		return mapping.findForward("success");
	}
    
    private void gerarArquivoExportacao(ImportacaoServicoFixaConsultaForm form, HttpServletRequest request, HttpServletResponse response) {
        StringBuilder sb = new StringBuilder();
        String nomeArquivo = "nomeArquivo";
        if (form.getIdTipoImportacao() == ImportacaoServicoFixaConstants.RELACIONAMENTO_SERVICO) {
            sb.append(ImportacaoServicoFixaConstants.CABECALHO_RELACIONAMENTO_CRITICA_EXPORT);
            for (ServicoServicoArqItemTO to : this.resultadoServicoServicoArqItemList) {
                sb.append(String.format("\n%s;%s;%s;%s;%s", 
                        to.getAcao() == null ? "" : to.getAcao(), 
                        to.getCdServicoMestre() == null ? "" : to.getCdServicoMestre(), 
                        to.getSgTipoRelacionamento() == null ? "" : to.getSgTipoRelacionamento(), 
                        to.getCdServicoSubordinado() == null ? "" : to.getCdServicoSubordinado(), 
                        to.getErros() == null ? "" : to.getErros() 
                        ));
            }
            nomeArquivo = "IMPORTACAO_RELACIONAMENTO";

        } else if (form.getIdTipoImportacao() == ImportacaoServicoFixaConstants.SC_GC_PM_AC) {
            sb.append(ImportacaoServicoFixaConstants.CABECALHO_SC_GC_PM_AC_CRITICA_EXPORT);
            for (SolComGrupoComArqItemTO to : this.resultadoSolComGrupoComArqItemList) {
                //ACAO;CDSOLICITACAOCOMERCIAL;CDGRUPOCOMERCIAL;CDPLANOMINUTOS;CDAREACONCORRENCIA;CRITICA
                sb.append(String.format("\n%s;%s;%s;%s;%s;%s",
                        to.getAcao() == null ? "" : to.getAcao(),
                        to.getCdSolicitacaoComercial() == null ? "" : to.getCdSolicitacaoComercial(),
                        to.getCdGrupoComercial() == null ? "" : to.getCdGrupoComercial(),
                        to.getCdPlanoMinutos() == null ? "" : to.getCdPlanoMinutos(),
                        to.getCdAreaConcorrencia() == null ? "" : to.getCdAreaConcorrencia(),
                        to.getErros() == null ? "" : to.getErros()
                        ));
            }
            nomeArquivo = "IMPORTACAO_SC_GC_PM_AC";

        } else if (form.getIdTipoImportacao() == ImportacaoServicoFixaConstants.SC_ENC_GC) {
            sb.append(ImportacaoServicoFixaConstants.CABECALHO_SC_ENC_GC_CRITICA_EXPORT);
            for (SCEncargoGruComArqItemTO to : this.resultadoSCEncargoGruComArqItemList) {
                //ACAO;CDSOLICITACAOCOMERCIAL;CDENCARGO;CDPLANOFINANCIAMENTO;CDGRUPOCOMERCIAL;CRITICA
                sb.append(String.format("\n%s;%s;%s;%s;%s;%s",
                        to.getAcao() == null ? "" : to.getAcao(),
                        to.getCdSolicitacaoComercial() == null ? "" : to.getCdSolicitacaoComercial(),
                        to.getCdEncargo() == null ? "" : to.getCdEncargo(),
                        to.getCdPlanoFinanciamento() == null ? "" : to.getCdPlanoFinanciamento(),
                        to.getCdGrupoComercial() == null ? "" : to.getCdGrupoComercial(),
                        to.getErros() == null ? "" : to.getErros()
                        ));
            }
            nomeArquivo = "IMPORTACAO_SC_ENC_GC";

        } else if (form.getIdTipoImportacao() == ImportacaoServicoFixaConstants.SC_ENC_GC_PM_AC) {
            sb.append(ImportacaoServicoFixaConstants.CABECALHO_SC_ENC_GC_PM_AC_CRITICA_EXPORT);
            for (SCEncargoGruComArqItemTO to : this.resultadoSCEncargoGCPMACArqItemList) {
                //ACAO;CDSOLICITACAOCOMERCIAL;CDENCARGO;CDPLANOFINANCIAMENTO;CDGRUPOCOMERCIAL;CDPLANOMINUTOS;CDAREACONCORRENCIA;CRITICA
                sb.append(String.format("\n%s;%s;%s;%s;%s;%s;%s;%s",
                        to.getAcao() == null ? "" : to.getAcao(),
                        to.getCdSolicitacaoComercial() == null ? "" : to.getCdSolicitacaoComercial(),
                        to.getCdEncargo() == null ? "" : to.getCdEncargo(),
                        to.getCdPlanoFinanciamento() == null ? "" : to.getCdPlanoFinanciamento(),
                        to.getCdGrupoComercial() == null ? "" : to.getCdGrupoComercial(),
                        to.getCdPlanoMinutos() == null ? "" : to.getCdPlanoMinutos(),
                        to.getCdAreaConcorrencia() == null ? "" : to.getCdAreaConcorrencia(),
                        to.getErros() == null ? "" : to.getErros()
                        ));
            }
            nomeArquivo = "IMPORTACAO_SC_ENC_GC_PM_AC";

        } else if (form.getIdTipoImportacao() == ImportacaoServicoFixaConstants.SERVICO) {
            sb.append(ImportacaoServicoFixaConstants.CABECALHO_SERVICO_EXPORT_CRITICA);
            for (ServicoArqItemTO to : this.resultadoServicoArqItemList) {
                sb.append(String.format(
                        "\n%s;%s;%s;%s",
                        to.getCdServico() != null ? to.getCdServico() : "",
                        to.getNmComercial() != null ? to.getNmComercial() : "",
                        to.getDisponibilidade() != null ? to.getDisponibilidade() : "",
                        to.getErros() != null ? to.getErros() : "" 
                ));
            }
            nomeArquivo = "IMPORTACAO_SERVICO";
            
        } else if (form.getIdTipoImportacao() == ImportacaoServicoFixaConstants.OFERTA_VENDA_LINHA) {
            sb.append(ImportacaoServicoFixaConstants.CABECALHO_SERVICO_EXPORT_OFERTA_VENDA_LINHA_CRITICA);
            for (OfertaVLArqItemTO to : this.resultadoOfertaVLArqItemList) {
                sb.append(String.format(
                        "\n%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s",
                        to.getCdOferta() != null ? to.getCdOferta() : "",
                        to.getNmOferta() != null ? to.getNmOferta() : "",
                        to.getDtInicioVigencia() != null ? to.getDtIncioFormat() : "",
                        to.getDtFimVigencia() != null ? to.getDtFimFormat() : "",
                        to.getCdPsLinha() != null ? to.getCdPsLinha() : "",
                        to.getCdOpcomLinha() != null ? to.getCdOpcomLinha() : "",    
                        to.getCdOpComPreCadastro() != null ? to.getCdOpComPreCadastro() : "",
                        to.getCdPsPlano() != null ? to.getCdPsPlano() : "",                        		
                        to.getCdOpComPlano() != null ? to.getCdOpComPlano() : "",
                        to.getInPortabilidade() != null ? to.getInPortabilidade() : "",
                        to.getInFwt() != null ? to.getInFwt() : "",
                        to.getInBasePontual() != null ? to.getInBasePontual() : "",  
                        to.getInInadimplente() != null ? to.getInInadimplente() : "",
                        to.getInFatb() != null ? to.getInFatb() : "",
                        to.getInProdutoObrigatorio() != null ? to.getInProdutoObrigatorio() : "", 
                        to.getInSpeedySolo() != null ? to.getInSpeedySolo() : "",
                        to.getErros() != null ? to.getErros() : "" 
                ));
            }
            nomeArquivo = "IMPORTACAO_OFERTAVENDALINHA";
            
        } else if (form.getIdTipoImportacao() == ImportacaoServicoFixaConstants.OFERTA_COMPLEMENTAR) {
            sb.append(ImportacaoServicoFixaConstants.CABECALHO_SERVICO_EXPORT_OFERTA_COMPLEMENTAR_CRITICA);
            for (OfertaVLFArqItemTO to : this.resultadoOfertaVLFArqItemList) {
                sb.append(String.format(
                        "\n%s;%s;%s;%s;%s",
                        to.getCdOferta()!= null ? to.getCdOferta() : "",
                        to.getCdPsServico() != null ? to.getCdPsServico() : "",
                        to.getCdOpComServico() != null ? to.getCdOpComServico() : "",
                        to.getPzVigencia() != null ? to.getPzVigencia() : "",
                        to.getErros() != null ? to.getErros() : "" 
                ));
            }
            nomeArquivo = "IMPORTACAO_OFERTACOMPLEMENTAR";
        }
        

        String arquivo = sb.toString();
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", String.format("attachment; filename=%s.csv", nomeArquivo));
        response.setContentLength(arquivo.getBytes().length);
        try {
            OutputStream out = response.getOutputStream();
            out.write(arquivo.getBytes());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	private ImportacaoServicoFixaTO doImportacaoServicoFixaTO(ImportacaoServicoFixaConsultaForm form) throws CatalogoPRSException {
        Date dateInicial = null;
		if(!StringUtils.isBlank(form.getPeriodoInicio())){
			try {
				dateInicial = sdf.parse(form.getPeriodoInicio());
			} catch (Exception e) {
                throw new CatalogoPRSException(MENSAGEM_ERRO_DATA);
			}
		}
        
        Date dateFinal = null;
		if(!StringUtils.isBlank(form.getPeriodoFim())){
			try {
				dateFinal = sdf.parse(form.getPeriodoFim());
                dateFinal.setTime(dateFinal.getTime() + 86400000L);
			} catch (Exception e) {
				throw new CatalogoPRSException(MENSAGEM_ERRO_DATA);
			}
        }
        
        if(dateInicial != null && dateFinal != null){
			if(!(dateInicial.compareTo(dateFinal)  <= 0)){
				throw new CatalogoPRSException(MENSAGEM_ERRO_DATA);
			}
		}
		
		//Prepara TO
		ImportacaoServicoFixaTO to = new ImportacaoServicoFixaTO();
		
		if(form.getIdStatusArquivoImportacao() != null) {			 
			to.setStatusArquivoImportacaoTO(new StatusArquivoImportacaoTO(form.getIdStatusArquivoImportacao(), ""));	
		}
		if(form.getNmArquivo() != null && form.getNmArquivo().length() > 0) {
			to.setNmArquivo(form.getNmArquivo());
		}
		if(form.getUserImportacao() != null && !form.getUserImportacao().equals("")) {
			to.setNmUsuarioImportacao(form.getUserImportacao());		
		}
		if(form.getPeriodoInicio() != null && !"".equals(form.getPeriodoInicio())) {
			to.setDtImportacaoInicial(dateInicial);
		}
		if(form.getPeriodoFim() != null && !"".equals(form.getPeriodoFim())) {
			to.setDtImportacaoFinal(dateFinal);		
		}
		
		return to;
	}  
    
	private void carregar(ImportacaoServicoFixaConsultaForm formulario, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
        if (this.statusArquivoImportacaoTOList == null) {
            this.statusArquivoImportacaoTOList = new ImportacaoServicoFixaDelegate().carregarComboStatusArq();
        }
        request.setAttribute("statusArquivoImportacaoTOList", statusArquivoImportacaoTOList);
	}
	
	public ImportacaoServicoFixaConsultaForm getImportacaoServicoFixaConsultaForm() {
		return importacaoServicoFixaConsultaForm;
	}
	public void setImportacaoServicoFixaConsultaForm(
			ImportacaoServicoFixaConsultaForm importacaoServicoFixaConsultaForm) {
		this.importacaoServicoFixaConsultaForm = importacaoServicoFixaConsultaForm;
	}

    public List<StatusArquivoImportacaoTO> getStatusArquivoImportacaoTOList() {
        return statusArquivoImportacaoTOList;
    }


    public void setStatusArquivoImportacaoTOList(
            List<StatusArquivoImportacaoTO> statusArquivoImportacaoTOList) {
        this.statusArquivoImportacaoTOList = statusArquivoImportacaoTOList;
    }


    public List<ImportacaoServicoFixaTO> getResultadoImportacaoList() {
        return resultadoImportacaoList;
    }


    public void setResultadoImportacaoList(
            List<ImportacaoServicoFixaTO> resultadoImportacaoList) {
        this.resultadoImportacaoList = resultadoImportacaoList;
    }


    public List<ServicoServicoArqItemTO> getResultadoServicoServicoArqItemList() {
        return resultadoServicoServicoArqItemList;
    }


    public void setResultadoServicoServicoArqItemList(
            List<ServicoServicoArqItemTO> resultadoServicoServicoArqItemList) {
        this.resultadoServicoServicoArqItemList = resultadoServicoServicoArqItemList;
    }


    public List<SolComGrupoComArqItemTO> getResultadoSolComGrupoComArqItemList() {
        return resultadoSolComGrupoComArqItemList;
    }


    public void setResultadoSolComGrupoComArqItemList(
            List<SolComGrupoComArqItemTO> resultadoSolComGrupoComArqItemList) {
        this.resultadoSolComGrupoComArqItemList = resultadoSolComGrupoComArqItemList;
    }

	public List<SCEncargoGruComArqItemTO> getResultadoSCEncargoGCPMACArqItemList() {
		return resultadoSCEncargoGCPMACArqItemList;
	}

	public void setResultadoSCEncargoGCPMACArqItemList(
			List<SCEncargoGruComArqItemTO> resultadoSCEncargoGCPMACArqItemList) {
		this.resultadoSCEncargoGCPMACArqItemList = resultadoSCEncargoGCPMACArqItemList;
	}

	public List<SCEncargoGruComArqItemTO> getResultadoSCEncargoGruComArqItemList() {
		return resultadoSCEncargoGruComArqItemList;
	}

	public void setResultadoSCEncargoGruComArqItemList(
			List<SCEncargoGruComArqItemTO> resultadoSCEncargoGruComArqItemList) {
		this.resultadoSCEncargoGruComArqItemList = resultadoSCEncargoGruComArqItemList;
	}


    public List<ServicoArqItemTO> getResultadoServicoArqItemList() {
        return resultadoServicoArqItemList;
    }


    public void setResultadoServicoArqItemList(List<ServicoArqItemTO> resultadoServicoArqItemList) {
        this.resultadoServicoArqItemList = resultadoServicoArqItemList;
    }


	public List<OfertaVLArqItemTO> getResultadoOfertaVLArqItemList() {
		return resultadoOfertaVLArqItemList;
	}


	public void setResultadoOfertaVLArqItemList(
			List<OfertaVLArqItemTO> resultadoOfertaVLArqItemList) {
		this.resultadoOfertaVLArqItemList = resultadoOfertaVLArqItemList;
	}


	public List<OfertaVLFArqItemTO> getResultadoOfertaVLFArqItemList() {
		return resultadoOfertaVLFArqItemList;
	}


	public void setResultadoOfertaVLFArqItemList(
			List<OfertaVLFArqItemTO> resultadoOfertaVLFArqItemList) {
		this.resultadoOfertaVLFArqItemList = resultadoOfertaVLFArqItemList;
	}
    
}