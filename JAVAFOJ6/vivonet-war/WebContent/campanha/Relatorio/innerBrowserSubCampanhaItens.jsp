<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
 
<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relEfetividadeActionForm" />
<bean:define id="aCanal" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relEfetividadeActionForm.listaCanal" />
<bean:define id="aMotivo" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relEfetividadeActionForm.listaMotivo" />
<bean:define id="aPerfil" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relEfetividadeActionForm.listaPerfil" />
<bean:define id="aRegional" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relEfetividadeActionForm.listaRegional" />
<html>

    <head></head>
    <body>
        <form>
            <html:select name="Form" property="canalSelecionado">
                <option value=""></option>
                <html:options collection="aCanal" property="codigo" labelProperty="descricao"/> 
            </html:select>
            
            <html:select name="Form" property="motivoSelecionado">
                <option value=""></option>
                <html:options collection="aMotivo" property="codigo" labelProperty="sigla"/>                                                     
            </html:select>    
            
            <html:select name="Form" property="perfilSelecionado">
                <option value=""></option>
                <html:options collection="aPerfil" property="codigo" labelProperty="sigla"/> 
            </html:select>                    
            
            <html:select name="Form" property="regionalSelecionada">
                <option value=""></option>
                <html:options collection="aRegional" property="idUFOperadora" labelProperty="dsUFOperadora"/> 
            </html:select>            
		</form>
		
		
    <SCRIPT FOR=window EVENT=onload>    
        if(parent.document.forms["relEfetividadeActionForm"])
        {    
            copiarCombo(document.forms[0].elements["canalSelecionado"], parent.document.forms["relEfetividadeActionForm"].elements["canalSelecionado"]);            
            copiarCombo(document.forms[0].elements["motivoSelecionado"], parent.document.forms["relEfetividadeActionForm"].elements["motivoSelecionado"]);            
            copiarCombo(document.forms[0].elements["perfilSelecionado"], parent.document.forms["relEfetividadeActionForm"].elements["perfilSelecionado"]);            
            copiarCombo(document.forms[0].elements["regionalSelecionada"], parent.document.forms["relEfetividadeActionForm"].elements["regionalSelecionada"]);                        
        }      
        top.frameApp.oculta_div();
        parent.redrawTable(this);  
    </script>    
    
    <script>    
    function copiarCombo(comboOrigem, comboDestino)
    {   
        var aComboOrigem  = new Array(comboOrigem);
        
        var aComboDestino = new Array(comboDestino);    
        
        
        for(i = 0; i < aComboDestino.length; i++) 
        {
            
            while(aComboDestino[i].options.length != 0)
            {
                aComboDestino[i].options.remove(0);
            }
                
            for( x = 0; x < aComboOrigem[i].options.length; x++ ) 
            {
                aComboOrigem[i].options[x].selected;
                oOption = document.createElement("OPTION");               
                aComboDestino[i].options.add(oOption);
                oOption.innerText    = aComboOrigem[i].options(x).text;
                oOption.value        = aComboOrigem[i].options(x).value;            
                oOption.selected     = aComboOrigem[i].options(x).selected;
            }  
        }
    }                                 
    </script>  
    </body>
</html>
