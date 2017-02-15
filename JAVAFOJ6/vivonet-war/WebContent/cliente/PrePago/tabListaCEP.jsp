<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">
        <script type="text/javascript" language="javascript" src="/FrontOfficeWeb/resources/scripts/prototype.js"></script>
        <script language="JavaScript" type="text/JavaScript">
        <!--
            function swapImage(control, image){
                control.src = image;
            }

            function selectEndereco(args){
                
            }
        -->
        </script>
    </head>
    <body style="margin:5px 5px 5px 5px;">
        <vivo:tbTable selectable="true" layoutWidth="730" layoutHeight="135" tableWidth="730" styleId="tableTitle" sortable="true">
            <vivo:tbHeader>
                <vivo:tbHeaderColumn align="center" width="5%"  type="string"></vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="10%" type="string">CEP</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="25%" type="string">Municipio</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="25%" type="string">Logradouro</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="10%" type="string">Lado</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="20%" type="string">Bairro</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="5%"  type="string">UF</vivo:tbHeaderColumn>
            </vivo:tbHeader>
            <vivo:tbRows>
                <vivo:tbRow key="Linha">
                    <vivo:tbRowColumn><input type="radio" name="indCEP" value="" onclick="" style="border:none;"></vivo:tbRowColumn>
                    <vivo:tbRowColumn>08240-680</vivo:tbRowColumn>
                    <vivo:tbRowColumn>São Paulo</vivo:tbRowColumn>
                    <vivo:tbRowColumn>RUA CARUATAI</vivo:tbRowColumn>
                    <vivo:tbRowColumn>Par</vivo:tbRowColumn>
                    <vivo:tbRowColumn>JD S SEBASTIAO</vivo:tbRowColumn>
                    <vivo:tbRowColumn>SP</vivo:tbRowColumn>
                </vivo:tbRow>
            </vivo:tbRows>
        </vivo:tbTable>
        <div align="right" style="margin-top:10px;">
            <img src="/FrontOfficeWeb/resources/images/bt_selecionar_nrml.gif" style="border:none;cursor:hand;" onclick="recuperarEndereco();return false;" onMouseOut="swapImage(this,'/FrontOfficeWeb/resources/images/bt_selecionar_nrml.gif')" onMouseOver="swapImage(this,'/FrontOfficeWeb/resources/images/bt_selecionar_over.gif')">
        </div>
    </body>
</html>