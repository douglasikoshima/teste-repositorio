<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
<script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="notasForm"/>
<bean:define id="acaoVO" name="form" property="acaoRetornoVO"/>
<body>
<acesso:controlHiddenItem nomeIdentificador="ntura_ont_verpagina">
<form>
    <html:hidden name="form" property="idAtendimentoNota"/>
    <br>
    <br>
    <table border="0" width="100%">
        <tr>
            <td align="center">
                <font size=3><b>
                <logic:match value="S" name="acaoVO" property="acaoExecucao">
                    Operação Concluída com Sucesso!
                </logic:match>
                <logic:match value="N" name="acaoVO" property="acaoExecucao">
                    Operação não Concluída!
                </logic:match>
                </b></font>
            </td>
        </tr>
    </table>
    <br>
    <br>    
    <table border="0" width="100%">
        <tr>
            <td align="center">
                <vivo:botao id="btFechar" width="100px" height="15px" value="Fechar" styleClass="btTemplate" onclick="fechar();"/>
            </td>
        </tr>
    </table>  
</form>
</acesso:controlHiddenItem>
</body>
<script language=javascript>
    //fechar();
    
    function fechar() {
        
        parent.document.forms[0]['tela'].value = '1';
        parent.document.forms[0].action = 'lerNotasURADetalhe.do?idAtendimentoNota=<bean:write property="idAtendimentoNota" name="form"/>'; 
        parent.document.forms[0].target = "ifrmHistorico";
        parent.document.forms[0].submit(); 
        parent.abaHistoricoNota();
        
        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
  
    }

    //Fim animação
    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();

</script>
