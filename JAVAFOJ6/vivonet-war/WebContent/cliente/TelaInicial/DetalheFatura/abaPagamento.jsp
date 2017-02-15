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
    <acesso:controlHiddenItem nomeIdentificador="cli_abp_verpagina">
         <bean:define id="pagamentos" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="lupaFaturamentoPos.lupaFaturamentoPosVO.LFPagamentoArray" />

         <html:form action="/cliente/TelaInicial/DetalheFatura/loadPagamento.do" >  
            <table width="500" border="0" cellpadding="0" bgcolor="white"cellspacing="1">
                <tr>
                    <td>            
                        <vivo:tbTable selectable="false" layoutWidth="755" layoutHeight="120" tableWidth="755" styleId="tableTitle" sortable="true">
                            <vivo:tbHeader>
                                <vivo:tbHeaderColumn align="center" width="15%" type="string">Data Pagamento</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="15%" type="string">Valor Pagamento</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="15%" type="string">Tipo Pagamento</vivo:tbHeaderColumn>	
                                <vivo:tbHeaderColumn align="center" width="20%" type="string">Referência Pagamento</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="20%" type="string">Motivo Reversão</vivo:tbHeaderColumn>	
                                <vivo:tbHeaderColumn align="center" width="15%" type="string">Data Reversão</vivo:tbHeaderColumn>					                            						                                                            
                            </vivo:tbHeader>
                            <vivo:tbRows>
                                <logic:iterate id="pag" name="pagamentos">
                                    <vivo:tbRow key="linha1">
                                         <vivo:tbRowColumn><bean:write name="pag" property="dtPagamento" /></vivo:tbRowColumn>
                                         <vivo:tbRowColumn><bean:write name="pag" property="vlPagamento" /></vivo:tbRowColumn>
                                         <vivo:tbRowColumn><bean:write name="pag" property="dsTipoPagamento" /></vivo:tbRowColumn>
                                         <vivo:tbRowColumn><bean:write name="pag" property="dsReferencia" /></vivo:tbRowColumn> 
                                         <vivo:tbRowColumn><bean:write name="pag" property="dsMotivoReversao" /></vivo:tbRowColumn>  
                                         <vivo:tbRowColumn><bean:write name="pag" property="dtReversao" /></vivo:tbRowColumn>                                                                      
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