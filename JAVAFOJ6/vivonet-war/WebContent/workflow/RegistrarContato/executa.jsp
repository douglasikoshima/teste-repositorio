<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<html>
<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="abaContato" type="workflow.RegistrarContato.RegistrarContatoController.AbaContato"/>        
    <logic:equal name="Form" property="inMsgRetorno" value="true">
        <script>
            <%
                Form.setInMsgRetorno(null);
            %>            
            alert('Contato não pôde ser incluído/alterado pois já existe um contato do mesmo tipo com essa descrição!');
            parent.refresh(<%=Form.getIdPessoa()%>);
        </script>
    </logic:equal>  
    <logic:equal name="Form" property="inMsgRetorno" value="false">
        <script>
            <% 
                Form.setInMsgRetorno(null);
            %>
            //Liga animação
            var aOptComponentsParentSelec = parent.ifrmAbas.document.forms["registrarContatoForm"].elements["comunicacaoSelecionada"];
            var len = aOptComponentsParentSelec.options.length;
            for( x = 0; x < len; x++ ){
                if (aOptComponentsParentSelec.options(x).value == '<%=Form.getDsComunicacao()%>'){
                    aOptComponentsParentSelec.options.remove(x);
                    break;
                }
            }
            parent.refresh(<%=Form.getIdPessoa()%>);
        </script>
    </logic:equal> 
    <script>
        //Fim animação
        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
    </script>
</body>
</html>