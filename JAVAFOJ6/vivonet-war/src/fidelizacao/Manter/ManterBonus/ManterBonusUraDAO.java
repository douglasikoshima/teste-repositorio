package fidelizacao.Manter.ManterBonus;

import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoVODocument.FidelizacaoVO;
import br.com.vivo.fo.fidelizacao.vo.ItDocument.It;
import br.com.vivo.fo.log.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import weblogic.jdbc.vendor.oracle.OraclePreparedStatement;

public class ManterBonusUraDAO {

    static final long serialVersionUID = 1L;

    private static Logger log = new Logger("fidelizacao");

    public ManterBonusUraDAO(){
    }

    private Connection getConnection(){
        Context     ctx = null;
        Hashtable    ht = new Hashtable();
        Connection conn = null;
        try{
            ht.put( Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
            ctx = new InitialContext(ht);
            javax.sql.DataSource ds = (javax.sql.DataSource) ctx.lookup("jdbc.OracleDS");
            conn = ds.getConnection();
            while(conn.isClosed()) {
            	conn = ds.getConnection();
            }
            conn.setAutoCommit(false);
        }catch(SQLException e){
            conn = null;
            log.error("SQLException::", e);
        }catch(NamingException e){
            conn = null;
            log.error("NamingException::", e);
        }finally{
            return conn;
        }
    }

    private long getIdBonus(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT retencao.bonussq.nextval FROM dual");
        if(rs.next()){
            return rs.getLong(1);
        }else{
            return 0;
        }
    }

    private boolean insertBonus(Connection conn, long idUser, long idBonus, FidelizacaoVO xml){
        boolean      success = false;
        PreparedStatement ps = null;
        try{
            StringBuffer qryInsert = new StringBuffer("insert into retencao.bonus ");
            qryInsert.append(" (idbonus, dsbonus, nrvalidade, idunidadeoferta, idtipolinha, cdservico, vlbonus, idtiposervico, idtipogrupopacote, idusuarioalteracao, dtalteracao, inativo, inura) ");
            qryInsert.append(" values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, sysdate, 1, 1) ");
            ps = conn.prepareStatement(qryInsert.toString());
            ps.setLong(1, idBonus); //idbonus
            ps.setString(2, xml.getManter().getNmBonus()); //dsbonus
            ps.setLong(3, Long.parseLong(xml.getManter().getVdBonus())); //nrvalidade

            if(xml.getManter().getTpBonus()!=null){
                ps.setLong(4, Long.parseLong(xml.getManter().getTpBonus()) ); //idunidadeoferta
            }else{
                ps.setLong(4, Long.parseLong("100001010") ); //idunidadeoferta
            }

            ps.setLong(5, Long.parseLong(xml.getManter().getTpLinha())); //idtipolinha
            ps.setString(6, xml.getManter().getCdServico()); //cdservico

            if(xml.getManter().getVlBonus()!=null && !ConstantesCRM.SVAZIO.equals(xml.getManter().getVlBonus())){
                ps.setDouble(7, Double.parseDouble(xml.getManter().getVlBonus()) ); //vlbonus
            }else{
                ps.setNull(7, Types.DOUBLE); //vlbonus
            }

            if(xml.getManter().getTpServico()!=null && !ConstantesCRM.SVAZIO.equals(xml.getManter().getTpServico())){
                ps.setLong(8, Long.parseLong(xml.getManter().getTpServico()) ); //idtiposervico
            }else{
                ps.setLong(8, Long.parseLong(ConstantesCRM.SONE) ); //idtiposervico
            }

            ps.setLong(9, Long.parseLong(xml.getManter().getIdGrpPacote())); //idtipogrupopacote
            ps.setLong(10, idUser); //idusuarioalteracao

            ps.executeUpdate();

            conn.commit();
            success = true;
        }catch(SQLException e){
            log.error("SQLException::", e);
            try{
                conn.rollback();
            }catch(SQLException e1){
                log.error("SQLException::rollback", e);
            }
        }finally{
            try{
                ps.close();
                ps = null;
            }catch(SQLException e1){
                log.error("SQLException::ps.close", e1);
            }
            return success;
        }
    }

    public FidelizacaoVO setBonus(String user, FidelizacaoVO xml) throws FacadeException {
        FidelizacaoVO out = FidelizacaoVO.Factory.newInstance();
        try{
            Connection      conn = null;
            PreparedStatement ps = null;
            ArrayList    aListUF = new ArrayList();
            ArrayList    aListSg = new ArrayList();
            try{
                long idUser  = Long.parseLong(user);
                long idBonus = 0;
                long idUF    = 0;
                long idSegm  = 0;

                conn = getConnection();

                try{
                    idBonus = getIdBonus(conn);
                }catch(SQLException e){
                    log.error("SQLException:: ", e);
                    idBonus = 0;
                }

                if(idBonus!=0 && insertBonus(conn, idUser, idBonus, xml) && conn!=null ){
                    ps = conn.prepareStatement("insert into retencao.matrizbonusura values (RETENCAO.MATRIZBONUSURASQ.NEXTVAL, ?, ?, ?, 1, ?, sysdate)");
                    ((OraclePreparedStatement)ps).setExecuteBatch(100);
                    int iTam = xml.getListasVO().getListaArray().length;
                    for(int i=0;i<iTam;i++){
                        if("REGIONAL".equals(xml.getListasVO().getListaArray(i).getNmSelect())){
                            int jTam = xml.getListasVO().getListaArray(i).getSelecionado().getItArray().length;
                            for(int j=0;j<jTam;j++){
                                aListUF.add(xml.getListasVO().getListaArray(i).getSelecionado().getItArray(j).getId());
                            }
                        }else if("SEGMENTACAO".equals(xml.getListasVO().getListaArray(i).getNmSelect())){
                            int jTam = xml.getListasVO().getListaArray(i).getSelecionado().getItArray().length;
                            for(int j=0;j<jTam;j++){
                                aListSg.add(xml.getListasVO().getListaArray(i).getSelecionado().getItArray(j).getId());
                            }
                        }
                    }

                    for(int i=0;i<aListUF.size();i++){
                        idUF = Long.parseLong((String) aListUF.get(i));
                        for(int j=0;j<aListSg.size();j++){
                            idSegm = Long.parseLong((String) aListSg.get(j));
                            ps.setLong(1, idUF);
                            ps.setLong(2, idBonus);
                            ps.setLong(3, idSegm);
                            ps.setLong(4, idUser);
                            ps.executeUpdate(); //JDBC queues this for later execution
                        }
                    }

                    ((OraclePreparedStatement)ps).sendBatch(); //JDBC sends the queued request
                    conn.commit();
                }
            }catch(SQLException e){
                log.error("SQLException:: ", e);
                try{
                    conn.rollback();
                }catch(SQLException e1){
                    log.error("SQLException::rollback", e1);
                }
            }finally{
                try{
                    if(ps!=null) ps.close();
                    conn.close();
                    ps = null;
                    conn = null;
                }catch(SQLException e1){
                    log.error("SQLException::ps.close", e1);
                }
            }

        }catch(Exception e){
            log.error("Exception::", e);
            throw (new FacadeException(e));
        }
        return out;
    }

    public FidelizacaoVO getBonus(String user, long idBonus){
        FidelizacaoVO vo = FidelizacaoVO.Factory.newInstance();
        try{
            Connection      conn = null;
            PreparedStatement ps = null;
            try{
                conn = getConnection();

                //Pesquisa os dados da tabela BONUS
                StringBuffer query = new StringBuffer("select idbonus, dsbonus, nrvalidade, idtipolinha, cdservico, vlbonus, idtiposervico, idtipogrupopacote ");
                query.append("from retencao.bonus where inura = 1 and inativo = 1 and idbonus = ? ");

                ps = conn.prepareStatement(query.toString());
                ps.setLong(1, idBonus);

                ResultSet rs = ps.executeQuery();

                vo = FidelizacaoVO.Factory.newInstance();
                vo.addNewManter();
                if(rs.next()){
                    FidelizacaoVO.Manter manter = vo.getManter();
                    manter.setIdCadastrado(rs.getString("idbonus"));
                    manter.setNmBonus(rs.getString("dsbonus"));
                    manter.setVdBonus(rs.getString("nrvalidade"));
                    manter.setTpLinha(rs.getString("idtipolinha"));
                    manter.setCdServico(rs.getString("cdservico"));
                    manter.setVlBonus(rs.getString("vlbonus"));
                    manter.setTpServico(rs.getString("idtiposervico"));
                    manter.setIdGrpPacote(rs.getString("idtipogrupopacote"));

                    //Preparacao do VO para as listas
                    vo.addNewListasVO();

                    //Pesquisa os dados de Regionais da Tabela MatrizBonusURA
                    query = new StringBuffer("select distinct mbu.idufoperadora, uf.nmuf from retencao.matrizbonusura mbu, customer.ufoperadora ufo, apoio.uf uf ");
                    query.append("where mbu.idufoperadora = ufo.idufoperadora and mbu.inativo = 1 and ufo.iduf = uf.iduf ");
                    query.append("and mbu.idbonus = ? ");

                    ps = conn.prepareStatement(query.toString());
                    ps.setLong(1, idBonus);

                    rs = ps.executeQuery();

                    FidelizacaoVO.ListasVO.Lista lista = vo.getListasVO().addNewLista();
                    lista.setNmSelect("REGIONAL");
                    lista.addNewSelecionado();

                    while(rs.next()){
                        It it = lista.getSelecionado().addNewIt();
                        it.setId(rs.getString("idufoperadora"));
                        it.setDs(rs.getString("nmuf"));
                    }

                    //Pesquisa os dados de Segmentacao da Tabela MatrizBonusURA
                    query = new StringBuffer("select distinct mbu.idsegmentacao, seg.dssegmentacao from retencao.matrizbonusura mbu, apoio.segmentacao seg ");
                    query.append("where inativo = 1 and mbu.idsegmentacao = seg.idsegmentacao ");
                    query.append("and idbonus = ? ");

                    ps = conn.prepareStatement(query.toString());
                    ps.setLong(1, idBonus);

                    rs = ps.executeQuery();

                    lista = vo.getListasVO().addNewLista();
                    lista.setNmSelect("SEGMENTACAO");
                    lista.addNewSelecionado();

                    while(rs.next()){
                        It it = lista.getSelecionado().addNewIt();
                        it.setId(rs.getString("idsegmentacao"));
                        it.setDs(rs.getString("dssegmentacao"));
                    }

                }
            }catch(SQLException e){
                log.error("SQLException::", e);
            }finally{
                try{
                    if(ps!=null) ps.close();
                    conn.close();
                    ps = null;
                    conn = null;
                }catch(SQLException e1){
                    log.error("SQLException::ps.close", e1);
                }
            }
        }catch(Exception e){
            log.error("Exception::", e);
        }
        return vo;
    }

    public FidelizacaoVO listBonus(String user, FidelizacaoVO xml){
        FidelizacaoVO vo = null;
        String[]   aList = {"","05 Dias","15 Dias","30 Dias","45 Dias","60 Dias","Sem Validade"};
        try{
            Connection conn = null;
            Statement    st = null;
            try{
                conn = getConnection();

                StringBuffer query = new StringBuffer(ConstantesCRM.SVAZIO);
                query.append("SELECT distinct b.idbonus, b.dsbonus, t.dsTipogrupopacote, b.nrvalidade ");
                query.append("FROM retencao.bonus b, retencao.matrizbonusura u, retencao.tipogrupopacote t ");
                query.append("WHERE b.inativo = 1 and u.inativo = 1 and b.inUra = 1 and b.idbonus = u.idbonus ");
                query.append("  AND t.idTipoGrupoPacote = b.idTipogrupopacote ");

                //Caso seja selecionado algum filtro de Regional
                if(!"".equals(xml.getFiltro().getIdRegional())){
                    query.append(" AND u.idufoperadora = ").append(xml.getFiltro().getIdRegional());
                }

                //Caso seja selecionado algum filtro de Segmentação
                if(!"".equals(xml.getFiltro().getIdSegmento())){
                    query.append(" AND u.idsegmentacao = ").append(xml.getFiltro().getIdSegmento());
                }

                //Caso seja selecionado algum filtro de Tipo de Linha
                if(!"".equals(xml.getFiltro().getTpLinha())){
                    query.append(" AND b.idtipolinha = ").append(xml.getFiltro().getTpLinha());
                }

                //Caso seja selecionado algum filtro de Grupo de Pacote
                if(!"".equals(xml.getFiltro().getIdGrpPacote())){
                    query.append(" AND b.idtipogrupopacote = ").append(xml.getFiltro().getIdGrpPacote());
                }

                st = conn.createStatement();
                ResultSet rs = st.executeQuery(query.toString());

                vo = FidelizacaoVO.Factory.newInstance();
                vo.addNewTabelas().addNewBonus();
                while(rs.next()){
                    FidelizacaoVO.Tabelas.Bonus.Linha linha = vo.getTabelas().getBonus().addNewLinha();
                    linha.setIdBonus(rs.getString("idbonus"));
                    linha.setDsTpBonus(rs.getString("dsTipogrupopacote"));
                    linha.setDsBonus(rs.getString("dsbonus"));
                    String nrValidade = rs.getString("nrvalidade")!=null && !ConstantesCRM.SVAZIO.equals(rs.getString("nrvalidade"))?rs.getString("nrvalidade"):ConstantesCRM.SZERO;
                    int nrVal = Integer.parseInt(nrValidade);
                    if(nrVal>6) nrVal = 0;
                    linha.setVdBonus(aList[nrVal]);
                }
                vo.setMsgError(ConstantesCRM.SVAZIO);
                vo.setCodError(ConstantesCRM.SZERO);
                rs.close();
            }catch(SQLException e){
                log.error("SQLException::", e);
                vo.setMsgError("SQLException::"+e.getMessage());
                vo.setCodError(ConstantesCRM.SONE);
            }finally{
                try{
                    if(st!=null) st.close();
                    conn.close();
                    st = null;
                    conn = null;
                }catch(SQLException e1){
                    log.error("SQLException::ps.close", e1);
                }
            }
        }catch(Exception e){
            log.error("Exception::", e);
            vo.setMsgError("Exception::"+e.getMessage());
            vo.setCodError(ConstantesCRM.SONE);
        }
        return vo;
    }

    public FidelizacaoVO delBonus(String user, long idBonus) throws FacadeException {
        FidelizacaoVO out = FidelizacaoVO.Factory.newInstance();
        try{
            Connection      conn = null;
            PreparedStatement ps = null;
            try{
                conn = getConnection();
                String query1 = "UPDATE retencao.bonus SET inativo = 0, idusuarioalteracao = ?, dtalteracao = sysdate WHERE idbonus = ? ";
                String query2 = "UPDATE retencao.matrizbonusura SET inativo = 0, idusuarioalteracao = ?, dtultimaalteracao = sysdate WHERE idbonus = ? ";

                ps = conn.prepareStatement(query1);
                ps.setLong(1, Long.parseLong(user));
                ps.setLong(2, idBonus);
                ps.executeUpdate();

                ps = conn.prepareStatement(query2);
                ps.setLong(1, Long.parseLong(user));
                ps.setLong(2, idBonus);
                ps.executeUpdate();

                conn.commit();
            }catch(SQLException e){
                log.error("SQLException:: ", e);
                try{
                    conn.rollback();
                }catch(SQLException e1){
                    log.error("SQLException::rollback", e1);
                }
            }finally{
                try{
                    if(ps!=null) ps.close();
                    conn.close();
                    ps = null;
                    conn = null;
                }catch(SQLException e1){
                    log.error("SQLException::ps.close", e1);
                }
            }
        }catch(Exception e){
            log.error("Exception::", e);
            throw (new FacadeException(e));
        }
        return out;
    }

    public FidelizacaoVO altBonus(String user, FidelizacaoVO xml) throws FacadeException {
        FidelizacaoVO out = FidelizacaoVO.Factory.newInstance();
        try{
            Connection      conn = null;
            PreparedStatement ps = null;
            ArrayList    aListUF = new ArrayList();
            ArrayList    aListSg = new ArrayList();
            try{
                long idUser  = Long.parseLong(user);
                long idBonus = Long.parseLong(xml.getManter().getIdCadastrado());
                long idUF    = 0;
                long idSegm  = 0;

                conn = getConnection();

                //Atualiza os campos da Tabela Bonus
                StringBuffer qryInsert = new StringBuffer("UPDATE retencao.bonus ");
                qryInsert.append(" SET idusuarioalteracao = ?, dtalteracao = sysdate, dsbonus = ?, nrvalidade = ?, ");
                qryInsert.append(" idtipolinha = ?, cdservico = ?, vlbonus = ?, idtiposervico = ? , idTipogrupopacote = ? ");
                qryInsert.append(" WHERE idbonus = ? ");

                ps = conn.prepareStatement(qryInsert.toString());

                ps.setLong(1, Long.parseLong(user));//idusuarioalteracao
                ps.setString(2, xml.getManter().getNmBonus()); //dsbonus
                ps.setLong(3, Long.parseLong(xml.getManter().getVdBonus())); //nrvalidade
                ps.setLong(4, Long.parseLong(xml.getManter().getTpLinha())); //idtipolinha
                ps.setString(5, xml.getManter().getCdServico()); //cdservico

                if(xml.getManter().getVlBonus()!=null && !ConstantesCRM.SVAZIO.equals(xml.getManter().getVlBonus())){
                    ps.setDouble(6, Double.parseDouble(xml.getManter().getVlBonus()) ); //vlbonus
                }else{
                    ps.setNull(6, Types.DOUBLE); //vlbonus
                }

                if(xml.getManter().getTpServico()!=null && !ConstantesCRM.SVAZIO.equals(xml.getManter().getTpServico())){
                    ps.setLong(7, Long.parseLong(xml.getManter().getTpServico()) ); //idtiposervico
                }else{
                    ps.setNull(7, Types.INTEGER); //idtiposervico
                }

                ps.setLong(8, Long.parseLong(xml.getManter().getIdGrpPacote())); //idTipogrupopacote
                ps.setLong(9, idBonus); //idbonus

                ps.executeUpdate();

                //Realiza a Exclusão Logica da MatrizBonusURA
                String query1 = "UPDATE retencao.matrizbonusura SET inativo = 0, idusuarioalteracao = ?, dtultimaalteracao = sysdate WHERE idbonus = ? ";

                ps = conn.prepareStatement(query1);
                ps.setLong(1, Long.parseLong(user));
                ps.setLong(2, idBonus);
                ps.executeUpdate();

                //Realiza a Inclusão dos novos itens na MatrizBonusURA
                ps = conn.prepareStatement("insert into retencao.matrizbonusura values (RETENCAO.MATRIZBONUSURASQ.NEXTVAL, ?, ?, ?, 1, ?, sysdate)");
                ((OraclePreparedStatement)ps).setExecuteBatch(100);

                int iTam = xml.getListasVO().getListaArray().length;
                for(int i=0;i<iTam;i++){
                    if("REGIONAL".equals(xml.getListasVO().getListaArray(i).getNmSelect())){
                        int jTam = xml.getListasVO().getListaArray(i).getSelecionado().getItArray().length;
                        for(int j=0;j<jTam;j++){
                            aListUF.add(xml.getListasVO().getListaArray(i).getSelecionado().getItArray(j).getId());
                        }
                    }else if("SEGMENTACAO".equals(xml.getListasVO().getListaArray(i).getNmSelect())){
                        int jTam = xml.getListasVO().getListaArray(i).getSelecionado().getItArray().length;
                        for(int j=0;j<jTam;j++){
                            aListSg.add(xml.getListasVO().getListaArray(i).getSelecionado().getItArray(j).getId());
                        }
                    }
                }

                for(int i=0;i<aListUF.size();i++){
                    idUF = Long.parseLong((String) aListUF.get(i));
                    for(int j=0;j<aListSg.size();j++){
                        idSegm = Long.parseLong((String) aListSg.get(j));
                        ps.setLong(1, idUF);
                        ps.setLong(2, idBonus);
                        ps.setLong(3, idSegm);
                        ps.setLong(4, idUser);
                        ps.executeUpdate(); //JDBC queues this for later execution
                    }
                }

                ((OraclePreparedStatement)ps).sendBatch(); //JDBC sends the queued request
                conn.commit();


            }catch(SQLException e){
                log.error("SQLException:: ", e);
                try{
                    conn.rollback();
                }catch(SQLException e1){
                    log.error("SQLException::rollback", e1);
                }
            }finally{
                try{
                    if(ps!=null) ps.close();
                    conn.close();
                    ps = null;
                    conn = null;
                }catch(SQLException e1){
                    log.error("SQLException::ps.close", e1);
                }
            }

        }catch(Exception e){
            log.error("Exception::", e);
            throw (new FacadeException(e));
        }
        return out;
    }

}
