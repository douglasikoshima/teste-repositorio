<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<acesso:controlInitEnv/>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="perfilGrupoContatoCRIForm" />

<bean:define id="GrupoVO" name="Form" property="perfilVariaveisVO.grupoVOArray" />
<bean:define id="PerfilVO" name="Form" property="perfilVariaveisVO.perfilArray" />
<bean:define id="PerfilVariaveisVO" name="Form" property="perfilVariaveisVO" />

<head>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/xtree.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
    <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">        
    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/frameweb.js" ></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/toolTip.js"></script>      
    <script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/xtooltip.js"></script>

    <script language="Javascript">
        
        function efetivarAssociacao(){
        
            if(document.forms[0]['inGrupoIguais'].value == '1'){
                if(!confirm("Existe grupos iguais!\nDeseja Continuar ?"))
                {
                    return false;
                }
            }
            
            //Incia animação
            if( top.frameApp.dvAnimarAguarde != null ){
                top.frameApp.startAnimation();
            }
            
            document.forms[0].target='frameEscondido';
            document.forms[0].action='efetivarAssociacao.do';
            document.forms[0].submit();
        }
        
        function associar()        
        {
            if(document.forms[0]['perfil'].selectedIndex == 0){
                alert('Selecione um perfil!');
                return false;
            }
            
            if(document.forms[0]['grupo'].selectedIndex == 0){
                alert('Selecione um grupo!');
                return false;
            }
            //Incia animação
            if( top.frameApp.dvAnimarAguarde != null ){
                top.frameApp.startAnimation();
            }
            
            document.forms[0].target='';
            document.forms[0].action='associarPerfilGrupo.do';
            document.forms[0].submit();
        }
        
        function excluirPerfilGrupo(idPerfil, idGrupo, idxGrupoPerfil){
            
            if (!confirm("Confirma a exclusão?")) {
                return;
            }

            //Incia animação
            if( top.frameApp.dvAnimarAguarde != null ){
                top.frameApp.startAnimation();
            }
            
            document.forms[0].target='';
            document.forms[0].action='desassociaPerfilGrupo.do?perfilDes='+idPerfil+'&grupoDes='+idGrupo+'&idxGrupoPer='+idxGrupoPerfil;
            document.forms[0].submit();
        }
        
        function ocultarDiv()
        {
           if (typeof(parent.oculta_div) != "undefined")
            {
                parent.oculta_div();
            }
            else
            {
                parent.parent.oculta_div();
            }
        }        
    
        function enviaSequencia(op)
        {
            var idxChecked = -99;

            for(i=0;i<document.forms[0].elements.length;i++)
            {
                if(document.forms[0].elements[i].name == 'perfilLista' 
                    && document.forms[0].elements[i].type == 'radio' 
                    && document.forms[0].elements[i].checked == true)
                {
                    idxChecked = document.forms[0].elements[i].value;
                }
            
            }
            
            if(idxChecked == -99)
            {
                alert('Selecione um registro para move-lo.');
                return false;
            }
            
            var tam = '<bean:write name="Form" property="perfilIdx"/>';
            
            if(tam == '0')
            {
                alert('Não existe(m) item(ns) na lista.');
                return false;
                
            }else if(tam == 1)
            {
                alert('Existe apenas um item na lista, não é possivel move-lo.');
                return false;
                
            }else if(op == 1 && idxChecked == 0)
            {
                alert('Este item é o primeiro da lista, não pode ser movido para cima.');
                return false;
                
            }else if(op == 0 && parseInt(idxChecked)+1 == tam)
            {
                alert('Este item é o ultimo da lista, não pode ser movido para baixo.');
                return false;            
            }
            
            document.forms[0].inMover.value = op;
            document.forms[0].target='';
            document.forms[0].action='moverPerfilGrupo.do';
            document.forms[0].submit();
        
        }
        
</script>
    

</head>
<body onload="ocultarDiv()">
<!--acesso:controlHiddenItem nomeIdentificador="adm_aret_verpagina"-->
    <form action="begin" id="begin" name="begin">
    <html:hidden property="idContato" name="Form"/>
    <html:hidden property="inMover" name="Form"/>
    <html:hidden property="inGrupoIguais" name="Form"/>
            <div id="dvAbaGrupo">
                <table border="0" cellspacing="0" cellpadding="1" class="tbl_bgGray" height="30" width="750" align="center">
                    <tr>
                        <td>
                            <table border="1" cellspacing="0" cellpadding="1" class="tbl_bgGray" align="left">
                                <tr>
                                    <td style="text-indent:6px;" valign="middle" align="left">
                                        <b>Perfil:</b>
                                    </td>
                                    <td class="tblEstatica_campoNome" align="left" valign="middle">
                                        <input type="hidden" name="nmPerfil">
                                        <html:select name="Form" property="perfil" style="width=200" onmouseover="ativarToolTip(this,'1')">
                                            <html:option value="-1">Selecione um Perfil</html:option>
                                            <html:options collection="PerfilVO" property="idPerfil" labelProperty="nmPerfil"/>
                                        </html:select>
                                    </td>     
                                    <td width="100%" align="center">
                                        <img  style="cursor:hand;border:none;" id="btAssociar" onClick="associar();" src="/FrontOfficeWeb/resources/images/bt_icon_mais.gif" />
                                        <img onclick="enviaSequencia(1);" id="btup" src="/FrontOfficeWeb/resources/images/bt_up_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_up_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_up_over.gif'" style="border:none;cursor:hand"/>
                                        <img onclick="enviaSequencia(0);" id="btdown" src="/FrontOfficeWeb/resources/images/bt_down_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_down_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_down_over.gif'" style="border:none;cursor:hand"/>
                                    </td>
                                    <td style="text-indent:6px;" valign="middle" align="right">
                                        <b>Grupo:</b>
                                    </td>
                                    <td class="tblEstatica_campoNome" align="right" valign="middle">
                                        <input type="hidden" name="nmGrupo">
                                        <html:select name="Form" property="grupo" style="width=200" onmouseover="ativarToolTip(this,'1')">
                                            <html:option value="-1">Selecione um Grupo</html:option>
                                            <html:options collection="GrupoVO" property="codigo" labelProperty="descricao"/>
                                        </html:select>
                                    </td>                                         
                                </tr>
                            </table>
                        </td>
                    </tr>    
                    <tr>
                        <td>            
                            <table align="center">
                                <tr>
                                    <td align="center">
                                        <vivo:tbTable selectable="true" layoutWidth="725" layoutHeight="300" tableWidth="725" styleId="tableTitle" sortable="true">
                                            <vivo:tbHeader>
                                                <vivo:tbHeaderColumn align="center" width="7%" type="string">&nbsp;</vivo:tbHeaderColumn>
                                                <vivo:tbHeaderColumn align="left"   width="43%" type="string">Perfis</vivo:tbHeaderColumn>
                                                <vivo:tbHeaderColumn align="center" width="43%" type="string">Grupos</vivo:tbHeaderColumn>
                                                <vivo:tbHeaderColumn align="center" width="7%" type="string"></vivo:tbHeaderColumn>
                                            </vivo:tbHeader>
                                            <vivo:tbRows>
                                            <logic:iterate name="PerfilVariaveisVO" property="perfilGrupoVOArray" id="perfilGrupo" indexId="idx">
                                                <vivo:tbRow key="linha">
                                                    <vivo:tbRowColumn key="linhaPerfil">
                                                        <input type="radio" name="perfilLista" id="perfilLista" value="<%=idx%>" class="radio" align="middle"/>
                                                    </vivo:tbRowColumn>
                                                    <vivo:tbRowColumn key="linhaPerfil">
                                                        <bean:write name="perfilGrupo" property="perfil.nmPerfil"/>
                                                    </vivo:tbRowColumn>
                                                    <vivo:tbRowColumn key="linhaGrupo">
                                                        <bean:write name="perfilGrupo" property="grupoVO.descricao"/>
                                                    </vivo:tbRowColumn>   
                                                    <vivo:tbRowColumn>
                                                        <!--acesso:controlHiddenItem nomeIdentificador="usu_pg_editargrupo"-->
                                                        <a href="Javascript:excluirPerfilGrupo('<bean:write name="perfilGrupo" property="perfil.idPerfil"/>','<bean:write name="perfilGrupo" property="grupoVO.codigo" format="######"/>', '<%=idx%>');"><img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" border="0" alt="Excluir Perfil Grupo"></a>
                                                        <!--/acesso:controlHiddenItem-->
                                                    </vivo:tbRowColumn>                                                 
                                                </vivo:tbRow>
                                            </logic:iterate>
                                            </vivo:tbRows>
                                        </vivo:tbTable>                                 
                                    </td>
                                </tr>
                            </table>                        
                        </td>
                    </tr>        
                    <tr>
                        <td align="right">              
                            <img  style="cursor:hand;border:none;" id="btAssociar" onClick="efetivarAssociacao();" src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_gravar_over.gif'"/>
                        </td>
                    </tr>   
                </table>
            </div>            
            <iframe scrolling="yes" style="visibility:hidden;" name="frameEscondido" id="frameEscondido" height="1px" width="1px"></iframe>
        </form>  
<vivo:alert atributo="msgRetornoExclusao"/>
    <script>
        document.body.style.backgroundColor = '#ededed';
    </script>
<!--/acesso:controlHiddenItem-->
</body>