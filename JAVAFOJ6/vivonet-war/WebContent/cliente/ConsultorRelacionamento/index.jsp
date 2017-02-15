<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<acesso:controlInitEnv/>

<bean:define id="Form"
             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"
             property="consultorRelacionamentoForm"
             type="cliente.ConsultorRelacionamento.ConsultorRelacionamentoController.ConsultorRelacionamentoForm" />

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
<netui-template:setAttribute name="title" value="FrontOffice - Administração" />
<netui-template:setAttribute name="modulo" value="Consultor de Relacionamento" />
<netui-template:section name="headerSection">
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/calendar.js"></script>
    <script type="text/javascript" language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js" ></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/validacao.js"></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
    <script type="text/javascript" language="javascript">

         CarregaAba = function(nome) {
                if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();
                var dyniframe = document.getElementById("fraSubAbas");
                var pagina = "";
                if(nome=="btx01") pagina = "loadConsultorCliente.do";
                if(nome=="btx02") pagina = "loadClienteConsultor.do";
                dyniframe.src = pagina;
            }

          onload = function() {
                CarregaAba("btx01");
            }

    </script>
</netui-template:section>
<netui-template:section name="bodySection">
    <jsp:include page="/resources/menus/MenuPrincipal.jsp"/>
    <vivo:sessao id="bodyRelatoriosTracking" height="470" width="790" label="Administração" operacoes="Manutenção de Consultor de Relacionamento" scroll="N">
    <body style="margin-left:5px; margin-right:5px; margin-top:5px;">
    <form>
        <div><img id="imgRefDown" src="<%=request.getContextPath()%>/resources/images/transp.gif" width="1" height="2"></div>
        <table width="765" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td>
                    <vivo:abaGrupo id="btxAba" width="777" height="10px" styleClass="abatexto">
                        <vivo:abaItem id="btx01" onclick="abaSelected(btxAba, btx01);CarregaAba(this.id);" value="Associar Consultor a Cliente" select="S"/>
                        <vivo:abaItem id="btx02" onclick="abaSelected(btxAba, btx02);CarregaAba(this.id);" value="Associar Cliente a Consultor"/>
                    </vivo:abaGrupo>
                </td>
            </tr>
        </table>
        <table width="777" class="tbl_bgBlue" border="1" cellpadding="1" cellspacing="1">
            <tr>
                <td colspan="4" valign="top">
                    <iframe name="fraSubAbas" id="fraSubAbas" width="100%" height="430" frameborder="0" src="" scrolling="no"></iframe>
                </td>
            </tr>
        </table>
        <div><img id="imgRefDown" src="<%=request.getContextPath()%>/resources/images/transp.gif" width="1" height="5"></div>
    </vivo:sessao>
</netui-template:section>
</netui-template:template>