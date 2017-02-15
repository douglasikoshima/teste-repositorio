<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<html>
    <head>
        <title> Questionario Vazio </title>
        <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">
        <link href="/FrontOfficeWeb/resources/css/xtree.css" type="text/css" rel="stylesheet"/>
        <script language="javascript" src="/FrontOfficeWeb/resources/scripts/frameweb.js"></script>
        <script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/xtree.js"></script>
        <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
        <!--
			parent.parent.oculta_div();
        -->
      </SCRIPT>
    </head>
    
    <vivo:body width="760" height="320" idTable="tbMain" idDiv="dvMain">
    <acesso:controlHiddenItem nomeIdentificador="que_qvf_verpagina">
    <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td align="center">
            <vivo:quadro id="qdPerg" width="100%" height="100%" label="Script" scroll="false">
            <table border="0" width="100%" cellpadding="1" cellspacing="10" class="tbl_bgGray" height="100%" >
                <tr valign="middle">
                    <td valign="middle" align="center" >
                    <b style="font-size:14pt;">Não existem perguntas cadastradas  neste canal e/ou cliente!</b>
                    </td>
                </tr>
                <tr>
                    <td valign="middle" align="center" >    
                    <!--                    
                        <img src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_voltar_over.gif" style="border:none" onclick=" history.go(-1);"/>
                    -->
                    </td>
                </tr>
            </table>
            </vivo:quadro>
            </td>
        </tr>
    </table>
    </acesso:controlHiddenItem>
    </vivo:body>
</html>
