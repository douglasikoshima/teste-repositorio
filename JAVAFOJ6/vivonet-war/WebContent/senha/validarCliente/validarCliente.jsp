<!--
Módulo.....: Gestão de Senhas
Caso de Uso: Validar Cliente
Versão.....: $Author: a5112272 $ - $Revision: 1.5 $ - $Date: 2011/06/14 14:34:06 $
-->
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<bean:define id="form" name="validarClienteForm"/>
<bean:define id="campanhas" name="form" property="campanhasVO"/>

<html>
    <head>
        <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" rel='stylesheet' type='text/css'>
    </head>
    <body>
    <acesso:controlHiddenItem nomeIdentificador="wor_vc_verpagina"> 
    <html:form action="transferirUraAction">
        <div id="detalhe" style="z-index:999 ;visibility: hidden; position:absolute;"><img src="<%=request.getContextPath()%>/resources/images/transp.gif" style="z-index:999; visibility: visible; width:13px; height:41px; top:0; left:0;"/></div>
        <table id="tabelaUra" width="790" height="46" border="0" align="center" cellpadding="0" cellspacing="0" background="<%=request.getContextPath()%>/resources/images/bura_new_bg.gif" bgcolor="#e2e2e2" class="bura_texto">
            <tr>
                <td width="410">
                    <table cellpadding="0" cellspacing="0" border="0" width="410">
                        <tr>
                            <td width="20"></td>
                            <td id="tdNrOrigem" width="72">Nº de Origem:</td>
                            <td width="86" style="padding-left:2px;"><label class="bura_telefone"><bean:write name="form" property="numeroOrigem" /></label></td>
                            <td width="75" align="right">
                            <acesso:controlHiddenItem nomeIdentificador="wor_vc_NagegacaoUra">
                            <img id="imgUra" src="<%=request.getContextPath()%>/resources/images/bt_navegacao_nrml.gif" onmouseover="this.src='<%=request.getContextPath()%>/resources/images/bt_navegacao_over.gif'" onmouseout="this.src='<%=request.getContextPath()%>/resources/images/bt_navegacao_nrml.gif'" onClick="parent.abraLupa('Navegação na URA', '/FrontOfficeWeb/senha/mostrarNavegacaoURA/begin.do', 700, 300);" style="cursor:hand; border:none;"/>
                            </acesso:controlHiddenItem>
                            </td>
                            <td width="15"></td>
                            <td width="142" rowspan="2" valign="middle" align="center" style="line-height:15px;;">
                                <logic:equal name="form" property="senhaValidada" value="false">
                                    <label style="color=red;"><b>Senha não validada</b></label><br>
                                </logic:equal>
                                <logic:equal name="form" property="senhaValidada" value="true">
                                    <logic:equal name="form" property="cliente" value="true">
                                        <label style="color=green; background-color=yellow;"><b>Cliente</b></label><br>
                                    </logic:equal>
                                    <logic:equal name="form" property="usuario" value="true">
                                        <label style="color=green; background-color=yellow;"><b>Usuário</b></label><br>
                                    </logic:equal>
                                    <label style="color=green;"><b>Senha validada</b></label>
                                </logic:equal>
                                <logic:equal name="form" property="senhaValidada" value="false">
                                    <label style="background:inherit; background-color:#e2e2e2; border:0px">ID Pós</label>
                                    <html:select name="form" property="idPos" style="width:42px; height:14px" onchange="submitIdPos();"> 
                                        <html:option value="1">Não</html:option>
                                        <html:option value="2">Sim</html:option>
                                    </html:select>
                                    <html:select name="form" property="tipoRelacionamento" style="width:57px; height:14px"> 
                                        <html:option value="1">Usuário</html:option>
                                        <html:option value="2">Cliente</html:option>
                                        <html:option value="6">Não Cliente/Prospect</html:option>
                                    </html:select>
                                </logic:equal>
                            </td>
                            <td width="6"></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td style="padding-top:3px;">Falando com :</td>
                            <td colspan="2" style="padding-top:3px;">
                                <html:text name="form" property="nomeContato" styleClass="textfield" style="width:160px; height:15px; line-height: 9px;"/>
                            </td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td colspan="7"></td>
                        </tr>
                    </table>
                </td>
                <td width="380">
                    <table cellpadding="0" cellspacing="0" border="0" width="380">
                        <tr>
                            <td width="20">&nbsp;</td>
                            <td width="187" rowspan="2">
                                <div id="idDivCampanha" style="display:none;">
                                    <table border="0" cellpadding="0" cellspacing="0">
                                        <tr>
                                            <td style="padding-left:3px;">
                                                <html:select name="form" property="numCampanhaSel" style="width=100px" onchange="trocarCampanha(this.value)">
                                                    <html:option value="-1" key="numCampanhaSel">&nbsp;</html:option>
                                                    <html:options collection="campanhas" property="idRetornoWFCTI" labelProperty="dsRetornoWFCTI"/> 
                                                </html:select>
                                            </td>
                                            <td style="padding-left:5px;">
                                            <acesso:controlHiddenItem nomeIdentificador="wor_vc_transferir">
                                                <img id="imgTransferir" src="/FrontOfficeWeb/resources/images/bt_transferir_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_transferir_over.gif" onClick="transferir(); return false" style="border:none;"/>
                                            </acesso:controlHiddenItem>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                                <div id="idDivChamado" style="display:none;">
                                    <table border="0" cellpadding="0" cellspacing="0" width="100%">
                                        <tr>
                                            <td align="center">
                                            <acesso:controlHiddenItem nomeIdentificador="wor_vc_iniciarch">
                                                <img id="imgIniciarChamado" src="/FrontOfficeWeb/resources/images/bt_iniciar_transf_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_iniciar_transf_over.gif" onClick="iniciarChamado(); return false" style="border:none;"/>
                                            </acesso:controlHiddenItem>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                            </td>
                            <td id="tdDisponibilidade" width="40" align="right"><img id="imgDisponibilidade" src="<%=request.getContextPath()%>/resources/images/icon_atend_disponivel.gif" onclick="mudaDisponibilidade()" style='cursor:hand' border="0">
                            </td>
                            <td width="56" rowspan="2" align="center" valign="top">
                                <img id="idImgSmiley" style="cursor:hand" src="<%=request.getContextPath()%>/resources/images/smiley_satisfeito.gif" width="42" height="41" onclick="smiley.style.visibility='visible';">
                            </td>
                            <td width="79" rowspan="2" valign="middle">
                                <table cellpadding="0" cellspacing="0" border="0">
                                    <tr>
                                        <td height="50%" valign="middle">
                                            <acesso:controlHiddenItem nomeIdentificador="wor_vc_terminar">
                                                <img id="imgTerminar" src="<%=request.getContextPath()%>/resources/images/bt_terminar_nrml.gif" onmouseover="this.src='<%=request.getContextPath()%>/resources/images/bt_terminar_over.gif'" onmouseout="this.src='<%=request.getContextPath()%>/resources/images/bt_terminar_nrml.gif'" onclick="submitTerminar();" style="cursor:hand; border:none;"/>
                                            </acesso:controlHiddenItem>
                                        </td>
                                    </tr>
                                    <logic:equal name="form" property="novaLigacao" value="true">
                                    <tr>
                                        <td height="50%" valign="middle">
                                            <img id="imgLigacaoPendente" src="<%=request.getContextPath()%>/resources/images/ic_ligacao_pend_nrml.gif"  onmouseover="this.src='<%=request.getContextPath()%>/resources/images/ic_ligacao_pend_over.gif'" onmouseout="this.src='<%=request.getContextPath()%>/resources/images/ic_ligacao_pend_nrml.gif'" style="cursor:hand" onclick="mostraNovaLigacao();"/>
                                        </td>
                                    </tr>
                                    </logic:equal>                                           
                                </table>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table> 
        <div id="divNovaLigacao" style="z-index:999; position:absolute; left:0; top:0; visibility:hidden;">            
            <logic:equal name="form" property="novaLigacao" value="true">
                <bean:define id="formNovaLigacao" name="form" property="novaLigacaoForm"></bean:define>
                 <table id="tabelaNovaLigacao" width="790" height="41" border="0" align="left" cellpadding="0" cellspacing="0" bgcolor="#e2e2e2" class="bura_texto">
                    <tr>
                        <td width="36" rowspan="2"><img src="<%=request.getContextPath()%>/resources/images/bura_bg2.gif"/></td>
                        <td width="100" align="right">Nr. Telefone:</td>
                        <td width="80" align="left" style="padding-left:2px;"><bean:write name="formNovaLigacao" property="numeroOrigem"/></td>
                        <td width="80" align="right">Nr. Linha:</td>
                        <td width="80" align="left" style="padding-left:2px;"><bean:write name="formNovaLigacao" property="numeroConsultado"/></td>
                        <td align="center" width="120"  >
                            <logic:equal name="formNovaLigacao" property="cliente" value="true">
                                <label style="color=green; background-color=yellow;" ><b>Cliente</b></label>
                            </logic:equal>
                            <logic:equal name="formNovaLigacao" property="usuario" value="true">
                                <label style="color=green; background-color=yellow;"><b>Usuário</b></label>
                            </logic:equal>
                        </td>
                        <td rowspan="2" align="center" valign="middle">
                            <img id="imgVoltar" src="<%=request.getContextPath()%>/resources/images/bt_voltar_nrml.gif" onmouseover="this.src='<%=request.getContextPath()%>/resources/images/bt_voltar_over.gif'" onmouseout="this.src='<%=request.getContextPath()%>/resources/images/bt_voltar_nrml.gif'" onclick="voltarAtendimentoAnterior();" style="cursor:hand; border:none;"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">IdUra:</td>
                        <td align="left" style="padding-left:2px;"><bean:write name="formNovaLigacao" property="idUra"/></td>
                        <td align="right">IdCallCenter:</td>
                        <td align="left" style="padding-left:2px;"><bean:write name="formNovaLigacao" property="idCallCenter"/></td>
                        <td align="center" >
                            <logic:equal name="formNovaLigacao" property="senhaValidada" value="false">
                                <label style="color=red;"><b>Senha não validada</b></label>
                            </logic:equal>
                            <logic:equal name="formNovaLigacao" property="senhaValidada" value="true">
                                <label style="color=green;"><b>Senha validada</b></label>
                            </logic:equal>
                        </td>
                        
                    </tr>
                </table>
            </logic:equal>
        </div>        
        <div id="smiley" style="z-index:999; background-color:#000000; position:absolute; left:660; top:0; visibility:hidden;">
            <table background="<%=request.getContextPath()%>/resources/images/smiley_bg.gif" width="132" height="43" border="0" cellpadding="0" cellspacing="0">
                <tr>
                    <td colspan="4"><img src="<%=request.getContextPath()%>/resources/images/transp.gif" width="1" height="1"></td>
                </tr>
                <tr>
                    <td width="2"></td>
                    <td width="41" valign="top"><img src="<%=request.getContextPath()%>/resources/images/smiley_satisfeito_pop.gif" onclick="idImgSmiley.src = '<%=request.getContextPath()%>/resources/images/smiley_satisfeito.gif'; smiley.style.visibility = 'hidden';" style='cursor:hand'></td>
                    <td width="43" valign="top"><img src="<%=request.getContextPath()%>/resources/images/smiley_imparcial_pop.gif" onclick="idImgSmiley.src = '<%=request.getContextPath()%>/resources/images/smiley_imparcial.gif'; smiley.style.visibility = 'hidden';" style='cursor:hand'></td>
                    <td valign="top"><img src="<%=request.getContextPath()%>/resources/images/smiley_insatisfeito_pop.gif" onclick="idImgSmiley.src = '<%=request.getContextPath()%>/resources/images/smiley_insatisfeito.gif'; smiley.style.visibility = 'hidden';" style='cursor:hand'></td>
                </tr>
            </table>
        </div>  
        <div id="divFrameEscondido"  style="z-index:999; position:absolute; left:0; top:0; visibility:hidden;">
            <iframe id="frameEscondido" name="frameEscondido" frameborder="0" scrolling="no" height="0" width="0" style="top:0; left: 0;"></iframe>
        </div>  
        <html:hidden property="numeroConsultado" name="form"/> 
        <html:hidden property="terminar" name="form"/> 
        <html:hidden property="identificacaoPositiva" name="form"/>
    </html:form>
    </acesso:controlHiddenItem>
    </body>
</html>
<script language="javascript">

    //mostra observaçao da CTI
    <logic:notEqual name="form" property="observacao" value="">
        alert("Observaçao Atendente: "+"<bean:write name="form" property="observacao" />");
    </logic:notEqual>
    
    /**
     *   seta a campanha aberta
    */ 
    function setaCampanha()
    {            
        var campanhas = top.frameCTI.obtemCampanhas();
        var posHifen = campanhas.search('-');
        
        if(posHifen >= 0) 
        {
            var campanhaAnterior = campanhas.substr(0, posHifen);
            campanhaAnterior = Trim(campanhaAnterior);
            
            var estadoCampanha = campanhas.substr(posHifen+1,campanhas.length);
            estadoCampanha = Trim(estadoCampanha);
            
            oOption = document.getElementById('numCampanhaSel');
            for(i = 0; i < oOption.options.length; i++ ) 
            {      
                if(oOption.options(i).innerText == campanhaAnterior) 
                {
                    oOption.options(i).selected=true;
                }
            }
        }    
        
        //verifica se tem ligacao caso tenha disabilita combos
        if(obtemEstadoLigacaoCTI()!=2)
        {
            document.getElementById('numCampanhaSel').disabled = true;   
            parent.document.getElementById('grupoSel').disabled = true;   
            <logic:equal name="form" property="novaLigacao" value="true">     
                document.getElementById('imgTransferir').style.display = 'none';     
            </logic:equal>
        }
        else
        {
            document.getElementById('imgTransferir').style.display = 'none';
            
        }        
    }
    
    var isAgenteLogado;
    
    /**
     *   verifica se o objeto OCX está carregado
    */ 
    if(top.frameCTI.isCarregadoOCX())
    {
        isAgenteLogado = top.frameCTI.isAgenteLogado();
    }
    else
    {
        isAgenteLogado = top.frameCTI.isCarregadoOCX();
    }
        
    /**
     *   verifica se irá mostrar combo com campanhas ou botão iniciar
    */ 
    if(isAgenteLogado) {
        // houve login no CTI com sucesso
        document.getElementById('tdDisponibilidade').style.display = '';
        document.getElementById('idDivCampanha').style.display = '';
        document.getElementById('idDivChamado').style.display = 'none';
        document.getElementById('imgUra').style.display = '';
        document.getElementById('tdNrOrigem').innerText = 'Nº de Origem:';
        
        //seta campanha selecionada 
        setaCampanha();
    }
    else {
        // NÃO houve login no CTI com sucesso
        document.getElementById('tdDisponibilidade').style.display = 'none';
        document.getElementById('idDivCampanha').style.display = 'none';
        document.getElementById('idDivChamado').style.display = '';
        document.getElementById('imgUra').style.display = 'none';
        document.getElementById('tdNrOrigem').innerText  = '';
    }
            
    /**
     *   se não esteja carregado OCX ou não tenha login CTI
     *   idChamadaTelefonica é carregado ao clicar em inicia chamada
    */ 
    if(!isAgenteLogado) {          
        top.frameApp.idChamadaTelefonica = '<bean:write name="form" property="idChamadaTelefonica"/>';
    }
    
    //verifica se identificaçao positiva = sim
    <logic:equal name="form" property="idPos" value="2">
    top.frameApp.idRelacionamento = '<bean:write name="form" property="tipoRelacionamento"/>';
    </logic:equal>    
    <logic:equal name="form" property="senhaValidada" value="true">
    top.frameApp.idRelacionamento = '<bean:write name="form" property="tipoRelacionamento"/>';
    </logic:equal>
    
    /*
    *   Verifica se irá desabilitar idPos
    *   caso idTipoRelacionamento != 1,2 ou 6
    */
    if((top.frameApp.idRelacionamento!='')&&(top.frameApp.idRelacionamento!='1')&&(top.frameApp.idRelacionamento!='2')&&(top.frameApp.idRelacionamento!='6')){
        document.getElementById('idPos').disabled = true;   
    }
    
    /**
     *   verifica se houve uma identificaçao positiva
     *   se sim carrega a tela inicial novamente
    */ 
    if(document.forms["validarClienteForm"].identificacaoPositiva.value != "") {
        if(document.forms["validarClienteForm"].identificacaoPositiva.value == "S") {
            telaInicial('idPositiva');
        }
    }
    
    /**
     *   verifica se irá terminar atendimento
     *   caso termine e aja ligaçao pendente irá carregá-la
    */  
    if(document.forms["validarClienteForm"].terminar.value != "") {
        if(document.forms["validarClienteForm"].terminar.value == "S") {
            telaInicial('inicioTela');
        }
        else 
            if(document.forms["validarClienteForm"].terminar.value == "N") {
                carregaLigacaoPendente();
            }
    }
    
    /**
     *   mostra antendimento anterior
    */
    function voltarAtendimentoAnterior() {
        document.getElementById('divNovaLigacao').style.visibility='hidden';
        document.getElementById('tabelaUra').style.display = '';
    }
    
    /**
     *   mostra ligacao pendente
    */
    function mostraNovaLigacao() {
        document.getElementById('divNovaLigacao').style.visibility='visible';
        document.getElementById('tabelaUra').style.display = 'none';
    }
    
    /**
     *   carrega ligacao pendente
    */
    function carregaLigacaoPendente() {
        <logic:equal name="form" property="novaLigacao" value="true">
            <bean:define id="formNovaLigacao" name="form" property="novaLigacaoForm"></bean:define>
            var strAction = "TelaInicial.do?";
            strAction += "idUra=<bean:write name="formNovaLigacao" property="idUra"/>";
            strAction += "&";
            strAction += "idCallCenter=<bean:write name="formNovaLigacao" property="idCallCenter"/>";
            strAction += "&";
            strAction += "inSenhaValidada=<bean:write name="formNovaLigacao" property="senhaValidada"/>";
            strAction += "&";
            strAction += "nrLinha=<bean:write name="formNovaLigacao" property="numeroConsultado"/>";
            strAction += "&";
            strAction += "nrTelefone=<bean:write name="formNovaLigacao" property="numeroOrigem"/>";
            strAction += "&";
            strAction += "observacao=<bean:write name="formNovaLigacao" property="observacao"/>";
            strAction += "&";
            strAction += "timeoutURA=<bean:write name="formNovaLigacao" property="indRedirTimeoutURA"/>";            
            strAction += "&";
            strAction += "idTipoRelacionamento=<bean:write name="formNovaLigacao" property="tipoRelacionamento"/>";
            
            top.frameApp.document.forms[0].method = "POST";
            top.frameApp.document.forms[0].target = "";
            top.frameApp.document.forms[0].action = strAction;  
            top.frameApp.document.forms[0].submit();        
        </logic:equal> 
    }
    
    /**
     *   verifica se o objeto OCX está carregado
    */
    if(top.frameCTI.isCarregadoOCX())
    {
        // obtém a disponibilidade atual
        obtemDisponibilidadeCTI();
    }
    
    /**
     *   Faz identificaçao positiva
    */
    function submitIdPos() {        
    
        var vidProspect
        vidProspect = 'FALSE';
        <%if(request.getSession().getAttribute("idProspect")!=null){%>
            vidProspect = 'TRUE';
        <%}%>
        if (document.forms["validarClienteForm"].numeroConsultado.value == '' && vidProspect=='FALSE') {
                    document.forms["validarClienteForm"].idPos.selectedIndex = 0; 
                    alert("Não existe pesquisa!");
                    return false;
        }else if(vidProspect=='TRUE' && ((document.forms["validarClienteForm"].tipoRelacionamento.selectedIndex == 0) || (document.forms["validarClienteForm"].tipoRelacionamento.selectedIndex == 1))){
                    alert('Relacionamento não pode ser Cliente ou Usuário');
                    document.forms["validarClienteForm"].idPos.selectedIndex = 0;
                    return false;
        }else if(vidProspect=='FALSE' && (document.forms["validarClienteForm"].tipoRelacionamento.selectedIndex == 2)){
                    document.forms["validarClienteForm"].idPos.selectedIndex = 0;
                    alert('Relacionamento deve ser Cliente ou Usuário');
                    return false;
        }
        
        //Liga animação
        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();

        document.forms["validarClienteForm"].method = "POST";
        document.forms["validarClienteForm"].action = "/FrontOfficeWeb/senha/validarCliente/idPosAction.do";
        document.forms["validarClienteForm"].target = "";
        document.forms["validarClienteForm"].submit();
    }
    
    /**
     *
    */
    function submitTerminar() {
        //Liga animação
        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();

        top.frameApp.idChamadaTelefonica = '';
        document.forms["validarClienteForm"].method = "POST";
        document.forms["validarClienteForm"].action = "/FrontOfficeWeb/senha/validarCliente/terminarAction.do";
        document.forms["validarClienteForm"].target = "";
        document.forms["validarClienteForm"].submit();
    }
        
    function transferir() {
        //Liga animação
        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
        
        parent.dvTransferir.style.display = '';
        
        document.forms["validarClienteForm"].method = "POST";
        document.forms["validarClienteForm"].action = "/FrontOfficeWeb/senha/validarCliente/transferirCarregar.do";
        document.forms["validarClienteForm"].target = "ifrmTransferir";
        document.forms["validarClienteForm"].submit();
    }
    
    function completarTerminar() {
        parent.dvTransferir.style.display = 'none';
        submitTerminar();
    }
    
    /**
     *   Faz chamada a telaInicial
    */
    function telaInicial(inicioTela) {
       // top.frameApp.location = '<%=request.getContextPath()%>/inicio.jsp';
       
        top.frameApp.document.forms[0].method = "POST";
        top.frameApp.document.forms[0].target = "";
        
        if(inicioTela!='' && inicioTela=='inicioTela'){
            top.frameApp.document.forms[0].action = "/FrontOfficeWeb/cliente/TelaInicial/TelaInicial.do?inicioTela=TRUE";
        }else if(inicioTela=='idPositiva'){
            top.frameApp.document.forms[0].action = "/FrontOfficeWeb/cliente/TelaInicial/TelaInicial.do?idPositiva=TRUE";
        }else{
            top.frameApp.document.forms[0].action = "/FrontOfficeWeb/cliente/TelaInicial/TelaInicial.do";
        }
        top.frameApp.document.forms[0].submit(); 
    }
    
    /**
     *   Troca campanha atual selecionada
    */
    function trocarCampanha(itemValue) {
        // se tem campanha aberta deve fechar a campanha
        var campanhas = top.frameCTI.obtemCampanhas();
        
        var posHifen = campanhas.search('-');
        
        //se há campanha anterior, deve ser fechada
        if(posHifen >= 0) {
            var campanhaAnterior = campanhas.substr(0, posHifen);
            campanhaAnterior = Trim(campanhaAnterior);
            
            //marcar inDisponibilidade
            top.frameCTI.alterarEstadoEspecifico('N');
            
            top.frameCTI.fecharCampanha(campanhaAnterior);
        }

        if(itemValue != -1) {
            oOption = document.getElementById('numCampanhaSel');
            for(i = 0; i < oOption.options.length; i++ ) {
                if(oOption.options(i).value == itemValue) {
                    // abrir a nova campanha
                    top.frameCTI.abrirCampanha(oOption.options(i).text);
                    
                    //marcar disponibilidade 
                    top.frameCTI.alterarEstadoEspecifico('S');
                    
                    break;
                }
            }
        }
        
        obtemDisponibilidadeCTI();
    }
    
    /**
     *   Obtem disponibilidade do agente
     *   caso esteja logado e disponivel seta como disponivel
    */
    function obtemDisponibilidadeCTI() {
        var estadoAgente = top.frameCTI.obtemEstadoAgente();
        var loginOk = estadoAgente.search('logado');
        var disponivelOk = estadoAgente.search('disponivel');
        
        if(loginOk >= 0) { 
            if(disponivelOk >= 0) {
                document.getElementById('imgDisponibilidade').src = '<%=request.getContextPath()%>/resources/images/icon_atend_disponivel.gif';
            }
            else {
                document.getElementById('imgDisponibilidade').src = '<%=request.getContextPath()%>/resources/images/icon_atend_indisponivel.gif';
            }
        }
        else {
            document.getElementById('imgDisponibilidade').src = '<%=request.getContextPath()%>/resources/images/icon_atend_indisponivel.gif';
        }
    }
    
    /**
     *   Muda disponibilidade do agente
     *   caso disponivel marca como indisponivel e vice-versa
    */
    function mudaDisponibilidade() {
    
        //verifica se o objeto OCX está carregado
        if(top.frameCTI.isCarregadoOCX())
        {
            if(obtemEstadoLigacaoCTI()!=1)
            {
                // Executa chamada ao cti para marcar como disponível
                top.frameCTI.alterarEstado();
        
                //obtém a disponibilidade do usuário quanto ao CTI
                obtemDisponibilidadeCTI();
            }
            else
            {
                alert('Estado com Ligação');
            }
        }
    }    
    
    /**
     *   Obtem o estado da ligaçao
     *   1 - com ligaçao
     *   2 - sem ligaçao
    */
    function obtemEstadoLigacaoCTI() {
    
        var estadoLigacao = top.frameCTI.obtemEstadoLigacao();
        
        var comLigacao = estadoLigacao.search('Com');
        var semLigacao = estadoLigacao.search('Sem');
        
        if(comLigacao >= 0) { 
            return 1;
        }
        else{
            if(semLigacao >= 0){
                return 2;
            }
        }
    }    
    
    /**
     *   Inicia uma chamadaTelefonica
    */
    function iniciarChamado() {
    
        <logic:equal name="form" property="idPos" value="">
            alert("Tem que identificar a ligação!");
            return;
        </logic:equal>
        if ((top.frameApp.idChamadaTelefonica != '') && (top.frameApp.idChamadaTelefonica != '1')){
            alert("Já existe chamada!");
            return;
        }
        
        //Liga animação
        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
        
        document.forms["validarClienteForm"].method = "POST";
        document.forms["validarClienteForm"].action = "/FrontOfficeWeb/senha/validarCliente/iniciarChamada.do";
        document.forms["validarClienteForm"].target = "";
        document.forms["validarClienteForm"].submit();
    }
    
    // Função de Trim, pois o javascript não tem uma função/método correspondente
    function Trim(s) {
        // Remove leading spaces and carriage returns
        while ((s.substring(0,1) == ' ') || (s.substring(0,1) == '\n') || (s.substring(0,1) == '\r')) {
            s = s.substring(1,s.length);
        }
        
        // Remove trailing spaces and carriage returns
        while ((s.substring(s.length-1,s.length) == ' ') || (s.substring(s.length-1,s.length) == '\n') || (s.substring(s.length-1,s.length) == '\r')) {
            s = s.substring(0,s.length-1);
        }
        return s;
    }
    
    //Fim animação
    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
    
</script>