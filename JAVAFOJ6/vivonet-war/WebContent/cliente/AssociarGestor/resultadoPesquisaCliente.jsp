<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>

<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="grupoCRIForm"/>
<bean:define id="Grupos" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="grupoCRIForm.grupoCRI.gruposArray"/>    

<html>
	<acesso:controlHiddenItem nomeIdentificador="cli_ag_rpc_verpagina">
    <head>
        <title></title>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
        <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">
        <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/frameweb.js" ></script>
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>        
        <script>
        
            function controlChecks(){
                if(document.getElementById('todosClientes').checked==true && document.getElementById('clientesSelecionados')){
                    if(document.forms[0].clientesSelecionados.length){
                        for (var i=0; i < document.forms[0].clientesSelecionados.length; i++){
                            document.forms[0].clientesSelecionados[i].checked = true;
                        }
                    }else{
                        document.getElementById('clientesSelecionados').checked = true;
                    }
                }else if(document.getElementById('todosClientes').checked==false && document.getElementById('clientesSelecionados')){
                    if(document.forms[0].clientesSelecionados.length){
                        for (var i=0; i < document.forms[0].clientesSelecionados.length; i++){
                            document.forms[0].clientesSelecionados[i].checked = false;
                        }
                    }else{
                        document.getElementById('clientesSelecionados').checked = false;
                    }
                }else{
                    document.getElementById('todosClientes').checked=false;
                    alert('Não existem linhas a serem selecionadas!');
                }
            }
            
            function salvarCRI(){
                if(validaGravar()==true){
                    parent.mostrar_div();
                    document.forms[0].submit();
                }else{
                    alert(validaGravar());
                }
            }
            
            function validaGravar(){
                if(document.getElementById('idGrupoSelecionado').value == '0'){
                    return('Selecione um GRUPO CRI!');
                }else{
                    if(document.getElementById('clientesSelecionados')){
                        if(document.forms[0].clientesSelecionados.length){
                            checado = null;
                            for (var i=0; i < document.forms[0].clientesSelecionados.length; i++){
                                if (document.forms[0].clientesSelecionados[i].checked == true){
                                    checado = 'OK';
                                }
                            }
                            if (checado == null){
                                return('Nenhuma linha foi selecionada!');
                            }else{
                                return true;
                            }
                        }else{
                            if(document.getElementById('clientesSelecionados').checked == true){
                                return true;
                            }else{
                                return('A linha não foi selecionada!');
                            }
                        }
                    }else{
                        return('Não existem linhas a serem selecionadas!');
                    }
                }
            }

        </script>
        
        <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
            <!--
                document.body.style.backgroundColor = '#ededed';
                <logic:equal name="Form" property="grupoCRI.pesquisa.inErro" value="0">
                document.getElementById('idGrupoSelecionado').value = 0;
                </logic:equal>
                parent.oculta_div();
                parent.focoValorPesquisa();
            -->
        </SCRIPT>
        
    </head>
    
    <html:form action="salvarCRI">
        <body>
            <table id="tbbusca" width="770" height="260" cellpadding="0" cellspacing="0" >
                <tr>
                    <td valign="top">
                        <logic:equal name="Form" property="grupoCRI.pesquisa.inErro" value="1">
                            <center><b>Não foi encontrada nenhuma linha com o número fornecido.</b></center>
                        </logic:equal>
                        <logic:equal name="Form" property="grupoCRI.pesquisa.inErro" value="0">
                        <input tabindex="6" type="checkbox" class="radio" name="todosClientes" value="" id="todosClientes" style="text-indent:11px;" onclick="controlChecks();" ><span><b>Todas as linhas</b></span>
                        <vivo:tbTable  selectable="true" onRowClick=""  layoutWidth="750" layoutHeight="280" tableWidth="750"  styleId="tableTitle" sortable="true">
                            <vivo:tbHeader>
                                <vivo:tbHeaderColumn align="left"   width="5%"  type="string"></vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="Center" width="20%" type="string">Linha</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="Center" width="25%" type="string">Cliente</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="Center" width="25%" type="string">Usuário</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="Center" width="25%" type="string">Grupo CRI</vivo:tbHeaderColumn>
                            </vivo:tbHeader>
                            <vivo:tbRows>
                                <logic:iterate id="listaLinhas" name="Form" property="grupoCRI.linhasArray">
                                    <vivo:tbRow key="Linha">
                                        <vivo:tbRowColumn><input tabindex="7" type="checkbox" class="radio" name="clientesSelecionados" value="<bean:write name="listaLinhas" property="idLinha"/>" id="clientesSelecionados"></vivo:tbRowColumn>
                                        <vivo:tbRowColumn><bean:write name="listaLinhas" property="nrLinha"/></vivo:tbRowColumn>
                                        <vivo:tbRowColumn><bean:write name="listaLinhas" property="nmCliente"/></vivo:tbRowColumn>
                                        <vivo:tbRowColumn><bean:write name="listaLinhas" property="nmUsuario"/></vivo:tbRowColumn>
                                        <vivo:tbRowColumn><bean:write name="listaLinhas" property="dsGrupoAssociado"/></vivo:tbRowColumn>
                                    </vivo:tbRow>
                                </logic:iterate>
                            </vivo:tbRows>
                        </vivo:tbTable>
                        
                        </logic:equal>
                    </td>
                </tr>
            </table>
            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
            <logic:equal name="Form" property="grupoCRI.pesquisa.inErro" value="0">
            <table id="tbbotoes" width="770" height="29" cellpadding="0" cellspacing="0" class="tbl_bgGray">
                <tr>
                    <td width="12%" class="bfixa_texto_black" style="text-indent:6px;"><b>Grupo CRI</b></td>
                    <td valign="middle" width="15%">
                        <html:select name="Form" property="idGrupoSelecionado" styleId="idGrupoSelecionado">
                            <html:option value="0">-- Selecione --</html:option>
                            <html:option value="">SEM GRUPO</html:option>
                            <html:options collection="Grupos" property="idGrupo" labelProperty="dsGrupo"/>
                        </html:select>
                    </td>
                    <td>
                        &nbsp;
                    </td>
                    <td width="12%">
                        &nbsp;
                    </td>
                    <td >
                        &nbsp;
                    </td>
                    <td width="12%">
					<acesso:controlHiddenItem nomeIdentificador="cli_ag_rpc_bt_gravar">
                        <img align="center"
						     hspace="5"
							 src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif"
							 onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_gravar_over.gif';"
							 onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif';"
							 style="border:none;cursor:hand;"
							 onClick="salvarCRI(); return false;"/>
					</acesso:controlHiddenItem>
                    </td>
                </tr>
            </table>
            </logic:equal>
        </body>
    </html:form>
	</acesso:controlHiddenItem>
    <script>
    <!--
        parent.oculta_div();
    -->
    </script>
            
</html>