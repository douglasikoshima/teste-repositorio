<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>

<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data" %>
<%@ taglib uri="netui-tags-html.tld" prefix="netui" %>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso" %>

<acesso:controlInitEnv/>

<bean:define id="piDocumento" name="piDocumento" scope="request"/>

<html>
    <head>
        <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        <script FOR=window EVENT=onload LANGUAGE="JScript">
            <!--
                parent.parent.oculta_div();
            -->
        </script>
    </head>
    <body style="background-color:#E3ECF4;">
    <acesso:controlHiddenItem nomeIdentificador="cli_adoc_verpagina">
        <form action="loadDocumento.do" method="post">
        <table style="background-color:#E3ECF4;" width="100%" border="0" cellpadding="0" cellspacing="1">
            <tr>
                <td>
                    <vivo:tbTable selectable="true" onRowClick="" layoutWidth="751" layoutHeight="208" tableWidth="751" styleId="tableTitle" sortable="true">
                        <vivo:tbHeader>
                            <vivo:tbHeaderColumn align="center" width="5%" type="number">Seq.</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="center" width="25%" type="string">Tipo</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="center" width="20%" type="string">Documento</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="center" width="15%" type="string">&Oacute;rgão Expedidor</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="center" width="5%" type="string">UF</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="center" width="15%" type="string">Emissão</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="center" width="15%" type="string">Pa&iacute;s</vivo:tbHeaderColumn>
                        </vivo:tbHeader>
                        <vivo:tbRows>
                            <logic:iterate id="item" name="piDocumento" indexId="c">
                                <vivo:tbRow key="linha1">
                                    <vivo:tbRowColumn>
                                        <bean:write name="item" property="nrPrioridade" />
                                    </vivo:tbRowColumn>
                                    <vivo:tbRowColumn>
                                        <bean:write name="item" property="tipoDocumentoVO.dsTipoDocumento" />
                                    </vivo:tbRowColumn>
                                    <vivo:tbRowColumn>
                                        <bean:write name="item" property="nrDocumento" />
                                    </vivo:tbRowColumn>
                                    <vivo:tbRowColumn>
                                        <bean:write name="item" property="dsOrgaoExpedidor" />
                                    </vivo:tbRowColumn>
                                    <vivo:tbRowColumn>
                                        <bean:write name="item" property="sgUF" />
                                    </vivo:tbRowColumn>
                                    <vivo:tbRowColumn>
                                        <bean:write name="item" property="dtEmissao" />
                                    </vivo:tbRowColumn>
                                    <vivo:tbRowColumn>
                                        <bean:write name="item" property="paisVO.nmPais" />
                                    </vivo:tbRowColumn>
                                </vivo:tbRow>
                            </logic:iterate>
                        </vivo:tbRows>
                    </vivo:tbTable>
                </td>
            </tr>
        </table>
        </form>
    </acesso:controlHiddenItem>
    </body>
</html>