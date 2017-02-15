<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="validarClienteForm"/>
<bean:define id="campanhas" name="form" property="campanhasVO"/>

<html>
    <head>
        <link href="../../resources/css/frontoffice.css" rel='stylesheet' type='text/css'>
        <script type="text/javascript" src="../../resources/scripts/validacioncliente.js"></script>
        <script type="text/javascript" src="../../resources/scripts/telainicial_atual.js"></script>
    </head>
    
    <body>
    <!--acesso:controlHiddenItem nomeIdentificador="wor_vc_verpagina"--> 
    <html:form action="/cliente/TelaInicial/transferirUraAction.do" method="POST" onsubmit="return false;">
        <div id="detalhe" style="z-index:999 ;visibility: hidden; position:absolute;"><img src="<%=request.getContextPath()%>/resources/images/transp.gif" style="z-index:999; visibility: visible; width:13px; height:41px; top:0; left:0;"/></div>
        <table id="tabelaUra" width="790" height="46" border="0" align="center" cellpadding="0" cellspacing="0" background="../../resources/images/bura_new_bg.gif" bgcolor="#e2e2e2" class="bura_texto">
            <tr>
                <td width="410">
                    <table cellpadding="0" cellspacing="0" border="0" width="410">
                        <tr>
                            <td width="20"></td>
                            <td id="tdNrOrigem" width="72">Nº de Origem:</td>
                            <td width="86" style="padding-left:2px;"><label class="bura_telefone"><span id="spNrOrigem"><bean:write name="form" property="numeroOrigem" /></span></label></td>
                            <td width="75" align="right">
                            <!--acesso:controlHiddenItem nomeIdentificador="wor_vc_NagegacaoUra"-->
                            <img id="imgUra" src="../../resources/images/bt_navegacao_nrml.gif" onClick="abre();" style="cursor:hand; border:none;"/>
                            <!--/acesso:controlHiddenItem-->
                            </td>
                            <td width="15"></td>
                            <td width="142" rowspan="2" valign="middle" align="center" style="line-height:15px;;">
                                <div id="dvSenhaValidadaTrue" style="display=none;">
                                    <label style="color=green; background-color=yellow;"><b><span id="sp_Cli_user"></span></b></label><br>
                                    <label style="color=green;"><b>Senha validada</b></label>
                                </div>
                                <div id="dvSenhaValidadaFalse" style="display=none;">
                                    <label style="color=red;"><b>Senha não validada</b></label><br>
                                    <html:select name="form" property="tipoRelacionamento" style="width:57px; height:14px" onchange="guardaIds()"> 
                                        <html:option value="-1">&nbsp;</html:option>
                                        <html:option value="1">Usuário</html:option>
                                        <html:option value="2">Cliente</html:option>
                                        <html:option value="6">Não Cliente/Prospect</html:option>
                                    </html:select>
                                    <label style="background:inherit; background-color:#e2e2e2; border:0px">ID Pos</label>
                                    <html:select name="form" property="idPos" style="width:42px; height:14px" onchange="guardaIds();submitIdPos();"> 
                                        <html:option value="0">Não</html:option>
                                        <html:option value="1">Sim</html:option>
                                    </html:select>
                                </div>
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
                                            <div id="dvImgTransferir">
                                            <!--acesso:controlHiddenItem nomeIdentificador="wor_vc_transferir"-->
                                                <img id="imgTransferir" src="/FrontOfficeWeb/resources/images/bt_transferir_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_transferir_over.gif" onClick="transferir(); return false" style="border:none;"/>
                                            <!--/acesso:controlHiddenItem-->
                                            </div>
                                            <div id="dvImgIniciarChamado" style="display:none;">
                                            <!--acesso:controlHiddenItem nomeIdentificador="wor_vc_iniciarch"-->
                                                <img id="imgIniciarChamado" src="/FrontOfficeWeb/resources/images/bt_iniciar_transf_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_iniciar_transf_over.gif" onClick="iniciarChamado(); return false" style="border:none;"/>
                                            <!--/acesso:controlHiddenItem-->
                                            </div>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                                <div id="idDivChamado" style="display:none;">
                                    <table border="0" cellpadding="0" cellspacing="0" width="100%">
                                        <tr>
                                            <td align="center">
                                            <!--acesso:controlHiddenItem nomeIdentificador="wor_vc_iniciarch"-->
                                                <img id="imgIniciarChamado" src="../../resources/images/bt_iniciar_transf_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_iniciar_transf_over.gif" onClick="iniciarChamado(); return false" style="border:none;"/>
                                            <!--/acesso:controlHiddenItem-->
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                            </td>
                            <td width="40" align="right"><div id="tdDisponibilidade"><img id="imgDisponibilidade" src="../../resources/images/icon_atend_disponivel.gif" onclick="mudaDisponibilidade()" style='cursor:hand' border="0"></div>
                            </td>
                            <td width="56" rowspan="2" align="center" valign="top">
                                <img id="idImgSmiley" style="cursor:hand" src="../../resources/images/smiley_satisfeito.gif" width="42" height="41" onclick="smiley.style.visibility='visible';">
                            </td>
                            <td width="79" rowspan="2" valign="middle">
                                <div id="dvLigacaoPendente" style="display=none;">
                                    <table cellpadding="0" cellspacing="0" border="0">
                                        <tr>
                                            <td height="50%" valign="middle">
                                                <!--acesso:controlHiddenItem nomeIdentificador="wor_vc_terminar"-->
                                                    <img id="imgTerminar" src="../../resources/images/bt_terminar_nrml.gif" onclick="submitTerminar();" style="cursor:hand; border:none;"/>
                                                <!--/acesso:controlHiddenItem-->
                                            </td>
                                        </tr>
                                        <tr>
                                            <td height="50%" valign="middle">
                                                <img id="imgLigacaoPendente" src="../../resources/images/ic_ligacao_pend_nrml.gif"  style="cursor:hand" onclick="mostraNovaLigacao();"/>
                                            </td>
                                        </tr>                                          
                                    </table>
                                </div>
                                <div id="dvTerminar">
                                    <table cellpadding="0" cellspacing="0" border="0">
                                        <tr>
                                            <td height="50%" valign="middle">
                                                <!--acesso:controlHiddenItem nomeIdentificador="wor_vc_terminar"-->
                                                    <img id="imgTerminar" src="../../resources/images/bt_terminar_nrml.gif" onclick="submitTerminar();" style="cursor:hand; border:none;"/>
                                                <!--/acesso:controlHiddenItem-->
                                            </td>
                                        </tr>                                       
                                    </table>
                                </div>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table> 
        <!-- div que carrega a ligaçao pendente -->
        <div id="divNovaLigacao" style="z-index:999; position:absolute; left:0; top:0; visibility:hidden;">
        </div>        
        <div id="smiley" style="z-index:999; background-color:#000000; position:absolute; left:660; top:0; visibility:hidden;">
            <table background="../../resources/images/smiley_bg.gif" width="132" height="43" border="0" cellpadding="0" cellspacing="0">
                <tr>
                    <td colspan="4"><img src="<%=request.getContextPath()%>/resources/images/transp.gif" width="1" height="1"></td>
                </tr>
                <tr>
                    <td width="2"></td>
                    <td width="41" valign="top"><img src="<%=request.getContextPath()%>/resources/images/smiley_satisfeito_pop.gif" onclick="idImgSmiley.src = '../../resources/images/smiley_satisfeito.gif'; smiley.style.visibility = 'hidden';document.forms[0].idGrauSatisfacao.value=1;" style='cursor:hand'></td>
                    <td width="43" valign="top"><img src="<%=request.getContextPath()%>/resources/images/smiley_imparcial_pop.gif" onclick="idImgSmiley.src = '../../resources/images/smiley_imparcial.gif'; smiley.style.visibility = 'hidden';document.forms[0].idGrauSatisfacao.value=2;" style='cursor:hand'></td>
                    <td valign="top"><img src="<%=request.getContextPath()%>/resources/images/smiley_insatisfeito_pop.gif" onclick="idImgSmiley.src = '../../resources/images/smiley_insatisfeito.gif'; smiley.style.visibility = 'hidden';document.forms[0].idGrauSatisfacao.value=3;" style='cursor:hand'></td>
                </tr>
            </table>
        </div>  
        <div id="divFrameEscondido"  style="z-index:999; position:absolute; left:0; top:0; visibility:hidden;">
            <iframe id="frameEscondido" name="frameEscondido" frameborder="0" scrolling="no" height="0" width="0" style="top:0; left: 0;"></iframe>
        </div>  
        <html:hidden property="numeroConsultado" name="form"/> 
        <html:hidden property="idUra" name="form"/> 
        <html:hidden property="idCallCenter" name="form"/> 
        <html:hidden property="navegacaoURA" name="form"/> 
        <!--html:hidden property="senhaValidada" name="form"/-->
        <input type="hidden" name="senha" value=""/> 
        <html:hidden property="tipoProc" name="form"/> 
        <html:hidden property="numeroProc" name="form"/> 
        <html:hidden property="senhaValidada" name="form"/> 
        <!--html:hidden property="tipoRelacionamento" name="form"/--> 
        <input type="hidden" name="relacionamento" value=""/> 
        <input type="hidden" name="identificacao" value=""/>
        <html:hidden property="numeroOrigem" name="form"/> 
        <html:hidden property="observacao" name="form"/> 
        <html:hidden property="indRedirTimeoutURA" name="form"/> 
        
        <html:hidden property="terminar" name="form"/> 
        <html:hidden property="idGrauSatisfacao" name="form"/> 
        <html:hidden property="identificacaoPositiva" name="form"/>
    </html:form>
    <!--/acesso:controlHiddenItem-->
    </body>
</html>
<script language="javascript">
   
    var ligacaoCTI = false;
    var senhaValidadaCTI = false;
    var ligacaoCTIPendente = false;
    
    <logic:equal name="form" property="senhaValidada" value="true">
        dvSenhaValidadaTrue.style.display='';
        <logic:equal name="form" property="cliente" value="true">
        sp_Cli_user.innerHTML="Cliente";
        </logic:equal>
        <logic:equal name="form" property="usuario" value="true">
        sp_Cli_user.innerHTML="Usuário";
        </logic:equal>
    </logic:equal>
    // arrumar para carregamento com bindCombos
    <logic:equal name="form" property="senhaValidada" value="false">
        dvSenhaValidadaFalse.style.display='';
        <logic:equal name="form" property="cliente" value="true">
        document.forms["0"].tipoRelacionamento.selectedIndex = 1;
        </logic:equal>
        <logic:equal name="form" property="usuario" value="true">
        document.forms["0"].tipoRelacionamento.selectedIndex = 2;
        </logic:equal>
    </logic:equal>
    
    /**
    *   Quando recebe uma chamada telefonica carrega o id da chamada
    */     
    top.frameApp.idChamadaTelefonica = '<bean:write name="form" property="idChamadaTelefonica"/>';  
    
         
    /**
    *   Caso senha validade carrega o tipo de relacionamento
    */     
    <logic:equal name="form" property="senhaValidada" value="true">
    top.frameApp.idRelacionamento = '<bean:write name="form" property="tipoRelacionamento"/>';
    </logic:equal>
    
    
    var vidProspect
    vidProspect = 'FALSE';
    <%if(request.getSession().getAttribute("idProspect")!=null){%>
        vidProspect = 'TRUE';
    <%}%>
    
    
    <logic:notEqual name="form" property="observacao" value="">
    //mostra observaçao da CTI
    alert("Observaçao Atendente: "+"<bean:write name="form" property="observacao" />");
    </logic:notEqual>
    
    function abre(){
        var navegacaoURA = document.forms[0]["navegacaoURA"].value;
        var IndTimeoutRedirURA = document.forms[0]["indRedirTimeoutURA"].value;
        parent.abraLupa('Navegação na URA', '/FrontOfficeWeb/senha/mostrarNavegacaoURA/begin.do?navegacaoURA='+navegacaoURA+'&IndTimeoutRedirURA='+IndTimeoutRedirURA, 700, 300);
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
        //não exibiçao de botão iniciar
        document.getElementById('idDivChamado').style.display = 'none';
        document.getElementById('imgUra').style.display = 'none';
        document.getElementById('tdNrOrigem').innerText = '';
        
        //seta campanha selecionada 
        setaCampanha();
        
        // obtém a disponibilidade atual
        obtemDisponibilidadeCTI();
    }
    else {
        // NÃO houve login no CTI com sucesso
        document.getElementById('tdDisponibilidade').style.display = 'none';
        document.getElementById('idDivCampanha').style.display = 'none';
        //não exibiçao de botão iniciar
        document.getElementById('idDivChamado').style.display = 'none';
        document.getElementById('imgUra').style.display = 'none';
        document.getElementById('tdNrOrigem').innerText  = '';
    }
           
   
    /**
     *   carrega ligacao pendente
    */
    function carregaLigacaoPendente() {
    }
    
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
    
    //Fim animação
    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
    
</script>