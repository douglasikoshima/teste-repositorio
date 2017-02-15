package br.com.indrasistemas.vivoservices.sittel.assinante.application;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.SessionContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import br.com.indrasistemas.framework.service.BusinessException;
import br.com.indrasistemas.framework.service.application.ApplicationServiceException;
import br.com.indrasistemas.framework.service.application.BaseApplicationService;
import br.com.indrasistemas.framework.service.dao.exceptions.DAOException;
import br.com.indrasistemas.vivoservices.listapup.to.LinhaPUPWSTO;
import br.com.indrasistemas.vivoservices.sittel.assinante.dao.AssinanteDAO;
import br.com.indrasistemas.vivoservices.sittel.assinante.to.AssinanteTO;
import br.com.indrasistemas.vivoservices.sittel.assinante.to.InvestigadoTO;
import br.com.indrasistemas.vivoservices.sittel.assinante.to.ResultadoConsultaTO;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class AssinanteAS extends BaseApplicationService {

    private static final Log    logger                       = LogFactory.getLog("AssinanteAS");

    private static final String PARAM_LIMTE_PARAM_ALVO       = "PROCESSUM_LIMITE_PARAM_ALVO";
    private static final String PARAM_LIMTE_PERIODO_CONSULTA = "PROCESSUM_LIMITE_PERIODO_CONSULTA";
    private static final String PARAM_LIMTE_TERMINAL_ALVO    = "PROCESSUM_LIMITE_CONSULTA_TERMINAL";

    public AssinanteAS(SessionContext sessionContext) {
        super(sessionContext);
    }

    public ResultadoConsultaTO gravarRequisicaoProcessum(AssinanteTO to, Integer idTipoSolicitacao) throws ApplicationServiceException, BusinessException {

        if (logger.isInfoEnabled()) {
            logger.info("AssinanteAS.gravarRequisicaoProcessum - Método iniciado.");
        }

        ResultadoConsultaTO resultTO = validarParametros(to);

        if ("OK".equalsIgnoreCase(resultTO.getStatus())) {
            AssinanteDAO assinanteDAO = new AssinanteDAO();

            try {
                resultTO = assinanteDAO.gravarRequisicaoProcessum(idTipoSolicitacao,
                                                                  to.getCodigoOrdem(), 
                                                                  to.getCodigoRequisicao(), 
                                                                  to.getNumeroSolicitacao(), 
                                                                  to.getTipoServico(), 
                                                                  to.getDirecao(), 
                                                                  to.getDadosCadastraisInterlocutor(), 
                                                                  to.getInvestigados());

            } catch (DAOException ex) {
                getSessionContext().setRollbackOnly();
                throw new ApplicationServiceException(LinhaPUPWSTO.CD_ERRO_BASE + "|Erro de acesso à base de dados.");
            }
        }
        if (logger.isInfoEnabled()) {
            logger.info("AssinanteAS.gravarRequisicaoProcessum - Método finalizado.");
        }

        return resultTO;
    }

    private static ResultadoConsultaTO validarParametros(AssinanteTO assinanteTO) throws ApplicationServiceException {

        logger.info("AssinanteAS.validarEntrada - Método iniciado. " + assinanteTO);

        ResultadoConsultaTO resultadoConsulta = new ResultadoConsultaTO(0L, "Sucesso no retorno da API.", "OK", null);

        /* Buscar parametros de  Configuração */
        Integer iLimiteParametro = null;
        Integer iLimitePeriodo = null;
        Integer iLimiteTerminal = null;

        try {
            iLimiteParametro = obterParametroConfiguracao(PARAM_LIMTE_PARAM_ALVO);
            iLimitePeriodo = obterParametroConfiguracao(PARAM_LIMTE_PERIODO_CONSULTA);
            iLimiteTerminal = obterParametroConfiguracao(PARAM_LIMTE_TERMINAL_ALVO);

        } catch (Exception e) {
            resultadoConsulta.setCodigoErro(new Long("99"));
            resultadoConsulta.setDescricaoErro("Impossível obter parâmetros de configuração.");
            resultadoConsulta.setStatus("NOK");
            resultadoConsulta.setReason("Impossível obter parâmetros de configuração.");
            return resultadoConsulta;
        }

        if (logger.isDebugEnabled()) {
            logger.debug("AssinanteAS.validarEntrada. NumeroSolicitacao: " + assinanteTO.getNumeroSolicitacao());
            logger.debug("AssinanteAS.validarEntrada. CodigoOrdem: " + assinanteTO.getCodigoOrdem());
            logger.debug("AssinanteAS.validarEntrada. CodigoRequisicao: " + assinanteTO.getCodigoRequisicao());
        }

        /* Parametros Obrigatorias para a pesquisa */
        if (assinanteTO == null || assinanteTO.getInvestigados() == null || assinanteTO.getInvestigados().size() == 0) {
            resultadoConsulta.setCodigoErro(new Long("6"));
            resultadoConsulta.setDescricaoErro("Não consta o alvo a ser pesquisado.");
            resultadoConsulta.setStatus("NOK");
            resultadoConsulta.setReason("Não consta o alvo a ser pesquisado.");
            return resultadoConsulta;
        }

        if ("".equals(assinanteTO.getNumeroSolicitacao()) || "".equals(assinanteTO.getCodigoOrdem()) || "".equals(assinanteTO.getCodigoRequisicao())) {
            resultadoConsulta.setCodigoErro(new Long("6"));
            resultadoConsulta.setDescricaoErro("Não consta o alvo a ser pesquisado.");
            resultadoConsulta.setStatus("NOK");
            resultadoConsulta.setReason("Não consta o alvo a ser pesquisado.");
            return resultadoConsulta;
        }

        List listInvestigados = assinanteTO.getInvestigados();
        logger.debug("AssinanteAS.validarEntrada. Antes Totais ");

        /* Obter a quantidade de Parametros totais enviados na solicitação */
        int iTotalNome = countParametros(listInvestigados, "NOME");
        int iTotalRG = countParametros(listInvestigados, "RG");
        int iTotalCPF = countParametros(listInvestigados, "CPF");
        int iTotalCNPJ = countParametros(listInvestigados, "CNPJ");

        /* Validar os totais de Parametros com os Limites configurados na Apoio.Parametro */
        if ((iTotalNome > iLimiteParametro) || (iTotalRG > iLimiteParametro) || (iTotalCPF > iLimiteParametro) || (iTotalCNPJ > iLimiteParametro)) {
            resultadoConsulta.setCodigoErro(new Long("102"));
            resultadoConsulta.setDescricaoErro("Número de registros para consulta excede o máximo permitido. Favor refinar a consulta.");
            resultadoConsulta.setStatus("NOK");
            resultadoConsulta.setReason("Número de registros para consulta excede o máximo permitido. Favor refinar a consulta.");
            return resultadoConsulta;
        }

        /* Validar os totais de Terminais com o Limte configurados na Apoio.Parametro */
        int iTotalTelefone = countParametros(listInvestigados, "TELEFONE");
        if (iTotalTelefone > iLimiteTerminal) {
            resultadoConsulta.setCodigoErro(new Long("101"));
            resultadoConsulta.setDescricaoErro("Número de registros de telefone excede o máximo permitido. Favor refinar a consulta.");
            resultadoConsulta.setStatus("NOK");
            resultadoConsulta.setReason("Número de registros de telefone excede o máximo permitido. Favor refinar a consulta.");
            return resultadoConsulta;
        }

        if (logger.isDebugEnabled()) {
            logger.debug("AssinanteAS.validarEntrada. iTotalCPF: " + iTotalCPF);
            logger.debug("AssinanteAS.validarEntrada. iTotalCNPJ: " + iTotalCNPJ);
            logger.debug("AssinanteAS.validarEntrada. iTotalNome: " + iTotalNome);
            logger.debug("AssinanteAS.validarEntrada. iTotalRG: " + iTotalRG);
            logger.debug("AssinanteAS.validarEntrada. iTotalTelefone: " + iTotalTelefone);
        }

        /* Validar se pelo menos algum parâmetro foi enviado */
        if (iTotalCPF == 0 && iTotalCNPJ == 0 && iTotalNome == 0 && iTotalRG == 0 && iTotalTelefone == 0) {
            resultadoConsulta.setCodigoErro(new Long("6"));
            resultadoConsulta.setDescricaoErro("Não consta o alvo a ser pesquisado.");
            resultadoConsulta.setStatus("NOK");
            resultadoConsulta.setReason("Não consta o alvo a ser pesquisado.");
            return resultadoConsulta;
        }
        
      
        Iterator itAlvoInvestigado = listInvestigados.iterator();
        /* Validação individual de cada alvo informado na solicitação */
        for (; itAlvoInvestigado.hasNext();) {
            InvestigadoTO investigadoTO = (InvestigadoTO) itAlvoInvestigado.next();

            if (logger.isDebugEnabled()) {
                logger.debug("AssinanteAS.validarEntrada. getCnpj: " + investigadoTO.getCnpj());
                logger.debug("AssinanteAS.validarEntrada. getCpf: " + investigadoTO.getCpf());
                logger.debug("AssinanteAS.validarEntrada. getDocumentoAssinante: " + investigadoTO.getDocumentoAssinante());
                logger.debug("AssinanteAS.validarEntrada. getInicioPeriodoQuebra: " + investigadoTO.getInicioPeriodoQuebra());
                logger.debug("AssinanteAS.validarEntrada. getFimPeriodoQuebra: " + investigadoTO.getFimPeriodoQuebra());
            }

            /* Validação CPF informado */
            if (investigadoTO.getCpf() != null && !"".equals(investigadoTO.getCpf())) {
                boolean erro = false;
                if (investigadoTO.getCpf().trim().length() != 11) {
                    erro = true;
                }
                String cpf = investigadoTO.getCpf().trim();
                if (erro == false && cpf.length() != investigadoTO.getCpf().replaceAll("[^0-9]", "").trim().length()) {
                    erro = true;
                }
                if (erro) {
                    resultadoConsulta.setCodigoErro(new Long("103"));
                    resultadoConsulta.setDescricaoErro("Número de CPF inválido.");
                    resultadoConsulta.setStatus("NOK");
                    resultadoConsulta.setReason("Número de CPF inválido.");
                    return resultadoConsulta;
                }
            }

            /* Validação CNPJ informado */
            if (investigadoTO.getCnpj() != null && !"".equals(investigadoTO.getCnpj())) {
                boolean erro = false;
                if (investigadoTO.getCnpj().trim().length() != 14) {
                    erro = true;
                }
                String cnpj = investigadoTO.getCnpj().trim();
                if (erro == false && cnpj.length() != investigadoTO.getCnpj().replaceAll("[^0-9]", "").trim().length()) {
                    erro = true;
                }
                if (erro) {
                    resultadoConsulta.setCodigoErro(new Long("104"));
                    resultadoConsulta.setDescricaoErro("Número de CNPJ inválido.");
                    resultadoConsulta.setStatus("NOK");
                    resultadoConsulta.setReason("Número de CNPJ inválido.");
                    return resultadoConsulta;
                }
            }

            /* Validação se para o mesmo alvo foi informado CPF e CNPJ */
            if (investigadoTO.getCnpj() != null && !"".equals(investigadoTO.getCnpj()) && investigadoTO.getCpf() != null && !"".equals(investigadoTO.getCpf())) {
                resultadoConsulta.setCodigoErro(new Long("12"));
                resultadoConsulta.setDescricaoErro("Parâmetros de pesquisa divergentes.");
                resultadoConsulta.setStatus("NOK");
                resultadoConsulta.setReason("Parâmetros de pesquisa divergentes.");
                return resultadoConsulta;
            }
            
            /* Validação do campo Documento Assinante que deve conter o RG com o máximo de 10 Caracteres */
            if (investigadoTO.getDocumentoAssinante() != null && !"".equals(investigadoTO.getDocumentoAssinante())) {
            	
            	String documento = investigadoTO.getDocumentoAssinante().trim();
            	if (documento.length() > 10) {
	                resultadoConsulta.setCodigoErro(new Long("104"));
	                resultadoConsulta.setDescricaoErro("Número de RG inválido.");
	                resultadoConsulta.setStatus("NOK");
	                resultadoConsulta.setReason("Número de RG inválido.");
	                return resultadoConsulta;
            	}
            }
            

            /* Validação se campos data início foi informado */
            if (investigadoTO.getInicioPeriodoQuebra() == null || "".equals(investigadoTO.getInicioPeriodoQuebra())) {
                resultadoConsulta.setCodigoErro(new Long("9"));
                resultadoConsulta.setDescricaoErro("Período não especificado ou indisponível.");
                resultadoConsulta.setStatus("NOK");
                resultadoConsulta.setReason("Período não especificado ou indisponível.");
                return resultadoConsulta;
            }

            /* Validação se campos data fim foi informado */
            if (investigadoTO.getFimPeriodoQuebra() == null || "".equals(investigadoTO.getFimPeriodoQuebra())) {
                resultadoConsulta.setCodigoErro(new Long("9"));
                resultadoConsulta.setDescricaoErro("Período não especificado ou indisponível.");
                resultadoConsulta.setStatus("NOK");
                resultadoConsulta.setReason("Período não especificado ou indisponível.");
                return resultadoConsulta;
            }

            /* Log que retorna o total de anos entre a data incial e final informada */
            float a = validarTotalIntervaloData(investigadoTO.getInicioPeriodoQuebra(), investigadoTO.getFimPeriodoQuebra());
            logger.debug("AssinanteAS.validarEntrada. Total Periodo: " + a);

            if (!validarData(investigadoTO.getInicioPeriodoQuebra())) { // Validação data Inicial é Valida
                resultadoConsulta.setCodigoErro(new Long("9"));
                resultadoConsulta.setDescricaoErro("Período não especificado ou indisponível.");
                resultadoConsulta.setStatus("NOK");
                resultadoConsulta.setReason("Período não especificado ou indisponível.");
                return resultadoConsulta;
            } else if (!validarData(investigadoTO.getFimPeriodoQuebra())) { // Validação data Fim é Valida
                resultadoConsulta.setCodigoErro(new Long("9"));
                resultadoConsulta.setDescricaoErro("Período não especificado ou indisponível.");
                resultadoConsulta.setStatus("NOK");
                resultadoConsulta.setReason("Período não especificado ou indisponível.");
                return resultadoConsulta;
            } else if (validarIntervaloData(investigadoTO.getInicioPeriodoQuebra(), investigadoTO.getFimPeriodoQuebra()) < 0) { // Validação data Inicial é menor que data Fim
                resultadoConsulta.setCodigoErro(new Long("12"));
                resultadoConsulta.setDescricaoErro("Parâmetros de pesquisa divergentes.");
                resultadoConsulta.setStatus("NOK");
                resultadoConsulta.setReason("Parâmetros de pesquisa divergentes.");
                return resultadoConsulta;
            } else if (validarTotalIntervaloData(investigadoTO.getInicioPeriodoQuebra(), investigadoTO.getFimPeriodoQuebra()) > iLimitePeriodo) { // Validação do intervalo entre as datas é menos que o limite configurado na apoio.parametro
                resultadoConsulta.setCodigoErro(new Long("10"));
                resultadoConsulta.setDescricaoErro("Período superior ao armazenado. ( " + iLimitePeriodo + " anos )");
                resultadoConsulta.setStatus("NOK");
                resultadoConsulta.setReason("Informe um período de quebra válido para a consulta.");
                return resultadoConsulta;
            }

            /* Validação do formato do campo NrTerminalTelefone padrão 55DD9######## OU 55DD######## */
       		if (investigadoTO.getNumeroTerminalAssinante() != null && !"".equals(investigadoTO.getNumeroTerminalAssinante()) && investigadoTO.getNumeroTerminalAssinante().length() > 20) {
                resultadoConsulta.setCodigoErro(new Long("7"));
                resultadoConsulta.setDescricaoErro("Número do terminal telefônico incompleto ou com excesso de dígitos.");
                resultadoConsulta.setStatus("NOK");
                resultadoConsulta.setReason("Número do terminal telefônico incompleto ou com excesso de dígitos.");
                return resultadoConsulta;
            }
            
        }
        return resultadoConsulta;
    }

    private static int countParametros(List listaParametros, String parametro) {
        int i = 0;
        Iterator itAlvoInvestigado = listaParametros.iterator();
        for (; itAlvoInvestigado.hasNext();) {
            InvestigadoTO tipoInvestigaoTO = (InvestigadoTO) itAlvoInvestigado.next();
            if ("CPF".equalsIgnoreCase(parametro)) {
                if (tipoInvestigaoTO.getCpf() != null && !"".equals(tipoInvestigaoTO.getCpf())) {
                    i++;
                }
            } else if ("CNPJ".equalsIgnoreCase(parametro)) {
                if (tipoInvestigaoTO.getCnpj() != null && !"".equals(tipoInvestigaoTO.getCnpj())) {
                    i++;
                }
            } else if ("RG".equalsIgnoreCase(parametro)) {
                if (tipoInvestigaoTO.getDocumentoAssinante() != null && !"".equals(tipoInvestigaoTO.getDocumentoAssinante())) {
                    i++;
                }
            } else if ("NOME".equalsIgnoreCase(parametro)) {
                if (tipoInvestigaoTO.getNomeAssinante() != null && !"".equals(tipoInvestigaoTO.getNomeAssinante())) {
                    i++;
                }
            } else if ("TELEFONE".equalsIgnoreCase(parametro)) {
                if (tipoInvestigaoTO.getNumeroTerminalAssinante() != null && !"".equals(tipoInvestigaoTO.getNumeroTerminalAssinante())) {
                    i++;
                }
            }
        }
        return i;
    }

    private static boolean validarData(String dtPeriodo) {

    	SimpleDateFormat df = new SimpleDateFormat("ddMMyyyyHHmmss");
        df.setLenient(false);

        if (logger.isDebugEnabled()) {
            logger.debug("AssinanteAS.validarData. dtPeriodo: " + dtPeriodo);
        }

        try {
            df.parse(dtPeriodo);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    private static int validarIntervaloData(String dtInicioPeriodo, String dtFimPeriodo) {

    	SimpleDateFormat df = new SimpleDateFormat("ddMMyyyyHHmmss");
        df.setLenient(false);
        try {
            java.util.Date dataInicio = df.parse(dtInicioPeriodo);
            java.util.Date dataFim = df.parse(dtFimPeriodo);

            return dataFim.compareTo(dataInicio);
        } catch (Exception e) {
            return -1;
        }
    }

    private static float validarTotalIntervaloData(String dtInicioPeriodo, String dtFimPeriodo) {

    	SimpleDateFormat df = new SimpleDateFormat("ddMMyyyyHHmmss");
        df.setLenient(false);
        try {
            java.util.Date dataInicio = df.parse(dtInicioPeriodo);
            java.util.Date dataFim = df.parse(dtFimPeriodo);
            if (dataFim.compareTo(dataInicio) >= 0) {
                return (float) (dataFim.getTime() - dataInicio.getTime()) / (1000 * 60 * 60 * 24) / 365;
            } else {
                return -1;
            }
        } catch (Exception e) {
            return -1;
        }
    }

    private static Integer obterParametroConfiguracao(String cdParametro) {

        AssinanteDAO assinanteDAO = new AssinanteDAO();
        Integer iParametro = null;
        String dsParametro = null;

        try {
            dsParametro = assinanteDAO.buscarParametro(cdParametro);
            iParametro = new Integer(dsParametro);
            logger.debug("obterParametroConfiguracao. dsParametro: " + dsParametro);

        } catch (Exception e) {
            logger.error("Erro ao obterParametroConfiguracao", e);
        }
        return iParametro;
    }

    public static void main(String[] args) {

        AssinanteTO to = new AssinanteTO();

        to.setCodigoOrdem("1234");
        to.setCodigoRequisicao("5678");
        to.setNumeroSolicitacao("9101112");
        to.setDadosCadastraisInterlocutor(new Boolean(true));
        to.setDirecao("TODAS");
        to.setTipoServico("TODOS");

        List tipoListaInvestigados = new ArrayList();
        InvestigadoTO tiInvestigadoTO = new InvestigadoTO();

        tiInvestigadoTO.setCpf("04021284605");
        tiInvestigadoTO.setCnpj(null);
        tiInvestigadoTO.setDocumentoAssinante("");
        tiInvestigadoTO.setNomeAssinante("");
        tiInvestigadoTO.setInicioPeriodoQuebra(null);
        tiInvestigadoTO.setFimPeriodoQuebra(null);
        tiInvestigadoTO.setNumeroTerminalAssinante("5511983909172");

        tiInvestigadoTO.setInicioPeriodoQuebra("02/04/2000 23:59:59");
        tiInvestigadoTO.setFimPeriodoQuebra("01/04/2005 00:00:01");

        tipoListaInvestigados.add(tiInvestigadoTO);

        InvestigadoTO tiInvestigadoTO2 = new InvestigadoTO();

        tiInvestigadoTO2.setCpf("");
        tiInvestigadoTO2.setCnpj("08008057000103");
        tiInvestigadoTO2.setDocumentoAssinante("");
        tiInvestigadoTO2.setNomeAssinante("");
        tiInvestigadoTO2.setInicioPeriodoQuebra(null);
        tiInvestigadoTO2.setFimPeriodoQuebra(null);
        tiInvestigadoTO2.setNumeroTerminalAssinante("");
        tiInvestigadoTO2.setInicioPeriodoQuebra("02/04/2000 23:59:59");
        tiInvestigadoTO2.setFimPeriodoQuebra("01/04/2005 00:00:01");
        tipoListaInvestigados.add(tiInvestigadoTO2);
        /*
        TipoInvestigadoTO tiInvestigadoTO3 = new TipoInvestigadoTO();
        tiInvestigadoTO3.setCpf("");
        tiInvestigadoTO3.setCnpj("");
        tiInvestigadoTO3.setDocumentoAssinante("10310375");
        tiInvestigadoTO3.setNomeAssinante("dssdsd");
        tiInvestigadoTO3.setNumeroTerminalAssinante("");
        tiInvestigadoTO3.setInicioPeriodoQuebra("02/04/2000 23:59:59");
        tiInvestigadoTO3.setFimPeriodoQuebra("01/04/2005 00:00:01");
        tipoListaInvestigados.add(tiInvestigadoTO3);
        
        TipoInvestigadoTO tiInvestigadoTO4 = new TipoInvestigadoTO();
        tiInvestigadoTO4.setCpf("");
        tiInvestigadoTO4.setCnpj("");
        tiInvestigadoTO4.setDocumentoAssinante("");
        tiInvestigadoTO4.setNomeAssinante("");
        tiInvestigadoTO4.setNumeroTerminalAssinante("");
        tiInvestigadoTO4.setInicioPeriodoQuebra("02/04/2000 23:59:59");
        tiInvestigadoTO4.setFimPeriodoQuebra("01/04/2005 00:00:01");
        tipoListaInvestigados.add(tiInvestigadoTO4);
        
        TipoInvestigadoTO tiInvestigadoTO5 = new TipoInvestigadoTO();
        tiInvestigadoTO5.setCpf("");
        tiInvestigadoTO5.setCnpj("");
        tiInvestigadoTO5.setDocumentoAssinante("");
        tiInvestigadoTO5.setNomeAssinante("");
        tiInvestigadoTO5.setNumeroTerminalAssinante("");
        tiInvestigadoTO5.setInicioPeriodoQuebra("02/04/2000 23:59:59");
        tiInvestigadoTO5.setFimPeriodoQuebra("01/04/2005 00:00:01");
        tipoListaInvestigados.add(tiInvestigadoTO5);
        
        TipoInvestigadoTO tiInvestigadoTO6 = new TipoInvestigadoTO();
        tiInvestigadoTO6.setCpf("");
        tiInvestigadoTO6.setCnpj("");
        tiInvestigadoTO6.setDocumentoAssinante("");
        tiInvestigadoTO6.setNomeAssinante("");
        tiInvestigadoTO6.setNumeroTerminalAssinante("551143296955");
        tiInvestigadoTO6.setInicioPeriodoQuebra("02/04/2000 23:59:59");
        tiInvestigadoTO6.setFimPeriodoQuebra("01/04/2005 00:00:01");
        tipoListaInvestigados.add(tiInvestigadoTO6);
        */
        /*
        TipoInvestigadoTO tiInvestigadoTO7 = new TipoInvestigadoTO();
        tiInvestigadoTO7.setCpf("");
        tiInvestigadoTO7.setCnpj("");
        tiInvestigadoTO7.setDocumentoAssinante("");
        tiInvestigadoTO7.setNomeAssinante("fsffsf");
        tiInvestigadoTO7.setNumeroTerminalAssinante("");
        tiInvestigadoTO7.setInicioPeriodoQuebra("02/04/2000 23:59:59");
        tiInvestigadoTO7.setFimPeriodoQuebra("01/04/2005 00:00:01");
        tipoListaInvestigados.add(tiInvestigadoTO7);
        */

        //to.setListaInvestigado(listaInvestigado);
        try {
            ResultadoConsultaTO resultTO = validarParametros(to);
            System.out.println("Erro " + resultTO.getCodigoErro() + " - " + resultTO.getStatus() + " - " + resultTO.getDescricaoErro());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro " + e.getMessage());
        }

    }

}