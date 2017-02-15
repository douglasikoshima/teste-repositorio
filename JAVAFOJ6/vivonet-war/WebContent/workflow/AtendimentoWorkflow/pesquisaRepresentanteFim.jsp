<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm" />
<html>
    <head>
        <title>Vivo Net</title>
        <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
        <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/controleEventos.js"></script>
    </head>
    <body>
    <acesso:controlHiddenItem nomeIdentificador="wor_prf_verpagina">
        <form action="pesquisaSatisfacaoGravar.do" method="post" name="atendimentoForm">
            <html:hidden name="Form" property="idAtendimento"/>
            Nota do Atendimento: <html:select name="Form" property="notaPesqSatisfa" >
                                    <html:option value="">-- Selecione --</html:option>
                                    <html:option value="0">0</html:option>
                                    <html:option value="1">1</html:option>
                                    <html:option value="2">2</html:option>
                                    <html:option value="3">3</html:option>
                                    <html:option value="4">4</html:option>
                                    <html:option value="5">5</html:option>
                                    <html:option value="6">6</html:option>
                                    <html:option value="7">7</html:option>
                                    <html:option value="8">8</html:option>
                                    <html:option value="9">9</html:option>
                                    <html:option value="10">10</html:option>
                                 </html:select><br>
            Observação: <html:textarea name="Form" property="observacaoPesqSatisfa" onkeyup="checaTextarea(this, 500);"/><br>
        </form>
        <table border="0" width="100%">
            <tr>
                <acesso:controlHiddenItem nomeIdentificador="wor_prf_finalizar">
                    <td align="right">
                        <vivo:botao id="botaoVoltar" width="25px" height="20px" value="Voltar" styleClass="btTemplate" onclick="voltar()"/>
                    </td>
                    <td align="left" width="50px">
                        <vivo:botao id="botaoTransfere" width="25px" height="20px" value="Finalizar" styleClass="btTemplate" onclick="terminaQuest()"/>
                    </td>
                </acesso:controlHiddenItem>
            </tr>
        </table>
        <script>
            function checaTextarea(obj, tamanho){
                obj.value.length>tamanho?obj.value = obj.value.substr(0,tamanho):0;
            }

            function terminaQuest(){
                if(document.forms[0].notaPesqSatisfa.selectedIndex == 0){
                    alert("Escolha uma nota para o atendimento!");
                    return false;
                }
                document.forms[0].action = "pesquisaSatisfacaoGravar.do";
                document.forms[0].submit();
            }

            function voltar(){
                document.forms[0].action = "pesquisaSatisfacaoVoltar.do";
                document.forms[0].submit();
            }

           //Fim animação
            if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
            document.body.focus();
        </script>
    </acesso:controlHiddenItem>
    </body>
</html>