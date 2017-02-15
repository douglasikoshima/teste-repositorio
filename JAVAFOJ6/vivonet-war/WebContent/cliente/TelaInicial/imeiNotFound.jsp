<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="desbloqueioImei"/>
<bean:define id="ItemListaVOMarca" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="desbloqueioImei.listaMarcasVO.itemListaVOArray"/>
<html>
    <head>
        <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
        <style type="text/css">
            ul{ width: 100%; margin: 1em 0; padding: 0; counter-reset: ul; }
            ul li{ line-height: 10px; list-style-type: none; padding-left: 10px; }
            br{ clear: left; }
        </style>
        <script language="javascript">
            function validaForm(){
                f = document.forms[0];
                if(f.idMarca.value==""){
                    alert("Selecione a Marca do Aparelho");
                    return false;
                }
                if(f.dsModelo.value==""){
                    alert("Preencha o Modelo do Aparelho");
                    return false;
                }
                f.dsMarca.value = f.idMarca.options[f.idMarca.selectedIndex].text;
                top.frameApp.ti_frameAbas.submitDadosAparelho(f.dsMarca.value, f.dsModelo.value, f.nmLoja.value);
                top.frameApp.fechaLupa();
            }
        </script>
    </head>
    <body rightmargin="0" leftmargin="0" topmargin="0" style="margin-left:3px;background-color:#ffffff;font-family:Tahoma;font-size:11px;">
    <html:form action="/cliente/TelaInicial/getDataImeiNotFound.do" onsubmit="return false;">
        <input type="hidden" name="dsMarca" value="">
        <input type="hidden" name="nrIMEI" value="<bean:write name="Form" property="desbloqueioGsmVO.nrIMEI"/>">
        <div class="tbl_bgGray" style="padding:5px;height:135px;">
            <table width="100%">
                <tr>
                    <td colspan="2" height="20"><b>Serial do Aparelho não encontrado</b></td>
                </tr>
                <tr>
                    <td><strong>Marca do aparelho:</strong></td>
                    <td>
                        &nbsp;<html:select name="Form" property="idMarca" style="width:200px" styleClass="SELECT" size="1">
                            <option value="">Selecione a Marca</option>
                            <html:options collection="ItemListaVOMarca" property="id" labelProperty="descricao"/>
                        </html:select>
                    </td>
                </tr>
                <tr>
                    <td valign="top"><strong>Modelo do aparelho:</strong></td>
                    <td valign="top">
                        <input type="text" name="dsModelo" style="width:200px" value="">
                    </td>
                </tr>
                <tr>
                    <td valign="top"><b>Loja:</b></td>
                    <td valign="top">
                        &nbsp;<input type="text" name="nmLoja" style="width:200px" value="">
                    </td>
                </tr>
                <tr>
                    <td valign="top"><b>IMEI:</b></td>
                    <td valign="top">
                        &nbsp;<bean:write name="Form" property="desbloqueioGsmVO.nrIMEI"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="right">
                        <img src="/FrontOfficeWeb/resources/images/bt_soliccodigo_nrml.gif" onclick="return validaForm();"/>&nbsp;
                        <img src="/FrontOfficeWeb/resources/images/bt_cancelar_nrml.gif" onclick="top.frameApp.fechaLupa();"/>
                    </td>
                </tr>
            </table>
        </div>
    </html:form>
    </body>
</html>