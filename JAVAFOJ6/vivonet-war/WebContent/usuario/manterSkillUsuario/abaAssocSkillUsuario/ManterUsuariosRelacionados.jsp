<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.admsistemas.vo.AdmSkillUsuarioVODocument.AdmSkillUsuarioVO.UsuarioSkillAssociadoVO"%>
<%@ page import="usuario.manterSkillUsuario.abaAssocSkillUsuario.ManterSkillUsuarioController.SkillUsuarioForm"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<bean:define id="FormSkill"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="skillUsuarioForm"/>
<bean:define id="AdmUsuarioSkill"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="skillUsuarioForm.admSkillUsuario"/>

    <head>
    <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
    <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>

    <script language="JavaScript">

        function excluiItem()
        {
            var flgExisteItem = false;
            for(i = 0; i<document.getElementById('admSkillUsuario.contatosSelecionadosArray').options.length; i++)
            {
                if(document.getElementById('admSkillUsuario.contatosSelecionadosArray').options[i].selected)
                {
                    flgExisteItem = true;
                    document.getElementById('admSkillUsuario.contatosSelecionadosArray').options[i] = null;
                }
            }
            
            if(!flgExisteItem)
                alert("Selecione um Ítem para retirar da lista de Contato.");
                
            return false;
        }
        
        function excluirSkillUsuario()
        {
            var form = document.forms[0];
            var fldSelected = false;
            
            if(!form.chkUser)
            {
                alert('Não existem Usuários Associados a serem exluídos.');
                return false;
            }
            for(i = 0;i < form.chkUser.length;i++)
            {
                if(form.chkUser[i].checked == true)
                    fldSelected = true;
            }      
            
            try
            {
                if(form.chkUser.checked == true){
                    fldSelected = true;
                }
                    
            }catch(e)
            {
                fldSelected = false;
            }
            
            if(fldSelected == false){
                alert('Selecione pelo menos um registro para Exclusão.');
                return false;
            }
            
            if(confirm('Confirma remoção do(s) item(ns)?'))
            {
                form.method = "POST";
                form.target = 'innerBrowserArvoreContato';
                form.action = 'excluiSkillUsuario.do';
                form.submit();
                
            }else
            {
                return false;                
            }
            
        
        }
         
    function checaSkillUsuario(obj) 
    {
        
        var op = obj.checked;
        var valor = obj.value;
        
        if(op)
        {
            document.getElementById('paramChkUser').value += valor+";";
    
        }else if(!op)
        {
            var pltStrValue = document.getElementById('paramChkUser').value.split(';');
            
            document.getElementById('paramChkUser').value = '';
    
            for(i = 0;i < pltStrValue.length; i++)
            {
                if(pltStrValue[i] == valor)
                {
                    pltStrValue[i] = null;
                }
                
                if(pltStrValue[i] != null && pltStrValue[i] != '')
                {
                    document.getElementById('paramChkUser').value += pltStrValue[i]+';';
                }
            }
            
        } 
    }   

    function selecionaTodos(op)
    {
        if(op)
            document.getElementById('paramChkUser').value = '';

        for(i=0;i<document.forms[0].elements.length;i++)
        {
            if(document.forms[0].elements[i].name == 'chkUser' && document.forms[0].elements[i].type == 'checkbox')
            {
                document.forms[0].elements[i].checked = op;
                if(op){
                    checaSkillUsuario(document.forms[0].elements[i]);
                }
            }
        }
        
        if(!op)
            document.getElementById('paramChkUser').value = '';
    }
    
    </script>
    </head>

    <body onload="top.frameApp.stopAnimation();" class="tbl_bgBlue">
    <!--acesso:controlHiddenItem nomeIdentificador="usu_skill_verpagina" -->

        <form name="skillForm" onsubmit="return false;" method="post">
            <html:hidden name="FormSkill" property="paramChkUser"/>
            
        <table width="100%" border="0">
            <tr>
                <td align="left">
                <logic:greaterThan name="FormSkill" property="skillUsuarioArrayLength" value="0">
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" class="radio" name="chkTodos" value="1" onclick="selecionaTodos(this.checked);"> <b>Selecionar Todos</b>
                </logic:greaterThan>
                </td>
            </tr>
        </table>

        <div id="div_lista_manter_usuario">
            <logic:greaterThan name="FormSkill" property="skillUsuarioArrayLength" value="0">
            <table align="center">
                <tr>
                    <td>                
                        <vivo:tbTable selectable="true" layoutWidth="730" layoutHeight="340" tableWidth="730" styleId="tableTitle" sortable="true">
                            <vivo:tbHeader>
                                <vivo:tbHeaderColumn align="center" width="8%"></vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="left" width="18%" type="string">Usuário</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="left" width="18%" type="string">Caminho Contato</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="left" width="18%" type="string">Nome Perfil</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="left" width="18%" type="string">Efetuado Por</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="left" width="18%" type="string">Data/Hora</vivo:tbHeaderColumn>
                            </vivo:tbHeader>
                            <vivo:tbRows scroll="yes">
                                <%
                                ArrayList skill = ((SkillUsuarioForm)FormSkill).getListaUsuarioSkillAssociado();
                                
                                if(skill!=null){
                                    if(skill.size()>0){
                                       
                                        for(int i=0;i<skill.size();i++){
                                            
                                                UsuarioSkillAssociadoVO[] u = (UsuarioSkillAssociadoVO[])skill.get(i);
                                                for(int j=0;j<u.length;j++){ 
                                                    if(u[j]!=null){
                                        %>  
                                                     <vivo:tbRow key="linha1">
                                                        <vivo:tbRowColumn>
                                                               <input type="checkbox" class="radio" id="chkUser" name="chkUser" value="<%=u[j].getIdSkillUsuario()%>" onclick="checaSkillUsuario(this);document.getElementById('chkTodos').checked =false;">
                                                        </vivo:tbRowColumn>                                 
                                                        <vivo:tbRowColumn>
                                                                <%=u[j].getNmUsuario()%>
                                                        </vivo:tbRowColumn>
                                                        <vivo:tbRowColumn>
                                                                <%=u[j].getDsPath()%>
                                                        </vivo:tbRowColumn>
                                                        <vivo:tbRowColumn>
                                                                <%=u[j].getNmPerfil()%>
                                                        </vivo:tbRowColumn>
                                                        <vivo:tbRowColumn>
                                                                <%=u[j].getLoginEfetuado()%>
                                                        </vivo:tbRowColumn>
                                                        <vivo:tbRowColumn>
                                                                <%=u[j].getDataHora()%>
                                                        </vivo:tbRowColumn>
                                                    </vivo:tbRow>
                                        <%              
                                                    }
                                                }
                                            
                                        }
                                         
                                    }else{
                                    %>    
                                          <option value="-1"></option> 
                                    <%      
                                    }
                                }
                                %>    
                            </vivo:tbRows>
                        </vivo:tbTable>
                    </td>
                </tr>
            </table>
            </logic:greaterThan>
            <logic:equal name="FormSkill" property="skillUsuarioArrayLength" value="0">
                    <table width="100%" border="0" cellspacing="5" cellpadding="5" align="center">
                        <tr>
                            <td width="100%" align="center" class="tbl_bgGray">
                            
                                <b><p>Nenhum usuário associado encontrado com os parâmetros fornecidos.</p></b>
                            
                            </td>
                        </tr>
                    </table>
            </logic:equal>
        </div>
        
        <table width="100%" border="0">
            <tr valign="top">
                <!--acesso:controlHiddenItem nomeIdentificador="usu_skill_excluir" -->
                <logic:greaterThan name="FormSkill" property="skillUsuarioArrayLength" value="0">
                    <td align="left"><img tabindex="2" hspace="5" id="btExcluir" style="border:none;cursor:hand"  onclick="excluirSkillUsuario();return false;" src="/FrontOfficeWeb/resources/images/bt_excluir_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_excluir_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_excluir_over.gif'"/></td>
                </logic:greaterThan>
                <!--/acesso:controlHiddenItem-->
            </tr>
        </table>

        <iframe scrolling="yes"  name="innerBrowserArvoreContato" height="0px" width="0px"></iframe>              

        </form>

    <vivo:alert atributo="msgStatus" scope="request"/>

    <!--/acesso:controlHiddenItem -->
    
</body>
