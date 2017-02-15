<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<acesso:controlInitEnv/>
<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="scriptForm"/>

<netui-template:template templatePage="/resources/jsp/templateRetencao.jsp">
    <netui-template:section name="headerSection">
        <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js" ></script>
        <script language="javascript">
            loadScript = function(){
                var f = document.forms[0];
                if($F('idScript').strip()!=''){
                    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
                    f.action = 'loadScript.do';
                    f.submit();
                }else{
                    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
                    f.action = 'begin.do';
                    f.submit();
                }
            }

            validaForm = function(){
                var f = document.forms[0];
                if($F('idScript').strip()==''){
                    alert('É necessário a seleção de uma Matriz ou o item Nova Matriz');
                    return false;
                }
                if($F('nmMatriz').strip()==''){
                    alert('É necessário especificar o nome da Matriz.');
                    return false;
                }
                if(f.idSelRegional.options.length<1){
                    alert('É necessário a seleção de ao menos um(a) valor de Regional');
                    return false;
                }
                if(f.idSelTpCliente.options.length<1){
                    alert('É necessário a seleção de ao menos um(a) valor de Tipo de Cliente');
                    return false;
                }
                if(f.idSelTpLinha.options.length<1){
                    alert('É necessário a seleção de ao menos um(a) valor de Tipo de Linha');
                    return false;
                }
                if(f.idSelIntencao.options.length<1){
                    alert('É necessário a seleção de ao menos um(a) valor de Intencao');
                    return false;
                }
                if(f.idSelDestinos.options.length<1){
                    alert('É necessário a seleção de ao menos um(a) valor de Destinos');
                    return false;
                }
                if(f.idSelGrupos.options.length<1){
                    alert('É necessário a seleção de ao menos um(a) valor de Grupos');
                    return false;
                }
                if(f.idSelSegmentacao.options.length<1){
                    alert('É necessário a seleção de ao menos um(a) valor de Segmentacao');
                    return false;
                }
                if(f.idSelOfertas.options.length<1){
                    alert('É necessário a seleção de ao menos um(a) valor de Ofertas');
                    return false;
                }
                return true;
            }

            gravar = function(){
                if(validaForm()){
                    var f = document.forms[0];
                    for(j = 0; j<f.idSelRegional.options.length; j++) {
                        f.idSelRegional.options[j].selected = true;
                    }
                    for(j = 0; j<f.idSelTpCliente.options.length; j++) {
                        f.idSelTpCliente.options[j].selected = true;
                    }
                    for(j = 0; j<f.idSelTpLinha.options.length; j++) {
                        f.idSelTpLinha.options[j].selected = true;
                    }
                    for(j = 0; j<f.idSelIntencao.options.length; j++) {
                        f.idSelIntencao.options[j].selected = true;
                    }
                    for(j = 0; j<f.idSelDestinos.options.length; j++) {
                        f.idSelDestinos.options[j].selected = true;
                    }
                    for(j = 0; j<f.idSelGrupos.options.length; j++) {
                        f.idSelGrupos.options[j].selected = true;
                    }
                    for(j = 0; j<f.idSelSegmentacao.options.length; j++) {
                        f.idSelSegmentacao.options[j].selected = true;
                    }
                    for(j = 0; j<f.idSelOfertas.options.length; j++) {
                        f.idSelOfertas.options[j].selected = true;
                    }
                    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
                    f.action = "gravarMatriz.do";
                    f.submit();
                }
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
                        if(listaOrigem.options[i].selected)
                            listaDestino.options[listaDestino.options.length] = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);
                    }
                    for(i = listaOrigem.options.length-1; i>=0; i--)
                        if(listaOrigem.options[i].selected)
                            listaOrigem.options[i] = null;
                }
            }

            function CarregaAba(nome){
                switch(nome){
                    case "bt01":
                        abaSelected(btAba, bt01);
                        $("grpCampos1").show();
                        $("grpCampos2").hide();
                        $("grpCampos3").hide();
                        break;

                    case "bt02":
                        abaSelected(btAba, bt02);
                        $("grpCampos2").show();
                        $("grpCampos1").hide();
                        $("grpCampos3").hide();
                        break;

                    case "bt03":
                        abaSelected(btAba, bt03);
                        $("grpCampos3").show();
                        $("grpCampos1").hide();
                        $("grpCampos2").hide();
                        break;
                }
            }

            function habilitarMatriz(valor){
                if($F('idScript')!='N' && $F('idScript')!=''){
                    try {
                        if (top.frameApp.dvAnimarAguarde != null) top.frameApp.startAnimation();
                        var params = $H();
                        params.set('idScript', $F('idScript'));
                        params.set('inHabilitado', valor);

                        new Ajax.Request('habilitarMatriz.do', {
                            method: 'get',
                            contentType: 'text/xml',
                            encoding: 'ISO-8859-1',
                            parameters: params,
                            onComplete: function (originalRequest) {
                                var xmlString = originalRequest.responseText;
                                var oXml      = new ActiveXObject("microsoft.xmldom");
                                oXml.async    = false;
                                var regExp    = new RegExp ('&', 'gi') ;
                                xmlString     = xmlString.replace(regExp,"&amp;");
                                oXml.loadXML(xmlString);

                                if (oXml.selectSingleNode("/xml-fragment/severity") != null) {
                                    if(oXml.selectSingleNode("/xml-fragment/severity").text=='0'){
                                        friendlyMessage  = oXml.selectSingleNode("/xml-fragment/friendlyMessage").text;
                                        alert(friendlyMessage);
                                    }else{
                                        exceptionMessage = oXml.selectSingleNode("/xml-fragment/exceptionMessage").text;
                                        alert(exceptionMessage);
                                    }
                                }
                                if(top.frameApp.dvAnimarAguarde != null) top.frameApp.stopAnimation();
                                loadScript();
                            },
                            onException: function(t, e){
                                if(top.frameApp.dvAnimarAguarde != null) top.frameApp.stopAnimation();
                                alert("Erro[habilitarMatriz(Ajax-E)]: "+e.description+"\n"+t.transport);
                            },
                            onFailure: function(e){
                                if(top.frameApp.dvAnimarAguarde != null) top.frameApp.stopAnimation();
                                alert("Erro[habilitarMatriz(Ajax-F)]: "+e.description);
                            }
                        });
                    } catch(e) {
                        if(top.frameApp.dvAnimarAguarde != null) top.frameApp.stopAnimation();
                        alert("Erro[habilitarMatriz]: "+e.description);
                    }
                }
            }

        </script>
        <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
            <!--
                if(top.frameApp.dvAnimarAguarde != null) top.frameApp.stopAnimation();
                <logic:equal name="Form" property="inHabilitado" value="0">
                $('imgHabil').show();
                $('imgDesab').hide();
                </logic:equal>
                <logic:equal name="Form" property="inHabilitado" value="1">
                $('imgDesab').show();
                $('imgHabil').hide();
                </logic:equal>
                <logic:notEmpty name="msgError" scope="request">
                alert('<bean:write name="msgError" scope="request"/>');
                </logic:notEmpty>
            -->
        </SCRIPT>
    </netui-template:section>
    <netui-template:section name="bodySection">
    <!--acesso:controlHiddenItem nomeIdentificador="fid_ms_verpagina"-->
    <form method="post" action="" id="scriptForm" name="scriptForm" enctype="multipart/form-data" style="margin:0px;" onsubmit="return false;">
        <table width="95%" align="center" cellpadding="2" cellspacing="2" border="0">
            <tr>
                <td width="325">
                    <span style="width:80px;">Matriz:</span>
                    <html:select name="Form" property="idScript" style="width:240px;" styleClass="SELECT" onchange="loadScript(this);">
                        <option value="">-- Selecione --</option>
                        <option value="N" <logic:equal value="N" name="Form" property="idScript">selected</logic:equal>>Nova Matriz</option>
                        <html:optionsCollection name="Form" property="listaScript.itArray" value="id" label="ds"/>
                    </html:select>
                </td>
                <td align="left">
                    <img id="imgHabil" src="/FrontOfficeWeb/resources/images/bt_habilitar_nrml.gif" border="0" style="cursor:pointer;display:none;" onclick="habilitarMatriz(1);">
                    <img id="imgDesab" src="/FrontOfficeWeb/resources/images/bt_desabilitar_nrml.gif" border="0" style="cursor:pointer;display:none;" onclick="habilitarMatriz(0);">
                </td>
                <td align="right">
                    <logic:notEmpty name="Form" property="nmUsuario">
                    <div style="width:200px;">Usuario: <bean:write name="Form" property="nmUsuario"/> </div>
                    </logic:notEmpty>
                </td>
            </tr>
            <tr>
                <td>
                    <span style="width:80px;">Nome Matriz:</span>
                    <html:text name="Form" property="nmMatriz" size="40" maxlength="255"/>
                </td>
                <td align="right">
                </td>
                <td align="right">
                    <logic:notEmpty name="Form" property="nmUsuario">
                    <div style="width:200px;">Dt Alt.: <bean:write name="Form" property="dtAlteracao"/> </div>
                    </logic:notEmpty>
                </td>
            </tr>
        </table>

        <vivo:abaGrupo id="btAba" width="300" height="16" styleClass="abatexto">
            <vivo:abaItem id="bt01" onclick="abaSelected(btAba, bt01);CarregaAba(this.id);" value="Campos 1" select="S"/>
            <vivo:abaItem id="bt02" onclick="CarregaAba(this.id);" value="Campos 2"/>
            <vivo:abaItem id="bt03" onclick="CarregaAba(this.id);" value="Campos 3"/>
        </vivo:abaGrupo>

        <div style="height:330px;">
            <div id="grpCampos1" style="display:none;">
                <table width="95%" align="center" cellpadding="2" cellspacing="2">
                    <tr>
                        <td>
                            <table width="100%">
                                <tr>
                                    <td align="center">Regionais Disponíveis</td>
                                    <td>&nbsp;</td>
                                    <td align="center">Regionais Selecionadas</td>
                                </tr>
                                <tr>
                                    <td width="43%" align="center">
                                        <select name="idDispRegional" id="idDispRegional" style="width:150px;" size="9" class="SELECT" multiple>
                                            <logic:iterate id="it" name="Form" property="listaRegional.itArray" indexId="idx">
                                                <option value="<bean:write name="it" property="id"/>"><bean:write name="it" property="ds"/></option>
                                            </logic:iterate>
                                        </select>
                                    </td>
                                    <td width="14%" align="center">
                                        <img src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" style="cursor:pointer;" onclick="transfereSelecaoLista($('idDispRegional'),$('idSelRegional'),false);">
                                        <br><br>
                                        <img src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" style="cursor:pointer;" onclick="transfereSelecaoLista($('idDispRegional'),$('idSelRegional'),true);">
                                    </td>
                                    <td width="43%" align="center">
                                        <select name="idSelRegional" id="idSelRegional" style="width:150px;" size="9" class="SELECT" multiple>
                                            <logic:iterate id="it" name="Form" property="listaSelRegional.itArray" indexId="idx">
                                                <option value="<bean:write name="it" property="id"/>"><bean:write name="it" property="ds"/></option>
                                            </logic:iterate>
                                        </select>
                                    </td>
                                </tr>
                            </table>
                        </td>
                        <td>
                            <table width="100%">
                                <tr>
                                    <td align="center">Tipos de Clientes Disponíveis</td>
                                    <td>&nbsp;</td>
                                    <td align="center">Tipos de Clientes Selecionadas</td>
                                </tr>
                                <tr>
                                    <td width="43%" align="center">
                                        <select name="idDispTpCliente" id="idDispTpCliente" style="width:150px;" size="9" class="SELECT" multiple>
                                            <logic:iterate id="it" name="Form" property="listaClientes.itArray" indexId="idx">
                                                <option value="<bean:write name="it" property="id"/>"><bean:write name="it" property="ds"/></option>
                                            </logic:iterate>
                                        </select>
                                    </td>
                                    <td width="14%" align="center">
                                        <img src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" style="cursor:pointer;" onclick="transfereSelecaoLista($('idDispTpCliente'),$('idSelTpCliente'),false);">
                                        <br><br>
                                        <img src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" style="cursor:pointer;" onclick="transfereSelecaoLista($('idDispTpCliente'),$('idSelTpCliente'),true);">
                                    </td>
                                    <td width="43%" align="center">
                                        <select name="idSelTpCliente" id="idSelTpCliente" style="width:150px;" size="9" class="SELECT" multiple>
                                            <logic:iterate id="it" name="Form" property="listaSelClientes.itArray" indexId="idx">
                                                <option value="<bean:write name="it" property="id"/>"><bean:write name="it" property="ds"/></option>
                                            </logic:iterate>
                                        </select>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <table width="100%">
                                <tr>
                                    <td align="center">Tipo de Linha Disponíveis</td>
                                    <td>&nbsp;</td>
                                    <td align="center">Tipo de Linha Selecionadas</td>
                                </tr>
                                <tr>
                                    <td width="43%" align="center">
                                        <select name="idDispTpLinha" id="idDispTpLinha" style="width:150px;" size="9" class="SELECT" multiple>
                                            <logic:iterate id="it" name="Form" property="listaLinhas.itArray" indexId="idx">
                                                <option value="<bean:write name="it" property="id"/>"><bean:write name="it" property="ds"/></option>
                                            </logic:iterate>
                                        </select>
                                    </td>
                                    <td width="14%" align="center">
                                        <img src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" style="cursor:pointer;" onclick="transfereSelecaoLista($('idDispTpLinha'),$('idSelTpLinha'),false);">
                                        <br><br>
                                        <img src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" style="cursor:pointer;" onclick="transfereSelecaoLista($('idDispTpLinha'),$('idSelTpLinha'),true);">
                                    </td>
                                    <td width="43%" align="center">
                                        <select name="idSelTpLinha" id="idSelTpLinha" style="width:150px;" size="9" class="SELECT" multiple>
                                            <logic:iterate id="it" name="Form" property="listaSelLinhas.itArray" indexId="idx">
                                                <option value="<bean:write name="it" property="id"/>"><bean:write name="it" property="ds"/></option>
                                            </logic:iterate>
                                        </select>
                                    </td>
                                </tr>
                            </table>
                        </td>
                        <td>
                        </td>
                    </tr>
                </table>
            </div>
            <div id="grpCampos2" style="display:none;">
                <table width="95%" align="center" cellpadding="2" cellspacing="2">
                    <tr>
                        <td>
                            <table width="100%">
                                <tr>
                                    <td align="center">Grupos Disponíveis</td>
                                    <td>&nbsp;</td>
                                    <td align="center">Grupos Selecionadas</td>
                                </tr>
                                <tr>
                                    <td width="43%" align="center">
                                        <select name="idDispGrupos" id="idDispGrupos" style="width:150px;" size="9" class="SELECT" multiple>
                                            <logic:iterate id="it" name="Form" property="listaGrupos.itArray" indexId="idx">
                                                <option value="<bean:write name="it" property="id"/>"><bean:write name="it" property="ds"/></option>
                                            </logic:iterate>
                                        </select>
                                    </td>
                                    <td width="14%" align="center">
                                        <img src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" style="cursor:pointer;" onclick="transfereSelecaoLista($('idDispGrupos'),$('idSelGrupos'),false);">
                                        <br>
                                        <img src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" style="cursor:pointer;" onclick="transfereSelecaoLista($('idDispGrupos'),$('idSelGrupos'),true);">
                                    </td>
                                    <td width="43%" align="center">
                                        <select name="idSelGrupos" id="idSelGrupos" style="width:150px;" size="9" class="SELECT" multiple>
                                            <logic:iterate id="it" name="Form" property="listaSelGrupos.itArray" indexId="idx">
                                                <option value="<bean:write name="it" property="id"/>"><bean:write name="it" property="ds"/></option>
                                            </logic:iterate>
                                        </select>
                                    </td>
                                </tr>
                            </table>
                        </td>
                        <td>
                            <table width="100%">
                                <tr>
                                    <td align="center">Segmentações Disponíveis</td>
                                    <td>&nbsp;</td>
                                    <td align="center">Segmentações Selecionadas</td>
                                </tr>
                                <tr>
                                    <td width="43%" align="center">
                                        <select name="idDispSegmentacao" id="idDispSegmentacao" style="width:150px;" size="9" class="SELECT" multiple>
                                            <logic:iterate id="it" name="Form" property="listaSegmentacao.itArray" indexId="idx">
                                                <option value="<bean:write name="it" property="id"/>"><bean:write name="it" property="ds"/></option>
                                            </logic:iterate>
                                        </select>
                                    </td>
                                    <td width="14%" align="center">
                                        <img src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" style="cursor:pointer;" onclick="transfereSelecaoLista($('idDispSegmentacao'),$('idSelSegmentacao'),false);">
                                        <br>
                                        <img src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" style="cursor:pointer;" onclick="transfereSelecaoLista($('idDispSegmentacao'),$('idSelSegmentacao'),true);">
                                    </td>
                                    <td width="43%" align="center">
                                        <select name="idSelSegmentacao" id="idSelSegmentacao" style="width:150px;" size="9" class="SELECT" multiple>
                                            <logic:iterate id="it" name="Form" property="listaSelSegmentacao.itArray" indexId="idx">
                                                <option value="<bean:write name="it" property="id"/>"><bean:write name="it" property="ds"/></option>
                                            </logic:iterate>
                                        </select>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <table width="100%">
                                <tr>
                                    <td align="center">Ofertas Disponíveis</td>
                                    <td>&nbsp;</td>
                                    <td align="center">Ofertas Selecionados</td>
                                </tr>
                                <tr>
                                    <td width="43%" align="center">
                                        <select name="idDispOfertas" id="idDispOfertas" style="width:340px;" size="9" class="SELECT" multiple>
                                            <logic:iterate id="it" name="Form" property="listaOfertas.itArray" indexId="idx">
                                                <option value="<bean:write name="it" property="id"/>"><bean:write name="it" property="ds"/></option>
                                            </logic:iterate>
                                        </select>
                                    </td>
                                    <td width="14%" align="center">
                                        <img src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" style="border:none;cursor:pointer;" onClick="transfereSelecaoLista($('idDispOfertas'),$('idSelOfertas'),false);"/>
                                        <br><br>
                                        <img src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" style="border:none;cursor:pointer;" onClick="transfereSelecaoLista($('idDispOfertas'),$('idSelOfertas'),true);"/>
                                    </td>
                                    <td width="43%" align="center">
                                        <select name="idSelOfertas" id="idSelOfertas" style="width:340px;" size="9" class="SELECT" multiple>
                                            <logic:iterate id="it" name="Form" property="listaSelOfertas.itArray" indexId="idx">
                                                <option value="<bean:write name="it" property="id"/>"><bean:write name="it" property="ds"/></option>
                                            </logic:iterate>
                                        </select>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
            </div>
            <div id="grpCampos3" style="display:none;">
                <table width="95%" align="center" cellpadding="2" cellspacing="2">
                    <tr>
                        <td>
                            <table width="100%">
                                <tr>
                                    <td align="center">Intenções Disponíveis</td>
                                    <td>&nbsp;</td>
                                    <td align="center">Intenções Selecionados</td>
                                </tr>
                                <tr>
                                    <td width="43%" align="center">
                                        <select name="idDispIntencao" id="idDispIntencao" style="width:340px;" size="9" class="SELECT" multiple>
                                            <logic:iterate id="it" name="Form" property="listaIntencao.itArray" indexId="idx">
                                                <option value="<bean:write name="it" property="id"/>"><bean:write name="it" property="ds"/></option>
                                            </logic:iterate>
                                        </select>
                                    </td>
                                    <td width="14%" align="center">
                                        <img src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" style="cursor:pointer;" onclick="transfereSelecaoLista($('idDispIntencao'),$('idSelIntencao'),false);">
                                        <br><br>
                                        <img src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" style="cursor:pointer;" onclick="transfereSelecaoLista($('idDispIntencao'),$('idSelIntencao'),true);">
                                    </td>
                                    <td width="43%" align="center">
                                        <select name="idSelIntencao" id="idSelIntencao" style="width:340px;" size="9" class="SELECT" multiple>
                                            <logic:iterate id="it" name="Form" property="listaSelIntencao.itArray" indexId="idx">
                                                <option value="<bean:write name="it" property="id"/>"><bean:write name="it" property="ds"/></option>
                                            </logic:iterate>
                                        </select>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <table width="100%">
                                <tr>
                                    <td align="center">Destinos previstos Disponíveis</td>
                                    <td>&nbsp;</td>
                                    <td align="center">Destinos previstos Selecionados</td>
                                </tr>
                                <tr>
                                    <td width="43%" align="center">
                                        <select name="idDispDestinos" id="idDispDestinos" style="width:340px;" size="9" class="SELECT" multiple>
                                            <logic:iterate id="it" name="Form" property="listaDestinos.itArray" indexId="idx">
                                                <option value="<bean:write name="it" property="id"/>"><bean:write name="it" property="ds"/></option>
                                            </logic:iterate>
                                        </select>
                                    </td>
                                    <td width="14%" align="center">
                                        <img src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" style="cursor:pointer;" onclick="transfereSelecaoLista($('idDispDestinos'),$('idSelDestinos'),false);">
                                        <br><br>
                                        <img src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" style="cursor:pointer;" onclick="transfereSelecaoLista($('idDispDestinos'),$('idSelDestinos'),true);">
                                    </td>
                                    <td width="43%" align="center">
                                        <select name="idSelDestinos" id="idSelDestinos" style="width:340px;" size="9" class="SELECT" multiple>
                                            <logic:iterate id="it" name="Form" property="listaSelDestinos.itArray" indexId="idx">
                                                <option value="<bean:write name="it" property="id"/>"><bean:write name="it" property="ds"/></option>
                                            </logic:iterate>
                                        </select>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
        <div style="margin-top:3px;width:776px;">
            <!--acesso:controlHiddenItem nomeIdentificador="fid_ms_gravar"-->
                <img src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" onclick="gravar();" align="right" style="cursor:pointer;"/>
            <!--/acesso:controlHiddenItem-->
        </div>
        <script>
            CarregaAba('bt01');
            document.body.style.backgroundColor = '#ededed';
        </script>
    </form>
    <!--/acesso:controlHiddenItem-->
    </netui-template:section>
</netui-template:template>
