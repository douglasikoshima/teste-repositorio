package br.com.vivo.catalogoPRS.pageflows.tradutorErro.vo;

import java.io.Serializable;

/**
 * Usuario Logado
 */
public class LogedUser implements Serializable
{
	private static final long serialVersionUID = 1L;
    private String dsLogin;    
    private Integer idGrupo;
    private String requestIp;

    public LogedUser()
    {
    }
        
    /**
     * @return Returns the dsLogin.
     */
    public String getDsLogin()
    {
        return dsLogin;
    }
    /**
     * @param dsLogin The dsLogin to set.
     */
    public void setDsLogin(String dsLogin)
    {
        this.dsLogin = dsLogin;
    }
    /**
     * @return Returns the idGrupo.
     */
    public Integer getIdGrupo()
    {
        return idGrupo;
    }
    /**
     * @param idGrupo The irPedfil to set.
     */
    public void setIdGrupo(Integer idGrupo)
    {
        this.idGrupo = idGrupo;
    }

    /**
     * @return Returns the requestIp.
     */
    public String getRequestIp()
    {
        return requestIp;
    }
    /**
     * @param requestIp The requestIp to set.
     */
    public void setRequestIp(String requestIp)
    {
        this.requestIp = requestIp;
    }
}
