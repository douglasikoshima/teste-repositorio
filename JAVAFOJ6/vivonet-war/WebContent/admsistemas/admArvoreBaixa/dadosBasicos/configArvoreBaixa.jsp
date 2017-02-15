<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Administração de Árvore"/>
    <netui-template:setAttribute name="modulo" value="Administração de Sistemas"/>

    <netui-template:section name="headerSection">
        <script language="javascript">
        <!--
        
        function CarregaAba(nome){
            var pagina = "";
            if(nome=="bt02") pagina = "/FrontOfficeWeb/admsistemas/admArvoreBaixa/dadosBasicos/abaFiltros/begin.do?idBaixa=<%=request.getParameter("idBaixa")%>";
            if(nome=="bt03") pagina = "/FrontOfficeWeb/admsistemas/admArvoreBaixa/dadosBasicos/abaOperadoras/begin.do?idBaixa=<%=request.getParameter("idBaixa")%>";
            document.getElementById("ifmAbas").src = pagina;
            mostrar_div();

            
        }
        function enviaVoltar()
        {
        
            document.forms[0].action = '/FrontOfficeWeb/admsistemas/admArvoreBaixa/begin.do';
            document.forms[0].submit();
            mostrar_div();
        }
        
        // -->
        </script>
        <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
        <!--
            CarregaAba('bt02');
        -->
        </SCRIPT>
    </netui-template:section>

    <netui-template:section name="bodySection">
        <!-- Menu de Administração de Sistemas -->
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
    
        <!--APLICACAO-->
        <vivo:sessao id="qdMain" height="470" width="790" label="Árvore de Baixa" operacoes="Configuração de Parâmetros" scroll="no">
            <form name="myForm" action="" method="post">
            <table width="780" cellpadding="1" cellspacing="8" align="center">
                <tr>
                    <td colspan="2" valign="top">
                        <table width="100%" border="0" cellspacing="0" cellpadding="1" class="tbl_bgGray">
                        <tr>
                            <td class="tblEstatica_campoNome" align="left" width="20%">&nbsp;
                                <netui:label value="Ítem Selecionado:" styleClass="tblEstatica_campoNome"/>
                            </td>
                            <td class="tblEstatica_campoValor" align="left" width="80%">&nbsp;
                                <netui:label value='<%=request.getParameter("nmBaixa")%>'/>
                            </td>
                        <tr>
                        </table>
                    </td>
                </tr>
            </table>
            
            <table width="97%" border="0" cellspacing="0" cellpadding="0" bgcolor="#e3ecf4" align="center">
                <tr><td height="4"></td></tr>
                <tr>
                    <td valign="top" width="100%">
                        <vivo:abaGrupo id="btAba" width="45%" height="10px" styleClass="abatexto">
                            <vivo:abaItem id="bt02" onclick="abaSelected(btAba, bt02);CarregaAba(this.id);" value="Filtros para Visualização" select="S"/>
                            <vivo:abaItem id="bt03" onclick="abaSelected(btAba, bt03);CarregaAba(this.id);" value="Regionais"/>                                
                        </vivo:abaGrupo>
                    </td>
                </tr>
                <tr>
                    <td valign="top" width="100%" style="border:1px solid #adadad;">
                        <iframe id="ifmAbas" name="ifmAbas" width="100%" height="380" frameborder="0" scrolling="no" src="nada.html" style="bgcolor:#e3ecf4;"></iframe>
                    </td>
                </tr>
            </table>

            <img hspace="8" vspace="6" style="border:none;" onClick="enviaVoltar();" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_voltar_over.gif"/>
        </vivo:sessao>

    </netui-template:section>
</netui-template:template>