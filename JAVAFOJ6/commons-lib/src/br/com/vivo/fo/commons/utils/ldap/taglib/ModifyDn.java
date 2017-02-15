package br.com.vivo.fo.commons.utils.ldap.taglib;

import javax.naming.NamingException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;
import br.com.vivo.fo.commons.utils.ldap.beans.LdapModifyDnBean;

// Referenced classes of package br.com.vivo.fo.commons.utils.ldap.taglib:
//            Connect, LdapTaglibException

public class ModifyDn extends TagSupport
{

	private static final long serialVersionUID = -6527016279855086647L;
	private String olddn;
    private String newdn;

    public ModifyDn()
    {
        olddn = null;
        newdn = null;
    }

    public int doStartTag()
        throws JspException
    {
        Connect connect = (Connect)getParent();
        if(connect == null)
            throw new JspTagException("<modifydn> tag should be child node of <connect> tag.");
        LdapModifyDnBean ldapmodifydnbean = new LdapModifyDnBean();
        ldapmodifydnbean.setOldDn(olddn);
        ldapmodifydnbean.setNewDn(newdn);
        ldapmodifydnbean.setContext(connect.getContext());
        try
        {
            ldapmodifydnbean.modifyDN();
        }
        catch(NamingException namingexception)
        {
            throw new LdapTaglibException(namingexception);
        }
        return 6;
    }

    public void release()
    {
        olddn = null;
        newdn = null;
    }

    public String getOlddn()
    {
        return olddn;
    }

    public void setOlddn(String s)
    {
        olddn = s;
    }

    public String getNewdn()
    {
        return newdn;
    }

    public void setNewdn(String s)
    {
        newdn = s;
    }
}
