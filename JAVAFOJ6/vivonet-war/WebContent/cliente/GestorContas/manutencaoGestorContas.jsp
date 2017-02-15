<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="gestorContasForm" type="cliente.GestorContas.GestorContasController.GestorContasForm"/>
<html>
    <head>
        <script type="text/javascript" language="javascript">
            $('containerManutencaoGestorContas').setStyle({
                backgroundColor: '#ededed',
                padding: '10px'
            });

            getPesquisaContas = function() {
                var bSubmit = true;
                if($F('tpPesquisaConta')=="CONTA" && $F('nrConta').blank() ) {
                    bSubmit = false;
                    alert('Por favor, informe ao menos um filtro para a pesquisa de contas.');
                }
                if($F('tpPesquisaConta')=="CNPJ" && $F('nrCNPJ').blank() ) {
                    bSubmit = false;
                    alert('Por favor, informe ao menos um filtro para a pesquisa de contas.');
                }
                if(!$F('nrCNPJ').blank() && !validaCNPJ($F('nrCNPJ'))){
                    bSubmit = false;
                    alert('Por favor, corrija o valor do CNPJ.');
                }
                if(bSubmit){
                    new Ajax.Updater('container_pesquisarClienteVO', 'getPesquisaContas.do', {
                        method: 'post',
                        evalScripts: false,
                        parameters: {
                            'gestorContasVO.tpOperacao': 'pesquisarContas',
                            'gestorContasVO.filtros.nrConta': $F('nrConta').gsub('[^0-9]',''),
                            'gestorContasVO.filtros.nrCNPJ': $F('nrCNPJ').gsub('[^0-9]','')
                        },
                        onComplete: function() {
                            if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                        },
                        onCreate: function() {
                            if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();
                        },
                        onFailure: function(r){
                            alert("[Failure] "+r.responseText);
                        },
                        onException: function(transport, e){
                            alert("[Exception] "+e.description+"\n"+transport);
                        }
                    });
                }
            }

            submitGravar = function() {
                var aFields = new Array();
                var hasGestor = false;
                if($F('nmPrimeiro').blank())
                    aFields[aFields.length] = $('nmPrimeiro').readAttribute('title');
                if($F('nmUltimo').blank())
                    aFields[aFields.length] = $('nmUltimo').readAttribute('title');
                if($F('nrCPF').blank() || !validaCPF($F('nrCPF').gsub('[^0-9]','')))
                    aFields[aFields.length] = $('nrCPF').readAttribute('title');

                if($F('nrTelComercial1').blank())
                    aFields[aFields.length] = $('nrTelComercial1').readAttribute('title');
                if($F('nrTelCelular').blank())
                    aFields[aFields.length] = $('nrTelCelular').readAttribute('title');
                if(!$F('nmEmail').blank() && !validaEmail($F('nmEmail').strip()))
                    aFields[aFields.length] = $('nmEmail').readAttribute('title');

                if($F('nmLogradouro').blank())
                    aFields[aFields.length] = $('nmLogradouro').readAttribute('title');
                if($F('nrLogradouro').blank())
                    aFields[aFields.length] = $('nrLogradouro').readAttribute('title');
                if($F('nmBairro').blank())
                    aFields[aFields.length] = $('nmBairro').readAttribute('title');
                if($F('nmMunicipio').blank())
                    aFields[aFields.length] = $('nmMunicipio').readAttribute('title');
                if($F('idUF').blank())
                    aFields[aFields.length] = $('idUF').readAttribute('title');
                if($F('nrCEP').blank())
                    aFields[aFields.length] = $('nrCEP').readAttribute('title');

                if($('listaContasSelecionadas').options.length == 0)
                    aFields[aFields.length] = $('listaContasSelecionadas').readAttribute('title');

                if(aFields.length == 0){
                    for(j=0;j<$('listaContasSelecionadas').options.length;j++){
                        if($('listaContasSelecionadas').options[x].value.split('|')[1]=="1"){
                            hasGestor = true;
                        }
                    }
                    if(hasGestor){
                        if(confirm('Foi identificado uma ou mais contas que já possuem gestor associado, deseja prosseguir?')){
                            enviarForm();
                        }else{
                            $('manutencaoGestorContas').remove();
                        }
                    }else{
                        enviarForm();
                    }
                }else{
                    formMessage(aFields);
                }
            }

            enviarForm = function(){
                for(x=0;x<$('listaContasSelecionadas').options.length;x++){
                    $('listaContasSelecionadas').options[x].selected = true;
                    $('listaContasSelecionadas').options[x].value = $('listaContasSelecionadas').options[x].value.split('|')[0];
                }
                new Ajax.Request('gravarGestor.do', {
                    method: 'post',
                    evalScripts: true,
                    parameters: {
                        nrTelComercial1: $F('nrTelComercial1').gsub('[^0-9]',''),
                        nrTelComercial1: $F('nrTelComercial1').gsub('[^0-9]',''),
                        nrTelRamal1: $F('nrTelRamal1').gsub('[^0-9]',''),
                        nrTelComercial2: $F('nrTelComercial2').gsub('[^0-9]',''),
                        nrTelRamal2: $F('nrTelRamal2').gsub('[^0-9]',''),
                        nrTelCelular: $F('nrTelCelular').gsub('[^0-9]',''),
                        listaContasSelecionadas: $F('listaContasSelecionadas'),
                        'gestorContasVO.tpOperacao': 'gravarGestor',
                        'gestorContasVO.gestorVO.nmPrimeiro': $F('nmPrimeiro').strip(),
                        'gestorContasVO.gestorVO.nmMeio': $F('nmMeio').strip(),
                        'gestorContasVO.gestorVO.nmUltimo': $F('nmUltimo').strip(),
                        'gestorContasVO.gestorVO.nrCPF': $F('nrCPF').gsub('[^0-9]',''),
                        'gestorContasVO.gestorVO.nmCargo': $F('nmCargo').strip(),
                        'gestorContasVO.gestorVO.tpGestor': $F('tpGestor').strip(),
                        'gestorContasVO.gestorVO.nmEmail': $F('nmEmail').strip(),
                        'enderecoVO.nmLogradouro': $F('nmLogradouro').strip(),
                        'enderecoVO.nrLogradouro': $F('nrLogradouro').strip(),
                        'enderecoVO.nmComplemento': $F('nmComplemento').strip(),
                        'enderecoVO.nmBairro': $F('nmBairro').strip(),
                        'enderecoVO.nmMunicipio': $F('nmMunicipio').strip(),
                        'enderecoVO.idUF': $F('idUF').strip(),
                        'enderecoVO.nrCEP': $F('nrCEP').gsub('[^0-9]','')
                    },
                    onCreate: function() {
                        if(window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();
                    },
                    onComplete: function(){
                        if(window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                    },
                    onSuccess: function(transport) {
                        alert('Cadastro de gestor de contas realizado com sucesso.');
                        if(window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                        $('manutencaoGestorContas').remove();
                    },
                    on503: function(e) {
                        createErrorPopUp('erroPortabilidade', e.statusText, e.responseText, false);
                        if(window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                    }
                });
            }

            cancelar = function(){
                //if(confirm('Deseja cancelar a manutenção de Gestor de Contas?')){
                    $('manutencaoGestorContas').remove();
                //}
            }

            transfereSelecaoLista = function(listaOrigem, listaDestino) {
                var i;
                for(i = 0; i<listaOrigem.options.length; i++) {
                    if (listaOrigem.options[i].selected) {
                        if (!valueExistsInSelect(listaDestino, listaOrigem.options[i].value)) {
                            listaDestino.options[listaDestino.options.length] = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);
                        }
                    }
                }
                for(i = listaOrigem.options.length-1; i>=0; i--)
                    if(listaOrigem.options[i].selected)
                        listaOrigem.options[i] = null;
            }

            filtroPesquisaConta = function(obj){
                if(obj.value=="CONTA"){
                    $('fdPesqConta').style.display = 'block';
                    $('fdPesqCnpj').style.display = 'none';
                    $('nrCNPJ').value = '';
                }else if(obj.value=="CNPJ"){
                    $('fdPesqCnpj').style.display = 'block';
                    $('fdPesqConta').style.display = 'none';
                    $('nrConta').value = '';
                }
            }

        </script>
    </head>
    <body>
        <html:form styleId="manutencaoGestorContasForm" method="POST" action="gravarGestor.do" style="margin:0;">
            <fieldset>
                <table width="100%">
                    <tr>
                        <td>Nome:</td>
                        <td><html:text title="Primeiro Nome" name="Form" property="gestorContasVO.gestorVO.nmPrimeiro" styleId="nmPrimeiro" maxlength="50" tabindex="1"/></td>
                        <td align="right">Nome do meio:</td>
                        <td align="left"><html:text title="Nome do meio" name="Form" property="gestorContasVO.gestorVO.nmMeio" styleId="nmMeio" maxlength="100" tabindex="2"/></td>
                        <td align="right">Sobrenome:</td>
                        <td align="right"><html:text name="Form" title="Sobrenome" property="gestorContasVO.gestorVO.nmUltimo" styleId="nmUltimo" maxlength="50" tabindex="3"/></td>
                    </tr>
                    <tr>
                        <td colspan="6">
                            <table width="100%" cellpadding="0" cellspacing="0">
                                <tr>
                                    <td>CPF:</td>
                                    <td><html:text title="CPF" name="Form" property="gestorContasVO.gestorVO.nrCPF" style="width:85px;" styleId="nrCPF" onkeyup="checaCPF(this)" onblur="checaCPF(this)" size="14" maxlength="14" tabindex="4"/></td>
                                    <td align="right">Cargo:</td>
                                    <td align="left"><html:text title="Cargo" name="Form" property="gestorContasVO.gestorVO.nmCargo" styleId="nmCargo" maxlength="50"/></td>
                                    <td align="right">Gestor Master:</td>
                                    <td align="left">
                                        <html:select title="Tipo Gestor" name="Form" property="gestorContasVO.gestorVO.tpGestor" styleId="tpGestor" style="width:50px;">
                                            <option value="">--</option>
                                            <option value="MASTER" <logic:equal name="Form" property="gestorContasVO.gestorVO.tpGestor" value="MASTER">selected</logic:equal>>SIM</option>
                                            <option value="COMUM" <logic:equal name="Form" property="gestorContasVO.gestorVO.tpGestor" value="COMUM">selected</logic:equal>>NÃO</option>
                                        </html:select>
                                    </td>
                                    <td colspan="2">
                                        <logic:notEmpty name="Form" property="gestorContasVO.gestorVO.dtAlteracao">
                                        <div id="divUltimaAlteracao">&Uacute;ltima altera&ccedil;&atilde;o: <span><bean:write name="Form" property="gestorContasVO.gestorVO.dtAlteracao"/></span></div>
                                        </logic:notEmpty>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
            </fieldset>
            <fieldset style="margin-top:7px;">
                <legend>Telefone</legend>
                <table width="100%">
                    <tr>
                        <td>Comercial 1:</td>
                        <td><html:text title="Telefone Comercial 1" name="Form" property="nrTelComercial1" styleId="nrTelComercial1" size="13" maxlength="13" onkeypress="formatTelNo(this);" onblur="formatTelNo(this);"/></td>
                        <td>Ramal:</td>
                        <td><html:text name="Form" title="Ramal 1" property="nrTelRamal1" styleId="nrTelRamal1" style="width:45px;" size="6" maxlength="6" onkeypress="checaInteiro(this);" onblur="checaInteiro(this);"/></td>
                        <td>Comercial 2:</td>
                        <td><html:text title="Telefone Comercial 2" name="Form" property="nrTelComercial2" styleId="nrTelComercial2" size="13" maxlength="13" onkeypress="formatTelNo(this);" onblur="formatTelNo(this);"/></td>
                        <td align="right">Ramal:</td>
                        <td align="right"><html:text name="Form" title="Ramal 2" property="nrTelRamal2" styleId="nrTelRamal2" style="width:45px;" size="6" maxlength="6" onkeypress="checaInteiro(this);" onblur="checaInteiro(this);"/></td>
                    </tr>
                    <tr>
                        <td>Celular:</td>
                        <td><html:text title="Telefone Celular" name="Form" property="nrTelCelular" styleId="nrTelCelular" size="13" maxlength="13" onkeypress="formatTelNo(this);" onblur="formatTelNo(this);"/></td>
                        <td>E-Mail:</td>
                        <td colspan="7"><html:text title="E-Mail" name="Form" property="gestorContasVO.gestorVO.nmEmail" styleId="nmEmail" size="20" maxlength="100"/></td>
                    </tr>
                </table>
            </fieldset>
            <fieldset style="margin-top:7px;margin-bottom:7px;">
                <legend>Endereço</legend>
                <table width="100%">
                    <tr>
                        <td>Endereço:</td>
                        <td><html:text title="Logradouro" name="Form" property="enderecoVO.nmLogradouro" styleId="nmLogradouro" maxlength="60"/></td>
                        <td>Nro:</td>
                        <td><html:text title="Número" name="Form" property="enderecoVO.nrLogradouro" styleId="nrLogradouro" style="width:65px;" size="5" maxlength="10" onkeypress="checaInteiro(this);" onblur="checaInteiro(this);"/></td>
                        <td align="right">Complemento:</td>
                        <td align="right"><html:text title="Complemento" name="Form" property="enderecoVO.nmComplemento" styleId="nmComplemento" size="10" maxlength="10"/></td>
                    </tr>
                    <tr>
                        <td>Bairro:</td>
                        <td><html:text title="Bairro" name="Form" property="enderecoVO.nmBairro" styleId="nmBairro" maxlength="50"/></td>
                        <td>Município:</td>
                        <td><html:text title="Município" name="Form" property="enderecoVO.nmMunicipio" styleId="nmMunicipio" maxlength="50"/></td>
                        <td>
                            UF:
                            <html:select title="UF" name="Form" property="enderecoVO.idUF" styleId="idUF" style="width:50px;">
                                <option value="">--</option>
                                <html:optionsCollection name="Form" property="listaUF.itArray" value="id" label="ds"/>
                            </html:select>
                        </td>
                        <td align="right">CEP:
                            <html:text title="CEP" name="Form" property="enderecoVO.nrCEP" styleId="nrCEP" style="width:60px;" size="9" maxlength="9" onkeypress="checaCEP(this);" onblur="checaCEP(this);"/>
                        </td>
                    </tr>
                </table>
            </fieldset>
            <vivo:quadro width="730" height="200" id="quadroCliente" label="Cliente">
                <div id="filtrosCliente" class="divFiltros">
                    <table width="100%">
                        <tr>
                            <td nowrap style="padding-right:20px;">
                                <select id="tpPesquisaConta" name="tpPesquisaContas" onchange="filtroPesquisaConta(this);" style="width:65px;">
                                    <option value="CONTA">CONTA</option>
                                    <option value="CNPJ">CNPJ</option>
                                </select>
                            </td>
                            <td align="left" width="100%">
                                <div id="fdPesqConta" style=""><html:text name="Form" property="gestorContasVO.filtros.nrConta" styleId="nrConta" maxlength="100" onkeyup="checaInteiro(this)" onblur="checaInteiro(this)"/></div>
                                <div id="fdPesqCnpj"  style="display:none"><html:text name="Form" property="gestorContasVO.filtros.nrCNPJ" styleId="nrCNPJ" onkeyup="checaCNPJ(this)" onblur="checaCNPJ(this);" maxlength="18" style="width:110px;"/></div>
                            </td>
                            <td align="right">
                                <img style="cursor:pointer;" src="<%=request.getContextPath()%>/resources/images/bt_pesquisar_nrml.gif" border="0" onclick="getPesquisaContas();"/>
                            </td>
                        </tr>
                    </table>
                </div>
                <table>
                    <tr>
                        <td align="center">Contas disponíveis</td>
                        <td></td>
                        <td align="center">Contas selecionadas</td>
                    </tr>
                    <tr>
                        <td width="320" id="container_pesquisarClienteVO" valign="top">
                            <html:select name="Form" property="listaContasDisponiveis" styleId="listaContasDisponiveis" multiple="true">
                                <logic:iterate id="itContas" name="Form" property="contasDisponiveis.itArray">
                                    <option value="<bean:write name="itContas" property="id"/>"><bean:write name="itContas" property="ds"/></option>
                                </logic:iterate>
                            </html:select>
                        </td>
                        <td align="center" valign="middle">
                            &nbsp;<img vspace="5" id="imgRightRegionais" src="<%=request.getContextPath()%>/resources/images/bt_rightaln_nrml.gif" style="clear:both;" border="0" onclick="transfereSelecaoLista($('listaContasDisponiveis'),$('listaContasSelecionadas'));"/>
                            &nbsp;<img vspace="5" id="imgLeftRegionais" src="<%=request.getContextPath()%>/resources/images/bt_leftaln_nrml.gif" style="clear:both;margin-bottom:5px;" border="0" onclick="transfereSelecaoLista($('listaContasSelecionadas'),$('listaContasDisponiveis'));"/><br/>
                        </td>
                        <td width="320" valign="top" style="padding-left:10px;">
                            <html:select title="Conta selecionada" name="Form" property="listaContasSelecionadas" styleId="listaContasSelecionadas" multiple="true">
                                <logic:iterate id="itContas" name="Form" property="contasSelecionadas.itArray">
                                    <option value="<bean:write name="itContas" property="id"/>"><bean:write name="itContas" property="ds"/></option>
                                </logic:iterate>
                            </html:select>
                        </td>
                    </tr>
                </table>
            </vivo:quadro>
            <div style="float:left;">
                <img style="margin:5px 0 0 5px;border:none;cursor:pointer;" src="<%=request.getContextPath()%>/resources/images/bt_cancelar_nrml.gif" onclick="cancelar();"/>
            </div>
            <div style="float:right">
                <img style="margin:5px 5px 0 0;border:none;cursor:pointer;" src="<%=request.getContextPath()%>/resources/images/bt_gravar_nrml.gif" onclick="submitGravar();"/>
            </div>
        </html:form>
    </body>
</html>