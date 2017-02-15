<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>
<bean:define id="relatorioForm" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relatorioAcessosForm"/>
<bean:define id="regionaisVO" name="relatorioForm" property="filtroRelatorioTerminal.WFRelatoriosFiltroRegionalVOArray"/>
<html>
    <body>
        <form name="formUsuario">
            <html:select name="relatorioForm" property="regionalTerminalSelecionada" style="width:200px">
                <html:option value="-1" key="regionalTerminalSelecionada">Todos</html:option>
                <html:options collection="regionaisVO" property="idRegional" labelProperty="dsRegional"/>
            </html:select>
        </form>
        
        <script>

            var oOption = document.forms["formUsuario"].elements["regionalTerminalSelecionada"];
            var oOptionParent = parent.document["formFiltro"]["regionalTerminalSelecionada"];
            
            
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
            if( top.dvAnimarAguarde != null ) top.stopAnimation();
        </script>   
    </body>
</html>
