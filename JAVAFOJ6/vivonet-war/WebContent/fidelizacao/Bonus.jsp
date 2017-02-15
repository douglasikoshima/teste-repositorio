<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv />
<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="cadastrarBonusForm" />
<bean:define id="listaBonusVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"
    property="listaBonusVO.bonusVOArray" />
<netui-template:template templatePage="/resources/jsp/templateRetencao.jsp">
    <netui-template:section name="headerSection">
        <link rel="stylesheet" type="text/css" href="/FrontOfficeWeb/resources/css/calendar.css">
        <link href="/FrontOfficeWeb/resources/css/frontoffice.css" type="text/css" rel="stylesheet" />
        <script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/calendar.js"></script>
        <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/frameweb.js"></script>
        <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/vivoval.js"></script>
        <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/rfidelizacao.js" charset="ISO-8859-1"></script>
        <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/fidelizacao-bonus.js" charset="ISO-8859-1"></script>
        <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/xtooltip.js"></script>
        <script language="javascript">
	eval(zsoPP);
        </script>
    </netui-template:section>
    <netui-template:section name="bodySection">
        <acesso:controlHiddenItem nomeIdentificador="fid_b_verpagina">
            <div style="width: 764px; height: 331px; overflow: auto; margin-bottom: 1px solid #adadad;"><jsp:include
                page="informacoesCliente.jsp" /> <vivo:quadro id="qdMain" height="248" width="760" label="Bônus"
                scroll="no">
                <form action="reterBonus.do" id="bonusForm" name="consultaMatrizOfertaForm" method="post">
                	<input type="hidden" name="valor" value="reter">
					<input type="hidden" name="acao" value="ok">
                	<input type="hidden" name="tipoEncerramento" value="3">
					<html:hidden property="validade" name="Form" readonly="true" styleClass="textfield" size="10" />
					<acesso:controlInitEnv />
                <table border="0" height="140">
                    <tr align="left">
                        <td>Bônus Cadastrados:</td>
                        <td><html:select name="Form" property="bonusSelecionado" style="width:200px" styleClass="SELECT" size="1" onmouseover="ativarToolTip(this);">
                            <option value="0">Selecione</option>
                            <html:options collection="listaBonusVO" property="idBonus" labelProperty="descricao" />
                        </html:select></td>
                    </tr>
                    <tr align="left">
                        <td>Início de Vigência:</td>
                        <td><html:text property="dtInicio" name="Form" readonly="true" styleClass="textfield"
                            size="10" onchange="if(document.forms[0].dtFim.value!=''){carregarData()};" /><img
                            src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor: hand;"
                            title="Calendário" onclick="return showCalendar('dtInicio', '%d/%m/%Y');" /> <acesso:controlHiddenItem
                            nomeIdentificador="fid_b_calc">
                            <img href="" class="button" src="/FrontOfficeWeb/resources/images/bt_calcular_nrml.gif" onClick="carregarData(); return false" border="0" />
                        </acesso:controlHiddenItem></td>
                    </tr>
                    <tr>
                        <td>Fim de Vigência:</td>
                        <td><html:text property="dtFim" name="Form" readonly="true" styleClass="textfield"
                            size="10" /><img src="<%=request.getContextPath()%>\resources\images\calendario.gif"
                            style="cursor: hand; visibility: hidden;" title="Calendário"
                            onclick="return showCalendar('dtFim', '%d/%m/%Y');" id="calendar2"></td>
                    </tr>
                    <acesso:controlHiddenItem nomeIdentificador="fid_b_excecao">
                        <tr>
                            <td><b>Oferta de Exceção:</b></td>
                            <td><html:checkbox property="inExcecao" name="Form" style="border:none;background-color:#E3ECF4;" onclick="liberaData(this);" value="true" /></td>
                        </tr>
                    </acesso:controlHiddenItem>
                    <tr>
                        <td>&nbsp;</td>
                    </tr>
                </table>
                <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="85"></div>
                <table width="100%" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                        <td><img class="button" action="voltarOfertas"
                            src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" border="0" onClick="eval(zsaPP);" /></td>
                        <td align="center"><acesso:controlHiddenItem nomeIdentificador="fid_b_manualeletronico">
                            <img class="button"
                                onmouseup="window.open('http://intranet.vivo-sp.com.br/manual/retencao/index.htm');"
                                target="_blank" src="/FrontOfficeWeb/resources/images/bt_manelet_nrml.gif" border="0" />
                        </acesso:controlHiddenItem> <acesso:controlHiddenItem nomeIdentificador="fid_b_agendar">
                            <img src="/FrontOfficeWeb/resources/images/bt_agendar_nrml.gif"
                                style="border: none; cursor: hand;" border="0" onClick="agendar();" />
                        </acesso:controlHiddenItem> <acesso:controlHiddenItem nomeIdentificador="fid_b_vaipensar">
                            <img src="/FrontOfficeWeb/resources/images/bt_vaipensar_nrml.gif" border="0"
                                onClick="cancela('vaipensar'); return false" />
                        </acesso:controlHiddenItem></td>
                        <td align="right"><acesso:controlHiddenItem nomeIdentificador="fid_b_reter">
                            <img onClick="reterBonus()" src="/FrontOfficeWeb/resources/images/bt_reter_nrml.gif"
                                border="0" style="border: none; cursor: hand;" />
                        </acesso:controlHiddenItem></td>
                    </tr>
                </table>
                <html:select name="Form" property="validadeSelecionada" size="1"
                    style="width=0px;height=0px;visibility:visible">
                    <option value="0">Selecione</option>
                    <html:options collection="listaBonusVO" property="idBonus" labelProperty="validade" />
                </html:select></form>
            </vivo:quadro> <vivo:quadroFlutuante id="dvMsgPontos" idIframe="ifrmMensagem" height="50" width="300" spacesTop="50"
                spacesLeft="50" display="none" url="<%=request.getContextPath()%>" label="" /></div>
        </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>
