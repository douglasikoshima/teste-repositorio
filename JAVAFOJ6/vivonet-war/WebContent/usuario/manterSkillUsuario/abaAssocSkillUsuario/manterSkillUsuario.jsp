<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.admsistemas.vo.AdmSkillUsuarioVODocument.AdmSkillUsuarioVO.UsuarioExistenteVO"%>
<%@ page import="br.com.vivo.fo.admsistemas.vo.AdmSkillUsuarioVODocument.AdmSkillUsuarioVO.UsuarioSelecionadoVO"%>
<%@ page import="usuario.manterSkillUsuario.abaAssocSkillUsuario.ManterSkillUsuarioController.SkillUsuarioForm"%>
<%@ page import="java.util.ArrayList"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<bean:define id="FormSkill"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="skillUsuarioForm"/>
<bean:define id="AdmUsuarioSkill"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="skillUsuarioForm.admSkillUsuario"/>
<bean:define id="aGrupo"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="skillUsuarioForm.admSkillUsuario.admGrupoVOArray"/>
<bean:define id="aSkill"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="skillUsuarioForm.admSkillUsuario.admSkillSimplVOArray"/>
<bean:define id="aUsuarioExistente"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="skillUsuarioForm.admSkillUsuario.usuarioExistenteVOArray"/>
<bean:define id="aUsuarioSelecionado"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="skillUsuarioForm.admSkillUsuario.usuarioSelecionadoVOArray"/>
<head>
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/xtree.css"/>
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/xtree.js"></script>
    <script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/xtooltip.js"></script>
    <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
    <script language="JavaScript">
	    var tmpArvore = null;
	    var tmpIndex = 0;
	    var tbmIndexGrupo = 0;
	    var tmpIndexSkill = 0;

	    function avancar(){
	        parent.abaSelected(parent.btAba, parent.bt04);
	        document.forms[0].target = '';
	        document.forms[0].action = 'avancarParaContato.do';
	        parent.mostrar_div();
	        document.forms[0].submit();
	    }
    
	    function exibeEscondeCampos(campo){
	        if(campo=='0'){
	            var a = document.getElementById("divTipoGravar");
	            a.style.visibility = 'visible';
	            var b = document.getElementById("divTipoRelacionar");
	            b.style.visibility = 'hidden';
	        }else{
	            var a = document.getElementById("divTipoGravar");
	            a.style.visibility = 'hidden';
	            var b = document.getElementById("divTipoRelacionar");
	            b.style.visibility = 'visible';
	        }
	    }

	    function transfereSelecaoLista(listaOrigem, listaDestino){
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

        function excluirSkillUsuario(){
            var form = document.forms[0];
            var fldSelected = false;
            
            if(!form.chkUser){
                alert('Não existem Usuários Associados a serem exluídos.');
                return false;
            }

            for(i = 0;i < form.chkUser.length;i++){
                if(form.chkUser[i].checked == true)
                    fldSelected = true;
            }

            if(fldSelected == false){
                alert('Selecione pelo menos um registro para Exclusão.');
                return false;
            }

            if(confirm('Confirma remoção do(s) item(ns)?')){
                form.target = 'innerBrowserArvoreContato';
                form.action = 'excluiSkillUsuario.do';
                parent.mostrar_div();
                form.submit();
            }else{
                return false;
            }
        }
         
	    function checaSkillUsuario(obj){
	        var op = obj.checked;
	        var valor = obj.value;
	        if(op){
	            document.getElementById('paramChkUser').value += valor+";";
	        }else if(!op){
	            var pltStrValue = document.getElementById('paramChkUser').value.split(';');
	            document.getElementById('paramChkUser').value = '';
	            for(i = 0;i < pltStrValue.length; i++){
	                if(pltStrValue[i] == valor){
	                    pltStrValue[i] = null;
	                }
	                if(pltStrValue[i] != null && pltStrValue[i] != ''){
	                    document.getElementById('paramChkUser').value += pltStrValue[i]+';';
	                }
	            }
	        } 
	    }   
	    
	    function findByGrupo(){
	        if(document.getElementById('admSkillUsuario.grupoSelecionado').selectedIndex > 0 ){
	            if(document.getElementById("admSkillUsuario.skillSelecionado").selectedIndex > 0 || 
	                document.getElementById("usuarioSelecionado").options.length > 0){
	                if(!confirm("Existe(m) dado(s) selecionado(s) que sera(m) descartado(s) com a alteração de grupo.\nDeseja continuar com a alteração?")){
	                    document.getElementById('admSkillUsuario.grupoSelecionado').selectedIndex = this.tbmIndexGrupo;
	                    return false;
	                }
	            }
	            while(document.forms[0].elements["admSkillUsuario.skillSelecionado"].options.length > 0){
	                  document.forms[0].elements["admSkillUsuario.skillSelecionado"].options[0]     = null;  
	            }    
	            document.getElementById('admSkillUsuario.skillSelecionado').options[0] = new Option("-- Selecione --", "");
	            document.forms[0].target = '';
	            document.forms[0].action = 'filtraGrupo.do';
	            parent.mostrar_div();
	            document.forms[0].submit();
	        }
	    }

	    function filtraSkill(){
	        if(document.getElementById('admSkillUsuario.skillSelecionado').selectedIndex > 0 ){
	            if(document.getElementById("usuarioSelecionado").options.length > 0){
	                if(!confirm("Existe(m) Usuário(s) selecionado(s) que sera(m) descartado(s) com a alteração de Skill.\nDeseja continuar com a alteração?")){
	                    document.getElementById('admSkillUsuario.skillSelecionado').selectedIndex = this.tmpIndexSkill;
	                    return false;
	                }
	            }
	            document.forms[0].target = '';
	            document.forms[0].action = 'filtraSkill.do';
	            parent.mostrar_div();
	            document.forms[0].submit();
	        }
	    }
	    
	    function validaSalvar(){
	        if(document.getElementById('admSkillUsuario.grupoSelecionado').selectedIndex == 0){
	            alert('Selecione um grupo.');
	            return false;
	        }else if(document.getElementById('admSkillUsuario.skillSelecionado').selectedIndex == 0){
	            alert('Selecione um skill.');
	            return false;
	        }
	        /*
	        else if(document.getElementById('usuarioSelecionado').length == 0){
	            alert('Selecione pelo menos um Usuário.');
	            return false;
	        }*/
	        return true;
	    }
	    
	    function salvar(){
	        if(validaSalvar()){
	            for(i = 0;i<document.getElementById('usuarioSelecionado').length;i++){
	               document.getElementById('usuarioSelecionado').options[i].selected =true; 
	            }
	            document.forms[0].target = '';
	            document.forms[0].action = 'gravaSkillUsuario.do';
	            parent.mostrar_div();
	            document.forms[0].submit();
	        }
	    }
	    
	    function listar(){
	        if(document.getElementById('admSkillUsuario.grupoSelecionado').selectedIndex == 0){
	            alert('Selecione um grupo para pesquisa.');
	            return false;
	        }        
	
	        for(i = 0;i<document.getElementById('usuarioSelecionado').length;i++){
	           document.getElementById('usuarioSelecionado').options[i].selected =true; 
	        }
	
	        var form = document.forms[0];
	        parent.mostrar_div();
	        form.inPesquisa.value = 2;
	        form.target = '';
	        form.action = 'listaUsuarios.do?inPesquisa=2'; 
	        parent.abaSelected(parent.btAba, parent.bt03);   
	        form.submit();
	    }
	
	    function pesquisar(op){
	        if(op == null || op.keyCode == 13){
	            if(document.getElementById('admSkillUsuario.grupoSelecionado').selectedIndex == 0){
	                alert('É necessário selecionar um grupo para pesquisar por Login.');
	                document.getElementById('admSkillUsuario.grupoSelecionado').focus();
	                return false;
	            }else if(document.getElementById('admSkillUsuario.skillSelecionado').selectedIndex == 0){
	                alert('É necessário selecionar um skill para pesquisar por Login.');
	                document.getElementById('admSkillUsuario.skillSelecionado').focus();
	                return false;
	            }else{
                    for(i = 0;i<document.getElementById('usuarioSelecionado').length;i++){
                       document.getElementById('usuarioSelecionado').options[i].selected =true; 
                    }
                    //var form = document.forms[0];
                    document.forms[0].target = 'innerBrowserArvoreContato';
                    document.forms[0].action = 'pesquisaPorLogin.do';
                    parent.mostrar_div();
                    document.forms[0].submit();
	            }
	        }
	    }
    </script>
    </head>
    <body onload="if( parent.dvAnimarAguarde != null ){parent.stopAnimation();}" class="tbl_bgBlue">
    <!--acesso:controlHiddenItem nomeIdentificador="usu_skill_verpagina" -->
        <form name="skillForm" onsubmit="return false;" method="post">
            <html:hidden name="FormSkill" property="idContato"/>
            <html:hidden name="FormSkill" property="dsPath"/>
            <html:hidden name="FormSkill" property="inFolha"/>
            <html:hidden name="FormSkill" property="inPesquisa"/>
            <html:hidden name="FormSkill" property="flagOperacao"/>
            <table border="0" width="100%" >
                <tr>
                    <td><netui:label value="Grupo:" styleClass="tblEstatica_campoNome"/></td>
                    <td>
                        <html:select name="FormSkill" property="admSkillUsuario.grupoSelecionado" style="width:180px" onchange="findByGrupo();" onmouseover="ativarToolTip(this,1)">
                            <html:option value="">-- Selecione --</html:option>
                            <html:options collection="aGrupo" property="idGrupo" labelProperty="nmGrupo"/>
                        </html:select>
                    </td>
                    <td >
                        <netui:label value="Login:" styleClass="tblEstatica_campoNome"/>
                    </td>
                    <td>
                        <html:text name="FormSkill" property="admSkillUsuario.login" style="width:150px;" onkeyup="pesquisar(event);" maxlength="30"/>
                    </td>
                    <td align="left">
                        <div id="div_pesquisa_login">
                            <img id="btnSelect" vspace="10" onClick="pesquisar(null);" src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif" style="border:none;"/>
                        </div>
                    </td>
                    <td align="right"><netui:label value="Skill:" styleClass="tblEstatica_campoNome"/></td>
    
                    <td align="right">
                        <html:select name="FormSkill" property="admSkillUsuario.skillSelecionado" style="width:180px" onmouseover="ativarToolTip(this,1)" onchange="filtraSkill();">
                            <html:option value="">-- Selecione --</html:option>
                            <html:options collection="aSkill" property="idSkill" labelProperty="nmSkill"/>
                        </html:select>
                    </td>
                </tr>
            </table>
        <vivo:quadro id="qdUsuario" height="340" width="752" label="Usuários" scroll="no">
            <table border="0" align="center" width="100%">
                <tr>
                    <td valign="top">
                    <SPAN id="dvUsuarioExistente" style="overflow:hidden;width:340px;height:310px;" >
                        <b>Dispon&iacute;veis</b><br>
                        <div id="usuario_existente_div">
                                <html:select name="FormSkill" property="usuarioExistente" multiple="true" ondblclick="transfereSelecaoLista(document.forms[0].usuarioExistente, document.getElementById('usuarioSelecionado'));exibeEscondeCampos('0');" onmouseover="ativarToolTip(this,null)" size="9" style="width=338px;height=292px;">
                                <%ArrayList skill = ((SkillUsuarioForm)FormSkill).getListaUsuariosExistentes();
                                if(skill!=null){
                                    if(skill.size()>0){
                                        for(int i=0;i<skill.size();i++){
                                                UsuarioExistenteVO[] u = (UsuarioExistenteVO[])skill.get(i);
                                                for(int j=0;j<u.length;j++){%>                 
                                                    <option value="<%=u[j].getIdUsuario()%>"><%=u[j].getNmUsuario()%></option>
                                              <%}
                                        }
                                    }else{%>    
                                          <option value="-1"></option> 
                                  <%}
                                }%>
                                </html:select> 
                            </div>   
                        </SPAN>
                    </td>
                    <td align="center">
                        <img  id="btRightSimp1" onclick="transfereSelecaoLista(document.forms[0].usuarioExistente, document.getElementById('usuarioSelecionado'));exibeEscondeCampos('0');return false;" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_over.gif'" style="cursor:hand;border:none" /><br><br>
                        <img id="btRightSimp2" onclick="transfereSelecaoLista(document.getElementById('usuarioSelecionado'), document.forms[0].usuarioExistente);exibeEscondeCampos('0');return false;" src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_over.gif'" style="cursor:hand;border:none" />
                    </td>
                    <td valign="top">
                        <SPAN id="dvUsuarioAssociado" style="overflow:hidden;width:340px;height:310px;"> 
                            <b>Associados</b><br>                    
                            <html:select name="FormSkill" property="usuarioSelecionado" size="9" style="width=338px;height=292px;" multiple="true" ondblclick="transfereSelecaoLista(document.getElementById('usuarioSelecionado'), document.forms[0].usuarioExistente);exibeEscondeCampos('0');" onmouseover="ativarToolTip(this,null);">
                                    <%ArrayList skill = ((SkillUsuarioForm)FormSkill).getListaUsuarioSkillAssociado();
                                    if(skill!=null){
                                        if(skill.size()>0){
                                            for(int i=0;i<skill.size();i++){
                                                    UsuarioSelecionadoVO[] u = (UsuarioSelecionadoVO[])skill.get(i);
                                                    for(int j=0;j<u.length;j++){%>                 
                                                        <option value="<%=u[j].getIdUsuario()%>"><%=u[j].getNmUsuario()%></option>
                                                  <%}
                                            }
                                        }
                                    }%>
                            </html:select>
                        </SPAN>
                    </td>
                </tr>
                <tr>
                    <td colspan="3" align="center">
                        <SPAN id="botaoEsqArvore" style="overflow:hidden" onclick="redimensionaFrames('botaoEsqArvore')">
                            <img src="<%=request.getContextPath()%>/resources/images/bt_abre_left.gif" alt="Clique para visualizar apenas os Usuários Associados" style="cursor: hand;">
                        </SPAN>
                        <SPAN id="botaoMeioArvore" style="overflow:hidden" onclick="redimensionaFrames('botaoMeioArvore')">
                            <img src="<%=request.getContextPath()%>/resources/images/bt_abre_centro.gif" alt="Clique para visualizar os Usuários Disponíveis e os Usuários Associados" style="cursor: hand;">
                        </SPAN>
                        <SPAN id="botaoDirContato" style="overflow:hidden;" onclick="redimensionaFrames('botaoDirContato')">
                            <img src="<%=request.getContextPath()%>/resources/images/bt_abre_right.gif" alt="Clique para visualizar apenas os Usuários Disponíveis" style="cursor: hand;">
                        </SPAN>
                    </td>
                </tr>
            </table>
        </vivo:quadro>
        <table width="100%" border="0">
            <tr valign="top">
                <td align="right">
                    <div id="divTipo3" style="visibility:'visible'; position:relative; top: 0px; left: 0px; padding: 0px;">
                        <div id="divTipoGravar" style="visibility:'hidden'; position:absolute; top: 0px; left: -70px; height: 0px; padding: 0px;">
                            <img hspace="5" id="btSalvar" style="border:none;cursor:hand"  onclick="salvar();" src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_salvar_over.gif'"/>
                        </div>
                        <div id="divTipoRelacionar" style="visibility:'hidden'; position:absolute; top: 0px; left: -125px; height: 0px; padding: 0px;">
                            <img hspace="5" id="btAvancar" style="border:none;cursor:hand"  onclick="avancar();" src="/FrontOfficeWeb/resources/images/bt_relcontato_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_relcontato_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_relcontato_over.gif'"/>
                        </div>
                    </div>
                </td>
            </tr>
        </table>
        <iframe scrolling="yes"  name="innerBrowserArvoreContato" height="0px" width="0px"></iframe> 
        </form>
    <script>
        tbmIndexGrupo = document.getElementById('admSkillUsuario.grupoSelecionado').selectedIndex;
        tmpIndexSkill = document.getElementById('admSkillUsuario.skillSelecionado').selectedIndex;
        function redimensionaFrames(botao) {
            if(botao == 'botaoEsqArvore'){
                dvUsuarioAssociado.style.width = "720px";
                dvUsuarioExistente.style.width = "0px";
                dvUsuarioExistente.style.overflow = "hidden";
                document.all['usuarioSelecionado'].style.width="720px";
                document.all['usuarioSelecionado'].style.overflow = "auto";  
                document.all['usuarioExistente'].style.width="0";
                document.all['usuarioExistente'].style.overflow = "auto"; 
                document.all['botaoMeioArvore'].style.display = "";       
            }

            if(botao == 'botaoMeioArvore' ){
                dvUsuarioAssociado.style.width = "340px";
                dvUsuarioExistente.style.width = "340px";
                document.all['usuarioExistente'].style.width="338px";
                document.all['usuarioSelecionado'].style.width="338px";
                dvUsuarioExistente.style.overflow = "auto";
                document.all['botaoMeioArvore'].style.display = "none";
            }

            if(botao == 'botaoDirContato'){
                dvUsuarioExistente.style.width = "680px";
                dvUsuarioAssociado.style.width = "1px";
                dvUsuarioAssociado.style.overflow = "hidden";
                document.all['usuarioExistente'].style.width="680px";
                document.all['usuarioExistente'].style.overflow = "auto";   
                document.all['usuarioSelecionado'].style.width="1";
                document.all['usuarioSelecionado'].style.overflow = "auto";        
                document.all['botaoMeioArvore'].style.display = "";
            }
        }
        document.all['botaoMeioArvore'].style.display = "none";
        exibeEscondeCampos('<bean:write name="FormSkill" property="flagOperacao"/>');

        if( parent.dvAnimarAguarde != null ) 
            parent.stopAnimation();
    </script>
    <vivo:alert atributo="msgStatus" scope="request"/>
</body>
