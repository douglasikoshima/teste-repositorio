<!--
M�dulo.....: Gest�o de Processos
Caso de Uso: Atendimento Workflow
Vers�o.....: $Author: a5112274 $ - $Revision: 1.1 $ - $Date: 2011/04/25 19:36:41 $
-->

<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
   
<acesso:controlInitEnv/>
<link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
<script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
<acesso:controlHiddenItem nomeIdentificador="wor_gsato_verpagina">
<vivo:body idTable="tbMain" idDiv="dvMain" height="290" width="770">
    <p>
        Opera��o realizada com sucesso!
    </p>
    <table border="0" width="100%">
        <tr>
            <td align="center">
                <vivo:botao id="btFechar" width="100px" height="15px" value="Fechar" styleClass="btTemplate" onclick="fechar();"/>
            </td>
        </tr>
    </table>  
</vivo:body>
   
</acesso:controlHiddenItem>
<script language=javascript>
    //fechar();
    
    function fechar() {
        parent.submitPesquisar();
    }

    //Fim anima��o
    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();

</script>
