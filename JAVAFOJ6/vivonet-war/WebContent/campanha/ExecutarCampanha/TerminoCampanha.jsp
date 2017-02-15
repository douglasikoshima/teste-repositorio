<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="obtemCampanhaListaActionForm"/>
<bean:define id="aStatusDisp" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="obtemCampanhaListaActionForm.statusDisp" />
<bean:define id="aMotivoDisp" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="obtemCampanhaListaActionForm.motivoDisp" />
<bean:define id="aSubMotivoDisp" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="obtemCampanhaListaActionForm.subMotivoDisp" />
<html>
    <head>
        <title></title>
        <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">
        <script language="javascript" src="/FrontOfficeWeb/resources/scripts/frameweb.js"></script>
        <script language="javascript" src="/FrontOfficeWeb/resources/scripts/executarCampanha.js" charset="ISO-8859-1"></script>                
        <SCRIPT FOR=window EVENT=onload>
            top.frameApp.oculta_div();
        </script>         
    </head>
    <acesso:controlHiddenItem nomeIdentificador="cam_terca_verpagina">
    <form action="obtemCampanhaListaAction.do" method="post" name="obtemCampanhaListaActionForm">
    
    <vivo:quadro id="qdPerg" width="100%" height="100%" label="Status Atendimento">
    <table border="0"  width="100%" cellpadding="1" cellspacing="10" class="tbl_bgGray" height="100%">
    <tr>
        <td valign="top">
            <table border="0"  width="100%" cellpadding="1" cellspacing="10" class="tbl_bgGray" >
            <tr> 
                <td colspan="2" valign="top">
                    <table width="100%" border="0" cellspacing="1" cellpadding="2">
                    <tr class="corpo"> 
                        <td class="tblEstatica_campoNome" align="right"><netui:label value="Status:"/> </td>
                        <td align="left">
                        <html:select name="Form" property="statusDispSelecionado" size="1" style="width=300px;height=10px" onchange="terminoCampanha_consultaIFrameMotivo();">
                            <html:option value=""></html:option>
                            <html:options collection="aStatusDisp" property="codigo" labelProperty="descricao"/>
                        </html:select> 
                        </td>
                    </tr>
                    <tr class="corpo"> 
                        <td class="tblEstatica_campoNome" align="right"><netui:label value="Motivo:"/> </td>
                        <td align="left">
                        <html:select name="Form" property="motivoDispSelecionado" size="1" style="width=300px;height=10px" onchange="terminoCampanha_consultaIFrameSubMotivo();">
                            <html:option value=""></html:option>
                            <html:options collection="aMotivoDisp" property="codigo" labelProperty="descricao"/>
                        </html:select>  
                        </td>
                    </tr>
                    <tr class="corpo"> 
                        <td class="tblEstatica_campoNome" align="right"><netui:label value="Sub Motivo:"/> </td>
                        <td align="left">
                        <html:select name="Form" property="subMotivoDispSelecionado" size="1" style="width=300px;height=10px">
                            <html:options collection="aSubMotivoDisp" property="codigo" labelProperty="descricao"/>
                        </html:select>
                        </td>
                    </tr>
                    <tr> 
                        <td colspan="2" valign="top" align="right"> 
                            <table  border="0" cellspacing="5" cellpadding="0">
                            <tr> 
                                    <td colspan="2">&nbsp;</td>
                            </tr>	
                            <tr> 
                                    <td align="right" >
                                    <acesso:controlHiddenItem nomeIdentificador="cam_terca_agen">
                                        <div style="display:none;" id="btnCamTercaAgen">
                                            <img src="/FrontOfficeWeb/resources/images/bt_agendamento_nrml.gif"
                                            	 border="0"
                                            	 style="border:none"
                                            	 class="button"
                                            	 onmouseup="terminoCampanha_agendar();"
                                            	 formSubmit="false" />
                                        </div>
                                    </acesso:controlHiddenItem>
                                    </td>	 
                                    <td align="right">
                                    <acesso:controlHiddenItem nomeIdentificador="cam_terca_gravar">
                                        <div style="display:'';" id="btnGravar">
                                            <img src="/FrontOfficeWeb/resources/images/bt_registrar_nrml.gif"
                                            	 class="button"
                                            	 style="border:none"
                                            	 onmouseup="terminoCampanha_salvar();"
                                            	 formSubmit="false" />
                                        </div>
                                    </acesso:controlHiddenItem>
                                    </td>                                    
                            </tr>
                            </table>
                        </td>
                    </tr>
                    </table>
                </td>
            </tr>
            </table>
        </td>
    </tr>
</table>
    <iframe scrolling="yes" src="" name="iframe" height="200px" width="300px" style="display:none;"></iframe>
</vivo:quadro>
</form>
   
</acesso:controlHiddenItem>
</html>
