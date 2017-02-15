package br.com.vivo.fo.commons.utils.ldap.taglib;

import java.io.IOException;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import br.com.vivo.fo.commons.utils.ldap.beans.LdapSearchBean;

// Referenced classes of package br.com.vivo.fo.commons.utils.ldap.taglib:
//            Connect, LdapTaglibException

public class Query extends BodyTagSupport
{

	private static final long serialVersionUID = -1191230022492413283L;
	private LdapSearchBean searchBean;

    public Query()
    {
        searchBean = new LdapSearchBean();
    }

    public int doStartTag()
        throws JspException
    {
        Connect connect = (Connect)getParent();
        if(connect == null)
            throw new JspTagException("<query> tag should be child node of <connect> tag.");
        try
        {
            searchBean.search(connect.getContext());
            if(!searchBean.next())
                return 0;
        }
        catch(NamingException namingexception)
        {
            throw new LdapTaglibException(namingexception);
        }
        if(id != null)
            pageContext.setAttribute(id, searchBean);
        return 2;
    }

    public int doAfterBody()
        throws JspException
    {
        BodyContent bodycontent = getBodyContent();
        try
        {
            bodycontent.writeOut(getPreviousOut());
        }
        catch(IOException ioexception)
        {
            throw new JspTagException("IO Error");
        }
        bodycontent.clearBody();
        try
        {
            if(searchBean.next())
                return 2;
        }
        catch(NamingException namingexception)
        {
            throw new LdapTaglibException(namingexception);
        }
        return 0;
    }

    public Attribute getAttribute(String s)
        throws NamingException
    {
        return searchBean.getAttribute(s);
    }

    public void release()
    {
        searchBean = new LdapSearchBean();
        id = null;
    }

    public void setFilter(String s)
    {
        searchBean.setFilter(s);
    }

    public void setAttributes(String s)
    {
        searchBean.setReturningAttributes(s);
    }

    public void setLimit(String s)
    {
        searchBean.setLimit(s);
    }

    public void setBasedn(String s)
    {
        searchBean.setSearchBase(s);
    }

    public String getDN()
    {
        return searchBean.getDN();
    }

    public void setScope(String s)
    {
        searchBean.setScope(s);
    }

    public String getScope()
    {
        return searchBean.getScope();
    }
}
