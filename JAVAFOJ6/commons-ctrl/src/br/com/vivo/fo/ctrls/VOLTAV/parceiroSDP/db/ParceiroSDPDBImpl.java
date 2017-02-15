package br.com.vivo.fo.ctrls.VOLTAV.parceiroSDP.db;

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

@Stateless(name = "ParceiroSDPDB", mappedName = "ParceiroSDPDB")
@Local(ParceiroSDPDB.class)
@Session(ejbName = "ParceiroSDPDB", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ParceiroSDPDBImpl implements ParceiroSDPDB {

	@EJB
	private DataBaseCall database;

	@Override
	public Parceiro[] getParceiros(String nome) throws SQLException {

		StringBuffer query = new StringBuffer();
		query.append("SELECT P.IDITEMMENU, P.NMPARCEIRO, P.IDCONTATO, P.DSIPPARCEIRO, P.DSURLPARCEIRO, I.IDITEMMENUPAI ");
		query.append("  FROM VOL.PARCEIROSDP P, ");
		query.append("       ACESSO.ITEMMENUHIERARQUIA I ");
		query.append(" WHERE P.IDITEMMENU = I.IDITEMMENU ");
		query.append("   AND UPPER(P.NMPARCEIRO) = '").append(nome).append("'");

		ArrayList<Parceiro> list = new ArrayList<Parceiro>();
		ResultSet rs = database.executeQuery(query.toString());

		while (rs.next()) {
			Parceiro parceiro = new Parceiro();
			parceiro.setIditemmenu(rs.getString("iditemmenu"));
			parceiro.setNmparceiro(rs.getString("nmparceiro"));
			parceiro.setIdcontato(rs.getString("idcontato"));
			parceiro.setDsipparceiro(rs.getString("dsipparceiro"));
			parceiro.setDsurlparceiro(rs.getString("dsurlparceiro"));
			parceiro.setIditemmenupai(rs.getString("iditemmenupai"));
			list.add(parceiro);
		}
		rs.close();
		database.release();

		return list.toArray(new Parceiro[0]);

	}

	@Override
	public Parceiro carregaParceiro(String id) throws SQLException {

		StringBuffer query = new StringBuffer();
		query.append("SELECT P.IDITEMMENU, P.NMPARCEIRO, P.IDCONTATO, P.DSIPPARCEIRO, P.DSURLPARCEIRO, I.IDITEMMENUPAI ");
		query.append("  FROM VOL.PARCEIROSDP P, ");
		query.append("       ACESSO.ITEMMENUHIERARQUIA I ");
		query.append(" WHERE P.IDITEMMENU = I.IDITEMMENU ");
		query.append("   AND P.IDITEMMENU = ").append(id);

		ResultSet rs = database.executeQuery(query.toString());
		Parceiro parceiro = new Parceiro();

		while (rs.next()) {
			parceiro.setIditemmenu(rs.getString("iditemmenu"));
			parceiro.setNmparceiro(rs.getString("nmparceiro"));
			parceiro.setIdcontato(rs.getString("idcontato"));
			parceiro.setDsipparceiro(rs.getString("dsipparceiro"));
			parceiro.setDsurlparceiro(rs.getString("dsurlparceiro"));
			parceiro.setIditemmenupai(rs.getString("iditemmenupai"));
		}
		rs.close();
		database.release();

		return parceiro;
	}

	@Override
	public Parceiro[] getTodosParceiros() throws SQLException {

		StringBuffer query = new StringBuffer();
		query.append("SELECT P.IDITEMMENU, P.NMPARCEIRO, P.IDCONTATO, P.DSIPPARCEIRO, P.DSURLPARCEIRO, I.IDITEMMENUPAI ");
		query.append("  FROM VOL.PARCEIROSDP P, ");
		query.append("       ACESSO.ITEMMENUHIERARQUIA I ");
		query.append(" WHERE P.IDITEMMENU = I.IDITEMMENU");

		ArrayList<Parceiro> list = new ArrayList<Parceiro>();
		ResultSet rs = database.executeQuery(query.toString());

		while (rs.next()) {
			Parceiro parceiro = new Parceiro();
			parceiro.setIditemmenu(rs.getString("iditemmenu"));
			parceiro.setNmparceiro(rs.getString("nmparceiro"));
			parceiro.setIdcontato(rs.getString("idcontato"));
			parceiro.setDsipparceiro(rs.getString("dsipparceiro"));
			parceiro.setDsurlparceiro(rs.getString("dsurlparceiro"));
			parceiro.setIditemmenupai(rs.getString("iditemmenupai"));
			list.add(parceiro);
		}
		rs.close();
		database.release();

		return list.toArray(new Parceiro[0]);
	}

	@Override
	public int duplicidade(String nome) throws SQLException {
		int retorno = 0;

		StringBuffer query = new StringBuffer();
		query.append("SELECT COUNT(1) ");
		query.append("FROM VOL.PARCEIROSDP ");
		query.append("WHERE UPPER(NMPARCEIRO) = '").append(nome).append("'");

		ResultSet rs = database.executeQuery(query.toString());
		if (rs.next()) {
			retorno = rs.getInt(1);
		}
		return retorno;
	}

	@Override
	public FolhaContato[] getFolhasDisponiveis() throws SQLException {

		StringBuffer query = new StringBuffer();		
		query.append("select c.idcontato, n.nmcontato ");
		query.append("	from contatohierarquia h, contatoadm.contato c, contatoadm.nomecontato n ");
		query.append(" where h.idcontato = c.idcontato ");
		query.append("   and n.idnomecontato = c.idnomecontato ");
		query.append("and h.idcontatopai = ");
		query.append("       (select idcontato ");
		query.append("          from contatoadm.contato ");
		query.append("         where NMPATH LIKE '%SDP-PARCEIROS%' ");
		query.append("          AND IDCONTATO NOT IN ");
		query.append("               (SELECT IDCONTATO FROM CONTATOADM.CONTATOFOLHA)) ");
		query.append("   and c.idcontato not in (select idcontato from vol.PARCEIROSDP) ");
		query.append(" order by n.nmcontato ");		
		


		ArrayList<FolhaContato> list = new ArrayList<FolhaContato>();
		ResultSet rs = database.executeQuery(query.toString());

		while (rs.next()) {
			FolhaContato folhaContato = new FolhaContato();
			folhaContato.setIdcontato(rs.getString("idcontato"));
			folhaContato.setNmcontato(rs.getString("nmcontato"));
			list.add(folhaContato);
		}
		rs.close();
		database.release();

		return list.toArray(new FolhaContato[0]);

	}

	@Override
	public FolhaContato[] getFolhasDisponiveisAlteracao(String id) throws SQLException {

		StringBuffer query = new StringBuffer();
		query.append("select c.idcontato, n.nmcontato ");
		query.append("from contatoadm.contatohierarquia h, ");
		query.append("contatoadm.contato c, ");
		query.append("contatoadm.nomecontato n ");
		query.append("where h.idcontato = c.idcontato ");
		query.append("and n.idnomecontato = c.idnomecontato ");
		query.append("and h.idcontatopai = ( select idcontato from contatoadm.contato ");
		query.append("where NMPATH LIKE '%SDP-PARCEIROS%' ");
		query.append("AND IDCONTATO NOT IN (SELECT IDCONTATO FROM CONTATOADM.CONTATOFOLHA)) ");
		query.append("and c.idcontato not in (select idcontato from vol.PARCEIROSDP where idcontato <> ").append(id).append(") ");
		query.append("order by n.nmcontato ");

		ArrayList<FolhaContato> list = new ArrayList<FolhaContato>();
		ResultSet rs = database.executeQuery(query.toString());

		while (rs.next()) {
			FolhaContato folhaContato = new FolhaContato();
			folhaContato.setIdcontato(rs.getString("idcontato"));
			folhaContato.setNmcontato(rs.getString("nmcontato"));
			list.add(folhaContato);
		}
		rs.close();
		database.release();

		return list.toArray(new FolhaContato[0]);
	}
}