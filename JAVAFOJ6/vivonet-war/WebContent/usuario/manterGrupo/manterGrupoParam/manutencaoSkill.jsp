<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>


<bean:define id="FormGrupo"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaGruposForm"/>
<!-- utilizado para percorrer cada item do array de resultados-->

<html>
    <head>
    <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
    <link href="<%=request.getContextPath()%>/resources/css/xtree.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/xtree.js"></script>
    <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>      
    <script language="Javascript">

   /* Funcão que passa um id do Skill a ser removido para a Action
    * by Jonas Henrique Bernardes
    */
    function removeSkill(id) {
      document.forms[0].target = '';
      document.forms[0].idSkill.value =id;
      document.forms[0].action = "removeSkill.do";
      if (window.confirm("Confirma remoção do Skill?")) {
        parent.mostrar_div();
        document.forms[0].submit(); 
      }
    }
    
    
    function pesquisaSkill(dsPesquisa){
      document.forms[0].target = '';
      document.forms[0].action = "listaSkill.do";
      //document.forms[0].dsGrupoSkill.value = 
      document.forms[0].submit(); 
    }
    
     function limpar(){
            document.forms[0].target = '';
            document.forms[0].action = 'manutencaoSkill.do';
            document.forms[0].submit();
     }

   /*
    *  Funcão que passa um id vazio para incluir e id não vazio para alterar
    */    
    
    function incluiAlteraSkill(id) {
      document.forms[0].target = '';
      if(id !=null && id != 'undefined'){
        document.forms[0].idSkill.value =id;
        divAlteraSkill.style.display = '';
        document.forms[0].target = "ifrmAlteraSkill";
      }else{
        document.forms[0].idSkill.value ='';
        divIncluiSkill.style.display = '';
        document.forms[0].target = "ifrmIncluiSkill";
      }            
      document.forms[0].action = "incluirAlterarSkill.do";
      parent.mostrar_div();
      document.forms[0].submit();
    }

   /*
    *  Funcão reponsável por chamar um vivo quadro para parametrizar um skill
    */    
    function parametrizar(idSkill) {
      parent.divParametrizarSkill.style.display = '';
      document.forms[0].target = "ifrmParametrizarSkill";
      document.forms[0].action = "parametrizarSkill.do";
      document.forms[0].idSkill.value =idSkill;
      parent.mostrar_div();
      document.forms[0].submit();
    }
        
    function listaEnter(ev){
      if(ev.keyCode == 13)
        lista();
      else
        return false;    
      }
              
        
    </script>
    <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
       <!--    
          if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation(); 
          if('<bean:write name="FormGrupo" property="msgError" />' != ""){
            alert('<bean:write name="FormGrupo" property="msgError" />');
          }
          
       -->
    </SCRIPT>
    </head>
    <body>    
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="3"></div>
		<form action="listaSkill" onsubmit="return false;" method="post">
			<html:hidden name="FormGrupo" property="idSkill" styleId="idSkill"/>
			<html:hidden name="FormGrupo" property="dsSkill" styleId="dsSkill"/>
			<html:hidden name="FormGrupo" property="indicativoAlt"/>
			<html:hidden name="FormGrupo" property="idGrupo"/>
			<html:hidden name="FormGrupo" property="idAlt"/>
            <table width="100%" border="0" cellspacing="1" cellpadding="1">
	            <tr>
	                <td>
                    <table width="100%" border="0" cellspacing="1" cellpadding="1" align="center" class="tbl_bgGray">
                        <tr><td height="4"></td></tr>
                        <tr>
                            <td align="center">
                                <table width="100%" border="0" cellspacing="2" cellpadding="0" align="center" class="tbl_bgBlue">
                                    <tr><td height="6"></td></tr>
                                    <tr>
                                        <td class="tblEstatica_campoNome" align="left">
                                            <netui:label value="Nome do Skill:" styleClass="tblEstatica_campoNome"/>
                                        </td>
                                        <td align="left">
                                            &nbsp; <html:text name="FormGrupo" property="dsGrupoSkill" tabindex="1" style="width:400px" styleClass="input" maxlength="200" onkeypress="listaEnter(event);"/> 
                                        </td>
                                        <td align="left">&nbsp;
                                            <!--acesso:controlHiddenItem nomeIdentificador="usu_pg_limpar"-->
                                            <img style="cursor:hand;border:none" id="btLimpar" onclick="limpar();" src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif" onmouseout="this.src='/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif'" onmouseover="this.src='/FrontOfficeWeb/resources/images/bt_limpar_over.gif'"/>
                                            <!--/acesso:controlHiddenItem-->
                                        </td>
                                        <td align="left">
                                            <!--acesso:controlHiddenItem nomeIdentificador="usu_pg_listar"-->
                                            <img style="cursor:hand;border:none" id="btPesquisar" onclick="pesquisaSkill();" src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" onmouseout="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif'" onmouseover="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif'" />
                                            <!--/acesso:controlHiddenItem-->
                                        </td>
                                    </tr>
	                                <tr><td height="6"></td></tr>
                                </table>
                            </td>
                        </tr>
	                    <tr><td height="4"></td></tr>
	                    <logic:greaterThan name ="FormGrupo" property="arraySkillLength" value="0"> 
                        <tr>
	                        <td align="center">
	                            <table width="98%" border="0" cellspacing="1" cellpadding="1" align="center">
	                                <tr>
	                                    <td width="100%" align="center">
					                        <vivo:tbTable selectable="true" layoutWidth="705" layoutHeight="100" tableWidth="695" styleId="tableTitle" sortable="true">
						                        <vivo:tbHeader>
							                        <vivo:tbHeaderColumn align="left"   width="" type="string">Lista de Skill´s </vivo:tbHeaderColumn>
							                        <vivo:tbHeaderColumn align="center" width="20" type="string">&nbsp;</vivo:tbHeaderColumn>
							                        <vivo:tbHeaderColumn align="center" width="20" type="string">&nbsp;</vivo:tbHeaderColumn>
							                        <vivo:tbHeaderColumn align="center" width="20" type="string">&nbsp;</vivo:tbHeaderColumn>
						                        </vivo:tbHeader>
						                        <vivo:tbRows>
						                            <logic:iterate name="FormGrupo" property="listaSkill" id="itemSkill">
							                            <vivo:tbRow key="linha">
								                            <vivo:tbRowColumn>
									                            <vivo:hint maxLength="80"> <bean:write name="itemSkill" property="dsSkill"/></vivo:hint>
								                            </vivo:tbRowColumn>
								                            <vivo:tbRowColumn>    
									                            <a href="Javascript:incluiAlteraSkill('<bean:write name="itemSkill" property="idSkill"/>');">
										                            <img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" border="0" alt="Editar Skill">
									                            </a>
								                            </vivo:tbRowColumn>
								                            <vivo:tbRowColumn>
									                            <a href="#" onclick="removeSkill('<bean:write name="itemSkill" property="idSkill" />'); return false">
										                            <img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" border="0" alt="Excluir Skill">
									                            </a>
								                            </vivo:tbRowColumn>
								                            <vivo:tbRowColumn>
									                            <a href="#" onclick="parametrizar('<bean:write name="itemSkill" property="idSkill" />');return false">
										                            <img src="/FrontOfficeWeb/resources/images/bt_icon_propried.gif" alt="Editar Parametros" border="0">
									                            </a>
								                            </vivo:tbRowColumn>
							                            </vivo:tbRow>
						                            </logic:iterate>
                                                </vivo:tbRows>
					                        </vivo:tbTable>
					                        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="2"></div>
				                        </td>
				                    </tr>
				                    <tr>
					                    <td width="100%" align="center">
						                    <table width="720" border="0" cellspacing="0" cellpadding="5" style="border:1px solid #D4D7DE; background-color:#F7F9FA; font-size:9px;">
							                    <tr>
								                    <td valign="middle" width="70"><b>&nbsp;Legendas:</b></td>
								                    <td>
									                    <img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" align="middle"> &nbsp;Editar Skill
								                    </td>
								                    <td>
									                    <img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" align="middle"> &nbsp;Remover Skill
								                    </td>
								                    <td>
									                    <img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="4">
								                    </td>
								                    <td>
									                    <img src="/FrontOfficeWeb/resources/images/bt_icon_propried.gif" align="middle"> &nbsp;Editar Parâmetros
								                    </td>
							                    </tr>
						                    </table>
					                    </td>
				                    </tr>
                        </logic:greaterThan>
	                    <logic:notEqual name="FormGrupo" property="arraySkillLength" value="-1">
	                       <logic:equal name="FormGrupo" property="arraySkillLength" value="">
				                    <tr>
					                    <td align="center">
						                    <table width="100%" height="165" border="0" cellspacing="1" cellpadding="1" align="center">
							                    <tr align="center">
								                    <td width="100%" align="center" class="tblEstatica_campoNome">
									                    Não há nenhum Skill cadastrado para o Grupo <bean:write name="FormGrupo" property="itemGrupo.dsGrupo"/>. 
								                    </td>
							                    </tr>
						                    </table>
					                    </td>
                                    </tr>
                            </logic:equal>
                        </logic:notEqual>
				                    <tr>
					                    <td align="right">
						                    <img align="right" id="btIncluir" onClick="incluiAlteraSkill();return false" src="/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_incluir_over.gif'" style="cursor:hand;border:none;" hspace="5" vspace="5" />
					                    </td>
				                    </tr>
			                    </table>
		                    </td>
			            </tr>
			        </table>
		        </tr>
		    </table>
	    </form>
		<vivo:quadroFlutuante label="Inclusão de Skill"  scroll="false" src="" idIframe="ifrmIncluiSkill" id="divIncluiSkill" spacesLeft="150" height="80" spacesTop="120" url="<%=request.getContextPath()%>" display="none" width="500"></vivo:quadroFlutuante>
		<vivo:quadroFlutuante label="Alteração de Skill"  scroll="false" src="" idIframe="ifrmAlteraSkill" id="divAlteraSkill" spacesLeft="150" height="80" spacesTop="120" url="<%=request.getContextPath()%>" display="none" width="500"></vivo:quadroFlutuante>
	</body>	
</html>