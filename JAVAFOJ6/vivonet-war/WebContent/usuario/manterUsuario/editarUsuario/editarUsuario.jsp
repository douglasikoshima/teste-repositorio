<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<bean:define id="FormUser"           name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaUsuariosForm"/>
<bean:define id="usuarioVO"          name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaUsuariosForm.usuarioVO" type="br.com.vivo.fo.usuario.vo.UsuarioVODocument.UsuarioVO" />
<bean:define id="aStatus"            name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaUsuariosForm.listaSelects.statusUsuarioVOArray" />
<bean:define id="aRegional"          name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaUsuariosForm.listaSelects.UFOperadoraUsuarioVOArray" />
<bean:define id="aUFs"               name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaUsuariosForm.listaSelects.UFVOArray"/>
<bean:define id="aMotivos"           name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaUsuariosForm.listaSelects.motivoStatusUsuarioVOArray" />
<bean:define id="aDDDs"              name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaUsuariosForm.listaSelects.DDDVOArray"/>
<bean:define id="aPerfisAtendimento" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaUsuariosForm.listaSelects.perfilConsultorAtdVOArray"/>
<bean:define id="aFornecedores"      name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaUsuariosForm.listaSelects.fornecedorConsultorAtdVOArray"/>
<bean:define id="aSites"             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaUsuariosForm.listaSelects.siteConsultorAtdVOArray"/>
<bean:define id="aTipoDoc"           name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaUsuariosForm.listaSelects.tipoDocumentoVOArray"/>
<bean:define id="listaSelects"       name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaUsuariosForm.listaSelects"/>
<bean:define id="aPaises"            name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaUsuariosForm.listaSelects.paisVOArray" />
<bean:define id="aDocs"              name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaUsuariosForm.docsUsuario.documentoSimpVOArray"/>
<bean:define id="docsUsuario"        name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaUsuariosForm.docsUsuario"/>
<bean:define id="aCargo"             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaUsuariosForm.listaCargo.cargoVOArray"/>
<bean:define id="aNiveis"            name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaUsuariosForm.listaNivelOrganograma.nivelOrganogramaSimplVOArray"/>
<bean:define id="aOrganizacoes"      name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaUsuariosForm.listaOrganizacao.organizacaoSimplVOArray"/>
<bean:define id="aUnidades"          name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaUsuariosForm.listaUnidadeOrganograma.unidadeOrganogramaVOArray"/>
<bean:define id="dadosAtuais"        name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaUsuariosForm.dadosAtuais" />

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">

    <netui-template:setAttribute name="title" value="Manter Sistema"/>
    <netui-template:setAttribute name="modulo" value="Gestão de Usuários"/>

    <netui-template:section name="headerSection">

    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/xml2json.js"></script>
    <script language="javascript" src="/FrontOfficeWeb/resources/scripts/calendar.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
    <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js" ></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/formatPhoneNumber.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/validacao.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/calendar.js"></script>
    <script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/xtooltip.js"></script>

    <script type="text/javascript">

        // Globais
        var bolEnvia = false;
        var isSuperiorImediatoAvailable = <%= (!ConstantesCRM.SVAZIO.equals(usuarioVO.getNomeChefe()) && !ConstantesCRM.SVAZIO.equals(usuarioVO.getLoginChefe())) ? "true" : "false" %>;
        var forceSuperiorImediatoAvailable = isSuperiorImediatoAvailable ? true : false;
        var superiorSelecionado = '';

        onload = function() {
            oculta_div();
            carregaCombosOrgano();
            if ('<bean:write name="FormUser" property="msgError" />' != "") {
                alert('<bean:write name="FormUser" property="msgError" />');
            }
            if ($('idPerfilAtendimento').value == '' || $('idPerfilAtendimento').value == 'VIVO') {
                if ($('idPerfilAtendimento').value == '') {
                    selectListItemByValue('VIVO', $('idPerfilAtendimento'));
                }
                managePerfilAtendimento($('idPerfilAtendimento'));
            }
            $('nome').focus();
            manageConsultorRelacionamento($('inConsultor'));
        }

        function mostraTabela() {
            document.divtable.visibility='visible';
        }

        function escondeMotivo() {
            var a = document.getElementById("div1");
            a.style.visibility = 'hidden';
        }

        function exibeMotivo() {
            var a = document.getElementById("div1");
            a.style.visibility = 'visible';
        }

        function testaMotivo() {
            if (document.forms[0].idStatusAtual.value == "1") {
                exibeMotivo();
            }
            else {
                escondeMotivo();
            }
        }

        valida = function() {

            var form = document.forms[0];

            if ('<bean:write name="FormUser" property="msgError" />' == "") {
                if ($('inConsultor').checked && (superiorSelecionado != $F('loginChefe'))) {
                    isSuperiorImediatoAvailable = false;
                }
            }

            if ($F('nome').blank()) {
                alert('Nome é obrigatório.');
                $('nome').focus();
                return false;

            } else if ($('idUFAtual').selectedIndex == 0) {
                alert('Selecione uma UF.');
                $('idUFAtual').focus();
                return false;

            } else if (!$F('email').blank() && !validaEmail($F('email'))) {
                alert('Formato de e-mail inválido. Digite outro válido.');
                $('email').focus();
                return false;

            }  else if ($F('dsTelefone').blank() && $('idDDDAtual').selectedIndex != 0) {
                alert('Ao Selecionar DDD, é necessário preencher Telefone.');
                $('dsTelefone').focus();
                return false;

            } else if (!$F('dsTelefone').blank() && $F('dsTelefone').length < 8) {
                alert('O Campo Telefone deve ter no mínimo 7 dígitos.');
                $('dsTelefone').focus();
                return false;

            } else if (!$F('dsTelefone').blank() && $('idDDDAtual').selectedIndex == 0) {
                alert('Ao digitar Telefone, é necessário selecionar um DDD.');
                form.dsTelefone.focus();
                return false;

            } else if ($F('login').blank()) {
                alert('Login FrontOffice é obrigatório.');
                $('login').focus();
                return false;

            } else if (!checaLoginCSpecial($('login'), "Login FrontOffice")) {
                $('login').focus();
                return false;

            } else if ($F('login').strip().length < 6) {
                alert('Login FrontOffice não pode ter menos que 6 caracteres.');
                $('login').focus();
                return false;

            } else if (!checaLoginCSpecial($('loginCti'), "Login CTI")) {
                $('loginCti').focus();
                return false;

            <logic:notEqual name="FormUser" property="pessoa" value="true">
            } else if ($('idStatusAtual').selectedIndex == 0) {
                alert('Selecione um Status.');
                return false;
            </logic:notEqual>

            } else if ($('idRegionalAtual').selectedIndex == 0) {
                alert('Selecione uma Regional.');
                $('idRegionalAtual').focus();
                return false;

            } else if ($('nivelOrganogramaAtual').selectedIndex == 0) {
                alert('Selecione uma Hierarquia Funcional.');
                $('nivelOrganogramaAtual').focus();
                return false;

            } else if ($('cargoAtual').selectedIndex == 0) {
                alert('Selecione um Cargo.');
                $('cargoAtual').focus();
                return false;

            } else if ($('organizacaoAtual').selectedIndex == 0 ) {
                alert('Selecione uma Organização.');
                $('organizacaoAtual').focus();
                return false;

            } else if ($('unidadeOrganizacaoAtual').selectedIndex == 0) {
                alert('Selecione um Departamento.');
                $('unidadeOrganizacaoAtual').focus();
                return false;

            } else if ($('idPerfilAtendimento').selectedIndex == 0) {
                alert('Selecione um Perfil de Atendimento.');
                $('idPerfilAtendimento').focus();
                return false;

            } else if ($('idFornecedor').selectedIndex == 0) {
                alert('Selecione um Fornecedor.');
                $('idFornecedor').focus();
                return false;

            } else if ($('idSite').selectedIndex == 0) {
                alert('Selecione um Site.');
                $('idSite').focus();
                return false;

            } else if ($F('loginChefe').blank() && $('idPerfilAtendimento').value != 'VIVO') {
                alert('Login Superior Imediato é obrigatório.');
                return false;

            } else if (!isSuperiorImediatoAvailable && $('idPerfilAtendimento').value != 'VIVO') {
                alert('Login do superior imediato é obrigatório.');
                $('loginChefe').focus();
                $('loginChefe').select();
                return false;

            } else if ($F('loginChefe').indexOf('(') < 0 && !$('inConsultor').checked) {
                alert('Login do superior imediato é obrigatório');
                $('loginChefe').focus();
                $('loginChefe').select();
                return false;

            }

            return true;
        }

        function checaLoginCSpecial(obj, strLogin) {
            valor = obj.value;
            for(i = 0; i < valor.length; i++) {
                if(!alphaNumeric_StrEspecias.test(valor.charAt(i))) {
                    alert('Caracteres permitidos no ' + strLogin + ' são:\n de \"a\" até \"z\" maiúsculas ou minúsculas,números e os caracteres especiais ponto,underline e hífen(\".\","\_"\ e \"-\"),\nPor favor altere o conteúdo do campo.');
                    return false;
                }
            }
            return true;
        }

        salvaDados = function() {
            var form = document.forms[0];

            if (valida()) {

                $('idSite').disabled = false;
                $('idFornecedor').disabled = false;
                $('idPerfilAtendimento').disabled = false;

                $('idSite').disabled = false;
                $('idFornecedor').disabled = false;
                $('idPerfilAtendimento').disabled = false;

                var params = $H();
                if ($('idSite').selectedIndex != 0
                        && $('idSite').value != 'NAO_CLASSIFICADO') {
                    params.set('dsSite', $('idSite').options[$('idSite').selectedIndex].text);
                }
                if ($('idFornecedor').selectedIndex != 0
                        && $('idFornecedor').value != 'NAO_CLASSIFICADO') {
                    params.set('dsFornecedor', $('idFornecedor').options[$('idFornecedor').selectedIndex].text);
                }

                document.forms[0].target = 'frameApp';
                form.action = 'salvaUsuario.do?acao=edicao&' + params.toQueryString();
                mostrar_div();
                form.submit();
            }
        }

        function carregaComboNivel() {

            if(document.forms[0].nivelOrganogramaAtual.value != "")
            {

                if(!bolEnvia) {

                    document.forms[0].target = 'ifmOrganomagrama';
                    document.forms[0].action = 'carregaComboOrgano.do?acao=pesquisaH';
                    mostrar_div();
                    bolEnvia = true;
                    document.forms[0].submit();
                    desativar_combos();

                }
            }else
            {
                while(document.forms[0].elements["cargoAtual"].options.length > 0)
                {
                      document.forms[0].elements["cargoAtual"].options[0]     = null;
                }

                document.forms[0].cargoAtual.options[0] = new Option("Escolha uma opção...", "");

            }
        }

        function carregaComboOrganizacao()
        {
            if(document.forms[0].organizacaoAtual.value != "")
            {
                if(!bolEnvia)
                {

                    document.forms[0].target = 'ifmOrganomagrama';
                    document.forms[0].action = 'carregaComboOrgano.do?acao=pesquisaO';
                    mostrar_div();
                    bolEnvia = true;
                    document.forms[0].submit();
                    desativar_combos();

                }

            }else
            {
                while(document.forms[0].elements["unidadeOrganizacaoAtual"].options.length > 0)
                {
                      document.forms[0].elements["unidadeOrganizacaoAtual"].options[0]     = null;
                }

                document.forms[0].unidadeOrganizacaoAtual.options[0] = new Option("Escolha uma opção...", "");

            }
        }

        function carregaCombosOrgano()
        {
            var nivel = '<bean:write name="dadosAtuais" property="idNivel" />';
            var organizacao = '<bean:write name="dadosAtuais" property="idOrganizacao" />';
            var cargo = '<bean:write name="dadosAtuais" property="idCargo" />';
            var departamento = '<bean:write name="dadosAtuais" property="idUnidade" />';
            var form = document.forms[0];

            form.cargoAtual.value = cargo;
            form.nivelOrganogramaAtual.value = nivel;
            form.organizacaoAtual.value = organizacao;
            form.unidadeOrganizacaoAtual.value = departamento;

        }

        envia = function() {
            document.forms[0].target = 'frameApp';
            document.forms[0].action = 'begin.do?tipo=resultado';
            $('idSite').disabled = false;
            $('idFornecedor').disabled = false;
            $('idPerfilAtendimento').disabled = false;
            mostrar_div();
            document.forms[0].submit();
        }

        function limpar()
        {
            var form = document.forms[0];

            form.tipoDocAtual.selectedIndex = 0;
            form.dsDocAtual.value = '';
            form.idUFDocAtual.selectedIndex = 0;
            form.idPaisDocAtual.selectedIndex = 0;

            <logic:iterate name="listaSelects" property="tipoDocumentoVOArray" id="documento" >
                if(<bean:write name="documento" property="sgTipoDocumento"/>)
                {
                    <bean:write name="documento" property="sgTipoDocumento"/>.style.visibility = 'hidden';
                }
            </logic:iterate>

        }

        function incluirDoc()
        {
            if(validaDoc()) {

                $('idSite').disabled = false;
                $('idFornecedor').disabled = false;
                $('idPerfilAtendimento').disabled = false;

                document.forms[0].target = 'frameApp';
                document.forms[0].action = 'incluiDocumento.do?tipo=edita';
                mostrar_div();
                document.forms[0].submit();
            }
        }

        function mostraTipoDoc(){
                document.forms[0].dsDocAtual.value = "";
                <logic:iterate name="listaSelects" property="tipoDocumentoVOArray" id="documento" >
                var <bean:write name="documento" property="sgTipoDocumento"/> = document.getElementById("<bean:write name="documento" property="sgTipoDocumento"/>");
                <bean:write name="documento" property="sgTipoDocumento"/>.style.visibility = 'hidden';
                if (document.forms[0].tipoDocAtual.value == "<bean:write name="documento" property="sgTipoDocumento"/>"){
                    <bean:write name="documento" property="sgTipoDocumento"/> = document.getElementById("<bean:write name="documento" property="sgTipoDocumento"/>");
                    <bean:write name="documento" property="sgTipoDocumento"/>.style.visibility = 'visible';
                }
                </logic:iterate>
                document.forms[0].dsDocAtual.focus();
        }

        function checaDoc(obj)
        {
            var form = document.forms[0];
            //Se CPF.
            if(form.tipoDocAtual.value == 'CPF')
            {
                checaCPF(obj);

            //Se CNPJ.
            }else if(form.tipoDocAtual.value == 'CNPJ')
            {
                checaCNPJ(obj);

             //Se R.G.
            }else if(form.tipoDocAtual.value == 'RG')
            {
                if(obj.value.length > 12)
                {
                    obj.value = obj.value.substring(0,12);
                    return false;
                }else
                    checaStrEspecial(obj);

            // Se R.E.
            }else if(form.tipoDocAtual.value == 'RE')
            {
                if(obj.value.length > 12)
                {
                    obj.value = obj.value.substring(0,12);
                    return false;
                }else
                    checaInteiro(obj);

            }
        }


        function validaDoc()
        {
            var form = document.forms[0];
            if(form.tipoDocAtual.selectedIndex == 0)
            {
                alert('Selecione um Documento.');
                return false;

            }else if(form.dsDocAtual.value == '')
            {
                alert('Documento é obrigatório.');
                return false;

            }else if(form.idUFDocAtual.selectedIndex == 0)
            {
                alert('Selecione uma UF.');
                return false;

            }else if(form.idPaisDocAtual.selectedIndex == 0)
            {

                alert('Selecione um País.');
                return false;

             //Se CPF
            }else if(form.tipoDocAtual.value == 'CPF')
            {
                if(validaCPF(form.dsDocAtual.value) == false)
                {
                    alert('Formato de CPF inválido.');
                    return false;
                }

            // Se CNPJ.
            }else if(form.tipoDocAtual.value == 'CNPJ')
            {
                if(validaCNPJ(form.dsDocAtual.value) == false)
                {
                    alert('Formato de CNPJ inválido.');
                    return false;
                }

            }

            return true;

        }


        function excluirDoc(idDoc)
        {
            if (window.confirm("Confirma remoção do Documento?")) {

                $('idSite').disabled = false;
                $('idFornecedor').disabled = false;
                $('idPerfilAtendimento').disabled = false;

                document.forms[0].target = 'frameApp';
                document.forms[0].tipoDocAtual.value = idDoc;
                document.forms[0].tipoDocAtualEdita.value = idDoc;
                document.forms[0].action = 'excluiDocumento.do?acao=edicao';
                mostrar_div();
                document.forms[0].submit();
            }
        }

    // Funções para digitar somente letras, numeros e os caracteres "ponto, underline e traço"
    alphaNumeric_StrEspecias = new RegExp("[0-9a-zA-Z\._-]");
    alphaNumeric = new RegExp("[0-9a-zA-Z]");

    function checaCaracteresLogin(obj) {
        valor = obj.value;
        if (obj.id != 'loginChefe' || (obj.id == 'loginChefe' && $('idPerfilAtendimento').value != 'VIVO' && !isSuperiorImediatoAvailable)) {
            for (i = 0; i < valor.length; i++) {
                if (!alphaNumeric_StrEspecias.test(valor.charAt(i))) {
                    valor = valor.substring(0,i) + valor.substring(i+1,valor.length);
                    alert('Caracteres permitidos no Login são:\n de \"a\" até \"z\" maiúsculas ou minúsculas,números e os caracteres especiais ponto,underline e hífen(\".\","\_"\ e \"-\")');
                    i = -1;
                }
            }
            obj.focus();
            obj.value = valor;
        }
    }

    function alterarDoc(iDoc) {
        if (iDoc == "CPF") {
            document.forms[0].tipoDocAtual.value = iDoc;
            document.forms[0].dsDocAtual.value = document.forms[0].dsDoc0.value;
            document.forms[0].idUFDocAtual.value = document.forms[0].idufDoc0.value;
            document.forms[0].idPaisDocAtual.value = document.forms[0].idpaisDoc0.value;
        }
        if (iDoc == "RG") {
            document.forms[0].tipoDocAtual.value = iDoc;
            document.forms[0].dsDocAtual.value = document.forms[0].dsDoc1.value;
            document.forms[0].idUFDocAtual.value = document.forms[0].idufDoc1.value;
            document.forms[0].idPaisDocAtual.value = document.forms[0].idpaisDoc1.value;
        }
        if (iDoc == "RE") {
            document.forms[0].tipoDocAtual.value = iDoc;
            document.forms[0].dsDocAtual.value = document.forms[0].dsDoc2.value;
            document.forms[0].idUFDocAtual.value = document.forms[0].idufDoc2.value;
            document.forms[0].idPaisDocAtual.value = document.forms[0].idpaisDoc2.value;
        }
    }

    clickCancelaEnter = function(op) {
        if (op.keyCode == 13) {
            return false;
        }
        return true;
    }

    frmsState = null;
    function desativar_combos() {
        if (frmsState == null) {
            forms=document.forms;
            frmsState=new Array(forms.length);
            for (i=0;i<forms.length;i++) {
                el=forms[i].elements;
                elState=new Array(el.length);
                frmsState[i]=elState;
                for(j=0;j<el.length;j++){elState[j]=el[j].disabled;el[j].disabled=true;}
            }
        }
        return;
    }
    function ativar_combos() {
        if (frmsState != null) {
            forms=document.forms;
            for (i=0;i<forms.length;i++) {
                el=forms[i].elements;
                elState=frmsState[i];
                frmsState[i]=elState;
                for(j=0;j<el.length;j++){el[j].disabled=elState[j];}
            }
        }
        frmsState = null;
        return;
    }

    manageConsultorRelacionamento = function(o) {

        if (o.checked) {

            $('loginChefe').readOnly = false;
            var hasCROption = false;
            for (var i = 0; i < $('idPerfilAtendimento').options.length; i++) {
                if ($('idPerfilAtendimento').options[i].value == 'CR') {
                    hasCROption = true;
                    break;
                }
            }
            if (!hasCROption) {
                addValueToSelectList('Consultor de Relacionamento', 'CR', $('idPerfilAtendimento'));
            }

            if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.startAnimation();
            selectListItemByValue('CR', $('idPerfilAtendimento'));
            managePerfilAtendimento($('idPerfilAtendimento'));
            $('td_login_roteamento_label').show();
            $('td_login_roteamento_input').show();

        } else {
            removeListItemByValue('CR', $('idPerfilAtendimento'));
            $('idPerfilAtendimento').disabled = false;
            $('dsLoginRoteamento').value = '';
            $('td_login_roteamento_label').hide();
            $('td_login_roteamento_input').hide();
        }
    }

    managePerfilAtendimento = function(o) {

        isSuperiorImediatoAvailable = false;
        var idPerfilSelecionado = o.value;

        if ($('idSite').disabled) {

            $('idSite').disabled = false;
            $('idFornecedor').disabled = false;
            $('idFornecedor').selectedIndex = 0;
            $('idSite').selectedIndex = 0;
            $('idSite').options[$('idSite').options.length-1] = null;
            $('idFornecedor').options[$('idFornecedor').options.length-1] = null;

        }

        if (idPerfilSelecionado == '') {

            $('loginChefe').value = '';
            $('loginChefe').readOnly = true;

        } else if (idPerfilSelecionado == 'VIVO') {

            if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();
            setLoginSuperiorImediato();
            $('loginChefe').readOnly = true;
            isSuperiorImediatoAvailable = true;

            addValueToSelectList('Não classificado', 'NAO_CLASSIFICADO', $('idFornecedor'));
            addValueToSelectList('Não classificado', 'NAO_CLASSIFICADO', $('idSite'));
            $('idSite').selectedIndex = $('idSite').options.length-1;
            $('idFornecedor').selectedIndex = $('idFornecedor').options.length-1;
            $('idSite').disabled = true;
            $('idFornecedor').disabled = true;

            $('btPesquisarSuperiorImediato').hide();
            $('loginChefe').setStyle({
                'background' : 'transparent',
                'width' : '300px',
                'border' : 'none'
            });

            if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();

        } else {

            if ($('inConsultor').checked && forceSuperiorImediatoAvailable) {
                isSuperiorImediatoAvailable = true;
            } else {
                $('loginChefe').value = '';
                $('loginChefe').readOnly = false;
            }

            $('btPesquisarSuperiorImediato').show();
            $('loginChefe').setStyle({
                'background' : '#fff',
                'width' : '220px',
                'border' : '1px solid black'
            });

            if (window.top.frameApp.$('idAnime')) window.top.frameApp.$('idAnime').hide();

        }

        setLoginSuperiorImediato();

        if ($('inConsultor').checked) {
            $('idPerfilAtendimento').disabled = true;
        }

    }

    pesquisarSuperiorImediato = function() {
        if (!$('loginChefe').readOnly) {
            if ($('idPerfilAtendimento').selectedIndex == 0) {
                alert('Selecione um Perfil de Atendimento.');
                $('idPerfilAtendimento').focus();
                $('loginChefe').value = '';
            } else if ($('inConsultor').checked && $('idFornecedor').selectedIndex == 0) {
                alert('Por favor, selecione um fornecedor.');
            } else if ($F('loginChefe').blank()) {
                alert('Por favor, digite um login de superior imediato para pesquisa.');
            } else if ($F('loginChefe').strip().length < 7) {
                alert('Por favor, digite um login de superior imediado com ao menos 7 caracteres.');
            } else {

                var dsLoginSuperiorImediato = ($F('loginChefe').indexOf('(') > 0 && $F('loginChefe').indexOf(')') > 0)
                        ? $F('loginChefe').substring($F('loginChefe').indexOf('(') + 1, $F('loginChefe').indexOf(')'))
                        : $F('loginChefe');

                var params = $H();
                params.set('inConsultor', $('inConsultor').checked ? true : false);
                params.set('dsLoginSuperiorImediato', dsLoginSuperiorImediato);
                params.set('operacao', 'isSuperiorImediatoAvailable');

                var manageDisabled = false;
                if ($('idPerfilAtendimento').disabled) {
                    $('idPerfilAtendimento').disabled = false;
                    manageDisabled = true;
                }
                params.set('idPerfil', $('idPerfilAtendimento').value);
                if (manageDisabled) {
                    $('idPerfilAtendimento').disabled = true;
                }

                isSuperiorImediatoAvailable = false;

                new Ajax.Request('/FrontOfficeWeb/usuario/manterUsuario/editarUsuario/manageSuperioresImediatos.do?' + params.toQueryString(), {
                    method: 'post',
                    parameters: $('listaUsuariosForm').serialize(),
                    onSuccess: function(e) {

                        if (e.responseText != '') {

                            var dom = parseXml(e.responseText);
                            var jsonString = xml2json(dom, '');
                            var jsonObj = jsonString.evalJSON();

                            // Consultor de Relacionamento.
                            // Regra: Qualquer usuário do sistema pode ser considerado superior imediato.
                            if ($('inConsultor').checked) {
                                if (jsonObj.out.usuarioEncontrado == 'false') {
                                    alert('Usuário não encontrado.');
                                } else {
                                    if (jsonObj.out.mesmoFornecedor == 'false') {
                                        if (confirm('Este Consultor e seu Superior Imediato possuem fornecedores diferentes. Deseja confirmar?')) {
                                            isSuperiorImediatoAvailable = true;
                                            $('loginChefe').value = jsonObj.out.nome;
                                        } else {
                                            $('loginChefe').value = '';
                                            $('loginChefe').focus();
                                        }
                                    } else {
                                        isSuperiorImediatoAvailable = true;
                                        $('loginChefe').value = jsonObj.out.nome;
                                    }
                                }
                                superiorSelecionado = $F('loginChefe');
                                return;
                            } else {
                                if (jsonObj.UsuarioVO
                                        && jsonObj.UsuarioVO.idUsuario
                                        && jsonObj.UsuarioVO.idUsuario != '') {

                                    var nmMeio = jsonObj.UsuarioVO.nomeMeio ? ' ' + jsonObj.UsuarioVO.nomeMeio : '';
                                    var dsCampo = jsonObj.UsuarioVO.nome
                                                  + nmMeio
                                                  + ' ' + jsonObj.UsuarioVO.sobrenome + ' (' + jsonObj.UsuarioVO.login  + ')';

                                    $('loginChefe').value = dsCampo;
                                    isSuperiorImediatoAvailable = true;
                                }
                            }
                        }

                        if (!isSuperiorImediatoAvailable) {
                            alert('Login Superior Imediato é Obrigatório.');
                            $('loginChefe').focus();
                            $('loginChefe').select();
                        }

                    }, onCreate: function() {
                        if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.startAnimation();
                    }, onComplete: function() {
                        if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                    }, onException: function(e) {
                        if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                    }, on406: function(e) {
                        if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                    }
                });
            }
        }
    }

    setLoginSuperiorImediato = function() {
        if ($('idPerfilAtendimento').value.toUpperCase() == 'VIVO') {
            $('loginChefe').value = $F('nome') + ' (' + $F('login') + ')';
        }
        if ($('inConsultor').checked) {
            isSuperiorImediatoAvailable = true;
        }
    }

    </script>
    <style type="text/css">
        .obrigatorio {
            color:#ff0000;
            margin-right:4px;
        }
        #table_pessoa td {
            font-weight:bold;
            white-space:nowrap;
        }
        #table_usuario td {
			font-weight:bold;
		}
		#table_perfil td {
			font-weight:bold;
		}
        #body_perfil select {
            width:130px;
            margin-left:3px;
        }
        #body_usuario select {
            width:290px;
            margin-left:3px;
        }
    </style>

</netui-template:section>
<netui-template:section name="bodySection">

<acesso:controlHiddenItem nomeIdentificador="usu_eus_verpagina">

<jsp:include page="/resources/menus/MenuPrincipal.jsp" />
<div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="3"></div>

<vivo:quadro id="qdMain" height="482" width="790" label="Manutenção de Usuário > Editar Usuário" scroll="no">

    <form name="listaUsuariosForm" id="listaUsuariosForm" method="post" action="/FrontOfficeWeb/usuario/manterUsuario/editarUsuario/salvaUsuario.do" onsubmit="return false;">

        <div id="ttip" style="display:none;position:absolute;width:170px;"></div>

        <input type="hidden" name="acao" value="">
        <html:hidden name="FormUser" property="dsDoc2"/>
        <html:hidden name="FormUser" property="idUsuario"/>
        <html:hidden name="FormUser" property="tipo"/>
        <html:hidden name="FormUser" property="tipoDocAtualEdita"/>
        <html:hidden name="FormUser" property="tipoDoc0"/>
        <html:hidden name="FormUser" property="idDoc0"/>
        <html:hidden name="FormUser" property="sgDoc0"/>
        <html:hidden name="FormUser" property="dsDoc0"/>
        <html:hidden name="FormUser" property="ufDoc0"/>
        <html:hidden name="FormUser" property="paisDoc0"/>
        <html:hidden name="FormUser" property="idufDoc0"/>
        <html:hidden name="FormUser" property="idpaisDoc0"/>
        <html:hidden name="FormUser" property="tipoDoc1"/>
        <html:hidden name="FormUser" property="idDoc1"/>
        <html:hidden name="FormUser" property="sgDoc1"/>
        <html:hidden name="FormUser" property="dsDoc1"/>
        <html:hidden name="FormUser" property="ufDoc1"/>
        <html:hidden name="FormUser" property="paisDoc1"/>
        <html:hidden name="FormUser" property="idufDoc1"/>
        <html:hidden name="FormUser" property="idpaisDoc1"/>
        <html:hidden name="FormUser" property="tipoDoc2"/>
        <html:hidden name="FormUser" property="idDoc2"/>
        <html:hidden name="FormUser" property="sgDoc2"/>
        <html:hidden name="FormUser" property="ufDoc2"/>
        <html:hidden name="FormUser" property="paisDoc2"/>
        <html:hidden name="FormUser" property="idufDoc2"/>
        <html:hidden name="FormUser" property="idpaisDoc2"/>
        <html:hidden name="usuarioVO" property="nmUF"/>
        <html:hidden name="FormUser" property="loginFOWAntigo" />
        <html:hidden name="usuarioVO" property="nomeChefe" styleId="nomeChefe" />

        <vivo:quadro id="pessoa" width="780" height="55" label="Dados de Pessoa" scroll="no">

            <table width="100%">
                <tr>
                    <td><span class="obrigatorio">*</span>Nome:</td>
                    <td colspan="4">
                        <html:text name="usuarioVO"
                                   tabindex="1"
                                   property="nome"
                                   styleId="nome"
                                   style="width:710px"
                                   styleClass="input"
                                   maxlength="254"
                                   onfocus="setLoginSuperiorImediato()"
                                   onkeyup="setLoginSuperiorImediato()"
                                   onblur="setLoginSuperiorImediato()"
                                   onkeypress="setLoginSuperiorImediato(); return clickCancelaEnter(window.event);" />
                    </td>
                </tr>
                <tr>
                    <td><span class="obrigatorio">*</span>UF:</td>
                    <td>
                        <html:select name="FormUser"
                                     tabindex="2"
                                     property="idUFAtual"
                                     styleId="idUFAtual"
                                     style="width:180px;margin-left:3px;"
                                     onmouseover="ativarToolTip(this,1)">
                            <html:option value="">Escolha...</html:option>
                            <html:options collection="aUFs" property="idUF" labelProperty="nmUF" />
                        </html:select>
                    </td>
                    <td>
                        E-mail: &nbsp;
                        <html:text name="usuarioVO"
                                   tabindex="3"
                                   property="email"
                                   styleId="email"
                                   style="width:235px"
                                   styleClass="input"
                                   maxlength="254" />
                    </td>
                    <td>
                        DDD: &nbsp;
                        <html:select name="FormUser"
                                     tabindex="4"
                                     property="idDDDAtual"
                                     style="width:70px"
                                     onmouseover="ativarToolTip(this,1)">
                            <html:option value="0">Escolha...</html:option>
                            <html:options collection="aDDDs" property="idDDD" labelProperty="dsDDD"/>
                        </html:select>
                    </td>
                    <td>
                        Telefone: &nbsp;
                        <html:text name="usuarioVO"
                                   tabindex="5"
                                   property="dsTelefone"
                                   style="width:65px"
                                   styleClass="input"
                                   size="16"
                                   maxlength="10"
                                   onkeyup="Formatar(this.value, this.form.name, this.name, 'telefone');" />
                    </td>
                </tr>
            </table>
            <table width="100%">
                <tr>
                    <td class="tblEstatica_campoNome">
                        <font color="red">*</font><netui:label value="UF: " styleClass="tblEstatica_campoNome" style="text-indent:4px;"/>
                    </td>
                    <td align="left" colspan="3">

                    </td>
                </tr>
            </table>
        </vivo:quadro>
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
        <table width="780" cellpadding="0" cellspacing="0" border="0">
            <tr>
                <td width="378" valign="top">
                    <vivo:quadro id="usuario" width="448" height="190" label="Dados de Usuário" scroll="no">
                        <table width="100%">
                            <tr>
                                <td><span class="obrigatorio">*</span>Login FrontOffice:</td>
                                <td>
                                    <html:text name="usuarioVO"
                                               tabindex="6"
                                               property="login"
                                               styleId="login"
                                               style="width:100px"
                                               styleClass="input"
                                               size="14"
                                               maxlength="9"
                                               onkeydown="checaCaracteresLogin(this)"
                                               onkeyup="checaCaracteresLogin(this);setLoginSuperiorImediato()"
                                               onblur="setLoginSuperiorImediato()" onfocus="setLoginSuperiorImediato()"
                                               onkeypress="return clickCancelaEnter(window.event);" />
                                    <span class="obrigatorio" style="margin-left:15px;">&nbsp;</span>Login CTI:
                                    <html:text name="usuarioVO"
                                               tabindex="7"
                                               property="loginCti"
                                               styleId="loginCti"
                                               style="width:111px"
                                               styleClass="input"
                                               size="14"
                                               maxlength="254"
                                               onkeydown="checaCaracteresLogin(this)"
                                               onkeyup="checaCaracteresLogin(this)"
                                               onkeypress="return clickCancelaEnter(window.event);" />
                                </td>
                            </tr>
                            <tr>
                                <td><span class="obrigatorio">*</span>Status:</td>
                                <td>
                                    <html:select name="FormUser"
                                                 tabindex="8"
                                                 property="idStatusAtual"
                                                 styleId="idStatusAtual"
                                                 style="margin-left:3px;"
                                                 onmouseover="ativarToolTip(this,1)">
                                        <html:option value="">Escolha uma opção...</html:option>
                                        <html:options collection="aStatus" property="idStatus" labelProperty="nmStatus" />
                                    </html:select>
                                </td>
                            </tr>
                            <tr>
                                <td><span class="obrigatorio">*</span>Regional:</td>
                                <td>
                                    <html:select name="FormUser"
                                                 tabindex="9"
                                                 property="idRegionalAtual"
                                                 styleId="idRegionalAtual"
                                                 style="height:100px;margin-left:3px;"
                                                 onmouseover="ativarToolTip(this,1)">
                                        <html:option value="">Escolha uma opção...</html:option>
                                        <html:options collection="aRegional" property="idUFOperadora" labelProperty="dsUFOperadora" />
                                    </html:select>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    Consultor de Relacionamento:
                                    <html:checkbox name="FormUser"
                                                   tabindex="10"
                                                   property="inConsultor"
                                                   styleId="inConsultor"
                                                   value="1"
                                                   onclick="manageConsultorRelacionamento(this)"
                                                   styleClass="radio" />
                                </td>
                            </tr>
                            <tr>
                                <td><span class="obrigatorio">*</span>Hierarquia Funcional:</td>
                                <td>
                                    <html:select name="FormUser"
                                                 tabindex="11"
                                                 property="nivelOrganogramaAtual"
                                                 styleId="nivelOrganogramaAtual"
                                                 style="height:100px;margin-left:3px;"
                                                 onchange="carregaComboNivel();"
                                                 onmouseover="ativarToolTip(this,1)">
                                        <html:option value="">Escolha uma opção...</html:option>
                                        <html:options collection="aNiveis" property="idNivel" labelProperty="dsNivel" />
                                    </html:select>
                                </td>
                            </tr>
                            <tr>
                                <td><span class="obrigatorio">*</span>Cargo:</td>
                                <td>
                                    <div id="divHFuncional">
                                    <html:select name="FormUser"
                                                 tabindex="12"
                                                 property="cargoAtual"
                                                 styleId="cargoAtual"
                                                 style="height:70px;margin-left:3px;"
                                                 onmouseover="ativarToolTip(this,1)">
                                        <html:option value="">Escolha uma opção...</html:option>
                                        <html:options collection="aCargo" property="idCargo" labelProperty="nmCargo" />
                                    </html:select>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td><span class="obrigatorio">*</span>Organização:</td>
                                <td>
                                    <html:select name="FormUser"
                                                 tabindex="13"
                                                 property="organizacaoAtual"
                                                 style="height:100px;margin-left:3px;"
                                                 onchange="carregaComboOrganizacao();"
                                                 onmouseover="ativarToolTip(this,1)">
                                        <html:option value="">Escolha uma opção...</html:option>
                                        <html:options collection="aOrganizacoes" property="idOrganizacao" labelProperty="dsTipoOrganizacao" />
                                    </html:select>
                                </td>
                            </tr>
                            <tr>
                                <td><span class="obrigatorio">*</span>Departamento:</td>
                                <td>
                                    <div id="divOrganizacao">
                                        <html:select name="FormUser"
                                                     tabindex="14"
                                                     property="unidadeOrganizacaoAtual"
                                                     style="height:70px;margin-left:3px;"
                                                     onmouseover="ativarToolTip(this,1)">
                                            <html:option value="">Escolha uma opção...</html:option>
                                            <html:options collection="aUnidades" property="idUnidade" labelProperty="nmUnidade" />
                                        </html:select>
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </vivo:quadro>
                </td>
                <td width="5" valign="top">&nbsp;</td>
                <td width="397" valign="top">
                    <vivo:quadro id="perfil" width="327" height="190" label="Dados de Perfil" scroll="no">
                        <table width="100%">
                            <tr>
                                <td nowrap><span class="obrigatorio">*</span>Perfil de Atendimento:</td>
                                <td>
                                    <html:select name="FormUser"
                                                 tabindex="15"
                                                 property="idPerfilAtendimento"
                                                 styleId="idPerfilAtendimento"
                                                 onchange="managePerfilAtendimento(this)"
                                                 onmouseover="ativarToolTip(this,1)">
                                        <option value="">Escolha uma opção...</option>
                                        <html:options collection="aPerfisAtendimento" property="idPerfilConsultorAtd" labelProperty="dsPerfilConsultorAtd" />
                                    </html:select>
                                </td>
                            </tr>
                            <tr>
                                <td><span class="obrigatorio">*</span>Fornecedor:</td>
                                <td>
                                    <html:select name="FormUser"
                                                 property="idFornecedor"
                                                 styleId="idFornecedor"
                                                 tabindex="16"
                                                 onmouseover="ativarToolTip(this,1)">
                                        <option value="">Escolha uma opção...</option>
                                        <html:options collection="aFornecedores" property="idFornecedorConsultorAtd" labelProperty="dsFornecedorConsultorAtd" />
                                    </html:select>
                                </td>
                            </tr>
                            <tr>
                                <td><span class="obrigatorio">*</span>Site:</td>
                                <td>
                                    <html:select name="FormUser"
                                                 property="idSite"
                                                 styleId="idSite"
                                                 tabindex="17"
                                                 onmouseover="ativarToolTip(this,1)">
                                        <option value="">Escolha uma opção...</option>
                                        <html:options collection="aSites" property="idSiteConsultorAtd" labelProperty="dsSiteConsultorAtd" />
                                    </html:select>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">&nbsp;</td>
                            </tr>
                            <tr>
                                <td valign="top" colspan="2"><span class="obrigatorio">*</span>Login Superior Imediato:</td>
                            </tr>
                            <tr>
                                <td valign="top" colspan="2">
                                    <input type="text"
                                                   name="loginChefe"
                                                   id="loginChefe"
                                                   maxlength="254"
                                                   size="14"
                                                   tabindex="18"
                                                   value="<%= ((usuarioVO.getNomeChefe() != null && !ConstantesCRM.SVAZIO.equals(usuarioVO.getNomeChefe())) && (usuarioVO.getLoginChefe() != null && !ConstantesCRM.SVAZIO.equals(usuarioVO.getLoginChefe())))
                                                            ? (usuarioVO.getNomeChefe() + " (" + usuarioVO.getLoginChefe() + ")")
                                                            : usuarioVO.getLoginChefe() == null ? ConstantesCRM.SVAZIO : usuarioVO.getLoginChefe() %>"
                                                   onkeyup="isSuperiorImediatoAvailable=false;forceSuperiorImediatoAvailable=false;"
                                                   readonly="readonly"
                                                   style="width:220px"
                                                   class="input"
                                                   title="Selecione um perfil de atendimento." />
                                    <img src="<%=request.getContextPath()%>/resources/images/bt_pesquisar_nrml.gif"
                                         id="btPesquisarSuperiorImediato"
                                         onmouseup="pesquisarSuperiorImediato()"
                                         style="cursor:pointer;margin:2px 0 0 3px" />
                                </td>
                            </tr>
                            <tr>
                                <td id="td_login_roteamento_label" style="display:none"><span class="obrigatorio">&nbsp;&nbsp;</span>Login (roteamento):</td>
                                <td id="td_login_roteamento_input" style="display:none">
                                    <html:text name="FormUser"
                                               property="dsLoginRoteamento"
                                               styleId="dsLoginRoteamento"
                                               maxlength="10"
                                               tabindex="18" />
                                </td>
                            </tr>
                            <tr>
                                <td id="td_dataExpiracao">Data Expiração:</td>
                                <td id="td_dataExpiracao_input">
                                    <html:text name="FormUser" 
                                        id="dataExpiracao" 
                                    	property="dataExpiracao" 
                                    	styleId="dataExpiracao" 
                                    	maxlength="10" 
                                    	tabindex="18" 
                                    	onblur="validaData(this);"
                                    	onkeyup="Formatar(this.value, this.form.name, this.name, 'data');" 
                                    	onchange="Formatar(this.value, this.form.name, this.name, 'data');" />
                                    <img src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:hand;" title="Calendário" onclick="return showCalendar('dataExpiracao', '%d/%m/%Y');">
                                </td>
                                
                            </tr>
                        </table>
                    </vivo:quadro>
                </td>
            </tr>
        </table>
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
        <vivo:quadro id="documento" width="780" height="156" label="Dados de Documento" scroll="no">
            <table width="100%" border="0" cellpadding="0" cellspacing="0">
                <tr>
                    <td width="14%" class="tblEstatica_campoNome">
                        <font color="red">*</font><netui:label value="Documento: " styleClass="tblEstatica_campoNome" style="text-indent:4px;"/>
                    </td>
                    <td align="left">
                        <html:select name="FormUser"
                                     property="tipoDocAtual"
                                     tabindex="19"
                                     onchange="mostraTipoDoc();"
                                     style="width:70px;height:70px;margin-left:3px;"
                                     onmouseover="ativarToolTip(this,1)">
                            <html:option value="">Escolha...</html:option>
                            <html:options collection="aTipoDoc" property="sgTipoDocumento" labelProperty="sgTipoDocumento" />
                        </html:select>
                    </td>
                    <td  class="tblEstatica_campoNome">
                        <div id="tipoDoc" style="visibility:'visible'; position:relative; top: 0px; padding: 0px;">
                        <logic:iterate name="listaSelects" property="tipoDocumentoVOArray" id="documento" >
                            <div id="<bean:write name="documento" property="sgTipoDocumento"/>" style="visibility:'hidden'; position:absolute; top: 0px; left: 0px; height: 0px; padding: 0px;">
                                <bean:write name="documento" property="sgTipoDocumento"/>
                            </div>
                        </logic:iterate>
                        </div>
                    </td>
                    <td  align="left">
                       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                       <html:text name="FormUser" property="dsDocAtual" tabindex="20" styleClass="input" size="14" onkeyup="checaDoc(this);"/>
                    </td>
                    <td  class="tblEstatica_campoNome">
                        <netui:label value="UF: " styleClass="tblEstatica_campoNome" style="text-indent:4px;"/>
                    </td>
                    <td  align="left">
                        <html:select name="FormUser"
                                     property="idUFDocAtual"
                                     tabindex="21"
                                     style="width:120px;height:160px;margin-left:3px;"
                                     onmouseover="ativarToolTip(this,1)">
                            <html:option value="">Escolha...</html:option>
                            <html:options collection="aUFs" property="idUF" labelProperty="nmUF" />
                        </html:select>
                    </td>
                    <td  class="tblEstatica_campoNome">
                        <netui:label value="País: " styleClass="tblEstatica_campoNome" style="text-indent:4px;"/>
                    </td>
                    <td align="left">
                        <html:select name="FormUser"
                                     property="idPaisDocAtual"
                                     tabindex="22"
                                     style="width:70px;height:70px;margin-left:3px;"
                                     onmouseover="ativarToolTip(this,1)">
                            <html:option value="">Escolha...</html:option>
                                <logic:iterate name="aPaises" id="iPais">
                                    <bean:define id="varPais" name="iPais" property="nmPais"/>
                                    <bean:define id="varUpperPais" value="<%=varPais.toString().toUpperCase()%>"/>
                                    <logic:equal name="varUpperPais"  value="BRASIL">
                                          <option selected value="<bean:write name='iPais' property='idPais'/>"><bean:write name="iPais" property="nmPais"/></option>
                                    </logic:equal>
                                    <logic:notEqual name="varUpperPais"  value="BRASIL">
                                          <option value="<bean:write name='iPais' property='idPais'/>"><bean:write name="iPais" property="nmPais"/></option>
                                    </logic:notEqual>
                                </logic:iterate>
                        </html:select>
                    </td>
                    <td align="left">
                        <acesso:controlHiddenItem nomeIdentificador="usu_cus_incluir_edit">
                            <a href="Javascript:incluirDoc();" tabindex="23">
                                <img src="/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif" alt="Incluir Documento" style="border:none;cursor:pointer">
                            </a>
                        </acesso:controlHiddenItem>
                        <img id="btLimpar"
                                           tabindex="24"
                                           onClick="limpar();"
                                           style="border:none;cursor:pointer;"
                                           src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif"
                                           onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif'"
                                           onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_limpar_over.gif'" />
                    </td>
                </tr>
            </table>
            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="10"></div>
            <center>
            <vivo:tbTable styleId="tbDocumentos" layoutHeight="55" tableWidth="700" layoutWidth="700" selectable="true" sortable="true">
                <vivo:tbHeader>
                    <vivo:tbHeaderColumn align="left"   width="180" type="string">Documento</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="left"   width="310" type="string">Número</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="center" width="34" type="string">UF</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="center" width="100" type="string">Pais</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="center" width="30" type="string"></vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="center" width="30" type="string"></vivo:tbHeaderColumn>
                </vivo:tbHeader>
                <vivo:tbRows>

                <logic:equal name="FormUser" property="sgDoc0" value="CPF">
                    <vivo:tbRow key="linha1">
                        <vivo:tbRowColumn><bean:write name="FormUser" property="sgDoc0"/></vivo:tbRowColumn>
                        <vivo:tbRowColumn><bean:write name="FormUser" property="dsDoc0"/></vivo:tbRowColumn>
                        <vivo:tbRowColumn><bean:write name="FormUser" property="ufDoc0"/></vivo:tbRowColumn>
                        <vivo:tbRowColumn><bean:write name="FormUser" property="paisDoc0"/></vivo:tbRowColumn>
                        <vivo:tbRowColumn>
                        <a href="Javascript:alterarDoc('<bean:write name="FormUser" property="sgDoc0"/>');">
                            <img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" alt="Alterar Documento" border="0">
                        </a>
                        </vivo:tbRowColumn>
                        <vivo:tbRowColumn>
                        <a href="Javascript:excluirDoc('<bean:write name="FormUser" property="sgDoc0"/>');">
                            <img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" alt="Excluir Documento" border="0">
                        </a>
                        </vivo:tbRowColumn>
                    </vivo:tbRow>
                </logic:equal>
                <logic:equal name="FormUser" property="sgDoc1" value="RG">
                    <vivo:tbRow key="linha1">
                        <vivo:tbRowColumn><bean:write name="FormUser" property="sgDoc1"/></vivo:tbRowColumn>
                        <vivo:tbRowColumn><bean:write name="FormUser" property="dsDoc1"/></vivo:tbRowColumn>
                        <vivo:tbRowColumn><bean:write name="FormUser" property="ufDoc1"/></vivo:tbRowColumn>
                        <vivo:tbRowColumn><bean:write name="FormUser" property="paisDoc1"/></vivo:tbRowColumn>
                        <vivo:tbRowColumn>
                        <a href="Javascript:alterarDoc('<bean:write name="FormUser" property="sgDoc1"/>');">
                            <img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" alt="Alterar Documento" border="0">
                        </a>
                        </vivo:tbRowColumn>
                        <vivo:tbRowColumn>
                        <a href="Javascript:excluirDoc('<bean:write name="FormUser" property="sgDoc1"/>');">
                            <img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" alt="Excluir Documento" border="0">
                        </a>
                        </vivo:tbRowColumn>
                    </vivo:tbRow>
                </logic:equal>
                <logic:equal name="FormUser" property="sgDoc2" value="RE">
                    <vivo:tbRow key="linha1">
                        <vivo:tbRowColumn><bean:write name="FormUser" property="sgDoc2"/></vivo:tbRowColumn>
                        <vivo:tbRowColumn><bean:write name="FormUser" property="dsDoc2"/></vivo:tbRowColumn>
                        <vivo:tbRowColumn><bean:write name="FormUser" property="ufDoc2"/></vivo:tbRowColumn>
                        <vivo:tbRowColumn><bean:write name="FormUser" property="paisDoc2"/></vivo:tbRowColumn>
                        <vivo:tbRowColumn>
                        <a href="Javascript:alterarDoc('<bean:write name="FormUser" property="sgDoc2"/>');">
                            <img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" alt="Alterar Documento" border="0">
                        </a>
                        </vivo:tbRowColumn>
                        <vivo:tbRowColumn>
                        <a href="Javascript:excluirDoc('<bean:write name="FormUser" property="sgDoc2"/>');">
                            <img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" alt="Excluir Documento" border="0">
                        </a>
                        </vivo:tbRowColumn>
                    </vivo:tbRow>
                </logic:equal>
                </vivo:tbRows>
            </vivo:tbTable>
            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
            <center>
                <table width="720" height="15" border="0" cellspacing="0" cellpadding="2" style="border:1px solid #D4D7DE; background-color:#F7F9FA; font-size:10px;">
                    <tr>
                        <td valign="middle" width="100"><b>&nbsp;Legendas:</b></td>
                        <td width="150"><img vspace="1" src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" align="absmiddle"> &nbsp;Alterar Documento</td>
                        <td width="150" align="left"><img vspace="1" src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" align="absmiddle"> &nbsp;Excluir Documento</td>
                        <td width="250" align="left"> &nbsp; Os campos indicados com (<font color="red">*</font>) são obrigatórios</td>
                    </tr>
                </table>
            </center>
        </vivo:quadro>
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
        <table width="90%" border="0" cellpadding="0" cellspacing="0" align="center">
            <tr>
                <td align="left" colspan="2">
                    <img tabindex="25" onClick="envia();" id="btVoltar" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_voltar_over.gif'" style="border:none;cursor:pointer;"/>
                </td>
                <td align="right" width="15%" colspan="2">
                    <acesso:controlHiddenItem nomeIdentificador="usu_eus_grav">
                        <img id="btCadastrar"
                                           tabindex="27"
                                           src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif"
                                           onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif'"
                                           onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_salvar_over.gif'"
                                           style="border:none;cursor:pointer;"
                                           onClick="return salvaDados();"
                                           onBlur="document.forms[0].nome.focus();" />
                    </acesso:controlHiddenItem>
                </td>
            </tr>
        </table>
        <script type="text/javascript">
            var moveToolTip = true;
            xBump=yBump=10;
            MSIE=document.all;
            NS6=document.getElementById&&!document.all;
            if(MSIE||NS6){
                ttipObj=document.all?document.all["ttip"]:document.getElementById?document.getElementById("ttip"):"";
            }
            function carregaToolTip(content) {
             ttipObj.innerHTML=content;
             ttipObj.style.display='';
            }
        </script>
        <iframe style="width:0px;height:0px;" name="ifmOrganomagrama"></iframe>
    </form>
</vivo:quadro>
</acesso:controlHiddenItem>
</netui-template:section>
</netui-template:template>