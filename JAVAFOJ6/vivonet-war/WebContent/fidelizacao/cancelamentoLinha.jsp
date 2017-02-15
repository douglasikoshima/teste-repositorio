<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="consultaMatrizOfertaForm" type="fidelizacao.FidelizacaoController.ConsultaMatrizOfertaForm"/>
<bean:define id="CancelarLinhasForm" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="cancelarLinhasForm" type="fidelizacao.FidelizacaoController.CancelarLinhasForm"/>

<netui-template:template templatePage="/resources/jsp/templateRetencao.jsp">
    <netui-template:section name="headerSection">
        <script type="text/javascript" language="javascript">
            onload = function() {
                if(top.frameApp.dvAnimarAguarde != null) top.frameApp.stopAnimation();
            }
            buscaLinhasByConta = function(cdConta) {
                try {
                    if (top.frameApp.dvAnimarAguarde != null) top.frameApp.startAnimation();
                    new Ajax.Request('getLinhasByConta.do', {
                        method: 'get',
                        contentType: 'text/xml',
                        encoding: 'ISO-8859-1',
                        parameters: {
                            cdConta: cdConta, limit: 18
                        },
                        onComplete: function (originalRequest) {
                            var xmlString = originalRequest.responseText;
                            var oXml      = new ActiveXObject("microsoft.xmldom");
                            oXml.async    = false;
                            var regExp    = new RegExp ('&', 'gi') ;
                            xmlString     = xmlString.replace(regExp,"&amp;");
                            oXml.loadXML(xmlString);

                            while($('nrLinhasContaSel').options.length != 0)
                                $('nrLinhasContaSel').options.remove(0);

                            nrLinhas = oXml.selectNodes("/xml-fragment/vlItem");
                            for (r = 0; r < nrLinhas.length; r++) {
                                var nrLinha = nrLinhas[r].text;
                                var texto = formatLinha(nrLinha,"(##)####-####");
                                $('nrLinhasContaSel').options[$('nrLinhasContaSel').options.length] = new Option(texto, nrLinha);
                            }
                            if(top.frameApp.dvAnimarAguarde != null) top.frameApp.stopAnimation();
                        },
                        onException: function(t, e){
                            if(top.frameApp.dvAnimarAguarde != null) top.frameApp.stopAnimation();
                            alert("Erro[buscaLinhasByConta(Ajax-E)]: "+e.description+"\n"+t.transport);
                        },
                        onFailure: function(e){
                            if(top.frameApp.dvAnimarAguarde != null) top.frameApp.stopAnimation();
                            alert("Erro[buscaLinhasByConta(Ajax-F)]: "+e.description);
                        }
                    });
                } catch(e) {
                    if(top.frameApp.dvAnimarAguarde != null) top.frameApp.stopAnimation();
                    alert("Erro[buscaLinhasByConta]: "+e.description);
                }
            }
            function formatLinha(value,format) {
                var i=0, j=0;
                value = value.replace(/\D/g,"");
                var result="";
                if(format.length < value.length)
                    value = value.substring(0,format.length -1);

                for(i=0,j=0;(i<format.length)&&(j<value.length);i++){
                    var ch = format.charAt(i);
                    if(ch=='#'){
                        result += value.charAt(j++);
                        continue;
                    }
                    result += ch;
                }
                return result;
            }
            function addLinhaSelect(){
                var x = 0;
                for(x=0; x<$('nrLinhasContaSel').options.length; x++){
                    if($('nrLinhasContaSel').options[x].selected){
                        var linha = formatLinha($('nrLinhasContaSel').options[x].value,"(##)####-####");
                        var value = $('nrContaConsulta').value+"|"+$('nrLinhasContaSel').options[x].value;
                        if(!isAdded(value, 'nrLinhasSelecionadas')){
                            $('nrLinhasSelecionadas').options[$('nrLinhasSelecionadas').options.length] = new Option(linha, value);
                        }
                    }
                }
            }
            function isAdded(value, sObjSel){
                var x = 0;
                for(x=0;x<document.getElementById(sObjSel).options.length;x++){
                    if(document.getElementById(sObjSel).options[x].value == value){
                        return true;
                    }
                }
                return false;
            }
            function removeLinhaSelec(){
                var cont=0;
                var x = 0;
                for(x=0;x<$('nrLinhasSelecionadas').options.length;x++)
                    if($('nrLinhasSelecionadas').options[x].selected) cont++;
                while(cont>0){
                    for(x=0;x<$('nrLinhasSelecionadas').options.length;x++){
                        if($('nrLinhasSelecionadas').options[x].selected){
                            $('nrLinhasSelecionadas').options[x] = null;
                            cont--;
                        }
                    }
                }
            }
            submitForm = function() {
                if (top.frameApp.dvAnimarAguarde != null) top.frameApp.startAnimation();
                if ($('nrLinhasSelecionadas')) {
                    for (var i = 0; i < $('nrLinhasSelecionadas').options.length; i++) {
                        $('nrLinhasSelecionadas').options[i].selected = true;
                    }
                }
                $('ConsultaMatrizOfertaForm').submit();
            }
            voltarOfertasCancelamento = function() {
                var f = $('ConsultaMatrizOfertaForm');
                f.action = "voltarOfertas.do?inCancelamentoLinhasAssociadas=true";
                f.submit();
                parent.parent.mostrar_div();
            }
        </script>
    </netui-template:section>
    <netui-template:section name="bodySection">
        <form name="ConsultaMatrizOfertaForm" id="ConsultaMatrizOfertaForm" action="filtroFimRetencao.do?acao=cancelar" method="post">
            <input type="hidden" name="tipoEncerramento" id="tipoEncerramento" value="2" />
            <div style="width:764px;height:331px;overflow:auto;margin-bottom:1px solid #adadad;">
                <jsp:include page="informacoesCliente.jsp"/>
                <vivo:quadro id="qdMain" height="247" width="760" label="Matriz de Oferta" scroll="no">
                    <% if (Form.getInCancelamentoLinhasAssociadas()) { %>
                        <fieldset style="width:100%;">
                            <legend style="font-weight:bold;">Linhas Associadas</legend>
                            <table width="100%" style="font-size:9px" cellpadding="3" cellspacing="3">
                                <tr>
                                    <td>Conta:</td>
                                    <td>Linhas da Conta</td>
                                    <td></td>
                                    <td>Linhas Associadas</td>
                                    <td>
                                        Tipo de palitagem
                                    </td>
                                </tr>
                                <tr>
                                    <td width="200" valign="top" nowrap>
                                        <select name="nrContaConsulta" style="width:150px;" onchange="buscaLinhasByConta(this.value);">
                                            <option value="0">--Selecione--</option>
                                            <logic:iterate id="itValue" name="CancelarLinhasForm" property="listaDadosVO.vlItemArray">
                                            <option value="<bean:write name="itValue"/>"><bean:write name="itValue"/></option>
                                            </logic:iterate>
                                        </select>
                                    </td>
                                    <td nowrap>
                                        <select id="nrLinhasContaSel" name="nrLinhasContaSel" size="7" multiple style="width:100px;"></select>
                                    </td>
                                    <td align="center" width="55" nowrap>
                                        <img src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" onclick="addLinhaSelect();"><br><br>
                                        <img src="/FrontOfficeWeb/resources/images/bt_x_nrml.gif" onclick="removeLinhaSelec();">
                                    </td>
                                    <td nowrap>
                                        <html:select id="nrLinhasSelecionadas"
                                                     name="Form"
                                                     property="nrLinhasSelecionadas"
                                                     multiple="true"
                                                     style="width:100px;"
                                                     size="7" />
                                    </td>
                                    <td valign="top" width="500">
                                        <select name="idPalitagem" id="idPalitagem" style="width:250px">
                                            <option value="0">Nova palitagem</option>
                                            <logic:iterate id="listaPalitagens" name="CancelarLinhasForm" property="listaPalitagens.admContatoFolhaVOArray">
                                            <option value="<bean:write name="listaPalitagens" property="idContato" />"><bean:write name="listaPalitagens" property="nmContato" /></option>
                                            </logic:iterate>
                                        </select>
                                    </td>
                                </tr>
                            </table>
                        </fieldset>
                        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="75"></div>
                    <% } else { %>
    
                        <table width="100%" height="175" style="font-size:9px" cellpadding="3" cellspacing="3">
                            <tr>
                                <td valign="top">Tipo de palitagem</td>
                            </tr>
                            <tr>
                                <td valign="top">
                                    <select name="idPalitagem" id="idPalitagem" style="width:250px">
                                        <option value="0">Nova palitagem</option>
                                        <logic:iterate id="listaPalitagens" name="CancelarLinhasForm" property="listaPalitagens.admContatoFolhaVOArray">
                                        <option value="<bean:write name="listaPalitagens" property="idContato" />"><bean:write name="listaPalitagens" property="nmContato" /></option>
                                        </logic:iterate>
                                    </select>
                                </td>
                            </tr>
                            <tr><td height="170">&nbsp;</td></tr>
                        </table>
                        <% } %>
                    <table width="750" cellpadding="0" cellspacing="0">
                        <tr>
                            <td>
                                <a href="javascript:voltarOfertasCancelamento()"><img src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" border="0" /></a>
                            </td>
                            <td align="center">
                                
                            </td>
                            <td align="right" valign="bottom">
                                <a href="javascript:submitForm()"><img src="/FrontOfficeWeb/resources/images/bt_ok_nrml.gif" border="0" /></a>
                            </td>
                        </tr>
                    </table>
                </vivo:quadro>
            </div>
        </form>
    </netui-template:section>
</netui-template:template>