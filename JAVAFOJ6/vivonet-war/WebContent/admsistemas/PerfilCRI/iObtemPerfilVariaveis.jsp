<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="perfilForm" />

<bean:define id="LinhaDisponivel"  name="Form" property="perfilVariaveisVO.perfilVariaveisDisponiveisVO.tipoLinhaVOArray" />
<bean:define id="LinhaAssociada" name="Form" property="perfilVariaveisVO.perfilVariaveisAssociadasVO.tipoLinhaVOArray" />

<bean:define id="SegmentacaoDisponivel" name="Form" property="perfilVariaveisVO.perfilVariaveisDisponiveisVO.segmentacaoVOArray" />
<bean:define id="SegmentacaoAssociada" name="Form" property="perfilVariaveisVO.perfilVariaveisAssociadasVO.segmentacaoVOArray" />

<bean:define id="CarteiraDisponivel" name="Form" property="perfilVariaveisVO.perfilVariaveisDisponiveisVO.carterizacaoVOArray" />
<bean:define id="CarteiraAssociada" name="Form" property="perfilVariaveisVO.perfilVariaveisAssociadasVO.carterizacaoVOArray" />

<bean:define id="ProcedenciaDisponivel" name="Form" property="perfilVariaveisVO.perfilVariaveisDisponiveisVO.procedenciaVOArray" />
<bean:define id="ProcedenciaAssociada" name="Form" property="perfilVariaveisVO.perfilVariaveisAssociadasVO.procedenciaVOArray" />

<bean:define id="NaturezaDisponivel" name="Form" property="perfilVariaveisVO.perfilVariaveisDisponiveisVO.admNaturezaVOArray" />
<bean:define id="NaturezaAssociada" name="Form" property="perfilVariaveisVO.perfilVariaveisAssociadasVO.admNaturezaVOArray" />

<bean:define id="ClienteDisponivel" name="Form" property="perfilVariaveisVO.perfilVariaveisDisponiveisVO.tipoClienteVOArray" />
<bean:define id="ClienteAssociada" name="Form" property="perfilVariaveisVO.perfilVariaveisAssociadasVO.tipoClienteVOArray" />

<bean:define id="GrupoDisponivel" name="Form" property="perfilVariaveisVO.perfilVariaveisDisponiveisVO.admGrupoAberturaVOArray" />
<bean:define id="GrupoAssociada" name="Form" property="perfilVariaveisVO.perfilVariaveisAssociadasVO.admGrupoAberturaVOArray" />

<bean:define id="CanalDisponivel" name="Form" property="perfilVariaveisVO.perfilVariaveisDisponiveisVO.canalVOArray" />
<bean:define id="CanalAssociada" name="Form" property="perfilVariaveisVO.perfilVariaveisAssociadasVO.canalVOArray" />

<bean:define id="RegionalDisponivel" name="Form" property="perfilVariaveisVO.perfilVariaveisDisponiveisVO.admUFOperadoraSimplVOArray" />
<bean:define id="RegionalAssociada" name="Form" property="perfilVariaveisVO.perfilVariaveisAssociadasVO.admUFOperadoraSimplVOArray" />


<html>
<head>
</head>
<body>
    <form name="perfilVariaveisFormIFrame">
    
    
    
    <!--> -->
        <html:select name="Form" property="tipoLinhaDisponivel" multiple="true" size="6" style="width=150px">
            <html:options collection="LinhaDisponivel" property="id" labelProperty="descricao"/>
        </html:select>                    
        <html:select name="Form" property="tipoLinhaAssociada" multiple="true" size="6" style="width=150px">
            <html:options collection="LinhaAssociada" property="id" labelProperty="descricao"/>
        </html:select>    
    
        <html:select name="Form" property="segmentacaoDisponivel" multiple="true" size="6" style="width=150px">
            <html:options collection="SegmentacaoDisponivel" property="idSegmentacao" labelProperty="descricao" />
        </html:select>                 
        <html:select name="Form" property="segmentacaoAssociada" multiple="true" size="6" style="width=150px">
            <html:options collection="SegmentacaoAssociada" property="idSegmentacao" labelProperty="descricao" />
        </html:select>
        
        <html:select name="Form" property="carterizacaoDisponivel" multiple="true" size="6" style="width=150px">
            <html:options collection="CarteiraDisponivel" property="idTipoCarteira" labelProperty="descricao" />
        </html:select>                  
        <html:select name="Form" property="carterizacaoAssociada" multiple="true" size="6" style="width=150px">
            <html:options collection="CarteiraAssociada" property="idTipoCarteira"  labelProperty="descricao" />
        </html:select>     
            
        <html:select name="Form" property="procedenciaDisponivel" multiple="true" size="6" style="width=150px">
            <html:options collection="ProcedenciaDisponivel" property="idProcedencia" labelProperty="descricao" />
        </html:select>                  
        <html:select name="Form" property="procedenciaAssociada" multiple="true" size="6" style="width=150px">
            <html:options collection="ProcedenciaAssociada" property="idProcedencia"  labelProperty="descricao" />
        </html:select>
        
        <html:select name="Form" property="naturezaDisponivel" multiple="true" size="6" style="width=150px">
            <html:options collection="NaturezaDisponivel" property="idNatureza" labelProperty="dsNatureza" />
        </html:select>
        <html:select name="Form" property="naturezaAssociada" multiple="true" size="6" style="width=150px">
            <html:options collection="NaturezaAssociada" property="idNatureza" labelProperty="dsNatureza" />
        </html:select>        
        
        <html:select name="Form" property="tipoClienteDisponivel" multiple="true" size="6" style="width=150px">
            <html:options collection="ClienteDisponivel" property="codigo" labelProperty="descricao"/>
        </html:select>                 
        <html:select name="Form" property="tipoClienteAssociada" multiple="true" size="6" style="width=150px">
            <html:options collection="ClienteAssociada" property="codigo" labelProperty="descricao"/>
        </html:select>
        
        <html:select name="Form" property="canalDisponivel" multiple="true" size="6" style="width=150px">
            <html:options collection="CanalDisponivel" property="idCanal" labelProperty="descricao"/>
        </html:select>                
        <html:select name="Form" property="canalAssociada" multiple="true" size="6" style="width=150px">
            <html:options collection="CanalAssociada" property="idCanal" labelProperty="descricao"/>
        </html:select>
        
        <html:select name="Form" property="regionalDisponivel" multiple="true" size="6" style="width=150px">
            <html:options collection="RegionalDisponivel" property="idUFOperadora" labelProperty="dsUFOperadora"/>
        </html:select>              
        <html:select name="Form" property="regionalAssociada" multiple="true" size="6" style="width=150px">
            <html:options collection="RegionalAssociada" property="idUFOperadora" labelProperty="dsUFOperadora"/>
        </html:select>
        <!-- -->       
    </form>
    
    <script>
        var aOptComponent = new Array(document.forms["perfilVariaveisFormIFrame"].elements["tipoLinhaDisponivel"],
                                      document.forms["perfilVariaveisFormIFrame"].elements["tipoLinhaAssociada"],
                                      document.forms["perfilVariaveisFormIFrame"].elements["segmentacaoDisponivel"],
                                      document.forms["perfilVariaveisFormIFrame"].elements["segmentacaoAssociada"],
                                      document.forms["perfilVariaveisFormIFrame"].elements["carterizacaoDisponivel"],
                                      document.forms["perfilVariaveisFormIFrame"].elements["carterizacaoAssociada"],
                                      document.forms["perfilVariaveisFormIFrame"].elements["procedenciaDisponivel"],
                                      document.forms["perfilVariaveisFormIFrame"].elements["procedenciaAssociada"],
                                      document.forms["perfilVariaveisFormIFrame"].elements["naturezaDisponivel"],
                                      document.forms["perfilVariaveisFormIFrame"].elements["naturezaAssociada"],
                                      document.forms["perfilVariaveisFormIFrame"].elements["tipoClienteDisponivel"],
                                      document.forms["perfilVariaveisFormIFrame"].elements["tipoClienteAssociada"],
                                      document.forms["perfilVariaveisFormIFrame"].elements["canalDisponivel"],
                                      document.forms["perfilVariaveisFormIFrame"].elements["canalAssociada"],
                                      document.forms["perfilVariaveisFormIFrame"].elements["regionalDisponivel"],
                                      document.forms["perfilVariaveisFormIFrame"].elements["regionalAssociada"]);
                                      
        var aOptComponentsParent = new Array(parent.document.forms["0"].elements["tipoLinhaDisponivel"],
                                             parent.document.forms["0"].elements["tipoLinhaAssociada"],
                                             parent.document.forms["0"].elements["segmentacaoDisponivel"],
                                             parent.document.forms["0"].elements["segmentacaoAssociada"],
                                             parent.document.forms["0"].elements["carterizacaoDisponivel"],
                                             parent.document.forms["0"].elements["carterizacaoAssociada"],
                                             parent.document.forms["0"].elements["procedenciaDisponivel"],
                                             parent.document.forms["0"].elements["procedenciaAssociada"],
                                             parent.document.forms["0"].elements["naturezaDisponivel"],
                                             parent.document.forms["0"].elements["naturezaAssociada"],
                                             parent.document.forms["0"].elements["tipoClienteDisponivel"],
                                             parent.document.forms["0"].elements["tipoClienteAssociada"],
                                             parent.document.forms["0"].elements["canalDisponivel"],
                                             parent.document.forms["0"].elements["canalAssociada"],
                                             parent.document.forms["0"].elements["regionalDisponivel"],
                                             parent.document.forms["0"].elements["regionalAssociada"]);                                      
        
                                 
       for(i = 0; i < 16; i++) {
           while(aOptComponentsParent[i].options.length != 0)
                aOptComponentsParent[i].options.remove(0);

           for( x = 0; x < aOptComponent[i].options.length; x++ ) {
                oOption = parent.document.createElement("OPTION");               
                aOptComponentsParent[i].options.add(oOption);
                oOption.innerText = aOptComponent[i].options(x).text;
                oOption.value     = aOptComponent[i].options(x).value;            
            }  
        }                                     
        
        parent.document.forms["0"]["idPerfil"].value='<bean:write name="Form" property="idPerfil"/>';
        parent.document.forms["0"]["nmPerfil"].value='<bean:write name="Form" property="nmPerfil"/>';
           
        //Habilita botões
        parent.divNovo.style.display = 'none';
        
        if(parent.document.forms["0"]["nmPerfil"].value!=''){
            parent.divIncluir.style.display = 'none';
            parent.divAlterar.style.display = '';
        }
        else{
            parent.divIncluir.style.display = '';
            parent.divAlterar.style.display = 'none';
        }
        parent.divVariaveis.style.display = '';
        
        
        //Desliga animação
        if( top.frameApp.dvAnimarAguarde != null ){
            top.frameApp.stopAnimation();
        }  
    </script>                                                     
</body>
</html>
