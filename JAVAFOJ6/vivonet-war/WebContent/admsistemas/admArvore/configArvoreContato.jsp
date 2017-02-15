<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<netui-template:template templatePage="./../../resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Administração de Árvore"/>
    <netui-template:setAttribute name="modulo" value="Administração de Sistemas"/>

    <netui-template:section name="headerSection">
        <script language="javascript">
        <!--
        
        function CarregaAba(nome){
            var pagina = "";
            if(nome=="bt01") pagina = "/FrontOfficeWeb/admsistemas/admArvore/vincularDadosBasicos.jsp";
            if(nome=="bt02") pagina = "/FrontOfficeWeb/admsistemas/admArvore/vincularTipos.jsp";
            if(nome=="bt03") pagina = "/FrontOfficeWeb/admsistemas/admArvore/vincularOperadoras.jsp";
            if(nome=="bt04") pagina = "/FrontOfficeWeb/admsistemas/admArvore/vincularLinkInfo.jsp";
            document.getElementById("fraAbas").src = pagina;
        }
        // -->
        </script>
        <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
        <!--
            CarregaAba('bt01');
        -->
        </SCRIPT>
    </netui-template:section>

    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="adm_cac_verpagina">
        <!-- Menu de Administração de Sistemas -->
        <jsp:include page="/resources/menus/MenuAdmSistemas.jsp" />
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="3"></div>
    
        <!--APLICACAO-->
        <vivo:body idTable="tbMain" idDiv="dvMain" height="498" width="790">
            <vivo:quadro id="qdMain" height="473" width="780" label="Configurar Parâmetros da Árvore de Contatos">
                <table width="98%" cellpadding="1" cellspacing="8" align="center">
                    <tr>
                        <td colspan="2" valign="top">
                            <table width="100%" border="0" cellspacing="0" cellpadding="1" class="tbl_bgGray">
                            <tr>
                                <td class="tblEstatica_campoNome" align="left" width="20%">&nbsp;
                                    <netui:label value="Nó Selecionado:" styleClass="tblEstatica_campoNome"/>
                                </td>
                                <td class="tblEstatica_campoValor" align="left" width="80%">&nbsp;
                                    <netui:label value="Root > Serviço > Identificador > Assinatura"/>
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
                            <vivo:abaGrupo id="btAba" width="50%" height="10px" styleClass="abatexto">
                            <acesso:controlHiddenItem nomeIdentificador="adm_cac_carregarabadadosbasicos">
                                <vivo:abaItem id="bt01" onclick="abaSelected(btAba, bt01);CarregaAba(this.id);"  value="Dados Básicos" select="S"/>
                            </acesso:controlHiddenItem>
                            <acesso:controlHiddenItem nomeIdentificador="adm_cac_carregarabafiltros">
                                <vivo:abaItem id="bt02" onclick="abaSelected(btAba, bt02);CarregaAba(this.id);" value="Filtros"/>
                            </acesso:controlHiddenItem>
                            <acesso:controlHiddenItem nomeIdentificador="adm_cac_carregarabaoperadoras">
                                <vivo:abaItem id="bt03" onclick="abaSelected(btAba, bt03);CarregaAba(this.id);" value="Operadoras"/>
                            </acesso:controlHiddenItem>
                            <acesso:controlHiddenItem nomeIdentificador="adm_cac_carregarabavincularlink">
                                <vivo:abaItem id="bt04" onclick="abaSelected(btAba, bt04);CarregaAba(this.id);" value="Vincular Link"/>
                            </acesso:controlHiddenItem>
                            </vivo:abaGrupo>
                        </td>
                    </tr>
                    <tr>
                        <td valign="top" width="100%">
                            <IFRAME ID=fraAbas WIDTH="100%" HEIGHT="400" FRAMEBORDER=0 SCROLLING=NO SRC="/FrontOfficeWeb/admsistemas/nada.html" style="bgcolor:#e3ecf4"></IFRAME>
                        </td>
                    </tr>
                </table>
            </vivo:quadro>
        </vivo:body>
    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>