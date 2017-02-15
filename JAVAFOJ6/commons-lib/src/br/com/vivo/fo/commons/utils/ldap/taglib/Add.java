package br.com.vivo.fo.commons.utils.ldap.taglib;

import javax.naming.NamingException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import br.com.vivo.fo.commons.utils.ldap.beans.LdapAddEntryBean;

// Referenced classes of package br.com.vivo.fo.commons.utils.ldap.taglib:
//            Connect, LdapTaglibException

public class Add extends BodyTagSupport
{


	private static final long serialVersionUID = -3052099818932920007L;
	private String dn;
//    private String name;
//    private String value;
    private LdapAddEntryBean addBean;

    public Add()
    {
        dn = null;
//        name = null;
//        value = null;
        addBean = new LdapAddEntryBean();
    }

    public int doStartTag()
        throws JspException
    {
        Connect connect = (Connect)getParent();
        if(connect == null)
            throw new JspTagException("<add> tag should be child node of <connect> tag.");
        addBean.setDN(dn);
        addBean.setContext(connect.getContext());
        if(id != null)
            pageContext.setAttribute(id, addBean);
        return 2;
    }

    public int doAfterBody()
        throws JspException
    {
        try
        {
            addBean.addEntry();
        }
        catch(NamingException namingexception)
        {
            throw new LdapTaglibException(namingexception);
        }
        return 0;
    }

    public void release()
    {
        dn = null;
//        name = null;
//        value = null;
    }

    public String getDn()
    {
        return dn;
    }

    public void setDn(String s)
    {
        dn = s;
    }

    public void setAttribute(String s, String s1)
    {
        addBean.setAddAttribute(s, s1);
    }
}
