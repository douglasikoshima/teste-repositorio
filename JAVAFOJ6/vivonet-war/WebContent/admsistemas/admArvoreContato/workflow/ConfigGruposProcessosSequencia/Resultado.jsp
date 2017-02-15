<!--
Módulo.....: Gestão de Sistemas
Caso de Uso: Configurar Grupo de Usuário com Seqüencia Workflow
Versão.....: $Author: a5112272 $ - $Revision: 1.2 $ - $Date: 2011/05/17 17:18:00 $
-->
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<bean:define id="Form"         name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="gruposProcessosForm" />

<acesso:controlInitEnv/>
<html>
    <head>
        <title>Configurar Grupos de Procesos com Sequencia - Resultado</title>
    </head>
    <body onload="parent.parent.oculta_div()">
    <acesso:controlHiddenItem nomeIdentificador="adm_result_verpagina">
        <script language=javascript>
        if( top.dvAnimarAguarde != null ) top.stopAnimation();
        </script>
    </acesso:controlHiddenItem>
    </body>
    <script>
    
        alert("<bean:write name="Form" property="mensagem"/>");
        
        //Fim animação
        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
        
    </script>
    
</html>
