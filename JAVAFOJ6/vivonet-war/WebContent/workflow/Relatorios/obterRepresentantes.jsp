<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
   
<acesso:controlInitEnv/>
<bean:define id="relatorioForm" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relatorioForm"/>
<bean:define id="representantesVO" name="relatorioForm" property="WFRelatoriosFiltrosVO.WFRFRVOArray"/>

<html>
    <body>
    <acesso:controlHiddenItem nomeIdentificador="wor_rorp_verpagina">
        <form name="formUsuario">
            <html:select name="relatorioForm" property="representanteSel" style="width: 200px">
                <html:option value="-1" key="representanteSel">Todos</html:option>
                <html:options collection="representantesVO" property="id" labelProperty="ds"/>
            </html:select>
        </form>
        
        <script>
            var oOption = document.forms["formUsuario"].elements["representanteSel"];
            var oOptionParent = parent.document.forms[0].elements["representanteSel"];
            
            while(oOptionParent.options.length != 0) {
                oOptionParent.options.remove(0);
            }
            
            for(i = 0; i < oOption.options.length; i++ ) {
                var oOptionNew = parent.document.createElement("OPTION");               
                oOptionParent.options.add(oOptionNew);
                oOptionNew.innerText = oOption.options(i).text;
                oOptionNew.value = oOption.options(i).value;            
            }

            //Fim animação
            if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
        </script>  
   
    </acesso:controlHiddenItem>                                                  
    </body>
</html>
