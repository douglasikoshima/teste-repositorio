package br.com.vivo.catalogoPRS.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.to.CanalAtendimentoTO;

import pt.ptinovacao.sca.AccessLoginModule;
import pt.ptinovacao.sca.AuthorizationManager;
import pt.ptinovacao.sca.ClientSystem;
import pt.ptinovacao.sca.ControlPoint;
import pt.ptinovacao.sca.Domain;
import pt.ptinovacao.sca.DomainFamily;
import pt.ptinovacao.sca.LoginProcess;
import pt.ptinovacao.sca.ManagementSubSystem;
import pt.ptinovacao.sca.Profile;
import pt.ptinovacao.sca.SCAException;
import pt.ptinovacao.sca.User;
import pt.ptinovacao.sca.UserSession;
import pt.ptinovacao.sca.database.AccessLoginDatabase;
import pt.ptinovacao.sca.database.DatabaseConnectionManager;
import pt.ptinovacao.sca.database.DatabaseConnectionManagerAdapter;
import pt.ptinovacao.sca.web.AccessLoginWebDatabase;
import br.com.vivo.catalogoPRS.bo.DadosCookie;
import br.com.vivo.catalogoPRS.bo.UserCatalogo;
import br.com.vivo.catalogoPRS.exception.UsuarioInvalidoException;


public class SCAUtils {

	private static Logger logger = Logger.getLogger(SCAUtils.class);

	private static DataSource getDataSource() {
		Context ctx = null;
		Hashtable<String, String> ht = new Hashtable<String, String>();
		ht.put(Context.INITIAL_CONTEXT_FACTORY, ApplicationConfiguration.getScaDatasourceContextFactory());
		ht.put(Context.PROVIDER_URL, ApplicationConfiguration.getScaDatasourceURL());
		DataSource ds = null;

		try {
			ctx = new InitialContext();
			ds = (javax.sql.DataSource) ctx.lookup(ApplicationConfiguration.getScaDatasourceJndiName());
			ctx.close();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return ds;
	}

	/**
	 * Autentica o usu�rio no BCA.
	 * 
	 * @param login
	 *            usu�rio.
	 * @param pass
	 *            senha.
	 * @param host
	 *            host.
	 * @param ip
	 *            ip.
	 * @throws LoginException
	 *             caso ocorra qualquer erro no processo de autentica��o.
	 * @return Logged user.
	 * @throws UsuarioInvalidoException 
	 */
	@SuppressWarnings("rawtypes")
	public final static Map<Long, String> obterPerfisVivo360(LoginProcess loginProcess, UserCatalogo userCatalogo) {
		Map<Long, String> userProfilesByIdProfile = new HashMap<Long, String>();

		if (loginProcess == null)
			return userProfilesByIdProfile;

		AuthorizationManager authManager = loginProcess.getAuthorizationManager();
		
		try {
			Collection collectionPerfilVivo360 = authManager.getArrayPerfisSG("Vivo360");	
			for (Object perfilVivo360 : collectionPerfilVivo360) {
				Profile profileVivo360 = (Profile) perfilVivo360;
				userProfilesByIdProfile.put(Long.valueOf(profileVivo360.getId()), profileVivo360.getName());
			}
			
			Collection collectionPerfilCallCenter = authManager.getArrayPerfisSG("Vivo360 Call Center");	
			for(Object perfilCallCenter : collectionPerfilCallCenter) {
				Profile profileCallCenter = (Profile) perfilCallCenter;
				userProfilesByIdProfile.put(Long.valueOf(profileCallCenter.getId()), profileCallCenter.getName());
			}

			Collection collectionPerfilVivo360Express = authManager.getArrayPerfisSG("Vivo360 Express");	
			for(Object perfilCallCenter : collectionPerfilVivo360Express) {
				Profile profileCallCenter = (Profile) perfilCallCenter;
				userProfilesByIdProfile.put(Long.valueOf(profileCallCenter.getId()), profileCallCenter.getName());
			}
			
		} catch (SCAException e) {
			e.printStackTrace();
		}
		return userProfilesByIdProfile;
	}
	
	@SuppressWarnings("rawtypes")
	public final static Map<Long, ProfileSys> obterPerfisVivo360CallCenter(LoginProcess loginProcess, UserCatalogo userCatalogo) {
		Map<Long, ProfileSys> userProfilesByIdProfile = new HashMap<Long, ProfileSys>();
		if (loginProcess == null){
			return userProfilesByIdProfile;
		}

		AuthorizationManager authManager = loginProcess.getAuthorizationManager();
		try {
			Collection perfilVivo360 = authManager.getArrayPerfisSG("Vivo360");	
			for (Object vivo360 : perfilVivo360) {
				Profile profileVivo360 = (Profile) vivo360;
				ProfileSys profile360 = new ProfileSys();
				profile360.setIdSistema(6L);
				profile360.setId(Long.valueOf(profileVivo360.getId()));
				profile360.setName(profileVivo360.getName());
				userProfilesByIdProfile.put(Long.valueOf(profileVivo360.getId()), profile360);
			}
			
			Collection perfilCallCenter = authManager.getArrayPerfisSG("Vivo360 Call Center");	
			for(Object callCenter : perfilCallCenter) {
				Profile profileCallCenter = (Profile) callCenter;
				ProfileSys profileCall = new ProfileSys();
				profileCall.setIdSistema(7L);
				profileCall.setId(Long.valueOf(profileCallCenter.getId()));
				profileCall.setName(profileCallCenter.getName());
				userProfilesByIdProfile.put(Long.valueOf(profileCallCenter.getId()), profileCall);
			}

			Collection perfisVivo360Express = authManager.getArrayPerfisSG("Vivo360 Express");	
			for(Object vivo360Express : perfisVivo360Express) {
				Profile profileVivo360Express = (Profile) vivo360Express;
				ProfileSys profileExpress = new ProfileSys();
				profileExpress.setIdSistema(8L);
				profileExpress.setId(Long.valueOf(profileVivo360Express.getId()));
				profileExpress.setName(profileVivo360Express.getName());
				userProfilesByIdProfile.put(Long.valueOf(profileVivo360Express.getId()), profileExpress);
			}
			
		} catch (SCAException e) {
			e.printStackTrace();
		}
		return userProfilesByIdProfile;
	}
	
	@SuppressWarnings("rawtypes")
	public final static Map<Long, ProfileSys> obterPerfisCallCenter(LoginProcess loginProcess, UserCatalogo userCatalogo) {
		Map<Long, ProfileSys> userProfilesByIdProfile = new HashMap<Long, ProfileSys>();
		if (loginProcess == null){
			return userProfilesByIdProfile;
		}

		AuthorizationManager authManager = loginProcess.getAuthorizationManager();
		try {
			Collection perfilCallCenter = authManager.getArrayPerfisSG("Vivo360 Call Center");	
			for(Object callCenter : perfilCallCenter) {
				Profile profileCallCenter = (Profile) callCenter;
				ProfileSys profileCall = new ProfileSys();
				profileCall.setIdSistema(7L);
				profileCall.setId(Long.valueOf(profileCallCenter.getId()));
				profileCall.setName(profileCallCenter.getName());
				userProfilesByIdProfile.put(Long.valueOf(profileCallCenter.getId()), profileCall);
			}
			
		} catch (SCAException e) {
			e.printStackTrace();
		}
		return userProfilesByIdProfile;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public final static UserCatalogo authenticate(final String login, final String pass, final String host, final String ip) throws LoginException, UsuarioInvalidoException {
		if (logger.isDebugEnabled()) {
			logger.debug("Iniciando autenticação...");
		}
		DatabaseConnectionManager dbManager = new DatabaseConnectionManagerAdapter(getDataSource());
		AccessLoginWebDatabase.setDatabaseConnectionManager(dbManager);
		AccessLoginDatabase accessLoginDatabase = new AccessLoginDatabase(dbManager);
		User userLoginTentativesStatus = null;
		try{
			userLoginTentativesStatus = accessLoginDatabase.getUserLoginTentativesStatus(login);
		}catch(Exception e){
			e.printStackTrace();
		}
		ClientSystem clientSystem = new ClientSystem();
		clientSystem.setSystemName(host);
		clientSystem.setIp(ip);
		AccessLoginModule loginModule = AccessLoginWebDatabase.createAccessLoginWebDatabase(clientSystem);
		UserSession userSession = loginModule.login(login, pass, "Catalogo");
		LoginProcess loginProcess = loginModule.getProcessLogin();
		User user = loginProcess.getUserManager().getUser();
		UserCatalogo userCatalogo = new UserCatalogo();
		userCatalogo.setUser(user);
		userCatalogo.setClientHostname(host);
		userCatalogo.setClientIP(ip);
		userCatalogo.setUserSession(userSession);
		userCatalogo.setUltimaAtividade(new Date());
		if(userLoginTentativesStatus != null){
			userCatalogo.setMaxTentativasLogin(userLoginTentativesStatus.getMaxLoginAttempts());
			userCatalogo.setNumTentativasLogin(userLoginTentativesStatus.getNumTentLogin());
		}
		
		Map permissoesDoUsuario = SCAUtils.carregarPermissoes(loginProcess, userCatalogo);
		userCatalogo.setPermissoes(permissoesDoUsuario);
		if (logger.isDebugEnabled()) {
			logger.debug("Autenticação realizada com sucesso!");
		}
				
		return userCatalogo;
	}

	public final static LoginProcess verify(UserCatalogo userCatalogo) throws LoginException {
		DatabaseConnectionManager dbManager = new DatabaseConnectionManagerAdapter(getDataSource());
		AccessLoginWebDatabase.setDatabaseConnectionManager(dbManager);
		ClientSystem clientSystem = new ClientSystem();
		clientSystem.setSystemName(userCatalogo.getClientHostname());
		clientSystem.setIp(userCatalogo.getClientIP());
		AccessLoginModule loginModule = AccessLoginWebDatabase.createAccessLoginWebDatabase(clientSystem);
		loginModule.login(userCatalogo.getUserSession().getId().replaceFirst("/1", ""));
		LoginProcess loginProcess = loginModule.getProcessLogin();
		return loginProcess;
	}

	public final static boolean logout(UserCatalogo userCatalogo) throws LoginException {
		DatabaseConnectionManager dbManager = new DatabaseConnectionManagerAdapter(getDataSource());
		AccessLoginWebDatabase.setDatabaseConnectionManager(dbManager);
		ClientSystem clientSystem = new ClientSystem();
		clientSystem.setSystemName(userCatalogo.getClientHostname());
		clientSystem.setIp(userCatalogo.getClientIP());
		AccessLoginModule loginModule = AccessLoginWebDatabase.createAccessLoginWebDatabase(clientSystem);
		loginModule.login(userCatalogo.getUserSession().getId().replaceFirst("/1", ""));
		LoginProcess loginProcess = loginModule.getProcessLogin();
		if (loginProcess.isUserAuthenticated()) {
			return loginProcess.logout();
		} else {
			return true;
		}
	}

	public final static boolean logoutWithCheckNullableUser(UserCatalogo userCatalogo) throws LoginException {
		
		boolean retorno = true;
		
		if (userCatalogo != null){
			retorno = logout(userCatalogo);
		}
		
		return retorno;
	}
	
	public final static UserCatalogo alterarSenha(String login, String senhaAntiga, String senhaNova, String host, String ip) throws LoginException, UsuarioInvalidoException {
		DatabaseConnectionManager dbManager = new DatabaseConnectionManagerAdapter(getDataSource());
		AccessLoginWebDatabase.setDatabaseConnectionManager(dbManager);
		ClientSystem clientSystem = new ClientSystem();
		clientSystem.setSystemName(host);
		clientSystem.setIp(ip);
		AccessLoginModule loginModule = AccessLoginWebDatabase.createAccessLoginWebDatabase(clientSystem);
		if (loginModule.changePassword(login, senhaAntiga, senhaNova, "Catalogo")) {
			return authenticate(login,senhaNova, host,ip);
		}
		return null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public final static Map<String, Map<String, List<String>>> carregarPermissoes(LoginProcess loginProcess, UserCatalogo userCatalogo) {
		Map<String, Map<String, List<String>>> userPermissions = new HashMap<String, Map<String, List<String>>>();
		
		if (loginProcess == null)
			return userPermissions;

		AuthorizationManager authManager = loginProcess.getAuthorizationManager();
		
		// SubSistemas
		Collection subSystems;
		try {
			subSystems = authManager.getManagementSubSystemList("Catalogo",null);
			Collection userDomainFamilies = authManager.getUserDomainFamilies("Catalogo");
			userCatalogo.setUserDomainFamilies(userDomainFamilies);
			
			if (!subSystems.isEmpty()) {

				Iterator subSysIt = subSystems.iterator();
				Map<String, List<String>> controlPoints = null;
				while (subSysIt.hasNext()) {
					ManagementSubSystem subSys = (ManagementSubSystem) subSysIt.next();

					// Pontos Intermedios (Control Points)
					Collection ctrlPoints = subSys.getControlPointsList();
					if (!ctrlPoints.isEmpty()) {
						controlPoints = new HashMap<String, List<String>>();
						Iterator cpIt = ctrlPoints.iterator();
						while (cpIt.hasNext()) {
							List<String> cpDomains = new ArrayList<String>();
							ControlPoint cp = (ControlPoint) cpIt.next();

							// Dominios geridos por Ponto Intermedio (Control
							// Point)
							Collection domains = cp.getControlPointDomainsList();
							if (!domains.isEmpty()) {
								Iterator dIt = domains.iterator();
								while (dIt.hasNext()) {
									Domain domain = (Domain) dIt.next();
									Iterator domIt = userDomainFamilies.iterator();
									while (domIt.hasNext()) {
										DomainFamily domainFamily = (DomainFamily)domIt.next();
										if (domainFamily.getId().equals(domain.getIdFamily())){
											cpDomains.add(domainFamily.getName() + "###" + domain.getSigla());
											break;
										}
									}
								}
							}
							controlPoints.put(cp.getName().toLowerCase(), cpDomains);
						}
					}
					userPermissions.put(subSys.getName(), controlPoints);

				}
			}
		} catch (SCAException e) {
			e.printStackTrace();
		}
		return userPermissions;
	}

	public static boolean hasPermissoes(UserCatalogo usuario, String acao) {
		Map<String, Map<String, List<String>>> permissoesUsuario = usuario.getPermissoes();
		String subSistemaAcao = PermissionsConfiguration.getSubSistemaAcao(acao);
		if (subSistemaAcao == null)
			return false;
		String pontoIntermedioAcao = PermissionsConfiguration.getPontoIntermedioAcao(acao);
		Map<String, List<String>> pontosIntermedios = permissoesUsuario.get(subSistemaAcao);
		if (pontosIntermedios == null)
			return false;

		if (pontoIntermedioAcao != null) {
			List<String> domains = pontosIntermedios.get(pontoIntermedioAcao.toLowerCase());

			if (domains == null)
				return false;
			
			if(domains != null){
				//domains.get(index)
			}
		}

		return true;
	}

	public static boolean hasPermissoesBySubSistema(UserCatalogo usuario, String acao) {
		String subSistemaAcao = PermissionsConfiguration.getSubSistemaAcao(acao);
		Map<String, Map<String, List<String>>> permissoesUsuario = usuario.getPermissoes();
		
		Map<String, List<String>> pontosIntermedios = permissoesUsuario.get(subSistemaAcao);
		if (pontosIntermedios == null) {
			return false;
		}
		
		return true;
	}
	
	public static Long checkExpiracaoSenha(UserCatalogo userCatalogo) {
		User user = userCatalogo.getUser();
		String dataValidadePassword = user.getDataValidadePassword();
		Integer diasPreAviso = user.getDiasPreAviso();
		if(dataValidadePassword!= null && dataValidadePassword.trim().length()>0){
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date dataExpiracao = null;
			try {
				dataExpiracao = simpleDateFormat.parse(dataValidadePassword);
			} catch (ParseException e) {
				return null;
			}
			Date dataAtual = new Date();
			long expiracao = dataExpiracao.getTime();
			long atual = dataAtual.getTime();
			long diference = expiracao - atual;
			long diferenceIndays = Math.round((diference /1000/60/60/24) + 0.5d);
			if(diferenceIndays < diasPreAviso)
				return diferenceIndays;
		}
		return null;
	}
	
	public static boolean hasExpired(UserCatalogo userCatalogo){
		SimpleDateFormat sdfHH = new SimpleDateFormat("hh");
		SimpleDateFormat sdfMM = new SimpleDateFormat("mm");
		Integer dataAtualHH = Integer.parseInt(sdfHH.format(new Date()));
		Integer dataUltimaAtividadeHH =  Integer.parseInt(sdfHH.format(userCatalogo.getUltimaAtividade()));
		Integer dataAtualMM = Integer.parseInt(sdfMM.format(new Date()));
		Integer dataUltimaAtividadeMM =  Integer.parseInt(sdfMM.format(userCatalogo.getUltimaAtividade()));
		if((((dataAtualHH - dataUltimaAtividadeHH)*60) + (dataAtualMM-dataUltimaAtividadeMM)) > userCatalogo.getUser().getMaxInactTime()){
			return true;
		}
		return false;
	}
	

	@SuppressWarnings("rawtypes")
	public static boolean checkHorario(UserCatalogo usuario, HttpSession session) {
		// Verifica se usuário está associado a Subsistema/Control Point Global e 
		// Recupera horario de inicio e fim configurados
		
		Map<String, Map<String, List<String>>> permissoesUsuario = usuario.getPermissoes();
		String subSistemaAcao = PermissionsConfiguration.getSubSistemaAcao("paginaInicial");
		if (subSistemaAcao == null) // usuario nao tem restricao de horario
			return true;
		String pontoIntermedioAcao = PermissionsConfiguration.getPontoIntermedioAcao("paginaInicial");
		Map<String, List<String>> pontosIntermedios = permissoesUsuario.get(subSistemaAcao);
		if (pontosIntermedios == null) // usuario nao tem restricao de horario
			return true;

		if (pontoIntermedioAcao != null) {
			List<String> domains = pontosIntermedios.get(pontoIntermedioAcao.toLowerCase());

			if (domains == null)
				return true; // usuario nao tem restricao de horario
			else{
				String horarioInicio = null;
				String horarioFim = null;
				List<String> siglaOrganizacaoList = new ArrayList<String>();
				List<CanalAtendimentoTO> listaCanal = new ArrayList<CanalAtendimentoTO>();
				
				Iterator dmIt = domains.iterator();
				while (dmIt.hasNext()) {
					String dominio = (String) dmIt.next();
					if (dominio.contains("###")){
						String[] dom = dominio.split("###");
						if (dom[0].equalsIgnoreCase(PermissionsConfiguration.getDominioGeridoAcaoNome("paginaInicial","horarioInicio"))){
							horarioInicio = dom[1];
						}
						else if (dom[0].equalsIgnoreCase(PermissionsConfiguration.getDominioGeridoAcaoNome("paginaInicial","horarioFim"))){
							horarioFim = dom[1];
						}
						else if(dom[0].equalsIgnoreCase(PermissionsConfiguration.getDominioGeridoAcaoNome("paginaInicial","organizacaoVendas"))) {
							String stringDom = dom[1].substring(0, dom[1].indexOf("@"));
							if(stringDom != null && !stringDom.equalsIgnoreCase("")) {
								siglaOrganizacaoList.add(stringDom);
							}
						}
						else if(dom[0].equalsIgnoreCase(PermissionsConfiguration.getDominioGeridoAcaoNome("paginaInicial","canalAtendimento"))) {
							String[] canal = dom[1].split("@");
							String idCanal = canal[0];
							String nmCanal = canal[1];
							
							CanalAtendimentoTO item = new CanalAtendimentoTO(Integer.parseInt(idCanal));
							if(nmCanal != null && !nmCanal.equalsIgnoreCase("")) {								
								item.setNmCanal(nmCanal);
							}
							listaCanal.add(item);
						}
					}
				}
				if (session != null) {
					session.setAttribute("LISTA_SIGLA_ORGANIZACAO_VENDAS", siglaOrganizacaoList);
					session.setAttribute("canalAtendimentoList", listaCanal);
				}
				
				if (horarioInicio != null && horarioFim != null){
					// Verifica se o horario atual esta dentro do intervalo
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
					try{
						Date horarioInicioConfig = simpleDateFormat.parse(horarioInicio);
						Date horarioFimConfig = simpleDateFormat.parse(horarioFim);
						Calendar calendarInicioConfig = Calendar.getInstance();
						calendarInicioConfig.setTime(horarioInicioConfig);
						Calendar calendarFimConfig = Calendar.getInstance();
						calendarFimConfig.setTime(horarioFimConfig);
						Calendar calendarAtual = Calendar.getInstance();
						calendarAtual.set(calendarInicioConfig.get(Calendar.YEAR),calendarInicioConfig.get(Calendar.MONTH),calendarInicioConfig.get(Calendar.DAY_OF_MONTH));
						
						if(calendarAtual.compareTo(calendarInicioConfig) >=0 && (calendarAtual.compareTo(calendarFimConfig) <=0)){
							return true;
						}
						else{
							return false;
						}
					} catch (ParseException e) {
						return false;
					}
				}
			}
	    }
		else{
			return true; // usuario nao tem restricao de horario
		}
		return true;
	}
	
	public static boolean checkHorario(UserCatalogo usuario) {
		return checkHorario(usuario, null);
  }
	
	public static void liberaSessaoSCA(DadosCookie dadosCookie){
		if(dadosCookie == null)
			return;
		try {
			DatabaseConnectionManager dbManager = new DatabaseConnectionManagerAdapter(getDataSource());
			AccessLoginWebDatabase.setDatabaseConnectionManager(dbManager);
			ClientSystem clientSystem = new ClientSystem();
			clientSystem.setSystemName(dadosCookie.getHostnameCliente());
			clientSystem.setIp(dadosCookie.getIpCliente());
			AccessLoginModule loginModule = AccessLoginWebDatabase.createAccessLoginWebDatabase(clientSystem);
			loginModule.login(dadosCookie.getIdSessaoCliente().replaceFirst("/1", ""));
		
			LoginProcess loginProcess = loginModule.getProcessLogin();
			if(loginProcess.isUserAuthenticated()){
				loginProcess.logout();
			}
			
		} catch (LoginException e) {
			e.printStackTrace();
		}
	}
}
