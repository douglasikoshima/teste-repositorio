<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<bean:define id="FormArvoreConsulta" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreConsulta"/>
    <head>
        <title>Arvore Parâmetros</title>
        <link href="<%=request.getContextPath()%>/resources/css/xtree.css" type="text/css" rel="stylesheet"/>
        <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/xtree.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
    </head>
    <body>
    <script language="JavaScript">
    
    
            function visualizar() 
            {
                for(i = 0; i<document.forms[0].idContatoArray.options.length; i++)
                    document.forms[0].idContatoArray[i].selected = true;

                document.forms[0].target = "ifrPesquisa";
                parent.document.getElementById("dvPesquisa").style.display = 'block';
                document.forms[0].action = 'montaLista.do';
                //parent.mostrar_div();
                document.forms[0].submit();
            }

            // Exporta dados XLS (CSV).
            function exportar()
            {
                for(i = 0; i<document.forms[0].idContatoArray.options.length; i++)
                    document.forms[0].idContatoArray[i].selected = true;

                document.forms[0].target = '';
                document.forms[0].action = 'exportaRetorno.do';
                //mostrar_div();
                document.forms[0].submit();
            }                

            function capturaParametrosContato(idContato, idContatoPai, inDisponibilidade, inFolha, dsPath)
            {
                document.forms[0].idContato.value = idContato;            
                document.forms[0].idContatoPai.value = idContatoPai;
                document.forms[0].inDisponibilidade.value = inDisponibilidade;            
                document.forms[0].inFolha.value = inFolha;
                document.forms[0].dsPath.value = dsPath;
            }
            
            var tmpArvore = null;
            
            function expandirNo(idContato, idContatoPai, inDisponibilidade, inFolha, dsPath)
            {
                
                document.forms[0].idContato.value = idContato;
                document.forms[0].idContatoPai.value = idContatoPai;
                document.forms[0].inDisponibilidade.value = inDisponibilidade;            
                document.forms[0].inFolha.value = inFolha;
                document.forms[0].dsPath.value = dsPath;

                if (!tree.getSelected().isAddEnabled()) 
                {
                    return;
                }

                document.forms[0].method = "POST";
                document.forms[0].action = "montaArvoreParte.do";
                document.forms[0].target = "iframeArvoreParametro";
                parent.mostrar_div();
                document.forms[0].submit();
            
            }
            
            function limpar()
            {
                var form  = document.forms[0];

                form.idContato.value                    = '';
                form.dsPath.value                       = '';

                while(document.forms[0].idContatoArray.length > 0)
                {
                    document.forms[0].idContatoArray[0] = null;
                }
                
                return false;
            
            }

            function selecionarItem()
            {
                //Verifica se Ítem já existe na lista.
                for(i = 0; i<document.forms[0].idContatoArray.options.length; i++)
                {
                    if(document.forms[0].idContatoArray.options[i].value == document.forms[0].idContato.value)
                    {
                        alert("Este Ítem já foi selecionado.");
                        return;
                    }
                }
                
                if(document.forms[0].idContato.value == '')
                {
                   alert('Selecione um Ítem para visualização.');
                   return false;
                             
                }else
                {
                    document.forms[0].idContatoArray.options[document.forms[0].idContatoArray.options.length] = new Option(document.forms[0].dsPath.value,document.forms[0].idContato.value);
                    document.forms[0].idContato.value = '';
                }
            }
            
            function excluiItem()
            {
                var flgExisteItem = false;
                for(i = 0; i<document.forms[0].idContatoArray.options.length; i++)
                {
                    if(document.forms[0].idContatoArray.options[i].selected)
                    {
                        flgExisteItem = true;
                        document.forms[0].idContatoArray.options[i] = null;
                    }
                }
                
                if(!flgExisteItem)
                    alert("Selecione um Ítem para retirar da lista de Contato.");
            }

    </script>
        <form method="post" name="myForm" onsubmit="return false;">

        <html:hidden name="FormArvoreConsulta" property="idContatoSelecionado" value="-1"/>
        <html:hidden name="FormArvoreConsulta" property="idContato"/>
        <html:hidden name="FormArvoreConsulta" property="idContatoPai"/>
        <html:hidden name="FormArvoreConsulta" property="inDisponibilidade"/>
        <html:hidden name="FormArvoreConsulta" property="inFolha"/>
        <html:hidden name="FormArvoreConsulta" property="dsPath"/>

        <table cellpadding="0" cellspacing="0" width="760" height="430" border="0" class="tbl_bggray">
            <tr>
                <td>
                    <table id="tb_arvore" cellpadding="0" cellspacing="0" border="0" width="100%" height="320">
                        <tr>
                            <td width="47%">
                                <vivo:quadro height="100%" id="" width="100%" label="Árvore de Contato Existente" >
                                    <table cellpadding="0" class="tbl_bggray" width="100%" height="100%">
                                        <tr>
                                            <td valign="top" align="left">
                                                <script>
                                                     <%=(String)request.getAttribute("arvore")%>
                                                </script>
                                            </td>
                                        </tr>
                                    </table>
                                </vivo:quadro>
                            </td>
                            <td align="center">
                                <table >
                                    <tr>
                                        <td align="center">
                                            <img id="btnSelect" vspace="10"onClick="selecionarItem();" src="/FrontOfficeWeb/resources/images/bt_setadir_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_setadir_over.gif" style="border:none;"/></td></tr><tr><td><img vspace="10"onClick="excluiItem();" src="/FrontOfficeWeb/resources/images/bt_xix_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_xis_over.gif" style="border:none;"/>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                            <td width="47%">
                                <vivo:quadro height="100%" id="" width="100%" label="Árvore de Contato Selecionada" scroll="no">
                                    <table cellpadding="0" cellspacing="0" class="tbl_bggray" width="100%" height="100%">
                                        <tr>
                                            <td valign="top" align="center">
                                                <html:select name="FormArvoreConsulta" property="idContatoArray" multiple="true" style="width:320px;height:305px;" styleClass="SELECT" size="6" />
                                            </td>
                                        </tr>
                                    </table>
                                </vivo:quadro>
                            </td>
                        </tr>
                    </table>

                </td>
            </tr>
            <tr>
                <td align="right">
                        <img vspace="5" hspace="10" onClick="return visualizar(); return false;" src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif" style="border:none;"/>
                        <img vspace="5"  hspace="10" onClick="return limpar(); return false;" src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_limpar_over.gif" style="border:none;"/>                        
                        <img vspace="5"  hspace="10" onclick="exportar(); return false;" src="/FrontOfficeWeb/resources/images/bt_exportar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_exportar_over.gif" style="border:none;"/>
                </td>
            </tr>
        </table>
        </form>

        <iframe scrolling="yes" name="iframeArvoreParametro" height="110px" width="110px" style="visible:hidden;display:none"></iframe>

    <script language="javascript" for="window" event="onload">
    <!--   
        if('<bean:write name="FormArvoreConsulta" property="msgError" />' != "")
        {
            alert('<bean:write name="FormArvoreConsulta" property="msgError" />');
        }
        parent.oculta_div();
        document.forms[0].idContato.value = '';
        parent.abaSelected(parent.btAba, parent.bt02);
    -->
    </script>