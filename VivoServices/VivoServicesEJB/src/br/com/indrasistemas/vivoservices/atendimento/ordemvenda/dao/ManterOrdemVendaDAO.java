package br.com.indrasistemas.vivoservices.atendimento.ordemvenda.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.indrasistemas.framework.service.application.ApplicationServiceException;
import br.com.indrasistemas.framework.service.dao.DAOExceptionBuilder;
import br.com.indrasistemas.framework.service.dao.HibernateBaseDAO;
import br.com.indrasistemas.framework.service.dao.exceptions.DAOException;
import br.com.indrasistemas.vivoservices.atendimento.ordemvenda.to.PalitagemTO;

public class ManterOrdemVendaDAO extends HibernateBaseDAO {

    private static final Log logger = LogFactory.getLog(ManterOrdemVendaDAO.class);

    private boolean buscarCdParametro(PalitagemTO palitagemTO) throws DAOException {

        boolean retorno = false;
        StringBuffer sql = new StringBuffer();
        String cdStatusRejeicao = new String(palitagemTO.getErrorCode());

        sql.append("SELECT DSVALORPARAMETRO");
        sql.append(" FROM APOIO.PARAMETRO ");
        sql.append("WHERE CDPARAMETRO='NFE_CODREJEICAO'");

        Connection conn = getJDBCConnection();
        PreparedStatement ps = null;

        if (logger.isDebugEnabled()) {
            logger.debug("Valor procurado=(" + cdStatusRejeicao + ")");
        }

        try {
            ps = conn.prepareStatement(sql.toString());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String dsValorParametro = new String(rs.getString(1));

                if (logger.isDebugEnabled()) {
                    logger.debug("Valor retornado=(" + dsValorParametro + ")");
                }

                int index = dsValorParametro.indexOf(",");
                if (index > 0) {
                    while (index > 0) {
                        String valor = new String(dsValorParametro.substring(0, index));

                        if (logger.isDebugEnabled()) {
                            logger.debug("token=(" + valor + ")");
                        }

                        if (index + 1 < dsValorParametro.length()) {
                            dsValorParametro = dsValorParametro.substring(index + 1);
                            index = dsValorParametro.indexOf(",");
                        } else {
                            index = 0;
                        }

                        if (valor.equals(cdStatusRejeicao)) {
                            retorno = true;
                            break;
                        }
                    }
                }
                if (dsValorParametro.length() > 0) {
                    if (dsValorParametro.equals(cdStatusRejeicao)) {
                        retorno = true;
                    }
                }

                if (retorno == true) {
                    palitagemTO.setCdParametro(new String("ContatoRejeitarOrdemVenda"));
                } else {
                    palitagemTO.setCdParametro(new String("ContatoDenegarOrdemVenda"));
                }

                if (logger.isDebugEnabled()) {
                    logger.debug("Parametro a ser consultado=(" + palitagemTO.getCdParametro() + ")");
                }
            }

            // retorno = true;

        } catch (Exception ex) {
            throw DAOExceptionBuilder.build(ex);
        } finally {
            closePreparedStatement(ps);
            closeConnection(conn);
        }

        return retorno;
    }

    private boolean buscarCdFuncionalidadeTelefone(PalitagemTO palitagemTO) throws DAOException {

        boolean retorno = false;

        StringBuffer sql = new StringBuffer("SELECT VLLOGXMLIN FROM RETENCAO.STATUSSAP WHERE ORDEMVENDA = '"+palitagemTO.getNrOrdemVenda()+"' AND ROWNUM < 2");

        Connection conn = getJDBCConnection();
        PreparedStatement ps = null;

        if (logger.isDebugEnabled()) {
            logger.debug("ordem venda=(" + palitagemTO.getNrOrdemVenda() + ")");
        }

        try {
            ps = conn.prepareStatement(sql.toString());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String xml = new String(rs.getString(1));

                if (logger.isDebugEnabled()) {
                    logger.debug("Valor retornado=(" + xml + ")");
                }

                int index = xml.indexOf("<TIPO_DOC>CPF</TIPO_DOC>");

                if (logger.isDebugEnabled()) {
                    logger.debug("index <TIPO_DOC>CPF</TIPO_DOC> =(" + index + ")");
                }

                if (index > 0) {
                    if (palitagemTO.getCdParametro().equals("ContatoRejeitarOrdemVenda") ) {
                        palitagemTO.setCdFuncionalidade(new String("NFE_REGCONTATOREJPF"));
                    } else {
                        palitagemTO.setCdFuncionalidade(new String("NFE_REGCONTATODENPF"));
                    }
                } else {
                    if (palitagemTO.getCdParametro().equals("ContatoRejeitarOrdemVenda") ) {
                        palitagemTO.setCdFuncionalidade(new String("NFE_REGCONTATOREJPJ"));
                    } else {
                        palitagemTO.setCdFuncionalidade(new String("NFE_REGCONTATODENPJ"));
                    }
                }
                palitagemTO.setCdServico(palitagemTO.getCdFuncionalidade());

                // -------------------------------------
                // Numero do telefone do cliente da OV
                // -------------------------------------

                int index0 = xml.indexOf("<TELEFONE>");
                int index1 = xml.indexOf("</TELEFONE>");

                if (logger.isDebugEnabled()) {
                    logger.debug("index <TELEFONE> =(" + index0 + ")");
                    logger.debug("index </TELEFONE> =(" + index1 + ")");
                    logger.debug("bloco= =(" + xml.substring(index0,index1) + ")");
                }

                if (index0 > 0) {
                    int tam = (index1 - index0 - 10);
                    int pos0 = index0+10;
                    String nrTelefone = new String(xml.substring(pos0,pos0+tam));

                    if (logger.isDebugEnabled()) {
                        logger.debug("nrTelefone = (" + nrTelefone + ")");
                    }
                    palitagemTO.setNrTelefone(nrTelefone);
                }

                retorno = true;
            }
            //else {
            //    palitagemTO.setErrorCodeDAO("1");
            //    palitagemTO.setErrorMessageDAO("Ordem de venda informada não existe.");
            //    
            //    //throw new Exception("Ordem de venda informada não existe.");
            //}

        } catch (Exception ex) {
            throw DAOExceptionBuilder.build(ex);
        } finally {
            closePreparedStatement(ps);
            closeConnection(conn);
        }

        return retorno;
    }

    public PalitagemTO buscarParametros(PalitagemTO palitagemTO) throws DAOException {
        buscarCdParametro(palitagemTO);
        buscarCdFuncionalidadeTelefone(palitagemTO);

        StringBuffer sql = new StringBuffer();

        if (logger.isDebugEnabled()) {
            logger.debug("cdParametro = (" + palitagemTO.getCdParametro() + ")");
        }

        sql.append("SELECT ");
        sql.append("CONTATO.IDCONTATO,");
        sql.append("CONTATO.NMPATH");
        sql.append(" FROM ");
        sql.append("APOIO.PARAMETRO PARAMETRO,");
        sql.append("CONTATOADM.CONTATO CONTATO ");
        sql.append("WHERE PARAMETRO.CDPARAMETRO='" + palitagemTO.getCdParametro() + "' ");
        sql.append("AND PARAMETRO.DSVALORPARAMETRO = CONTATO.NMPATH");

        Connection conn = getJDBCConnection();
        PreparedStatement ps = null;

        try {
            //
            // Path correpondente ao tipo de ação da ordem de venda
            //
            ps = conn.prepareStatement(sql.toString());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                palitagemTO.setIdContato(new Long(rs.getLong(1)));
                palitagemTO.setNmPath(new String(rs.getString(2)));
                
                if (logger.isDebugEnabled()) {
                    logger.debug("idContato = (" + palitagemTO.getIdContato() + ")");
                    logger.debug("nmPath = (" + palitagemTO.getNmPath() + ")");
                }
            }

            closePreparedStatement(ps);
            ps = null;
            // rs.close();
            //
            // Parametros para a abertura do processo
            //
            if (logger.isDebugEnabled()) {
                logger.debug("cdFuncionalidade = (" + palitagemTO.getCdFuncionalidade() + ")");
            }
            
            sql = new StringBuffer();
            sql.append("SELECT ");
            sql.append("IDCANAL,IDPROCEDENCIA,IDSISTEMAORIGEM");
            sql.append(" FROM ");
            sql.append("CONTATOADM.CONTATOFUNCIONALIDADE");
            sql.append(" WHERE SGSUBSISTEMA = 'FO' ");
            sql.append("AND CDFUNCIONALIDADE = '" + palitagemTO.getCdFuncionalidade() + "'");

            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();

            if (rs.next()) {
                palitagemTO.setIdCanal(new String(rs.getString(1)==null?"":rs.getString(1)));
                palitagemTO.setIdProcedencia(new String(rs.getString(2)==null?"":rs.getString(2)));
                palitagemTO.setIdSistema(new String(rs.getString(3)==null?"":rs.getString(3)));

                if (logger.isDebugEnabled()) {
                    logger.debug("idCanal = (" + palitagemTO.getIdCanal() + ")");
                    logger.debug("idProcedencia = (" + palitagemTO.getIdProcedencia() + ")");
                    logger.debug("idSistema = (" + palitagemTO.getIdSistema() + ")");
                }
            }
            closePreparedStatement(ps);
            ps = null;
            //
            // Grupo de Abertura
            //
            sql = new StringBuffer();
            sql.append("SELECT ");
            sql.append("IDGRUPO");
            sql.append(" FROM ");
            sql.append("(");
            sql.append("SELECT ");
            sql.append("IDGRUPO,SQORDEM");
            sql.append(" FROM ");
            sql.append("(");
            sql.append("SELECT ");
            sql.append("CONTATOGRUPO.IDGRUPO,SEQUENCIA.SQORDEM");
            sql.append(" FROM ");
            sql.append("CONTATOADM.CONTATOGRUPO CONTATOGRUPO,");
            sql.append("CONTATOADM.SEQUENCIA SEQUENCIA");
            sql.append(" WHERE ");
            sql.append("CONTATOGRUPO.IDCONTATOGRUPO = SEQUENCIA.IDCONTATOGRUPO ");
            sql.append("AND SEQUENCIA.IDTIPOSEQUENCIA = 1 ");
            sql.append("AND CONTATOGRUPO.IDCONTATO = " + palitagemTO.getIdContato());
            sql.append(" UNION ALL ");
            sql.append("SELECT ");
            sql.append("IDGRUPO,999 AS SQORDEM");
            sql.append(" FROM ");
            sql.append("ACESSO.GRUPO ");
            sql.append("WHERE NMGRUPO = 'VOL'");
            sql.append(")");
            sql.append(" ORDER BY SQORDEM ");
            sql.append(")");
            sql.append(" WHERE ROWNUM < 2");

            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();

            if (rs.next()) {
                palitagemTO.setIdGrupoAbertura(new String(rs.getString(1)));
            }

        } catch (Exception ex) {
            throw DAOExceptionBuilder.build(ex);
        } finally {
            closePreparedStatement(ps);
            closeConnection(conn);
        }
        return palitagemTO;
    }
}