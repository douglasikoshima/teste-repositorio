<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
   
<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoDetalheForm"/>
<bean:define id="subEstadosVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoDetalheForm.atendimentoDetalheVO.WFSubEstadoVOArray"/>

<html>
    <body>    
        <form name="formSubEstado">
            <html:select name="form" property="subEstadoSel" title="subEstadoSel" style="width=120px">
                <html:option value="-1" key="subEstadoSel">&nbsp;</html:option>
                <html:options collection="subEstadosVO" property="idSubEstado" labelProperty="dsSubEstado" /> 
            </html:select>
        </form>
        
        <script>
            var oOption = document.forms["formSubEstado"].elements["subEstadoSel"];
            var oOptionParent = parent.document.forms["formHistorico"].elements["subEstadoSel"];
            
            while(oOptionParent.options.length != 0) {
                oOptionParent.options.remove(0);
            }
            
            for(i = 0; i < oOption.options.length; i++ ) {
                var oOptionNew = parent.document.createElement("OPTION");               
                oOptionParent.options.add(oOptionNew);
                oOptionNew.innerText = oOption.options(i).text;
                oOptionNew.value = oOption.options(i).value;            
            }
            
            //Desliga anima��o
            if(top.frameApp.dvAnimarAguarde != null) {
                top.frameApp.stopAnimation();
            }
            
        </script>  
   
                                                
    </body>
</html>
