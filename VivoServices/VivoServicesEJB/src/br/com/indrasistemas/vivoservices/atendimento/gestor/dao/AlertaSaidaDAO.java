package br.com.indrasistemas.vivoservices.atendimento.gestor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import br.com.indrasistemas.framework.service.dao.HibernateBaseDAO;
import br.com.indrasistemas.framework.service.dao.exceptions.DAOException;
import br.com.indrasistemas.framework.service.to.RequestInfoTO;
import br.com.indrasistemas.vivoservices.atendimento.gestor.to.AlertaSaidaTO;

public class AlertaSaidaDAO extends HibernateBaseDAO {

    private static final Log logger = LogFactory.getLog(AlertaSaidaDAO.class);

    public AlertaSaidaTO consultar(RequestInfoTO requestInfo, Long cdAreaRegistro, Long nrLinha) throws DAOException {
        StringBuffer sql = new StringBuffer();
        AlertaSaidaTO to = new AlertaSaidaTO();;
        String inPessoa = "";
        Connection conn = getJDBCConnection();
        PreparedStatement ps = null;

        try{
            // Consulta os dados do Cliente
            sql.append("SELECT p.idpessoa,  ");
            sql.append("       tc.sgtipocarteira,  ");
            sql.append("       nvl(seg.sgsegmentacao,'-1') sgsegmentacao,  ");
            sql.append("       ar.cdarearegistro || lb.nrlinha nrlinha,  ");
            sql.append("       pj.nmfantasia,  ");
            sql.append("       p.nmpessoa,  ");
            sql.append("       go.nmgrupooperadora, ");
            sql.append("       tip.sgtipopessoa ");
            sql.append("  FROM linha.linhabase lb, ");
            sql.append("       linha.linhatelefonica lt, ");
            sql.append("       apoio.arearegistro ar, ");
            sql.append("       customer.pessoalinha pl, ");
            sql.append("       customer.pessoadepara pdp, ");
            sql.append("       customer.pessoa p, ");
            sql.append("     customer.pessoasegmentacao pes, ");
            sql.append("       customer.pessoasegmentacaohistorico psh, ");
            sql.append("       apoio.tipocarteira tc, ");
            sql.append("       apoio.segmentacao seg, ");
            sql.append("       customer.ufoperadora ufo, ");
            sql.append("       customer.operadora ope, ");
            sql.append("       customer.grupooperadora go, ");
            sql.append("       customer.pessoajuridica pj, ");
            sql.append("       apoio.tipopessoa tip ");
            sql.append(" WHERE lt.idlinhatelefonica = pl.idlinhatelefonica ");
            sql.append("   AND pdp.idpessoadepara = pl.idpessoadepara ");
            sql.append("   AND pdp.idpessoadepara = pes.idpessoadepara(+) ");
            sql.append("   AND pes.idpessoasegmentacao = psh.idpessoasegmentacao(+) ");
            sql.append("   AND tc.idtipocarteira = p.idtipocarteira ");
            sql.append("   AND psh.idsegmentacao = seg.idsegmentacao(+) ");
            sql.append("   AND ufo.idufoperadora = ar.idufoperadora ");
            sql.append("   AND ope.idpessoadeparaoperadora = ufo.idpessoadeparaoperadora ");
            sql.append("   AND lb.idlinhabase = lt.idlinhabase ");
            sql.append("   AND pdp.idpessoa = p.idpessoa ");
            sql.append("   AND lb.idarearegistro = ar.idarearegistro ");
            sql.append("   AND go.idgrupooperadora = ope.idgrupooperadora ");
            sql.append("   AND p.idpessoa = pj.idpessoa (+) ");
            sql.append("   AND tip.idtipopessoa = p.idtipopessoa ");
            sql.append("   AND ar.cdarearegistro = ? ");
            sql.append("   AND lb.nrlinha = ? ");
            sql.append("   AND pl.idtiporelacionamento = 2 ");

            ps = conn.prepareStatement(sql.toString());
            ps.setLong(1, cdAreaRegistro.longValue());
            ps.setLong(2, nrLinha.longValue());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                to.setSgTipoCarteira(rs.getString("sgtipocarteira"));
                to.setSgSegmentacao(rs.getString("sgsegmentacao"));
                to.setNmFantasia(rs.getString("nmfantasia"));
                to.setRazaoSocial(rs.getString("nmpessoa"));
                to.setOperadora(rs.getString("nmgrupooperadora"));
                inPessoa = getValue(rs.getString("sgtipopessoa"));
            }
            if("PJ".equals(inPessoa)){
                //Consulta os dados do Gestor
                sql = new StringBuffer();
                sql.append("SELECT DISTINCT   ");
                sql.append("    PESSOAGC.IDPESSOA,  ");
                sql.append("    PESSOAGC.NMNOME,    ");
                sql.append("    PESSOAGC.NMNOMEMEIO,    ");
                sql.append("    PESSOAGC.NMSOBRENOME,  ");
                sql.append("    PESSOAGC.IDPESSOASISTEMAORIGEM,  ");
                sql.append("    DOCUMENTOGC.NRDOCUMENTO,  ");
                sql.append("    CPCCELULAR.DSCONTATO AS DSCELULAR,    ");
                sql.append("    CPCTELEFONE.DSCONTATO AS DSTELEFONE,    ");
                sql.append("    CPCEMAIL.DSCONTATO AS DSEMAIL,  ");
                sql.append("    USUARIOGC.NMLOGINUSUARIO,  ");
                sql.append("    TO_CHAR(PESSOAGC.DTULTIMAALTERACAO,'DD/MM/YYYY') AS DTULTIMAALTERACAO    ");
                sql.append("FROM  ");
                sql.append("    CUSTOMER.PESSOALINHA PESSOALINHA,  ");
                sql.append("    CUSTOMER.PESSOACONTA PESSOACONTA,  ");
                sql.append("    LINHA.LINHATELEFONICA LINHATELEFONICA,  ");
                sql.append("    LINHA.LINHABASE LINHABASE,  ");
                sql.append("    APOIO.AREAREGISTRO AREAREGISTRO,   ");
                sql.append("    CUSTOMER.PESSOACONTA PESSOACONTAGC,  ");
                sql.append("    CUSTOMER.PESSOADEPARA PESSOADEPARAGC,  ");
                sql.append("    CUSTOMER.PESSOA PESSOAGC,  ");
                sql.append("    CUSTOMER.PESSOACOMUNICACAO CPCCELULAR,  ");
                sql.append("    CUSTOMER.PESSOACOMUNICACAO CPCTELEFONE,  ");
                sql.append("    CUSTOMER.PESSOACOMUNICACAO CPCEMAIL,  ");
                sql.append("    CUSTOMER.PESSOADOCUMENTO PESSOADOCUMENTOGC,  ");
                sql.append("    CUSTOMER.DOCUMENTO DOCUMENTOGC,  ");
                sql.append("    ACESSO.USUARIO USUARIOGC,  ");
                sql.append("    APOIO.TIPOCOMUNICACAO ATCCELULAR,  ");
                sql.append("    APOIO.TIPOCOMUNICACAO ATCTELEFONE,  ");
                sql.append("    APOIO.TIPOCOMUNICACAO ATCEMAIL  ");
                sql.append("WHERE  ");
                sql.append("    AREAREGISTRO.CDAREAREGISTRO = ?  ");
                sql.append("AND LINHABASE.NRLINHA = ? ");
                sql.append("AND LINHABASE.IDLINHABASE = LINHATELEFONICA.IDLINHABASE  ");
                sql.append("AND LINHABASE.IDAREAREGISTRO = AREAREGISTRO.IDAREAREGISTRO  ");
                sql.append("AND LINHATELEFONICA.IDLINHATELEFONICA = PESSOALINHA.IDLINHATELEFONICA  ");
                sql.append("AND PESSOALINHA.IDTIPORELACIONAMENTO = 2  ");
                sql.append("AND PESSOALINHA.IDPESSOADEPARA = PESSOACONTA.IDPESSOADEPARA  ");
                sql.append("AND PESSOACONTA.IDTIPORELACIONAMENTO = 2  ");
                sql.append("AND NVL(PESSOACONTA.DTEXPIRACAO,SYSDATE+1) > SYSDATE  ");
                sql.append("AND PESSOACONTAGC.IDCONTA = PESSOACONTA.IDCONTA  ");
                sql.append("AND NVL(PESSOACONTAGC.DTEXPIRACAO,SYSDATE+1) > SYSDATE  ");
                sql.append("AND PESSOACONTAGC.IDTIPORELACIONAMENTO =   ");
                sql.append("                           (SELECT IDTIPORELACIONAMENTO   ");
                sql.append("                            FROM CUSTOMER.TIPORELACIONAMENTO  ");
                sql.append("                            WHERE SGTIPORELACIONAMENTO = 'GC')  ");
                sql.append("AND PESSOACONTAGC.IDPESSOADEPARA = PESSOADEPARAGC.IDPESSOADEPARA  ");
                sql.append("AND PESSOADEPARAGC.IDPESSOA = PESSOAGC.IDPESSOA  ");
                sql.append("AND PESSOAGC.IDUSUARIOALTERACAO = USUARIOGC.IDPESSOAUSUARIO(+)  ");
                sql.append("AND PESSOADEPARAGC.IDPESSOA = PESSOADOCUMENTOGC.IDPESSOA(+)  ");
                sql.append("AND PESSOADOCUMENTOGC.IDDOCUMENTO = DOCUMENTOGC.IDDOCUMENTO(+)  ");
                sql.append("AND PESSOADEPARAGC.IDPESSOA = CPCCELULAR.IDPESSOA(+)  ");
                sql.append("AND CPCCELULAR.IDTIPOCOMUNICACAO = ATCCELULAR.IDTIPOCOMUNICACAO(+)  ");
                sql.append("AND ATCCELULAR.SGTIPOCOMUNICACAO = 'CELULAR'   ");
                sql.append("AND PESSOADEPARAGC.IDPESSOA = CPCTELEFONE.IDPESSOA(+)  ");
                sql.append("AND CPCTELEFONE.IDTIPOCOMUNICACAO = ATCTELEFONE.IDTIPOCOMUNICACAO(+)  ");
                sql.append("AND ATCTELEFONE.SGTIPOCOMUNICACAO = 'GC-TEL'   ");
                sql.append("AND PESSOADEPARAGC.IDPESSOA = CPCEMAIL.IDPESSOA(+)  ");
                sql.append("AND CPCEMAIL.IDTIPOCOMUNICACAO = ATCEMAIL.IDTIPOCOMUNICACAO(+)  ");
                sql.append("AND ATCEMAIL.SGTIPOCOMUNICACAO = 'GC-EMAIL'  ");

                ps = conn.prepareStatement(sql.toString());
                ps.setLong(1, cdAreaRegistro.longValue());
                ps.setLong(2, nrLinha.longValue());
                rs = ps.executeQuery();
                if(rs.next()){
                    String nome = getValue(rs.getString("NMNOME"))+ " " + getValue(rs.getString("NMNOMEMEIO")) + " " + getValue(rs.getString("NMSOBRENOME"));
                    to.setNomeGestor(nome);
                    to.setNrLinha(rs.getString("DSCELULAR"));
                    to.setEmailGestor(rs.getString("DSEMAIL"));
                }
                
                //Consulta os dados do Gerente
                sql = new StringBuffer();
                sql.append("SELECT DISTINCT ");
                sql.append("    PESSOAGC.IDPESSOA, ");
                sql.append("    PESSOAGC.NMNOME,   ");
                sql.append("    PESSOAGC.NMNOMEMEIO,   ");
                sql.append("    PESSOAGC.NMSOBRENOME, ");
                sql.append("    PESSOAGC.IDPESSOASISTEMAORIGEM, ");
                sql.append("    DOCUMENTOGC.NRDOCUMENTO, ");
                sql.append("    CPCCELULAR.DSCONTATO AS DSCELULAR,   ");
                sql.append("    CPCTELEFONE.DSCONTATO AS DSTELEFONE,   ");
                sql.append("    CPCEMAIL.DSCONTATO AS DSEMAIL, ");
                sql.append("    USUARIOGC.NMLOGINUSUARIO, ");
                sql.append("    TO_CHAR(PESSOAGC.DTULTIMAALTERACAO,'DD/MM/YYYY') AS DTULTIMAALTERACAO   ");
                sql.append("FROM ");
                sql.append("    LINHA.LINHATELEFONICA LINHATELEFONICA, ");
                sql.append("    LINHA.LINHABASE LINHABASE, ");
                sql.append("    CUSTOMER.PESSOADOCUMENTO PESSOADOCUMENTO, ");
                sql.append("    CUSTOMER.PESSOALINHA PESSOALINHA, ");
                sql.append("    CUSTOMER.PESSOACONTA PESSOACONTA, ");
                sql.append("    CUSTOMER.PESSOADEPARA PESSOADEPARA, ");
                sql.append("    CUSTOMER.DOCUMENTO DOCUMENTO, ");
                sql.append("    APOIO.TIPODOCUMENTO TIPODOCUMENTO, ");
                sql.append("    APOIO.AREAREGISTRO AREAREGISTRO, ");
                sql.append("    CUSTOMER.PESSOACONTA PESSOACONTAGC, ");
                sql.append("    CUSTOMER.PESSOADEPARA PESSOADEPARAGC, ");
                sql.append("    CUSTOMER.PESSOA PESSOAGC, ");
                sql.append("    CUSTOMER.PESSOACOMUNICACAO CPCCELULAR, ");
                sql.append("    CUSTOMER.PESSOACOMUNICACAO CPCTELEFONE, ");
                sql.append("    CUSTOMER.PESSOACOMUNICACAO CPCEMAIL, ");
                sql.append("    CUSTOMER.PESSOADOCUMENTO PESSOADOCUMENTOGC, ");
                sql.append("    CUSTOMER.DOCUMENTO DOCUMENTOGC, ");
                sql.append("    ACESSO.USUARIO USUARIOGC, ");
                sql.append("    APOIO.TIPOCOMUNICACAO ATCCELULAR, ");
                sql.append("    APOIO.TIPOCOMUNICACAO ATCTELEFONE, ");
                sql.append("    APOIO.TIPOCOMUNICACAO ATCEMAIL ");
                sql.append("WHERE "); //-- CNPJ do cliente associado à linha carregada na tela inicial
                sql.append("    LINHATELEFONICA.IDLINHABASE = LINHABASE.IDLINHABASE ");
                sql.append("AND LINHABASE.IDAREAREGISTRO = AREAREGISTRO.IDAREAREGISTRO ");
                sql.append("AND AREAREGISTRO.CDAREAREGISTRO = ? ");
                sql.append("AND LINHABASE.NRLINHA = ? ");
                sql.append("AND LINHATELEFONICA.IDLINHATELEFONICA = PESSOALINHA.IDLINHATELEFONICA ");
                sql.append("AND PESSOALINHA.IDTIPORELACIONAMENTO = 2 ");
                sql.append("AND PESSOALINHA.IDPESSOADEPARA = PESSOADEPARA.IDPESSOADEPARA ");
                sql.append("AND NVL(PESSOADOCUMENTO.DTEXPIRACAO,SYSDATE+1) > SYSDATE ");
                sql.append("AND PESSOADEPARA.IDPESSOA = PESSOADOCUMENTO.IDPESSOA ");
                sql.append("AND PESSOADEPARA.IDPESSOADEPARA = PESSOACONTA.IDPESSOADEPARA ");
                sql.append("AND PESSOACONTA.IDTIPORELACIONAMENTO = 2 ");
                sql.append("AND NVL(PESSOACONTA.DTEXPIRACAO,SYSDATE+1) > SYSDATE ");
                sql.append("AND PESSOADOCUMENTO.IDDOCUMENTO = DOCUMENTO.IDDOCUMENTO ");
                sql.append("AND DOCUMENTO.IDTIPODOCUMENTO = TIPODOCUMENTO.IDTIPODOCUMENTO ");
                sql.append("AND TIPODOCUMENTO.SGCLASSIFICACAO = 'CNPJ' ");
                sql.append("AND PESSOACONTAGC.IDCONTA = PESSOACONTA.IDCONTA ");
                sql.append("AND NVL(PESSOACONTAGC.DTEXPIRACAO,SYSDATE+1) > SYSDATE ");
                sql.append("AND PESSOACONTAGC.IDTIPORELACIONAMENTO =  ");
                sql.append("                           (SELECT IDTIPORELACIONAMENTO  ");
                sql.append("                            FROM CUSTOMER.TIPORELACIONAMENTO ");
                sql.append("                            WHERE SGTIPORELACIONAMENTO = 'GRC') ");
                sql.append("AND PESSOACONTAGC.IDPESSOADEPARA = PESSOADEPARAGC.IDPESSOADEPARA ");
                sql.append("AND PESSOADEPARAGC.IDPESSOA = PESSOAGC.IDPESSOA ");
                sql.append("AND PESSOAGC.IDUSUARIOALTERACAO = USUARIOGC.IDPESSOAUSUARIO(+) ");
                sql.append("AND PESSOADEPARAGC.IDPESSOA = PESSOADOCUMENTOGC.IDPESSOA(+) ");
                sql.append("AND PESSOADOCUMENTOGC.IDDOCUMENTO = DOCUMENTOGC.IDDOCUMENTO(+) ");
                sql.append("AND PESSOADEPARAGC.IDPESSOA = CPCCELULAR.IDPESSOA(+) ");
                sql.append("AND CPCCELULAR.IDTIPOCOMUNICACAO = ATCCELULAR.IDTIPOCOMUNICACAO(+) ");
                sql.append("AND ATCCELULAR.SGTIPOCOMUNICACAO = 'CELULAR'  ");
                sql.append("AND PESSOADEPARAGC.IDPESSOA = CPCTELEFONE.IDPESSOA(+) ");
                sql.append("AND CPCTELEFONE.IDTIPOCOMUNICACAO = ATCTELEFONE.IDTIPOCOMUNICACAO(+) ");
                sql.append("AND ATCTELEFONE.SGTIPOCOMUNICACAO = 'GC-TEL'  ");
                sql.append("AND PESSOADEPARAGC.IDPESSOA = CPCEMAIL.IDPESSOA(+) ");
                sql.append("AND CPCEMAIL.IDTIPOCOMUNICACAO = ATCEMAIL.IDTIPOCOMUNICACAO(+) ");
                sql.append("AND ATCEMAIL.SGTIPOCOMUNICACAO = 'GC-EMAIL' ");
                
                ps = conn.prepareStatement(sql.toString());
                ps.setLong(1, cdAreaRegistro.longValue());
                ps.setLong(2, nrLinha.longValue());
                rs = ps.executeQuery();
                if(rs.next()){
                    String nome = getValue(rs.getString("NMNOME"))+ " " + getValue(rs.getString("NMNOMEMEIO")) + " " + getValue(rs.getString("NMSOBRENOME"));
                    to.setNomeGerente(nome);
                    to.setNrLinhaGr(rs.getString("DSCELULAR"));
                    to.setEmailGerente(rs.getString("DSEMAIL"));
                }
            }else{
                to.setNmFantasia(null);
                to.setRazaoSocial(null);
                to.setOperadora(null);
            }
        }catch(Exception ex){
            if(logger.isDebugEnabled()){
                logger.debug("Não foi possível realização a busca. " + ex.getMessage());
            }
        }finally{
            closePreparedStatement(ps);
            closeConnection(conn);
        }
        return to;
    }

    private String getValue(String value) {
        return(value != null ? value : "");
    }

}