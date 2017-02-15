package br.com.vivo.fo.commons.utils.ldap.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

// Referenced classes of package br.com.vivo.fo.commons.utils.ldap.taglib:
//            Modify

public class ModifyAttribute extends TagSupport
{

	private static final long serialVersionUID = -6576111519321364769L;
//	private static final String DEFAULT_DELIMITER = ", ";
//    private static final String DEFAULT_MIMETYPE = "text/plain";
//    private static final String DEFAULT_MIMETEXT = "text";
//    private static final int DEFAULT_INDEX = -1;
//    private static final String DEFAULT_VALUE = "";
    private String name;
    private String value;
    private String modType;
    private String mimeType;

    public ModifyAttribute()
    {
        name = null;
        value = null;
        modType = null;
        mimeType = "text/plain";
    }

    public int doStartTag()
        throws JspException
    {
        Modify modify = (Modify)getParent();
        if(modify == null)
            throw new JspTagException("<modify attribute> tag should be child node of <modify> tag.");
        if(modType.equalsIgnoreCase("add"))
            modify.setAttribute(name, value, 1);
        if(modType.equalsIgnoreCase("replace"))
            modify.setAttribute(name, value, 2);
        if(modType.equalsIgnoreCase("remove"))
            modify.setAttribute(name, value, 3);
        return 1;
    }

    public void release()
    {
        name = null;
        value = null;
        modType = null;
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

    public String getType()
    {
        return modType;
    }

    public void setType(String s)
    {
        modType = s;
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
