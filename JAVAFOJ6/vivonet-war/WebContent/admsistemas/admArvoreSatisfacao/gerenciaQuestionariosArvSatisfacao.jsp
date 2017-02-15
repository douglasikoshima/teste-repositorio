<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="admsistemas.admArvoreSatisfacao.admArvoreSatisfacaoController.FormQuestionario"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<bean:define id="FormQuestionario"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formQuestionario"/>

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Gerenciamento de Árvore de Perguntas"/>
    <netui-template:setAttribute name="modulo" value="Administração de Sistemas"/>

    <netui-template:section name="headerSection"> 

    <head>
    <script language="Javascript">
        
        function removeQuestionario(id) {
            document.forms[0].target = '';
            if (window.confirm("Confirma remoção do item?")) {
                document.forms[0].action = 'removeQuestionario.do?idQuestionario=' + id;
                mostrar_div();
                document.forms[0].submit(); 
            }
        }

        function excluirItem(){
            confirm('Tem certeza que deseja excluir este questionário? Todas as perguntas e respostas relacionadas a ele também serão removidas!"');
        }
        
        function alteraQuestionario(id)  {
            divAlteraQuestionario.style.display = '';
            document.forms[0].target = "ifrmAlteraQuestionario";
            document.forms[0].idQuestionario.value = id;            
            document.forms[0].action = 'incluirAlterarQuestionario.do?operacao=editar&idQuestionario=' + id;
            mostrar_div();
            document.forms[0].submit();            
        }

        function editaParam(id) {            
            document.forms[0].idQuestionario.value = id;  
            document.forms[0].target = '';          
            document.forms[0].action = 'editaParam.do?idQuestionario=' + id;
            mostrar_div();
            document.forms[0].submit();
        }

        function incluiQuestionario() {
            divIncluiQuestionario.style.display = '';
            ifrmIncluiQuestionario.location.href = 'incluirAlterarQuestionario.do?operacao=incluir';
            mostrar_div();
        }
        
        function limpa()
        {
            document.forms[0].target = '';
            document.forms[0].dsQuestionario.value = '';
            document.forms[0].dsQuestionario.focus();
        }

        function pesquisaQuestionario() {
            document.forms[0].target = '';
            var action = 'gerenciaQuestionariosArvSatisfacao.do';
            document.forms[0].action = action;
            mostrar_div();
            document.forms[0].submit();
        }
    </script>
        
        <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
        <link href="<%=request.getContextPath()%>/resources/css/xtree.css" type="text/css" rel="stylesheet"/>
		<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/xtree.js"></script>
		<script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    </head>    
    </netui-template:section>
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="adm_qsat_verpagina">
    
    <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
    
    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
    
        <vivo:sessao id="qdMain" height="468" width="790" label="Questionário de Satisfação" operacoes="Gerenciamento" scroll="no">

        <html:form styleId="gerenciaQuestionariosArvSatisfacao" action="/admsistemas/admArvoreSatisfacao/gerenciaQuestionariosArvSatisfacao.do" method="post">
        
            <html:hidden name="FormQuestionario" property="idQuestionario"/>
        
            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="2"></div>
            
            <table width="770" align="center" cellpadding="0" cellspacing="0" class="tbl_bgGray">
                <tr>
                    <td width="155" style="text-indent:10px;"><netui:label value="Pesquisa de Satisfação:" styleClass="tblEstatica_campoNome"/>
                    </td>
                    <td><html:text name="FormQuestionario" property="dsQuestionario" style="width:330px" styleClass="input"/>
                    </td>
                    <td>
                            <img vspace="5" onclick="limpa(); return false" id="btLimpar" src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif" style="border:none;"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;                       
                            <acesso:controlHiddenItem nomeIdentificador="adm_qsat_pesquisar">
                            <img vspace="5" onclick="pesquisaQuestionario();" id="btLimpar" src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" style="border:none;"/>                        
                            </acesso:controlHiddenItem>
                    </td>
                </tr>
            </table>
            <br>
            
            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="4"></div>
            
            <logic:equal name="FormQuestionario" property="listaQuestionarioLength" value="0">
            <table width="100%">
                <tr>
                    <td align="center">
                        <netui:label value="Não foi encontrado nenhum questionario com a descrição fornecida." styleClass="tblEstatica_campoNome"/>
                        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="4"></div>
                    </td>
                </tr>
            </table>
            </logic:equal>
            <logic:greaterThan name="FormQuestionario" property="listaQuestionarioLength" value="0">
            <vivo:tbTable selectable="true" layoutWidth="762" layoutHeight="330" tableWidth="762" styleId="tableTitle" sortable="true">
                <vivo:tbHeader>
                    <vivo:tbHeaderColumn align="left" width=""    type="string">Lista de Question&aacute;rios</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="center" width="30"  type="string"></vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="center" width="30"  type="string"></vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="center" width="30"  type="string"></vivo:tbHeaderColumn>
                </vivo:tbHeader>
                <vivo:tbRows>
                    <logic:iterate name="FormQuestionario" property="admQuestionarioVO" id="questionario">
                        <vivo:tbRow key="linha">
                            <vivo:tbRowColumn>
                                <bean:write name="questionario" property="dsQuestionario" />
                            </vivo:tbRowColumn>
                            <vivo:tbRowColumn>
                            <acesso:controlHiddenItem nomeIdentificador="adm_qsat_alterar">
                                <a href="Javascript:alteraQuestionario('<bean:write name="questionario" property="idQuestionario" />');"><img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" border="0" alt="Alterar Questionario"></a>
                             </acesso:controlHiddenItem>  
                            </vivo:tbRowColumn>
                            <vivo:tbRowColumn>
                            <acesso:controlHiddenItem nomeIdentificador="adm_qsat_excluir">
                                <a href="Javascript:removeQuestionario('<bean:write name="questionario" property="idQuestionario" />');"><img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" border="0" alt="Excluir Questionario"></a>
                            </acesso:controlHiddenItem>
                            </vivo:tbRowColumn>
                            <vivo:tbRowColumn>
                            <acesso:controlHiddenItem nomeIdentificador="adm_qsat_gerenciar">
                                <a href="Javascript:editaParam('<bean:write name="questionario" property="idQuestionario" />');"><img src="/FrontOfficeWeb/resources/images/bt_icon_propried.gif" alt="Gerenciar Questionario" border="0"></a>
                            </acesso:controlHiddenItem>
                                <!--html:link page="/editaParam.do"  paramId="idQuestionario" paramName="questionario" paramProperty="idQuestionario"-->
                                    <!--img src="/FrontOfficeWeb/resources/images/bt_icon_propried.gif" alt="Editar Parametros" border="0"-->
                                <!--/html:link-->
                            </vivo:tbRowColumn>
                        </vivo:tbRow>
                    </logic:iterate>
                </vivo:tbRows>
            </vivo:tbTable>

            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
                
            <table width="780" border="0" cellspacing="0" cellpadding="5" style="border:1px solid #D4D7DE; background-color:#F7F9FA; font-size:9px;">
                <tr>
                    <td valign="middle" width="70"><b>&nbsp;Legendas:</b></td>
                    <td>
                        <img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="4">
                    </td>
                    <td>
                        <img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" align="middle"> &nbsp;Alterar Questionário
                    </td>
                    <td>
                        <img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" align="middle"> &nbsp;Excluir Questionário
                    </td>
                    <td>
                        <img src="/FrontOfficeWeb/resources/images/bt_icon_propried.gif" align="middle"> &nbsp;Gerenciar Questionário
                    </td>
                    <td width="250">
                    </td>
                </tr>
            </table>
            
            </logic:greaterThan>
            
            <table width="100%">
                <tr>
                    <td>
                         <img class="button" 
                             onClick="window.location.href='/FrontOfficeWeb/index.jsp'";
                             style="border:none;"
                             id="btVoltar"
                             src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" />
                    </td>
                    <td align="right">
                    <acesso:controlHiddenItem nomeIdentificador="adm_qsat_incluir">
                        <img align="middle" src="/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif" style="border:none;" onClick="incluiQuestionario(); return false"/>
                    </acesso:controlHiddenItem>
                    </td>
                </tr>
            </table>

            <vivo:quadroFlutuante label="Alteração de Questionário" scroll="false" src="" idIframe="ifrmAlteraQuestionario" id="divAlteraQuestionario" spacesLeft="150" height="80" spacesTop="240" url="<%=request.getContextPath()%>" display="none" width="500"></vivo:quadroFlutuante>
            <vivo:quadroFlutuante label="Inclusão de Questionário" scroll="false" src="" idIframe="ifrmIncluiQuestionario" id="divIncluiQuestionario" spacesLeft="150" height="80" spacesTop="240" url="<%=request.getContextPath()%>" display="none" width="500"></vivo:quadroFlutuante>            
            
        <script language="javascript" for="window" event="onload">
        <!--   
            if('<bean:write name="FormQuestionario" property="msgError" />' != "")
            {
                alert('<bean:write name="FormQuestionario" property="msgError" />');
            }
             oculta_div();
        -->
        </script> 
        

        </html:form>
    
        </vivo:sessao>
    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>
