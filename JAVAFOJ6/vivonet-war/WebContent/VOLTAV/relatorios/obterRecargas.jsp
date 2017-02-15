<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<acesso:controlInitEnv/>
<bean:define id="relatorioForm" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relatorioAcessosForm"/>
<bean:define id="recargaVO"		name="relatorioForm" property="recargas"/>
<bean:define id="lojasVO" name="relatorioForm" property="lojasVO"/>

<html>
    <body>
        <form name="formUsuario">
            <html:select name="relatorioForm" property="recargaSelecionada" style="width:200px">
                <html:option value="-1" key="recargaSelecionada">Todos</html:option>
                <html:options collection="recargaVO" property="idTipoRecarga" labelProperty="dsTipoRecarga"/>
            </html:select>

            <html:select name="relatorioForm" property="lojaSelecionada" style="width:200px">
                <html:option value="-1" key="lojaSelecionada">Todas</html:option>
                <html:options collection="lojasVO" property="idPessoaDePara" labelProperty="dsLoja"/>
            </html:select>
        </form>
        
        <script>

            var oOption = document.forms["formUsuario"].elements["recargaSelecionada"];
            var oOptionParent = parent.document["formFiltro"]["recargaSelecionada"];

            while(oOptionParent.options.length != 0) {
                oOptionParent.options.remove(0);
            }
            
            for(i = 0; i < oOption.options.length; i++ ) {
                var oOptionNew = parent.document.createElement("OPTION");               
                oOptionParent.options.add(oOptionNew);
                oOptionNew.innerText = oOption.options(i).text;
                oOptionNew.value = oOption.options(i).value;            
            }

            oOption = document.forms["formUsuario"].elements["lojaSelecionada"];
            oOptionParent = parent.document["formFiltro"]["lojaSelecionada"];

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
			
			<logic:equal name="relatorioForm" property="isErro" value="true">
				alert("<%= request.getAttribute("mensagem")%>");
			</logic:equal>		
        </script>   
		
    </body>
</html>
