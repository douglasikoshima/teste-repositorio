<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<bean:define id="form" name="validarClienteForm" scope="request"/>
<bean:define id="campanhas" name="form" property="campanhasVO"/>

<html>
    <body>
        <form name="formComboCampanha">
            <html:select name="form" property="numCampanhaSel" style="width=100px" onchange="trocarCampanha(this.value)">
                <html:option value="-1" key="numCampanhaSel">&nbsp;</html:option>
                <html:options collection="campanhas" property="idRetornoWFCTI" labelProperty="dsRetornoWFCTI"/> 
            </html:select>
        </form>        
        <script>
            var oOption = document.forms["formComboCampanha"].elements["numCampanhaSel"];
            var oOptionParent = parent.document.forms["validarClienteForm"].elements["numCampanhaSel"];
            
            while(oOptionParent.options.length != 0) {
                oOptionParent.options.remove(0);
            }
            
            for(i = 0; i < oOption.options.length; i++ ) {
                var oOptionNew = parent.document.createElement("OPTION");               
                oOptionParent.options.add(oOptionNew);
                oOptionNew.innerText = oOption.options(i).text;
                oOptionNew.value = oOption.options(i).value;            
            }        
        </script>                                                 
    </body>
</html>
