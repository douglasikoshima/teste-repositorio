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

<bean:define id="FormFormulario"          name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formFormulario"/>
<bean:define id="arrayGrupoCampos"        name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formFormulario.arrayGrupoCampos"/>
<bean:define id="arrayCampoClassificador" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formFormulario.arrayCampoClassificador"/>
<bean:define id="CamposAssociadosVO"      name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formFormulario.camposAssociados"/>
<bean:define id="CamposExistentesVO"      name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formFormulario.camposExistentes"/>



<netui-template:template templatePage="/resources/jsp/templateAdmSistClean.jsp">
<netui-template:section name="bodySection">
<!--acesso:controlHiddenItem nomeIdentificador="adm_vcf_verpagina"-->

    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js" ></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/toolTip.js"></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
    <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    <script type="text/javascript" language="javascript">

        var inOperacao;
        var flAlteracao = false;

        if( window.top.frameApp.dvAnimarAguarde != null ) window.top.frameApp.startAnimation();

        function getAcao() {
            var inOperacao = "";
            //Novo Grupo
            if ($('tipoOperacao[0]').checked) {
                inOperacao = $('tipoOperacao[0]').value;
            }
            //Alteração
            else if ($('tipoOperacao[1]').checked) {
                inOperacao = $('tipoOperacao[1]').value;
            }
            return inOperacao;
        }

        function salvar() {

            var f = document.forms[0];
            
            //Novo Grupo
            if ($('tipoOperacao[0]').checked) {
                if (trim($('nmGrupoCampos').value) == "") {
                    alert('Digite um nome para o grupo de campos a ser criado.');
                    return;
                /*} else if (!flAlteracao) {
                    alert("Associe ao menos um campo ao grupo.");
                    return false;*/
                } else {
                    inOperacao = $('tipoOperacao[0]').value;
                }
            }

            //Alteração
            if ($('tipoOperacao[1]').checked) {
                if ($('idGrupoCampos').value == "-1") {
                    alert('Selecione um grupo de campos.');
                    return false;
                /*} else if (!flAlteracao) {
                    alert("Nenhuma alteração foi feita.");
                    return false;*/
                } else {
                    inOperacao = $('tipoOperacao[1]').value;
                }
            }
            
            for (i=0; i < $('aCamposSelecionados').length; i++) {
                $('aCamposSelecionados').options[i].selected = true;
            }

            f.inOperacao.value = "SETAGRUPARCAMPOS";
            f.action='agruparCampos.do?acao=' + getAcao();
            if( window.top.frameApp.dvAnimarAguarde != null ) window.top.frameApp.startAnimation();
            document.forms[0].submit();    
        }

        function transfereSelecaoLista(listaOrigem, listaDestino, obj) {
            var i;
            var f = document.forms[0]; 

            if ($('spanSelectList').style.display == "" 
                || $('spanSelectList').style.display == "block") {
                if (obj.id == "btRightSimp") {
                    if (f["idGrupoCampos"] && f["idGrupoCampos"].value == "") {
                        alert("É necessária a seleção de um grupo de campos.")
                        return;
                    }
                }
            }

            for(i = 0; i<listaOrigem.options.length; i++)
                if(listaOrigem.options[i].selected)
                    listaDestino.options[listaDestino.options.length] = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);
                    
            for(i = listaOrigem.options.length-1; i>=0; i--)
                if(listaOrigem.options[i].selected)
                    listaOrigem.options[i] = null;
        }

        function manageContentGrupoCampos(valor) {
            flAlteracao = false;
            $('idClassificadorCampoAtual').selectedIndex = 0;
            while ($('aCamposSelecionados').options.length > 0) {
                $('aCamposSelecionados').options.remove(0);
            }
            while ($('aCamposExistentes').options.length > 0) {
                $('aCamposExistentes').options.remove(0);
            }
            if (valor == "NOVO") {
                $('spanSelectList').style.display = "none";
                $('spanTextField').style.display = "block";
                $('nmGrupoCampos').focus();
            } else if (valor == "ALTERAR") {
                
                $('spanTextField').style.display = "none";
                $('spanSelectList').style.display = "block";
                $('idGrupoCampos').selectedIndex = 0;
            }
        }

        function getCamposSelecionados(obj) {
            inOperacao = "AGRUCAMGETCAMPOSSEL";
            var f = document.forms[0];
            if (obj.value != "") {
                if( window.top.frameApp.dvAnimarAguarde != null ) window.top.frameApp.startAnimation();

                //Lista de classificadores deve ser limpa pois podem surgir dados repetidos em ambas as listagens.
                $('idClassificadorCampoAtual').selectedIndex = 0;
                while ($('aCamposExistentes').options.length > 0) {
                    $('aCamposExistentes').options.remove(0);
                }

                while ($('aCamposSelecionados').options.length > 0) {
                    $('aCamposSelecionados').options.remove(0);
                }

                f.inOperacao.value = inOperacao;
                f.action = 'agruparCampos.do?acao=' + getAcao();
                f.submit();

            } else {
                while ($('aCamposSelecionados').options.length > 0) {
                    $('aCamposSelecionados').options.remove(0);
                }
            }
        }

        function reloadPage() {
            var f = document.forms[0];
            f.inOperacao.value = "GETAGRUPARCAMPOS";
            f.submit();
        }

        function getCamposDisponiveis(obj) {
            inOperacao = "AGRUCAMGETCAMPOSDISP";
            var f = document.forms[0];
            if (obj.value != "") {
                if( window.top.frameApp.dvAnimarAguarde != null ) window.top.frameApp.startAnimation();
                
                /*f.inOperacao.value = "AGRUCAMGETCAMPOSDISP";
                f.action = 'agruparCampos.do?acao=' + getAcao();
                f.submit();*/
                new Ajax.Request('agruparCampos.do', {
                    method: 'get',
                    parameters: {
                        inOperacao: inOperacao, limit: 20,
                        idClassificadorCampoAtual: obj.value, limit: 12,
                        idGrupoCampos: f.idGrupoCampos.value, limit: 12
                    },
                    contentType: 'text/xml',
                    onComplete: feedSelectList
                });
                
            } else {
                while ($('aCamposExistentes').options.length > 0) {
                    $('aCamposExistentes').options.remove(0);
                }
            }
        }

        function feedSelectList(objXML) {
            var idCampo, nmCampo, obj, tagDados;
            var oXml   = new ActiveXObject("microsoft.xmldom");
            oXml.async = false;
            var regExp = new RegExp ('&', 'gi') ;
            xmlString  = objXML.responseText;
            xmlString  = xmlString.replace(regExp,"&amp;");

            if (!oXml.loadXML(xmlString) || oXml.selectSingleNode("/xml-fragment/friendlyMessage") != null) {
                exceptionMessage = oXml.selectSingleNode("/xml-fragment/exceptionMessage").text;
                friendlyMessage  = oXml.selectSingleNode("/xml-fragment/friendlyMessage").text;
                top.frameApp.$('errTitle').innerHTML = "Erro";
                top.frameApp.$('errCode').innerHTML = "";
                top.frameApp.$('errMsg').innerHTML = friendlyMessage;
                top.frameApp.$('errDetails').value = exceptionMessage;
                top.frameApp.$('dvAnimarAguardeErro').style.display = "block";
            } else {
                if (inOperacao == "AGRUCAMGETCAMPOSDISP") {
                    obj = $('aCamposExistentes');
                    tagDados = "disponiveis";

                    while ($('aCamposExistentes').options.length > 0) {
                        $('aCamposExistentes').options.remove(0);
                    }

                    /*while ($('aCamposSelecionados').options.length > 0) {
                        $('aCamposSelecionados').options.remove(0);
                    }*/

                } else if (inOperacao == "AGRUCAMGETCAMPOSSEL") {
                    obj = $('aCamposSelecionados');
                    tagDados = "selecionados";
                    while ($('aCamposSelecionados').options.length > 0) {
                        $('aCamposSelecionados').options.remove(0);
                    }
                }
                if (oXml.getElementsByTagName(tagDados)) {
                    for (i=0; i < oXml.getElementsByTagName("AdmCampoVO").length; i++ ) {
                        idCampo = oXml.getElementsByTagName("AdmCampoVO")[i].childNodes[0].text;
                        nmCampo = oXml.getElementsByTagName("AdmCampoVO")[i].childNodes[1].text;
                        obj.options[obj.options.length] = new Option(nmCampo, idCampo);
                    }
                }
                if( window.top.frameApp.dvAnimarAguarde != null ) window.top.frameApp.stopAnimation();
            }
        }

    </script>

    <script language="javascript" for="window" event="onload">
        if( window.top.frameApp.dvAnimarAguarde != null ) window.top.frameApp.stopAnimation();
        document.body.style.backgroundColor = '#ededed';
    </script>

    <form action="agruparCampos.do" name="formFormulario" id="formFormulario" onSubmit="return false" method="POST">
    <html:hidden name="FormFormulario" property="idContato"/>
    <html:hidden name="FormFormulario" property="inOperacao"/>

    <table width="730" border="0" cellspacing="0" cellpadding="0" align="center" style="margin:5px 0 0 10px;font-weight:bold;">
        <tr>
            <td width="120">
                <input type="radio" onclick="manageContentGrupoCampos(this.value)" name="tipoOperacao" id="tipoOperacao[0]" value="NOVO" style="border:none;background:none" <logic:lessThan name="FormFormulario" property="arrayGrupoCamposLength" value="1">checked</logic:lessThan> /> Novo grupo
            </td>
            <td width="200">
                <input type="radio" onclick="manageContentGrupoCampos(this.value)" name="tipoOperacao" id="tipoOperacao[1]" value="ALTERAR" style="border:none;background:none" <logic:greaterThan name="FormFormulario" property="arrayGrupoCamposLength" value="0">checked</logic:greaterThan> /> Alterar grupo existente
            </td>
            <td colspan="2" align="left" width="410">
                <div id="spanSelectList" <logic:lessThan name="FormFormulario" property="arrayGrupoCamposLength" value="1">style="display:none;"</logic:lessThan>>
                    Grupo de campos:
                    <html:select name="FormFormulario" property="idGrupoCampos" styleId="idGrupoCampos" style="width:180px;" onchange="getCamposSelecionados(this)">
                        <html:option value="">-- Selecione --</html:option>
                        <html:options collection="arrayGrupoCampos" property="idGrupoCampos" labelProperty="nmGrupoCampos"/>
                    </html:select>
                </div>
                <div id="spanTextField" <logic:greaterThan name="FormFormulario" property="arrayGrupoCamposLength" value="0">style="display:none;"</logic:greaterThan>>
                    Nome do grupo:
                    <html:text name="FormFormulario" property="nmGrupoCampos" styleId="nmGrupoCampos" style="width:180px;margin-left:13px;" />
                </div>
            </td>
        </tr>
        <tr>
            <td style="padding-top:15px">Classificador:</td>
            <td colspan="4" style="padding-top:15px">
                <html:select name="FormFormulario" property="idClassificadorCampoAtual" onchange="getCamposDisponiveis(this);" style="width:200" styleClass="SELECT">
                    <html:option value="">-- Selecione --</html:option>
                    <html:options collection="arrayCampoClassificador" property="idClassificadorCampo" labelProperty="nmClassificadorCampo"/>
                </html:select>
            </td>
        </tr>
        <tr>
            <td colspan="2" style="padding-top:20px;">
                Campos dispon&iacute;veis<br>
                <html:select name="FormFormulario" property="aCamposExistentes" styleId="aCamposExistentes" multiple="true" style="width:300px;height:230px">
                    <html:options collection="CamposExistentesVO" property="idCampo" labelProperty="nmCampo"/>
                </html:select>
            </td>
            <td width="130" valign="middle" align="center">
                <img id="btRightSimp" onMouseUp="transfereSelecaoLista(document.forms[0].aCamposExistentes, document.formFormulario.aCamposSelecionados, this); return false;" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_rightaln_over.gif" style="border:none;" /><br><br>
                <img id="btLeftSimp"  onMouseUp="transfereSelecaoLista(document.forms[0].aCamposSelecionados, document.formFormulario.aCamposExistentes, this); return false;"src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_leftaln_over.gif" style="border:none;" />
            </td>
            <td style="padding-top:20px;">
                Campos selecionados<br>
                <html:select name="FormFormulario" property="aCamposSelecionados" styleId="aCamposSelecionados" multiple="true" style="width:300px;height:230px">
                    <html:options collection="CamposAssociadosVO" property="idCampo" labelProperty="nmCampo"/>
                </html:select>
            </td>
        </tr>
        <tr>
            <td align="right" height="12px" colspan="4">
                <img vspace="3" id="btSalvar1" onClick="return salvar();" src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_gravar_over.gif'" border="0" align="right" style="cursor:pointer" />
            </td>
        </tr>
    </table>

    <vivo:alert atributo="msg" scope="request" afterFunction="reloadPage()" />

    </form>

<!--/acesso:controlHiddenItem-->
</netui-template:section>
</netui-template:template>