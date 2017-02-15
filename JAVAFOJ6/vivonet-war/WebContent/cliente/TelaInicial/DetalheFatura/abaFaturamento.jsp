<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
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
    <acesso:controlHiddenItem nomeIdentificador="cli_abf_verpagina">

    
         <bean:define id="faturamento" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="lupaFaturamentoPos.lupaFaturamentoPosVO.LFFaturamentoArray" />
    
         <html:form action="/cliente/TelaInicial/DetalheFatura/loadFatura.do">  
            <table width="773" border="0" cellpadding="0" bgcolor="white"cellspacing="1">
                <tr>
                    <td>            
                        <vivo:tbTable selectable="false" layoutWidth="755" layoutHeight="100" tableWidth="750" styleId="tableTitle" sortable="true">
                            <vivo:tbHeader>
                                <vivo:tbHeaderColumn align="center" width="17%" type="string">Mês</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="17%" type="string">Ano</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="17%" type="string">Ciclo</vivo:tbHeaderColumn>	
                                <vivo:tbHeaderColumn align="center" width="17%" type="string">Vencimento</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="16%" type="string">Valor Total</vivo:tbHeaderColumn>	
                                <vivo:tbHeaderColumn align="center" width="16%" type="string">Status</vivo:tbHeaderColumn>					                            						                                                            
                            </vivo:tbHeader>
                            <vivo:tbRows>
                                <logic:iterate id="fat" name="faturamento">
                                    <vivo:tbRow key="linha1">
                                         <vivo:tbRowColumn><bean:write name="fat" property="nrMes" /></vivo:tbRowColumn>
                                         <vivo:tbRowColumn><bean:write name="fat" property="nrAno" /></vivo:tbRowColumn>
                                         <vivo:tbRowColumn><bean:write name="fat" property="dsCiclo" /></vivo:tbRowColumn>
                                         <vivo:tbRowColumn><bean:write name="fat" property="dtVencimento" /></vivo:tbRowColumn> 
                                         <vivo:tbRowColumn><bean:write name="fat" property="vlTotal" /></vivo:tbRowColumn>  
                                         <vivo:tbRowColumn><bean:write name="fat" property="dsStatus" /></vivo:tbRowColumn>                                                                      
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