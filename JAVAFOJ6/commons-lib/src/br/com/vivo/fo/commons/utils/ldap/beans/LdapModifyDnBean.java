package br.com.vivo.fo.commons.utils.ldap.beans;

import javax.naming.NamingException;
import javax.naming.directory.DirContext;

public class LdapModifyDnBean
{

    private DirContext ctx;
    private String olddn;
    private String newdn;

    public LdapModifyDnBean()
    {
        ctx = null;
    }

    public void setContext(DirContext ctx)
    {
        this.ctx = ctx;
    }

    public void setOldDn(String olddn)
    {
        this.olddn = olddn;
    }

    public void setNewDn(String newdn)
    {
        this.newdn = newdn;
    }

    public void modifyDN()
        throws NamingException
    {
        ctx.rename(olddn, newdn);
    }
}
