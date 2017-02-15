<%@page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ page import="br.com.vivo.fo.cliente.vo.ParametrosVODocument.ParametrosVO"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
<head>
	<script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/formatPhoneNumber.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
    <script>
    function carregarDados(strXml){
        var oXml = new ActiveXObject("microsoft.xmldom");
        oXml.async=false;
        if(!oXml.loadXML(strXml)){
            alert("Error on load of document");
            alert(oXml.parseError.reason);
            alert(oXml.parseError.line);
        }

        if(oXml.selectSingleNode("/salida/@tipo").text!='naoCliente'){
            top.frameApp.numeroPesquisado=oXml.selectSingleNode("/salida/linha/nrLinhaFormat").text;
            if(document.getElementById('linha_nrLinhaFormat')){
            	
                numeroLinha = oXml.selectSingleNode("/salida/linha/nrLinhaFormat").text;
                
                numeroLinha = formatPhoneNumber(numeroLinha);
                
                /*
                if (numeroLinha.length==12){
                    numeroLinha = numeroLinha.substring(0,7)+"-"+numeroLinha.substring(7);
                    //numeroLinha = numeroLinha.substring(0,7)+"-"+numeroLinha.substring(7,8)+numeroLinha.substring(9,numeroLinha.length);
                }
                if (numeroLinha.length==13){
                    numeroLinha = numeroLinha.substring(0,8)+"-"+numeroLinha.substring(8);
                    //numeroLinha = numeroLinha.substring(0,7)+"-"+numeroLinha.substring(7,8)+numeroLinha.substring(9,numeroLinha.length);
                }*/
                
                linha_nrLinhaFormat.innerHTML = numeroLinha;
                tipoLinha = oXml.selectSingleNode("/salida/linha/dsTipoLinha").text;
                linha_dsTipoLinha.innerHTML = tipoLinha;
                linha_dsPlanoServico.innerHTML = oXml.selectSingleNode("/salida/linha/dsPlanoServico").text;                
                linha_dsEstadoLinha.innerHTML = oXml.selectSingleNode("/salida/linha/dsEstadoLinha").text;
                
                document.getElementById("imagemLupaLinha").innerHTML = "<img src='/FrontOfficeWeb/resources/images/ti_lupa_linha.jpg' width='63' height='75' id='imgLupaLinha'>";

                
            }
            
            if(document.getElementById('linha_usuarioLinhaVO_nmUsuario')){
                linha_usuarioLinhaVO_nmUsuario.innerHTML = oXml.selectSingleNode("/salida/linha/usuarioLinhaVO/nmUsuario").text;
                linha_usuarioLinhaVO_dsTipoDocumento.innerHTML = oXml.selectSingleNode("/salida/linha/usuarioLinhaVO/dsTipoDocumento").text;
                linha_usuarioLinhaVO_nrDocumento.innerHTML = oXml.selectSingleNode("/salida/linha/usuarioLinhaVO/nrDocumento").text;
                linha_usuarioLinhaVO_dsCargo.innerHTML = oXml.selectSingleNode("/salida/linha/usuarioLinhaVO/dsCargo").text;
                linha_usuarioLinhaVO_nrContato.innerHTML = oXml.selectSingleNode("/salida/linha/usuarioLinhaVO/nrContato").text;
            }
            
            if(document.getElementById('linha_qtdLinhasTotal')){
                linha_qtdLinhasTotal.innerHTML = oXml.selectSingleNode("/salida/linha/qtdLinhasTotal").text;
                linha_qtdLinhasAtivas.innerHTML = oXml.selectSingleNode("/salida/linha/qtdLinhasAtivas").text;
                linha_qtdLinhasCanceladas.innerHTML = oXml.selectSingleNode("/salida/linha/qtdLinhasCanceladas").text;
            }    
        }   
        var tipoRelacionamiento = oXml.selectSingleNode("/salida/parametros/tipoRelacionamiento").text;
        var idProspect = oXml.selectSingleNode("/salida/parametros/idProspect").text;
        
        bindLupaLinhaUsuario(tipoRelacionamiento, idProspect);
    }

    function limparDados(){
        if(document.getElementById('linha_nrLinhaFormat')){
            linha_nrLinhaFormat.innerHTML = "";
            linha_dsTipoLinha.innerHTML = "";
            linha_dsPlanoServico.innerHTML = "";                
            linha_dsEstadoLinha.innerHTML = "";
        }

        if(document.getElementById('linha_usuarioLinhaVO_nmUsuario')){
            linha_usuarioLinhaVO_nmUsuario.innerHTML = "";
            linha_usuarioLinhaVO_dsTipoDocumento.innerHTML = "";
            linha_usuarioLinhaVO_nrDocumento.innerHTML = "";
            linha_usuarioLinhaVO_dsCargo.innerHTML = "";
            linha_usuarioLinhaVO_nrContato.innerHTML = "";
        }
        
        if(document.getElementById('linha_qtdLinhasTotal')){
            linha_qtdLinhasTotal.innerHTML = "";
            linha_qtdLinhasAtivas.innerHTML = "";
            linha_qtdLinhasCanceladas.innerHTML = "";
        }    

        bindLupaLinhaUsuario(6, "  ");
    }

    function bindLupaLinhaUsuario(tipoRelacionamiento, idProspect){
        if(tipoRelacionamiento==6 || tipoRelacionamiento==7 || idProspect!=""){
            if(document.getElementById('imgLupaLinha')){
                imgLupaLinha.onclick = null;
                imgLupaLinha.style.cursor='';
            }
            if(document.getElementById('imgLupaUsuario')){
                imgLupaUsuario.onclick = null;
                imgLupaUsuario.style.cursor='';
            }
        }else{
            if(document.getElementById('imgLupaLinha')){
                imgLupaLinha.onclick = top.frameApp.abreLupaLinha;
                imgLupaLinha.style.cursor='hand';
            }
            if(document.getElementById('imgLupaUsuario')){
                imgLupaUsuario.onclick = top.frameApp.abreLupaUsuario;
                imgLupaUsuario.style.cursor='hand';
            }
        }
    }
    </script>    
</head>
<body onload="parent.resizeIframe()">
    <acesso:controlHiddenItem nomeIdentificador="cli_dlin_verpagina">
    <table width="790" border="0" cellspacing="0" cellpadding="0">
        <tr>
			<td height="75" valign="top">
                <acesso:controlHiddenItem nomeIdentificador="cli_dlin_abrirlupalin">
                <div id="imagemLupaLinha">
                    <img src="/FrontOfficeWeb/resources/images/ti_lupa_linha.jpg" width="63" height="75" id="imgLupaLinha" >
                </div>
                </acesso:controlHiddenItem>
            </td>
			<td width="3">
                <!-- Form para possível refresh do Frame Faturamento -->
                <html:form action="loadFaturaIni.do">
                    <%
                        if(request.getSession().getAttribute("reloadLinha")!=null){
                    %>
                            <script>
                                document.forms[0].action="loadFaturaIni.do";
                                document.forms[0].target="frmFatura";
                                document.forms[0].method = "POST";
                                document.forms[0].submit();
                            </script>
                        <%}%>
                </html:form>
            </td>
			<td valign="top"><table width="724" height="75" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="211" rowspan="3" valign="top">
						<table width="211" height="75" border="0" cellpadding="0" cellspacing="0" style="border:1px solid #ADADAD;background-color:#F1F1F1;">
							<tr>
                                <td width="50"></td>
                                <td width="61"></td>
                                <td width="50"></td>
                                <td width="50"></td>
                            </tr>
                            <tr>
								<td colspan="4" style="text-indent:5px;font-weight;"><b>Telefone:&nbsp</b><netui:label id="linha_nrLinhaFormat" value="{pageFlow.tiForm.linha.nrLinhaFormat}"/></td>
							</tr>
							<tr>
								<td colspan="4" style="text-indent:5px;font-weight;"><B>Tipo de Linha:&nbsp;</B>
								<netui:label id="linha_dsTipoLinha" value="{pageFlow.tiForm.linha.dsTipoLinha}"/>
                                </td>
							</tr>
							<tr>
								<td colspan="2" style="text-indent:5px;font-weight:bold;">Plano de Servi&ccedil;o:</td>
								<td colspan="2"> <netui:label id="linha_dsPlanoServico" value="{pageFlow.tiForm.linha.dsPlanoServico}"/></td>
							</tr>
                            <tr>
                                
                               <td colspan="1" style="text-indent:5px;font-weight:bold;">Status:</td>
                               <td colspan="3">&nbsp;<netui:label id="linha_dsEstadoLinha" value="{pageFlow.tiForm.linha.dsEstadoLinha}"/></td>
                                      
                            </tr>
						</table>
                        
					</td>
					<td width="3" rowspan="3"></td>
					<td width="510" height="46" valign="top">
						<acesso:controlHiddenItem nomeIdentificador="cli_dlin_dadosusuario">
                        <table width="510" height="46" border="0" cellpadding="0" cellspacing="0" background="/FrontOfficeWeb/resources/images/bg_usuario.gif">
							<tr>
                                <td width="67" rowspan="2" valign="top">
                                <acesso:controlHiddenItem nomeIdentificador="cli_dlin_abrirlupausu"><img src="/FrontOfficeWeb/resources/images/ti_lupa_usuario.jpg" id="imgLupaUsuario"></acesso:controlHiddenItem></td>
                                <td width="3" rowspan="2"></td>
								<td width="50" style="text-indent:8px;font-weight:bold;">Nome</td>
								<td width="229"><netui:label id="linha_usuarioLinhaVO_nmUsuario" value="{pageFlow.tiForm.linha.usuarioLinhaVO.nmUsuario}"/></td>
								<td width="60" style="text-indent:8px;font-weight:bold;"><netui:label id="linha_usuarioLinhaVO_dsTipoDocumento" value="{pageFlow.tiForm.linha.usuarioLinhaVO.dsTipoDocumento}"/></td>
								<td width="101"><netui:label id="linha_usuarioLinhaVO_nrDocumento" value="{pageFlow.tiForm.linha.usuarioLinhaVO.nrDocumento}"/></td>
							</tr>
							<tr>
								<td style="text-indent:8px;font-weight:bold;">Cargo</td>
								<td><netui:label id="linha_usuarioLinhaVO_dsCargo" value="{pageFlow.tiForm.linha.usuarioLinhaVO.dsCargo}"/></td>
								<td style="text-indent:8px;font-weight:bold;">Contato</td>
								<td><netui:label id="linha_usuarioLinhaVO_nrContato" value="{pageFlow.tiForm.linha.usuarioLinhaVO.nrContato}"/></td>
							</tr>
						</table>
                        </acesso:controlHiddenItem>
					</td>
				</tr>
				<tr>
					<td height="3" valign="top"></td>
				</tr>
				<tr>
					<td height="26" valign="top"><table width="510" height="26" border="0" cellpadding="0" cellspacing="0" style="border:1px solid #ADADAD;background-color:#F1F1F1;">
						<acesso:controlHiddenItem nomeIdentificador="cli_dlin_totaislinhas">
                        <tr>                        
							<td align="center"><b>Qtd. de linhas</b> <netui:label id="linha_qtdLinhasTotal" value="{pageFlow.tiForm.qtdLinhasTotal}"/></td>
							<td align="center"><b>Linhas Ativas</b> <netui:label id="linha_qtdLinhasAtivas" value="{pageFlow.tiForm.linha.qtdLinhasAtivas}"/></td>
							<td align="center"><b>Linhas Canceladas</b> <netui:label id="linha_qtdLinhasCanceladas" value="{pageFlow.tiForm.qtdLinhasCanceladas}"/></td>
						</tr>
                        </acesso:controlHiddenItem>
					</table></td>
				</tr>
			</table></td>
		</tr>
</table>
</acesso:controlHiddenItem>
</body>
<script>

    //caso linha seja carregada sem ser pesquisa por celular
    if(document.getElementById('linha_nrLinhaFormat')){
        top.frameApp.numeroPesquisado=linha_nrLinhaFormat.innerHTML;
    }
<%
    if(request.getSession().getAttribute("reloadLinha")!=null){
    request.getSession().removeAttribute("reloadLinha");
    request.removeAttribute("reloadLinha");
%>
    parent.parent.document.getElementById('valorPesquisa').value='<%=(((ParametrosVO)session.getAttribute(ConstantesCRM.SPARAMETROS)).getNrLinha()!=null?((ParametrosVO)session.getAttribute(ConstantesCRM.SPARAMETROS)).getNrLinha():ConstantesCRM.SVAZIO)%>';
    tipoRelacionamiento = '<%=(((ParametrosVO)session.getAttribute(ConstantesCRM.SPARAMETROS)).getIdTipoRelacionamento()!=null?((ParametrosVO)session.getAttribute(ConstantesCRM.SPARAMETROS)).getIdTipoRelacionamento():ConstantesCRM.SVAZIO)%>';
    idProspect = '';
    bindLupaLinhaUsuario(tipoRelacionamiento, idProspect);
<%}%>

</script>