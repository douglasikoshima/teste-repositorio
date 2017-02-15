package br.com.vivo.fo.commons.utils.ldap.taglib;

import javax.naming.NamingException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;
import br.com.vivo.fo.commons.utils.ldap.beans.LdapAuthenticateBean;

// Referenced classes of package br.com.vivo.fo.commons.utils.ldap.taglib:
//            Connect, LdapTaglibException

public class Authenticate extends TagSupport
{

	private static final long serialVersionUID = -5023155893029045247L;
	private String dn;
    private String password;
    private String authType;

    public Authenticate()
    {
        dn = null;
        password = null;
        authType = "simple";
    }

    public int doStartTag()
        throws JspException
    {
        Connect connect = (Connect)getParent();
        if(connect == null)
            throw new JspTagException("<authenticate> tag should be child node of <connect> tag.");
        LdapAuthenticateBean ldapauthenticatebean = new LdapAuthenticateBean();
        ldapauthenticatebean.setContext(connect.getContext());
        ldapauthenticatebean.setDN(dn);
        ldapauthenticatebean.setPassword(password);
        ldapauthenticatebean.setAuthType(authType);
        try
        {
            ldapauthenticatebean.authenticate();
        }
        catch(NamingException namingexception)
        {
            throw new LdapTaglibException(namingexception);
        }
        return 6;
    }

    public void release()
    {
        dn = null;
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

    public String getAuthType()
    {
        return authType;
    }

    public void setAuthType(String s)
    {
        authType = s;
    }
}
