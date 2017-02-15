<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
   
<bean:define id="AtendimentoVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="registrarContatoForm.atendimentoVO" type="br.com.vivo.fo.workflow.vo.AtendimentoVODocument.AtendimentoVO"/>
<bean:define id="Fila"          name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="registrarContatoForm"/>

<html>
    <head>
        <title>Gestão de Processos e Workflow - Informação Codigo Processo</title>
        <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
    </head>
    <body>    
        <logic:notEqual name="AtendimentoVO" property="idAtendimento" value="0">
            <p><center>
                Número do Processo:<br>
                <%--<bean:write name="AtendimentoVO" property="idAtendimento" format="##########"/>--%>
                <bean:write name="AtendimentoVO" property="nrProtocolo" format="##########"/><br><br>                
                <vivo:botao id="btAplicar" width="60px" height="10px" value="Fechar" styleClass="btTemplate" onclick="fechar();"/>                
            </p>
        </logic:notEqual>
        <logic:equal name="AtendimentoVO" property="idAtendimento" value="0">
                <center>Processo não pode ser aberto, tente novamente.
                <br>                
                    <vivo:botao id="btFechar" width="60px" height="10px" value="Fechar" styleClass="btTemplate" onclick="arvore();"/>                                   
                </center>
        </logic:equal>
    <input type="hidden" name="descricao" id="descricao" value="<%=AtendimentoVO.getArvoreAtendimentoVO().getDescricaoCompleta().toString()%>"></input>    
    <script>
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
        parent.ifrmAbas.listarProcessos(<bean:write name="AtendimentoVO" property="idAtendimento" format="######"/>, document.all['descricao'].value, '<bean:write name="AtendimentoVO" property="nrProtocolo" format="######"/>');
        
        parent.document.all['abaPCorrentes'].click();
        parent.ifrmNumProc.document.location.replace("about:blank");
        parent.ifrmAtendimento.document.location.replace("about:blank");
    }   
            
    function arvore() {
        //fecha a apresentação
        parent.dvNumProc.style.display = 'none';
        if(parent.dvAtendimento)
            parent.dvAtendimento.style.display = 'none';
        else
            parent.$('divPopupTI').style.display = 'none';
        parent.ifrmArvore.iniciaAtendimento('<bean:write name="Fila" property="idContato"/>','', '<bean:write name="Fila" property="inResponsavelAbertura"/>');
    }

    updateProtocolo = function() {
		window.top.frameApp.updateProtocolo('<bean:write name="Fila" property="nrProtocolo" />');
    }

    </script>
   
    </body>

</html>

<vivo:alert scope="request" atributo="msgProtocolo" afterFunction="updateProtocolo()" />

<script>

    //Fim animação
    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();

</script>  
