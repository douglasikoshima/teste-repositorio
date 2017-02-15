<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.admsistemas.vo.AdmSkillUsuarioVODocument.AdmSkillUsuarioVO.ContatoExistenteVO"%>
<%@ page import="br.com.vivo.fo.admsistemas.vo.AdmSkillUsuarioVODocument.AdmSkillUsuarioVO.ContatoSelecionadoVO"%>
<%@ page import="br.com.vivo.fo.admsistemas.vo.AdmSkillUsuarioVODocument.AdmSkillUsuarioVO.UsuarioExistenteVO"%>
<%@ page import="br.com.vivo.fo.admsistemas.vo.AdmSkillUsuarioVODocument.AdmSkillUsuarioVO.UsuarioSelecionadoVO"%>
<%@ page import="usuario.manterSkillUsuario.abaAssocSkillUsuario.ManterSkillUsuarioController.SkillUsuarioForm"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<bean:define id="FormSkill"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="skillUsuarioForm"/>
<bean:define id="AdmUsuarioSkill"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="skillUsuarioForm.admSkillUsuario"/>

<bean:define id="aGrupo"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="skillUsuarioForm.admSkillUsuario.admGrupoVOArray"/>
<bean:define id="aSkill"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="skillUsuarioForm.admSkillUsuario.admSkillSimplVOArray"/>
<bean:define id="aUsuarioExistente"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="skillUsuarioForm.admSkillUsuario.usuarioExistenteVOArray"/>
<bean:define id="aUsuarioSelecionado"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="skillUsuarioForm.admSkillUsuario.usuarioSelecionadoVOArray"/>

    <head>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/xtree.js"></script>
    <script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/xtooltip.js"></script>
    <link href="<%=request.getContextPath()%>/resources/css/xtree.css" type="text/css" rel="stylesheet"/>
    <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
    <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
    <script language="JavaScript">
        function findByGrupo()
        {
           
            if(document.getElementById('admSkillUsuario.grupoSelecionado').selectedIndex > 0 )
            {
                if(document.getElementById("admSkillUsuario.skillSelecionado").selectedIndex > 0 || 
                    document.getElementById("contatoSelecionado").options.length > 0)
                {
                    if(!confirm("Existe(m) dado(s) selecionado(s) que sera(m) descartado(s) com a alteração de grupo.\nDeseja continuar com a alteração?"))
                    {
                        document.getElementById('admSkillUsuario.grupoSelecionado').selectedIndex = this.tbmIndexGrupo;
                        return false;
                    }
                }
                while(document.forms[0].elements["admSkillUsuario.skillSelecionado"].options.length > 0)
                {
                      document.forms[0].elements["admSkillUsuario.skillSelecionado"].options[0]     = null;  
                }    
    
                document.getElementById('admSkillUsuario.skillSelecionado').options[0] = new Option("-- Selecione --", "");
                
                document.forms[0].target = '';
                document.forms[0].action = 'filtraGrupoContato.do';
                parent.mostrar_div();
                document.forms[0].submit();
            }
        
        }
        
        function filtraSkillContato()
        {
            if(document.getElementById('admSkillUsuario.skillSelecionado').selectedIndex > 0 )
            {
                if(document.getElementById("contatoSelecionado").options.length > 0)
                {
                    if(!confirm("Existe(m) Contato(s) selecionado(s) que sera(m) descartado(s) com a alteração de Skill.\nDeseja continuar com a alteração?"))
                    {
                        document.getElementById('admSkillUsuario.skillSelecionado').selectedIndex = this.tmpIndexSkill;
                        return false;
                    }
                }
                document.forms[0].target = '';
                document.forms[0].action = 'filtraSkillContato.do';
                parent.mostrar_div();
                document.forms[0].submit();
            }
        }
        
        function transfereSelecaoLista(listaOrigem, listaDestino)
        {
            var i;
            for(i = 0; i<listaOrigem.options.length; i++)
                if(listaOrigem.options[i].selected)
                    // Trata a primeira posicao vazia do list, se houver
                    if ((listaDestino.options.length == 1) && (trim(listaDestino.options[0].text) == "")) {
                        listaDestino.options[0] = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);
                    } else {
                        listaDestino.options[listaDestino.options.length] = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);
                    }
                    
            for(i = listaOrigem.options.length-1; i>=0; i--)
                if(listaOrigem.options[i].selected)
                    listaOrigem.options[i] = null;
        
        }
        function salvar()
        {
            if(validaSalvar())
            {
                for(i = 0;i<document.getElementById('contatoSelecionado').length;i++){
                   document.getElementById('contatoSelecionado').options[i].selected =true; 
                }
            
                
                document.forms[0].target = '';
                document.forms[0].action = 'gravaSkillContato.do';
                parent.mostrar_div();
                document.forms[0].submit();
            }
        }   
        
        function validaSalvar()
        {
            if(document.getElementById('admSkillUsuario.grupoSelecionado').selectedIndex == 0)
            {
                alert('Selecione um grupo.');
                return false;
                
            }else if(document.getElementById('admSkillUsuario.skillSelecionado').selectedIndex == 0)
            {
                alert('Selecione um skill.');
                return false;
            }
            /*
            else if(document.getElementById('contatoSelecionado').length == 0)
            {
                alert('Selecione pelo menos um Usuário.');
                return false;
                
            }*/
            
            
            return true;
        }     
    </script>
    
    </head>

    <body onload="if( parent.dvAnimarAguarde != null )parent.stopAnimation();" class="tbl_bgBlue">
    <!--acesso:controlHiddenItem nomeIdentificador="usu_skill_verpagina" -->

        <form name="skillForm" onsubmit="return false;" method="post">
        <html:hidden name="FormSkill" property="inPesquisa"/>
        

        <table border="0" width="100%" >
            <tr>
                <td></td>
            <tr>
            <tr>
                <td><netui:label value="Grupo:" styleClass="tblEstatica_campoNome"/></td>
                <td>
                    <html:select name="FormSkill" property="admSkillUsuario.grupoSelecionado" style="width:180px" onchange="findByGrupo();" onmouseover="ativarToolTip(this,1)">
                        <html:option value="">-- Selecione --</html:option>
                        <html:options collection="aGrupo" property="idGrupo" labelProperty="nmGrupo"/>
                    </html:select>
                </td>
                <td align="left" >
                    <netui:label value="Skill:" styleClass="tblEstatica_campoNome"/>
                </td>
                <td>
                    
                    <html:select name="FormSkill" property="admSkillUsuario.skillSelecionado" style="width:180px" onmouseover="ativarToolTip(this,1)" onchange="filtraSkillContato();">
                        <html:option value="">-- Selecione --</html:option>
                        <html:options collection="aSkill" property="idSkill" labelProperty="nmSkill"/>
                    </html:select>
                </td>
                <td align="left">
                    <div id="div_pesquisa_login">
                        &nbsp;
                    </div>
                </td>
                <td align="right">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>

                <td align="right">
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                </td>
                 <td align="right">
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                </td>
                 <td align="right">
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                </td>
            </tr>
            <tr>
                <td></td>
            <tr>
        </table>



        <vivo:quadro id="qdContato" height="340" width="752" label="Contatos" scroll="no">
            <table border="0" align="center" width="100%">
                <tr>
                        <td valign="top">
                    <SPAN id="dvContatoExistente" style="overflow:hidden;width:340px;height:310px;" >
                        <b>Dispon&iacute;veis</b><br>
                        <html:select name="FormSkill" property="contatoExistente" multiple="true" ondblclick="transfereSelecaoLista(document.forms[0].contatoExistente, document.getElementById('contatoSelecionado'));" onmouseover="ativarToolTip(this,null)" size="9" style="width=338px;height=292px;">
                                <%ArrayList skill = ((SkillUsuarioForm)FormSkill).getListaContatoExistente();
                                if(skill!=null){
                                    if(skill.size()>0){
                                        for(int i=0;i<skill.size();i++){
                                                ContatoExistenteVO[] u = (ContatoExistenteVO[])skill.get(i);
                                                for(int j=0;j<u.length;j++){ 
                                    %>                 
                                                    <option value="<%=u[j].getIdContato()%>"><%=u[j].getNmPath()%></option>
                                    <%                
                                                }
                                        }
                                        
                                    }else{
                                    %>    
                                          <option value="-1"></option> 
                                    <%      
                                    }
                                }
                                %>
                        </html:select>
                    </SPAN>
                    </td>
                    <td align="center">                        
                        <img id="btRightSimp1" vspace="10" tabindex="2" onclick="transfereSelecaoLista(document.forms[0].contatoExistente, document.getElementById('contatoSelecionado'));return false;" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_over.gif'" style="cursor:hand;border:none" /><br>
                        <img id="btRightSimp2" vspace="10" tabindex="3" onclick="transfereSelecaoLista(document.getElementById('contatoSelecionado'), document.forms[0].contatoExistente);return false;" src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_over.gif'" style="cursor:hand;border:none" />
                    </td>
                    
                    <td valign="top">
                        <SPAN id="dvContatoAssociado" style="overflow:hidden;width:340px;height:310px;"> 
                            <b>Associados</b><br> 

                                <html:select name="FormSkill" property="contatoSelecionado" size="9" style="width=338px;height=292px;" multiple="true" ondblclick="transfereSelecaoLista(document.getElementById('contatoSelecionado'), document.forms[0].contatoExistente);" onmouseover="ativarToolTip(this,null);">
                                    <%ArrayList skill = ((SkillUsuarioForm)FormSkill).getListaContatoAssociado();
                                    if(skill!=null){
                                        if(skill.size()>0){
                                            for(int i=0;i<skill.size();i++){
                                                    ContatoSelecionadoVO[] u = (ContatoSelecionadoVO[])skill.get(i);
                                                    for(int j=0;j<u.length;j++){ 
                                        %>                 
                                                        <option value="<%=u[j].getIdContato()%>"><%=u[j].getNmPath()%></option>
                                        <%                
                                                    }
                                            }
                                        }
                                    }
                                    %>
                                </html:select>                    
                        </SPAN>
                    </td>
                </tr>
                <tr>
                    <td colspan="3" align="center">

                        <SPAN id="botaoEsqArvore" style="overflow:hidden" onclick="redimensionaFrames('botaoEsqArvore')">
                            <img src="<%=request.getContextPath()%>/resources/images/bt_abre_left.gif" alt="Clique para visualizar apenas o Registro do Atendimento" style="cursor: hand;">
                        </SPAN>
                        <SPAN id="botaoMeioArvore" style="overflow:hidden" onclick="redimensionaFrames('botaoMeioArvore')">
                            <img src="<%=request.getContextPath()%>/resources/images/bt_abre_centro.gif" alt="Clique para visualizar o Registro do Atendimento e a Árvore de Contato" style="cursor: hand;">
                        </SPAN>
                        <SPAN id="botaoDirContato" style="overflow:hidden;" onclick="redimensionaFrames('botaoDirContato')">
                            <img src="<%=request.getContextPath()%>/resources/images/bt_abre_right.gif" alt="Clique para visualizar apenas a Árvore de Contato" style="cursor: hand;">
                        </SPAN>
                    </td>
                    

                </tr>
            </table>
        </vivo:quadro>

        <table width="100%" border="0">
            <tr valign="top">
                <!--acesso:controlHiddenItem nomeIdentificador="usu_skill_salvar"-->
                    <td align="right">
                        <img style="cursor:hand;border:none" id="btGravar" onclick="salvar();" src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_gravar_over.gif'" />&nbsp;
                                 
                    </td>
                    
                <!--/acesso:controlHiddenItem-->
            </tr>
        </table>

        <iframe scrolling="yes"  name="innerBrowserArvoreContato" height="0px" width="0px"></iframe>              
        </form>

    <!--/acesso:controlHiddenItem -->
    <vivo:alert atributo="msgStatus" scope="request"/>
    <script>
        tbmIndexGrupo = document.getElementById('admSkillUsuario.grupoSelecionado').selectedIndex;
        tmpIndexSkill = document.getElementById('admSkillUsuario.skillSelecionado').selectedIndex;
    </script>
</body>

<script language="javascript">
           
           
        function redimensionaFrames(botao) {
            if(botao == 'botaoEsqArvore'){
                dvContatoAssociado.style.width = "720px";
                dvContatoExistente.style.width = "0px";
                dvContatoExistente.style.overflow = "hidden";
                document.all['contatoSelecionado'].style.width="720px";
                document.all['contatoSelecionado'].style.overflow = "auto";  
                document.all['contatoExistente'].style.width="0";
                document.all['contatoExistente'].style.overflow = "auto"; 
                document.all['botaoMeioArvore'].style.display = "";       
            } 
            
            if(botao == 'botaoMeioArvore' ){
                dvContatoAssociado.style.width = "340px";
                dvContatoExistente.style.width = "340px";
                document.all['contatoExistente'].style.width="338px";
                document.all['contatoSelecionado'].style.width="338px";
                dvContatoExistente.style.overflow = "auto";
                document.all['botaoMeioArvore'].style.display = "none";
            }
            
            if(botao == 'botaoDirContato'){
                dvContatoExistente.style.width = "680px";
                dvContatoAssociado.style.width = "1px";
                dvContatoAssociado.style.overflow = "hidden";
                document.all['contatoExistente'].style.width="680px";
                document.all['contatoExistente'].style.overflow = "auto";   
                document.all['contatoSelecionado'].style.width="1";
                document.all['contatoSelecionado'].style.overflow = "auto";        
                document.all['botaoMeioArvore'].style.display = "";
            }
                   
        }           
                        
        //Fim animação
        if( parent.dvAnimarAguarde != null ) 
            parent.stopAnimation();
            
        document.all['botaoMeioArvore'].style.display = "none";
        
    </script>