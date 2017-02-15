<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>

<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<bean:define id="Form"                  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="permissoesForm"/>
<bean:define id="CanaisPreferidos"      name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="permissoesForm.formCanaisExistentesVO"/>
<bean:define id="CanaisEscolhidos"      name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="permissoesForm.formCanaisRelacionadosVO"/>
<bean:define id="MeioContato"           name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="permissoesForm.formMeioContatoVO"/>
<bean:define id="MeioContatoRecusado"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="permissoesForm.formMeioContatoRecusadoVO"/>

<html> 
<head>
    <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
    <script language="javascript">
        function moveFixo(objOrigem, objDestino){
            erro = false;
            for (i=0; i<objOrigem.options.length; i++){
                if (objOrigem.options[i].selected){
                    for (j=0; j<objDestino.options.length; j++){
                        if (objOrigem.options[i].text == objDestino.options[j].text){
                            //alert("Item já selecionado!")
                            erro = true;
                        }
                    }
                    !erro?objDestino.options[objDestino.options.length] = new Option(objOrigem.options[i].text,objOrigem.options[i].value):0;
                }
            }
        }
    
        function deleteFixo(obj){
            for (i=0; i<obj.options.length; i++){
                if (obj.options[i].selected){
                    obj.options[i] = null;
                }
            }
        }
        
        function salvar() {
            
            for ( i = 0; i < document.forms[0].formCanaisRelacionados.options.length; i++ )
                document.forms[0].formCanaisRelacionados.options[i].selected = true;
              
            for ( i = 0; i < document.forms[0].formMeioContatoRecusado.options.length; i++ )
                document.forms[0].formMeioContatoRecusado.options[i].selected = true;
    
              
              document.forms[0].action = "salvar.do";  
              document.forms[0].method = "POST";
              document.forms[0].submit();  
        }
    </script>
</head>
<body rightmargin="0" leftmargin="0" topmargin="0">
<acesso:controlHiddenItem nomeIdentificador="cli_prm_verpagina">
    <form action="loadPermissoes.do" id="fPermissoes" name="permissoesForm" method="post">
        <table width="760" cellpadding="10" cellspacing="10">
            <tr>
                <td>
                
                    <vivo:sessao height="355" id="permissoes" width="740" scroll="no" label="Cliente" operacoes="Permissões">
                    
                        <table width="730" border="0" cellspacing="1" cellpadding="0">
                            <tr><td height="10"></td></tr>
                            <tr>
                                <td valign="middle" width="220">&nbsp;&nbsp;Qual é o canal preferido do cliente?</td>
                                <td width="220" valign="top"> 
                                    <html:select name="Form" property="formCanaisExistentes"  multiple="true" style="width:220px;height:149px;" size="5" ondblclick="moveFixo(document.forms[0].formCanaisExistentes, document.forms[0].formCanaisRelacionados);">
                                        <html:options collection="CanaisPreferidos" property="idCanal" labelProperty="nmCanal" /> 
                                    </html:select>
                                </td>
                                <td width="70" align="center">
                                    <table width="100%" border="0" cellspacing="1" cellpadding="1">
                                            <tr>
                                            <td width="5%" align="center">
                                                <img id="btRightSimp"
                                                	 onmouseup="moveFixo(document.forms[0].formCanaisExistentes, document.forms[0].formCanaisRelacionados);"
                                                	 src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif"
                                                	 class="button" />
                                                <br/><br/>
                                                <img id="btLeftSimp"
                                                	 onmouseup="deleteFixo(document.forms[0].formCanaisRelacionados, document.forms[0].formCanaisExistentes);"
                                                	 src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif"
                                                	 rolloverImage="/FrontOfficeWeb/resources/images/bt_leftaln_over.gif"
                                                	 class="button" />
                                            </td>
                                            </tr>
                                    </table>
                                </td>
                                <td width="220" valign="top">
                                    <html:select name="Form" property="formCanaisRelacionados"  multiple="true" style="width:220px;height:149px;" size="5" ondblclick="deleteFixo(document.forms[0].formCanaisRelacionados, document.forms[0].formCanaisExistentes);">
                                        <html:options collection="CanaisEscolhidos" property="idCanal" labelProperty="nmCanal" /> 
                                    </html:select>
                                </td>
                            </tr>
                            <tr><td height="10"></td></tr>
                            <tr>
                                <td>&nbsp;&nbsp;O que o cliente não gosta de receber?</td>
                                <td> 
                                    <html:select name="Form" property="formMeioContato"  multiple="true" style="width:220px;height:149px;" size="5" ondblclick="moveFixo(document.forms[0].formMeioContato, document.forms[0].formMeioContatoRecusado);">
                                        <html:options collection="MeioContato" property="idMeioContato" labelProperty="dsMeioContato" /> 
                                    </html:select>
                                </td>
                                <td align="center">
                                    <table width="100%" border="0" cellspacing="1" cellpadding="1">
                                            <tr>
                                            <td width="5%" align="center">
                                                <img id="btRightMeioContato"
                                                	 onmouseup="moveFixo(document.forms[0].formMeioContato, document.forms[0].formMeioContatoRecusado);"
                                                	 src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif"
                                                	 class="button" />
                                                <br/><br/>
                                                <img id="btLeftMeioContatoRecusado"
                                                	 onmouseup="deleteFixo(document.forms[0].formMeioContatoRecusado, document.forms[0].formMeioContato);"
                                                	 src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif"
                                                	 rolloverImage="/FrontOfficeWeb/resources/images/bt_leftaln_over.gif"
                                                	 class="button" />
                                            </td>
                                            </tr>
                                    </table>
                                </td>
                                <td> 
                                    <html:select name="Form" property="formMeioContatoRecusado"  multiple="true" style="width:220px;height:149px;" size="5" ondblclick="deleteFixo(document.forms[0].formMeioContatoRecusado, document.forms[0].formMeioContato);">
                                        <html:options collection="MeioContatoRecusado" property="idMeioContato" labelProperty="dsMeioContato" /> 
                                    </html:select>
                                </td>
                            </tr>
                        </table>
                        
                        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
                        
                        <table width="730">
                            <tr>
                                <td align="left">
                                    <img onmouseup="document.forms[0].action='DetalheCliente.do';document.forms[0].submit()"
                                    	 class="button"
                                    	 src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" />
                                </td>
                                <td align="right">
                                    <img onClick="document.forms[0].method = 'POST';document.forms[0].submit; return false" src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_limpar_over.gif" style="border:none;"/>
                                    <acesso:controlHiddenItem nomeIdentificador="cli_prm_gravar">
                                    <img onClick="salvar(); return false"  src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_salvar_over.gif" style="border:none;"/>
                                    </acesso:controlHiddenItem>
                                </td>
                            </tr>
                        </table>
                    
                    </vivo:sessao>
                    
                </td>
            </tr>
        </table>                         
    </form>    
</acesso:controlHiddenItem>
</body>
</html>