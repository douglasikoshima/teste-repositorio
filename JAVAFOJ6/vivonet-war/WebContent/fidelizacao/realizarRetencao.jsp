<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<netui-template:template templatePage="/resources/jsp/templateRetencao.jsp">
<netui-template:section name="headerSection">
    <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
    <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/fidelizacao-atendimento.js"></script>
    <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/formatPhoneNumber.js"></script>
    <script language="JavaScript">
		<!--
            function pesquisarLinha(obj){
                if(document.actionRetencaoForm.elements["linhaPesquisa"].value.length < 13 && document.actionRetencaoForm.elements["linhaPesquisa"].value != ""){
                    alert('Número da Linha inválido, favor Corrigir!')
                
                }else{
                    document.forms[0].target = "";
                    document.forms[0].action = "getLinhas.do?pesquisa=true";
                    parent.parent.mostrar_div();
                    document.forms[0].method = "POST";
                    document.forms[0].submit();
                }
            }
		
    		function proximaPagina(obj) {
    		    if (validarSelecaoLinha()!="") {
    		        document.forms[0]["linhaSelecionada"].value = validarSelecaoLinha();
    		        document.forms[0].target = "frmQuestionario";
    		        document.forms[0].action = "getQuestionarioCampanha.do";
    		        parent.parent.mostrar_div();
    		        document.forms[0].method = "POST";
    		        document.forms[0].submit();
    		        return true;
    		    } else {
    		        alert("Favor selecionar uma Linha!");
    		        return false;
    		    }
    		}
    	-->
    </script>
</netui-template:section>
<netui-template:section name="bodySection">
    <logic:notEmpty name="error">
        <script>
            alert("<bean:write name="error"/>");
        </script>
    </logic:notEmpty>
    <acesso:controlHiddenItem nomeIdentificador="fid_rr_verpagina">
        <bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="actionRetencaoForm" />
        <html:form action="/fidelizacao/getLinhas.do" method="post" onsubmit="return false;" styleId="formLinhas">
            <!--vivo:quadro id="qdMain" height="200" width="700" label="Dados da Linha"-->
            <html:hidden name="Form" property="strLinhaSel" styleId="strLinhaSel" />
            <html:hidden name="Form" property="linhaSelecionada" styleId="linhaSelecionada"/>
            <input type="hidden" name="progresso" value="0"/>
            <table style="table" width="750" cellpadding="0" cellspacing="0" border="0">
                <tr>
                    <td valign="middle">
                        N&uacute;mero da Linha:
                        <html:text name="Form" property="linhaPesquisa" maxlength="14" size="15" onblur="maskPhoneNumberObj(this);" onkeyup="maskPhoneNumberObj(this);"></html:text>
                        <acesso:controlHiddenItem nomeIdentificador="fid_rr_pesquisar"><!-- action="getLinhas" -->
                            <img align="middle" src="<%=request.getContextPath()%>/resources/images/bt_pesquisar_nrml.gif" onclick="pesquisarLinha(this);return false;" border="0" style="cursor:hand;"/>
                        </acesso:controlHiddenItem>
                    </td>
                    <td align="right">
                        <acesso:controlHiddenItem nomeIdentificador="fid_rr_historico"><!-- action="getListaHistorico" -->
                            <img align="middle" src="<%=request.getContextPath()%>/resources/images/bt_histor_nrml.gif" border="0" onClick="validarAbrirHistorico();return false;" style="cursor:hand;"/>
                        </acesso:controlHiddenItem>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" height="260" valign="top">
                        <vivo:tbTable selectable="true" layoutWidth="740" layoutHeight="250" tableWidth="740" styleId="tableTitle" sortable="true">
                            <vivo:tbHeader>
                                <vivo:tbHeaderColumn align="left" width="5%" type="string" >&nbsp;</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="left" width="10%" type="string">Linha</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="left" width="10%" type="string">Segmentação</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="left" width="10%" type="string">Rentabilidade</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="left" width="10%" type="string">Contrato de Fidelização</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="left" width="10%" type="string">Multa Contratual</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="left" width="10%" type="string">Término Contrato</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="left" width="25%" type="string">Plano Serviço</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="left" width="10%" type="string">Habilitação</vivo:tbHeaderColumn>
                            </vivo:tbHeader>
                            <vivo:tbRows>
                                <bean:define id="listaLinhasVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaLinhasVO.detalheLinhaVOArray" />
                                <logic:iterate name="listaLinhasVO" id="iterator" indexId="idx">
                                    <vivo:tbRow key="linha1">
                                        <vivo:tbRowColumn>
                                            <input align="middle" name="wlw-radio_button_group_key:{actionForm.linhaSelecionada}" type="radio" value="<%=idx%>" class="radio" style="margin:0;"/><span style="visibility:hidden"><%=idx%></span>
                                        </vivo:tbRowColumn>
                                        <vivo:tbRowColumn><span><bean:write name="iterator" property="numero" /></span></vivo:tbRowColumn>
                                        <vivo:tbRowColumn><span><bean:write name="iterator" property="segmentacao" /></span></vivo:tbRowColumn>
                                        <vivo:tbRowColumn><span><bean:write name="iterator" property="rentabilidade" /></span></vivo:tbRowColumn>
                                        <vivo:tbRowColumn><span><bean:write name="iterator" property="contrato" /></span></vivo:tbRowColumn>
                                        <vivo:tbRowColumn><span><bean:write name="iterator" property="valorMulta" /></span></vivo:tbRowColumn>
                                        <vivo:tbRowColumn><span><bean:write name="iterator" property="dtFimContrato" /></span></vivo:tbRowColumn>
                                        <vivo:tbRowColumn><span><bean:write name="iterator" property="plano" /></span></vivo:tbRowColumn>
                                        <vivo:tbRowColumn><span><bean:write name="iterator" property="dtHabilitacao" /></span></vivo:tbRowColumn>
                                    </vivo:tbRow>
                                </logic:iterate>
                            </vivo:tbRows>
                        </vivo:tbTable>
                    </td>
                </tr>
                <tr>
                    <td><b>Total de linhas:</b> <bean:write name="Form" property="totalLinhas"/><html:hidden name="Form" property="totalLinhas" />
                    </td>
                </tr>
                <logic:notEqual name="Form" property="totalLinhas" value="0" >
                    <tr><td align="right" colspan="2">
                    <table cellpadding="0" cellspacing="0" width="100%">
                        <tr>
                            <td valign="baseline">&nbsp;</td>
                            <td align="center" valign="baseline">
                                <acesso:controlHiddenItem nomeIdentificador="fid_rr_manualeletronico">
                                    <img onclick="window.open('http://intranet.vivo-sp.com.br/manual/retencao/index.htm');return false;" src="<%=request.getContextPath()%>/resources/images/bt_manelet_nrml.gif" border="0" style="cursor:hand;"/>
                                </acesso:controlHiddenItem>
                                <acesso:controlHiddenItem nomeIdentificador="fid_rr_cancelarlinha">
                                    <img src="<%=request.getContextPath()%>/resources/images/bt_cancelatend_nrml.gif" onClick="cancela('ligacao');return false;" border="0" style="cursor:hand;"/>
                                </acesso:controlHiddenItem>
                            </td>
                            <td align="right" valign="baseline">
                            <acesso:controlHiddenItem nomeIdentificador="fid_rr_proxima">
                                <img src="<%=request.getContextPath()%>/resources/images/bt_proxima_nrml.gif" onclick="proximaPagina(this);return false;" border="0" style="cursor:hand;"/>
                            </acesso:controlHiddenItem>
                            </td>
                        </tr>
                    </table></td></tr>
                </logic:notEqual>
            </table>
            <div id='dvCancelarAtd' name='dvCancelarAtd' style='z-index:998 ;position:absolute; top:0; left:0; width:100%; height:100%; border-style:solid; background-image:url(<%=request.getContextPath()%>/resources/images/window_bg.gif); border-width:1px; border-color:#adadad; display:none;'>
                <table border='0' cellpadding='0' cellspacing='0'>
                    <tr>
                        <td style='height:115;' colspan='2'></td>
                    </tr>
                    <tr>
                        <td style='width:230;'></td>
                        <td>
                            <table width='450' height='21' cellpadding='0' cellspacing='0' background='<%=request.getContextPath()%>/resources/images/window_topbg.gif' bgcolor='#0066cb' class='tbl_titulo'>
                                <tr>
                                    <td width='386' height='21'>
                                        <div id='dv_dvCancelarAtd' align="center">Cancelar Atendimento</div>
                                    </td>
                                    <td width='64' valign='top' background='<%=request.getContextPath()%>/resources/images/window_topbtbg.gif'>
                                        <div align='right'>
                                            <img hspace='3' src='<%=request.getContextPath()%>/resources/images/window_fechar.gif' onclick="dvCancelarAtd.style.display='none';" style='cursor:hand;'>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                            <table width='450' height='100' border='0' cellpadding='0' cellspacing='1' bgcolor='#adadad'>
                                <tr>
                                    <td bgcolor='#E3ECF4' align='center'>
                                        <table border="0" cellpadding="0" cellspacing="0" align="center" background="#545454">
                                            <tr>
                                                <td valign="middle" align="center">
                                                    <html:select name="Form" property="idTipoEncerramento" ></html:select>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td valign="middle" align="center">&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td valign="middle" align="center">
                                                    <img src="<%=request.getContextPath()%>/resources/images/bt_ok_nrml.gif" onClick="cancelarAtd();return false;" border="0"/>
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
            </div>
        </html:form>
        <!--Quadro com o script de retencao, este dive fica oculto at? o usuario escolher qual a linha que esta sendo retida-->
        <div id="telaInsere" style="visibility:<%= request.getAttribute("valorDiv")%>">
            <!-- quadro com o historico de atendimento do cliente-->
        </div>
        <form name="frmHistorico" method="post" onsubmit="return false;">
            <vivo:quadroFlutuante id="dvNrProcesso" idIframe="ifrmNrProcesso"  height="300" width="759" spacesTop="0" spacesLeft="0" display="none" url="<%=request.getContextPath()%>" label="Histórico"/>
        </form>
        <form id="frmSelecao" name="frmSelecao" method="post" onsubmit="return false;">
            <vivo:quadroFlutuante id="dvVaiPensar" idIframe="ifrmVaiPensar" height="100" width="350" spacesTop="115" spacesLeft="230" display="none" url="<%=request.getContextPath()%>" label=""/>
            <vivo:quadroFlutuante id="dvCancelarLinha" idIframe="ifrmCancelarLinha" height="100" width="350" spacesTop="115" spacesLeft="230" display="none" url="<%=request.getContextPath()%>" label=""/>
        </form>
        <iframe name="getLigacaoIndevida" height="0" width="0" style="display:none"></iframe>
        <form id="formLigacaoIndevida" name="formLigacaoIndevida" action="getLigacaoIndevida.do" target="getLigacaoIndevida" onsubmit="return false;"></form>
        <form style="height:1px;" name="frmAnalisesBKO" onsubmit="return false;">
            <vivo:quadroFlutuante id="dvAnalisesBKO" scroll="true" idIframe="ifrmAnalisesBKO" height="300" width="760" spacesTop="0" spacesLeft="0" display="none" url="<%=request.getContextPath()%>" label="An&aacute;lise de Cr&eacute;dito" onclick="validarAbrirHistorico()"/>
        </form>
        <script>
            inicio = location.href.indexOf("acao=")+5
            fim = location.href.length
            if(location.href.substring(inicio,fim) == "erro"){
                alert("Não existem linhas cadastradas.")
            }
            if(document.forms[0].elements['totalLinhas'].value == "0"){
                alert('Nâo existem registros para essa pesquisa!');
                document.forms[0].elements['totalLinhas'].value = "";
            }
        </script>
        <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
            <!--
                parent.parent.oculta_div();
            -->
        </SCRIPT>
    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>