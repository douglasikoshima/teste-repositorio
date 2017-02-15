<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%> 

<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">

<netui-template:setAttribute name="title" value="Ação Incentivo"/>
<netui-template:setAttribute name="modulo" value="Administração"/>

<netui-template:section name="headerSection">

<link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
<script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/vivoheader.js" ></script>
<script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/calendar.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/validacao.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>

<script type="text/javascript" language="javascript">

        function validaHoraInicialFinal(time1, time2) {
            var seconds1 = time1.split(":")[0] * 3600 + time1.split(":")[1] * 60;
            var seconds2 = time2.split(":")[0] * 3600 + time2.split(":")[1] * 60;
            return Math.abs(seconds1 - seconds2); 
        }    

        function voltar() {
            document.forms[0].action = 'begin.do';
            document.forms[0].submit();   
        }

        function manterAcaoIncentivo() {
            var f = document.forms[0];
            if (validarFormulario()) {
                document.forms[0].submit();     
            }
        }

        function closeWindow() {
            document.forms[0].action = 'begin.do';
            document.forms[0].submit();     
        }        

        function validarFormulario() {
            if (trim($F('dsacaoincentivo'))=="") {
                alert("Por favor, digite a Descrição");
                $('dsacaoincentivo').focus();
                return false;
            }        
            
            if (trim($F('dataInicio'))=="") {
                alert("Por favor, digite a Data Início");
                $('dataInicio').focus();
                return false;
            }       

            if (trim($F('horaInicio'))=="") {
                alert("Por favor, digite a Hora Início");
                $('horaInicio').focus();
                return false;
            } 

            if (trim($F('dataTermino'))=="") {
                alert("Por favor, digite a Data Término");
                $('dataTermino').focus();
                return false;
            }       

            if (trim($F('horaTermino'))=="") {
                alert("Por favor, digite a Hora Término");
                $('horaTermino').focus();
                return false;
            }

            if (!validaDataFinal(trim($F('dataInicio')),trim($F('dataTermino'))) ) {
                alert("Data e Hora Término deve ser posterior a Data e Hora Início");
                $('dataTermino').focus();
                return false;
            }

            if (trim($F('dataInicio'))==trim($F('dataTermino'))) {
                if (validaHoraInicialFinal(trim($F('horaInicio')),trim($F('horaTermino'))) == 0) {
                    alert("Data e Hora Término deve ser posterior a Data e Hora Início");
                    $('horaTermino').focus();
                    return false;
                }
            }       

            if (trim($F('msgantesinicioacao'))=="") {
                alert("Por favor, digite a Mensagem exibida antes do Início");
                $('msgantesinicioacao').focus();
                return false;
            }      

            if (trim($F('msgaposinicioacao'))=="") {
                alert("Por favor, digite a Mensagem exibida após o Término");
                $('msgaposinicioacao').focus();
                return false;
            }                                                 

            return true;
        }
    </script>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="acaoIncentivoForm" type="admsistemas.acaoIncentivo.AcaoIncentivoController.AcaoIncentivoForm"/>

</netui-template:section>
<netui-template:section name="bodySection">

<jsp:include page="/resources/menus/MenuPrincipal.jsp" />
<div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>

<vivo:sessao id="qdMain" height="468" width="790" label="Ação Incentivo" operacoes="Manter Ação Incentivo" scroll="no">
    
<html:form action="/admsistemas/acaoIncentivo/manterAcaoIncentivo.do" enctype="multipart/form-data" method="post" onsubmit="return false">
<html:hidden name="Form" property="cdacaoincentivo" styleId="cdacaoincentivo" />

<table cellpadding="2" cellspacing="1" style="margin-left:35px;">
    <tr>
        <td align="right" nowrap>Descrição:</td>
        <td colspan="5">
             <html:text name="Form" property="dsacaoincentivo" styleId="dsacaoincentivo;" maxlength="50" style="width:550px;margin-left:3px;" />
        </td>
    </tr>
    <tr>
        <td align="right" nowrap></td>
        <td>Data Inicio</td>
        <td>Hora Inicio</td>
        <td></td>
        <td>Data Termino</td>
        <td>Hora Termino</td>
    </tr>     
    <tr>
        <td align="right" nowrap>Data Hora Inicio / Data Hora Termino:</td>
        <td>        
            <html:text name="Form" property="dataInicio" size="10" maxlength="10" onkeyup ="Formatar(this.value, this.form.name, this.name, 'data');" onchange="Formatar(this.value, this.form.name, this.name, 'data');" onblur="validaData(this);"/><img id="imgCalendDtAbIni" src="/FrontOfficeWeb/resources/images/calendario.gif" style="cursor:hand;" title="Calendário" onclick="return showCalendar('dataInicio', '%d/%m/%Y');">
        </td>
        <td><html:text name="Form" property="horaInicio" styleId="horaInicio" maxlength="50" style="width:80px;" onkeyup ="Formatar(this.value, this.form.name, this.name, 'hora');" onchange="Formatar(this.value, this.form.name, this.name, 'hora');" /></td>        
        <td>Até</td>
        <td><html:text name="Form" property="dataTermino" size="10" maxlength="10" onkeyup ="Formatar(this.value, this.form.name, this.name, 'data');" onchange="Formatar(this.value, this.form.name, this.name, 'data');" onblur="validaData(this);" /><img id="imgCalendDtAbIni" src="/FrontOfficeWeb/resources/images/calendario.gif" style="cursor:hand;" title="Calendário" onclick="return showCalendar('dataTermino', '%d/%m/%Y');">
        </td>
        <td><html:text name="Form" property="horaTermino" styleId="horaTermino" maxlength="50" style="width:80px;" onkeyup ="Formatar(this.value, this.form.name, this.name, 'hora');" onchange="Formatar(this.value, this.form.name, this.name, 'hora');" /></td>
    </tr>    
    <tr>
        <td align="right" nowrap>Mensagem exibida antes do inicio:</td>
        <td colspan="5">
             <html:textarea name="Form" property="msgantesinicioacao" styleId="msgantesinicioacao"  maxlength="50" style="width:550px;margin-left:3px;" />
        </td>
    </tr>        
    <tr>
        <td align="right" nowrap>Mensagem exibida após o Término:</td>
        <td colspan="5">
             <html:textarea name="Form" property="msgaposinicioacao" styleId="msgaposinicioacao" maxlength="50" style="width:550px;margin-left:3px;" />
        </td>
    </tr>          
    <tr>
        <td align="right" nowrap>Ativo ?:</td>
        <td colspan="5">
             <html:checkbox name="Form" property="inativo" styleId="intipoacaoincentivo" value="1" styleClass="checkbox"/>
        </td>
    </tr>      
</table>
    
<table cellpadding="2" cellspacing="1" align="right" style="margin-right:15px;">    
    <tr>
        <td colspan="5" valign="top" align="right">

            <img id="btVoltar"
                 value="Voltar"
                 src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif"
                 rolloverImage="/FrontOfficeWeb/resources/images/bt_voltar_over.gif"
                 style="border:none;margin-right:4px;"
                 onMouseUp="voltar()" />

            <img id="btGravar"
                 value="Gravar"
                 src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif"
                 rolloverImage="/FrontOfficeWeb/resources/images/bt_gravar_over.gif"
                 style="border:none;margin-right:4px;"
                 onMouseUp="manterAcaoIncentivo()" />
        </td>
    </tr>
</table>  
</html:form>     

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

</vivo:sessao>

<vivo:alert atributo="msgStatus" scope="request" afterFunction="closeWindow()" />
<vivo:alert atributo="msgErro" scope="request" />
</netui-template:section>

</netui-template:template>