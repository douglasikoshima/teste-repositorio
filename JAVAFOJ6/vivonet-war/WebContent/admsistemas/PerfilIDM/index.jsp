<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="perfilIdmForm"/>

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Perfil IDM"/>
    <netui-template:setAttribute name="modulo" value="Gestão de Usuários"/>

    <netui-template:section name="headerSection">
        <script language="javascript">
        <!--
            onload = function(){
                //if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                CarregaAba($('bt01'));
                <logic:notEmpty name="Form" property="msgError">
                createNewPopUpText('msgErr','Aviso',300,100,null,true,'<b><bean:write name="Form" property="msgError"/></b>','','font-size:12px;text-align:center');
                </logic:notEmpty>
            }

            hasPerfil = function(){
                if($('idPerfil').value!='0' && $('idPerfil').value!=''){
                    return true;
                }
                return false;
            }

            function CarregaAba(o){
                if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.startAnimation();
                var pagina = '';
                if(o.id=='bt01') pagina = "/FrontOfficeWeb/admsistemas/PerfilIDM/relGrupo.do"+ getParam();
                if(o.id=='bt02') pagina = "/FrontOfficeWeb/admsistemas/PerfilIDM/relPerfil.do"+ getParam();
                if(o.id=='bt03') pagina = "/FrontOfficeWeb/admsistemas/PerfilIDM/relItemMenu.do"+ getParam();
                $('ifrmAbas').src = pagina;
                abaSelected($('btAba'), o);
            }

            validaNome = function(){
                if($('idPerfil').value=='0'){
                    if($F('nmPerfil').blank()){
                        alert('Por favor, crie um perfil preenchendo o nome do Perfil IDM.');
                        return false;
                    }
                }else if($('idPerfil').value==''){
                    alert('Por favor, selecione um Perfil ou crie um novo.');
                    return false;
                }
                return true;
            }

            getParam = function(){
                var param = '?';
                if(hasPerfil()){
                    param += 'idPerfil='+$('idPerfil').value;
                }else{
                    param += 'nmPerfil='+escape($('nmPerfil').value);
                }
                return param;
            }

            voltar = function(){
                location.href = '<%=request.getContextPath()%>/index.jsp';
            }

            showNome = function(o){
                if(o.value=='0'){
                    $('DspNome').style.visibility = 'visible';

                }else{
                    $('DspNome').style.visibility = 'hidden';
                }
                CarregaAba($('bt01'));
                return true;
            }

            seleciona = function(o){
                if(hasPerfil()){
                    if(validaNome()){
                        CarregaAba(o);
                    }
                }else{
                    alert('Por favor, selecione ou crie um Perfil antes de relacionar outras variáveis.');
                    return false;
                }
                return true;
            }
        -->
        </script>
    </netui-template:section>

    <netui-template:section name="bodySection">

        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="1"></div>

        <vivo:body idTable="tbMain" idDiv="dvMain" height="495" width="790">
            <vivo:quadro id="qdMain" height="468" width="780" label="Editar Usuario > Perfil IDM">
                <form id="frmMain" name="frmMain" method="post" style="margin:0px;" onsubmit="return false;"/>
                    <table width="100%" cellspacing="1" cellpadding="1">
                        <tr>
                            <td>
                                <table width="98%" border="0" cellspacing="1" cellpadding="1" align="center">
                                    <tr><td height="1"></td></tr>
                                    <tr>
                                        <td align="center">
                                            <table width="100%" border="0" cellspacing="1" cellpadding="1" align="center" class="tbl_bgGray">
                                                <tr><td height="1" colspan="4"></td></tr>
                                                <tr>
                                                    <td width="10%" class="tblEstatica_campoNome">Perfil IDM:</td>
                                                    <td class="tblEstatica_campoValor" colspan="3">&nbsp;
                                                        <html:select name="Form" property="idPerfil" styleId="idPerfil" style="width:270px;" styleClass="SELECT" onchange="showNome(this);">
                                                            <option value="">-- Selecione --</option>
                                                            <option value="0">Nova associação</option>
                                                            <logic:present name="Form" property="lstPerfisIdm">
                                                                <logic:iterate id="it" name="Form" property="lstPerfisIdm.itArray">
                                                                    <option value="<bean:write name="it" property="id"/>"><bean:write name="it" property="ds"/></option>
                                                                </logic:iterate>
                                                            </logic:present>
                                                        </html:select>
                                                    </td>
                                                </tr>
                                                <tr id="DspNome" style="visibility:hidden;">
                                                    <td class="tblEstatica_campoNome">Nome:</td>
                                                    <td class="tblEstatica_campoValor" colspan="3">
                                                        <html:text name="Form" property="nmPerfil" styleId="nmPerfil" size="50"/>
                                                    </td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                    <tr><td height="1"></td></tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                </form>
                <!--table><tr><td height="1"></td></tr></table-->
                <table width="98%" cellpadding="0" cellspacing="0" align="center">
                    <tr>
                        <td valign="top">
                            <vivo:abaGrupo id="btAba" width="80%" height="10px" styleClass="abatexto">
                                <vivo:abaItem id="bt01" onclick="seleciona(this);" value="Relacionar Grupo e Operadora*"/>
                                <vivo:abaItem id="bt02" onclick="seleciona(this);" value="Relacionar Perfil"/>
                                <vivo:abaItem id="bt03" onclick="seleciona(this);" value="Relacionar Item Menu"/>
                            </vivo:abaGrupo>
                        </td>
                    </tr>
                    <tr>
                        <td valign="top" width="100%">
                            <table width="100%" cellpadding="0" cellspacing="0" align="center">
                                <tr>
                                    <td valign="top" align="center" width="100%" class="tbl_bgGray">
                                        <iframe id="ifrmAbas" width="100%" height="360px" frameborder=0 scrolling="no" src="about:blank"></iframe>
                                    </td>
                                </tr>
                            </table>
                            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
                            <img onClick="voltar();" style="border:none;cursor:hand;" id="btVoltar" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_voltar_over.gif'"/>
                        </td>
                    </tr>
                </table>
            </vivo:quadro>
        </vivo:body>
    </netui-template:section>
</netui-template:template>
