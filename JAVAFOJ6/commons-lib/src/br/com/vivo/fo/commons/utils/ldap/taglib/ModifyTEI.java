package br.com.vivo.fo.commons.utils.ldap.taglib;

import javax.servlet.jsp.tagext.TagData;
import javax.servlet.jsp.tagext.TagExtraInfo;
import javax.servlet.jsp.tagext.VariableInfo;

public class ModifyTEI extends TagExtraInfo
{

    public ModifyTEI()
    {
    }

    public VariableInfo[] getVariableInfo(TagData tagdata)
    {
        if(tagdata.getId() != null)
        {
            VariableInfo variableinfo = new VariableInfo(tagdata.getId(), "br.com.vivo.fo.commons.utils.ldap.beans.LdapModifyEntryBean", true, VariableInfo.NESTED);
            return (new VariableInfo[] {
                variableinfo
            });
        }
        else
        {
            return new VariableInfo[0];
        }
    }

    public boolean isValid(TagData tagdata)
    {
        return true;
    }
}
