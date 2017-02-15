<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formMenu"/>

<html:html>
    <head>
        <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js" ></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/xml2json.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/validacao.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        <script type="text/javascript">
            <!--
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

            pesquisar = function(){
                if($('idSelCanal').options.length>0){

                    if(top.frameApp!=null)
                        if(top.frameApp.dvAnimarAguarde!=null) top.frameApp.startAnimation();

                    for(i=0; i<$('idSelCanal').options.length; i++)
                        $('idSelCanal').options[i].selected=true;

                    for(i=0; i<$('idSelUF').options.length; i++)
                        $('idSelUF').options[i].selected=true;

                    for(i=0; i<$('idSelCart').options.length; i++)
                        $('idSelCart').options[i].selected=true;

                    for(i=0; i<$('idSelSegm').options.length; i++)
                        $('idSelSegm').options[i].selected=true;

                    for(i=0; i<$('idSelIMenu').options.length; i++)
                        $('idSelIMenu').options[i].selected=true;

                    parent.selecionaAbaResultado(false);
                    document.forms[0].target = '_self';
                    document.forms[0].action = 'resultadoPesquisa.do';
                    document.forms[0].submit();

                }else{
                    alert('Favor selecionar pelo menos o Canal.');
                    return false;
                }
            }

            onload = function(){
                if(top.frameApp!=null)
                    if(top.frameApp.dvAnimarAguarde!=null) top.frameApp.stopAnimation();
            }

            -->
        </script>
    </head>
    <body>
        <form action="/FrontOfficeWeb/VOLE/Parametrizacao/resultadoPesquisa.do" method="POST" id="frmMenu" enctype="multipart/form-data" onsubmit="return false;">
        <table width="100%" height="100%" border="0" cellpadding="1" cellspacing="1" align="center" class="tbl_bgGray">
            <tr>
                <td>
                    <vivo:quadro id="qdCanal" height="110" width="370" label="Canal">

                        <table align="left" border="0" cellpadding="0" cellspacing="0">
                            <tr>
                                <td width="310" align="center" valign="top">
                                    Existente<br>
                                    <html:select name="Form" property="idDispCanal" styleId="idDispCanal" multiple="true" style="width:150px;" size="6" ondblclick="transfereSelecaoLista($('idDispCanal'), $('idSelCanal'), false);" >
                                        <html:optionsCollection name="Form" property="opCanalDisp" value="id" label="name"/>
                                    </html:select>
                                </td>
                                <td width="70" align="center" valign="middle">
                                    <img id="btRightSimp" onclick="transfereSelecaoLista($('idDispCanal'), $('idSelCanal'), false);"
                                         src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif"
                                         onmouseover="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_over.gif'"
                                         onmouseout="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif'"
                                         style="cursor:pointer;border:none;"
                                         border="0">
                                    <br>
                                    <img id="btLeftSimp"  onclick="transfereSelecaoLista($('idDispCanal'), $('idSelCanal'), true);"
                                         src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif"
                                         onmouseover="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_over.gif'"
                                         onmouseout="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif'"
                                         style="cursor:pointer;border:none;"
                                         border="0">
                                </td>
                                <td width="310" align="center" valign="top">
                                    Associada<br>
                                    <html:select name="Form" property="idSelCanal" styleId="idSelCanal" multiple="true" style="width:150px;" size="6" ondblclick="transfereSelecaoLista($('idDispCanal'), $('idSelCanal'), true);">
                                    </html:select>
                                </td>
                            <tr>
                        </table>

                    </vivo:quadro>
                </td>
                <td>
                    <vivo:quadro id="qdUF" height="110" width="370" label="UF">
                        <table align="left" border="0" cellpadding="0" cellspacing="0">
                            <tr>
                                <td width="310" align="center" valign="top">
                                    Existente<br>
                                    <html:select name="Form" property="idDispUF" styleId="idDispUF" multiple="true" style="width:150px;" size="6" ondblclick="transfereSelecaoLista($('idDispUF'), $('idSelUF'), false);">
                                        <html:optionsCollection name="Form" property="opUFDisp" value="id" label="name"/>
                                    </html:select>
                                </td>
                                <td width="70" align="center" valign="middle">
                                    <img id="btRightSimp" onclick="transfereSelecaoLista($('idDispUF'), $('idSelUF'), false);"
                                         src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif"
                                         onmouseover="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_over.gif'"
                                         onmouseout="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif'"
                                         style="cursor:pointer;border:none;"
                                         border="0">
                                    <br>
                                    <img id="btLeftSimp"  onclick="transfereSelecaoLista($('idDispUF'), $('idSelUF'), true);"
                                         src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif"
                                         onmouseover="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_over.gif'"
                                         onmouseout="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif'"
                                         style="cursor:pointer;border:none;"
                                         border="0">
                                </td>
                                <td width="310" align="center" valign="top">
                                    Associada<br>
                                    <html:select name="Form" property="idSelUF" styleId="idSelUF" multiple="true" style="width:150px;" size="6" ondblclick="transfereSelecaoLista($('idDispUF'), $('idSelUF'), true);">
                                    </html:select>
                                </td>
                            <tr>
                        </table>
                    </vivo:quadro>
                </td>
            </tr>
            <tr>
                <td>
                    <vivo:quadro id="qdCarteira" height="110" width="370" label="Carteira">
                        <table align="left" border="0" cellpadding="0" cellspacing="0">
                            <tr>
                                <td width="310" align="center" valign="top">
                                    Existente<br>
                                    <html:select name="Form" property="idDispCart" styleId="idDispCart" multiple="true" style="width:150px;" size="6" ondblclick="transfereSelecaoLista($('idDispCart'), $('idSelCart'), false);" >
                                        <html:optionsCollection name="Form" property="opCartDisp" value="id" label="name"/>
                                    </html:select>
                                </td>
                                <td width="70" align="center" valign="middle">
                                    <img id="btRightSimp" onclick="transfereSelecaoLista($('idDispCart'), $('idSelCart'), false);"
                                         src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif"
                                         onmouseover="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_over.gif'"
                                         onmouseout="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif'"
                                         style="cursor:pointer;border:none;"
                                         border="0">
                                    <br>
                                    <img id="btLeftSimp"  onclick="transfereSelecaoLista($('idDispCart'), $('idSelCart'), true);"
                                         src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif"
                                         onmouseover="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_over.gif'"
                                         onmouseout="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif'"
                                         style="cursor:pointer;border:none;"
                                         border="0">
                                </td>
                                <td width="310" align="center" valign="top">
                                    Associada<br>
                                    <html:select name="Form" property="idSelCart" styleId="idSelCart" multiple="true" style="width:150px;" size="6" ondblclick="transfereSelecaoLista($('idDispCart'), $('idSelCart'), true);">
                                    </html:select>
                                </td>
                            <tr>
                        </table>
                    </vivo:quadro>
                </td>
                <td>
                    <vivo:quadro id="qdSegmentacao" height="110" width="370" label="Segmentação">
                        <table align="left" border="0" cellpadding="0" cellspacing="0">
                            <tr>
                                <td width="310" align="center" valign="top">
                                    Existente<br>
                                    <html:select name="Form" property="idDispSegm" styleId="idDispSegm" multiple="true" style="width:150px;" size="6" ondblclick="transfereSelecaoLista($('idDispSegm'),$('idSelSegm'), false);" >
                                        <html:optionsCollection name="Form" property="opSegmDisp" value="id" label="name"/>
                                    </html:select>
                                </td>
                                <td width="70" align="center" valign="middle">
                                    <img id="btRightSimp" onclick="transfereSelecaoLista($('idDispSegm'),$('idSelSegm'), false);"
                                         src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif"
                                         onmouseover="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_over.gif'"
                                         onmouseout="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif'"
                                         style="cursor:pointer;border:none;"
                                         border="0">
                                    <br>
                                    <img id="btLeftSimp"  onclick="transfereSelecaoLista($('idDispSegm'),$('idSelSegm'), true);"
                                         src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif"
                                         onmouseover="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_over.gif'"
                                         onmouseout="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif'"
                                         style="cursor:pointer;border:none;"
                                         border="0">
                                </td>
                                <td width="310" align="center" valign="top">
                                    Associada<br>
                                    <html:select name="Form" property="idSelSegm" styleId="idSelSegm" multiple="true" style="width:150px;" size="6" ondblclick="transfereSelecaoLista($('idDispSegm'),$('idSelSegm'), true);">
                                    </html:select>
                                </td>
                            <tr>
                        </table>
                    </vivo:quadro>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <vivo:quadro id="qdItemMenu" height="110" width="745" label="Item Menu">
                        <table align="left" border="0" cellpadding="0" cellspacing="0">
                            <tr>
                                <td width="310" align="center" valign="top">
                                    Existente<br>
                                    <html:select name="Form" property="idDispIMenu" styleId="idDispIMenu" multiple="true" style="width:330px;" size="6" ondblclick="transfereSelecaoLista($('idDispIMenu'), $('idSelIMenu'), false);" >
                                        <html:optionsCollection name="Form" property="opIMenuDisp" value="id" label="name"/>
                                    </html:select>
                                </td>
                                <td width="70" align="center" valign="middle">
                                    <img id="btRightSimp" onclick="transfereSelecaoLista($('idDispIMenu'), $('idSelIMenu'), false);"
                                         src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif"
                                         onmouseover="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_over.gif'"
                                         onmouseout="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif'"
                                         style="cursor:pointer;border:none;"
                                         border="0">
                                    <br><br>
                                    <img id="btLeftSimp"  onclick="transfereSelecaoLista($('idDispIMenu'), $('idSelIMenu'), true);"
                                         src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif"
                                         onmouseover="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_over.gif'"
                                         onmouseout="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif'"
                                         style="cursor:pointer;border:none;"
                                         border="0">
                                </td>
                                <td width="310" align="center" valign="top">
                                    Associada<br>
                                    <html:select name="Form" property="idSelIMenu" styleId="idSelIMenu" multiple="true" style="width:330px;" size="6" ondblclick="transfereSelecaoLista($('idDispIMenu'), $('idSelIMenu'), true);">
                                    </html:select>
                                </td>
                            <tr>
                        </table>
                    </vivo:quadro>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="right">
                    <img style="cursor:pointer;border:none;" id="btPesquisar" onclick="pesquisar();" src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" onmouseout="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif'" onmouseover="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif'" />&nbsp;
                </td>
            </tr>
        </table>
        </form>
    </body>
</html:html>