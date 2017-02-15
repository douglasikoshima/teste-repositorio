<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.workflow.vo.WFAtdNotaVODocument.WFAtdNotaVO"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
<bean:define id="notasForm" name="notasForm" scope="request" />
<html>
    <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
    <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    <body>
<acesso:controlHiddenItem nomeIdentificador="ntura_res_verpagina">
        <vivo:tbTable selectable="true" sortable="true" styleId="resultado" layoutHeight="245" tableWidth="757" layoutWidth="757">
            <vivo:tbHeader>
                <vivo:tbHeaderColumn width="70" type="number" align="center">Proc.</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn width="70" type="string" align="center">Tel.</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn width="70" type="date" align="center">Data/Hora</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn width="60" type="string" align="center">RE</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn width="130" type="string" align="center">Consultor</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn width="60" type="string" align="center">Tipo Nota</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn width="60" align="center" type="string">Ret.</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn width="150" type="string" align="center">Grupo</vivo:tbHeaderColumn>     
                <vivo:tbHeaderColumn align="center" width="40" type="string">Editar</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="40" type="string">Param.</vivo:tbHeaderColumn>           
            </vivo:tbHeader>
            <vivo:tbRows>
            <logic:iterate id="notaVO" name="notasForm" property="notasVO.WFAtdNotaVOArray">
                <%
                String idNota = ((WFAtdNotaVO)notaVO).getIdAtendimentoNota();
                int contatado = ((WFAtdNotaVO)notaVO).getCliContatado();
                String script = "javascript:detalhesNotaURA("+idNota+")";
                %>
                <vivo:tbRow key="linha">
                    <vivo:tbRowColumn>
                        <bean:write name="notaVO" property="idAtendimento"/>
                    </vivo:tbRowColumn>
                    <vivo:tbRowColumn>
                        <bean:write name="notaVO" property="nrLinha"/>
                    </vivo:tbRowColumn>
                    <vivo:tbRowColumn>
                        <bean:write name="notaVO" property="dtAberturaIni"/>
                    </vivo:tbRowColumn>
                    <vivo:tbRowColumn>
                        <bean:write name="notaVO" property="reConsultor"/>
                    </vivo:tbRowColumn>
                    <vivo:tbRowColumn>
                        <bean:write name="notaVO" property="nmUsuario"/>
                    </vivo:tbRowColumn>
                    <vivo:tbRowColumn>
                        <bean:write name="notaVO" property="tipoNotaAtendimento"/>
                    </vivo:tbRowColumn>
                    <vivo:tbRowColumn>
                        <logic:equal value="1" name="notaVO"  property="cliContatado">Sim</logic:equal>
                        <logic:equal value="0" name="notaVO"  property="cliContatado">Não</logic:equal>
                    </vivo:tbRowColumn>                   
                    <vivo:tbRowColumn>
                        <bean:write name="notaVO" property="nmGrupo"/>
                    </vivo:tbRowColumn>  
                    <vivo:tbRowColumn><acesso:controlHiddenItem nomeIdentificador="icri_respedi_editar"><a href="Javascript:parent.alteraNota('<%=idNota%>');"><img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" border="0" alt="Editar Nota"></a></acesso:controlHiddenItem></vivo:tbRowColumn>
                    <vivo:tbRowColumn><acesso:controlHiddenItem nomeIdentificador="icri_respedip_editpar"><a href="Javascript:parent.detNota('<%=idNota%>');"><img src="/FrontOfficeWeb/resources/images/bt_icon_propried.gif" alt="Editar Parametros" border="0"></a></acesso:controlHiddenItem></vivo:tbRowColumn>   
                </vivo:tbRow>
            </logic:iterate>
            </vivo:tbRows>
        </vivo:tbTable>
        <div id="divLegendas">
            <table style="background-color:#ededed;border:1px solid #adadad;" width="780" align="left">
                <tr>
                    <td><b>Legendas:</b></td>
                    <td><img align="middle" src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" border="0">&nbsp;Editar Notas</td>
                    <td><img align="middle" src="/FrontOfficeWeb/resources/images/bt_icon_propried.gif" border="0">&nbsp;Editar Parâmetros</td>
                </tr>
            </table>
        </div>
        <div id="divProcessos">
            <iframe id="fraAbas" name="fraAbas" src="" width="0" height="0" frameborder="0" scrolling="no"></iframe>
        </div>
        <form action="lerNotasURADetalhe.do" method="POST">
            <input type="hidden" name="idAtendimentoNota">
        </form>
</acesso:controlHiddenItem>        
    </body>
<script>
f=document.forms[0];

function editar(idAtendimentoNota) {
    parent.divIncluirAlterar.style.display="block";
    f.target="ifrmIncluirAlterar";
    f.idAtendimentoNota.value=idAtendimentoNota;
    f.action="lerNotasURADetalhe.do";
    f.submit();
}

function detalhesNotaURA(idAtendimentoNota) {
    parent.divIncluirAlterar.style.display="block";
    f.target="ifrmIncluirAlterar";
    f.idAtendimentoNota.value=idAtendimentoNota;
    f.action="lerNotasURADetalhe.do";
    f.submit();
}

function fechar() {
    parent.pesquisar();
}
parent.screen_unblock();

</script>
</html>