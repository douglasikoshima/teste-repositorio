package br.com.vivo.fo.commons.utils.ldap.beans;

import javax.naming.NamingException;
import javax.naming.directory.DirContext;

public class LdapAuthenticateBean
{

    private DirContext ctx;
    private String authType;
    private String DN;
    private String password;

    public LdapAuthenticateBean()
    {
        ctx = null;
        authType = "simple";
    }

    public void setContext(DirContext dircontext)
    {
        ctx = dircontext;
    }

    public void setAuthType(String s)
    {
        authType = s;
    }

    public void setDN(String s)
    {
        DN = s;
    }

    public void setPassword(String s)
    {
        password = s;
    }

    public void authenticate()
        throws NamingException
    {
        ctx.addToEnvironment("java.naming.security.authentication", authType);
        ctx.addToEnvironment("java.naming.security.principal", DN);
        ctx.addToEnvironment("java.naming.security.credentials", password);
        //LdapAuthenticateBean _tmp = this;
        String s = (String)ctx.getEnvironment().get("java.naming.provider.url");
        ctx.lookup(s);
    }
}
