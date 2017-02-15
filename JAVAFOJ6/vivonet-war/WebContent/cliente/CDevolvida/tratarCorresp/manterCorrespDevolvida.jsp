<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<bean:define id="Form"    name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="filtroForm"/>
<bean:define id="Motivos" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="filtroForm.correspDevolvidaVO.motivoDevolucaoVOArray"/>
<bean:define id="Tipos"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="filtroForm.correspDevolvidaVO.tipoCorrespondenciaVOArray"/>
<bean:define id="Status"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="filtroForm.correspDevolvidaVO.statusCorrespondenciaArray"/>

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute value="Correspondência Devolvida" name="title"></netui-template:setAttribute>
    <netui-template:setAttribute value="Gestão de Clientes" name="modulo"></netui-template:setAttribute>
    <netui-template:section name="headerSection">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/calendar.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/formatPhoneNumber.js"></script>
        <script>
            dia = new Date();
            mes = dia.getMonth() + 1;
            hoje = dia.getDate().toString() + "/" + mes.toString() + "/" + dia.getYear().toString();
            
            function valida1mes(objInicial,objFinal){
                objSomaDias = document.forms[0].somaDias;
                somaDiasData(objInicial,objSomaDias,31);
                resposta = validaDataFinal(objFinal.value,objSomaDias.value);
                return resposta;
            }
    
            function validaPesquisa( parametro ){                                                                                                  
                if (parametro=="pesquisa"){
                    if(document.forms[0].dtDevolucaoIni.value == "" && document.forms[0].dtDevolucaoFim.value == "" && document.forms[0].dtRegistroIni.value == "" && document.forms[0].dtRegistroFim.value == ""){
                        alert('Favor selecionar ou uma Data de Devolução ou uma Data de Registro')
                    }else if((document.forms[0].dtDevolucaoIni.value != "" && document.forms[0].dtDevolucaoFim.value == "") || (document.forms[0].dtDevolucaoIni.value == "" && document.forms[0].dtDevolucaoFim.value != "")){
                        alert('Favor selecionar Data de Devolução Inicial e Data de Devolução Final')
                    }else if(document.forms[0].dtDevolucaoIni.value != "" && document.forms[0].dtDevolucaoFim.value != "" && !validaDataFinal(document.forms[0].dtDevolucaoIni.value,document.forms[0].dtDevolucaoFim.value)){
                        alert('Data de Devolução Inicial maior que Data de Devolução Final, favor corrigir!')
                    }else if(document.forms[0].dtDevolucaoIni.value != "" && document.forms[0].dtDevolucaoFim.value != "" && !valida1mes(document.forms[0].dtDevolucaoIni,document.forms[0].dtDevolucaoFim)){
                        alert('Data de Devolução Final deve ser no maximo\n31 dias maior do que Data Devolução Inicial,\nfavor corrigir!')
                    }else if((document.forms[0].dtRegistroIni.value != "" && document.forms[0].dtRegistroFim.value == "") || (document.forms[0].dtRegistroIni.value == "" && document.forms[0].dtRegistroFim.value != "")){
                        alert('Favor selecionar Data de Registro Inicial e Data de Registro Final')
                    }else if(document.forms[0].dtRegistroIni.value != "" && document.forms[0].dtRegistroFim.value != "" && !validaDataFinal(document.forms[0].dtRegistroIni.value,document.forms[0].dtRegistroFim.value)){
                        alert('Data de Registro Inicial maior que Data de Registro Final, favor corrigir!')
                    }else if(document.forms[0].dtRegistroIni.value != "" && document.forms[0].dtRegistroFim.value != "" && !valida1mes(document.forms[0].dtRegistroIni,document.forms[0].dtRegistroFim)){
                        alert('Data de Registro Final deve ser no maximo\n31 dias maior do que Data Registro Inicial,\nfavor corrigir!')
                    }else if(document.forms[0].dtDevolucaoIni.value!="" && !validaDataFinal(document.forms[0].dtDevolucaoIni.value,hoje)){
                        alert('Data de Devolução Inicial maior que a data de hoje,\nfavor corrigir!')                                
                    }else if(document.forms[0].dtRegistroIni.value!="" && !validaDataFinal(document.forms[0].dtRegistroIni.value,hoje)){
                        alert('Data de Registro Inicial maior que a data de hoje,\nfavor corrigir!')
                    }else if(document.forms[0].dtDevolucaoFim.value!="" && !validaDataFinal(document.forms[0].dtDevolucaoFim.value,hoje)){
                        alert('Data de Devolução Final maior que a data de hoje,\nfavor corrigir!');
                    }else if(document.forms[0].dtRegistroFim.value!="" && !validaDataFinal(document.forms[0].dtRegistroFim.value,hoje)){
                        alert('Data de Registro Final maior que a data de hoje,\nfavor corrigir!');
                    }else{
                        document.forms[0].nrLinha.value = limpaInteiro(document.forms[0].nrLinha.value);
                        document.forms[0].action="pesquisar.do?destino="+parametro;
                        document.forms[0].target="frmPesquisa";
                        mostrar_div();
                        document.forms[0].submit();
                        //checaTelefone(document.forms[0].nrLinha);
                        formatPhoneNumberObj(document.forms[0].nrLinha);
                    }
                }else{
                    if (document.forms[0].id.value==""){
                        alert('Execute uma pesquisa e selecione um item válido para tratar!');
                    }else{
                        document.forms[0].id.length?valorID = document.forms[0].id[parametro].value:valorID = document.forms[0].id.value;
                        document.forms[0].action="pesquisar.do?destino=altera"+"&idArray="+parametro+"&idCorresp="+valorID;
                        //dvTratar.style.display = '';
                        document.forms[0].target="_parent";
                        mostrar_div();
                        document.forms[0].submit();
                    }
                }
            }
            
            function limpar(){
                document.forms[0].action="manterDevolvida.do";
                document.forms[0].target="_parent";
                mostrar_div();
                document.forms[0].submit();
            }        
        </script>
    </netui-template:section>
    <netui-template:section name="bodySection">
        <acesso:controlHiddenItem nomeIdentificador="cli_mcd_verpagina">
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
        <script>
            mostrar_div();
        </script>
        <script for="window" event="onload">
            oculta_div();
        </script>
        <div><img width="1" height="5" src="/FrontOfficeWeb/resources/images/transp.gif"></div>
        <vivo:sessao id="correspondenciadevolvida" height="470" width="790" label="Correspond&ecirc;ncia Devolvida" operacoes="Filtro" scroll="no">
        <form action="pesquisar.do" method="POST" name="filtroForm">
        <table width="780" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td>
                    <table  width="450" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <td height="25" width="160" style="text-indent:6px;">Data da Devolução:</td>
                            <td width="290">
                                <html:text name="Form" property="filtroCorrespondencia.dtDevolucaoIni" readonly="true" styleId="dtDevolucaoIni" size="10"/><img src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:hand;" title="Calendário" onclick="return showCalendar('filtroCorrespondencia.dtDevolucaoIni', '%d/%m/%Y');"> at&eacute;                              
                                <html:text name="Form" property="filtroCorrespondencia.dtDevolucaoFim" readonly="true" styleId="dtDevolucaoFim" size="10"/><img src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:hand;" title="Calendário" onclick="return showCalendar('filtroCorrespondencia.dtDevolucaoFim', '%d/%m/%Y');">
																<input type="Hidden" name="somaDias">
                            </td>                         
                        </tr>                                
                        <tr>
                            <td height="25" width="110" style="text-indent:6px;">Data do Registro:</td>
                            <td>
                                <html:text name="Form" property="filtroCorrespondencia.dtRegistroIni" readonly="true" styleId="dtRegistroIni" size="10"/><img src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:hand;" title="Calendário" onclick="return showCalendar('filtroCorrespondencia.dtRegistroIni', '%d/%m/%Y');"> at&eacute;                              
                                <html:text name="Form" property="filtroCorrespondencia.dtRegistroFim" readonly="true" styleId="dtRegistroFim" size="10"/><img src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:hand;" title="Calendário" onclick="return showCalendar('filtroCorrespondencia.dtRegistroFim', '%d/%m/%Y');">
                            </td>                         
                        </tr>                            
                        <tr>
                            <td height="25" style="text-indent:6px;">Tipo de Correspondência:</td>
                            <td>&nbsp;
                                <html:select name="Form" property="filtroCorrespondencia.idTipoCorrespondencia" style="width:200px">
                                    <html:options property="id" collection="Tipos" labelProperty="descricao"/>
                                </html:select> 
                            </td>                                                            
                        </tr>
                        <tr>                                                                                
                            <td height="25" style="text-indent:6px;" width="130">Motivo da Devolução:</td>
                            <td colspan="3">&nbsp;
                                <html:select name="Form" property="filtroCorrespondencia.idMotivoDevolucao" style="width:200px">
                                    <html:options property="id" collection="Motivos" labelProperty="descricao"/>
                                </html:select>
                            </td>  
                        </tr>
                        <tr>
                            <td height="25" style="text-indent:6px;">Status:</td>
                            <td colspan="3">&nbsp;
                                <html:select name="Form" property="filtroCorrespondencia.idStatus" style="width:200px">
                                    <html:options property="idStatus" collection="Status" labelProperty="dsStatus"/>
                                </html:select>
                            </td>                                                                  
                        </tr>
                        <tr><td height="10"></td></tr>                                                                                  
                        <tr>
                            <td style="text-indent:6px;">Linha:</td>
                            <td colspan="3">                                                                        
                                <html:text name="Form" style="width:85px;" onblur="formatPhoneNumberObj(this)" maxlength="11" property="filtroCorrespondencia.nrLinha" styleId="nrLinha"/>
                               
                            </td>                                                                 
                        </tr>                       
                   </table>
                </td>                
            </tr>
            <tr>
                <td>
                    <table width="100%">
                        <tr>
                            <td>
                                <img onClick="window.location.href='/FrontOfficeWeb/index.jsp';" vspace="5" style="border:0px;cursor:hand;" hspace="6" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_voltar_over.gif'"/>
                            </td>
                            <td align="right">                            
                                <img onClick="limpar(); return false;" vspace="5" style="border:0px;cursor:hand;" src="/FrontOfficeWeb/resources/images/bt_limparcampos_nrml.gif" hspace="6" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_limparcampos_nrml.gif';" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_limparcampos_over.gif';"/>
                                <acesso:controlHiddenItem nomeIdentificador="cli_mcdd_pesq"><img onclick="validaPesquisa('pesquisa')" style="border:0px;cursor:hand;" vspace="5" hspace="6" src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" onmouseout="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif'" onmouseover="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif'"/></acesso:controlHiddenItem>
                            </td>                           
                        </tr>
                    </table>
                </td>
            <tr>
                <td valign="top">                    
                    <iframe src="" frameborder="0" name="frmPesquisa" width="776" height="280" scrolling="no"></iframe>
                </td>
            </tr>
        </table>
    <logic:equal value="TRUE" name="Form" property="inRefresh">
        <script>
            validaPesquisa('pesquisa');
        </script>
    </logic:equal>
    </form>
        <vivo:quadroFlutuante id="dvTratar" idIframe="ifrmTratar" width="780" height="550" spacesTop="0" spacesLeft="0" display="none" url="<%=request.getContextPath()%>" label="Tratar Correspondencia Devolvida"/>
    </vivo:sessao>    
</acesso:controlHiddenItem>                             
</netui-template:section>
</netui-template:template>