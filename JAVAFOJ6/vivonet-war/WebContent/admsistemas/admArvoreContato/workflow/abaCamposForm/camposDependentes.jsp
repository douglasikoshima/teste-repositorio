<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<bean:define id="FormFormulario"          name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formFormulario"/>
<bean:define id="arrayGrupoCampos"        name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formFormulario.arrayGrupoCampos"/>

<html>

<head>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js" ></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/toolTip.js"></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
    <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" language="javascript">

        var inOperacao    = "";
        var idGrupoCampos = "";
        var flAlteracao   = false;
        var idPrimeiroPai, idPai, idCampo, nrNivel;

        function getOperacao(valor) {
            if (valor == "CRIAR") {
                $('divTextField').style.display    = "block";
                $('divConteudoAbas').style.display = "block";
                $('divSelectList').style.display   = "none";
                CarregaAba("bt01", valor, true);
            } else if (valor == "ALTERAR") {
                $('divSelectList').style.display   = "block";
                $('divTextField').style.display    = "none";
                if ($('idGrupoCampos').value == "") {
                    $('idGrupoCampos').selectedIndex = 0;
                    $('divConteudoAbas').style.display='none';
                } else {
                    CarregaAba("bt01", valor, true);
                }
            } else {
                $('divSelectList').style.display = "none";
                $('divConteudoAbas').style.display = "none";
                $('divTextField').style.display  = "none";
            }
        }

        function CarregaAba(nome, acao, flForm) {
            var f = document.forms[0];
            if (nome == "bt01") {
                if (acao == "ALTERAR") f.idGrupoCampos.value = $('idGrupoCampos').value;
                else {
                    f.idGrupoCampos.value = "";
                    f.nmGrupoCampos.value = "";
                }
                f.inOperacao.value = "GETCAMPOSVALORES";
                pagina = "camposDependentes.do";
            }
            if (nome=="bt02") {
                if ($('idOperacao').value == "CRIAR") {
                    alert("Relacionamento de campos só pode ser realizado após efetivaçao da criação do grupo.");
                    abaSelected(btAba, bt01);
                    return false;
                }
                pagina = "/FrontOfficeWeb/admsistemas/admArvoreContato/workflow/abaCamposForm/camposDependentes.do?idGrupoCampos="+$('idGrupoCampos').value+"&idContato=<bean:write name="FormFormulario" property="idContato"/>&inOperacao=GETTREE";
            }

            if( window.top.frameApp.dvAnimarAguarde != null ) window.top.frameApp.startAnimation();
            if (flForm) {
                
                f.target = "iframeConteudoAbas";
                f.action = pagina;
                f.submit();
            } else {
                $("iframeConteudoAbas").src = pagina;
            }
        }

        function getCamposSelecionados(obj) {
            var f = document.forms[0];
            if (obj.value != "-1") {
                f.nmGrupoCampos.value = obj.options[obj.selectedIndex].text;
                $('divConteudoAbas').style.display = "block";
                CarregaAba("bt01", "ALTERAR", true);
                abaSelected(btAba, bt01);
            } else {
                $('divConteudoAbas').style.display = "none";
            }
        }

        function controlNome(obj, op){
            if (op == "blur") {
                obj.value = trim(obj.value);
            }
            window.iframeConteudoAbas.$('nmGrupoCampos').value = obj.value;
        }

    </script>

    <script language="javascript" for="window" event="onload">
        if( window.top.frameApp.dvAnimarAguarde != null ) window.top.frameApp.stopAnimation();
        document.body.style.backgroundColor = '#ededed';
    </script>

</head>
<body>

    <form action="camposDependentes" id="camposDependentes" name="camposDependentes" method="post" >

    <html:hidden name="FormFormulario" property="idContato"/>
    <html:hidden name="FormFormulario" property="inOperacao"/>

    <table width="740" border="0" cellspacing="0" cellpadding="0" align="center" style="margin:5px 0 0 10px;font-weight:bold;">
        <tr>
            <td width="100" valign="top" nowrap>Opera&ccedil;&atilde;o:</td>
            <td width="180" valign="top" nowrap>
                <select name="idOperacao" id="idOperacao" style="width:150px;" onchange="getOperacao(this.value);">
                    <option value="-1">-- Selecione --</option>
                    <option value="CRIAR">Criar novo grupo</option>
                    <logic:greaterThan name="FormFormulario" property="arrayGrupoCamposLength" value="0">
                    <option value="ALTERAR">Alterar grupo existente</option>
                    </logic:greaterThan>
                </select>
            </td>
            <td width="460" valign="top">
                <div id="divSelectList" style="display:none;">
                    Grupo de campos:
                    <html:select name="FormFormulario" property="idGrupoCampos" styleId="idGrupoCampos" style="width:180px;" onchange="getCamposSelecionados(this)">
                        <html:option value="-1">-- Selecione --</html:option>
                        <html:options collection="arrayGrupoCampos" property="idGrupoCampos" labelProperty="nmGrupoCampos"/>
                    </html:select>
                </div>
                <div id="divTextField" <logic:lessThan name="FormFormulario" property="arrayGrupoCamposLength" value="1">style="display:block;"</logic:lessThan><logic:greaterThan name="FormFormulario" property="arrayGrupoCamposLength" value="0">style="display:none;"</logic:greaterThan>>
                    Nome do grupo:
                    <html:text name="FormFormulario" property="nmGrupoCampos" styleId="nmGrupoCampos" style="width:180px;margin-left:13px;" onkeyup="controlNome(this,'up')" onblur="controlNome(this,'blur')" onfocus="this.focus();this.value=this.value" />
                </div>
            </td>
        </tr>
        <tr>
            <td colspan="3" valign="top" style="padding-top:10px;">

                <div id="divConteudoAbas" style="display:none">
                    <table width="735" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <td valign="top">
                                <vivo:abaGrupo id="btAba" width="200" height="10" styleClass="abatexto">
                                    <vivo:abaItem id="bt01" onclick="abaSelected(btAba, bt01);getOperacao($('idOperacao').value);" value="Campos/Valores" select="S"/>
                                    <vivo:abaItem id="bt02" onclick="abaSelected(btAba, bt02);CarregaAba(this.id);" value="Relacionar"/>
                                </vivo:abaGrupo>
                            </td>
                        </tr>
                        <tr>
                            <td height="305" valign="top" style="padding-top:10px;border:1px solid #adadad;">
                                <iframe width="735" height="293" id="iframeConteudoAbas" name="iframeConteudoAbas" frameborder="0" scrolling="auto" marginwidth="0" marginheight="0"></iframe>
                            </td>
                        </tr>
                    </table>
                </div>

            </td>
        </tr>
    </table>
    
    </form>

    <vivo:alert atributo="msg" scope="request" />

<!--/acesso:controlHiddenItem-->
</body>
</html>