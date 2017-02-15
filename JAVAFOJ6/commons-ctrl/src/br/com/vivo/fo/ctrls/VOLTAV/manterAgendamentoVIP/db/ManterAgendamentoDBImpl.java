package br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import weblogic.ejbgen.Constants.Bool;
import weblogic.ejbgen.Session;
import weblogic.ejbgen.Session.SessionType;
import br.com.vivo.fo.db.DataBaseCall;

@Stateless(name = "ManterAgendamentoDB", mappedName = "ManterAgendamentoDB")
@Local(ManterAgendamentoDB.class)
@Session(ejbName = "ManterAgendamentoDB", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ManterAgendamentoDBImpl implements ManterAgendamentoDB {

    @EJB
    private DataBaseCall database;

    @Override
    public LinhaPremmiun[] getLinhaPremmiunAll(Integer paginaInicial, Integer paginaFinal) throws SQLException {

        StringBuffer query = new StringBuffer();
        query.append("SELECT nrlinha, CDAREAREGISTRO ddd, LINHA idx FROM (SELECT nrlinha, cdarearegistro, ROWNUM AS linha FROM vol.linhapremium) ");
        query.append("WHERE LINHA BETWEEN ").append(paginaInicial).append(" AND ").append(paginaFinal);

        ArrayList<LinhaPremmiun> list = new ArrayList<LinhaPremmiun>();
        ResultSet rs = database.executeQuery(query.toString());

        while (rs.next()) {
            LinhaPremmiun linhaPremmiun = new LinhaPremmiun();
            linhaPremmiun.setDdd(rs.getString("ddd"));
            linhaPremmiun.setLinha(rs.getString("nrlinha"));
            linhaPremmiun.setIdx(rs.getString("idx"));
            list.add(linhaPremmiun);
        }
        rs.close();
        database.release();

        return list.toArray(new LinhaPremmiun[0]);
    }

    @Override
    public LinhaPremmiun[] getLinhaPremmiunByDdd(Integer paginaInicial, Integer paginaFinal, Integer ddd) throws SQLException {

        StringBuffer query = new StringBuffer();
        query.append("SELECT nrlinha, cdarearegistro ddd, linha idx FROM ");
        query.append("(SELECT nrlinha, cdarearegistro, ROWNUM AS linha FROM vol.linhapremium where cdarearegistro = ").append(ddd).append(") ");
        query.append("WHERE LINHA BETWEEN ").append(paginaInicial).append(" AND ").append(paginaFinal);

        ArrayList<LinhaPremmiun> list = new ArrayList<LinhaPremmiun>();
        ResultSet rs = database.executeQuery(query.toString());

        while (rs.next()) {
            LinhaPremmiun linhaPremmiun = new LinhaPremmiun();
            linhaPremmiun.setDdd(rs.getString("ddd"));
            linhaPremmiun.setLinha(rs.getString("nrlinha"));
            linhaPremmiun.setIdx(rs.getString("idx"));
            list.add(linhaPremmiun);
        }
        rs.close();
        database.release();

        return list.toArray(new LinhaPremmiun[0]);
    }

    @Override
    public LinhaPremmiun[] getLinhaPremmiunByLinha(Integer paginaInicial, Integer paginaFinal, Integer linha) throws SQLException {

        StringBuffer query = new StringBuffer();
        query.append("SELECT NRLINHA linha, CDAREAREGISTRO ddd, LINHA idx FROM ");
        query.append("(SELECT nrlinha, cdarearegistro, ROWNUM AS linha FROM vol.linhapremium where nrlinha = ").append(linha).append(") ");
        query.append("WHERE LINHA BETWEEN ").append(paginaInicial).append(" AND ").append(paginaFinal);

        ArrayList<LinhaPremmiun> list = new ArrayList<LinhaPremmiun>();
        ResultSet rs = database.executeQuery(query.toString());

        while (rs.next()) {
            LinhaPremmiun linhaPremmiun = new LinhaPremmiun();
            linhaPremmiun.setDdd(rs.getString("ddd"));
            linhaPremmiun.setLinha(rs.getString("linha"));
            linhaPremmiun.setIdx(rs.getString("idx"));
            list.add(linhaPremmiun);
        }
        rs.close();
        database.release();

        return list.toArray(new LinhaPremmiun[0]);
    }

    @Override
    public void incluirLinha(Integer ddd, Integer linha) throws SQLException {

        StringBuffer query = new StringBuffer();
        query.append("insert into vol.linhapremium (CDAREAREGISTRO, NRLINHA) values (").append(ddd).append(",").append(linha).append(")");

        database.executeUpdate(query.toString());
    }

    @Override
    public void excluirLinha(Integer ddd, Integer linha) throws SQLException {

        StringBuffer query = new StringBuffer();
        query.append("delete from vol.linhapremium where CDAREAREGISTRO = ").append(ddd).append(" and NRLINHA = ").append(linha);

        database.executeUpdate(query.toString());
    }

    @Override
    public LinhaPremmiun getTotalRegistroLinhaPremiun(Integer ddd, Integer linha) throws SQLException {

        StringBuffer query = new StringBuffer();
        query.append("select count(*) totalRegistros from vol.linhapremium ");
        query.append("where (cdarearegistro = ").append(ddd).append(" or ").append(ddd).append(" = -1)");
        query.append(" and (nrlinha = ").append(linha).append(" or ").append(linha).append(" = -1)");

        LinhaPremmiun linhaPremmiun = new LinhaPremmiun();
        ResultSet rs = database.executeQuery(query.toString());

        if(rs.next()) {
            linhaPremmiun.setTotalRegistros(rs.getString("totalRegistros"));
        }
        rs.close();
        database.release();

        return linhaPremmiun;
    }

    @Override
    public LinhaPremmiun getLinhaExiste(Integer ddd, Integer linha) throws SQLException {

        StringBuffer query = new StringBuffer();
        query.append("select count(*) totalRegistros from linha.linhabase lb, apoio.arearegistro ag ");
        query.append("where lb.nrlinha = ").append(linha).append(" ");
        query.append(" and ag.idarearegistro = lb.idarearegistro ");
        query.append(" and ag.cdarearegistro = ").append(ddd);

        LinhaPremmiun linhaPremmiun = new LinhaPremmiun();
        ResultSet rs = database.executeQuery(query.toString());

        if(rs.next()) {
            linhaPremmiun.setTotalRegistros(rs.getString("totalRegistros"));
        }
        rs.close();
        database.release();

        return linhaPremmiun;
    }

    @Override
    public LinhaPremmiun isPremium(Integer ddd, Integer linha) throws SQLException {

        StringBuffer query = new StringBuffer();
        query.append("select count(*) totalRegistros from vol.linhapremium ");
        query.append("where cdarearegistro = ").append(ddd).append(" ");
        query.append("and nrlinha = ").append(linha);

        LinhaPremmiun linhaPremmiun = new LinhaPremmiun();
        ResultSet rs = database.executeQuery(query.toString());

        if(rs.next()) {
            linhaPremmiun.setTotalRegistros(rs.getString("totalRegistros"));
        }
        rs.close();
        database.release();

        return linhaPremmiun;
    }

    @Override
    public LinhaPremmiun getUfByIdPessoa(Integer ddd, Integer linha) throws SQLException {

        StringBuffer query = new StringBuffer();
        query.append("SELECT UF.SGUF uf FROM APOIO.AREAREGISTRO AREAREGISTRO, APOIO.UF UF, ");
        query.append("CUSTOMER.UFOPERADORA UFOPERADORA ");
        query.append("WHERE AREAREGISTRO.CDAREAREGISTRO = ").append(ddd).append(" ");
        query.append("AND UFOPERADORA.IDUF = UF.IDUF ");
        query.append("AND AREAREGISTRO.IDUFOPERADORA = UFOPERADORA.IDUFOPERADORA");

        LinhaPremmiun linhaPremmiun = new LinhaPremmiun();
        ResultSet rs = database.executeQuery(query.toString());

        if(rs.next()) {
            linhaPremmiun.setUf(rs.getString("uf"));
        }
        rs.close();
        database.release();

        return linhaPremmiun;
    }
}