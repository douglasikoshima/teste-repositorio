?<!--
Módulo.....: Gestão de Sistemas
Caso de Uso: Configurar Grupo de Usuário com Regras de Encaminhamento
Versão.....: $Author: a5112272 $ - $Revision: 1.2 $ - $Date: 2011/05/25 14:01:46 $
-->
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>



<bean:define id="Form"              name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="usuarioEncaminhamentoForm" />
<bean:define id="LinhaDisp"         name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="usuarioEncaminhamentoForm.regrasEncaminhamentoVO.regrasEncaminhamentoDisponivelVO.tipoLinhaVOArray" />
<bean:define id="LinhaAssoc"        name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="usuarioEncaminhamentoForm.regrasEncaminhamentoVO.regrasEncaminhamentoSelecionadoVO.tipoLinhaVOArray" />
<bean:define id="SegmenDisp"        name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="usuarioEncaminhamentoForm.regrasEncaminhamentoVO.regrasEncaminhamentoDisponivelVO.segmentacaoVOArray" />
<bean:define id="SegmenAssoc"       name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="usuarioEncaminhamentoForm.regrasEncaminhamentoVO.regrasEncaminhamentoSelecionadoVO.segmentacaoVOArray" />
<bean:define id="CarterDisp"        name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="usuarioEncaminhamentoForm.regrasEncaminhamentoVO.regrasEncaminhamentoDisponivelVO.carterizacaoVOArray" />
<bean:define id="CarterAssoc"       name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="usuarioEncaminhamentoForm.regrasEncaminhamentoVO.regrasEncaminhamentoSelecionadoVO.carterizacaoVOArray" />
<bean:define id="ProcedDisp"        name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="usuarioEncaminhamentoForm.regrasEncaminhamentoVO.regrasEncaminhamentoDisponivelVO.procedenciaVOArray" />
<bean:define id="ProcedAssoc"       name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="usuarioEncaminhamentoForm.regrasEncaminhamentoVO.regrasEncaminhamentoSelecionadoVO.procedenciaVOArray" />

<bean:define id="canalAssoc"        name="Form" property="regrasEncaminhamentoVO.regrasEncaminhamentoSelecionadoVO.canalVOArray" />
<bean:define id="canalDisp"         name="Form" property="regrasEncaminhamentoVO.regrasEncaminhamentoDisponivelVO.canalVOArray" />

<bean:define id="tipoClienteAssoc"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="usuarioEncaminhamentoForm.regrasEncaminhamentoVO.regrasEncaminhamentoSelecionadoVO.tipoClienteVOArray" />
<bean:define id="tipoClienteDisp"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="usuarioEncaminhamentoForm.regrasEncaminhamentoVO.regrasEncaminhamentoDisponivelVO.tipoClienteVOArray" />
<bean:define id="tipoPessoaAssoc"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="usuarioEncaminhamentoForm.regrasEncaminhamentoVO.regrasEncaminhamentoSelecionadoVO.tipoPessoaVOArray" />
<bean:define id="tipoPessoaDisp"    name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="usuarioEncaminhamentoForm.regrasEncaminhamentoVO.regrasEncaminhamentoDisponivelVO.tipoPessoaVOArray" />
<bean:define id="tipoAberturaAssoc" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="usuarioEncaminhamentoForm.regrasEncaminhamentoVO.regrasEncaminhamentoSelecionadoVO.grupoVOArray" />
<bean:define id="tipoAberturaDisp"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="usuarioEncaminhamentoForm.regrasEncaminhamentoVO.regrasEncaminhamentoDisponivelVO.grupoVOArray" />

<bean:define id="regionalDisp"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="usuarioEncaminhamentoForm.regrasEncaminhamentoVO.regrasEncaminhamentoDisponivelVO.regionalVOArray" />
<bean:define id="regionalAssoc"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="usuarioEncaminhamentoForm.regrasEncaminhamentoVO.regrasEncaminhamentoSelecionadoVO.regionalVOArray" />

<html>
<head>
</head>
<body>
<acesso:controlHiddenItem nomeIdentificador="adm_iore_verpagina">
    <form name="usuarioEncaminhamentoFormIFrame">
        <html:select name="Form" property="tipoLinhaDisponivel" multiple="true" size="10">
            <html:options collection="LinhaDisp" property="id" labelProperty="descricao" /> 
        </html:select><br/><br/>
        
        <html:select  name="Form" property="tipoLinhaAssociada" multiple="true" size="10">
            <html:options collection="LinhaAssoc" property="id" labelProperty="descricao" /> 
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
        
        <html:select name="Form" property="tipoClienteAssociada" multiple="true" size="10">
            <html:options collection="tipoClienteAssoc" property="codigo" labelProperty="descricao" /> 
        </html:select><br/><br/>

        <html:select name="Form" property="tipoClienteDisponivel" multiple="true" size="10">
            <html:options collection="tipoClienteDisp" property="codigo" labelProperty="descricao" /> 
        </html:select><br/><br/>

        <html:select name="Form" property="tipoPessoaAssociada" multiple="true" size="10">
            <html:options collection="tipoPessoaAssoc" property="idtipopessoaArray[0]" labelProperty="dstipopessoaArray[0]" /> 
        </html:select><br/><br/>

        <html:select name="Form" property="tipoPessoaDisponivel" multiple="true" size="10">
            <html:options collection="tipoPessoaDisp" property="idtipopessoaArray[0]" labelProperty="dstipopessoaArray[0]" /> 
        </html:select><br/><br/>

        <html:select name="Form" property="tipoAberturaAssociada" multiple="true" size="10">
            <html:options collection="tipoAberturaAssoc" property="codigo" labelProperty="descricao" /> 
        </html:select><br/><br/>

        <html:select name="Form" property="tipoAberturaDisponivel" multiple="true" size="10">
            <html:options collection="tipoAberturaDisp" property="codigo" labelProperty="descricao" /> 
        </html:select><br/><br/>
        
        <html:select name="Form" property="canalAssociado" multiple="true" size="10">
            <html:options collection="canalAssoc" property="idCanal" labelProperty="descricao" /> 
        </html:select><br/><br/>
        
        <html:select name="Form" property="canalDisponivel" multiple="true" size="10">
            <html:options collection="canalDisp" property="idCanal" labelProperty="descricao" /> 
        </html:select><br/><br/>

        <html:select name="Form" property="regionalAssociada" multiple="true" size="10">
            <html:options collection="regionalAssoc" property="idRegional" labelProperty="descricao" /> 
        </html:select><br/><br/>

        <html:select name="Form" property="regionalDisponivel" multiple="true" size="10">
            <html:options collection="regionalDisp" property="idRegional" labelProperty="descricao" /> 
        </html:select><br/><br/>

        
    </form>
    
    <script>
        var aOptComponent = new Array(document.forms["usuarioEncaminhamentoFormIFrame"].elements["tipoLinhaDisponivel"],
                                      document.forms["usuarioEncaminhamentoFormIFrame"].elements["tipoLinhaAssociada"],
                                      document.forms["usuarioEncaminhamentoFormIFrame"].elements["segmentacaoDisponivel"],
                                      document.forms["usuarioEncaminhamentoFormIFrame"].elements["segmentacaoAssociada"],
                                      document.forms["usuarioEncaminhamentoFormIFrame"].elements["carterizacaoDisponivel"],
                                      document.forms["usuarioEncaminhamentoFormIFrame"].elements["carterizacaoAssociada"],
                                      document.forms["usuarioEncaminhamentoFormIFrame"].elements["procedenciaDisponivel"],
                                      document.forms["usuarioEncaminhamentoFormIFrame"].elements["procedenciaAssociada"],
                                      document.forms["usuarioEncaminhamentoFormIFrame"].elements["tipoClienteDisponivel"],
                                      document.forms["usuarioEncaminhamentoFormIFrame"].elements["tipoClienteAssociada"],
                                      document.forms["usuarioEncaminhamentoFormIFrame"].elements["tipoPessoaDisponivel"],
                                      document.forms["usuarioEncaminhamentoFormIFrame"].elements["tipoPessoaAssociada"],
                                      document.forms["usuarioEncaminhamentoFormIFrame"].elements["tipoAberturaDisponivel"],
                                      document.forms["usuarioEncaminhamentoFormIFrame"].elements["tipoAberturaAssociada"],
                                      document.forms["usuarioEncaminhamentoFormIFrame"].elements["canalDisponivel"],
                                      document.forms["usuarioEncaminhamentoFormIFrame"].elements["canalAssociado"],                                      
                                      document.forms["usuarioEncaminhamentoFormIFrame"].elements["regionalAssociada"],
                                      document.forms["usuarioEncaminhamentoFormIFrame"].elements["regionalDisponivel"]);
                                      
        var aOptComponentsParent = new Array(parent.document.forms["usuarioEncaminhamentoForm"].elements["tipoLinhaDisponivel"],
                                             parent.document.forms["usuarioEncaminhamentoForm"].elements["tipoLinhaAssociada"],
                                             parent.document.forms["usuarioEncaminhamentoForm"].elements["segmentacaoDisponivel"],
                                             parent.document.forms["usuarioEncaminhamentoForm"].elements["segmentacaoAssociada"],
                                             parent.document.forms["usuarioEncaminhamentoForm"].elements["carterizacaoDisponivel"],
                                             parent.document.forms["usuarioEncaminhamentoForm"].elements["carterizacaoAssociada"],
                                             parent.document.forms["usuarioEncaminhamentoForm"].elements["procedenciaDisponivel"],
                                             parent.document.forms["usuarioEncaminhamentoForm"].elements["procedenciaAssociada"],
                                             parent.document.forms["usuarioEncaminhamentoForm"].elements["tipoClienteDisponivel"],
                                             parent.document.forms["usuarioEncaminhamentoForm"].elements["tipoClienteAssociada"],
                                             parent.document.forms["usuarioEncaminhamentoForm"].elements["tipoPessoaDisponivel"],
                                             parent.document.forms["usuarioEncaminhamentoForm"].elements["tipoPessoaAssociada"],
                                             parent.document.forms["usuarioEncaminhamentoForm"].elements["tipoAberturaDisponivel"],
                                             parent.document.forms["usuarioEncaminhamentoForm"].elements["tipoAberturaAssociada"],
                                             parent.document.forms["usuarioEncaminhamentoForm"].elements["canalDisponivel"],
                                             parent.document.forms["usuarioEncaminhamentoForm"].elements["canalAssociado"],
                                             parent.document.forms["usuarioEncaminhamentoForm"].elements["regionalAssociada"],
                                             parent.document.forms["usuarioEncaminhamentoForm"].elements["regionalDisponivel"]);
                                             
             
                                 
       for(i = 0; i < aOptComponent.length; i++) {
           while(aOptComponentsParent[i].options.length != 0)
                aOptComponentsParent[i].options.remove(0);

           for( x = 0; x < aOptComponent[i].options.length; x++ ) {
                oOption = parent.document.createElement("OPTION");               
                aOptComponentsParent[i].options.add(oOption);
                oOption.innerText = aOptComponent[i].options(x).text;
                oOption.value     = aOptComponent[i].options(x).value;            
            }  
        }

        //Fim animação
        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();

    </script>  
    
    
</acesso:controlHiddenItem>                                                   
</body>
</html>
