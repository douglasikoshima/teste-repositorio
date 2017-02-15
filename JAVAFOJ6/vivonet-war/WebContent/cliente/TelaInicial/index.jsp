<%@ page import="br.com.vivo.fo.cliente.vo.ParametrosVODocument.ParametrosVO"%>
<%@ page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<acesso:controlInitEnv/>

<bean:define id="form"               name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="tiForm" />
<bean:define id="formpesquisa"       name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="pesquisaForm" />
<bean:define id="validarClienteForm" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="validarClienteForm"/>
<bean:define id="grupos"             name="form" property="gruposVO"/>
<%
String template = "./../../resources/jsp/CRMTemplate.jsp";
if((request.getParameter("idAtendimento")!=null) && (request.getParameter("idAtendimento").length() > 0)){
        template = "./../../resources/jsp/CRMTemplateDet.jsp";
}%>
<netui-template:template templatePage='<%=template%>'>
<netui-template:setAttribute value="FrontOffice - Atendimento" name="title"/>
<netui-template:setAttribute value="Atendimento" name="modulo"/>
<netui-template:section name="headerSection">
    <link rel="stylesheet" media="all" type="text/css" href="<%=request.getContextPath()%>/resources/css/telainicial.css" />
    <link rel="stylesheet" media="all" type="text/css" href="<%=request.getContextPath()%>/resources/css/autocomplete.css" />
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/toolTip.js"></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/telainicial.js"></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/validacioncliente.js"></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/ObjectPositionSize.js"></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/mouseTrail.js"></script>
	<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/xml2json.js"></script>
	<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/autocomplete.js"></script>
	<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/formatPhoneNumber.js"></script>

    <script FOR=window EVENT=onload LANGUAGE="JScript">
		if (window.top.frameApp.dvAnimarAguarde != null) window.top.frameApp.stopAnimation();
        <%if(ConstantesCRM.STRUE.equals(request.getParameter("updateCombos"))){%>
        idGrupoAtendimento   = '<%= request.getSession().getAttribute("idGrupoTroca") %>';
        idTipoRelacionamento = '<%= request.getSession().getAttribute("idRelacionamentoTroca") %>';
        idPos                = '<%= request.getSession().getAttribute("idPosTroca") %>';

        flUpdateCombos       = true;
        <%}%>
		carregaTela();
    </script>
    <script language="VBScript">
        function messageBox(pergunta)
            messageBox = msgbox(pergunta, 4, "Vivo Net")
        end function
    </script>
</netui-template:section>

<netui-template:section name="bodySection">

<div id="menuPrincipal"><jsp:include page="/resources/menus/MenuPrincipal.jsp" /></div>

<div id="ttip" style="display:none;position:absolute;max-width:200px;"></div>

<script type="text/javascript">

    if( window.top.frameApp.dvAnimarAguarde != null ) window.top.frameApp.startAnimation();

    var moveToolTip = true;
    var sfTimeout = 500;

    xBump = yBump=10;
    MSIE  = document.all;
    NS6   = document.getElementById && !document.all;
    if (MSIE||NS6) {
        ttipObj = document.all?document.all["ttip"]:document.getElementById?document.getElementById("ttip"):"";
    }

    function carregaToolTip(content) {
        ttipObj.innerHTML=content;
        ttipObj.style.display='';
    }

    tipoAtendimento = '<bean:write name="form" property="tipoAtendimento"/>';
    nrLinha         = '<bean:write name="form" property="nrLinha"/>';
    pesquisa        = '<bean:write name="form" property="pesquisa"/>';
    idAtendimento   = '<%=request.getParameter("idAtendimento")!=null?request.getParameter("idAtendimento"):ConstantesCRM.SVAZIO%>';
    fila            = '<%=request.getParameter("fila")!=null?request.getParameter("fila"):ConstantesCRM.SVAZIO%>';

    <%
    String framesMostrados = (request.getParameter("framesMostrados")!=null?request.getParameter("framesMostrados"):ConstantesCRM.SVAZIO);
    if (framesMostrados.equals("dados")) { %>
    telaCarregadaFila = true;
    idRelacionamentoIndex = <%=request.getParameter("relacionamento")%>;
    <%}%>

    showSiteFornecedor = function() {
        $('site_fornecedor').show();
    }

</script>

<div id="divSmiley" style="display:none">
    <ul>
        <li id="smileySatisfeito" onmouseup="manageSmiley(this)"></li>
        <li id="smileyImparcial" onmouseup="manageSmiley(this)"></li>
        <li id="smileyInsatisfeito" onmouseup="manageSmiley(this)"></li>
    </ul>
</div>

<div id="divContainer">

    <div id="ti_divPesquisa">
        Pesquisa por
        <select name="ti_comboPesquisa" id="ti_comboPesquisa" onChange="$('ti_inputValorPesquisa').value=''; inIdPosAutomatico = true; verificaDoc(); top.frameApp.nrProtocolo = ''; top.frameApp.nrProtocoloScreenPop = '';">
            <option value="celular">N&uacute;mero da linha</option>
            <option value="nome">Nome do Cliente</option>
            <option value="conta">N&uacute;mero da Conta</option>
            <option value="CPF">CPF</option>
            <option value="CNPJ">CNPJ</option>
            <option value="RNE">RNE</option>
            <option value="Pas">Passaporte</option>
            <option value="IE">Inscri&ccedil;&atilde;o Estadual</option>
            <option value="razao">Raz&atilde;o Social</option>
            <option value="CN">Certid&atilde;o de Nascimento</option>
            <option value="RG">RG</option>
            <option value="IM">Inscri&ccedil;&atilde;o Municipal</option>
            <option value="naoCliente">N&atilde;o Identificado</option>
            <option value="protocolo">Protocolo</option>
        </select>
        <input type="text"
               id="ti_inputValorPesquisa"
               name="ti_inputValorPesquisa"
               style="width:150px;margin-top:-19px;"
               onkeyup="escolheMascara(this);preValidaKey();"
               value="<bean:write name="formpesquisa" property="nome"/>" />
               <!--onkeyup="escolheMascara(this)-->
        <html:hidden name="form" property="nrLinha" styleId="nrLinhaOculto"/>
        <script>
            $('ti_inputValorPesquisa').value = "<bean:write name="form" property="nrLinha" />";
        </script>

        <acesso:controlHiddenItem nomeIdentificador="cli_indti_pesquisar">
		<img id="ti_botaoPesquisar"
                     src="<%=request.getContextPath()%>/resources/images/bt_pesquisar_nrml.gif"
                     onClick="if($F('ti_comboPesquisa')=='naoCliente'){$('ti_comboPesquisa').selectedIndex=0};isScreenPop=false;inIdPosAutomatico=true;mostraIdPos();nrProtocoloScreenPop='';validaPesquisa();"/>
		</acesso:controlHiddenItem>

        <img id="ti_botaoLimpar" src="<%=request.getContextPath()%>/resources/images/bt_limpar_nrml.gif" onClick="$('ti_inputValorPesquisa').value='';$('ti_comboPesquisa').selectedIndex=0; retornaBtPesquisar(); return false;"/>

    </div>

    <div id="ti_divGrupoLogin">
        <html:select name="form" style="width:121px" property="grupoSel" onchange="guardaIds(); trocarGrupo(this.value); focaTipCampos(this.options[this.selectedIndex].text,this, 110, 540, 15);" onfocus="focaTipCampos(this.options[this.selectedIndex].text,this, 110, 540, 15);" onmouseover="focaTipCampos(this.options[this.selectedIndex].text,this, 110, 540, 15);" onblur="HideTip();" onmouseout="HideTip();">
            <html:options collection="grupos" property="idGrupo" labelProperty="dsGrupo"/>
        </html:select>
        <span id="ti_login"><bean:write name="form" property="nmLogin"/></span>

        <% /* if (request.getSession().getAttribute(ConstantesCRM.USUARIO_SITE) != null
                    && request.getSession().getAttribute(ConstantesCRM.USUARIO_FORNECEDOR) != null) { */ %>
        <span id="ti_site_fornecedor_lupa" style="display:inline;">
            <img src="<%=request.getContextPath()%>/resources/images/ti_site_fornecedor_lupa.gif"
                 style="cursor:pointer"
                 onmouseup="showSiteFornecedor()" />
        </span>
        <% //} %>

    </div>

    <div id="site_fornecedor"
         style="display:none"
         onmouseover="clearTimeout(sfTimeout)"
         onmouseout="sfTimeout=setTimeout(function(){$('site_fornecedor').hide()}, 2000);">
        <dl>
            <dt><img alt="Login" src="<%=request.getContextPath()%>/resources/images/ti_login.gif"></dt>
            <dd id="ds_login">
                <% String dsLogin = request.getSession().getAttribute(ConstantesCRM.USUARIO_LOGIN) != null
                    ? (String) request.getSession().getAttribute(ConstantesCRM.USUARIO_LOGIN)
                    : "&nbsp;"; %>
                <span title="<%=dsLogin%>">
                    <%=dsLogin%>
                </span>
            </dd>
            <dt><img alt="Site" src="<%=request.getContextPath()%>/resources/images/ti_site.gif"></dt>
            <dd id="ds_site">
                <% String dsSite = request.getSession().getAttribute(ConstantesCRM.USUARIO_SITE) != null
                    ? (String) request.getSession().getAttribute(ConstantesCRM.USUARIO_SITE)
                    : "&nbsp;"; %>
                <span title="<%=dsSite%>">
                    <%=dsSite%>
                </span>
            </dd>
            <dt><img alt="Fornecedor" src="<%=request.getContextPath()%>/resources/images/ti_fornecedor.gif"></dt>
            <dd id="ds_fornecedor">
                <% String dsFornecedor = request.getSession().getAttribute(ConstantesCRM.USUARIO_FORNECEDOR) != null
                    ? (String) request.getSession().getAttribute(ConstantesCRM.USUARIO_FORNECEDOR)
                    : "&nbsp;"; %>
                <span title="<%=dsFornecedor%>">
                    <%=dsFornecedor%>
                </span>
            </dd>
        </dl>
    </div>

	<div id="ti_barraHorizontal">
		<div id="ti_divProtocolo">
			<span id="nrProtocolo">
				<logic:equal name="form" property="nrProtocolo" value="">
				Protocolo não gerado
				</logic:equal>
				<logic:notEqual name="form" property="nrProtocolo" value="">
                    <logic:equal name="form" property="protocoloScreenPop" value="true">
                        <script type="text/javascript">nrProtocoloScreenPop = <bean:write name="form" property="nrProtocolo"/>;</script>
                    </logic:equal>
				<bean:write name="form" property="nrProtocolo"/>
				</logic:notEqual>
			</span>
		</div>

		<div id="ti_divSMS">
            <acesso:controlHiddenItem nomeIdentificador="cli_indti_altnumerosms">
                <logic:notEqual name="form" property="nrProtocolo" value="">
                <img src="<%=request.getContextPath()%>/resources/images/ti_icon_sms.gif"
                     width="28"
                     height="26"
                     onmouseup="alterarNumeroSMS()" />
                </logic:notEqual>
            </acesso:controlHiddenItem>

            <acesso:controlHiddenItem nomeIdentificador="cli_indti_gerarprotocolo">
                <logic:equal name="form" property="nrProtocolo" value="">
                <img title="Solicitar Protocolo"
                         width="25"
                         height="27"
                         src="<%=request.getContextPath()%>/resources/images/ti_bt_novoprotocolo.gif"
                         onmouseup="solicitarProtocolo()" />
                </logic:equal>
            </acesso:controlHiddenItem>
		</div>

		<div id="ti_divSenha">
			<span id="spanClienteUsuario"></span>
            <span id="spanStatusSenha"></span>
            <span id="spanValidacaoClienteUsuario">
                <select id="tipoRelacionamento" style="width:57px;height:14px" onchange="guardaIds()">
                    <option value="-1">&nbsp;</option>
                    <option value="1">Usu&aacute;rio</option>
                    <option value="2">Cliente</option>
                    <option value="6">N&atilde;o Cliente/Prospect</option>
                </select>
                <label for="idPos" title="Identifica&ccedil;&atilde;o positiva">ID Pos</label>
                <select name="idPos" id="idPos" style="width:42px; height:14px" onchange="guardaIds();submitIdPos();">
                    <option value="0">N&atilde;o</option>
                    <option value="1">Sim</option>
                </select>
            </span>
		</div>

		<div id="ti_divPortabilidade" style="background:url(<%=request.getContextPath()%>/resources/images/ti_bg_portabilidade.gif)">
			<div id="ti_divPortabilidadeContentIcon"></div>
			<div id="ti_divPortabilidadeContentInfoWrapper">
				<div id="ti_divPortabilidadeContentTipo" class="portIn"></div>
				<div id="ti_divPortabilidadeContentDescricao"></div>
			</div>
		</div>

		<div id="ti_divSaltador"></div>

		<div id="ti_divSmiley">
			<img id="idImgSmiley"
				 style="cursor:pointer;margin:4px 0 0 5px"
				 src="<%=request.getContextPath()%>/resources/images/smiley_satisfeito.gif"
				 width="31"
				 height="32"
				 onclick="manageSmiley()">
		</div>

		<div id="ti_divTerminar"
			 <acesso:controlHiddenItem nomeIdentificador="cli_indti_terminar">
			 onmouseup="submitTerminar();"
			 style="background:url(<%=request.getContextPath()%>/resources/images/ti_terminar.gif) no-repeat;cursor:pointer;"
			 </acesso:controlHiddenItem>
        >
		</div>
	</div>

    <div id="divContentContainer" style="display:none">
        <div id="ti_divCliente">
            <div id="ti_divLupaCliente"></div>
            <div id="ti_divInfoCliente">
                <dl>
                    <dt style="margin-right:20px;">Nome</dt>
                    <dd id="ddClienteNome" title=""></dd>
                    <dt id="dtClienteTipoDocumento" style="width:54px;">CNPJ</dt>
                    <dd id="ddClienteDocumento"></dd>
                    <dt style="margin:0 15px 0 25px;">Natureza</dt>
                    <dd id="ddClienteNatureza" title=""></dd>
                    <dt style="margin-right:3px;">Telefone</dt>
                    <dd id="ddClienteTelefone"></dd>
                    <dt style="margin:0 33px 0 25px">Churn</dt>
                    <dd id="ddClienteChurn"></dd>
                </dl>

                <div id="ti_divSegmentacaoShadow">
                    <div id="ti_divSegmentacao" class="ltin tpin">
                        <dl style="margin:-13px 0 0 -3px;">
                            <dt style="display:block;line-height:25px;">
                                Segmenta&ccedil;&atilde;o
                                <span id="ti_lupaSegmentacao"></span>
                            </dt>
                            <dd id="ddSegmentacao" title=""></dd>
                        </dl>
                    </div>
                </div>

                <div id="ti_divCarteirizacaoShadow">
                    <div id="ti_divCarteirizacao" class="ltin tpin">
                        <dl style="margin:-13px 0 0 -3px;">
                            <dt style="display:block;line-height:25px;">
                                Carteiriza&ccedil;&atilde;o
                                <span id="ti_lupaCarteirizacao"></span>
                            </dt>
                            <dd id="ddCarteirizacao" title=""></dd>
                        </dl>
                    </div>
                </div>
            </div>
        </div>

        <div id="ti_divLinha">
            <div id="ti_divLupaLinha"></div>
            <div id="ti_divInfoLinha">
                <dl>
                    <dt style="margin-right:49px;">Telefone</dt>
                    <dd id="ddNrLinha" title="" style="display:inline;"></dd>
                    <dt style="margin-right:24px;">Tipo de Linha</dt>
                    <dd id="ddTipoLinha" title=""></dd>
                    <dt style="margin-right:5px;">Plano de Servi&ccedil;o</dt>
                    <dd id="ddPlanoServico" title=""></dd>
                    <dt style="margin-right:60px;">Status</dt>
                    <dd id="ddStatusLinha" title=""></dd>
                </dl>
            </div>

        </div>

        <div id="ti_divUsuario">

            <div id="ti_divLupaUsuario"></div>

            <div id="ti_divInfoUsuario">
                <dl>
                    <dt style="margin-right:10px;">Nome</dt>
                    <dd id="ddUsuarioNome" title=""></dd>
                    <dt id="ddUsuarioTipoDocumento" style="width:50px;">CPF</dt>
                    <dd id="ddUsuarioDocumento"></dd>
                    <dt style="margin-right:9px;">Cargo</dt>
                    <dd id="ddUsuarioCargo" title=""></dd>
                    <dt style="margin-right:3px;">Contato</dt>
                    <dd id="ddUsuarioContato"></dd>
                </dl>
            </div>

        </div>

		<div id="ti_divGestor">
			<div id="ti_divLupaGestor"></div>
			<div id="ti_divInfoGestor">
	            <dl>
					<dt style="margin-right:5px;">Gestor de Contas</dt>
	                <dd id="ddGestorNome"></dd>
					<dt style="margin-right:5px;">Telefone</dt>
	                <dd id="ddGestorTelefone">(11)9999-4444</dd>
	            </dl>
			</div>
        </div>

        <div id="ti_divPrePagoFaturamento">
            <div id="ti_divLupaPrePagoFaturamento" class="ti_lupaFaturamento"></div>
            <div id="ti_divInfoPrePagoFaturamento" class="ti_infoFaturamento"></div>
        </div>

        <div id="ti_divEndereco">
            <dl>
                <dt style="margin-right:10px;">Endere&ccedil;o</dt>
                <dd id="ddEndereco" title=""></dd>
                <dt style="margin-right:27px;">Bairro</dt>
                <dd id="ddBairro"></dd>
                <dt style="margin:0 15px 0 10px;">Cidade</dt>
                <dd id="ddCidade" title=""></dd>
                <dt style="margin-right:23px;">Estado</dt>
                <dd id="ddEstado"></dd>
                <dt style="margin:0 33px 0 10px">CEP</dt>
                <dd id="ddCEP"></dd>
            </dl>
        </div>

        <div id="ti_divConteudoAbasTI">

            <div id="ti_divAbas">

                <div id="ti_divAbasConteudo">

                    <vivo:abaGrupo id="btAba" width="766" height="16" styleClass="abatexto">

                        <acesso:controlHiddenItem nomeIdentificador="cli_indti_arel">
                        <vivo:abaItem id="bt01" onclick="abaSelected(btAba, bt01);CarregaAba(this.id);flUp=true;resizeFrameDetalhe();" value="Relacionamento" select="S"/>
                        </acesso:controlHiddenItem>

                        <acesso:controlHiddenItem nomeIdentificador="cli_indti_aat">
                        <vivo:abaItem id="bt02" onclick="CarregaAba(this.id);flUp=true;resizeFrameDetalhe();" value="Atendimento"/>
                        </acesso:controlHiddenItem>

                        <acesso:controlHiddenItem nomeIdentificador="cli_indti_aserv">
                        <vivo:abaItem id="bt03" onclick="CarregaAba(this.id);" value="Serviços"/>
                        </acesso:controlHiddenItem>

                        <acesso:controlHiddenItem nomeIdentificador="cli_indti_acamp">
                        <vivo:abaItem id="bt08" onclick="abaSelected(btAba, bt08);CarregaAba(this.id);" value="Campanha"/>
                        </acesso:controlHiddenItem>

                        <acesso:controlHiddenItem nomeIdentificador="cli_indti_ahc">
                        <vivo:abaItem id="bt04" onclick="abaSelected(btAba, bt04);CarregaAba(this.id);" value="Hist.Campanha"/>
                        </acesso:controlHiddenItem>

                        <acesso:controlHiddenItem nomeIdentificador="cli_indti_asen">
                        <vivo:abaItem id="bt05" onclick="CarregaAba(this.id);flUp=true;resizeFrameDetalhe();" value="Senha"/>
                        </acesso:controlHiddenItem>

                        <acesso:controlHiddenItem nomeIdentificador="cli_indti_aret">
                        <vivo:abaItem id="bt06" onclick="abaSelected(btAba, bt06);CarregaAba(this.id);resizeFrameDetalhe();" value="Retenção"/>
                        </acesso:controlHiddenItem>

                        <acesso:controlHiddenItem nomeIdentificador="cli_indti_acc">
                        <vivo:abaItem id="bt07" onclick="abaSelected(btAba, bt07);CarregaAba(this.id);" value="C.Devolvida"/>
                        </acesso:controlHiddenItem>

                        <acesso:controlHiddenItem nomeIdentificador="cli_indti_achip">
                        <vivo:abaItem id="bt09" onclick="CarregaAba(this.id);" value="Chip"/>
                        </acesso:controlHiddenItem>

                        <acesso:controlHiddenItem nomeIdentificador="cli_indti_imei">
                            <vivo:abaItem id="bt10" onclick="CarregaAba(this.id);" value="IMEI"/>
                        </acesso:controlHiddenItem>

                        <acesso:controlHiddenItem nomeIdentificador="cli_indti_track">
                            <vivo:abaItem id="bt11" onclick="CarregaAba(this.id);flUp=true;" value="Tracking"/>
                        </acesso:controlHiddenItem>

                    </vivo:abaGrupo>

                </div>

                <div id="ti_divAbasResizer" onmouseup="resizeFrameDetalhe(true)"></div>

            </div>

            <div id="ti_divConteudoAbas" style="display:none"><!-- Relacionamento.jsp -->
                <iframe name="ti_frameAbas" id="ti_frameAbas" src="about:blank" width="788" height="168" frameborder="0" style="overflow-x:hidden"></iframe>
            </div>

        </div>

        <div id='menuVoltar' style="display:none;padding-top:5px;">
            <img src="<%=request.getContextPath()%>/resources/images/bt_voltar_nrml.gif" style="border:none;cursor:pointer;" onmouseup="mostraDetalhe();"/>
        </div>

    </div>

</div>

<div id="divPopupTI" class="popUpDiv" style="z-index:998;position:absolute;top:0;left:0;width:150%;height:150%;background-image:url(/FrontOfficeWeb/resources/images/window_bg.gif);display:none;"></div>

<vivo:quadroFlutuante id="divFaturaDetalhadaImagem"
                          idIframe="iframeFaturaDetalhadaImagem"
                          width="800"
                          height="600"
                          scroll="auto"
                          spacesTop="0"
                          spacesLeft="0"
                          display="none"
                          url="<%=request.getContextPath()%>"
                          label="Fatura Detalhada"/>

<vivo:quadroFlutuante id="divDetalheHistorico"
                          idIframe="iframeDetalheHistorico"
                          width="800"
                          height="600"
                          scroll="auto"
                          spacesTop="0"
                          spacesLeft="0"
                          display="none"
                          url="<%=request.getContextPath()%>"
                          label="Atendimento (Histórico)"/>

<iframe id="frameEscondido" name="frameEscondido" frameborder="0" scrolling="no" height="0" width="0" style="top:0; left: 0;"></iframe>

<script language="javascript" type="text/javascript">

<acesso:controlHiddenItem nomeIdentificador="cli_indti_altnumerosms">
	inAcessoAltNumeroSMS = true;
</acesso:controlHiddenItem>

<acesso:controlHiddenItem nomeIdentificador="cli_indti_gerarprotocolo">
	inAcessoGerarProtocolo = true;
</acesso:controlHiddenItem>

<logic:equal name="form" property="protocoloScreenPop" value="true">
    isScreenPop = true;
</logic:equal>

<% if (request.getAttribute("protocoloInativo") != null) { %>
protocoloInativo = true;
<% } %>

<logic:equal name="validarClienteForm" property="senhaValidada" value="true">
    $('spanValidacaoClienteUsuario').style.display = "none";
    $('spanStatusSenha').style.color = "#00ff00";
    $('spanStatusSenha').innerHTML = "Senha validada";
    $('spanValidacaoClienteUsuario').style.display = "none";
    <logic:equal name="validarClienteForm" property="cliente" value="true">
    $('spanClienteUsuario').innerHTML = "Cliente";
    </logic:equal>
    <logic:equal name="validarClienteForm" property="usuario" value="true">
    $('spanClienteUsuario').innerHTML = "Usu&aacute;rio";
    </logic:equal>
</logic:equal>

<logic:equal name="validarClienteForm" property="senhaValidada" value="false">
	$('spanValidacaoClienteUsuario').style.display = 'block';
    $('spanStatusSenha').setStyle({'color':'#aa2c2c'});
    $('spanClienteUsuario').style.display = "none";
    $('spanStatusSenha').innerHTML = "Senha n&atilde;o validada";
    <logic:equal name="validarClienteForm" property="cliente" value="true">
    $('tipoRelacionamento').selectedIndex = 1;
    </logic:equal>
    <logic:equal name="validarClienteForm" property="usuario" value="true">
    $('tipoRelacionamento').selectedIndex = 2;
    </logic:equal>
</logic:equal>
</script>

</netui-template:section>

</netui-template:template>