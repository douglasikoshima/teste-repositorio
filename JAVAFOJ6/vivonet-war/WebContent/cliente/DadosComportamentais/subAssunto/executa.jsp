<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
<html>
<acesso:controlHiddenItem nomeIdentificador="cli_exsa_verpagina">
    <bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaSubAssuntoForm" type="cliente.DadosComportamentais.subAssunto.SubAssuntoController.ListaSubAssuntoForm"/>
    <logic:equal name="Form" property="inMsgRetorno" value="vazio">
        <script>
            <%
                Form.setInMsgRetorno(null);
            %>            
            alert('Descrição do subassunto não pode ser vazia!');
        </script>
    </logic:equal>
    <logic:equal name="Form" property="inMsgRetorno" value="false">
        <script>            
            top.frameApp.refresh();
            var pes = '<%=request.getAttribute("pesquisar").toString()%>'
            //alert(pes);
           // window.location.href ='/FrontOfficeWeb/cliente/DadosComportamentais/subAssunto/begin.do?pesquisa='+pes;
          //  parent.oculta_div();
            <%
                Form.setInMsgRetorno(null);
            %>
        </script>
    </logic:equal>
</acesso:controlHiddenItem>
</html>

