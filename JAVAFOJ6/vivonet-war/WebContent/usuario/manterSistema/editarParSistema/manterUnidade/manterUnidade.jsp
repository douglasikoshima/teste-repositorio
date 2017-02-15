<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>
<bean:define id="FormUnidade"            name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="unidadeForm"/>
<bean:define id="idSistema"              name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="unidadeForm.idSistema"/>
<bean:define id="listaSubSistemas"       name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="unidadeForm.listaSubSistemasUsuarioVO"/>
<bean:define id="unidadeAtual"           name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="unidadeForm.unidadeAtual"/>
<bean:define id="listaSubSistemaPaginas" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="unidadeForm.listaSubSistemaPaginas"/>
<logic:notEqual name="FormUnidade" property="listaPaginasVO" value="null">
    <bean:define id="listaPaginasVO"     name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="unidadeForm.listaPaginasVO" />
</logic:notEqual>
<bean:define id="listaUnidadesPesquisa"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="unidadeForm.listaUnidadesPesquisa"/>

<netui-template:template templatePage="/resources/jsp/templateUsuarioClean.jsp">
    <netui-template:section name="headerSection">
    <script language="JavaScript" type="text/JavaScript">
            function swapImage(control, image) {
              control.src = image;
            }
            
            // Carrega o select de Paginas
            function carregaSelect(objeto){
                document.forms[0].target = '_self';
                if(objeto.value=='0000'){
                    document.forms[0].elements['unidadeAtual.idPagina'].selectedIndex = 0;
                    return;
                }else{
                    document.forms[0].action = "carregaSelectPagina.do";
                    parent.mostrar_div();
                    document.forms[0].submit(); 
                }
            }
                
            function inserir(){
                if(document.forms[0].elements["unidadeAtual.idSubSistema"].value=="0000"){
                    alert("É necessário a escolha de um subsistema para efetuar a inclusão.");
                    return;
                }
                if(document.forms[0].elements["unidadeAtual.idPagina"].value=="0000"){
                    alert("É necessário existir uma pagina cadastrada para efetuar a inclusão.");
                    return;
                }
                if(document.forms[0].elements["unidadeAtual.nmUnidade"].value==""){
                    alert("É necessário o preenchimento de todos os campos.");
                    return;
                }
                if(document.forms[0].elements["unidadeAtual.cdUnidade"].value==""){
                    alert("É necessário o preenchimento de todos os campos.");
                    return;
                }
                document.forms[0].action ='salvaUnidade.do';
                parent.mostrar_div();
                document.forms[0].submit();
            }
            
            function pesquisa(){
               if(document.forms[0].elements["unidadeAtual.idSubSistema"].value=="0000"){
                    alert("É necessário a escolha de um subsistema para efetuar a pesquisa.");
                    return;
                }
                document.forms[0].action ='pesquisaUnidades.do';
                document.forms[0].target = '_self';
                parent.mostrar_div();
                document.forms[0].submit();
            }
            
            function removeUnidade(unidade){ 
                document.forms[0].target = '';
                if (window.confirm("Confirma remoção do item?")) {
                    document.forms[0].action = "removeUnidade.do?" + unidade ;
                    parent.mostrar_div();
                    document.forms[0].submit();
                }
                else{
                    return;
                }
            }
            
            function editaUnidade(idUnidade, idSubSistema, idPagina, nmPagina, nmUnidade, cdUnidade, inDetalhe, dsSubSistema ){
                document.forms[0].idUnidadeTemp.value = idUnidade;
                document.forms[0].idSubSistemaTemp.value = idSubSistema;
                document.forms[0].idPaginaTemp.value = idPagina;
                document.forms[0].inDetalheTemp.value = inDetalhe;
                
                document.forms[0].elements["unidadeAtual.idSubSistema"].options.length=0;
                document.forms[0].elements["unidadeAtual.idSubSistema"].options[0] = new Option(dsSubSistema  , idSubSistema);
                
                document.forms[0].elements["unidadeAtual.idPagina"].options.length=0;
                document.forms[0].elements["unidadeAtual.idPagina"].options[0] = new Option(nmPagina  , idPagina);
                document.forms[0].elements["unidadeAtual.nmUnidade"].value = nmUnidade;
                document.forms[0].elements["unidadeAtual.cdUnidade"].value = cdUnidade;
                document.forms[0].dsSubSistemaTemp.value = dsSubSistema;
                document.forms[0].nmPaginaTemp.value = nmPagina;
                document.forms[0].flag.value = 1;
                testaBotao();
            }
            
            function testaBotao() {
                if (document.forms[0].flag.value == 0) {
                    document.all.dvBtIncluir.style.display='block';
                    document.all.dvBtAlterar.style.display='none';
                } else {
                    document.all.dvBtIncluir.style.display='none';
                    document.all.dvBtAlterar.style.display='block';                
                }
            }
            
            function trocaBotao(){
                document.all.dvBtIncluir.style.display='block';
                document.all.dvBtAlterar.style.display='none';
                document.forms[0].flag.value = 0;
                document.forms[0].action = "limpaUnidade.do"
                parent.mostrar_div();
                document.forms[0].submit();
            }
            
            function alteraUnidade(idUnidade){
                divAlteraUnidade.style.display = '';                 
                document.forms[0].target = 'ifrmAlteraUnidade';
                document.forms[0].action = 'incluirAlterarUnidade.do?idUnidade=' + idUnidade;
                parent.mostrar_div();
                document.forms[0].submit();
            }
            
            function incluiUnidade(){
                divAlteraUnidade.style.display = '';                 
                document.forms[0].target = 'ifrmAlteraUnidade';
                document.forms[0].action = 'incluiUnidade.do';
                parent.mostrar_div();
                document.forms[0].submit();

                //divIncluiUnidade.style.display = '';
                //document.forms[0].target = "ifrmIncluiUnidade";
                //document.forms[0].action = "incluirAlterarUnidade.jsp?flag=I";
                //parent.mostrar_div();
                //document.forms[0].submit();
                //ifrmIncluiUnidade.src = 'incluirAlterarUnidade.jsp';
                  // document.getElementById('ifrmIncluiUnidade').src = 'incluirAlterarUnidade.jsp';                
            }

            function limpa(){
                        document.forms[0].elements["unidadeAtual.idSubSistema"].selectedIndex = 0;
                while(document.forms[0].elements["unidadeAtual.idPagina"].options.length > 1){
                              document.forms[0].elements["unidadeAtual.idPagina"].options[1]     = null;  
                          }         
                          document.forms[0].elements["unidadeAtual.cdUnidade"].value    = '';   
                          document.forms[0].elements["unidadeAtual.nmUnidade"].value    = ''; 
            }
                        
            function verificaEnter(ev){
                if(ev.keyCode == 13)
                    return false;
            }
    </script>
    <SCRIPT>
        parent.mostrar_div();
    </script>
    </netui-template:section>
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="usu_mun_verpagina">
        <form action="pesquisaUnidades.do" method="post">
        <input type="hidden" name="idUnidadeTemp"/> 
        <input type="hidden" name="idSubSistemaTemp"/>
        <input type="hidden" name="idPaginaTemp"/>
        <input type="hidden" name="inDetalheTemp"/>
        <input type="hidden" name="dsSubSistemaTemp"/>
        <input type="hidden" name="nmPaginaTemp"/>
        
        <!-- html:hidden name="FormUnidade" property="msgOperation"/ -->
        <!-- html:hidden name="FormUnidade" property="statusOperation" /-->

        <table width="100%" border="0" cellspacing="1" cellpadding="1" align="center" class="tbl_bgBlue">
            <tr><td height="4"></td></tr>
            <tr>
                <td width="17%" class="tblEstatica_campoNome"><netui:label value="Sub-sistema: " styleClass="tblEstatica_campoNome"/></td>
                <td width="37%" style="padding-left:3px;">
                    <html:select name="FormUnidade" property="unidadeAtual.idSubSistema" style="width:250px;" onchange="carregaSelect(this);">
                        <html:option value="0000">Escolha uma opção...</html:option>
                        <html:options collection="listaSubSistemas" property="idSubSistema" labelProperty="dsSubSistema" /> 
                    </html:select>
                </td>
                <td width="14%" align="right" class="tblEstatica_campoNome"><netui:label value="Página: " styleClass="tblEstatica_campoNome"/></td>
                <td width="32%" align="left">
                    <html:select name="FormUnidade" property="unidadeAtual.idPagina" style="width:200px;" styleClass="SELECT">
                        <html:option value="0000">Escolha uma opção...</html:option>
                        <html:options collection="listaPaginasVO" property="idPagina" labelProperty="nmPagina" /> 
                    </html:select>
                </td>
            </tr>
            <tr>
                <td class="tblEstatica_campoNome"><netui:label value="Nome da Unidade: " styleClass="tblEstatica_campoNome"/></td>
                <td><html:text name="FormUnidade" property="unidadeAtual.nmUnidade" style="width:300px" onkeypress="return verificaEnter(event)" maxlength="150"/></td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td class="tblEstatica_campoNome"><netui:label value="Identificação: " styleClass="tblEstatica_campoNome"/></td>
                <td><html:text name="FormUnidade" property="unidadeAtual.cdUnidade" style="width:300px;" maxlength="150" onkeypress="return verificaEnter(event)"/></td>
                <td align="right" class="tblEstatica_campoNome"><!-- <netui:label value="Detalhe: " styleClass="tblEstatica_campoNome"/> --></td>
                <td width="31%" align="left">&nbsp;
                    <img onClick="limpa(); return false" id="btLimpar" src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif'" style="border:none;cursor/:hand;" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_limpar_over.gif'"/>
                    <acesso:controlHiddenItem nomeIdentificador="usu_mun_pesq"><img onClick="pesquisa(); return false" id="btPesquisar" src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif'" style="border:none;cursor:hand;" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif'"/></acesso:controlHiddenItem>
                    <!--
                    < netui:select dataSource="{}" style="width:50px" styleClass="SELECT">
                        < netui:selectOption value="Sim"/>
                        < netui:selectOption value="Não"/>
                    < /netui:select>
                    -->
                </td>
            </tr>
            <tr>
                <td></td>
                <td></td>
            </tr>
        </table>
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
        <!-- Testa se alguma busca foi realizada -->
        <!-- Caso haja uma lista de paginas como retorno, seu comprimento será diferente de 0 -->
        <logic:notEqual name="FormUnidade" property="indicativoOperacao" value="0">
            <logic:equal name="FormUnidade" property="indicativoOperacao" value="Resultado">
                <vivo:tbTable selectable="true" layoutWidth="732" layoutHeight="213" tableWidth="732" styleId="tableTitle" sortable="true">
                    <vivo:tbHeader>
                        <vivo:tbHeaderColumn align="left"   width="255" type="string">Nome da Unidade</vivo:tbHeaderColumn>					
                        <vivo:tbHeaderColumn align="left"   width="118" type="string">Identificação</vivo:tbHeaderColumn>
                        <vivo:tbHeaderColumn align="left"   width="118" type="string">Página</vivo:tbHeaderColumn>
                        <vivo:tbHeaderColumn align="left"   width="227" type="string">Sub-sistema</vivo:tbHeaderColumn>
                        <vivo:tbHeaderColumn align="center" width="32" type="string"></vivo:tbHeaderColumn>
                        <vivo:tbHeaderColumn align="center" width="32" type="string"></vivo:tbHeaderColumn>
                    </vivo:tbHeader>
                    <vivo:tbRows>
                        <logic:iterate name="FormUnidade" property="listaUnidadesPesquisa" id="itemUnidade">
                            <vivo:tbRow key="linha1">
                                <vivo:tbRowColumn><bean:write name="itemUnidade" property="nmUnidade"/></vivo:tbRowColumn>
                                <vivo:tbRowColumn><bean:write name="itemUnidade" property="cdUnidade"/></vivo:tbRowColumn>
                                <vivo:tbRowColumn><bean:write name="itemUnidade" property="nmPagina"/></vivo:tbRowColumn>
                                <vivo:tbRowColumn><bean:write name="itemUnidade" property="dsSubSistema"/></vivo:tbRowColumn>
                                <vivo:tbRowColumn>
                                <acesso:controlHiddenItem nomeIdentificador="usu_mun_alterar">
                                        <img onclick="alteraUnidade('<bean:write name="itemUnidade" property="idUnidade"/>');" src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" border="0">
                                </acesso:controlHiddenItem>
                                </vivo:tbRowColumn>
                                <vivo:tbRowColumn>
                                    <acesso:controlHiddenItem nomeIdentificador="usu_mun_remove">
                                    <img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" border="0" onclick="removeUnidade('unidadeId=<bean:write name="itemUnidade" property="idUnidade"/>&idPagina=<bean:write name="itemUnidade" property="idPagina"/>');">
                                    </acesso:controlHiddenItem>
                                </vivo:tbRowColumn>
                            </vivo:tbRow>
                        </logic:iterate>
                    </vivo:tbRows>
                </vivo:tbTable>
            </logic:equal>
            <logic:equal name="FormUnidade" property="indicativoOperacao" value="Vazio">
                <div align="center" style="font-size:11px;">Não foi encontrado nenhum registro com a descrição fornecida.</div>
            </logic:equal>
        </logic:notEqual>
        <div align="right">
            <acesso:controlHiddenItem nomeIdentificador="usu_mun_incluir">
                <img onClick="incluiUnidade(); return false" vspace="6" id="btIncluir" src="/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif'" style="border:none;cursor:hand;" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_incluir_over.gif'"/>
            </acesso:controlHiddenItem>
        </div>
        </form>
        <script>
            document.body.style.backgroundColor = '#ededed';
        </script>
            <vivo:quadroFlutuante label="Alterar Unidade" scroll="false" src="" idIframe="ifrmAlteraUnidade" id="divAlteraUnidade" spacesLeft="150" height="150" spacesTop="100" url="<%=request.getContextPath()%>" display="none" width="500"></vivo:quadroFlutuante>
            <vivo:quadroFlutuante label="Incluir Unidade" scroll="false" src="" idIframe="ifrmIncluiUnidade" id="divIncluiUnidade" spacesLeft="150" height="150" spacesTop="100" url="<%=request.getContextPath()%>" display="none" width="500"></vivo:quadroFlutuante>
        </div>
    <script language="javascript" for="window" event="onload">
    <!--   
                if('<bean:write name="FormUnidade" property="msgError" />' != ""){
            alert('<bean:write name="FormUnidade" property="msgError" />');
        }
        parent.oculta_div();
    -->
    </script> 
    </acesso:controlHiddenItem>    
    </netui-template:section>
</netui-template:template>
