<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%> 

<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">

<netui-template:setAttribute name="title" value="Ação Incentivo"/>
<netui-template:setAttribute name="modulo" value="Administração"/>

<netui-template:section name="headerSection">

<script type="text/javascript" language="javascript">

        function incluirAcaoIncentivo() {
            document.forms[0].action = 'incluirAcaoIncentivo.do';
            document.forms[0].submit();        
        }
                 
        function alterarAcaoIncentivo(id) {
            document.forms[0].action = 'alteraAcaoIncentivo.do?id='+id;
            document.forms[0].submit();
        }
        
        function excluirAcaoIncentivo(id) {
            document.forms[0].action = 'excluirAcaoIncentivo.do?id='+id;
            document.forms[0].submit();
        }        
        
        function pesquisarAcaoIncentivo(){
            document.forms[0].action = 'pesquisarAcaoIncentivo.do';
            document.forms[0].submit();
        }

    </script>
    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/vivoval.js" ></script>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="acaoIncentivoForm" type="admsistemas.acaoIncentivo.AcaoIncentivoController.AcaoIncentivoForm"/>
<bean:define id="listaAcoes" name="Form" property="acoes" />
    
</netui-template:section>

<netui-template:section name="bodySection">

<jsp:include page="/resources/menus/MenuPrincipal.jsp" />
<div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>

    <vivo:sessao id="qdMain" height="468" width="790" label="Ações de Incentivo" operacoes="Consultar" scroll="no">
    
        <form name="begin" id="begin" action="begin.do" enctype="multipart/form-data" method="post" onSubmit="return false">
            <table cellpadding="0" cellspacing="1" style="margin:10px;">
                <tr>
                    <td  valign="top" height="30">
                        <strong>Código:</strong>
                    </td>            
                    <td>                    
                        <html:text name="Form" property="pesquisaCodigoAcao" styleId="pesquisaCodigoAcao" maxlength="10" style="width:100px;margin-left:3px;"/>
                    </td>        
                    <td  valign="top" height="30">
                        <strong>Situação:</strong>
                    </td>            
                    <td>                    
                        <html:select name="Form" property="pesquisaSituacao" style="width:80px">
                            <html:option value="0">Todas</html:option>
                            <html:option value="1">Em Aberto</html:option>
                            <html:option value="2">Concluidas</html:option>
                            <html:option value="3">Futuras</html:option>
                        </html:select>
                    </td>                        
                    <td>
             
                    </td>
                </tr>            
                <tr>
                    <td  valign="top" height="30">
                        <strong>Descrição:</strong>
                    </td>            
                    <td>                    
                        <html:text name="Form" property="pesquisaDescricao" styleId="pesquisaDescricao" maxlength="20" style="width:200px;margin-left:3px;"/>
                    </td>        
                    <td  valign="top" height="30">
                        <strong>Ativo:</strong>
                    </td>            
                    <td>                                        
                        <html:select name="Form" property="pesquisaAtivo" style="width:80px">
                            <html:option value="2">Todos</html:option>
                            <html:option value="1">Sim</html:option>
                            <html:option value="0">Não</html:option>
                        </html:select>                                                
                    </td>                        
                    <td>
                        <img id="btIncluir"
                                           value="Incluir"
                                           src="/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif"
                                           rolloverImage="/FrontOfficeWeb/resources/images/bt_incluir_over.gif"
                                           style="border:none;margin-left:4px;"
                                           onMouseUp="incluirAcaoIncentivo();return false" />
                                           
                        <img id="btPesquisar"
                                           value="Pesquisar"
                                           src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif"
                                           rolloverImage="/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif"
                                           style="border:none;margin-left:4px;"
                                           onMouseUp="pesquisarAcaoIncentivo()" />                    
                    </td>
                </tr>
                <tr>
                    <td valign="top" colspan="6" style="padding-top:15px;">
                        <vivo:tbTable selectable="true" layoutWidth="733" layoutHeight="290" tableWidth="733" styleId="tbCampanha" sortable="true">
                            <vivo:tbHeader>
                                <vivo:tbHeaderColumn align="left" width="10%" type="string">Código</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="left" width="30%" type="string">Descrição</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="left" width="20%" type="string">Data Hora Início</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="left" width="20%" type="string">Data Hora Término</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="left" width="10%" type="string">Ativo?</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="left" width="20%" type="string">Ações</vivo:tbHeaderColumn>
                            </vivo:tbHeader>
                            <vivo:tbRows>
                                <logic:iterate name="listaAcoes" id="acao" indexId="ctr">
                                    <vivo:tbRow key="campanha" idClass="classListaCampanhas">
                                        <vivo:tbRowColumn>
                                            <center>
                                                <bean:write name="acao" property="cdacaoincentivo"/>
                                            </center>
                                        </vivo:tbRowColumn>
                                        <vivo:tbRowColumn>
                                            <bean:write name="acao" property="dsacaoincentivo"/>                            
                                        </vivo:tbRowColumn>                                        
                                        <vivo:tbRowColumn>
                                            <center>
                                                <bean:write name="acao" property="dthorainicio"/>                            
                                            </center>
                                        </vivo:tbRowColumn>                                           
                                        <vivo:tbRowColumn>
                                            <center>
                                                <bean:write name="acao" property="dthoratermino"/>                            
                                            </center>
                                        </vivo:tbRowColumn>                                                                       
                                        <vivo:tbRowColumn>
                                            <center>
                                                <logic:equal value="1" name="acao" property="inativo">Sim</logic:equal>                                            
                                                <logic:equal value="0" name="acao" property="inativo">Não</logic:equal>                                            
                                            </center>
                                        </vivo:tbRowColumn>                                              
                                        <vivo:tbRowColumn>
                                            <img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" style="cursor:pointer;" onmouseup="alterarAcaoIncentivo(<bean:write name="acao" property="cdacaoincentivo" format="#" />)" />
                                            <img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" style="cursor:pointer;" onmouseup="excluirAcaoIncentivo(<bean:write name="acao" property="cdacaoincentivo" format="#" />)" />
                                        </vivo:tbRowColumn>
                                    </vivo:tbRow>
                                </logic:iterate>
                            </vivo:tbRows>
                        </vivo:tbTable>
                    </td>
                </tr>                             
            </table>
        </form>
        <table width="770" height="30" cellpadding="0" cellspacing="0" align="center" class="tbl_bgGray">
            <tr>
                <td width="100">&nbsp;&nbsp;Legendas:</td>
                <td width="150"><img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" align="middle">&nbsp;&nbsp;Alterar Ação</td>
                <td width="150"><img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" align="middle">&nbsp;&nbsp;Excluir Ação</td>
            </tr>
        </table>         
        
        <iframe name="hiddenFrame" id="hiddenFrame" src="" width="0" height="0" marginheight="0" marginwidth="0" frameborder="0" style="display:none;"></iframe>
        

    
        
    </vivo:sessao>
    
    <vivo:alert atributo="msgErro" scope="request"/>
    <vivo:alert atributo="msgStatus" scope="request"/>
    
    <vivo:quadroFlutuante id="divInclusaoAlteracao"
                          idIframe="iframeInclusaoAlteracao"
                          width="775"
                          height="480"
                          spacesTop="65"
                          spacesLeft="15"
                          display="none"
                          url="<%=request.getContextPath()%>"
                          label="Cadastro/Alteração de Campanha Promocional"
                          onclick="return false;"/>    

</netui-template:section>
</netui-template:template>