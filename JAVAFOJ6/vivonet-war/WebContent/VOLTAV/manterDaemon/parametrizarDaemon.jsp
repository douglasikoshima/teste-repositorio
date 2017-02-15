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

<bean:define id="FormManterDaemon" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formManterDaemon"/>

<bean:define id="FilaPreVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formManterDaemon.filaPrePagoVO"/>

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Manter Daemon"/>
    <netui-template:setAttribute name="modulo" value="Gestão de Clientes"/>

    <netui-template:section name="headerSection">
    
        <script language="Javascript">    
            function desativar(){
            
                if(!confirm('Deseja realmente DESATIVAR o Daemon?')){
                    return false;
                }
                
                var form = document.forms[0];
                            
                form.target = 'innerBrowserDaemonParametrizacao';
                form.action = 'desativarDaemon.do';
                mostrar_div();
                
                form.submit();        
        
            }
            
            function ativar(){

                if(!confirm('Deseja realmente ATIVAR o Daemon?')){
                    return false;
                }

                var form = document.forms[0];

                form.target = 'innerBrowserDaemonParametrizacao';
                form.action = 'ativarDaemon.do';
                mostrar_div();
                
                form.submit();        
        
            }            
            
            function valida(){
                
                var form = document.forms[0];
                
                if(trim(form.tmpRegistroLeitura.value) == ''){
                
                    alert('Tempo de Leitura entre Registros é um campo obrigatório, favor preencher.');
                    return false;
                    
                }else if(trim(form.qtdRegistrosLidos.value) == ''){
                
                    alert('Quantidade de Registros Lidos é um campo obrigatório, favor preencher.');
                    return false;
                }
            
                return true;
                
            }
        
            function gravar(){

                if(valida()){

                    var form = document.forms[0];
                                
                    form.action = 'gravarParametroDaemon.do';
                    form.target = 'innerBrowserDaemonParametrizacao';
                    mostrar_div();
                    
                    form.submit();        
                
                }
            }
            
        </script>

    </netui-template:section>
    
    <netui-template:section name="bodySection">
    
        <!--APLICAÇÃO-> MENU-->
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
        
         <vivo:quadro id="qdMain" height="475" width="790" label="Parametrização do Daemon do Cadastro Pré-Pago" scroll="no">
         
            <acesso:controlHiddenItem nomeIdentificador="ver_manter_daemon_parametrizacao">
         
            <form name="daemonForm" onsubmit="return false;">
         
                <vivo:quadro width="780" height="210" id="qdrStatusFila" scroll="no" label="Status da Fila">         
                    <table class="tbl_bgGray" width="100%">
                        <tr>
                            <td align="left">
                                <netui:label styleClass="tblEstatica_campoNome" value="Status da Fila: " />
                                
                                <logic:equal name="FilaPreVO" property="nrStatusFila" value="1">
                                    <span id="div_status_fila">ATIVADO</span>
                                </logic:equal>
                                <logic:equal name="FilaPreVO" property="nrStatusFila" value="0">
                                    <span id="div_status_fila">DESATIVADO</span>
                                </logic:equal>

                            </td>
                            <td align="right">
                                <logic:equal name="FilaPreVO" property="nrStatusFila" value="1">
                                
                                    <acesso:controlHiddenItem nomeIdentificador="desativar_manter_daemon_parametrizacao">
                                        <img style="cursor:hand;border:none;" id="btDesativar" onclick="desativar();" src="/FrontOfficeWeb/resources/images/bt_desativar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_desativar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_desativar_over.gif'" />
                                    </acesso:controlHiddenItem>
                                    
                                    <acesso:controlHiddenItem nomeIdentificador="ativar_manter_daemon_parametrizacao">
                                        <img style="cursor:hand;border:none;display:none" id="btAtivar" onclick="ativar();" src="/FrontOfficeWeb/resources/images/bt_ativar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_ativar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_ativar_over.gif'" />
                                    </acesso:controlHiddenItem>
                                    
                                </logic:equal>
                                <logic:equal name="FilaPreVO" property="nrStatusFila" value="0">
                                
                                    <acesso:controlHiddenItem nomeIdentificador="ativar_manter_daemon_parametrizacao">
                                        <img style="cursor:hand;border:none;" id="btAtivar" onclick="ativar();" src="/FrontOfficeWeb/resources/images/bt_ativar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_ativar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_ativar_over.gif'" />
                                    </acesso:controlHiddenItem>
                                    
                                    <acesso:controlHiddenItem nomeIdentificador="desativar_manter_daemon_parametrizacao">
                                        <img style="cursor:hand;border:none;display:none" id="btDesativar" onclick="desativar();" src="/FrontOfficeWeb/resources/images/bt_desativar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_desativar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_desativar_over.gif'" />
                                    </acesso:controlHiddenItem>
                                </logic:equal>
                            </td>
                        </tr>
                    </table>
                </vivo:quadro>

                &nbsp;
                
                <vivo:quadro id="qdMain" height="210" width="780" label="Parametrização do Daemon do Cadastro Pré-Pago" scroll="no">
                    <table class="tbl_bgGray" width="100%" border="0">
                        <tr>
                            <td width="200"><netui:label styleClass="tblEstatica_campoNome" value="Tempo de Leitura entre Registros: " /></td>
                            <td colspan="2">
                                <logic:equal name="FilaPreVO" property="nrStatusFila" value="1">
                                    <html:text name="FormManterDaemon" property="tmpRegistroLeitura" style="width:50px;" disabled="TRUE"/> 
                                </logic:equal>
                                <logic:equal name="FilaPreVO" property="nrStatusFila" value="0">
                                    <html:text name="FormManterDaemon" property="tmpRegistroLeitura" style="width:50px;"/> 
                                </logic:equal>
                            </td>
                        </tr>
                        <tr>
                            <td><netui:label styleClass="tblEstatica_campoNome" value="Quantidade de Registros Lidos: " /></td>
                            <td>
                            <logic:equal name="FilaPreVO" property="nrStatusFila" value="1">
                                <html:text name="FormManterDaemon" property="qtdRegistrosLidos" style="width:50px;" disabled="TRUE"/>
                            </logic:equal>

                            <logic:equal name="FilaPreVO" property="nrStatusFila" value="0">
                                <html:text name="FormManterDaemon" property="qtdRegistrosLidos" style="width:50px;"/>
                            </logic:equal>
                            
                            </td>
                            <td align="right">
                            <logic:equal name="FilaPreVO" property="nrStatusFila" value="0">
                            
                                <acesso:controlHiddenItem nomeIdentificador="gravar_manter_daemon_parametrizacao">
                                    <img style="cursor:hand;border:none" id="btGravar" onclick="gravar();" src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_gravar_over.gif'" />&nbsp;
                                </acesso:controlHiddenItem>
                            
                            </logic:equal>
                            <logic:equal name="FilaPreVO" property="nrStatusFila" value="1">
                            
                                <acesso:controlHiddenItem nomeIdentificador="gravar_manter_daemon_parametrizacao">
                                    <img style="cursor:hand;border:none;visibility:hidden" id="btGravar" onclick="gravar();" src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_gravar_over.gif'" />&nbsp;
                                </acesso:controlHiddenItem>
                            
                            </logic:equal>                            
                            </td>
                        </tr>
                    </table>
                </vivo:quadro>

                 <iframe scrolling="no"  name="innerBrowserDaemonParametrizacao" height="0px" width="0px"></iframe>              

            </form>
        </acesso:controlHiddenItem>
        
        </vivo:quadro>

        <vivo:alert atributo="msgStatus" scope="request"/>

    </netui-template:section>
</netui-template:template>
                