package cliente.TelaInicial;

import br.com.vivo.fo.admsistemas.vo.ArvoreAtendimentoVODocument.ArvoreAtendimentoVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.usuario.vo.UsuarioVIVODocument.UsuarioVIVO;
import br.com.vivo.fo.workflow.vo.AtendimentoPesquisaVODocument.AtendimentoPesquisaVO;
import br.com.vivo.fo.workflow.vo.AtendimentoRelacionamentosVODocument.AtendimentoRelacionamentosVO;
import br.com.vivo.fo.workflow.vo.AtendimentoVODocument.AtendimentoVO;
import br.com.vivo.fo.workflow.vo.ProtocoloVODocument.ProtocoloVO;
import br.com.vivo.fo.workflow.vo.WFEstadoVODocument.WFEstadoVO;
import br.com.vivo.fo.workflow.vo.WFSubEstadoVODocument.WFSubEstadoVO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class RelacionamentoDAO {

    static final long serialVersionUID = 1L;
    private static Logger log = new Logger("fidelizacao");
    private static final String FILA_TPPESQ_CLIENTE = "1";
    private static final String FILA_TPPESQ_LINHA = "2";
    private static final String FILA_TPPESQ_PESSOA = "3";
    private static final String FILA_TPPESQ_LINHACLIENTE = "4";
    private static final String FILA_TPPESQ_CONTA = "5";

    public RelacionamentoDAO() {
    }

    private Connection getConnection() {
        Context ctx = null;
        Hashtable<String, String> ht = new Hashtable<String, String>();
        Connection conn = null;
        try {
            ht.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
            ctx = new InitialContext(ht);
            javax.sql.DataSource ds = (javax.sql.DataSource) ctx.lookup("jdbc.OracleDS");
            conn = ds.getConnection();
            while(conn.isClosed()) {
            	conn = ds.getConnection();
            }
            conn.setAutoCommit(false);
        
        } catch (SQLException e) {
            conn = null;
            log.error("SQLException::", e);
        
        } catch (NamingException e) {
            conn = null;
            log.error("NamingException::", e);
        }
        return conn;
    }

    public AtendimentoRelacionamentosVO getRelacionamentoExpurgo(String user, AtendimentoPesquisaVO atPesquisaVOgravar) {

        AtendimentoRelacionamentosVO at = null;
        try {
            Connection conn = null;
            Statement st = null;

            try {
                conn = getConnection();

                StringBuffer query = new StringBuffer("");
                query.append("SELECT ATENDIMENTOPROTOCOLO.IDATENDIMENTOPROTOCOLO, ");
                query.append("DECODE (ATENDIMENTOPROTOCOLO.NRTELEFONE, NULL, 'NÃO INFORMADO', ");
                query.append("'('||TRIM (TO_CHAR (ATENDIMENTOPROTOCOLO.CDAREAREGISTRO, '000'))||')'  ");
                query.append("||SUBSTR(TO_CHAR (LPAD (ATENDIMENTOPROTOCOLO.NRTELEFONE,9), '999999999'), 1, 6 )  ");
                query.append("||'-'|| SUBSTR (TO_CHAR (LPAD(ATENDIMENTOPROTOCOLO.NRTELEFONE, 9), '999999999'), 7, 9) ) AS NRTELEFONEPROTOCOLO, ");
                query.append("ATENDIMENTOPROTOCOLO.DSSTATUSPROTOCOLO, ");
                query.append("ATENDIMENTOPROTOCOLO.NMSISTEMAORIGEM, ");
                query.append("TO_CHAR (ATENDIMENTOPROTOCOLO.DTABERTURA, 'DD/MM/YYYY HH24:MI:SS') AS DTABERTURA, ");
                query.append("TO_CHAR (ATENDIMENTOPROTOCOLO.DTENCERRAMENTO, 'DD/MM/YYYY HH24:MI:SS') AS DTENCERRAMENTO ");
                query.append("FROM ");
                query.append("( ");
                query.append("SELECT ");
                query.append("ATENDIMENTOPROTOCOLO.IDATENDIMENTOPROTOCOLO, ");
                query.append("ATENDIMENTOPROTOCOLO.CDAREAREGISTRO, ");
                query.append("ATENDIMENTOPROTOCOLO.NRTELEFONE, ");
                query.append("ATENDIMENTOPROTOCOLO.NMSISTEMAORIGEM, ");
                query.append("ATENDIMENTOPROTOCOLO.DTABERTURA, ");
                query.append("ATENDIMENTOPROTOCOLO.DTENCERRAMENTO, ATENDIMENTOPROTOCOLO.DSSTATUSPROTOCOLO  ");
                query.append("FROM ");
                query.append("FOHIST_OW.ATENDIMENTOPROTOCOLO ATENDIMENTOPROTOCOLO ");
                query.append("");

                if (atPesquisaVOgravar.getIdContato() != null && !atPesquisaVOgravar.getIdContato().equals(ConstantesCRM.SVAZIO)) {
                    query.append(",FOHIST_OW.ATENDIMENTO ATENDIMENTO ");
                }

                query.append(" WHERE 1=1 ");

                if (atPesquisaVOgravar.getGrupoPesquisa().getTpPesquisa() != null
                        && !atPesquisaVOgravar.getGrupoPesquisa().getTpPesquisa().equals(ConstantesCRM.SVAZIO)) {

                    if (atPesquisaVOgravar.getGrupoPesquisa().getTpPesquisa().equals(FILA_TPPESQ_CLIENTE)
                            || atPesquisaVOgravar.getGrupoPesquisa().getTpPesquisa().equals(FILA_TPPESQ_LINHACLIENTE)) {

                        query.append(" AND ATENDIMENTOPROTOCOLO.IDLINHATELEFONICA = " + atPesquisaVOgravar.getGrupoPesquisa().getLinhaVO().getIdPessoaLinhaHistorico());

                        if ((atPesquisaVOgravar.getGrupoPesquisa().getIdPessoaDePara() != null && !atPesquisaVOgravar.getGrupoPesquisa().getIdPessoaDePara().equals(ConstantesCRM.SVAZIO)) && !atPesquisaVOgravar.getInPrimeiraChamada().equals(ConstantesCRM.SONE)) {
                            query.append(" AND ATENDIMENTOPROTOCOLO.IDPESSOADEPARA = " + atPesquisaVOgravar.getGrupoPesquisa().getIdPessoaDePara());
                        }
                    } else if (atPesquisaVOgravar.getGrupoPesquisa().getTpPesquisa().equals(FILA_TPPESQ_LINHA)) {
                        query.append(" AND ATENDIMENTOPROTOCOLO.CDAREAREGISTRO = " + atPesquisaVOgravar.getGrupoPesquisa().getLinhaVO().getNrLinha().substring(0, 2));
                        query.append(" AND ATENDIMENTOPROTOCOLO.NRTELEFONE = " + atPesquisaVOgravar.getGrupoPesquisa().getLinhaVO().getNrLinha().substring(2, atPesquisaVOgravar.getGrupoPesquisa().getLinhaVO().getNrLinha().length()));

                    } else if (atPesquisaVOgravar.getGrupoPesquisa().getTpPesquisa().equals(FILA_TPPESQ_PESSOA)) {
                        query.append(" AND ATENDIMENTOPROTOCOLO.IDPESSOADEPARA = " + atPesquisaVOgravar.getGrupoPesquisa().getIdPessoaDePara());
                    } else if (atPesquisaVOgravar.getGrupoPesquisa().getTpPesquisa().equals(FILA_TPPESQ_CONTA)) {
                        query.append(" AND ATENDIMENTOPROTOCOLO.CDCONTA = '" + atPesquisaVOgravar.getGrupoPesquisa().getNrConta() + "' ");
                    }
                }
                if (atPesquisaVOgravar.getNrProtocolo() != null && !atPesquisaVOgravar.getNrProtocolo().equals(ConstantesCRM.SVAZIO)) {
                    query.append(" AND ATENDIMENTOPROTOCOLO.IDATENDIMENTOPROTOCOLO=" + atPesquisaVOgravar.getNrProtocolo());
                }
                if (atPesquisaVOgravar.getDtAberturaInicio() != null && !atPesquisaVOgravar.getDtAberturaInicio().equals(ConstantesCRM.SVAZIO)) {
                    query.append(" AND ATENDIMENTOPROTOCOLO.DTABERTURA>=TO_DATE('" + atPesquisaVOgravar.getDtAberturaInicio() + " 00:00:00','DD/MM/YYYY HH24:MI:SS')");
                } else {
                    //REMOVER COMENTARIO
                    //query.append(" AND TRUNC(ATENDIMENTOPROTOCOLO.DTABERTURA) >= TRUNC(SYSDATE-180)");   
                }

                if (atPesquisaVOgravar.getDtAberturaFim() != null && !atPesquisaVOgravar.getDtAberturaFim().equals(ConstantesCRM.SVAZIO)) {
                    query.append(" AND ATENDIMENTOPROTOCOLO.DTABERTURA<=TO_DATE('" + atPesquisaVOgravar.getDtAberturaFim() + " 23:59:59','DD/MM/YYYY HH24:MI:SS')");
                }

                if (atPesquisaVOgravar.getDtFechamentoInicio() != null && !atPesquisaVOgravar.getDtFechamentoInicio().equals(ConstantesCRM.SVAZIO)) {
                    query.append(" AND ATENDIMENTOPROTOCOLO.DTENCERRAMENTO>=TO_DATE('" + atPesquisaVOgravar.getDtFechamentoInicio() + " 00:00:00','DD/MM/YYYY HH24:MI:SS')");
                }

                if (atPesquisaVOgravar.getDtFechamentoFim() != null && !atPesquisaVOgravar.getDtFechamentoFim().equals(ConstantesCRM.SVAZIO)) {
                    query.append(" AND ATENDIMENTOPROTOCOLO.DTENCERRAMENTO<=TO_DATE('" + atPesquisaVOgravar.getDtFechamentoFim() + " 23:59:59','DD/MM/YYYY HH24:MI:SS')");
                }

                if (atPesquisaVOgravar.getDsStatusProtocolo() != null && !atPesquisaVOgravar.getDsStatusProtocolo().equals(ConstantesCRM.SVAZIO)) {
                    query.append(" AND ATENDIMENTOPROTOCOLO.DSSTATUSPROTOCOLO = '" + atPesquisaVOgravar.getDsStatusProtocolo() + "'");
                }

                if (atPesquisaVOgravar.getIdContato() != null && !atPesquisaVOgravar.getIdContato().equals(ConstantesCRM.SVAZIO)) {
                    query.append(" AND ATENDIMENTO.IDATENDIMENTOPROTOCOLO=ATENDIMENTOPROTOCOLO.IDATENDIMENTOPROTOCOLO ");
                    query.append(" AND ATENDIMENTO.IDCONTATO=" + atPesquisaVOgravar.getIdContato());
                    //REMOVER COMENTARIO
                    //query.append(" AND TRUNC(ATENDIMENTO.DTABERTURA) >= TRUNC(SYSDATE-730)");
                }

                query.append(" ORDER BY ATENDIMENTOPROTOCOLO.DTABERTURA DESC ) ATENDIMENTOPROTOCOLO  ");

                if ((atPesquisaVOgravar.getIdAtendimento() == null || atPesquisaVOgravar.getIdAtendimento().equals(ConstantesCRM.SVAZIO)) && atPesquisaVOgravar.getInPrimeiraChamada() != null && !atPesquisaVOgravar.getInPrimeiraChamada().equals(ConstantesCRM.SVAZIO)) {
                    if (atPesquisaVOgravar.getInPrimeiraChamada().equals(ConstantesCRM.SONE)) {
                        query.append(" WHERE ROWNUM <= 10");
                    }
                }

                st = conn.createStatement();
                ResultSet rs = st.executeQuery(query.toString());

                at = AtendimentoRelacionamentosVO.Factory.newInstance();
                int i = 0;
                //for(int i=0;i<15;i++){
                while (rs.next()) {
                    ProtocoloVO protocolo = ProtocoloVO.Factory.newInstance();
                    protocolo.setNrProtocolo(rs.getString("IDATENDIMENTOPROTOCOLO"));
                    //protocolo.setNrProtocolo("987654");
                    protocolo.setNrLinha(rs.getString("NRTELEFONEPROTOCOLO"));
                    //protocolo.setNrLinha("1195257318");
                    protocolo.setDsEstado(rs.getString("DSSTATUSPROTOCOLO"));
                    //protocolo.setDsEstado("status");
                    protocolo.setNmSistema(rs.getString("NMSISTEMAORIGEM"));
                    //protocolo.setNmSistema("vivonet");
                    protocolo.setDtAbertura(rs.getString("DTABERTURA"));
                    //protocolo.setDtAbertura("01/01/2008");
                    protocolo.setDtFechamento(rs.getString("DTENCERRAMENTO"));
                    //protocolo.setDtFechamento("02/02/2008");

                    at.addNewProtocoloVO();
                    at.setProtocoloVOArray(i, protocolo);
                    i++;
                }
                //}
                rs.close();
            } catch (SQLException e) {
                log.error("SQLException::", e);
            } finally {
                try {
                    if (st != null) {
                        st.close();
                    }
                    conn.close();
                    st = null;
                    conn = null;
                } catch (SQLException e1) {
                    log.error("SQLException::ps.close", e1);
                }
            }
        } catch (Exception e) {
            log.error("Exception::", e);
        }
        return at;
    }

    public AtendimentoRelacionamentosVO getAtendimentoPorProtocolo(String user, AtendimentoRelacionamentosVO atendimento, AtendimentoPesquisaVO atPesquisaVO) throws FacadeException {
        try {
            Connection conn = null;
            Statement st = null;

            if (atendimento != null) {
                if (atendimento.getProtocoloVOArray() != null && atendimento.getProtocoloVOArray().length > 0) {
                    for (int j = 0; j < atendimento.getProtocoloVOArray().length; j++) {
                        try {
                            conn = getConnection();

                            StringBuffer query = new StringBuffer("");

                            query.append("SELECT ATENDIMENTO.IDATENDIMENTOPROTOCOLO, ");
                            query.append("ATENDIMENTO.IDATENDIMENTO, ");
                            query.append("ATENDIMENTO.DSESTADO, ");
                            query.append("ATENDIMENTO.DSSUBESTADO, ");
                            query.append("ATENDIMENTO.NMPATH, ");
                            query.append("TO_CHAR(ATENDIMENTO.DTABERTURA,'DD/MM/YYYY HH24:MI:SS') as DTABERTURA, ");
                            query.append("TO_CHAR(ATENDIMENTO.DTFECHAMENTO,'DD/MM/YYYY HH24:MI:SS') as DTFECHAMENTO, ATENDIMENTO.NMLOGINABERTURA ");
                            query.append("FROM FOHIST_OW.ATENDIMENTO ATENDIMENTO ");
                            //query.append("FOHIST_OW.ATENDIMENTOPROTOCOLO ATENDIMENTOPROTOCOLO, "); 
                            //query.append("FOHIST_OW.ATENDIMENTOPESSOA ATENDIMENTOPESSOA ");
                            query.append("WHERE ATENDIMENTO.IDATENDIMENTOPROTOCOLO = " + atendimento.getProtocoloVOArray(j).getNrProtocolo());
                            //query.append(" AND ATENDIMENTO.IDATENDIMENTO = ATENDIMENTOPESSOA.IDATENDIMENTO ");
                            //query.append("AND ATENDIMENTOPESSOA.IDTIPORELACIONAMENTO > 1 ");
                            //query.append("AND ATENDIMENTO.IDATENDIMENTOPROTOCOLO = ATENDIMENTOPROTOCOLO.IDATENDIMENTOPROTOCOLO ");
                            //REMOVER COMENTARIO
                            //query.append(" AND TRUNC(ATENDIMENTO.DTABERTURA) >= TRUNC(SYSDATE-730) ");

                            if (atPesquisaVO.getIdContato() != null && !atPesquisaVO.getIdContato().equals(ConstantesCRM.SVAZIO)) {
                                query.append(" AND ATENDIMENTO.IDCONTATO = " + atPesquisaVO.getIdContato());
                            }

                            //if((atPesquisaVO.getGrupoPesquisa().getIdPessoaDePara()!=null && !atPesquisaVO.getGrupoPesquisa().getIdPessoaDePara().equals("")) && !atPesquisaVO.getInPrimeiraChamada().equals("1")){     
                            //query.append(" AND ATENDIMENTOPESSOA.IDPESSOADEPARA = " + atPesquisaVO.getGrupoPesquisa().getIdPessoaDePara());
                            //}      
                            //if(atPesquisaVO.getGrupoPesquisa().getTpPesquisa()!=null && 
                            //!atPesquisaVO.getGrupoPesquisa().getTpPesquisa().equals("")){
                            //if(atPesquisaVO.getGrupoPesquisa().getTpPesquisa().equals(FILA_TPPESQ_CLIENTE )){
                            //query.append(" AND ATENDIMENTOPESSOA.IDTIPORELACIONAMENTO > 0 ");
                            //}else{
                            //query.append(" AND ATENDIMENTOPESSOA.IDTIPORELACIONAMENTO = 2 ");
                            //}
                            //}
                            //if(atPesquisaVO.getDsStatusProtocolo()!=null && !atPesquisaVO.getDsStatusProtocolo().equals("")){
                            //query.append(" AND ATENDIMENTO.DSESTADO = " + atPesquisaVO.getDsStatusProtocolo());
                            //}

                            query.append(" ORDER BY ATENDIMENTO.DTABERTURA DESC");

                            st = conn.createStatement();
                            ResultSet rs = st.executeQuery(query.toString());

                            int i = 0;

                            while (rs.next()) {
                                //for(int i=0;i<2;i++){
                                AtendimentoVO vo = AtendimentoVO.Factory.newInstance();
                                WFEstadoVO estado = WFEstadoVO.Factory.newInstance();
                                WFSubEstadoVO subEstado = WFSubEstadoVO.Factory.newInstance();
                                ArvoreAtendimentoVO arvore = ArvoreAtendimentoVO.Factory.newInstance();
                                UsuarioVIVO usuario = UsuarioVIVO.Factory.newInstance();

                                vo.setIdAtendimento(rs.getString("IDATENDIMENTO"));
                                //vo.setIdAtendimento(new Integer("123").intValue());

                                estado.setDsEstado(rs.getString("DSESTADO"));
                                //estado.setDsEstado("estado");
                                vo.setWFEstadoVO(estado);

                                subEstado.setDsSubEstado(rs.getString("DSSUBESTADO"));
                                //subEstado.setDsSubEstado("subestado");
                                vo.setWFSubEstadoVO(subEstado);

                                arvore.setDescricaoCompleta(rs.getString("NMPATH"));
                                //arvore.setDescricaoCompleta("a/b/c");
                                vo.setArvoreAtendimentoVO(arvore);

                                vo.setDtAbertura(rs.getString("DTABERTURA"));
                                //vo.setDtAbertura("10/10/2009");
                                vo.setDtFechamento(rs.getString("DTFECHAMENTO"));
                                //vo.setDtFechamento("11/10/2009");
                                //NAO HA EXPLICAÇAO PARA ESSE CAMPO, SEMPRE SERA ZERO
                                vo.setInACS(ConstantesCRM.SZERO);

                                usuario.setNmLoginUsuario(rs.getString("NMLOGINABERTURA"));
                                //usuario.setNmLoginUsuario("Glauber");
                                vo.setUsuarioVIVO(usuario);

                                atendimento.getProtocoloVOArray(j).addNewAtendimentoVO();
                                atendimento.getProtocoloVOArray(j).setAtendimentoVOArray(i, vo);

                                i++;
                            }
                            //}
                            rs.close();
                        } catch (SQLException e) {
                            log.error("SQLException::", e);
                        } finally {
                            try {
                                if (st != null) {
                                    st.close();
                                }
                                //conn.close();
                                st = null;
                                conn = null;
                            } catch (SQLException e1) {
                                log.error("SQLException::ps.close", e1);
                            }
                        }
                    }
                }

            }
        } catch (Exception e) {
            log.error("Exception::", e);
        }
        return atendimento;
    }
}
