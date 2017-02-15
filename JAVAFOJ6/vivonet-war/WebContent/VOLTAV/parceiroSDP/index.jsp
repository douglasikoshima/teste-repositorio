<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">

<netui-template:setAttribute name="title" value="Manutenção de Campanhas"/>
<netui-template:setAttribute name="modulo" value="VOL/TAV"/>

<netui-template:section name="headerSection">


<script type="text/javascript" language="javascript">

        function incluirParceiro() {
            document.forms[0].action = 'novoParceiro.do';
            document.forms[0].submit();        
        }
                 
        function alterarParceiro(idParceiro) {
            document.forms[0].action = 'alteraParceiro.do?idParceiro='+idParceiro;
            document.forms[0].submit();
        }
        
        function pesquisarParceiro(){
            document.forms[0].action = 'pesquisa.do';
            document.forms[0].submit();
        }

    </script>
    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/vivoval.js" ></script>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="parceiroSDPForm" type="VOLTAV.parceiroSDP.ParceiroSDPController.ParceiroSDPForm"/>
<bean:define id="listaParceiros" name="Form" property="parceiros" />
    
</netui-template:section>

<netui-template:section name="bodySection">

<jsp:include page="/resources/menus/MenuPrincipal.jsp" />
<div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>

    <vivo:sessao id="qdMain" height="468" width="790" label="Parceiro" operacoes="Manutenção SDP" scroll="no">
    
        <form action="begin.do" enctype="multipart/form-data" method="post" onSubmit="return false">
            <table cellpadding="0" cellspacing="1" style="margin:10px;">
                <tr>
                    <td colspan="1" valign="top" height="30">
                        <strong>Parceiro:</strong>
                    </td>            
                    <td>
                        <html:text name="Form" property="nomePesquisar" styleId="nomePesquisar" maxlength="50" style="width:550px;margin-left:3px;"/>
                    </td>        
                    <td>
                        <img id="btIncluir"
                                           value="Incluir"
                                           src="/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif"
                                           rolloverImage="/FrontOfficeWeb/resources/images/bt_incluir_over.gif"
										   onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_incluir_over.gif'"
                                           style="border:none;margin-left:4px;cursor:hand;"
                                           onMouseUp="incluirParceiro();return false"
										   onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif'"
										   />
					</td>
					<td>
                        <img id="btPesquisar"
                                           value="Pesquisar"
                                           src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif"
                                           rolloverImage="/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif"
										   onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif'"
                                           style="border:none;margin-left:4px;cursor:hand;"
                                           onMouseUp="pesquisarParceiro()" 
										   onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif'"										   
										   />             
										   
                    </td>
                </tr>
                <tr>
                    <td valign="top" colspan="6" style="padding-top:15px;">
                        <vivo:tbTable selectable="true" layoutWidth="733" layoutHeight="370" tableWidth="733" styleId="tbCampanha" sortable="true">
                            <vivo:tbHeader>
                                <vivo:tbHeaderColumn align="left" width="95%" type="string">Descrição</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="left" width="5%" type="string">&nbsp;</vivo:tbHeaderColumn>
                            </vivo:tbHeader>
                            <vivo:tbRows>
                                <logic:iterate name="listaParceiros" id="parceiro" indexId="ctr">
                                    <vivo:tbRow key="campanha" idClass="classListaCampanhas">
                                        <vivo:tbRowColumn>
                                            <span title="<bean:write name="parceiro" property="nmparceiro"/>">
                                                <bean:write name="parceiro" property="nmparceiro"/>
                                            </span>                                            
                                        </vivo:tbRowColumn>
                                        <vivo:tbRowColumn>
                                            <img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" style="cursor:pointer;" onmouseup="alterarParceiro(<bean:write name="parceiro" property="iditemmenu" format="#" />)" />
                                        </vivo:tbRowColumn>
                                    </vivo:tbRow>
                                </logic:iterate>
                            </vivo:tbRows>
                        </vivo:tbTable>
                    </td>
                </tr>                
            </table>
        </form>
        
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