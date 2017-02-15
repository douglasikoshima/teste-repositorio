<%@page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<bean:define id="Form"         name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="ofertaAparelhoForm" type="fidelizacao.FidelizacaoController.OfertaAparelhoForm"/>
<bean:define id="param"        name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="parametrosVO"/>
<bean:define id="Adimplencia"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="consultaAdimplenciaForm" />
<bean:define id="Regionais"    name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="ofertaAparelhoForm.regionais" />
<bean:define id="Endereco"     name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="ofertaAparelhoForm.enderecoVO" />

<netui-template:template templatePage="/resources/jsp/templateRetencao.jsp">
    <netui-template:section name="headerSection">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/calendar.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/fidelizacao-aparelhos-entrega.js"></script>
        <script language="JavaScript"  src="<%=request.getContextPath()%>/resources/scripts/frameweb.js" ></script>
        <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/formatPhoneNumber.js"></script>
        <script type="text/javascript" language="javascript">
        <!--
            var inChamadaMotivos = 0;
            var origRua             = '<bean:write name="Form" property="rua"/>';
            var origNumero          = '<bean:write name="Form" property="numero"/>';
            var origComplemento     = '<bean:write name="Form" property="complemento"/>';
            var origBairro          = '<bean:write name="Form" property="bairro"/>';
            var origCidade          = '<bean:write name="Form" property="cidade"/>';
            var origIdUFSelecionado = '<bean:write name="Form" property="idUFSelecionado"/>';
            var origCep             = '<bean:write name="Form" property="cep"/>';

            if (parent.inValFirst == null || parent.inValFirst == undefined){
                parent.origRua             = '<bean:write name="Form" property="rua" />';
                parent.origNumero          = '<bean:write name="Form" property="numero" />';
                parent.origComplemento     = '<bean:write name="Form" property="complemento" />';
                parent.origBairro          = '<bean:write name="Form" property="bairro" />';
                parent.origCidade          = '<bean:write name="Form" property="cidade" />';
                parent.origIdUFSelecionado = '<bean:write name="Form" property="idUFSelecionado" />';
                parent.origCep             = '<bean:write name="Form" property="cep" />';
            }

            parent.inValFirst = 1;

            checkAlteracaoEndereco = function() {
                if (trim(parent.origRua) != trim(f.rua.value)) return true;
                else if(trim(parent.origNumero) != trim(f.numero.value)) return true;
                else if(trim(parent.origComplemento) != trim(f.complemento.value)) return true;
                else if(trim(parent.origBairro) != trim(f.bairro.value)) return true;
                else if(trim(parent.origCidade) != trim(f.cidade.value)) return true;
                else if(trim(parent.origIdUFSelecionado) != trim(f.idUFSelecionado.value)) return true;
                else if(trim(parent.origCep) != trim(f.cep.value)) return true;
                else return false;
            }

            retornaValoresOriginais = function() {
                f.rua.value = parent.origRua;
                f.numero.value = parent.origNumero;
                f.complemento.value = parent.origComplemento;
                f.bairro.value = parent.origBairro;
                f.cidade.value = parent.origCidade;
                f.idUFSelecionado.value = parent.origIdUFSelecionado;
                f.cep.value = parent.origCep;
            }

            function setPermissaoAlteracao(booleano) {
                f = document.forms[0];
                if (booleano==false) {
                    f.rua.readOnly = true;
                    f.numero.readOnly = true;
                    f.complemento.readOnly = true;
                    f.bairro.readOnly = true;
                    f.cidade.readOnly = true;
                    f.idUFSelecionado.disabled = true;
                    f.cep.readOnly = true;
                } else {
                    f.rua.readOnly = false;
                    f.numero.readOnly = false;
                    f.complemento.readOnly = false;
                    f.bairro.readOnly = false;
                    f.cidade.readOnly = false;
                    //f.idUFSelecionado.disabled = false;
                    f.cep.readOnly = false;
                }
            }

            function verificaAlteracaoEndereco(statusCheck) {
                imgLupa = $("imgLupaPesquisaEndereco");
                if (statusCheck==true) {
                    imgLupa.style.visibility = "visible";
                    gerenciarAcoes("ENCAMINHAR");
                    setPermissaoAlteracao(true);
                    $("divMotivoAlteracao").style.visibility = "visible";
                    if (inChamadaMotivos==0) {
                        getMotivosAlteracaoEndereco();
                    }

                } else {
                    setPermissaoAlteracao(true);
                    if (checkAlteracaoEndereco()) {
                        if (confirm("Alterações efetuadas no endereço serão perdidas. Deseja continuar?")){
                            retornaValoresOriginais();
                            $("divMotivoAlteracao").style.visibility = "hidden";
                            imgLupa.style.visibility = "hidden";
                            setPermissaoAlteracao(false);
                            gerenciarAcoes("RETER");
                        }else{
                            f = document.forms[0];
                            <logic:notEqual name="param" property="inTipoPessoa" value="PJ">
                            f.inAlteracaoEndereco.checked = true;
                            </logic:notEqual>
                            setPermissaoAlteracao(true);
                        }
                    } else{
                        imgLupa.style.visibility = "hidden";
                        gerenciarAcoes("RETER");
                        setPermissaoAlteracao(false);
                        document.getElementById("divMotivoAlteracao").style.visibility = "hidden";
                        retornaValoresOriginais();
                    }
                }
            }

            function appendToSelect(objSelect, valueOption, textOption, isSelectedFlag) {
                var motivoAlteracaoIndex = '<%=request.getAttribute("motivoAlteracaoIndex")%>';
                var newOption = new Option(textOption, valueOption, false, isSelectedFlag);
                objSelect.options.add(newOption);
            }

            getMotivosAlteracaoEndereco = function() {
                var xmlhttp = new ActiveXObject("microsoft.xmlhttp");
                xmlhttp.open("GET","getMotivosAlteracaoEndereco.do", true);
                xmlhttp.setRequestHeader("Content-Type","text/xml");
                xmlhttp.setRequestHeader("chartset","ISO-8859-1");
                xmlhttp.send();
                
                xmlhttp.onreadystatechange = function() {
                    if (xmlhttp.readyState==4){
                        var texto = xmlhttp.responseText;
                        texto = unescape(texto);
                        oXml = new ActiveXObject("microsoft.xmldom");
                        if ( !oXml.loadXML(texto) || oXml.selectSingleNode("/listaMotivosAlteracaoEndereco")==null ) {
                            alert('Erro durante carregamento de lista de \nmotivos de alteração de endereço. \n\nTente novamente.');
                            inChamadaMotivos = 0;
                            $("imgLupaPesquisaEndereco").style.visibility = "hidden";
                            gerenciarAcoes("RETER");
                            setPermissaoAlteracao(false);
                            document.getElementById("divMotivoAlteracao").style.visibility = "hidden";
                            <logic:notEqual name="param" property="inTipoPessoa" value="PJ">
                            f.inAlteracaoEndereco.checked = false;
                            </logic:notEqual>

                        } else {
                            var objSelect      = document.getElementById("idMotivoAlteracaoEndereco");
                            var objForm        = document.forms[0];
                            var isSelectedFlag = false;

                            for (i=0; i < oXml.getElementsByTagName("itemListaVO").length; i++) {
                                var itemListaVO = oXml.getElementsByTagName("itemListaVO")[i];
                                var nodes       = itemListaVO.childNodes;
                                var idMotivoAlteracaoEndereco    = itemListaVO.childNodes[0].text;
                                var idMotivoAlteracaoSelecionado = '<%=request.getAttribute("idMotivoAlteracaoSelecionado")%>';

                                if (idMotivoAlteracaoEndereco == idMotivoAlteracaoSelecionado) isSelectedFlag = true;
                                else isSelectedFlag = false;

                                var dsMotivoAlteracaoEndereco = itemListaVO.childNodes[1].text;
                                appendToSelect(objSelect, idMotivoAlteracaoEndereco, dsMotivoAlteracaoEndereco, isSelectedFlag);
                            }
                            inChamadaMotivos++;
                        }
                    }
                }
            }

            
            verificaPermissaoAlteracao = function() {
                <logic:notEqual name="param" property="inTipoPessoa" value="PJ">
                f = document.forms[0];
                if (f.inAlteracaoEndereco.checked) {
                    setPermissaoAlteracao(true);
                    gerenciarAcoes("ENCAMINHAR");
                } else {
                </logic:notEqual>
                    setPermissaoAlteracao(false);
                    gerenciarAcoes("RETER");
                <logic:notEqual name="param" property="inTipoPessoa" value="PJ">
                }
                </logic:notEqual>
            }

            function gerenciarAcoes(acao){
                if (acao=="RETER") {
                    var chamada = '<a href="reterAparelho.do?acao=ok" onclick="reter(this);return false;"><img vspace="3" border="0" src="<%=request.getContextPath()%>/resources/images/bt_reter_nrml.gif" /></a>';
                } else if (acao=="ENCAMINHAR") {
                    var chamada = '<a href="encaminharAlteracaoEndereco.do" onclick="encaminhar(this);return false;"><img vspace="3" border="0" src="<%=request.getContextPath()%>/resources/images/bt_encaminhar_nrml.gif" /></a>';
                }
                document.getElementById("divAcoes").innerHTML = chamada;
            }

            function encaminhar(){
                f = document.forms[0];
                var nrCPF = onlyNumbers(window.top.frameApp.$('ddClienteDocumento').innerHTML);

                if (f.idMotivoAlteracaoEndereco.value=="") {
                    alert('Selecione um motivo para enviar a solicitação de alteração de endereço.');
                } else {
                    var idEntrega = 0;

                    //Maximo de caracteres que o SAP aceita para os campos
                    maxRua    = 60;     //Rua considera a somatoria dos campos (Rua/Numero/Complemento)
                    maxBairro = 35;
                    maxCidade = 35;
                    maxEstado = 3;
                    maxCEP    = 10;

                    if(f.complemento.value=="") 
                        qtdeCaracSepComplemento = 0; 
                    else 
                        qtdeCaracSepComplemento = 3;
                        
                    qtdeCaracSepNumero = 2;

                    ruaLength    = f.rua.value.length+f.complemento.value.length+f.numero.value.length+qtdeCaracSepNumero+qtdeCaracSepComplemento;
                    bairroLength = f.bairro.value.length;
                    cidadeLength = f.cidade.value.length;
                    estadoLength = f.idUFSelecionado.value.length;
                    cepLength    = f.cep.value.length;

                    if(document.ofertaAparelhoForm.elements["modelo"].value == ""){
                        alert("Favor preencher o campo Modelo!");
                    }else if(document.ofertaAparelhoForm.elements["cor"].value == ""){
                        alert("Favor preencher o campo Cor!");
                    }else if(document.ofertaAparelhoForm.elements["preco"].value == ""){
                        alert("Favor preencher o campo Preço!");
                    }else if ( idEntrega == 0 && (f.rua.value == ""
                                                  || f.numero.value == ""
                                                  || f.cidade.value == ""
                                                  || f.idUFSelecionado.value == ""
                                                  || f.bairro.value == ""
                                                  || f.cep.value == "")){

                        alert('Algum dos campos referentes a dados de endereço está vazio.')

                    }else if (idEntrega == 0 && ruaLength > maxRua){
                        qtdeCaracteresNumeroComplemento = f.numero.value.length+f.complemento.value.length;
                        qtdePermitidaRua = maxRua - (qtdeCaracSepComplemento+qtdeCaracSepNumero+qtdeCaracteresNumeroComplemento);
                        alert("A quantidade de caracteres nos campos de endereço ultrapassa o máximo \n permitido pelo sistema legado. Correção necessária.");

                    }else if (idEntrega == 0 && bairroLength > maxBairro){
                        alert("A quantidade de caracteres no campo Bairro ultrapassa o máximo \n permitido pelo sistema legado. Correção necessária.");
                    }else if (idEntrega == 0 && cidadeLength > maxCidade){
                        alert("A quantidade de caracteres no campo Cidade ultrapassa o máximo \n permitido pelo sistema legado. Correção necessária.");
                    }else if (idEntrega == 0 && estadoLength > maxEstado){
                        alert("A quantidade de caracteres no campo Estado ultrapassa o máximo \n permitido pelo sistema legado. Correção necessária.");
                    }else if (idEntrega == 0 && cepLength > maxCEP){
                        alert("A quantidade de caracteres no campo CEP ultrapassa o máximo \n permitido pelo sistema legado. Correção necessária.");
                    }else if(idEntrega == 0 && trim(f.elements["rgContato"].value)==""){
                        alert("Por favor, preencha o RG de Contato");
                    }else if(idEntrega == 0 && f.elements["telContato"].value.length < 13){
                        alert("Telefone contato inválido");
                    }else{
                        f.target = "";
                        f.action = "encaminharAlteracaoEndereco.do?idMotivoAlteracaoEndereco="+f.idMotivoAlteracaoEndereco.value+"&nrCPF="+nrCPF;
                        f.idUFSelecionado.disabled = false;
                        parent.parent.mostrar_div();
                        f.submit();
                    }
                }
            }
            
            function fecharPesquisaEndereco(){
                $('ifrmPesqEndereco').src = "about:blank";
            }
        -->
        </script>
        <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
            <!--
                parent.parent.oculta_div();
                top.frameApp.$("idAnime").style.display="none";
            -->
        </SCRIPT>
</netui-template:section>
<netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="fid_dea_verpagina">
    <div style="width:770px;height:520px;overflow:auto;margin-bottom:1px solid #adadad;">
    <jsp:include page="informacoesCliente.jsp"/>
    <vivo:body idTable="tbMain" idDiv="dvMain" height="330" width="770">
    <form action="reterAparelho.do" method="post" name="ofertaAparelhoForm">
       	<html:hidden name="Form" property="inExcecao"/>
       	<html:hidden name="Form" property="vlDesconto"/>
       	<html:hidden name="Form" property="SAP"/>
       	<html:hidden name="Form" property="idAparelho"/>
       	<html:hidden name="Form" property="idAparelhoCor"/>
       	<html:hidden name="Form" property="idMeioPagamento"/>
       	<html:hidden name="Form" property="przVigencia"/>
        <input type="hidden" name="tipoEncerramento" value="3"/>
        
        <vivo:quadro id="qdMain" height="62" width="750" label="Dados do Aparelho">
            <table>
                <tr>
                    <td><b>Modelo:</b></td>
                    <td>
                    	<html:text name="Form" property="modelo" styleClass="textfield" readonly="true" size="20"/>
                    </td>
                    <td width="5%"><b>Cor:</b></td>
                    <td>
                    	<html:text name="Form" property="cor" styleClass="textfield" readonly="true" size="5"/>
                    </td>
                    <td width="5%"><b>Preço:</b></td>
                    <td>
                    	<html:text name="Form" property="preco" styleClass="textfield" readonly="true" size="10" onkeyup="checaReal(this)"/>
                    </td>
                    <td><b>Meio de Pagamento:</b></td>
                    <td>
                        <html:text name="Form" property="meioPagamento" styleClass="textfield" readonly="true" size="18"/>
                    </td>
                </tr>
                <tr>
                    <td><b>Desconto:</b></td>
                    <td><html:text name="Form" property="descontoSelecionado"  size="10"  readonly="true"/></td>
                    <td><b>Nº de Parcelas:</b></td>
                    <td><html:text name="Form" property="parcelaSelecionada"  size="10"  readonly="true"/></td>
                    <td><b>Valor Parcela:</b></td>
                    <td><html:text name="Form" property="vlParcela" size="10" onkeyup="checaReal(this)" readonly="true"/></td>
                    <td>
                        <acesso:controlHiddenItem nomeIdentificador="fid_dea_delivery">
                            <b>Delivery:</b>
                            <html:radio name="Form" style="background-color:#E3ECF4;border:0px" property="idEntrega" value="0" onclick="document.getElementById('loja').style.display='none';document.getElementById('delivery').style.display='block';verificaPermissaoAlteracao()"/>
                        </acesso:controlHiddenItem>
                        <acesso:controlHiddenItem nomeIdentificador="fid_dea_loja">
                            <b>Loja:</b>
                            <html:radio name="Form" style="background-color:#E3ECF4;border:0px" property="idEntrega" value="1" onclick="document.getElementById('delivery').style.display='none';document.getElementById('loja').style.display='block';gerenciarAcoes('RETER');"/>
                        </acesso:controlHiddenItem>
                    </td>
                    <td>
                    	<html:checkbox name="Form" property="semSimCard" value="0"  style="vertical-align:absmiddle;background:none; border:none;" /><b>Sem SIM CARD</b>
                    </td>
                </tr>
            </table>
        </vivo:quadro>
    <logic:equal name="Form" property="idEntrega" value="0" >
        <div id="delivery" style="display:block">
    </logic:equal>
    <logic:notEqual name="Form" property="idEntrega" value="0">
        <div id="delivery" style="display:none">
    </logic:notEqual>
        <vivo:quadro id="qdMain" height="80" width="750" label="Entrega do Aparelho" >
            <table width="100%"  cellpadding="1" cellspacing="1">
                <tr>
                    <td>Rua:</td>
                    <td colspan="2"><html:text name="Form" property="rua" maxlength="255" size="50" styleId="dsEndereco"/></td>
                    <td colspan="5" align="right">
                        <div id="divMotivoAlteracao" style="font-weight:bold;font-size:9px;visibility:hidden;">
                            Motivo da Altera&ccedil;&atilde;o:
                            <html:select name="Form" name="Adimplencia" property="idMotivoAlteracaoEndereco" style="width:150px;">
                                <html:option value="">-- Selecione --</html:option>
                            </html:select>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>Número:</td>
                    <td><html:text name="Form" property="numero" maxlength="10" size="2"/></td>
                    <td>Complemento:</td>
                    <td><html:text name="Form" property="complemento" maxlength="50" size="10" styleId="dsEndereco"/></td>
                    <td>Bairro:</td>
                    <td colspan="2"><html:text name="Form" property="bairro" maxlength="255" size="35" styleId="dsEndereco"/></td>
                    <td></td>
                </tr>
                <tr>
                    <td>Cidade:</td>
                    <td><html:text name="Form" property="cidade" maxlength="255" size="25" styleId="dsEndereco"/></td>
                    <td>Estado:</td>
                    <td>
                        <html:select style="width:45px" name="Form" property="idUFSelecionado">
                            <html:options collection="Regionais" property="sgUF" labelProperty="sgUF"/>
                        </html:select>
                    </td>
                    <td>CEP:</td>
                    <td nowrap>
                        <html:text name="Form" property="cep" maxlength="9" size="10" onkeypress="checaCEP(this)"/>
                        <logic:notEqual name="param" property="inTipoPessoa" value="PJ">
                        <img id="imgLupaPesquisaEndereco" align="absmiddle" onClick="pesquisaEndereco()" src="<%=request.getContextPath()%>/resources/images/lupa_bg_b8d0e7.gif" style="visibility:hidden;border:none;cursor:hand;"/>
                        </logic:notEqual>
                    </td>
                    <td valign="bottom" align="right" nowrap style="font-weight:bold;font-size:9px;">
                        <logic:notEqual name="param" property="inTipoPessoa" value="PJ">
                        <acesso:controlHiddenItem nomeIdentificador="fid_dea_alterarendereco">
                            <html:checkbox name="Form" property="inAlteracaoEndereco" value="0" onclick="verificaAlteracaoEndereco(this.checked)" style="vertical-align:absmiddle;background:none; border:none;" />Alterar Endere&ccedil;o
                        </acesso:controlHiddenItem>
                        </logic:notEqual>
                    </td>
                </tr>
            </table>
        </vivo:quadro>
        <vivo:quadro id="qdMain" height="32" width="750" label="Autorização de Entrega para Terceiros">
            <table width="100%" border="0" cellpadding="1" cellspacing="1">
                <tr>
                    <td>Nome: <html:text name="Form"  property="nmContato" maxlength="255" size="60"/></td>
                    <td>RG: <html:text name="Form" property="rgContato" maxlength="20" /></td>
                    <td>Telefone Contato: <html:text name="Form" property="telContato" maxlength="14" onblur="maskPhoneNumberObj(this);" onkeyup="maskPhoneNumberObj(this);"/></td>
                </tr>
            </table>
        </vivo:quadro>
		</div>
        <div id="loja" style="display:none">
            <vivo:quadro id="qdMain" height="60" width="750" label="Agendar para Retirar em Loja">
                <table width="100%">
                    <tr>
                        <td><a href="#" onClick="abreUrl('http://intranet.vivo-prsc.com.br/deptos/cli/aloglobal/call.php?topframe=grupos&area=grupos&x=index.php');return false">Nome da Loja:</a></td>
                        <td colspan="3"><html:text name="Form"  property="nmLoja" maxlength="255" size="50"/></td>
                    </tr>
                    <tr>
                        <td>Quantidade Em Estoque:</td>
                        <td><html:text name="Form" property="qtdEstoque" onkeyup="checaInteiro(this)"/></td>
                        <td>Data de Retirada:</td>
                        <td><html:text property="dtRetirada" name="Form" readonly="true" styleClass="textfield" size="10"/><img src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:hand;" title="Calendário" onclick="return showCalendar('dtRetirada', '%d/%m/%Y');"></td>
                    </tr>
                </table>
            </vivo:quadro>
		</div>
		<!--/td>
    </tr>
</table-->
        <table width="760" cellpadding="0" cellspacing="0" bgcolor="#e3ecf4" height="20">
            <tr>
                <td width="100" align="center">
                    <% if ("analiseCreditoAprovada_Prosseguir".equals(request.getSession().getAttribute(ConstantesCRM.SACTION))){ %>
                        <script language="javascript" type="text/javascript">
                            voltarAnaliseAprovada = function() {
                                top.frameApp.mostrar_div();
                                /*
                                document.forms[0].target = "";
                                document.forms[0].action = "efetivarRetencaoAnaliseCreditoAprovada.do?idRetencao=<%=request.getSession().getAttribute("idRetencao")%>&inVoltar=1";
                                document.forms[0].submit();
                                */
                                top.frameApp.CarregaAba("bt06");
                            }
                        </script>
                        <img action="voltarDEA.do"
                        	 src="<%=request.getContextPath()%>/resources/images/bt_voltar_nrml.gif"
                        	 border="0" onClick="voltarAnaliseAprovada(); return false;"/>
                    <% } else { %>
                        <script language="javascript" type="text/javascript">
                            voltarProcedimentoNormal = function() {
                                top.frameApp.mostrar_div();
                                document.forms[0].target = "";
                                document.forms[0].action = "voltarDEA.do?inProcedimentoNormal=1";
                                document.forms[0].submit();
                            }
                        </script>
                        <img action="voltarDEA.do"
                        	 src="<%=request.getContextPath()%>/resources/images/bt_voltar_nrml.gif"
                        	 border="0"
                        	 onClick="voltarProcedimentoNormal();return false;"/>
                    <% } %>
                </td>
                <td align="center" width="450">
                    <acesso:controlHiddenItem nomeIdentificador="fid_dea_manualeletronico">
                        <img onmouseup="window.open='http://intranet.vivo-sp.com.br/manual/retencao/index.htm';"
                        	 class="button"
                        	 src="<%=request.getContextPath()%>/resources/images/bt_manelet_nrml.gif"
                        	 border="0" />
                    </acesso:controlHiddenItem>
                    <acesso:controlHiddenItem nomeIdentificador="fid_dea_agendar">
                        <img src="<%=request.getContextPath()%>/resources/images/bt_agendar_nrml.gif" style="border:none;cursor:hand;" border="0" onClick="agendar();"/>
                    </acesso:controlHiddenItem>
                    <acesso:controlHiddenItem nomeIdentificador="fid_dea_vaipensar">
                        <img src="<%=request.getContextPath()%>/resources/images/bt_vaipensar_nrml.gif"
                        	 border="0"
                        	 class="button"
                        	 onclick="cancela('vaipensar');return false;" />
                    </acesso:controlHiddenItem>
                </td>
                <td align="center" valign="bottom" width="100">
                    <div id="divAcoes">
                        <acesso:controlHiddenItem nomeIdentificador="fid_dea_reter">
                            <img class="button"
                            	 action="reterAparelho.do?acao=ok"
                            	 src="<%=request.getContextPath()%>/resources/images/bt_reter_nrml.gif"
                            	 onClick="reter(this);return false;" />
                        </acesso:controlHiddenItem>
                    </div>
                </td>
            </tr>
        </table>
    </form>
    <div id="divCartaoCredito" style="position:absolute; top:185; left:150;visibility: hidden">
        <table width="80%" border="0">
            <tr>
                <td width="45%" align="right">Cartão:</td>
                <td width="10%">
	                <select style="width=100px;">
	                	<option value=""></option>
	                </select> 
                </td>
            </tr>
            <tr>
                <td width="20%" align="right">Numero:</td>
                <td width="10%">
	                <select class="textfield" size="15">
	                	<option value=""></option>
	                </select>                 
          
                	</td>
                <td width="55%">Validade: <input class="textfield" size="5" /></td>
            </tr>
        </table>
    </div>
    <div id="divDebitoConta" style="position:absolute; top:185; left:150;visibility: hidden">
        <table width="80%" border="0">
            <tr>
                <td width="45%" align="right">Banco:</td>
                <td width="10%"><select style="width=100px;"><option value=""></option></select></td>
            </tr>
            <tr>
                <td width="20%" align="right">Agencia:</td>
                <td width="10%"><select style="width=100px;"><option value=""></option></select></td>
                <td width="55%" >Conta:<select style="width=100px;"><option value=""></option></select></td>
            </tr>
        </table>
    </div>
    <script>
        <!--
            f = document.forms[0];
            f.rua.readOnly = true;
            f.numero.readOnly = true;
            f.complemento.readOnly = true;
            f.bairro.readOnly = true;
            f.cidade.readOnly = true;
            f.idUFSelecionado.disabled = true;
            f.cep.readOnly = true;
        -->
    </script>
    <% if (request.getAttribute("idMotivoAlteracaoSelecionado") != null) { %>
    <script language="javascript">
        <!--
            <logic:notEqual name="param" property="inTipoPessoa" value="PJ">
            f = document.forms[0];
            f.inAlteracaoEndereco.checked = true;
            f.inAlteracaoEndereco.value = "1";
            verificaAlteracaoEndereco(f.inAlteracaoEndereco.checked);
            </logic:notEqual>
        -->
    </script>
    <% } %>
    <vivo:alert atributo="msgSAP" scope="request"/>
    <%--vivo:quadroFlutuante id="dvObs" idIframe="ifrmObservacao" height="200" width="300" spacesTop="80" spacesLeft="100" display="none" url="<%=request.getContextPath()%>" label=""/--%>
	<vivo:quadroFlutuante id="dvPesqEndereco" idIframe="ifrmPesqEndereco" width="750" height="240" spacesTop="0" spacesLeft="0" display="none" url="<%=request.getContextPath()%>" label="Pesquisa de Endereço" onclick="fecharPesquisaEndereco()"/>
    </vivo:body>
    </div>
</acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>
