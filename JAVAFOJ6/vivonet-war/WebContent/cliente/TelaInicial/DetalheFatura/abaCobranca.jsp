<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
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
    <acesso:controlHiddenItem nomeIdentificador="cli_abc_verpagina">
          <html:form action="/cliente/TelaInicial/DetalheFatura/loadCobranca.do">
          
          <bean:define id="cobranca" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="lupaFaturamentoPos.lupaFaturamentoPosVO.LFCobrancaArray" />
          
            <table width="600" border="0" cellpadding="0" bgcolor="white"cellspacing="1">
                <tr>
                    <td>            
                        <vivo:tbTable selectable="false" layoutWidth="755" layoutHeight="126" tableWidth="755" styleId="tableTitle" sortable="true">
                            <vivo:tbHeader>
                                <vivo:tbHeaderColumn align="center" width="120" type="string">Data Faturamento</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="100" type="string">Valor Faturado</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="80" type="string">Valor Aberto</vivo:tbHeaderColumn>	
                                <vivo:tbHeaderColumn align="center" width="100" type="string">Valor 30 Dias</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="100" type="string">Valor 60 Dias</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="90" type="string">Valor 90 Dias</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="120" type="string">Valor pós 90 Dias</vivo:tbHeaderColumn>						                            						                                                           
                            </vivo:tbHeader>
                            <vivo:tbRows>
                                <logic:iterate id="cob" name="cobranca">
                                    <vivo:tbRow key="linha1">
                                         <vivo:tbRowColumn><bean:write name="cob" property="dtFaturamento" /></vivo:tbRowColumn>
                                         <vivo:tbRowColumn><bean:write name="cob" property="vlFaturado" /></vivo:tbRowColumn>
                                         <vivo:tbRowColumn><bean:write name="cob" property="vlAbertoAtual" /></vivo:tbRowColumn>
                                         <vivo:tbRowColumn><bean:write name="cob" property="vl30Dias" /></vivo:tbRowColumn> 
                                         <vivo:tbRowColumn><bean:write name="cob" property="vl60Dias" /></vivo:tbRowColumn>
                                         <vivo:tbRowColumn><bean:write name="cob" property="vl90Dias" /></vivo:tbRowColumn> 
                                         <vivo:tbRowColumn><bean:write name="cob" property="vlMaisDias" /></vivo:tbRowColumn>                                                                          
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