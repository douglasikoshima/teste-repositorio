<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<bean:define id="Form"              name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaGruposForm" />
<bean:define id="LinhaDisp"         name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaGruposForm.regrasEncaminhamentoVO.regrasEncaminhamentoDisponivelVO.tipoLinhaVOArray" />
<bean:define id="LinhaAssoc"        name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaGruposForm.regrasEncaminhamentoVO.regrasEncaminhamentoSelecionadoVO.tipoLinhaVOArray" />
<bean:define id="SegmenDisp"        name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaGruposForm.regrasEncaminhamentoVO.regrasEncaminhamentoDisponivelVO.segmentacaoVOArray" />
<bean:define id="SegmenAssoc"       name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaGruposForm.regrasEncaminhamentoVO.regrasEncaminhamentoSelecionadoVO.segmentacaoVOArray" />
<bean:define id="CarterDisp"        name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaGruposForm.regrasEncaminhamentoVO.regrasEncaminhamentoDisponivelVO.carterizacaoVOArray" />
<bean:define id="CarterAssoc"       name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaGruposForm.regrasEncaminhamentoVO.regrasEncaminhamentoSelecionadoVO.carterizacaoVOArray" />
<bean:define id="ProcedDisp"        name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaGruposForm.regrasEncaminhamentoVO.regrasEncaminhamentoDisponivelVO.procedenciaVOArray" />
<bean:define id="ProcedAssoc"       name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaGruposForm.regrasEncaminhamentoVO.regrasEncaminhamentoSelecionadoVO.procedenciaVOArray" />
<bean:define id="canalAssoc"        name="Form" property="regrasEncaminhamentoVO.regrasEncaminhamentoSelecionadoVO.canalVOArray" />
<bean:define id="canalDisp"         name="Form" property="regrasEncaminhamentoVO.regrasEncaminhamentoDisponivelVO.canalVOArray" />
<bean:define id="tipoClienteAssoc"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaGruposForm.regrasEncaminhamentoVO.regrasEncaminhamentoSelecionadoVO.tipoClienteVOArray" />
<bean:define id="tipoClienteDisp"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaGruposForm.regrasEncaminhamentoVO.regrasEncaminhamentoDisponivelVO.tipoClienteVOArray" />
<bean:define id="tipoPessoaAssoc"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaGruposForm.regrasEncaminhamentoVO.regrasEncaminhamentoSelecionadoVO.tipoPessoaVOArray" />
<bean:define id="tipoPessoaDisp"    name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaGruposForm.regrasEncaminhamentoVO.regrasEncaminhamentoDisponivelVO.tipoPessoaVOArray" />
<bean:define id="tipoAberturaAssoc" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaGruposForm.regrasEncaminhamentoVO.regrasEncaminhamentoSelecionadoVO.grupoVOArray" />
<bean:define id="tipoAberturaDisp"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaGruposForm.regrasEncaminhamentoVO.regrasEncaminhamentoDisponivelVO.grupoVOArray" />
<bean:define id="regionalDisp"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaGruposForm.regrasEncaminhamentoVO.regrasEncaminhamentoDisponivelVO.regionalVOArray" />
<bean:define id="regionalAssoc"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaGruposForm.regrasEncaminhamentoVO.regrasEncaminhamentoSelecionadoVO.regionalVOArray" />

<html>
<head>
</head>
<body>
<!--acesso:controlHiddenItem nomeIdentificador="adm_iore_verpagina"-->
    <form name="listaGruposFormIFrame">

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
        var aOptComponent = new Array(document.forms["listaGruposFormIFrame"].elements["tipoLinhaDisponivel"],
                                      document.forms["listaGruposFormIFrame"].elements["tipoLinhaAssociada"],
                                      document.forms["listaGruposFormIFrame"].elements["segmentacaoDisponivel"],
                                      document.forms["listaGruposFormIFrame"].elements["segmentacaoAssociada"],
                                      document.forms["listaGruposFormIFrame"].elements["carterizacaoDisponivel"],
                                      document.forms["listaGruposFormIFrame"].elements["carterizacaoAssociada"],
                                      document.forms["listaGruposFormIFrame"].elements["procedenciaDisponivel"],
                                      document.forms["listaGruposFormIFrame"].elements["procedenciaAssociada"],
                                      document.forms["listaGruposFormIFrame"].elements["tipoClienteDisponivel"],
                                      document.forms["listaGruposFormIFrame"].elements["tipoClienteAssociada"],
                                      document.forms["listaGruposFormIFrame"].elements["tipoPessoaDisponivel"],
                                      document.forms["listaGruposFormIFrame"].elements["tipoPessoaAssociada"],
                                      document.forms["listaGruposFormIFrame"].elements["tipoAberturaDisponivel"],
                                      document.forms["listaGruposFormIFrame"].elements["tipoAberturaAssociada"],
                                      document.forms["listaGruposFormIFrame"].elements["canalDisponivel"],
                                      document.forms["listaGruposFormIFrame"].elements["canalAssociado"],                                      
                                      document.forms["listaGruposFormIFrame"].elements["regionalAssociada"],
                                      document.forms["listaGruposFormIFrame"].elements["regionalDisponivel"]);
                                      
        var aOptComponentsParent = new Array(parent.document.forms[0].elements["tipoLinhaDisponivel"],
                                             parent.document.forms[0].elements["tipoLinhaAssociada"],
                                             parent.document.forms[0].elements["segmentacaoDisponivel"],
                                             parent.document.forms[0].elements["segmentacaoAssociada"],
                                             parent.document.forms[0].elements["carterizacaoDisponivel"],
                                             parent.document.forms[0].elements["carterizacaoAssociada"],
                                             parent.document.forms[0].elements["procedenciaDisponivel"],
                                             parent.document.forms[0].elements["procedenciaAssociada"],
                                             parent.document.forms[0].elements["tipoClienteDisponivel"],
                                             parent.document.forms[0].elements["tipoClienteAssociada"],
                                             parent.document.forms[0].elements["tipoPessoaDisponivel"],
                                             parent.document.forms[0].elements["tipoPessoaAssociada"],
                                             parent.document.forms[0].elements["tipoAberturaDisponivel"],
                                             parent.document.forms[0].elements["tipoAberturaAssociada"],
                                             parent.document.forms[0].elements["canalDisponivel"],
                                             parent.document.forms[0].elements["canalAssociado"],
                                             parent.document.forms[0].elements["regionalAssociada"],
                                             parent.document.forms[0].elements["regionalDisponivel"]);
                                             
             
                                 
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
<!--/acesso:controlHiddenItem-->                                                   
</body>
</html>
