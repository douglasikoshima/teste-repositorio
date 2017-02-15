<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<bean:define id="FormArvoreContato"
             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"
             property="formArvoreContato"
             type="admsistemas.admArvoreContato.AdmArvoreContatoController.FormArvoreContato" />

<netui-template:template templatePage="/resources/jsp/templateUsuarioClean.jsp">
    <netui-template:section name="headerSection">

        <script type="text/javascript" language="javascript">
            onload = function() {
                //if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                //alert($('iframeTratamentoFolhaContato'))
                loadConfiguracaoFolha();
            };
            
            loadConfiguracaoFolha = function() {
                if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.startAnimation();
                abaSelected($('abaTratamentoFolhaContato'), $('abaConfiguracaoFolha'));
                $('iframeTratamentoFolhaContato').src = '<%=request.getAttribute("action")%>.do';
                
            };
            
            loadConfiguracaoPalitagem = function() {
                if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.startAnimation();
                abaSelected($('abaTratamentoFolhaContato'), $('abaPalitagem'));
                $('iframeTratamentoFolhaContato').src = 'criarEditarItemContato.do?getDadosPalitagem=true';
            };
        </script>

    </netui-template:section>
    <netui-template:section name="bodySection">

        <vivo:abaGrupo id="abaTratamentoFolhaContato" width="590" height="10" styleClass="">
            <vivo:abaItem select="S" id="abaConfiguracaoFolha" onclick="loadConfiguracaoFolha()" value="Configuração da Folha" />
            <% if ("criarEditarItemContato".equals(request.getAttribute("action")) && "1".equals(FormArvoreContato.getInFolha())) { %>
            <vivo:abaItem id="abaPalitagem" onclick="loadConfiguracaoPalitagem()" value="Palitagem" />
            <% } %>
        </vivo:abaGrupo>

        <iframe id="iframeTratamentoFolhaContato"
                name="iframeTratamentoFolhaContato"
                src="about:blank"
                frameborder="0"
                width="590"
                height="494"></iframe>

    </netui-template:section>
</netui-template:template>