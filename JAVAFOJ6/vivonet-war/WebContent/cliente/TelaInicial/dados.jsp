<%@ page import="br.com.vivo.fo.cliente.vo.ParametrosVODocument.ParametrosVO"%>
<%@ page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <link href="../../resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="../../resources/scripts/dados.js"></script>
    <script language="javascript" src="../../resources/scripts/resizeFrame.js"></script>
    <script>
    var idTipoLinha = '';

    function carregaAbas(){
        top.frameApp.document.getElementById('dvFrameDados').style.display = '';
        top.frameApp.document.getElementById('areaDetalhe').style.display = '';
        <%
        ParametrosVO parametrosVO = (ParametrosVO) session.getAttribute(ConstantesCRM.SPARAMETROS);
        //Caso linha seja do tipo Pos-Pago CDMA (idTipoLinha 1) ou Pos-pago GSM (idTipoLinha 5) ou Controle CDMA OU GSM (idTipoLinha 4 ou 7)
        if(parametrosVO.getIdTipoLinha()!=null){
            if(ConstantesCRM.SONE.equals(parametrosVO.getIdTipoLinha()) || ConstantesCRM.SFIVE.equals(parametrosVO.getIdTipoLinha()) || ConstantesCRM.SFOUR.equals(parametrosVO.getIdTipoLinha()) || ConstantesCRM.SSEVEN.equals(parametrosVO.getIdTipoLinha())){%>
                nrConta             = '<%=parametrosVO.getNrConta()%>';
                tipoRelacionamiento = '<%=(parametrosVO.getIdTipoRelacionamento()!=null?parametrosVO.getIdTipoRelacionamento():ConstantesCRM.SVAZIO)%>';
                idProspect          = '';
                idPessoaCliente     = '<%=(parametrosVO.getIdPessoaCliente()!=null?parametrosVO.getIdPessoaCliente():ConstantesCRM.SVAZIO)%>';
                inCorporativo       = '<%=(parametrosVO.getInCorporativo()!=null?parametrosVO.getInCorporativo():ConstantesCRM.SVAZIO)%>';
                idPos               = '<%=(parametrosVO.getInPos()!=null?parametrosVO.getInPos():ConstantesCRM.SVAZIO)%>';
                idTipoLinha         = '<%=(parametrosVO.getIdTipoLinha()!=null?parametrosVO.getIdTipoLinha():ConstantesCRM.SVAZIO)%>';

                frmFatura.setNroLinha(nrConta,tipoRelacionamiento, idProspect);

                bindLupaCliente(tipoRelacionamiento, idPessoaCliente);
                bindLupaCarterizacaoSegmentacao(tipoRelacionamiento, idProspect, inCorporativo);

                ifrLinha.bindLupaLinhaUsuario(tipoRelacionamiento, idProspect);
                parent.frameURA.bindCombos(idPos, tipoRelacionamiento);

                //top.frameApp.abaOcultar(top.frameApp.btAba, top.frameApp.bt03, true);
                //top.frameApp.abaOcultar(top.frameApp.btAba, top.frameApp.bt05, true);
                top.frameApp.abaOcultar(top.frameApp.btAba, top.frameApp.bt07, true);
                top.frameApp.abaOcultar(top.frameApp.btAba, top.frameApp.bt06, true);

                if(idTipoLinha==1 || idTipoLinha==5 || idTipoLinha==4 || idTipoLinha==7){
                    top.frameApp.abaOcultar(top.frameApp.btAba, top.frameApp.bt06, false);
                }
                //Fim animação
                if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();

                if(parent.inVerAbaRelacionamento!=null)
                    top.frameApp.abaSelected(top.frameApp.btAba, top.frameApp.bt01);

                if(parent.inVerAbaRelacionamento!=null)
                    top.frameApp.CarregaAba('bt01');

                top.frameApp.fechaLupa('true');
                top.frameApp.pesquisaEfetuada = true;
                document.getElementById("dvFaturamento").style.display='';
            <%//Caso linha seja do tipo Pre-Pago CDMA (idTipoLinha 2) ou Pre-pago GSM (idTipoLinha 6)
            }else if(ConstantesCRM.STWO.equals(parametrosVO.getIdTipoLinha()) || ConstantesCRM.SSIX.equals(parametrosVO.getIdTipoLinha())){%>
                nrConta             = '<%=parametrosVO.getNrConta()%>';
                tipoRelacionamiento = '<%=(parametrosVO.getIdTipoRelacionamento()!=null?parametrosVO.getIdTipoRelacionamento():ConstantesCRM.SVAZIO)%>';
                idProspect          = '';
                idPessoaCliente     = '<%=(parametrosVO.getIdPessoaCliente()!=null?parametrosVO.getIdPessoaCliente():ConstantesCRM.SVAZIO)%>';
                inCorporativo       = '<%=(parametrosVO.getInCorporativo()!=null?parametrosVO.getInCorporativo():ConstantesCRM.SVAZIO)%>';
                idPos               = '<%=(parametrosVO.getInPos()!=null?parametrosVO.getInPos():ConstantesCRM.SVAZIO)%>';
                idTipoLinha         = '<%=(parametrosVO.getIdTipoLinha()!=null?parametrosVO.getIdTipoLinha():ConstantesCRM.SVAZIO)%>';

                bindLupaCliente(tipoRelacionamiento, idPessoaCliente);
                bindLupaCarterizacaoSegmentacao(tipoRelacionamiento, idProspect, inCorporativo);

                ifrLinha.bindLupaLinhaUsuario(tipoRelacionamiento, idProspect);
                parent.frameURA.bindCombos(idPos, tipoRelacionamiento);

                //top.frameApp.abaOcultar(top.frameApp.btAba, top.frameApp.bt03, true);
                //top.frameApp.abaOcultar(top.frameApp.btAba, top.frameApp.bt05, true);
                top.frameApp.abaOcultar(top.frameApp.btAba, top.frameApp.bt07, true);
                top.frameApp.abaOcultar(top.frameApp.btAba, top.frameApp.bt06, true);

                if(idTipoLinha==1 || idTipoLinha==5 || idTipoLinha==4 || idTipoLinha==7){
                    top.frameApp.abaOcultar(top.frameApp.btAba, top.frameApp.bt06, false);
                }

                //Fim animação
                if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();

                top.frameApp.abaSelected(top.frameApp.btAba, top.frameApp.bt01);
                top.frameApp.CarregaAba('bt01');
                top.frameApp.fechaLupa('true');
                top.frameApp.pesquisaEfetuada=true;
                document.getElementById("dvPrePago").style.display='';
            <%}
        }%>
        //caso seja carregada por fila
        if(top.frameApp.telaCarregadaFila){
            top.frameApp.carregaTelaFila();
        }
    }
    </script>
<%if(ConstantesCRM.SONE.equals(request.getAttribute("inBloqueado"))){ %>
    <script>
        top.frameApp.document.getElementById('dvFrameDados').style.display = 'none';
        top.frameApp.document.getElementById('areaDetalhe').style.display = 'none';
        alert('DDD <%=parametrosVO.getNrLinha().substring(0,2)%> bloqueado temporariamente. Não é possível seguir com o atendimento.');
        top.frameApp.document.getElementById('valorPesquisa').value = "";
        top.frameApp.document.getElementById('valorPesquisa').focus();
    </script>
</head>
<body>
<%}else{%>
</head>
<body onload="carregaAbas();">
<%}%>
<acesso:controlHiddenItem nomeIdentificador="cli_dad_verpagina">
<%if(ConstantesCRM.SZERO.equals(request.getAttribute("inBloqueado")) || null==request.getAttribute("inBloqueado")){%>

<table width="790" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td width="63" height="67" valign="top">
            <img src="<%=request.getContextPath()%>/resources/images/ti_lupa_cliente.jpg" width="63" height="67" id="imgLupaCliente">
        </td>
        <td width="3"></td>
        <td valign="top">
			<table width="724" height="67" border="0" cellpadding="0" cellspacing="0" class="tbl_bgBlue">
				<tr>
					<td width="74" style="text-indent:5px;font-weight:bold;">Nome</td>
					<td colspan="3">&nbsp;&nbsp<netui:label id="cliente_nmNome" value="{pageFlow.tiForm.cliente.nmNome}"/></td>
					<td width="202" rowspan="3">
						<table width="194" height="47" border="0" cellpadding="0" cellspacing="0" style="background-image:url(../../resources/images/bcliente_bg_segcart.gif);background-repeat:no-repeat;">
							<tr>
								<td width="170" height="20" style="text-indent:6px; font-weight:bold;">Carteiriza&ccedil;&atilde;o</td>
								<td width="24" valign="top">
                                    <img src="<%=request.getContextPath()%>/resources/images/bcliente_lupa_segcart.gif" id="imgLupaCarterizacao">
                                </td>
							</tr>
							<tr>
                                <td height="21" colspan="2" style="padding-left:5px;line-height:12px;">
                                    <netui:label id="cliente_carterizacaoVO_descricao" value="{pageFlow.tiForm.cliente.carterizacaoVO.descricao}"/>
                                </td>
								</tr>
							<tr>
								<td colspan="2"><img src="<%=request.getContextPath()%>/resources/images/transp.gif" width="1" height="6"></td>
							</tr>
						</table>
					</td>
                	<td width="117" rowspan="3">
						<table width="110" height="47" border="0" cellpadding="0" cellspacing="0" style="background-image:url(../../resources/images/bcliente_bg_cart.gif);background-repeat:no-repeat;">
							<tr>
								<td width="86" height="20" style="text-indent:6px; font-weight:bold;">Segmenta&ccedil;&atilde;o</td>
								<td width="24" valign="top">
                                    <img src="<%=request.getContextPath()%>/resources/images/bcliente_lupa_segcart.gif" id="imgLupaSegmentacao">
                                </td>
							</tr>
							<tr>
                                <td height="21" colspan="2" style="padding-left:5px;line-height:12px;">
                                    <netui:label id="cliente_segmentacaoVO_descricao" value="{pageFlow.tiForm.cliente.segmentacaoVO.descricao}"/>
                                </td>
							</tr>
							<tr>
								<td colspan="2"><img src="<%=request.getContextPath()%>/resources/images/transp.gif" width="1" height="6"></td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td style="text-indent:5px;font-weight:bold;"><b><netui:label value="{pageFlow.tiForm.documento.dsTipoDocumento}" id="dsTpDocto"/></b></td>
                    <td width="121">&nbsp;&nbsp<netui:label value="{pageFlow.tiForm.documento.nrDocumento}" id="nrDocto"/></td>
					<td width="38"><b>Natureza</b></td>
                    <td width="172" style="padding-left:10px;">
                        <netui:label id="cliente_inTipoPessoa" value="{pageFlow.tiForm.cliente.inTipoPessoa}"/>
                    </td>
				</tr>
				<tr>
					<td style="text-indent:5px;font-weight:bold;">Telefone</td>
					<td>&nbsp;&nbsp<netui:label id="cliente_nrTelefone" value="{pageFlow.tiForm.cliente.nrTelefone}"/></td>
					<td><b>Churn</b></td>
					<td style="padding-left:10px;"><netui:label id="cliente_dsChurn" value="{pageFlow.tiForm.cliente.dsChurn}"/></td>
				</tr>
			</table></td>
		</tr>
		<tr>
			<td colspan="3" height="3"></td>
		</tr>
		<tr>
			<td colspan="3" height="75">
                <iframe src="dadosLinha.jsp" width="790" height="75" frameborder="0" name="ifrLinha" id="ifrLinha"></iframe>
            </td>
		</tr>
		<tr>
			<td colspan="3" height="3"></td>
		</tr>
		<tr>
			<td height="75" colspan="3" valign="top">
                <table width="790" height="75" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                        <td width="345" valign="top">
                            <div style="display:none" id="dvFaturamento">
                                <iframe src="dadosFaturamento.jsp" width="345" height="75" frameborder="0" name="frmFatura" scrolling="no" id="frmFatura"></iframe>
                            </div>
                            <div style="display:none" id="dvPrePago">
                                <iframe src="dadosPrePago.jsp" width="345" height="75" frameborder="0" name="frmPrePago" scrolling="no" id="frmPrePago"></iframe>
                            </div>
                        </td>
                        <td width="3"></td>
                        <td width="442" valign="top">
                            <acesso:controlHiddenItem nomeIdentificador="cli_dad_enderecoconta">
                            <table width="442" height="75" border="0" cellpadding="0" cellspacing="0" class="tbl_bgBlue">
                                <tr>
                                    <td width="71" style="text-indent:8px;font-weight:bold;">Endere&ccedil;o</td>
                                    <td colspan="3"><netui:label id="cliente_endereco_dsEndereco" value="{pageFlow.tiForm.endereco.dsEndereco}"/><netui:label value="{pageFlow.tiForm.endereco.nrEndereco}" id="cliente_endereco_nrEndereco"/></td>
                                </tr>
                                <tr>
                                    <td style="text-indent:8px;font-weight:bold;">Bairro</td>
                                    <td width="145"><netui:label id="cliente_endereco_dsBairro" value="{pageFlow.tiForm.endereco.dsBairro}"/></td>
                                    <td width="49"><b>Cidade</b></td>
                                    <td width="177"><netui:label id="cliente_endereco_dsCidade" value="{pageFlow.tiForm.endereco.dsCidade}"/></td>
                                </tr>
                                <tr>
                                    <td style="text-indent:8px;font-weight:bold;">Estado</td>
                                    <td><netui:label id="cliente_endereco_UFVO_nmUF" value="{pageFlow.tiForm.endereco.UFVO.nmUF}"/></td>
                                    <td><b>CEP</b></td>
                                    <td><netui:label id="cliente_endereco_nrCEP" value="{pageFlow.tiForm.endereco.nrCEP}"/></td>
                                </tr>
                            </table>
                            </acesso:controlHiddenItem>
                        </td>
                    </tr>
                </table>
             </td>
        </tr>
    </table>
<% } %>
</acesso:controlHiddenItem>
</body>
</html>