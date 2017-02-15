<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<acesso:controlInitEnv/>
<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"  property="consultaHexaForm"/>    
<html>
<link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
    <script>
        function validaPesquisa(){
            document.forms[0].target="";
            if (document.forms[0].consHexa.value.length == 0) {
                alert('Preencha o campo de Consulta Hexa');
            } else if (document.forms[0].consHexa.value.length != 8) {                
                alert('Campo de Hexa deve ser preenchido com 8 caracteres!');
            } else {
                top.frameApp.mostrar_div();
                document.forms[0].action="/FrontOfficeWeb/cliente/ConsultaHexa/consultaHexa.do";
                document.forms[0].submit();
            }
        }

        function limpa(){
            document.forms[0].consHexa.value = "";
        }

        function voltar() {
            document.forms[0].action="voltar.do";
            document.forms[0].target="ifrmFalse";
            document.forms[0].submit();
        }
    </script>    
<acesso:controlHiddenItem nomeIdentificador="cli_ch_verpagina">
    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
        <vivo:sessao id="consultaHexa" width="100%" height="150" label="HEXA" operacoes="Consulta">            
            <form id="idConsulta" action="/FrontOfficeWeb/cliente/ConsultaHexa/consultaHexa.do">
            <html:hidden name="Form" property="resultadoBatimento"/>
            <table border="0" cellspacing="0" cellpadding="0" style="text-indent:3px;" class="tbl_bgGray" width="100%">
                <tr><td height="5"></td></tr>
                <tr> 
                    <td width="100"><b>&nbsp;HLR:</b></td>
                    <td align="left">
                        <logic:equal name="Form" property="inErro" value="FALSE">
                            <netui:textBox styleClass="textfield" size="20" dataSource="{}" readonly="true" defaultValue="********" password="true"/>
                        </logic:equal>
                        <logic:equal name="Form" property="inErro" value="TRUE">
                            <netui:textBox styleClass="textfield" size="20" dataSource="{}" readonly="true" defaultValue="" password="true"/>
                        </logic:equal>
                    </td>                            
                </tr>
                <tr> 
                    <td><b>&nbsp;Billing:</b></td>
                    <td colspan="3">
                    <logic:equal name="Form" property="inErro" value="FALSE">
                        <netui:textBox styleClass="textfield" size="20" dataSource="{}" readonly="true" defaultValue="********" password="true"/>
                    </logic:equal>
                    <logic:equal name="Form" property="inErro" value="TRUE">
                        <netui:textBox styleClass="textfield" size="20" dataSource="{}" readonly="true" defaultValue="" password="true"/>
                    </logic:equal>
                    </td>                            
                </tr>
                <tr><td colspan="2" height="6"></td></tr>
                <tr> 
                    <td><b>&nbsp;Consulta Hexa:</b></td>
                    <td>
                        <html:password name="Form" onkeyup="checaHexa(this)" property="consHexa" styleClass="textfield" size="20" maxlength="8"/>
                    </td>
                </tr>
                <tr><td height="5"></td></tr>
                <tr>
                    <td colspan="2">
                        <table width="100%">
                            <tr>
                                <td align="right" height="20">
                                    <img style="border:none;" onClick="limpa(); return false" vspace="5" src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_limpar_over.gif"/>
                                    <acesso:controlHiddenItem nomeIdentificador="cli_ch_pesquisar">
                                        <img hspace="10" onClick="validaPesquisa(); return false" style="border:none;" src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" vspace="5" rolloverImage="/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif"/>
                                    </acesso:controlHiddenItem>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>                        
            </form>            
        </vivo:sessao>
        <iframe id="ifrmFalse" name="ifrmFalse" style="visibility:hidden;" width="0px" height="0px"></iframe>
        <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
            top.frameApp.oculta_div();
            alert(document.forms[0].resultadoBatimento.value);    
        </SCRIPT>
</acesso:controlHiddenItem>
</html>