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
<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="favoritosForm"/>
<SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
    <!--
        parent.parent.oculta_div();
    -->
</SCRIPT>
<html>
    <head>
        <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/formatPhoneNumber.js"></script>
    </head>
    <body style="margin-left:5px; margin-right:5px; margin-top:5px;">
      <logic:equal value="TRUE" name="form" property="erro">    
           <table width="739" border="0" cellpadding="0" cellspacing="0" align="center">
        <tr>
          <td>&nbsp;</td>
        </tr>
        <tr>
        
        <td><i><b>Dados de Favoritos não recuperados. Sistema temporariamente fora de serviço!</b></i></td>
        </tr></table>
    </logic:equal>
	<acesso:controlHiddenItem nomeIdentificador="cli_fpp_verpagina">
      <logic:equal value="FALSE" name="form" property="erro"> 
        <vivo:tbTable selectable="true" onRowClick="" layoutWidth="735" layoutHeight="360" tableWidth="735" styleId="tableTitle" sortable="true">
            <vivo:tbHeader>
                <vivo:tbHeaderColumn align="center" width="100%" type="string">Número</vivo:tbHeaderColumn>
            </vivo:tbHeader>
            <vivo:tbRows>
                <logic:iterate id="itens" name="form" property="favoritosVO.favoritosItemArray">                
                    <vivo:tbRow key="Linha">
                        <vivo:tbRowColumn><bean:write name="itens" property="numero" /></vivo:tbRowColumn>
                    </vivo:tbRow>
                </logic:iterate>
            </vivo:tbRows>
        </vivo:tbTable>
        </logic:equal>
        
	</acesso:controlHiddenItem>
    </body>
</html>
