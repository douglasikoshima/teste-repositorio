<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
   
<acesso:controlInitEnv/>

        <script language="javascript">
            var itemSel = "";
            
            function update( poRow ) {
                //alert('Telefone selecionado: ' + poRow.cells(1).childNodes.item(0).value);
                itemSel = poRow.cells(1).childNodes.item(0).value;
            }
            
            function updateItem( valor ) {
                itemSel = valor;
            }
            
            function retornar() {
                window.opener.formContato.elements[4].value = itemSel;
                window.close();
            }
            //Fim animação
            if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
        </script>
<html>
    <head>
        <title>Customer Profile</title>
        <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    </head>
    <body>
    <acesso:controlHiddenItem nomeIdentificador="wor_cpl_verpagina">
    <center>
        <vivo:tbTable selectable="true" onRowClick="update( this );" layoutWidth="150" layoutHeight="100" tableWidth="150" styleId="tableTitle2" sortable="true">
            <vivo:tbHeader>
                <vivo:tbHeaderColumn align="center" width="10%" type="string">#</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="90%" type="string">Linha</vivo:tbHeaderColumn>
            </vivo:tbHeader>

            <vivo:tbRows>
                <vivo:tbRow key="linha1" zebrar="S">
                    <vivo:tbRowColumn>1</vivo:tbRowColumn>
                    <vivo:tbRowColumn><netui:textBox dataSource="{}" defaultValue="(11) 3145-6900" onChange="updateItem(this.value);"></netui:textBox></vivo:tbRowColumn>
                </vivo:tbRow>
                <vivo:tbRow key="linha2" zebrar="S">
                    <vivo:tbRowColumn>2</vivo:tbRowColumn>
                    <vivo:tbRowColumn><netui:textBox dataSource="{}" defaultValue="(11) 6666-5555" onChange="updateItem(this.value);"></netui:textBox></vivo:tbRowColumn>
                </vivo:tbRow>
                <vivo:tbRow key="linha3" zebrar="S">
                    <vivo:tbRowColumn>3</vivo:tbRowColumn>
                    <vivo:tbRowColumn><netui:textBox dataSource="{}" defaultValue="(11) 7777-6666" onChange="updateItem(this.value);"></netui:textBox></vivo:tbRowColumn>
                </vivo:tbRow>
                <vivo:tbRow key="linha4" zebrar="S">
                    <vivo:tbRowColumn>4</vivo:tbRowColumn>
                    <vivo:tbRowColumn><netui:textBox dataSource="{}" defaultValue="(11) 2222-3333" onChange="updateItem(this.value);"></netui:textBox></vivo:tbRowColumn>
                </vivo:tbRow>
            </vivo:tbRows>
        </vivo:tbTable>
        <br>
        <table border="0" align="center">
            <tr>
                <td>
                <acesso:controlHiddenItem nomeIdentificador="wor_cpl_salvar">
                    <vivo:botao id="btSalvar" width="60px" height="10px" value="Salvar" styleClass="btTemplate" onclick="alert('Alterações salvas!');"/>
                </acesso:controlHiddenItem>
                </td>
                <td>
                    <vivo:botao id="btRetornar" width="60px" height="10px" value="Retornar" styleClass="btTemplate" onclick="retornar();"/>
                </td>
            </tr>
        </table>
    </center>
   
    </acesso:controlHiddenItem>
    </body>
</html>

