package br.com.vivo.fo.commons.utils.ldap.taglib;

import javax.naming.NamingException;
import javax.servlet.jsp.JspTagException;

public class LdapTaglibException extends JspTagException
{

	private static final long serialVersionUID = 731757759502953549L;
	NamingException namingException;

    LdapTaglibException(NamingException namingexception)
    {
        super(namingexception.getMessage());
        namingException = null;
        namingException = namingexception;
    }

    public NamingException getNamingException()
    {
        return namingException;
    }
}
