<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.cliente.vo.ParametrosVODocument.ParametrosVO"%>
<%@ page import="br.com.vivo.fo.commons.utils.StringUtilStatic"%>
<%@ page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<netui-data:declarePageInput name="piLupaCliente"  type="br.com.vivo.fo.cliente.vo.LupaClienteVODocument.LupaClienteVO"/>
<netui-data:declarePageInput name="piImpedimentos" type="br.com.vivo.fo.cliente.vo.ImpedimentoVODocument.ImpedimentoVO[]"/>

<% ParametrosVO parametros = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)); %>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formID"/>

<html>
    <head>
        <html:hidden name="Form" property="idPessoaCliente" styleId="idPessoaCliente"/>
        <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/formatPhoneNumber.js"></script>
        <script type="text/javascript">
            <!--
            CarregaAba = function(nome) {
                var dyniframe = document.getElementById("fraSubAbas");
                parent.mostrar_div();
                var pagina = "";
                if(nome=="btx01") pagina = "loadDocumento.do?idPessoaCliente="+document.getElementById("idPessoaCliente").value;
                if(nome=="btx02") pagina = "loadEndereco.do?idPessoaCliente="+document.getElementById("idPessoaCliente").value;
                if(nome=="btx03") pagina = "loadContato.do?idPessoaCliente="+document.getElementById("idPessoaCliente").value;
                if(nome=="btx04") pagina = "loadPontuacao.do?idPessoaCliente="+document.getElementById("idPessoaCliente").value;
                dyniframe.src = pagina;
            }

            dadosComportamentais = function() {
                dvProfile.style.display = '';
                document.getElementById("dv_dvProfile").innerHTML = 'Customer Profile';
                document.forms[0].target = "ifrmProfile";
                document.forms[0].action="loadDadosProfiler.do";
                parent.mostrar_div();
                document.forms[0].method = "POST";
                document.forms[0].submit();
            }

            goCadastroProspect = function() {
                if (confirm('Caso um novo prospect seja cadastrado, a linha <%=ConstantesCRM.formatPhoneNumber(parametros.getNrLinha())%>\nperderá seu relacionamento com o prospect atual.\n\nDeseja prosseguir?')) {
                    if (top.frameApp.dvAnimarAguarde != null) top.frameApp.startAnimation();
                    top.frameApp.$('wrapper_divPopupTI').style.marginTop = '37px';
                    top.frameApp.$('title_divPopupTI').update('Cadastrar Prospect');
                    top.frameApp.$('iframePopupTI').height = 525;
                    top.frameApp.$('iframePopupTI').src = 'irCadastroProspect.do?screenPop=true&nrLinhaProspect=<%=parametros.getNrLinha()%>';
                }
            }

            permissoes = function() {
                document.forms[0].action="loadPermissoes.do";
                document.forms[0].method = "POST";
                document.forms[0].submit();
            }

            onload = function() {
                CarregaAba("btx01");
            }
            // -->
        </script>
    </head>
    <body style="margin-left:5px; margin-right:5px; margin-top:5px;">
    <form>
    <input type="hidden" value="" id="inReloadTOTAL" name="inReloadTOTAL">
    <input type="hidden" value="<%=((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getNrLinha()%>" id="nrLinhaReload" name="nrLinhaReload">
    <acesso:controlHiddenItem nomeIdentificador="cli_lus_verpagina">
        <table width="777" class="tbl_bgBlue" cellpadding="0" cellspacing="0">
            <tr>
                <td colspan="6" class="tbl_tituloBG" style="text-indent:5px;">Dados</td>
            </tr>
            <tr class="tblDinamica_linhaimpar">
                <td width="82" align="right"><b>Nome:</b></td>
                <td colspan="3"><netui:label id="" value="{pageContext.piLupaCliente.dadosLupaCliente.nmNome}"/> </td>
                <td width="94" align="right"><b>Desde:</b></td>
                <td width="171"><netui:label id="" value="{pageContext.piLupaCliente.dadosLupaCliente.dtClienteDesde}"/></td>
            </tr>
            <tr class="tblDinamica_linhapar">
                <td align="right"><b>Estado Civil:</b></td>
                <td width="223"><netui:label id="" value="{pageContext.piLupaCliente.dadosLupaCliente.dsEstadoCivil}"/></td>
                <td width="57" align="right"></td>
                <td width="138"><netui:label id="" value=""/></td>
                <td align="right"><b>Data Nasc.:</b></td>
                <td><netui:label id="" value="{pageContext.piLupaCliente.dadosLupaCliente.dtNascimento}"/></td>
            </tr>
        </table>
        <div><img id="imgRefDown" src="<%=request.getContextPath()%>/resources/images/transp.gif" width="1" height="5"></div>
        <table width="768" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td>
                    <vivo:abaGrupo id="btxAba" width="777" height="10px" styleClass="abatexto">
                        <acesso:controlHiddenItem nomeIdentificador="cli_lus_abadoc">
                        <vivo:abaItem id="btx01" onclick="abaSelected(btxAba, btx01);CarregaAba(this.id);" value="Documento" select="S"/>
                        </acesso:controlHiddenItem>
                        <acesso:controlHiddenItem nomeIdentificador="cli_lus_abaend">
                        <vivo:abaItem id="btx02" onclick="abaSelected(btxAba, btx02);CarregaAba(this.id);" value="Endereço "/>
                        </acesso:controlHiddenItem>
                        <acesso:controlHiddenItem nomeIdentificador="cli_lus_abacont">
                        <vivo:abaItem id="btx03" onclick="abaSelected(btxAba, btx03);CarregaAba(this.id);" value="Contato  "/>
                        </acesso:controlHiddenItem>
                    </vivo:abaGrupo>
                </td>
            </tr>
        </table>
        <table width="777" class="tbl_bgBlue" border="0" cellpadding="1" cellspacing="2">
            <tr>
                <td colspan="4" valign="top">
                    <iframe name="fraSubAbas" id="fraSubAbas" width="100%" height="235" frameborder="0" src="" scrolling="no"></iframe>
                </td>
            </tr>
        </table>
        <div><img id="imgRefDown" src="<%=request.getContextPath()%>/resources/images/transp.gif" width="1" height="5"></div>
        <table width="777" class="tbl_bgGray" height="25">
            <tr>
                <td width="33%" align="center">
                 <!-- PERMISSOES AKI -->
                </td>
                <td width="34%" align="center">
                    <acesso:controlHiddenItem nomeIdentificador="cli_lus_carregadados">
                     <img src="<%=request.getContextPath()%>/resources/images/bt_outrosdados_nrml.gif"
                          style="cursor:pointer"
                          onmouseup="dadosComportamentais()" />
                    </acesso:controlHiddenItem>
                </td>
                <td width="33%" align="right">
                    <% if (ConstantesCRM.SSIX.equals(parametros.getIdTipoRelacionamento())) { %>
                    <img src="<%=request.getContextPath()%>/resources/images/bt_alterar_prospect_nrml.gif"
                         style="cursor:pointer"
                         onmouseup="goCadastroProspect()" />
                    <% } %>
                </td>
            </tr>
        </table>
        <div><img id="imgRefDown" src="<%=request.getContextPath()%>/resources/images/transp.gif" width="1" height="5"></div>
    </acesso:controlHiddenItem>
    </form>
    </body>
    <vivo:quadroFlutuante id="dvProfile" idIframe="ifrmProfile" width="783" height="490" spacesTop="0" spacesLeft="0" display="none" url="<%=request.getContextPath()%>" label="Customer Profile" />
</html>
