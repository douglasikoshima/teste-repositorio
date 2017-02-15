<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
  
<html> 
    <head>
        <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">        
    </head>
    
    <body>
    <acesso:controlHiddenItem nomeIdentificador="usu_nothing_verpagina">
    <vivo:quadro id="nothing" width="390" height="340" label="Descriçao" scroll="true">
    </vivo:quadro>
    </acesso:controlHiddenItem>
    </body>
    
</html>