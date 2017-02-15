<!--
Módulo.....: Gestão de Sistemas
Caso de Uso: Configurar Grupo de Usuário com Seqüencia Workflow
Versão.....: $Author: a5112272 $ - $Revision: 1.2 $ - $Date: 2011/05/25 14:01:46 $
-->
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<bean:define id="Form"         name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="associacaoGrupoForm" />

<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
<html>
    <script>        
        var idRetorno = <bean:write name="Form" property="idRetorno"/>;
        parent.document.forms[0].persiste.value='0';
        if(idRetorno==1){
            if (confirm("<bean:write name="Form" property="mensagem"/>\nDeseja atualizar as variáveis?")) {   
                //Liga animação
                if( top.frameApp.dvAnimarAguarde != null ){
                    top.frameApp.startAnimation();
                }  
                parent.document.forms["0"].persiste.value='1';
                parent.salvar();
            }
            else{
                //Fim animação
                if( top.frameApp.dvAnimarAguarde != null ){
                    top.frameApp.stopAnimation();
                }
            }
        }
        else
            if(idRetorno==0){
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
