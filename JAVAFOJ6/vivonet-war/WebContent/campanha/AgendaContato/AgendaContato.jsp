<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<html>
<acesso:controlHiddenItem nomeIdentificador="cam_ac_verpagina">
<link rel="stylesheet" type="text/css" href="/FrontOfficeWeb/resources/css/calendar.css">
<link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
<script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/calendar.js"></script>
<script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/frameweb.js" ></script>
<script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/vivoval.js" ></script>
 
 <script language="javascript">
            
function checaTextarea(obj, tamanho){
  obj.value.length>tamanho?obj.value = obj.value.substr(0,tamanho):0;
}

function salvar(obj){
    var dia = new Date();
    var mes = dia.getMonth() + 1;
    var hoje = dia.getDate().toString() + "/" + mes.toString() + "/" + dia.getYear().toString();
    var hojeMinutos = dia.getHours().toString() + ":" + dia.getMinutes().toString()


    if(trim(document.forms[0].elements["{actionForm.nomeContato}"].value) == "")
    {
        alert("Favor preencher o campo Contato!");
        document.forms[0].elements["{actionForm.nomeContato}"].focus();
    }
    else if(document.forms[0].elements["{actionForm.dsObservacao}"].value.length > 450)    
    {
        alert("Favor preencher o campo observação com no máximo 450 caracteres!");
        document.forms[0].elements["{actionForm.dsObservacao}"].focus();
    }
    else if(document.forms[0].elements["{actionForm.telefoneContato}"].value.length < 13)
    {
        alert("Favor preencher o campo Telefone de Contato!");
        document.forms[0].elements["{actionForm.telefoneContato}"].focus();
    }
    else if( trim( document.forms[0].elements["{actionForm.data}"].value ) == "" || !validaData( document.forms[0].elements["{actionForm.data}"].value ) )
    {
       alert("Data inválida!");
       document.forms[0].elements["{actionForm.data}"].focus();
    }
    else 
        if(!validaDataFinalEx(hoje,document.forms[0].elements["{actionForm.data}"].value, hojeMinutos, document.forms[0].elements["wlw-select_key:{actionForm.horas}"].value + ":" + document.forms[0].elements["wlw-select_key:{actionForm.minutos}"].value)){                
            alert('Data/Hora menor que a data/hora atual, favor corrigir!');
    }
    else
    {
        valor = obj.href.split("?");
        valor[1]?action = document.forms[0].action + "?" + valor[1]:action = document.forms[0].action;
        document.forms[0].action = action;
        top.frameApp.mostrar_div();
        document.forms[0].submit();
    }
}

    function voltar()
    {
        window.location.href = "/FrontOfficeWeb/campanha/AgendaContato/voltar.do";
        top.frameApp.mostrar_div();
    }
            
 </script>
 
    <SCRIPT FOR=window EVENT=onload>
        top.frameApp.oculta_div();
    </script>    

    <form action="agendaContato.do" name="agendaContatoForm" method="post">       
    <vivo:quadro id="qdPerg" width="100%" height="100%" label="Dados do Agendamento" scroll="no">
        <table width="100%">              
            <tr>
                <td><b>Contato:</b></td>
                <td>
                    <netui:textBox dataSource="nomeContato" styleClass="textfield" size="110" maxlength="100"  defaultValue=""/>
                </td>
            </tr>
        </table>
        <table width="100%" align="center">
            <tr>
                <td width="25%"><b>Telefone de Contato:</b></td>
                <td width="20%" ><netui:textBox dataSource="telefoneContato" styleClass="textfield" size="14" onKeyUp="this.value = Format(this.value,'(##)####-####');"/></td>
           
                <td><b>Data:</b></td>
                <td><netui:textBox dataSource="data" styleClass="textfield" size="10"  onKeyUp="this.value = Format(this.value,'##/##/####');"/><img src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:hand;" title="Calendário" onclick="return showCalendar('{actionForm.data}', '%d/%m/%Y');"></td>            
           
                <td ><b>Hora:</b></td>
                <td width="60%">
                <netui:select dataSource="horas">
                    <netui:selectOption value="00" />
                    <netui:selectOption value="01"/>
                    <netui:selectOption value="02"/>
                    <netui:selectOption value="03"/>
                    <netui:selectOption value="04"/>
                    <netui:selectOption value="05"/>
                    <netui:selectOption value="06"/>
                    <netui:selectOption value="07"/>
                    <netui:selectOption value="08"/>
                    <netui:selectOption value="09"/>
                    <netui:selectOption value="10"/>
                    <netui:selectOption value="11"/>
                    <netui:selectOption value="12"/>
                    <netui:selectOption value="13"/>
                    <netui:selectOption value="14"/>
                    <netui:selectOption value="15"/>
                    <netui:selectOption value="16"/>
                    <netui:selectOption value="17"/>
                    <netui:selectOption value="18"/>
                    <netui:selectOption value="19"/>
                    <netui:selectOption value="20"/>
                    <netui:selectOption value="21"/>
                    <netui:selectOption value="22"/>
                    <netui:selectOption value="23"/>                                        
                </netui:select>
                :
                <netui:select dataSource="minutos">
                    <netui:selectOption value="00"/>
                    <netui:selectOption value="05"/>
                    <netui:selectOption value="10"/>
                    <netui:selectOption value="15"/>
                    <netui:selectOption value="20"/>
                    <netui:selectOption value="25"/>
                    <netui:selectOption value="30"/>
                    <netui:selectOption value="35"/>
                    <netui:selectOption value="40"/>
                    <netui:selectOption value="45"/>
                    <netui:selectOption value="50"/>
                    <netui:selectOption value="55"/>                    
                </netui:select>
                </td>
            </tr>
            </table>
            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
            <table width="100%">
                <tr>
                    <td>
                        <b>Observações:</b><br>
                        <netui:textArea dataSource="dsObservacao" rows="2" cols="92" onKeyDown="checaTextarea(this,450)" onKeyPress="checaTextarea(this,450)" onKeyUp="checaTextarea(this,450)" onClick="checaTextarea(this,450)" onBlur="checaTextarea(this,450)" />
                    </td>
                </tr>
            </table>
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
        <vivo:tbTable selectable="true" layoutWidth="753" layoutHeight="160" tableWidth="753" styleId="TBHistorico" sortable="true">
            <vivo:tbHeader>
                <vivo:tbHeaderColumn align="left" width="15%" type="string">Data/Hora</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="left" width="20%" type="string">Telefone Contato</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="left" width="35%" type="string">Pessoa de Contato</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="left" width="25%" type="string">Observação</vivo:tbHeaderColumn>
            </vivo:tbHeader>
            <vivo:tbRows>
                <netui-data:repeater dataSource="{pageFlow.lAgendamento}">
                <netui-data:repeaterHeader></netui-data:repeaterHeader>
                <netui-data:repeaterItem>
                    <vivo:tbRow key="historico">
                        <vivo:tbRowColumn>
                            <vivo:hint maxLength="25">
                                <netui:label value="{container.item.dtAgendamento}" />
                            </vivo:hint>
                        </vivo:tbRowColumn>
                        <vivo:tbRowColumn>
                            <vivo:hint maxLength="30">
                                <netui:label value="{container.item.nrTelefoneContato}" />
                            </vivo:hint>
                        </vivo:tbRowColumn>
                        <vivo:tbRowColumn>
                            <vivo:hint maxLength="30">
                                <netui:label value="{container.item.nmPessoaContato}" />
                            </vivo:hint>
                        </vivo:tbRowColumn>
                        <vivo:tbRowColumn>
                            <vivo:hint maxLength="20">
                                <netui:label value="{container.item.dsObservacaoAgenda}" />
                            </vivo:hint>
                        </vivo:tbRowColumn>
                    </vivo:tbRow>
                </netui-data:repeaterItem>
                <netui-data:repeaterFooter> </netui-data:repeaterFooter>
                </netui-data:repeater>
            </vivo:tbRows> 
        </vivo:tbTable>
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="10"></div>
        <table align="right">
            <tr>
                 <td align="right">   
                 <acesso:controlHiddenItem nomeIdentificador="cam_ac_grav">             
                    <img class="button"
                    	 href="agendaContato.do?acao=ok"
                    	 src="/FrontOfficeWeb/resources/images/bt_registrar_nrml.gif"
                    	 onClick="salvar(this); return false" />
				</acesso:controlHiddenItem>
                </td>
                <td align="right">
                    <img src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif"  onClick="voltar();" onMouseOver=" this.src = '/FrontOfficeWeb/resources/images/bt_voltar_over.gif';" onMouseOut=" this.src = '/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif';" style="cursor:hand;" border="0"/>
                </td>
            </tr>
        </table>
</vivo:quadro>
</form>

</acesso:controlHiddenItem>
</html>