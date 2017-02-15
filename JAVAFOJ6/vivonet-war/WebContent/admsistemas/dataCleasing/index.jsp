<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="filtroForm"/>

<acesso:controlInitEnv/>

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="VivoNet"/>
    <netui-template:setAttribute name="modulo" value="Administração de Sistemas"/>

    <netui-template:section name="headerSection">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/xml2json.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/calendar.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/validacao.js"></script>
        <script language="JavaScript">

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
                        if(listaOrigem.options[i].selected)
                            listaDestino.options[listaDestino.options.length] = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);
                    }
                    for(i = listaOrigem.options.length-1; i>=0; i--)
                        if(listaOrigem.options[i].selected)
                            listaOrigem.options[i] = null;
                }
            }

            selecionaFiltro = function(valor){
                $('frmFiltro').reset();
                if(valor=="1"){
                    $('dvFiltroCEP').hide();
                    $('dvFiltroUF').show();
                    $('dvGeral').show();
                    $('dvDataAtualiza').show();
                    $('tpFiltroUF').checked = true;

                }else if(valor=="2"){
                    $('dvFiltroCEP').show();
                    $('dvFiltroUF').hide();
                    $('dvGeral').show();
                    $('dvDataAtualiza').show();
                    $('tpFiltroCEP').checked = true;

                }else if(valor=="0"){
                    $('dvFiltroCEP').hide();
                    $('dvFiltroUF').hide();
                    $('dvGeral').show();
                    $('dvDataAtualiza').hide();
                    $('tpFiltroAll').checked = true;
                }else{
                    $('dvFiltroCEP').hide();
                    $('dvFiltroUF').hide();
                    $('dvDataAtualiza').show();
                    $('dvGeral').hide();
                }
            }

            function gravar(){
                if($('tpFiltroUF').checked){
                    for(i=0; i<$('idSelUF').options.length; i++)
                        $('idSelUF').options[i].selected=true;

                    for(i=0; i<$('idSelDDD').options.length; i++)
                        $('idSelDDD').options[i].selected=true;

                    $('nrCEPIni').value = '';
                    $('nrCEPFim').value = '';

                }else if($('tpFiltroCEP').checked){
                    for(i=0; i<$('idSelUF').options.length; i++)
                        $('idSelUF').options[i].selected=false;

                    for(i=0; i<$('idSelDDD').options.length; i++)
                        $('idSelDDD').options[i].selected=false;

                    $('nrCEPIni').value = $F('nrCEPIni').gsub('[^0-9]','');
                    $('nrCEPFim').value = $F('nrCEPFim').gsub('[^0-9]','');

                }else{
                    for(i=0; i<$('idSelUF').options.length; i++)
                        $('idSelUF').options[i].selected=false;

                    for(i=0; i<$('idSelDDD').options.length; i++)
                        $('idSelDDD').options[i].selected=false;

                    $('nrCEPIni').value = '';
                    $('nrCEPFim').value = '';
                    $('dtAtualiza').value = '';
                }

                new Ajax.Request('gravaFiltro.do', {
                    method: 'post',
                    asynchronous: false,
                    parameters: $('frmFiltro').serialize(),
                    onComplete: function(r){
                        var dom = parseXml(r.responseText);
                        var jsonString = xml2json(dom, '');
                        var jsonObj = jsonString.evalJSON();

                        if(jsonObj.message!=null){
                            if(jsonObj.message.msg!=null){
                                alert(jsonObj.message.msg);
                            }
                        }
                    },
                    onCreate: function() {
                    },
                    onFailure: function(e){alert("[Failure] "+e+"\n");},
                    onException: function(transport, e){alert("[Exception] "+e.description+"\n"+transport);}
                });
                clearSelectList($('idDispDDD'));
                clearSelectList($('idSelDDD'));
                transfereSelecaoLista($('idDispUF'),$('idSelUF'),true,true);
                sortList($('idDispUF'));
                selecionaFiltro();
            }

            validaForm = function(){
                if($('tpFiltroUF').checked){
                    if($('idDispUF').options.length < 1){
                        alert('Selecione ao menos uma UF');
                        return false;
                    }

                }else if($('tpFiltroCEP').checked){
                    if($F('nrCEPIni')=='' || $F('nrCEPFim')==''){
                        alert('Informe o CEP inicial e o CEP final');
                        return false;
                    }
                    var cepini = eval($F('nrCEPIni').gsub('[^0-9]',''));
                    var cepfim = eval($F('nrCEPFim').gsub('[^0-9]',''));
                    if(cepini > cepfim){
                        alert('O CEP inicial deve ser menor ou igual que o CEP final');
                        return false;
                    }
                }
                return true;
            }

            function verificaNumCEP(obj) {
                if (obj!=null && (obj.value!="" && obj.value!=null)) {
                    if (obj.value.length<8) {
                        alert("Número de CEP inválido!");
                        obj.value = "";
                        obj.focus();
                        return false;

                    } else if (obj.value.length==9 && obj.value.indexOf('-')!=5) {
                        alert("Número de CEP inválido!");
                        obj.value = "";
                        obj.focus();
                        return false;
                    }
                    return true;
                }
                return false;
            }

            verificaFiltro = function(){
                if(validaForm()){
                    new Ajax.Request('verificaFiltro.do', {
                        method: 'post',
                        asynchronous: false,
                        onComplete: function(r){
                            var dom = parseXml(r.responseText);
                            var jsonString = xml2json(dom, '');
                            var jsonObj = jsonString.evalJSON();

                            if(jsonObj.message!=null){
                                if(jsonObj.message.qtd!=null){
                                    if(jsonObj.message.qtd>0){
                                        if(confirm("Já existem parâmetros de filtros gravados no sistema, deseja continuar?")){
                                            gravar();
                                        }
                                    }else{
                                        gravar();
                                    }
                                }else{
                                    alert('Ocorreu um problema na verificação dos dados');
                                }
                            }else{
                                alert('Ocorreu um problema na verificação dos dados');
                            }
                        },
                        onFailure: function(e){alert("[Failure] "+e+"\n");},
                        onException: function(transport, e){alert("[Exception] "+e.description+"\n"+transport);}
                    });
                }
            }

            getListaDDD = function() {
                if ($('idSelUF').options.length > 0) {
                    for(i=0; i<$('idSelUF').options.length; i++)
                        $('idSelUF').options[i].selected=true;

                    new Ajax.Request('listarCodArea.do', {
                        method: 'post',
                        parameters: $('frmFiltro').serialize(),
                        onComplete: function(r){
                            var dom = parseXml(r.responseText);
                            var jsonString = xml2json(dom, '');
                            var jsonObj = jsonString.evalJSON();

                            clearSelectList($('idDispDDD'));
                            clearSelectList($('idSelDDD'));

                            $('idDispDDD').disabled = false;

                            if(jsonObj.select!=null){
                                if(jsonObj.select.item!=null){
                                    if(jsonObj.select.item.length){
                                        for (var i = 0; i < jsonObj.select.item.length; i++) {
                                            addValueToSelectList(jsonObj.select.item[i].ds, jsonObj.select.item[i].id, $('idDispDDD'));
                                        }
                                    }else{
                                        addValueToSelectList(jsonObj.select.item.ds, jsonObj.select.item.id, $('idDispDDD'));
                                    }
                                }else{
                                    clearSelectList($('idDispDDD'));
                                    clearSelectList($('idSelDDD'));
                                }
                            }else{
                                clearSelectList($('idDispDDD'));
                                clearSelectList($('idSelDDD'));
                            }
                        },
                        onCreate: function() {
                            addValueToSelectList('', 'Carregando...', $('idDispDDD'));
                        },
                        onFailure: function(e){alert("[Failure] "+e+"\n");},
                        onException: function(transport, e){alert("[Exception] "+e.description+"\n"+transport);}
                    });

                }else{
                    clearSelectList($('idDispDDD'));
                    clearSelectList($('idSelDDD'));
                }
            }

            function sortList(lb){
                arrTexts = new Array();
                arrValues = new Array();
                arrOldTexts = new Array();

                for(i=0; i<lb.length; i++){
                    arrTexts[i] = lb.options[i].text;
                    arrValues[i] = lb.options[i].value;
                    arrOldTexts[i] = lb.options[i].text;
                }

                arrTexts.sort();

                for(i=0; i<lb.length; i++){
                    lb.options[i].text = arrTexts[i];
                    for(j=0; j<lb.length; j++){
                        if (arrTexts[i] == arrOldTexts[j]){
                            lb.options[i].value = arrValues[j];
                            j = lb.length;
                        }
                    }
                }
            }

        </script>
    </netui-template:section>
    <netui-template:section name="bodySection">
        <!-- Menu de Administração de Sistemas -->
        <div id="menuPrincipal"><jsp:include page="/resources/menus/MenuPrincipal.jsp" /></div>
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="3"></div>

        <!--APLICACAO-->
        <vivo:body idTable="tbMain" idDiv="dvMain" height="498" width="790">
            <vivo:quadro id="qdMain" height="473" width="780" label="Data Cleasing > Filtros de Extração">
                <form name="frmFiltro" id="frmFiltro" method="post" enctype="application/x-www-form-urlencoded" onsubmit="return false;" style="margin:0;">
                    <div style="width:100%;margin-left:10px;">
                        <table cellpadding="1" cellspacing="8" align="left">
                            <tr>
                                <td><b>Selecione um tipo de filtro:</b></td>
                                <td>
                                    <input type="radio" name="tpFiltro" id="tpFiltroUF"  value="1" class="radio" onclick="selecionaFiltro(this.value);"><b>UF</b>&nbsp;
                                    <input type="radio" name="tpFiltro" id="tpFiltroCEP" value="2" class="radio" onclick="selecionaFiltro(this.value);"><b>CEP</b>&nbsp;
                                    <input type="radio" name="tpFiltro" id="tpFiltroAll" value="0" class="radio" onclick="selecionaFiltro(this.value);"><b>Todos</b>
                                </td>
                            </tr>
                        </table>
                    </div>
                    <fieldset style="width:420px;height:400px;margin-left:10px;">
                        <div id="dvFiltroUF" style="display:none;">
                            <div style="width:100%">
                                <table width="390">
                                    <tr>
                                        <td align="center">UFs Existentes</td>
                                        <td>&nbsp;</td>
                                        <td align="center">UFs Associadas</td>
                                    </tr>
                                    <tr>
                                        <td width="43%" align="center">
                                            <html:select name="Form" property="idDispUF" styleId="idDispUF" size="6" style="width:150px;" styleClass="SELECT">
                                                <html:optionsCollection name="Form" property="optionsUFDisp" value="id" label="name"/>
                                            </html:select>
                                        </td>
                                        <td width="14%" align="center">
                                            <img src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" style="cursor:pointer;" onclick="transfereSelecaoLista($('idDispUF'),$('idSelUF'),false);getListaDDD();">
                                            <br><br>
                                            <img src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" style="cursor:pointer;" onclick="transfereSelecaoLista($('idDispUF'),$('idSelUF'),true);getListaDDD();sortList($('idDispUF'));">
                                        </td>
                                        <td width="43%" align="center">
                                            <select name="idSelUF" id="idSelUF" style="width:150px;" size="6" class="SELECT" multiple>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="center">Códigos de área Existentes</td>
                                        <td>&nbsp;</td>
                                        <td align="center">Códigos de área Associados</td>
                                    </tr>
                                    <tr>
                                        <td width="43%" align="center">
                                            <select name="idDispDDD" id="idDispDDD" style="width:150px;" size="6" class="SELECT">
                                            </select>
                                        </td>
                                        <td width="14%" align="center">
                                            <img src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" style="cursor:pointer;" onclick="transfereSelecaoLista($('idDispDDD'),$('idSelDDD'),false);">
                                            <br><br>
                                            <img src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" style="cursor:pointer;" onclick="transfereSelecaoLista($('idDispDDD'),$('idSelDDD'),true);sortList($('idDispDDD'));">
                                        </td>
                                        <td width="43%" align="center">
                                            <select name="idSelDDD" id="idSelDDD" style="width:150px;" size="6" class="SELECT" multiple>
                                            </select>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                        <div id="dvFiltroCEP" style="display:none;">
                            <div style="width:100%">
                                <table cellpadding="1" cellspacing="8" align="center">
                                    <tr>
                                        <td>CEP Inicial:</td>
                                        <td>
                                            <input type="text" name="nrCEPIni" id="nrCEPIni" maxlength="9" size="10"
                                                   onkeyup="Formatar(this.value, this.form.name, this.name, 'cep');"
                                                   onblur="Formatar(this.value, this.form.name, this.name, 'cep');verificaNumCEP(this);"/>
                                        </td>
                                        <td>CEP Final:</td>
                                        <td>
                                            <input type="text" name="nrCEPFim" id="nrCEPFim" maxlength="9" size="10"
                                                   onkeyup="Formatar(this.value, this.form.name, this.name, 'cep');"
                                                   onblur="Formatar(this.value, this.form.name, this.name, 'cep');verificaNumCEP(this);"/>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                        <div id="dvGeral" style="display:none;">
                            <div style="width:100%" id="dvDataAtualiza">
                                <table cellpadding="1" cellspacing="8" align="left" width="390">
                                    <tr>
                                        <td width="170">Data de Atualização do Cadastro:</td>
                                        <td>
                                            <input type="text" id="dtAtualiza" name="dtAtualiza" size="10" maxlength="10"
                                                   onblur="validaData(this);"
                                                   onkeyup ="Formatar(this.value, this.form.name, this.name, 'data');"
                                                   onchange="Formatar(this.value, this.form.name, this.name, 'data');"/>
                                            <img src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:hand;" title="Calendário" onclick="return showCalendar('dtAtualiza', '%d/%m/%Y');">
                                        </td>
                                    </tr>
                                </table>
                            </div>
                            <div style="width:100%">
                                <table cellpadding="1" cellspacing="8" align="center" width="390">
                                    <tr>
                                        <td align="center">
                                            <img src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" style="cursor:pointer;" onclick="verificaFiltro();"/>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                    </fieldset>
                </form>
            </vivo:quadro>
        </vivo:body>

    </netui-template:section>
</netui-template:template>
