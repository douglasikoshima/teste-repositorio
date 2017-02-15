<!--
Módulo.....: Gestão de Processos
Caso de Uso: Atendimento Fila
Versão.....: $Author: a5112274 $ - $Revision: 1.1 $ - $Date: 2011/04/25 19:36:41 $
-->

<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
   
<%--<acesso:controlInitEnv/>--%>

<script src="<%=request.getContextPath()%>/resources/scripts/xtree.js"></script>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/xtree.css">
<link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
<script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>

<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relatorioForm"/>
<bean:define id="scriptArvore" name="form" property="scriptArvore"/>

<%--<acesso:controlHiddenItem nomeIdentificador="wor_iac_verpagina">--%>
<form name="formArvore">
    <script language="javascript">
        <%=scriptArvore%>
    </script>
    <table  width="100%" cellspacing="0" cellpadding="0" border="0">
        <tr>
            <td align="right">
            <%--<acesso:controlHiddenItem nomeIdentificador="wor_iac_selecionar">--%>
            <vivo:botao id="btSelecionar" width="100px" height="15px" value="Selecionar" styleClass="btTemplate" onclick="selecionar();"/>
            <%--</acesso:controlHiddenItem>--%>
            </td>
        </tr>
    </table>
    <iframe name="ifrmArvoreContato" style="visibility:hidden;" width="1" height="1"></iframe>
    <input type=hidden name="contatoSel">
</form>
   
<%--</acesso:controlHiddenItem>--%>

<script language="javascript">
    
    var idContatoSel = '';
    var dscContatoSel = '';
    
    function selecionar() {
        if(idContatoSel == '') {
            alert('Nenhum item (folha) da árvore de contato foi selecionado!');
        }
        else {
            parent.document.forms[0].contatoSel.value = idContatoSel;
            parent.document.forms[0].textoContato.value = dscContatoSel;
            parent.dvArvore.style.display = 'none';
        }
    }
    
    function cancelar() {
        parent.dvArvore.style.display = 'none';
    }
    
    function selecionaContato(idContato, inFolha, dscContato) {
        if(inFolha == 1) {
            idContatoSel = idContato;
            dscContatoSel = dscContato;
        }
        else {
            idContatoSel = '';
            dscContatoSel = '';
            expandirNo(idContato);
        }
        
    }
    
    function itemDblClick(idContato, inFolha, dscContato) {
        idContatoSel=idContato;
        dscContatoSel=dscContato;
        selecionar();
    }
        
    function expandirNo(idContato){
        if (!tree.getSelected().isAddEnabled()) {
            return;
        }

        //Liga animação
        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
        
        document.forms[0].contatoSel.value = idContato;
        document.forms[0].target = "ifrmArvoreContato";
        document.forms[0].action = "expandeArvoreContato.do";
        document.forms[0].submit(); 
    }
    
    //Fim animação
    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
    
</script>
