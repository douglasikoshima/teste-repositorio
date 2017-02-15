<%@page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formManterAgendamentoVIP" type="extworkflw.manterAgendamentoVIP.ManterAgendamentoVIPController.FormManterAgendamentoVIP"/>
<bean:define id="aEmail"    name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formManterAgendamentoVIP.emails"/>
<bean:define id="aTipoEmail"    name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formManterAgendamentoVIP.tiposEmail"/>

<html>
<head>
    <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">        
    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/frameweb.js" ></script>
    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/tratamento.js" ></script>
    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/prototype.js" ></script>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
    <script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/calendar.js"></script>    
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>    
    
    <script language="javaScript">
    
        function validaPesquisa()
        {
            document.forms[0].target="";
            if (document.forms[0].consHexa.value.length == 0) {
                alert('Preencha o campo de Consulta Hexa');
            } else if (document.forms[0].consHexa.value.length != 8) {                
                alert('Campo de Hexa deve ser preenchido com 8 caracteres!');
            } else {                                        
                top.frameApp.mostrar_div();
                document.forms[0].action="/FrontOfficeWeb/extworkflw/ConsultaHexa/consultaHexa.do";
                document.forms[0].submit();
            }
        }

        
        function voltar() {
            document.forms[0].action="voltar.do";
            document.forms[0].target="ifrmFalse";
            document.forms[0].submit();
        }
        
        function ocultaCampoEmail(paramIdTipoEmail){
                        
            //EMAIL
            if (paramIdTipoEmail == 'EMAIL'){

                document.getElementById('divEmail').style.display = "block";
                document.getElementById('divEmail').style.visibility.display = "visible";

                document.getElementById('divSMS').style.display = "none";
                document.getElementById('divSMS').style.visibility.display = "hidden";
                
                document.getElementById('divSemAlerta').style.display = "none";
                document.getElementById('divSemAlerta').style.visibility.display = "hidden";
                
            }
            
            // AMBOS
            if(paramIdTipoEmail == 'AMBOS'){

                document.getElementById('divEmail').style.display = "block";
                document.getElementById('divEmail').style.visibility.display = "visible";

                document.getElementById('divSMS').style.display = "block";
                document.getElementById('divSMS').style.visibility.display = "visible";
                
                document.getElementById('divSemAlerta').style.display = "none";
                document.getElementById('divSemAlerta').style.visibility.display = "hidden";
                
            
            }
            
            // SMS
            if(paramIdTipoEmail == 'SMS'){
            
                document.getElementById('divSMS').style.display = "block";
                document.getElementById('divSMS').style.visibility.display = "visible";
                
                document.getElementById('divEmail').style.display = "none";
                document.getElementById('divEmail').style.visibility.display = "hidden";
                
                document.getElementById('divSemAlerta').style.display = "none";
                document.getElementById('divSemAlerta').style.visibility.display = "hidden";
                
            
            }
            
            // SEM ALERTA
            if(paramIdTipoEmail == 'NENHUM'){
            
                document.getElementById('divEmail').style.display = "none";
                document.getElementById('divEmail').style.visibility.display = "hidden";
            
                document.getElementById('divSMS').style.display = "none";
                document.getElementById('divSMS').style.visibility.display = "hidden";
            
                document.getElementById('divSemAlerta').style.display = "block";
                document.getElementById('divSemAlerta').style.visibility.display = "visible";
            
            }
            
        }
        
        function ocultaEmailNovo(op){
            
            if(op == "NOVO"){
                document.getElementById('trEmail').style.display = "block";
                document.getElementById('trEmail').style.visibility.display = "visible";
            
            }else{

                document.getElementById('trEmail').style.display = "none";
                document.getElementById('trEmail').style.visibility.display = "hidden";
            
            }
            
        }
        
        function gravar(){
            
                if(valida()){
            
                    document.formManterAgendamentoVIP.action = "gravarAlerta.do";
                    document.formManterAgendamentoVIP.target = "ifrmAbas";
                    
                    top.frameApp.mostrar_div();
                    document.formManterAgendamentoVIP.submit();
                
                }
        
        }
        
        function valida(){
            
            form = document.formManterAgendamentoVIP;
            
            if(form.idTipoEnvio.value == 'SMS' || form.idTipoEnvio.value == 'AMBOS'){
                
                if(form.dtAlertaSMS.value == ''){
                    alert("Por favor, informe a data do envio do lembrete por SMS.");
                    return false;
                }
                if(form.idHorarioSMS.value == ''){
                    alert('Por favor, informe a hora do envio do lembrete por SMS.');
                    return false;
                }else{
                    
                    if(!validaHora(form.idHorarioSMS.value)){
                        alert('Formato de hora inválido, preencha novamente.');
                        return false;
                    }
                }

                if (!validarFaixa(form.dtAlertaSMS, form.idHorarioSMS)) {
                    alert("A data/hora do SMS não pode ser posterior ao agendamento.");
                    return false;
                }
                
                if (!validarFaixa2(form.dtAlertaSMS, form.idHorarioSMS)) {
                    alert("A data/hora do SMS não pode ser menor que a data atual.");
                    return false;
                }
            
            }
            
            if(form.idTipoEnvio.value == 'EMAIL' || form.idTipoEnvio.value == 'AMBOS'){
            
                if(form.dtAlerta.value == ''){
                    alert("Por favor, informe a data do envio do lembrete por e-mail.");
                    return false;
                }
                if(form.idHorario.value == ''){
                    alert(' Por favor, informe a hora do envio do lembrete por e-mail.');
                    return false;
                
                }else{

                    if(!validaHora(form.idHorario.value)){
                        alert('Formato de hora inválido, preencha novamente.');
                        return false;
                    }
                
                
                }
                
                if(form.idEmail.value == "NOVO"){
                    if(form.idTipoEmail.selectedIndex == 0){
                        alert("Por favor, informe o tipo do e-mail.");
                        return false;
                    }
                    
                    if(form.email.value == ''){
                        alert("Por favor, informe o e-mail para receber o lembrete.");
                        return false;
                    }
                    
                    if(!validaEmail(form.email.value)){
                        alert("E-mail inválido. Por favor, informe seu e-mail.");
                        return false;
                    }
                
                }
                
                if (!validarFaixa(form.dtAlerta, form.idHorario)) {
                    alert("A data/hora do e-mail não pode ser posterior ao agendamento.");
                    return false;
                }

                if (!validarFaixa2(form.dtAlerta, form.idHorario)) {
                    alert("A data/hora do e-mail não pode ser menor que a data atual.");
                    return false;
                }                
            
            }
            
            return true;
        
        }
        
        function ocultaTrEMail(op){
            
            if(op != 'NOVO'){
            
                document.getElementById('trEmail').style.display = "none";
                document.getElementById('trEmail').style.visibility.display = "hidden";
            
            }
        
        }
        
        function validarFaixa(data, hora) {

            d = data.value;
            h = hora.value;
            dVal = d.substring(6, 10) + d.substring(3, 5) + d.substring(0, 2) + h;
            dt = document.formManterAgendamentoVIP.data.value;
            hr = document.formManterAgendamentoVIP.hora.value;
            dt = dt.substring(6, 10) + dt.substring(3, 5) + dt.substring(0, 2);
            dAg = dt + hr;

            return !(dVal > dAg);
        }

        function validarFaixa2(data, hora) {

            d = data.value;
            h = hora.value;
            dVal = d.substring(6, 10) + d.substring(3, 5) + d.substring(0, 2) + h;

            return !(dVal < '<bean:write name="Form"  property="alertaDataHoje" />');
        }        
        
    </script>

</head>
<body>

    <form name="formManterAgendamentoVIP" method="post" onsubmit="return false;">
        
        <%
            String hora = ConstantesCRM.SVAZIO;
            if(Form.getAgendamento().getHora() != null && !ConstantesCRM.SVAZIO.equals(Form.getAgendamento().getHora()) 
                && Form.getAgendamento().getHora().length() > 5 ){
                
                hora = Form.getAgendamento().getHora();
                hora = hora.substring(0,5);

            }
        
        %>
        <input type="hidden" name="hora" value="<%=hora%>" >
        
        <input type="hidden" name="data" value="<%=Form.getAgendamento().getData()%>" >

            <table border="0" width="100%">
                <tr>
                    <td colspan="1" width="100"><b>&nbsp;Enviar por :</b></td>                
                    <td>
                        <html:select name="Form"  property="idTipoEnvio" onchange="ocultaCampoEmail(this.value);">
                            <html:option value="AMBOS">AMBOS</html:option>
                            <html:option value="EMAIL">E-MAIL</html:option>
                            <html:option value="SMS">SMS</html:option>
                            <html:option value="NENHUM">NENHUM</html:option>
                        </html:select>                    
                    </td>                
                </tr>
            </table>
            
            
            <div id="divEmail" >
            
                <fieldset>
                    <legend><b>E-mail</b></legend>
                
                    <table>
                    
                        <tr>
                            <td><b>&nbsp;E-mail:</b></td>
                            <td>
                                    <html:select name="Form"  property="idEmail" style="width:150px;" onchange="ocultaEmailNovo(this.value);">
                                        <html:option value="NOVO">NOVO....</html:option>
                                        <html:options collection="aEmail" property="dsEmail" labelProperty="dsEmail" />                                    
                                    </html:select>                    
                            </td>

                        </tr>
                        <tr id="trEmail">
                                <td><b>&nbsp;Tipo do E-Mail :</b></td>                
                                <td align="left">
                                    <html:select name="Form"  property="idTipoEmail">
                                        <html:option value="0">-- Selecione --</html:option>
                                        <html:options collection="aTipoEmail"  property="idTipoEmail" labelProperty="dsTipoEmail"/>
                                    </html:select>                    
                                </td>                
                                <td><b>&nbsp;E-Mail:</b></td>                
                                <td>
                                    <html:text name="Form"  property="email" maxlength="50" size="40"/>
                                </td> 
                        </tr>
                        <tr>
                            <td><b>&nbsp;Data Lembrete:</b></td>                
                            <td align="left"><html:text name="Form"  property="dtAlerta" maxlength="10" size="12" onblur="validaData(this.value);" onkeyup="Formatar(this.value, this.form.name, this.name, 'data');" onchange="Formatar(this.value, this.form.name, this.name, 'data');"/><img src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:hand;" title="Calendário" onclick="return showCalendar('dtAlerta', '%d/%m/%Y');">&nbsp;</td>                
                            <td><b>&nbsp;Horário:</b></td>                
                            <td align="left">
                                <html:text name="Form" property="idHorario" maxlength="5" size="6"  onkeyup="checaHora(this);"/>
                            
                            </td>                
                        </tr>
                    </table>
                
                </fieldset>
                
            </div>
            
            
            <br>

            <div id="divSMS" >

                <fieldset>
                    <legend><b>SMS</b></legend>
    
                    <table>
                        <tr>
                            <td><b>&nbsp;Data Lembrete:</b></td>                
                            <td align="left"><html:text name="Form"  property="dtAlertaSMS" maxlength="10" size="12" onblur="validaData(this.value);" onkeyup="Formatar(this.value, this.form.name, this.name, 'data');" onchange="Formatar(this.value, this.form.name, this.name, 'data');"/><img src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:hand;" title="Calendário" onclick="return showCalendar('dtAlertaSMS', '%d/%m/%Y');">&nbsp;</td>                
                            <td><b>&nbsp;Horário:</b></td>                
                            <td align="left">
                                <html:text name="Form" property="idHorarioSMS" maxlength="5" size="6"  onkeyup="checaHora(this);"/>
                            
                            </td>                
                        </tr>
                    </table>
                    
                </fieldset>
                
            </div>     
            

            <div id="divSemAlerta" >
                    <table width="100%">
                        <tr>
                            <td align="center"><br><br>
                                <b>AGENDAMENTO SEM LEMBRETE DE AVISO.</b>
                            </td>
                        </tr>
                    </table>
                    
            </div>                       

            <table border="0" width="100%">
                <tr>
                    <td align="right" width="100%" height="20">
                        <img style="border:none;" onClick="gravar(); return false" vspace="5" src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_gravar_over.gif"/>
                    </td>
                </tr>
            </table>

    </form>
    
    <script language="javaScript">
        document.body.style.backgroundColor = '#ededed';
        ocultaCampoEmail(document.getElementById('idTipoEnvio').value);
        ocultaTrEMail(document.getElementById('idEmail').value);
        parent.oculta_div();
    </script>

</body>
</html>