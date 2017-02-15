<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<bean:define id="planoVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formDadosPesquisa.planoVO"/>
<bean:define id="formDadosPesquisa" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formDadosPesquisa"/>                 

<html>
    <head>
        <title>            
        </title>
        

<script>
    function carrega()
    { 
        if(parent.document.forms["formDadosPesquisa"])
        {
        
            var aOptComponent        = new Array(document.forms[0].elements["plano"]);
        
            var aOptComponentsParent = new Array(parent.document.forms["formDadosPesquisa"].elements["plano"]);                                      
        
        
        
            for(i = 0; i < aOptComponentsParent.length; i++) 
            {
        
                while(aOptComponentsParent[i].options.length != 0)
                    aOptComponentsParent[i].options.remove(0);
        
                for( x = 0; x < aOptComponent[i].options.length; x++ ) {
                    aOptComponent[i].options[x].selected;
                    oOption = parent.document.createElement("OPTION");               
                    aOptComponentsParent[i].options.add(oOption);
                    oOption.innerText    = aOptComponent[i].options(x).text;
                    oOption.value        = aOptComponent[i].options(x).value;            
                    oOption.selected     = aOptComponent[i].options(x).selected;
                }  
            }
            
        }                                   
    }
</script>  
    
    <SCRIPT FOR=window EVENT=onload>
        top.frameApp.oculta_div();
        carrega();
    </script> 
        
    </head>
    <body>
        <form name="formCarregar">
			<html:select name="formDadosPesquisa" property="plano" size="1" style="width=200px;height=10px">
                <option value=""></option>
                <html:options collection="planoVO" property="id" labelProperty="descricao" />
			</html:select>	
        </form>
    </body>
</html>