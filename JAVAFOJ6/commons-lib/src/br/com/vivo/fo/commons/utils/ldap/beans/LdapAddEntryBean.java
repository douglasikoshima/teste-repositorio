package br.com.vivo.fo.commons.utils.ldap.beans;

import java.util.Enumeration;
import java.util.Hashtable;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;

@SuppressWarnings({"rawtypes","unchecked"})
public class LdapAddEntryBean {

	private DirContext ctx;
	private Attributes attrList;
	private Hashtable attrTable;
	private String DN;

	public LdapAddEntryBean() {
		ctx = null;
		attrList = new BasicAttributes(true);
		attrTable = new Hashtable();
	}

	public void setContext(DirContext dircontext) {
		ctx = dircontext;
	}

	public void setDN(String s) {
		DN = s;
	}

	public void setAddAttribute(String s, Object obj) {
		Attribute attribute = (Attribute) attrTable.get(s);
		if (attribute == null) {
			attrTable.put(s, new BasicAttribute(s));
			Attribute attribute1 = (Attribute) attrTable.get(s);
			attribute1.add(obj);
			attrTable.put(s, attribute1);
		} else {
			Attribute attribute2 = (Attribute) attrTable.get(s);
			attribute2.add(obj);
		}
	}

	public void addEntry() throws NamingException {
		String s;
		for (Enumeration enumeration = attrTable.keys(); enumeration.hasMoreElements(); attrList.put((Attribute) attrTable.get(s))) {
			s = (String) enumeration.nextElement();
		}

		ctx.createSubcontext(DN, attrList);
	}
}
