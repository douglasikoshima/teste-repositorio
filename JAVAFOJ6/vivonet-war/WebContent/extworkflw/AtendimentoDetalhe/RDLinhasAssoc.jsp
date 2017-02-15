<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.workflow.vo.NivelVODocument.NivelVO"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>

<bean:define id="nproc" scope="request" name="rWFLinhasAssocForm"/>
<script type="text/javascript" src="../../resources/scripts/frameweb.js"></script>
<table width="750" cellpadding="0" cellspacing="0" border="0" align="center">
    <tr><td height="5"></td></tr>
    <tr>
        <td valign="top">
            <vivo:tbTable selectable="false" resize ="false" layoutWidth="300" layoutHeight="65" tableWidth="300" styleId="tableTitleRDLinAss" sortable="true">
                <vivo:tbHeader>
                    <vivo:tbHeaderColumn align="center" width="50%" type="string">Conta</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="center" width="50%" type="string">Linha</vivo:tbHeaderColumn>
                </vivo:tbHeader>
                <vivo:tbRows>
                    <logic:present name="nproc" property="linhasAssoc">
                    <bean:define id="array" name="nproc" property="linhasAssoc"/>
                    <logic:iterate id="linhas" name="array" property="linhasAssociadasVOArray">
                        <vivo:tbRow key="linha1">
                            <vivo:tbRowColumn><bean:write name="linhas" property="cdConta"/></vivo:tbRowColumn>
                            <vivo:tbRowColumn><bean:write name="linhas" property="nrTelefone"/></vivo:tbRowColumn>
                        </vivo:tbRow>
                    </logic:iterate>
                    </logic:present>
                </vivo:tbRows>
            </vivo:tbTable>
        </td>
    </tr>
</table>
<script>
    parent.showIfr("LAssoc");
    //abaArvoreAtendimentoVoltar.click();
</script>
