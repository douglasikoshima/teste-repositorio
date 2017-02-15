<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<bean:define id="Form" name="atendimentoForm" scope="request" />

<link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
<script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/calendar.js"></script>
<script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/controleEventos.js"></script>
<script>
    function showPesquisa(){
        objDiv = document.getElementById('idDivPesquisa');
        objDiv.style.display = '';
    }

    function showUra(){
        objDiv = document.getElementById('idDivPesquisa');
        objDiv.style.display = 'none';
    }
</script>
<acesso:controlHiddenItem nomeIdentificador="wor_ps_verpagina">
    <form action="pesquisaSatisfacaoGravar.do" method="post" id="atendimentoForm" name="atendimentoForm">
        <html:hidden name="Form" property="idAtendimento"/>
        <logic:greaterThan name="Form" property="tamanho" value="0"> <!--name="Form" property="tamanho" value="0"-->
            <table height="100%" width="100%" cellpadding="0" cellspacing="5" style="border:1px solid #adadad;">
                <tr>
                    <td>
                        <div id="idDivPesquisa">
                            <iframe src="pesquisaSatisfacaoApresentacao.do" width="100%" height="100%" frameborder="0"></iframe>
                        </div>
                    </td>
                </tr>
            </table>
        </logic:greaterThan><!--notEqual-->
        <logic:equal name="Form" property="tamanho" value="0">
        <table border="0" width="100%">
            <tr>
                <td align="center">
                    <font size=3><b>Não existe pesquisa cadastrada para o atendimento!</b></font>
                </td>
            </tr>
        </table>
        <br>
        <br>
        <table border="0" width="100%">
            <tr>
                <td align="center">
                    <vivo:botao id="btFechar" width="100px" height="15px" value="Fechar" styleClass="btTemplate" onclick="fechar();"/>
                </td>
            </tr>
        </table>
        </logic:equal>
        <script>
            <!--
            document.body.style.backgroundColor = '#ededed';
            function fechar() {
                //Fim animação
                if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
                extra_vars="";
                filtro = window.top.frameCTI.filtro;
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
                    "&login=" +filtro.massa.login+
                    "&dtSuspeitoInicio="+filtro.massa.dtSuspeitoInicio+
                    "&dtSuspeitoFim="+filtro.massa.dtSuspeitoFim+
                    "&textoContato=" +filtro.massa.textoContato+
                    "&idContato="    +filtro.massa.idContato;
                }
            <logic:notEmpty name="Form" property="fila">
                parent.window.location.href="<bean:write name="Form" property="fila"/>?voltar=1"+extra_vars;
            </logic:notEmpty>
            <logic:empty name="Form" property="fila">
                parent.document.forms[0].target = "";
                parent.document.forms[0].submit();
            </logic:empty>
            }
            //Fim animação
        <logic:equal name="Form" property="tamanho" value="0">
            if(top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
        </logic:equal>
            -->
        </script>
    </form>
</acesso:controlHiddenItem>