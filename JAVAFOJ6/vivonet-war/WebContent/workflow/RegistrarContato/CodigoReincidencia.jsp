<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
   
<acesso:controlInitEnv/>
<bean:define id="Form"                name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="registrarContatoForm" />
<bean:define id="AtendimentoVO"       name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="registrarContatoForm.atendimentoVO" />

<html>
    <head>
        <script language="JavaScript">            
            function fechar() {
                //fecha a apresentação
                parent.dvNumProc.style.display = 'none';
                if(parent.dvAtendimento)
                    parent.dvAtendimento.style.display = 'none';
                else
                    parent.$('divPopupTI').style.display = 'none';
                
                <logic:notEmpty name="Fila" property="fila">
                    parent.parent.voltar();
                    return false;
                </logic:notEmpty>     
                //Atualiza a lista de processos correntes
                parent.ifrmAbas.listarProcessos('<bean:write name="AtendimentoVO" property="idAtendimento" format="######"/>', document.all['descricao'].value, '<bean:write name="AtendimentoVO" property="nrProtocolo" format="######"/>');
                
                parent.document.all['abaPCorrentes'].click();
                parent.ifrmNumProc.document.location.replace("about:blank");
                parent.ifrmAtendimento.document.location.replace("about:blank");
            }

			updateProtocolo = function() {
				window.top.frameApp.updateProtocolo('<bean:write name="Fila" property="nrProtocolo" />');
		    }

        </script>
            
        <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
    </head>
    <body>
    <acesso:controlHiddenItem nomeIdentificador="wor_crecia_verpagina">
            <center>O processo corrente <bean:write name="AtendimentoVO" property="nrProtocolo" format="######"/>
             esta relacionado com o processo <bean:write name="AtendimentoSituacao" property="fechadoReincidencia" format="######"/> na situação de Reincidencia.
            <br>
            <acesso:controlHiddenItem nomeIdentificador="wor_crecia_fechar">
                <vivo:botao id="btFechar" width="60px" height="10px" value="Fechar" styleClass="btTemplate" onclick="fechar();"/>                    
            </acesso:controlHiddenItem>
            </center>
   
    </acesso:controlHiddenItem>

	<vivo:alert scope="request" atributo="msgProtocolo" afterFunction="updateProtocolo()" />

    </body>
</html>

<script>
    //Fim animação
    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
</script>