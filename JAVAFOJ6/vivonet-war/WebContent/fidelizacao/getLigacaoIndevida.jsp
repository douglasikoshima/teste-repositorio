<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<bean:define id="listaLigacaoIndevidaVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaLigacaoIndevidaVO.itemListaVOArray"/>
<html>
    <head>
        <title>Vivo Net</title>
    </head>
    <body>
    <form>
        <select name="idTipoEncerramento">
            <logic:iterate id="lista" name="listaLigacaoIndevidaVO" >
                <option value="<bean:write name="lista" property="id"/>"><bean:write name="lista" property="descricao"/></option>
            </logic:iterate>
        </select>
    </form>
    <script>
        for(i=0;i<document.forms[0].idTipoEncerramento.options.length; i++){
            var newOpt  = document.createElement("OPTION");
            newOpt.text = document.forms[0].idTipoEncerramento.options[i].text;
            newOpt.value= document.forms[0].idTipoEncerramento.options[i].value;
            parent.document.forms[0].elements["idTipoEncerramento"].add(newOpt);
        }
        parent.dvCancelarAtd.style.display = '';
        //parent.document.forms[0].elements["idTipoEncerramento"] = document.forms[0].elements["idTipoEncerramento"];
    </script>
    <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
        <!--
            parent.parent.parent.oculta_div();
        -->
      </SCRIPT>
    </body>
</html>