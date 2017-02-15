<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<html>
    <head>
        <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">        
    </head>
    
    <body>
    <acesso:controlHiddenItem nomeIdentificador="usu_nothing_verpagina">
    <vivo:quadro id="nothing" width="500" height="80" label="Descriçao" scroll="true">
    </vivo:quadro>
    </acesso:controlHiddenItem>
    </body>
    
</html>