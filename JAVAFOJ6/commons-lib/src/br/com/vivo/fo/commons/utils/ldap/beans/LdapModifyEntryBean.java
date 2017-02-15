package br.com.vivo.fo.commons.utils.ldap.beans;

import java.util.Enumeration;
import java.util.Vector;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;

public class LdapModifyEntryBean
{

    private DirContext ctx;
    private ModificationItem item[];
    //private Attributes attrList;
    //private Hashtable attrTable;
    private Vector vector;
    private String DN;

    public LdapModifyEntryBean()
    {
        ctx = null;
        item = null;
        //attrList = new BasicAttributes(true);
        //attrTable = new Hashtable();
        vector = new Vector();
    }

    public void setContext(DirContext dircontext)
    {
        ctx = dircontext;
    }

    public void setDN(String s)
    {
        DN = s;
    }

    public void setModifyAttribute(String s, Object obj, int i)
    {
        if(i != 0)
        {
            BasicAttribute basicattribute = new BasicAttribute(s, obj);
            vector.add(new ModificationItem(i, basicattribute));
        }
        else
        {
            Enumeration enumeration = vector.elements();
            for(int j = 0; enumeration.hasMoreElements(); j++)
            {
                ModificationItem modificationitem = (ModificationItem)enumeration.nextElement();
                Attribute attribute = modificationitem.getAttribute();
                if(!attribute.getID().equals(s));
                attribute.add(obj);
            }

        }
    }

    public void modifyEntry()
        throws NamingException
    {
        Enumeration enumeration = vector.elements();
        item = new ModificationItem[vector.size()];
        for(int i = 0; enumeration.hasMoreElements(); i++)
            item[i] = (ModificationItem)enumeration.nextElement();

        ctx.modifyAttributes(DN, item);
    }
}
