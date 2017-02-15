<!--
Módulo.....: Gestão de Sistemas
Caso de Uso: Configurar Grupo de Usuário com Regras de Encaminhamento
Versão.....: $Author: a5112272 $ - $Revision: 1.2 $ - $Date: 2011/05/25 14:01:46 $
-->
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<acesso:controlInitEnv/>

<bean:define id="Form"         name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="usuarioEncaminhamentoForm" />

<html>
    <head>
        <title></title>
    </head>
    <body>
    <acesso:controlHiddenItem nomeIdentificador="adm_rrenc_verpagina">
        <script>
        var idRetorno = '<bean:write name="Form" property="idRetorno"/>';

        if( idRetorno=='S' ){
            if( window.confirm('<bean:write name="Form" property="mensagem"/>') ){
                parent.document.forms["usuarioEncaminhamentoForm"].target = "iframe";
                parent.document.forms["usuarioEncaminhamentoForm"].action = "AplicarRegrasEncaminhamento.do?valida=0";
                parent.document.forms["usuarioEncaminhamentoForm"].submit();
            }else{
                   if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
            }
        }

        if( idRetorno=='N' ){
            alert('<bean:write name="Form" property="mensagem"/>');
            if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
            parent.exibicao(0);
            parent.DoResizeHeaderTableVivo();
        }
        </script>
    </acesso:controlHiddenItem>
    </body>
</html>