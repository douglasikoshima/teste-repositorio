<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>

<bean:define id="Form"
             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"
             property="lupaFaturamentoPos"
             type="cliente.TelaInicial.DetalheFatura.DetalheFaturaController.LupaFaturamentoPosForm" />

<html>
<head>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
    <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js" ></script>
	<script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js" ></script>
	<script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/messages.js" ></script>
	<script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/formatPhoneNumber.js" ></script>
    <style type="text/css">
        form {
            padding: 0;
            margin: 0;
        }
        * {
            font-family:Tahoma,Verdana,Arial;
            font-size:11px;
        }
        #divFatura {
            display:block;
            width:793px;
            height:593px;
            overflow:auto;
        }
        #divBuscaRapida {
            padding:5px;
            position:absolute;
            z-index:2;
            left:350px;
            top:30px;
            border:1px dotted #adadad;
            background:#ededed;
        }
        #divBuscaRapida table {
            margin:0;
            padding:0;
        }
        #divBuscaRapida table thead td {
            font-size:9px;
            text-indent:3px;
            height:10px;
        }
    </style>
    <script type="text/javascript" language="javascript">

        var f;
        var dataVencimento = '<bean:write name="dataVencimento" scope="request" />';

        onload = function() {
            f = document.forms[0];
			if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
            <logic:present name="bufferContaOnline" scope="session">
            setPagina();
			Event.observe('divFatura', 'scroll', function(event) {
				if ($('divFatura').cumulativeScrollOffset()[1] < 150) {
					$('divBuscaRapida').show();
				} else {
					$('divBuscaRapida').hide();
				}
            });
            </logic:present>
        }
        incrementaPagina = function() {
            var pagina = 2;
            if (<bean:write name="Form" property="faturaPaginaAtual" format="#" /> != 'Boleto') {
                pagina = Number(<bean:write name="Form" property="faturaPaginaAtual" format="#" />)+1;
            } else {
                $('paginaDestino').value = 'Boleto';
            }
            f.action = 'consultarImagemFatura.do?data=<bean:write name="Form" property="faturaCicloSelecionado" />&nrPagina=' + pagina + '&tipoArquivo=GIF&dataVencimento=' + dataVencimento;
            if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();
            f.submit();
        }

        decrementaPagina = function() {
            var pagina = Number(<bean:write name="Form" property="faturaPaginaAtual" format="#" />)-1;
            $('paginaDestino').value = pagina;
            f.action = 'consultarImagemFatura.do?data=<bean:write name="Form" property="faturaCicloSelecionado" />&nrPagina=' + pagina + '&tipoArquivo=GIF&dataVencimento=' + dataVencimento;
            if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();
            f.submit();
        }

        irParaPagina = function() {
            var pagina = ($F('paginaDestino').toLowerCase() == 'boleto') ? 1 : Number($F('paginaDestino')) + 1;
            var paginaDestino = $F('paginaDestino').toLowerCase() == 'boleto' ? 1 : Number($F('paginaDestino'));
            if (paginaDestino == '' || paginaDestino == 0 || isNaN(paginaDestino)) {
                window.alert('Informe o número de página corretamente.');
                return false;
            }
            if (Number(pagina) > Number(<bean:write name="Form" property="faturaQtdePaginas" format="#" />)) {
                var qtdPaginas = Number(<bean:write name="Form" property="faturaQtdePaginas" format="#" />)-1;
                window.alert('Informe uma página entre 1 - ' + qtdPaginas);
                return false;
            }
            f.action = 'consultarImagemFatura.do?data=<bean:write name="Form" property="faturaCicloSelecionado" />&nrPagina='+pagina+'&tipoArquivo=GIF&dataVencimento=' + dataVencimento;
            if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();
            f.submit();
        }

        primeiraPagina = function() {
            var qtdPaginas = <bean:write name="Form" property="faturaQtdePaginas" format="#" />;
            if (qtdPaginas >= 2) {
                f.action = 'consultarImagemFatura.do?data=<bean:write name="Form" property="faturaCicloSelecionado" />&nrPagina=2&tipoArquivo=GIF&dataVencimento=' + dataVencimento;
                if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();
                f.submit();
            } else {
                window.alert('Paginação incorreta');
            }
        }

        ultimaPagina = function() {
            var qtdPaginas = <bean:write name="Form" property="faturaQtdePaginas" format="#" />;
            f.action = 'consultarImagemFatura.do?data=<bean:write name="Form" property="faturaCicloSelecionado" />&nrPagina=' + qtdPaginas + '&tipoArquivo=GIF&dataVencimento=' + dataVencimento;
            if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();
            f.submit();
        }

        visualizarBoleto = function() {
            f.action = 'consultarImagemFatura.do?data=<bean:write name="Form" property="faturaCicloSelecionado" />&nrPagina=1&tipoArquivo=GIF&dataVencimento=' + dataVencimento;
            if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();
            f.submit();
        }

        visualizarNotaFiscal = function() {
            f.action = 'consultarImagemFatura.do?data=<bean:write name="Form" property="faturaCicloSelecionado" />&nrPagina=2&tipoArquivo=GIF&dataVencimento=' + dataVencimento;
            if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();
            f.submit();
        }

        setPagina = function() {
            var pagina = Number(<bean:write name="Form" property="faturaPaginaAtual" format="#" />)-1;
            if (pagina == 0)
                pagina = 'Boleto';
            $('paginaDestino').value = pagina;
        }

        enviarCodigoBarras = function() {
            //if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();
            var action = '/FrontOfficeWeb/cliente/TelaInicial/DetalheFatura/manageCodigoBarras.do?inTipoEnvio=true&dataVencimento=' + dataVencimento;
            window.top.frameApp.createNewPopUp("codigoBarras", 'Tipo de Envio', 350, 250, null, action, true, 'teste');
        }

        buscaRapida = function() {
			if ($F('busca_ddd').length != 2) {
				alert(Messages.DG_DDD_VALIDO);
			} else if ($F('busca_telefone').length != 8 && $F('busca_telefone').length != 9) {
				alert(Messages.DG_TELEFONE_NUMERO_VALIDO);
			} else {
				var numero = $F('busca_ddd') + $F('busca_telefone');
				f.action = 'consultarImagemFatura.do?data=<bean:write name="Form" property="faturaCicloSelecionado" />&terminal_origem=' + numero + '&tipoArquivo=GIF&dataVencimento=' + dataVencimento;
				if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();
				f.submit();
			}
        }

    </script>

</head>

<body style="margin:0;">

<logic:present name="bufferContaOnline" scope="session">

 	<logic:present name="isNovaFatura" scope="request">
        <div id="divBuscaRapida" style="position:absolute; left:545px" >
    </logic:present>
    <logic:notPresent name="isNovaFatura" scope="request">
        <div id="divBuscaRapida" style="position:absolute; left:450px" >
    </logic:notPresent>
        <table>
            <thead>
                <tr>
                    <td></td>
                    <td valign="bottom">DDD</td>
                    <td valign="bottom">Telefone</td>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>Busca r&aacute;pida:</td>
                    <td><input type="text" name="busca_ddd" id="busca_ddd" style="width:20px;" maxlength="2" onkeyup="checaInteiro(this);if(this.value.length>1){$('busca_telefone').focus()}" onblur="checaInteiro(this)" onfocus="checaInteiro(this)" /></td>
                    <td><input type="text" name="busca_telefone" id="busca_telefone" style="width:60px;" maxlength="9" onkeyup="checaInteiro(this)" onblur="checaInteiro(this)" onfocus="checaInteiro(this)" /></td>
                </tr>
                <tr>
                    <td colspan="3" align="right">
                        <a href="javascript:buscaRapida()"><img src="<%=request.getContextPath()%>/resources/images/bt_buscar_nrml.gif" border="0" /></a>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>

    <div id="divFatura">

        <img src="<%=request.getContextPath()%>/ContaOnlineServlet">

        <div id="paginacao" align="center">
            <br>
            <html:form action="/cliente/TelaInicial/DetalheFatura/consultarImagemFatura.do" onsubmit="return irParaPagina();" method="post">
                <table border="0" cellpadding="0" cellspacing="2" width="780">
                    <tbody>
                        <tr>
                            <bean:define id="total" name="Form" property="faturaQtdePaginas" />
                            <td width="30">
                                <logic:greaterThan name="Form" property="faturaPaginaAtual" value="2">
                                    <img style="cursor:pointer" src="<%=request.getContextPath()%>/resources/images/bt_primeira_nrml.gif" onmouseup="primeiraPagina()" />
                                </logic:greaterThan>
                            </td>
                            <td>&nbsp;</td>
                            <td width="30" align="center">
                                <logic:greaterThan name="Form" property="faturaPaginaAtual" value="2">
                                    <img style="cursor:pointer" src="<%=request.getContextPath()%>/resources/images/bt_anterior_nrml.gif" onmouseup="decrementaPagina()" />
                                </logic:greaterThan>
                            </td>
                            <td align="center">
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                Página  (<input type="text" id="paginaDestino" name="paginaDestino" size="6" value="1" style="text-align:center;border:none" />)
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            </td>
                            <td align="right" width="30">
                                <logic:lessThan name="Form" property="faturaPaginaAtual" value="<%=String.valueOf(Integer.parseInt(total.toString()))%>">
                                    <img style="cursor:pointer" src="<%=request.getContextPath()%>/resources/images/bt_proxima_nrml.gif" onmouseup="incrementaPagina()" />
                                </logic:lessThan>
                            </td>
                            <td>&nbsp;</td>
                            <td align="right" width="30">
                                <logic:lessThan name="Form" property="faturaPaginaAtual" value="<%=String.valueOf(Integer.parseInt(total.toString()))%>">
                                    <img style="cursor:pointer" src="<%=request.getContextPath()%>/resources/images/bt_ultima_nrml.gif" onmouseup="ultimaPagina()" />
                                </logic:lessThan>
                            </td>
                        </tr>
                        <tr><td>&nbsp;</td></tr>
                        <tr align="center">
                            <td colspan="8" align="center">
                                <logic:greaterThan name="Form" property="faturaPaginaAtual" value="1">
                                    <img src="<%=request.getContextPath()%>/resources/images/bt_visualizarboleto_nrml.gif" onmouseup="visualizarBoleto()" style="cursor:pointer;margin-right:15px;" />
                                </logic:greaterThan>
                                <logic:equal name="Form" property="faturaPaginaAtual" value="1">
                                    <img src="<%=request.getContextPath()%>/resources/images/bt_visualizarnotafiscal_nrml.gif" onmouseup="visualizarNotaFiscal()" style="cursor:pointer;margin-right:15px;" />
                                </logic:equal>
                                &nbsp;
                                <img src="<%=request.getContextPath()%>/resources/images/bt_irparapagina_nrml.gif" onmouseup="irParaPagina()" style="cursor:pointer;" />
                                <br/>
                                <!--img src="<%=request.getContextPath()%>/resources/images/bt_enviarcdbarras_nrml.gif" onmouseup="enviarCodigoBarras()" style="cursor:pointer;margin-top:15px;" /-->
                            </td>
                        </tr>
                        <tr><td>&nbsp;</td></tr>
                    </tbody>
                </table>
            </html:form>
        </div>

    </div>

</logic:present>

<logic:notPresent name="bufferContaOnline" scope="session">
    <div>
		<br><br>
		<table width="100%" border="0" height="90%" cellpadding="0" cellspacing="0">
		  <tbody>
				<tr align="center">
					<td align="center" valign="middle">
                        <logic:present name="msgErro">
                            <br/><bean:write name="msgErro" scope="request" />
                        </logic:present>
                        <logic:notPresent name="msgErro">
                            No momento este servi&ccedil;o n&atilde;o est&aacute; dispon&iacute;vel. Por favor, tente mais tarde.
                        </logic:notPresent>
					</td>
	   			</tr>
		  </tbody>
		</table>
	</div>
</logic:notPresent>

<vivo:alert atributo="msgRetorno" scope="request" />

</body>
</html>