package br.com.indrasistemas.vivoservices.sittel.assinante.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import br.com.indrasistemas.framework.service.dao.DAOExceptionBuilder;
import br.com.indrasistemas.framework.service.dao.HibernateBaseDAO;
import br.com.indrasistemas.framework.service.dao.exceptions.DAOException;
import br.com.indrasistemas.vivoservices.sittel.assinante.to.InvestigadoTO;
import br.com.indrasistemas.vivoservices.sittel.assinante.to.ResultadoConsultaTO;

@SuppressWarnings({ "rawtypes"})
public class AssinanteDAO extends HibernateBaseDAO {

    private static final Log logger = LogFactory.getLog(AssinanteDAO.class);

    public ResultadoConsultaTO gravarRequisicaoProcessum(Integer idTipoSolicitacao, String codigoOrdem, String codigoRequisicao, String numeroSolicitacao, String tipoServico, String direcao,
            Boolean dadosCadastraisInterlocutor, List listaInvestigado) throws DAOException {

        if (logger.isDebugEnabled()) {
            logger.debug("Início de inserção de gravarRequisicaoProcessum: codigoOrdem: " + codigoOrdem + "; codigoRequisicao: " + codigoRequisicao + "; numeroSolicitacao: " + numeroSolicitacao
                    + "; idTipoSolicitacao: " + idTipoSolicitacao.intValue() + "; tipoServico: " + tipoServico + "; direcao: " + direcao + "; dadosCadastraisInterlocutor: "
                    + dadosCadastraisInterlocutor);
        }

        ResultadoConsultaTO resultadoConsultaTO = new ResultadoConsultaTO(0L, "Sucesso no retorno da API.", "OK", null);
        StringBuffer sql = new StringBuffer();

        Integer idFilaProcessum = obterIdFilaProcessum();
        if (logger.isDebugEnabled()) {
            logger.debug("(gravarRequisicaoProcessum) idFilaProcessum: " + idFilaProcessum);
        }

        if (idFilaProcessum != null) {

            sql = new StringBuffer(" INSERT INTO CUSTOMER.FILAPROCESSUM (IDFILAPROCESSUM, CODIGOORDEM, CODIGOREQUISICAO, NUMEROSOLICITACAO, TIPOSOLICITACAO, INPROCESSADO ");

            if (tipoServico != null && !"".equals(tipoServico)) {
                sql.append(" , TIPOSERVICO ");
            }
            if (direcao != null && !"".equals(direcao)) {
                sql.append(" , DIRECAO ");
            }
            if (dadosCadastraisInterlocutor != null) {
                sql.append(" ,INDADOSINTERLOCUTOR ");
            }

            sql.append(" ) ");

            sql.append(" VALUES (?, ?, ?, ?, ?, ? ");

            if (tipoServico != null && !"".equals(tipoServico)) {
                sql.append(", '").append(tipoServico.toUpperCase().trim()).append("' ");
            }

            if (direcao != null && !"".equals(direcao)) {
                sql.append(", '").append(direcao.toUpperCase().trim()).append("' ");
            }

            if (dadosCadastraisInterlocutor != null) {
                sql.append(", ").append(dadosCadastraisInterlocutor ? 1 : 0);
            }

            sql.append(" ) ");

            if (logger.isDebugEnabled()) {
                logger.debug("(gravarRequisicaoProcessum) query: " + sql.toString());
            }

            Connection conn = getJDBCConnection();
            PreparedStatement ps = null;

            try {
                ps = conn.prepareStatement(sql.toString());

                ps.setInt(1, idFilaProcessum.intValue());
                ps.setLong(2, new Long(codigoOrdem).longValue());
                ps.setInt(3, new Integer(codigoRequisicao).intValue());
                ps.setInt(4, new Integer(numeroSolicitacao).intValue());
                ps.setInt(5, idTipoSolicitacao.intValue());
                ps.setInt(6, 0);

                if (logger.isDebugEnabled()) {
                    logger.debug("(gravarRequisicaoProcessum) Execute: ");
                }
                ps.executeUpdate();

                /* Inclusão dos Alvos */
                gravarListaAlvoProcessum(idFilaProcessum, listaInvestigado);

            } catch (Exception ex) {
                logger.error("gravarRequisicaoProcessum::", ex);
                throw DAOExceptionBuilder.build(ex);
            } finally {
                closePreparedStatement(ps);
                closeConnection(conn);
            }
        }

        if (logger.isDebugEnabled()) {
            logger.debug("Fim de Inserção de Requesição Processum: codigoOrdem: " + codigoOrdem + "; codigoRequisicao: " + codigoRequisicao + "; numeroSolicitacao: " + numeroSolicitacao
                    + " idFilaProcessum: " + idFilaProcessum);
        }
        return resultadoConsultaTO;
    }

    private void gravarListaAlvoProcessum(Integer idFilaProcessum, List listaInvestigado) throws DAOException {

        if (logger.isDebugEnabled()) {
            logger.debug("Início de inserção de gravarListaAlvoProcessum: idFilaProcessum: " + idFilaProcessum);
        }

        StringBuffer sql = new StringBuffer();

        if (listaInvestigado != null && listaInvestigado.size() > 0) {

            Iterator itAlvoInvestigado = listaInvestigado.iterator();

            for (; itAlvoInvestigado.hasNext();) {
                InvestigadoTO investigadoTO = (InvestigadoTO) itAlvoInvestigado.next();

                sql = new StringBuffer(" INSERT INTO CUSTOMER.ALVOPROCESSUM (IDALVOPROCESSUM, IDFILAPROCESSUM, INICIOPERIODOQUEBRA, FIMPERIODOQUEBRA ");
                if (investigadoTO.getCpf() != null && !"".equals(investigadoTO.getCpf())) {
                    sql.append(", CPF ");
                }
                if (investigadoTO.getCnpj() != null && !"".equals(investigadoTO.getCnpj())) {
                    sql.append(", CNPJ ");
                }
                if (investigadoTO.getDocumentoAssinante() != null && !"".equals(investigadoTO.getDocumentoAssinante())) {
                    sql.append(", DOCUMENTOASSINANTE ");
                }
                if (investigadoTO.getNumeroTerminalAssinante() != null && !"".equals(investigadoTO.getNumeroTerminalAssinante())) {
                    sql.append(", NRTERMINALASSINANTE ");
                }
                if (investigadoTO.getNomeAssinante() != null && !"".equals(investigadoTO.getNomeAssinante())) {
                    sql.append(", NOMEASSINANTE ");
                }
                if (investigadoTO.getImei() != null && !"".equals(investigadoTO.getImei())) {
                    sql.append(", IMEI  ");
                }
                if (investigadoTO.getCgiErb() != null && !"".equals(investigadoTO.getCgiErb())) {
                    sql.append(", CGIERB ");
                }
                if (investigadoTO.getEnderecoIp() != null && !"".equals(investigadoTO.getEnderecoIp())) {
                    sql.append(", ENDERECOIP ");
                }
                if (investigadoTO.getNumeroPortaIp() != null && !"".equals(investigadoTO.getNumeroPortaIp())) {
                    sql.append(", NRPORTAIP ");
                }

                sql.append(" ) ");
                sql.append(" VALUES (CUSTOMER.ALVOPROCESSUMSQ.NEXTVAL, ");
                sql.append(idFilaProcessum.intValue()).append(", ");
                sql.append(" TO_DATE('").append(investigadoTO.getInicioPeriodoQuebra()).append("', 'DDMMYYYYHH24MISS'), ");
                sql.append(" TO_DATE('").append(investigadoTO.getFimPeriodoQuebra()).append("', 'DDMMYYYYHH24MISS') ");

                if (investigadoTO.getCpf() != null && !"".equals(investigadoTO.getCpf())) {
                    sql.append(", '").append(investigadoTO.getCpf().trim().replaceAll("[^0-9]", "")).append("' ");
                }
                if (investigadoTO.getCnpj() != null && !"".equals(investigadoTO.getCnpj())) {
                    sql.append(", '").append(investigadoTO.getCnpj().trim().replaceAll("[^0-9]", "")).append("' ");
                }
                if (investigadoTO.getDocumentoAssinante() != null && !"".equals(investigadoTO.getDocumentoAssinante())) {
                    sql.append(", '").append(investigadoTO.getDocumentoAssinante().trim()).append("' ");
                }
                if (investigadoTO.getNumeroTerminalAssinante() != null && !"".equals(investigadoTO.getNumeroTerminalAssinante())) {
                    sql.append(", ").append(investigadoTO.getNumeroTerminalAssinante().trim());
                }
                if (investigadoTO.getNomeAssinante() != null && !"".equals(investigadoTO.getNomeAssinante())) {
                    sql.append(", '").append(investigadoTO.getNomeAssinante().trim()).append("' ");
                }
                if (investigadoTO.getImei() != null && !"".equals(investigadoTO.getImei())) {
                    sql.append(", '").append(investigadoTO.getImei().trim()).append("' ");
                }
                if (investigadoTO.getCgiErb() != null && !"".equals(investigadoTO.getCgiErb())) {
                    sql.append(", '").append(investigadoTO.getCgiErb().trim()).append("' ");
                }
                if (investigadoTO.getEnderecoIp() != null && !"".equals(investigadoTO.getEnderecoIp())) {
                    sql.append(", '").append(investigadoTO.getEnderecoIp().trim()).append("' ");
                }
                if (investigadoTO.getNumeroPortaIp() != null && !"".equals(investigadoTO.getNumeroPortaIp())) {
                    sql.append(", ").append(investigadoTO.getNumeroPortaIp().trim());
                }

                sql.append(" ) ");

                if (logger.isDebugEnabled()) {
                    logger.debug("(gravarListaAlvoProcessum) query: " + sql.toString());
                }

                Connection conn = getJDBCConnection();
                PreparedStatement ps = null;

                try {
                    ps = conn.prepareStatement(sql.toString());
                    ps.executeUpdate();
                } catch (Exception ex) {
                    logger.error("gravarListaAlvoProcessum::", ex);
                    throw DAOExceptionBuilder.build(ex);
                } finally {
                    closePreparedStatement(ps);
                    closeConnection(conn);
                }
            }
        }

        if (logger.isDebugEnabled()) {
            logger.debug("Fim de Inserção Alvos Fila : idFilaProcessum: " + idFilaProcessum);
        }
    }

    private Integer obterIdFilaProcessum() throws DAOException {

        Integer idFilaProcessum = null;
        
        /* Obter IDFILAPROCESSUM da SEQUENCE FILAPROCESSUMSQ */
        StringBuffer sql = new StringBuffer("SELECT CUSTOMER.FILAPROCESSUMSQ.NEXTVAL FROM DUAL ");

        if (logger.isDebugEnabled()) {
            logger.debug("(obterIdFilaProcessum) query: " + sql.toString());
        }
        
        try {
            Connection conn = getJDBCConnection();

            PreparedStatement ps = null;

            try {
                ps = conn.prepareStatement(sql.toString());
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    idFilaProcessum = rs.getInt(1);
                }

            } catch (Exception ex) {
                logger.error("Não foi possível obter IDFILAPROCESSUM . " + ex.getMessage());
            } finally {
                closePreparedStatement(ps);
                closeConnection(conn);
            }
        } catch (Exception connEx) {
            logger.error("Não foi possível conexão banco. " + connEx.getMessage());
        }
        return idFilaProcessum;
    }

    public String buscarParametro(String cdParametro) throws DAOException {

        if (logger.isDebugEnabled()) {
            logger.debug("Início buscarParametro: cdParametro: " + cdParametro);
        }

        StringBuffer sql = new StringBuffer();
        String dsValorParametro = "";

        sql.append("SELECT DSVALORPARAMETRO FROM APOIO.PARAMETRO WHERE CDPARAMETRO = ? ");

        if (logger.isDebugEnabled()) {
            logger.debug("(buscarParametro) query: " + sql.toString());
        }

        try {
            Connection conn = getJDBCConnection();

            PreparedStatement ps = null;

            try {
                ps = conn.prepareStatement(sql.toString());
                ps.setString(1, cdParametro);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    dsValorParametro = rs.getString(1);
                }

            } catch (Exception ex) {
                logger.error("Não foi possível obter parametro::", ex);                
            } finally {
                closePreparedStatement(ps);
                closeConnection(conn);
            }
        } catch (Exception connEx) {
            logger.error("Não foi possível conexão banco. " + connEx.getMessage());
        }
        return dsValorParametro;
    }
}