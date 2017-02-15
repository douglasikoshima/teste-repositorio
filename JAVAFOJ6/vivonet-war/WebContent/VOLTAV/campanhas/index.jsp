<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%> 

<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<bean:define id="Form"                    name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="campanhasForm" type="VOLTAV.campanhas.CampanhasController.CampanhasForm"/>
<bean:define id="listaCampanhas"          name="Form" property="campanhaVO.campanhaVOArray" />
<bean:define id="disponiveisDDD"          name="Form" property="campanhaVO.disponiveis.DDDVOArray" />
<bean:define id="disponiveisTipoLinha"    name="Form" property="campanhaVO.disponiveis.tipoLinhaVOArray" />
<bean:define id="disponiveisSegmentacao"  name="Form" property="campanhaVO.disponiveis.segmentacaoVOArray" />
<bean:define id="selecionadosDDD"         name="Form" property="campanhaVO.selecionados.DDDVOArray" />
<bean:define id="selecionadosTipoLinha"   name="Form" property="campanhaVO.selecionados.tipoLinhaVOArray" />
<bean:define id="selecionadosSegmentacao" name="Form" property="campanhaVO.selecionados.segmentacaoVOArray" />

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">

<netui-template:setAttribute name="title" value="Manutenção de Campanhas"/>
<netui-template:setAttribute name="modulo" value="VOL/TAV"/>

<netui-template:section name="headerSection">

    <script type="text/javascript" language="javascript">
    
    
        function pesquisarCampanhas() {       
            document.forms[0].action = 'populaComboCampanha.do';
            document.forms[0].target = "_self";
            document.forms[0].idCampanha.disabled  = false;
            document.forms[0].submit();            
            
        }
    

        function transfereSelecaoLista(listaOrigem, listaDestino) {
            var i;
            for(i = 0; i<listaOrigem.options.length; i++)
                if(listaOrigem.options[i].selected)
                    listaDestino.options[listaDestino.options.length] = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);
            for(i = listaOrigem.options.length-1; i>=0; i--)
                if(listaOrigem.options[i].selected)
                    listaOrigem.options[i] = null;
            return false;
        }

        function transfereSelecaoListaTodos(listaOrigem, listaDestino) {
            var i;
            for(i = 0; i<listaOrigem.options.length; i++)
                listaDestino.options[listaDestino.options.length] = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);
            for(i = listaOrigem.options.length-1; i>=0; i--)
                listaOrigem.options[i] = null;
            return false;
        }

        function limparCampos() {
            transfereSelecaoListaTodos($('selecionadosDDD'), $('disponiveisDDD'));
            transfereSelecaoListaTodos($('selecionadosTipoLinha'), $('disponiveisTipoLinha'));
            transfereSelecaoListaTodos($('selecionadosSegmentacao'), $('disponiveisSegmentacao'));
        }

        function pesquisar(flBtPesquisar) {
            var i;
            var f = document.forms[0];
            f.action = "pesquisaCampanhas.do";
            f.target = "hiddenFrame";

            if ($F('idCampanha') != "") {
                limparCampos();
                if (top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation(); 
                f.submit();
            } else if (flBtPesquisar) {
    
                if ($F('tpPessoa') == "") {
                    alert("Por favor, selecione o Tipo de Cliente.");
                    return false;
                }           
    
                if ($F('tpCampanha') == "") {
                    alert("Por favor, selecione o tipo de campanha.");
                    return false;
                }

                if ($F('idCampanha') == "" 
                    && $('selecionadosDDD').length == 0
                    && $('selecionadosTipoLinha').length == 0
                    && $('selecionadosSegmentacao').length == 0) {
                    transfereSelecaoListaTodos($('disponiveisDDD'), $('selecionadosDDD'));
                    transfereSelecaoListaTodos($('disponiveisTipoLinha'), $('selecionadosTipoLinha'));
                    transfereSelecaoListaTodos($('disponiveisSegmentacao'), $('selecionadosSegmentacao'));
                }

                if ($('selecionadosDDD').length == 0
                    && $('selecionadosTipoLinha').length == 0
                    && $('selecionadosSegmentacao').length == 0) {
                    alert("Por favor, selecione ao menos um filtro para a realização de pesquisa.");
                } else {
                    for (i=0;i<$('selecionadosDDD').length;i++)$('selecionadosDDD').options[i].selected = true;
                    for (i=0;i<$('selecionadosTipoLinha').length;i++)$('selecionadosTipoLinha').options[i].selected = true;
                    for (i=0;i<$('selecionadosSegmentacao').length;i++)$('selecionadosSegmentacao').options[i].selected = true;
                    if (top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
                    f.submit();
                }
            }
        }

        function excluirCampanha(idCampanha, idx) {
            idx = parseInt(idx);
            if (confirm("Deseja realmente excluir esta campanha?")) {
                if (top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation(); 
                new Ajax.Request('excluirCampanha.do', {
                    method: 'POST',
                    parameters: {
                        idCampanha: idCampanha, limit: 20
                    },
                    contentType: 'application/x-www-form-urlencoded',
                    onComplete: function (originalRequest) {
                        var oXml   = new ActiveXObject("microsoft.xmldom");
                        oXml.async = false;
                        var regExp = new RegExp ('&', 'gi') ;
                        xmlString  = originalRequest.responseText;
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
                            var c = 0;
                            var trExc = $('divSearchResults').select('[class="classListaCampanhas"]')[idx];
                            while ((trExc == undefined || trExc == null) && c < 3) {
                                idx = idx-1;
                                c++;
                                trExc = $('divSearchResults').select('[class="classListaCampanhas"]')[idx];
                            }
                            removeElement(trExc);

                            if ($F('idCampanha') != "") {
                                for (i=0; i<$('idCampanha').options.length;i++) {
                                    if ($('idCampanha').options[i].value == idCampanha) {
                                        $('idCampanha').options[i] = null;
                                    }
                                }
                            }

                            alert("Campanha excluída com sucesso.");
                        }
                        if( window.top.frameApp.dvAnimarAguarde != null ) window.top.frameApp.stopAnimation();
                    }, onException: function(e,msg) {
                        alert("Ocorreu um problema durante exclusão da campanha.");
                        if( window.top.frameApp.dvAnimarAguarde != null ) window.top.frameApp.stopAnimation();
                    }
                });
            }
        }

        function alterarCampanha(idCampanha) {

            if (top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation(); 
            var params = "?idCampanha="+idCampanha;
            $('divInclusaoAlteracao').style.display = "block";
            $('iframeInclusaoAlteracao').src = "beginCadastroAlteracaoCampanha.do"+params;
        }

        function incluirCampanha(idCampanha) {
            if (top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation(); 
            var params = "?idCampanha=";
            $('divInclusaoAlteracao').style.display = "block";
            $('iframeInclusaoAlteracao').src = "beginCadastroAlteracaoCampanha.do"+params;
        }

    </script>
    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/vivoval.js" ></script>
    
</netui-template:section>

<netui-template:section name="bodySection">

<jsp:include page="/resources/menus/MenuPrincipal.jsp" />
<div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>

<vivo:sessao id="qdMain" height="468" width="790" label="Campanhas" operacoes="Manutenção" scroll="no">

    <acesso:controlHiddenItem nomeIdentificador="voltav_manutcampanhas_verpagina">

    <form action="begin.do" enctype="multipart/form-data" method="post" onSubmit="return false" name="campanhasForm">

    <table cellpadding="0" cellspacing="1" style="margin:10px;">
        <tr>
            <td colspan="6" valign="top" height="30">
                <strong>Tipo de Cliente:</strong>
                <html:select name="Form" property="tpPessoa" style="margin-left:50px;width:300px;">
                    <html:option value="">-- Selecione --</html:option>
                    <html:option value="1">Pessoa Fisica</html:option>
                    <html:option value="2">Pessoa Juridica</html:option>
                </html:select>
            </td>
        </tr>       
        <tr>
            <td colspan="6" valign="top" height="30">
                <strong>Tipo de Campanha:</strong>
                <html:select name="Form" property="tpCampanha" style="margin-left:30px;width:300px" onchange="pesquisarCampanhas()">
                    <html:option value="">-- Selecione --</html:option>
                    <html:option value="0">Todos</html:option>
                    <html:option value="1">Promo&ccedil;&atilde;o</html:option>
                    <html:option value="2">Campanha Parceiro</html:option>                    
                </html:select>
            </td>
        </tr>    
        <tr>
            <td colspan="6" valign="top" height="30">
                <strong>Campanhas:</strong>
                <html:select name="Form" property="idCampanha" style="margin-left:67px;width:300px" onchange="pesquisar(false)">
                    <html:option value="">-- Selecione --</html:option>
                    <html:options collection="combo" property="idCampanha" labelProperty="nmCampanha"/>
                </html:select>
            </td>
        </tr>
        <tr>
            <td width="100" colspan="3">
                <strong>DDD:</strong>
            </td>
            <td width="100" colspan="3">
                <strong>Tipo de Linha:</strong>
            </td>
        </tr>
        <tr>
            <td>
                <html:select name="Form" property="disponiveisDDD" multiple="true" size="4" style="width:130px">
                    <html:options collection="disponiveisDDD" property="idDDD" labelProperty="dsDDD"/>
                </html:select>
            </td>
            <td>
                <img hspace="10" id="btRightSimp" onClick="$('idCampanha').selectedIndex=0;transfereSelecaoLista($('disponiveisDDD'), $('selecionadosDDD')); return false" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_over.gif'" style="border:none;cursor:hand;"/><br><br>
                <img hspace="10" id="btLeftSimp" onClick="transfereSelecaoLista($('selecionadosDDD'), $('disponiveisDDD')); return false" src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_over.gif'" style="border:none;cursor:hand;"/>
            </td>
            <td width="160">
                <html:select name="Form" property="selecionadosDDD" multiple="true" size="4" style="width:130px">
                    <html:options collection="selecionadosDDD" property="idDDD" labelProperty="dsDDD"/>
                </html:select>
            </td>
            <td>
                <html:select name="Form" property="disponiveisTipoLinha" multiple="true" size="4" style="width:130px">
                    <html:options collection="disponiveisTipoLinha" property="id" labelProperty="descricao"/>
                </html:select>
            </td>
            <td>
                <img hspace="10" id="btRightSimp" onClick="$('idCampanha').selectedIndex=0;transfereSelecaoLista($('disponiveisTipoLinha'), $('selecionadosTipoLinha')); return false" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_over.gif'" style="border:none;cursor:hand;"/><br><br>
                <img hspace="10" id="btLeftSimp" onClick="transfereSelecaoLista($('selecionadosTipoLinha'), $('disponiveisTipoLinha')); return false" src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_over.gif'" style="border:none;cursor:hand;"/>
            </td>
            <td>
                <html:select name="Form" property="selecionadosTipoLinha" multiple="true" size="4" style="width:130px">
                    <html:options collection="selecionadosTipoLinha" property="id" labelProperty="descricao"/>
                </html:select>
            </td>
        </tr>
        <tr>
            <td width="100" colspan="3" style="margin-top:10px;">
                <strong>Segmenta&ccedil;&atilde;o:</strong>
            </td>
        </tr>
        <tr>
            <td>
                <html:select name="Form" property="disponiveisSegmentacao" multiple="true" size="4" style="width:130px">
                    <html:options collection="disponiveisSegmentacao" property="idSegmentacao" labelProperty="descricao"/>
                </html:select>
            </td>
            <td>
                <img hspace="10" id="btRightSimp" onClick="$('idCampanha').selectedIndex=0;transfereSelecaoLista($('disponiveisSegmentacao'), $('selecionadosSegmentacao')); return false" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_over.gif'" style="border:none;cursor:hand;"/><br><br>
                <img hspace="10" id="btLeftSimp" onClick="transfereSelecaoLista($('selecionadosSegmentacao'), $('disponiveisSegmentacao')); return false" src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_over.gif'" style="border:none;cursor:hand;"/>
            </td>
            <td width="130">
                <html:select name="Form" property="selecionadosSegmentacao" multiple="true" size="4" style="width:130px">
                    <html:options collection="selecionadosSegmentacao" property="idSegmentacao" labelProperty="descricao"/>
                </html:select>
            </td>
            <td colspan="3" valign="bottom" align="right" style="padding-right:20px;">
                <img id="btIncluir"
                                   value="Incluir"
                                   src="/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif"
                                   rolloverImage="/FrontOfficeWeb/resources/images/bt_incluir_over.gif"
                                   style="border:none;margin-left:4px;"
                                   onMouseUp="incluirCampanha();return false" />
                <img id="btLimpar"
                                   value="Limpar"
                                   src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif"
                                   rolloverImage="/FrontOfficeWeb/resources/images/bt_limpar_over.gif"
                                   style="border:none;margin-left:4px;"
                                   onMouseUp="limparCampos()" />
                <img id="btPesquisar"
                                   value="Pesquisar"
                                   src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif"
                                   rolloverImage="/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif"
                                   style="border:none;margin-left:4px;"
                                   onMouseUp="pesquisar(true)" />
            </td>
        </tr>
        <tr>
            <td valign="top" colspan="6" style="padding-top:15px;">
                <div id="divSearchResults" style="display:block;width:733px;height:210px">
                    <vivo:tbTable selectable="true" layoutWidth="733" layoutHeight="210" tableWidth="733" styleId="tbCampanha" sortable="true">
                        <vivo:tbHeader>
                            <vivo:tbHeaderColumn align="left" width="70%" type="string">Campanha</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="left" width="20%" type="string">Validade</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="left" width="5%" type="string"></vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="left" width="5%" type="string"></vivo:tbHeaderColumn>
                        </vivo:tbHeader>
                        <vivo:tbRows>
                            <vivo:tbRow key="campanha">
                                <vivo:tbRowColumn></vivo:tbRowColumn>
                                <vivo:tbRowColumn></vivo:tbRowColumn>
                                <vivo:tbRowColumn>
                                    <img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" style="cursor:pointer;" />
                                </vivo:tbRowColumn>
                                <vivo:tbRowColumn>
                                    <img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" style="cursor:pointer;" />
                                </vivo:tbRowColumn>
                            </vivo:tbRow>
                        </vivo:tbRows>
                    </vivo:tbTable>
                </div>
            </td>
        </tr>
    </table>

    </form>
    
    <iframe name="hiddenFrame" id="hiddenFrame" src="" width="0" height="0" marginheight="0" marginwidth="0" frameborder="0" style="display:none;"></iframe>

    </acesso:controlHiddenItem>

</vivo:sessao>

<vivo:alert atributo="msgStatus" scope="request"/>

<vivo:quadroFlutuante id="divInclusaoAlteracao"
                      idIframe="iframeInclusaoAlteracao"
                      width="775"
                      height="480"
                      spacesTop="65"
                      spacesLeft="15"
                      display="none"
                      url="<%=request.getContextPath()%>"
                      label="Cadastro/Alteração de Campanha Promocional"
                      onclick="return false;"/>

</netui-template:section>

</netui-template:template>