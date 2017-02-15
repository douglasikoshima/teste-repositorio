<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
<netui-template:template templatePage="/resources/jsp/templateUsuarioClean.jsp">
<netui-template:section name="headerSection">
    <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
    <link href="<%=request.getContextPath()%>/resources/css/calendar.css" type="text/css" rel="stylesheet"/>
    <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/calendar.js"></script>
    <script>
        <!--
            function salvar(){
                if(valida()){
                    document.forms[0].action='editaOperadora.do';
                    document.forms[0].target = "_parent";
                    parent.parent.mostrar_div();
                    document.forms[0].submit();
                }
            }

            function valida(){
                if(document.forms[0].dtInicioVigencia.value == ''){
                    alert('Data de Inicio de Vigência é obrigatória.');
                    return false;
                }
                if (!validaData(document.forms[0].dtInicioVigencia.value) && document.forms[0].dtInicioVigencia.value != '' ) {
                    alert('Data deve estar no formato DD/MM/AAAA.');
                    document.forms[0].dtInicioVigencia.focus();
                    return false;
                }
                if (!validaData(document.forms[0].dtFimVigencia.value) && document.forms[0].dtFimVigencia.value != '' ) {
                    alert('Data deve estar no formato DD/MM/AAAA.');
                    document.forms[0].dtFimVigencia.focus();
                    return false;
                }
                if(document.forms[0].indeterminado.value == 1){
                    if(document.forms[0].dtFimVigencia.value == ''){
                        alert('Data de Fim de Vigência é obrigatória.');
                        return false;
                    }else if(!validaDataFinal(document.forms[0].dtInicioVigencia.value,document.forms[0].dtFimVigencia.value)){
                        alert('Data de fim deve ser maior que data de inicio de vigência.');
                        return false;
                    }
                }
                return true;
            }
            
            function mostraVigencia(opcao){
                calendario = document.getElementById('calVigencia');
                calendInput = document.getElementById('dtFimVigencia');
                if (opcao == '1') {
                    calendario.style.visibility = 'visible';                
                } else {
                    calendInput.value = '';
                    calendario.style.visibility = 'hidden';
                }
            }
            
            function montaTela(){
                calendario = document.getElementById('calVigencia');
                var dtFim = '<%=request.getParameter("dtFimVigencia")%>';
                if(dtFim != '' && dtFim != 'null'){
                    calendario.style.visibility = 'visible';    
                    document.forms[0].indeterminado.value = 1;            
                }
            }
        -->
    </script>
</netui-template:section>
<netui-template:section name="bodySection">
<acesso:controlHiddenItem nomeIdentificador="adm_altufop_verpagina">
<vivo:quadro id="regionais" width="99.99%" height="99.99%" label="Regionais" scroll="no">
<form action="editaOperadora" id="editaOperadora" name="editaOperadora" onSubmit="return false;" method="post">
<table width="100%" border="0" cellspacing="1" cellpadding="1">
    <tr>
        <td>
        <table width="97%" border="0" cellspacing="1" cellpadding="1" align="center">
            <tr>
                <td align="center">
                <table width="100%" border="0" cellspacing="2" cellpadding="2" align="center" >
                        <tr>
                            <td width="30%"><b>Inicio Vigência</b>
                            </td>
                            <td width="70%">&nbsp;<input type="text" id="dtInicioVigencia" name="dtInicioVigencia" onkeypress="checaData(this);" value="<%= (String) request.getParameter("dtInicioVigencia")%>"  style='width:65px;' maxlength="10"/><img id="imgCalendDtAbFim" src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:hand;" title="Calendário" onclick="return showCalendar('dtInicioVigencia', '%d/%m/%Y');">
                            </td>
                        </tr>
                        <tr>
                            <td width="30%"><b>Fim Vigência</b>
                            </td>
                            <td style="padding-left:3px;">
                                <select name="indeterminado" onChange="mostraVigencia(this.value)" style="width:93px;">
                                    <option value="0">Indeterminado</option>
                                    <option value="1">Calend&aacute;rio</option>
                                </select>  
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>
                                <div id="calVigencia" style="visibility:hidden;"><input type="text" id="dtFimVigencia"  name="dtFimVigencia" onkeypress="checaData(this);" style="width:65px;" maxlength="10" value="<%=(request.getParameter("dtFimVigencia") != null) ? request.getParameter("dtFimVigencia") : ""%>"/><img id="imgCalendDtAbFim" src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:hand;" title="Calendário" onclick="return showCalendar('dtFimVigencia', '%d/%m/%Y');"></div>
                            </td>
                        </tr>
                        <!--tr>
                            <td width="30%"><b>Fim Vigência</b>
                            </td>
                            <td width="70%">&nbsp;<input type="text" id="dtFimVigencia"    name="dtFimVigencia"  value="< %= (String) request.getParameter("dtFimVigencia")%>"  onfocus="this.blur();"  style="width:65px;" maxlength="10"/><img id="imgCalendDtAbFim" src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:hand;" title="Calendário" onclick="return showCalendar('dtFimVigencia', '%d/%m/%Y');">
                            </td>
                        </tr-->
                        <tr>
                            <td width="30%"><b>Operadora</b>
                            <input type="hidden" name="nmUFOperadora" value="<%=request.getParameter("nmUfOperadora")%>"/>
                            <input type="hidden" name="idUFOperadora" value="<%=request.getParameter("idUFOperadora")%>"/>
                            <input type="hidden" name="idContato" value="<%= (String) session.getAttribute("idContato")%>"/>
                            </td>
                            <td width="70%">&nbsp;<%=request.getParameter("nmUfOperadora")%>
                            </td>
                        </tr>
                </table>
                <table width="100%" border="0" cellspacing="2" cellpadding="2" align="center">
                    <tr>
                        <td width="70%">&nbsp;
                        </td>
                        <td width="30%" align="right">
                        <acesso:controlHiddenItem nomeIdentificador="adm_altufop_gravar">
                        	<img vspace="3" onclick="salvar(); return false" id="btSalvar1" src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_gravar_over.gif'" style="border:none;cursor:hand;"/>
                        </acesso:controlHiddenItem>
                        </td>
                    </tr>
                </table>
                </td>
            </tr>
        </table>
        </td>
    </tr>
</table>
    <script language="javascript" for="window" event="onload">
        <!--   
            montaTela();
            parent.parent.oculta_div();
        -->
    </script> 
</form>
</vivo:quadro>
</acesso:controlHiddenItem>
</netui-template:section >
</netui-template:template>
