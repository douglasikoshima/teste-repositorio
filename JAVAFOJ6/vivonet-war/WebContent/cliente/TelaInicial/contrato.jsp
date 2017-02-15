<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

   

<acesso:controlInitEnv/>
<html>
    <head>
        <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    </head>
    <body rightmargin="0" leftmargin="0" topmargin="0">
    <acesso:controlHiddenItem nomeIdentificador="cli_con_verpagina">
          <form id="fFaturaAjustes" action="salvar.do" method="post" name="lupaLinhaForm"> 
            <table width="500" border="0" cellpadding="0" bgcolor="white"cellspacing="1">
                <tr>
                    <td>            
                        <vivo:tbTable selectable="true" onRowClick="" layoutWidth="720" layoutHeight="100" tableWidth="735" styleId="tableTitle" sortable="true">
                            <vivo:tbHeader>
                                <vivo:tbHeaderColumn align="center" width="100" type="string">Tipo</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="100" type="string">Data Término</vivo:tbHeaderColumn>                                
                            </vivo:tbHeader>
                            <vivo:tbRows>
                                <vivo:tbRow key="linha1">
                                    <vivo:tbRowColumn>Coluna 1.1</vivo:tbRowColumn>
                                     <vivo:tbRowColumn>Coluna 1.1</vivo:tbRowColumn>                                                                                                           
                                </vivo:tbRow>
                                <vivo:tbRow key="linha1">
                                    <vivo:tbRowColumn>Coluna 1.2</vivo:tbRowColumn> 
                                     <vivo:tbRowColumn>Coluna 1.1</vivo:tbRowColumn>                                                                                                    
                                </vivo:tbRow>
                                <vivo:tbRow key="linha1">
                                    <vivo:tbRowColumn>Coluna 1.3</vivo:tbRowColumn> 
                                     <vivo:tbRowColumn>Coluna 1.1</vivo:tbRowColumn>                                                                                                           
                                </vivo:tbRow>
                                <vivo:tbRow key="linha1">
                                    <vivo:tbRowColumn>Coluna 1.4</vivo:tbRowColumn> 
                                    <vivo:tbRowColumn>Coluna 1.1</vivo:tbRowColumn>                                                                                                  
                                </vivo:tbRow>                                                                                     
                            </vivo:tbRows>
                        </vivo:tbTable>
                    </td>     
                </tr>
            </table>
        </form>
           
    </acesso:controlHiddenItem>
    </body>
</html>
