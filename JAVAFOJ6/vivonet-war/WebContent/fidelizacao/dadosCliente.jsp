<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<netui-template:template templatePage="/resources/jsp/templateRetencao.jsp">
    <netui-template:section name="headerSection">
        <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js" ></script>
        <script language="JavaScript">
            <!--
            function agendar(param){
                if(!param){
                    param = "";
                }
                dvNrProcesso.style.display = '';
                //dvAgenda.style.display = '';
                document.forms["frmAgenda"].target = "ifrmNrProcesso";
                document.forms["frmAgenda"].action = "getDadosAgendarContato.do"+param;
                parent.mostrar_div();
                document.forms["frmAgenda"].submit();
            }

            function agendarMatriz(){
                dvNrProcesso.style.display = '';
                document.forms["frmAgenda"].target = "ifrmNrProcesso";
                document.forms["frmAgenda"].action = "ConsultaMatrizOferta/addForm.do?acao=agendar";
                parent.mostrar_div();
                document.forms["frmAgenda"].submit();
            }

            function voltar(){
                dvNrProcesso.style.display = 'none';
            }

            function getSaldoPontos(){
                parent.mostrar_div();
                dvSaldoPontos.style.display= '';
                document.forms["frmSaldoPontos"].submit();
            }
            -->
        </script>
        <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
        <!--
            parent.oculta_div();
        -->
        </SCRIPT>
    </netui-template:section>
    <netui-template:section name="bodySection">
        <acesso:controlHiddenItem nomeIdentificador="fid_dcli_verpagina">
            <script>
                <logic:notEmpty name="msgErro">
                    alert("<bean:write name="msgErro"/>");
                </logic:notEmpty>
                window.name = "frmRetencao";
            </script>
            <bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="dadosClienteForm"/>
            <table style="border:1px solid #adadad; background-color:#E7E7E7;" width="762" border="0">
                <tr>
                    <td><b>Cliente:</b>&nbsp;<bean:write name="Form" property="nomeCliente"/></td>
                    <td><b>Tipo de Cliente:</b>&nbsp;<bean:write name="Form" property="idTipoCliente"/></td>
                    <td><b>Segmentação:</b>&nbsp;<bean:write name="Form" property="segmentacao"/></td>
                </tr>
                <tr>
                    <td align="left"><b>Linha:</b>&nbsp;<bean:write name="Form" property="numeroLinha"/></td>
                    <td><b>Carteirização:</b>&nbsp;<bean:write name="Form" property="carteirizacao"/></td>
                    <td align="left" colspan="3">
                        <b>Saldo Total de Pontos:</b>&nbsp;
                        <img onClick="getSaldoPontos()" src="/FrontOfficeWeb/resources/images/bt_icon_pesquisar_lista.gif" style="border:none;cursor:hand;" vspace="0"/>
                    </td>
                </tr>
            </table>
            <table width="100%"  cellpadding="1" class="BorderTable1" border="0">
                <tr>
                    <td valign="top">
                        <iframe id="fquestionario" name="frmQuestionario" width="762" height="325" frameborder="0" scrolling="no" src="getLinhas.do"></iframe>
                    </td>
                </tr>
            </table>
            <div style="display:none">
                <form name="frmSaldoPontos" method="post" target="ifrmSaldoPontos" action="getSaldoPontos.do" onsubmit="return false;"></form>
                <form name="frmAgenda" method="post" onsubmit="return false;"></form>
            </div>
            <vivo:quadroFlutuante id="dvNrProcesso" idIframe="ifrmNrProcesso"  height="350" width="765" spacesTop="0" spacesLeft="0" display="none" url="<%=request.getContextPath()%>" label=""/>
            <vivo:quadroFlutuante id="dvSaldoPontos" idIframe="ifrmSaldoPontos"  height="50" width="220" spacesTop="10" spacesLeft="300" display="none" url="<%=request.getContextPath()%>" label=""/>
        </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>