<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<bean:define id="numerico" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relatorioMotivoForm.relatorioPercentualVO"/>
<bean:define id="relHeader" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="headerVO"/>                 

<html>
<head>
    <meta HTTP-EQUIV="Pragma" CONTENT="no-cache">
    <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
    <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    <SCRIPT FOR=window EVENT=onload>
        //top.frameApp.oculta_div();  
        window.print();       
    </script>  
</head>
<body leftmargin="0" bottommargin="0" rightmargin="0" topmargin="0">    
<table width="600" align="left" cellpadding="5" cellspacing="0">
        <tr>
            <td>
            <table width="100%" cellpadding="2" cellspacing="2" border="1">
                <tr bordercolor="#FFFFFF">
                    <td rowspan="7"><img src="/FrontOfficeWeb/resources/images/logo_relatorio.jpg"></td>
                    <td colspan="4" bgcolor="#00569f" align="center"><b><font color="#FFFFFF">Relatório
                    de Percentual por Motivos</font></b></td>
                </tr>
                <tr>
                    <td width="100" bordercolor="#FFFFFF" bgcolor="#ededed"><b>Data de Emissão:</b></td>
                    <td width="180" bordercolor="#adadad"><bean:write name="relHeader" property="dtEmissao" /></td>
                    <td width="150" bordercolor="#FFFFFF" bgcolor="#ededed"><b>Operador:</b></td>
                    <td width="180" bordercolor="#adadad"><bean:write name="relHeader" property="nmLoginUsuario"  /></td>
                </tr>
                <tr>
                    <td bordercolor="#FFFFFF" bgcolor="#ededed"><b>Data Inicial:</b></td>
                    <td bordercolor="#adadad"><bean:write name="relHeader" property="filtroDtInicio" /></td>
                    <td bordercolor="#FFFFFF" bgcolor="#ededed"><b>Data Final:</b></td>
                    <td bordercolor="#adadad"><bean:write name="relHeader" property="filtroDtFim" /></td>
                </tr>  
                <tr>
                    <td bordercolor="#FFFFFF" bgcolor="#ededed"><b>Grupo:</b></td>
                    <td bordercolor="#adadad">&nbsp;<bean:write name="relHeader" property="nmGrupo" /></td>
                    <td bordercolor="#FFFFFF" bgcolor="#ededed"><b>Canal:</b></td>
                    <td bordercolor="#adadad"><bean:write name="relHeader" property="nmCanal" /></td>
                </tr>                              
                <tr>
                    <td bordercolor="#FFFFFF" bgcolor="#ededed"><b>Campanha:</b></td>
                    <td bordercolor="#adadad"><bean:write name="relHeader" property="sgCampanha" /></td>
                    <td bordercolor="#FFFFFF" bgcolor="#ededed"><b>Sub Campanha:</b></td>
                    <td bordercolor="#adadad"><bean:write name="relHeader" property="nmSubCampanha" /></td>
                </tr>
                
                <tr>
                    <td bordercolor="#FFFFFF" bgcolor="#ededed"><b>Versão:</b></td>
                    <td bordercolor="#adadad"><bean:write name="relHeader" property="sqVersao" /></td>
                    <td bordercolor="#FFFFFF" bgcolor="#ededed"><b>Distinção:</b></td>
                    <td bordercolor="#adadad"><bean:write name="relHeader" property="inDistincao" /></td>
                <tr>
                    <td bordercolor="#FFFFFF" bgcolor="#ededed"><b>Regional:</b></td>
                    <td colspan="4" bordercolor="#adadad">&nbsp;<bean:write name="relHeader" property="nmRegional" /></td>
                </tr>
            </table>
            </td>
        </tr>
        <tr>
            <td> </td>
        </tr>
        <tr>
            <td>
            <table width="100%" cellpadding="2" cellspacing="2" border="1">
                <tr bordercolor="#FFFFFF">
                    <td>
                    <table width="100%" style="border-collapse: collapse;" border="1">
                        <tr>
                            <td width="30%" bgcolor="#ededed">Motivo</td>
                            <td width="30%" bgcolor="#ededed">Sub Motivo</td>
                            <td width="20%" bgcolor="#ededed" align="center">Quantidade</td>
                            <td width="20%" bgcolor="#ededed" align="center">Porcentagem</td>                            
                        </tr>                    
                        <logic:iterate id="linha" name="numerico" property="numericoArray">
                        <tr>
                            <td width="30%" bgcolor="#adadad"><bean:write name="linha" property="sgTipoMotivoCampanha"/></td>
                            <td width="30%" bgcolor="#adadad"><bean:write name="linha" property="sgTipoSubMotivoCampanha"/></td>
                            <td width="20%" bgcolor="#adadad" align="center"><bean:write name="linha" property="nroAtendimento"/></td>
                            <td width="20%" bgcolor="#adadad" align="center"><bean:write name="linha" property="porcent"/></td>                            
                        </tr>
                        </logic:iterate>
                        <tr>
                            <td width="30%" align="right" bgcolor="#ededed"></td>
                            <td width="30%" align="right" bgcolor="#ededed">Total:</td>
                            <td width="20%" bgcolor="#adadad" align="center"><bean:write name="numerico" property="totalNroAtendimento"/></td>
                            <td width="20%" bgcolor="#adadad" align="center"><bean:write name="numerico" property="totalPorcent"/></td>                            
                        </tr>                        
                    </table>
                    </td>
                </tr>
            </table>
            </td>
        </tr>
    </table>
</body>
</html>