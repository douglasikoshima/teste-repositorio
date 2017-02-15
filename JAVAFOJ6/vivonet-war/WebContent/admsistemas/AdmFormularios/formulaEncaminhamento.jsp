<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>

<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<bean:define id="Form"
             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"
             property="regrasEncaminhamentoForm"
             type="admsistemas.AdmFormularios.formBeans.RegrasEncaminhamentoForm" />

<bean:define id="RegionaisAssociadas"    name="Form" property="listaRegionaisSelecionadas" />
<bean:define id="CarteirasAssociadas"    name="Form" property="listaCarteirasSelecionadas" />
<bean:define id="SegmentacoesAssociadas" name="Form" property="listaSegmentacoesSelecionadas" />

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
<netui-template:setAttribute name="title" value="Administração de Workflow" />
<netui-template:setAttribute name="modulo" value="Administração de Sistemas" />

<netui-template:section name="headerSection">
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/xml2json.js"></script>
    <script type="text/javascript">
        if(self.$('dvAnimarAguarde'))self.startAnimation();
        var nmRegraEncaminhamentoOriginal = '<bean:write name="Form" property="regraEncaminhamento.nmRegraEncaminhamento" />';
        onload = function() {
            $('nmRegraEncaminhamento').readOnly = <%= "alteracao".equals(Form.getDsOperacao()) ? "true" : "false" %>;
            CarregaAba('btx01');
            if(self.$('dvAnimarAguarde'))self.stopAnimation();
        }
        voltar = function() {
            if(self.$('dvAnimarAguarde'))self.startAnimation();
            <logic:notEqual name="Form" property="idFormulario" value="">
            self.location.href = "/FrontOfficeWeb/admsistemas/AdmFormularios/loadCfgFormularioAlterar.do?idFormulario=<bean:write name="Form" property="idFormulario" />";
            </logic:notEqual>
            <logic:equal name="Form" property="idFormulario" value="">
            self.location.href = "/FrontOfficeWeb/admsistemas/AdmFormularios/loadCfgFormulario.do";
            </logic:equal>
        }
        CarregaAba = function(aba) {
            var htmlContent;
            if (aba == 'btx01') {
                $('divContentFiltros').show();
                $('divContentRegionais').hide();
            } else if (aba == 'btx02') {
                $('divContentRegionais').show();
                $('divContentFiltros').hide();
            }
        }
        gravar = function() {
            // Aba Filtros de Visualização
            if($('btx01').hasClassName('abaSelected')) {
                if (validar('filtros')) {
                    abaSelected($('btxAba'), btx02);
                    CarregaAba('btx02');
                }
            }
            // Aba Regionais
            else {
                if (validar('filtros')) {
                    if (validar('regionais')) {
                        checkDuplicidadeFormularEncaminhamento();
                    }
                }
            }
        }
        validar = function(tpValidacao) {
            if (tpValidacao == 'filtros') {
                if ($F('nmRegraEncaminhamento').blank()) {
                    $('nmRegraEncaminhamento').activate();
                    $('nmRegraEncaminhamento').focus();
                    alert('Por favor, digite o nome da nova fórmula.');
                    return false;

                // TODO: Inserir aqui validação de duplicidade de nome de fórmula

                } else if ($('segmentacoesSelecionadas').options.length == 0) {
                    alert('Por favor, selecione ao menos uma segmentação.');
                    return false;
                } else if ($('carteirasSelecionadas').options.length == 0) {
                    alert('Por favor, selecione ao menos uma carteira.');
                    return false;
                } else if ($('idDestino').selectedIndex == 0) {
                    alert('Por favor, selecione o destino de encaminhamento.');
                    return false;
                }
                return true;
            } else if (tpValidacao == 'regionais') {
                if ($('regionaisSelecionadas').options.length == 0) {
                    alert('Por favor, selecione ao menos uma regional.');
                    return false;
                }
                return true;
            }
        }
        transfereSelecaoLista = function(listaOrigem, listaDestino) {
            if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();
            var i;
            var c = 0;
            var arrayTransferidos = new Array();
            var inVerificaDuplicidade = true;
            for(i = 0; i < listaOrigem.options.length; i++) {
                if (listaOrigem.options[i].selected) {
                    if (inVerificaDuplicidade) {
                        if (!valueExistsInSelect(listaDestino, listaOrigem.options[i].value)) {
                            listaDestino.options[listaDestino.options.length] = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);
                            arrayTransferidos[c] = i;
                            c++;
                        }
                    } else {
                        if (!valueExistsInSelect(listaDestino, listaOrigem.options[i].value)) {
                            listaDestino.options[listaDestino.options.length] = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);
                        }
                        arrayTransferidos[c] = i;
                        c++;
                    }
                }
            }
            if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
        }
        removeSelecaoLista = function(listaSelecionada) {
            var i;
            for(i = listaSelecionada.options.length-1; i>=0; i--)
                if(listaSelecionada.options[i].selected) listaSelecionada.options[i] = null;
        }
        checkDuplicidadeFormularEncaminhamento = function() {
            new Ajax.Request('/FrontOfficeWeb/admsistemas/AdmFormularios/checkDuplicidadeFormularEncaminhamento.do', {
                parameters: {
                    'nmFormulaEncaminhamento' : $F('nmRegraEncaminhamento').strip()
                },
                asynchronous: false,
                method: 'get',
                onSuccess: function(e) {
                    if (e.responseText != '') {
                        var dom = parseXml(e.responseText);
                        var jsonString = xml2json(dom, '');
                        var jsonObj = jsonString.evalJSON();
                        if (jsonObj.formulaExists == 'true' && (nmRegraEncaminhamentoOriginal == '' || nmRegraEncaminhamentoOriginal != $F('nmRegraEncaminhamento').strip())) {
                            alert('A fórmula ' + $F('nmRegraEncaminhamento').strip() + ' já existe.\nPor favor, escolha outro nome para a fórmula.');
                            $('nmRegraEncaminhamento').focus();
                            $('nmRegraEncaminhamento').activate();
                        } else {
                            if(self.$('dvAnimarAguarde'))self.stopAnimation();
                            selectAllOptions($('segmentacoesSelecionadas'));
                            selectAllOptions($('carteirasSelecionadas'));
                            selectAllOptions($('regionaisSelecionadas'));
                            $('saveFormulaEncaminhamento').action += '?operacao=<%=Form.getDsOperacao()%>';
                            $('saveFormulaEncaminhamento').submit();
                        }
                    }
                }, onCreate: function() {
                    if(self.$('dvAnimarAguarde'))self.startAnimation();
                }, onComplete: function() {
                    if(self.$('dvAnimarAguarde'))self.stopAnimation();
                }, onException: function(e) {
                }, on406: function(e) {
                }
            });
        }
    </script>
    <style type="text/css">
        form {
            margin:0;
            padding:0;
        }
        #formulaContent {
            border:1px solid #ccc;
            height:385px;
            width:777px;
            background:#ededed;
            padding:10px;
        }
        #formulaContent .contentHolder {

        }
        .hiddenContent {
            display:none;
        }
        #formulaContent img {
            cursor:pointer;
        }
        #formulaContentButtons {
            height:15px;
            text-align:right;
        }
        #formulaContent select {
            width:330px;
            height:100px;
        }
        #formulaContent td {
            text-align:center;
        }
        #formulaContent .controlButtons {
            margin:0 5px 0 5px;
        }
    </style>
</netui-template:section>

<netui-template:section name="bodySection">

    <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
    <div style="height:5px;width:10px;display:block;font-size:1px;"></div>

    <html:form action="/admsistemas/AdmFormularios/saveFormulaEncaminhamento.do" styleId="saveFormulaEncaminhamento">
    <html:hidden name="Form" property="regraEncaminhamento.idRegraEncaminhamento" styleId="idRegraEncaminhamento" />
    <html:hidden name="Form" property="idFormulario" styleId="idFormulario" />

    <% String operacao = "alteracao".equals(Form.getDsOperacao()) ? "Editar" : "Criar"; %>
    <vivo:sessao width="790" height="470" id="qdoManutencaoFormularios" label="Manutenção de formulários" operacoes='<%= operacao + " Fórmula"%>'>

        <div style="padding:10px 0 10px 0">
            <label for="nmRegraEncaminhamento">Nome:</label>
            <html:text name="Form" property="regraEncaminhamento.nmRegraEncaminhamento" styleId="nmRegraEncaminhamento" style="width:300px;" />
        </div>

        <div id="formulaTabsHolder">
            <vivo:abaGrupo id="btxAba" width="777" height="10px" styleClass="abatexto">
                <vivo:abaItem id="btx01" onclick="abaSelected(btxAba, btx01);CarregaAba(this.id);" value="Filtros para Visualização" select="S"/>
                <vivo:abaItem id="btx02" onclick="abaSelected(btxAba, btx02);CarregaAba(this.id);" value="Regionais"/>
            </vivo:abaGrupo>
            <div id="formulaContent">
                <div id="divContentFiltros" class="contentHolder">
                    <table width="100%">
                        <tr>
                            <td>Segmentações Existentes</td>
                            <td></td>
                            <td>Segmentações Associadas</td>
                        </tr>
                        <tr>
                            <td>
                                <select name="listaSegmentacoes" id="listaSegmentacoes" class="camposPermissao" multiple="true">
                                    <logic:iterate id="listaSegmentacoes"
                                                   name="Form"
                                                   property="filtrosListas.segmentacaoVOArray"
                                                   type="br.com.vivo.fo.admsistemas.vo.SegmentacaoVODocument.SegmentacaoVO">
                                    <option value="<bean:write name="listaSegmentacoes" property="idSegmentacao" format="#" />">
                                        <bean:write name="listaSegmentacoes" property="descricao" />
                                    </option>
                                    </logic:iterate>
                                </select>
                            </td>
                            <td>
                                <img class="button controlButtons"
                                	 id="btRightSegmentacoes"
                                     onmouseup="transfereSelecaoLista($('listaSegmentacoes'), $('segmentacoesSelecionadas'));"
                                     src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif"
                                     border="0" /><br/><br/>
                                <img class="button controlButtons"
                                	 id="btLeftSegmentacoes"
                                     onmouseup="removeSelecaoLista($('segmentacoesSelecionadas'));"
                                     src="/FrontOfficeWeb/resources/images/bt_x_nrml.gif"
                                     border="0" />
                            </td>
                            <td>
                                <html:select name="Form" property="segmentacoesSelecionadas" styleId="segmentacoesSelecionadas" multiple="true">
                                    <html:options collection="SegmentacoesAssociadas"
                                                  property="idSegmentacao"
                                                  labelProperty="descricao"/>
                                </html:select>
                            </td>
                        </tr>
                        <tr>
                            <td>Carteiras Existentes</td>
                            <td></td>
                            <td>Carteiras Associadas</td>
                        </tr>
                        <tr>
                            <td>
                                <select name="listaCarteiras" id="listaCarteiras" class="camposPermissao" multiple="true">
                                    <logic:iterate id="listaCarteiras" name="Form" property="filtrosListas.carterizacaoVOArray" type="br.com.vivo.fo.admsistemas.vo.CarterizacaoVODocument.CarterizacaoVO">
                                        <option value="<bean:write name="listaCarteiras" property="idTipoCarteira" format="#" />">
                                            <bean:write name="listaCarteiras" property="descricao" />
                                        </option>
                                    </logic:iterate>
                                </select>
                            </td>
                            <td>
                                <img class="button controlButtons"
                                	 id="btRightCarteiras"
                                     onmouseup="transfereSelecaoLista($('listaCarteiras'), $('carteirasSelecionadas'));"
                                     src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif"
                                     border="0" /><br/><br/>
                                <img id="btLeftCarteiras"
                                     onmouseup="removeSelecaoLista($('carteirasSelecionadas'));"
                                     src="/FrontOfficeWeb/resources/images/bt_x_nrml.gif"
                                     border="0"
                                     class="button controlButtons" />
                            </td>
                            <td>
                                <html:select name="Form" property="carteirasSelecionadas" styleId="carteirasSelecionadas" multiple="true">
                                    <html:options collection="CarteirasAssociadas"
                                                  property="idTipoCarteira"
                                                  labelProperty="descricao"/>
                                </html:select>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="3" style="text-align:left;vertical-align:middle;padding-top:20px;">
                                <strong>Destinos existentes: </strong>
                                <html:select name="Form" property="regraEncaminhamento.destinoVO.idDestino" styleId="idDestino" style="width:200px;margin-left:5px;">
                                    <option value="">-- Selecione --</option>
                                    <logic:iterate id="listaDestinos"
                                                   name="Form"
                                                   property="filtrosListas.destinoVOArray"
                                                   type="br.com.vivo.fo.admsistemas.vo.DestinoVODocument.DestinoVO">
                                        <option value="<bean:write name="listaDestinos" property="idDestino" format="#" />" <%=listaDestinos.getIdDestino().equals(Form.getRegraEncaminhamento().getDestinoVO().getIdDestino())?"selected":""%>>
                                            <bean:write name="listaDestinos" property="dsDestino" />
                                        </option>
                                    </logic:iterate>
                                </html:select>
                            </td>
                        </tr>
                    </table>
                </div>
                <div id="divContentRegionais" class="contentHolder">
                    <table width="100%">
                        <tr>
                            <td>Regionais Existentes</td>
                            <td></td>
                            <td>Regionais Associadas</td>
                        </tr>
                        <tr>
                            <td>
                                <select name="listaRegionais" id="listaRegionais" class="camposPermissao" multiple="true">
                                    <logic:iterate id="listaRegionais"
                                                   name="Form"
                                                   property="filtrosListas.WFRegionalVOArray"
                                                   type="br.com.vivo.fo.workflow.vo.WFRegionalVODocument.WFRegionalVO">
                                    <option value="<bean:write name="listaRegionais" property="idRegional" format="#" />">
                                        <bean:write name="listaRegionais" property="dsRegional" />
                                    </option>
                                    </logic:iterate>
                                </select>
                            </td>
                            <td>
                                <img id="btRightRegionais"
                                     onmouseup="transfereSelecaoLista($('listaRegionais'), $('regionaisSelecionadas'));"
                                     src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif"
                                     class="button controlButtons"
                                     border="0" /><br/><br/>
                                <img id="btLeftRegionais"
                                     onmouseup="removeSelecaoLista($('regionaisSelecionadas'));"
                                     src="/FrontOfficeWeb/resources/images/bt_x_nrml.gif"
                                     class="button controlButtons"
                                     border="0" />
                            </td>
                            <td>
                                <html:select name="Form" property="regionaisSelecionadas" styleId="regionaisSelecionadas" multiple="true">
                                    <html:options collection="RegionaisAssociadas"
                                                  property="idRegional"
                                                  labelProperty="dsRegional"/>
                                </html:select>
                            </td>
                        </tr>
                    </table>
                </div>

                <div id="formulaContentButtons">
                    <img style="margin-top:7px;" src="<%=request.getContextPath()%>/resources/images/bt_gravar_nrml.gif" onmouseup="gravar()" />
                </div>
            </div>
            <a href="javascript:voltar()"><img style="margin-top:7px;" src="<%=request.getContextPath()%>/resources/images/bt_voltar_nrml.gif" /></a>
        </div>

    </vivo:sessao>

    <vivo:alert atributo="msgStatus" scope="request"/>

    </html:form>

</netui-template:section>
</netui-template:template>