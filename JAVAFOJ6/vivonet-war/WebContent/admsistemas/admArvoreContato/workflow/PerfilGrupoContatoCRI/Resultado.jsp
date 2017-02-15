<!--
Módulo.....: Gestão de Sistemas
Caso de Uso: Configurar Grupo de Usuário com Seqüencia Workflow
Versão.....: $Author: a5112272 $ - $Revision: 1.2 $ - $Date: 2011/05/25 14:01:46 $
-->
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<bean:define id="Form"         name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="perfilGrupoContatoCRIForm" />

<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
<html>
    <script>        
        var idRetorno = '<bean:write name="Form" property="idRetorno"/>';        
        if(idRetorno=='S'){
            alert('<bean:write name="Form" property="mensagem"/>');

            //Fim animação
            if( top.frameApp.dvAnimarAguarde != null ){
                top.frameApp.stopAnimation();
            }
            parent.parent.CarregaAba('bt08');
        }
        else{
        
            //Fim animação
            if( top.frameApp.dvAnimarAguarde != null ){
                top.frameApp.stopAnimation();
            }
        }
    </script>    
</html>
