<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="configurarSubCampanhaActionForm" />
<bean:define id="aStatusDisp" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="configurarSubCampanhaActionForm.statusDisp" />
<bean:define id="aMotivoDisp" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="configurarSubCampanhaActionForm.motivoDisp" />
<bean:define id="aSubMotivoDisp" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="configurarSubCampanhaActionForm.subMotivoDisp" />
<html>
    <head>
        <title> Web Application Page </title>
        <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
	<script language="javascript">
		function consultaIFrameMotivo()
		{
			 var idStatus = document.forms["configurarSubCampanhaActionForm"].statusDispSelecionado.options[document.forms["configurarSubCampanhaActionForm"].statusDispSelecionado.selectedIndex].value;

             if( idStatus.length > 0 ){
                 //Monta o path seleção
                 document.forms["configurarSubCampanhaActionForm"].target = "iframe";
                 document.forms["configurarSubCampanhaActionForm"].action = "../../Configurar/ConfigurarSubCampanha/obterMotivos.do?acao=status&idStatus="+idStatus;
                 document.forms["configurarSubCampanhaActionForm"].submit();
                 parent.parent.mostrar_div();
             }
		}
				
		function consultaIFrameSubMotivo() 
		{
			 var idStatus = document.forms["configurarSubCampanhaActionForm"].statusDispSelecionado.options[document.forms["configurarSubCampanhaActionForm"].statusDispSelecionado.selectedIndex].value;
			 var idSubMotivo = document.forms["configurarSubCampanhaActionForm"].motivoDispSelecionado.options[document.forms["configurarSubCampanhaActionForm"].motivoDispSelecionado.selectedIndex].value;

             if( idStatus.length > 0 && idSubMotivo.length > 0 ){
                 //Monta o path seleção
                 document.forms["configurarSubCampanhaActionForm"].target = "iframe";
                 document.forms["configurarSubCampanhaActionForm"].action = "../../Configurar/ConfigurarSubCampanha/obterSubMotivos.do?acao=motivo&idStatus=" + idStatus + "&idMotivo="+idSubMotivo;
                 document.forms["configurarSubCampanhaActionForm"].submit();
                 parent.parent.mostrar_div();
             }
		}
                 
        function salvar()
		{
            if(document.configurarSubCampanhaActionForm.elements["statusDispSelecionado"].value == "")
			{
                alert("Favor selecionar um Status!");
            }
			else if(document.configurarSubCampanhaActionForm.elements["motivoDispSelecionado"].value == "")
			{
                alert("Favor selecionar um Motivo!");
            }
			else if(document.configurarSubCampanhaActionForm.elements["subMotivoDispSelecionado"].value == "")
			{
                alert("Favor selecionar um Sub Motivo!");
            }
			else
			{
                document.configurarSubCampanhaActionForm.action = "../../Configurar/ConfigurarSubCampanha/salvarStatusMotivoSubMotivo.do";
                document.configurarSubCampanhaActionForm.submit();
                parent.parent.mostrar_div();
            }
		}		
	</script>
    <SCRIPT FOR=window EVENT=onload>
        parent.parent.oculta_div();
    </SCRIPT>
    </head>
    <acesso:controlHiddenItem nomeIdentificador="cam_csc_verpagina">
    <form action="ConfigurarSubCampanhaAction.do" name="configurarSubCampanhaActionForm" method="post">
        <table bgcolor="#ededed" height="100%" width="100%" border="0" cellspacing="5" cellpadding="2" >
            <tr>
                <td width="230" height="25" align="right" class="tblEstatica_campoNome">Status:</td>
                <td valign="top" align="left" >
                <html:select name="Form" tabindex="1" property="statusDispSelecionado" size="1" style="width=300px;height=10px" onchange="consultaIFrameMotivo();">
                <html:option value=""></html:option>
                <html:options collection="aStatusDisp" property="codigo" labelProperty="descricao"/> </html:select>
                </td>
            </tr>
            <tr>
                <td height="25" align="right" class="tblEstatica_campoNome">Motivo:</td>
                <td valign="top" align="left">
                <html:select name="Form" tabindex="2" property="motivoDispSelecionado" size="1" style="width=300px;height=10px" onchange="consultaIFrameSubMotivo();">
                <html:option value=""></html:option>
                <html:options collection="aMotivoDisp" property="codigo" labelProperty="descricao"/> </html:select>
                </td>
            </tr>
            <tr>
                <td align="right" height="25" class="tblEstatica_campoNome">Sub
                Motivo:</td>
                <td align="left">
                <html:select name="Form" tabindex="3" property="subMotivoDispSelecionado" size="1" style="width=300px;height=10px">
                <html:options collection="aSubMotivoDisp" property="codigo" labelProperty="descricao"/> </html:select>
                </td>
            </tr>
            <tr>
                <td align="left" height="40"> </td>
                <td>
                <table align="right">
                    <tr>
                        <td>
                <acesso:controlHiddenItem nomeIdentificador="cam_csc_gravar">
                <img  onClick="salvar();" tabindex="4" src="/FrontOfficeWeb/resources/images/bt_registrar_nrml.gif" border="0" onMouseOver="this.src = '/FrontOfficeWeb/resources/images/bt_registrar_over.gif'" onMouseOut="this.src = '/FrontOfficeWeb/resources/images/bt_registrar_nrml.gif'" style="border:none;cursor:hand;"/>
                </acesso:controlHiddenItem> &nbsp;
               </td>
               </tr>
               </table>
                </td>
            </tr>
            <tr>
                <td> </td>
            </tr>
        </table>
        <iframe scrolling="yes" name="iframe" height="1px" width="1px"></iframe>
        <netui:hidden dataSource="{actionForm.idCampanha}" />
        <netui:hidden dataSource="{actionForm.idSubCampanha}" />
        <netui:hidden dataSource="{actionForm.idSubCampanhaFixa}" />
    </form>
    </acesso:controlHiddenItem>
</html>
