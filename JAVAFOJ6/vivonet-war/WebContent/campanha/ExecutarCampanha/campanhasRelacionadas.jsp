<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<bean:define id="pageFlow" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" />
<bean:define id="viewScriptActionForm" name="pageFlow" property="viewScriptActionForm" />

<acesso:controlInitEnv/>
<html>
<acesso:controlHiddenItem nomeIdentificador="cam_camprel_verpagina">
<link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">
<script language="javascript" src="/FrontOfficeWeb/resources/scripts/frameweb.js"></script>
<script language="javascript" src="/FrontOfficeWeb/resources/scripts/executarCampanha.js"></script>

<SCRIPT FOR=window EVENT=onload>
    campanhasRelacionadas_manterChecado("<bean:write name="pageFlow" property="idCampanha" />");
    top.frameApp.oculta_div();
</script>
<form name="viewScriptActionForm" action="ViewScriptAction.do" method="post">
    <vivo:quadro id="qdPerg" width="770" height="350" label="Campanhas Relacionadas" scroll="true">

        <html:hidden name="viewScriptActionForm" property="idSubCampanhaHistoricoSel" />

        <vivo:tbTable selectable="false" onRowClick="" layoutWidth="753" layoutHeight="330" tableWidth="753" styleId="table" sortable="true" >
            <vivo:tbHeader>
                <vivo:tbHeaderColumn align="center" width="10%" type="string"></vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="30%" type="string">Campanha</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="30%" type="string">Subcampanha</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="30%" type="string">Canal Atendimento</vivo:tbHeaderColumn>
            </vivo:tbHeader>
            <vivo:tbRows>
                <logic:iterate id="item" name="pageFlow" property="campanhasRelacionadas">
                    <vivo:tbRow key="linha1">
                        <vivo:tbRowColumn>
                            <input type="radio"
                                   name="campanha"
                                   value="<bean:write name="item" property="idCanalCampanha" />"
                                   class="radio"
                                   onClick="campanhasRelacionadas_selecionar();" />
                        </vivo:tbRowColumn>
                        <vivo:tbRowColumn>
                            <bean:write name="item" property="sgCampanha" />
                        </vivo:tbRowColumn>
                        <vivo:tbRowColumn>
                            <bean:write name="item" property="nmSubCampanha" />
                            <html:hidden name="item" property="idSubCampanhaHistorico" />
                        </vivo:tbRowColumn>
                        <vivo:tbRowColumn>
                            <bean:write name="item" property="nmCanal" />
                        </vivo:tbRowColumn>
                    </vivo:tbRow>
                </logic:iterate>
            </vivo:tbRows>
        </vivo:tbTable>
    </vivo:quadro>
</form>
<logic:notEmpty name="pageFlow" property="idCampanha">
</logic:notEmpty>
</acesso:controlHiddenItem>

</html>