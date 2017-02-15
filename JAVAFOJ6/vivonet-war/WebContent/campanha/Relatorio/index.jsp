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
<script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js" ></script>
<script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/formatPhoneNumber.js" ></script>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relEfetividadeActionForm" />
<bean:define id="aCampanha" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relEfetividadeActionForm.listaCampanha" />

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute value="Fidelizacao" name="modulo"></netui-template:setAttribute>
    <netui-template:section name="headerSection">
 <script language="Javascript">

function redrawTable(obj){

    //document.forms["relEfetividadeActionForm"].campanhaSelecionada.selectedIndex = 0;

    var percentual1 =  document.getElementById("percentual1");
    var percentual2 =  document.getElementById("percentual2");
    var gerenciamento1 =  document.getElementById("gerenciamento1");
    var gerenciamento2 =  document.getElementById("gerenciamento2");
    var gerenciamento3 =  document.getElementById("gerenciamento3");
    var gerenciamento4 =  document.getElementById("gerenciamento4");
    var efetividade =  document.getElementById("efetividade");
    var operador =  document.getElementById("operador");
    var respostas1 =  document.getElementById("respostas1");
    var respostas2 =  document.getElementById("respostas2");
    var respostas3 =  document.getElementById("respostas3");
    var respostas4 =  document.getElementById("respostas4");
    //var respostas5 =  document.getElementById("respostas5");
	var respostas6 =  document.getElementById("respostas6");
    var respostas7 =  document.getElementById("respostas7");
	var area1 =  document.getElementById("area1");
	var area2 =  document.getElementById("area2");
    var todos1 =  document.getElementById("todos1");
    var todos2 =  document.getElementById("todos2");
    var todos3 =  document.getElementById("todos3");
    var niver1 =  document.getElementById("niver1");
    var niver2 =  document.getElementById("niver2");
    var niver3 =  document.getElementById("niver3");
    var niver4 =  document.getElementById("niver4");
    var datas =  document.getElementById("datas");


    var type = obj.value;




   if(type == 1 || ('<%=request.getAttribute("idRelatorio")%>') =='1' && (type == undefined )){

        if(('<%=request.getAttribute("idRelatorio")%>') == '1' ){
            document.forms["relEfetividadeActionForm"].idRelatorio[0].checked = true;
        }
        percentual1.style.display = 'none';
        percentual2.style.display = 'none';
        gerenciamento1.style.display = '';
        gerenciamento2.style.display = '';
        gerenciamento3.style.display = '';
        gerenciamento4.style.display = '';
        efetividade.style.display = 'none';
        operador.style.display = 'none';
        respostas1.style.display =  'none';
        respostas2.style.display =  'none';
        respostas3.style.display =  'none';
        respostas4.style.display =  'none';
        //respostas5.style.display =  'none';
		respostas6.style.display =  'none';
		respostas7.style.display =  'none';
        area1.style.display = 'none';
        area2.style.display = 'none';
        niver1.style.display = 'none';
        niver2.style.display = 'none';
        niver3.style.display = 'none';
        niver4.style.display = 'none';
        todos1.style.display = '';
        todos2.style.display = '';
        todos3.style.display = '';
        datas.style.display = '';

    }
    else
    if(type == 2  || ('<%=request.getAttribute("idRelatorio")%>')=='2' && (type == undefined ) ){
        if(('<%=request.getAttribute("idRelatorio")%>')=='2'){
            document.forms["relEfetividadeActionForm"].idRelatorio[2].checked = true;

        }
        percentual1.style.display = 'none';
        percentual2.style.display = 'none';
        gerenciamento1.style.display = 'none';
        gerenciamento2.style.display = 'none';
        gerenciamento3.style.display = 'none';
        gerenciamento4.style.display = 'none';
        efetividade.style.display = '';
        operador.style.display = 'none';
        respostas1.style.display =  'none';
        respostas2.style.display =  'none';
        respostas3.style.display =  'none';
        respostas4.style.display =  'none';
        //respostas5.style.display =  'none';
		respostas6.style.display =  'none';
		respostas7.style.display =  'none';
		area1.style.display =  'none';
		area2.style.display =  'none';
        niver1.style.display = 'none';
        niver2.style.display = 'none';
        niver3.style.display = 'none';
        niver4.style.display = 'none';
        todos1.style.display = '';
        todos2.style.display = '';
        todos3.style.display = '';
        datas.style.display = '';

    }
    else
    if(type == 4  || ('<%=request.getAttribute("idRelatorio")%>')=='4' && (type == undefined ) ){
        if(('<%=request.getAttribute("idRelatorio")%>')=='4'){
            document.forms["relEfetividadeActionForm"].idRelatorio[1].checked = true;

        }
        percentual1.style.display = '';
        percentual2.style.display = '';
        gerenciamento1.style.display = 'none';
        gerenciamento2.style.display = 'none';
        gerenciamento3.style.display = 'none';
        gerenciamento4.style.display = 'none';
        efetividade.style.display = 'none';
        operador.style.display ='none';
        respostas1.style.display =  'none';
        respostas2.style.display =  'none';
        respostas3.style.display =  'none';
        respostas4.style.display =  'none';
        //respostas5.style.display =  'none';
		respostas6.style.display =  '';
		respostas7.style.display =  '';
        area1.style.display =  'none';
        area2.style.display =  'none';
        niver1.style.display = 'none';
        niver2.style.display = 'none';
        niver3.style.display = 'none';
        niver4.style.display = 'none';
        todos1.style.display = '';
        todos2.style.display = '';
        todos3.style.display = '';
        datas.style.display = '';

    }
    else
    if(type == 3  || ('<%=request.getAttribute("idRelatorio")%>')=='3' && (type == undefined ) ){
        if(('<%=request.getAttribute("idRelatorio")%>')=='3'){
            document.forms["relEfetividadeActionForm"].idRelatorio[3].checked = true;

        }

        percentual1.style.display = 'none';
        percentual2.style.display = 'none';
        gerenciamento1.style.display = '';
        gerenciamento2.style.display = '';
        gerenciamento3.style.display = '';
        gerenciamento4.style.display = '';
        efetividade.style.display = 'none';
        operador.style.display =  '';
        respostas1.style.display =  'none';
        respostas2.style.display =  'none';
        respostas3.style.display =  'none';
        respostas4.style.display =  'none';
        //respostas5.style.display =  'none';
		respostas6.style.display =  'none';
		respostas7.style.display =  'none';
        area1.style.display =  'none';
        area2.style.display =  'none';
        niver1.style.display = 'none';
        niver2.style.display = 'none';
        niver3.style.display = 'none';
        niver4.style.display = 'none';
        todos1.style.display = '';
        todos2.style.display = '';
        todos3.style.display = '';
        datas.style.display = '';

    }
    else
    if(type == 5  || ('<%=request.getAttribute("idRelatorio")%>')=='5' && (type == undefined ) ){
        if(('<%=request.getAttribute("idRelatorio")%>')=='5'){
            document.forms["relEfetividadeActionForm"].idRelatorio[4].checked = true;

        }

        percentual1.style.display = 'none';
        percentual2.style.display = 'none';
        gerenciamento1.style.display = 'none';
        gerenciamento2.style.display = 'none';
        gerenciamento3.style.display = 'none';
        gerenciamento4.style.display = 'none';
        efetividade.style.display = 'none';
        operador.style.display =  'none';
        respostas1.style.display =  '';
        respostas2.style.display =  '';
        respostas3.style.display =  '';
        respostas4.style.display =  '';
        //respostas5.style.display =  '';
		respostas6.style.display =  '';
		respostas7.style.display =  '';
        area1.style.display =  '';
        area2.style.display =  '';
        niver1.style.display = 'none';
        niver2.style.display = 'none';
        niver3.style.display = 'none';
        niver4.style.display = 'none';
        todos1.style.display = '';
        todos2.style.display = '';
        todos3.style.display = '';
        datas.style.display = '';

    }
    else
    if(type == 6  || ('<%=request.getAttribute("idRelatorio")%>')=='6' && (type == undefined ) ){
        if(('<%=request.getAttribute("idRelatorio")%>')=='6'){
            document.forms["relEfetividadeActionForm"].idRelatorio[5].checked = true;

        }


        percentual1.style.display = 'none';
        percentual2.style.display = 'none';
        gerenciamento1.style.display = '';
        gerenciamento2.style.display = '';
        gerenciamento3.style.display = 'none';
        gerenciamento4.style.display = 'none';
        efetividade.style.display = 'none';
        operador.style.display =  'none';
        respostas1.style.display =  'none';
        respostas2.style.display =  'none';
        respostas3.style.display =  'none';
        respostas4.style.display =  'none';
        //respostas5.style.display =  'none';
		respostas6.style.display =  'none';
		respostas7.style.display =  'none';
        area1.style.display =  'none';
        area2.style.display =  'none';
        niver1.style.display = '';
        niver2.style.display = '';
        niver3.style.display = '';
        niver4.style.display = '';
        todos1.style.display = 'none';
        todos2.style.display = 'none';
        todos3.style.display = 'none';
        datas.style.display = '';



    }
}

            function consultaIFrameSubCampanha()
            {
                if(document.forms["relEfetividadeActionForm"].campanhaSelecionada.value=="")
                {
                    alert('É obrigatório selecionar uma Campanha!');
                }
                else
                {
                    var idCampanha = document.forms["relEfetividadeActionForm"].campanhaSelecionada.options[document.forms["relEfetividadeActionForm"].campanhaSelecionada.selectedIndex].value;

                    limparItens();

                    //Monta o path seleção
                    document.forms["relEfetividadeActionForm"].target = "innerBrowser";
                    document.forms["relEfetividadeActionForm"].action = "obtemListaSubCampanha.do?acao=campanha&idCampanha="+idCampanha;
                    document.forms["relEfetividadeActionForm"].submit();

                    top.frameApp.mostrar_div();
                }
            }

            function consultaIFrameVersao()
            {

                if( ( document.forms["relEfetividadeActionForm"].campanhaSelecionada.value=="" ) || ( document.forms["relEfetividadeActionForm"].subcampanhaSelecionada.value=="" ) )
                {
                    alert('É obrigatório selecionar uma Campanha e  uma SubCampanha!');
                }
                else
                {

                    //var idCampanha		= document.forms["relEfetividadeActionForm"].campanhaSelecionada.options[document.forms["relEfetividadeActionForm"].campanhaSelecionada.selectedIndex].value;
                    //var idSubCampanha	= document.forms["relEfetividadeActionForm"].subcampanhaSelecionada.options[document.forms["relEfetividadeActionForm"].subcampanhaSelecionada.selectedIndex].value;

                    //Monta o path seleção
                    document.forms["relEfetividadeActionForm"].target = "innerBrowser";
                    document.forms["relEfetividadeActionForm"].action = "obtemListaVersao.do" ; //?acao=subcampanha&idCampanha="+idCampanha + "&idSubCampanha="+idSubCampanha +"$idRelatorio"+ ('<%=request.getAttribute("idRelatorio")%>');

                    document.forms["relEfetividadeActionForm"].submit();

                    top.frameApp.mostrar_div();
                }
            }

            function consultaIFrameCanal()
            {

                if( ( document.forms["relEfetividadeActionForm"].campanhaSelecionada.value=="" ) || ( document.forms["relEfetividadeActionForm"].subcampanhaSelecionada.value=="" ) || ( document.forms["relEfetividadeActionForm"].versaoSelecionada.value=="" ))
                {
                    alert('É obrigatório selecionar uma Campanha, SubCampanha e Versão!');
                }
                else
                {

                    //var idCampanha		= document.forms["relEfetividadeActionForm"].campanhaSelecionada.options[document.forms["relEfetividadeActionForm"].campanhaSelecionada.selectedIndex].value;
                    //var idSubCampanha	= document.forms["relEfetividadeActionForm"].subcampanhaSelecionada.options[document.forms["relEfetividadeActionForm"].subcampanhaSelecionada.selectedIndex].value;

                    //Monta o path seleção
                    document.forms["relEfetividadeActionForm"].target = "innerBrowser";
                    document.forms["relEfetividadeActionForm"].action = "obtemListaCanal.do"; //?acao=subcampanha&idCampanha="+idCampanha + "&idSubCampanha="+idSubCampanha +"$idRelatorio"+ ('<%=request.getAttribute("idRelatorio")%>');

                    document.forms["relEfetividadeActionForm"].submit();

                    top.frameApp.mostrar_div();
                }
            }


function mostrarRelatorio() {

	var objForm = document.forms["relEfetividadeActionForm"];
	var objRel  = objForm.idRelatorio;
	var dia  = new Date();
	var mes  = dia.getMonth() + 1;
	var hoje = (dia.getDate()+1).toString() + "/" + mes.toString() + "/" + dia.getYear().toString();

	if (objRel[0].checked || objRel[1].checked || objRel[2].checked || objRel[3].checked || objRel[4].checked) {

		if (objForm.campanhaSelecionada.value=="") {
			alert('Campo campanha é obrigatório!');
			objForm.campanhaSelecionada.focus();
		} else if(objForm.subcampanhaSelecionada.value=="") {
			alert('Campo Sub Campanha é obrigatório!');
			objForm.subcampanhaSelecionada.focus();
		}
		else if(objForm.versaoSelecionada.value=="")
		{
			alert('Campo Versão é obrigatório!');
			objForm.versaoSelecionada.focus();
		}
		else if( ( objForm.elements["dtInicio"].value == "" ) || ( objForm.elements["dtFim"].value == "" ) )
		{
			alert('Os campos período início e período fim são obrigatórios!');
		}
        else if ( !validaData(objForm.elements["dtInicio"].value))
        {
            alert("Data inicial inválida !");
            objForm.elements["dtInicio"].focus();
        }
        else if ( !validaData(objForm.elements["dtFim"].value))
        {
            alert(" Data final inválida !");
            objForm.elements["dtFim"].focus();
        }
		else if(objForm.elements["dtInicio"].value != objForm.elements["dtFim"].value && (!validaDataFinal(objForm.elements["dtInicio"].value, objForm.elements["dtFim"].value)))
		{
			alert("Data Término menor que a data inicio, favor corrigir!");
			objForm.elements["dtFim"].focus();
		}
		else if(validaDataFinal(hoje,objForm.elements["dtFim"].value) && ( validaDataFinal(hoje,objForm.elements["dtInicio"].value) ) )
		{
			alert("Os campos Data Inicial e Data Final estão maiores que a data atual, favor corrigir!");
			objForm.elements["dtInicio"].focus();
		}
        else if( objForm.canalSelecionado.value == "" )
        {
			alert('O campo Canal Atendimento é obrigatório!');
			objForm.canalSelecionado.focus();
		} else {
			top.frameApp.mostrar_div();
			objForm.action = "generarRelatorio.do";
			objForm.target = "";
			objForm.submit();
		}
	}
	else if( objRel[5].checked )
	{
		/*
		 * Validação relatório data de aniversario
		*/
		if( objForm.elements['{actionForm.dataAniversario}'].value == "" && (objForm.elements["dtInicio"].value == "" ||  objForm.elements["dtFim"].value == ""))
		{
			alert("Favor preencher Data Início e Data Término ou Data de Aniversário!")
		}
		else
		{
			if( (objForm.elements["dtInicio"].value != "") || (objForm.elements["dtFim"].value != ""))
			{
				if(objForm.elements["dtInicio"].value != objForm.elements["dtFim"].value && (!validaDataFinal(objForm.elements["dtInicio"].value, objForm.elements["dtFim"].value)))
				{
					alert("Data Término menor que a data inicio, favor corrigir!");
					objForm.elements["dtFim"].focus();
				}
				else if(validaDataFinal(hoje,objForm.elements["dtFim"].value) && ( validaDataFinal(hoje,objForm.elements["dtInicio"].value) ) )
				{
					alert("Os campos Data Inicial e Data Final estão maiores que a data atual, favor corrigir!");
					objForm.elements["dtInicio"].focus();
				}
                else
                {
                    top.frameApp.mostrar_div();
                    objForm.action = "generarRelatorio.do";
                    objForm.target = "";
                    objForm.submit();
                }
			}
			else if(!validaData(objForm.elements['{actionForm.dataAniversario}'].value))
			{
				alert("Data Aniversário inválida!");
				objForm.elements['{actionForm.dataAniversario}'].focus();
			}
			else
			{
				top.frameApp.mostrar_div();
				objForm.action = "generarRelatorio.do";
				objForm.target = "";
				objForm.submit();
			}
		}
	} else {
		alert("Nenhum relatório selecionado!");
	}
}


                function activaOperador()
                {
					document.forms["relEfetividadeActionForm"].sgOperador.disabled = false;
                }

                function inactivaOp()
                {
					document.forms["relEfetividadeActionForm"].sgOperador.disabled = true;
                }


                function limpar(obj)
                {
                    document.relEfetividadeActionForm.action = "begin.do";
                    document.relEfetividadeActionForm.target = "";
                    document.relEfetividadeActionForm.submit();
                }


                function cargaPergunta() {
                    var objForm = document.forms["relEfetividadeActionForm"];
                    if(objForm.canalSelecionado.value != "" && objForm.idRelatorio[4].checked) {
                        objForm.action = "cargarPergunta.do";
                        objForm.target = "innerBrowser";
                        objForm.submit();
                        top.frameApp.mostrar_div();
                    }
                }

                function cargaAreaRegistro() {
                    var objForm = document.forms["relEfetividadeActionForm"];
                    if(objForm.regionalSelecionada.value != "") { // && objForm.idRelatorio[4].checked) {
                        objForm.action = "cargarAreaRegistro.do";
                        objForm.target = "innerBrowser";
                        objForm.submit();
                        top.frameApp.mostrar_div();
                    }
                }


               function limparItens() {
                    var objForm = document.forms["relEfetividadeActionForm"];
                    objForm.versaoSelecionada.options.length = 0;
                    objForm.subcampanhaSelecionada.options.length = 0;
                    objForm.canalSelecionado.options.length = 0;
                    objForm.perguntaSelecionada.options.length = 0;
                    objForm.motivoSelecionado.options.length = 0;
                    objForm.perfilSelecionado.options.length = 0;
                    objForm.regionalSelecionada.options.length = 0;
               }
    </script>
    </netui-template:section>
    <netui-template:section name="bodySection">

    <acesso:controlHiddenItem nomeIdentificador="cam_indexrel_verpagina">
    <SCRIPT FOR=window EVENT=onload>
        top.frameApp.oculta_div();
        redrawTable(this);
    </script>

    <jsp:include page="/resources/menus/MenuPrincipal.jsp"/>
    <div><img src="<%=request.getContextPath()%>/resources/images/transp.gif" border="0" width="1" height="5"></div>
    <vivo:sessao id="idFrelatorio" height="470" width="790" label="Campanha > Relatórios" operacoes="" scroll="N">
    <form action="relEfetividadeAction.do" name="relEfetividadeActionForm" method="post">
        <div><img src="<%=request.getContextPath()%>/resources/images/transp.gif" border="0" width="1" height="3"></div>
        <table width="100%" cellpadding="0" cellspacing="0" border="0" height="100%">
        <tr>
        <td valign="top">
        <table width="100%" class="tbl_bggray" align="center">
            <tr>
                <td align="center" height="10%">
                <table border="0" >
                    <tr >
                        <td width="200">

                        <input class="radio" type="radio" name="idRelatorio"  value="1" onchange="activaOperador();" onclick="redrawTable(this);">
                        <netui:label value="Gerenciamento"/>
                        </td>
                        <td width="200">
                        <input class="radio" type="radio" name="idRelatorio" value="4"  onchange="inactivaOp();" onclick="redrawTable(this);" >
                        <netui:label value="Percentual por Motivos"/>
                        </td>
                        <td width="200">
                        <input class="radio" type="radio" name="idRelatorio" value="2" onchange="inactivaOp();" onclick="redrawTable(this);">
                        <netui:label value="Efetividade"/>
                        </td>
                    </tr>
                    <tr>
                        <td width="200">
                        <input class="radio" type="radio" name="idRelatorio" value="3" onchange="activaOperador();" onclick="redrawTable(this);">
                        <netui:label value="Operador"/>
                        </td>
                        <td width="200">
                        <input class="radio" type="radio" name="idRelatorio" value="5" onchange="activaOperador();" onclick="redrawTable(this);cargaPergunta();">
                        <netui:label value="Percentual de Respostas"/>
                        </td>
                        <td width="200">
                        <input class="radio" type="radio" name="idRelatorio" value="6" onchange="activaOperador();" onclick="redrawTable(this);">
                        <netui:label value="Agenda de Aniversário"/>
                        </td>
                    </tr>
                </table>
                </td>
            </tr>
        </table>
        <div><img src="<%=request.getContextPath()%>/resources/images/transp.gif" border="0" width="1" height="5"></div>
        <vivo:quadro id="qdMain" height="0" width="780" scroll="no" label="Filtros">
        <table width="100%">
            <tr>
                <td >
                <table id="todos3" width="100%" cellpadding="3" cellspacing="0" border="0">
                    <tr valign="top">
                        <td align="right" class="tblEstatica_campoNome" width="140"><font color="red">*</font>&nbsp;Campanha:
                        </td>
                        <td  colspan="3">
                        <html:select name="Form" property="campanhaSelecionada" size="1" style="width=525px;height=10px" onchange="consultaIFrameSubCampanha();">
                        <option value=""></option>
                        <html:options collection="aCampanha" property="codigo" labelProperty="sigla"/> </html:select>
                        </td>
                    </tr>
                    <tr valign="top">
                        <td align="right" class="tblEstatica_campoNome" width="140"><font color="red">*</font>&nbsp;Sub
                        Campanha:
                        </td>
                        <td colspan="3">
                        <html:select name="Form" property="subcampanhaSelecionada" size="1" style="width=525px;height=10px" onchange="consultaIFrameVersao();">
                            <option value=""></option>
                        </html:select>
                        </td>
                    </tr>
                    <tr valign="top">
                        <td align="right" class="tblEstatica_campoNome" width="140"><font color="red">*</font>&nbsp;Versão:
                        </td>
                        <td colspan="3">
                        <html:select name="Form" property="versaoSelecionada" size="1" style="width=150px;height=10px" onchange="consultaIFrameCanal();">
                        </html:select>
                        </td>
                    </tr>
                    <tr  valign="top">
                        <td class="tblEstatica_campoNome" align="right" width="140"><!-- span id="respostas5" style="display:none" --><font color="red">*&nbsp;</font><!--/span -->Canal
                        Atendimento:</td>
                        <td width="300" >
                        <html:select name="Form" property="canalSelecionado" size="1" style="width=150px;height=10px" onchange="cargaPergunta();">
                            <option value=""></option>
                        </html:select>
                        </td>
                        <td class="tblEstatica_campoNome" align="right" >Grupo:</td>
                        <td >
                        <html:select name="Form" property="perfilSelecionado" size="1" style="width=150px;height=10px">
                            <option value=""></option>
                        </html:select>
                        </td>
                    </tr>
                </table>
                <table  border="0" width="100%" cellpadding="3" cellspacing="0">
                    <tr>
                        <td id="respostas1" style="display:none"  align="right" width="140"><b>Pergunta</b></td>
                        <td id="respostas2" style="display:none" width="270" >
                            <html:select name="Form" property="perguntaSelecionada" size="1" style="width=150px;height=10px">
                                <option value=""></option>
							</html:select>
                        </td>
                        <td id="percentual1" style="display:none"  align="right" width="140"><b>Motivo:</b></td>
                        <td id="percentual2" style="display:none" width="270" >
                            <html:select name="Form" property="motivoSelecionado" size="1" style="width=150px;height=10px">
                                <option value=""></option>
                            </html:select>
                        </td>
                        <td id="gerenciamento1" width="140" align="right"><span id="operador" style="display:none"></span><b>Operador:</b></td>
                        <td id="gerenciamento2" width="180"><html:text name="Form" property="sgOperador"   styleClass="textfield"  maxlength="80" size="33"/></td>
                        <td id="gerenciamento3" width="15"></td>
                        <td id="gerenciamento4"  width="55"></td>
                        <td id="efetividade" style="display:none" width="416" align="right"></td>
                        <td id="todos1" align="right" width="93"><b>Regional:</b></td>
                        <td id="todos2">
                        <html:select name="Form" property="regionalSelecionada" size="1" style="width=150px;height=10px"  onchange="cargaAreaRegistro();">
                            <option value=""></option>
                        </html:select>
                        </td>
                        <td id="niver1" style="display:none"  align="right" width="93"><b>Linha:</b></td>
                        <td style="display:none"  id="niver2"><html:text name="Form" property="linha" styleClass="textfield" maxlength="14" size="15" onkeyup="maskPhoneNumberObj(this)"/>
                        </td>
                    </tr>
                    <tr>
                        <td id="respostas3" style="display:none"  align="right" width="140"></td>
                        <td colspan="5" style="display:none" id="respostas4"></td>
                        <td id="niver3" style="display:none"  align="right" width="140"><b><font color="red">*</font>&nbsp;Data de Aniversário</b></td>
                        <td colspan="5" style="display:none" id="niver4" >
                        <netui:textBox style="width:80px;" dataSource="dataAniversario"  maxlength="10" size="10" defaultValue=""/>
                        </td>
                    </tr>
                </table>
                <table  border="0" width="100%" cellpadding="3" cellspacing="0">
                    <tr>
                        <td id="respostas6" style="display:none"  align="right" width="140"><b>Distinção</b></td>
                        <td id="respostas7" style="display:none" width="150" >
                            <html:select name="Form" property="distincaoSelecionada" size="1" style="width=50px;height=10px">
        						<html:option value="0">Não</html:option>
								<html:option value="1">Sim</html:option>
							</html:select>
                        </td>
						<td colspan="9">&nbsp;</td>
                        <td id="area1" style="display:none"  align="right" width="140"><b>Area de Registro:</b></td>
                        <td id="area2" style="display:none" width="236" >
                            <html:select name="Form" property="areaRegistroSelecionada" size="1" style="width=50px;height=10px">
        						<html:option value=""></html:option>
							</html:select>
                        </td>
                    </tr>
				</table>
                <table width="700" border="0" cellpadding="3" cellspacing="0">
                    <tr id="datas" valign="top">
                        <td class="tblEstatica_campoNome" align="right" width="140"><font color="red">*</font>&nbsp;Data Inicial:</td>
                        <td width="250">
                            <html:text name="Form"
                                       property="dtInicio"
                                       onKeyUp="this.value = Format(this.value,'##/##/####');"
                                       maxlength="10"
                                       styleClass="textfield"
                                       size="10" />
                            <img src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:hand;" title="Calendário" onclick="return showCalendar('dtInicio', '%d/%m/%Y');" onkeydown="alert('dia')"></td>
                        <td width="111" align="right" class="tblEstatica_campoNome" ><font color="red">*</font>&nbsp;Data Final:</td>
                        <td>
                            <html:text name="Form"
                                       property="dtFim"
                                       onKeyUp="this.value = Format(this.value,'##/##/####');"
                                       maxlength="10"
                                       styleClass="textfield"
                                       size="10" />
                            <img src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:hand;" title="Calendário" onclick="return showCalendar('dtFim', '%d/%m/%Y');" onkeydown="alert('dia')">
                        </td>
                    </tr>
                    <tr>
                        <td  colspan="4" align="right" >
                        	<acesso:controlHiddenItem nomeIdentificador="cam_rel_pesq">
	                            <img class="button"
	                            	 vspace="10"
	                            	 onmouseup="mostrarRelatorio()"
	                            	 src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" />
                        	</acesso:controlHiddenItem>
                        	<img class="button"
                        		 vspace="10"
                        		 hspace="20"
                        		 onmouseup="window.location.href='/FrontOfficeWeb/campanha/Relatorio/begin.do';"
                        		 src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif" />
                        </td>
                    </tr>
                    <vivo:alert atributo="msgErrorRelatorio" />
                </table>
                </td>
            </tr>
        </table>
        </vivo:quadro>
        </td>
        </tr>
        <tr>
        <td>
        <table height="100%" border="0">
        <tr>
        <td valign="bottom"><img src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_voltar_over.gif'" border="0" onClick="top.location.href='/FrontOfficeWeb/index.jsp'; return false" style="border:none;cursor:hand;return false"/>
        </td>
        </tr>
        </table>
        </td>
        </tr>
        </table>
    </form>
    </vivo:sessao>
    <iframe id="lyrInnerBrowser" name="innerBrowser" style="display:none;" width="0px" height="0px">
    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>