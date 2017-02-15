<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="br.com.vivo.fo.admsistemas.vo.AdmSkillUsuarioVODocument.AdmSkillUsuarioVO.UsuarioSkillAssociadoVO"%>
<%@ page import="usuario.manterSkillUsuario.abaAssocSkillUsuario.ManterSkillUsuarioController.SkillUsuarioForm"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<bean:define id="FormSkill"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="skillUsuarioForm"/>
    <head>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    </head>
    <body>
        <div id="div_lista_usuario_inner">
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
                                                if(u[j]!=null){%>          
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
                                              <%}
                                            }
                                        }
                                    }else{%>    
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
</body>
    <script>
        parent.document.getElementById('div_lista_manter_usuario').innerHTML = document.getElementById('div_lista_usuario_inner').innerHTML;    
        parent.document.getElementById('chkTodos').checked = false;
        parent.document.getElementById('paramChkUser').value = '';
        parent.parent.oculta_div();
    </script>
    <vivo:alert atributo="msgStatus" scope="request"/>