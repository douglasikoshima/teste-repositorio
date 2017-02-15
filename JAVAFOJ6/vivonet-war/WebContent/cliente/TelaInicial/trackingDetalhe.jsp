<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.cliente.vo.TrackingAparelhosVODocument.TrackingAparelhosVO.DetalheTrackingAparelhoVO.DadosPedido"%>
<%@ page import="br.com.vivo.fo.cliente.vo.TrackingAparelhosVODocument.TrackingAparelhosVO.DetalheTrackingAparelhoVO.ListaDadosOrdem.DadosOrdem.DadosEtapa"%>
<%@ page import="br.com.vivo.fo.cliente.vo.TrackingAparelhosVODocument.TrackingAparelhosVO.DetalheTrackingAparelhoVO.ListaDadosOrdem.DadosOrdem"%>
<%@ page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="trackingForm" type="cliente.TelaInicial.TelaInicialController.TrackingForm"/>
<bean:define id="dPedido" name="Form" property="detalheTrackingAparelhoVO.dadosPedido" type="DadosPedido"/>
<bean:define id="dOrdem"  name="Form" property="detalheTrackingAparelhoVO.listaDadosOrdem"/>
<html>
    <head>
        <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
        <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
		<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js"></script>
        <script type="text/javascript" language="javascript">
            <!--

            <logic:equal name="Form" property="tpRetorno" value="E">
                alert('<bean:write name="Form" property="msgError"/>');
                history.back();
            </logic:equal>

            function comparar(nrPed,nrOV,nrFor,nrPic,nrNF){
                var f = document.forms[0];
                var idSO = f.idSO.value;
                var nrDoc = f.nrDoc.value;
                if(nrOV==null) nrOV   = "";
                if(nrFor==null) nrFor = "";
                if(nrPic==null) nrPic = "";
                if(nrNF==null) nrNF   = "";
                var param = "nrDoc="+nrDoc+"&nrPedido="+nrPed+"&idSistemaOrigem="+idSO+"&nrOrdemVenda="+nrOV+"&nrFornecimento="+nrFor+"&nrPicking="+nrPic+"&nrNotaFiscal="+nrNF;
                var src = "<%=request.getContextPath()%>/cliente/TelaInicial/trackingComparar.do?"+param;
                window.top.frameApp.showPopupTI("Comparação Pedido na origem com o SAP", 785, 550, null, src);
                return false;
            }

            function setObsEtapa(obs,od){
                document.getElementById("obsEtapa"+od).value = "Observações da Etapa: "+obs;
            }

            function voltar(){
                var f = document.forms[0];
                location.href="trackingLista.do?pageNumber="+f.pageNumber.value;
            }

            function inserirComentario(){
                document.getElementById("divComentario").style.display = "block";
                ifrmComentario.location.href = "inserirComentarioTracking.jsp";
            }

            onload = function() {
                if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
                <% if (request.getAttribute("msgProtocolo") != null && !ConstantesCRM.SVAZIO.equals(request.getAttribute("msgProtocolo"))) { %>
				var nrProtocolo = '<%=request.getAttribute("msgProtocolo")%>';
				alert(nrProtocolo)
				nrProtocolo = nrProtocolo.gsub(/[^0-9]/,'');
				top.frameApp.$('nrProtocolo').update(nrProtocolo);
                top.frameApp.setAlteracaoSMSSolicitacaoProtocolo('alteracaoSMS');
                <% } %>
            }
			-->
        </script>
    </head>
    <body>
        <html:form action="/cliente/TelaInicial/trackingDetalhe.do" onsubmit="return false;" style="margin:0">
        <input type="hidden" name="nrDoc" value="<%=request.getParameter("nrDoc")%>">
        <html:hidden name="Form" property="idProcesso"/>
        <input type="hidden" name="idSO" value="<%=request.getParameter("idSistemaOrigem")%>">
        <html:hidden name="Form" property="pageNumber"/>
        <table style="border:1px solid #adadad; background-color:#E7E7E7;margin-top:5px;" width="762" border="0" cellpadding="2" cellspacing="2">
            <tr>
                <td><b>Dados do Pedido</b></td>
                <td></td>
                <td align="right">
                    <logic:notEqual name="Form" property="idProcesso" value="0">
                    <img onClick="inserirComentario();" src="/FrontOfficeWeb/resources/images/bt_inserircomentario_nrml.gif" style="border:none;cursor:pointer;"/>
                    </logic:notEqual>
                    <img onClick="voltar();" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" style="border:none;cursor:pointer;"/>
                </td>
            </tr>
        </table>
        <table style="border:1px solid #adadad; background-color:#E7E7E7;margin-top:5px;margin-bottom:5px;" width="762" border="0" cellpadding="2" cellspacing="2">
            <tr>
                <td width="33%">Número do Pedido no Sistema Origem:</td>
                <td align="left" colspan="2"><b><bean:write name="dPedido" property="nrPedido"/></b></td>
            </tr>
            <tr>
                <td width="33%">Sistema Origem: <b><bean:write name="dPedido" property="dsOrigem"/></b></td>
                <td width="33%">UF Pedido: <b><bean:write name="dPedido" property="sgUF"/></b></td>
                <td width="33%">Abertura: <b><bean:write name="dPedido" property="dtAbertura"/></b></td>
            </tr>
            <tr>
                <td width="33%">Cd Agente/Filial (ADABAS):<b><bean:write name="dPedido" property="cdAgenteFilial"/></b></td>
                <td width="33%">Canal Origem: <b><bean:write name="dPedido" property="dsCanalOrigem"/></b></td>
                <td width="33%">Valor Total do Pedido: <b><logic:notEmpty name="dPedido" property="vlTotalPedido">R$ </logic:notEmpty><bean:write name="dPedido" property="vlTotalPedido"/></b></td>
            </tr>
            <tr>
                <td width="33%">Quantidades de Pontos Resgatados: <b><bean:write name="dPedido" property="qtdPontosResgatados"/></b></td>
                <td width="66%" colspan="2">Valor das Parcelas: <b><logic:notEmpty name="dPedido" property="vlParcelas">R$ </logic:notEmpty><bean:write name="dPedido" property="vlParcelas"/></b></td>
            </tr>
            <tr>
                <td width="100%" colspan="3">Pagamento: <b><bean:write name="dPedido" property="dsPagamento"/></b></td>
            </tr>
            <tr>
                <td width="100%" colspan="3">Endereço de Entrega: <b><bean:write name="dPedido" property="dsEnderecoEntrega"/></b></td>
            </tr>
        </table>
        <vivo:tbTable selectable="true" layoutWidth="750" layoutHeight="75" tableWidth="750" styleId="tbTrackingDetalheNota" sortable="true">
            <vivo:tbHeader>
                <vivo:tbHeaderColumn align="center" width="5%"  type="number">Item</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="27%" type="string">Código Produto</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="27%" type="string">Descrição</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="10%" type="string">Valor</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="5%"  type="string">Qtde</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="27%" type="string">Observações</vivo:tbHeaderColumn>
            </vivo:tbHeader>
            <vivo:tbRows scroll="false">
                <logic:iterate id="lsPedido" name="dPedido" property="listaItens.itensArray" indexId="idx">
                <vivo:tbRow key="linha1" zebrar="true">
                    <vivo:tbRowColumn><bean:write name="lsPedido" property="nrItem"/></vivo:tbRowColumn>
                    <vivo:tbRowColumn><bean:write name="lsPedido" property="cdItem"/></vivo:tbRowColumn>
                    <vivo:tbRowColumn><bean:write name="lsPedido" property="dsItem"/></vivo:tbRowColumn>
                    <vivo:tbRowColumn><logic:notEmpty name="lsPedido" property="vlItem">R$ </logic:notEmpty><bean:write name="lsPedido" property="vlItem"/></vivo:tbRowColumn>
                    <vivo:tbRowColumn><bean:write name="lsPedido" property="qtItem"/></vivo:tbRowColumn>
                    <vivo:tbRowColumn><bean:write name="lsPedido" property="dsObservacao"/></vivo:tbRowColumn>
                </vivo:tbRow>
                </logic:iterate>
            </vivo:tbRows>
        </vivo:tbTable>
        <table style="border:1px solid #adadad; background-color:#E7E7E7;margin-top:5px;" width="762" border="0" cellpadding="2" cellspacing="2">
            <tr>
                <td>Observações do Pedido: <b><bean:write name="dPedido" property="dsObservacao"/></b></td>
            </tr>
        </table>
        <logic:iterate id="lsOrdem" name="dOrdem" property="dadosOrdemArray" indexId="idxOrdem">
        <table style="border:1px solid #adadad; background-color:#E7E7E7;margin-top:5px;" width="762" border="0" cellpadding="2" cellspacing="2">
            <tr>
                <td><b>Ordem de Venda/Nota Fiscal</b></td>
                <td align="right">
                    <logic:equal value="1" name="lsOrdem" property="flagComparar">
                    <%String jsParam = "'".concat(dPedido.getNrPedido()).concat("','").concat(((DadosOrdem)lsOrdem).getNrOrdemVenda()).concat("','").concat(((DadosOrdem)lsOrdem).getNrFornecimento()).concat("','").concat(((DadosOrdem)lsOrdem).getNrPicking()).concat("','").concat(((DadosOrdem)lsOrdem).getNrNotaFiscal()).concat("'");%>
                    <img onclick="comparar(<%=jsParam%>);" src="/FrontOfficeWeb/resources/images/bt_comparar_nrml.gif" style="border:none;cursor:pointer;"/>
                    </logic:equal>
                </td>
            </tr>
        </table>
        <table style="border:1px solid #adadad; background-color:#E7E7E7;margin-top:5px;" width="762" border="0" cellpadding="2" cellspacing="2">
            <tr>
                <td width="33%">Ordem de Venda: <b><bean:write name="lsOrdem" property="nrOrdemVenda"/></b></td>
                <td width="33%">
                    Nota Fiscal/Série: <b><bean:write name="lsOrdem" property="nrNotaFiscal"/></b>
                    <logic:present name="lsOrdem" property="serieNotaFiscal">
                        /<b><bean:write name="lsOrdem" property="serieNotaFiscal"/></b>
                    </logic:present>
                </td>
                <td width="33%">Data da Nota Fiscal: <b><bean:write name="lsOrdem" property="dtNotaFiscal"/></b></td>
            </tr>
            <tr>
                <td width="33%">Valor Total da Nota: <b><logic:notEmpty name="lsOrdem" property="vlTotalNota">R$ </logic:notEmpty><bean:write name="lsOrdem" property="vlTotalNota"/></b></td>
                <td width="33%">Responsável: <b><bean:write name="lsOrdem" property="dsResponsavel"/></b></td>
                <td width="33%">Tipo de Ordem: <b><bean:write name="lsOrdem" property="dsTipo"/></b></td>
            </tr>
            <tr>
                <td width="33%">Motivo Ordem: <b><bean:write name="lsOrdem" property="dsMotivo"/></b></td>
                <td width="33%">Status Ordem: <b><bean:write name="lsOrdem" property="dsStatus"/></b></td>
                <td width="33%">Motivo Bloqueio:<b><bean:write name="lsOrdem" property="dsMotivoBloqueio"/></b></td>
            </tr>
            <tr>
                <td width="33%">Fornecimento nº: <b><bean:write name="lsOrdem" property="nrFornecimento"/></b></td>
                <td width="33%">Data do Fornecimento: <b><bean:write name="lsOrdem" property="dtFornecimento"/></b></td>
                <td width="33%">Picking nº: <b><bean:write name="lsOrdem" property="nrPicking"/></b></td>
            </tr>
            <tr>
                <td width="33%">Data do Picking: <b><bean:write name="lsOrdem" property="dtPicking"/></b></td>
                <td width="33%">Data de Conf. de Picking: <b><bean:write name="lsOrdem" property="dtConfPicking"/></b></td>
                <td width="33%">Dt Reg Saída de mercadoria: <b><bean:write name="lsOrdem" property="dtRegSaida"/></b></td>
            </tr>
            <tr>
                <td width="33%">Norma de Expedição: <b><bean:write name="lsOrdem" property="dsNormaExpedicao"/></b></td>
                <td width="33%">Escritório de Venda: <b><bean:write name="lsOrdem" property="dsEscritorioVenda"/></b></td>
                <td width="33%">Grupo de Vendedores: <b><bean:write name="lsOrdem" property="dsGrupoVendedores"/></b></td>
            </tr>
            <tr>
                <td width="33%">Canal de Distribuição: <b><bean:write name="lsOrdem" property="dsCanalDist"/></b></td>
                <td colspan="2"></td>
            </tr>
        </table>
        <table style="border:1px solid #adadad; background-color:#E7E7E7;margin-top:5px;" width="762" border="0" cellpadding="2" cellspacing="2">
            <tr>
                <td width="33%"><b>Dados da Entrega</b></td>
                <td width="33%">Status da Entrega: <b><bean:write name="lsOrdem" property="dadosEntrega.dsStatusAtual"/> (<bean:write name="lsOrdem" property="dadosEntrega.dsMotivoStatus"/>)</b></td>
                <td width="33%"></td>
            </tr>
        </table>
        <table style="border:1px solid #adadad; background-color:#E7E7E7;margin-top:5px;" width="762" border="0" cellpadding="2" cellspacing="2">
            <tr>
                <td width="33%">Tipo de Entrega: <b><bean:write name="lsOrdem" property="dadosEntrega.tipo"/></b></td>
                <td width="33%">Previsão Entrega: <b><bean:write name="lsOrdem" property="dadosEntrega.dtPrevisao"/></b></td>
                <td width="33%">Tipo de Comprovante: <b><bean:write name="lsOrdem" property="dadosEntrega.tpComprovante"/></b></td>
            </tr>
            <tr>
                <td width="66%" colspan="2">Nome do Recebedor: <b><bean:write name="lsOrdem" property="dadosEntrega.nmRecebedor"/></b></td>
                <td width="33%">Nº Documento do Recebedor: <b><bean:write name="lsOrdem" property="dadosEntrega.nrRG"/></b></td>
            </tr>
            <tr>
                <td width="100%" colspan="3">Observação: <b><bean:write name="lsOrdem" property="dadosEntrega.dsObservacao"/></b></td>
            </tr>
        </table>
        <vivo:tbTable layoutWidth="745" layoutHeight="120" tableWidth="740" styleId="tbTrackingEtapa">
            <vivo:tbHeader>
                <vivo:tbHeaderColumn align="center" width="18%" type="string">Data/Hora Realizado</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="18%" type="string">Atualizado em</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="18%" type="string">Sistema</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="36%" type="string">Etapa</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="10%" type="string">Usuário</vivo:tbHeaderColumn>
            </vivo:tbHeader>
            <vivo:tbRows scroll="true">
                <logic:iterate id="lsEtapa" name="lsOrdem" property="dadosEtapaArray">
                <%
                if(((DadosEtapa) lsEtapa).getDsObservacao()!=null){
                    ((DadosEtapa)lsEtapa).setDsObservacao(((DadosEtapa) lsEtapa).getDsObservacao().replaceAll("'","`").replaceAll("\"","`") );
                }else{
                    ((DadosEtapa)lsEtapa).setDsObservacao(ConstantesCRM.SVAZIO);
                }
                %>
                <vivo:tbRow key="xlinha1" zebrar="S" onRowClick='<%="setObsEtapa(\'"+((DadosEtapa) lsEtapa).getDsObservacao()+"\',"+idxOrdem+");"%>' onMouseOver="this.style.cursor='pointer'" onMouseOut="this.style.cursor=''">
                    <vivo:tbRowColumn><bean:write name="lsEtapa" property="dtEtapa"/></vivo:tbRowColumn>
                    <vivo:tbRowColumn><bean:write name="lsEtapa" property="dtAtualizacao"/></vivo:tbRowColumn>
                    <vivo:tbRowColumn><bean:write name="lsEtapa" property="dsSistema"/></vivo:tbRowColumn>
                    <vivo:tbRowColumn><bean:write name="lsEtapa" property="dsEtapa"/></vivo:tbRowColumn>
                    <vivo:tbRowColumn><bean:write name="lsEtapa" property="nmUsuario"/></vivo:tbRowColumn>
                </vivo:tbRow>
                </logic:iterate>
            </vivo:tbRows>
        </vivo:tbTable>
        <textarea id="obsEtapa<%=idxOrdem%>" name="obsEtapa<%=idxOrdem%>" cols="90" rows="3">Observações da Etapa:</textarea>
        <div style="margin-bottom:5px;margin-top:5px;"></div>
        </logic:iterate>
        </html:form>
        <vivo:quadroFlutuante id="divComentario" idIframe="ifrmComentario" src="" width="740" height="100" spacesTop="10" spacesLeft="10" display="none" url="<%=request.getContextPath()%>" label="Inserção de Comentário" scroll="auto" />
    </body>
</html>