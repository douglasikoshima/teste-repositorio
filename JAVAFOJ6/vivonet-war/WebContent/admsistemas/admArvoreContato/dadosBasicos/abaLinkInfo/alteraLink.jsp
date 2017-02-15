<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
<bean:define id="FormLink"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formLink"/>

<netui-template:template templatePage="/resources/jsp/templateUsuarioClean.jsp">
    <netui-template:section name="headerSection">
    <script language="Javascript">
        function valida(){
            if(trim(document.forms[0].nmLink.value) == ""){
                alert('Preencha o campo Link.');
                return false;
            }
            return true;
        }
    
        function salvar(){
            if(valida()){
                document.forms[0].action='salvaEditaLink.do';
                document.forms[0].target = "_parent";
                parent.mostrar_div();
                document.forms[0].submit();
            }
        }
    </script>
    <script language="javascript" for="window" event="onload">
    <!--   
        if('<bean:write name="FormLink" property="msgError" />' != ""){
            alert('<bean:write name="FormLink" property="msgError" />');
        }
    -->
    </script> 
    </netui-template:section>
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="adm_altlin_verpagina">
        <form action="salvaEditaLink" id="salvaEditaLink" name="salvaEditaLink" method="post">
            <html:hidden name="FormLink" property="idUFOperadora"/>
            <html:hidden name="FormLink" property="idContato"/>
            <html:hidden name="FormLink" property="idTipoRelacionamento"/>
            <html:hidden name="FormLink" property="idTipoLinha"/>
            <html:hidden name="FormLink" property="nmTipoLinha"/>
            <html:hidden name="FormLink" property="nmUfOperadora"/>
            <html:hidden name="FormLink" property="nmTipoRelacionamento"/>
            <html:hidden name="FormLink" property="idContatoInformacao"/>
            <table><tr><td height="4"></td></tr></table>
            <table width="100%" border="0" cellspacing="1" cellpadding="1">
                <tr>
                    <td>
                    <table width="97%" border="0" cellspacing="1" cellpadding="1" align="center">
                        <tr><td height="4"></td></tr>
                        <tr>
                            <td align="center">
                                <table width="100%" border="0" cellspacing="2" cellpadding="2" align="center">
                                    <tr>
                                        <td width="30%">
                                            <netui:label value="Operadora : " styleClass="tblEstatica_campoNome"/>
                                        </td>
                                        <td width="70%" align="left">
                                                &nbsp;<bean:write name="FormLink" property="nmUfOperadora"/>

                                        </td>
                                    </tr>
                                     
                                    <tr>
                                        <td width="30%">
                                            <netui:label value="Tipo de Linha : " styleClass="tblEstatica_campoNome"/>
                                        </td>
                                        <td width="70%" align="left">
                                            &nbsp;<bean:write name="FormLink" property="nmTipoLinha"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="30%">
                                            <netui:label value="Tipo de Cliente: " styleClass="tblEstatica_campoNome"/>
                                        </td>
                                        <td width="70%" align="left">
                                            &nbsp;<bean:write name="FormLink" property="nmTipoRelacionamento"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="30%">
                                            <netui:label value="Link : " styleClass="tblEstatica_campoNome"/>
                                        </td>
                                        <td width="70%" align="left"><html:text name="FormLink" property="nmLink" size="50" maxlength="254"/></td>
                                    </tr>

              
                                    <tr><td height="4"></td></tr>                                   
                                    <tr>
                                        <td colspan="2" align="right"> 
                                        <acesso:controlHiddenItem nomeIdentificador="adm_altlin_gravar">                    
                                            <img id="btSalvar1" onclick="salvar(); return false" src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_salvar_over.gif'" style="border:none;cursor:hand;"/>
                                        </acesso:controlHiddenItem>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr><td height="5"></td></tr>
                    </table>
                    </td>
                </tr>
            </table>
        </form>
        <script language="javascript" for="window" event="onload">
            <!--   
                parent.oculta_div();
                document.body.style.backgroundColor = '#ededed';
            -->
        </script> 
    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>