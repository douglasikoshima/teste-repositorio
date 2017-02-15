<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<bean:define id="FormOperadora" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formOperadora"/>
<bean:define id="OperadorasAssociadasVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formOperadora.operadorasAssociadasVO"/>
<bean:define id="OperadorasExistentesVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formOperadora.operadorasExistentesVO"/>
<bean:define id="listaOperadoras" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formOperadora.listaOperadoras"/>

   

<head>    
    
    <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
    <link href="<%=request.getContextPath()%>/resources/css/calendar.css" type="text/css" rel="stylesheet"/>
    <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/calendar.js"></script>
        
    <script language="Javascript">
        function salvar() {
            if(document.forms[0].dtInicioVigencia.value != '' && document.forms[0].arrayOperadoraAssociada.length == 0)
            {
                alert('É necessário Associar pelo menos uma Regional.');
                return false;
            }            
            if((document.forms[0].dtInicioVigencia.value == '') && (search())){
                var hoje = "";//new Date();
                
                // COMENTADO POR DARCIO/LEANDRO EM 06/12/2005 DEVIDO A INCIDENCIA 3363
                // escondeOperadoraInicio();
                
                document.forms[0].dtInicioVigencia.value = hoje//hoje.getDate()+"/"+parseInt(hoje.getMonth()+1)+"/"+hoje.getFullYear();
            } else
            if((document.forms[0].dtInicioVigencia.value == '') && (!search())){
                alert('Data Inicio obrigatória.');
                return;
            }
            
            if (!validaData(document.forms[0].dtInicioVigencia.value) && document.forms[0].dtInicioVigencia.value != '' && !search()) {
                alert('Data deve estar no formato DD/MM/AAAA.');
                document.forms[0].dtInicioVigencia.focus();
                return false;
            }
            
            if(document.forms[0].indeterminado.value == '0'){
                for ( i = 0; i < document.forms[0].arrayOperadoraAssociada.options.length; i++ ){
                    document.forms[0].arrayOperadoraAssociada.options[i].selected = true;
                }
                document.forms[0].action='salvaOperadora.do';
                parent.mostrar_div();
                document.forms[0].submit();    
            }else{
                if(document.forms[0].dtFimVigencia.value == ''){
                    alert('Data Fim obrigatória.');                    
                    return;
                } else if (!validaData(document.forms[0].dtFimVigencia.value) && document.forms[0].dtFimVigencia.value != '' && !search()) {
                    alert('Data deve estar no formato DD/MM/AAAA.');
                    document.forms[0].dtFimVigencia.focus();
                    return false;
                } else{
                   if(validaDataFinal(document.forms[0].dtInicioVigencia.value,document.forms[0].dtFimVigencia.value)){
                        for ( i = 0; i < document.forms[0].arrayOperadoraAssociada.options.length; i++ ){
                            document.forms[0].arrayOperadoraAssociada.options[i].selected = true;
                        }
                        document.forms[0].action='salvaOperadora.do';
                        parent.mostrar_div();
                        document.forms[0].submit();
                    }
                    else{
                        alert('Data de fim deve ser maior que data de inicio de vigência.');
                        return;
                    } 
                }
            }
        }

        function editaOperadoraOriginal(idUFOperadora,dtInicioVigencia,dtFimVigencia){
            document.forms[0].idUFOperadoraEditado.value = idUFOperadora;
            document.forms[0].dtInicioVigencia.value = dtInicioVigencia;
            document.forms[0].dtFimVigencia.value = dtFimVigencia;
            document.forms[0].action = 'editaOperadora.do';
            mostrar_div();
            document.forms[0].submit();
        }

        function editaOperadora(idUFOperadora,dtInicioVigencia,dtFimVigencia, nmUfOperadora){
            
            //document.forms[0].idUFOperadoraEditado.value = idUFOperadora;
            //document.forms[0].dtInicioVigencia.value = dtInicioVigencia;
            //document.forms[0].dtFimVigencia.value = dtFimVigencia;

            divAlterar.style.display = '';
            divFrameAlterar = document.getElementById('ifrmAlterar');
            divFrameAlterar.src = 'alteraUfOperadora.jsp?idUFOperadora=' 
                                            + idUFOperadora 
                                            + '&dtInicioVigencia=' + dtInicioVigencia
                                            + '&dtFimVigencia=' + dtFimVigencia
                                            + '&nmUfOperadora=' + nmUfOperadora;
            parent.mostrar_div();
        }

        function removeOperadora(idUFOperadora){
            if (window.confirm("Confirma remoção do item?")) {
                document.forms[0].idUFOperadoraEditado.value = idUFOperadora;
                document.forms[0].action = 'removeOperadora.do';
                parent.mostrar_div();
                document.forms[0].submit();
            }
        }

        var tirandoMembros = false;
        var poeMembros = false;
        function transfereSelecaoListaTira(listaOrigem, listaDestino)
        {
            var i;
            for(i = 0; i<listaOrigem.options.length; i++)
                if(listaOrigem.options[i].selected) {
                    listaDestino.options[listaDestino.options.length] = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);
                    tirandoMembros = true;
                    poeMembros = false;
                }
                    
            for(i = listaOrigem.options.length-1; i>=0; i--)
                if(listaOrigem.options[i].selected)
                    listaOrigem.options[i] = null;
        }
        function transfereSelecaoListaPoe(listaOrigem, listaDestino)
        {
            var i;
            for(i = 0; i<listaOrigem.options.length; i++)
                if(listaOrigem.options[i].selected) {
                    listaDestino.options[listaDestino.options.length] = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);
                    poeMembros = true;
                    tirandoMembros = false;
                }
                    
            for(i = listaOrigem.options.length-1; i>=0; i--)
                if(listaOrigem.options[i].selected)
                    listaOrigem.options[i] = null;
        }

        function initPagina() { }
        
        function escondeOperadoraInicio()
        {
            calendario = document.getElementById('dtOperadoraInicio');
            calendario.style.visibility = 'hidden';
        }

        function exibeOperadoraInicio()
        {
            calendario = document.getElementById('dtOperadoraInicio');
            calendario.style.visibility = 'visible';
        }

        function mostraVigencia(opcao)
        {
            calendario = document.getElementById('calVigencia');
            calendInput = document.getElementById('dtFimVigencia');
            
            if (opcao == '1') {
                calendario.style.visibility = 'visible';                
            } else {
                calendInput.value = '';
                calendario.style.visibility = 'hidden';
            }
        }
        
        function Comparar(dtMenor,dtMaior)
        {
            var Parte1=dtMenor.split("/");
            var Parte2=dtMaior.split("/");
            var DataMenor=(Parte1[2]+Parte1[1]+Parte1[0]);
            var DataMaior=(Parte2[2]+Parte2[1]+Parte2[0]);
            if (DataMaior < DataMenor) { 
                alert("Data Inicial deve ser menor que Data Final.");
                return false;
            }else{
                return true;
            }
        }
        
        var arrayAssociadoClone;
        
        function capturaClone()
        {
            arrayAssociadoClone = new Array(document.forms[0].arrayOperadoraAssociada.value);
            
            for(i=0;i < document.forms[0].arrayOperadoraAssociada.length;i++)
            {
                arrayAssociadoClone[i] = document.forms[0].arrayOperadoraAssociada[i].value;
            }
        }

        function search()
        {
            flgInclui = false;
            
            if(document.forms[0].arrayOperadoraAssociada.length == 0)
                return true;
            
            for(i=0;i < document.forms[0].arrayOperadoraAssociada.length; i++)
            {
                flgInclui = false;

                for(x=0;x < arrayAssociadoClone.length; x++)
                {
                    if(arrayAssociadoClone[x] ==  document.forms[0].arrayOperadoraAssociada[i].value)
                    {
                        flgInclui = true;
                        continue;
                    }
                }
                
                if(flgInclui == false)
                    return flgInclui;
            }

            return flgInclui;
        }

    </script>
    <script language="javascript" for="window" event="onload">
    <!--   
    
        parent.oculta_div();
        capturaClone();
        document.body.style.backgroundColor = '#ededed';
    
        if('<bean:write name="FormOperadora" property="msgError" />' != "")
        {
            alert('<bean:write name="FormOperadora" property="msgError" />');
            exibeOperadoraInicio();
        }
    -->
    </script> 
    
</head>        

<body bgcolor="#ededed">
<acesso:controlHiddenItem nomeIdentificador="adm_vop_verpagina">
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="10"></div>
        
        <form action="salvaOperadora" name="formOperadora" id="formOperadora" method="post">
            
            <html:hidden name="FormOperadora" property="idContato"/>
            <html:hidden name="FormOperadora" property="idUFOperadoraEditado"/>
        <table width="735" align="center" cellpadding="0" cellspacing="0">
            <tr>
                <td width="135" align="center"></td>
                <td width="195" align="center">
                    Regionais Existentes
                </td>
                <td width="70" align="center"></td>
                <td width="330" align="center">
                    Regionais Associadas
                </td>
            </tr>
            <tr>
                <td valign="top">
                
                    <table cellpadding="0" cellspacing="0" width="135">
                        <tr>
                            <td colspan="2" valign="top" height="25"><b>Data de Vigência</b></td>
                        </tr>
                        <tr>
                            <td width="30">De</td>
                            <td width="105">
                                <div id="dtOperadoraInicio" style="visibility:visible;">
                                    <input type="text" id="dtInicioVigencia" name="dtInicioVigencia" style='width:65px;' maxlength="10" onkeypress="checaData(this);"/><img id="imgCalendDtAbFim" src="<%=request.getContextPath()%>/resources/images/calendario.gif" style="cursor:hand;" title="Calendário"  onclick="return showCalendar('dtInicioVigencia', '%d/%m/%Y');">
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>At&eacute;</td>
                            <td style="padding-left:3px;">
                            <div id="dtOperadoraIndeterminado" style="visibility:visible;">
                                <select name="indeterminado" onChange="mostraVigencia(this.value)" style="width:93px;">
                                    <option value="0">Indeterminado</option>
                                    <option value="1">Calend&aacute;rio</option>
                                </select>  
                            </div>
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>
                                <div id="calVigencia" style="visibility:hidden;"><input type="text" id="dtFimVigencia"  name="dtFimVigencia" style="width:65px;" maxlength="10"  onkeypress="checaData(this);"/><img id="imgCalendDtAbFim" src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:hand;" title="Calendário" onclick="return showCalendar('dtFimVigencia', '%d/%m/%Y');"></div></td>
                        </tr>
                    </table>
                </td>
                <td valign="top" width="345" align="center">
                    <html:select name="FormOperadora" property="a" multiple="true" size="9" style="width:192px;">
                        <html:options collection="OperadorasExistentesVO" property="idUFOperadora" labelProperty="sgUf" /> 
                    </html:select>
                </td>
                <td  width="70" valign="middle" align="center" style="padding-top:10px; padding-right:10px;">
                    <img id="btRightSimp" onclick="transfereSelecaoListaPoe(document.formOperadora.a, document.formOperadora.arrayOperadoraAssociada); return false" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_over.gif'" style="border:none;cursor:hand;"/><br><br>
                    <img id="btLeftSimp" onclick="transfereSelecaoListaTira(document.formOperadora.arrayOperadoraAssociada, document.formOperadora.a); return false" src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_over.gif'" style="border:none;cursor:hand;"/>
                </td>
                <td valign="top" width="345"  align="center">
                    <html:select name="FormOperadora" size="9"  property="arrayOperadoraAssociada" multiple="true" style="width:280px;">
                        <html:options collection="OperadorasAssociadasVO" property="idUFOperadora" labelProperty="sgUf" /> 
                    </html:select>
                </td>
            </tr>
        </table>
        
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="10"></div>
        
        <center>
        <vivo:tbTable selectable="true" layoutWidth="720" layoutHeight="115" tableWidth="720" styleId="tableTitle" sortable="true">
            <vivo:tbHeader>
                <vivo:tbHeaderColumn align="left" width="15%" type="string">Data de Vigência</vivo:tbHeaderColumn>					
                <vivo:tbHeaderColumn align="left"   width="35%" type="string">Data de Término</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="left"   width="45%" type="string">Regional</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="5%" type="string"></vivo:tbHeaderColumn>
                <!--vivo:tbHeaderColumn align="center" width="5%" type="string">< /vivo:tbHeaderColumn-->
            </vivo:tbHeader>

            <vivo:tbRows scroll="false">
                <logic:iterate name="FormOperadora" property="listaOperadoras" id="regional">
                    <vivo:tbRow key="linha" >
                        <vivo:tbRowColumn>
                            <bean:write name="regional" property="dtInicioVigencia"/>
                        </vivo:tbRowColumn>
                        <vivo:tbRowColumn>
                            <logic:notEqual name="regional" property="dtFimVigencia" value="">
                                <bean:write name="regional" property="dtFimVigencia"/>
                            </logic:notEqual>
                            <logic:equal name="regional" property="dtFimVigencia" value="">
                                    Indeterminado
                            </logic:equal>
                        </vivo:tbRowColumn>
                        <vivo:tbRowColumn>
                            <bean:write name="regional" property="sgUf"/>
                        </vivo:tbRowColumn>
                        <vivo:tbRowColumn>
                        <acesso:controlHiddenItem nomeIdentificador="adm_vop_alterar">
                            <a onClick="editaOperadora('<bean:write name="regional" property="idUFOperadora"/>',
                                                                '<bean:write name="regional" property="dtInicioVigencia"/>',
                                                                '<bean:write name="regional" property="dtFimVigencia"/>',
                                                                '<bean:write name="regional" property="sgUf"/>');">
                                <img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" border="0" alt="Alterar Regional">
                            </a>
                        </acesso:controlHiddenItem>
                        </vivo:tbRowColumn>                                        
                        <!--vivo:tbRowColumn>
                            <a href="Javascript:removeOperadora('< bean:write name="regional" property="idUFOperadora"/>');">
                                <img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" border="0" alt="Excluir Regional">
                            </a>
                        < /vivo:tbRowColumn-->                                        
                    </vivo:tbRow>
                </logic:iterate>
            </vivo:tbRows>
        </vivo:tbTable>
        </center>
        
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="3"></div>

        <table style="background-color:#ededed;border:1px solid #adadad;" width="735" align="center">
            <tr>
                <td width="100"><b>&nbsp;&nbsp;Legendas:</b></td>
                <td width="150"><img align="middle" src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" border="0">&nbsp;Alterar Regional</td>
                <td width="510">&nbsp;</td>
                <!--td width="150"><img align="middle" src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" border="0">Excluir Regional</td-->
            </tr>
        </table>
                
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
        <table width="740">
            <tr>
                <td align="right">
                <acesso:controlHiddenItem nomeIdentificador="adm_vop_salvar">
                    <img vspace="3" id="btSalvar1" onclick="salvar(); return false" src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_gravar_over.gif'" style="border:none;cursor:hand;"/>
                </acesso:controlHiddenItem>                
                </td>
            </tr>
        </table>
            <vivo:quadroFlutuante id="divAlterar" idIframe="ifrmAlterar" spacesLeft="130" spacesTop="20" url="<%=request.getContextPath()%>" display="none" height="240" width="540" label="Alteração de Regionais"/>                
        </form>    
</acesso:controlHiddenItem>
</body>
