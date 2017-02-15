<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>

<html>
    <head>
        <link type="text/css" rel="stylesheet" href="./../../resources/css/xtree.css">
        <link rel="STYLESHEET" type="text/css" href="./../../resources/css/frontoffice.css">
        <script src="./../../resources/scripts/xtree.js">
        </script>
    </head>
        
        <!--APLICACAO-->
        <vivo:body idTable="tbMain" idDiv="dvMain" height="200" width="268">
            <vivo:quadro id="qdMain" height="178" width="258" label="Transferência Chamada">
             <table border="0">
                <tr>
                    <td>
                        Ramal:
                    </td>
                    <td>                    
                        <netui:textBox dataSource="{}" size="5"/>
                    </td>
                </tr>                
                <tr>
                    <td>
                        Posição:
                    </td>
                    <td>
                        <netui:select dataSource="{}">
                            <netui:selectOption value="Atendimento 2 Nível"/>
                            <netui:selectOption value="Atendimento 3 Nível - Retenção"/>
                        </netui:select>
                    </td>
                </tr>                
            </table>
            <table>
                <tr>
                    <td colspan="2">
                        Comentário:<br/>
                        <netui:textArea dataSource="{}" cols="25"/>                               
                    </td>
                </tr>
                <tr>
                    <td><vivo:botao id="bt01" width="60px" height="25px" value="Transferir" styleClass="btTemplate" onclick=""/></td>                    
                </tr>
            </table>
            
            </vivo:quadro>
        </vivo:body>        
</html>           