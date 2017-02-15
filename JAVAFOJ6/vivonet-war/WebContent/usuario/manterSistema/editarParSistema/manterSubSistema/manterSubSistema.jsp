<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>
<bean:define id="FormSubSistema"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="subSistemasForm"/>
<bean:define id="listaSubSistemas" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="subSistemasForm.listaObjSubSistemas"/>
<bean:define id="subSistema"       name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="subSistemasForm.subSistema"/>
<bean:define id="idSistema"        name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="subSistemasForm.idSistema"/>
<bean:define id="dsSubSistema"     name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="subSistemasForm.dsSubSistema"/>

<netui-template:template templatePage="/resources/jsp/templateUsuarioClean.jsp">
    <netui-template:section name="headerSection">
    <SCRIPT LANGUAGE="JavaScript">
        <!--
        function removeItem(idSubSistema) {
            document.forms[0].target = '';
            if (window.confirm("Confirma remoção do item?")) {
                document.forms[0].action = "removeSubSistema.do?idSubSistema="+idSubSistema;
                parent.mostrar_div();
                document.forms[0].submit();
                return true;
            }else{
                return false;
            }
        }
        
        function limpa(){
            document.forms[0].dsSubSistema.value = '';
            document.forms[0].dsSubSistema.focus();
        }
        
        function pesquisaSubSistema(subSistema) {
            document.forms[0].target = '';
            var action = 'listaSubSistema.do?dsSubSistema='+subSistema;
            document.forms[0].action = action;
            parent.mostrar_div();
            document.forms[0].submit();
        }
        
        function incluiSubsistema(){
            divIncluiSubsistema.style.display = '';
            document.getElementById('ifrmIncluiSubsistema').src = 'incluirAlterarSubsistema.do?tipo=novo';
            document.forms[0].action = "incluirAlterarSubsistema.jsp";   
        }
        
        function alteraSubsistema(id){
            divAlteraSubsistema.style.display = '';
            //document.getElementById('ifrmAlteraSubsistema').src = 'incluirAlterarSubsistema.jsp';
            document.forms[0].idSubSistema.value = id;            
            document.forms[0].action = 'salvaEditaSubSistema.do?operacao=alterar&idsubSistema='+id;
            document.forms[0].target = "ifrmAlteraSubsistema";  
            parent.mostrar_div();
            document.forms[0].submit();
            ifrmAlteraSubsistema.src = 'incluirAlterarSubsistema.jsp';
        }
        // -->
    </SCRIPT>
    <SCRIPT>
        parent.mostrar_div();
    </script>
    </netui-template:section>
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="usu_mss_verpagina">
        <html:form action="/usuario/manterSistema/editarParSistema/manterSubSistema/salvaSubSistema.do" onsubmit="return false;" method="post">
        <html:hidden name="FormSubSistema" property="idSubSistema"/>
        <html:hidden name="FormSubSistema" property="idSistema"/>
                <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="2"></div>
                <table width="730" cellpadding="0" cellspacing="0" class="tbl_bgBlue">
                    <tr>
                        <td width="155" style="text-indent:10px;"><b>Nome de SubSistema</b></td>
                        <td><html:text name="FormSubSistema" property="dsSubSistema" style="width:300px" styleClass="input" maxlength="200" /></td>
                        <td>
                            <img vspace="5" onclick="limpa(); return false" id="btLimpar" src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif'" style="border:none;cursor:hand" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_limpar_over.gif'"/>
                            <acesso:controlHiddenItem nomeIdentificador="usu_mss_pesq"><img vspace="5" hspace="10" onclick="pesquisaSubSistema(document.forms[0].dsSubSistema.value);" id="btPesquisar" src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif'" style="border:none;cursor:hand"/></acesso:controlHiddenItem>

                        </td>

                    </tr>
                </table>
                    <br>
    <logic:notEqual name="FormSubSistema" property="listaSubSistemaLength" value="0">
    <logic:notEqual name="FormSubSistema" property="listaSubSistemaLength" value="-1">
        <vivo:tbTable styleId="subSistemas" layoutHeight="260" tableWidth="728" layoutWidth="728" selectable="true" sortable="true">
            <vivo:tbHeader>
                <vivo:tbHeaderColumn align="left" width="" type="string">Subsistemas cadastrados</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn width="20" type="string"></vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn width="20" type="string"></vivo:tbHeaderColumn>
            </vivo:tbHeader>
            <vivo:tbRows>
                <logic:iterate id="lista" name="listaSubSistemas">
                <vivo:tbRow key="Linha">
                    <vivo:tbRowColumn><bean:write name="lista" property="dsSubSistema"/></vivo:tbRowColumn>
                    <vivo:tbRowColumn><acesso:controlHiddenItem nomeIdentificador="usu_mss_alterar"><img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" onclick="alteraSubsistema('<bean:write name="lista" property="idSubSistema"/>'); return false;"></acesso:controlHiddenItem></vivo:tbRowColumn>
                    <vivo:tbRowColumn><acesso:controlHiddenItem nomeIdentificador="usu_mss_excluir"><img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" onclick="return removeItem('<bean:write name="lista" property="idSubSistema"/>');"></acesso:controlHiddenItem></vivo:tbRowColumn>
                </vivo:tbRow>
                </logic:iterate>
            </vivo:tbRows>
        </vivo:tbTable>
        </logic:notEqual>
        <logic:equal name="FormSubSistema" property="listaSubSistemaLength" value="-1">
            <div align="center" style="font-size:11px;">Não foi encontrado nenhum registro com a descrição fornecida.</div>
        </logic:equal>
    </logic:notEqual>
        <div align="right">
        <acesso:controlHiddenItem nomeIdentificador="usu_mss_salvar">
            <img vspace="5" hspace="8" src="/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_incluir_over.gif'" onClick="incluiSubsistema(); return false" style="border:none;cursor:hand;"/>
        </acesso:controlHiddenItem>
        </div>
        </html:form>
        <script>
            document.body.style.backgroundColor = '#ededed';
        </script>
        <vivo:quadroFlutuante label="Incluir Subsistema" scroll="false" src="" idIframe="ifrmIncluiSubsistema" id="divIncluiSubsistema" spacesLeft="150" height="80" spacesTop="150" url="<%=request.getContextPath()%>" display="none" width="500"></vivo:quadroFlutuante>
        <vivo:quadroFlutuante label="Alterar Subsistema" scroll="false" src="" idIframe="ifrmAlteraSubsistema" id="divAlteraSubsistema" spacesLeft="150" height="80" spacesTop="150" url="<%=request.getContextPath()%>" display="none" width="500"></vivo:quadroFlutuante>
    <script language="javascript" for="window" event="onload">
    <!--   
                if('<bean:write name="FormSubSistema" property="msgError" />' != ""){
            alert('<bean:write name="FormSubSistema" property="msgError" />');
        }
        parent.oculta_div();
    -->
    </script> 
    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>