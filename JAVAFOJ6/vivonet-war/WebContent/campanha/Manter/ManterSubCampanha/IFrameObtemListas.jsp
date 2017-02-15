<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>

<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<bean:define id="Form"				name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="copiaPerguntasActionForm" />
<bean:define id="aSubCampanhaOrig"	name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="copiaPerguntasActionForm.listaSubCampanhaOrig" />
<bean:define id="aCanalOrig"		name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="copiaPerguntasActionForm.listaCanalOrig" />
<bean:define id="aSubCampanhaDest"	name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="copiaPerguntasActionForm.listaSubCampanhaDest" />
<bean:define id="aCanalDest"		name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="copiaPerguntasActionForm.listaCanalDest" />

<html>

    <head></head>
    <body>
    <acesso:controlHiddenItem nomeIdentificador="cam_iframlis_verpagina">
        <form name="copiaPerguntasActionForm" action="obtemSubCampanha.do" method="post">


			<html:select name="Form" property="subCampanhaDestSelecionada" size="1" style="width=100px;height=10px">
					<html:option value=""></html:option>
					<html:options collection="aSubCampanhaDest" property="codigo" labelProperty="nmSubCampanha"/>
			</html:select>

			<html:select name="Form" property="canalSelecionadoDest" size="1" style="width=100px;height=10px">
					<html:option value=""></html:option>
					<html:options collection="aCanalDest" property="codigo" labelProperty="descricao"/>
			</html:select>

		</form>

<SCRIPT FOR=window EVENT=onload>

    var aOptComponent = new Array(document.forms[0].elements["subCampanhaDestSelecionada"],
    document.forms[0].elements["canalSelecionadoDest"]);

    var aOptComponentsParent = new Array(parent.document.forms["copiaPerguntasActionForm"].elements["subCampanhaDestSelecionada"],
    parent.document.forms["copiaPerguntasActionForm"].elements["canalSelecionadoDest"]);



    for(i = 0; i < aOptComponentsParent.length; i++)
    {

        while(aOptComponentsParent[i].options.length != 0)
        {
            aOptComponentsParent[i].options.remove(0);
        }

        for( x = 0; x < aOptComponent[i].options.length; x++ )
        {
            aOptComponent[i].options[x].selected;
            oOption = parent.document.createElement("OPTION");
            aOptComponentsParent[i].options.add(oOption);
            oOption.innerText = aOptComponent[i].options(x).text;
            oOption.value     = aOptComponent[i].options(x).value;
            oOption.selected     = aOptComponent[i].options(x).selected;
        }
    }
    parent.parent.oculta_div();
</script>

    </acesso:controlHiddenItem>
    </body>
</html>