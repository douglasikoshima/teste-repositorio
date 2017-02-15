<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%> 

<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<bean:define id="Form"                    name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="campanhasForm" type="VOLTAV.campanhas.CampanhasController.CampanhasForm"/>
<bean:define id="listaCampanhas"          name="Form" property="campanhaVO.campanhaVOArray" />

<script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/frameweb.js" ></script>

<div id="divSearchResults">
    <vivo:tbTable selectable="true" layoutWidth="733" layoutHeight="210" tableWidth="733" styleId="tbCampanha" sortable="true">
        <vivo:tbHeader>
            <vivo:tbHeaderColumn align="left" width="70%" type="string">Campanha</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="left" width="20%" type="string">Validade</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="left" width="5%" type="string">&nbsp;</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="left" width="5%" type="string">&nbsp;</vivo:tbHeaderColumn>
        </vivo:tbHeader>
        <vivo:tbRows>
            <logic:iterate name="listaCampanhas" id="CampanhaVO" indexId="ctr">
                <vivo:tbRow key="campanha" idClass="classListaCampanhas">
                    <vivo:tbRowColumn>
                        <span title="<bean:write name="CampanhaVO" property="dsCampanha"/>">
                            <bean:write name="CampanhaVO" property="nmCampanha"/>
                        </span>
                    </vivo:tbRowColumn>
                    <vivo:tbRowColumn>
                        <bean:write name="CampanhaVO" property="dtValidadeDe"/> a <bean:write name="CampanhaVO" property="dtValidadeAte"/>
                    </vivo:tbRowColumn>
                    <vivo:tbRowColumn>
                        <img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" style="cursor:pointer;" onmouseup="excluirCampanha(<bean:write  name="CampanhaVO" property="idCampanha" format="#" />,<%=ctr%>)" />
                    </vivo:tbRowColumn>
                    <vivo:tbRowColumn>
                        <img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" style="cursor:pointer;" onmouseup="alterarCampanha(<bean:write name="CampanhaVO" property="idCampanha" format="#" />)" />
                    </vivo:tbRowColumn>
                </vivo:tbRow>
            </logic:iterate>
        </vivo:tbRows>
    </vivo:tbTable>
</div>

<script type="text/javascript" language="javascript">
    if( window.top.frameApp.dvAnimarAguarde != null ) window.top.frameApp.stopAnimation();
    parent.document.getElementById('divSearchResults').innerHTML = document.getElementById('divSearchResults').innerHTML;
</script>