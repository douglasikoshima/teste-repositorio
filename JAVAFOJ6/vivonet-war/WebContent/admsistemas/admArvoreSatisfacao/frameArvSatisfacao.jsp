<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>

<head>
    <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
    <link href="<%=request.getContextPath()%>/resources/css/xtree.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/xtree.js"></script>
    <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>

<STYLE type="text/css">
<!--
BODY {
background-color: #E3ECF4;
scrollbar-track-color:#E3ECF4;
}
-->
</STYLE>
</head>    
<body bgcolor="#E3ECF4">
<table bgcolor="#E3ECF4" width="100%" height="100%">
    <tr>
        <td valign="top" width="100%">
            <script>
                if (document.getElementById) {
                    
                    
                    var tree = new WebFXTree('Nome do Questionário', 'javascript:SetCodigoPerg(1);', '', '/FrontOfficeWeb/resources/images/icon_subcamp_close.png', '/FrontOfficeWeb/resources/images/icon_subcamp_open.png');
                    tree.setBehavior('classic');
                                                                                    
                    var aPergunta1 = new WebFXTreeItem('P01 - Qual sua operadora?','javascript:mostraItemSelecionado(\'P01 - Qual sua operadora?\');javascript:SetCodigoPerg(1);', '','/FrontOfficeWeb/resources/images/icon_perg_indic.gif', '/FrontOfficeWeb/resources/images/icon_perg_indic.gif');
                    aPergunta1.add(new WebFXTreeItem('Vivo','javascript:mostraItemSelecionado(\'Vivo\');javascript:SetCodigoResp(1,1);', '', '/FrontOfficeWeb/resources/images/icon_resp_nrml.gif'));
                    aPergunta1.add(new WebFXTreeItem('P03 - Tim - (Gostaria de mudar de operadora?)','javascript:mostraItemSelecionado(\'P03 - Tim - (Gostaria de mudar de operadora?)\');javascript:SetCodigoResp(1,2);', '', '/FrontOfficeWeb/resources/images/icon_resp_salto.gif'));                                        
                    aPergunta1.add(new WebFXTreeItem('P04 - Claro - (Gostaria de mudar de operadora?)','javascript:mostraItemSelecionado(\'P04 - Claro - (Gostaria de mudar de operadora?)\');javascript:SetCodigoResp(1,3);', '', '/FrontOfficeWeb/resources/images/icon_resp_salto.gif'));
                    
                    tree.add(aPergunta1);
                    
                    
                    var aPergunta2 = new WebFXTreeItem('P02 - Qual seu tipo de plano?','javascript:SetCodigoPerg(2);', '','/FrontOfficeWeb/resources/images/icon_perg_indic.gif', '/FrontOfficeWeb/resources/images/icon_perg_indic.gif');
                    aPergunta2.add(new WebFXTreeItem('Pós-Pago','javascript:SetCodigoResp(2,1);', '', '/FrontOfficeWeb/resources/images/icon_resp_nrml.gif'));
                    aPergunta2.add(new WebFXTreeItem('Pré-Pago', 'javascript:SetCodigoResp(2,2);', '','/FrontOfficeWeb/resources/images/icon_resp_nrml.gif',''));                                        
                    aPergunta2.add(new WebFXTreeItem('Corporativo','javascript:SetCodigoResp(2,3);', '', '/FrontOfficeWeb/resources/images/icon_resp_nrml.gif'));
                    
                    tree.add(aPergunta2);
                    
                    var aPergunta3 = new WebFXTreeItem('P03 - Gostaria de mudar de plano?','javascript:SetCodigoPerg(3);', '','/FrontOfficeWeb/resources/images/icon_perg_indic.gif', '/FrontOfficeWeb/resources/images/icon_perg_indic.gif');
                    aPergunta3.add(new WebFXTreeItem('Sim','javascript:SetCodigoResp(3,1);', '', '/FrontOfficeWeb/resources/images/icon_resp_nrml.gif'));
                    aPergunta3.add(new WebFXTreeItem('Não','javascript:SetCodigoResp(3,2);', '', '/FrontOfficeWeb/resources/images/icon_resp_nrml.gif'));                                        
                    aPergunta3.add(new WebFXTreeItem('Depende','javascript:SetCodigoResp(3,3);', '', '/FrontOfficeWeb/resources/images/icon_resp_nrml.gif'));
                    
                    tree.add(aPergunta3);
                    
                    var aPergunta4 = new WebFXTreeItem('P04 - O que acha da Vivo?','javascript:SetCodigoPerg(4);', '','/FrontOfficeWeb/resources/images/icon_perg_indic.gif', '/FrontOfficeWeb/resources/images/icon_perg_indic.gif');
                    aPergunta4.add(new WebFXTreeItem('Gosto','javascript:SetCodigoResp(4,1);', '', '/FrontOfficeWeb/resources/images/icon_resp_nrml.gif'));
                    aPergunta4.add(new WebFXTreeItem('Sei lá','javascript:SetCodigoResp(4,2);', '', '/FrontOfficeWeb/resources/images/icon_resp_nrml.gif'));                                        
                    aPergunta4.add(new WebFXTreeItem('Não gosto','javascript:SetCodigoResp(4,3);', '', '/FrontOfficeWeb/resources/images/icon_resp_nrml.gif'));
                    
                    tree.add(aPergunta4);
                    
                    document.write(tree);
                }
            </script>
        </td>
    </tr>
</table>
</body>