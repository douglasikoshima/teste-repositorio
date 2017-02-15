<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<acesso:controlInitEnv/>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="camposDinamicosForm" type="admsistemas.AdmFormularios.AdmFormulariosController.CamposDinamicosForm"/>

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:section name="headerSection">
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/abas.js"></script>
        <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/xml2json.js"></script>
        <script type="text/javascript" language="JavaScript" src="/FrontOfficeWeb/resources/scripts/frameweb.js"></script>
        <script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/validacao.js"></script>
        <script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/vivoval.js"></script>
        <script type="text/javascript" language="javascript" src="/FrontOfficeWeb/resources/scripts/prototype.js"></script>
        <script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/xtooltip.js"></script>

        <script>
            <!--
                ativar_combos = function(){
                    ifrmArvore.src = 'about:blank';
                }

                transfereSelecaoLista = function(listaOrigem, listaDestino, inverse, flAll){
                    var i;
                    if (inverse) {
                        for(i = 0; i<listaDestino.options.length; i++) {
                            if (flAll) listaDestino.options[i].selected = true;
                                if(listaDestino.options[i].selected)
                                    listaOrigem.options[listaOrigem.options.length] = new Option(listaDestino.options[i].text, listaDestino.options[i].value);
                        }
                        for(i = listaDestino.options.length-1; i>=0; i--)
                            if(listaDestino.options[i].selected)
                                listaDestino.options[i] = null;
                    } else {
                        for(i = 0; i<listaOrigem.options.length; i++) {
                            if (flAll) listaOrigem.options[i].selected = true;
                            if(listaOrigem.options[i].selected) {
                                listaDestino.options[listaDestino.options.length] = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);
                            }
                        }
                        for(i = listaOrigem.options.length-1; i>=0; i--)
                            if(listaOrigem.options[i].selected)
                                listaOrigem.options[i] = null;
                    }
                }

                function showCamposExistentes(){
                    if($F('tpCampo')!=''){
                        clearSelectList($('idCampoExistente'));
                        /* Verifica se ação é altera: Necessário seleciona sub-formulário antes */
                        if ($('funcSubFormularioAlterar').checked && $('subFrmDisponiveis').selectedIndex == 0) {
                            alert('Não é possível realizar a busca de campos, pois não existe sub-formulário selecionado.');
                            $('tpCampo').selectedIndex = 0;
                        } else {
                            getListaCamposExistentes();
                        }
                    }else{
                        clearSelectList($('idCampoExistente'));
                    }
                }

                /* Buscar lista de campos disponíveis */
                function getListaCamposExistentes(){
                    if($F('tpCampo')!=''){
                        var params = $H();
                        params.set('tpCampo', $F('tpCampo'));
                        new Ajax.Request('loadCamposExistentes.do?' + params.toQueryString(), {
                            method: 'post',
                            onSuccess: function(e) {
                                if (e.responseText != '') {
                                    var dom = parseXml(e.responseText);
                                    var jsonString = xml2json(dom, '');
                                    var jsonObj = jsonString.evalJSON();
                                    if(jsonObj.Resultset && jsonObj.Resultset.linhas){
                                        var campos = jsonObj.Resultset.linhas.linha;
                                        if(campos.length){
                                            for(var i=0;i<campos.length;i++){
                                                var campo = campos[i].valor;
                                                if (!valueExistsInSelect($('listaCamposSelecionadosCombo'), campo[0])) {
                                                    addValueToSelectList(campo[1], campo[0], $('idCampoExistente'));
                                                }
                                            }
                                        }else{
                                            var campos = jsonObj.Resultset.linhas.linha.valor;
                                            if (!valueExistsInSelect($('listaCamposSelecionadosCombo'), campos[0])) {
                                                addValueToSelectList(campos[1], campos[0], $('idCampoExistente'));
                                            }
                                        }
                                    }

                                }

                            }, onCreate: function() {
                                if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();

                            }, onComplete: function() {
                                if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();

                            }, onException: function(e) {
                                window.top.frameApp.createErrorPopUp('erro', e.statusText, e.responseText, false);
                                if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                            }
                        });
                    }
                }

                /* Buscar Lista Campos Associados ao Formulário pesquisado */
                function getListaCamposAssociados(){
                        var params = $H();
                        params.set('idSubFormulario', $F('subFrmDisponiveis'));
                        new Ajax.Request('loadCamposAssociados.do?' + params.toQueryString(), {
                            method: 'post',
                            onSuccess: function(e) {
                                if (e.responseText != '') {
                                    var dom = parseXml(e.responseText);
                                    var jsonString = xml2json(dom, '');
                                    var jsonObj = jsonString.evalJSON();
                                    if(jsonObj.Resultset && jsonObj.Resultset.linhas){
                                        var campos = jsonObj.Resultset.linhas.linha;
                                        if(campos.length){
                                            for(var i=0;i<campos.length;i++){
                                                var campo = campos[i].valor;
                                                if (!valueExistsInSelect($('listaCamposSelecionadosCombo'), campo[0])) {
                                                    addValueToSelectList(campo[1], campo[0], $('listaCamposSelecionadosCombo'));
                                                }
                                            }
                                        }else{
                                            var campos = jsonObj.Resultset.linhas.linha.valor;
                                            if (!valueExistsInSelect($('listaCamposSelecionadosCombo'), campos[0])) {
                                                addValueToSelectList(campos[1], campos[0], $('listaCamposSelecionadosCombo'));
                                            }
                                        }
                                    }

                                }

                            }, onCreate: function() {
                                if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();

                            }, onComplete: function() {
                                if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();

                            }, onException: function(e) {
                                window.top.frameApp.createErrorPopUp('erro', e.statusText, e.responseText, false);
                                if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                            }
                        });

                }



                alteraFuncionalidade = function (id) {
                    if (id == "1") {
                        $('divNomeSubFormulario').style.display = "none";
                        $('divListaSubFormulario').style.display = "block";
                        $('divExcluirSubFormulario').style.display = "block";
                        $('nomeSubFormulario').value = '';
                    } else {
                        $('divNomeSubFormulario').style.display = "block";
                        $('divListaSubFormulario').style.display = "none";
                        $('divExcluirSubFormulario').style.display = "none";
                        $('subFrmDisponiveis').selectedIndex = 0;
                    }
                    $('subForm').action = 'incluirAlterarSubFormulario.do';
                    $('tpCampo').selectedIndex = 0;
                    clearSelectList($('idCampoExistente'));
                    clearSelectList($('listaCamposSelecionadosCombo'));
                }

                function moveAcima(destino){
                    if(destino.selectedIndex){
                        if(destino.selectedIndex>0){
                            var id = destino.options[destino.selectedIndex-1].value;
                            var ds = destino.options[destino.selectedIndex-1].text;
                            destino.options[destino.selectedIndex-1].value = destino.options[destino.selectedIndex].value;
                            destino.options[destino.selectedIndex-1].text  = destino.options[destino.selectedIndex].text;
                            destino.options[destino.selectedIndex].value = id;
                            destino.options[destino.selectedIndex].text  = ds;
                            destino.selectedIndex--;
                        }
                    }
                }

                function moveAbaixo(destino){
                    if(destino){
                        if(destino.selectedIndex>-1 && destino.selectedIndex<(destino.options.length-1)){
                            var id = destino.options[destino.selectedIndex+1].value;
                            var ds = destino.options[destino.selectedIndex+1].text;
                            destino.options[destino.selectedIndex+1].value = destino.options[destino.selectedIndex].value;
                            destino.options[destino.selectedIndex+1].text  = destino.options[destino.selectedIndex].text;
                            destino.options[destino.selectedIndex].value = id;
                            destino.options[destino.selectedIndex].text  = ds;
                            destino.selectedIndex++;
                        }
                    }
                }

                gravar = function (){
                    if(validaForm()){
                        if (!$('funcSubFormularioIncluir').checked) {
                            verbo = 'Incluir';
                        } else if (!$('funcSubFormularioAlterar').checked) {
                            verbo = 'Alterar';
                        }

                        var listaId = "";
                        for (i = 0; i < $('listaCamposSelecionadosCombo').options.length; i++) {
                            $('listaCamposSelecionadosCombo').options[i].selected = true;
                            listaId += $('listaCamposSelecionadosCombo').options[i].value;
                            if (i < $('listaCamposSelecionadosCombo').options.length -1) {
                                listaId +=",";
                            }
                        }

                       if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.startAnimation();
                       top.frameApp.createNewPopUp('dvIncluirAlterarSubFormulario', 'Definir obrigatoriedade de campos', 500, 400, null, 'loadConfigCamposSubFormulario.do?idSubFormularioSelecionado=' + $F('subFrmDisponiveis') + '&listaCamposSecionados=' +listaId , true, null);

                    }
                }



                function validaForm(){

                    if (!$('funcSubFormularioIncluir').checked && !$('funcSubFormularioAlterar').checked) {
                         alert('Para prosseguir é necessário informar uma ação.');
                        return false;
                    } else if ($('funcSubFormularioIncluir').checked && $F('nomeSubFormulario') == '') {
                        alert('Para prosseguir é necessário informar o nome do sub-formulário.');
                        return false;
                    } else if ($('funcSubFormularioIncluir').checked && $F('nomeSubFormulario') != '' && !verificarDuplicidadeNomes($F('nomeSubFormulario'))) {
                        alert('O nome do sub-formulário informado já existe cadastrado no sitema.');
                        return false;
                    } else if ($('funcSubFormularioIncluir').checked && $F('nomeSubFormulario') != '' && $('listaCamposSelecionadosCombo').options.length == 0) {
                        alert('Para realizar a gravação é necessário selecionar ao menos um campo.');
                        return false;
                    } else if ($('funcSubFormularioAlterar').checked && $('subFrmDisponiveis').selectedIndex == 0) {
                        alert('Não é possível realizar a gravação, pois não existe sub-formulário selecionado.');
                        return false;
                    } else if ($('funcSubFormularioAlterar').checked && $('subFrmDisponiveis').selectedIndex != 0 && $('listaCamposSelecionadosCombo').options.length == 0) {
                        alert('Não é possível realizar a gravação, pois não existe campo selecionado.');
                        return false;
                    }

                    return true;
                }

                verificarDuplicidadeNomes = function (novoSubFormulario) {
                   for (var i = $('subFrmDisponiveis').options.length-1; i >= 0; i--) {
                        if (trim($('subFrmDisponiveis').options[i].text.toUpperCase()) == trim(novoSubFormulario.toUpperCase())) {
                            return false;
                        }
                   }
                   return true;
                }

                carregarCamposSubFormulario = function () {
                    $('tpCampo').selectedIndex = 0;
                    getListaCamposAssociados();
                    clearSelectList($('idCampoExistente'));
                    clearSelectList($('listaCamposSelecionadosCombo'));
                }

                salvarInclusaoAlteracaoSubFormulario = function() {
                    if (validaForm()) {
                        if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.startAnimation();
                        var checkboxes = $('tableObrigatorio').select('[class=radio]');
                        for (var i = 0; i < checkboxes.length; i++) {
                            if (checkboxes[i].checked) {
                                $('camposObrigatoriosSelecionados').options[$('camposObrigatoriosSelecionados').options.length] = new Option(checkboxes[i].value, checkboxes[i].value);
                            } else {
                                $('camposNaoObrigatoriosSelecionados').options[$('camposNaoObrigatoriosSelecionados').options.length] = new Option(checkboxes[i].value, checkboxes[i].value);
                            }
                        }
                        selectAllOptions($('camposObrigatoriosSelecionados'));
                        selectAllOptions($('camposNaoObrigatoriosSelecionados'));
                        $('subForm').submit();
                    }
                }

                excluir = function() {

                    if ($('subFrmDisponiveis').selectedIndex <=0 ){
                        alert('Para prosseguir é necessário informar o sub-formulário a ser excluído.');
                    } else if (confirm('Deseja realmente excluir o sub-formulário selecionado?')) {
                         $('subForm').action = 'excluirSubFormulario.do';
                         $('subForm').submit();

                    }

                }


                onload = function(){
                    if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                    <logic:present name="msgRetorno" scope="request">
                        alert('Operação realizada com sucesso!');
                    </logic:present>
                    <logic:present name="msgError" scope="request">
                        alert('<bean:write name="msgError" scope="request"/>');
                    </logic:present>
                    clearSelectList($('idCampoExistente'));
                }
            -->
        </script>
    </netui-template:section>
    <netui-template:section name="bodySection">
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
        <vivo:sessao id="configurar" width="790" height="480" operacoes="Configurar Sub-formulário" scroll="no">
        <form method="post" action="" id="subForm" name="subForm" enctype="multipart/form-data" style="margin:0px;" onsubmit="return false;" target="_self">
        <input type="hidden" name="idContato" id="idContato"/>
        <html:select name="Form" property="camposObrigatorios" styleId="camposObrigatoriosSelecionados" multiple="true" style="display:none;"></html:select>
        <html:select name="Form" property="camposNaoObrigatorios" styleId="camposNaoObrigatoriosSelecionados" multiple="true" style="display:none;"></html:select>
        <table width="780" border="0" style="border:1px solid #D4D7DE; background-color:#F7F9FA; font-size:9px;">
            <tr>
                <td colspan="4">
                 <table border="0" width="100%">
                        <tr>
                            <td><html:radio name="Form" property="funcSubFormulario" styleId="funcSubFormularioIncluir" value="incluir" onclick="alteraFuncionalidade('0');" styleClass="radio"/></td>
                            <td width="200"><b>Novo sub-formulário:</b></td>
                            <td><html:radio name="Form" property="funcSubFormulario" styleId="funcSubFormularioAlterar" value="alterar" onclick="alteraFuncionalidade('1');" styleClass="radio"/></td>
                            <td width="300"><b>Alterar sub-formulário existente:</b></td>
                            <td width="100" align="center"><b>Nome:</b></td>
                            <td style="display:block" id="divNomeSubFormulario"><html:text name="Form" property="nomeSubFormulario" size="40" style="width:250px;"/></td>
                            <td style="display:none" id="divListaSubFormulario" align="left">
                            <html:select name="Form" property="subFrmDisponiveis" styleId="subFrmDisponiveis" style="width:200px;" size="1" styleClass="SELECT" onchange="carregarCamposSubFormulario();">
                                <option value="">-- Selecione --</option>
                                <logic:iterate id="it" name="Form" property="listaSubFormularios.linhas.linhaArray" indexId="idx">
                                         <option value="<bean:write name="it" property="valorArray[0]"/>"><bean:write name="it" property="valorArray[1]"/></option>
                                </logic:iterate>
                            </html:select>
                            </td>
                            <td style="display:none" id="divExcluirSubFormulario" align="left">
                                <img src="/FrontOfficeWeb/resources/images/bt_excluir_nrml.gif" onclick="excluir();" style="cursor:pointer;"/>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td colspan="4">
                    <table>
                        <tr>
                            <td width="100"><b>Tipo de campo:</b></td>
                            <td>
                                <select name="tpCampo" id="tpCampo" style="width:250px;" onchange="showCamposExistentes();">
                                    <option value="">-- Selecione --</option>
                                    <option value="T">Texto</option>
                                    <option value="S">Combo</option>
                                    <option value="SM">Lista seleção múltipla</option>
                                     <option value="LP">Lista preenchida</option>
                                    <option value="L">Label</option>
                                    <option value="M">Memo</option>
                                </select>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td colspan="4">&nbsp;</td>
            </tr>
            <tr>
                <td colspan="4">&nbsp;</td>
            </tr>
            <tr>
                <td colspan="4">
                    <table width="100%">
                    <tr>
                        <td>Campos disponíveis</td>
                        <td></td>
                        <td>Campos selecionados</td>
                        <td></td>
                    </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td colspan="4">
                    <table width="100%">
                    <tr>
                        <td width="310" valign="top">
                            <select name="idCampoExistente" size="17" id="idCampoExistente" style="width:300px;"></select>
                        </td>
                        <td width="40" align="">
                            <img src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" onclick="transfereSelecaoLista($('idCampoExistente'),$('listaCamposSelecionadosCombo'),false,false);" style="cursor:pointer;"/>
                            <br/><br/>
                            <img src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" onclick="transfereSelecaoLista($('listaCamposSelecionadosCombo'),$('idCampoExistente'));" style="cursor:pointer;"/>
                        </td>
                        <td width="310" valign="top">
                            <html:select name="Form" property="listaCamposSelecionadosCombo" styleId="listaCamposSelecionadosCombo" style="width:300px;" size="17" styleClass="SELECT" multiple="true">
                            </html:select>
                        </td>
                        <td>
                            <img src="/FrontOfficeWeb/resources/images/bt_up_nrml.gif" onclick="moveAcima($('listaCamposSelecionadosCombo'));" style="cursor:pointer;"/>
                            <br/><br/>
                            <img src="/FrontOfficeWeb/resources/images/bt_down_nrml.gif" onclick="moveAbaixo($('listaCamposSelecionadosCombo'));" style="cursor:pointer;"/>
                        </td>
                     </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td colspan="4">
                    <table width="100%" border="0" cellspacing="5" cellpadding="5">
                        <tr>
                            <td align="left">
                                <!--img src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif"-->
                            </td>
                            <td></td>
                            <td align="right">
                                <img src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" onclick="gravar();" style="cursor:pointer;"/>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
        </form>
        </vivo:sessao>
    </netui-template:section>
</netui-template:template>