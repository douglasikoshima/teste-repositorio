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
    </head>
     <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
        <!--
			parent.parent.oculta_div();
        -->
      </SCRIPT>	
    <acesso:controlHiddenItem nomeIdentificador="que_scf_verpagina">
    <vivo:quadro id="qdPerg" width="760" height="320" label="Histórico de Perguntas" scroll="auto">
	<table height="100%">
        <tr valign="top" >
            <td >
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td>
                            <script>
                                <%= request.getAttribute("scriptQuestionario") %>                           
                            </script>
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
                           	    onClick="parent.parent.mostrar_div();window.location.href='voltarNavegacaoScript.do';"/>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>    
    </vivo:quadro>
    </acesso:controlHiddenItem>
    
</html>