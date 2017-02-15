<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Administração de Campos"/>
    <netui-template:setAttribute name="modulo" value="Administração de Sistemas"/>

    <netui-template:section name="headerSection">
        <script language="javascript">
        <!--
        
        function CarregaAba(nome){
            var pagina = "";
            if(nome=="bt01") pagina = "/FrontOfficeWeb/VOLTAV/manterMenu/beginManterMenu.do";
            if(nome=="bt02") pagina = "/FrontOfficeWeb/VOLTAV/manterMenu/abaResultadoPesquisa.jsp";

            document.getElementById("fraAbas").src = pagina;
            mostrar_div();

        }
        
        // -->
        </script>
        <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
        <!--
            <acesso:controlHiddenItem nomeIdentificador="ver_voltav_manter_menu">
        
                CarregaAba('bt01');
    
            </acesso:controlHiddenItem>
        -->
        </SCRIPT>
    </netui-template:section>

    <netui-template:section name="bodySection">
        <!-- Menu de Administração de Sistemas -->
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
    
        <!--APLICACAO-->
        <vivo:sessao id="qdMain" height="470" width="790" label="VOL/TAV" operacoes="Parametrização de Menu" scroll="no">
            <form name="myForm" action="">

            <acesso:controlHiddenItem nomeIdentificador="ver_voltav_manter_menu">

                <table width="98%" border="0" cellspacing="0" cellpadding="0" bgcolor="#e3ecf4" align="center">
                    <tr><td height="4"></td></tr>
                    <tr>
                        <td valign="top" width="100%">
                            <vivo:abaGrupo id="btAba" width="50%" height="10px" styleClass="abatexto">
                                <vivo:abaItem id="bt01" onclick="abaSelected(btAba, bt01);CarregaAba(this.id);"  value="Filtros para Pesquisa" select="S"/>
                                <vivo:abaItem id="bt02" onclick="alert('Utilize o botão Pesquisa.');" value="Resultado de Pesquisa"/>
                          </vivo:abaGrupo>
                        </td>
                    </tr>
                    <tr>
                        <td valign="top" width="100%" style="border:1px solid #adadad;">
                            <iframe id="fraAbas" width="100%" height="420" frameborder="0" scrolling="no" src="/FrontOfficeWeb/admsistemas/nada.html" style="bgcolor:#e3ecf4;"></iframe>
                        </td>
                    </tr>
                </table>
            
            </acesso:controlHiddenItem -->
            <img hspace="8" vspace="6" style="border:none;" onClick="window.location.href='/FrontOfficeWeb/index.jsp'; return false" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_voltar_over.gif"/>
        </vivo:sessao>

    </netui-template:section>
</netui-template:template>