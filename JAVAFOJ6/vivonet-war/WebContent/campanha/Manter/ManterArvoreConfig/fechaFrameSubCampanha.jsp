<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>

<html>
    <head>
<SCRIPT FOR=window EVENT=onload>

    //Constantes
    var INCLUIR_SUBCAMPANHA = 1;
    var DELECAO_SUBCAMPANHA = 2;
    var DEL_STA_PERG_RESP   = 6;
    var INS_PERGUNTA        = 7;
    var UPD_PERGUNTA        = 8;
    var UPD_SUBCAMPANHA     = 9;
    var operacao = parseInt("<%=(request.getAttribute("tipo") == null) ? "-1" : request.getAttribute("tipo")%>");
    var msg = "<%=request.getAttribute("msgErrorViewScript")%>";
    
    top.frameApp.document.scriptCampanhaActionForm.campanhaSelecionada.disabled = false;
    top.frameApp.document.scriptCampanhaActionForm.subCampanhaSelecionada.disabled = false;
    top.frameApp.document.scriptCampanhaActionForm.versaoSelecionada.disabled = false;
        
    switch ( operacao ) {
        case INCLUIR_SUBCAMPANHA:
            top.frameApp.document.forms.frmSelecao.target = "_self";
            top.frameApp.document.forms.frmSelecao.action = "/FrontOfficeWeb/campanha/Manter/ManterArvoreConfig/ScriptCampanhaAction.do?acao=campanha&idCampanha=<%= session.getAttribute("idCampanha") %>&idSubCampanha=<%= session.getAttribute("idSubCampanha") %>";
            top.frameApp.document.forms.frmSelecao.submit();        
            break;
        case DELECAO_SUBCAMPANHA:            
            if ( msg != "null" && msg != "" ) {
                alert(msg);
            } else {                
                top.frameApp.consultaIFrameSubCampanha();                   
            }  
            top.frameApp.oculta_div();
            break;
        case 3:
            parent.fechaQuadroAuxiliar();
            parent.parent.oculta_div();        
            break;        
        case 4:
            parent.parent.fechaQuadroAuxiliar();
            top.frameApp.oculta_div();         
            break;
        case 5:
            parent.parent.consultaIFrameVersao();    
            break;
        case DEL_STA_PERG_RESP:
            if ( msg != "null" && msg != "" ) { 
                alert(msg);
            } else {                 
                parent.deleteSelectedNode();
            }  
            top.frameApp.oculta_div();
            break;
        case INS_PERGUNTA:
            parent.fechaQuadroAuxiliar();
            parent.parent.oculta_div(); 
            parent.SetCodigoCanal("<%=session.getAttribute("idCanalCampanha")%>");
            break;
        case UPD_PERGUNTA:
            parent.tree.getSelected().getParent().select();
            parent.fechaQuadroAuxiliar();
            parent.parent.oculta_div(); 
            parent.SetCodigoCanal("<%=session.getAttribute("idCanalCampanha")%>");
            break;            
        case UPD_SUBCAMPANHA:
            top.frameApp.document.forms.frmSelecao.target = "_self";
            top.frameApp.document.forms.frmSelecao.action = "/FrontOfficeWeb/campanha/Manter/ManterArvoreConfig/ScriptCampanhaAction.do?acao=campanha&idCampanha=<%= session.getAttribute("idCampanha") %>&idSubCampanha=<%= session.getAttribute("idSubCampanha") %>";
            top.frameApp.document.forms.frmSelecao.submit();
            break;
        default:
            location.href = "/FrontOfficeWeb/campanha/Manter/ManterArvoreConfig/CarregarAction.do?idCampanha=<%= session.getAttribute("idCampanha") %>&idSubCampanha=<%= session.getAttribute("idSubCampanha") %>&idCanalCampanha=<%= session.getAttribute("idCanalCampanha") %>";
            parent.abaSelected(parent.btAba, parent.bt01);        
            break;        
    }
</SCRIPT>

    </head>
    <body>
    </body>
</html>