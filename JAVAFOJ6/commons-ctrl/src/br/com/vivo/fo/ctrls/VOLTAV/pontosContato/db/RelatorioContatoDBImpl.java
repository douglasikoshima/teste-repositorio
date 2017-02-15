package br.com.vivo.fo.ctrls.VOLTAV.pontosContato.db;

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

@Stateless(name="RelatorioContatoDB",mappedName="RelatorioContatoDB")
@Local(RelatorioContatoDB.class)
@Session(ejbName = "RelatorioContatoDB", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, 
		defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class RelatorioContatoDBImpl implements RelatorioContatoDB {

	@EJB
	private DataBaseCall database;
	
	@Override
	public PontoContato[] getRelatorioContato(String cdCnpjEmpresa) throws SQLException {
		
		StringBuffer query = new StringBuffer();
		query.append("SELECT  IDTIPOEMPRESA                 idTipoEmpresa, ");
	    query.append("DSCONSULTORRELACIONAMENTO DsConsultorRelacionamento, ");
	    query.append("NRTELEFONECONSULTOR             nrTelefoneConsultor, ");
	    query.append("DSEMAILCONSULTOR                   dsEmailConsultor, ");
	    query.append("NMTECNICORESIDENTE               nmTecnicoResidente, ");
	    query.append("NRTELEFONETECNICO                 nrTelefoneTecnico, ");
	    query.append("DSEMAILTECNICO                       dsEmailTecnico, ");
	    query.append("NMGERENTECONTAS                     nmGerenteContas, ");
	    query.append("NRTELEFONEGERCONTAS             nrTelefoneGerContas, ");
	    query.append("DSEMAILGERCONTAS                   dsEmailGerContas, ");
	    query.append("DSGAM                                         dsGAM, ");
	    query.append("NRTELEFONEGAM                         nrTelefoneGAM, ");
	    query.append("DSEMAILGAM                               dsEmailGAM, ");
	    query.append("DSGERENTESECAO                       dsGerenteSecao, ");
	    query.append("NRTELEFONEGERSECAO               nrTelefoneGerSecao, ");
	    query.append("DSEMAILGERSECAO                     dsEmailGerSecao, ");
	    query.append("DSGERENTEDIVISAO                   dsGerenteDivisao, ");
	    query.append("NRTELEFONEGERDIVISAO           nrTelenoneGerDivisao, ");
	    query.append("SGUF                                           sgUF, ");
	    query.append("DSEMAILGERDIVISAO                 dsEmailGerDivisao, ");
	    query.append("NMDEALER                         		  nmDealer,    ");     
	    query.append("DSRAZAOSOCIALDEALER             dsRazaoSocialDealer, ");
	    query.append("NRTELEF1                                   nrTelef1, ");
	    query.append("NRTELEF2                                   nrTelef2, ");
	    query.append("DSEMAILDEALER                         dsEmailDealer, ");
	    query.append("DSCIDADEDEALER                       dsCidadeDealer, ");
	    query.append("CDCNPJEMPRESA                         cdCnpjEmpresa ");
	    query.append("FROM VOL.PONTOCONTATO  ");
	    query.append("WHERE CDCNPJEMPRESA = ").append(cdCnpjEmpresa);
		
	    ArrayList<PontoContato> list = new ArrayList<PontoContato>();
		ResultSet rs = database.executeQuery(query.toString());
		
		while(rs.next()){
			PontoContato pontoContato = new PontoContato();
			pontoContato.setIdTipoEmpresa(rs.getString("idTipoEmpresa"));
			pontoContato.setDsConsultorRelacionamento(rs.getString("dsConsultorRelacionamento"));
			pontoContato.setNrTelefoneConsultor(rs.getString("nrTelefoneConsultor"));
			pontoContato.setDsEmailConsultor(rs.getString("dsEmailConsultor"));
			pontoContato.setNmTecnicoResidente(rs.getString("nmTecnicoResidente"));
	        pontoContato.setNrTelefoneTecnico(rs.getString("nrTelefoneTecnico"));
	        pontoContato.setDsEmailTecnico(rs.getString("dsEmailTecnico"));
	        pontoContato.setNmGerenteContas(rs.getString("nmGerenteContas"));
	        pontoContato.setNrTelefoneGerContas(rs.getString("nrTelefoneGerContas"));
	        pontoContato.setDsEmailGerContas(rs.getString("dsEmailGerContas"));
	        pontoContato.setDsGAM(rs.getString("dsGAM"));
	        pontoContato.setNrTelefoneGAM(rs.getString("nrTelefoneGAM"));
	        pontoContato.setDsEmailGAM(rs.getString("dsEmailGAM"));
	        pontoContato.setDsGerenteSecao(rs.getString("dsGerenteSecao"));
	        pontoContato.setNrTelefoneGerSecao(rs.getString("nrTelefoneGerSecao"));
	        pontoContato.setDsEmailGerSecao(rs.getString("dsEmailGerSecao"));
	        pontoContato.setDsGerenteDivisao(rs.getString("dsGerenteDivisao"));
	        pontoContato.setNrTelenoneGerDivisao(rs.getString("nrTelenoneGerDivisao"));
	        pontoContato.setSgUF(rs.getString("sgUF"));
	        pontoContato.setDsEmailGerDivisao(rs.getString("dsEmailGerDivisao"));
	        pontoContato.setDsRazaoSocialDealer(rs.getString("dsRazaoSocialDealer"));
	        pontoContato.setNmDealer(rs.getString("nmDealer"));
	        pontoContato.setNrTelef1(rs.getString("nrTelef1"));
	        pontoContato.setNrTelef2(rs.getString("nrTelef2"));
	        pontoContato.setDsEmailDealer(rs.getString("dsEmailDealer"));
	        pontoContato.setDsCidadeDealer(rs.getString("dsCidadeDealer"));
	        pontoContato.setCdCnpjEmpresa(rs.getString("cdCnpjEmpresa"));
	        pontoContato.setNmLoginUsuario(rs.getString("nmLoginUsuario"));
	        pontoContato.setInAtivaSolicitacaoWEB(rs.getString("inAtivaSolicitacaoWEB"));
	        pontoContato.setSolicitaLinha(rs.getString("solicitaLinha"));
	        list.add(pontoContato);
			
		}
		rs.close();
		database.release();
	
		return list.toArray(new PontoContato[0]);
		
	}

	@Override
	public PontoContato[] getRelatorioContatoPorCnpj(String cdCnpjEmpresa) throws SQLException {
		
		StringBuffer query = new StringBuffer();
		query.append("SELECT IDTIPOEMPRESA                   idTipoEmpresa, ");
	    query.append("CDCNPJEMPRESA                          cdCnpjEmpresa, ");
	    query.append("SGUF                                   sgUF, ");
	    query.append("NVL(DSCONSULTORRELACIONAMENTO, ' ')    DsConsultorRelacionamento, ");
	    query.append("NVL(NRTELEFONECONSULTOR, '')           nrTelefoneConsultor, ");
	    query.append("NVL(DSEMAILCONSULTOR, ' ')             dsEmailConsultor, ");
	    query.append("NVL(NMTECNICORESIDENTE, ' ')           nmTecnicoResidente, ");
	    query.append("NVL(NRTELEFONETECNICO, '')             nrTelefoneTecnico, ");
	    query.append("NVL(DSEMAILTECNICO, ' ')               dsEmailTecnico, ");
	    query.append("NVL(NMGERENTECONTAS, ' ')              nmGerenteContas, ");
	    query.append("NVL(NRTELEFONEGERCONTAS, '')           nrTelefoneGerContas, ");
	    query.append("NVL(DSEMAILGERCONTAS, ' ')             dsEmailGerContas    , ");
	    query.append("NVL(DSGAM, ' ')                        dsGAM, ");
	    query.append("NVL(NRTELEFONEGAM, '')                 nrTelefoneGAM, ");
	    query.append("NVL(DSEMAILGAM, ' ')                   dsEmailGAM, ");
	    query.append("NVL(DSGERENTESECAO, ' ')               dsGerenteSecao, ");
	    query.append("NVL(NRTELEFONEGERSECAO, '')            nrTelefoneGerSecao, ");
	    query.append("NVL(DSEMAILGERSECAO, ' ')              dsEmailGerSecao, ");
	    query.append("NVL(DSGERENTEDIVISAO, ' ')             dsGerenteDivisao, ");
	    query.append("NVL(NRTELEFONEGERDIVISAO, '')          nrTelenoneGerDivisao, ");
	    query.append("NVL(DSEMAILGERDIVISAO, ' ')            dsEmailGerDivisao, ");
	    query.append("NVL(NMDEALER, ' ')           		   	 nmDealer, ");
	    query.append("NVL(DSRAZAOSOCIALDEALER, ' ')          dsRazaoSocialDealer, ");
	    query.append("NVL(NRTELEF1, ' ')                     nrTelef1, ");
	    query.append("NVL(NRTELEF2, ' ')                     nrTelef2, ");
	    query.append("NVL(DSEMAILDEALER, ' ')                dsEmailDealer, ");
	    query.append("NVL(DSCIDADEDEALER, ' ')               dsCidadeDealer, ");
	    query.append("NVL(NMLOGINUSUARIO, ' ')               nmLoginUsuario, ");
	    query.append("NVL(INATIVASOLICITACAOWEB, 'N')        inAtivaSolicitacaoWEB, ");
	    query.append("NVL(SOLICITALINHA, '')                 solicitaLinha ");
	    query.append("FROM VOL.PONTOCONTATO  ");
	    query.append("WHERE CDCNPJEMPRESA = ").append(cdCnpjEmpresa);
	    
	    ArrayList<PontoContato> list = new ArrayList<PontoContato>();
		ResultSet rs = database.executeQuery(query.toString());
		
		while(rs.next()){
			PontoContato pontoContato = new PontoContato();
			pontoContato.setIdTipoEmpresa(rs.getString("idTipoEmpresa"));
			pontoContato.setDsConsultorRelacionamento(rs.getString("dsConsultorRelacionamento"));
			pontoContato.setNrTelefoneConsultor(rs.getString("nrTelefoneConsultor"));
			pontoContato.setDsEmailConsultor(rs.getString("dsEmailConsultor"));
			pontoContato.setNmTecnicoResidente(rs.getString("nmTecnicoResidente"));
	        pontoContato.setNrTelefoneTecnico(rs.getString("nrTelefoneTecnico"));
	        pontoContato.setDsEmailTecnico(rs.getString("dsEmailTecnico"));
	        pontoContato.setNmGerenteContas(rs.getString("nmGerenteContas"));
	        pontoContato.setNrTelefoneGerContas(rs.getString("nrTelefoneGerContas"));
	        pontoContato.setDsEmailGerContas(rs.getString("dsEmailGerContas"));
	        pontoContato.setDsGAM(rs.getString("dsGAM"));
	        pontoContato.setNrTelefoneGAM(rs.getString("nrTelefoneGAM"));
	        pontoContato.setDsEmailGAM(rs.getString("dsEmailGAM"));
	        pontoContato.setDsGerenteSecao(rs.getString("dsGerenteSecao"));
	        pontoContato.setNrTelefoneGerSecao(rs.getString("nrTelefoneGerSecao"));
	        pontoContato.setDsEmailGerSecao(rs.getString("dsEmailGerSecao"));
	        pontoContato.setDsGerenteDivisao(rs.getString("dsGerenteDivisao"));
	        pontoContato.setNrTelenoneGerDivisao(rs.getString("nrTelenoneGerDivisao"));
	        pontoContato.setSgUF(rs.getString("sgUF"));
	        pontoContato.setDsEmailGerDivisao(rs.getString("dsEmailGerDivisao"));
	        pontoContato.setDsRazaoSocialDealer(rs.getString("dsRazaoSocialDealer"));
	        pontoContato.setNmDealer(rs.getString("nmDealer"));
	        pontoContato.setNrTelef1(rs.getString("nrTelef1"));
	        pontoContato.setNrTelef2(rs.getString("nrTelef2"));
	        pontoContato.setDsEmailDealer(rs.getString("dsEmailDealer"));
	        pontoContato.setDsCidadeDealer(rs.getString("dsCidadeDealer"));
	        pontoContato.setCdCnpjEmpresa(rs.getString("cdCnpjEmpresa"));
	        pontoContato.setNmLoginUsuario(rs.getString("nmLoginUsuario"));
	        pontoContato.setInAtivaSolicitacaoWEB(rs.getString("inAtivaSolicitacaoWEB"));
	        pontoContato.setSolicitaLinha(rs.getString("solicitaLinha"));
	        list.add(pontoContato);
			
		}
		rs.close();
		database.release();
	
		return list.toArray(new PontoContato[0]);
		
	}

	@Override
	public PontoContato[] getRelatorioContatoCompleto(int pageInicial, int pageFinal) throws SQLException {
		
		StringBuffer query = new StringBuffer();
		query.append("SELECT IDTIPOEMPRESA                   idTipoEmpresa, ");            
	    query.append("CDCNPJEMPRESA                          cdCnpjEmpresa, ");
	    query.append("SGUF                                   sgUF, ");
	    query.append("NVL(DSCONSULTORRELACIONAMENTO, ' ')    DsConsultorRelacionamento, ");
	    query.append("NVL(NRTELEFONECONSULTOR, '')           nrTelefoneConsultor, ");
	    query.append("NVL(DSEMAILCONSULTOR, ' ')             dsEmailConsultor, ");
	    query.append("NVL(NMTECNICORESIDENTE, ' ')           nmTecnicoResidente, ");
	    query.append("NVL(NRTELEFONETECNICO, '')             nrTelefoneTecnico, ");
	    query.append("NVL(DSEMAILTECNICO, ' ')               dsEmailTecnico, ");
	    query.append("NVL(NMGERENTECONTAS, ' ')              nmGerenteContas,");
	    query.append("NVL(NRTELEFONEGERCONTAS, '')           nrTelefoneGerContas, ");
	    query.append("NVL(DSEMAILGERCONTAS, ' ')             dsEmailGerContas    , ");
	    query.append("NVL(DSGAM, ' ')                        dsGAM, ");
	    query.append("NVL(NRTELEFONEGAM, '')                 nrTelefoneGAM, ");
	    query.append("NVL(DSEMAILGAM, ' ')                   dsEmailGAM, ");
	    query.append("NVL(DSGERENTESECAO, ' ')               dsGerenteSecao, ");
	    query.append("NVL(NRTELEFONEGERSECAO, '')            nrTelefoneGerSecao, ");
	    query.append("NVL(DSEMAILGERSECAO, ' ')              dsEmailGerSecao, ");
	    query.append("NVL(DSGERENTEDIVISAO, ' ')             dsGerenteDivisao, ");
	    query.append("NVL(NRTELEFONEGERDIVISAO, '')          nrTelenoneGerDivisao, ");
	    query.append("NVL(DSEMAILGERDIVISAO, ' ')            dsEmailGerDivisao, ");
	    query.append("NVL(NMDEALER, ' ')           		   	 nmDealer, ");
	    query.append("NVL(DSRAZAOSOCIALDEALER, ' ')          dsRazaoSocialDealer, ");
	    query.append("NVL(NRTELEF1, ' ')                     nrTelef1, ");
	    query.append("NVL(NRTELEF2, ' ')                     nrTelef2, ");
	    query.append("NVL(DSEMAILDEALER, ' ')                dsEmailDealer, ");
	    query.append("NVL(DSCIDADEDEALER, ' ')               dsCidadeDealer, ");
	    query.append("NVL(NMLOGINUSUARIO, ' ')               nmLoginUsuario, ");
	    query.append("NVL(INATIVASOLICITACAOWEB, 'N')        inAtivaSolicitacaoWEB, ");
	    query.append("NVL(SOLICITALINHA, '')                 solicitaLinha ");
	    query.append("FROM ");
	    query.append("( ");
	    query.append("SELECT ");
	    query.append("    ROWNUM AS NLIN, "); 
	    query.append("    IDTIPOEMPRESA,  ");         
	    query.append("    CDCNPJEMPRESA, ");            
	    query.append("    SGUF, ");                     
	    query.append("    DSCONSULTORRELACIONAMENTO, ");
	    query.append("    NRTELEFONECONSULTOR, ");     
	    query.append("    DSEMAILCONSULTOR, ");         
	    query.append("    NMTECNICORESIDENTE, ");       
	    query.append("    NRTELEFONETECNICO, ");        
	    query.append("    DSEMAILTECNICO, ");           
	    query.append("    NMGERENTECONTAS, ");          
	    query.append("    NRTELEFONEGERCONTAS, ");      
	    query.append("    DSEMAILGERCONTAS, ");         
	    query.append("    DSGAM, ");                     
	    query.append("    NRTELEFONEGAM, ");            
	    query.append("    DSEMAILGAM, ");               
	    query.append("    DSGERENTESECAO, ");           
	    query.append("    NRTELEFONEGERSECAO, ");      
	    query.append("    DSEMAILGERSECAO, ");          
	    query.append("    DSGERENTEDIVISAO, ");        
	    query.append("    NRTELEFONEGERDIVISAO, ");     
	    query.append("    DSEMAILGERDIVISAO, ");      
	    query.append("    NMDEALER, ");
	    query.append("    DSRAZAOSOCIALDEALER, ");      
	    query.append("    NRTELEF1, ");                 
	    query.append("    NRTELEF2, ");                
	    query.append("    DSEMAILDEALER, ");            
	    query.append("    DSCIDADEDEALER, ");           
	    query.append("    NMLOGINUSUARIO, ");           
	    query.append("    INATIVASOLICITACAOWEB,");    
	    query.append("    SOLICITALINHA ");
	    query.append("FROM VOL.PONTOCONTATO ");
	    query.append(") ");
	    query.append("WHERE NLIN >= ").append(pageInicial).append(" AND NLIN <= ").append(pageFinal).append(") ");
	    query.append("ORDER BY CDCNPJEMPRESA ");
	    
	    ArrayList<PontoContato> list = new ArrayList<PontoContato>();
		ResultSet rs = database.executeQuery(query.toString());
		
		while(rs.next()){
			PontoContato pontoContato = new PontoContato();
			pontoContato.setIdTipoEmpresa(rs.getString("idTipoEmpresa"));
			pontoContato.setDsConsultorRelacionamento(rs.getString("dsConsultorRelacionamento"));
			pontoContato.setNrTelefoneConsultor(rs.getString("nrTelefoneConsultor"));
			pontoContato.setDsEmailConsultor(rs.getString("dsEmailConsultor"));
			pontoContato.setNmTecnicoResidente(rs.getString("nmTecnicoResidente"));
	        pontoContato.setNrTelefoneTecnico(rs.getString("nrTelefoneTecnico"));
	        pontoContato.setDsEmailTecnico(rs.getString("dsEmailTecnico"));
	        pontoContato.setNmGerenteContas(rs.getString("nmGerenteContas"));
	        pontoContato.setNrTelefoneGerContas(rs.getString("nrTelefoneGerContas"));
	        pontoContato.setDsEmailGerContas(rs.getString("dsEmailGerContas"));
	        pontoContato.setDsGAM(rs.getString("dsGAM"));
	        pontoContato.setNrTelefoneGAM(rs.getString("nrTelefoneGAM"));
	        pontoContato.setDsEmailGAM(rs.getString("dsEmailGAM"));
	        pontoContato.setDsGerenteSecao(rs.getString("dsGerenteSecao"));
	        pontoContato.setNrTelefoneGerSecao(rs.getString("nrTelefoneGerSecao"));
	        pontoContato.setDsEmailGerSecao(rs.getString("dsEmailGerSecao"));
	        pontoContato.setDsGerenteDivisao(rs.getString("dsGerenteDivisao"));
	        pontoContato.setNrTelenoneGerDivisao(rs.getString("nrTelenoneGerDivisao"));
	        pontoContato.setSgUF(rs.getString("sgUF"));
	        pontoContato.setDsEmailGerDivisao(rs.getString("dsEmailGerDivisao"));
	        pontoContato.setDsRazaoSocialDealer(rs.getString("dsRazaoSocialDealer"));
	        pontoContato.setNmDealer(rs.getString("nmDealer"));
	        pontoContato.setNrTelef1(rs.getString("nrTelef1"));
	        pontoContato.setNrTelef2(rs.getString("nrTelef2"));
	        pontoContato.setDsEmailDealer(rs.getString("dsEmailDealer"));
	        pontoContato.setDsCidadeDealer(rs.getString("dsCidadeDealer"));
	        pontoContato.setCdCnpjEmpresa(rs.getString("cdCnpjEmpresa"));
	        pontoContato.setNmLoginUsuario(rs.getString("nmLoginUsuario"));
	        pontoContato.setInAtivaSolicitacaoWEB(rs.getString("inAtivaSolicitacaoWEB"));
	        pontoContato.setSolicitaLinha(rs.getString("solicitaLinha"));
	        list.add(pontoContato);
			
		}
		rs.close();
		database.release();
	
		return list.toArray(new PontoContato[0]);
		
	}

}
