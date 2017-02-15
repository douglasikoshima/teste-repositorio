<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>

<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<html>
<acesso:controlHiddenItem nomeIdentificador="cli_exe_verpagina">
    
	<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="motivoDevolucaoForm" type="cliente.CDevolvida.motivodevolucao.MotivoDevolucaoController.MotivoDevolucaoForm"/>
    
    <logic:equal name="Form" property="inMsgRetorno" value="vazio">
        <script>
            <%
                Form.setInMsgRetorno(null);
            %>            
            alert('Descri��o e Sigla n�o podem ser vazios!');
        </script>
    </logic:equal> 
    <logic:equal name="Form" property="inMsgRetorno" value="true">
        <script>
            <%
                Form.setInMsgRetorno(null);
            %>            
            parent.oculta_div();
            alert('Motivo de Devolu��o n�o p�de ser inclu�do/alterado pois j� existe um motivo com essa descri��o/sigla!');
        </script>
    </logic:equal>
    <logic:equal name="Form" property="inMsgRetorno" value="false">
        <script>            
            top.frameApp.refresh();
            <%
                Form.setInMsgRetorno(null);
            %>
        </script>
    </logic:equal>    
</acesso:controlHiddenItem>

</html>