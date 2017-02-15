<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<bean:define id="Form"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="consultaMatrizOfertaForm" />
<bean:define id="dadosClienteForm"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="dadosClienteForm" />
<bean:define id="listaPlanosVO"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaPlanosVO.planoVOArray" />

<acesso:controlInitEnv/>
<netui-template:template templatePage="/resources/jsp/templateRetencao.jsp">
    <netui-template:section name="headerSection">
        <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
        <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js" ></script>
        <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/fidelizacao-planos.js" ></script>
        <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/xtooltip.js" ></script>
         <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
                <!--
                    parent.parent.oculta_div();
                -->
         </SCRIPT>
    </netui-template:section>
    <netui-template:section name="bodySection">
<div style="width:760px;height:325px;overflow:auto;margin-bottom:1px solid #adadad;">
<jsp:include page="informacoesCliente.jsp"/>
<acesso:controlInitEnv/>
    <acesso:controlHiddenItem nomeIdentificador="fid_ap_verpagina">
    <vivo:quadro id="qdMain" height="247" width="760" label="Adequação de Planos" scroll="no">
    <form id="frmSelecao" name="frmSelecao" method="post"/>
		<input type="hidden" name="CODIGO" value="">
    </form>
    <table width="100%">
    <form action="reterAdequacaoPlano.do" name="consultaMatrizOfertaForm" method="post">
    <input type="hidden" name="tipoEncerramento" value="3">
        <tr>
            <td width="15%">Plano Atual:</td>
            <td align="left" style="padding-left:5px">
            <html:text name="dadosClienteForm" property="detalheLinha.plano" readonly="true" styleClass="textfield" size="49" />
            </td>
        </tr>
        <tr>
            <td>Novo Plano:</td>
            <td align="left" style="padding-left:8px">
            <html:select name="Form" property="planoSelecionado" style="width:268px" styleClass="SELECT" size="1" onmouseover="ativarToolTip(this);" >
					<option value="0">Selecione</option>
                    <html:options collection="listaPlanosVO" property="id" labelProperty="descricao" />
            </html:select>
            			</td>
        </tr>
        <acesso:controlHiddenItem nomeIdentificador="fid_ap_excecao">
        <tr>
            <td>Oferta de Exceção:</td>
            <td><html:checkbox name="Form" property="inExcecao" style="border:none;background-color:#E3ECF4;"></html:checkbox></td>
        </tr>
        </acesso:controlHiddenItem>
    </table>
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1"></div>
        <table width="95%" cellpadding="0" cellspacing="0" height="165">
            <tr>
                <td valign="bottom">
                	<img class="button"
                		 src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif"
                		 border="0"
                		 onClick="parent.parent.mostrar_div();document.forms[0].action='voltarOfertas.do';document.forms[0].submit();" />
                </td>
                <td align="center" valign="bottom">
                <acesso:controlHiddenItem nomeIdentificador="fid_ap_manualeletronico">
                    <img class="button"
                		 src="/FrontOfficeWeb/resources/images/bt_manelet_nrml.gif"
                		 border="0"
                		 onClick="window.open='http://intranet.vivo-sp.com.br/manual/retencao/index.htm';" />
				</acesso:controlHiddenItem>
                <acesso:controlHiddenItem nomeIdentificador="fid_ap_agendar">
                    <img src="/FrontOfficeWeb/resources/images/bt_agendar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_agendar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_agendar_over.gif'" style="border:none;cursor:hand;" border="0" onClick="agendar();" />
				</acesso:controlHiddenItem>
                <acesso:controlHiddenItem nomeIdentificador="fid_ap_vaipensar">
                    <img class="button"
                		 src="/FrontOfficeWeb/resources/images/bt_vaipensar_nrml.gif"
                		 border="0"
                		 onClick="cancela('vaipensar')" />
				</acesso:controlHiddenItem>
                </td>
                <td align="right" valign="bottom">
                <acesso:controlHiddenItem nomeIdentificador="fid_ap_reter">
                     <img src="/FrontOfficeWeb/resources/images/bt_reter_nrml.gif" border="0"  onClick="enviarForm(this);return false;" style="border:none;cursor:hand;" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_reter_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_reter_over.gif'" />
				</acesso:controlHiddenItem>
                </td>
            </tr>
    </form>
        </table>
    <vivo:quadroFlutuante id="dvMsgPontos" idIframe="ifrmMensagem" height="50" width="300" spacesTop="50" spacesLeft="50" display="none" url="<%=request.getContextPath()%>" label=""/>
    </vivo:quadro>

</acesso:controlHiddenItem>
</div>
    </netui-template:section>
</netui-template:template>
