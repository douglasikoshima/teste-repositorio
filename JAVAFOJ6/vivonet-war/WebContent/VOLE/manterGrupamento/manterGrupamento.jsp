<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<acesso:controlInitEnv/>
<bean:define id="FormGrupamento"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="manterGrupamentoForm"/>
<bean:define id="gruposVO"    name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="manterGrupamentoForm.gruposVO"/>

<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
 <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/frameweb.js" ></script>

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Parametrização"/>
    <netui-template:setAttribute name="modulo" value="Manutenção de grupos"/>

    <netui-template:section name="headerSection">

    </netui-template:section>

    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="vole_grpa_verpagina">
    <script language="javascript">
    <!--
        function removeItem(id) {
            document.forms[0].target = '';
            document.forms[0].idGrupamento.value = id;
            if (window.confirm("Confirma remoção do item?")) {
                mostrar_div();
                document.forms[0].acao.value = "remover";
                document.forms[0].action = 'removerGrupamento.do'; 
                document.forms[0].submit();
                //return true;
            }else{
                return false;
            }
        }
        
        function lista() 
        {
            document.forms[0].target = '';
            document.forms[0].siglaGrupamento.value = trim(document.forms[0].siglaGrupamento.value);
            document.forms[0].nomeGrupamento.value = trim(document.forms[0].nomeGrupamento.value);
            document.forms[0].descricaoGrupamento.value = trim(document.forms[0].descricaoGrupamento.value);
            document.forms[0].action = "listarGrupamentos.do";
            mostrar_div();
            document.forms[0].submit();
        }
        
        function limpar() 
        {
        	document.forms[0].action = "begin.do";
            mostrar_div();
            document.forms[0].submit();
        }
        
        function alterarGrupamento(idArray) 
        {
            divAlteraPerfil.style.display = '';
            document.forms[0].target = "ifrmAlteraPerfil";
            document.forms[0].action = "alterarGrupamento.do?idArray="+idArray;
            mostrar_div();
            document.forms[0].submit();            
        }
        
        function incluiGrupamento() {
            divIncluiPerfil.style.display = '';
            document.forms[0].target = "ifrmIncluiPerfil";
            document.forms[0].action = "incluirGrupamento.do";
            mostrar_div();
            document.forms[0].submit();
        }
        
        function listaEnter(ev){
            if(ev.keyCode == 13)
                lista();
            else
                return false;    
        }
        
        
    // -->
    </script>  
        <!-- Menu de Gestao de Usuarios -->
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
        <script>
            mostrar_div();
        </script>   
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
            
            <vivo:sessao id="qdMain" height="460" width="790" label="Usuários" operacoes="Manutenção de Grupos">
            
            <form action="listarGrupamentos.do" onSubmit="return false;" method="post">
                <html:hidden name="FormGrupamento" property="idGrupamento"/>
                <html:hidden name="FormGrupamento" property="acao" value="pesquisar"/>
                            
                            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
                        
                                <table width="760" border="0" cellspacing="1" cellpadding="1" align="center" class="tbl_bgGray">
                                    <tr>
                                        <td>
                                            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
                                            <table width="740" border="0" cellspacing="0" cellpadding="0" align="center" class="tbl_bgBlue">
                                                <tr><td height="6"></td></tr>
                                                <tr>
                                                    <td width="20%">&nbsp;
                                                        <b><netui:label value="Sigla do Grupo:" styleClass="tblEstatica_campoNome"/></b>
                                                    </td>
                                                    <td width="47%" align="left">
                                                        <html:text name="FormGrupamento" property="siglaGrupamento" tabindex="1" style="width:440px" styleClass="input" maxlength="3" onkeypress="listaEnter(event);" onkeyup="checaStrEspecial(this);"/>                    
                                                    </td>
                                                    <td></td><td></td>
                                                </tr>
                                                <tr>
                                                    <td width="20%">&nbsp;
                                                        <b><netui:label value="Nome do Grupo:" styleClass="tblEstatica_campoNome"/></b>
                                                    </td>
                                                    <td width="47%" align="left">
                                                        <html:text name="FormGrupamento" property="nomeGrupamento" tabindex="2" style="width:440px" styleClass="input" maxlength="254" onkeypress="listaEnter(event);" onkeyup="checaStrEspecial(this);"/>                    
                                                    </td>    
                                                    <td></td><td></td>
                                                </tr>
                                                <tr>
                                                    <td width="20%">&nbsp;
                                                        <b><netui:label value="Descrição do Grupo:" styleClass="tblEstatica_campoNome"/></b>
                                                    </td>
                                                    <td width="47%" align="left">
                                                        <html:text name="FormGrupamento" property="descricaoGrupamento" tabindex="2" style="width:440px" styleClass="input" maxlength="254" onkeypress="listaEnter(event);" onkeyup="checaStrEspecial(this);"/>                    
                                                    </td>  
                                                    <td></td><td></td>  
                                                </tr>                                                
                                                <tr>      
                                                	<td></td><td></td>                                          
                                                    <td width="10%" align="center">
                                                        <img style="border:none;cursor:hand;" tabindex="2" id="btLimpar" onClick="limpar();" src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_limpar_over.gif'"/>
                                                    </td>
                                                    <td width="18%" align="left">&nbsp;
                                                    <acesso:controlHiddenItem nomeIdentificador="vole_grpa_pesquisar">
                                                        <img id="btPesquisar" tabindex="3" onclick="lista();" src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif'" style="cursor:hand;border:none;"/>
                                                    </acesso:controlHiddenItem>
                                                    </td>
                                                </tr>
                                                <tr><td height="6"></td></tr>
                                            </table>
                                            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
                                        </td>
                                    </tr>                                   
                                    <tr><td height="4"></td></tr>
                                    <tr>
                                        <td width="20%" class="tblEstatica_campoNome" align="center">
                                        
                                        <logic:equal name="FormGrupamento" property="indicativoOperacao" value="Resultado">
                                            <vivo:tbTable selectable="true" layoutWidth="705" layoutHeight="250" tableWidth="700" styleId="tableTitle" sortable="true">
                                                <vivo:tbHeader>
                                                    <vivo:tbHeaderColumn align="left"   width="20" type="string">Sigla</vivo:tbHeaderColumn>
                                                    <vivo:tbHeaderColumn align="center" width="320" type="string">Nome do Grupo</vivo:tbHeaderColumn>
                                                    <vivo:tbHeaderColumn align="center" width="320" type="string">Descrição do Grupo</vivo:tbHeaderColumn>
                                                    <vivo:tbHeaderColumn align="center" width="20" type="string"></vivo:tbHeaderColumn>
                                                    <vivo:tbHeaderColumn align="center" width="20" type="string"></vivo:tbHeaderColumn>
                                                </vivo:tbHeader>
                                                <vivo:tbRows>
                                                    <logic:iterate name="FormGrupamento" property="gruposVO" id="gruposVO" indexId="idx">
                                                        <vivo:tbRow key="linha1">
                                                            <vivo:tbRowColumn>
                                                                <bean:write name="gruposVO" property="siglaGrupamento"/>
                                                            </vivo:tbRowColumn>   
                                                            <vivo:tbRowColumn>
                                                                <bean:write name="gruposVO" property="nomeGrupamento"/>
                                                            </vivo:tbRowColumn>      
                                                            <vivo:tbRowColumn>
                                                                <bean:write name="gruposVO" property="descricaoGrupamento"/>
                                                            </vivo:tbRowColumn>                                                                                                                                                   
                                                            <vivo:tbRowColumn>
                                                                <acesso:controlHiddenItem nomeIdentificador="vole_grpa_alterar">
                                                                    <img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" onclick="alterarGrupamento(<%=idx%>);" border="0" alt="Alterar Grupo">
                                                                </acesso:controlHiddenItem>
                                                            </vivo:tbRowColumn>
                                                            <vivo:tbRowColumn>
                                                                <acesso:controlHiddenItem nomeIdentificador="vole_grpa_remover">
                                                                    <img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" border="0" onclick="return removeItem('<bean:write name="gruposVO" property="idGrupamento"/>');" alt="Excluir Grupo">
                                                                </acesso:controlHiddenItem>
                                                            </vivo:tbRowColumn>
                                                        </vivo:tbRow>
                                                    </logic:iterate>
                                                </vivo:tbRows>
                                            </vivo:tbTable>
                                            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
                                                <table width="720" height="26" border="0" cellspacing="0" cellpadding="5" style="border:1px solid #D4D7DE; background-color:#F7F9FA; font-size:10px;">
                                                    <tr>
                                                        <td valign="middle"><b>&nbsp;Legendas:</b></td>
                                                        <td><img vspace="2" src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" align="absmiddle"> &nbsp;Alterar Grupo</td>
                                                        <td><img vspace="2" src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" align="absmiddle"> &nbsp;Excluir Grupo</td>
                                                        <td></td>
                                                    </tr>
                                                </table>
                                                
                                                <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
                                        </logic:equal>
                                        <logic:equal name="FormGrupamento" property="indicativoOperacao" value="Vazio">
                                            Não foi encontrado nenhum grupo com a descrição fornecida.
                                        </logic:equal>
                                    </tr>                    
                    <tr>
                        <td align="right">
                        <acesso:controlHiddenItem nomeIdentificador="vole_grpa_salvar">
                                <img id="btIncluir" tabindex="4" onClick="incluiGrupamento();" src="/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_incluir_over.gif'" style="cursor:hand;border:none"/>
                        </acesso:controlHiddenItem>
                        </td>
                    </tr>

                </table>
                <table>
                    <tr>
                        <td>
                            <img id="voltar" tabindex="5" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_voltar_over.gif'" onClick="window.location.href='/FrontOfficeWeb/index.jsp'; return false" style="border:none;cursor:hand;" vspace="5" hspace="5"  onBlur="document.forms[0].siglaGrupamento.focus();"/>
                        </td>
                    </tr>
                </table>
                
                </form>
                    
                    <vivo:quadroFlutuante onclick="" label="Alterar Grupo" scroll="false" src="" idIframe="ifrmAlteraPerfil" id="divAlteraPerfil" spacesLeft="150" height="100" spacesTop="120" url="<%=request.getContextPath()%>" display="none" width="500"></vivo:quadroFlutuante>
                    <vivo:quadroFlutuante onclick="" label="Incluir Grupo" scroll="false" src="" idIframe="ifrmIncluiPerfil" id="divIncluiPerfil" spacesLeft="150" height="100" spacesTop="120" url="<%=request.getContextPath()%>" display="none" width="500"></vivo:quadroFlutuante>

    <script language="javascript" for="window" event="onload">
    <!--   
        if('<bean:write name="FormGrupamento" property="msgError" />' != ""){
            alert('<bean:write name="FormGrupamento" property="msgError" />');
        }
	    if('<bean:write name="FormGrupamento" property="msgWarning" />' != ""){
	        alert('<bean:write name="FormGrupamento" property="msgWarning" />');
	    }    
        oculta_div();
        document.forms[0].siglaGrupamento.focus();
    -->
    </script> 
            </vivo:sessao>
    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>
