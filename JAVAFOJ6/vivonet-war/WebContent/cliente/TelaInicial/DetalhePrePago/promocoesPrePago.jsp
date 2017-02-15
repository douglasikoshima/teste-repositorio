<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>


<acesso:controlInitEnv/>
<SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
    <!--
        parent.parent.oculta_div();
    -->
</SCRIPT>
<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="promocoesForm"/>

<html>
    <head>
        <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        <script></script>
    </head>
    <body style="margin-left:5px; margin-right:5px; margin-top:5px;">
     <logic:equal value="TRUE" name="form" property="erro">    
           <table width="739" border="0" cellpadding="0" cellspacing="0" align="center">
        <tr>
          <td>&nbsp;</td>
        </tr>
        <tr>
        
        <td><i><b>Dados de Promoções não recuperados. Sistema temporariamente fora de serviço!</b></i></td>
        </tr></table>
    </logic:equal>
     
    <acesso:controlHiddenItem nomeIdentificador="cli_ppp_verpagina">
     <logic:notEqual value="TRUE" name="form" property="erro">    
        <vivo:tbTable selectable="true" onRowClick="" layoutWidth="735" layoutHeight="360" tableWidth="735" styleId="tableTitle" sortable="true">
            <vivo:tbHeader>
                <vivo:tbHeaderColumn align="center" width="476" type="string">Promo&ccedil;&atilde;o</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="84" type="string">Estado</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="175" type="string">Per&iacute;odo de Vig&ecirc;ncia</vivo:tbHeaderColumn>
            </vivo:tbHeader>
            <vivo:tbRows>
                <logic:iterate id="itens" name="form" property="promocoesVO.promocoesItemArray">                
                    <vivo:tbRow key="Linha">
                        <vivo:tbRowColumn><bean:write name="itens" property="descricao"/></vivo:tbRowColumn>
                        <vivo:tbRowColumn><bean:write name="itens" property="ativo" /></vivo:tbRowColumn>
                        <vivo:tbRowColumn><bean:write name="itens" property="dataIni" /> a <bean:write name="itens" property="dataFim" /></vivo:tbRowColumn>
                    </vivo:tbRow>
                </logic:iterate>
            </vivo:tbRows>            
        </vivo:tbTable>
        </logic:notEqual>
      
    </acesso:controlHiddenItem>    
    </body>
</html>
