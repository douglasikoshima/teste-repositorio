package br.com.indrasistemas.vivoservices.listapup.application;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.SessionContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import br.com.indrasistemas.framework.service.BusinessException;
import br.com.indrasistemas.framework.service.application.ApplicationServiceException;
import br.com.indrasistemas.framework.service.application.BaseApplicationService;
import br.com.indrasistemas.framework.service.dao.exceptions.DAOException;
import br.com.indrasistemas.framework.service.to.RequestInfoTO;
import br.com.indrasistemas.vivoservices.listapup.dao.ListaPUPDAO;
import br.com.indrasistemas.vivoservices.listapup.exception.LinhaInexistenteException;
import br.com.indrasistemas.vivoservices.listapup.exception.NumeroTelefonicoInvalidoException;
import br.com.indrasistemas.vivoservices.listapup.exception.OperacaoNaoPermitidaException;
import br.com.indrasistemas.vivoservices.listapup.exception.ParametroInvalidoException;
import br.com.indrasistemas.vivoservices.listapup.to.LinhaPUPWSTO;
import br.com.indrasistemas.vivoservices.listapup.to.ParametrosLinhaTO;
import br.com.indrasistemas.vivoservices.listapup.to.PermissaoTO;
import br.com.indrasistemas.vivoservices.listapup.tuxgateway.RegistrarPalitagemGateway;
import br.com.indrasistemas.vivoservices.listapup.webservice.to.ResultadoLinhaPUPTO;
import br.com.indrasistemas.vivoservices.webservice.to.RespostaWSTO;

public class ListaPUPAS extends BaseApplicationService {

    private static final Log logger = LogFactory.getLog(ListaPUPAS.class);

    public ListaPUPAS(SessionContext sessionContext) {
        super(sessionContext);
    }

    @SuppressWarnings("unused")
	public ResultadoLinhaPUPTO gravarLinhaPUP(RequestInfoTO requestInfo, LinhaPUPWSTO filtro) throws ApplicationServiceException, BusinessException {

        if (logger.isInfoEnabled()) {
            logger.info("gravarLinhaPUP - Método iniciado.");
        }

        ResultadoLinhaPUPTO resultDAO = null;
        boolean novoCadastroPlataforma = false;

        validarParametros(requestInfo, filtro);

        ListaPUPDAO listaPUPDAO = new ListaPUPDAO();

        List linhasAssociadas = new ArrayList();
        Long[] linhas = new Long[filtro.getNrMIN().size()];
        for (int i = 0; i < filtro.getNrMIN().size(); i++) {
            linhas[i] = (Long) filtro.getNrMIN().get(i);
        }

        boolean inDadosPrimeiraLinha = false;
        boolean inNmPathsPresentes = true;
        List permissoes = new ArrayList();

        try {

            permissoes = listaPUPDAO.consultarFolhasContatos();

            permissoes = atualizarPermissoes(permissoes, "SMSPROT", (filtro.getInSMSProtocolos() == null) ? null : (filtro.getInSMSProtocolos().intValue() == 1) ? new Boolean(true) : new Boolean(false));
            permissoes = atualizarPermissoes(permissoes, "SMSPROM", (filtro.getInSMSPromocoes() == null) ? null : (filtro.getInSMSPromocoes().intValue() == 1) ? new Boolean(true) : new Boolean(false));
            permissoes = atualizarPermissoes(permissoes, "SMSPROD", (filtro.getInSMSProdutos() == null) ? null : (filtro.getInSMSProdutos().intValue() == 1) ? new Boolean(true) : new Boolean(false));
            permissoes = atualizarPermissoes(permissoes, "SMSPARC", (filtro.getInSMSParceiros() == null) ? null : (filtro.getInSMSParceiros().intValue() == 1) ? new Boolean(true) : new Boolean(false));
            permissoes = atualizarPermissoes(permissoes, "IVRPROM", (filtro.getInIVRPromocoes() == null) ? null : (filtro.getInIVRPromocoes().intValue() == 1) ? new Boolean(true) : new Boolean(false));
            permissoes = atualizarPermissoes(permissoes, "IVRPROD", (filtro.getInIVRProdutos() == null) ? null : (filtro.getInIVRProdutos().intValue() == 1) ? new Boolean(true) : new Boolean(false));
            permissoes = atualizarPermissoes(permissoes, "IVRPARC", (filtro.getInIVRParceiros() == null) ? null : (filtro.getInIVRParceiros().intValue() == 1) ? new Boolean(true) : new Boolean(false));
            permissoes = atualizarPermissoes(permissoes, "BLKTEAT", (filtro.getInTelmktMsgVoz() == null) ? null : (filtro.getInTelmktMsgVoz().intValue() == 1) ? new Boolean(true) : new Boolean(false));

            if (logger.isDebugEnabled()) {
                logger.debug("Lista de permissões gerada.");
            }

        } catch (DAOException e) {
            inNmPathsPresentes = false;
        }

        int qtdeLinhasGravadas = 0;
        for (int i = 0; i < linhas.length; i++) {

            novoCadastroPlataforma = false;

            if (linhas[i] != null && (linhas[i].toString().length() == 10 || linhas[i].toString().length() == 11)) {

                try {

                    listaPUPDAO = new ListaPUPDAO();

                    filtro.setCdDDD(new Integer(linhas[i].toString().substring(0, 2)));
                    filtro.setNrTelefone(new Integer(linhas[i].toString().substring(2)));

                    String nmSistemaOrigem = listaPUPDAO.consultarSistemaOrigem(new Long(filtro.getCdDDD().toString() + filtro.getNrTelefone().toString()));

                    if (("ATLYS".equals(requestInfo.getSystemModule()) || "NGIN".equals(requestInfo.getSystemModule())) && !requestInfo.getSystemModule().equals(nmSistemaOrigem)) {
                        getSessionContext().setRollbackOnly();
                        throw new OperacaoNaoPermitidaException(LinhaPUPWSTO.CD_OPERACAO_NAO_PERMITIDA + "|Operação não permitida para este sistema origem.");
                    }

                    if (logger.isDebugEnabled()) {
                        logger.debug("Tratamento de linha: " + linhas[i].toString());
                    }

                    // Exclusão da linha da Plataforma Unificada de Permissões.
                    if (filtro.getCdOperacao().intValue() == 2) {
                        if (logger.isDebugEnabled()) {
                            logger.debug("Exclusão de linha da PUP: " + linhas[i].toString());
                        }
                        listaPUPDAO.excluirLinhaPUP(filtro.getCdDDD(), filtro.getNrTelefone());
                    
                    } else {
                        // Troca de Titularidade - Linha será excluída da PUP para inserção de novo cadastro.
                        if (filtro.getCdOperacao().intValue() == 1) {

                            // Troca de Titularidade deve possuir ao menos
                            // um tipo de bloqueio informado.
                            if (filtro.getInSMSProtocolos() == null && filtro.getInSMSPromocoes() == null && filtro.getInSMSProdutos() == null && filtro.getInSMSParceiros() == null
                                    && filtro.getInIVRPromocoes() == null && filtro.getInIVRProdutos() == null && filtro.getInIVRParceiros() == null && filtro.getInTelmktMsgVoz() == null) {
                                throw new ParametroInvalidoException(LinhaPUPWSTO.CD_PARAMETROS_INVALIDOS + "|Ao menos um tipo de bloqueio deve ser informado. "
                                        + "Para exclusão da linha da Plataforma Unificada de Permissões, " + "utilize o cdOperacao 2.");
                            }

                            if (logger.isDebugEnabled()) {
                                logger.debug("Troca de titularidade de linha: " + linhas[i].toString());
                            }

                            listaPUPDAO.excluirLinhaPUP(filtro.getCdDDD(), filtro.getNrTelefone());

                            novoCadastroPlataforma = true;

                        } else {
                            resultDAO = listaPUPDAO.consultarLinhaPUP(filtro.getCdDDD(), filtro.getNrTelefone(), false, null, "GRAVACAO");
                            if (resultDAO.getLinhaPUPWSTO() == null) novoCadastroPlataforma = true;
                        }

                        for (int j = 0; j < permissoes.size(); j++) {

                            PermissaoTO permissao = (PermissaoTO) permissoes.get(j);

                            // Verifica se o tipo de permissão já está cadastrado.
                            resultDAO = listaPUPDAO.consultarLinhaPUP(filtro.getCdDDD(), filtro.getNrTelefone(), false, permissao.getCdPermissao(), "CONSULTA");

                            // Se não estiver cadastrado, insere nova permissão.
                            if (resultDAO.getLinhaPUPWSTO() == null) {

                                // Cadastro é feito somente se a permissão foi enviada pelo solicitante.
                                if (permissao.getInAceitacao() != null) {
                                    resultDAO = listaPUPDAO.inserirLinhaPUP(requestInfo, filtro.getCdDDD(), filtro.getNrTelefone(), permissao.getCdPermissao(), (new Boolean(permissao.getInAceitacao()
                                            .intValue() == 1 ? true : false).booleanValue()));
                                    qtdeLinhasGravadas++;
                                }
                            } else {

                                // Alteração é feita somente se a permissão foi enviada pelo solicitante.
                                if (permissao.getInAceitacao() != null) {
                                    resultDAO = listaPUPDAO.alterarLinhaPUP(requestInfo, filtro.getCdDDD(), filtro.getNrTelefone(), permissao.getCdPermissao(), (new Boolean(permissao.getInAceitacao()
                                            .intValue() == 1 ? true : false).booleanValue()));
                                    qtdeLinhasGravadas++;
                                }
                            }
                        }

                        if (novoCadastroPlataforma) {
                            boolean isWithinLinhaPUP = listaPUPDAO.verificaLinhaPUP(requestInfo, filtro.getCdDDD(), filtro.getNrTelefone());
                            if (!isWithinLinhaPUP) {
                                listaPUPDAO.insertLinhaTelefonica(requestInfo, filtro.getCdDDD(), filtro.getNrTelefone());
                            }
                        }

                    }

                } catch (DAOException ex) {
                    getSessionContext().setRollbackOnly();
                    throw new ApplicationServiceException(LinhaPUPWSTO.CD_ERRO_BASE + "|Erro de acesso à base de dados.");
                }
            }
        }

        resultDAO = new ResultadoLinhaPUPTO();
        resultDAO.setStatus(RespostaWSTO.OK);

        if (logger.isInfoEnabled()) {
            logger.info("gravarLinhaPUP - Método finalizado.");
        }

        return resultDAO;
    }

    public ResultadoLinhaPUPTO consultarLinhaPUP(RequestInfoTO requestInfo, LinhaPUPWSTO filtro) throws ApplicationServiceException, BusinessException {

        ResultadoLinhaPUPTO resultDAO = null;
        validarParametros(requestInfo, filtro);

        try {

            ListaPUPDAO listaPUPDAO = new ListaPUPDAO();

            // Tratamento para que apenas sejam trazidas as descrições das permissões.
            if (filtro.getCdDDD().intValue() == 99 && filtro.getNrTelefone().intValue() == 99999999
                    && ("VIVONET".equals(requestInfo.getSystemModule()) || "VOL".equals(requestInfo.getSystemModule()) || "TAV".equals(requestInfo.getSystemModule()))) {
                resultDAO = listaPUPDAO.consultarDescricoesPermissoesPUP();
                return resultDAO;
            }

            List permissoes = listaPUPDAO.consultarFolhasContatos();

            /*
             * OS 1016 - Agora PUP também atende linhas de não clientes, ou
             * seja, linhas não presentes na base do Vivonet. ParametrosLinhaTO
             * parametrosLinha = listaPUPDAO .buscarParametrosLinha(filtro);
             * 
             * if (parametrosLinha == null) { throw new
             * LinhaInexistenteException(
             * LinhaPUPWSTO.CD_LINHA_NAO_CADASTRADA_VIVONET + "|Linha não
             * cadastrada no Vivonet."); }
             */

            ParametrosLinhaTO parametrosLinha = new ParametrosLinhaTO();

            // Se a chamada não for proveniente de Vivonet, VOL, TAV ou URA, serão buscadas informações a partir das siglas de canal e procedência enviados.
            if (!"VIVONET".equals(requestInfo.getSystemModule()) && !"VOL".equals(requestInfo.getSystemModule()) && !"TAV".equals(requestInfo.getSystemModule())
                    && !"URA".equals(requestInfo.getSystemModule())) {

                ParametrosLinhaTO codigosDePara = consultarIDUsuarioCanalProcedencia(requestInfo);
                parametrosLinha.setIdCanal(codigosDePara.getIdCanal());
                parametrosLinha.setIdProcedencia(codigosDePara.getIdProcedencia());
                parametrosLinha.setIdUser(codigosDePara.getIdUser());

                requestInfo.setUserId(new Long(codigosDePara.getIdUser().toString()));

            } else {
                parametrosLinha.setIdCanal(filtro.getCdCanal());
                parametrosLinha.setIdProcedencia(filtro.getCdProcedencia());
            }

            boolean inDescricao = false;
            if ("VIVONET".equals(requestInfo.getSystemModule().toUpperCase()) || "VOLE".equals(requestInfo.getSystemModule().toUpperCase())
                    || "VOL".equals(requestInfo.getSystemModule().toUpperCase()) || "TAV".equals(requestInfo.getSystemModule().toUpperCase())) {
                inDescricao = true;
            }

            resultDAO = listaPUPDAO.consultarLinhaPUP(filtro.getCdDDD(), filtro.getNrTelefone(), inDescricao, null, "CONSULTA");

            if ((!inDescricao && resultDAO.getLinhaPUPWSTO() == null)
                    || (!inDescricao && resultDAO.getLinhaPUPWSTO().getInSMSParceiros() == null && resultDAO.getLinhaPUPWSTO().getInSMSProdutos() == null
                            && resultDAO.getLinhaPUPWSTO().getInSMSPromocoes() == null && resultDAO.getLinhaPUPWSTO().getInSMSProtocolos() == null
                            && resultDAO.getLinhaPUPWSTO().getInIVRParceiros() == null && resultDAO.getLinhaPUPWSTO().getInIVRProdutos() == null
                            && resultDAO.getLinhaPUPWSTO().getInTelmktMsgVoz() == null && resultDAO.getLinhaPUPWSTO().getInIVRPromocoes() == null)) {

                throw new LinhaInexistenteException(LinhaPUPWSTO.CD_LINHA_NAO_CADASTRADA_PUP + "|Linha não cadastrada na Plataforma Unificada de Permissões.");
            }

            if ("VIVONET".equals(requestInfo.getSystemModule().toUpperCase()) && resultDAO.getLinhaPUPWSTO() != null) {
                boolean existeBloqueioProcon = listaPUPDAO.existeBloqueioProcon(filtro.getCdDDD(), filtro.getNrTelefone());
                if (existeBloqueioProcon) {
                    resultDAO.getLinhaPUPWSTO().setSgProcedencia("BLOQUEIO_PROCON");
                }
            }

            if (inDescricao) {
                String[] dtCadastroUltimaAlteracaoPUP = listaPUPDAO.buscarDataCadastroAlteracaoPUP(filtro.getCdDDD(), filtro.getNrTelefone());
                resultDAO.getLinhaPUPWSTO().setDtInclusao(dtCadastroUltimaAlteracaoPUP[0]);
                resultDAO.getLinhaPUPWSTO().setDtUltimaAlteracao(dtCadastroUltimaAlteracaoPUP[1]);
            }

            // Registro de palitagem
            String nmPath = "";
            if ("VOL".equals(requestInfo.getSystemModule().toUpperCase()) || "TAV".equals(requestInfo.getSystemModule().toUpperCase()) || "URA".equals(requestInfo.getSystemModule().toUpperCase())) {
                nmPath = (String) (atualizarPermissoes(permissoes, "CONSCAN", null)).get(0);
            } else if (!"VIVONET".equals(requestInfo.getSystemModule().toUpperCase())) {
                nmPath = (String) (atualizarPermissoes(permissoes, "CONSLEG", null)).get(0);
            }

            if (!"".equals(nmPath)) {
                parametrosLinha.setNmPath(nmPath);
                ParametrosLinhaTO idAtendimento = registrarPalitagem(requestInfo, parametrosLinha);
                if (idAtendimento == null) {
                    resultDAO.setCdRetorno(LinhaPUPWSTO.CD_ERRO_SUBROTINAS);
                    resultDAO.setStatus(RespostaWSTO.OK);
                    resultDAO.setReason("Não foi possível registrar a palitagem.");
                } else {
                    resultDAO.setIdAtendimento(idAtendimento.getIdAtendimento());
                }
            }

        } catch (DAOException ex) {
            getSessionContext().setRollbackOnly();
            throw new ApplicationServiceException(LinhaPUPWSTO.CD_ERRO_BASE + "|Erro de acesso à base de dados.");
        }
        return resultDAO;
    }

    public ParametrosLinhaTO registrarPalitagem(RequestInfoTO requestInfo, ParametrosLinhaTO filtro) {

        long timeIni = System.currentTimeMillis();
        if (logger.isDebugEnabled()) {
            StringBuffer logSB = new StringBuffer();
            logSB.append("ListaPUPAS.registrarPalitagem - Linha[").append(filtro.getCdAreaRegistro()).append(filtro.getNrLinha()).append("] - Palito[").append(filtro.getNmPath())
                    .append("] - STARTED");

            logger.debug(logSB.toString());
        }

        ParametrosLinhaTO ret = null;

        try {
            RegistrarPalitagemGateway registrarPalitagemGateway = new RegistrarPalitagemGateway();
            ret = (ParametrosLinhaTO) registrarPalitagemGateway.call(requestInfo, filtro);

        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                logger.debug("Palitagem não registrada: " + e.getMessage());
            }
        }

        if (logger.isDebugEnabled()) {
            StringBuffer logSB = new StringBuffer();
            logSB.append("ListaPUPAS.registrarPalitagem - Linha[").append(filtro.getCdAreaRegistro()).append(filtro.getNrLinha()).append("] - Palito[").append(filtro.getNmPath())
                    .append("] - FINISHED (" + (System.currentTimeMillis() - timeIni) + ")");
            logger.debug(logSB.toString());
        }

        return ret;
    }

    private List atualizarPermissoes(List permissoes, String cdPermissao, Boolean aceitacao) {

        PermissaoTO permissao;
        String pathComplement = "";
        
        for (int i = 0; i < permissoes.size(); i++) {
            permissao = (PermissaoTO) permissoes.get(i);
            if ("CONSULT".equals(cdPermissao) && cdPermissao.equals(permissao.getCdPermissao())) {
                List lConsulta = new ArrayList();
                lConsulta.add(permissao.getNmPath());
                return lConsulta;
            
            } else if ("CONSCAN".equals(cdPermissao) && cdPermissao.equals(permissao.getCdPermissao())) {
                List lConsulta = new ArrayList();
                lConsulta.add(permissao.getNmPath());
                return lConsulta;
            
            } else if ("CONSLEG".equals(cdPermissao) && cdPermissao.equals(permissao.getCdPermissao())) {
                List lConsulta = new ArrayList();
                lConsulta.add(permissao.getNmPath());
                return lConsulta;
            }
            
            if (cdPermissao.equals(permissao.getCdPermissao())) {
                if (aceitacao != null) {
                    if (aceitacao.booleanValue() == true)
                        pathComplement = "_ACEITO";
                    else
                        pathComplement = "_NAO_ACEITO";

                    if (permissao.getCdPath().equals("PUP_CONTATO_" + cdPermissao + pathComplement)) {
                        permissao.setInAceitacao(new Integer(aceitacao.booleanValue() ? 1 : 0));
                    }
                }
            }
        }

        return permissoes;
    }

    private void validarParametros(RequestInfoTO requestInfo, LinhaPUPWSTO filtro) throws BusinessException {

        if (requestInfo.getSystemModule() == null) {
            throw new ParametroInvalidoException(LinhaPUPWSTO.CD_PARAMETROS_INVALIDOS + "|É obrigatório o envio de systemModule.");
        }

        if (filtro.getNrMIN() == null && (filtro.getCdDDD() == null || filtro.getCdDDD().toString().length() != 2)) {
            throw new NumeroTelefonicoInvalidoException(LinhaPUPWSTO.CD_PARAMETROS_INVALIDOS + "|Código de DDD informado inválido.");
        }

        if (filtro.getNrMIN() == null && (filtro.getNrTelefone() == null || filtro.getNrTelefone().toString().length() < 8 || filtro.getNrTelefone().toString().length() > 9)) {
            throw new NumeroTelefonicoInvalidoException(LinhaPUPWSTO.CD_PARAMETROS_INVALIDOS + "|Número de telefone inválido.");
        }

        if (filtro.getCdCanal() == null && filtro.getSgCanal() == null && filtro.getCdProcedencia() == null && filtro.getSgProcedencia() == null) {
            throw new ParametroInvalidoException(LinhaPUPWSTO.CD_PARAMETROS_INVALIDOS + "|É obrigatório o envio de códigos ou siglas de Canal e Procedência.");
        
        } else if ((filtro.getCdCanal() != null && filtro.getCdProcedencia() == null) || (filtro.getCdCanal() == null && filtro.getCdProcedencia() != null)) {
            throw new ParametroInvalidoException(LinhaPUPWSTO.CD_PARAMETROS_INVALIDOS + "|É obrigatório o envio dos códigos de Canal e Procedência.");
        
        } else if ((filtro.getSgCanal() != null && filtro.getSgProcedencia() == null) || (filtro.getSgCanal() == null && filtro.getSgProcedencia() != null)) {
            throw new ParametroInvalidoException(LinhaPUPWSTO.CD_PARAMETROS_INVALIDOS + "|É obrigatório o envio das siglas de Canal e Procedência.");
        }

        return;
    }

    private ParametrosLinhaTO consultarIDUsuarioCanalProcedencia(RequestInfoTO requestInfo) throws ApplicationServiceException, BusinessException {
        try {
            ListaPUPDAO listaPUPDAO = new ListaPUPDAO();
            ParametrosLinhaTO dadosIdCanProc = listaPUPDAO.consultarIDUsuarioCanalProcedencia(requestInfo);

            if (dadosIdCanProc == null) {
                throw new BusinessException(LinhaPUPWSTO.CD_ERRO_SUBROTINAS + "|Dados de usuário, canal e procedência insuficientes para prosseguimento de operação.");
            }
            return dadosIdCanProc;

        } catch (DAOException ex) {
            getSessionContext().setRollbackOnly();
            throw new ApplicationServiceException(LinhaPUPWSTO.CD_ERRO_BASE + "|Erro de acesso à base de dados.");
        }
    }
}