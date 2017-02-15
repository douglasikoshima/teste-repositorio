<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="mostrarNavegacaoURAForm"/>
<bean:define id="arvore" name="form" property="arvoreNavegacaoURA"/>

<netui-template:template templatePage="/resources/jsp/templateUsuarioArvore.jsp">
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="wor_nvura_verpagina">
        <logic:equal name="form" property="indTimeoutRedirURA" value="S">
            <label style="color=red; font-size=11px;">timeout na URA</label><br>
        </logic:equal>                                   
        <script>
            if (document.getElementById) {
                var tree = new WebFXTree('Raiz');
                <logic:iterate id="itemArvore" name="arvore" property="itemArvoreNavegacaoUraVOArray">
                tree.add(new WebFXTreeItem('<bean:write name="itemArvore" property="descricao"/>'));
                </logic:iterate>
                tree.setBehavior('classic');                
                document.write(tree);
            }
        </script>
    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>