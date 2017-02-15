<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
    <!--
        parent.oculta_div();
    -->
</SCRIPT>
<html>
    <head>
        <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    </head>
    <body rightmargin="0" leftmargin="0" topmargin="0">
    <acesso:controlHiddenItem nomeIdentificador="cli_histservicos_verpagina">    
         <bean:define id="Servicos" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="abaServ.historicoServicos.servicosArray"/>
         
         <html:form action="/cliente/TelaInicial/getHistoricoServicos.do">
            <table width="500" border="0" cellpadding="0" bgcolor="white"cellspacing="1">
                <tr>
                    <td>            
                        <vivo:tbTable selectable="true" layoutWidth="750" layoutHeight="300" tableWidth="750" styleId="tableTitle" sortable="false">
                            <vivo:tbHeader>
                                <vivo:tbHeaderColumn align="center" width="10%" type="string">CSA</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="23%" type="string">Serviço</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="23%" type="string">Facilidade</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="10%" type="string">Canal Acesso</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="14%" type="string">Identificador</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="10%" type="date">Vigência</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="10%" type="date">Expiração</vivo:tbHeaderColumn>
                            </vivo:tbHeader>
                            <vivo:tbRows>
                                <logic:iterate id="ListaServicos" name="Servicos">
                                    <bean:define id="Facilidades" name="ListaServicos" property="facilidadesArray"/>
                                    <vivo:tbRow key="idServico">
                                         <vivo:tbRowColumn><bean:write name="ListaServicos" property="dsCSA"/></vivo:tbRowColumn>
                                         <vivo:tbRowColumn><bean:write name="ListaServicos" property="dsServico"/></vivo:tbRowColumn>
                                         <vivo:tbRowColumn>&nbsp;</vivo:tbRowColumn>
                                         <vivo:tbRowColumn>&nbsp;</vivo:tbRowColumn>
                                         <vivo:tbRowColumn>&nbsp;</vivo:tbRowColumn>
                                         <vivo:tbRowColumn><bean:write name="ListaServicos" property="dtVigencia"/></vivo:tbRowColumn>
                                         <vivo:tbRowColumn><bean:write name="ListaServicos" property="dtExpiracao"/></vivo:tbRowColumn>
                                         <logic:iterate id="ListaFacilidades" name="Facilidades">
                                            <vivo:tbRow key="idFacilidade">
                                                <vivo:tbRowColumn>&nbsp;</vivo:tbRowColumn>
                                                <vivo:tbRowColumn>&nbsp;</vivo:tbRowColumn>
                                                <vivo:tbRowColumn><bean:write name="ListaFacilidades" property="dsFacilidade"/></vivo:tbRowColumn>
                                                <vivo:tbRowColumn><bean:write name="ListaFacilidades" property="dsCanalAcesso"/></vivo:tbRowColumn>
                                                <vivo:tbRowColumn><bean:write name="ListaFacilidades" property="nrIdentificador"/></vivo:tbRowColumn>
                                                <vivo:tbRowColumn>&nbsp;</vivo:tbRowColumn>
                                                <vivo:tbRowColumn>&nbsp;</vivo:tbRowColumn>
                                            </vivo:tbRow>
                                         </logic:iterate>
                                    </vivo:tbRow>
                                <tr><td colspan="7" height="1" bgcolor="#CCCCCC" >&nbsp;</td></tr>
                                </logic:iterate>
                            </vivo:tbRows>
                        </vivo:tbTable>
                    </td>     
                </tr>
            </table>
        </html:form>        
    </acesso:controlHiddenItem>
    </body>
</html>