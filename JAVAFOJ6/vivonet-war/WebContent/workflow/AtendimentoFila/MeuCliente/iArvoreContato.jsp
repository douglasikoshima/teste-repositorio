<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>

<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="filaMeuClienteFiltrosForm"/>
<bean:define id="scriptArvore" name="form" property="scriptArvore" />

<html>
<head>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/xtree.js"></script>
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/xtree.css" />
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/frontoffice.css" />
</head>
<body>

<form action="expandeArvoreContato.do" name="filaMeuClienteFiltrosForm" method="post" tagId="formArvore" id="formArvore">
    <script type="text/javascript">
        <%=scriptArvore%>
    </script>
    <table width="100%" cellspacing="0" cellpadding="0" border="0">
        <tr>
            <td align="right">
                <vivo:botao id="btSelecionar" width="100px" height="15px" value="Selecionar" styleClass="btTemplate" onclick="selecionar();"/>
            </td>
            <td align="left">
                <vivo:botao id="btCancelar" width="100px" height="15px" value="Cancelar" styleClass="btTemplate" onclick="cancelar();"/>
            </td>
        </tr>
    </table>
    <input type="hidden" name="idContato" />
    <iframe name="ifrmArvoreContato" style="visibility:hidden;" width="1" height="1"></iframe>
</form>

<script type="text/javascript">

    var idContatoSel = '';
    var dscContatoSel = '';

    function selecionar() {
        if (idContatoSel == '') { alert('Nenhum item (folha) da árvore de contato foi selecionado!');}
        else {
            parent.$('idContato').value = idContatoSel;
            parent.$('dsContato').value = dscContatoSel;
            parent.dvArvore.style.display = 'none';
        }
    }

    function cancelar() {
        parent.dvArvore.style.display = 'none';
        parent.ativar_combos();
    }

    function selecionaContato(idContato, inFolha, dscContato, idPai) {

        if(idPai!="PAI"){
            if(inFolha == 1) {
                idContatoSel=idContato;
                dscContatoSel=dscContato;
            } else {
                idContatoSel='';
                dscContatoSel='';
                expandirNo(idContato);
            }
        }
    }

    function itemDblClick(idContato, inFolha, dscContato) {
        idContatoSel = idContato;
        dscContatoSel = dscContato;
        selecionar();
    }

    function expandirNo(idContato){
        if (!tree.getSelected().isAddEnabled()) { return; }
        if (top.frameApp.dvAnimarAguarde!=null) top.frameApp.startAnimation();
        document.forms[0].target="ifrmArvoreContato";
        document.forms[0].action="expandeArvoreContato.do?IDCONTATO="+idContato;
        document.forms[0].submit();
    }

    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
</script>
</body>
</html>