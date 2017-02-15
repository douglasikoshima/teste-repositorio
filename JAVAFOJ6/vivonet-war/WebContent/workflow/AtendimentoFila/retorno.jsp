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
<bean:define id="wFAcaoRetornoVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm.WFAcaoRetornoVO"/>
<body>
<acesso:controlHiddenItem nomeIdentificador="wor_suret_verpagina">
    <br>
    <br>
    <table border="0" width="100%">
        <tr>
            <td align="center">
                <font size=3><b>
                <logic:match value="S" name="wFAcaoRetornoVO" property="acaoExecucao">
                    Operação Concluída com Sucesso!
                </logic:match>
                <logic:match value="N" name="wFAcaoRetornoVO" property="acaoExecucao">
                    Operação não Concluída!
                </logic:match>
                </b></font>
            </td>
        </tr>
        <tr>
            <td align="center">
                <b>Mensagem:</b>
                <bean:write name="wFAcaoRetornoVO" property="mensagem"/>
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
   
</acesso:controlHiddenItem>
</body>
<script language=javascript>
    //fechar();
    
    function fechar() {

            parent.submitPesquisar();

   
    }

    //Fim animação
    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();

</script>
