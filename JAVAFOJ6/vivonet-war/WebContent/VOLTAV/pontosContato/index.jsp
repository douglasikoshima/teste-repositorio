<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%> 

<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="pontosContatoFormBean"/>

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">

<netui-template:setAttribute name="title" value="Pontos de Contato"/>
<netui-template:setAttribute name="modulo" value="VOL/TAV"/>

<netui-template:section name="headerSection">

    <script type="text/javascript" language="javascript">

        function enviarArquivo() {
            f = document.forms[0];
            if ($F('file')=="") {
                alert('Por favor, selecione um arquivo para upload.');
            } else {
                if (validaArquivoEntrada($F('file'))) {
                    //f.submit();
                }
            }
        }

        function validaArquivoEntrada(pathArquivo) {
            var extensao = pathArquivo.substring(pathArquivo.lastIndexOf(".")+1, pathArquivo.length);
            if (extensao.toLowerCase() != "txt") {
                alert("Por favor, faça o upload de um arquivo TXT.");
                return false;
            }
            return true;
        }

        function consultarProcessamento() {
            var f    = document.forms[0];
            f.target = "";
            f.action = "consultarProcessamento.do";
            f.submit();
        }

    </script>
</netui-template:section>

<netui-template:section name="bodySection">

<jsp:include page="/resources/menus/MenuPrincipal.jsp" />
<div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>

<vivo:sessao id="qdMain" height="468" width="790" label="Parametrização" operacoes="Pontos de Contato" scroll="no">

    <acesso:controlHiddenItem nomeIdentificador="voltav_pcontato_verpagina">

    <form action="uploadArquivo.do" name="pontosContatoFormBean" enctype="multipart/form-data" method="post">

    <table cellpadding="10">
        <tr>
            <td width="100" nowrap>
                Arquivo para upload
            </td>
            <td>
                <html:file name="Form" property="file" styleId="file" style="width:400px;" />
            </td>
        </tr>
        <tr>
            <td colspan="2" align="right">
                <img id="btConsultarProcessamento"
                                   value="Consultar Processamento"
                                   src="/FrontOfficeWeb/resources/images/bt_consultarprocessamento_nrml.gif"
                                   rolloverImage="/FrontOfficeWeb/resources/images/bt_consultarprocessamento_over.gif"
                                   style="border:none;margin-left:4px;"
                                   onMouseUp="consultarProcessamento()" />
                <img id="btEnviar"
                                   value="Enviar"
                                   src="/FrontOfficeWeb/resources/images/bt_enviar_nrml.gif"
                                   rolloverImage="/FrontOfficeWeb/resources/images/bt_enviar_over.gif"
                                   style="border:none;margin-left:4px;"
                                   onMouseUp="enviarArquivo()" />
            </td>
        </tr>
    </table>
    
    </form>

    </acesso:controlHiddenItem>

</vivo:sessao>

<vivo:alert atributo="msgStatus" scope="request"/>

</netui-template:section>

</netui-template:template>