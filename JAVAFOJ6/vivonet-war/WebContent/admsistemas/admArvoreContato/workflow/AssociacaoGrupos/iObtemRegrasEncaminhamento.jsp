<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<bean:define id="Form"         name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="associacaoGrupoForm" />
<bean:define id="ClienteDisp"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="associacaoGrupoForm.assosiacaoGrupoVariaveisVO.assosiacaoGrupoVariaveisDisponiveisVO.tipoClienteVOArray" />
<bean:define id="ClienteAssoc" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="associacaoGrupoForm.assosiacaoGrupoVariaveisVO.assosiacaoGrupoVariaveisAssosiadosVO.tipoClienteVOArray" />
<bean:define id="LinhaDisp"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="associacaoGrupoForm.assosiacaoGrupoVariaveisVO.assosiacaoGrupoVariaveisDisponiveisVO.tipoLinhaVOArray" />
<bean:define id="LinhaAssoc" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="associacaoGrupoForm.assosiacaoGrupoVariaveisVO.assosiacaoGrupoVariaveisAssosiadosVO.tipoLinhaVOArray" />
<bean:define id="SegmenDisp"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="associacaoGrupoForm.assosiacaoGrupoVariaveisVO.assosiacaoGrupoVariaveisDisponiveisVO.segmentacaoVOArray" />
<bean:define id="SegmenAssoc"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="associacaoGrupoForm.assosiacaoGrupoVariaveisVO.assosiacaoGrupoVariaveisAssosiadosVO.segmentacaoVOArray" />
<bean:define id="CarterDisp"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="associacaoGrupoForm.assosiacaoGrupoVariaveisVO.assosiacaoGrupoVariaveisDisponiveisVO.carterizacaoVOArray" />
<bean:define id="CarterAssoc"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="associacaoGrupoForm.assosiacaoGrupoVariaveisVO.assosiacaoGrupoVariaveisAssosiadosVO.carterizacaoVOArray" />
<bean:define id="NatuDisp"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="associacaoGrupoForm.assosiacaoGrupoVariaveisVO.assosiacaoGrupoVariaveisDisponiveisVO.admNaturezaVOArray" />
<bean:define id="NatuAssoc"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="associacaoGrupoForm.assosiacaoGrupoVariaveisVO.assosiacaoGrupoVariaveisAssosiadosVO.admNaturezaVOArray" />
<html>
<head>
</head>
<body>
    <form name="usuarioEncaminhamentoFormIFrame" id="usuarioEncaminhamentoFormIFrame" method="post">
        <html:select name="Form" property="tipoClienteDisponivel" multiple="true" size="10">
            <html:options collection="ClienteDisp" property="codigo" labelProperty="descricao" /> 
        </html:select><br/><br/>
        
        <html:select  name="Form" property="tipoClienteAssociada" multiple="true" size="10">
            <html:options collection="ClienteAssoc" property="codigo" labelProperty="descricao" /> 
        </html:select><br/><br/>
        
        <html:select name="Form" property="tipoLinhaDisponivel" multiple="true" size="10">
            <html:options collection="LinhaDisp" property="id" labelProperty="descricao" /> 
        </html:select><br/><br/>
        
        <html:select  name="Form" property="tipoLinhaAssociada" multiple="true" size="10">
            <html:options collection="LinhaAssoc" property="id" labelProperty="descricao" /> 
        </html:select><br/><br/>
        
        <html:select name="Form" property="segmentacaoDisponivel" multiple="true" size="10">
            <html:options collection="SegmenDisp" property="idSegmentacao" labelProperty="descricao" /> 
        </html:select> <br/><br/>
        
        <html:select name="Form" property="segmentacaoAssociada" multiple="true" size="10">
            <html:options collection="SegmenAssoc" property="idSegmentacao" labelProperty="descricao" /> 
        </html:select> <br/><br/>       
        
        <html:select name="Form" property="carterizacaoDisponivel" multiple="true" size="10">
            <html:options collection="CarterDisp" property="idTipoCarteira" labelProperty="descricao" /> 
        </html:select>  <br/><br/>
        
        <html:select name="Form" property="carterizacaoAssociada" multiple="true" size="10">
            <html:options collection="CarterAssoc" property="idTipoCarteira" labelProperty="descricao" /> 
        </html:select> <br/><br/>  
        
        <html:select name="Form" property="naturezaDisponivel" multiple="true" size="10">
            <html:options collection="NatuDisp" property="idNatureza" labelProperty="dsNatureza" /> 
        </html:select> <br/><br/>    
        
        <html:select name="Form" property="naturezaAssociada" multiple="true" size="10">
            <html:options collection="NatuAssoc" property="idNatureza" labelProperty="dsNatureza" /> 
        </html:select><br/><br/>
    </form>
    
    <script>
        var aOptComponent = new Array(document.forms["usuarioEncaminhamentoFormIFrame"].elements["tipoClienteDisponivel"],
                                      document.forms["usuarioEncaminhamentoFormIFrame"].elements["tipoClienteAssociada"],
                                      document.forms["usuarioEncaminhamentoFormIFrame"].elements["tipoLinhaDisponivel"],
                                      document.forms["usuarioEncaminhamentoFormIFrame"].elements["tipoLinhaAssociada"],
                                      document.forms["usuarioEncaminhamentoFormIFrame"].elements["segmentacaoDisponivel"],
                                      document.forms["usuarioEncaminhamentoFormIFrame"].elements["segmentacaoAssociada"],
                                      document.forms["usuarioEncaminhamentoFormIFrame"].elements["carterizacaoDisponivel"],
                                      document.forms["usuarioEncaminhamentoFormIFrame"].elements["carterizacaoAssociada"],
                                      document.forms["usuarioEncaminhamentoFormIFrame"].elements["naturezaDisponivel"],
                                      document.forms["usuarioEncaminhamentoFormIFrame"].elements["naturezaAssociada"]);
                                      
        var aOptComponentsParent = new Array(parent.document.forms["0"].elements["tipoClienteDisponivel"],
                                             parent.document.forms["0"].elements["tipoClienteAssociada"],
                                             parent.document.forms["0"].elements["tipoLinhaDisponivel"],
                                             parent.document.forms["0"].elements["tipoLinhaAssociada"],
                                             parent.document.forms["0"].elements["segmentacaoDisponivel"],
                                             parent.document.forms["0"].elements["segmentacaoAssociada"],
                                             parent.document.forms["0"].elements["carterizacaoDisponivel"],
                                             parent.document.forms["0"].elements["carterizacaoAssociada"],
                                             parent.document.forms["0"].elements["naturezaDisponivel"],
                                             parent.document.forms["0"].elements["naturezaAssociada"]);                                      
        
                                 
       for(i = 0; i < 10; i++) {
           while(aOptComponentsParent[i].options.length != 0)
                aOptComponentsParent[i].options.remove(0);

           for( x = 0; x < aOptComponent[i].options.length; x++ ) {
                oOption = parent.document.createElement("OPTION");               
                aOptComponentsParent[i].options.add(oOption);
                oOption.innerText = aOptComponent[i].options(x).text;
                oOption.value     = aOptComponent[i].options(x).value;            
            }  
        }                                     
        
        parent.document.forms["0"].elements["grupo"].value='<bean:write name="Form" property="grupo"/>';
           
        //Desliga animação
        if( top.frameApp.dvAnimarAguarde != null ){
            top.frameApp.stopAnimation();
        }  
    </script>                                                     
</body>
</html>
