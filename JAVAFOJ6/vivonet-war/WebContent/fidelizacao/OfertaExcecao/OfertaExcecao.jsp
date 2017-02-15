<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>
<bean:define id="arrayOferta"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="arrayOferta"/>

<netui-template:template templatePage="/resources/jsp/templateRetencao.jsp">
    <netui-template:section name="headerSection">
        <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js" ></script>
        <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
        <script language="javascript">
            function abreDetalhe(obj){
                valor = document.all[obj.sourceIndex + 1].value;
                document.getElementById('dvDetalhe').style.display = '';
                document.forms[0].target = "ifrmDetalhe";
                document.forms[0].action = "showDetalheOferta.do?acao=ok&id="+valor;
                document.forms[0].submit();
            }

            function salvar(obj){
                //alert(document.forms[0].elements['{pageFlow.arrayOferta[0].idOfertaAceita}'].value);
                valor = document.forms[0].elements['{pageFlow.arrayOferta[0].idOfertaAceita}'].value;
                document.forms[0].action ="OfertaExcecao.do?acao=ok&id="+valor;
                document.forms[0].submit();
            }

            function cerrar(){
                document.forms[0].target = '';
                document.forms[0].method = "POST";
                document.forms[0].action = "OfertaExcecao";
                document.getElementById('dvDetalhe').style.display = 'none';
            }
        </script>
        <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
            <!--
                top.frameApp.oculta_div();
            -->
        </SCRIPT>
    </netui-template:section>
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="fid_oe_verpagina">
        <form action="OfertaExcecao.do" method="post">
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="10"></div>
        <center>
        <vivo:tbTable selectable="true" layoutWidth="750" layoutHeight="330" tableWidth="750" styleId="tableTitle" sortable="true">
            <vivo:tbHeader>
                <vivo:tbHeaderColumn align="left" width="10%" type="string">Login</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="left" width="15%" type="date">Data/Hora</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="left" width="10%" type="string">Linha</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="left" width="20%" type="string">Intenção</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="left" width="15%" type="string">Destino</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="left" width="20%" type="string">Oferta Aceita</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="5%" type="string">Vistados</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="10%" type=""></vivo:tbHeaderColumn>
            </vivo:tbHeader>

            <vivo:tbRows>
                <netui-data:repeater dataSource="{pageContext.arrayOferta}">
                    <netui-data:repeaterHeader></netui-data:repeaterHeader>
                    <netui-data:repeaterItem>
                        <vivo:tbRow key="linha1">
                        <vivo:tbRowColumn><netui:label value="{container.item.login}"/> </vivo:tbRowColumn>
                        <vivo:tbRowColumn><netui:label value="{container.item.dataHora}"/> </vivo:tbRowColumn>
                        <vivo:tbRowColumn><netui:label value="{container.item.linha}"/> </vivo:tbRowColumn>
                        <vivo:tbRowColumn><netui:label value="{container.item.intencao}"/> </vivo:tbRowColumn>
                        <vivo:tbRowColumn><netui:label value="{container.item.destino}"/> </vivo:tbRowColumn>
                        <vivo:tbRowColumn><netui:label value="{container.item.nmOfertaAceita}"/> </vivo:tbRowColumn>
                        <vivo:tbRowColumn>
                            <netui:checkBoxGroup dataSource="{actionForm.ofertasVistadas}"  optionsDataSource="{container.item.idCaracOfertaAceita}" labelStyle="border:none;visibility:hidden;" styleClass="radio"/>
                        </vivo:tbRowColumn>
                        <vivo:tbRowColumn>
                            <img src="/FrontOfficeWeb/resources/images/lupa_bg_ffffff.gif" border="0" onClick="abreDetalhe(this);" />
                            <netui:hidden dataSource="{container.item.idOfertaAceita}" tagId="idOfertaAceita"/>
                         </vivo:tbRowColumn>
                        </vivo:tbRow>
                    </netui-data:repeaterItem>
                    <netui-data:repeaterFooter></netui-data:repeaterFooter>
                </netui-data:repeater>
            </vivo:tbRows>
    </vivo:tbTable>
    </center>

    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="3"></div>

    <table align="center" width="760" style="border:1px solid #adadad;background-color:#ffffff;" height="25">
        <tr>
            <td width="100"><b>&nbsp;&nbsp;&nbsp;Legendas:</b></td>
            <td width="660"><img align="middle" src="/FrontOfficeWeb/resources/images/lupa_bg_ffffff.gif"> Detalhamento da Oferta</td>
        </tr>
    </table>

    <table width="760" align="center">
        <tr>
            <td align="right">
            <acesso:controlHiddenItem nomeIdentificador="fid_oe_gravar">
            <img  id="btRightSimp" onclick="salvar(this); return false;" src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_gravar_over.gif'" style="border:none;cursor:hand;"/>

            </acesso:controlHiddenItem>
            </td>
        </tr>
    </table>

    </form>

    <form id="frmSelecao" name="frmSelecao" method="post">
        <input type="hidden" name="CODIGO" value="">
    </form>

    <vivo:quadroFlutuante id="dvDetalhe" onclick="cerrar();" idIframe="ifrmDetalhe" height="350" width="700"  spacesTop="20" spacesLeft="10" display="none" url="<%=request.getContextPath()%>" label="" scroll="auto"/>

    <script>
        document.body.style.backgroundColor = '#ededed';
    </script>

</acesso:controlHiddenItem>
  </netui-template:section>
</netui-template:template>

