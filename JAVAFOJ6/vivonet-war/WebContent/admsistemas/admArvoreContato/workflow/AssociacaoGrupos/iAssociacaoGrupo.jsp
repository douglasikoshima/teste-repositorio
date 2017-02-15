<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<bean:define id="Form"         name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="associacaoGrupoForm" />
<bean:define id="aGruposDisp"  name="Form" property="gruposProcessos.gruposDisponiveis.grupoProcessoVO.grupoVOArray" />
<bean:define id="aGruposAsso"  name="Form" property="gruposProcessos.gruposAbertura.grupoProcessoVO.grupoVOArray" />

<head>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/xtree.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
    <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">        
    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/frameweb.js" ></script>

    <script language="Javascript">        
        function ocultarDiv()
        {
           if (typeof(parent.oculta_div) != "undefined")
            {
                parent.oculta_div();
            }
            else
            {
                parent.parent.oculta_div();
            }
        }
    </script>

</head>
<body onload="ocultarDiv()">
<!--acesso:controlHiddenItem nomeIdentificador="adm_aret_verpagina"-->
    <form action="salvaAssociacaoGrupo.do" id="salvaAssociacaoGrupo" name="salvaAssociacaoGrupo" method="post">
            <html:hidden property="contato" name="Form"/>
            <table border="0" cellspacing="0" cellpadding="1" class="tbl_bgGray" height="30" align="center">
                <tr>
                    <td>            
                        <table align="center">
                            <tr>
                                <td align="center">
                                    Grupos disponíveis<br>
                                    <html:select name="Form" property="gruposDisponiveis" multiple="true" size="22" style="width:300px;">
                                        <html:options collection="aGruposDisp" property="codigo" labelProperty="descricao" /> 
                                    </html:select>                                        
                                </td>
                                <td align="right" valign="middle" width="76">
                                    <img id="btRightSimp" onclick="transfereSelecaoLista(document.forms[0].gruposDisponiveis, document.forms[0].gruposAssociados);" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_rightaln_over.gif" style="border:none;"/>
                                    <img id="btLeftSimp" onclick="transfereSelecaoLista(document.forms[0].gruposAssociados, document.forms[0].gruposDisponiveis);" src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_leftaln_over.gif" style="border:none;"/>
                                </td>
                                <td width="30"></td>
                                <td align="center">
                                    Grupos associados<br>
                                    <html:select name="Form" property="gruposAssociados" multiple="true" size="22" style="width:300px;">
                                        <html:options collection="aGruposAsso" property="codigo" labelProperty="descricao" /> 
                                    </html:select>  
                                </td>
                            </tr>
                        </table>                        
                    </td>
                </tr>
            </table>
        </form>  

    <script>
    //disponíveis
        var oOption = document.forms["0"].elements["gruposDisponiveis"];
        var oOptionParent = parent.document.forms["0"].elements["gruposDisponiveis"];
        
        while(oOptionParent.options.length != 0) {
            oOptionParent.options.remove(0);
        }
        
        for(i = 0; i < oOption.options.length; i++ ) {
            var oOptionNew = parent.document.createElement("OPTION");               
            oOptionParent.options.add(oOptionNew);
            oOptionNew.innerText = oOption.options(i).text;
            oOptionNew.value = oOption.options(i).value;            
        } 
        
        //associados
        oOption = document.forms["0"].elements["gruposAssociados"];
        oOptionParent = parent.document.forms["0"].elements["gruposAssociados"];
        
        while(oOptionParent.options.length != 0) {
            oOptionParent.options.remove(0);
        }
        
        for(i = 0; i < oOption.options.length; i++ ) {
            var oOptionNew = parent.document.createElement("OPTION");               
            oOptionParent.options.add(oOptionNew);
            oOptionNew.innerText = oOption.options(i).text;
            oOptionNew.value = oOption.options(i).value;            
        }   
        
        //parent.document.getElementById('mensagem').innerHTML='';        
    </script>
<!--/acesso:controlHiddenItem-->
</body>