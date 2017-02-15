<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<bean:define id="retornoCTIForm" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="retornoCTIForm"/>
<bean:define name="retornoCTIForm" property="retornoWFCTIVO.WFGrupoDisponivelVOArray"  id="WFGrupoDisponivelVOArray"/>
<bean:define name="retornoCTIForm" property="retornoWFCTIVO.WFGrupoSelecionadoVOArray" id="WFGrupoSelecionadoVOArray"/>

<html>
    <body>
        <form name="formUsuario">
        <table border="0" width="100%">
            <tr align="center">
                <td>
                    Disponivel<br>
                    <html:select name="retornoCTIForm" property="idGruposDisponiveis" multiple="true" size="10" style="width=340px;height=360px;">
                        <html:options collection="WFGrupoDisponivelVOArray" property="idGrupo" labelProperty="dsGrupo"/>
                    </html:select>
                </td>
                <td>
                    Associada<br>                    
                    <html:select name="retornoCTIForm" property="idGruposSelecionados"  multiple="true" size="10" style="width=340px;height=360px;">
                        <html:options collection="WFGrupoSelecionadoVOArray" property="idGrupo" labelProperty="dsGrupo"/>
                    </html:select>
                </td>
            </tr>
        </table>
        </form>

    </body>
    
    <script>
        var oOption = document.forms["formUsuario"].elements["idGruposDisponiveis"];
        var oOptionParent = parent.document.forms["retornoCTIForm"].elements["idGruposDisponiveis"];
        
        while(oOptionParent.options.length != 0) {
            oOptionParent.options.remove(0);
        }
        
        for(i = 0; i < oOption.options.length; i++ ) {
            var oOptionNew = parent.document.createElement("OPTION");               
            oOptionParent.options.add(oOptionNew);
            oOptionNew.innerText = oOption.options(i).text;
            oOptionNew.value = oOption.options(i).value;            
        }
        
        oOption = document.forms["formUsuario"].elements["idGruposSelecionados"];
        oOptionParent = parent.document.forms["retornoCTIForm"].elements["idGruposSelecionados"];
        
        while(oOptionParent.options.length != 0) {
            oOptionParent.options.remove(0);
        }
        
        for(i = 0; i < oOption.options.length; i++ ) {
            var oOptionNew = parent.document.createElement("OPTION");               
            oOptionParent.options.add(oOptionNew);
            oOptionNew.innerText = oOption.options(i).text;
            oOptionNew.value = oOption.options(i).value;            
        }

        //Liga animação
        if( top.dvAnimarAguarde != null ) top.stopAnimation();
    </script>    
    
</html>