<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="com.bea.wlw.netui.tags.html.Form"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<acesso:controlInitEnv/>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="abaContato" type="cliente.TelaInicial.DetalheCliente.DetalheClienteController.AbaContato" />

<html>
<acesso:controlHiddenItem nomeIdentificador="cli_tidcexec_verpagina">
    <form action="/FrontOfficeWeb/cliente/TelaInicial/DetalheCliente/alterarEmailLegado.do" method="post">
        <body>
            <html:hidden name="Form" property="idComunicacao"       styleId="idEmail"/>
            <html:hidden name="Form" property="contato.dsContato"   styleId="dsEmail"/>
            <html:hidden name="Form" property="contato.nrSequencia" styleId="nrSequencia"/>
            <html:hidden name="Form" property="idPessoa" styleId="idPessoa"/>
            <html:hidden name="Form" property="dsOldEmail" styleId="dsOldEmail"/>

            <logic:equal name="Form" property="inMsgRetorno" value="alteracaoSequencia">
                <script>
                    <%
                        Form.setInMsgRetorno(null);
                    %>
                    parent.parent.oculta_div();
                    idPessoa     = document.getElementById('idPessoa').value;
                    alert('<bean:write name="Form" property="msgAlerta"/>');
                    document.forms[0].target ='fraSubAbas';
                    document.forms[0].action ='loadContato.do?reload=OK&&idPessoaCliente='+idPessoa;
                    document.forms[0].method = "POST";
                    document.forms[0].submit();
                </script>
            </logic:equal>

            <logic:equal name="Form" property="inMsgRetorno" value="true">
                <script>
                    <%
                        Form.setInMsgRetorno(null);
                    %>
                    parent.parent.parent.oculta_div();
                    alert('<bean:write name="Form" property="msgAlerta"/>');
                </script>
            </logic:equal>

            <logic:equal name="Form" property="inMsgRetorno" value="naoPermiteExclusao">
                <%
                    Form.setInMsgRetorno(null);
                %>
                <script>
                    parent.parent.oculta_div();
                    idPessoa     = document.getElementById('idPessoa').value;
                    alert('<bean:write name="Form" property="msgAlerta"/>');
                    document.forms[0].target ='fraSubAbas';
                    document.forms[0].action ='loadContato.do?reload=OK&&idPessoaCliente='+idPessoa;
                    document.forms[0].method = "POST";
                    document.forms[0].submit();
                </script>
            </logic:equal>

            <logic:equal name="Form" property="inMsgRetorno" value="confirm">
                <script>
                    <%
                        Form.setInMsgRetorno(null);
                    %>
                    parent.parent.parent.oculta_div();
                    if(confirm('E-mail vinculado ao envio de comprovante/conta! Deseja realmente alterar os dados, atualizando também o envio?')){
                        idEmail     = document.getElementById('idEmail').value;
                        dsEmail     = document.getElementById('dsEmail').value;
                        nrSequencia = document.getElementById('nrSequencia').value;
                        document.forms[0].action="alterarEmailLegado.do?idEmail="+idEmail+"&dsEmail="+dsEmail+"&nrSequencia="+nrSequencia;
                        document.forms[0].method = "POST";
                        document.forms[0].submit();
                    }
                </script>
            </logic:equal>

            <logic:equal name="Form" property="inMsgRetorno" value="false">
                <script>
                    <%
                        Form.setInMsgRetorno(null);
                    %>




                    parent.refresh(<%=Form.getIdPessoa()%>);



                </script>
            </logic:equal>

            <logic:equal name="Form" property="inMsgRetorno" value="erroFO">
                <html:hidden name="Form" property="inMsgRetorno" styleId="inMsgRetorno"/>
                <script>
                    parent.parent.parent.oculta_div();
                    alert('<bean:write name="Form" property="msgAlerta"/>');
                    idEmail     = document.getElementById('idEmail').value;
                    dsEmail     = document.getElementById('dsEmail').value;
                    nrSequencia = document.getElementById('nrSequencia').value;
                    document.forms[0].action="alterarEmailLegado.do?idEmail="+idEmail+"&dsEmail="+dsEmail+"&nrSequencia="+nrSequencia;
                    document.forms[0].method = "POST";
                    document.forms[0].submit();
                </script>
            </logic:equal>

            <logic:equal name="Form" property="inMsgRetorno" value="erroLegado">
                <html:hidden name="Form" property="inMsgRetorno" styleId="inMsgRetorno"/>
                <script>
                    alert('<bean:write name="Form" property="msgAlerta"/>');
                    idPessoa     = document.getElementById('idPessoa').value;
                    document.forms[0].target ='fraSubAbas';
                    document.forms[0].action ='loadContato.do?reload=OK&&idPessoaCliente='+idPessoa;
                    document.forms[0].method = "POST";
                    document.forms[0].submit();
                </script>
            </logic:equal>

        </body>
    </form>
</acesso:controlHiddenItem>
</html>
