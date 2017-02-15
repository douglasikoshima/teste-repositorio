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
<bean:define id="listaContatos"           name="Form" property="campanhaVO.admContatoVOArray" />
<bean:define id="listaCampanhas"          name="Form" property="campanhaVO.campanhaVOArray" />
<bean:define id="disponiveisDDD"          name="Form" property="campanhaVO.disponiveis.DDDVOArray" />
<bean:define id="disponiveisTipoLinha"    name="Form" property="campanhaVO.disponiveis.tipoLinhaVOArray" />
<bean:define id="disponiveisSegmentacao"  name="Form" property="campanhaVO.disponiveis.segmentacaoVOArray" />
<bean:define id="selecionadosDDD"         name="Form" property="campanhaVO.selecionados.DDDVOArray" />
<bean:define id="selecionadosTipoLinha"   name="Form" property="campanhaVO.selecionados.tipoLinhaVOArray" />
<bean:define id="selecionadosSegmentacao" name="Form" property="campanhaVO.selecionados.segmentacaoVOArray" />

<html>

<head>

    <script type="text/javascript" language="javascript">
		
        onload = function() {
            if( window.top.frameApp.dvAnimarAguarde != null ) window.top.frameApp.stopAnimation();
            <% if (Form.getCampanhaVO().getIdCampanha() != 0) { %>
            $('dsCodigo').readOnly = true;
            $('tpCampanha').disabled = true;
            <% } %>
            if ($('cdSGPConsulta').value != "") {
            	$('cdSGPConsulta').disabled = false;
            } 
            
			validaTelaQuestionario();
        }
		
		
        function closeWindow() {
            parent.$('divInclusaoAlteracao').style.display = 'none';
            top.frameApp.location.href = "begin.do";
        }

        function validarFormCampanha() {

            if (trim($F('nmCampanha'))=="") {
                alert("É necessário incluir o nome da campanha.");
                $('nmCampanha').focus();
                return false; }
            if (trim($F('dsCampanha'))=="") {
                alert("É necessário incluir a descrição da campanha.");
                $('dsCampanha').focus();
                return false; }
            if (trim($F('dsRegulamento'))=="" && $('inAceite').checked == true) {
                alert("É necessário incluir o regulamento da campanha.");
                $('dsRegulamento').focus();
                return false; }
            if (trim($F('idContato'))=="") {
                alert("É necessário selecionar o contato para registro de palitagem.");
                return false; }
                
            if ( $F('tpCampanha') == 1 ) {    

                if (trim($F('dsCodigo'))=="") {
                    alert("É necessário incluir o código da campanha.");
                    $('dsCodigo').focus();
                    return false; }
                    
                 if ($('inPrecedente').checked && trim($F('cdSGPConsulta'))=="") {
                    alert('Favor informar um código de consulta.');
                    return false; }                      
            } else {

                if (trim($F('dsUrlFinal'))=="") {
                    alert("É necessário informar uma URL final.");
                    $('dsUrlFinal').focus();
                    return false; 
                }            
                
                if (trim($F('dsUrlAutenticador'))=="") {
                    alert("É necessário informar o IP do autenticador.");
                    $('dsUrlAutenticador').focus();
                    return false; 
                }     

                document.forms[0].inPrecedente.value  = "0";
                document.forms[0].dsCodigo.value      = "";
            }
                
            if (validaData($F('dtValidadeDe'))==false
                    || validaData($F('dtValidadeAte'))==false
                    || validaDataFinal($F('dtValidadeDe'),$F('dtValidadeAte'))==false) {
                alert("É necessário incluir o período de validade da campanha.");
                $('dtValidadeDe').focus();
                return false; }
            if ($('selecionadosDDD').length == 0) {
                alert('É necessário selecionar ao menos um DDD.');
                return false; }
            if ($('selecionadosTipoLinha').length == 0) {
                alert('É necessário selecionar ao menos um Tipo de Linha.');
                return false; }
            if ($('selecionadosSegmentacao').length == 0) {
                alert('É necessário selecionar ao menos um Segmento.');
                return false; }
             
            return true;
        }

        function salvarCampanha() {
            var f = document.forms[0];
            if (validarFormCampanha()) {
                for (i=0;i<$('selecionadosDDD').length;i++) {
                    $('selecionadosDDD').options[i].selected = true;
                    $('selecionadosDDD').options[i].value = $('selecionadosDDD').options[i].value + "|" + $('selecionadosDDD').options[i].text;
                }
                for (i=0;i<$('selecionadosTipoLinha').length;i++) {
                    $('selecionadosTipoLinha').options[i].selected = true;
                    $('selecionadosTipoLinha').options[i].value = $('selecionadosTipoLinha').options[i].value + "|" + $('selecionadosTipoLinha').options[i].text;
                }
                for (i=0;i<$('selecionadosSegmentacao').length;i++) {
                    $('selecionadosSegmentacao').options[i].selected = true;
                    $('selecionadosSegmentacao').options[i].value = $('selecionadosSegmentacao').options[i].value + "|" + $('selecionadosSegmentacao').options[i].text;
                }
                if (top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
                f.submit();
            }
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
        
        function liberarCodigo() {
        
            $('cdSGPConsulta').value = "";
            if ($('inPrecedente').checked) {
                $('cdSGPConsulta').disabled = false;
            } else {
                $('cdSGPConsulta').disabled = true;
            }
        
        }
        
        
        function tipoCampanha() {
        
            var superPromocao = document.getElementById("divSuperPromocao");
            var campanha = document.getElementById("divCampanha");
            var rodape = document.getElementById("rodape");
                        
            if(document.forms[0].tpCampanha.value == 1) {
                campanha.style.visibility      = 'visible';
                superPromocao.style.visibility = 'hidden';
                
                rodape.style.position = "absolute";
                rodape.style.top      = 235;
                rodape.style.left     = 5;
                
            } else {
                campanha.style.visibility      = 'hidden';
                
                superPromocao.style.visibility = 'visible';
                superPromocao.style.position   = "absolute";
                superPromocao.style.top        = 130;
                
                rodape.style.position = "absolute";
                rodape.style.top = 190;
                rodape.style.left = 5;
                


            }
        }
        function alterarQuestionario()
        {
        
            
            if (top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();          
            $('divInclusaoAlteracao').style.display = "block";
            $('iframeInclusaoAlteracao').src = "callAlterarQuestionario.do";
        

        
        }

        function validaTelaQuestionario()
        {
            var imgQuestionario = document.getElementById("imagemQuestionario");      
            
         if(document.forms[0].inExibeQuestionario.checked == true) 
                    
                imgQuestionario.style.visibility = 'visible';                
            else
                imgQuestionario.style.visibility = 'hidden';  
         
        }

    </script>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js" ></script>
    <script type="text/javascript" language="javascript" src="/FrontOfficeWeb/resources/scripts/calendar.js"></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js" ></script>
    <script type="text/javascript" language="javascript" src="/FrontOfficeWeb/resources/scripts/vivoval.js" ></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/controleEventos.js"></script>
    <style type="text/css">

        textarea {
            font-family: Tahoma, Arial, Helvetica, sans-serif;
            font-size: 11px;
            color: #006699;
            background-color: #ffffff;
            border: 1px solid #000000;
            text-indent: 3px;
        }

    </style>

</head>

<body style="background-color:#ededed;">

<div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>

<form action="salvarCadastroAlteracaoCampanha.do" enctype="multipart/form-data" method="post" onSubmit="return false" name="campanhasForm">

<html:hidden name="Form" property="campanhaVO.idCampanha" styleId="idCampanha" />

<table cellpadding="2" cellspacing="1" style="margin-left:35px;">
    <tr>
        <td align="right" nowrap>Tipo de Cliente:</td>
        <td colspan="4">
             <html:select name="Form" property="campanhaVO.tpPessoa" styleId="tpPessoa" style="margin-left:3px;width:235px">
                <html:option value="1">Pessoa Fisica</html:option>
                <html:option value="2">Pessoa Juridica</html:option>
             </html:select>             
        </td>
    </tr>
    <tr>
        <td align="right" nowrap>Tipo da Campanha:</td>
        <td colspan="4">
             <html:select name="Form" property="campanhaVO.tpCampanha" styleId="tpCampanha" onchange="javascript:tipoCampanha();" style="margin-left:3px;width:235px">
                <html:option value="1">Promo&ccedil;&atilde;o</html:option>
                <html:option value="2">Campanha Parceiro</html:option>
             </html:select>             
        </td>
    </tr>
    <tr>
        <td align="right" nowrap>Nome da Campanha:</td>
        <td colspan="4">
             <html:text name="Form" property="campanhaVO.nmCampanha" styleId="nmCampanha" maxlength="50" style="width:550px;margin-left:3px;" />
        </td>
    </tr>
    <tr>
        <td align="right" valign="top">Descri&ccedil;&atilde;o:</td>
        <td colspan="4" valign="top">
             <html:textarea name="Form" property="campanhaVO.dsCampanha"
                                        styleId="dsCampanha"
                                        onkeyup="limitaQtdeCaracteres(this, 2000)"
                                        onblur="limitaQtdeCaracteres(this, 2000)"
                                        style="margin-left:3px;width:550px;height:45px;" />
        </td>
    </tr>
</table>

<div id="divCampanha">    
    <table cellpadding="2" cellspacing="1" style="margin-left:8px;">            
        <tr>
            <td align="right" valign="top">Regulamento:</td>
            <td colspan="4" valign="top">
                 <html:textarea name="Form" property="campanhaVO.dsRegulamento"
                                            styleId="dsRegulamento"
                                            onkeyup="limitaQtdeCaracteres(this, 2000)"
                                            onblur="limitaQtdeCaracteres(this, 2000)"
                                            style="margin-left:3px;width:550px;height:45px;" />
            </td>
        </tr>
        <tr>
             <td align="right">Exige Aceite:</td>
             <td colspan="4">
                <table border="0" cellpadding="0" cellspacing="0" width="90%">
                <tr>         
                    <td width="5%">
                         <html:checkbox name="Form" property="campanhaVO.inAceite"
                                                    styleId="inAceite"
                                                    style="border:none;margin-left:-3px;background:none;" />
                    </td>
                    <td align="right">Exibir consulta de Cupons:</td>
                    <td>
                         <html:checkbox name="Form" property="campanhaVO.inExibeCupom"
                                                    styleId="inExibeCupom"
                                                    style="border:none;margin-left:-3px;background:none;" />
                    </td>
                    <td align="right">Precedente:</td>
                    <td>
                             <html:checkbox name="Form" property="campanhaVO.inPrecedente"
                                                        styleId="inPrecedente"
                                                        style="border:none;margin-left:-3px;background:none;" />
                    </td>
                    <td align="right" width="20%">C&oacute;digo de Consulta:</td>
                    <td>
                        <html:text name="Form" property="campanhaVO.cdSGPConsulta" styleId="cdSGPConsulta" maxlength="15" size="15"  />
                    </td>
                </table>
              </td>
        </tr>
            <tr>
             <td align="right">Exibir consulta de Pr&ecirc;mios:</td>         
             <td>
                <table border="0" cellpadding="0" cellspacing="0" width="90%">
                <tr>         
                    <td width="5%">
                         <html:checkbox name="Form" property="campanhaVO.inExibePremio"
                                                    styleId="inExibePremio"
                                                    style="border:none;margin-left:-3px;background:none;" />
                    </td>
                    <td align="right" width="29%">Exibir questionario:</td>
                    <td>
                         <html:checkbox name="Form" property="campanhaVO.inExibeQuestionario"
                                                    styleId="inExibeQuestionario"
                                                     style="border:none;margin-left:-3px;background:none;" 
													onclick="validaTelaQuestionario();"/>
        


                    </td>        
                      <td>
                         <div id="imagemQuestionario" style="visibility:'hidden'; position:relative; top: 0px; left: 2px; height: 24px; padding: 0px;">
                            <img src="<%=request.getContextPath()%>\resources\images\bt_icon_propried.gif" style="cursor:pointer;margin-right:5px; vertical-align: middle; text-align: center;" title="Questionario" onclick="alterarQuestionario();" >Manter Questionario         
                        </div>   
                      
                      </td>
                </table>
              </td>
        </tr>
    </table>    
</div>

<div id="divSuperPromocao" style="visibility:'hidden';">
    <table cellpadding="2" cellspacing="1" style="margin-left:78px;">        
        <tr>
             <td align="right">URL Final:</td>
             <td colspan="4"><html:text name="Form" property="campanhaVO.dsUrlFinal" styleId="dsUrlFinal" maxlength="50" style="width:550px;margin-left:3px;"/></td>
        </tr>    
        <tr>
             <td align="right">IP Parceiro:</td>
             <td colspan="4"> <html:text name="Form" property="campanhaVO.dsUrlAutenticador" styleId="dsUrlAutenticador" maxlength="50" style="width:550px;margin-left:3px;"/></td>
        </tr>    
    </table>    
</div>    


<div id="rodape" style="position:absolute; top: 245px;">    
    <table cellpadding="2" cellspacing="1" style="margin-left:400px;">                
        <tr>
            <td align="right">Contato:</td>
            <td colspan="4">
                 <html:select name="Form" property="campanhaVO.idContato" styleId="idContato" style="margin-left:3px;width:235px">
                    <html:option value="">Selecione o Contato a ser registrado</html:option>
                    <html:options collection="listaContatos" property="idContato" labelProperty="nmContato" />
                 </html:select>
            </td>   
        </tr>
    </table>    
        
    <table cellpadding="2" cellspacing="1" style="margin-left:95px;">            
        <tr>
            <td align="right">
                Validade: De
            </td>
            <td colspan="2">
                <html:text name="Form"
                           property="campanhaVO.dtValidadeDe"
                           styleId="dtValidadeDe"
                           onkeyup="checaData(this)"
                           onblur="checaData(this)"
                           maxlength="10"
                           style="width:70px" />
                <img src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:pointer;margin-right:20px;" title="Calendário" onclick="return showCalendar('dtValidadeDe', '%d/%m/%Y');">
                At&eacute;: &nbsp;&nbsp;
                <html:text name="Form"
                           property="campanhaVO.dtValidadeAte"
                           styleId="dtValidadeAte"
                           onkeyup="checaData(this)"
                           onblur="checaData(this)"
                           maxlength="10"
                           style="width:70px" />
                <img src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:pointer;" title="Calendário" onclick="return showCalendar('dtValidadeAte', '%d/%m/%Y');">
            </td>
            
                    <td align="right">C&oacute;digo SGP:</td>
                    <td>
                        <html:text name="Form" property="campanhaVO.dsCodigo" styleId="dsCodigo" maxlength="15" size="15" />
                    </td>                                      
                    <td align="right">C&oacute;digo Legado:</td>
                    <td>
                        <html:text name="Form" property="campanhaVO.cdLegado" styleId="cdLegado" maxlength="15" size="15" />
                    </td>              
            
        </tr>
    </table>
        
    <table cellpadding="2" cellspacing="1" style="margin-left:35px;">        
        <tr>
            <td colspan="5" valign="top">
    
                <table>
                    <tr>
                        <td>DDD</td>
                        <td></td>
                        <td>Selecionados</td>
                        <td>Segmento</td>
                        <td></td>
                        <td>Selecionados</td>
                    </tr>
                    <tr>
                        <td>
                            <html:select name="Form" property="disponiveisDDD" multiple="true" size="4" style="width:130px">
                                <html:options collection="disponiveisDDD" property="idDDD" labelProperty="dsDDD"/>
                            </html:select>
                        </td>
                        <td>
                            <img hspace="10" id="btRightSimp" onClick="transfereSelecaoLista($('disponiveisDDD'), $('selecionadosDDD')); return false" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_over.gif'" style="border:none;cursor:hand;"/><br><br>
                            <img hspace="10" id="btLeftSimp" onClick="transfereSelecaoLista($('selecionadosDDD'), $('disponiveisDDD')); return false" src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_over.gif'" style="border:none;cursor:hand;"/>
                        </td>
                        <td width="160" nowrap>
                            <html:select name="Form" property="selecionadosDDD" multiple="true" size="4" style="width:130px">
                                <html:options collection="selecionadosDDD" property="idDDD" labelProperty="dsDDD"/>
                            </html:select>
                        </td>
                        <td rowspan="3" valign="top">
                            <html:select name="Form" property="disponiveisSegmentacao" multiple="true" size="10" style="width:130px">
                                <html:options collection="disponiveisSegmentacao" property="idSegmentacao" labelProperty="descricao"/>
                            </html:select>
                        </td>
                        <td rowspan="3">
                            <img hspace="10" id="btRightSimp" onClick="transfereSelecaoLista($('disponiveisSegmentacao'), $('selecionadosSegmentacao')); return false" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_over.gif'" style="border:none;cursor:hand;"/><br><br>
                            <img hspace="10" id="btLeftSimp" onClick="transfereSelecaoLista($('selecionadosSegmentacao'), $('disponiveisSegmentacao')); return false" src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_over.gif'" style="border:none;cursor:hand;"/>
                        </td>
                        <td rowspan="3"width="130" valign="top">
                            <html:select name="Form" property="selecionadosSegmentacao" multiple="true" size="10" style="width:130px">
                                <html:options collection="selecionadosSegmentacao" property="idSegmentacao" labelProperty="descricao"/>
                            </html:select>
                        </td>
                    </tr>
                    <tr>
                        <td>Tipo de Linha</td>
                        <td></td>
                        <td>Selecionados</td>
                    </tr>
                    <tr>
                        <td>
                            <html:select name="Form" property="disponiveisTipoLinha" multiple="true" size="4" style="width:130px">
                                <html:options collection="disponiveisTipoLinha" property="id" labelProperty="descricao"/>
                            </html:select>
                        </td>
                        <td>
                            <img hspace="10" id="btRightSimp" onClick="transfereSelecaoLista($('disponiveisTipoLinha'), $('selecionadosTipoLinha')); return false" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_over.gif'" style="border:none;cursor:hand;"/><br><br>
                            <img hspace="10" id="btLeftSimp" onClick="transfereSelecaoLista($('selecionadosTipoLinha'), $('disponiveisTipoLinha')); return false" src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_over.gif'" style="border:none;cursor:hand;"/>
                        </td>
                        <td>
                            <html:select name="Form" property="selecionadosTipoLinha" multiple="true" size="4" style="width:130px">
                                <html:options collection="selecionadosTipoLinha" property="id" labelProperty="descricao"/>
                            </html:select>
                        </td>
                    </tr>
                </table>
    
            </td>
        </tr>
    </table>   
    
    <table cellpadding="2" cellspacing="1" align="right" style="margin-right:15px;">    
        <tr>
            <td colspan="5" valign="top" align="right">
    
                <img id="btCancelar"
                                   value="Cancelar"
                                   src="/FrontOfficeWeb/resources/images/bt_cancelar_nrml.gif"
                                   rolloverImage="/FrontOfficeWeb/resources/images/bt_cancelar_over.gif"
                                   style="border:none;margin-right:4px;"
                                   onMouseUp="parent.$('divInclusaoAlteracao').style.display = 'none';return false;" />
    
                <img id="btGravar"
                                   value="Gravar"
                                   src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif"
                                   rolloverImage="/FrontOfficeWeb/resources/images/bt_gravar_over.gif"
                                   style="border:none;margin-right:4px;"
                                   onMouseUp="salvarCampanha()" />
            </td>
        </tr>
    </table>     
    <script language="JavaScript">
        tipoCampanha();
    </script>
</div>

</form>

<vivo:alert atributo="msgStatus" scope="request" afterFunction="closeWindow()" />
<vivo:alert atributo="msgErro" scope="request" />


<vivo:quadroFlutuante id="divInclusaoAlteracao"
                      idIframe="iframeInclusaoAlteracao"
                      width="763"                
                      height="520"
                      spacesTop="65"
                      spacesLeft="25"
                      display="none"
                      url="<%=request.getContextPath()%>"
                      label="Manter Questionário"
                      onclick="return false;"/>

</body>
</html>