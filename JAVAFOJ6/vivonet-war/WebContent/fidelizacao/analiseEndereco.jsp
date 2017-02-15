<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>
<bean:define id="ConsultaAdimplenciaForm" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="consultaAdimplenciaForm"/>
<bean:define id="DadosClienteForm"        name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="dadosClienteForm"/>
<bean:define id="OfertaAparelhoForm"      name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="ofertaAparelhoForm"/>
<bean:define id="UFs"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="ofertaAparelhoForm.regionais"/>

<netui-template:template templatePage="/resources/jsp/templateRetencao.jsp">
    <netui-template:section name="headerSection">
        <link rel="stylesheet" type="text/css" href="/FrontOfficeWeb/resources/css/calendar.css">
        <script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/calendar.js"></script>
        <script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/vivoval.js"></script>
        <script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/fidelizacao-aparelhos-entrega.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
            <!--
                top.frameApp.oculta_div();
                top.frameApp.$("idAnime").style.display="none";
            -->
        </SCRIPT>
        <script language="javascript">
            function confirmarAprovacao() {
                var acao = "APROVAR";
                if (verificaEndereco()) {
                    if (trim(document.forms[0].dsObsAnalista.value) == "") {
                        top.frameApp.$("idAnime").style.display = "block";
                        alert('O preenchimento do campo Observa\347\365es é obrigat\363rio.');
                        top.frameApp.$("idAnime").style.display="none";
                    } else {
                        top.frameApp.$("idAnime").style.display = "block";
                        var idGrupo = '<bean:write name="idGrupo"/>';
                        var sgUF = document.forms[0].estado.options[document.forms[0].estado.selectedIndex].text;
                        document.forms[0].action = "finalizarAnaliseEndereco.do?acao="+acao+"&idAtendimento="+idAtendimento+"&nrCPF=<bean:write name="ConsultaAdimplenciaForm" property="nrDocumento"/>&sgUF="+sgUF+"&idGrupo="+idGrupo;
                        document.forms[0].submit();
                    }
                }
            }

            function confirmarReprovacao() {
                var acao = "REPROVAR";
                if (trim(document.forms[0].dsObsAnalista.value) == "") {
                    top.frameApp.$("idAnime").style.display = "block";
                    alert('O preenchimento do campo Observa\347\365es é obrigat\363rio.');
                    top.frameApp.$("idAnime").style.display="none";
                } else {
                    top.frameApp.$("idAnime").style.display = "block";
                    var idGrupo = '<bean:write name="idGrupo"/>';
                    var sgUF = document.forms[0].estado.options[document.forms[0].estado.selectedIndex].text;
                    document.forms[0].action = "finalizarAnaliseEndereco.do?acao="+acao+"&idAtendimento="+idAtendimento+"&nrCPF=<bean:write name="ConsultaAdimplenciaForm" property="nrDocumento"/>&sgUF="+sgUF+"&idGrupo="+idGrupo;
                    document.forms[0].submit();
                }
            }

            function pesquisaEndereco() {
                window.top.frameApp.showPopupTI('Pesquisa de Endereço',790,400,null,"/FrontOfficeWeb/fidelizacao/pesquisaEndereco.do?source=analiseEndereco");
            }

            verificaEndereco = function() {
                f = document.forms[0];
                //Maximo de caracteres que o SAP aceita para os campos
                maxRua    = 60;     //Rua considera a somatoria dos campos (Rua/Numero/Complemento)
                maxBairro = 35;
                maxCidade = 35;
                maxEstado = 3;
                maxCEP    = 10;

                if (f.complemento.value=="") qtdeCaracSepComplemento = 0; else qtdeCaracSepComplemento = 3;
                qtdeCaracSepNumero = 2;

                ruaLength    = trim(f.rua.value).length + trim(f.complemento.value).length + trim(f.numero.value).length + qtdeCaracSepNumero + qtdeCaracSepComplemento;
                bairroLength = trim(f.bairro.value).length;
                cidadeLength = trim(f.cidade.value).length;
                estadoLength = trim(f.estado.value).length;
                cepLength    = trim(f.cep.value).length;

                if (  (trim(f.rua.value) == ""
                      || trim(f.numero.value) == ""
                      || trim(f.cidade.value) == ""
                      || trim(f.estado.value) == ""
                      || trim(f.bairro.value) == ""
                      || trim(f.cep.value) == "")) {
                    alert('Algum dos campos obrigatórios referentes a dados de endereço está vazio.');
                    return false;

                } else if (ruaLength > maxRua) {
                    qtdeCaracteresNumeroComplemento = f.numero.value.length+f.complemento.value.length;
                    qtdePermitidaRua = maxRua - (qtdeCaracSepComplemento+qtdeCaracSepNumero+qtdeCaracteresNumeroComplemento);

                    alert("A quantidade de caracteres nos campos de endereço ultrapassa o máximo \n permitido pelo sistema legado. Correção necessária.");
                    return false;
                } else if (bairroLength > maxBairro) {
                    alert("A quantidade de caracteres no campo Bairro ultrapassa o máximo \n permitido pelo sistema legado. Correção necessária.");
                    return false;
                } else if (cidadeLength > maxCidade) {
                    alert("A quantidade de caracteres no campo Cidade ultrapassa o máximo \n permitido pelo sistema legado. Correção necessária.");
                    return false;
                } else if (estadoLength > maxEstado) {
                    alert("A quantidade de caracteres no campo Estado ultrapassa o máximo \n permitido pelo sistema legado. Correção necessária.");
                    return false;
                } else if (cepLength > maxCEP) {
                    alert("A quantidade de caracteres no campo CEP ultrapassa o máximo \n permitido pelo sistema legado. Correção necessária.");
                    return false;
                } else {
                    return true;
                }
            }

            getCPF = function(){ return onlyNumbers(window.top.frameApp.$('ddClienteDocumento').innerHTML); }
            idAtendimento = top.frameApp.idAtendimento;
        </script>
    </netui-template:section>
<netui-template:section name="bodySection">
<acesso:controlHiddenItem nomeIdentificador="fid_anaend_verpagina">

<form action="finalizarAnaliseEndereco.do" name="ofertaAparelhoForm" method="post" style="margin:0 0px 0 0px;padding:0 0px 0 0px;">
    
    <html:hidden name="ConsultaAdimplenciaForm" property="idRetencao"/>
    <html:hidden name="ConsultaAdimplenciaForm" property="nrParcelas"/>
    <html:hidden name="OfertaAparelhoForm" property="SAP"/>
    <html:hidden name="OfertaAparelhoForm" property="idAparelho"/>
    <html:hidden name="OfertaAparelhoForm" property="dsMaterial"/>

    <table cellpadding="0" cellspacing="0" style="border:1px solid #adadad; background-color:#E7E7E7; margin-bottom:5px;" width="735">
        <tr style="background-color:#fff;">
            <td style="padding:3px;" width="255">
                Cliente:&nbsp;
                <span title="<bean:write name="DadosClienteForm" property="nomeCliente"/>" style="width:215px;overflow:hidden;text-overflow:ellipsis">
                    <nobr>
                        <b><bean:write name="DadosClienteForm" property="nomeCliente"/></b>
                    </nobr>
                </span>
            </td>
            <td width="70">Modelo:</td>
            <td width="245">
                <span title="<bean:write name="ConsultaAdimplenciaForm" property="dsModelo"/>" style="width:240px;overflow:hidden;text-overflow:ellipsis">
                    <nobr>
                        <b><bean:write name="ConsultaAdimplenciaForm" property="dsModelo"/></b>
                    </nobr>
                </span>
            </td>
            <td width="95">% de desconto:</td>
            <td width="70"><b><bean:write name="ConsultaAdimplenciaForm" property="vlPercentualDesconto"/></b></td>
        </tr>
        <tr>
            <td style="padding:3px;">CPF: <b><bean:write name="ConsultaAdimplenciaForm" property="nrDocumento"/></b></td>
            <td>Cor:</td>
            <td><b><bean:write name="ConsultaAdimplenciaForm" property="nmCor" /></b></td>
            <td>Nº. de parcelas:</td>
            <td><b><bean:write name="ConsultaAdimplenciaForm" property="nrParcelas" /></b></td>
        </tr>
        <tr style="background-color:#fff;">
            <td style="padding:3px;">
                Inten&ccedil;&atilde;o Cancelamento:
                <span title="<bean:write name="ConsultaAdimplenciaForm" property="dsIntencaoCancelamento"/>" style="width:130px;overflow:hidden;text-overflow:ellipsis">
                    <nobr>
                        <b><bean:write name="ConsultaAdimplenciaForm" property="dsIntencaoCancelamento"/></b>
                    </nobr>
                </span>
            </td>
            <td>Pre&ccedil;o:</td>
            <td><b>R$<bean:write name="ConsultaAdimplenciaForm" property="vlPreco"/></b></td>
            <td>Valor da parcela:</td>
            <td><b>R$<bean:write name="ConsultaAdimplenciaForm" property="vlParcela"/></b></td>
        </tr>
        <tr>
            <td style="padding:3px;">
                Destino Previsto:
                <span title="<bean:write name="ConsultaAdimplenciaForm" property="dsDestinoPrevisto"/>" style="width:165px;overflow:hidden;text-overflow:ellipsis">
                    <nobr>
                        <b><bean:write name="ConsultaAdimplenciaForm" property="dsDestinoPrevisto"/></b>
                    </nobr>
                </span>
            </td>
            <td>Meio Pagto.:</td>
            <td>
                <span title="<bean:write name="ConsultaAdimplenciaForm" property="dsMeioPagamento"/>" style="width:240px;overflow:hidden;text-overflow:ellipsis">
                    <nobr>
                        <b><bean:write name="ConsultaAdimplenciaForm" property="dsMeioPagamento"/></b>
                    </nobr>
                </span>
            </td>
            <td>Valor c/ Desconto:</td>
            <td>
                <span title="<bean:write name="ConsultaAdimplenciaForm" property="vlPrecoComDesconto"/>" style="width:70px;overflow:hidden;text-overflow:ellipsis">
                    <nobr>
                        <b>R$<bean:write name="ConsultaAdimplenciaForm" property="vlPrecoComDesconto"/></b>
                    </nobr>
                </span>
            </td>
        </tr>
    </table>
    <vivo:quadro id="resultadoAnalise" height="190" width="735" label="Endere&ccedil;o">
        <table width="100%" cellpadding="1" cellspacing="1" border="0">
            <tr>
                <td>Motivo:</td>
                <td colspan="3"><b><bean:write name="ConsultaAdimplenciaForm" property="dsMotivoAlteracaoEndereco"/></b></td>
                <td>Login:</td>
                <td><b><bean:write name="nmLoginUsuario"/></b></td>
            </tr>
            <tr>
                <td>Rua:</td>
                <td colspan="5"><html:text name="OfertaAparelhoForm" property="rua" style="width:383px" styleId="rua"/></td>
            </tr>
            <tr>
                <td>N&uacute;mero:</td>
                <td><html:text name="OfertaAparelhoForm" styleId="numero" property="numero"/></td>
                <td>Complemento:</td>
                <td><html:text name="OfertaAparelhoForm" styleId="complemento" property="complemento"/></td>
                <td>Bairro:</td>
                <td><html:text name="OfertaAparelhoForm" styleId="bairro" property="bairro"/></td>
            </tr>
            <tr>
                <td>Cidade:</td>
                <td><html:text name="OfertaAparelhoForm" property="cidade" styleId="cidade"/></td>
                <td>Estado:</td>
                <td>
                    <html:select name="OfertaAparelhoForm" property="estado" style="width:50px" styleId="estado">
                        <html:option value=""></html:option>
                        <html:options collection="UFs" property="idUF" labelProperty="sgUF"/>
                    </html:select>
                </td>
                <td>CEP:</td>
                <td>
                    <html:text name="OfertaAparelhoForm" property="cep" style="width:80px" maxlength="9" styleId="cep"/>
                    <logic:notEqual name="ConsultaAdimplenciaForm" property="inBloquearEdicao" value="true">
                        <img id="imgLupaPesquisaEndereco" align="absmiddle" onClick="pesquisaEndereco()" src="/FrontOfficeWeb/resources/images/lupa_bg_b8d0e7.gif" style="visibility:visible;border:none;cursor:hand;"/>
                    </logic:notEqual>
                </td>
            </tr>
            <tr>
                <td colspan="6">Observa&ccedil;&otilde;es:</td>
            </tr>
            <tr>
                <td colspan="6">
                    <html:textarea name="ConsultaAdimplenciaForm" property="dsObsAnalista" style="width:710px;height:55px;" styleId="dsObsAnalista"/>
                </td>
            </tr>
            <logic:notEqual name="ConsultaAdimplenciaForm" property="inBloquearEdicao" value="true">
            <tr>
                <td colspan="6" align="right" valign="bottom" style="padding-right:10px;">
                    <acesso:controlHiddenItem nomeIdentificador="fid_anaend_reprovar">
                        <img class="button"
                        	 action="begin"
                        	 src="/FrontOfficeWeb/resources/images/bt_reprovar_nrml.gif"
							 border="0"
							 onClick="confirmarReprovacao(); return false;"/>
                    </acesso:controlHiddenItem>
                    &nbsp;
                    <acesso:controlHiddenItem nomeIdentificador="fid_anaend_aprovar">
                        <img class="button"
                        	 src="/FrontOfficeWeb/resources/images/bt_aprovar_nrml.gif"
                        	 rolloverImage="/FrontOfficeWeb/resources/images/bt_aprovar_over.gif"
                        	 border="0"
                        	 onClick="confirmarAprovacao(); return false;"/>                       
                    </acesso:controlHiddenItem>
                </td>
            </tr>
            </logic:notEqual>
        </table>
    </vivo:quadro>
    </form>
    <vivo:alert atributo="msgErro" scope="request" />
    <script>
        f = document.forms[0];
        for (i=0; i<f.estado.length; i++) {
            if (f.estado.options[i].text == '<bean:write name="OfertaAparelhoForm" property="estado"/>'){
                f.estado.options[i].selected = true;
            }
        }
        <logic:equal name="ConsultaAdimplenciaForm" property="inBloquearEdicao" value="true">
        f.rua.readOnly = true;
        f.numero.readOnly = true;
        f.complemento.readOnly = true;
        f.bairro.readOnly = true;
        f.cidade.readOnly = true;
        f.estado.disabled = true;
        f.cep.readOnly = true;
        f.dsObsAnalista.readOnly = true;
        </logic:equal>
    </script>
    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>