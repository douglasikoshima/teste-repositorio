package br.com.vivo.fo.ctrls.VOLTAV.mailing.db;

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

@Stateless(name = "Mailing", mappedName = "Mailing")
@Local(Mailing.class)
@Session(ejbName = "Mailing", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class MailingDBImpl implements Mailing {

    @EJB
    private DataBaseCall database;

    @Override
    public AreaBanner[] listarAreaBanner() throws SQLException {

        StringBuffer query = new StringBuffer();
        query.append("select idareabanner idAreaBanner, ");
        query.append("dsareabanner, ");
        query.append("dsAreaBanner from apoio.areabanner ");
        query.append("where idareabanner >= 10 ");
        query.append("and idareabanner <= 20");

        ArrayList<AreaBanner> list = new ArrayList<AreaBanner>();
        ResultSet rs = database.executeQuery(query.toString());

        while (rs.next()) {
            AreaBanner areaBanner = new AreaBanner();
            areaBanner.setDsAreaBanner(rs.getString("dsAreaBanner"));
            areaBanner.setIdAreaBanner(rs.getString("idAreaBanner"));
            list.add(areaBanner);
        }
        rs.close();
        database.release();

        return list.toArray(new AreaBanner[0]);

    }

    @Override
    public MailingBanner[] listarMailing() throws SQLException {

        StringBuffer query = new StringBuffer();
        query.append("select idmailingbanner, ");
        query.append("nmmailingbanner, ");
        query.append("idareabanner, ");
        query.append("TO_CHAR(dtvigenciainicio,'dd/mm/yyyy') ");
        query.append("dtvigenciainicio,TO_CHAR(dtvigenciafim,'dd/mm/yyyy') ");
        query.append("dtvigenciafim, idUsuarioAlteracao from vol.mailingbanner ");
        query.append("order by nmmailingbanner asc");

        ArrayList<MailingBanner> list = new ArrayList<MailingBanner>();
        ResultSet rs = database.executeQuery(query.toString());

        while (rs.next()) {
            MailingBanner mailingBanner = new MailingBanner();
            mailingBanner.setIdMailingBanner(rs.getString("idMailingBanner"));
            mailingBanner.setNmMailingBanner(rs.getString("nmMailingBanner"));
            mailingBanner.setIdAreaBanner(rs.getString("idAreaBanner"));
            mailingBanner.setDtVigenciaInicio(rs.getString("dtVigenciaInicio"));
            mailingBanner.setDtVigenciaFim(rs.getString("dtVigenciaFim"));
            mailingBanner.setIdUsuarioAlteracao(rs.getString("idUsuarioAlteracao"));
            list.add(mailingBanner);
        }
        rs.close();
        database.release();

        return list.toArray(new MailingBanner[0]);

    }

    @Override
    public MailingLinha[] listarLinhasPorMailing(Integer idMailing) throws SQLException {

        StringBuffer query = new StringBuffer();
        query.append("select idmailingbanner idMailingBanner, ");
        query.append("cdarearegistro ddd, ");
        query.append("nrlinha linha ");
        query.append("from vol.mailinglinha ");
        query.append("where idmailingbanner = ").append(idMailing);

        ArrayList<MailingLinha> list = new ArrayList<MailingLinha>();
        ResultSet rs = database.executeQuery(query.toString());

        while (rs.next()) {
            MailingLinha mailingLinha = new MailingLinha();
            mailingLinha.setIdMailingBanner(rs.getString("idMailingBanner"));
            mailingLinha.setDdd(rs.getString("ddd"));
            mailingLinha.setLinha(rs.getString("linha"));
            mailingLinha.setTotalRegistros(rs.getString("totalRegistros"));
            list.add(mailingLinha);

        }
        rs.close();
        database.release();

        return list.toArray(new MailingLinha[0]);

    }

    @Override
    public MailingLinha[] pesquisarPorLinha(Integer idMailing, Integer linha) throws SQLException {
        StringBuffer query = new StringBuffer();
        query.append("select idmailingbanner idMailingBanner, ");
        query.append("cdarearegistro ddd, ");
        query.append("nrlinha linha from vol.mailinglinha  ");
        query.append("where idmailingbanner = ").append(idMailing);
        query.append(" and nrlinha = ").append(linha);

        ArrayList<MailingLinha> list = new ArrayList<MailingLinha>();
        ResultSet rs = database.executeQuery(query.toString());

        while (rs.next()) {
            MailingLinha mailingLinha = new MailingLinha();
            mailingLinha.setIdMailingBanner(rs.getString("idMailingBanner"));
            mailingLinha.setDdd(rs.getString("ddd"));
            mailingLinha.setLinha(rs.getString("linha"));
            //mailingLinha.setTotalRegistros(rs.getString("totalRegistros"));
            list.add(mailingLinha);

        }
        rs.close();
        database.release();

        return list.toArray(new MailingLinha[0]);

    }

    @Override
    public MailingLinha[] pesquisarPorDDD(Integer idMailing, Integer ddd) throws SQLException {

        StringBuffer query = new StringBuffer();
        query.append("select idmailingbanner ");
        query.append("idMailingBanner, ");
        query.append("cdarearegistro ddd, ");
        query.append("nrlinha linha ");
        query.append("from vol.mailinglinha ");
        query.append("where idmailingbanner = ").append(idMailing);
        query.append(" and cdarearegistro = ").append(ddd);

        ArrayList<MailingLinha> list = new ArrayList<MailingLinha>();
        ResultSet rs = database.executeQuery(query.toString());

        while (rs.next()) {
            MailingLinha mailingLinha = new MailingLinha();
            mailingLinha.setIdMailingBanner(rs.getString("idMailingBanner"));
            mailingLinha.setDdd(rs.getString("ddd"));
            mailingLinha.setLinha(rs.getString("linha"));
            //mailingLinha.setTotalRegistros(rs.getString("totalRegistros"));
            list.add(mailingLinha);

        }
        rs.close();
        database.release();

        return list.toArray(new MailingLinha[0]);

    }

    @Override
    public void incluirLinha(Integer idMailing, Integer ddd, Integer linha) throws SQLException {

        StringBuffer query = new StringBuffer();
        query.append("insert into vol.mailinglinha ");
        query.append("(idmailingbanner, ");
        query.append("cdarearegistro, ");
        query.append("nrlinha) ");
        query.append("values ").append(idMailing).append(", ");
        query.append(" ").append(ddd).append(", ");
        query.append(" )").append(linha);

        database.executeUpdate(query.toString());
    }

    @Override
    public void excluirLinha(Integer idMailing, Integer ddd, Integer linha) throws SQLException {

        StringBuffer query = new StringBuffer();
        query.append("delete from vol.mailinglinha ");
        query.append("where nrLinha =  ").append(linha);
        query.append(" and cdarearegistro =  ").append(ddd);
        query.append(" and idMailingBanner = ").append(idMailing);

        database.executeUpdate(query.toString());

    }

    @Override
    public void incluirMailing(String nome, String dataInicio, String dataFim, Integer idAreaBanner, Integer idMailingBanner, Integer idUser) throws SQLException {

        StringBuffer query = new StringBuffer();
        query.append("insert into vol.mailingbanner ");
        query.append("(idmailingbanner, ");
        query.append("nmmailingbanner, ");
        query.append("idareabanner, ");
        query.append("dtvigenciainicio, ");
        query.append("dtvigenciafim, ");
        query.append("dtultimaalteracao, ");
        query.append("idusuarioalteracao) ");
        query.append("values (").append(idMailingBanner).append(", ");
        query.append(" '").append(nome).append("', ");
        query.append(" ").append(idAreaBanner).append(", ");
        query.append("TO_DATE('").append(dataInicio).append("', ");
        query.append("'DD/MM/YYYY'), ");
        query.append("TO_DATE('").append(dataFim).append("', ");
        query.append("'DD/MM/YYYY'), ");
        query.append("sysdate, ");
        query.append(" ").append(idUser).append(")");
        database.executeQuery(query.toString());

    }

    @Override
    public void alterarMailing(String dataInicio, String dataFim, Integer idMailingBanner) throws SQLException {

        StringBuffer query = new StringBuffer();
        query.append("update vol.mailingbanner ");
        query.append("set dtvigenciainicio = TO_DATE(").append(dataInicio).append(",)");
        query.append("'DD/MM/YYYY'), ");
        query.append("dtvigenciafim = TO_DATE({},").append(dataFim);
        query.append("'DD/MM/YYYY') ");
        query.append("where idmailingbanner = ").append(idMailingBanner);

        database.executeUpdate(query.toString());

    }

    @Override
    public MailingBanner getChavePrimariaMailing() throws SQLException {

        StringBuffer query = new StringBuffer();
        query.append("select vol.mailingbannersq.nextval idMailingBanner ");
        query.append("from dual");

        ResultSet rs = database.executeQuery(query.toString());
        MailingBanner mailingBanner = new MailingBanner();

        while (rs.next()) {
            mailingBanner.setIdMailingBanner(rs.getString("idMailingBanner"));
            //mailingBanner.setNmMailingBanner(rs.getString("nmMailingBanner"));
            //mailingBanner.setIdAreaBanner(rs.getString("idAreaBanner"));
            //mailingBanner.setDtVigenciaInicio(rs.getString("dtVigenciaInicio"));
            //mailingBanner.setDtVigenciaFim(rs.getString("dtVigenciaFim"));
            //mailingBanner.setIdUsuarioAlteracao(rs.getString("idUsuarioAlteracao"));
            //mailingBanner.setIdUsuarioAlteracao(rs.getString("idUsuarioAlteracao"));

        }
        rs.close();
        database.release();

        return mailingBanner;

    }

    @Override
    public void excluirMailing(Integer idMailingBanner) throws SQLException {

        StringBuffer query = new StringBuffer();
        query.append("delete from vol.mailingbanner ");
        query.append("where idmailingbanner = ").append(idMailingBanner);

        database.executeQuery(query.toString());

    }

    @Override
    public void excluirLinhasMailing(Integer idMailingBanner) throws SQLException {
        StringBuffer query = new StringBuffer();
        query.append("delete from vol.mailinglinha ");
        query.append("where idmailingbanner = ").append(idMailingBanner);

        database.executeQuery(query.toString());

    }

    @Override
    public MailingLinha getLinhaExiste(Integer ddd, Integer linha) throws SQLException {

        StringBuffer query = new StringBuffer();
        query.append("select count(*) totalRegistros ");
        query.append("from linha.linhabase lb, ");
        query.append("apoio.arearegistro ag ");
        query.append("where lb.nrlinha = ").append(linha);
        query.append(" and ag.idarearegistro = lb.idarearegistro ");
        query.append("and ag.cdarearegistro = ").append(ddd);

        ResultSet rs = database.executeQuery(query.toString());
        MailingLinha mailingLinha = new MailingLinha();

        while (rs.next()) {
            mailingLinha.setIdMailingBanner(rs.getString("idMailingBanner"));
            mailingLinha.setDdd(rs.getString("ddd"));
            mailingLinha.setLinha(rs.getString("linha"));
            mailingLinha.setTotalRegistros(rs.getString("totalRegistros"));
        }
        rs.close();
        database.release();

        return mailingLinha;
    }

    @Override
    public void incluirArquivo(String cdParametro, String idMailingBanner, Integer idUser) throws SQLException {

        StringBuffer query = new StringBuffer();
        query.append("insert into apoio.parametro ");
        query.append("(idparametro, ");
        query.append("cdparametro, ");
        query.append("dsparametro, ");
        query.append("dsvalorparametro, ");
        query.append("idusuarioalteracao, ");
        query.append("dtultimaalteracao) ");
        query.append("values (apoio.parametrosq.nextval, ");
        query.append(" '").append(cdParametro).append("', ");
        query.append(" '").append(cdParametro).append("', ");
        query.append(" ").append(idMailingBanner).append(", ");
        query.append(" ").append(idUser).append(", ");
        query.append("sysdate)");

        database.executeQuery(query.toString());

    }

}
