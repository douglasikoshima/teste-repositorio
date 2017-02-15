<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
   
<link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
<script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>

<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoDetalheForm"/>
<bean:define id="atdVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoDetalheForm.atendimentoDetalheVO.atendimentoVO"/>
<bean:define id="pessoaVO" name="atdVO" property="atendimentoPessoaVO"/>
<bean:define id="carterizacaoVO" name="pessoaVO" property="carterizacaoVO"/>
<bean:define id="segmentacaoVO" name="pessoaVO" property="segmentacaoVO"/>

<form action="processoAction" id="processoAction" name="processoAction" method="post">
    <table width="100%" height="50" cellspacing="5">
        <tr valign="top">
            <td width="15%" class="TableCellL3"><b>Nome:</b></td>
            <td width="35%" style="color:#006699;"><bean:write property="nome" name="pessoaVO"/></td>
            <td width="15%"></td>
            <td width="35%"></td>
        </tr>
        <tr valign="top">
            <td class="TableCellL3"><b>Segmentação:</b></td>
            <td style="color:#006699;"><bean:write name="segmentacaoVO" property="descricao"/></td>
        </tr>
        <tr valign="top">
            <td class="TableCellL3"><b>Carterização:</b></td>
            <td style="color:#006699;"><bean:write name="carterizacaoVO" property="descricao"/></td>
        </tr>
    </table>
    <script>
        document.body.style.backgroundColor = '#ededed';
    </script>
</form>
   
