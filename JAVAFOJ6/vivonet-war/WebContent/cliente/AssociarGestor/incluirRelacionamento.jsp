<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<script>
    function salvaAssociar(){
        parent.dvInclusao.style.display = 'none';
        document.forms[0].target="ifrmResultado";
        document.forms[0].action="salvar.do";
        parent.mostrar_div();
        document.forms[0].submit();
    }
</script>
<script for="window" event="onload">
    parent.oculta_div();
</script>
<html:html>
    <html:form action="/cliente/AssociarGestor/inlcuirAlterarRelacionamento.do">
    <bean:define id="ConsAss" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="consultorForm.associarCR.consultorRelacionamentoArray"/>
    <head>
        <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    </head>
    <body leftmargin="5" topmargin="5" bgcolor="#E3ECF4">
        <acesso:controlHiddenItem nomeIdentificador="cli_iacrel_verpagina">
        <table bgcolor="#E3ECF4" border="0" cellpadding="5" cellspacing="5" width="100%">
            <tr>
                <td align="center" valign="top">Consultor de Relacionamento:</td>
            </tr>
            <tr>
                <td align="center" valign="top">
	                <html:select style="width:300px" property="idConsultorSelecionado">
	                	<html:options name="aki" collection="ConsAss" property="idConsultor" labelProperty="nmConsultor"/>
					</html:select>
                </td>
            </tr>
            <tr>
                <td valign="top" align="right">
	                <acesso:controlHiddenItem nomeIdentificador="cli_iacrel_gravar">
	                	<input type="image" style="border:0px;margin:1px 1px;" onClick="salvaAssociar(); return false;" src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif" onmouseover="this.src='/FrontOfficeWeb/resources/images/bt_salvar_over.gif';"/>
	                </acesso:controlHiddenItem>                
                </td>
            </tr>
        </table>
        </acesso:controlHiddenItem>
    </body>
    </html:form>
</html:html>