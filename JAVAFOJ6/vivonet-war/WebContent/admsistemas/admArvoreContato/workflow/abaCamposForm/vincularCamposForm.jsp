<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<bean:define id="FormFormulario"                name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formFormulario"/>
<bean:define id="arrayCampoClassificador"       name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formFormulario.arrayCampoClassificador"/>
<bean:define id="arrayFaseProcesso"             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formFormulario.arrayFaseProcesso"/>
<bean:define id="OperadorasAssociadasVO"        name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formFormulario.ufOperadorasAssociadas" type="br.com.vivo.fo.usuario.vo.UFOperadoraUsuarioVODocument.UFOperadoraUsuarioVO[]" />
<bean:define id="OperadorasExistentesVO"        name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formFormulario.ufOperadorasExistentes"/>
<bean:define id="TipoLinhasExistentesVO"        name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formFormulario.tipoLinhasExistentes"/>
<bean:define id="TipoLinhasAssociadasVO"        name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formFormulario.tipoLinhasAssociadas" type="br.com.vivo.fo.admsistemas.vo.AdmTipoLinhaVODocument.AdmTipoLinhaVO[]"/>
<bean:define id="CamposAssociadosVO"            name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formFormulario.camposAssociados"/>
<bean:define id="CamposExistentesVO"            name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formFormulario.camposExistentes"/>
<bean:define id="listaDisponiveisCamposObjetos" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formFormulario.listaDisponiveisCamposObjetos" type="br.com.vivo.fo.admsistemas.vo.CampoObjetoFormularioVODocument.CampoObjetoFormularioVO[]"/>
<bean:define id="listaAssociadaCamposObjetos"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formFormulario.listaAssociadaCamposObjetos" type="br.com.vivo.fo.admsistemas.vo.CampoObjetoFormularioVODocument.CampoObjetoFormularioVO[]"/>



<netui-template:template templatePage="/resources/jsp/templateAdmSistClean.jsp">
<netui-template:section name="bodySection">
<acesso:controlHiddenItem nomeIdentificador="adm_vcf_verpagina">

    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js" ></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/toolTip.js"></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
    <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    <script type="text/javascript" language="javascript">
	
	function getAnimarAguarde(){
		//return window.top.frameApp.dvAnimarAguarde;
	}

        if( getAnimarAguarde() != null ) window.top.frameApp.startAnimation();
        var qtdeRecontagem = 0;

        function reloadPage() {
            if ("<%=request.getAttribute("msg")%>" == "Operação realizada com sucesso.") {
                var f = document.forms[0];
                f.action = "configurarFormulario.do";
                f.inOperacao.value = "";
                f.idContato.value = "<bean:write name="FormFormulario" property="idContato"/>";
                f.submit();
            }
        }

        function previewFormulario() {
            var f = document.forms[0];
            
            <logic:notEqual name="FormFormulario" property="idFaseProcessoAtual" value="">
            if (confirm("Apenas campos e componentes já gravados serão apresentados nesta visualização. \n\n Deseja prosseguir?")) {
                controlCombos();
                $('arraySelectedIndex').style.display = "none";
                if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
                $('divPreviewFormulario').style.display = "";
                f.action = "visualizarFormulario.do";
                f.target = "iframePreviewFormulario";
                f.submit();
            }
            </logic:notEqual>
        }

        function salvar() {

            var f = document.forms[0];
            f.target = "";

            if ($('idFaseProcessoAtual').selectedIndex == 0) {
                alert('Selecione uma fase.');
                return false;
            }

            if ($('aOperadoraSelecionados').options.length < 1) {
                alert('Não existe nenhuma regional associada.');
                return false;
            }
            for (i = 0; i < $('aOperadoraSelecionados').options.length; i++) {
                $('aOperadoraSelecionados').options[i].selected = true;
            }

            if ($('aTipoLinhaSelecionados').options.length < 1) {
                alert('Não existe nenhum tipo de linha associado.');
                return false;
            }
            for (i = 0; i < $('aTipoLinhaSelecionados').options.length; i++){
                $('aTipoLinhaSelecionados').options[i].selected = true;
            }

            <% if (listaAssociadaCamposObjetos.length == 0){ %>
            alert('Associe a esta fase ao menos um componente ou campo.');
            return false;
            <% } %>

            if (getAnimarAguarde() != null) window.top.frameApp.startAnimation();
            f.action = 'salvaCamposForm.do';
            f.target = "";
            f.submit();
        }

        function filtrar(obj) {

            var inCamposComponentes = "";
						

            for (i = 0; i < document.forms[0].inCamposComponentes.length; i++) {
                if (document.forms[0].inCamposComponentes[i].checked) {
                    inCamposComponentes = document.forms[0].inCamposComponentes[i].value;
                }
            }
            if (inCamposComponentes == "CAMPOS") {
                $('spanTituloDisponiveis').innerHTML = "Campos Existentes";
                $('spanClassificadorComponente').innerHTML = "Classificador:";
            } else if (inCamposComponentes == "COMPONENTES") {
                $('spanTituloDisponiveis').innerHTML = "Grupos";
                $('spanClassificadorComponente').innerHTML = "Componente:";
            }

            while ($('aCamposExistentes').options.length > 0) {
                $('aCamposExistentes').options[0] = null;
            }

            if (document.forms[0].idFaseProcessoAtual.value != "") {
                if( getAnimarAguarde() != null ) window.top.frameApp.startAnimation();
                if (obj.id == "idFaseProcessoAtual") {
                    document.forms[0].action = 'configurarFormulario.do';
                    document.forms[0].target = "";
                    document.forms[0].submit();
                } else {
                    inOperacao = "UPDATECOMBOS"
                    inCamposComponentes = obj.value;

                    new Ajax.Request('getCombosClassificadorComponente.do', {
                        method: 'get',
                        parameters: {
                            inOperacao: "UPDATECOMBOS", limit: 12,
                            inCamposComponentes: obj.value, limit: 12
                        },
                        contentType: 'text/xml',
                        onComplete: function (originalRequest) {
                            var objRaiz, idCampo, nmCampo;
                            var xmlString = originalRequest.responseText;
                            var oXml      = new ActiveXObject("microsoft.xmldom");
                            oXml.async    = false;
                            var regExp    = new RegExp ('&', 'gi') ;
                            xmlString     = xmlString.replace(regExp,"&amp;");
                            oXml.loadXML(xmlString);

                            if (oXml.selectSingleNode("/xml-fragment/friendlyMessage") != null) {

                                exceptionMessage = oXml.selectSingleNode("/xml-fragment/exceptionMessage").text;
                                friendlyMessage  = oXml.selectSingleNode("/xml-fragment/friendlyMessage").text;

                                top.frameApp.$('errTitle').innerHTML = "Erro";
                                top.frameApp.$('errCode').innerHTML = "";
                                top.frameApp.$('errMsg').innerHTML = friendlyMessage;
                                top.frameApp.$('errDetails').value = exceptionMessage;
                                top.frameApp.$('dvAnimarAguardeErro').style.display = "";

                            } else {
                                objRaiz = oXml.getElementsByTagName("AdmClassificadorCampoVO");
                                while ($('idClassificadorCampoAtual').options.length > 0) {
                                    $('idClassificadorCampoAtual').options[0] = null;
                                }

                                $('idClassificadorCampoAtual').options[0] = new Option("-- Selecione --", "");

                                if (objRaiz) {
                                    for (i=0; i < objRaiz.length; i++ ) {
                                        idCampo = objRaiz[i].childNodes[0].text;
                                        nmCampo = objRaiz[i].childNodes[1].text;
                                        $('idClassificadorCampoAtual').options[$('idClassificadorCampoAtual').options.length] = new Option(nmCampo, idCampo);
                                    }
                                }
                            }
                            if( getAnimarAguarde() != null ) window.top.frameApp.stopAnimation();
                        }
                    });
                }
            }
        }

        function getListaCamposClassificadoresComponentes(idClassificadorCampoAtual) {

            var inCamposComponentes = "";
            for (i = 0; i < document.forms[0].inCamposComponentes.length; i++) {
                if (document.forms[0].inCamposComponentes[i].checked) {
                    inCamposComponentes = document.forms[0].inCamposComponentes[i].value;
                }
            }

            if (document.forms[0].idFaseProcessoAtual.value != ""
                && idClassificadorCampoAtual != "") {

                var obj = $('aCamposExistentes');
                if( getAnimarAguarde() != null ) window.top.frameApp.startAnimation();
                
                while (obj.length > 0) {
                    obj.options[0] = null;
                }

                new Ajax.Request('getListaCamposClassificadoresComponentes.do', {
                    method: 'get',
                    parameters: {
                        inCamposComponentes: inCamposComponentes, limit: 12,
                        idClassificadorCampoAtual: idClassificadorCampoAtual, limit: 12
                    },
                    contentType: 'text/xml',
                    onComplete: function (originalRequest) {
                        var objRaiz, idCampo, nmCampo;
                        var xmlString = originalRequest.responseText;
                        var oXml      = new ActiveXObject("microsoft.xmldom");
                        oXml.async    = false;
                        var regExp    = new RegExp ('&', 'gi') ;
                        xmlString     = xmlString.replace(regExp,"&amp;");
                        oXml.loadXML(xmlString);

                        if (oXml.selectSingleNode("/xml-fragment/friendlyMessage") != null) {

                            exceptionMessage = oXml.selectSingleNode("/xml-fragment/exceptionMessage").text;
                            friendlyMessage  = oXml.selectSingleNode("/xml-fragment/friendlyMessage").text;
                            //severity         = oXml.selectSingleNode("/xml-fragment/severity").text;

                            window.top.frameApp.$('errTitle').innerHTML = "Erro";
                            window.top.frameApp.$('errCode').innerHTML = "";
                            window.top.frameApp.$('errMsg').innerHTML = friendlyMessage;
                            window.top.frameApp.$('errDetails').value = exceptionMessage;
                            window.top.frameApp.$('dvAnimarAguardeErro').style.display = "";

                        } else {
                            objRaiz = oXml.getElementsByTagName("CampoObjetoFormularioVO");
                            if (objRaiz) {
                                for (i=0; i < objRaiz.length; i++ ) {
                                    idCampo = objRaiz[i].childNodes[0].text;
                                    nmCampo = objRaiz[i].childNodes[1].text;
                                    obj.options[obj.options.length] = new Option(nmCampo, idCampo);
                                }
                            }
                        }
                        if( getAnimarAguarde() != null ) window.top.frameApp.stopAnimation();
                    }
                });
            } else {
                while ($('aCamposExistentes').options.length > 0) {
                    $('aCamposExistentes').options[0] = null;
                }
            }
        }

        function excluir(index, idCampoObjeto, idClassificadorComponente) {

            if (confirm("Tem certeza que deseja excluir este item?")) {
                if( getAnimarAguarde() != null ) window.top.frameApp.startAnimation();
                new Ajax.Request('excluirCampoGrupo.do', {
                    method: 'get',
                    parameters: {
                        idCampoObjeto: idCampoObjeto, limit: 12,
                        idClassificadorComponente: idClassificadorComponente, limit: 12
                    },
                    contentType: 'text/xml',
                    onComplete: function () {
                        removeElementById(index);
                        if( getAnimarAguarde() != null ) window.top.frameApp.stopAnimation();
                    }
                });
            }
        }


        function removeElementById(obj) {
            obj = document.getElementById(obj);
            if (obj.parentNode && obj.parentNode.removeChild(obj)) {
                obj.parentNode.removeChild(obj); 
            }
        }

        function alterarCampos(idClassificadorCampo,idFaseProcesso,idUFOperadora,idTipoLinha,idCampo) {
            document.forms[0].idClassificadorCampoAtual.value = idClassificadorCampo;
            document.forms[0].idFaseProcessoAtual.value = idFaseProcesso;
            document.forms[0].action = 'alteraCampos.do';
            if( getAnimarAguarde() != null ) window.top.frameApp.startAnimation();
            document.forms[0].target = "";
            document.forms[0].submit();
        }
        
        function removeCampos(idClassificadorCampo,idFaseProcesso,idUFOperadora,idTipoLinha,idCampo)
        {
            if (window.confirm("Confirma remoção do item?")) 
            {
                document.forms[0].idClassificadorCampoAtual.value = idClassificadorCampo;
                document.forms[0].idFaseProcessoAtual.value = idFaseProcesso;
                document.forms[0].action = 'removeCampos.do';
                if( getAnimarAguarde() != null ) window.top.frameApp.startAnimation();
                document.forms[0].target = "";
                document.forms[0].submit();
            }
        }
        
        function transfereSelecaoLista(listaOrigem, listaDestino) {
            var i;
            var flSelected = false;

            if (listaDestino != undefined) {

                for(i = 0; i<listaOrigem.options.length; i++)
                    if(listaOrigem.options[i].selected)
                        listaDestino.options[listaDestino.options.length] = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);
                for(i = listaOrigem.options.length-1; i>=0; i--)
                    if(listaOrigem.options[i].selected)
                        listaOrigem.options[i] = null;

            } else {

                if ($('idClassificadorCampoAtual').value != "") {
                    var nmClassificadorCampoAtual = $('idClassificadorCampoAtual').options[$('idClassificadorCampoAtual').options.selectedIndex].text;
                    for(i = 0; i < listaOrigem.options.length; i++) {
                        if (listaOrigem.options[i].selected == true) {
                            flSelected = true;
                            $('arraySelectedIndex').options[$('arraySelectedIndex').options.length] = new Option(i, i, true);
                        }
                    }

                    for(i = 0; i < $('arraySelectedIndex').options.length; i++) {
                        $('arraySelectedIndex').options[i].selected = true;
                    }

                    for (i = 0; i < $('aOperadoraSelecionados').options.length; i++) {
                        $('aOperadoraSelecionados').options[i].selected = true;
                        $('aOperadoraSelecionados').options[i].value += "|" + $('aOperadoraSelecionados').options[i].text;
                    }
                    for (i = 0; i < $('aOperadoraExistentes').options.length; i++) {
                        $('aOperadoraExistentes').options[i].selected = true;
                        $('aOperadoraExistentes').options[i].value += "|" + $('aOperadoraExistentes').options[i].text;
                    }
                    for (i = 0; i < $('aTipoLinhaExistentes').options.length; i++) {
                        $('aTipoLinhaExistentes').options[i].selected = true;
                        $('aTipoLinhaExistentes').options[i].value += "|" + $('aTipoLinhaExistentes').options[i].text;
                    }
                    for (i = 0; i < $('aTipoLinhaSelecionados').options.length; i++) {
                        $('aTipoLinhaSelecionados').options[i].selected = true;
                        $('aTipoLinhaSelecionados').options[i].value += "|" + $('aTipoLinhaSelecionados').options[i].text;
                    }

                    if (flSelected) {
                        if( getAnimarAguarde() != null ) window.top.frameApp.startAnimation();
                        document.forms[0].action = "associarCamposGrupos.do?nmClassificadorCampoAtual="+nmClassificadorCampoAtual;
                        document.forms[0].target = "";
                        document.forms[0].submit();
                    }
                }
            }
        }

        function controlCombos(){
            for(i=0; i < document.frames.length; i++){
                var array = document.frames[i].document.getElementsByTagName("SELECT");
                for(j=0; j < array.length; j++){
                    if(array[j].style.display=='none')
                        array[j].style.display='';
                    else
                        array[j].style.display='none';
                }
            }
            internalCombos(document.getElementsByTagName("SELECT"));
            $('arraySelectedIndex').style.display = "none";
        }

        function internalCombos(array){
            for(j=0; j < array.length; j++){
                if(array[j].style.display=='none')
                    array[j].style.display='';
                else
                    array[j].style.display='none';
            }
        }

    </script>
    <script language="javascript" for="window" event="onload">
        if ('<bean:write name="FormFormulario" property="msgError" />' != "") {
            alert('<bean:write name="FormFormulario" property="msgError" />');
        }
        <logic:equal name="FormFormulario" property="idFaseProcessoAtual" value="">
        document.forms[0].idClassificadorCampoAtual.disabled = true;
        </logic:equal>
        if( window.top.frameApp.dvAnimarAguarde != null ) window.top.frameApp.stopAnimation();
        document.body.style.backgroundColor = '#ededed';
    </script>

    <form action="salvaCamposForm" name="formFormulario" id="formFormulario" method="post">
    <html:hidden name="FormFormulario" property="idContato"/>
    <html:hidden name="FormFormulario" property="inOperacao"/>

    <table width="730" border="0" cellspacing="0" cellpadding="0" align="center" style="margin:5px 0 0 10px">
        <tr>
            <td width="140" valign="top">
                <select name="arraySelectedIndex" id="arraySelectedIndex" style="display:none;" multiple></select>
                <netui:label value="Fase:" styleClass="tblEstatica_campoNome"/>
            </td>
            <td colspan="2" valign="top" style="padding-bottom:4px;">
                <html:select name="FormFormulario" property="idFaseProcessoAtual" styleId="idFaseProcessoAtual" onchange="filtrar(this);" style="width:185px;" styleClass="SELECT">
                    <html:option value="">-- Selecione --</html:option>
                    <html:options collection="arrayFaseProcesso" property="idFaseProcesso" labelProperty="nmFaseProcesso"/>
                </html:select>
            </td>
            <td width="390" style="padding-left:30px" colspan="3" valign="top">
                <html:radio name="FormFormulario" property="inCamposComponentes" value="CAMPOS" styleClass="radio" onclick="filtrar(this);" style="background:none"> Campos</html:radio>
                <html:radio name="FormFormulario" property="inCamposComponentes" value="COMPONENTES" styleClass="radio" onclick="filtrar(this);" style="background:none"> Componentes</html:radio>
            </td>
        </tr>
    
        <!--logic:notEqual name="FormFormulario" property="idFaseProcessoAtual" value=""-->
    
        <tr>
            <td valign="top" style="padding-bottom:10px;">
                Regionais existentes<br>
                <html:select name="FormFormulario" property="aOperadoraExistentes" multiple="true" style="width:125px;" size="4">
                    <html:options collection="OperadorasExistentesVO" property="idUFOperadora" labelProperty="dsUFOperadora" /> 
                </html:select>
            </td>
            <td width="60" valign="middle">
                <img id="btRightSimp" onclick="transfereSelecaoLista(document.forms[0].aOperadoraExistentes, document.formFormulario.aOperadoraSelecionados);" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_rightaln_over.gif" border="0"></img><br><br>
                <img id="btLeftSimp" onclick="transfereSelecaoLista(document.forms[0].aOperadoraSelecionados, document.formFormulario.aOperadoraExistentes);"src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_leftaln_over.gif" border="0"></img>
            </td>
            <td width="140" valign="top">
                Regionais selecionadas<br>
                <html:select name="FormFormulario" property="aOperadoraSelecionados" multiple="true" style="width:125px;" size="4">
                    <html:options collection="OperadorasAssociadasVO" property="idUFOperadora" labelProperty="dsUFOperadora" /> 
                </html:select>
            </td>
            <td valign="top" style="padding-left:25px;">
                Tipos de linhas existentes<br>
                <html:select name="FormFormulario" property="aTipoLinhaExistentes" size="4" multiple="true" style="width:125px;">
                    <html:options collection="TipoLinhasExistentesVO" property="idTipoLinha" labelProperty="dsTipoLinha" />
                </html:select>
            </td>
            <td width="70" align="center" valign="middle">
                <img id="btRightSimp" onclick="transfereSelecaoLista(document.forms[0].aTipoLinhaExistentes, document.formFormulario.aTipoLinhaSelecionados);" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_rightaln_over.gif" border="0"></img><br><br>
                <img id="btLeftSimp" onclick="transfereSelecaoLista(document.forms[0].aTipoLinhaSelecionados, document.formFormulario.aTipoLinhaExistentes);"src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_leftaln_over.gif" border="0"></img>
            </td>
            <td valign="top">
                Tipos de linhas selecionados<br>
                <html:select name="FormFormulario" property="aTipoLinhaSelecionados" size="4" multiple="true" style="width:125px;">
                    <html:options collection="TipoLinhasAssociadasVO" property="idTipoLinha" labelProperty="dsTipoLinha" />
                </html:select>
            </td>
        </tr>
        <tr>
            <td width="140" valign="top">
                <logic:equal name="FormFormulario" property="inCamposComponentes" value="CAMPOS">
                <netui:label value="Classificador:" id="spanClassificadorComponente" styleClass="tblEstatica_campoNome"/>
                </logic:equal>
                <logic:equal name="FormFormulario" property="inCamposComponentes" value="COMPONENTES">
                <netui:label value="Componente:" id="spanClassificadorComponente" styleClass="tblEstatica_campoNome"/>
                </logic:equal>
            </td>
            <td colspan="5" valign="top" style="padding-bottom:4px;">
                <html:select name="FormFormulario" styleId="idClassificadorCampoAtual" property="idClassificadorCampoAtual" onchange="getListaCamposClassificadoresComponentes(this.value);" style="width:200" styleClass="SELECT">
                    <html:option value="">-- Selecione --</html:option>
                    <html:options collection="arrayCampoClassificador" property="idClassificadorCampo" labelProperty="nmClassificadorCampo"/>
                </html:select>
            </td>
        </tr>
        <tr>
            <td colspan="6">
                <table id="tableContent" width="730" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td valign="top" width="180">
                            <span id="spanTituloDisponiveis">Campos Existentes</span><br>
                            <html:select name="FormFormulario" property="aCamposExistentes" multiple="true" style="width:180px;height:188px">
                                <html:options collection="listaDisponiveisCamposObjetos" property="idCampoObjeto" labelProperty="nmCampoObjeto"/>
                            </html:select>
                        </td>
                        <td width="70" valign="middle" align="center">
                            <img id="btRightSimp" onclick="transfereSelecaoLista(document.forms[0].aCamposExistentes);" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_rightaln_over.gif" border="0"></img><br><br>
                        </td>
                        <td valign="top" width="480">
    
                            <!--logic:greaterThan name="FormFormulario" property="listaFormularioLength" value="0"-->
                                <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="10"></div>
                                <vivo:tbTable selectable="true" layoutWidth="460" layoutHeight="160" tableWidth="460" styleId="tableTitle" sortable="true">
                                    <vivo:tbHeader>
                                        <vivo:tbHeaderColumn align="center" width="20%" type="string">Tipo</vivo:tbHeaderColumn>
                                        <vivo:tbHeaderColumn align="center" width="20%" type="string">Classif/Comp</vivo:tbHeaderColumn>
                                        <vivo:tbHeaderColumn align="center" width="55%" type="string">Item</vivo:tbHeaderColumn>
                                        <vivo:tbHeaderColumn align="center" width="5%" type="string"></vivo:tbHeaderColumn>
                                    </vivo:tbHeader>
                                    <vivo:tbRows>
                                        <logic:iterate name="listaAssociadaCamposObjetos" id="listaAssociados" indexId="i" type="br.com.vivo.fo.admsistemas.vo.CampoObjetoFormularioVODocument.CampoObjetoFormularioVO">
                                            <vivo:tbRow key="linha" id='<%="" + i + "" %>'>
                                                <vivo:tbRowColumn>
                                                    <bean:write name="listaAssociados" property="inClassificadorComponente"/>
                                                </vivo:tbRowColumn>
                                                <vivo:tbRowColumn>
                                                    <bean:write name="listaAssociados" property="nmClassificadorComponente"/>
                                                </vivo:tbRowColumn>
                                                <vivo:tbRowColumn>
                                                    <bean:write name="listaAssociados" property="nmCampoObjeto"/>
                                                </vivo:tbRowColumn>
                                                <vivo:tbRowColumn>
                                                    <img onmouseup="excluir(<%=i%>,<%=listaAssociados.getIdCampoObjeto()%>,<%=listaAssociados.getIdClassificadorComponente()%>)" src="<%=request.getContextPath()%>/resources/images/bt_icon_excluir.gif" style="cursor:pointer;">
                                                </vivo:tbRowColumn>
                                            </vivo:tbRow>
                                        </logic:iterate>
                                    </vivo:tbRows>
                                </vivo:tbTable>
                            <!--/logic:greaterThan-->
    
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
        
        <% if (OperadorasAssociadasVO == null || OperadorasAssociadasVO.length == 0) { %>
            <% } %>
            <td align="right" height="12px" colspan="6">
                <logic:notEqual name="FormFormulario" property="idFaseProcessoAtual" value="">
                    <% if ((listaAssociadaCamposObjetos != null && listaAssociadaCamposObjetos.length > 0)
                           && (OperadorasAssociadasVO != null && OperadorasAssociadasVO.length > 0)
                           && (TipoLinhasAssociadasVO != null || TipoLinhasAssociadasVO.length > 0)) { %>
                    <img vspace="3" id="btPreview" onClick="previewFormulario();" src="/FrontOfficeWeb/resources/images/bt_visualform_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_visualform_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_visualform_over.gif'" border="0" style="cursor:pointer" />
                    <%}%>
                </logic:notEqual>
                <img vspace="3" id="btSalvar1" onClick="salvar();" src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_gravar_over.gif'" border="0" style="cursor:pointer" />
            </td>
        </tr>
    
        <!--/logic:notEqual-->
    
    </table>

    <vivo:quadroFlutuante onclick="controlCombos()" idIframe="iframePreviewFormulario" id="divPreviewFormulario" spacesLeft="0" spacesTop="0" url="<%=request.getContextPath()%>" display="none" label="Visualização de Formulário Dinâmico" height="340" width="750" scroll="auto"></vivo:quadroFlutuante>
    
    <vivo:alert atributo="msg" scope="request" afterFunction="reloadPage()" />
    
    </form>

</acesso:controlHiddenItem>
</netui-template:section>
</netui-template:template>