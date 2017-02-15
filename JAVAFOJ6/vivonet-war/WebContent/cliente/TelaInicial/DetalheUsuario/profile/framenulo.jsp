<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<netui-template:template templatePage="/resources/jsp/templateUsuarioArvore.jsp">
<netui-template:section name="headerSection"> </netui-template:section>
<netui-template:section name="bodySection">
<acesso:controlHiddenItem nomeIdentificador="cli_frnul_verpagina">
    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="7"></div>

    <vivo:quadro id="perguntas" height="320" width="470">
        <table width="100%" height="100%">
            <tr>
                <td width="100%" height="100%" valign="middle" align="center">


                </td>
            </tr>
        </table>
        <script>
        top.frameApp.oculta_div();
        </script>
    </vivo:quadro>
</acesso:controlHiddenItem>
</netui-template:section>
</netui-template:template>
