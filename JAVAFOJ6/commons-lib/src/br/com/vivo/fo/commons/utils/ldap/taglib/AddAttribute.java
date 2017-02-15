package br.com.vivo.fo.commons.utils.ldap.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

// Referenced classes of package br.com.vivo.fo.commons.utils.ldap.taglib:
//            Add

public class AddAttribute extends TagSupport
{

	private static final long serialVersionUID = 2970176661504066737L;
//    private static final String DEFAULT_DELIMITER = ", ";
//    private static final String DEFAULT_MIMETYPE = "text/plain";
//    private static final String DEFAULT_MIMETEXT = "text";
//    private static final int DEFAULT_INDEX = -1;
//    private static final String DEFAULT_VALUE = "";
    private String name;
    private String value;
    private String mimeType;

    public AddAttribute()
    {
        name = null;
        value = null;
        mimeType = "text/plain";
    }

    public int doStartTag()
        throws JspException
    {
        Add add = (Add)getParent();
        if(add == null)
        {
            throw new JspTagException("<add attribute> tag should be child node of <add> tag.");
        }
        else
        {
            add.setAttribute(name, value);
            return 1;
        }
    }

    public void release()
    {
        name = null;
        value = null;
        mimeType = "text/plain";
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

    public String getMimeType()
    {
        return mimeType;
    }

    public void setMimeType(String s)
    {
        mimeType = s;
    }
}
