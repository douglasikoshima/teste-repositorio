<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>

<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ page import="br.com.vivo.fo.constantes.ConstantesCRM"%>

<bean:define id="form" name="ArvoreForm" scope="session" />

<acesso:controlInitEnv/>

<netui-template:template templatePage="/resources/jsp/templateUsuarioArvore.jsp">
<netui-template:section name="bodySection">
 <form action="montarArvore.do" id="montarArvore" name="montarArvore" method="post">
    <html:text property="idCampanha" name="form"/>
    <html:text property="idSubCampanha" name="form"/>
    <html:text property="idCanalCampanha" name="form"/>
    <html:text property="dsCanalCampanha" name="form"/>
    <html:text property="idCanal" name="form"/>
    <html:text property="idPergunta" name="form"/>
    <html:text property="idResposta" name="form"/>
    <html:text property="idStatus" name="form"/>
    <html:text property="idMotivo" name="form"/>
    <html:text property="idSubMotivo" name="form"/>
    <html:text property="xmlParam" name="form"/>
    <html:text property="inResposta" name="form"/>
 </form>
        <script  charset="ISO-8859-1">
            <%= request.getSession().getAttribute(ConstantesCRM.CT_ARVORE_CAMPANHA) %>
            <% request.getSession().removeAttribute(ConstantesCRM.CT_ARVORE_CAMPANHA); %>
        </script>

    <body>
    <acesso:controlHiddenItem nomeIdentificador="wor_iarbxa_verpagina">
    </acesso:controlHiddenItem>

    <script language="javascript" for="window" event="onload">
        <!--
            top.frameApp.oculta_div();
        -->
    </script>

    </body>
    </netui-template:section>
</netui-template:template>