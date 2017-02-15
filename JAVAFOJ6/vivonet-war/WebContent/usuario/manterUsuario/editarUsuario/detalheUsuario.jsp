<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<bean:define id="FormUser"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaUsuariosForm"/>
<!-- utilizado para percorrer cada item do array de usuarios -->
<bean:define id="usuarioVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaUsuariosForm.usuarioVO"/>
<bean:define id="docsUsuario" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaUsuariosForm.docsUsuario.documentoSimpVOArray"/>

<netui-template:template templatePage="/resources/jsp/templateAdmSistClean.jsp">
    <netui-template:setAttribute name="title" value="Manter Calendário"/>
    <netui-template:setAttribute name="modulo" value="Administração de Sistemas"/>

    <netui-template:section name="headerSection">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
    </netui-template:section>

    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="usu_dtla_verpagina">
    
    <script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/calendar.js"></script>
    <script>
        
        function mostraJanelaDetalhe(){
            var x = document.getElementById("detalhe");
            x.style.visibility = 'visible';
            var b = document.getElementById("bg");
            b.style.visibility = 'visible';
        }
        
        
        function abreJanela(){
            mostraJanelaDetalhe()
            divFrame = document.getElementById('frameDetalhe');
            divFrame.src = 'criarEditarItemContato.jsp';
        }

        function fechaJanela(){
            var x = document.getElementById("detalhe");
            x.style.visibility = 'hidden';
            divFrame = document.getElementById('frameDetalhe');
            divFrame.src = 'nada.html';
            var b = document.getElementById("bg");
            b.style.visibility = 'hidden';
        }
    
    </script>

        <!-- Menu de Administração de Sistemas <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div> -->        

        <vivo:quadro id="dadosPessoa" height="42" width="710" label="Dados de Pessoa">
            <table width="100%" cellspacing="1" cellpadding="1" border="0">
                <tr>
                    <td class="tblEstatica_campoNome" width="10%">
                        <netui:label value="Nome: " styleClass="tblEstatica_campoNome" style="text-indent:4px;"/>
                    </td>
                    <td class="tblEstatica_campoValor" colspan="2">
                        <bean:write name="usuarioVO" property="nome"/>
                    </td>
                    <!-- td  class="tblEstatica_campoNome">
                        < netui:label value="Sobrenome: " styleClass="tblEstatica_campoNome" style="text-indent:4px;"/>
                    </td>
                    <td class="tblEstatica_campoValor">
                        < bean:write name="usuarioVO" property="sobrenome"/>
                    </td -->
                </tr>
                <tr>
                    <td class="tblEstatica_campoNome">
                        <netui:label value="UF: " styleClass="tblEstatica_campoNome" style="text-indent:4px;"/>
                    </td>
                    <td class="tblEstatica_campoValor" colspan="3">
                        <bean:write name="usuarioVO" property="nmUF"/>
                    </td>
                
                </tr>
            </table>
        </vivo:quadro>
        <vivo:quadro id="dadosUsuario" height="140" width="710" label="Dados de Usuário">
            <table width="100%" cellspacing="1" cellpadding="1">
                <tr>
                    <td class="tblEstatica_campoNome">
                        <netui:label value="E-mail: " styleClass="tblEstatica_campoNome" style="text-indent:4px;"/>
                    </td>
                    <td class="tblEstatica_campoValor">
                        <bean:write name="usuarioVO" property="email"/>
                    </td>
                    <td class="tblEstatica_campoNome">
                        <netui:label value="Login CTI: " styleClass="tblEstatica_campoNome" style="text-indent:5px;"/>
                    </td>
                    <td class="tblEstatica_campoValor">
                        <bean:write name="usuarioVO" property="loginCti"/>
                    </td>
                </tr>
                <tr>
                    <td class="tblEstatica_campoNome">
                        <netui:label value="Login FrontOffice: " styleClass="tblEstatica_campoNome" style="text-indent:4px;"/>
                    </td>
                    <td class="tblEstatica_campoValor">
                        <bean:write name="usuarioVO" property="login"/>
                    </td>
                    <td class="tblEstatica_campoNome">
                        <netui:label value="Login Superior Imediato: " styleClass="tblEstatica_campoNome" style="text-indent:4px;"/>
                    </td>
                    <td class="tblEstatica_campoValor">
                        <bean:write name="usuarioVO" property="loginChefe"/>
                    </td>
                </tr>
                <tr>
                    <td class="tblEstatica_campoNome">
                        <netui:label value="Telefone: " styleClass="tblEstatica_campoNome" style="text-indent:4px;"/>
                    </td>
                    <td class="tblEstatica_campoValor">
                    <logic:notEqual name="usuarioVO" property="dsDDD" value="">
                        <logic:notEqual name="usuarioVO" property="dsDDD" value="0">
                            (<bean:write name="usuarioVO" property="dsDDD"/>) <bean:write name="usuarioVO" property="dsTelefone"/>
                        </logic:notEqual>
                    </logic:notEqual>
                    </td>
                    <td class="tblEstatica_campoNome">
                        <netui:label value="Consultor de Relacionamento: " styleClass="tblEstatica_campoNome" style="text-indent:4px;"/>
                    </td>
                    <td class="tblEstatica_campoValor" colspan="3">
                        <logic:equal name="usuarioVO" property="inConsultor" value="0">
                            Não
                        </logic:equal>
                        <logic:equal name="usuarioVO" property="inConsultor" value="1">
                            Sim
                        </logic:equal>
                    </td>
    
                </tr>
                <tr>
                    <td class="tblEstatica_campoNome">
                        <netui:label value="Status:" styleClass="tblEstatica_campoNome" style="text-indent:4px;"/>
                    </td>
                    <td class="tblEstatica_campoValor" colspan="3">
                        <bean:write name="usuarioVO" property="descricaoStatusAtual"/>
                    </td>
                </tr>
                
                <logic:notEqual name="FormUser" property="dsNivel" value="">
                    <tr>
                        <td class="tblEstatica_campoNome">
                            <netui:label value="Nível:" styleClass="tblEstatica_campoNome" style="text-indent:4px;"/>
                        </td>
                        <td class="tblEstatica_campoValor">
                            <bean:write name="FormUser" property="dsNivel"/>
                        </td>
                        <td class="tblEstatica_campoNome">
                            <netui:label value="Cargo: " styleClass="tblEstatica_campoNome" style="text-indent:4px;"/>
                        </td>
                        <td class="tblEstatica_campoValor">
                            <bean:write name="FormUser" property="dsCargo"/>
                        </td>                            
                    </tr>
                    <tr>
                        <td class="tblEstatica_campoNome">
                            <netui:label value="Organização:" styleClass="tblEstatica_campoNome" style="text-indent:4px;"/>
                        </td>
                        <td class="tblEstatica_campoValor">
                            <bean:write name="FormUser" property="dsOrganizacao"/>
                        </td>
                        <td class="tblEstatica_campoNome">
                            <netui:label value="Departamento: " styleClass="tblEstatica_campoNome" style="text-indent:4px;"/>
                        </td>
                        <td class="tblEstatica_campoValor">
                            <bean:write name="FormUser" property="dsUnidade"/>
                        </td>                            
                    </tr>
                </logic:notEqual>
                <tr>
                        <td>                                    
                            <netui:label value="Data Inclusão: " styleClass="tblEstatica_campoNome" style="text-indent:4px;"/>
                        </td>
                        <td style="text-align:left;" align="left" class="tblEstatica_campoValor">
                            <bean:write name="usuarioVO" property="dtInclusao"/>
                        </td>
                        <logic:notEqual name="usuarioVO" property="dtExclusao" value="">
                            <td>                                    
                                <netui:label value="Data Status: " styleClass="tblEstatica_campoNome" style="text-indent:5px;"/>
                            </td>
                            <td style="text-align:left;" align="left" class="tblEstatica_campoValor">
                                <bean:write name="usuarioVO" property="dtExclusao"/>
                            </td>
                        </logic:notEqual>
    
                </tr>
            </table>
        </vivo:quadro>
        <vivo:quadro id="dadosDocumento" height="92" width="710" label="Dados de Documento">
        <table>
            <tr>
                    <td colspan="4" class="tblEstatica_campoNome"><i>Dados Documento</i></td>
            </tr>            
            <logic:iterate name="FormUser" property="docsUsuario.documentoSimpVOArray" id="documento">
            <tr>
                <td class="tblEstatica_campoNome">
                    <netui:label value="Documento: " styleClass="tblEstatica_campoNome" style="text-indent:4px;"/>
                </td>
                <td class="tblEstatica_campoValor">
                    <bean:write name="documento" property="tipoDocumentoVO.sgTipoDocumento"/> - <%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%><bean:write name="documento" property="dsDocumento"/><%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
                </td>
                <td class="tblEstatica_campoNome">
                    <netui:label value="País: " styleClass="tblEstatica_campoNome" style="text-indent:4px;"/>
                </td>
                <td class="tblEstatica_campoValor">
                    <bean:write name="documento" property="paisVO.nmPais"/>
                </td>
            </tr>
            </logic:iterate>

            <!--tr>
                <td>
                    <table width="100%" border="0" cellpadding="1" cellspacing="1">
                        <tr>
                            <td width="16%" class="tblEstatica_campoNome">
                                < netui:label value="Motivo: " styleClass="tblEstatica_campoNome" style="text-indent:4px;"/>
                            </td>
                            <td width="28%" align="left" class="tblEstatica_campoValor">
                                < bean:write name="usuarioVO" property="dsMotivo"/>
                            </td>
                            <td width="7%">                                    
                                < netui:label value="De: " styleClass="tblEstatica_campoNome" style="text-indent:6px;"/>
                            </td>
                            <td width="18%" style="text-align:left;" align="left" class="tblEstatica_campoValor">
                                < bean:write name="usuarioVO" property="dtInicio"/>
                            </td>
                            <td width="7%">                                    
                                < netui:label value="Até: " styleClass="tblEstatica_campoNome"/>
                            </td>
                            <td width="24%" style="text-align:left;" align="left" class="tblEstatica_campoValor">
                                < bean:write name="usuarioVO" property="dtRetorno"/>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr-->
        </table>
        </vivo:quadro>
    
        <script language="javascript" for="window" event="onLoad">
           parent.oculta_div();
        </script>
    
    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>
