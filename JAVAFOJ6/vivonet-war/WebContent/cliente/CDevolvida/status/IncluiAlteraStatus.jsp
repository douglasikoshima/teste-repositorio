<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>

<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="statusForm"/>
<bean:define id="Status" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="statusForm.listaStatusCorrespVO"/>
<bean:define id="Status2" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="statusForm.listaStatusCorrespVO.statusCorrespVOArray"/>
<bean:define id="ListaDisp" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="statusForm.unidadeVO.unidadeDisponivelVOArray"/>
<bean:define id="ListaSelec" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="statusForm.unidadeVO.unidadeSelecionadaVOArray"/>

<head>

    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
    <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">        
    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/frameweb.js" ></script>

    <script language="javaScript">
        function valida() {
            if (document.forms[0].sigla.value == ""){
                alert("Favor preencher o campo Sigla!");
                return(false);
            }else if (document.forms[0].descricao.value == ""){
                alert("Favor preencher o campo Descrição!");
                return(false);
            }else if (document.forms[0].qtDiasValido.value == "0"){
                alert('O campo Dias Válidos não pode ter valor "0"!');
                return(false);
            }else if (document.forms[0].qtDiasValido.value == "" && document.forms[0].idProximo.value != "0"){
                alert('Status sem quantidade de dias válidos não deve possuir Próximo Status!');
                document.forms[0].idProximo.options[0].selected = true;
                return(false);
        <logic:equal value="UPDATE" name="operacao" scope="request">
            }else if(document.forms[0].inDisponibilidade.value == "1"){    
              if (document.forms[0].qtDiasValido.value != "" && document.forms[0].qtDiasValido.value != "0" && document.forms[0].idProximo.value == "0"){
                alert('Favor selecionar o campo Próximo Status!');
                return(false);
              }
              else return(true); 
            } 
          </logic:equal>
          <logic:notEqual value="UPDATE" name="operacao" scope="request">
            }else if (document.forms[0].qtDiasValido.value != "" && document.forms[0].qtDiasValido.value != "0" && document.forms[0].idProximo.value == "0"){
                alert('Favor selecionar o campo Próximo Status!');
                return(false);
             }
          </logic:notEqual>
            else {
                return(true);
            }
        }
        
    
        function salvarStatus() {
            for(i=0; i < document.statusForm.unidadesSelecionadas.options.length; i++){
                document.statusForm.unidadesSelecionadas.options[i].selected = true;
            }
            if(valida()) {
                var action   = "salvarstatuscorresp.do"
                var operacao = "<bean:write name="operacao" scope="request"/>";
                
                switch(operacao) {
                    case "INSERT":
                        action += "?operacao=INSERT";
                        break;
                        
                    case "UPDATE":
                        action += "?operacao=UPDATE&index=<bean:write name="index" scope="request"/>";
                        break;
                }
                document.forms[0].operacao.value=operacao;
                document.forms[0].action = action;
                document.forms[0].target = "executa";
                parent.mostrar_div();
                document.forms[0].submit();
            }
                            
         }
            
              //copia os elementos selecionados para o combo destino, mas não os apaga do combo origem
                function transfereSelecaoLista(listaOrigem, listaDestino)
                {
                    var i;
                    for(i = 0; i<listaOrigem.options.length; i++){
                        if(listaOrigem.options[i].selected && testaDestino(listaOrigem.options[i].value, listaDestino)){
                            listaDestino.options[listaDestino.options.length] = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);
                        }
                    }
                }
                
                //testa se o elemento selecionado do combo origem já está no combo destino
                function testaDestino(valor, listaDestino)
                {   var i;
                    if(listaDestino.options.length == 0){
                        return true;
                    }
                    else
                    {
                        for(i = 0; i < listaDestino.options.length; i++)
                            if(valor == listaDestino.options[i].value)
                                return false;
                        return true;
                    }
                }
                
                function removeSelecaoLista(listaSelecionada)
                {
                    var i;
                    for(i = listaSelecionada.options.length-1; i>=0; i--)
                        if(listaSelecionada.options[i].selected)
                            listaSelecionada.options[i] = null;
                            
                    return false;
                }           
     </script>
    <script for="window" event="onload">
        parent.oculta_div();
    </script>

</head>

<body>
    <acesso:controlHiddenItem nomeIdentificador="cli_ias_verpagina">
    <form name="statusForm" id="statusForm" action="salvarstatuscorresp" target="_parent"> 
    
    <input type="hidden" name="operacao" value="<bean:write name="operacao" scope="request"/>"/>
    
    <html:hidden name="Form" property="id"/>
    <logic:equal name="Form" property="inMsgRetorno" value="true">
        <script>
            alert('Status de correspondência não pôde ser incluído/alterado pois já existe um status com essa descrição/sigla!');
        </script>
    </logic:equal>              
        
        <table width="480" border="0" cellspacing="0" cellpadding="0">
        <tr><td height="10"></td></tr> 
        <tr> 
            <td style="text-indent:6px;">Sigla:</td>
            <td>
                <html:text name="Form" property="sigla" title="sigla" maxlength="6" size="60" style="width:40px;" />
            </td>
        </tr>
        <tr><td  height="5"></td></tr>     
        <tr> 
            <td style="text-indent:6px;" >Descrição:</td>
            <td>
                <html:text name="Form" property="descricao" maxlength="50" title="descricao" size="71" />
            </td>
        </tr>
        <tr><td height="5"></td></tr>         
        <tr> 
            <td style="text-indent:6px;">Dias Válidos:</td>
            <td>
               <html:text name="Form" property="qtDiasValido" maxlength="3" onkeyup="checaInteiro(this)" title="qtDiasValido" size="03" />
                &nbsp&nbsp;Próximo Status:&nbsp
                <html:select name="Form" property="idProximo" style="width:250px;">
                    <html:option value="0">-- Nenhum --</html:option>
                    <html:options collection="Status2" property="id" labelProperty="descricao"/>
                </html:select>                    
            </td>
        </tr>
        <logic:equal value="UPDATE" name="operacao" scope="request">
            <tr>
                <td style="text-indent:4px;" height="30">Disponibilidade:</td>
                <td>
                    <html:select name="Form" property="inDisponibilidade">
                        <html:option value="1">Sim</html:option>
                        <html:option value="0">Não</html:option>
                    </html:select>
                </td>
            </tr>
        </logic:equal>
        <tr><td  height="20"></td></tr>            
        <tr>
            <td colspan="2">
                <table width="480">
                    <tr>
                        <td rowspan="2" width="11"></td>
                        <td width="198" rowspan="2"><center>Unidades Disponíveis</center>
                            <html:select name="Form" style="width:198px;" property="unidadesDisponiveis" multiple="true" size="4" ondblclick="transfereSelecaoLista(document.statusForm.unidadesDisponiveis, document.statusForm.unidadesSelecionadas);">&nbsp;&nbsp;&nbsp;&nbsp; 
                                <html:options collection="ListaDisp"  property="id" labelProperty="descricao"/>                                        
                            </html:select>
                        </td>
                        <td width="72">
                            <img vspace="10"
							     src="/FrontOfficeWeb/resources/images/bt_right_nrml.gif"
								 style="border:none;"
								 onclick="transfereSelecaoLista(document.statusForm.unidadesDisponiveis, document.statusForm.unidadesSelecionadas);return false" /><br>
                            
                        </td>
                        <td rowspan="2" width="199"><center>Unidades Selecionadas</center>
                            <html:select name="Form" style="width:199px;" property="unidadesSelecionadas" multiple="true" ondblclick="removeSelecaoLista(document.statusForm.unidadesSelecionadas);" size="4">
                                <html:options collection="ListaSelec"  property="idUnidade" labelProperty="descricao"/>                                        
                            </html:select>
                        </td>
                    </tr>
                    <tr>
                        <td>
							<img src="/FrontOfficeWeb/resources/images/bt_left_nrml.gif"
							     style="border:none;"
								 onClick="removeSelecaoLista(document.statusForm.unidadesSelecionadas);return false" />
						</td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>              
            <td align="right" colspan="2">
            <acesso:controlHiddenItem nomeIdentificador="cli_ias_salvar">
                <img style="border:0px;" vspace="10" onClick="salvarStatus(); return false" src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" />
            </acesso:controlHiddenItem>
            </td>
        </tr>                                    
    </table>
    
    </form>
    <script>
        document.body.style.backgroundColor = '#ededed';
    </script>
</acesso:controlHiddenItem>
</body>