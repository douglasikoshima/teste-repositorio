package br.com.vivo.fo.ctrls.cliente.associarGestor.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

@Stateless(name = "GestorDB", mappedName = "GestorDB")
@Local(GestorDB.class)
@Session(ejbName = "GestorDB", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class GestorDBImpl implements GestorDB {

    @EJB
    private DataBaseCall database;

    @Override
    public Gestor[] getGestoresConta(String cdCnpjEmpresa, String cdConta) throws SQLException {

        StringBuffer query = new StringBuffer();
        query.append("SELECT pessoagestor.idpessoasistemaorigem idpessoasistemaorigem, ");
        query.append("       pessoagestor.nmpessoagestor nmpessoagestor, ");
        query.append("       pessoagestor.nmgestor nmgestor, ");
        query.append("       pessoagestor.nmmeiogestor nmmeiogestor, ");
        query.append("       pessoagestor.nmsobrenomegestor nmsobrenomegestor, ");
        query.append("       pessoagestor.nrtelefonecelularvivo nrtelefonecelvivo, ");
        query.append("       pessoagestor.nrtelefonecelularoutro nrtelefoneceloutro, ");
        query.append("       pessoagestor.nrtelefonefixo nrtelefonefixo, ");
        query.append("       pessoagestor.email email, ");
        query.append("       pessoagestor.nrdocumento nrdocumento, ");
        query.append("       pessoagestor.idtipodocumento idtipodocumento, ");
        query.append("       pessoagestor.idusuarioalteracao iduuarioalteracao, ");
        query.append("       pessoagestor.dtultimaalteracao dtultimaalteracao, ");
        query.append("       pessoagestor.dtnascimento dtnascimento, ");
        query.append("       TO_CHAR (conta.cdconta) cdconta, ");
        query.append("       pessoagestorconta.idtiporelacionamento idtiporelacionamento, ");
        query.append("       tiporelacionamento.sgtiporelacionamento sgtiporelacionamento, ");
        query.append("       NVL (usuario.nmloginusuario, 'Não informado') nmloginusuario ");
        query.append("FROM customer.pessoagestor pessoagestor, ");
        query.append("     customer.pessoagestorconta pessoagestorconta, ");
        query.append("     customer.tiporelacionamento tiporelacionamento, ");
        query.append("     customer.conta conta, ");
        query.append("     acesso.usuario usuario ");
        query.append("WHERE pessoagestorconta.idtiporelacionamento = tiporelacionamento.idtiporelacionamento ");
        query.append("      AND pessoagestor.nrdocumento = pessoagestorconta.nrdocumento ");
        query.append("      AND pessoagestor.idusuarioalteracao = usuario.idpessoausuario(+) ");
        query.append("      AND pessoagestorconta.idconta = conta.idconta ");
        query.append("      AND conta.cdconta = '").append(cdConta).append("' ");
        query.append("UNION ");
        query.append("SELECT pessoagestor.idpessoasistemaorigem idpessoasistemaorigem, ");
        query.append("       pessoagestor.nmpessoagestor nmpessoagestor, ");
        query.append("       pessoagestor.nmgestor nmgestor, ");
        query.append("       pessoagestor.nmmeiogestor nmmeiogestor, ");
        query.append("       pessoagestor.nmsobrenomegestor nmsobrenomegestor, ");
        query.append("       pessoagestor.nrtelefonecelularvivo nrtelefonecelvivo, ");
        query.append("       pessoagestor.nrtelefonecelularoutro nrtelefoneceloutro, ");
        query.append("       pessoagestor.nrtelefonefixo nrtelefonefixo, ");
        query.append("       pessoagestor.email email, ");
        query.append("       pessoagestor.nrdocumento nrdocumento, ");
        query.append("       pessoagestor.idtipodocumento idtipodocumento, ");
        query.append("       pessoagestor.idusuarioalteracao iduuarioalteracao, ");
        query.append("       pessoagestor.dtultimaalteracao dtultimaalteracao, ");
        query.append("       pessoagestor.dtnascimento dtnascimento, ");
        query.append("       '").append(cdConta).append("' cdconta, ");
        query.append("       (SELECT idtiporelacionamento FROM customer.tiporelacionamento WHERE sgtiporelacionamento = 'GM') idtiporelacionamento, ");
        query.append("       'GM' sgtiporelacionamento, ");
        query.append("       NVL (usuario.nmloginusuario, 'Não informado') nmloginusuario ");
        query.append("FROM customer.pessoagestor pessoagestor, ");
        query.append("     customer.pessoagestormaster pessoagestormaster, ");
        query.append("     acesso.usuario usuario ");
        query.append("WHERE     pessoagestormaster.nrdocumentogestor = pessoagestor.nrdocumento ");
        query.append("      AND pessoagestor.idusuarioalteracao = usuario.idpessoausuario(+) ");
        query.append("      AND pessoagestormaster.nrdocumentoempresa = '").append(cdCnpjEmpresa).append("' ");

        ArrayList<Gestor> list = new ArrayList<Gestor>();
        Statement st = null;
        ResultSet rs = null;
        try {
	        st = database.getConnection().createStatement();
	        rs = st.executeQuery(query.toString());

        while (rs.next()) {
            Gestor gestor = new Gestor();
            gestor.setCdConta(rs.getString("cdconta"));
            gestor.setDtNascimento(rs.getDate("dtnascimento"));
            gestor.setDtUltimaAlteracao(rs.getDate("dtultimaalteracao"));
            gestor.setEmail(rs.getString("email"));
            gestor.setIdPessoaSistemaOrigem(rs.getString("idpessoasistemaorigem"));
            gestor.setIdTipoDocumento(rs.getString("idtipodocumento"));
            gestor.setIdTipoRelacionamento(rs.getString("idtiporelacionamento"));
            gestor.setIdUuarioAlteracao(rs.getString("iduuarioalteracao"));
            gestor.setNmGestor(rs.getString("nmgestor"));
            gestor.setNmLoginUsuario(rs.getString("nmloginusuario"));
            gestor.setNmMeioGestor(rs.getString("nmmeiogestor"));
            gestor.setNmPessoaGestor(rs.getString("nmpessoagestor"));
            gestor.setNmSobreNomeGestor(rs.getString("nmsobrenomegestor"));
            gestor.setNrDocumento(rs.getString("nrdocumento"));
            gestor.setNrTelefoneCelOutro(rs.getString("nrtelefoneceloutro"));
            gestor.setNrTelefoneCelVivo(rs.getString("nrtelefonecelvivo"));
            gestor.setNrTelefoneFixo(rs.getString("nrtelefonefixo"));
            gestor.setSgTipoRelacionamento(rs.getString("sgtiporelacionamento"));
            gestor.setIdConta(null);
            gestor.setSgTipoDocumento(null);
            list.add(gestor);
        }
        } finally {
			if (rs != null) {
				try {
        rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
        database.release();
		}

        return list.toArray(new Gestor[0]);
    }

    @Override
    public PessoaConsultor[] getConsultorRelacionamento(String cdCnpjEmpresa) throws SQLException {

        StringBuffer query = new StringBuffer();
        query.append("SELECT PESSOACONSULTOR.NRDOCUMENTO                    nrDocumento, ");
        query.append("       PESSOACONSULTOR.IDPESSOA                       idPessoa, ");
        query.append("       PESSOACONSULTOR.IDTIPORELACIONAMENTO           idTipoRelacionamento, ");
        query.append("       TIPORELACIONAMENTO.SGTIPORELACIONAMENTO        sgTipoRelacionamento, ");
        query.append("       USUARIO.NMLOGINUSUARIO                         nmLoginUsuario, ");
        query.append("       USUARIO.NMNOME                                 nmNome, ");
        query.append("       USUARIO.IDPERFILCONSULTORATD                   idPerfilConsultorAtd ");
        query.append("  FROM CUSTOMER.PESSOACONSULTOR PESSOACONSULTOR, ");
        query.append("       CUSTOMER.TIPORELACIONAMENTO TIPORELACIONAMENTO, ");
        query.append("       ACESSO.USUARIO USUARIO ");
        query.append(" WHERE PESSOACONSULTOR.IDTIPORELACIONAMENTO = TIPORELACIONAMENTO.IDTIPORELACIONAMENTO ");
        query.append("   AND PESSOACONSULTOR.IDPESSOA = USUARIO.IDPESSOAUSUARIO ");
        query.append("   AND PESSOACONSULTOR.NRDOCUMENTO = '").append(cdCnpjEmpresa).append("' ");

        ArrayList<PessoaConsultor> list = new ArrayList<PessoaConsultor>();
        Statement st = null;
        ResultSet rs = null;
        try {
	        st = database.getConnection().createStatement();
	        rs = st.executeQuery(query.toString());

        while (rs.next()) {
            PessoaConsultor pessoaConsultor = new PessoaConsultor();
            pessoaConsultor.setIdPessoa(rs.getString("idPessoa"));
            pessoaConsultor.setIdTipoRelacionamento(rs.getString("idTipoRelacionamento"));
            pessoaConsultor.setIPerfilConsultorAtd(rs.getString("idPerfilConsultorAtd"));
            pessoaConsultor.setNmLoginUsuario(rs.getString("nmLoginUsuario"));
            pessoaConsultor.setNmNome(rs.getString("nmNome"));
            pessoaConsultor.setNrDocumento(rs.getString("nrDocumento"));
            pessoaConsultor.setSgTipoRelacionamento(rs.getString("sgTipoRelacionamento"));
            pessoaConsultor.setIdPessoaSistemaOrigem(null);
            list.add(pessoaConsultor);
        }
        } finally {
			if (rs != null) {
				try {
        rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
        database.release();
		}

        return list.toArray(new PessoaConsultor[0]);
    }

    @Override
    public ClienteEspecial[] getClienteEspecial(String cdCnpjEmpresa) throws SQLException {
        StringBuffer query = new StringBuffer();
        query.append("SELECT CLIENTEESPECIAL.CDCODIGOCLIENTEESPECIAL      cdCodigoClienteEspecial, ");
        query.append("       CLIENTEESPECIAL.NMNOMECOMPLETO               nmNomeCompleto, ");
        query.append("       CLIENTEESPECIAL.NRLINHACONTATO1              nrLinhaContato1, ");
        query.append("       CLIENTEESPECIAL.NRLINHACONTATO2              nrLinhaContato2, ");
        query.append("       CLIENTEESPECIAL.NRCPF                        nrCpf, ");
        query.append("       CLIENTEESPECIAL.EMAIL                        email, ");
        query.append("       CLIENTEESPECIAL.DTANIVERSARIO                dtAniversario, ");
        query.append("       CLIENTEESPECIAL.IDTIPORELACIONAMENTO         tpCargo, ");
        query.append("       CLIENTEESPECIAL.CNPJ                         cnpj, ");
        query.append("       TIPORELACIONAMENTO.NMTIPORELACIONAMENTO      nmTipoRelacionamento, ");
        query.append("       CLIENTEESPECIAL.IDUSUARIOALTERACAO           idUuarioAlteracao, ");
        query.append("       CLIENTEESPECIAL.DTULTIMAALTERACAO            dtUltimaAlteracao, ");
        query.append("       NVL(USUARIO.NMLOGINUSUARIO,'Não informado')     nmLoginUsuario ");
        query.append("  FROM CUSTOMER.CLIENTEESPECIAL CLIENTEESPECIAL, ");
        query.append("       CUSTOMER.TIPORELACIONAMENTO TIPORELACIONAMENTO, ");
        query.append("       ACESSO.USUARIO USUARIO ");
        query.append(" WHERE CLIENTEESPECIAL.IDTIPORELACIONAMENTO = TIPORELACIONAMENTO.IDTIPORELACIONAMENTO ");
        query.append("   AND CLIENTEESPECIAL.IDUSUARIOALTERACAO = USUARIO.IDPESSOAUSUARIO(+) ");
        query.append("   AND CLIENTEESPECIAL.CNPJ = '").append(cdCnpjEmpresa).append("' ");

        ArrayList<ClienteEspecial> list = new ArrayList<ClienteEspecial>();
        Statement st = null;
        ResultSet rs = null;
	    try {
	        st = database.getConnection().createStatement();
	        rs = st.executeQuery(query.toString());

        while (rs.next()) {
            ClienteEspecial clienteEspecial = new ClienteEspecial();
            clienteEspecial.setCdCodigoClienteEspecial(rs.getString("cdCodigoClienteEspecial"));
            clienteEspecial.setNmNomeCompleto(rs.getString("nmNomeCompleto"));
            clienteEspecial.setNrLinhaContato1(rs.getString("nrLinhaContato1"));
            clienteEspecial.setNrLinhaContato2(rs.getString("nrLinhaContato2"));
            clienteEspecial.setNrCpf(rs.getString("nrCpf"));
            clienteEspecial.setEmail(rs.getString("email"));
            clienteEspecial.setDtAniversario(rs.getDate("dtAniversario"));
            clienteEspecial.setTpCargo(rs.getString("tpCargo"));
            clienteEspecial.setCnpj(rs.getString("cnpj"));
            clienteEspecial.setNmTipoRelacionamento(rs.getString("nmTipoRelacionamento"));
            clienteEspecial.setIdUuarioAlteracao(rs.getString("idUuarioAlteracao"));
            clienteEspecial.setDtUltimaAlteracao(rs.getDate("dtUltimaAlteracao"));
            clienteEspecial.setNmLoginUsuario(rs.getString("nmLoginUsuario"));
            list.add(clienteEspecial);
        }
        } finally {
			if (rs != null) {
				try {
        rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
        database.release();
		}

        return list.toArray(new ClienteEspecial[0]);
    }
}
