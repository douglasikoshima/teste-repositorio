<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm"/>
<bean:define id="wFAcaoRetornoVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm.WFAcaoRetornoVO"/>
<html>
<head>
    <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
    <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/controleEventos.js"></script>
    <script language="JavaScript">
        <!--
            function voltarAdquirir(){
                top.frameApp.location.href="/FrontOfficeWeb/extworkflw/AtendimentoInBoxResposta/begin.do?retornoAdquirir=1";
            }

            function voltarFila(){
                top.frameApp.location.href="/FrontOfficeWeb/extworkflw/AtendimentoFila/beginResp.do?voltar=1";
            }

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
                } else if (filtro.telaOrigem==3) {

                    extra_vars =
                    "&login="+filtro.massa.login+
                    "&dtSuspeitoInicio="+filtro.massa.dtSuspeitoInicio+
                    "&dtSuspeitoFim="+filtro.massa.dtSuspeitoFim+
                    "&textoContato="+filtro.massa.textoContato+
                    "&idContato="+filtro.massa.idContato;

                } else if (filtro.telaOrigem == 'FILA_MEU_CLIENTE') {

                    extra_vars = '&' + filtro.queryString;

                }

                <logic:match value="N" name="wFAcaoRetornoVO" property="acaoExecucao">
                    <logic:empty name="form" property="fila">
                        parent.submitPesquisar();
                    </logic:empty>
                    <logic:notEmpty name="form" property="fila">
                        parent.document.forms[0].action = "<bean:write name="form" property="fila"/>";
                        //parent.document.forms[0].target = "frameApp";
                        //parent.document.forms[0].submit();
                        parent.window.location.href=parent.document.forms[0].action+'?voltar=1'+extra_vars;
                   </logic:notEmpty>
                </logic:match>
                <logic:match value="S" name="wFAcaoRetornoVO" property="acaoExecucao">
                    parent.document.forms[0].method = "POST";
                    <logic:notEmpty name="wFAcaoRetornoVO" property="urlDestino">
                    //Retorno do questionario
                        <logic:equal value="quest" name="wFAcaoRetornoVO" property="urlDestino">
                            <logic:empty name="form" property="fila">
                                top.frameApp.location.href='/FrontOfficeWeb/extworkflw/AtendimentoFila/begin.do';
                                //Fim animação
                                if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
                                return false;
                            </logic:empty>
                            <logic:notEmpty name="form" property="fila">
                                top.frameApp.location.href='<bean:write name="form" property="fila"/>'+'?voltar=1'+extra_vars;
                                //Fim animação
                                if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
                                return false;
                           </logic:notEmpty>
                        </logic:equal>
                        <logic:equal value="1" name="wFAcaoRetornoVO" property="urlDestino">
                            parent.document.forms[0].action = "<bean:write name="form" property="fila"/>";
                            <logic:notEmpty name="form" property="fila">
                                //parent.document.forms[0].target = "frameApp";
                                //parent.document.forms[0].submit();
                                parent.window.location.href = parent.document.forms[0].action+'?voltar=1'+extra_vars;
                            </logic:notEmpty>
                            <logic:empty name="form" property="fila">
                                if(top.frameApp.dvAtendimento)
                                    top.frameApp.dvAtendimento.style.display = 'none';
                                else
                                    top.frameApp.$('divPopupTI').style.display = 'none';
                                return false;
                            </logic:empty>
                        </logic:equal>
                        <logic:notEqual value="1" name="wFAcaoRetornoVO" property="urlDestino">
                            <logic:equal value="3" name="wFAcaoRetornoVO" property="urlDestino">
                                    <logic:empty name="form" property="fila">
                                        parent.document.forms[0].target = "";
                                        parent.document.forms[0].submit();
                                    </logic:empty>
                                    <logic:notEmpty name="form" property="fila">
                                        parent.window.location.href = '/FrontOfficeWeb/extworkflw/AtendimentoDetalhe/begin.do?idAtendimento='+document.all["idAtendimento"].value+'&inMenu=S&fila=<bean:write name="form" property="fila"/>&inCRI='+document.all["inCRI"].value;
                                    </logic:notEmpty>
                            </logic:equal>
                            <logic:notEqual value="3" name="wFAcaoRetornoVO" property="urlDestino">
                                <logic:equal value="2" name="wFAcaoRetornoVO" property="urlDestino">
                                    //parent.document.forms[0].action = "/FrontOfficeWeb/extworkflw/AtendimentoWorkflow/atendimentoWorkflowVoltar.do";
                                    parent.document.forms[0].action = "<bean:write name="form" property="fila"/>?voltar=1&idAtendimento="+document.all["idAtendimento"].value+extra_vars;
                                    <logic:notEmpty name="form" property="fila">
                                        parent.document.forms[0].target = "frameApp";
                                        parent.document.forms[0].submit();
                                    </logic:notEmpty>
                                    <logic:empty name="form" property="fila">
                                        top.frameApp.document.getElementById('ifrmAtendimento').src = '/FrontOfficeWeb/extworkflw/AtendimentoDetalhe/begin.do?idAtendimento='+document.all["idAtendimento"].value+'&inMenu=N&inCRI='+document.all["inCRI"].value'+'&fila=';
                                    </logic:empty>
                                </logic:equal>
                                <logic:notEqual value="2" name="wFAcaoRetornoVO" property="urlDestino">
                                    <%
                                    String oper = (String) request.getAttribute("OPER");
                                    if("RA".equals(oper)){%>
                                    extra_vars += "&OPER=RA";
                                    <%}%>
                                    parent.document.forms[0].action = "<bean:write name="wFAcaoRetornoVO" property="urlDestino"/>?voltar=1&fila=<bean:write name="form" property="fila"/>&idAtendimento="+document.all["idAtendimento"].value+extra_vars;
                                    parent.document.forms[0].target = "iframeRetornoOperacao";
                                    parent.document.forms[0].submit();
                                </logic:notEqual>
                            </logic:notEqual>
                        </logic:notEqual>
                    </logic:notEmpty>
                    <logic:empty name="wFAcaoRetornoVO" property="urlDestino">
                    <%if(request.getAttribute("inRespCliente")!=null && "1".equals(request.getAttribute("inRespCliente").toString())){%>
                        parent.document.forms[0].action = "/FrontOfficeWeb/extworkflw/AtendimentoInBoxResposta/begin.do";
                        parent.document.forms[0].target = "frameApp";
                        <%request.removeAttribute("inRespCliente"); %>
                    <%}else{%>
                        parent.document.forms[0].action = "/FrontOfficeWeb/extworkflw/AtendimentoWorkflow/testesBegin.do";
                        parent.document.forms[0].target = "ifrmAbas";
                    <%}%>
                        parent.document.forms[0].submit();
                    </logic:empty>
                    //parent.document.forms[0].submit();
                </logic:match>
                //Fim animação
                if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
            }
        -->
    </script>
</head>
<body>
    <html:hidden name="form" property="idAtendimento"/>
    <html:hidden name="form" property="inCRI"/>
    <br>
    <br>
    <table border="0" width="100%">
        <tr>
            <td align="center">
                <font size=3><b>
                <logic:match value="S" name="wFAcaoRetornoVO" property="acaoExecucao">Operação Concluída com Sucesso!</logic:match>
                <logic:match value="N" name="wFAcaoRetornoVO" property="acaoExecucao">Operação não Concluída!</logic:match>
                </b></font>
            </td>
        </tr>
        <tr>
            <td align="center">
                <b>Mensagem:</b><bean:write name="wFAcaoRetornoVO" property="mensagem"/>
            </td>
        </tr>
    </table>
    <br>
    <br>
    <table border="0" width="100%">
        <tr>
            <td align="center">           
                <%if("33".equals(request.getParameter("idAtividade"))){%>
                    <vivo:botao id="btFechar" width="100px" height="15px" value="Fechar" styleClass="btTemplate" onclick="voltarAdquirir();"/>

                <%}else if("/FrontOfficeWeb/extworkflw/AtendimentoFila/beginResp.do".equals(request.getParameter("fila"))){%>
                    <vivo:botao id="btFechar" width="100px" height="15px" value="Fechar" styleClass="btTemplate" onclick="voltarFila();"/>

                <%}else{%>
                    <vivo:botao id="btFechar" width="100px" height="15px" value="Fechar" styleClass="btTemplate" onclick="fechar();"/>
                <%}%>            
            </td>
        </tr>
    </table>
<script language="javascript">
    <!--
    //Fim animação
    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
    -->
</script>
</body>
</html>