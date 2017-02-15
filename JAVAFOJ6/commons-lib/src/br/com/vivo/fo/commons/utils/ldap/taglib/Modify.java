package br.com.vivo.fo.commons.utils.ldap.taglib;

import javax.naming.NamingException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import br.com.vivo.fo.commons.utils.ldap.beans.LdapModifyEntryBean;

// Referenced classes of package br.com.vivo.fo.commons.utils.ldap.taglib:
//            Connect, LdapTaglibException

public class Modify extends BodyTagSupport
{

	private static final long serialVersionUID = 6515104550221849296L;
	private String dn;
    private LdapModifyEntryBean modifyBean;

    public Modify()
    {
        dn = null;
        modifyBean = new LdapModifyEntryBean();
    }

    public int doStartTag()
        throws JspException
    {
        Connect connect = (Connect)getParent();
        if(connect == null)
            throw new JspTagException("<modify> tag should be child node of <connect> tag.");
        modifyBean.setDN(dn);
        modifyBean.setContext(connect.getContext());
        if(id != null)
            pageContext.setAttribute(id, modifyBean);
        return 2;
    }

    public int doAfterBody()
        throws JspException
    {
        try
        {
            modifyBean.modifyEntry();
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
    }

    public String getDn()
    {
        return dn;
    }

    public void setDn(String s)
    {
        dn = s;
    }

    public void setAttribute(String s, String s1, int i)
    {
        modifyBean.setModifyAttribute(s, s1, i);
    }
}
