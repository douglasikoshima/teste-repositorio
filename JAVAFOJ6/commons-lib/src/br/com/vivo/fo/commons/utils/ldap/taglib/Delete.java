package br.com.vivo.fo.commons.utils.ldap.taglib;

import javax.naming.NamingException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;
import br.com.vivo.fo.commons.utils.ldap.beans.LdapDeleteEntryBean;

// Referenced classes of package br.com.vivo.fo.commons.utils.ldap.taglib:
//            Connect, LdapTaglibException

public class Delete extends TagSupport
{

	private static final long serialVersionUID = 3958839479351829617L;
	private String dn;

    public Delete()
    {
        dn = null;
    }

    public int doStartTag()
        throws JspException
    {
        Connect connect = (Connect)getParent();
        if(connect == null)
            throw new JspTagException("<delete> tag should be child node of <connect> tag.");
        LdapDeleteEntryBean ldapdeleteentrybean = new LdapDeleteEntryBean();
        ldapdeleteentrybean.setDn(dn);
        ldapdeleteentrybean.setContext(connect.getContext());
        try
        {
            ldapdeleteentrybean.deleteEntry();
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
}
