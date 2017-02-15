<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/fidelizacao.tld" prefix="fid"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>


<bean:define id="formAdimplencia" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formAdimplencia"/>
<link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
<link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/fidelizacao_relatorios.css">
<script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    
    <netui-template:setAttribute value="Fidelizacao" name="Relatorio"></netui-template:setAttribute>
    <netui-template:section name="headerSection"> </netui-template:section>
    <netui-template:section name="bodySection">
    
    <acesso:controlHiddenItem nomeIdentificador="fid_rel_adimpl_verpagina">
    
    <SCRIPT FOR=window EVENT=onload>
        top.frameApp.oculta_div();              
    </script>   
    <jsp:include page="/resources/menus/MenuPrincipal.jsp"/>
    <div><img src="<%=request.getContextPath()%>/resources/images/transp.gif" border="0" width="1" height="5"></div>
    
    <vivo:sessao id="idFrelatorio" height="465" width="790" label="Relatórios Fidelização" operacoes="Relatório Quantitativo de Análise de Inadimplência" scroll="Y">   
    
    <div><img src="<%=request.getContextPath()%>/resources/images/transp.gif" border="0" width="1" height="3"></div>

    <table width="739" cellpadding="3" cellspacing="0" border="0">
        <tr>
            <td valign="top">Quantidade de consultas adimplentes</td>
            <td><bean:write name="formAdimplencia" property="totalAdimplentes"/></td>
        </tr>
        <tr>
            <td valign="top">Quantidade de consultas inadimplentes</td>
            <td><bean:write name="formAdimplencia" property="totalInadimplentes"/></td>
        </tr>
        <tr>
            <td valign="top"><b>Total de consultas</b></td>
            <td><b><bean:write name="formAdimplencia" property="totalConsultas"/></b></td>
        </tr>
    </table>

    <div><img src="<%=request.getContextPath()%>/resources/images/transp.gif" border="0" width="1" height="360"></div>

    <table width="200">
        <tr>
            <td width="500">
                <img align="letf" id="voltar" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_voltar_over.gif'" onClick="window.location.href='/FrontOfficeWeb/fidelizacao/Relatorio/begin.do'; top.frameApp.mostrar_div(); return false" style="border:none;cursor:hand;" hspace="5" vspace="5"/>                
            </td>
        </tr>            
    </table>

    </vivo:sessao>
    
    </acesso:controlHiddenItem>
    
    </netui-template:section>
    
</netui-template:template>  