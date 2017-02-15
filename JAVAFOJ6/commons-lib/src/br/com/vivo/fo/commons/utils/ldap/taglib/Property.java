package br.com.vivo.fo.commons.utils.ldap.taglib;

import java.util.Hashtable;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class Property extends TagSupport
{

	private static final long serialVersionUID = 4799911921079643701L;
	private String name;
    private String value;
    private static Hashtable preDefinedNames;

    public Property()
    {
        name = null;
        value = null;
    }

    public int doStartTag()
        throws JspException
    {
        if(preDefinedNames.containsKey(name))
            pageContext.setAttribute((String)preDefinedNames.get(name), value);
        else
            pageContext.setAttribute(name, value);
        return 6;
    }

    public void release()
    {
        name = null;
        value = null;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String s)
    {
        name = s;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String s)
    {
        value = s;
    }

    static 
    {
        preDefinedNames = new Hashtable();
        preDefinedNames.put("factory", "java.naming.factory.initial");
        preDefinedNames.put("url", "java.naming.provider.url");
        preDefinedNames.put("dn", "java.naming.security.principal");
        preDefinedNames.put("password", "java.naming.security.credentials");
        preDefinedNames.put("ssl", "java.naming.security.protocol");
        preDefinedNames.put("referral", "java.naming.referral");
        preDefinedNames.put("authtype", "java.naming.security.authentication");
    }
}
