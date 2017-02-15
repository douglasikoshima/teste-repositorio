package br.com.vivo.fo.commons.utils.ldap.beans;

import java.util.Hashtable;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

public class LdapConnectBean
{

    //private static final String DEFAULT_FACTORY = "com.sun.jndi.ldap.LdapCtxFactory";
    //private static final String DEFAULT_AUTHENTICATION = "simple";
    private DirContext ctx;
    private Hashtable env;

    public LdapConnectBean()
    {
        ctx = null;
        env = new Hashtable(11);
    }

    public void connect()
        throws NamingException
    {
        if(!env.containsKey("java.naming.factory.initial"))
            env.put("java.naming.factory.initial", "com.sun.jndi.ldap.LdapCtxFactory");
        if(env.containsKey("java.naming.security.principal") && !env.containsKey("java.naming.security.authentication"))
            env.put("java.naming.security.authentication", "simple");
        ctx = new InitialDirContext(env);
    }

    public void disconnect()
        throws NamingException
    {
        try
        {
            try
            {
                ctx.close();
            }
            catch(NamingException namingexception)
            {
                throw namingexception;
            }
        }
        finally
        {
            ctx = null;
        }
    }

    public boolean isConnected()
    {
        return ctx != null;
    }

    public DirContext getContext()
    {
        return ctx;
    }

    public Hashtable getEnv()
    {
        return env;
    }

    public void setProperty(String s, Object obj)
    {
        env.put(s, obj);
    }

    public Object getProperty(String s)
    {
        return env.get(s);
    }
}
