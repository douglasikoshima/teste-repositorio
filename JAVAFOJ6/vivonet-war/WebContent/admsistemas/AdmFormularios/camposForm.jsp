<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="camposDinamicosForm"/>

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:section name="headerSection">
        <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/xml2json.js"></script>
        <script type="text/javascript" language="javascript" src="/FrontOfficeWeb/resources/scripts/prototype.js"></script>
        <script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/xtooltip.js"></script>
        <script language="javascript" type="text/javascript">
            function validaForm(){
                if($F('nmCampo').strip().empty()){
                    alert("Entre com o Nome do Campo.");
                    return false;
                }

                if($F('lbCampo').strip().empty()){
                    alert("Entre com o Nome de apresentação do Campo.");
                    return false;
                }

                if($F('tpCampo')=='S' || $F('tpCampo')=='SM'|| $F('tpCampo')=='L'){
                    if($('vlCampoCombo').options.length<1){
                        alert("Selecione pelo menos um valor.");
                        return false;
                    }
                }

                if($F('tpCampo')=='T' || $F('tpCampo')=='LP'){
                    if($('fmtTexto').selectedIndex<1){
                        alert("Selecione pelo menos um valor.");
                        return false;
                    }
                }
                return true;
            }

            function adicionarItem(){
                var destino = $('vlCampoCombo');
                var valor   = $F('vlCampo');

                if(valor!=''){
                    if(!buscaItemExistente(valor)){
                        var option = document.createElement('option');
                        option.text  = valor;
                        option.value = valor;
                        destino.add(option);
                        $('vlCampo').value = '';
                   }else{
                        alert('Já existe o valor "'+valor+'" para o campo.');
                    }
                }else{
                    alert('Informe pelo menos um valor.');
                }
                return true;
            }

            function removerItem(){
                var destino = $('vlCampoCombo');
                if(destino.selectedIndex>=0){
                    destino.remove(destino.selectedIndex);
                }
            }

            function buscaItemExistente(strItem){
                var destino = $('vlCampoCombo');
                for(i=0;i<destino.options.length;i++){
                    if(destino.options[i].value==strItem){
                        return true;
                    }
                }
                return false;
            }

            function incluirCampo(){
                if(validaForm()){
                    if($F('tpCampo')=='S' || $F('tpCampo')=='SM' || $F('tpCampo')=='L') preparaCombo();
                    if (self.$('dvAnimarAguarde')) self.startAnimation();
                    new Ajax.Updater('dataTable', 'incluirCampo.do', {
                        method: 'post',
                        asynchronous: false,
                        parameters: $('camposForm').serialize(),
                        evalScripts: true,
                        onSuccess: function() {
                            if (self.$('dvAnimarAguarde')) self.stopAnimation();
                            limpaFormulario();
                        }, on406: function() {
                            if (self.$('dvAnimarAguarde')) self.stopAnimation();
                        }, onFailure: function(e) {
                            if (self.$('dvAnimarAguarde')) self.stopAnimation();
                            var stackTrace = e.getHeader("stackTrace") ? e.getHeader("stackTrace") : "";
                            top.frameApp.createErrorPopUp('erroConsultaNumero', e.statusText, stackTrace, false);
                        }
                    });
                    pesquisar();
                }
            }

            function pesquisar(){
                new Ajax.Updater('dataTable', 'pesquisar.do', {
                    method: 'post',
                    evalScripts: true,
                    onSuccess: function() {
                        if (self.$('dvAnimarAguarde')) self.stopAnimation();

                    }, onFailure: function(e) {
                        if (self.$('dvAnimarAguarde')) self.stopAnimation();
                        var stackTrace = e.getHeader("stackTrace") ? e.getHeader("stackTrace") : "";
                        top.frameApp.createErrorPopUp('erroConsultaNumero', e.statusText, stackTrace, false);
                    }
                });
            }

            function preparaCombo(){
                var destino = $('vlCampoCombo');
                for(i=0;i<destino.options.length;i++){
                    destino.options[i].selected = true;
                }
            }

            function limpaFormulario(){
                $('camposForm').reset();
                showCombo($('tpCampo'));
                var destino = $('vlCampoCombo');
                for(i = destino.options.length-1; i>=0; i--)
                    destino.options[i] = null;
            }

            function showCombo(obj){
                if(obj.value=='S' || obj.value=='SM' || obj.value=='L'){
                    showCamposCombo();
                    hideCamposTexto();
                }else if(obj.value=='T' || obj.value=='LP'){
                    showCamposTexto();
                    hideCamposCombo();
                }else{
                    hideCamposCombo();
                    hideCamposTexto();
                }
            }

            function showCamposTexto(){
                $('dvLblFormatoTexto').show();
                $('dvVlFormatoTexto').show();
            }

            function hideCamposTexto(){
                $('dvLblFormatoTexto').hide();
                $('dvVlFormatoTexto').hide();
            }

            function showCamposCombo(){
                $('lnValor').show();
                $('lstCampos').show();
                $('btAdd').show();
                $('dvLblValor').show();
                $('dvVlCampo').show();
                $('btRem').show();
            }

            function hideCamposCombo(){
                $('lnValor').hide();
                $('lstCampos').hide();
                $('btAdd').hide();
                $('dvLblValor').hide();
                $('dvVlCampo').hide();
                $('btRem').hide();
            }

            function verificaExcluirForm(id){
                if(id!='' || id!=undefined){
                    var params = $H();
                    params.set('idCampo', id);
                    new Ajax.Request('veficaExcluirCampo.do?' + params.toQueryString(), {
                        method: 'post',
                        onSuccess: function(e) {
                            if (e.responseText != '') {
                                var dom = parseXml(e.responseText);
                                var jsonString = xml2json(dom, '');
                                var jsonObj = jsonString.evalJSON();
                                if(jsonObj.Resultset && jsonObj.Resultset.linhas){
                                    var count = jsonObj.Resultset.linhas.linha.valor;
                                    if(count>0){
                                        alert('Esse campo está sendo utilizado por um formulário de solicitação.');
                                    }else{
                                        if(confirm('Deseja realmente excluir este Campo?')){
                                            excluirCampo(id);
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

            function excluirCampo(idCampo){
                var params = $H();
                params.set('idCampo', idCampo);
                new Ajax.Updater('dataTable', 'excluirCampo.do', {
                    method: 'post',
                    asynchronous: false,
                    parameters: params,
                    evalScripts: true,
                    onSuccess: function() {
                        if (self.$('dvAnimarAguarde')) self.stopAnimation();
                        $('camposForm').reset();

                    }, onFailure: function(e) {
                        if (self.$('dvAnimarAguarde')) self.stopAnimation();
                        var stackTrace = e.getHeader("stackTrace") ? e.getHeader("stackTrace") : "";
                        top.frameApp.createErrorPopUp('erroConsultaNumero', e.statusText, stackTrace, false);
                    }
                });
                pesquisar();
            }


        /* alteração de campos combo */
        function adicionarItemAlt(){
            var destino = $('vlCampoComboAlt');
            var valor   = $F('vlCampoAlt');
            if(valor!=''){
                if(!buscaItemExistenteAlt(valor)){
                    var option = document.createElement('option');
                    option.text  = valor;
                    option.value = valor;
                    destino.add(option);
                    $('vlCampoAlt').value = '';
                }else{
                    alert('Já existe o valor "'+valor+'" para o campo.');
                }
            }else{
                alert('Informe pelo menos um valor.');
            }
            return true;
        }

        function removerItemAlt(){
            var destino = $('vlCampoComboAlt');
            if(destino.selectedIndex>=0){
                destino.remove(destino.selectedIndex);
            }
        }

        function buscaItemExistenteAlt(strItem){
            var destino = $('vlCampoComboAlt');
            for(i=0;i<destino.options.length;i++){
                if(destino.options[i].value==strItem){
                    return true;
                }
            }
            return false;
        }

        function incluirCampoAlt(){
            if(validaFormAlt()){
                if($F('tpCampoAlt')=='S' || $F('tpCampoAlt')=='SM' || $F('tpCampoAlt')=='L')
                    preparaComboAlt();

                selectAllOptions($('vlCampoComboAlt'))

                if (self.$('dvAnimarAguarde')) self.startAnimation();
                new Ajax.Updater('dataTable', 'incluirCampoAlt.do', {
                    method: 'post',
                    parameters: $('camposFormAlt').serialize(),
                    evalScripts: true,
                    onSuccess: function() {
                        if (self.$('dvAnimarAguarde')) self.stopAnimation();
                        alert('Operação realizada com sucesso.');
                        self.$('dvAlteraCampo').remove();

                        limpaFormulario();

                    }, onFailure: function(e) {
                        if (self.$('dvAnimarAguarde')) self.stopAnimation();
                        var stackTrace = e.getHeader("stackTrace") ? e.getHeader("stackTrace") : "";
                        top.frameApp.createErrorPopUp('erroConsultaNumero', e.statusText, stackTrace, false);
                    }
                });
                pesquisar();
            }
        }

        function validaFormAlt(){
            if($('vlCampoComboAlt').options.length<1){
                alert("Selecione pelo menos um valor.");
                return false;
            }

            return true;
        }


        function preparaComboAlt(){
            var destino = $('vlCampoComboAlt');
            for(i=0;i<destino.options.length;i++){
                destino.options[i].selected = true;
            }
        }

        /* fim alteração de campos combo */


            alterar = function (idCampo){
                if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.startAnimation();
                top.frameApp.createNewPopUp('dvAlteraCampo', 'Alterar campo', 500, 400, null, 'alterarCampo.do?idCampo=' + idCampo, true, null);
            }
        </script>
        <SCRIPT FOR="window" EVENT="onload" LANGUAGE="JScript">
            <!--
                document.body.style.backgroundColor = '#ededed';
                <logic:notEmpty name="msgError" scope="request">
                    alert('<bean:write name="msgError" scope="request"/>');
                </logic:notEmpty>
                if(top.frameApp.dvAnimarAguarde != null) top.frameApp.stopAnimation();
                pesquisar();
            -->
        </SCRIPT>
    </netui-template:section>
    <netui-template:section name="bodySection">
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>

        <vivo:sessao id="configurar" width="790" height="480" label="Campos Dinâmicos" operacoes="Manutenção de Campos" scroll="no">
            <form method="post" action="" id="camposForm" name="camposForm" enctype="multipart/form-data" style="margin:0px;" onsubmit="return false;">
            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
            <table border="0" width="95%" align="center" cellpadding="2" cellspacing="2" bgcolor="#ededed" >
                <tr>
                    <td width="100"><b>Nome:</b></td>
                    <td width="161"><input type="text" name="nmCampo" id="nmCampo" value="" style="width:150px;"></td>
                    <td width="74">&nbsp;</td>
                    <td width="410" rowspan="3">
                        <div id="lstCampos" style="display:none;">
                            <select name="vlCampoCombo" size="5" id="vlCampoCombo" style="width:350px;" multiple onmouseover="ativarToolTip(this, 1);">
                            </select>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td><b>Nome de apresentação:</b></td>
                    <td width="161"><input type="text" name="lbCampo" id="lbCampo" value="" style="width:150px;"></td>
                    <td width="74">&nbsp;</td>
                </tr>
                <tr>
                    <td><b>Tipo de Campo:</b></td>
                    <td>
                        <select name="tpCampo" id="tpCampo" style="width:150px;" class="SELECT" onchange="showCombo(this);">
                            <option value="T" selected>Texto</option>
                            <option value="S">Combo</option>
                            <option value="SM">Lista seleção múltipla</option>
                            <option value="LP">Lista preenchida</option>
                            <option value="L">Label</option>
                            <option value="M">Memo</option>
                        </select>
                    </td>
                    <td align="center">
                        <div id="btAdd" style="display:none;">
                            <img src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" onclick="adicionarItem()" style="cursor:pointer;"/>
                        </div>
                    </td>
                </tr>
                <tr id="lnValor" style="display: none;">
                    <td>
                        <div id="dvLblValor" style="display:none;">
                        <b>Valor:</b>
                        </div>&nbsp;
                    </td>
                    <td>
                        <div id="dvVlCampo" style="display:none;">
                            <input type="text" name="vlCampo" id="vlCampo" value="" style="width:150px;">
                        </div>
                    </td>
                    <td align="center">
                        <div id="btRem" style="display:none;">
                            <img src="/FrontOfficeWeb/resources/images/bt_x_nrml.gif" onclick="removerItem()" style="cursor:pointer;"/>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div id="dvLblFormatoTexto">
                        <b>Formato do texto:</b>
                        </div>&nbsp;
                    </td>
                    <td>
                        <div id="dvVlFormatoTexto">
                            <select name="fmtTexto" id="fmtTexto" style="width:150px;" class="SELECT">
                                <option value="" selected>--- Selecione um valor ---</option>
                                <option value="data">Data</option>
                                <option value="hora">Hora</option>
                                <option value="datahora">Data Hora</option>
                                <option value="inteiro">Inteiro</option>
                                <option value="numero">Número</option>
                                <option value="moeda">Moeda</option>
                                <option value="telefone">Telefone</option>
                                <option value="Texto">Texto</option>
                                <option value="cpf">CPF</option>
                            </select>
                        </div>
                    </td>
                    <td align="center">
                        <div id="btRem" style="display:none;">
                            <img src="/FrontOfficeWeb/resources/images/bt_x_nrml.gif" onclick="removerItem()" style="cursor:pointer;"/>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td colspan="3">&nbsp;</td>
                    <td align="right"><img src="/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif" onclick="incluirCampo()" style="cursor:pointer;"/></td>
                </tr>
            </table>
            </form>

            <hr width="90%" align="center"/>

            <div id="dataTable" style="with:740;height:200px;margin-top:10px;margin-left:10px;">
                <vivo:tbTable selectable="true" layoutWidth="740" layoutHeight="200" tableWidth="740" styleId="tableTitle" sortable="true">
                    <vivo:tbHeader>
                        <vivo:tbHeaderColumn align="left" width="95%" type="string">Campos existentes</vivo:tbHeaderColumn>
                        <vivo:tbHeaderColumn align="left" width="5%" type="">&nbsp;</vivo:tbHeaderColumn>
                    </vivo:tbHeader>
                    <vivo:tbRows>
                        <vivo:tbRow key="linha1">
                            <vivo:tbRowColumn></vivo:tbRowColumn>
                            <vivo:tbRowColumn></vivo:tbRowColumn>
                        </vivo:tbRow>
                    </vivo:tbRows>
                </vivo:tbTable>
            </div>
            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
            <hr width="90%" align="center"/>
        </vivo:sessao>
    </netui-template:section>
</netui-template:template>
