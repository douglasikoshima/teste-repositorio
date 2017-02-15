<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.admsistemas.vo.AdmContatoPalitagemVODocument.AdmContatoPalitagemVO.Palitagem"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%  response.setContentType("text/html;charset=ISO-8859-1"); %>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreContato"/>

<netui-template:template templatePage="/resources/jsp/templateUsuarioClean.jsp">
    <netui-template:section name="headerSection">
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
        <script type="text/javascript" language="javascript">

			var MSG_SISTEMA        = 'Por favor, selecione um sistema para o serviço.';
			var MSG_DESC_SERVICO   = 'Por favor, digite uma descrição para o serviço.';
			var MSG_COD_SERVICO    = 'Por favor, digite o código para o serviço.';
			var MSG_PROCEDENCIA    = 'Por favor, selecione uma procedência para ser registrada na efetivação deste serviço.';
			var MSG_CONF_ALTERACAO = 'Deseja realmente alterar o serviço selecionado?';

            onload = function() {
                if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                <logic:notEqual name="Form" property="dadosPalitagem.idPalitagem" value="">
                $('cdServico').readOnly = true;
                </logic:notEqual>
            };
            
            loadPalitagem = function(indexPalitagem) {
                var f = document.forms[0];
                if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();
                $('acao').value = 'loadPalitagem';
                f.action = "managePalitagem.do?indexPalitagem=" + indexPalitagem;
                f.submit();
            };
            
            setPalitagem = function() {
                var f = document.forms[0];
                if (validateFields()) {
                    if ($F('acao') == '') {
                        $('acao').value = 'inclusao';
                    }
                    if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();
                    f.submit();
                }
            };
            
			limparCampos = function() {
				$('idSistema').selectedIndex = 0;
				$('idProcedencia').selectedIndex = 0;
				$('dsServico').value = '';

				if ($F('acao').blank()) {
					$('cdServico').value = '';
				}
			};
			
            unsetPalitagem = function(indexPalitagem) {
                if (confirm('Deseja realmente excluir este registro?')) {
                    if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();
                    var f = document.forms[0];
                    $('acao').value = 'unsetPalitagem';
                    f.action = "managePalitagem.do?indexPalitagem=" + indexPalitagem;
                    f.submit();
                }
            };
            
            validateFields = function() {
                if ($F('idSistema').blank()) {
                    alert(MSG_SISTEMA);
                    setTimeout(function(){$('idSistema').focus()}, 300);
                    return false;
                
                } else if ($F('dsServico').blank()) {
                    alert(MSG_DESC_SERVICO);
                    setTimeout(function(){$('dsServico').focus()}, 300);
                    return false;
                
                } else if ($F('cdServico').blank()) {
                    alert(MSG_COD_SERVICO);
                    setTimeout(function(){$('cdServico').focus()}, 300);
                    return false;
                
                } else if ($F('idProcedencia').blank()) {
                    alert(MSG_PROCEDENCIA);
                    setTimeout(function(){$('idProcedencia').focus()}, 300);
                    return false;
                }
                
                <logic:notEqual name="Form" property="dadosPalitagem.idPalitagem" value="">
                if (confirm(MSG_CONF_ALTERACAO)) {
                    return true;
                } else {
                    return false;
                }
                </logic:notEqual>
                
                return true;
            };
        </script>
        <style type="text/css">
            input {
                background:#ffffff;
            }
            body {
                background#:e8f2fa;
            }
            select {
                margin-left:3px;
            }
        </style>
    </netui-template:section>
    <netui-template:section name="bodySection">
    <form action="managePalitagem.do" id="managePalitagem" name="managePalitagem" onSubmit="return false;" method="post">
        <html:hidden name="Form" property="dadosPalitagem.idPalitagem" styleId="idPalitagem" />
        <html:hidden name="Form" property="admContatoPalitagemVO.acao" styleId="acao" />
        <vivo:tbTable selectable="true" layoutWidth="574" layoutHeight="330" tableWidth="574" styleId="tbResultadoPesquisa" sortable="true">
            <vivo:tbHeader>
                <vivo:tbHeaderColumn align="center" width="16%" type="string">Sistema</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="26%" type="string">Serviço</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="16%" type="string">Código</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="16%" type="string">Procedência</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="16%" type="string">Data de alteração</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="5%" type="string"></vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="5%" type="string"></vivo:tbHeaderColumn>
            </vivo:tbHeader>
            <vivo:tbRows scroll="false">
                <logic:notEmpty name="Form" property="admContatoPalitagemVO.palitagemArray">
                <logic:iterate id="palitagem" name="Form" property="admContatoPalitagemVO.palitagemArray" indexId="idx" type="br.com.vivo.fo.admsistemas.vo.AdmContatoPalitagemVODocument.AdmContatoPalitagemVO.Palitagem">
                <vivo:tbRow key="linha">
                    <vivo:tbRowColumn><bean:write name="palitagem" property="sistemaVO.nmSistema" /></vivo:tbRowColumn>
                    <vivo:tbRowColumn><bean:write name="palitagem" property="servico.dsServico" /></vivo:tbRowColumn>
                    <vivo:tbRowColumn><bean:write name="palitagem" property="servico.cdServico" /></vivo:tbRowColumn>
                    <vivo:tbRowColumn><bean:write name="palitagem" property="admProcedenciaVO.nmProcedencia" /></vivo:tbRowColumn>
                    <vivo:tbRowColumn><bean:write name="palitagem" property="dtAlteracao" /></vivo:tbRowColumn>
                    <vivo:tbRowColumn><img width="18" height="18" alt="Alterar" title="Alterar" src="<%=request.getContextPath()%>/resources/images/bt_icon_alterar.gif" style="cursor:pointer" onmouseup="loadPalitagem(<%=idx%>)" /></vivo:tbRowColumn>
                    <vivo:tbRowColumn><img width="18" height="18" alt="Excluir" title="Excluir" src="<%=request.getContextPath()%>/resources/images/bt_icon_excluir.gif" style="cursor:pointer" onmouseup="unsetPalitagem(<%=idx%>)" /></vivo:tbRowColumn>
                </vivo:tbRow>
                </logic:iterate>
                </logic:notEmpty>
            </vivo:tbRows>
        </vivo:tbTable>

        <table width="570" style="margin:10px" cellspacing="3">
            <tr>
                <td>Sistema</td>
                <td>
                    <bean:define id="Sistemas" name="Form" property="admContatoPalitagemVO.listas.sistemaVOArray" />
                    <html:select name="Form" property="dadosPalitagem.sistemaVO.idSistema" styleId="idSistema">
                        <option value="">-- Selecione --</option>
                        <html:options collection="Sistemas" property="idSistema" labelProperty="nmSistema" />
                    </html:select>
                </td>
            </tr>
            <tr>
                <td>Serviço</td>
                <td>
                    <html:text name="Form" property="dadosPalitagem.servico.dsServico" styleId="dsServico" />
                </td>
            </tr>
            <tr>
                <td>Código</td>
                <td>
                    <html:text name="Form" property="dadosPalitagem.servico.cdServico" styleId="cdServico" />
                </td>
            </tr>
            <tr>
                <td width="80" nowrap>Procedência</td>
                <td width="100%">
                    <bean:define id="Procedencias" name="Form" property="admContatoPalitagemVO.listas.admProcedenciaVOArray" />
                    <html:select name="Form" property="dadosPalitagem.admProcedenciaVO.idProcedencia" styleId="idProcedencia">
                        <option value="">-- Selecione --</option>
                        <html:options collection="Procedencias" property="idProcedencia" labelProperty="nmProcedencia" />
                    </html:select>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="right">
					<img width="52" height="13" alt="Limpar" title="Gravar" src="<%=request.getContextPath()%>/resources/images/bt_limpar_nrml.gif" style="cursor:pointer" onmouseup="limparCampos()" />
                    <logic:equal name="Form" property="admContatoPalitagemVO.acao" value="">
					<img width="52" height="13" alt="Incluir" title="Incluir" src="<%=request.getContextPath()%>/resources/images/bt_incluir_nrml.gif" style="cursor:pointer" onmouseup="setPalitagem()" />
					</logic:equal>
					<logic:notEqual name="Form" property="admContatoPalitagemVO.acao" value="">
					<img width="61" height="13" alt="Gravar" title="Gravar" src="<%=request.getContextPath()%>/resources/images/bt_gravar_nrml.gif" style="cursor:pointer" onmouseup="setPalitagem()" />
					</logic:notEqual>
                </td>
            </tr>
        </table>

        <vivo:alert atributo="msgRetorno" scope="request" />

    </form>
    </netui-template:section>
</netui-template:template>
