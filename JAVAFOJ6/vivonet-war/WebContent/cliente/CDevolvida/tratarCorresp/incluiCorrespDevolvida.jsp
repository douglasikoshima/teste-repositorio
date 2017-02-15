<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<bean:define id="Form"         name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="incluirForm"/>
<bean:define id="ListaCorresp" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="incluirForm.incluirCorresp.listaCorrespClienteArray"/>
<bean:define id="Motivos"      name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="incluirForm.incluirCorresp.motivoDevolucaoVOArray"/>
<bean:define id="Tipos"        name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="incluirForm.incluirCorresp.tipoCorrespondenciaVOArray"/>
<bean:define id="Ufs"          name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="incluirForm.incluirCorresp.UFVOArray"/>
<bean:define id="Paises"       name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="incluirForm.incluirCorresp.paisVOArray"/>

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute value="Correspondencia Devolvida" name="title"></netui-template:setAttribute>
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
            
            function validaPesquisa( botao ){                                                  
                if (botao=="pesquisa"){
                    if (document.getElementById("nrLinhaConsulta").value == "" && document.getElementById("nrContaConsulta").value == "" && document.getElementById("nrCodigoConsulta").value == ""){                
                        alert('Favor escolher um único valor para pesquisa!');
    
                    }else if (document.getElementById("nrLinhaConsulta").value == "Campo Inválido") {
                        alert('Linha invalida, favor corrigir!');
    
                    }else{
                        document.getElementById("nrLinhaConsulta").value = limpaInteiro(document.getElementById("nrLinhaConsulta").value);
                        document.forms[0].action="pesquisaIncluir.do?destino="+botao;                    
                        mostrar_div();
                        document.forms[0].submit();
                    }
                }
                else if (botao=="novo"){
                    if(document.forms[0].idTipoSelecionado.value == ""){
                        alert('Favor selecionar o campo Tipo Correspondência!');
                    }else if(document.forms[0].idMotivoSelecionado.value == ""){
                        alert('Favor preencher o campo Motivo Devolução!');
                    }else if(document.forms[0].elements['correspCliente.dtDevolucao'].value == ""){
                        alert('Favor preencher o campo Data de Devolução!');
                    }else if(!validaDataFinal(document.forms[0].elements['correspCliente.dtDevolucao'].value,hoje)){
                        alert('Data de Devolução maior que o dia de hoje, favor corrigir!');
                    }else if(trim(document.forms[0].elements['correspCliente.nmCliente'].value) == ""){
                        alert('Favor preencher o campo Nome!');
                    }else if(trim(document.forms[0].elements['correspCliente.nrDocumento'].value) == ""){
                        alert('Favor preencher o campo CPF/CNPJ!');
                    }else if(trim(document.forms[0].elements['correspCliente.enderecoBaseVO.dsEndereco'].value) == ""){
                        alert('Favor preencher o campo Endereço!');
                    }else if(trim(document.forms[0].elements['correspCliente.enderecoBaseVO.nrEndereco'].value) == ""){
                        alert('Favor preencher o campo Número!');
                    }else if(trim(document.forms[0].elements['correspCliente.enderecoBaseVO.dsBairro'].value) == ""){
                        alert('Favor preencher o campo Bairro!');
                    }else if(trim(document.forms[0].elements['correspCliente.enderecoBaseVO.dsCidade'].value) == ""){
                        alert('Favor preencher o campo Cidade!');
                    }else if(trim(document.forms[0].elements['correspCliente.enderecoBaseVO.nrCEP'].value) == "" || trim(document.forms[0].elements['correspCliente.enderecoBaseVO.nrCEP'].value).length<9){
                        alert('Favor preencher corretamente o campo CEP!');
                    }else if(document.forms[0].idUFSelecionado.value == ""){                
                        alert('Favor selecionar o campo Estado!');
                    }else if(document.forms[0].idPaisSelecionado.value == ""){                
                        alert('Favor selecionar o campo País!');
                    }else{                                        
                        document.forms[0].action="pesquisaIncluir.do?destino="+botao;
                        mostrar_div();                   
                        document.forms[0].target='frameApp';
                        document.forms[0].submit();
                    }
                }            
            }
    
            function utilizarEndereco( indice ){
                document.forms[0].action="pesquisaIncluir.do?destino=selecionado&indice="+indice;
                mostrar_div();
                document.forms[0].submit();
            }
            
            function pesquisaEndereco(){            
                document.forms[0].action="pesquisaEndereco.do";
                dvPesqEndereco.style.display = '';
                document.forms[0].target="ifrmPesqEndereco";
                mostrar_div();
                document.forms[0].submit();
            }
            
            function limpar(){
                document.forms[0].action="incluirCorrespDevolvida.do";
                mostrar_div();
                document.forms[0].submit();
            }
            
           function controlPesquisa(obj){
           if(obj.value.length>0)
             if(obj.name == 'buscaPor.nrLinha'){
               document.forms[0].nrContaConsulta.disabled  = true;
               document.forms[0].nrCodigoConsulta.disabled = true;
             }
             else if (obj.name == 'buscaPor.nrConta'){
               document.forms[0].nrLinhaConsulta.disabled  = true;
               document.forms[0].nrCodigoConsulta.disabled = true;
             }
             else {
              document.forms[0].nrLinhaConsulta.disabled = true;
              document.forms[0].nrContaConsulta.disabled = true;
             }
           else{
               document.forms[0].nrLinhaConsulta.disabled  = false;
               document.forms[0].nrContaConsulta.disabled  = false;
               document.forms[0].nrCodigoConsulta.disabled = false;
           }  
         }
        </script>
    </netui-template:section>
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="cli_icd_verpagina">
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
        <script>
            mostrar_div();
        </script>
        <script for="window" event="onload">
            if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation(); 
        </script>
        <div><img width="1" height="5" src="/FrontOfficeWeb/resources/images/transp.gif"></div>
        <vivo:sessao id="correspondenciadevolvida" height="470" width="790" label="Correspond&ecirc;ncia Devolvida" operacoes="Cadastro" scroll="no">
        <html:form action="/cliente/CDevolvida/tratarCorresp/pesquisaIncluir.do" method="POST">                    
            <table class="tbl_bgGray" width="777">
                <tr>
                    <td width="131">&nbsp;<b>Linha:</b></td>
                    <td width="127">&nbsp;<b>Conta:</b></td>
                    <td width="131">&nbsp;<b>Código:</b></td>
                    <td></td>
                </tr>
                <tr>
                    <td><html:text name="Form" property="buscaPor.nrLinha" onkeyup="controlPesquisa(this)" onblur="formatPhoneNumberObj(this)" maxlength="11" styleId="nrLinhaConsulta"/></td>
                    
                    <td><html:text name="Form" property="buscaPor.nrConta" onkeyup="checaInteiro(this);controlPesquisa(this)" maxlength="10" styleId="nrContaConsulta"/></td>
                    <td><html:text name="Form" property="buscaPor.idPessoa" onkeyup="checaInteiro(this);controlPesquisa(this)" maxlength="25" styleId="nrCodigoConsulta"/></td>
                    <td><acesso:controlHiddenItem nomeIdentificador="cli_icd_pesq"><img onclick="validaPesquisa('pesquisa')" align="middle" style="border:none; cursor:hand;" src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" onmouseover="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif';" onmouseout="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif';"/></acesso:controlHiddenItem><img onclick="limpar()" style="border:0px;cursor:hand;" vspace="5" hspace="6" src="/FrontOfficeWeb/resources/images/bt_limparcampos_nrml.gif" onmouseout="this.src='/FrontOfficeWeb/resources/images/bt_limparcampos_nrml.gif'" onmouseover="this.src='/FrontOfficeWeb/resources/images/bt_limparcampos_over.gif'" align="middle"/></td>
                </tr>
            </table>
            <script>            
                var msg = '<bean:write name="Form" property="tipoBusca"/>'
                if (msg=='conta') {
                    alert("Conta não encontrada!");
                }
                if (msg=='linha') {
                     alert("Linha não encontrada!");
                }
                if (msg=='pessoa'){
                     alert("Pessoa não encontrada!");
                }            
            </script>
            <table width="770" border="0" cellpadding="0" cellspacing="0">
                <tr style="background-color:#ffffff;border:1px solid #000000;">
                </tr>                        
                <tr>
                    <td  colspan="4" nowrap>                    
                        <table width="100%" border="0" cellpadding="1" cellspacing="0">
                            <tr>
                                <td>            
                                    <vivo:tbTable selectable="true" layoutWidth="757" layoutHeight="100" tableWidth="757" styleId="tableTitle" sortable="true">
                                        <vivo:tbHeader>
                                            <vivo:tbHeaderColumn align="left" width="5%" type="string">&nbsp</vivo:tbHeaderColumn>
                                            <vivo:tbHeaderColumn align="left" width="35%" type="string">Nome</vivo:tbHeaderColumn>
                                            <vivo:tbHeaderColumn align="left" width="20%" type="string">Tipo de Endereço</vivo:tbHeaderColumn>
                                            <vivo:tbHeaderColumn align="left" width="40%" type="string">Endereço</vivo:tbHeaderColumn>
                                        </vivo:tbHeader>
                                        <vivo:tbRows>                                        
                                            <logic:iterate id="listaCorrespCliente" name="ListaCorresp" indexId="idx">                                                
                                                 <vivo:tbRow key="linha1">
                                                    <vivo:tbRowColumn><acesso:controlHiddenItem nomeIdentificador="cli_icd_pesq"><input type="radio" class="radio" name="radio" onclick="utilizarEndereco(<%=idx%>)" <%if(idx.toString().equals((request.getParameter("indice")))){%>checked<%}%> style="cursor:hand;"/></acesso:controlHiddenItem></vivo:tbRowColumn>
                                                    <vivo:tbRowColumn><bean:write name="listaCorrespCliente" property="nmCliente"/></vivo:tbRowColumn>
                                                    <vivo:tbRowColumn><bean:write name="listaCorrespCliente" property="dsTipoEndereco"/></vivo:tbRowColumn>                                                  
                                                    <vivo:tbRowColumn><bean:write name="listaCorrespCliente" property="enderecoBaseVO.dsEndereco"/> <bean:write name="listaCorrespCliente" property="enderecoBaseVO.nrEndereco"/> <bean:write name="listaCorrespCliente" property="enderecoBaseVO.dsComplemento"/></vivo:tbRowColumn>                                                                                                     
                                                 </vivo:tbRow>                                                                                          
                                            </logic:iterate>                                           
                                        </vivo:tbRows>                                
                                    </vivo:tbTable>
                                </td>
                            </tr>     
                        </table>
                        <html:hidden name="Form" property="correspCliente.idPessoa"/>
                        <html:hidden name="Form" property="correspCliente.idLinha"/>
                        <html:hidden name="Form" property="correspCliente.idConta"/>
                        <html:hidden name="Form" property="correspCliente.dsTipoEndereco"/>
                        <html:hidden name="Form" property="correspCliente.idPessoaUsuario"/>                            
                    </td>
                </tr>
                <tr><td height="10"></td></tr> 
                <tr>
                    <td  colspan="5">
                        <table id="tabela" style="visibility:hidden">
                            <tr>
                                <td nowrap>Tipo Correspond&ecirc;ncia:</td>
                                <td style="padding-left:3px;"> 
                                    <html:select style="width:272px;" name="Form" property="idTipoSelecionado">
                                        <html:option value="">-- Selecione --</html:option>
                                        <html:options collection="Tipos" property="id" labelProperty="descricao"/>
                                    </html:select>
                                </td>
                                <td>&nbsp;</td>
                                <td nowrap>Motivo Devolu&ccedil;&atilde;o:</td>
                                <td style="padding-left:3px;"> 
                                    <html:select style="width:250px" name="Form" property="idMotivoSelecionado">
                                        <html:option value="">-- Selecione --</html:option>
                                        <html:options collection="Motivos" property="id" labelProperty="descricao"/>
                                    </html:select> 
                                </td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td colspan="2"></td>                                
                                <td nowrap>Data Devolu&ccedil;&atilde;o:</td>
                                <td>
                                    <html:text name="Form" property="correspCliente.dtDevolucao" styleId="dtDevolucao" readonly="true"/>
                                    <img src="<%=request.getContextPath()%>/resources/images/calendario.gif" style="cursor:hand;" title="Calendário" onclick="return showCalendar('dtDevolucao', '%d/%m/%Y');">
                                </td>
                            </tr>
                                
                            <tr>
                                <td >Nome:</td>
                                <td><html:text name="Form" property="correspCliente.nmCliente" size="50" readonly="true" maxlength="255" styleId="nmCliente"/></td>
                                <td>&nbsp;</td>
                                <td><bean:write name="Form" property="correspCliente.dsTipoDocumento"/>:</td>
                                <td><html:text name="Form" property="correspCliente.nrDocumento" readonly="true" maxlength="18" styleId="nrDocumento"/></td>
                                <script language="javascript">
                                <logic:equal value="CPF" name="Form" property="correspCliente.dsTipoDocumento">
                                    checaCPF(document.getElementById('nrDocumento'));
                                </logic:equal>
                                <logic:equal value="CNPJ" name="Form" property="correspCliente.dsTipoDocumento">
                                    checaCNPJ(document.getElementById('nrDocumento'));
                                </logic:equal>
                                </script>
            
                            </tr>
            
                            <tr>
                                <td>Endere&ccedil;o:</td>
                                <td><b><html:text name="Form" property="correspCliente.enderecoBaseVO.dsEndereco" maxlength="255" size="50" styleId="dsEndereco"/></b></td>
                                <td>&nbsp;</td>
                                <td>N&uacute;mero:</td>
                                <td><html:text name="Form" property="correspCliente.enderecoBaseVO.nrEndereco" maxlength="10" styleId="nrEndereco"/></td>
                            </tr>
            
                            <tr>
                                <td>Complemento:</td>
                                <td><html:text name="Form" property="correspCliente.enderecoBaseVO.dsComplemento" maxlength="255" size="50" styleId="dsComplemento"/></td>
                                <td>&nbsp;</td>
                                <td>Bairro:</td>
                                <td><html:text name="Form" property="correspCliente.enderecoBaseVO.dsBairro" maxlength="255" size="45" styleId="dsBairro"/></td>
                            </tr>
                            <tr>
                                <td>Cidade:</td>
                                <td><html:text name="Form" property="correspCliente.enderecoBaseVO.dsCidade" maxlength="255" size="50" styleId="dsCidade"/></td>
                                <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                <td>CEP:</td>
                                <td><html:text name="Form" property="correspCliente.enderecoBaseVO.nrCEP" maxlength="9" onkeyup="checaCEP(this)" styleId="nrCEP"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                Estado(UF):<html:select style="width:45px" name="Form" property="idUFSelecionado">
                                                <html:options collection="Ufs" property="idUF" labelProperty="sgUF"/>
                                            </html:select></td>
                                <script language="javascript">
                                    checaCEP(document.getElementById('nrCEP'));
                                </script>
                            </tr>                         
                            <tr>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td>País:</td>
                                <td style="padding-left:3px;">
                                    <html:select style="width:125px" name="Form" property="idPaisSelecionado">
                                        <html:options collection="Paises" property="idPais" labelProperty="nmPais" />
                                    </html:select>
                                    <img onClick="pesquisaEndereco()" vspace="0" style="border:0px;cursor:hand;" hspace="10" src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif'"/>
                                </td>                                
                            </tr>
                        </table>                            
                     
                        <div><img width="1" height="10" src="/FrontOfficeWeb/resources/images/transp.gif"></div>
                        
                        
                        <table border="0" height="30" width="100%">                
                            <tr>
                                <td valign="bottom">
                                    <img onClick="window.location.href='/FrontOfficeWeb/index.jsp';" vspace="0" style="border:0px;cursor:hand;" hspace="6" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_voltar_over.gif'"/>
                                </td>
                                <td valign="bottom" id="botoes" style="visibility:hidden" colspan="4" align="right">                            
                                    <img onclick="limpar()" style="border:0px;cursor:hand;" vspace="5" hspace="6" src="/FrontOfficeWeb/resources/images/bt_limparcampos_nrml.gif" onmouseout="this.src='/FrontOfficeWeb/resources/images/bt_limparcampos_nrml.gif'" onmouseover="this.src='/FrontOfficeWeb/resources/images/bt_limparcampos_over.gif'"/>
                                    <acesso:controlHiddenItem nomeIdentificador="cli_icd_salvar"><img onclick="validaPesquisa('novo')" style="border:0px;cursor:hand;" vspace="5" hspace="6" src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif" onmouseout="this.src='/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif'" onmouseover="this.src='/FrontOfficeWeb/resources/images/bt_salvar_over.gif'"/></acesso:controlHiddenItem>
                                </td>                           
                            </tr>
                        </table>                            
                    </td>
                </tr>                           
            </table>

            <script language="javascript">
                if(document.forms[0].radio && document.forms[0].radio.length){
                    for(i=0;i<document.forms[0].radio.length;i++){
                        if(document.forms[0].radio[i].checked){
                            document.getElementById('tabela').style.visibility = "visible";    
                            document.getElementById('botoes').style.visibility = "visible";    
                        }
                    }
                }else if(document.forms[0].radio && document.forms[0].radio.checked){
                    document.getElementById('tabela').style.visibility = "visible";    
                    document.getElementById('botoes').style.visibility = "visible";    
                }
                if("<%=request.getParameter("selecionado")%>" == "TRUE"){
                    document.getElementById('tabela').style.visibility = "visible";    
                    document.getElementById('botoes').style.visibility = "visible";    
                }
            </script>                    
        <vivo:quadroFlutuante id="dvPesqEndereco" idIframe="ifrmPesqEndereco" width="780" height="550" spacesTop="0" spacesLeft="0" display="none" url="<%=request.getContextPath()%>" label="Pesquisa de Endereço"/>                            
        </html:form>    
    </vivo:sessao>    
    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>