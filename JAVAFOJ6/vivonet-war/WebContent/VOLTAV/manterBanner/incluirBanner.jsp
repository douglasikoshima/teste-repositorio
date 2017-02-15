<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>

<bean:define id="FormManterBanner" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formUploadBanner"/>
<bean:define id="aArea"    name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formUploadBanner.comboFiltro.areaArray"/>

<html>
<head>
    <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">
    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/frameweb.js" ></script>
    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/tratamento.js" ></script>
    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/prototype.js" ></script>
    <script language="javaScript">

        parent.oculta_div();

        function habilitaDesabilitaLink(op){
            if(op == 1){
                document.getElementById('campoLink').style.visibility = 'hidden';
                document.getElementById('labelCampoLink').style.visibility = 'hidden';

            }else{
                document.getElementById('campoLink').style.visibility = 'visible';
                document.getElementById('labelCampoLink').style.visibility = 'visible';

            }
        }

        var timeout;

        function incluirBanner(){
            var form = document.forms[0];
            if(valida()){
                //form.target = 'innerBrowserInlcuirBanner';
                form.target = 'frameApp';
                form.action = 'incluirBanner.do';
                top.frameApp.mostrar_div();
                form.submit();
            }
        }

        function getListaCampanha() {
            new Ajax.Request('getListaCampanhas.do', {
                method: 'get',
                contentType: 'text/xml',
                onComplete: feedSelectList
            });
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

                $('campoLink').style.visibility = "visible";
                $('labelCampoLink').style.visibility = "visible";
                while ($('idCampanha').options.length > 0) {
                    $('idCampanha').options.remove(0);
                }

                var arrayCampanhas = oXml.getElementsByTagName("CampanhaVO");
                if (arrayCampanhas.length == 0) {
                    $('idCampanha').options[$('idCampanha').options.length] = new Option("Nenhuma campanha encontrada.", "");
                } else {
                    $('idCampanha').style.fontStyle = "normal";
                    $('idCampanha').options[$('idCampanha').options.length] = new Option("-- Selecione uma campanha --", "");
                    for (i=0; i < oXml.getElementsByTagName("CampanhaVO").length; i++ ) {
                        idCampo = oXml.getElementsByTagName("CampanhaVO")[i].childNodes[0].text;
                        nmCampo = oXml.getElementsByTagName("CampanhaVO")[i].childNodes[1].text;
                        $('idCampanha').options[$('idCampanha').options.length] = new Option(nmCampo, idCampo);
                    }
                }

                if (window.top.frameApp.dvAnimarAguarde != null) window.top.frameApp.stopAnimation();
            }
        }

        function valida(){
            var extensao;
            var form       = document.forms[0];
            var inCampanha = $('inCampanha').checked?true:false;

            if (trim(form.dsBanner.value) == '' ) {
                alert('Descrição é um campo obrigatório.');
                return false;

            } else if(form.idArea.selectedIndex == 0) {
                alert('Area é um campo obrigatório.');
                return false;

            } else if(trim(form.fileBanner.value) == '') {
                alert('Arquivo é um campo obrigatório.');
                return false;

            } else if(!inCampanha && (form.idTipoImagem[1].checked == false && form.idTipoImagem[0].checked == false)) {
                alert('Selecione o tipo da imagem.');
                return false;

            //} else if(!inCampanha && form.idTipoImagem[1].checked == true && trim(form.nmLink.value) == '') {
            //  alert('Na inclusão de arquivo tipo \'Imagem\' o campo Link é obrigatório.');
            //  return false;

            } else if(form.fileBanner.value != '') {

                extensao = form.fileBanner.value.substring(form.fileBanner.value.lastIndexOf('.')+1, form.fileBanner.value.length).toLowerCase();
                if(trim(form.fileBanner.value).length < 5) {
                    alert('Arquivo selecionado inválido.');
                    return false;
                }
                if (!inCampanha && (form.idTipoImagem[0].checked == true && extensao != 'swf')) {
                    alert('O tipo do arquivo (extensão) deve ser SWF.');
                    return false;
                }
                if ((inCampanha || form.idTipoImagem[1].checked == true) && (extensao != 'gif' && extensao != 'jpg')) {
                    alert('O tipo do arquivo (extensão) deve ser GIF ou JPG');
                    return false;
                }
            }
            if (inCampanha && trim($F('idCampanha'))=="") {
                alert('É necessário selecionar uma campanha já cadastrada no sistema.');
                return false;
            }
            return true;
        }

        function verifyCampanha(obj) {
            if (obj.checked == true) {
                $('divTipoImagem').style.visibility  = "hidden";
                $('divImagemFixed').style.display = "block";
                $('campoLink').innerHTML             = '<select id="idCampanha" name="idCampanha" style="width:392px;margin-left:3px;font-style:oblique;"><option value="">Carregando lista de campanhas...</option></select>';
                getListaCampanha();
            } else {
                $('divTipoImagem').style.visibility  = "visible";
                $('divImagemFixed').style.display = "none";
                $('campoLink').innerHTML             = '<input type="text" name="nmLink" id="nmLink" maxlength="100" style="width:390px" />';
            }
        }

    </script>

</head>
<body>
    <form name="formUpload" method="post" enctype="multipart/form-data" onsubmit="return false;">
        <table border="0">
            <tr>
                <td>
                    <b>Descrição:</b>
                </td>
                <td>
                    <html:text name="FormManterBanner" property="dsBanner" style="width:390px" maxlength="30"/>
                </td>
            </tr>
            <tr>
                <td>
                    <b>&Aacute;rea:</b>
                </td>
                <td>
                    &nbsp;
                    <html:select name="FormManterBanner" property="idArea" style="width:390px">
                        <html:option value="-1">Escolha...</html:option>
                        <html:options collection="aArea" property="idArea" labelProperty="nmArea" />
                    </html:select>
                </td>
            </tr>
            <tr>
                <td>
                    <b>Arquivo:</b>
                </td>
                <td>
                    <html:file name="FormManterBanner" property="fileBanner" style="width:390px"/>
                </td>
            </tr>
            <tr>
                <td>
                    <div id="labelCampoLink"><b>Link:</b></div>
                </td>
                <td>
                    <div id="campoLink">
                    <html:text name="FormManterBanner" property="nmLink" maxlength="100" style="width:390px" />
                    </div>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <strong>Banner de Campanha:</strong>
                    <html:checkbox name="FormManterBanner" property="inCampanha" onclick="verifyCampanha(this)" style="background:none;border:none" />
                </td>
            </tr>
            <tr>
                <td>
                    <b>Tipo Imagem:</b>
                </td>
                <td>
                    <div id="divTipoImagem">
                        <html:radio name="FormManterBanner" property="idTipoImagem" value="1" styleClass="radio" onclick="habilitaDesabilitaLink(this.value);" id="radioSwf"/>.SWF
                        <html:radio name="FormManterBanner" property="idTipoImagem" value="2" styleClass="radio" onclick="habilitaDesabilitaLink(this.value);" id="radioImagem" />Imagem (.GIF/.JPG)
                    </div>
                    <div id="divImagemFixed" style="display:none;height:10px;line-height:17px;padding-left:10px;margin-top:-18px;">
                        Imagem (.GIF/.JPG)
                    </div>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="right">
                    <img style="cursor:hand;border:none" id="btIncluir" onclick="incluirBanner();" src="/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_incluir_over.gif'" />&nbsp;
                </td>
            </tr>
        </table>

         <iframe scrolling="yes"  name="innerBrowserInlcuirBanner" height="0px" width="0px">
         </iframe>

        <html:hidden name="FormManterBanner" property="login"/>

    </form>

    <script language="javaScript">
        document.body.style.backgroundColor = '#ededed';
    </script>

</body>
</html>