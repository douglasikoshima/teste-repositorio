<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="lupaLinhaAbasGSMForm" type="cliente.TelaInicial.TelaInicialController.LupaLinhaAbasGSMForm"/>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
<html>
    <head>
        <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        <script language="javascript">
            <!--
            -->
        </script>
        <script FOR=window event=onload>
            <!--
                //Fim animação
                if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
            -->
        </script>
    </head>
    <body>
        <form action="lupaLinhaAbaHistAparelho.do" name="lupaLinhaAbasGSMForm" method="post" style="margin:0;" onSubmit="return false;">
            <vivo:tbTable selectable="true" layoutWidth="737" layoutHeight="137" tableWidth="737" styleId="tableTracking" sortable="true">
                <vivo:tbHeader>
                    <vivo:tbHeaderColumn align="center" width="32%" type="string">Data de associação</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="center" width="34%" type="string">Aparelho</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="center" width="34%" type="string">Fabricante</vivo:tbHeaderColumn>
                </vivo:tbHeader>
                <vivo:tbRows scroll="true">
                    <logic:present name="Form" property="lupaLinhaAbasGSMVO.abaHistorico">
                    <logic:iterate id="lista" name="Form" property="lupaLinhaAbasGSMVO.abaHistorico.listaHistoricoArray">
                    <vivo:tbRow key="linha1">
                        <vivo:tbRowColumn><bean:write name="lista" property="dtAssociacao"/></vivo:tbRowColumn>
                        <vivo:tbRowColumn><bean:write name="lista" property="dsAparelho"/></vivo:tbRowColumn>
                        <vivo:tbRowColumn><bean:write name="lista" property="dsFabricante"/></vivo:tbRowColumn>
                    </vivo:tbRow>
                    </logic:iterate>
                    </logic:present>
                    <logic:notEqual value="" name="Form" property="lupaLinhaAbasGSMVO.msgErro">
                        <table width="100%" border="0" cellpadding="0" cellspacing="0">
                            <tr>
                                <td align="center" style="font-color:red;"><bean:write name="Form" property="lupaLinhaAbasGSMVO.msgErro"/></td>
                            </tr>
                        </table>
                    </logic:notEqual>
                </vivo:tbRows>
            </vivo:tbTable>
        </form>
    </body>
</html>