<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<bean:define id="FormQuestionario"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formQuestionario"/>

<bean:define id="AdmQuestionarioVO"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formQuestionario.admQuestionarioVO"/>
<bean:define id="AdmTipoPessoaVO"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formQuestionario.admTipoPessoaVO"/>

<!--Relacionados-->
<bean:define id="AdmUFOperadoraExistente"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formQuestionario.admUFOperadoraExistente"/>

<!--Existentes-->
<bean:define id="AdmUFOperadoraRelacionado"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formQuestionario.admUFOperadoraRelacionado"/>

<bean:define id="AdmQuestionariosAssocioandos"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formQuestionario.questionariosAssociados"/>



<netui-template:template templatePage="/resources/jsp/templateAdmSistClean.jsp">



<script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>

    <script language ="Javascript">
        
        function initPagina() { }
                
        function removeItem(a,b,c,d){
            if (window.confirm("Confirma remoção do item?")) {
                document.forms[0].action = 'removerQuestionario.do?idContato=' +
                                        a + 
                                        '&idUFOperadora=' + 
                                        b + 
                                        '&idTipoPessoa=' +
                                        c + 
                                        '&idTipoApresentacaoPergunta=' + 
                                        d;
                document.forms[0].submit();       
            }else{
                return;
            }                                 
        }

        function transfereSelecaoUF(listaOrigem, listaDestino)
        {
            var i;
            for(i = 0; i<listaOrigem.options.length; i++)
                if(listaOrigem.options[i].selected)
                    listaDestino.options[listaDestino.options.length] = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);
                    
            for(i = listaOrigem.options.length-1; i>=0; i--)
                if(listaOrigem.options[i].selected)
                    listaOrigem.options[i] = null;
    
        }

        function enviaDados()
        {
            if(document.forms[0].idTipoPessoaAtual.selectedIndex != 0 && 
                document.forms[0].idQuestionarioAtual.selectedIndex == 0)
            {
                alert('É necessário selecionar um Questionário.');
                return false;
                
            }else
            if(document.forms[0].idTipoPessoaAtual.selectedIndex == 0 && 
                document.forms[0].idQuestionarioAtual.selectedIndex != 0)
            {
                alert('É necessário selecionar Natureza.');
                return false;
            }else
            {

                var form = document.forms[0];
                form.action = 'begin.do';
                parent.mostrar_div();
                form.submit();        
            }
            
        }
        function salvarItem()
        {
            if(valida())
            {

                for ( i = 0; i < document.forms[0].arrayAdmUFOperadoraUsuario.options.length; i++ )
                    document.forms[0].arrayAdmUFOperadoraUsuario.options[i].selected = true;

                document.forms[0].action = 'salvarQuestionario.do';
                parent.mostrar_div();
                document.forms[0].submit();
            }
        }

        // valida Dados.
        function valida()
        {
            if(document.forms[0].idQuestionarioAtual.selectedIndex == 0)
            {
                alert('Selecione um Questionário.');
                return false;
                
            }else if(document.forms[0].idTipoPessoaAtual.selectedIndex == 0)
            {
                alert('Selecione Natureza.');
                return false;
            
            }
            
            if (document.forms[0].inIdQuestionario==undefined && document.forms[0].arrayAdmUFOperadoraUsuario[0]==null){
                alert('Selecione ao menos uma regional.');
                return false;
            }
        
            return true;
        }
    </script>

    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="adm_vqu_verpagina">
        <form action="salvarQuestionario" id="salvarQuestionario" name="salvarQuestionario" method="post">
            <html:hidden  name="FormQuestionario" property="idContato"/>

            <table width="100%" cellspacing="1" cellpadding="1">
                <tr><td height="2" align="center"><b>Vincular Questionário de Satisfação</b></td></tr>
                <tr>
                    <td colspan="4">
                    <table width="98%" border="0" cellspacing="2" cellpadding="2" align="center" class="tbl_bgBlue">
                        <tr><td height="2"></td></tr>
                        <tr>
                            <td width="5%" class="tblEstatica_campoNome">&nbsp;
                            <netui:label value="Questionário:" styleClass="tblEstatica_campoNome"/>
                            </td>
                            <td width="30%">
                            <html:select name="FormQuestionario" property="idQuestionarioAtual" style="width:500" styleClass="SELECT" size="1" onchange="document.forms[0].idTipoPessoaAtual.selectedIndex =0;" >
                                <option value="">Escolha uma opçao...</option>
                                <html:options collection="AdmQuestionarioVO" property="idQuestionario" labelProperty="dsQuestionario"/> 
                            </html:select>
                            </td>
                        </tr>
                        <tr>
                            <td width="5%" class="tblEstatica_campoNome">&nbsp;
                            <netui:label value="Natureza:" styleClass="tblEstatica_campoNome"/>
                            </td>
                            <td width="30%">
                            <html:select name="FormQuestionario" property="idTipoPessoaAtual" style="width:500" styleClass="SELECT" size="1" onchange="enviaDados();">
                                <option value="">Escolha uma opçao...</option>
                                <html:options collection="AdmTipoPessoaVO" property="idTipoPessoa" labelProperty="dsTipoPessoa" /> 
                            </html:select>
                            </td>
                        </tr>
                        <tr><td height="2"></td></tr>
                    </table>
                    <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="tbl_bgGray">
                            <tr>
                                <td align="center" colspan="2">
                                    <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                                        <tr>
                                            <td width="47%"  align="center"><b>Regionais Existentes</b></td>
                                            <td width="5%">&nbsp;</td>
                                            <td width="47%" align="center"><b>Regionais Relacionadas</b></td>
                                        </tr>
                                        <tr><td height="4"></td></tr>
                                        <tr>
                                            <td width="47%" align="center">
                                                <html:select name="FormQuestionario" property="admUFOperadoraExistente" multiple="true" style="width:250" styleClass="SELECT" size="5" >
                                                    <html:options collection="AdmUFOperadoraExistente" property="idUFOperadora" labelProperty="dsUFOperadora" /> 
                                                </html:select>
                                            </td>
                                            <td width="5%" align="center">
                                                <img id="btRightSimp" onclick="transfereSelecaoUF(document.forms[0].admUFOperadoraExistente, document.forms[0].arrayAdmUFOperadoraUsuario); return false" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_over.gif'" style="border:none;cursor:hand;"/><br><br>
                                                <img id="btLeftSimp" onclick="transfereSelecaoUF(document.forms[0].arrayAdmUFOperadoraUsuario, document.forms[0].admUFOperadoraExistente); return false" src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_over.gif'" style="border:none;cursor:hand;"/>
                                            </td>
                                            <td width="37%" align="center">
                                                <html:select name="FormQuestionario" property="arrayAdmUFOperadoraUsuario" multiple="true" style="width:250" styleClass="SELECT" size="5" >
                                                    <html:options collection="AdmUFOperadoraRelacionado" property="idUFOperadora" labelProperty="dsUFOperadora" /> 
                                                </html:select>
                                            </td>
                                    
                                        </tr>
                                        <tr><td height="2"></td></tr>
                                    </table>
                                </td>
                            </tr>
                            <tr><td height="2"></td></tr>
                            <tr><td height="2"></td></tr>
                        </table>
                    </td>
                </tr>
                <tr><td height="1"></td></tr>
                <tr>
                    <td>                        
                        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="3"></div>
                    </td>    
                </tr>
                <tr>
                    <td align="center">                
                        <vivo:tbTable selectable="true" layoutWidth="730" layoutHeight="145" tableWidth="730" styleId="tableTitle" sortable="true" >
                            <vivo:tbHeader>
                                <vivo:tbHeaderColumn align="left" width="100%" type="string">Questionários Relacionados</vivo:tbHeaderColumn>					
                            </vivo:tbHeader>
                            <vivo:tbRows>
                                <logic:iterate name="AdmQuestionariosAssocioandos" id="Qassocioados">
                                <vivo:tbRow key="linha">
                                    <vivo:tbRowColumn>
                                        <bean:write name="Qassocioados" property="dsQuestionario"/>
                                        <input type="hidden" name="inIdQuestionario">
                                    </vivo:tbRowColumn>
                                </vivo:tbRow>
                                </logic:iterate>
                            </vivo:tbRows>
                        </vivo:tbTable>
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        <img id="btSalvar1" onclick="salvarItem(); return false" src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_salvar_over.gif'" style="border:none;cursor:hand;"/>
                    
                    </td>
                </tr>    
                <tr><td height="2"></td></tr>

            </table>

    <script language="javascript" for="window" event="onload">
    <!--   
        document.body.style.backgroundColor = '#ededed';
        parent.oculta_div();
    -->
    </script> 
    <vivo:alert atributo="msgError" scope="request"/>
            
        </form>
        
        </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>
