package br.com.vivo.catalogoPRS.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import pt.ptinovacao.sca.Domain;
import pt.ptinovacao.sca.User;
import pt.ptinovacao.sca.UserSession;

public class UserCatalogo implements Serializable {
	private static final long serialVersionUID = 8826347882885834379L;

	private User user;

	private UserSession userSession;

	private Map<String, Map<String, List<String>>> permissoes;
	
	private List userDomainFamilies;
	
	private Date ultimaAtividade;
	
	private String clientIP;
	
	private String clientHostname;
	
	private Integer maxTentativasLogin;
	
	private Integer numTentativasLogin;

	public Integer getMaxTentativasLogin() {
		return maxTentativasLogin;
	}

	public void setMaxTentativasLogin(Integer maxTentativasLogin) {
		this.maxTentativasLogin = maxTentativasLogin;
	}

	public Integer getNumTentativasLogin() {
		return numTentativasLogin;
	}

	public void setNumTentativasLogin(Integer numTentativasLogin) {
		this.numTentativasLogin = numTentativasLogin;
	}

	public String getClientHostname() {
		return clientHostname;
	}

	public void setClientHostname(String clientHostname) {
		this.clientHostname = clientHostname;
	}

	public String getClientIP() {
		return clientIP;
	}

	public void setClientIP(String clientIP) {
		this.clientIP = clientIP;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserSession getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}

	public Map<String, Map<String, List<String>>> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(Map<String, Map<String, List<String>>> permissoes) {
		this.permissoes = permissoes;
	}

	public List getUserDomainFamilies() {
		return userDomainFamilies;
	}

	public void setUserDomainFamilies(Collection<Domain> userDomainFamilies) {
		this.userDomainFamilies = new ArrayList<Domain>(userDomainFamilies);
	}

	public Date getUltimaAtividade() {
		return ultimaAtividade;
	}

	public void setUltimaAtividade(Date ultimaAtividade) {
		this.ultimaAtividade = ultimaAtividade;
	}
}
