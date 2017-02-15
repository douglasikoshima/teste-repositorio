<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>
<bean:define id="FormPagina"       name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="paginaForm"/>
<bean:define id="idSistema"        name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="paginaForm.idSistema"/>
<bean:define id="listaSubSistemas" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="paginaForm.listaSubSistemasVO"/>
<bean:define id="paginaAtual"      name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="paginaForm.paginaAtual"/>
<bean:define id="listaPaginasVO"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="paginaForm.listaPaginasVO"/>

<netui-template:template templatePage="/resources/jsp/templateUsuarioClean.jsp">
    <netui-template:section name="headerSection">
    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/tratamento.js" ></script>
    <script language="JavaScript" type="text/JavaScript">
        <!--
            // Simple rollover function which replaces the image.src with the passed image
            function swapImage(control, image){
              control.src = image;
            }
                
            function abrePopup(pagina,janela,largura,altura, y, x){
                var desktopname = window.open(pagina,janela,'width='+largura+',height='+altura+',toolbar=no,copyhistory=no,location=no,status=yes,menubar=no,scrollbars=no,resizable=no, top='+y+',left='+x);
                desktopname.focus();
            }
            
            function testaCampos() {
                document.forms[0].target = '';
                document.forms[0].nmPagina.value = trim(document.forms[0].nmPagina.value);
                document.forms[0].nmUrl.value = trim(document.forms[0].nmUrl.value);
                
                if ((document.forms[0].nmPagina.value == "") && (document.forms[0].nmUrl.value == "") && (document.forms[0].idSubSistema.value == "")) {
                    alert("É preciso preencher pelo menos um dos campos de pesquisa.");
                } else {
                    document.forms[0].action = "listaPaginas.do";
                    parent.mostrar_div();
                    document.forms[0].submit();
                }
            }
            
        function removeItem() {
            document.forms[0].target = '';
            if (window.confirm("Confirma remoção do item?")) {
                return true;
            }else{
                return false;
            }
        }

            function salvaPagina(tipo) {
                document.forms[0].nmPagina.value = trim(document.forms[0].nmPagina.value);
                document.forms[0].nmUrl.value = trim(document.forms[0].nmUrl.value);
                if (document.forms[0].nmPagina.value == "") {
                    alert("É necessário preencher o campo Nome da página.");
                } else if (document.forms[0].nmUrl.value == "") {
                    alert("É necessário preencher o campo URL da página.");
                } else if (document.forms[0].idSubSistema.value == "") {
                    alert("É necessário indicar o subsistema ao qual a página pertence");
                } else if (document.forms[0].inDisponib.value == "") {
                    alert("É necessário indicar a disponibilidade da página.");
                } else {
                    if (tipo=="novo")
                        document.forms[0].action = "salvaPagina.do?tipo=novo";
                    else
                        document.forms[0].action = "salvaPagina.do?tipo=edicao";
                    parent.mostrar_div();
                    document.forms[0].submit();
                }
            }
            
            function incluiPagina(){
                divIncluiPagina.style.display = '';
                document.forms[0].target = "ifrmIncluiPagina";
                document.forms[0].action = "incluirAlterarPagina.do?operacao=incluir";
                parent.mostrar_div();
                document.forms[0].submit();
                ifrmIncluiPagina.src = 'incluirAlterarPagina.jsp';
                //document.getElementById('ifrmIncluiPagina').src = 'incluirAlterarPagina.jsp';                
            }
            
            function alteraPagina(id){
                divAlteraPagina.style.display = '';                
                document.forms[0].target = "ifrmAlteraPagina";
                //document.forms[0].action = "incluirAlterarPagina.jsp?operacao=edicao&nmPagina="+nome+"&nmUrl="+URL+"&idSubSistema="+subSistema+"&inDisponib="+disponibilidade+"&idPagina="+id;
                document.forms[0].action = 'incluirAlterarPagina.do?operacao=alterar&idPagina='+id;
                parent.mostrar_div();
                document.forms[0].submit();
                ifrmAlteraPagina.src = 'incluirAlterarPagina.jsp';
                //document.getElementById('ifrmAlteraPagina').src = 'incluirAlterarPagina.jsp';                
            }

            function verificaEnter(ev){
                if(ev.keyCode == 13)
                    return false;
            }
        -->
    </script>
    <SCRIPT>
        parent.mostrar_div();
    </script>
    </netui-template:section>
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="usu_mpg_verpagina">
    <form action="listaPaginas.do" onSubmit="return false;" method="post">
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="10"></div>
        <center>
        <table width="97%" border="0" cellspacing="1" cellpadding="1" align="center" class="tbl_bgBlue">
            <tr>
                <td width="175" class="tblEstatica_campoNome"><netui:label value="Nome da Página: " styleClass="tblEstatica_campoNome"/></td>
                <td width="" align="left"><html:text name="paginaAtual" property="nmPagina" style="width:300px" styleClass="input" maxlength="254"/></td>
                <td></td>
            </tr>
            <tr>
                <td class="tblEstatica_campoNome"><netui:label value="URL da Página: " styleClass="tblEstatica_campoNome"/></td>
                <td align="left"><html:text name="paginaAtual" property="nmUrl" style="width:300px" styleClass="input" maxlength="254" onkeypress="return ValidarTeclaURL()"/></td>
                <td></td>
            </tr>
            <tr>
                <td class="tblEstatica_campoNome"><netui:label value="Pertence ao Sub-Sistema: " styleClass="tblEstatica_campoNome"/></td>
                <td align="left" colspan="2" style="padding-left:3px;">
                    <html:select name="paginaAtual" property="idSubSistema" style="width:200" styleClass="SELECT">
                        <html:option value="">Escolha uma opção...</html:option>
                        <html:options collection="listaSubSistemas" property="idSubSistema" labelProperty="dsSubSistema" /> 
                    </html:select>
                    <netui:label value="Disponibilidade: " styleClass="tblEstatica_campoNome"/>
                    <html:select name="paginaAtual" property="inDisponib">
                        <html:option value="">Escolha...</html:option>
                        <html:option value="1">Sim</html:option>
                        <html:option value="0">Não</html:option>
                    </html:select>
                </td>
            </tr>
            <tr>
                <td></td>
                <td colspan="2" align="right" height="20">
                    <img vspace="5" onClick="window.location.href='limpaPagina.do'; return false" id="btLimpar" src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif'" style="border:none;cursor:hand" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_limpar_over.gif'"/>
                    <acesso:controlHiddenItem nomeIdentificador="usu_mpg_pesq"><img vspace="5" hspace="10" onclick="testaCampos();" id="btPesquisar" src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif'" style="border:none;cursor:hand"/></acesso:controlHiddenItem>
                </td>
            </tr>
        </table>
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="10"></div> 
        <logic:notEqual name="FormPagina" property="listaPaginasLength" value="0">
            <logic:notEqual name="FormPagina" property="listaPaginasLength" value="-1">
                <vivo:tbTable selectable="true" layoutWidth="710" layoutHeight="195" tableWidth="710" styleId="tableTitle" sortable="true">
                    <vivo:tbHeader>
                        <vivo:tbHeaderColumn align="left"   width="180" type="string">Nome da Página</vivo:tbHeaderColumn>
                        <vivo:tbHeaderColumn align="left"   width="140" type="string">Subsistema</vivo:tbHeaderColumn>
                        <vivo:tbHeaderColumn align="left"   width="160" type="string">URL</vivo:tbHeaderColumn>
                        <vivo:tbHeaderColumn align="center" width="50" type="string">Disponib.</vivo:tbHeaderColumn>
                        <vivo:tbHeaderColumn align="center" width="20" type="string"></vivo:tbHeaderColumn>
                        <vivo:tbHeaderColumn align="center" width="20" type="string"></vivo:tbHeaderColumn>
                    </vivo:tbHeader>
                    <vivo:tbRows>
                        <logic:iterate name="FormPagina" property="listaPaginasVO" id="itemPagina">
                            <vivo:tbRow key="linha3">
                                <vivo:tbRowColumn><bean:write name="itemPagina" property="nmPagina"/></vivo:tbRowColumn>
                                <vivo:tbRowColumn><bean:write name="itemPagina" property="dsSubSistema"/></vivo:tbRowColumn>
                                <vivo:tbRowColumn><bean:write name="itemPagina" property="nmUrl"/></vivo:tbRowColumn>
                                <vivo:tbRowColumn>
                                    <logic:equal name="itemPagina" property="inDisponib" value="1">sim</logic:equal>
                                    <logic:equal name="itemPagina" property="inDisponib" value="0">não</logic:equal>
                                </vivo:tbRowColumn>
                                <vivo:tbRowColumn>
                                    <acesso:controlHiddenItem nomeIdentificador="usu_mpg_edit">
                                        <img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" border="0" onclick="alteraPagina('<bean:write name="itemPagina" property="idPagina"/>'); return false;">
                                    </acesso:controlHiddenItem>
                                </vivo:tbRowColumn>
                                <vivo:tbRowColumn>    
                                    <acesso:controlHiddenItem nomeIdentificador="usu_mpg_excluir">                                
                                    <html:link page="/usuario/manterSistema/editarParSistema/manterPagina/removePagina.do"  paramId="paginaId" onclick="return removeItem();" paramName="itemPagina" paramProperty="idPagina">
                                        <img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" border="0">
                                    </html:link>
                                    </acesso:controlHiddenItem>
                                </vivo:tbRowColumn>
                            </vivo:tbRow>
                        </logic:iterate>
                    </vivo:tbRows>
                </vivo:tbTable>
            </logic:notEqual>
            <logic:equal name="FormPagina" property="listaPaginasLength" value="-1">
                <center><span class="tblEstatica_campoNome">A pesquisa não retornou nenhum resultado.</span><center>
            </logic:equal>
        </logic:notEqual>
        <div align="right">
        <acesso:controlHiddenItem nomeIdentificador="usu_mpg_insert">
            <img hspace="12" vspace="5" src="/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_incluir_over.gif'" style="border:none;cursor:hand" onClick="incluiPagina(); return false"/>
        </acesso:controlHiddenItem>
        </div>
        </center>
        <!-- Testa se alguma busca foi realizada -->
        <!-- Caso haja uma lista de paginas como retorno, seu comprimento será diferente de 0 -->
    </form>
        <vivo:quadroFlutuante label="Incluir P&aacute;gina" scroll="false" src="" id="divIncluiPagina" idIframe="ifrmIncluiPagina" spacesLeft="120" height="150" spacesTop="80" url="<%=request.getContextPath()%>" display="none" width="500"></vivo:quadroFlutuante>
        <vivo:quadroFlutuante label="Alterar P&aacute;gina" scroll="false" src="" idIframe="ifrmAlteraPagina" id="divAlteraPagina" spacesLeft="120" height="150" spacesTop="80" url="<%=request.getContextPath()%>" display="none" width="500"></vivo:quadroFlutuante>
    <script>
        document.body.style.backgroundColor = '#ededed';
    </script>
    <script language="javascript" for="window" event="onload">
    <!--   
            if('<bean:write name="FormPagina" property="msgError" />' != ""){
            alert('<bean:write name="FormPagina" property="msgError" />');
        }
        parent.oculta_div();
    -->
    </script> 
    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>
