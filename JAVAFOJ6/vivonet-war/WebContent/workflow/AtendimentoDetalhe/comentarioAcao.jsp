<!--
M�dulo.....: Gest�o de Processos
Caso de Uso: Detalhe do processo
Vers�o.....: $Author: a5112272 $ - $Revision: 1.2 $ - $Date: 2011/05/17 17:18:00 $
-->

<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
   
<acesso:controlInitEnv/>
<link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">

<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoDetalheForm"/>

<html>
    <body>
<acesso:controlHiddenItem nomeIdentificador="wor_cacao_verpagina">
        <p>
            <b>Coment�rio: </b><bean:write name="form" property="comentarioHistorico"/> 
        </p>
   
</acesso:controlHiddenItem>
    </body>
</html>
<script>
    
    //Fim anima��o
    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
    
</script>