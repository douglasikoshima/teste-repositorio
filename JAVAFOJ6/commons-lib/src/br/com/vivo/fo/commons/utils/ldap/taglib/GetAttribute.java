package br.com.vivo.fo.commons.utils.ldap.taglib;

import java.io.IOException;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import br.com.vivo.fo.commons.utils.ldap.servlet.LdapGlobalCache;

// Referenced classes of package br.com.vivo.fo.commons.utils.ldap.taglib:
//            Query, LdapTaglibException

public class GetAttribute extends TagSupport
{

	private static final long serialVersionUID = -6433866200250407133L;
//	private static final String DEFAULT_DELIMITER = ", ";
//    private static final String DEFAULT_MIMETYPE = "text/plain";
//    private static final String DEFAULT_MIMETEXT = "text";
//    private static final int DEFAULT_INDEX = -1;
//    private static final String DEFAULT_VALUE = "";
    private String name;
    private String delimiter;
    private String mimeType;
    private int index;
    private String def;

    public GetAttribute()
    {
        name = null;
        delimiter = ", ";
        mimeType = "text/plain";
        index = -1;
        def = "";
    }

    public int doStartTag()
        throws JspException
    {
        Query query = null;
        try
        {
            query = (Query)getParent();
        }
        catch(Exception exception)
        {
            throw new JspTagException("<getAttribute> tag should be child node of <query> tag.");
        }
        JspWriter jspwriter = pageContext.getOut();
        try
        {
            Attribute attribute = query.getAttribute(name);
            if(attribute == null)
            {
                jspwriter.print(def);
                return 1;
            }
            if(index != -1)
            {
                output(jspwriter, attribute.get(index));
                return 1;
            }
            output(jspwriter, attribute.get());
            for(int i = 1; i < attribute.size(); i++)
            {
                jspwriter.print(delimiter);
                output(jspwriter, attribute.get(i));
            }

        }
        catch(IOException ioexception)
        {
            throw new JspTagException("IO Error");
        }
        catch(NamingException namingexception)
        {
            throw new LdapTaglibException(namingexception);
        }
        return 1;
    }

    public void release()
    {
        name = null;
        delimiter = ", ";
        index = -1;
        mimeType = "text/plain";
    }

    private void output(JspWriter jspwriter, Object obj)
        throws IOException
    {
        if(mimeType.startsWith("text"))
        {
            jspwriter.print(obj);
        }
        else
        {
            String s = LdapGlobalCache.addCache(obj, mimeType);
            jspwriter.print(((HttpServletRequest)pageContext.getRequest()).getContextPath());
            jspwriter.print('/');
            jspwriter.print("LdapGlobalCache?id=");
            jspwriter.print(s);
        }
    }

    public String getname()
    {
        return name;
    }

    public void setName(String s)
    {
        name = s;
    }

    public String getDelimiter()
    {
        return delimiter;
    }

    public void setDelimiter(String s)
    {
        delimiter = s;
    }

    public String getIndex()
    {
        return (new Integer(index)).toString();
    }

    public void setIndex(String s)
    {
        index = (new Integer(s)).intValue();
    }

    public String getMimeType()
    {
        return mimeType;
    }

    public void setMimeType(String s)
    {
        mimeType = s;
    }

    public String getDefault()
    {
        return def;
    }

    public void setDefault(String s)
    {
        def = s;
    }
}
