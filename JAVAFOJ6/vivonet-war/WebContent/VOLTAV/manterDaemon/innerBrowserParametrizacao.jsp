<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<bean:define id="FormManterDaemon" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formManterDaemon"/>

<bean:define id="FilaPreVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formManterDaemon.filaPrePagoVO"/>

<html>
    <head>
            <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>

    <script language="JavaScript">
    
    var nrStatusFila = '<bean:write name="FilaPreVO" property="nrStatusFila"/>';
    
    //ATIVO
    if(nrStatusFila == 1)
    {
        parent.document.getElementById('div_status_fila').innerText     = 'ATIVADO';
        
        if(parent.document.getElementById('btDesativar'))
            parent.document.getElementById('btDesativar').style.display     = 'block';
        
        if(parent.document.getElementById('btAtivar'))
            parent.document.getElementById('btAtivar').style.display        = 'none';
            
        if(parent.document.getElementById('btGravar'))
            parent.document.getElementById('btGravar').style.visibility     = 'hidden';
        
        parent.document.forms[0].qtdRegistrosLidos.disabled = true;
        parent.document.forms[0].tmpRegistroLeitura.disabled = true;
        
    }else{

        parent.document.getElementById('div_status_fila').innerText     = 'DESATIVADO';
        
        if(parent.document.getElementById('btDesativar'))
            parent.document.getElementById('btDesativar').style.display     = 'none';
            
        if(parent.document.getElementById('btAtivar'))
            parent.document.getElementById('btAtivar').style.display        = 'block';
            
        if(parent.document.getElementById('btGravar'))
            parent.document.getElementById('btGravar').style.visibility     = 'visible';
            
        parent.document.forms[0].qtdRegistrosLidos.disabled = false;
        parent.document.forms[0].tmpRegistroLeitura.disabled = false;
        
    }
    
    window.top.frameApp.oculta_div();
    
    </script>
            
    <vivo:alert atributo="msgStatus" scope="request"/>
    
    </head>

    <body>
    </body>
</html>