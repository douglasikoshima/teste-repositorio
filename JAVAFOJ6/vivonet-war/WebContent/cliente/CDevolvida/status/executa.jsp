<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
<html>
<acesso:controlHiddenItem nomeIdentificador="cli_exst_verpagina">
    <bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="statusForm" type="cliente.CDevolvida.status.StatusController.StatusForm"/>
    
    <logic:equal name="Form" property="inMsgRetorno" value="vazio">
        <script>
            <%
                Form.setInMsgRetorno(null);
            %>            
            alert('Descrição e Sigla não podem ser vazios!');
        </script>
    </logic:equal>
    <logic:equal name="Form" property="inMsgRetorno" value="true">
        <script>
            <%
                Form.setInMsgRetorno(null);
            %>            
            parent.oculta_div();
            alert('Status da correspondência não pôde ser incluído/alterado pois já existe um status com essa descrição/sigla!');
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