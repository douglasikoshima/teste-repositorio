package br.com.vivo.fo.ctrls.VOLTAV.quadroAviso.db;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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

@Stateless(name = "QuadroAvisoDB", mappedName = "QuadroAvisoDB")
@Local(QuadroAvisoDB.class)
@Session(ejbName = "QuadroAvisoDB", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class QuadroAvisoDBImpl implements QuadroAvisoDB {

    @EJB
    private DataBaseCall database;

    @Override
    public Segmento[] getSegmentos() throws SQLException {
        StringBuffer query = new StringBuffer();
        query.append("SELECT idsegmentacao, dssegmentacao FROM apoio.segmentacao ORDER BY sgsegmentacao");
        Segmento segmento = new Segmento();
        ArrayList<Segmento> list = new ArrayList<Segmento>();
        ResultSet rs = database.executeQuery(query.toString());
        while (rs.next()) {
        	segmento = new Segmento();
            segmento.setIdsegmentacao(rs.getString("idsegmentacao"));
            segmento.setDssegmentacao(rs.getString("dssegmentacao"));
            list.add(segmento);
        }
        rs.close();
        database.release();

        return list.toArray(new Segmento[0]);
    }

    @Override
    public UF[] getUFs() throws SQLException {
        StringBuffer query = new StringBuffer();
        query.append("SELECT iduf, nmuf FROM apoio.uf WHERE inpreenchelista = 1");
        UF uf = new UF();
        ArrayList<UF> list = new ArrayList<UF>();
        ResultSet rs = database.executeQuery(query.toString());
        while (rs.next()) {
        	uf = new UF();
        	uf.setIduf(rs.getString("iduf"));
            uf.setNmuf(rs.getString("nmuf"));
            list.add(uf);
        }
        rs.close();
        database.release();

        return list.toArray(new UF[0]);
    }

    @Override
    public void insertMensagemLog(String cdconta, String idMensagem) throws SQLException {
        StringBuffer query = new StringBuffer();
        query.append("INSERT INTO customer.msglog (idmensagem, cdconta) VALUES ");
        query.append(" (").append(idMensagem).append(", ").append(cdconta).append(")");

        database.executeUpdate(query.toString());
    }

    @Override
    public void insertMensagemSegmentacao(String segmentacao, String idconsultor, String idMensagem) throws SQLException {
        StringBuffer query = new StringBuffer();
        query.append("INSERT INTO ");
        query.append(" contatoadm.msgsegmentacao (idmensagem, idsegmentacao, dtultimaalteracao, idusuario) ");
        query.append(" VALUES ");
        query.append(" (").append(idMensagem).append(",").append(segmentacao).append(",").append("sysdate").append(",").append(idconsultor).append(")");

        database.executeUpdate(query.toString());
    }

    @Override
    public void insertMensagemUF(String uf, String idconsultor, String idMensagem) throws SQLException {
        StringBuffer query = new StringBuffer();
        query.append("INSERT INTO contatoadm.msguf (idmensagem, iduf, dtultimaalteracao, idusuario) ");
        query.append(" VALUES ");
        query.append(" (").append(idMensagem).append(",").append(uf).append(",").append("sysdate").append(",").append(idconsultor).append(")");

        database.executeUpdate(query.toString());
    }

    @Override
    public void excluirMensagem(String id) throws SQLException {
        StringBuffer query = new StringBuffer();
        query.append("UPDATE  contatoadm.mensagem SET dtcancelamento = sysdate WHERE idmensagem = ").append(id);

        database.executeUpdate(query.toString());
    }

    @Override
    public void insertMensagem(Date dtinicio, Date dtfim, String cdconta, String idconsultor, String dsmensagem, String idMensagem) throws SQLException {
        StringBuffer query = new StringBuffer();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        query.append("INSERT INTO contatoadm.mensagem (idmensagem, dtinicio, dtfim, cdconta, idconsultor, dtultimaalteracao, dsmensagem) VALUES ");
        query.append(" (").append(idMensagem).append(",to_date('").append(sdf.format(dtinicio)).append("','dd/mm/yyyy'),to_date('").append(sdf.format(dtfim)).append("','dd/mm/yyyy'),'").append(cdconta).append("','").append(idconsultor).append("',").append("SYSDATE").append(",'").append(dsmensagem).append("')");

        database.executeUpdate(query.toString());
    }

    @Override
    public Associado associadoUfSegmentacao(String conta) throws SQLException {
        StringBuffer query = new StringBuffer();
        query.append("SELECT DISTINCT ");
        query.append("	pe.idufas iduf, nvl(psh.idsegmentacao,11) AS idsegmentacao ");
        query.append(" FROM ");
        query.append("	customer.conta c, ");
        query.append("	customer.pessoaconta pc, ");
        query.append("	customer.contaendereco ce, ");
        query.append("	customer.pessoaendereco pe, ");
        query.append("	customer.pessoadepara pdp, ");
        query.append("	customer.pessoasegmentacao ps, ");
        query.append("	customer.pessoasegmentacaohistorico psh ");
        query.append(" WHERE ");
        query.append("	c.cdconta = ").append(conta).append(" AND ");
        query.append("	pc.idconta = c.idconta AND ");
        query.append("	ce.idconta = c.idconta AND ");
        query.append("	ce.idpessoaendereco = pe.idpessoaendereco AND ");
        query.append("	pdp.idpessoa = pe.idpessoa AND ");
        query.append("	ps.idpessoadepara(+) = pdp.idpessoadepara AND ");
        query.append("	psh.idpessoasegmentacao(+) = ps.idpessoasegmentacao ");

        Associado associado = new Associado();
        ResultSet rs = database.executeQuery(query.toString());

        if (rs.next()) {
            associado.setIduf(rs.getString("iduf"));
            associado.setIdsegmentacao(rs.getString("idsegmentacao"));
        }
        rs.close();
        database.release();

        return associado;
    }

    @Override
    public Associado validaAssociacao(String conta, String consultor) throws SQLException {
        StringBuffer query = new StringBuffer();
        query.append("SELECT COUNT(1)  iduf ");
        query.append("FROM customer.conta c, customer.pessoaconta pc, customer.pessoaconsultor pcns, customer.pessoadepara pdp, customer.pessoadocumento pd, customer.documento d ");
        query.append("WHERE ");
        query.append("	c.cdconta = ").append(conta).append("AND ");
        query.append("	pc.idconta = c.idconta AND ");
        query.append("	pcns.idpessoa = ").append(consultor).append(" AND ");
        query.append("	pdp.idpessoa = pd.idpessoa AND ");
        query.append("	pd.iddocumento = d.iddocumento AND ");
        query.append("	d.nrdocumento  = pcns.NRDOCUMENTO ");

        Associado associado = new Associado();
        ResultSet rs = database.executeQuery(query.toString());

        if (rs.next()) {
            associado.setIduf(rs.getString("iduf"));
            associado.setIdsegmentacao(rs.getString("idsegmentacao"));
        }
        rs.close();
        database.release();

        return associado;
    }

    @Override
    public Carteira[] carteirasPorRegional(String iduf, String user) throws SQLException {
        StringBuffer query = new StringBuffer();
        query.append("SELECT  c.idconta, psh.idsegmentacao, pe.iduf ");
        query.append("FROM customer.conta c, customer.pessoa p, customer.pessoaendereco pe, customer.pessoaconsultor pcns, customer.contaendereco ce, ");
        query.append("     customer.pessoadepara pdp, customer.pessoaconta pc, customer.pessoasegmentacao ps, customer.pessoasegmentacaohistorico psh ");
        query.append("WHERE ");
        query.append("	p.idpessoa = pdp.idpessoa AND ");
        query.append("	pc.idconta = c.idconta AND ");
        query.append("	pe.idpessoa = p.idpessoa AND ");
        query.append("	pcns.idpessoa(+)= pdp.idpessoa AND ");
        query.append("	ce.idconta = c.idconta AND ");
        query.append("	ce.idpessoaendereco = pe.idpessoaendereco AND ");
        query.append("	ps.idpessoadepara = pdp.idpessoadepara AND ");
        query.append("	psh.idpessoasegmentacao = ps.idpessoasegmentacao AND ");
        query.append("	pe.iduf = ").append(iduf).append(" AND ");
        query.append("	p.idpessoa = ").append(user).append(" ");

        Carteira carteira = new Carteira();
        ArrayList<Carteira> list = new ArrayList<Carteira>();
        ResultSet rs = database.executeQuery(query.toString());

        while (rs.next()) {
            carteira.setIduf(rs.getString("iduf"));
            carteira.setIdsegmentacao(rs.getString("idsegmentacao"));
            carteira.setIdconta(rs.getString("idconta"));
            list.add(carteira);
        }
        rs.close();
        database.release();

        return list.toArray(new Carteira[0]);
    }

    @Override
    public Carteira[] carteirasPorSegmentacao(String idsegmentacao, String user) throws SQLException {
        StringBuffer query = new StringBuffer();
        query.append("SELECT ");
        query.append("	c.idconta, psh.idsegmentacao, pe.iduf ");
        query.append("FROM ");
        query.append("	customer.conta c, customer.pessoa p, customer.pessoaendereco pe, customer.pessoaconsultor pcns, "
                + "	customer.contaendereco ce, customer.pessoadepara pdp, customer.pessoaconta pc, customer.pessoasegmentacao ps, " + "	customer.pessoasegmentacaohistorico psh");
        query.append("WHERE ");
        query.append("	p.idpessoa = pdp.idpessoa AND ");
        query.append("	pc.idconta = c.idconta AND ");
        query.append("	pe.idpessoa = p.idpessoa AND ");
        query.append("	pcns.idpessoa(+)= pdp.idpessoa AND ");
        query.append("	ce.idconta = c.idconta AND ");
        query.append("	ce.idpessoaendereco = pe.idpessoaendereco AND ");
        query.append("	ps.idpessoadepara = pdp.idpessoadepara AND ");
        query.append("	psh.idpessoasegmentacao = ps.idpessoasegmentacao AND ");
        query.append("	psh.idsegmentacao = ").append(idsegmentacao).append(" AND ");
        query.append("	p.idpessoa = ").append(user).append(" ");

        Carteira carteira = new Carteira();
        ArrayList<Carteira> list = new ArrayList<Carteira>();
        ResultSet rs = database.executeQuery(query.toString());

        while (rs.next()) {
            carteira.setIduf(rs.getString("iduf"));
            carteira.setIdsegmentacao(rs.getString("idsegmentacao"));
            carteira.setIdconta(rs.getString("idconta"));
            list.add(carteira);
        }
        rs.close();
        database.release();

        return list.toArray(new Carteira[0]);
    }

    @Override
    public Carteira[] carteirasPorRegionalSegmentacao(String iduf, String idsegmentacao, String user) throws SQLException {
        StringBuffer query = new StringBuffer();
        query.append("SELECT");
        query.append("	c.idconta, psh.idsegmentacao, pe.iduf");
        query.append("FROM");
        query.append("	customer.conta c, customer.pessoa p, customer.pessoaendereco pe, customer.pessoaconsultor pcns, "
                + "	customer.contaendereco ce, customer.pessoadepara pdp, customer.pessoaconta pc, customer.pessoasegmentacao ps, " + "	customer.pessoasegmentacaohistorico psh");
        query.append("WHERE ");
        query.append("	p.idpessoa = pdp.idpessoa AND");
        query.append("	pc.idconta = c.idconta AND");
        query.append("	pe.idpessoa = p.idpessoa AND");
        query.append("	pcns.idpessoa(+)= pdp.idpessoa AND");
        query.append("	ce.idconta = c.idconta AND");
        query.append("	ce.idpessoaendereco = pe.idpessoaendereco AND");
        query.append("	ps.idpessoadepara = pdp.idpessoadepara AND");
        query.append("	psh.idpessoasegmentacao = ps.idpessoasegmentacao AND");
        query.append("	psh.idsegmentacao = ").append(idsegmentacao).append("AND");
        query.append("	pe.iduf = ").append(iduf).append("AND");
        query.append("	p.idpessoa = ").append(user).append(")");

        Carteira carteira = new Carteira();
        ArrayList<Carteira> list = new ArrayList<Carteira>();
        ResultSet rs = database.executeQuery(query.toString());

        while (rs.next()) {
            carteira.setIduf(rs.getString("iduf"));
            carteira.setIdsegmentacao(rs.getString("idsegmentacao"));
            carteira.setIdconta(rs.getString("idconta"));
            list.add(carteira);
        }
        rs.close();
        database.release();

        return list.toArray(new Carteira[0]);
    }

    @Override
    public Carteira[] carteirasTodas(String user) throws SQLException {
        StringBuffer query = new StringBuffer();
        query.append("SELECT");
        query.append("	c.idconta, psh.idsegmentacao, pe.iduf ");
        query.append(" FROM ");
        query.append("	customer.conta c, customer.pessoa p, customer.pessoaendereco pe, customer.pessoaconsultor pcns, "
                + "	customer.contaendereco ce, customer.pessoadepara pdp, customer.pessoaconta pc, customer.pessoasegmentacao ps, " + "	customer.pessoasegmentacaohistorico psh ");
        query.append("WHERE ");
        query.append("	p.idpessoa = pdp.idpessoa AND");
        query.append("	pc.idconta = c.idconta AND");
        query.append("	pe.idpessoa = p.idpessoa AND");
        query.append("	pcns.idpessoa(+)= pdp.idpessoa AND");
        query.append("	ce.idconta = c.idconta AND");
        query.append("	ce.idpessoaendereco = pe.idpessoaendereco AND");
        query.append("	ps.idpessoadepara = pdp.idpessoadepara AND");
        query.append("	psh.idpessoasegmentacao = ps.idpessoasegmentacao AND ");
        query.append("	p.idpessoa = ").append(user).append(")");

        Carteira carteira = new Carteira();
        ArrayList<Carteira> list = new ArrayList<Carteira>();
        ResultSet rs = database.executeQuery(query.toString());

        while (rs.next()) {
            carteira.setIduf(rs.getString("iduf"));
            carteira.setIdsegmentacao(rs.getString("idsegmentacao"));
            carteira.setIdconta(rs.getString("idconta"));
            list.add(carteira);
        }
        rs.close();
        database.release();

        return list.toArray(new Carteira[0]);
    }

    @Override
    public Carteira[] carteirasGeral(String user) throws SQLException {
        StringBuffer query = new StringBuffer();
        query.append("SELECT ");
        query.append("	c.cdconta idconta ");
        query.append("FROM ");
        query.append("	customer.conta c, customer.pessoa p, customer.pessoaconsultor pcns, " + "	customer.pessoadepara pdp, customer.pessoaconta pc, customer.documento doc, "
                + "	customer.pessoadocumento pdoc ");
        query.append("WHERE ");
        query.append("	p.idpessoa = pdp.idpessoa AND ");
        query.append("	pc.idconta = c.idconta AND ");
        query.append("	pc.idpessoadepara = pdp.idpessoadepara AND ");
        query.append("	pc.idtiporelacionamento = 2 AND ");
        query.append("	doc.iddocumento = pdoc.iddocumento AND ");
        query.append("	pdoc.idpessoa = p.idpessoa AND ");
        query.append("	doc.nrdocumento = pcns.nrdocumento AND ");
        query.append("	pcns.idpessoa = ").append(user).append(" ");

        Carteira carteira = new Carteira();
        ArrayList<Carteira> list = new ArrayList<Carteira>();
        ResultSet rs = database.executeQuery(query.toString());

        while (rs.next()) {
            carteira.setIduf(rs.getString("iduf"));
            carteira.setIdsegmentacao(rs.getString("idsegmentacao"));
            carteira.setIdconta(rs.getString("idconta"));
            list.add(carteira);
        }
        rs.close();
        database.release();

        return list.toArray(new Carteira[0]);
    }
}