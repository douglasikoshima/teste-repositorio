<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>

<acesso:controlInitEnv/>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="uploadForm"/>

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Permissão Massiva de Usuários" />
    <netui-template:setAttribute name="modulo" value="Administração" />
    <netui-template:section name="headerSection">
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
        <script language="javascript" type="text/javascript">
            upload = function() {
                var f = document.forms[0];
                var t = f.fileUpload.value.length;
                var tp = f.sgFuncionalidade.value;
				
                var str = replaceAll(f.fileUpload.value, "\\", "/");
                var fName = str.substring(str.lastIndexOf("/") + 1);
				
                if (f.fileUpload.value == "") {
                    alert('Por favor, selecione um arquivo para upload.');
                } else if (f.fileUpload.value.toUpperCase().substr(t-4)!=".TXT") {
                    alert('Erro no Arquivo: Extensão Inválida.');
                } else if (fName.length > 34) {
                    alert('Erro no Arquivo: Erro no nome do arquivo.');
                } else if (f.sgFuncionalidade.value == '') {
                    alert('Por favor, selecione uma funcionalidade.');
                } else {
                    mostrar_div();
                    f.submit();
                }
            }
            
            replaceAll = function(string, token, newtoken) {
            	while (string.indexOf(token) != -1) {			
             		string = string.replace(token, newtoken);
            	}
            	return string;
            }
        </script>
        <script language="javascript" for="window" event="onload">
            <logic:notEmpty name="msgStatus" scope="request">
            alert('<bean:write name="msgStatus" scope="request"/>');
            </logic:notEmpty>
            oculta_div();
        </script>
    </netui-template:section>
    <netui-template:section name="bodySection">
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
        <div>
            <img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5">
        </div>
        <vivo:sessao id="qdMain" height="460" width="790" label="Permissão Massiva de Usuários" operacoes="Carregar Arquivos">
            <form action="uploadArquivo.do" enctype="multipart/form-data" method="post" onsubmit="return false;">
                <div>
                    <img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="10">
                </div>
            <table width="760" border="0" cellspacing="1" cellpadding="1" align="center" class="tbl_bgGray">
                <tr>
                    <td style="padding:5px;" width="130" nowrap>Arquivo para upload:</td>
                    <td style="padding:5px;" colspan="2"><html:file name="Form" property="fileUpload" style="width:400px;"/></td>
                </tr>

                <tr>
                  	<td style="padding:5px;">Funcionalidade:</td>
                        <td style="padding-left: 9px;"><html:select name="Form" property="sgFuncionalidade" styleId="sgFuncionalidade" style="width:350px;">
			                <option value="">Selecione</option>
                        <option value="MANUTA">Manutenção Acesso</option>
                        <option value="MANUTL">Manutenção Login</option>
                        <option value="MANUTC">Criação de Usuário</option>
                    </html:select></td>
                    <td align="right" style="padding-right: 5px;"><img src="/FrontOfficeWeb/resources/images/bt_enviar_nrml.gif" onclick="upload();" style="border: none; cursor: pointer;" /></td>
                </tr>
            </table>
            </form>
        </vivo:sessao>
    </netui-template:section>
</netui-template:template>