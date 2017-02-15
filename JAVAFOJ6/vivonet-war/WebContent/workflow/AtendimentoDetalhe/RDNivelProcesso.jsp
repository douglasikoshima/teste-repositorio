<%@page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="workflow.AtendimentoDetalhe.AtendimentoDetalheController.RWFNivelProcesoForm"%>
<%@ page import="workflow.AtendimentoFila.AtendimentoFilaController.AtendimentoFilaForm"%>
<%@ page import="br.com.vivo.fo.workflow.vo.NivelVODocument.NivelVO"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%-- TODO: Poner control de acceso
<acesso:controlInitEnv/>
--%>
<%-- TODO: Poner control de acceso
    <acesso:controlHiddenItem nomeIdentificador="wor_rdniv_verpagina">
--%>
<bean:define id="nproc" scope="request" name="rWFNivelProcesoForm"/>
<script type="text/javascript" src="../../resources/scripts/frameweb.js"></script>
<table width="750" cellpadding="0" cellspacing="0" border="0" align="center">
    <tr><td height="5"></td></tr>
    <tr>
        <td valign="top">
            <vivo:tbTable selectable="false" resize ="false" layoutWidth="732" layoutHeight="65" tableWidth="748" styleId="tableTitleRDNivel" sortable="false">
                <vivo:tbHeader>
                    <logic:iterate id="faseVO" name="nproc" property="faseVO" indexId="idxFase">
                        <logic:equal name="faseVO" property="idFase" value="2">
                        <vivo:tbHeaderColumn align="center" width="50%" type="string"><bean:write name="faseVO" property="dsFase"/></vivo:tbHeaderColumn>
                        </logic:equal>
                        <logic:notEqual name="faseVO" property="idFase" value="2">
                        <vivo:tbHeaderColumn align="center" width="25%" type="string"><bean:write name="faseVO" property="dsFase"/></vivo:tbHeaderColumn>
                        </logic:notEqual>
                    </logic:iterate>
                </vivo:tbHeader>
                <vivo:tbRows scroll="false">
                    <vivo:tbRow key="linha0">
                        <logic:iterate id="faseVO" name="nproc" property="faseVO" indexId="idxFase">
                            <logic:equal name="faseVO" property="idFase" value="2">
                                <TD valign="top" align="center" width="50%">
                                    <TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
                                        <TR>
                                            <TD class="holderTabela" align=middle width="10%" height=15>Nível</TD>
                                            <TD class="holderTabela" align=middle width="60%" height=15>Grupo</TD>
                                            <TD class="holderTabela" align=middle width="30%" height=15>Ação</TD>
                            </logic:equal>
                            <logic:notEqual name="faseVO" property="idFase" value="2">
                                <TD valign="top" align="center" width="25%">
                                    <TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
                                        <TR>
                                            <TD width="100%" class="holderTabela" align=middle height=15>Grupo</TD>
                            </logic:notEqual>
                            </TR>
                            <%int iteracionesNivel = 0;%>
                            <logic:iterate id="nivelVO" name="faseVO" property="nivelVOArray" indexId="idxNivel">
                                <%
                                iteracionesNivel++;
                                String rowClass = ConstantesCRM.SVAZIO;
                                if (((NivelVO)nivelVO).getStatus() == 1) {
                                    rowClass = "rowTabelaAlertaAlto";
                                }
                                %>
                                <TR class="<%=rowClass%>">
                                  <logic:equal name="faseVO" property="idFase" value="2">
                                     <TD width="10%" align=center><%=idxNivel%></TD>
                                     <TD width="60%" align=center><bean:write name="nivelVO" property="nmGrupo"/></TD>
                                     <logic:empty name="nivelVO" property="dsAtividade">
                                     <TD width="30%" align=center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</TD>
                                     </logic:empty>
                                     <logic:notEmpty name="nivelVO" property="dsAtividade">
                                     <TD width="30%" align=center><bean:write name="nivelVO" property="dsAtividade"/></TD>
                                     </logic:notEmpty>
                                  </logic:equal>
                                  <logic:notEqual name="faseVO" property="idFase" value="2">
                                     <TD align=center width="100%"><bean:write name="nivelVO" property="nmGrupo"/></TD>
                                  </logic:notEqual>
                                </TR>
                            </logic:iterate>
                            <%if (iteracionesNivel == 0){%>
                               <TR><TD>&nbsp;</TD></TR>
                            <%}%>
                            </TABLE>
                        </TD>
                    </logic:iterate>
                </vivo:tbRow>
            </vivo:tbRows>
        </vivo:tbTable>        
        <a href="abaVoltar.do" id="abaArvoreAtendimentoVoltar"></a>
    </td>
</tr>
</table>
<%-- TODO: Poner control de acceso
</acesso:controlHiddenItem>
--%>
<script>
    parent.showIfr("NProc");
    abaArvoreAtendimentoVoltar.click();
</script>
