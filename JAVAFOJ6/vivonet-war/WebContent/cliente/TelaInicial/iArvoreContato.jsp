<!--
Módulo.....: Gestão de Processos
Caso de Uso: Atendimento Fila
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
   
   
<script src="<%=request.getContextPath()%>/resources/scripts/xtree.js"></script>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/xtree.css">
<link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
<script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>


<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relacionamentoForm"/>
<bean:define id="scriptArvore" name="form" property="scriptArvore"/>


<form action="begin.do" name="formArvore" id="formArvore" method="post">
    <script language="javascript">
        <%=scriptArvore%>
    </script>
    <table  width="100%" cellspacing="0" cellpadding="0" border="0">
        <tr>
            <td align="right">
            <vivo:botao id="btSelecionar" width="100px" height="15px" value="Selecionar" styleClass="btTemplate" onclick="selecionar();"/>
            </td>
            <td align="left"><vivo:botao id="btCancelar" width="100px" height="15px" value="Cancelar" styleClass="btTemplate" onclick="cancelar();"/></td>
        </tr>
    </table>
    <iframe name="ifrmArvoreContato" style="visibility:hidden;" width="1" height="1"></iframe>
</form>
   
   
<script language="javascript">
    
    var idContatoSel = '';
    var dscContatoSel = '';
    
    function selecionar() {
        if(idContatoSel == '') {
            alert('Nenhum item (folha) da árvore de contato foi selecionado!');
        }
        else {
            parent.ativar_combos();
            parent.document.getElementById('idContato').value = idContatoSel;
            parent.document.getElementById('contato').value = dscContatoSel;
            parent.dvArvore.style.display = 'none';
        }
    }
    
    function cancelar() {
        parent.ativar_combos();    
        parent.dvArvore.style.display = 'none';
    }
    
    function selecionaContato(idContato, inFolha, dscContato, idPai) {

        if(idPai!="PAI"){
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
        document.forms[0].target = "ifrmArvoreContato";
        document.forms[0].action = "expandeArvoreContato.do?IDCONTATO="+idContato;
        document.forms[0].method = "POST";
        document.forms[0].submit(); 
    }
    
    //Fim animação
    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
    
</script>
