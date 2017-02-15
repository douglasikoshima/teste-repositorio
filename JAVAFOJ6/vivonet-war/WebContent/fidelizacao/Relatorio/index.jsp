<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>

<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
<link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/calendar.js"></script>
<script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js" ></script>
<script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js" ></script>
<script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/xtooltip.js" ></script>

<bean:define id="FormDadosPesquisa" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formDadosPesquisa"/>
<bean:define id="ClassificacaoVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formDadosPesquisa.classificacaoVO"/>
<bean:define id="OperadoraVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formDadosPesquisa.operadoraVO"/>
<bean:define id="FidelizacaoTipoClienteVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formDadosPesquisa.fidelizacaoTipoClienteVO"/>
<bean:define id="FidelizacaoGrupoVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formDadosPesquisa.fidelizacaoGrupoVO"/>
<bean:define id="OfertaFidelizacaoVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formDadosPesquisa.ofertaFidelizacaoVO"/>

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute value="Fidelizacao" name="modulo"></netui-template:setAttribute>
    <netui-template:section name="headerSection">
    <script language="Javascript">

    function relatorioOnClick(obj)
    {

        if(obj.value == 11 || obj.value == 12)
        {
            //relPlanos(obj, true);
        }
        else
        {
            //relPlanos(obj, false);
        }
    }

    function operadoraOnChange(obj)
    {
        if( ( findChecked(document.formDadosPesquisa.elements["idRelatorio"]).value == 11
          ||  findChecked(document.formDadosPesquisa.elements["idRelatorio"]).value == 12 )
          && (obj.value != "") )
        {
            document.forms['formDadosPesquisa'].target = "iframe";
            document.forms['formDadosPesquisa'].action = "/FrontOfficeWeb/fidelizacao/Relatorio/carregarPlanos.do";
            document.forms['formDadosPesquisa'].submit();
            top.frameApp.mostrar_div();
        }
    }

    function activaCostoPonto()
    {
        document.forms['formDadosPesquisa'].elements['{actionForm.custoPonto}'].disabled = false;
    }

    function relPlanos(obj, exibir)
    {
            document.getElementById("lyrPlanos").style.display = (exibir) ? "block" : "none";
    }

    function inactivaCP()
    {
        document.forms['formDadosPesquisa'].elements['{actionForm.custoPonto}'].disabled = true;
    }

    function IncluirStatus()
    {
        document.getElementById("idmostrarelatorio").style.display = '';
    }


    function cerrarRelatorio(){
        document.forms[0].target = "";
        document.forms[0].method = "POST";
        document.forms[0].action = "";
        document.getElementById("dvRelatorio").style.display = 'none';

    }


    function mostrarRelatorio(obj) {

        if( (document.formDadosPesquisa.elements["{actionForm.dataInicio}"].value == "") || (document.formDadosPesquisa.elements["{actionForm.dataFim}"].value == "") )
        {
            alert('Os campos período início e período fim são obrigatórios!');
            document.formDadosPesquisa.elements["{actionForm.dataInicio}"].focus();

        }
        else if(findChecked(document.formDadosPesquisa.elements["idRelatorio"]).value == 11 && (document.formDadosPesquisa.elements["operadora"].selectedIndex == -1))
        {
            alert('O campo Regional é obrigatório!');
            document.formDadosPesquisa.elements["operadora"].focus();

        }

        else if(findChecked(document.formDadosPesquisa.elements["idRelatorio"]).value == 12 && (document.formDadosPesquisa.elements["operadora"].selectedIndex == -1))
        {
            alert('O campo Regional é obrigatório!');
            document.formDadosPesquisa.elements["operadora"].focus();

        }

        else if( findChecked(document.formDadosPesquisa.elements["idRelatorio"]).value == 5 &&  (document.formDadosPesquisa.elements["oferta"].value == ""))
        {
            alert('O campo Tipo de Oferta é obrigatório!\n Verifique se realmente existe oferta(s) selecionada(s)!') ;
            document.formDadosPesquisa.elements["oferta"].focus();
        }

        else
        {
            document.forms['formDadosPesquisa'].target = "";
            document.forms['formDadosPesquisa'].action = "/FrontOfficeWeb/fidelizacao/Relatorio/gerarRelatorio.do";
            document.forms['formDadosPesquisa'].submit();
            top.frameApp.mostrar_div();
        }
    }


    function findChecked(objArr)
    {
        if(objArr.length)
        {
            for(i = 0; i < objArr.length; i++)
            {
                if(objArr[i].checked)
                {
                    return objArr[i];
                }
            }
        }
        else
        {
            return null;
        }
    }

    function checkIdRelatorio(){
        <logic:notEmpty name="FormDadosPesquisa" property="idRelatorio">
        for(i=0; i < document.formDadosPesquisa.elements["idRelatorio"].length; i++){
            if( document.formDadosPesquisa.elements["idRelatorio"][i].value == <bean:write name="FormDadosPesquisa" property="idRelatorio"/>){
                document.formDadosPesquisa.elements["idRelatorio"][i].checked=true;
                break;
            }
        }
        </logic:notEmpty>
    }

    function limpar(){
        document.formDadosPesquisa.elements["{actionForm.dataInicio}"].value = "";
        document.formDadosPesquisa.elements["{actionForm.dataFim}"].value = "";
        document.formDadosPesquisa.elements["classificacao"].value ="";
        document.formDadosPesquisa.elements["{actionForm.operador}"].value = "";
        document.formDadosPesquisa.elements["operadora"].value ="";
        document.formDadosPesquisa.elements["oferta"].value ="";
        document.formDadosPesquisa.elements["tipoCliente"].value ="";
        document.formDadosPesquisa.elements["{actionForm.custoPonto}"].value = "";
        document.formDadosPesquisa.elements["grupo"].value ="";

        if( findChecked(document.formDadosPesquisa.elements["idRelatorio"]).value == 11 || findChecked(document.formDadosPesquisa.elements["idRelatorio"]).value == 12 ){
            document.formDadosPesquisa.elements["plano"].value ="";
        }

        if(document.getElementById("msgErro")){
            document.getElementById("msgErro").innerHTML = "";
        }


    }
    </script> </netui-template:section>
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="fid_rel_ind_verpagina">
    <SCRIPT FOR=window EVENT=onload>
        top.frameApp.oculta_div();
        var a = '<%=request.getAttribute("errorFecha")%>';

        if( a == "Data inicial não deve ser maior que a data final!" || a ==  "Data final deve ser no máximo 31 dias maior que data inicial!" )
        {
            alert(  '<%=request.getAttribute("errorFecha")%>'  );

        }
    </script> <jsp:include page="/resources/menus/MenuPrincipal.jsp"/>
    <div><img src="<%=request.getContextPath()%>/resources/images/transp.gif" border="0" width="1" height="5"></div>
    <vivo:sessao id="idFrelatorio" height="470" width="790" label="Fidelização > Relatórios" operacoes="" scroll="N">
    <form action="gerarRelatorio.do" method="post">
        <table cellpadding="0" cellspacing="0" height="100%" border="0">
            <tr>
                <td valign="top" height="10%">
                <div><img src="<%=request.getContextPath()%>/resources/images/transp.gif" border="0" width="1" height="3"></div>
                <table width="100%" class="tbl_bggray" align="center">
                    <tr>
                        <td align="center">
                        <table align="center" width="100%" border="0" >
                            <tr >
                                <td width="100" valign="top" rowspan="2"><b>Tipos
                                de Relatório</b></td>
                                <td >
                                <input class="radio" type="radio" name="idRelatorio" value="1" onchange="inactivaCP();" onclick="relatorioOnClick(this);" checked>
                                <netui:label value="Retenção" />
                                </td>
                                <td >
                                <input class="radio" type="radio" name="idRelatorio" value="2" onchange="inactivaCP();" onclick="relatorioOnClick(this);" >
                                <netui:label value="Movimentações Diárias"/>
                                </td>
                                <td >
                                <input class="radio" type="radio" name="idRelatorio" value="5" onchange="inactivaCP();" onclick="relatorioOnClick(this);">
                                <netui:label value="Retenção por Oferta"/>
                                </td>
                            </tr>
                            <tr>
                                <td >
                                <input class="radio" type="radio" name="idRelatorio" value="8" onchange="inactivaCP();" onclick="relatorioOnClick(this);">
                                <netui:label value="Operador"/>
                                </td>
                                <td>
                                <input class="radio" type="radio" name="idRelatorio" value="9" onchange="activaCostoPonto();" onclick="relatorioOnClick(this);">
                                <netui:label value="Ligações Retenção"/>
                                </td>
                                <td>
                                <input class="radio" type="radio" name="idRelatorio" value="10" onchange="activaCostoPonto();" onclick="relatorioOnClick(this);">
                                <netui:label value="Retenção Com Distinção"/>
                                </td>
                            </tr>
                            <tr >
                                <td valign="top" rowspan="2"></td>
                                <td >
                                <input class="radio" type="radio" name="idRelatorio" value="3" onchange="inactivaCP();" onclick="relatorioOnClick(this);">
                                <netui:label value="Ofertas"/>
                                </td>
                                <td >
                                <input class="radio" type="radio" name="idRelatorio" value="4" onchange="inactivaCP();" onclick="relatorioOnClick(this);">
                                <netui:label value="Resultado Destino"/>
                                </td>
                                <td >
                                <input class="radio" type="radio" name="idRelatorio" value="11" onclick="relatorioOnClick(this);" >
                                <netui:label value="Retenção por planos"/>
                                </td>
                            </tr>
                            <tr>
                                <td >
                                <input class="radio" type="radio" name="idRelatorio" value="6" onchange="inactivaCP();" onclick="relatorioOnClick(this);">
                                <netui:label value="Resultado Destino com Distinção"/>
                                </td>
                                <td >
                                <input class="radio" type="radio" name="idRelatorio" value="12" onclick="relatorioOnClick(this);" >
                                <netui:label value="Retenção por Planos Distinção"/>
                                </td>
                                <td colspan="2">
                                <input class="radio" type="radio" name="idRelatorio" value="15" onchange="inactivaCP();" onclick="relatorioOnClick(this);">
                                <netui:label value="Notes Loja"/>
                                </td>
                            </tr>
                            <tr>
                                <td ></td>
                                <td >
                                <input class="radio" type="radio" name="idRelatorio" value="7" onchange="inactivaCP();" onclick="relatorioOnClick(this);">
                                <netui:label value="Controle de Credito"/>
                                </td>
                                <td>
                                <input class="radio" type="radio" name="idRelatorio" value="13" onclick="relatorioOnClick(this);" >
                                <netui:label value="Oferta Total Mensal"/>
                                </td>
                                <td>
                                <input class="radio" type="radio" name="idRelatorio" value="16" onclick="relatorioOnClick(this);" >
                                <netui:label value="Análise de Adimplência"/>
                                </td>
                            </tr>
                        </table>
                        </td>
                    </tr>
                </table>
                <div><img src="<%=request.getContextPath()%>/resources/images/transp.gif" border="0" width="1" height="5"></div>
                <vivo:quadro id="qdMain" height="230" width="780" scroll="no" label="Filtros">
                <table align="center" width="95%" cellpadding="0" cellspacing="0" border="0">
                    <tr>
                        <td height="27">Período de:</td>
                        <td >
                        <table cellpadding="0" cellspacing="0" border="0">
                            <tr>
                                <td width="127">
                                <netui:textBox dataSource="dataInicio" readonly="true" styleClass="textfield" size="10"/>
                                <img src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:hand;" title="Calendario" onclick="return showCalendar('{actionForm.dataInicio}', '%d/%m/%Y');"></td>
                                <td>até:</td>
                                <td>
                                <netui:textBox dataSource="dataFim" readonly="true" styleClass="textfield" size="10"/>
                                <img src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:hand;" title="Calendario" onclick="return showCalendar('{actionForm.dataFim}', '%d/%m/%Y');"></td>
                            </tr>
                        </table>
                        </td>
                    </tr>
                    <tr>
                        <td height="27" width="90">Segmentação:</td>
                        <td width="280" >
                        <html:select name="FormDadosPesquisa" property="classificacao" style="width:240" styleClass="SELECT" size="1">
                        <option value=""></option>
                        <html:options collection="ClassificacaoVO" property="id" labelProperty="descricao" /> </html:select>
                        </td>
                        <td width="120">Login do Usuário:</td>
                        <td>
                        <netui:textBox dataSource="operador" size="80" maxlength="80" styleClass="textfield" style="width:238"/>
                        </td>
                    </tr>
                    <tr>
                        <td height="27" rowspan="3" valign="top">Regional:</td>
                        <td rowspan="3">
                        <html:select name="FormDadosPesquisa" property="operadora" style="width:240;height:80" styleClass="SELECT" size="1" onchange="//operadoraOnChange(this);" multiple="true">
                        <html:options collection="OperadoraVO" property="id" labelProperty="descricao" /></html:select>
                        </td>
                        <td>Tipo de Oferta:</td>
                        <td style="padding-left:2px">
                        <html:select name="FormDadosPesquisa" property="oferta" style="width:240" styleClass="SELECT" size="1" onmouseover="ativarToolTip(this);">
                        <option value=""></option>
                        <html:options collection="OfertaFidelizacaoVO" property="id" labelProperty="descricao" /> </html:select>
                        </td>
                    </tr>
                    <tr>
                        <td height="27" >Tipo Cliente:</td>
                        <td style="padding-left:2px" >
                        <html:select name="FormDadosPesquisa" title="tipoCliente" property="tipoCliente" style="width:240" styleClass="SELECT" size="1">
                        <option value=""></option>
                        <html:options collection="FidelizacaoTipoClienteVO" property="id" labelProperty="descricao" /> </html:select>
                        </td>
                        <netui:hidden dataSource="custoPonto"/>

                    </tr>
                    <tr>
                        <td valign="top">Grupo:</td>
                        <td valign="top"  style="padding-left:2px">
                            <html:select name="FormDadosPesquisa" title="grupo" property="grupo" style="width:240" styleClass="SELECT" size="1">
                                <option value=""></option>
                                <html:options collection="FidelizacaoGrupoVO" property="id" labelProperty="descricao" />
                            </html:select>
                        </td>
                    </tr>
                    <tr>
                        <td valign="top"></td>
                        <td valign="top"  style="padding-left:2px"></td>
                    </tr>
                    <tr id="lyrPlanos" style="display:none;">
                        <td height="27">Plano</td>
                        <td style="padding-left:2px" colspan="3">
                        <html:select name="FormDadosPesquisa" title="plano" property="plano" style="width:240" styleClass="SELECT" size="1">
                        <option value=""></option>
                        </html:select>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4" align="right">
                        <acesso:controlHiddenItem nomeIdentificador="fid_rel_ind_pesq">
                        	<img hspace="35"
                        		 vspace="5"
                        		 onmouseup="mostrarRelatorio(this)"
                        		 src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif"
                        		 class="button" />
                        </acesso:controlHiddenItem>
                        <img onClick="limpar();return false" vspace="5" src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif" style="border:none"/>
                        </td>
                    </tr>
                    <tr>
                    <logic:notEqual name="FormDadosPesquisa" property="msgErro" value="">
                        <td colspan="4" id="msgErro" class="tblEstatica_campoNome" align="center">
                         <netui:label value="A pesquisa não retornou resultado." id="msgErro"/><br>
                         <netui:label value="Motivos possíveis:" id="msgErro"/><br>
                         <netui:label value="- Não existem retenções para os filtros selecionados." id="msgErro"/><br>
                         <netui:label value="- Volume de dados excedido, favor refinar pesquisa ou diminuir o período informado." id="msgErro"/>
                        <td>
                    </logic:notEqual>

                    </tr>
                </table>


                </vivo:quadro>
                <div><img src="<%=request.getContextPath()%>/resources/images/transp.gif" border="0" width="1" height="5"></div>
                </td>
            </tr>
            <tr>
                <td>
                <table border="0" height="100%">
                    <tr>
                        <td valign="bottom">
                        	<img vspace="5"
                        		 onmouseup="window.location.href='/FrontOfficeWeb/begin.do';"
                        		 class="button"
                        		 src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" />
                        </td>
                    </tr>
                </table>
                </td>
            </tr>
        </table>
        <iframe scrolling="yes" style="display:none;" name="iframe" height="0px" width="0px"></iframe>
        <script>
            checkIdRelatorio();
        </script>
    </form>
    </vivo:sessao>
    <vivo:quadroFlutuante onclick="cerrarRelatorio();" id="idmostrarelatorio" idIframe="divframe_relatorios" width="790" height="570" spacesTop="5" spacesLeft="5" display="none" src="/FrontOfficeWeb/fidelizacao/Relatorio/nada.html" url="/FrontOfficeWeb" label="Relatório"/> </acesso:controlHiddenItem> </netui-template:section>
</netui-template:template>