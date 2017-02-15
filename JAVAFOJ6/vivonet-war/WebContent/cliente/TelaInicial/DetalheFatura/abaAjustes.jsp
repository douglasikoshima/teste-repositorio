<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<html>
    <head>
        <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    </head>
    
    <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
    <!--
        parent.parent.oculta_div();
    -->
    </SCRIPT>
    
    <body rightmargin="0" leftmargin="0" topmargin="0">
    <acesso:controlHiddenItem nomeIdentificador="cli_abj_verpagina">
          <html:form action="/cliente/TelaInicial/DetalheFatura/loadAjustes.do"> 
          
          <bean:define id="ajustes" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="lupaFaturamentoPos.lupaFaturamentoPosVO.LFAjustesArray" />
          
            <table width="500" border="0" cellpadding="0" bgcolor="white"cellspacing="1">
                <tr>
                    <td>            
                        <vivo:tbTable selectable="false" layoutWidth="755" layoutHeight="120" tableWidth="755" styleId="tableTitle" sortable="true">
                            <vivo:tbHeader>
                                <vivo:tbHeaderColumn align="center" width="90" type="string">Data ajuste</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="90" type="string">Valor ajuste</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="480" type="string">Descrição de ajuste - Fatura personalizada</vivo:tbHeaderColumn>	
                                <vivo:tbHeaderColumn align="center" width="95" type="string">Faturado (S/N)</vivo:tbHeaderColumn>						                            						                                                           
                            </vivo:tbHeader>
                            <vivo:tbRows>
                                <logic:iterate id="aj" name="ajustes">
                                    <vivo:tbRow key="linha1">
                                         <vivo:tbRowColumn><bean:write name="aj" property="dtAjuste" /></vivo:tbRowColumn>
                                         <vivo:tbRowColumn><bean:write name="aj" property="vlAjuste" /></vivo:tbRowColumn>
                                         <vivo:tbRowColumn><bean:write name="aj" property="dsAjuste" /> <bean:write name="aj" property="inPersonalizada" /></vivo:tbRowColumn>
                                         <vivo:tbRowColumn><bean:write name="aj" property="inFaturado" /></vivo:tbRowColumn>                                                                        
                                    </vivo:tbRow>
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