<!--
Módulo.....: Gestão de Sistemas
Caso de Uso: Configurar Grupo de Usuário com Regras de Encaminhamento
Versão.....: $Author: a5112272 $ - $Revision: 1.2 $ - $Date: 2011/05/25 14:01:46 $
-->
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<bean:define id="Form"         name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="usuarioEncaminhamentoForm" />
<bean:define id="ClienteDisp"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="usuarioEncaminhamentoForm.regrasEncaminhamentoVO.regrasEncaminhamentoDisponivelVO.tipoClienteVOArray" />
<bean:define id="ClienteAssoc" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="usuarioEncaminhamentoForm.regrasEncaminhamentoVO.regrasEncaminhamentoSelecionadoVO.tipoClienteVOArray" />
<bean:define id="SegmenDisp"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="usuarioEncaminhamentoForm.regrasEncaminhamentoVO.regrasEncaminhamentoDisponivelVO.segmentacaoVOArray" />
<bean:define id="SegmenAssoc"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="usuarioEncaminhamentoForm.regrasEncaminhamentoVO.regrasEncaminhamentoSelecionadoVO.segmentacaoVOArray" />
<bean:define id="CarterDisp"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="usuarioEncaminhamentoForm.regrasEncaminhamentoVO.regrasEncaminhamentoDisponivelVO.carterizacaoVOArray" />
<bean:define id="CarterAssoc"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="usuarioEncaminhamentoForm.regrasEncaminhamentoVO.regrasEncaminhamentoSelecionadoVO.carterizacaoVOArray" />
<bean:define id="ProcedDisp"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="usuarioEncaminhamentoForm.regrasEncaminhamentoVO.regrasEncaminhamentoDisponivelVO.procedenciaVOArray" />
<bean:define id="ProcedAssoc"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="usuarioEncaminhamentoForm.regrasEncaminhamentoVO.regrasEncaminhamentoSelecionadoVO.procedenciaVOArray" />
<html>
<head>
</head>
<body>
    <form name="usuarioEncaminhamentoFormIFrame">
        <html:select name="Form" property="tipoClienteDisponivel" multiple="true" size="10">
            <html:options collection="ClienteDisp" property="codigo" labelProperty="descricao" /> 
        </html:select><br/><br/>
        
        <html:select  name="Form" property="tipoClienteAssociada" multiple="true" size="10">
            <html:options collection="ClienteAssoc" property="codigo" labelProperty="descricao" /> 
        </html:select><br/><br/>
        
        <html:select name="Form" property="segmentacaoDisponivel" multiple="true" size="10">
            <html:options collection="SegmenDisp" property="codigo" labelProperty="descricao" /> 
        </html:select> <br/><br/>
        
        <html:select name="Form" property="segmentacaoAssociada" multiple="true" size="10">
            <html:options collection="SegmenAssoc" property="codigo" labelProperty="descricao" /> 
        </html:select> <br/><br/>       
        
        <html:select name="Form" property="carterizacaoDisponivel" multiple="true" size="10">
            <html:options collection="CarterDisp" property="codigo" labelProperty="descricao" /> 
        </html:select>  <br/><br/>
        
        <html:select name="Form" property="carterizacaoAssociada" multiple="true" size="10">
            <html:options collection="CarterAssoc" property="codigo" labelProperty="descricao" /> 
        </html:select> <br/><br/>  
        
        <html:select name="Form" property="procedenciaDisponivel" multiple="true" size="10">
            <html:options collection="ProcedDisp" property="codigo" labelProperty="descricao" /> 
        </html:select> <br/><br/>    
        
        <html:select name="Form" property="procedenciaAssociada" multiple="true" size="10">
            <html:options collection="ProcedAssoc" property="codigo" labelProperty="descricao" /> 
        </html:select><br/><br/>
    </form>
    
    <script>
        var aOptComponent = new Array(document.forms["usuarioEncaminhamentoFormIFrame"].elements["tipoClienteDisponivel"],
                                      document.forms["usuarioEncaminhamentoFormIFrame"].elements["tipoClienteAssociada"],
                                      document.forms["usuarioEncaminhamentoFormIFrame"].elements["segmentacaoDisponivel"],
                                      document.forms["usuarioEncaminhamentoFormIFrame"].elements["segmentacaoAssociada"],
                                      document.forms["usuarioEncaminhamentoFormIFrame"].elements["carterizacaoDisponivel"],
                                      document.forms["usuarioEncaminhamentoFormIFrame"].elements["carterizacaoAssociada"],
                                      document.forms["usuarioEncaminhamentoFormIFrame"].elements["procedenciaDisponivel"],
                                      document.forms["usuarioEncaminhamentoFormIFrame"].elements["procedenciaAssociada"]);
                                      
        var aOptComponentsParent = new Array(parent.document.forms["usuarioEncaminhamentoForm"].elements["tipoClienteDisponivel"],
                                             parent.document.forms["usuarioEncaminhamentoForm"].elements["tipoClienteAssociada"],
                                             parent.document.forms["usuarioEncaminhamentoForm"].elements["segmentacaoDisponivel"],
                                             parent.document.forms["usuarioEncaminhamentoForm"].elements["segmentacaoAssociada"],
                                             parent.document.forms["usuarioEncaminhamentoForm"].elements["carterizacaoDisponivel"],
                                             parent.document.forms["usuarioEncaminhamentoForm"].elements["carterizacaoAssociada"],
                                             parent.document.forms["usuarioEncaminhamentoForm"].elements["procedenciaDisponivel"],
                                             parent.document.forms["usuarioEncaminhamentoForm"].elements["procedenciaAssociada"]);                                      
        
                                 
       for(i = 0; i < 8; i++) {
           while(aOptComponentsParent[i].options.length != 0)
                aOptComponentsParent[i].options.remove(0);

           for( x = 0; x < aOptComponent[i].options.length; x++ ) {
                oOption = parent.document.createElement("OPTION");               
                aOptComponentsParent[i].options.add(oOption);
                oOption.innerText = aOptComponent[i].options(x).text;
                oOption.value     = aOptComponent[i].options(x).value;            
            }  
        }                                     

        //Desliga animação
        if( top.dvAnimarAguarde != null ) top.stopAnimation();
    </script>                                                     
</body>
</html>
