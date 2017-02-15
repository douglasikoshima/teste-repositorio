<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm" />
<html>
    <head>
        <script language="JavaScript">
            function vaciarLista() {
                //limpa o combo de comunicação disponivel
                while(document.all['selecaoFormulario'].options.length != 1)
                    document.all['selecaoFormulario'].options.remove(1);
                document.all['textoPesquisa'].value = "";
            }

            function realizarPesquisa(){
                //Liga animação
                if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
                document.forms["registrarContatoForm"].target = "iPesquisaForm";
                document.forms["registrarContatoForm"].action = "obterPesquisaFormulario.do";
                document.forms["registrarContatoForm"].submit();
            }

            function finalizar() {
                var nome = document.all['nomeCampo'].value;
                if(document.all['selecaoFormulario'].selectedIndex == 0){
                    alert("Seleccione um item válido!");
                    return false;
                }
                if(typeof(parent.ifrmAbas)!="undefined"){
                    parent.ifrmAbas.document.all[nome].value = document.all['selecaoFormulario'].options(document.all['selecaoFormulario'].selectedIndex).text;
                    parent.dvPesqForm.style.display = 'none';
                    parent.ifrmAbas.ativar_combos();
                }else{
                    parent.document.all[nome].value = document.all['selecaoFormulario'].options(document.all['selecaoFormulario'].selectedIndex).text;
                    parent.dvPesqForm.style.display = 'none';
                    parent.ativar_combos();
                }
            }
            //Fim animação
            if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
        </script>
        <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
    </head>
    <body>
        <input type="hidden" name="nomeCampo" id="nomeCampo"/>
        <form name="registrarContatoForm">
            <html:hidden name="Form" property="idCampo"/>
            <%--<html:hidden name="Form" property="user"/>--%>
            <table border="0" width="100%">
                <tr>
                    <td colspan="2"><b id="tdCampo"></b></td>
                </tr>
                <tr>
                    <td>Pesquisa:</td>
                    <td width="100%" align="right">
                        <html:text name="Form" property="textoPesquisa" size="35" style="WIDTH:190px"/>
                    </td>
                </tr>
                <tr>
                    <td>Resultado:</td>
                    <td width="100%" align="right">
                        <html:select name="Form" property="selecaoFormulario" style="WIDTH:190px">
                            <html:option value="">-- Selecione --</html:option>
                        </html:select>
                    </td>
                </tr>
                <tr align="center">
                    <td colspan="3">
                        <table align="center" cellspacing="25">
                            <tr>
                                <td>
                                    <vivo:botao id="btPesquisar" width="50px" height="10px" value="Pesquisar" styleClass="btTemplate" onclick="realizarPesquisa();" />
                                </td>
                                <td>
                                    <vivo:botao id="btOk" width="50px" height="10px" value="Selecionar" styleClass="btTemplate" onclick="finalizar();" />
                                </td>
                            </tr>
                        </table>
                    <td>
                </tr>
            </table>
        </form>
        <iframe name="iPesquisaForm" style="visibility:hidden;" width="1" height="1"></iframe>
    </body>
</html>