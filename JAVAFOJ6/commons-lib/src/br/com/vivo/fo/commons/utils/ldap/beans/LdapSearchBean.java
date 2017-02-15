package br.com.vivo.fo.commons.utils.ldap.beans;

import java.util.StringTokenizer;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

public class LdapSearchBean
{

    private SearchControls ctls;
    private NamingEnumeration answer;
    private Attributes attributes;
    private SearchResult sr;
    private String DN;
    private String filter;
    private String searchBase;
    private String scope;

    public LdapSearchBean()
    {
        ctls = new SearchControls();
        answer = null;
        attributes = null;
        sr = null;
        DN = null;
        filter = null;
        searchBase = null;
        scope = null;
    }

    public void search(DirContext dircontext)
        throws NamingException
    {
        if(scope == null || scope.equalsIgnoreCase("sub"))
            ctls.setSearchScope(2);
        else
        if(scope.equalsIgnoreCase("one"))
            ctls.setSearchScope(1);
        else
        if(scope.equalsIgnoreCase("base"))
            ctls.setSearchScope(0);
        answer = dircontext.search(searchBase, filter, ctls);
    }

    public boolean next()
        throws NamingException
    {
        if(answer.hasMoreElements())
        {
            sr = (SearchResult)answer.next();
            DN = sr.getName();
            attributes = sr.getAttributes();
            return true;
        }
        else
        {
            return false;
        }
    }

    public void setReturningAttributes(String s)
    {
        StringTokenizer stringtokenizer = new StringTokenizer(s, ",");
        String as[] = new String[stringtokenizer.countTokens()];
        for(int i = 0; stringtokenizer.hasMoreTokens(); i++)
            as[i] = stringtokenizer.nextToken().trim();

        ctls.setReturningAttributes(as);
    }

    public Attribute getAttribute(String s)
        throws NamingException
    {
        return attributes.get(s);
    }

    public String getStringAttribute(String s)
        throws NamingException
    {
        Attribute attribute = attributes.get(s);
        return attribute == null ? "" : (String)attribute.get();
    }

    public String getStringAttribute(String s, String s1)
        throws NamingException
    {
        Attribute attribute = attributes.get(s);
        return attribute == null ? "" : (String)attribute.get((new Integer(s1)).intValue());
    }

    public int getAttributeSize(String s)
        throws NamingException
    {
        Attribute attribute = attributes.get(s);
        return attribute == null ? 0 : attribute.size();
    }

    public String getFilter()
    {
        return filter;
    }

    public void setFilter(String s)
    {
        filter = s;
    }

    public String getSearchBase()
    {
        return searchBase;
    }

    public void setSearchBase(String s)
    {
        searchBase = s;
    }

    public String getScope()
    {
        return scope;
    }

    public void setScope(String s)
    {
        scope = s;
    }

    public void setLimit(String s)
    {
        ctls.setCountLimit((new Integer(s)).intValue());
    }

    public String getDN()
    {
        return DN;
    }
}
