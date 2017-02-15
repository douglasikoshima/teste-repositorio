<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<acesso:controlInitEnv/>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formFormulario"/>

<html>
<head>
    <script language="jscript" for="window" event="onload">
        CarregaAba('bt01');
    </script>
    <script language="javascript" type="text/javascript">

        var pagina    = "";
        var idContato = "<bean:write name="Form" property="idContato" />";

        function CarregaAba(nome) {
            if(nome=="bt01") pagina = "/FrontOfficeWeb/admsistemas/admArvoreContato/workflow/abaCamposForm/configurarFormulario.do?idContato="+idContato;
            if(nome=="bt02") pagina = "/FrontOfficeWeb/admsistemas/admArvoreContato/workflow/abaCamposForm/agruparCampos.do?idContato="+idContato+"&inOperacao=GETAGRUPARCAMPOS";
            if(nome=="bt03") pagina = "/FrontOfficeWeb/admsistemas/admArvoreContato/workflow/abaCamposForm/camposDependentes.do?idContato="+idContato;
            $("iframeConteudo").src = pagina;
            if( window.top.frameApp.dvAnimarAguarde != null ) window.top.frameApp.startAnimation();
        }
    </script>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" />
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js" ></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/toolTip.js"></script>      
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/xtooltip.js"></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js"></script>
</head>
<body style="margin:0;border:0;">

<table cellpadding="0" cellspacing="0" width="755">
    <tr>
        <td>
            <vivo:abaGrupo id="btAba" width="755" height="10" styleClass="abatexto">

                <acesso:controlHiddenItem nomeIdentificador="adm_acf_confform">
                    <vivo:abaItem id="bt01" onclick="abaSelected(btAba, bt01);CarregaAba(this.id);" value="Configurar Formul&aacute;rio" select="S"/>
                </acesso:controlHiddenItem>

                <acesso:controlHiddenItem nomeIdentificador="adm_acf_agrucamp">
                    <vivo:abaItem id="bt02" onclick="abaSelected(btAba, bt02);CarregaAba(this.id);" value="Agrupar Campos"/>
                </acesso:controlHiddenItem>

                <acesso:controlHiddenItem nomeIdentificador="adm_acf_campdepend">
                    <vivo:abaItem id="bt03" onclick="abaSelected(btAba, bt03);CarregaAba(this.id);" value="Campos Dependentes" />
                </acesso:controlHiddenItem>

            </vivo:abaGrupo>
        </td>
    </tr>
    <tr>
        <td style="border:1px solid #ccc; border-top:none;">
            <iframe name="iframeConteudo" id="iframeConteudo" frameborder="0" height="363" width="755"></iframe>
        </td>
    </tr>
</table>

</body>
</html>