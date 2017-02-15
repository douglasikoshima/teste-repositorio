<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="lupaPrePagoForm" />
<html>
    <head>
        <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        <script language="javascript">
            function selecionaPlano(obj){
                if(obj.value!=''){
                    document.getElementById('btComparar').style.display = 'block';
                }else{
                    document.getElementById('btComparar').style.display = 'none';
                }
            }

            function compararPlanos(){
                var f = document.forms[0];
                if(top.frameApp.dvAnimarAguarde!=null) top.frameApp.startAnimation();
                f.action = 'loadDetalheSimulador.do';
                f.submit();

            }
        </script>
        <script FOR=window EVENT=onload LANGUAGE="JScript">
        <!--
            parent.parent.oculta_div();
            document.body.style.backgroundColor = '#e3ecf4';
        -->
        </script>
    </head>
    <body rightmargin="0" leftmargin="0" topmargin="0">
         <form action="" name="formSimulador" method="post" onsubmit="return false;">
            <table width="100%" border="0" style="margin-top:10px;">
                <tr>
                    <td width="70"><b>Plano Atual:</b></td>
                    <td width="200" style="background: #e3ecf4"><bean:write name="Form" property="planoAtual.nome" ignore="true"/></td>
                    <td align="right"><b>Planos:</b></td>
                    <td>
                        <select name="nmSelPlano" id="nmSelPlano" style="width:300px;" class="SELECT" onchange="selecionaPlano(this);">
                            <option value="">-- Selecione --</option>
                            <logic:iterate id="it" name="Form" property="listaPlanoSimulado">
                                <option value="<bean:write name="it" property="codigoPlanoSistemaOrigem"/>"><bean:write name="it" property="descricao"/></option>
                            </logic:iterate>
                        </select>
                    </td>
                    <td width="120" align="left">
                        <img id="btComparar" src="/FrontOfficeWeb/resources/images/bt_comparar_nrml.gif" style="cursor:pointer;display:none;" onclick="compararPlanos();">
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>