<%@page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.admsistemas.vo.AdmCalendarioContainerVODocument.AdmCalendarioContainerVO"%>
<%@ page import="br.com.vivo.fo.admsistemas.vo.AdmFeriadoVODocument.AdmFeriadoVO"%>
<%@ page import="java.util.ArrayList"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<bean:define id="FormCalendario"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formCalendario" type="admsistemas.admCalendario.manterCalendario.AdmCalendarioController.FormCalendario"/>
<bean:define id="AdmTipoFeriadoVO"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formCalendario.admTipoFeriadoVO"/>
<bean:define id="ListaUFs"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formCalendario.listaUFs"/>

<bean:define id="anoArray"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formCalendario.cmbCombo.cmbComboArray"/>

<bean:define id="ListaMunicipiosVO"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formCalendario.listaMunicipiosVO"/>
<bean:define id="ListaAdmFeriadoVO"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formCalendario.listaAdmFeriadoVO"/>

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Manter Calendário"/>
    <netui-template:setAttribute name="modulo" value="Administração de Sistemas"/>

    <netui-template:section name="headerSection">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
        <script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/calendar.js"></script>
        <script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/vivoval.js"></script>
    </netui-template:section>

    <netui-template:section name="bodySection">
	<acesso:controlHiddenItem nomeIdentificador="adm_pefe_verpagina">
    
    <script language="Javascript">
    
        function admTipoFeriadoChange()
        {
            var objSel = document.forms[0].admTipoFeriado;
            if(objSel.selectedIndex > 0)
            {
                
                var selText = objSel.options[objSel.selectedIndex].text;
                /*
                    tipo feriado    ufs     municipios
                    
                    nac             n       n
                    est             s       n
                    mun             s       s
                    pt nac          n       n
                    pt est          s       n
                    pt mun          s       s
                */                

                if("Ponte Municipal" == selText || "Municipal" == selText)
                {
                    if(document.getElementById("tdIdUF0"))
                        document.getElementById("tdIdUF0").style.visibility = "visible";
                    
                    if(document.getElementById("tdIdUF1"))
                        document.getElementById("tdIdUF1").style.visibility = "visible";
                    
                    if(document.getElementById("tdIdMunicipio0"))
                        document.getElementById("tdIdMunicipio0").style.visibility = "visible";
                    
                    if(document.getElementById("tdIdMunicipio1"))
                        document.getElementById("tdIdMunicipio1").style.visibility = "visible";                
                }
                else if("Nacional" == selText || "Ponte Nacional" == selText)
                { 
                    if(document.getElementById("tdIdUF0"))
                        document.getElementById("tdIdUF0").style.visibility = "hidden";
                    
                    if(document.getElementById("tdIdUF1"))
                        document.getElementById("tdIdUF1").style.visibility = "hidden";
                    
                    if(document.getElementById("tdIdMunicipio0"))
                        document.getElementById("tdIdMunicipio0").style.visibility = "hidden";
                    
                    if(document.getElementById("tdIdMunicipio1"))
                        document.getElementById("tdIdMunicipio1").style.visibility = "hidden";                  
                }
                else if("Estadual" == selText || "Ponte Estadual" == selText)
                { 
                    if(document.getElementById("tdIdUF0"))
                        document.getElementById("tdIdUF0").style.visibility = "visible";
                    
                    if(document.getElementById("tdIdUF1"))
                        document.getElementById("tdIdUF1").style.visibility = "visible";
                        
                    if(document.getElementById("tdIdMunicipio0"))
                        document.getElementById("tdIdMunicipio0").style.visibility = "hidden";
                        
                    if(document.getElementById("tdIdMunicipio1"))
                        document.getElementById("tdIdMunicipio1").style.visibility = "hidden";      
                }                

            }
        }
        
        function pesquisa()
        {
            if(trim(document.forms[0].dtFeriado.value) == '' && 
               trim(document.forms[0].dsFeriado.value) == '' && 
               document.forms[0].indMovel.value=='000' &&  
               document.forms[0].admTipoFeriado.value =='000' && 
               document.forms[0].inRelatorio.value=='000' && 
               document.forms[0].idUF.value=='000')
            {
               
                alert('É necessário a escolha de pelo menos um campo para fazer a pesquisa.');  
                
            }else if(document.forms[0].ano.selectedIndex == 0 && trim(document.forms[0].dtFeriado.value) == '')
            {
                alert('É necessário a escolha do campo Data ou o campo Ano');
                
            }else if(document.forms[0].ano.selectedIndex != 0 && trim(document.forms[0].dtFeriado.value) != '')
            {
                alert('Selecione apenas Data ou Ano.');
            
             }else{
                document.forms[0].target = '_self';
                document.forms[0].action = 'pesquisarFeriado.do';
                mostrar_div();
                document.forms[0].submit();
             }
        }

        function montaListaUF()
        {
            if(document.forms[0].idUF.selectedIndex != 0)
            {
                document.forms[0].target = '_self';
                document.forms[0].action = 'montaListaUF.do';
                mostrar_div();
                document.forms[0].submit();
            }
            else
            {
                while(document.forms[0].elements["idMunicipio"].options.length > 0)
                {
                      document.forms[0].elements["idMunicipio"].options[0]     = null;  
                }    

                document.forms[0].idMunicipio.options[0] = new Option("Escolha uma opção...", "");
            
            }
        }

        function abreEditar(idFeriado) {
            dvAlteracao = document.getElementById('dvAlterar');
            dvAlteracao.style.display = '';
            document.forms[0].idMunicipio.style.display = 'none';
            //ifrAlterar.location.href = 'carregaEditar.do?acao=alterar&idFeriado=' + idFeriado;
            document.forms[0].target = 'ifrAlterar';
            document.forms[0].action= 'carregaEditar.do?acao=alterar&idFeriado=' + idFeriado;
            document.forms[0].submit();
            mostrar_div();
        }
        
        function ExcluirFeriado(id){
            if (window.confirm('Confirma remoção do feriado?')) {
                document.forms[0].action = 'removerFeriado.do?idFeriado=' + id;
                mostrar_div();
                document.forms[0].submit(); 
            }
        }
        
        function executaEditarParametros(idFeriado,idTipoFeriado){
            document.forms[0].target = '_self';
            if(idTipoFeriado==3){
                document.forms[0].idFeriadoParam.value = idFeriado;
                document.forms[0].action = 'carregarParametrosMunicipal.do';
            }else{
                document.forms[0].idFeriadoParam.value = idFeriado;
                document.forms[0].action = 'carregarParametrosUF.do';
            }
            mostrar_div();
            document.forms[0].submit();
            
        }
        
        
        function mostraJanelaDetalhe(){
            var x = document.getElementById("detalhe");
            x.style.visibility = 'visible';
            var b = document.getElementById("bg");
            b.style.visibility = 'visible';
        }
        
        
        function abreJanela(){
            mostraJanelaDetalhe()
            divFrame = document.getElementById('frameDetalhe');
            divFrame.src = 'pesquisarFeriado.jsp';
        }

        function fechaJanela(){
           // var x = document.getElementById("detalhe");
           // x.style.visibility = 'hidden';
           // divFrame = document.getElementById('frameDetalhe');
           // divFrame.src = 'nada.html';
           // var b = document.getElementById("bg");
           // b.style.visibility = 'hidden';
            document.forms[0].idMunicipio.style.display = 'block';
        }
    
        
        function abreIncluir() {
            dvInclui = document.getElementById('dvIncluir');
            document.forms[0].idMunicipio.style.display = 'none';
            dvInclui.style.display = '';
            ifrIncluir.location.href = 'carregaInseri.do?acao=incluir';
            mostrar_div();
            
        }
        
        function limpar() {
        
            document.forms[0].dtFeriado.value = '';
            document.forms[0].dsFeriado.value = '';
            document.forms[0].admTipoFeriado.value = '000';
            document.forms[0].inRelatorio.value = '000';
            document.forms[0].idUF.value = '000';
            document.forms[0].idMunicipio.value = '000';
            document.forms[0].indMovel.value = '000';
            document.forms[0].ano.selectedIndex = 0;
            
            while(document.forms[0].elements["idMunicipio"].options.length > 0)
            {
                  document.forms[0].elements["idMunicipio"].options[0]     = null;  
            }    

            document.forms[0].idMunicipio.options[0] = new Option("Escolha uma opção...", "");

            document.forms[0].target = '';
            document.forms[0].action = 'limpaForm.do';
            document.forms[0].submit();
            
        }
       

        </SCRIPT>
        <script language="javascript" for="window" event="onload">
        <!--   
            if('<bean:write name="FormCalendario" property="msgError" />' != "")
            {
                alert('<bean:write name="FormCalendario" property="msgError" />');
            }
    
            oculta_div();
            admTipoFeriadoChange();
            document.forms[0].ano.value = '<bean:write name="FormCalendario" property="ano" />';
        -->
        </script> 
                

        <!-- Menu de Administração de Sistemas -->
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
            <vivo:sessao id="qdMain" height="470" width="790" label="Calendário - Manutenção de Feriados" operacoes="Pesquisar" scroll="no">

            <form action="pesquisarFeriado.do" id="pesquisarFeriado" name="pesquisarFeriado" method="POST">
                <html:hidden name="FormCalendario"  property="idDsFeriadoParam"/>
                <html:hidden name="FormCalendario"  property="dsFeriadoParam"/>
                <html:hidden name="FormCalendario"  property="idTipoFeriadoParam"/>
                <html:hidden name="FormCalendario"  property="inRelatorioParam"/>
                <html:hidden name="FormCalendario"  property="indMovelParam"/>
                <html:hidden name="FormCalendario"  property="dsTipoFeriadoParam"/>
                <html:hidden name="FormCalendario"  property="idFeriadoParam"/>
                <html:hidden name="FormCalendario"  property="dtFeriadoParam"/>
                                                                                                                                
    
                <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
                        
                <table align="center" width="100%" border="0" cellpadding="1" cellspacing="1" class="tbl_bgGray">
                    <tr>
                        <td colspan="6"><div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="3"></div></td>                            
                    </tr>
                    <tr>
                        <td width="95" style="padding-left:5px;">
                            <netui:label value="Data:" styleClass="tblEstatica_campoNome"/>
                        </td>
                        <td align="left" valign="middle" style="padding-left:2px;">
                            <input type="text" id="dtFeriado" name="dtFeriado" onkeypress="checaData(this);"  value="<bean:write name="FormCalendario" property="dtFeriado" />"  style='width:65px;' maxlength="10"/><img id="imgCalendDtAbFim" src="<%=request.getContextPath()%>/resources/images/calendario.gif" style="cursor:hand;" title="Calendário" onclick="return showCalendar('dtFeriado', '%d/%m/%Y');">
                        </td>
                        <td colspan="2" align="right">
                            <netui:label value="Ano: " styleClass="tblEstatica_campoNome"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        </td>
                        <td >
                            <html:select name="FormCalendario" property="ano" style="width:100px" styleClass="SELECT" onchange="admTipoFeriadoChange();">
                                <html:option value="">Escolha...</html:option>
                                <logic:iterate name="anoArray" id="aAno">
                                    <option value="<bean:write name="aAno" property="descricao" />"><bean:write name="aAno" property="descricao" /></option>
                                </logic:iterate>
                            </html:select>
                        </td>
                    </tr>    
                    <tr>
                        <td style="padding-left:5px;">
                            <netui:label value="Descrição: " styleClass="tblEstatica_campoNome"/>
                        </td>
                        <td valign="top" colspan="2"> 
                            <html:text name="FormCalendario" property="dsFeriado" size="40" style='text-indent:4px;width:220px' styleClass="input" maxlength="200"/>
                        </td>
                        <td style="padding-left:26px;" width="100">
                            <netui:label value="Feriado Móvel: " styleClass="tblEstatica_campoNome"/>
                        </td>
                        <td width="250">
                            <html:select name="FormCalendario" property="indMovel" style="width:100px" styleClass="SELECT">
                                <html:option value="000">Escolha...</html:option>
                                <html:option value="1">Sim</html:option>
                                <html:option value="0">Não</html:option>
                            </html:select>  
                        </td>
                    </tr>
                    <tr>
                        <td style="padding-left:5px;">
                            <netui:label value="Tipo do Feriado: " styleClass="tblEstatica_campoNome"/>
                        </td>
                        <td colspan="2" style="padding-left:4px;" class="tblEstatica_campoValor" align="left">
                            <html:select name="FormCalendario" property="admTipoFeriado" style="width:220px" styleClass="SELECT" onchange="admTipoFeriadoChange();">
                                <html:option value="">Escolha uma opção...</html:option>
                                <html:options collection="AdmTipoFeriadoVO" property="idTipoFeriado" labelProperty="dsTipoFeriado" /> 
                            </html:select>
                        </td>
                        <td style="padding-left:26px;">
                            <netui:label value="Relatório DRC:" styleClass="tblEstatica_campoNome"/>
                        </td>   
                        <td>
                            <html:select name="FormCalendario" property="inRelatorio" style="width:100px" styleClass="SELECT">
                                <html:option value="000">Escolha...</html:option>
                                <html:option value="1">Sim</html:option>
                                <html:option value="0">Não</html:option>
                            </html:select> 
                        </td>
                    </tr>
                    <tr>
                        <td id="tdIdUF0" style="visibility:hidden;padding-left:5px;">
                            <netui:label value="UFs: " styleClass="tblEstatica_campoNome"/>
                        </td>
                        <td  style="visibility:hidden;" id="tdIdUF1" colspan="2" class="tblEstatica_campoValor" align="left">
                            <html:select name="FormCalendario" property="idUF" style="padding-left:4px;width:220px" styleClass="SELECT" onchange="Javascript:montaListaUF();">
                                <html:option value="000">Escolha uma opção...</html:option>
                                <html:options collection="ListaUFs" property="idUF" labelProperty="nmUF" /> 
                            </html:select> 
                            
                        </td>
                        <logic:notEqual name="FormCalendario" property="indicativoUF" value="0">
                            <td id="tdIdMunicipio0" align="right" style="visibility:hidden;padding-right:35px;">
                                <netui:label value="Municípios: " styleClass="tblEstatica_campoNome"/>
                            </td>    
                            <td style="visibility:hidden;" id="tdIdMunicipio1">
                                <html:select name="FormCalendario" property="idMunicipio" style="width:240px" styleClass="SELECT">
                                    <html:option value="000">Escolha uma opção...</html:option>
                                    <html:options collection="ListaMunicipiosVO" property="idMunicipio" labelProperty="nmMunicipio" /> 
                                </html:select>                                                      
                            </td>
                        </logic:notEqual>
                        
                    </tr>
                    <tr>
                        <td align="right" colspan="6">
						<acesso:controlHiddenItem nomeIdentificador="adm_pefe_inserir">
                            <img vspace="5" onclick="abreIncluir();"  id="btIncluir" src="/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_incluir_over.gif'" style="border:none;cursor:hand"/>
						</acesso:controlHiddenItem>
						<acesso:controlHiddenItem nomeIdentificador="adm_pefe_pesq">
                            <img hspace="5" onclick=" if (!validaData(document.forms[0].dtFeriado.value) && document.forms[0].dtFeriado.value != '') { alert('Data deve estar no formato DD/MM/AAAA.');document.forms[0].dtFeriado.focus();return false;} else {pesquisa();}"  vspace="5" id="btPesquisar" src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif'" style="border:none;cursor:hand"/>
						</acesso:controlHiddenItem>
                            <img hspace="5" onclick="limpar();" vspace="5" id="btLimpar" src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_limpar_over.gif'" style="border:none;cursor:hand"/>
                            
                </td>
                    </tr>
                </table>
                

                
                <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="10"></div>
                
                <logic:lessEqual name="FormCalendario" property="contadorAdmFeriadoLenght" value="0">
                    <logic:equal name="FormCalendario" property="contadorAdmFeriadoLenght" value="0">
                            <table align="center" width="100%" border="0" cellpadding="0" cellspacing="0">
                                <tr>
                                    <td align="center">
                                        <table width="98%" border="0" cellspacing="1" cellpadding="1" align="center">
                                            <tr>
                                                <td width="90%" align="center" class="tblEstatica_campoNome">
                                                    Não foi encontrado nenhum feriado com a descrição fornecida.
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                            </table>
                    </logic:equal>
                </logic:lessEqual>

                    <logic:greaterThan name="FormCalendario" property="contadorAdmFeriadoLenght" value="0">
                        <table align="center" width="100%" border="0" cellpadding="0" cellspacing="0">
                            <tr>
                                <td>
                                <vivo:tbTable selectable="true" layoutWidth="764" layoutHeight="240" tableWidth="764" styleId="tableTitle" sortable="true">
                                    <vivo:tbHeader>
                                        <vivo:tbHeaderColumn align="center" width="10%"  type="string">Data</vivo:tbHeaderColumn>
                                        <vivo:tbHeaderColumn align="left" width="42%" type="string">Descri&ccedil;&atilde;o</vivo:tbHeaderColumn>
                                        <vivo:tbHeaderColumn align="left" width="21%"  type="string">Tipo</vivo:tbHeaderColumn>                    
                                        <vivo:tbHeaderColumn align="center" width="6%"  type="string">DRC</vivo:tbHeaderColumn>
                                        <vivo:tbHeaderColumn align="center" width="6%"  type="string">Móvel</vivo:tbHeaderColumn>
                                        <vivo:tbHeaderColumn align="center" width="5%"  type="string"></vivo:tbHeaderColumn>
                                        <vivo:tbHeaderColumn align="center" width="5%"  type="string"></vivo:tbHeaderColumn>
                                        <vivo:tbHeaderColumn align="center" width="5%"  type="string"></vivo:tbHeaderColumn>
                                    </vivo:tbHeader>
                                    
                                    
                            <vivo:tbRows scroll="yes">
                                <%
                                ArrayList listFeriado = FormCalendario.getListFeriado();
                                
                                if(listFeriado!=null){
                                    if(listFeriado.size()>0){
                                       
                                        for(int i=0;i<listFeriado.size();i++){
                                            
                                                AdmCalendarioContainerVO contaner = (AdmCalendarioContainerVO)listFeriado.get(i);
                                                AdmFeriadoVO[] array = contaner.getAdmFeriadoVOArray();
                                                
                                                for(int j=0;j<array.length;j++){ 
                                                    if(array[j]!=null){
                                        %>  
                                                     <vivo:tbRow key="linha1">
                                                        <vivo:tbRowColumn>
                                                                <%=array[j].getDtFeriado()%>
                                                        </vivo:tbRowColumn>

                                                        <vivo:tbRowColumn>                                 
                                                                <%=array[j].getDsFeriado()%>
                                                        </vivo:tbRowColumn>
                                                        <vivo:tbRowColumn>                                 
                                                                <%=array[j].getDsTipoFeriado()%>
                                                        </vivo:tbRowColumn>
                                                        <vivo:tbRowColumn>                                                                                         
                                                           <%if(array[j].getInRelatorio().equals(ConstantesCRM.SZERO)){%>
                                                                Não
                                                           <%}else{%>
                                                                Sim
                                                           <%}%>
                                                        </vivo:tbRowColumn>                                 
                                                        <vivo:tbRowColumn>
                                                           <%if(array[j].getIndMovel().equals(ConstantesCRM.SZERO)){%>
                                                                Não
                                                           <%}else{%>
                                                                Sim
                                                           <%}%>
                                                        </vivo:tbRowColumn>
                                                        <vivo:tbRowColumn>
                                                            <acesso:controlHiddenItem nomeIdentificador="adm_pefe_altferi">
                                                                <a href="Javascript:abreEditar('<%=array[j].getIdFeriado()%>');"><img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" border="0" alt="Editar"></a>
                                                            </acesso:controlHiddenItem>
                                                        </vivo:tbRowColumn>
                                                        <vivo:tbRowColumn>
                                                            <acesso:controlHiddenItem nomeIdentificador="adm_pefe_excluir">
                                                                <a href="Javascript:ExcluirFeriado('<%=array[j].getIdFeriado()%>');"><img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" border="0" alt="Excluir"></a>
                                                            </acesso:controlHiddenItem>
                                                        </vivo:tbRowColumn>
                                                        <vivo:tbRowColumn>
                                                            <acesso:controlHiddenItem nomeIdentificador="adm_pefe_altpara">
                                                                <%if(!array[j].getIdTipoFeriado().equals(ConstantesCRM.SONE)){%>
                                                                    <a href="Javascript:executaEditarParametros('<%=array[j].getIdFeriado()%>','<%=array[j].getIdTipoFeriado()%>');"><img src="/FrontOfficeWeb/resources/images/bt_icon_propried.gif" border="0" alt="Editar parâmetros"></a> 
                                                                <%}%>                                                                
                                                            </acesso:controlHiddenItem>
                                                            <%if(array[j].getIdTipoFeriado().equals(ConstantesCRM.SONE)){%>
                                                                <img src="/FrontOfficeWeb/resources/images/bt_icon_propried_inverso.gif" style="cursor:default"  border="0">                                                        
                                                            <%}%>
                                                        </vivo:tbRowColumn>
                                                    </vivo:tbRow>
                                        <%              
                                                    }
                                                }
                                            
                                        }
                                         
                                    }//else{
                                    %>    
                                          <!--option value="-1"></option--> 
                                    <%      
                                    //}
                                }
                                %>    
                            </vivo:tbRows>
                            

                                </vivo:tbTable>
                                <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="3"></div>
                                <table width="100%" class="tbl_bgGray">
                                    <tr>
                                        <td width="80" style="padding-left:5px;"><b>Legendas:</b></td>
                                        <td width="150"><img align="middle" src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif">&nbsp;Editar Feriado</td>
                                        <td width="150"><img align="middle" src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif">&nbsp;Excluir Feriado</td>
                                        <td width="400"><img align="middle" src="/FrontOfficeWeb/resources/images/bt_icon_propried.gif">&nbsp;Editar Parâmetros</td>
                                    </tr>
                                </table>
                                </td>
                            </tr>    
                        </table>
                    </logic:greaterThan>
                    
                    
                    
                <img vspace="5" onClick="location.href='/FrontOfficeWeb/index.jsp'"  id="btIncluir" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_voltar_over.gif'" style="border:none;cursor:hand"/>
            </form>    
            </vivo:sessao> 
        <vivo:quadroFlutuante onclick="fechaJanela();" idIframe="ifrIncluir" id="dvIncluir" spacesLeft="100" height="230" spacesTop="100" url="<%=request.getContextPath()%>" display="none" width="600" label="Inclusão de Feriado"/>
        <vivo:quadroFlutuante onclick="fechaJanela();" idIframe="ifrAlterar" id="dvAlterar" spacesLeft="100" height="230" spacesTop="100" url="<%=request.getContextPath()%>" display="none" width="600" label="Alteração de Feriado"/>
        </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>
