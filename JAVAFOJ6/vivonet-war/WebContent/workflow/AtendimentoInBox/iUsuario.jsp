<!--
Módulo.....: Gestão de Processos
Caso de Uso: Atendimento Fila
Versão.....: $Author: a5112272 $ - $Revision: 1.2 $ - $Date: 2011/05/17 17:18:00 $
-->

<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
   
<acesso:controlInitEnv/>
<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoInBoxForm"/>
<bean:define id="usuariosVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoInBoxForm.atendimentoInformacaoVO.usuarioVIVOArray" />

<html>
    <body>
    <acesso:controlHiddenItem nomeIdentificador="wor_iusa_verpagina">
        <form name="formUsuario">
            <html:select name="form" property="usuarioSel" title="usuarioSel" style="width=120px">
                <html:option value="-1" key="usuarioSel">&nbsp;</html:option>
                <html:options collection="usuariosVO" property="idPessoaUsuario" labelProperty="nmLoginUsuario"/> 
            </html:select>
        </form>
        
        <script>
            var oOption = document.forms["formUsuario"].elements["usuarioSel"];
            var oOptionParent = parent.document.forms["formInBox"].elements["usuarioSel"];
            
            while(oOptionParent.options.length != 0) {
                oOptionParent.options.remove(0);
            }
            
            for(i = 0; i < oOption.options.length; i++ ) {
                var oOptionNew = parent.document.createElement("OPTION");               
                oOptionParent.options.add(oOptionNew);
                oOptionNew.innerText = oOption.options(i).text;
                oOptionNew.value = oOption.options(i).value;            
            }

            //Fim animação
            if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
        </script>       
   
     </acesso:controlHiddenItem>                                              
    </body>
</html>
