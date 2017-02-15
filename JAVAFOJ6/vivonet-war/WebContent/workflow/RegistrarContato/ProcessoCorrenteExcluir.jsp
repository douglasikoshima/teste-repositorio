<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
   
<acesso:controlInitEnv/>

<bean:define id="rowIndex" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="registrarContatoForm.rowIndex" />
<html>
    <head>
        <title>Vivo Net</title>
    </head>
    <body>
    <acesso:controlHiddenItem nomeIdentificador="wor_pcex_verpagina">
        <script language="JavaScript">
            if( parent.ifrmListaProcessos.document.all['dvListaProcessos_body'] != null )
                parent.ifrmListaProcessos.document.all['dvListaProcessos_body'].deleteRow( <%=rowIndex%> );
                
            //Fim animação
            if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
        </script>
   
    </acesso:controlHiddenItem>
    </body>
</html>
