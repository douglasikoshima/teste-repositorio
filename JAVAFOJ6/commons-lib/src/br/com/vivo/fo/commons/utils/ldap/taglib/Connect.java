package br.com.vivo.fo.commons.utils.ldap.taglib;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import br.com.vivo.fo.commons.utils.ldap.beans.LdapConnectBean;

// Referenced classes of package br.com.vivo.fo.commons.utils.ldap.taglib:
//            LdapTaglibException

public class Connect extends BodyTagSupport
{

	private static final long serialVersionUID = 7662992332005335520L;
	private LdapConnectBean connectBean;
    private String url;
    private String dn;
    private String password;
    private String ssl;
    private String referral;
    private String authtype;

    public Connect()
    {
        connectBean = null;
        url = null;
        dn = null;
        password = null;
        ssl = null;
        referral = null;
        authtype = null;
    }

    public int doStartTag()
        throws JspException
    {
        connectBean = new LdapConnectBean();
        //Connect _tmp = this;
        String s;
        for(Enumeration enumeration = pageContext.getAttributeNamesInScope(1); enumeration.hasMoreElements(); connectBean.setProperty(s, pageContext.getAttribute(s)))
            s = (String)enumeration.nextElement();

        if(url != null)
            connectBean.setProperty("java.naming.provider.url", url);
        if(dn != null)
            connectBean.setProperty("java.naming.security.principal", dn);
        if(password != null)
            connectBean.setProperty("java.naming.security.credentials", password);
        if(ssl != null && ssl.equalsIgnoreCase("on"))
            connectBean.setProperty("java.naming.security.protocol", "ssl");
        if(referral != null)
            connectBean.setProperty("java.naming.referral", referral);
        if(authtype != null)
            connectBean.setProperty("java.naming.referral", referral);
        try
        {
            connectBean.connect();
        }
        catch(NamingException namingexception)
        {
            throw new LdapTaglibException(namingexception);
        }
        return 2;
    }

    public int doAfterBody()
        throws JspException
    {
        try
        {
            connectBean.disconnect();
        }
        catch(NamingException namingexception)
        {
            throw new LdapTaglibException(namingexception);
        }
        BodyContent bodycontent = getBodyContent();
        try
        {
            bodycontent.writeOut(getPreviousOut());
        }
        catch(IOException ioexception)
        {
            throw new JspTagException("IO Error");
        }
        return 0;
    }

    public void release()
    {
        connectBean = null;
        url = null;
        password = null;
        dn = null;
        ssl = null;
        referral = null;
        authtype = null;
    }

    public DirContext getContext()
    {
        return connectBean.getContext();
    }

    public Hashtable getEnv()
    {
        return connectBean.getEnv();
    }

    public Object getProperty(String s)
    {
        return connectBean.getProperty(s);
    }

    public String getDn()
    {
        return dn;
    }

    public void setDn(String s)
    {
        dn = s;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String s)
    {
        password = s;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String s)
    {
        url = s;
    }

    public String getSsl()
    {
        return url;
    }

    public void setSsl(String s)
    {
        ssl = s;
    }

    public String getReferral()
    {
        return referral;
    }

    public void setReferral(String s)
    {
        referral = s;
    }

    public String getAuthtype()
    {
        return authtype;
    }

    public void setAuthtype(String s)
    {
        authtype = s;
    }
}
