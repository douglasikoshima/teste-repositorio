<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!-- Form Bean -->
<bean:define id="FormValorDominio"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="valorDominioForm"/>

<!-- Combos -->
<bean:define id="aTabelaSemDominio"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="valorDominioForm.admCopiaVO.admTabelaComDominioVO"/>
<bean:define id="aTabelaComDominio"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="valorDominioForm.valorDominioCombo.admTabelaSemDominioVO"/>

<head>
    <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">        
    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/frameweb.js" ></script>

    <script language="javaScript">
        function valida() 
        {
            if (trim(document.getElementById("dominioVO.nmDominio").value) == "")
            {
                alert("Valor Domínio é um campo obrigatório, favor preencher.");
                return false;
                
            }else if(document.getElementById("dominioVO.admUFOperadoraSimplVO.idUFOperadora").selectedIndex == 0)
            {
                alert("Selecione Operadora.");
                return false;
                
            }else if(document.getElementById("dominioVO.admTipoLinhaSimplVO.idTipoLinha").selectedIndex == 0)
            {
                alert("Selecione Tipo Linha.");
                return false;
                
            }else if(ocument.getElementById("dominioVO.admTabelaDominioVO.idTabelaDominio").selectedIndex == 0)
            {
                alert("Selecione Tabela.");
                return false;
            
            } 
            
        return true;
            
        }

        function persisteCopia() 
        {
            if(valida())
            {
                document.forms[0].action = 'persisteCopia.do';
                parent.parent.mostrar_div();
                document.forms[0].submit();
            }
        }

    </script>

</head>

<body>
    <script>
        document.body.style.backgroundColor = '#ededed';
    </script>     
        
    <html:form action="persisteCopia" target="_parent" onsubmit="return false;">  
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
            <table cellspacing="1" cellpadding="1" height="60" align="center">
                    <tr>
                        <td>label</td>                    
                        <td>combo</td>                    
                        <td>lebal</td>                    
                        <td>combo</td>                    
                    </tr>
                    <tr>
                        <td colspan="6" align="right">
                            <img hspace="5" style="border:none;cursor:hand;"  onclick="persisteCopia();" src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_salvar_over.gif'"/>
                        </td>
                    </tr>
            </table>
   </html:form>
    <script language="javascript">
        parent.parent.oculta_div();
    </script>
</body>