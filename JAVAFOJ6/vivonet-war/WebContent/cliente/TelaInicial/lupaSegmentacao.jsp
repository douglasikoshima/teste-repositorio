<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>

<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<bean:define id="segmentacaoForm"
             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"
             property="segmentacaoForm" />

<html>
    <head>
        <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
        <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    </head>
    <body rightmargin="0" leftmargin="0" topmargin="0" onload="if( window.top.frameApp.dvAnimarAguarde != null ) window.top.frameApp.stopAnimation();">
        <acesso:controlHiddenItem nomeIdentificador="cli_lseg_verpagina">
            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
            <table width="750" class="tbl_bgBlue" border="0" cellspacing="1" cellpadding="1" bgcolor="#ffffff">
                <tr>
                    <td colspan="4"  class="tbl_titulo">Detalhe Segmentação</td>
                </tr>
                <tr><td height="5"></td></tr>
                <tr>
                    <td>
                        <table width="600" border="1" cellspacing="1" cellpadding="1" id="tblGrupos">
                            <tr>
                                <td>
                                    <vivo:tbTable selectable="true" onRowClick="" layoutWidth="748" layoutHeight="160" tableWidth="763" styleId="tableTitle" sortable="true">
                                        <vivo:tbHeader>
                                            <vivo:tbHeaderColumn align="center" width="20%" type="string">Mês</vivo:tbHeaderColumn>
                                            <vivo:tbHeaderColumn align="center" width="60%" type="string">Segmentação</vivo:tbHeaderColumn>
                                            <vivo:tbHeaderColumn align="center" width="20%" type="string">Rentabilidade</vivo:tbHeaderColumn>
                                        </vivo:tbHeader>
                                        <vivo:tbRows>
                                            <logic:iterate name="segmentacaoForm" property="listaSegmentacao" id="listaSegmentacao">
                                                <vivo:tbRow key="Linha">
                                                    <vivo:tbRowColumn><bean:write name="listaSegmentacao" property="dtSegmentacao"/></vivo:tbRowColumn>
                                                    <vivo:tbRowColumn><bean:write name="listaSegmentacao" property="dsSegmentacao"/></vivo:tbRowColumn>
                                                    <vivo:tbRowColumn><bean:write name="listaSegmentacao" property="vlRentabilidade"/></vivo:tbRowColumn>
                                                </vivo:tbRow>
                                            </logic:iterate>
                                        </vivo:tbRows>
                                    </vivo:tbTable>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr><td height="10"></td></tr>
                <tr class="corpo">
                    <td colspan="4"><img src="/FrontOfficeWeb/resources/images/pixel_azul.gif" height="1" width="100%" border="0"/></td>
                </tr>
                <tr><td height="10"></td></tr>
                <tr>
                    <td colspan="4" align="right"></td>
                </tr>
            </table>
    </acesso:controlHiddenItem>
    </body>
</html>