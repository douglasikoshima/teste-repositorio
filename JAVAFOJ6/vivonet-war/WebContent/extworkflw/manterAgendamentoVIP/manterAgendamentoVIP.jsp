<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ page import="br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.AssuntoBean"%>
<%@ page import="br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.CampoBean"%>
<%@ page import="br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ValorBean"%>
<%@ page import="br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xsd.consultaLojas.output.LojaType"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formManterAgendamentoVIP" type="extworkflw.manterAgendamentoVIP.ManterAgendamentoVIPController.FormManterAgendamentoVIP"/>
<bean:define id="aUF" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formManterAgendamentoVIP.listaUf.UFVOArray"/>
<bean:define id="agendamento" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formManterAgendamentoVIP.agendamento"/>

<logic:present name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formManterAgendamentoVIP.listaLojas" >
    <bean:define id="lojaLista" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formManterAgendamentoVIP.listaLojas" />
</logic:present>

<html>
<head>
    <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">        
    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/frameweb.js" ></script>
    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/tratamento.js" ></script>
    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/prototype.js" ></script>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
    <script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/calendar.js"></script>    
    <script language="javaScript">
        var assunto = true;
        function buscaCidadePorUF(obj){
        
            document.formManterAgendamentoVIP2.action = "buscaMunicipioPorUf.do";
            top.frameApp.mostrar_div();
            document.formManterAgendamentoVIP2.submit();
        }
        
        function buscaLojaPorUfMunicipio(obj){
            
            document.formManterAgendamentoVIP2.action = "buscaLojaPorUfMunicipio.do";
            top.frameApp.mostrar_div();
            document.formManterAgendamentoVIP2.submit();
        
        }
        
        function buscarHorarios(){
        
            if(validaDadosBuscaHorario()){
            
                if(validarFaixaData()){

                    document.formManterAgendamentoVIP2.action = "buscarHorarios.do";
                    top.frameApp.mostrar_div();
                    document.formManterAgendamentoVIP2.submit();
                    
                }
            }
            
        }
        
        function validaDadosBuscaHorario(){

              form = document.formManterAgendamentoVIP2; 
              
              if(form.idUF.selectedIndex == 0){
                alert('Selecione uma UF, por favor');
                return false;
                
              }else if(form.idMunicipio.selectedIndex == 0){
                alert('Selecione um municipio, por favor.');
                return false;
                
              }else if(form.idLoja.selectedIndex == 0){
                alert('Selecione uma Loja, por favor');
                return false;
                
              }else if(form.dtAgendamento.value == ''){
                alert('Preencha o campo data, por favor.');
                return false;
              }  
              
              return true;      
        
        }
        
        function agendar(){
            if(valida()){
                if(validarFaixaData()){
                    if(verificaAssunto()){

                        document.formManterAgendamentoVIP2.action = "agendar.do";
                        document.formManterAgendamentoVIP2.target = "ifrmAbas";
                        
                        top.frameApp.mostrar_div();
                        document.formManterAgendamentoVIP2.submit();
                    
                    }
                    
                }
            }
        
        }
        

    function valida(){
            
          form = document.formManterAgendamentoVIP2; 
          
          if(form.idUF.selectedIndex == 0){
            alert('Selecione uma UF, por favor');
            return false;
            
          }else if(form.idMunicipio.selectedIndex == 0){
            alert('Selecione um municipio, por favor.');
            return false;
            
          }else if(form.idLoja.selectedIndex == 0){
            alert('Selecione uma Loja, por favor');
            return false;
            
          }else if(form.dtAgendamento.value == ''){
            alert('Preencha o campo data, por favor.');
            return false;
            
          }else if( (form.idHorario == null) || (form.idHorario.value == '') ){
            alert('Selecione um horário, por favor.');
            return false;
          }
          
          if (assunto == true && (form.elements['assunto1{0}'].value == '' || form.elements['assunto1{0}'].value == 0)) {
             alert("Selecione o campo assunto, por favor.");
             return false;
          }    
          
          if((document.formManterAgendamentoVIP2.radioQuestionario) && document.formManterAgendamentoVIP2.radioQuestionario.value == ''){
            alert('Selecione uma opção do Questionário, por favor.');  
            return false;
          }      

        return true;
        
    }
    
        function validarFaixaData() {

            dt = document.formManterAgendamentoVIP2.dtAgendamento.value;
            dtMin = document.formManterAgendamentoVIP2.dataMinima.value;
            dtMax = document.formManterAgendamentoVIP2.dataMaxima.value;
            d = dt.substring(6, 10) + dt.substring(3, 5) + dt.substring(0, 2);
            dMin = dtMin.substring(6, 10) + dtMin.substring(3, 5) + dtMin.substring(0, 2);
            dMax = dtMax.substring(6, 10) + dtMax.substring(3, 5) + dtMax.substring(0, 2);
            var data = new Date();
            data.setDate(data.getDate()+2);
            var dtAtual = data.getDate()+'/'+data.getMonth()+'/'+data.getYear();
            data.setDate(data.getDate()+30);
            var dtFinal = data.getDate()+'/'+data.getMonth()+'/'+data.getYear();
            if (d < dMin || d > dMax) {
                alert("Selecione uma data entre os dias "+dtAtual+" e "+dtFinal);
                return false;
            }

            return true;
        }

        function assunto1Change() {

            document.formManterAgendamentoVIP2.action = 'obterAssunto2.do';
            document.formManterAgendamentoVIP2.target = '';
            top.frameApp.mostrar_div();
            document.formManterAgendamentoVIP2.submit();

        }

        function assunto2Change() {

            document.formManterAgendamentoVIP2.action = 'obterAssunto3.do';
            document.formManterAgendamentoVIP2.target = '';
            top.frameApp.mostrar_div();
            document.formManterAgendamentoVIP2.submit();

        }

        function assunto3Change() {

            document.formManterAgendamentoVIP2.action = 'obterAssunto4.do';
            document.formManterAgendamentoVIP2.target = '';
            top.frameApp.mostrar_div();
            document.formManterAgendamentoVIP2.submit();

        }
        
        function carregaRadio(valor){
            document.formManterAgendamentoVIP2.radioQuestionario.value = valor;
        }
        
        function voltar(){
            parent.fecharModal();
        
        }
        
        function limpaCampos(obj){
            
            document.formManterAgendamentoVIP2.dtAgendamento.value = "";
            if(document.getElementById('tdHorarios'))
                document.getElementById('tdHorarios').innerHTML = "";
        }
        
        function limpaHorarios(){
                    if(document.getElementById('tdHorarios'))
                document.getElementById('tdHorarios').innerHTML = "";
        }
        
        function alterarAssunto(){
        
            document.getElementById('selectAssunto').style.display = "block";
            document.getElementById('selectAssunto').style.visibility.display = "visible";
            assunto = true;
        
        }
        
        function verificaAssunto(){
        
                if(!assunto){
                    return true;
                }
        
				var msg = 'Por favor, informe o assunto principal e seus detalhamentos.';
                
				for (i=0;i<document.formManterAgendamentoVIP2.elements.length;i++) {

					if(document.formManterAgendamentoVIP2.elements[i].type == "text"){

						if(document.formManterAgendamentoVIP2.elements[i].value == ''){
							alert(msg);
							return false;
						}
					}

					if(document.formManterAgendamentoVIP2.elements[i].type == "select-one"){
						if(document.formManterAgendamentoVIP2.elements[i].selectedIndex == 0){
							alert(msg);
							return false;
						}
					}

				}
                
                return true;

			}   
            
    function lupaLoja(){
        document.getElementById('divListaLoja').style.display = 'block';
    
    }     
    
    function fechaJanela(){
        document.getElementById('divListaLoja').style.display = 'none';
    }
    
    function carregaLoja(op){
        
        document.formManterAgendamentoVIP2.idLoja.value = op;
        document.getElementById('divListaLoja').style.display = 'none';
    
    }
    
        function checkLength(obj,number){
            if(obj.value.length > number-1){
                obj.value = obj.value.substring(0,number);
            }
        }
    

    </script>

</head>
<body>

    <form name="formManterAgendamentoVIP2" method="post" onsubmit="return false;">
    
    <html:hidden name="Form" property="tipoOperacao"/>
    <html:hidden name="Form" property="dataMinima"/>
    <html:hidden name="Form" property="dataMaxima"/>
    
    <logic:equal name="Form" property="isPrimeiroAcesso" value="true">
        <html:hidden name="Form" property="radioQuestionario"/>
    </logic:equal>
    
        <table width="100%">
            <tr>
                <td align="left"><b>Agendamento de Visita em Loja Própria</b></td>
            </tr>
        </table>
        
        <fieldset>
            <legend><b>Loja</b></legend>
            
            <table border="0" width="100%" cellpadding="1" cellspacing="1">
                <tr>
                    <td width="5%" align="right">UF:</td>
                    <td width="10%" align="left">
                            <html:select name="Form"  property="idUF" style="width:40px" onchange="buscaCidadePorUF(this);">
                                <html:option value="-1">---</html:option>
                                <html:options collection="aUF" property="sgUF" labelName="sgUF"/>
                            </html:select>                    
                    </td>
                    <td width="10%" align="right">Cidade:</td>
                    <td width="25%">
                        <html:select name="Form"  property="idMunicipio" style="width:200px;" onchange="buscaLojaPorUfMunicipio(this);">
                            <html:option value="-1">-- Selecione -- </html:option>
                            <html:options name="Form" property="listaMunicipios" />
                        </html:select>                    
                    </td>
                    <td width="10%" align="right">Loja:</td>
                    <td width="35%">
                        <html:select name="Form"  property="idLoja" style="width:240px;" onchange="limpaCampos(this);">
                            <html:option value="-1">-- Selecione -- </html:option>
                            
                            <logic:present name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formManterAgendamentoVIP.listaLojas" >
                                <html:options collection="lojaLista" property="id" labelProperty="nomeLocal" />
                            </logic:present>
                            
                        </html:select>  
                    </td>
                    <td width="5%">                  
                    <img src="/FrontOfficeWeb/resources/images/lupa_bg_transp.gif"
                         style="border:none;margin-left:4px;cursor:hand"
                         onmouseup="lupaLoja();"/>
                    </td>
                </tr>
                </table>
                
                <table border="0" >
                <tr>
                    <td>Data:</td>
                    <td colspan="2" width="140"><html:text name="Form" property="dtAgendamento" maxlength="10" size="10" readonly="true"/><img src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:hand;" title="Calendário" onclick="showCalendar('dtAgendamento', '%d/%m/%Y');limpaHorarios();">&nbsp;</td>
                    <td>Horários Disponíveis 
                    <img id="btSeta"
                                   value="Seta"
                                   src="/FrontOfficeWeb/resources/images/bt_setadir_nrml.gif"
                                   rolloverImage="/FrontOfficeWeb/resources/images/bt_setadir_over.gif"
                                   style="border:none;margin-left:4px;"
                                   onMouseUp="buscarHorarios()" />                    
                    
                    </td>
                    <logic:present name="Form" property="horarios">
                    
                        <td id="tdHorarios"  colspan="2">
                        
                                <html:select name="Form"  property="idHorario" style="width:100px;" >
                                    <html:option value="-1">--Selecione--</html:option>
                                    
                                        <logic:notEqual name="Form"  property="idHorario" value="">
                                            <bean:define name="Form"  property="idHorario" id="idHorarioJsp" type="java.lang.String"/>
                                            <%
                                                String paramidHorario = ConstantesCRM.SVAZIO;
                                                if(idHorarioJsp != null && idHorarioJsp.length() > 5){
                                                    paramidHorario = idHorarioJsp.substring(0,5);
                                                }
                                            %>
                                           <option selected value="<%=paramidHorario%>"><%=paramidHorario%></option>                                     
                                        </logic:notEqual>
                                        
                                    <html:options name="Form" property="horarios" />
                                </html:select>  
                             
                        </td>
                                       
                    </logic:present>
                </tr>
    
            </table>
            
        </fieldset>
        <br>
        
        <fieldset>
            <legend ><b>Assunto</b></legend>

            <logic:equal name="Form" property="tipoOperacao" value="alterar">
            
                <table width="100%">
                    <tr>
                        <td>
                                <bean:write name="agendamento" property="assunto"/> - <BR>
                                
                                <logic:notEqual name="agendamento" property="detalhe1" value="">
                                    <bean:write name="agendamento" property="detalhe1"/> - <BR>
                                </logic:notEqual>
                    
                                <logic:notEqual name="agendamento" property="detalhe2" value="">
                                    <bean:write name="agendamento" property="detalhe2"/> - <BR>
                                </logic:notEqual>
                    
                                <logic:notEqual name="agendamento" property="detalhe3" value="">
                                    <bean:write name="agendamento" property="detalhe3"/> 
                                </logic:notEqual>
                        
                        </td>
                        <td>
                        
                            <img id="btAlterar" 
                                 src="/FrontOfficeWeb/resources/images/bt_alterar_nrml.gif" 
                                 style="border:none;margin-left:4px;"
                                 onmouseup="alterarAssunto();"/>
                        
                        
                        </td>
                    </tr>
                </table>

            </logic:equal>
        
        <table border="0" width="100%">
        
        <tr>
            <td  height="30">
                <table width="100%">

                    <%
                    AssuntoBean assunto1 = (AssuntoBean) session.getAttribute("assunto1");
                    if (assunto1 != null) {
                    %>
                    <tr>
                        <td align="left">&nbsp;
                            <%
                            for(int iCampo = 0; iCampo < assunto1.getCampos().size(); iCampo++) {
                                CampoBean campo1 = (CampoBean) assunto1.getCampos().get(iCampo);
                            %>
                                <select id="selectAssunto" name="assunto1{<%= iCampo%>}" class="FontBlack" style="width: 300px;" onchange="javascript: assunto1Change();">
                                    <option value="0">Selecione</option>
                                    <%
                                    Iterator itVal = campo1.getValores().iterator();
                                    while (itVal != null && itVal.hasNext()) {
                                        ValorBean valor1 = (ValorBean) itVal.next();
                                    %>
                                    <option value="<%= valor1.getCodigo() %>" <%= valor1.getCodigo().equals(campo1.getValorSelecionado()) ? "selected" : "" %> >
                                        <%= valor1.getDescricao() %>
                                    </option>
                                    <% } %>
                                </select>
                            <% } %>
                        </td>
                    </tr>
                    <% } %>

                    <%
                    AssuntoBean assunto2 = (AssuntoBean) session.getAttribute("assunto2");
                    if (assunto2 != null) {
                    %>
                    <tr>
                        <td align="left">&nbsp;
                                <table width="100%">
                                <%
                                for(int iCampo = 0; iCampo < assunto2.getCampos().size(); iCampo++) {
                                    CampoBean campo2 = (CampoBean) assunto2.getCampos().get(iCampo);
                                %>
                                    <tr>
                                    <%
                                    if ("P".equals(campo2.getTipo())) {
                                    %>
                                    <td class="FontLightGray" colspan="2">
                                        <input type="hidden" name="assunto2{<%= iCampo%>}" value="<%= campo2.getCodigo() %>">                                     
                                        <%= campo2.getNome() %>
                                    <% } else if ("C".equals(campo2.getTipo())) { %>

                                        <td class="FontLightGray" width="35%"> 
                                        <%=campo2.getNome()%>&nbsp;
                                        </td><td class="FontLightGray">                                        

                                        <select class="FontBlack" name="assunto2{<%= iCampo %>}" onchange="assunto2Change();">
                                            <option value="0">Selecione</option>
                                            <%
                                            Iterator itVal = campo2.getValores().iterator();
                                            while (itVal != null && itVal.hasNext()) {
                                                ValorBean valor2 = (ValorBean) itVal.next();
                                            %>
                                                <option value="<%= valor2.getCodigo() %>" <%= valor2.getCodigo().equals(campo2.getValorSelecionado()) ? "selected" : "" %> >
                                                    <%= valor2.getDescricao() %>
                                                </option>
                                            <% } %>
                                        </select>
                                    <% } else if ("U".equals(campo2.getTipo())) { %>
                                        <td class="FontLightGray" width="35%"> 
                                        <%=campo2.getNome()%>&nbsp;
                                        </td><td class="FontLightGray">                                        
                                        <select class="FontBlack" name="assunto2{<%= iCampo %>}">
                                            <option value="0">Selecione</option>
                                            <%
                                            Iterator itVal = campo2.getValores().iterator();
                                            while (itVal != null && itVal.hasNext()) {
                                                ValorBean valor2 = (ValorBean) itVal.next();
                                            %>
                                                <option value="<%= valor2.getCodigo() %>" <%= valor2.getCodigo().equals(campo2.getValorSelecionado()) ? "selected" : "" %> >
                                                    <%= valor2.getDescricao() %>
                                                </option>
                                            <% } %>
                                        </select>

                                    <% } else if ("A".equals(campo2.getTipo())) { %>
                                        <td class="FontLightGray" colspan="2">
                                        <textarea name="assunto2{<%= iCampo %>}" class="FontBlack" <%=(campo2.isNulo())?"style=\"background-color:#FFFFCC\" onclick=\"this.style.backgroundColor='#FFFFFF'\" foco=\"true\"":""%> cols="50" rows="3"  onkeyup="checkLength(this,200)"  onblur="checkLength(this,200)"><%= (campo2.getValorSelecionado()!=null)?campo2.getValorSelecionado():"" %></textarea>
                                        <%if(campo2.getContinuar()){%>
                                        <input type="button" name="submit2" value="continuar" class="FontBlack" onclick="assunto2Change()">
                                        <%}%>

                                    <% } else if ("T".equals(campo2.getTipo())) { %>
                                        <td class="FontLightGray" colspan="2">
                                        <input type="text" size="50" name="assunto2{<%= iCampo %>}" class="FontBlack" <%=(campo2.isNulo())?"style=\"background-color:#FFFFCC\" onclick=\"this.style.backgroundColor='#FFFFFF'\" foco=\"true\"":""%> maxlength="200" value="<%= (campo2.getValorSelecionado()!=null)?campo2.getValorSelecionado():""%>">
                                        <%if(campo2.getContinuar()){%>
                                        <input type="button" name="submit2" value="continuar" class="FontBlack" onclick="assunto2Change()">
                                        <%}%>
                                    <% } %>
                                    </td></tr>
                                <% } %>
                                </table>
                        </td>
                    </tr>
                    <% } %>

                    <%
                    AssuntoBean assunto3 = (AssuntoBean) session.getAttribute("assunto3");
                    if (assunto3 != null) {
                    %>
                    <tr>
                        <td align="left">&nbsp;
                                <table width="100%">
                                <%
                                for(int iCampo = 0; iCampo < assunto3.getCampos().size(); iCampo++) {
                                    CampoBean campo3 = (CampoBean) assunto3.getCampos().get(iCampo);
                                %>
                                    <tr>
                                    <%
                                    if ("P".equals(campo3.getTipo())) {
                                    %>
                                        <td class="FontLightGray" colspan="2">
                                        <input type="hidden" name="assunto3{<%= iCampo%>}" value="<%= campo3.getCodigo() %>">                            
                                        <%= campo3.getNome() %>
                                        
                                    <% } else if ("C".equals(campo3.getTipo())) { %>

                                        <td class="FontLightGray" width="35%"> 
                                        <%=campo3.getNome()%>&nbsp;
                                        </td><td class="FontLightGray">                                        
                                    
                                        <select class="FontBlack" name="assunto3{<%= iCampo %>}" onchange="assunto3Change();">
                                            <option value="0">Selecione</option>
                                            <%
                                            Iterator itVal = campo3.getValores().iterator();
                                            while (itVal != null && itVal.hasNext()) {
                                                ValorBean valor3 = (ValorBean) itVal.next();
                                            %>
                                                <option value="<%= valor3.getCodigo() %>">
                                                    <%= valor3.getDescricao() %>
                                                </option>
                                            <% } %>
                                        </select>
                                    <% } else if ("U".equals(campo3.getTipo())) { %>

                                        <td class="FontLightGray" width="35%"> 
                                        <%=campo3.getNome()%>&nbsp;
                                        </td><td class="FontLightGray">                                        
                                    
                                        <select class="FontBlack" name="assunto3{<%= iCampo %>}">
                                            <option value="0">Selecione</option>
                                            <%
                                            Iterator itVal = campo3.getValores().iterator();
                                            while (itVal != null && itVal.hasNext()) {
                                                ValorBean valor3 = (ValorBean) itVal.next();
                                            %>
                                                <option value="<%= valor3.getCodigo() %>" <%= valor3.getCodigo().equals(campo3.getValorSelecionado()) ? "selected" : "" %> >
                                                    <%= valor3.getDescricao() %>
                                                </option>
                                            <% } %>
                                        </select>
                                    <% } else if ("A".equals(campo3.getTipo())) { %>
                                        <td class="FontLightGray" colspan="2">
                                        <textarea name="assunto3{<%= iCampo %>}" class="FontBlack" <%=(campo3.isNulo())?"style=\"background-color:#FFFFCC\" onclick=\"this.style.backgroundColor='#FFFFFF'\" foco=\"true\"":""%> cols="50" rows="3"  onkeyup="checkLength(this,200)"  onblur="checkLength(this,200)"><%= (campo3.getValorSelecionado()!=null)?campo3.getValorSelecionado():"" %></textarea>
                                        <%if(campo3.getContinuar()){%>
                                        <input type="button" name="submit3" value="continuar" class="FontBlack" onclick="assunto3Change()">
                                        <%}%>

                                    <% } else if ("T".equals(campo3.getTipo())) { %>
                                        <td class="FontLightGray" colspan="2">
                                        <input type="text" size="50" name="assunto3{<%= iCampo %>}" class="FontBlack" <%=(campo3.isNulo())?"style=\"background-color:#FFFFCC\" onclick=\"this.style.backgroundColor='#FFFFFF'\" foco=\"true\"":""%> maxlength="200" value="<%= (campo3.getValorSelecionado()!=null)?campo3.getValorSelecionado():""%>">
                                        <%if(campo3.getContinuar()){%>
                                        <input type="button" name="submit3" value="continuar" class="FontBlack" onclick="assunto3Change()">
                                        <%}%>
                                    <% } %>
                                    </td></tr>
                                <% } %>
                                </table>
                        </td>
                    </tr>
                    <% } %>
                    
                    <%
                    AssuntoBean assunto4 = (AssuntoBean) session.getAttribute("assunto4");
                    if (assunto4 != null) {
                    %>
                    <tr>
                        <td align="left">&nbsp;
                                <table width="100%">
                                <%                                                                
                                for(int iCampo = 0; iCampo < assunto4.getCampos().size(); iCampo++) {
                                    CampoBean campo4 = (CampoBean) assunto4.getCampos().get(iCampo);
                                %>
                                    <tr>
                                    <%
                                    if ("P".equals(campo4.getTipo())) {
                                    %>   
                                        <td class="FontLightGray" colspan="2">
                                        <input type="hidden" name="assunto4{<%= iCampo%>}" value="<%= campo4.getCodigo() %>">                                                                             
                                        <%= campo4.getNome() %>  
                                    <% } else if ("C".equals(campo4.getTipo())) { %>
                                    
                                        <td class="FontLightGray" width="35%"> 
                                        <%=campo4.getNome()%>&nbsp;
                                        </td><td class="FontLightGray">                                        
                                    
                                        <select class="FontBlack" name="assunto4{<%= iCampo %>}" onchange="assunto4Change();">
                                            <option value="0">Selecione</option>
                                            <%
                                            Iterator itVal = campo4.getValores().iterator();
                                            while (itVal != null && itVal.hasNext()) {
                                                ValorBean valor4 = (ValorBean) itVal.next();
                                            %>
                                                <option value="<%= valor4.getCodigo() %>">
                                                    <%= valor4.getDescricao() %>
                                                </option>
                                            <% } %>
                                        </select>                                        
                                    <% } else if ("U".equals(campo4.getTipo())) { %>

                                        <td class="FontLightGray" width="35%"> 
                                        <%=campo4.getNome()%>&nbsp;
                                        </td><td class="FontLightGray">                                        
                                    
                                        <select class="FontBlack" name="assunto4{<%= iCampo %>}">
                                            <option value="0">Selecione</option>
                                            <%
                                            Iterator itVal = campo4.getValores().iterator();
                                            while (itVal != null && itVal.hasNext()) {
                                                ValorBean valor4 = (ValorBean) itVal.next();
                                            %>
                                                <option value="<%= valor4.getCodigo() %>" <%= valor4.getCodigo().equals(campo4.getValorSelecionado()) ? "selected" : "" %> >
                                                    <%= valor4.getDescricao() %>
                                                </option>
                                            <% } %>
                                        </select>
                                    <% } else if ("A".equals(campo4.getTipo())) { %>
                                        <td class="FontLightGray" colspan="2">
                                        <textarea name="assunto4{<%= iCampo %>}" class="FontBlack" <%=(campo4.isNulo())?"style=\"background-color:#FFFFCC\" onclick=\"this.style.backgroundColor='#FFFFFF'\" foco=\"true\"":""%> cols="50" rows="3"  onkeyup="checkLength(this,200)"  onblur="checkLength(this,200)"><%= (campo4.getValorSelecionado()!=null)?campo4.getValorSelecionado():"" %></textarea>
                                        <%if(campo4.getContinuar()){%>
                                        <input type="button" name="submit4" value="continuar" class="FontBlack" >
                                        <%}%>

                                    <% } else if ("T".equals(campo4.getTipo())) { %>
                                        <td class="FontLightGray" colspan="2">
                                        <input type="text" size="50" name="assunto4{<%= iCampo %>}" class="FontBlack" <%=(campo4.isNulo())?"style=\"background-color:#FFFFCC\" onclick=\"this.style.backgroundColor='#FFFFFF'\" foco=\"true\"":""%> maxlength="200" value="<%= (campo4.getValorSelecionado()!=null)?campo4.getValorSelecionado():""%>">
                                        <%if(campo4.getContinuar()){%>
                                        <input type="button" name="submit4" value="continuar" class="FontBlack" >
                                        <%}%>
                                    <% } %>
                                    </td></tr>
                                <% } %>
                                </table>
                        </td>
                    </tr>
                    <% } %>

        </table>
        
        </table>
        
        </fieldset>
        <br>
        
        <logic:equal name="Form" property="isPrimeiroAcesso" value="true">

            <fieldset>
                <legend><b>Questionário - Como o cliente ficou sabendo do serviço?</b></legend>
    
                <table width="100%" border="0">
                    <tr>
                        <td><input type="radio" name="radioQuest" class="radio" value="Mala Direta" onchange="carregaRadio(this.value);">Mala Direta</td>
                        <td><input type="radio" name="radioQuest" class="radio" value="Ligação telefônica" onchange="carregaRadio(this.value);">Ligação telefônica</td>
                    </tr>
                    <tr>
                        <td><input type="radio" name="radioQuest" class="radio" value="E-mail" onchange="carregaRadio(this.value);">E-mail</td>
                        <td><input type="radio" name="radioQuest" class="radio" value="Não recebi nenhuma comunicação" onchange="carregaRadio(this.value);">Não recebi nenhuma comunicação</td>
                    </tr>
                    <tr>
                        <td colspan="2"><input type="radio" name="radioQuest" class="radio" value="Mensagem de voz eletrônica" onchange="carregaRadio(this.value);">Mensagem de voz eletrônica</td>
                    </tr>
                </table>
            </fieldset>

        </logic:equal>

        <table border="0" width="100%">
            <tr>
                <td align="right">
                <img id="btVoltar" 
                     src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" 
                     style="border:none;margin-left:4px;"
                     onmouseup="voltar();"/>
                
                <img id="btAgerndar" 
                     src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" 
                     style="border:none;margin-left:4px;"
                     onmouseup="agendar();"/>
                </td>
            </tr>
        </table>
        
        <div style="z-index:999;display:none;position:absolute;top:90; left:230; width:430; height:280; border-style:solid; border-width:1px; border-color:#adadad; background-color:#ededed;" id="divListaLoja">
            <%int idx = 0;%>
            <table border="0" width="100%" cellpadding="1" cellspacing="6">
                <tr>
                    <td width="100%" align="center">
                    
                        <vivo:tbTable selectable="true" layoutWidth="430" layoutHeight="200" tableWidth="430" onRowClick="true" resize="false" styleId="tableTitle" sortable="true">
                            <vivo:tbHeader>
                            
                                <vivo:tbHeaderColumn align="center" width="40%"  type="string">Loja</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="60%" type="string">Local</vivo:tbHeaderColumn>
                                
                            </vivo:tbHeader>
                            <vivo:tbRows>
                                <logic:present name="Form" property="listaLojas">
                                
                                    <logic:iterate name="Form" property="listaLojas" id="lojas" indexId="id" >
                                        
                                        <%
                                        
                                            LojaType loja[] = Form.getListaLojas();
                                            
                                            String nomeLocal = "'" + loja[idx].getId() + "'";
                                        
                                        %>
                                        
                                        <vivo:tbRow key="linha" onRowClick='<%="carregaLoja("+nomeLocal+");"%>'>
                                            
                                            <vivo:tbRowColumn>
                                                <bean:write name="lojas" property="nome" />
                                            </vivo:tbRowColumn>
                                            <vivo:tbRowColumn>
                                                <bean:write name="lojas" property="local" />
                                            </vivo:tbRowColumn>
    
                                        </vivo:tbRow>
                                        
                                        <%++idx;%>
                                    </logic:iterate>
                                
                                </logic:present>

                            </vivo:tbRows>

                        </vivo:tbTable>
                        
                        <br> 
                    
                     <img src="/FrontOfficeWeb/resources/images/bt_fechar_nrml.gif" hspace="3" style="cursor:hand" onClick="fechaJanela();">                    
                    
                    </td>
                </tr>
            </table>        
            
        </div>
        

        
    </form>
    
    <vivo:alert atributo="msgStatus" scope="request"/>
        
    <script language="javaScript">
        document.body.style.backgroundColor = '#ededed';
        parent.oculta_div();
    
        <logic:equal name="Form" property="tipoOperacao" value="alterar">
    
            if(document.getElementById('selectAssunto').selectedIndex == 0){
                document.getElementById('selectAssunto').style.display = "none";
                document.getElementById('selectAssunto').style.visibility.display = "hidden";
                assunto = false;
            }
    
        </logic:equal>

    </script>
    
    

</body>
</html>