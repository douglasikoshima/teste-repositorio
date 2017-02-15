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
<bean:define id="AtendimentoVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="registrarContatoForm.atendimentoVO" />

<html>
    <head>
        <script language="JavaScript">            
            function fechar() {
                extra_vars="";
                filtro = top.frameCTI.filtro;
                if (filtro.telaOrigem==1) {
                    if (filtro.psqAv!=null) {
                        for (i=0; i<filtro.psqAv.length; i++) {
                            extra_vars+='&psqIdCampo='+filtro.psqAv[i].idCampo;
                            extra_vars+='&psqNmCampo='+filtro.psqAv[i].nmCampo;
                            extra_vars+='&psqTpComparacao='+filtro.psqAv[i].tpComparacao;
                            extra_vars+='&psqDsComparacao='+filtro.psqAv[i].dsComparacao;
                            extra_vars+='&psqValor='+filtro.psqAv[i].valor;
                            extra_vars+='&psqIdFormularioCampoValor='+filtro.psqAv[i].idFormularioCampoValor;
                        }
                    }
                }
                if (filtro.telaOrigem==3) {
                    extra_vars = 
                    "&login="+filtro.massa.login+
                    "&dtSuspeitoInicio="+filtro.massa.dtSuspeitoInicio+
                    "&dtSuspeitoFim="+filtro.massa.dtSuspeitoFim+
                    "&textoContato="+filtro.massa.textoContato+
                    "&idContato="+filtro.massa.idContato;        
                }
                <logic:notEmpty name="Form" property="fila">
                    <logic:equal value="1" name="Form" property="fila">
                        if(top.frameApp.dvAtendimento)
                            top.frameApp.dvAtendimento.style.display = 'none';
                        else
                            top.frameApp.$('divPopupTI').style.display = 'none';
                        return false;
                    </logic:equal>
                    top.frameApp.location.href='<bean:write name="Form" property="fila"/>?voltar=1'+extra_vars;
                    //top.frameApp.location.href='registrarContatoDone.do';
                    return;
                </logic:notEmpty>   
                parent.fechaInsistencia();
            }
        </script>
            
        <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
    </head>
    <body>    
            <center>Insistência do Contato <bean:write name="AtendimentoVO" property="arvoreAtendimentoVO.descricaoCompleta" />
            registrada no processo <bean:write name="AtendimentoVO" property="atendimentoSituacaoVO.nrProtocolo" format="######"/> .
            <br>            
                <vivo:botao id="btFechar" width="60px" height="10px" value="Fechar" styleClass="btTemplate" onclick="fechar();"/>                                
            </center>    
    </body>
</html>
<script>
    //Fim animação
    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
</script>  
