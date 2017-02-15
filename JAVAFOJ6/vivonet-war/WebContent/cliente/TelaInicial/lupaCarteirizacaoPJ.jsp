<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<bean:define id="carteiraPJForm" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="carteiraPJForm" />

<html> 
    <head>
        <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/formatPhoneNumber.js"></script>
        <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
        <!--
            parent.oculta_div();  
        -->
        </SCRIPT>
    </head>
    <body rightmargin="0" leftmargin="0" topmargin="0">
    <acesso:controlHiddenItem nomeIdentificador="cli_lcpj_verpagina">
        <form action="loadCarteirizacao.do" method="post" name="carteiraPJForm">
            <table width="765" class="tbl_bgBlue" border="0" cellspacing="1" cellpadding="1">
                <tr> 
                    <td colspan="4" class="tbl_titulo">Detalhe Carteirização (Corporativo)</td>
                </tr>                                                                                 
               <tr><td height="10"></td></tr>
                <tr>
                    <td>
                        <table width="765" border="0" cellspacing="1" cellpadding="0" >
                            <tr>
                                <td>            
                                    <vivo:tbTable selectable="true" layoutWidth="755" layoutHeight="125" tableWidth="770" styleId="tableTitle" sortable="true">
                                        <vivo:tbHeader>
                                            <vivo:tbHeaderColumn align="center" width="40%" type="string">Cargo</vivo:tbHeaderColumn>
                                            <vivo:tbHeaderColumn align="center" width="40%" type="string">Nome</vivo:tbHeaderColumn>
                                            <vivo:tbHeaderColumn align="center" width="20%" type="string">Tel. Contato</vivo:tbHeaderColumn>	                                		                            						                                                            
                                        </vivo:tbHeader>
                                        <vivo:tbRows>
                                            <logic:iterate name="carteiraPJForm" property="listaPF" id="carteiraPF">
                                                <vivo:tbRow key="Linha">
                                                    <vivo:tbRowColumn><bean:write name="carteiraPF" property="tipoRelacionamento.objetoTipo.dsTipo"/></vivo:tbRowColumn>
                                                    <vivo:tbRowColumn><bean:write name="carteiraPF" property="nmNome"/></vivo:tbRowColumn>
                                                    <vivo:tbRowColumn><bean:write name="carteiraPF" property="nrTelefone"/></vivo:tbRowColumn>
                                                </vivo:tbRow>
                                            </logic:iterate>                                                                                                                                                                                             
                                        </vivo:tbRows>
                                    </vivo:tbTable>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr> 
                <tr>                    
                    <td class='tbl_titulo'>Número de Linhas: <html:text name="carteiraPJForm" property="lupaCarteiraPJ.nrLinhas" readonly="true" size="1"/></td>
                </tr>
                <tr><td height="5"></td></tr>     
                <tr > 
                    <td colspan="4"><img src="/FrontOfficeWeb/resources/images/pixel_azul.gif" height="1" width="765"/></td>
                </tr>                
            </table>                    
        </form>
        <script>
            if('<bean:write name="carteiraPJForm" property="inVazio"/>' == 'TRUE'){
                alert('Cliente não é atendido por um Gestor de Relacionamento!');
                top.frameApp.fechaLupa();
            }
        </script>
    </acesso:controlHiddenItem>
    </body>
</html>