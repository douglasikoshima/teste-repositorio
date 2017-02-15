package br.com.vivo.fo.commons.utils.ldap.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

// Referenced classes of package br.com.vivo.fo.commons.utils.ldap.taglib:
//            Query

public class GetDN extends TagSupport
{

	private static final long serialVersionUID = 8650416051911329308L;

	public GetDN()
    {
    }

    public int doStartTag()
        throws JspException
    {
        Query query = (Query)getParent();
        if(query == null)
            throw new JspTagException("<GetDN> tag should be child node of <Query> tag.");
        JspWriter jspwriter = pageContext.getOut();
        try
        {
            String s = query.getDN();
            jspwriter.print(s);
        }
        catch(IOException ioexception)
        {
            throw new JspTagException("IO Error");
        }
        return 1;
    }
}
