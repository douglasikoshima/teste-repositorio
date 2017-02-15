<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
<script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm"/>

<vivo:body idTable="tbMain" idDiv="dvMain" height="290" width="770">
    <p>
Problemas ao executar a Operação: <bean:write name="form" property="mensagemErro" />
    </p>
    
    <table border="0" width="100%">
        <tr>
            <td align="center">
                <vivo:botao id="btFechar" width="100px" height="15px" value="Fechar" styleClass="btTemplate" onclick="fechar();"/>
            </td>
        </tr>
    </table>  
</vivo:body>

<script language=javascript>
    //fechar();
    
    function fechar() {
        parent.submitPesquisar();
    }

    //Fim animação
    if( top.dvAnimarAguarde != null ) top.stopAnimation();

</script>
