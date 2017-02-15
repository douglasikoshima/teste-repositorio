<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
   

<bean:define id="Form"          name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="registrarContatoForm" />
<bean:define id="AtendimentoVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="registrarContatoForm.atendimentoVO" type="br.com.vivo.fo.workflow.vo.AtendimentoVODocument.AtendimentoVO" />
<bean:define id="Fila"          name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="registrarContatoForm"/>

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
                    <logic:equal value="1" name="Fila" property="fila">
                        if(top.frameApp.dvAtendimento)
                            top.frameApp.dvAtendimento.style.display = 'none';
                        else
                            top.frameApp.$('divPopupTI').style.display = 'none';
                        return false;
                    </logic:equal>
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

                var idC = '<bean:write name="Fila" property="idContato"/>';
                if ((idC.toLowerCase() == 'null'
                    || idC.toLowerCase() == 'undefined'
                    || idC == '') && top.frameApp.idContato != undefined) {
                    idC = top.frameApp.idContato;
                }

                parent.ifrmArvore.iniciaAtendimento(idC,'', '<bean:write name="Fila" property="inResponsavelAbertura"/>');
            }

        </script>
        <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
    </head>
    <body>
    
        <logic:notEqual name="AtendimentoVO" property="idAtendimento" value="0">
            <input type="hidden" name="descricao" id="descricao" value="<%=AtendimentoVO.getArvoreAtendimentoVO().getDescricaoCompleta().toString()%>"></input>    
                <center>O processo corrente <bean:write name="AtendimentoVO" property="nrProtocolo" format="######"/>
                 esta relacionado com o processo <bean:write name="Form" property="nrProtocoloSituacao" format="######"/> na situação <bean:write name="Form" property="nmTipo" />.
                <br>
               
                    <vivo:botao id="btFechar" width="60px" height="10px" value="Fechar" styleClass="btTemplate" onclick="fechar();"/>                    
                
                </center>
        </logic:notEqual>
        <logic:equal name="AtendimentoVO" property="idAtendimento" value="0">
                <center>Processo não pode ser aberto, tente novamente.
                <br>
               
                    <vivo:botao id="btFechar" width="60px" height="10px" value="Fechar" styleClass="btTemplate" onclick="arvore();"/>                    
               
                </center>
        </logic:equal>

    </body>
</html>

<script>
    //Fim animação
    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();

</script>