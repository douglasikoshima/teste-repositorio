package br.com.vivo.fo.commons.utils.ldap.beans;

import javax.naming.NamingException;
import javax.naming.directory.DirContext;

public class LdapDeleteEntryBean
{

    private DirContext ctx;
    private String dn;

    public LdapDeleteEntryBean()
    {
        ctx = null;
    }

    public void setContext(DirContext dircontext)
    {
        ctx = dircontext;
    }

    public void setDn(String s)
    {
        dn = s;
    }

    public void deleteEntry()
        throws NamingException
    {
        ctx.destroySubcontext(dn);
    }
}
