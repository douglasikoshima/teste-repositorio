<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<html>

   <head>
        <title>
            Web Application Page
        </title>
        <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">
        <link href="/FrontOfficeWeb/resources/css/xtree.css" type="text/css" rel="stylesheet"/>
		<script language="javascript" src="/FrontOfficeWeb/resources/scripts/frameweb.js"></script>
		<script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/xtree.js"></script>
        
    <SCRIPT FOR=window EVENT=onload>
        parent.oculta_div();
    </script>
            
    </head>
    
    <acesso:controlHiddenItem nomeIdentificador="que_scs_verpagina">
    <table bgcolor="#ededed" width="100%" height="100%" border="0" cellspacing="5" cellpadding="0">
        <tr>
            <td align="center" valign="middle">
    <vivo:quadro id="qdPerg" width="100%" height="100%" label="Histórico de Perguntas" scroll="auto">
	<table height="100%">
        <tr valign="top" >
            <td >
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td>
                            <!--div style="width:680px;height:296px;overflow:auto;margin-bottom:1px solid #adadad;"-->
                                <script>
                                    <%= request.getAttribute("scriptQuestionario") %>                           
                                </script>
                            <!--/div-->
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr valign="bottom">    
            <td>
                <table>
                    <tr>
                        <td>
                           <img class="button"
                           	    src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif"
                           	    border="0"
                           	    onClick="parent.mostrar_div();window.location.href='voltarNavegacaoScript.do';" />
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>    
    </vivo:quadro>
    </td>
    </tr>
    </table>
  </acesso:controlHiddenItem>  
</html>