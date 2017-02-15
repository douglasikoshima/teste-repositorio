<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<bean:define id="FormArvoreContato"             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreContato" type="admsistemas.admArvoreContato.AdmArvoreContatoController.FormArvoreContato" />
<bean:define id="AdmNomeContatoVO"              name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreContato.admNomeContatoVO"/>
<bean:define id="AdmIndicadorAnatelExistenteVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreContato.admIndicadoresAnatelExistentesVO"/>
<bean:define id="AdmIndicadorAnatelAssociadoVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreContato.admIndicadoresAnatelAssociadosVO"/>
<bean:define id="aMensagem"                     name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreContato.listaMensagem"/>
<bean:define id="aFechamento"                   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreContato.listaFechamento"/>
<bean:define id="aRetorno"                      name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreContato.listaRetorno"/>
<bean:define id="aProcesso"                     name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreContato.listaProcesso"/>

<netui-template:template templatePage="/resources/jsp/templateUsuarioClean.jsp">
<netui-template:section name="headerSection">
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/tratamento.js" ></script>
<script type="text/javascript" language="javascript">

    var f;

    transfereSelecaoLista = function(listaOrigem, listaDestino) {
        var i;
        for (i = 0; i<listaOrigem.options.length; i++)
            if (listaOrigem.options[i].selected)
                listaDestino.options[listaDestino.options.length] = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);
        for (i = listaOrigem.options.length-1; i>=0; i--)
            if (listaOrigem.options[i].selected)
                listaOrigem.options[i] = null;
    }

    ajustaTamanho = function(obj) {
        if (obj.readyState == 'complete') {
            iframe = parent.parent.document.getElementById('frameDetalhe');
            div = parent.document.getElementById('detalhe');
            altura  = parent.parent.frameDetalhe.document.body.scrollHeight;
            largura = 600;
            iframe.style.width = largura;
            iframe.style.height = altura;
            iframe.style.top = (600 - altura) / 2;
            iframe.style.left = (800 - largura) / 2;
            div.style.width = largura;
            div.style.height = altura;
            div.style.top = (545 - altura) / 2;
            div.style.left = (815 - largura) / 2;
        }
    }

    camposPasta = function(valor) {
        var comboQtdCopia = parent.parent.document.getElementById('qtdCopia');
        comboQtdCopia.style.visibility = 'hidden';
        $('inFolha').value = valor;
        $('divnome').style.display = 'none';
    }

    camposFolha = function(valor) {
        $('inFolha').value = valor;
        $('divnome').style.display = 'block';
    }

    escondeAmbos = function(valor) {
        $('msg').value = valor;
        $('divLista1').style.visibility = 'hidden';
        $('divLista2').style.visibility = 'hidden';
        $('mensagem').value = '';
        $('mensagem').selectedIdex = 0;
    }

    escondeLista = function(valor) {
        $('msg').value = valor;
        $('divLista1').style.visibility = 'hidden';
        $('divLista2').style.visibility = 'visible';
        $('mensagem').selectedIdex = 0;
        $('mensagem').value = '';
        $('idNomeContatoEscolhido').selectedIndex = 0;
    }

    exibeLista = function(valor) {
        $('mensagem').selectedIndex = 0;
        $('msg').value = valor;
        $('divLista1').style.visibility = 'visible';
        $('divLista2').style.visibility = 'hidden';
    }

    escondeTipo = function(valor) {
        $('nomeTipo').value = valor;
        $('divTipo1').style.visibility = 'hidden';
        $('divTipo2').style.visibility = 'visible';
        $('nomeContato').style.display = 'none';
        $('pesquisaNome').style.display = 'none';
        $('idNomeContatoEscolhido').value = '';
        $('idNomeContatoEscolhido').selectedIndex = 0;
    }

    exibeTipo = function(valor) {
        $('nomeTipo').value = valor;
        $('divTipo1').style.visibility = 'visible';
        $('divTipo2').style.visibility = 'hidden';
        $('pesquisaNome').style.display = 'block';
        $('nomeContato').style.display = 'none';
        $('NomeContatoEscolhido').value = '';
    }

    salvar = function() {

        if (valida()) {

            if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();
            if ($F('nomeTipo') == '2') {
                $('idNomeContatoEscolhido').selectedIndex = 0;
                $('NomeContatoEscolhido').value = '';
            }

            if ($F('salvaedita') == 'salva') {
                f.action = 'insereItem.do';
            } else {
                f.action = 'salvaItemEditado.do';
            }

            f.target = 'frameApp';
            f.submit();
        }
    }

    // Validação de folhas e pastas.
    valida = function () {

        if ($F('inFolha') != '0' && $F('inFolha') != '1') {
            alert('Preencha o campo tipo.');
            return false;
        }

        if ($('divTipo2').style.visibility == 'visible'
                && $F('NomeContatoEscolhido').blank()) {
            alert('Preencha o campo nome tipo.');
            $('NomeContatoEscolhido').focus();
            return false;

        } else if ($('divTipo1').style.visibility == 'visible'
                  && ($('idNomeContatoEscolhido').selectedIndex == 0
                        || $('idNomeContatoEscolhido').selectedIndex == -1)) {
            alert('Por favor, selecione um nome tipo.');
            $('idNomeContatoEscolhido').focus();
            return false;
        }

        // Folha
        if ($F('inFolha') == '1') {

            if ($('qtDiasPrazoContato').selectedIndex == 0) {
                alert('Preencha o campo Prazo de Atendimento.');
                return false;
            }
            if ($('processoTec').selectedIndex == 0) {
                alert('Selecione um Processo Técnico.');
                return false;
            }
            if ($('qtDiasPrazoAnatel').selectedIndex == 0) {
                alert('Preencha o campo Prazo C. Prévio.');
                return false;
            }
            if ($('qtDiasPrazoAnatel').selectedIndex == 0) {
                alert('Preencha o campo Prazo C. Prévio.');
                return false;
            }
            if ($('vlPesoContato').selectedIndex == 0) {
                alert('Preencha o campo Peso/Prioridade.');
                return false;
            }
            if ($('msg').value == '1' && $('mensagem').selectedIndex == 0) {
                alert('Selecione uma Mensagem de Aviso.');
                return false;
            }
            if ($('qtDiasPrazoContato').value == '0'
                    || $('qtDiasPrazoContato').value == '00'
                    || $('qtDiasPrazoContato').value == '000'
                    || $('qtDiasPrazoContato').value == '0000') {
                alert('O campo Prazo de Atendimento deve ser maior que 0.');
                return false;
            }
            if ($('msg').value == '0' && $F('mensagemNova').blank()) {
                alert('Preencha uma nova Mensagem de Aviso.');
                return false;
            }
            if ($('sgRegraEncaminhamento').selectedIndex == 0) {
                alert('Selecione uma Regra de Encaminhamento.');
                return false;
            }
			if ($('inSMS').value == '') {
                alert('O campo seleção de Envio de SMS é obrigatório!');
                return false;
            } else if ($('inSMS').value == '1' && !$('inProtocolo').checked) {
				alert('É necessário habilitar este contato para visualização no histórico de protocolo dos canais eletrônicos.');
				return false;
			}
            if ($('idClassificacaoSMS').value == '') {
                alert('Por favor, selecione uma opção para Classificação de Envio de SMS.');
                setTimeout(function(){$('idClassificacaoSMS').focus()},300);
                return false;
            }
        }

        // Processa gravação
        for (i = 0; i < $('arrayAdmIndicadorAnatelAssociado').length; i++) {
            $('arrayAdmIndicadorAnatelAssociado').options[i].selected = true;
        }
        return true;
    }

    limpaNome = function() {
        $('NomeContatoEscolhido').value = '';
    }

    pesquisar = function() {
        if ($F('descPesquisa').strip().length < 3) {
            alert('É necessário digitar no mínimo 3 caracteres para pesquisa.');
            $('descPesquisa').focus();
            return false;
        }
        f.target = 'ifmInnerBrowserContato';
        f.action = 'getNome.do';
        if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();
        f.submit();
    }

    exibeAtual = function() {
        $('nomeTipo').value = 3;
        $('idNomeContatoEscolhido').selectedIndex = 0;
        $('NomeContatoEscolhido').value = '';
        $('nomeContato').style.display = 'block';
        $('pesquisaNome').style.display = 'none';
        $('divTipo1').style.visibility = 'hidden';
        $('divTipo2').style.visibility = 'hidden';
    }

    onload = function() {

        if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();

        f = document.forms[0];
        document.body.style.backgroundColor = '#ededed';
        var op = '<bean:write name="FormArvoreContato" property="inFolha"/>';

        // Folha
        if (op == '1') {

            if ($('sgRegraEncaminhamento').value == 'MC'
                    && $('sgFluxoAtendimento').value == '') {
                $('sgFluxoAtendimento').value = 'MC3';
            }

            camposFolha('1');
            $('qtDiasPrazoContato').value = '<bean:write name="FormArvoreContato" property="qtDiasPrazoContato"/>';
            $('qtDiasPrazoAnatel').value = '<bean:write name="FormArvoreContato" property="qtDiasPrazoAnatel"/>';
            <logic:equal name="FormArvoreContato" property="vlPesoContato" value="-1">
            $('vlPesoContato').selectedIndex = 0;
            </logic:equal>
            <logic:notEqual name="FormArvoreContato" property="vlPesoContato" value="-1">
            $('vlPesoContato').value = '<bean:write name="FormArvoreContato" property="vlPesoContato"/>';
            </logic:notEqual>
        }
        // Pasta
        else if (op == '0')
            camposPasta('0');

        else if (op == '') {
            $('inFolha').value = "1";
            $('msg').value = 1;
            $('qtDiasPrazoContato').value = '120';
            $('qtDiasPrazoAnatel').value = '120';
        }

        <logic:equal name="FormArvoreContato" property="salvaedita" value="salva">
            exibeTipo(1);
        </logic:equal>

        $('idNomeContatoEscolhido').value = '<bean:write name="FormArvoreContato" property="idNomeContato"/>';
        $('mensagem').value = '<bean:write name="FormArvoreContato" property="idMensagemAtual" />';
        if ($('mensagem').selectedIndex == 0) {
            escondeAmbos(2);
        }

    }

</script>

<style type="text/css">
    input {background:none}
    #nomeContato {
        display:block;
        position:absolute;
        top:70px;
        left:180px;
        height:0;
        padding:0
    }
</style>

</netui-template:section>
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="adm_ceico_verpagina">
        <form action="insereItem" id="insereItem" name="insereItem" onSubmit="return false;">

            <html:hidden name="FormArvoreContato" property="idContato" styleId="idContato" />
            <html:hidden name="FormArvoreContato" property="nmContato" styleId="nmContato" />
            <html:hidden name="FormArvoreContato" property="idContatoPai" styleId="idContatoPai" />
            <html:hidden name="FormArvoreContato" property="inDisponibilidade" styleId="inDisponibilidade" />
            <html:hidden name="FormArvoreContato" property="idNomeContato" styleId="idNomeContato" />
            <html:hidden name="FormArvoreContato" property="nrNivel" styleId="nrNivel" />
            <html:hidden name="FormArvoreContato" property="inFolha" styleId="inFolha" />
            <html:hidden name="FormArvoreContato" property="dsPath" styleId="dsPath" />
            <html:hidden name="FormArvoreContato" property="salvaedita" styleId="salvaedita" />
            <html:hidden name="FormArvoreContato" property="sgFluxoAtendimento" styleId="sgFluxoAtendimento" />
            <html:hidden property="nomeTipo" value="3" styleId="nomeTipo" />
            <input type="hidden" name="msg" id="msg" value="2">

        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
        <vivo:quadro id="descricao" width="100%" height="100" label='Descrição'>
            <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                <tr><td height="10" colspan="2"></td></tr>
                <tr>
                    <td width="12%" class="tblEstatica_campoNome" height="20" valign="top">
                        <netui:label value="Caminho: "/>
                    </td>
                    <td colspan="3" align="left" valign="top">
                        <script>document.write('<bean:write name="FormArvoreContato" property="dsPath"/>');</script>
                    </td>
                </tr>
                <tr>
                    <td width="15%" class="tblEstatica_campoNome">
                        <netui:label value="Tipo: "/>
                    </td>
                    <td width="35%" align="left" colspan="2">
                        <input type="radio" name="inFolhaForm" id="checkboxFolha" value="1" class="radio" onclick="camposFolha('1');" <logic:equal name="FormArvoreContato" property="inFolha" value="1">checked</logic:equal><logic:equal name="FormArvoreContato" property="inFolha" value="">checked</logic:equal>><span>&nbsp;Folha</span>
                        <input type="radio" name="inFolhaForm" id="checkboxPasta" value="0" class="radio" onclick="camposPasta('0');" <logic:equal name="FormArvoreContato" property="inFolha" value="0">checked</logic:equal>><span>&nbsp;Pasta</span>
                    </td>
                </tr>
                <tr>
                    <td width="15%" class="tblEstatica_campoNome">
                        <netui:label value="Nome: " defaultValue="0"/>
                    </td>
                    <td width="25%" align="left" colspan="2">
                        <div id="nomeContato">
                            <vivo:hint maxLength="20">
                                <bean:write name="FormArvoreContato" property="nmContato"/>
                            </vivo:hint>
                        </div>
                        <logic:equal name="FormArvoreContato" property="salvaedita" value="salva">
						
						<input type="radio" name="nomeTipo" id="nomeTipo" value="1" class="radio" onclick="exibeTipo('1');" tabindex="0" <logic:equal name="FormArvoreContato" property="nomeTipo" value="1">checked</logic:equal><logic:equal name="FormArvoreContato" property="nomeTipo" value="">checked</logic:equal>><span>&nbsp;Atual</span>
						
						<input type="radio" name="nomeTipo" id="nomeTipo" value="0" class="radio" onclick="escondeTipo('0');" tabindex="1" <logic:equal name="FormArvoreContato" property="nomeTipo" value="0">checked</logic:equal>><span>&nbsp;Novo</span>
						
                        </logic:equal>
                        <logic:notEqual name="FormArvoreContato" property="salvaedita" value="salva">
						
						<input type="radio" name="nomeTipo" id="nomeTipo" value="2" class="radio" onclick="exibeAtual('0');" tabindex="2" <logic:equal name="FormArvoreContato" property="nomeTipo" value="2">checked</logic:equal><logic:equal name="FormArvoreContato" property="nomeTipo" value="">checked</logic:equal>><span>&nbsp;Atual</span>
						
						<input type="radio" name="nomeTipo" id="nomeTipo" value="0" class="radio" onclick="escondeTipo('0');" tabindex="0" <logic:equal name="FormArvoreContato" property="nomeTipo" value="0">checked</logic:equal>><span>&nbsp;Novo</span>
						
						<input type="radio" name="nomeTipo" id="nomeTipo" value="1" class="radio" onclick="exibeTipo('1');" tabindex="1" <logic:equal name="FormArvoreContato" property="nomeTipo" value="1">checked</logic:equal>><span>&nbsp;Existente</span>
												
                        </logic:notEqual>
                    <div id="pesquisaNome" style="display:none;position:absolute; height: 0px; padding: 0px;width:210px;">
                        <html:text name="FormArvoreContato" property="descPesquisa" styleId="descPesquisa" maxlength="30"/>
                        <img src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif"
                                           style="border:none;cursor:hand"
                                           onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif';"
                                           onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif';"
                                           onClick="return pesquisar();" />
                    </div>
                    </td>
                </tr>
                <tr>
                    <td style="padding-left:6px;" colspan="4">
                        <div id="divTipo3" style="visibility:'visible'; position:relative; top: 0px; left: 0px; padding: 0px;">
                            <div id="divTipo1" style="visibility:'hidden'; position:absolute; top: 0px; left: 85px; height: 0px; padding: 0px;">
                                <html:select name="FormArvoreContato" property="idNomeContatoEscolhido" styleId="idNomeContatoEscolhido" style="text-indent:3px;width:370px;left: -150px;" styleClass="SELECT" onchange="limpaNome();">
                                    <html:option value="">Escolha uma opção...</html:option>
                                </html:select>
                            </div>
                            <div id="divTipo2" style="visibility:'hidden'; position:absolute; top: 0px; left: 85px; height: 0px; padding: 0px;">
                                <input name="NomeContatoEscolhido" id="NomeContatoEscolhido" type="text" style="width:370px" class="input" maxlength="240" value="">
                            </div>
                        </div>
                    </td>
                </tr>
            </table>
        </vivo:quadro>
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
        <div id="divnome" style="visibility:'visible'; position:relative; top: 0px; left: 0px; height: 24px; padding: 0px;">
        <vivo:quadro id="dadosBasicos" width="100%" height="150" label="Dados Básicos">
            <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                <tr>
                    <td class="tblEstatica_campoNome" height="30" valign="top">
                        <netui:label value="Prazo de Atendimento:" styleClass="tblEstatica_campoNome"/>
                    </td>
                    <td align="left" valign="top">
                        <html:select name="FormArvoreContato" property="qtDiasPrazoContato" styleId="qtDiasPrazoContato" style="text-indent:3px;width:70px" styleClass="SELECT">
                            <html:option value="">Escolha...</html:option>
                            <logic:iterate name="FormArvoreContato" property="admPrazo" id="prazo">
                                <option value='<bean:write name="prazo" property="vlPrazo"/>'><bean:write name="prazo" property="vlPrazo"/></option>
                            </logic:iterate>
                        </html:select>
                    </td>
                    <td class="tblEstatica_campoNome" height="30" valign="top">
                        <netui:label value="Prazo C. Prévio:" styleClass="tblEstatica_campoNome"/>
                    </td>
                    <td align="left" valign="top">
                        <html:select name="FormArvoreContato" property="qtDiasPrazoAnatel" styleId="qtDiasPrazoAnatel" style="text-indent:3px;width:70px" styleClass="SELECT">
                            <html:option value="">Escolha...</html:option>
                            <logic:iterate name="FormArvoreContato" property="admPrazoAnatel" id="prazoAnatel">
                                <option value='<bean:write name="prazoAnatel" property="vlPrazoAnatel"/>'><bean:write name="prazoAnatel" property="vlPrazoAnatel"/></option>
                            </logic:iterate>
                        </html:select>

                    </td>
                </tr>
                <tr>
                    <td class="tblEstatica_campoNome" height="30" valign="top">
                        <netui:label value="Processo Técnico:" styleClass="tblEstatica_campoNome"/>
                    </td>
                    <td align="left" valign="top">
                        <html:select name="FormArvoreContato" property="processoTec" styleId="processoTec" style="text-indent:3px;" styleClass="SELECT">
                            <html:option value="">Escolha uma opção...</html:option>
                            <html:options collection="aProcesso" property="idTipoProcesso" labelProperty="dsTipoProcesso" />
                        </html:select>
                    </td>
                    <td class="tblEstatica_campoNome" height="30" valign="top" >
                        <netui:label value="Peso/Prioridade:" styleClass="tblEstatica_campoNome"/>
                    </td>
                    <td align="left" valign="top">
                        <html:select name="FormArvoreContato" property="vlPesoContato" styleId="vlPesoContato" style="text-indent:3px;width:70px" styleClass="SELECT">
                            <html:option value="">Escolha...</html:option>
                            <logic:iterate name="FormArvoreContato" property="admPrioridade" id="prioridade">
                                <option value='<bean:write name="prioridade" property="vlPrioridade"/>'><bean:write name="prioridade" property="vlPrioridade"/></option>
                            </logic:iterate>
                        </html:select>
                    </td>
                </tr>
                <tr><td height="4" colspan="4"></td></tr>
                <tr>
                    <td align="Left"  valign="top">
                        &nbsp;<netui:label value="Mensagem de Aviso" styleClass="tblEstatica_campoNome"/>
                    </td>
                    <td valign="top" colspan="3">
                    <logic:equal name="FormArvoreContato" property="idMensagemAtual" value="">
					
					<input type="radio" name="msg" id="msg" value="2" class="radio" onclick="escondeLista('0');" <logic:equal name="FormArvoreContato" property="msg" value="0">checked</logic:equal><logic:equal name="FormArvoreContato" property="msg" value="">checked</logic:equal>><span>&nbsp;Nova</span>
					
					<input type="radio" name="msg" id="msg" value="1" class="radio" onclick="exibeLista('1');" <logic:equal name="FormArvoreContato" property="msg" value="1">checked</logic:equal>><span>&nbsp;Existente</span>
					
					<input type="radio" name="msg" id="msg" value="2" class="radio" onclick="escondeAmbos('2');" <logic:equal name="FormArvoreContato" property="msg" value="2">checked</logic:equal>><span>&nbsp;Sem mensagem</span>

                    </logic:equal>
                    <logic:notEqual name="FormArvoreContato" property="idMensagemAtual" value="">
					
						<input type="radio" name="msg" id="msg" value="0" class="radio" onclick="escondeLista('0');" <logic:equal name="FormArvoreContato" property="msg" value="0">checked</logic:equal><logic:equal name="FormArvoreContato" property="msg" value="">checked</logic:equal>><span>&nbsp;Nova</span>						
						<input type="radio" name="msg" id="msg" value="1" class="radio" onclick="exibeLista('1');" <logic:equal name="FormArvoreContato" property="msg" value="1">checked</logic:equal><logic:equal name="FormArvoreContato" property="msg" value="">checked</logic:equal>><span>&nbsp;Existente</span>						
						<input type="radio" name="msg" id="msg" value="2" class="radio" onclick="escondeAmbos('2');" <logic:equal name="FormArvoreContato" property="msg" value="2">checked</logic:equal><logic:equal name="FormArvoreContato" property="msg" value="">checked</logic:equal>><span>&nbsp;Sem mensagem</span>
					
                    </logic:notEqual>

                    </td>
                </tr>
                <tr>
                    <td width="80%" colspan="3" valign="top" height="40">
                        <div id="divLista3" style="visibility:'visible'; position:relative; top: 0px; left: 25px; height: 24px; padding: 0px;">
                            <div id="divLista1" style="visibility:'visible'; position:absolute; top: 0px; left: 110px; height: 0px; padding: 0px;">
                                <html:select name="FormArvoreContato" property="mensagem" styleId="mensagem" style="text-indent:3px;width:370px" styleClass="SELECT">
                                    <html:option value="">Escolha uma opção...</html:option>
                                    <html:options collection="aMensagem" property="idMensagemAviso" labelProperty="dsMensagemAviso" />
                                </html:select>
                            </div>
                            <div id="divLista2" style="visibility:'hidden'; position:absolute; top: -3px; left: 110px; height: 0px; padding: 0px;">
                                <html:textarea name="FormArvoreContato" property="mensagemNova" styleId="mensagemNova" style="width:370px" styleClass="input" rows="2" onkeyup="checaTextarea(this,254);" onkeypress="return verificaEnter(event);"/>
                            </div>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;<netui:label value="Regra de Encaminhamento:" styleClass="tblEstatica_campoNome"/></td>
                    <td colspan="2">
                        <html:select name="FormArvoreContato"
                                     property="sgRegraEncaminhamento"
                                     styleId="sgRegraEncaminhamento"
                                     style="text-indent:3px;width:130px"
                                     styleClass="SELECT">
                            <html:option value="">Escolha...</html:option>
                            <html:option value="WRK">Atual</html:option>
                            <html:option value="MC">Meu Cliente</html:option>
                        </html:select>
                    </td>
                </tr>
            </table>
            </vivo:quadro>
            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>

            <vivo:quadro id="interacoes" width="100%" height="130" label="Interações Cliente - Internet e TAV">
            <table width="100%">
                <tr>
                    <td><strong>Comp. de Cancelamento:</strong></td>
                    <td nowrap colspan="3">
                        <html:checkbox name="FormArvoreContato" property="inComprovanteCancelamento" style="border:none;" />
                    </td>
                </tr>
                <tr>
                    <td><b>Exibir Histórico:</b></td>
                    <td nowrap colspan="3">
                        <html:checkbox name="FormArvoreContato" property="inProtocolo"      styleId="inProtocolo" value="1" style="border:none;" />Protocolo&nbsp;&nbsp;&nbsp;
                        <html:checkbox name="FormArvoreContato" property="inRelacionamento" styleId="inRelacionamento" value="1" style="border:none;" />Relacionamento
                    </td>
                </tr>
                <tr>
                    <td><b>Descrição Histórico:</b></td>
                    <td colspan="3" nowrap><html:text name="FormArvoreContato" property="dsContatoCanais" size="70" maxlength="50"/></td>
                </tr>
            </table>
            </vivo:quadro>
            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
            <vivo:quadro id="envioSms" width="100%" height="60" label="Envio de SMS/E-mail">
            <table width="100%">
                <tr>
                    <td><b>Envio de SMS/E-mail:</b></td>
                    <td colspan="2">
                        <html:select name="FormArvoreContato" property="inSMS" styleId="inSMS" style="text-indent:3px;" styleClass="SELECT">
                            <option value="">Escolha uma opção...</option>
                            <option value="1" <logic:equal name="FormArvoreContato" property="inSMS" value="1">selected</logic:equal>>SIM</option>
                            <option value="0" <logic:equal name="FormArvoreContato" property="inSMS" value="0">selected</logic:equal>>NÃO</option>
                        </html:select>
                    </td>
                </tr>
                <tr>
                    <td><b>Classificação:</b></td>
                    <td colspan="3">
                        <html:select name="FormArvoreContato" property="idClassificacaoSMS" styleId="idClassificacaoSMS" style="text-indent:3px;width:300px" styleClass="SELECT">
                            <html:option value="">Escolha...</html:option>
                            <logic:iterate  id="classificacaoSMS" name="FormArvoreContato" property="listaClassificoesSMS" type="br.com.vivo.fo.admsistemas.vo.AdmSelectsContatoFolhaVODocument.AdmSelectsContatoFolhaVO.AdmClassificacaoSMSVO">
                                <option value='<bean:write name="classificacaoSMS" property="idClassificacao"/>'
                                <% if (classificacaoSMS.getIdClassificacao().equals(FormArvoreContato.getIdClassificacaoSMS())) { %>
                                selected
                                <% } %>
                                ><bean:write name="classificacaoSMS" property="dsClassificacao"/></option>
                            </logic:iterate>
                        </html:select>
                    </td>
                </tr>
            </table>
            </vivo:quadro>
            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
            <vivo:quadro id="indicadores" width="100%" height="100" label="Indicadores Anatel">
            <table width="100%">
                <tr>
                    <td align="center"><b>Existentes</b><br></td>
                    <td>&nbsp;</td>
                    <td align="center"><b>Associados</b><br></td>
                </tr>
                <tr>
                    <td width="43%" align="center">
                        <html:select name="FormArvoreContato" property="arrayAdmIndicadorAnatelExistente" multiple="true" style="width:171px;" styleClass="SELECT" size="5" >
                            <html:options collection="AdmIndicadorAnatelExistenteVO" property="idIndicador" labelProperty="nmIndicador" />
                        </html:select>
                    </td>
                    <td width="14%" align="center">
                        <img style="border:none;cursor:hand" id="btRightSimp1" onclick="transfereSelecaoLista(f.arrayAdmIndicadorAnatelExistente, f.arrayAdmIndicadorAnatelAssociado);" border="0" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_over.gif'"/>
                        <br><br>
                        <img style="border:none;cursor:hand" id="btRightSimp2" onclick="transfereSelecaoLista(f.arrayAdmIndicadorAnatelAssociado, f.arrayAdmIndicadorAnatelExistente);" border="0" src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_over.gif'" />
                    </td>
                    <td width="43%" align="center">
                        <html:select name="FormArvoreContato" property="arrayAdmIndicadorAnatelAssociado" styleId="arrayAdmIndicadorAnatelAssociado" multiple="true" style="width:172px;" styleClass="SELECT" size="5" >
                            <html:options collection="AdmIndicadorAnatelAssociadoVO" property="idIndicador" labelProperty="nmIndicador" />
                        </html:select>
                    </td>
                </tr>
            </table>
        </vivo:quadro>
        </div>
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
        <tr>
            <td colspan="2" >
            <acesso:controlHiddenItem nomeIdentificador="adm_ceico_salvar">
                <img id="btSalvar1" src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_salvar_over.gif'" style="border:none;cursor:hand;" onClick="salvar(); return false;" align="right"/>
            </acesso:controlHiddenItem>
            </td>
        </tr>
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
        </form>
        <iframe style="width:0px;height:0px;" name="ifmInnerBrowserContato"></iframe>
        </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>
