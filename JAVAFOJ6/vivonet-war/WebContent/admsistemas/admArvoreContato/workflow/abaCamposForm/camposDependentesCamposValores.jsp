<%@page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
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

<bean:define id="FormFormulario"          name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formFormulario" />
<bean:define id="arrayCamposDependentes"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formFormulario.arrayGrupoCamposDependentes" type="br.com.vivo.fo.admsistemas.vo.AdmGrupoCamposDependentesVODocument.AdmGrupoCamposDependentesVO[]"  />
<!--bean:define id="arrayCampoVO"            name="arrayCamposDependentes" property="AdmCampoVO" /-->
<bean:define id="arrayGrupoCampos"        name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formFormulario.arrayGrupoCampos"/>



<netui-template:template templatePage="/resources/jsp/templateAdmSistClean.jsp">

<netui-template:section name="bodySection">
<!--acesso:controlHiddenItem nomeIdentificador="adm_vcf_verpagina"-->

    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js" ></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/toolTip.js"></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
    <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    <script type="text/javascript" language="javascript">

        var inOperacao;
        var flAlteracao   = false;
        var flExclusao    = false;
        var nextIndex     = parseInt('<%=arrayCamposDependentes.length%>');
        var vlCampo;
        var idCampoInicial;
        var arrayPermissoes = new Array();
        var arrayCamposJaCadastrados = new Array();

        var idxNivelExcluido = null;        /* Variável utilizada para verificaçao do
                                               menor nível excluído, considerando-se apenas
                                               os campos que foram trazidos inicialmente
                                               pelo serviço */

        function getOperacao(valor) {
            if (valor == "CRIAR") {
                $('divTextField').style.display    = "block";
                $('divConteudoAbas').style.display = "block";
                $('divSelectList').style.display   = "none";
            } else if (valor == "ALTERAR") {
                $('divSelectList').style.display   = "block";
                $('divConteudoAbas').style.display = "none";
                $('divTextField').style.display    = "none";
            } else {
                $('divSelectList').style.display   = "none";
                $('divConteudoAbas').style.display = "none";
                $('divTextField').style.display    = "none";
            }
        }

        function verificaNovoValorInserido(idxAr,v){
            var idxCampo;
            var dsValor;
            for(i=0;i<arrayPermissoes.length;i++){
                idxCampo = arrayPermissoes[i].split("|")[0];
                dsValor  = arrayPermissoes[i].split("|")[1];
                if(idxAr==idxCampo && v==dsValor){
                    return true;
                }
            }
            return false;
        }
        
        function verificaCampoJaCadastrado(idxCampo){
            for(i=0;i<arrayCamposJaCadastrados.length;i++){
                if(idxCampo==arrayCamposJaCadastrados[0]){
                    return true;
                }
            }
            return false;
        }

        function transfereSelecaoLista(operacao, arrayId) {

            var i, idObjetoOrigem, idObjDestino;
            idObjetoOrigem = "valorInclusao[" + arrayId + "]";
            idObjDestino   = "selectCamposAssociados[" + arrayId + "]";

            if (operacao == "move") {
                if (trim($(idObjetoOrigem).value) != "") {
                    for (i=0; i < $(idObjDestino).options.length; i++) {
                        if (trim($(idObjetoOrigem).value).toUpperCase() == trim($(idObjDestino).options[i].text).toUpperCase()) {
                            alert("Um valor homônimo já encontra-se associado a este campo.");
                            return;
                        }
                    }
                    $(idObjDestino).options[$(idObjDestino).options.length] = new Option(trim($(idObjetoOrigem).value), trim($(idObjetoOrigem).value));
                    arrayPermissoes[arrayPermissoes.length] = arrayId+"|"+trim($(idObjetoOrigem).value);
                    $(idObjetoOrigem).value = "";
                    return;
                }
            }

            if (operacao == "delete") {
                var qtdeSelecionados = 0;
                var flValoresJaExistentes = false;
                var exclusao = false;
                for (i=$(idObjDestino).options.length-1; i>=0; i--) {
                    if ($(idObjDestino).options[i].selected)
                        qtdeSelecionados++;
                }
                for (i=$(idObjDestino).options.length-1; i>=0; i--) {
                    if ($(idObjDestino).options[i].selected) {
                        if(verificaNovoValorInserido(arrayId,$(idObjDestino).options[i].value)
                        || $F('inSubFormularios['+arrayId+']')=="0"){
                            $(idObjDestino).options[i] = null;
                            exclusao = true;
                        }else if(verificaCampoJaCadastrado(arrayId))
                            flValoresJaExistentes = true;
                    }
                }
                if(qtdeSelecionados>1){
                    if(flValoresJaExistentes && exclusao || ($F('inSubFormularios['+arrayId+']')=="1" && exclusao))
                        alert('Alguns valores não podem ser excluídos porque o campo está associado a outro grupo.');
                    else if(flValoresJaExistentes || $F('inSubFormularios['+arrayId+']')=="1")
                        alert('Estes valores não podem ser excluídos porque o campo está associado a outro grupo.');
                }else{
                    if(flValoresJaExistentes || ($F('inSubFormularios['+arrayId+']')=="1" && !exclusao))
                        alert("Este valor não pode ser excluído porque o campo está associado a outro grupo.");
                }
            }
        }

        function reloadPage(){
            if (parent.$('idOperacao').value == "CRIAR"){
                parent.parent.abaSelected(parent.parent.btAba, parent.parent.bt03);
                parent.parent.CarregaAba("bt03");
            }
        }

        function removeElementById(obj) {
            obj = document.getElementById(obj);
            if (obj.parentNode && obj.parentNode.removeChild(obj)) {
                obj.parentNode.removeChild(obj); 
            }
        }

        function excluir(index, idCampoDependente){

            var f = document.forms[0];
            var arrayNiveisSelecionados = new Array();
            var confirmTxt = "";
            var submit = true;

            for (i = 1; i < nextIndex; i++) {
                nmSelect = "selectNrNivel[" + i + "]";
                arrayNiveisSelecionados[i] = $(nmSelect).value;
            }

            f.inOperacao.value             = "EXCLUIRCAMPOSVALORES";
            f.arrayGrupoCamposLength.value = nextIndex;

            var confirmTxt = 'Deseja realmente excluir este nível? Todos os relacionamentos configurados a partir dele serão excluídos.';
            if(confirmTxt!=""){
                if(confirm(confirmTxt))
                    submit = true;
                else
                    submit = false;
            }

            if(submit){
                if(window.top.frameApp.dvAnimarAguarde != null ) window.top.frameApp.startAnimation();
                for(i=0;i<nextIndex;i++){
                    nmSelect = "selectCamposAssociados[" + i + "]";
                    for(j=0;j<$(nmSelect).options.length;j++)
                        $(nmSelect).options[j].selected = true;
                }
                f.idxNivelExcluido.value = index;
                f.submit();
            }
            
        }

        function organizeSelectsNiveis() {
            var i, j, nmSelect, bool, selValue;
            for (i = 1; i < nextIndex; i++) {
                nmSelect = "selectNrNivel[" + i + "]";
                selValue = getSelValue($(nmSelect));
                while($(nmSelect).options.length>0) $(nmSelect).options[0] = null;
                $(nmSelect).options[$(nmSelect).options.length] = new Option("-- Selecione --", "");
                for(j=2;j<=nextIndex;j++)
                    $(nmSelect).options[$(nmSelect).options.length] = new Option(j + " - " + getOrdinario(j), j);
                if(selValue!="")
                    selectByValue(selValue,$(nmSelect));
                else
                    $(nmSelect).selectedIndex = $(nmSelect).options.length-1;
            }
        }

        function getSelValue(obj){
            if(obj==null || obj==undefined)
                return "";
            else
                return obj.value;
        }

        function selectByValue(vl,obj){
            for(x=0;x<obj.length;x++){
                if(vl==obj.options[x].value){
                    obj.selectedIndex=x;
                    break;
                }
            }
        }
        
        function flJaExisteNivelEmSelect(nrNivel,objSelect){
            if(objSelect.length==0)return false;
            var b=false;
            for(i=0;i<objSelect.length;i++){
                if(objSelect.options[i].value==nrNivel){
                    b=true;
                }
            }
            return b;
        }

        function getNovoFilho(){
            var html = "<table style='font-weight:bold'><tr id='"+nextIndex+"'>";
            html += "<td width='100' valign='top' nowrap style='padding-top:10px;'>N&iacute;vel</td>";
            html += "<td width='180' valign='top' nowrap style='padding-top:10px;'>";
            html += "<select class='selectsNiveis' onchange='flAlteracao=true;' name='selectNrNivel["+nextIndex+"]' id='selectNrNivel["+nextIndex+"]' style='width:180px;margin-left:3px;'>";
            html += "</select>";
            html += "</td><td colspan='2'></td>";
            html += "</tr>";
            html += "<tr>";
            html += "<td valign='top'>Campo</td><td>";
            html += "<input type='hidden' name='idCampo[" + nextIndex + "]' id='idCampo[" + nextIndex + "]' value='' />";
            html += "<input type='hidden' name='inSubFormularios[" + nextIndex + "]' id='inSubFormularios[" + nextIndex + "]' value='0' />";
            html += "<input style='width:180px;' type='text' tabindex='" + nextIndex + "' name='nmCampo["+nextIndex+"]' id='nmCampo["+nextIndex+"]' value='' onfocus='blockPipe(this);vlCampo=this.value' onblur='blockPipe(this);this.value=trim(this.value);pesquisaCampo("+nextIndex+",this)' />";
            html += "</td>";
            html += "<td width='80' rowspan='2' valign='bottom' align='center'><img id='btRightSimp'   onMouseUp=\"transfereSelecaoLista('move', " + nextIndex + ");\" src='/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif' onmouseout=\"this.src='/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif';\" onmouseover=\"this.src='/FrontOfficeWeb/resources/images/bt_rightaln_over.gif';\" style='border:none;cursor:pointer;' /><br><br><img id='btExcluirSimp"+nextIndex+"' onMouseUp=\"transfereSelecaoLista('delete', " + nextIndex + "); return false;\" src='/FrontOfficeWeb/resources/images/bt_x_nrml.gif' onmouseout=\"this.src='/FrontOfficeWeb/resources/images/bt_x_nrml.gif';\" onmouseover=\"this.src='/FrontOfficeWeb/resources/images/bt_x_over.gif';\" style='border:none;cursor:pointer;' /></td>";
            html += "<td rowspan='2' valign='top'><select name='selectCamposAssociados[" + nextIndex + "]' id='selectCamposAssociados[" + nextIndex + "]' size='3' multiple='true' style='width:310px;'></select></td>";
            html += "</tr>";
            html += "<tr>";
            html += "<td valign='top'>Valor</td>";
            html += "<td><input tabindex='" + nextIndex + "' type='text' onfocus='blockPipe(this)' onblur='blockPipe(this);this.value=trim(this.value)' name='valorInclusao[" + nextIndex + "]' id='valorInclusao[" + nextIndex + "]' style='width:180px;' maxlength='180' /></td>";
            html += "</tr>";
            html += "<tr>";
            html += "<td colspan='4' align='right'><img id='btGravar' onMouseUp='excluir(" + nextIndex + ", \"\"); return false;' src='/FrontOfficeWeb/resources/images/bt_excluir_nrml.gif' onmouseout=\"this.src='/FrontOfficeWeb/resources/images/bt_excluir_nrml.gif';\" onmouseover=\"this.src='/FrontOfficeWeb/resources/images/bt_excluir_over.gif';\" style='margin-top:5px;border:none;cursor:pointer;' /></td>";
            html += "</tr>";
            html += "<tr>";
            html += "<td colspan='4' style='border-bottom:1px dotted #adadad;margin-top:10px;'>&nbsp;</td>";
            html += "</tr></table>";
            return html;
        }

        function getOrdinario(strNivel) {
            var nmNivel = "";
            var nrNivel = parseInt(strNivel);
            var arrayNmNiveis = new Array("Primeiro", "Segundo", "Terceiro", "Quarto", "Quinto", "Sexto","Sétimo", "Oitavo", "Nono", "Décimo", "Vigésimo");
            switch (nrNivel) {
                case 1:  nmNivel = arrayNmNiveis[0]; break;
                case 2:  nmNivel = arrayNmNiveis[1]; break;
                case 3:  nmNivel = arrayNmNiveis[2]; break;
                case 4:  nmNivel = arrayNmNiveis[3]; break;
                case 5:  nmNivel = arrayNmNiveis[4]; break;
                case 6:  nmNivel = arrayNmNiveis[5]; break;
                case 7:  nmNivel = arrayNmNiveis[6]; break;
                case 8:  nmNivel = arrayNmNiveis[7]; break;
                case 9:  nmNivel = arrayNmNiveis[8]; break;
                case 10: nmNivel = arrayNmNiveis[9]; break;
            }
            if (nrNivel > 10 && nrNivel < 20) {
                strNivel = strNivel+'';
                nmNivel = arrayNmNiveis[9] + " " + (arrayNmNiveis[parseInt(strNivel.substring(1,2))-1]).toLowerCase();
            } else if (nrNivel == 20) {
                nmNivel = arrayNmNiveis[10];
            }
            //nmNivel = strNivel + " - " + nmNivel;
            return nmNivel;
        }

        function novoFilho() {
            if (nextIndex > 19) {
                alert("O número máximo de nós-filhos permitidos é 20!");
            } else {
                
                var tr = document.createElement('TR');
                var td = document.createElement('TD');
                tr.id  = "linha[" + nextIndex + "]";
                td.id  = "tdContainer[" + nextIndex + "]";
                td.colSpan = 4;
                var idTd = td.id;
                tr.appendChild(td);
                $('tableContent').tBodies[0].appendChild(tr);
                $(idTd).innerHTML = getNovoFilho(nextIndex);
                scrollTo(0,999999);
                if (nextIndex == 1) {
                    $('tdOperacoes').style.paddingTop = "59px";
                } else {
                    $('tdOperacoes').style.paddingTop = "25px";
                }
                nextIndex++;
                organizeSelectsNiveis();
            }
        }

        function salvar() {
            var f = document.forms[0];
            var arrayNiveisSelecionados = new Array();
            var confirmTxt = "";
            var submit = true;

            <logic:equal name="FormFormulario" property="idGrupoCampos" value="">
            if (trim($('nmGrupoCampos').value) == "") {
                alert("Por favor, digite o nome do grupo a ser criado.");
                $('nmGrupoCampos').value = "";
                setTimeout(function(){$('nmGrupoCampos').focus()},300);
                return;
            }
            </logic:equal>

            <logic:equal name="FormFormulario" property="inContatoAssociado" value="1">
            var arraySelects   = $('tableContent').select('[class="selectsNiveis"]');

            for(i=0;i<arraySelects.length;i++)arraySelects[i].disabled = false;
            </logic:equal>

            for (i = 1; i < nextIndex; i++) {
                nmSelect = "selectNrNivel[" + i + "]";
                for (j=0; j<arrayNiveisSelecionados.length; j++) {
                    if ($(nmSelect).value == arrayNiveisSelecionados[j]) {
                        alert('Não é permitida a duplicidade de níveis.');
                        return;
                    }
                    if($(nmSelect).value == ""){
                        var nmC = "nmCampo["+i+"]";
                        alert('Por favor, selecione um nível para o campo "'+$F(nmC)+'".');
                        return;
                    }
                }
                arrayNiveisSelecionados[i] = $(nmSelect).value;
            }

            for (i = 0; i < nextIndex; i++) {
                nmCampos = "nmCampo[" + i + "]";
                if (trim($(nmCampos).value) == "") {
                    alert("Por favor, preencha o nome do campo no " + getOrdinario(i+1).toLowerCase() + " nível.");
                    return;
                }
            }
            var arraySelectsNiveis = $('tableContent').select('[class="selectsNiveis"]');
            var w, nmObj, nmSel;
            for(w=0;w<=arraySelectsNiveis.length;w++){
                nmSel = 'selectNrNivel['+w+']';
                nmObj = 'selectCamposAssociados['+w+']';
                if ($(nmObj).options.length == 0){
                    alert("Por favor, insira ao menos um valor para o campo no " + getOrdinario($(nmSel).value).toLowerCase() + " nível.");
                    return;
                }
            }

            f.inOperacao.value             = "GRAVARCAMPOSVALORES";
            f.arrayGrupoCamposLength.value = nextIndex;
            if(parent.$('idOperacao').value == "ALTERAR"){
                if(flExclusao || flAlteracao)
                    confirmTxt = 'Deseja realmente gravar as alterações? \n\nAo gravar os relacionamentos de valores serão excluídos a partir do nível alterado.';
            }

            if(confirmTxt!=""){
                if(confirm(confirmTxt))
                    submit = true;
                else
                    submit = false;
            }

            if(submit){
                if(window.top.frameApp.dvAnimarAguarde != null ) window.top.frameApp.startAnimation();
                for(i=0;i<nextIndex;i++){
                    nmSelect = "selectCamposAssociados[" + i + "]";
                    for(j=0;j<$(nmSelect).options.length;j++)
                        $(nmSelect).options[j].selected = true;
                }
                f.submit();
            }
        }

        function replaceAll(string, origem, destino){
            var st = string;
            if(origem.length==0)return st;
            var idx = st.indexOf(origem);
            while(idx >= 0){
                st = st.substring(0,idx) + destino + st.substr(idx+origem.length);
                idx = st.indexOf(origem);
            }
            return st;
        }

        function pesquisaCampo(iVal, obj){

            var nC         = "nmCampo["+iVal+"]";                 // id do input text que possui nome do campo
            var nIdC       = "idCampo["+iVal+"]";                 // id do input hidden que guarda id do campo
            var nSa        = "selectCamposAssociados["+iVal+"]";  // id da select-list com os valores associados
            var btEx       = "btExcluirSimp"+iVal;                // id do botão excluir
            var valorCampo = trim(obj.value);
            idCampoInicial = $F(nIdC);

            if(valorCampo==""){
                //alert('Por favor, digite um nome ao campo antes de realizar a pesquisa.');
            } else if(trim(vlCampo) != valorCampo) {
                if(!verificaDuplicidadeCampos(iVal, valorCampo)){
                    new Ajax.Request('getLupaCamposDependentes.do', {
                        method: 'get',
                        parameters: {
                            nmCampo: valorCampo, limit: 255
                        },
                        contentType: 'text/plain',
                        onComplete: function(objXML){
                            var oXml   = new ActiveXObject("microsoft.xmldom");
                            oXml.async = false;
                            var regExp = new RegExp ('&', 'gi') ;
                            var xmlString  = objXML.responseText;
                            xmlString  = xmlString.replace(regExp,"&amp;");
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
                                var inSubFormularios = (parseInt(oXml.selectSingleNode("/xml-fragment/inSubFormularios").text)==0)?false:true;
                                if(inSubFormularios){
                                    if(messageBox()==6){
                                        var i, objRaiz, idCampo, nmCampo;
                                        $('inSubFormularios['+iVal+']').value = "1";
                                        $(nIdC).value = oXml.selectSingleNode("/xml-fragment/idCampoDependente").text;
                                        arrayCamposJaCadastrados[arrayCamposJaCadastrados.length] = iVal;
                                        objRaiz  = oXml.getElementsByTagName("AdmCampoVO");
                                        while($(nSa).options.length>0) $(nSa).options[0] = null;
                                        for (i=0; i < objRaiz.length; i++ ) {
                                            idCampo = objRaiz[i].childNodes[0].text;
                                            nmCampo = objRaiz[i].childNodes[1].text;
                                            $(nSa).options[$(nSa).options.length] = new Option(nmCampo, nmCampo);
                                        }
                                        //$(btEx).hide();
                                    }else{
                                        $(nIdC).value = idCampoInicial;
                                        obj.value = trim(vlCampo);
                                        setTimeout(function(){obj.focus()},300);
                                        //$(btEx).show();
                                    }
                                }else{
                                    //$(btEx).show();
                                    $(nIdC).value = "";
                                    vlCampo = valorCampo;
                                }
                            }
                        }
                    });
                }else{
                    alert("Já existe um campo homônimo. Por favor, utilize um outro nome para este campo.");
                    setTimeout(function(){obj.value = "";obj.focus()},300);
                }
            }
        }

        function verificaDuplicidadeCampos(iVal, nmCampo){
            var i, dsIdC;
            for(i=0;i<20;i++){
                dsIdC = "nmCampo["+i+"]";
                if($(dsIdC) && iVal!=i){
                    if(trim($F(dsIdC))==trim(nmCampo)){
                        return true;
                    }
                }
            }
            return false;
        }

        function blockPipe(obj){
            obj.value = replaceAll(obj.value,"|","");
            obj.value = replaceAll(obj.value,"/","");
        }

    </script>

    <script language="javascript" for="window" event="onload">
        if( window.top.frameApp.dvAnimarAguarde != null ) window.top.frameApp.stopAnimation();
        document.body.style.backgroundColor = '#ededed';

        <logic:equal name="FormFormulario" property="idGrupoCampos" value="">
        $('nmGrupoCampos').focus();
        $('nmGrupoCampos').onblur = function() { $('nmGrupoCampos').value = trim($('nmGrupoCampos').value); parent.$('nmGrupoCampos').value = $('nmGrupoCampos').value;}
        $('nmGrupoCampos').onkeyup = function() { parent.$('nmGrupoCampos').value = $('nmGrupoCampos').value;}
        if($('selectCamposAssociados[0]').options[0].value=="")$('selectCamposAssociados[0]').options[0] = null;
        </logic:equal>

        <logic:equal name="FormFormulario" property="inContatoAssociado" value="1">
        $('btNovoFilho').style.display = "none";
        var arrayBtExcluir = $('tableContent').select('[class="classBtExcluir"]');
        var arraySelects   = $('tableContent').select('[class="selectsNiveis"]');
        var arrayCampos    = $('tableContent').select('[class="classCampos"]');
        for(i=0;i<arrayBtExcluir.length;i++)arrayBtExcluir[i].style.display = "none";
        for(i=0;i<arraySelects.length;i++)arraySelects[i].disabled = true;
        for(i=0;i<arrayCampos.length;i++)arrayCampos[i].readOnly = true;
        </logic:equal>

        <logic:notEqual name="FormFormulario" property="idGrupoCampos" value="">
        $('nmGrupoCampos').readOnly = true;
        </logic:notEqual>
        organizeSelectsNiveis();
        
        <% if(request.getAttribute("msgError")!=null &&
              request.getAttribute("msgError").toString().indexOf("cadastrado")>0){ %>
        document.forms[0]["inMsgErro"].value = "<%=request.getAttribute("msgError").toString()%>";
        <% } %>

    </script>
    
    <script language="VBScript">
        function messageBox()
            pergunta = "Este campo já existe cadastrado. Deseja carregar os valores cadastrados para este campo? " + _
                       VBCR & "Ao carregar os valores, o sistema irá sobrepor a lista de valores do campo."
            messageBox = msgbox(pergunta, 4, "VivoNet")
        end function
    </script>
    

    <form action="camposDependentes.do" id="camposDependentes" name="camposDependentes" onSubmit="return false" method="post">
    <html:hidden name="FormFormulario" property="idContato"/>
    <html:hidden name="FormFormulario" property="inMsgErro"/>
    <html:hidden name="FormFormulario" property="inOperacao"/>
    <html:hidden name="FormFormulario" property="idGrupoCampos"/>
    <html:hidden name="FormFormulario" property="arrayGrupoCamposLength"/>
    <html:hidden name="FormFormulario" property="idxNivelExcluido"/>

    <%!
    public String getOrdinario(String strNivel) {
        String nmNivel = "";
        try {
            int    nrNivel = Integer.parseInt(strNivel);
            String[] arrayNmNiveis = {"Primeiro", "Segundo", "Terceiro", "Quarto", "Quinto", "Sexto","Sétimo", "Oitavo", "Nono", "Décimo", "Vigésimo"};
            switch (nrNivel) {
                case 1:  nmNivel = arrayNmNiveis[0]; break;
                case 2:  nmNivel = arrayNmNiveis[1]; break;
                case 3:  nmNivel = arrayNmNiveis[2]; break;
                case 4:  nmNivel = arrayNmNiveis[3]; break;
                case 5:  nmNivel = arrayNmNiveis[4]; break;
                case 6:  nmNivel = arrayNmNiveis[5]; break;
                case 7:  nmNivel = arrayNmNiveis[6]; break;
                case 8:  nmNivel = arrayNmNiveis[7]; break;
                case 9:  nmNivel = arrayNmNiveis[8]; break;
                case 10: nmNivel = arrayNmNiveis[9]; break;
            }
            if (nrNivel > 10 && nrNivel < 20) {
                nmNivel = arrayNmNiveis[9] + " " + (arrayNmNiveis[Integer.parseInt(strNivel.substring(1,2))-1]).toLowerCase();
            } else if (nrNivel == 20) {
                nmNivel = arrayNmNiveis[10];
            }
            //nmNivel = strNivel + " - " + nmNivel;

        } catch (Exception e) { 
            nmNivel = strNivel;
        } finally { return nmNivel; }
    }

    %>

    <table id="tableContent" width="680" border="0" cellspacing="0" cellpadding="0" style="margin:5px 0 0 10px;font-weight:bold;">

        <logic:iterate name="arrayCamposDependentes" id="AdmGrupoCamposDependentesVO" indexId="i" type="br.com.vivo.fo.admsistemas.vo.AdmGrupoCamposDependentesVODocument.AdmGrupoCamposDependentesVO" >

            <script type="text/javascript" language="javascript">
            arrayCamposJaCadastrados[arrayCamposJaCadastrados.length] = <%=i%>;
            </script>

            <bean:define name="AdmGrupoCamposDependentesVO" property="admCampoVOArray" id="admCampoVOArray" />

            <tr id="linha[<%=i%>]">
                <td valign="top">
                    <input type="hidden" name="idCampo[<%=i%>]" id="idCampo[<%=i%>]" value="<bean:write name="AdmGrupoCamposDependentesVO" property="idCampoDependente" />" />
                    <input type="hidden" name="inSubFormularios[<%=i%>]" id="inSubFormularios[<%=i%>]" value="<bean:write name="AdmGrupoCamposDependentesVO" property="inSubFormularios" />" />
                    <input type="hidden" name="selectNrNivel[0]" id="selectNrNivel[0]" value="1" />
                    <table cellspacing="0" cellpadding="0" style="font-weight:bold">
                        <tr>
                            <td width="100" valign="top" nowrap <%if(!ConstantesCRM.SZERO.equals(i)){%>style="padding-top:10px;"<%}%>>
                                <%if(i.intValue()==0){%>
                                Grupo
                                <%} else {%>
                                N&iacute;vel
                                <%}%>
                            </td>
                            <td width="180" valign="top" nowrap <%if(!"0".equals(i)){%>style="padding-top:10px;"<%}%>>
                                <%if(i.intValue()==0){%>
                                <html:text name="FormFormulario" property="nmGrupoCampos" tabindex="1" style="width:180px;" />
                                <%} else {
                                    int nivel = (Integer.parseInt(i.toString())+1);
                                %>
                                <select class="selectsNiveis" onchange="flAlteracao=true" name="selectNrNivel[<%=i%>]" id="selectNrNivel[<%=i%>]" style="width:180px;margin-left:3px;">
                                    <option value="<bean:write name="AdmGrupoCamposDependentesVO" property="nrNivel" />"><%=String.valueOf(nivel)%> - <%=getOrdinario(String.valueOf(nivel))%></option>
                                </select>
                                <%}%>
                            </td>
                            <td colspan="2"></td>
                        </tr>
                        <tr>
                            <td valign="top">Campo <%if(i.intValue()==0){%>1º n&iacute;vel<%}%></td>
                            <td valign="top">
                                <input style="width:180px;" class="classCampos" type="text" tabindex="1" name="nmCampo[<%=i%>]" id="nmCampo[<%=i%>]" value="<bean:write name="AdmGrupoCamposDependentesVO" property="nmCampoDependente" />" onfocus="blockPipe(this);idCampoInicial=$F('idCampo[<%=i%>]');vlCampo=this.value" onblur="this.value=trim(this.value);blockPipe(this);pesquisaCampo(<%=i%>,this);" />
                            </td>
                            <td width="80" rowspan="2" valign="bottom" align="center">
                                <img id="btRightSimp"   onMouseUp="transfereSelecaoLista('move', <%=i%>)" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" onmouseout="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif'" onmouseover="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_over.gif'" style="border:none;cursor:pointer;" /><br><br>
                                    <img id="btExcluirSimp<%=i%>" 
                                         onMouseUp="transfereSelecaoLista('delete', <%=i%>)" 
                                         src="/FrontOfficeWeb/resources/images/bt_x_nrml.gif" 
                                         onmouseout="this.src='/FrontOfficeWeb/resources/images/bt_x_nrml.gif'" 
                                         onmouseover="this.src='/FrontOfficeWeb/resources/images/bt_x_over.gif'" 
                                         style="border:none;cursor:pointer;" />
                            </td>
                            <td rowspan="2" valign="top">
                                <select name="selectCamposAssociados[<%=i%>]" id="selectCamposAssociados[<%=i%>]" size="3" multiple="true" style="width:310px;">
                                    <logic:iterate id="admCampoVOArray" name="admCampoVOArray">
                                    <option value="<bean:write name="admCampoVOArray" property="nmCampo" />"><bean:write name="admCampoVOArray" property="nmCampo" /></option>
                                    </logic:iterate>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td valign="top">Valor</td>
                            <td valign="top">
                                <input type="text" tabindex="1" name="valorInclusao[<%=i%>]" id="valorInclusao[<%=i%>]" value="" onfocus="blockPipe(this)" onblur="blockPipe(this);this.value=trim(this.value)" style="width:180px;" maxlength="180" />
                            </td>
                        </tr>
                        <tr>
                            <td colspan="4" align="right">
                                <%if(i.intValue()!=0){%>
                                <img id="btGravar" class="classBtExcluir" onMouseUp="excluir(<%=i%>, '<bean:write name="AdmGrupoCamposDependentesVO" property="idCampoDependente" />'); return false;" src="/FrontOfficeWeb/resources/images/bt_excluir_nrml.gif" onmouseout="this.src='/FrontOfficeWeb/resources/images/bt_excluir_nrml.gif';" onmouseover="this.src='/FrontOfficeWeb/resources/images/bt_excluir_over.gif';" style="margin-top:5px;border:none;cursor:pointer;" />
                                <%}%>
                            </td>
                        </tr>
                        <tr><td colspan="4" style="border-bottom:1px dotted #adadad;margin-top:10px;">&nbsp;</td></tr>
                    </table>
                </td>
            </tr>

        </logic:iterate>

    </table>

    <table width="690" border="0" cellspacing="0" cellpadding="0" align="left">
        <tr>
            <td id="tdOperacoes" align="right" valign="top" style="padding-top:76px;">
                <img id="btNovoFilho" onMouseUp="novoFilho(); return false;"     src="/FrontOfficeWeb/resources/images/bt_novonivel_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_novonivel_over.gif" style="border:none;" />
                <img id="btGravar"    onMouseUp="salvar(); return false;" src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif"    rolloverImage="/FrontOfficeWeb/resources/images/bt_gravar_over.gif"    style="border:none;" />
            </td>
        </tr>
    </table>

    </form>

    <vivo:alert atributo="msg" scope="request" afterFunction="reloadPage()" />
    <vivo:alert atributo="msgError" scope="request" />

    
    

<!--/acesso:controlHiddenItem-->
</netui-template:section>
</netui-template:template>