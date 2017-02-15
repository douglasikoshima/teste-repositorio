<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
   
<acesso:controlInitEnv/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
<link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
<script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/calendar.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/validacao.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>

<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm"/>
<logic:notEmpty name="form" property="WFAcaoRetornoVO">
    <bean:define id="acao" name="form" property="WFAcaoRetornoVO"/>
<script>
    <logic:equal name="acao" property="acaoExecucao" value="N">
        alert('<bean:write name="acao" property="mensagem"/>');
    </logic:equal>
</script>
</logic:notEmpty>

<acesso:controlHiddenItem nomeIdentificador="wor_da_verpagina">
<table cellpadding="0" cellspacing="0">
    <tr>
        <td height="16">
            <vivo:abaGrupo id="abasAcoes" width="400" height="16" styleClass="abatexto">
                <logic:notEqual name="form" property="idAtendimento" value="0">
                <acesso:controlHiddenItem nomeIdentificador="wor_da_abaassociar"><vivo:abaItem id="abaDocsAssociados" onclick="abaSelected(abasAcoes, abaDocsAssociados); showDivDocsAssociados();" value="Associados" select="yes"/></acesso:controlHiddenItem>
                </logic:notEqual>
                <acesso:controlHiddenItem nomeIdentificador="wor_da_abadoc"  ><vivo:abaItem id="abaDocumentos" onclick="abaSelected(abasAcoes, abaDocumentos); showDivDocumentos();" value="Associar"/></acesso:controlHiddenItem>
                <acesso:controlHiddenItem nomeIdentificador="wor_da_abaabert"><vivo:abaItem id="abaAbert"      onclick="abaSelected(abasAcoes, abaAbert); showDivAbertura();"        value="Abertura"/></acesso:controlHiddenItem>
                <acesso:controlHiddenItem nomeIdentificador="wor_da_abaencer"><vivo:abaItem id="abaEncer"      onclick="abaSelected(abasAcoes, abaEncer); showDivEncerramento();"    value="Encerramento"/></acesso:controlHiddenItem>
            </vivo:abaGrupo>
        </td>
    </tr>
</table>

<div id="dvDocumentos" style="display:none;">
    <iframe src="documentoAssociadoPesquisarTodos.do" id="ifrmDocumentoAssociadoPesquisarTodos" width="100%" scrolling="no" height="135" frameborder="0">
    </iframe>
</div>

<div id="idDocAss" style="display:none;">
    <iframe id="ifrmDocumentoAssociadoPesquisarAssociados" src='documentoAssociadoPesquisarAssociados.do' width="100%" scrolling="no" height="135" frameborder="0">
    </iframe>
</div>

<div id="idAbertura" style="display:none;">
    <iframe src='DocumentoAssociadoIframeAbertura.jsp' width="100%" scrolling="no" height="135" frameborder="0">
    </iframe>
</div>

<div id="idEncerramento" style="display:none;">
    <iframe src='DocumentoAssociadoIframeFechamento.jsp' width="100%" scrolling="no" height="135" frameborder="0">
    </iframe>
</div>

<iframe id="frameRetornoDocTec" name="frameRetornoDocTec" frameborder="0" scrolling="no" height="0" width="0" style="top:0; left: 0;">
</iframe>
   
</acesso:controlHiddenItem>

<script language="JavaScript">
    var up=parent.up;
    <logic:notEqual name="form" property="idAtendimento" value="0">
    document.all['abaDocsAssociados'].click();
    </logic:notEqual>
    <logic:equal name="form" property="idAtendimento" value="0">
    document.all['abaDocumentos'].click();
    </logic:equal>
    function validaData(data){
        if(data.value == '')
            return false;
        if(!validate_date(data.value)){
            data.value = '';
            data.focus();
            alert("Data inválida");
        }
    }

    function alteraSize() {
        <logic:notEqual name="form" property="docTec" value="1">
        document.getElementById('ifrmDocumentoAssociadoPesquisarTodos').style.height="320";
        document.getElementById('ifrmDocumentoAssociadoPesquisarAssociados').style.height="320";
        if (ifrmDocumentoAssociadoPesquisarAssociados.document.getElementById('docTecAssoc_div') != null)
        {
            ifrmDocumentoAssociadoPesquisarAssociados.document.getElementById('docTecAssoc_div').style.height="290";
        }
        if (ifrmDocumentoAssociadoPesquisarTodos.document.getElementById('docTecTodos_div') != null)
        {
            ifrmDocumentoAssociadoPesquisarTodos.document.getElementById('docTecTodos_div').style.height="220";
        }
        up = false;
        </logic:notEqual>
        <logic:equal name="form" property="docTec" value="1">
            <logic:equal name="form" property="idAtendimento" value="0">
                document.getElementById('ifrmDocumentoAssociadoPesquisarTodos').style.height="420";
                document.getElementById('ifrmDocumentoAssociadoPesquisarAssociados').style.height="420";
                if (ifrmDocumentoAssociadoPesquisarAssociados.document.getElementById('docTecAssoc_div') != null)
                {
                    ifrmDocumentoAssociadoPesquisarAssociados.document.getElementById('docTecAssoc_div').style.height="410";
                }
                if (ifrmDocumentoAssociadoPesquisarTodos.document.getElementById('docTecTodos_div') != null)
                {
                    ifrmDocumentoAssociadoPesquisarTodos.document.getElementById('docTecTodos_div').style.height="320";
                }
            </logic:equal>
        </logic:equal>
    }

    function alteraSizeMin() {
        <logic:notEqual name="form" property="docTec" value="1">
        document.getElementById('ifrmDocumentoAssociadoPesquisarTodos').style.height="135";
        document.getElementById('ifrmDocumentoAssociadoPesquisarAssociados').style.height="135";
        if (ifrmDocumentoAssociadoPesquisarAssociados.document.getElementById('docTecAssoc_div') != null)
        {
            ifrmDocumentoAssociadoPesquisarAssociados.document.getElementById('docTecAssoc_div').style.height="110";
        }
        if (ifrmDocumentoAssociadoPesquisarTodos.document.getElementById('docTecTodos_div') != null)
        {
            ifrmDocumentoAssociadoPesquisarTodos.document.getElementById('docTecTodos_div').style.height="52";
        }
        up = true;
        </logic:notEqual>
        <logic:equal name="form" property="docTec" value="1">
            <logic:equal name="form" property="idAtendimento" value="0">
                document.getElementById('ifrmDocumentoAssociadoPesquisarTodos').style.height="420";
                document.getElementById('ifrmDocumentoAssociadoPesquisarAssociados').style.height="420";
                if (ifrmDocumentoAssociadoPesquisarAssociados.document.getElementById('docTecAssoc_div') != null)
                {
                    ifrmDocumentoAssociadoPesquisarAssociados.document.getElementById('docTecAssoc_div').style.height="410";
                }
                if (ifrmDocumentoAssociadoPesquisarTodos.document.getElementById('docTecTodos_div') != null)
                {
                    ifrmDocumentoAssociadoPesquisarTodos.document.getElementById('docTecTodos_div').style.height="320";
                }
            </logic:equal>
        </logic:equal>
    }

    function showDivDocumentos() {
        objDiv = document.getElementById('idEncerramento');
        objDiv.style.display = 'none';
        objDiv = document.getElementById('idAbertura');
        objDiv.style.display = 'none';
        objDiv = document.getElementById('idDocAss');
        objDiv.style.display = 'none';
        objDiv = document.getElementById('dvDocumentos');
        objDiv.style.display = '';
        DoResizeHeaderTableVivo();
    }
    function showDivDocsAssociados() {
        objDiv = document.getElementById('idEncerramento');
        objDiv.style.display = 'none';
        objDiv = document.getElementById('idAbertura');
        objDiv.style.display = 'none';
        objDiv = document.getElementById('dvDocumentos');
        objDiv.style.display = 'none';
        objDiv = document.getElementById('idDocAss');
        objDiv.style.display = '';
        DoResizeHeaderTableVivo();
    }   
    function showDivAbertura() {
        objDiv = document.getElementById('idEncerramento');
        objDiv.style.display = 'none';
        objDiv = document.getElementById('idDocAss');
        objDiv.style.display = 'none';
        objDiv = document.getElementById('dvDocumentos');
        objDiv.style.display = 'none';
        objDiv = document.getElementById('idAbertura');
        objDiv.style.display = '';
    }
    function showDivEncerramento() {
        objDiv = document.getElementById('idAbertura');
        objDiv.style.display = 'none';
        objDiv = document.getElementById('idDocAss');
        objDiv.style.display = 'none';
        objDiv = document.getElementById('dvDocumentos');
        objDiv.style.display = 'none';
        objDiv = document.getElementById('idEncerramento');
        objDiv.style.display = '';
    }
    document.body.style.backgroundColor = '#ededed';
</script>
