<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<bean:define id="Form"
             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"
             property="alteracaoNumeroSMSForm"
             type="cliente.TelaInicial.TelaInicialController.AlteracaoNumeroSMSForm"/>

<html>

<head>
	<script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/formatPhoneNumber.js"></script>
    <script type="text/javascript" language="javascript">


		var isProspect = <bean:write name="Form" property="prospect" />;

        getListaLinhas = function(o) {
            var index = o.selectedIndex;
            if (index > 0) {
                new Ajax.Request('alterarNumeroSMS.do?action=getListaLinhas&indexConta=' + (index-1), {
                    method: 'post',
                    onSuccess: function(e) {
                        if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                        var dom = parseXml(e.responseText);
                        var jsonString = xml2json(dom, '');
                        var jsonObj = jsonString.evalJSON();

						emptyList($('nrLinhaSMS'));
						addSelectOption($('nrLinhaSMS'), '-- Selecione --', '');

						if (jsonObj.LFContasFaturamento.LFLinhas.length) {
                            $('divAlterarNumeroSMSPesquisar').show();
							var arrayLinhas = jsonObj.LFContasFaturamento.LFLinhas;
							for (var i = 0; i < arrayLinhas.length; i++) {
                                addSelectOption($('nrLinhaSMS'), getFormattedTelNumber(arrayLinhas[i]), getFormattedTelNumber(arrayLinhas[i]));
                            }
                        } else {
							$('divAlterarNumeroSMSPesquisar').hide();
							addSelectOption($('nrLinhaSMS'), getFormattedTelNumber(jsonObj.LFContasFaturamento.LFLinhas), getFormattedTelNumber(jsonObj.LFContasFaturamento.LFLinhas));
						}

						new Autocomplete('query', {
							serviceUrl : 'alterarNumeroSMS.do?format=json&action=searchLinhas&indexConta=' + (index-1),
							minChars:2,
							maxHeight:80,
							width:80,
							onSelect: function(value, data){
								$('nrLinhaSMS').selectedIndex = data + 1;
								$('query').value = '';
							}
						});

                    }, onCreate: function() {
                        if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();
					}, onException: function(e) {
						if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                        window.top.frameApp.createErrorPopUp('erroAlterarNumeroSMS', e.statusText, e.responseText, false);
                    }, on406: function(e) {
                        window.top.frameApp.createErrorPopUp('erroAlterarNumeroSMS', e.statusText, e.responseText, false);
                        if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                    }
                });
            } else {
				emptyList($('nrLinhaSMS'));
				addSelectOption($('nrLinhaSMS'), '-- Selecione uma Conta --', '')
                $('query').value = '';
            }
        }

		getFormattedTelNumber = function(LFLinhas) {
			return '(' + LFLinhas.nrCodArea + ')' + formatPhoneNumber(LFLinhas.nrLinha);
			//return '(' + LFLinhas.nrCodArea + ')' + LFLinhas.nrLinha.substring(0,4) + '-' + LFLinhas.nrLinha.substring(4);
		}

		addSelectOption = function(selectObj, text, value) {

			var elOptNew = document.createElement('option');
			elOptNew.text = text;
			elOptNew.value = value;

			try {
				selectObj.add(elOptNew, null); // standards compliant; doesn't work in IE
			}
			catch(ex) {
				selectObj.add(elOptNew); // IE only
			}
		}

		emptyList = function(objList) {
			var nodes = objList.options;
			if (nodes.length > 0) {
				while (nodes.length > 0)
					nodes[0] = null;
			}
		}

        fieldsValidation = function() {
            var imperativo = isProspect ? 'digite' : 'selecione';
			if ($F('nrLinhaSMS').blank() || $F('nrLinhaSMS').length < 13) {
                alert('Por favor, ' + imperativo + ' um número de linha.')
                return false;
            }
            return true;
        }

        setAlterarNumeroSMS = function() {

            var method;
			if (fieldsValidation()) {

                var params = $H();
				params.set('action', 'setAlterarNumeroSMS');

				if (isProspect) {
					method = 'post';
					params.set('nrLinha', $F('nrLinhaSMS'));
				} else {
					method = 'get';
					var indexConta = $('idContaSMS').selectedIndex - 1;
	                var indexLinha = $('nrLinhaSMS').selectedIndex - 1;
	                params.set('indexConta', indexConta);
	                params.set('indexLinha', indexLinha);
				}

                new Ajax.Request('alterarNumeroSMS.do', {
                    method: method,
                    parameters: params,
                    onSuccess: function(e) {
						if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();

						var dom = parseXml(e.responseText);
						var jsonString = xml2json(dom, '');
						var jsonObj = jsonString.evalJSON();

						if (jsonObj.AlterProtocoloOutTO.cdError
							&& jsonObj.AlterProtocoloOutTO.cdError != 0) {
							alert(jsonObj.AlterProtocoloOutTO.msgError);
						} else {
							alert('Operação realizada com sucesso!');
							$('alterarNumeroSMS').remove();
						}
                    }, onCreate: function() {
                        if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();
					}, onComplete: function() {
						if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
					}, onException: function(e) {
						alert(e.description);
                    }, on406: function(e) {
                        window.top.frameApp.createErrorPopUp('erroAlterarNumeroSMS', e.statusText, e.responseText, false);
                        if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                    }
                });
            }
        }

        updateNrProtocolo = function() {
            top.frameApp.updateProtocolo(<%=request.getSession().getAttribute(ConstantesCRM.NRPROTOCOLO)%>);
        }

		if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();

    </script>

</head>

<body>

<div id="bodyAlterarNumeroSMS">

    <table width="100%" height="117" cellspacing="2" cellpadding="0">

		<tr>
            <td colspan="3">
                O SMS com protocolo será enviado para o número:
                <strong><bean:write name="Form" property="nrLinhaSMS" /></strong>
            </td>
        </tr>
        <logic:equal name="Form" property="prospect" value="false">
		<tr>
            <td>Conta:</td>
            <td>
                <bean:define id="listaContas" name="Form" property="listaContas" />
                <html:select name="Form" property="idConta" styleId="idContaSMS" onchange="getListaLinhas(this)">
                    <html:option value="">-- Selecione --</html:option>
                    <html:options collection="listaContas" property="idConta" labelProperty="nrConta"/>
                </html:select>
            </td>
        </tr>
        <tr>
            <td width="50" nowrap>Linha:</td>
            <td width="160" nowrap>
                <html:select name="Form" property="nrLinha" styleId="nrLinhaSMS">
                    <option value="">-- Selecione uma Conta --</option>
                </html:select>
            </td>
            <td width="180" nowrap>

				<div id="divAlterarNumeroSMSPesquisar" style="display:none">
					Pesquisar:

					<html:text name="Form" property="nrLinhaPesquisa" styleId="query" />

				</div>

            </td>
        </tr>

		</logic:equal>

		<logic:equal name="Form" property="prospect" value="true">
		<tr>
            <td nowrap>Digite o número da linha:</td>
            <td width="80%">
                <html:text name="Form" property="nrLinha" styleId="nrLinhaSMS" onblur="formatPhoneNumberObj(this)" maxlength="11" /> <!--onkeydown="checaTelefone(this)" onkeyup="checaTelefone(this)"-->
            </td>
        </tr>
		</logic:equal>

        <tr>
            <td colspan="3" align="right" style="padding-right:20px;">
                <img src="<%=request.getContextPath()%>/resources/images/bt_gravar_nrml.gif" onmouseup="setAlterarNumeroSMS()" style="cursor:pointer" />
            </td>
        </tr>
    </table>
</div>

<style type="text/css">
    #bodyAlterarNumeroSMS {
        background-color:#ededed;
        padding:5px;
        font-size:11px;
        margin:0;
    }
	#bodyAlterarNumeroSMS select {width:150px}
	#bodyAlterarNumeroSMS input {width:95px}
</style>

<vivo:alert scope="request" atributo="msgNovoProtocolo" afterFunction="updateNrProtocolo()" />

</body>

</html>