<%@page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.cliente.vo.ParametrosVODocument.ParametrosVO"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<acesso:controlInitEnv/>

<bean:define id="ConsultaAdimplenciaForm" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="consultaAdimplenciaForm" />
<netui-template:template templatePage="/resources/jsp/templateRetencao.jsp">
    <netui-template:section name="headerSection">
        <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
        <script language="javascript">
            fechar = function(){
                top.frameApp.mostrar_div();
                top.frameApp.$("idAnime").style.display = "block";
                localEx = window.top.frameApp;
                localEx.abaSelected(localEx.btAba, localEx.bt06);
                localEx.CarregaAba('bt06');
                top.frameApp.$("idAnime").style.display = "none";
            }
        </script>
        <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
            <!--
            top.frameApp.oculta_div();
            top.frameApp.$("idAnime").style.display = "none";
            -->
        </SCRIPT>
    </netui-template:section>
    <netui-template:section name="bodySection">

    <!--acesso:controlHiddenItem nomeIdentificador="fid_me_verpagina"-->

    <vivo:body idTable="tbMain" idDiv="dvMain" height="291" width="750">
    <vivo:alert atributo="msgErro" scope="request" />
    <% if (null!=request.getAttribute("nrProcesso")){ %>

    <table width="100%" height="250">
        <tr>
            <td align="center" valign="middle" style="font-size:11px;">
                Processo <b><bean:write name="nrProcesso" /></b> encaminhado para análise de BKO.<br>

                <br><br><br><br>

                <img action="begin.do"
                	 src="/FrontOfficeWeb/resources/images/bt_fechar_nrml.gif"
                	 border="0"
                	 onClick="fechar()"/>
            </td>
        </tr>
    </table>

    <% } else if (null!=request.getAttribute("nrLinha")) { %>

    <script language="javascript">
        finalizaRetencaoRestricao = function(){
            top.frameApp.mostrar_div();
            top.frameApp.$("idAnime").style.display = "block";
            var nrLinha = '<bean:write name="nrLinha" />';
            //document.forms[0].target = "frameApp";
            //document.forms[0].action = "<%=request.getContextPath()%>/cliente/TelaInicial/begin.do?nrLinha="+nrLinha;
            //document.forms[0].submit();
            //top.frameApp.location.href = "<%=request.getContextPath()%>/cliente/TelaInicial/begin.do?nrLinha="+nrLinha+"&updateCombos=TRUE";

            localEx = window.top.frameApp;
            //localEx.resizeFrameDetalhe();
            localEx.abaSelected(localEx.btAba, localEx.bt06);
            localEx.CarregaAba('bt06');
            top.frameApp.$("idAnime").style.display = "none";
        }
    </script>

    <% } else { %>
    <script language="javascript">
        <%
        ParametrosVO parametrosVO = (ParametrosVO)request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);
        String idUsuario = parametrosVO.getIdUsuario();
        %>
        fecharAction = function() {
            top.frameApp.mostrar_div();
            parent.document.getElementById('dvAnalisesBKO').style.display='none';
            window.top.frameApp.location.href = '/FrontOfficeWeb/workflow/AtendimentoInBox/begin.do?idUsuario=<%=idUsuario%>';
        }
    </script>
    <table width="100%" height="100%" align="center">
        <tr>
            <td align="center" valign="middle" style="font-size:11px;">
                <bean:write name="ConsultaAdimplenciaForm" property="statusAvaliacao" />
                <br><br><br>
                <img class="button"
                	 action="begin.do"
                	 src="/FrontOfficeWeb/resources/images/bt_fechar_nrml.gif"
                	 border="0"
                	 onClick="fecharAction(); return false;" />
            </td>
        </tr>
    </table>
    <% } %>
    <vivo:alert atributo="msgRestricao" scope="request" afterFunction="finalizaRetencaoRestricao()"  />

    </vivo:body >
    <!--/acesso:controlHiddenItem-->
    </netui-template:section>
</netui-template:template>


