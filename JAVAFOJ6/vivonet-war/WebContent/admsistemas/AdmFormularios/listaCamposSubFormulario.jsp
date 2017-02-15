<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>

<bean:define id="Form"
             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"
             property="camposDinamicosForm"
             type="admsistemas.AdmFormularios.AdmFormulariosController.CamposDinamicosForm"/>
<head>
    <title>Vivo Net</title>
    <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">
    <style type="text/css">
        #clienteWrapper {
            margin:3px;
        }
        #clienteWrapper img {
            margin-right:2px;
        }
        .gestorGerente strong {
            color:#000;
        }
        .gestorGerente td {
            color:#1865c5;
        }
    </style>
    <script type="text/javascript">
      onload = function() {
           if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
      }
    </script>
</head>

<div id="clienteWrapper">
 <logic:notPresent name="errorMsg" scope="request">
    <table id="tableObrigatorio" width="400" border="0" align="center">
    <tr>
            <td>
                <vivo:tbTable selectable="true" layoutWidth="450" layoutHeight="100" tableWidth="450" styleId="tableTitle" sortable="true">
                    <vivo:tbHeader>
                        <vivo:tbHeaderColumn align="left" width="15%" type="string">Onrigatório</vivo:tbHeaderColumn>
                        <vivo:tbHeaderColumn align="left" width="35%" type="string">Tipo</vivo:tbHeaderColumn>
                        <vivo:tbHeaderColumn align="left" width="40%" type="string">Item</vivo:tbHeaderColumn>
                        <vivo:tbHeaderColumn align="left" width="10%" type="">&nbsp;</vivo:tbHeaderColumn>
                    </vivo:tbHeader>
                    <vivo:tbRows>
                          <logic:iterate id="it" name="Form" property="listaCamposSubFormulario" indexId="idx">
                            <vivo:tbRow key="linha1">
                                <vivo:tbRowColumn><input type="checkbox" name="camposObrigatoriosCB"  id="campoObrigatorio_<%=idx%>" value="<bean:write name="it" property="idCampo"/>" class="radio" <logic:equal name="it" property="requerido" value="true">checked</logic:equal> /></vivo:tbRowColumn>
                                <vivo:tbRowColumn><bean:write name="it" property="apCampo"/></vivo:tbRowColumn>
                                <vivo:tbRowColumn><bean:write name="it" property="nmCampo"/></vivo:tbRowColumn>
                            </vivo:tbRow>
                        </logic:iterate>
                    </vivo:tbRows>
                </vivo:tbTable>
            </td>
    </tr>
    <tr>
        <td align="right" colspan="6" style="padding-top:19px">
                     <img src="<%=request.getContextPath()%>/resources/images/bt_gravar_nrml.gif"
                     border="0"
                     style="cursor:pointer;margin-right:15px;"
                     onclick="salvarInclusaoAlteracaoSubFormulario();" />
        </td>
    </tr>
    </table>
    </logic:notPresent>
</div>
<script type="text/javascript">

    $('containerdvIncluirAlterarSubFormulario').setStyle({
        backgroundColor : '#ededed'
    });

    if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();

</script>