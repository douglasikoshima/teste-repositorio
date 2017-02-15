<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<bean:define id="Form"        name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"  property="monitorarHexaForm"/>
<bean:define id="piConsHexa"  name="piConsHexa"/>
<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute value="Monitorar as Consultas ao Hexa" name="title"/>
    <netui-template:setAttribute value="Gestão de Clientes" name="modulo"/>
    <netui-template:section name="headerSection">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/calendar.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/formatPhoneNumber.js"></script>
        <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
            <!--
                oculta_div();
                if(document.forms[0].elements['filtroHexa.cdHexa'].value.length>0){
                    document.forms[0].fieldDecimal.disabled = true;
                }else if(document.forms[0].elements['filtroHexa.cdDecimal'].value.length>0){
                    document.forms[0].fieldHexa.disabled = true;
                }else{
                    document.forms[0].fieldDecimal.disabled  = false;
                    document.forms[0].fieldHexa.disabled  = false;
                }
            -->
        </SCRIPT>
        <script language="Javascript">
            dia = new Date();
            mes = dia.getMonth() + 1;
            hoje = dia.getDate().toString() + "/" + mes.toString() + "/" + dia.getYear().toString();


            function valida1mes(objInicial,objFinal){
                objSomaDias = document.forms[0].somaDias;
                somaDiasData(objInicial,objSomaDias,31);
                resposta = validaDataFinal(objFinal.value,objSomaDias.value);
                return resposta;
            }

            function valida(){
                if(document.forms[0].dtInicio.value == ""){
                    alert("Favor selecionar a Data de Início!");
                
                }else if(document.forms[0].dtFim.value == ""){
                    alert("Favor selecionar a Data de Término!");
                
                }else if(!validaDataFinal(document.forms[0].dtInicio.value,document.forms[0].dtFim.value)){
                    alert("Data de Término menor que Data Inicial, favor corrigir!");
                
                }else if(!valida1mes(document.forms[0].dtInicio,document.forms[0].dtFim)){
                    alert('Data de Término deve ser no maximo\n31 dias maior do que Data Inicial,\nfavor corrigir!')
                
                }else if(!validaDataFinal(document.forms[0].dtFim.value,hoje)){                
                    alert('Data de Término maior que o dia de hoje, favor corrigir!');
                
                }else if(document.forms[0].elements['filtroHexa.nrLinha'].value != "" && document.forms[0].elements['filtroHexa.nrLinha'].value.length < 13){
                      alert("Linha inválida, favor corrigir!!");
                
                }else if(document.forms[0].elements['filtroHexa.cdHexa'].value != "" && document.forms[0].elements['filtroHexa.cdHexa'].value.length != 8){
                      alert("Hexa inválido, favor corrigir!!");
                
                }else if(document.forms[0].elements['filtroHexa.cdDecimal'].value != "" && document.forms[0].elements['filtroHexa.cdDecimal'].value > 4294967295){
                      alert("Decimal inválido, favor corrigir!!");
                
                }else{
                    document.forms[0].elements['filtroHexa.nmLogin'].value = trim(document.forms[0].elements['filtroHexa.nmLogin'].value);
                    document.forms[0].elements['filtroHexa.cdHexa'].value = trim(document.forms[0].elements['filtroHexa.cdHexa'].value).toUpperCase();
                    document.getElementById("nrLinhaConsulta").value = limpaInteiro(document.getElementById("nrLinhaConsulta").value);
                    mostrar_div();
                    document.forms[0].submit();
                }
                return false;
            }

            function limpa(){
                document.forms[0].action = "begin.do";
                mostrar_div();
                document.forms[0].submit();
            }
        
            function controlFields(obj){
                if(obj.value.length>0){
                    if(obj.name == 'filtroHexa.cdDecimal'){
                        document.forms[0].fieldHexa.disabled = true;
                    }else{
                        document.forms[0].fieldDecimal.disabled = true;
                    }  
                }else{
                    document.forms[0].fieldDecimal.disabled  = false;
                    document.forms[0].fieldHexa.disabled  = false;
                }  
            }
        </script>
    </netui-template:section>
    <netui-template:section name="bodySection">        
        <jsp:include page="/resources/menus/MenuExterno.jsp" />
        <div><img width="1" height="5" src="/FrontOfficeWeb/resources/images/transp.gif"></div>
        <vivo:sessao id="monitHexa" width="790" height="470" label="Consulta HEXA" operacoes="Monitoramento">
        <html:form action="/extworkflw/ConsultaHexa/monitorarHexa.do" styleId="idMonitorar" method="post">
            <logic:notEqual name="Form" property="inInicio" value="TRUE">
                <logic:notEmpty name="Form" property="idErro">
                    <logic:equal name="Form" property="idErro" value="1">
                        <script>
                            alert('Linha não encontrada!');
                        </script>
                    </logic:equal>
                    <logic:equal name="Form" property="idErro" value="2">
                        <script>
                            alert('Hexa não encontrado!');
                        </script>
                    </logic:equal>
                    <logic:equal name="Form" property="idErro" value="3">
                        <script>
                            alert('Decimal não encontrado!');
                        </script>
                    </logic:equal>
                    <logic:equal name="Form" property="idErro" value="4">
                        <script>
                            alert('Login não encontrado!');
                        </script>
                    </logic:equal>
                </logic:notEmpty>
            </logic:notEqual>
            <div class="tbl_bgGray">
            <table border="0" cellpadding="0" cellspacing="0">
                <tr><td height="5"></td></tr>
                <tr>                                
                    <td style="text-indent:8px;" width="50">Data:</td>
                    <td colspan="3">
                        &nbsp;de 
                        <html:text style="width:65px;" name="Form" property="filtroHexa.dtInicio" styleId="dtInicio"/>
                        <img src="<%=request.getContextPath()%>/resources/images/calendario.gif" style="cursor:hand;" title="Calendário" onclick="return showCalendar('dtInicio', '%d/%m/%Y');">
                        at&eacute;&nbsp;
                        <html:text style="width:65px;" name="Form" property="filtroHexa.dtFim" styleId="dtFim"/>
                        <img src="<%=request.getContextPath()%>/resources/images/calendario.gif" style="cursor:hand;" title="Calendário" onclick="return showCalendar('dtFim', '%d/%m/%Y');">
                        <input type="Hidden" name="somaDias">
                    </td>
                </tr>
                <tr><td height="10"></td></tr>
                <tr>
                    <td style="text-indent:8px;">Linha:</td>
                    <td width="135"><html:text name="Form" property="filtroHexa.nrLinha" maxlength="11" onblur="formatPhoneNumberObj(this)" styleClass="textfield" styleId="nrLinhaConsulta"/></td>
                        
                    <td>Login:</td>
                    <td><html:text name="Form" property="filtroHexa.nmLogin" maxlength="40" styleClass="textfield"/></td>
                <tr>
                    <td style="text-indent:8px;">Hexa:</td>
                    <td><html:password name="Form" property="filtroHexa.cdHexa" styleId="fieldHexa" onkeyup="checaHexa(this);controlFields(this);" onkeypress="checaHexa(this);controlFields(this);" maxlength="8" styleClass="textfield"/></td>
                    <td>Decimal:</td>
                    <td><html:password name="Form" property="filtroHexa.cdDecimal" styleId="fieldDecimal" onkeyup="checaInteiro(this);controlFields(this);" onkeypress="checaInteiro(this);controlFields(this);" maxlength="10" styleClass="textfield"/></td>
                </tr>
            </table>
            <table width="770">
                <tr>
                    <td valign="middle">
                        <img style="border:none;cursor:hand;" vspace="5" hspace="3" onClick="window.location.href='/FrontOfficeWeb/index.jsp'; return false" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif"/>
                    </td>
                    <td valign="middle" align="right">
                        <img style="border:none;cursor:hand;" onClick="limpa();" vspace="5" src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif"/>                        
                            <img style="border:none;cursor:hand;" onClick="valida();" vspace="5" src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif"/>                        
                    </td>               
                </tr>
            </table>
            </div>
        </html:form>
        <table width="100%" border="0" cellpadding="1" cellspacing="0">
            <tr>
                <td align="center">            
                    <vivo:tbTable selectable="true" onRowClick="" layoutWidth="750" layoutHeight="300" tableWidth="750" styleId="tableTitle" sortable="true">
                        <vivo:tbHeader>
                            <vivo:tbHeaderColumn align="center" width="26%" type="date">Data/Hora</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="center" width="16%" type="string">Linha</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="center" width="22%" type="string">Tipo Linha</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="center" width="10%" type="string">Tipo Docto.</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="center" width="16%" type="string">Nº Docto.</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="center" width="10%" type="string">Login</vivo:tbHeaderColumn>
                        </vivo:tbHeader>
                        <vivo:tbRows>
                            <logic:iterate id="info" name="piConsHexa">
                                <vivo:tbRow key="linha1">
                                    <vivo:tbRowColumn><bean:write name="info" property="dtConsulta"/></vivo:tbRowColumn>
                                    <vivo:tbRowColumn><bean:write name="info" property="nrLinha"/></vivo:tbRowColumn>
                                    <vivo:tbRowColumn><bean:write name="info" property="dsTipoLinha"/></vivo:tbRowColumn>
                                    <vivo:tbRowColumn><bean:write name="info" property="dsTipoDocumento"/></vivo:tbRowColumn>
                                    <vivo:tbRowColumn><bean:write name="info" property="nrDocumento"/></vivo:tbRowColumn>
                                    <vivo:tbRowColumn><bean:write name="info" property="nmLogin"/></vivo:tbRowColumn>
                                </vivo:tbRow>
                            </logic:iterate>
                        </vivo:tbRows>
                    </vivo:tbTable>
                </td>
            </tr>
        </table>
        </vivo:sessao>        
    </netui-template:section>
</netui-template:template>