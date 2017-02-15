<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="prazoCRIForm" />

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
        }
        else{
        
            //Fim animação
            if( top.frameApp.dvAnimarAguarde != null ){
                top.frameApp.stopAnimation();
            }
        }
    </script>    
</html>
