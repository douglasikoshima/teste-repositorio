<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<acesso:controlInitEnv/>

<bean:define id="lista" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="lista" />

<netui-template:template templatePage="/resources/jsp/templateRetencao.jsp">
	<netui-template:section name="headerSection">
        <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js" ></script>
        <script language="javascript">
            function excluir(obj){
                if (confirm('Deseja realmente excluir este registro?')){
                    valor = obj.href.split("?");
                    valor[1] ? action = document.listaDefDestinoForm.action + "?" + valor[1] : action = document.listaDefDestinoForm.action;
                    anchor_submit_form("Netui_Form_0",action);
                    //document.forms[0].submit();
                }
            }
            function inclui(obj){
                if(document.listaDefDestinoForm.elements["{actionForm.descricao}"].value == ""){
                    alert("Favor preencher o Destino Previsto!");
                }else{
                    valor = obj.href.split("?");
                    valor[1]?action = document.listaDefDestinoForm.action + "?" + valor[1]:action = document.listaDefDestinoForm.action;
                    anchor_submit_form("Netui_Form_0",action);
                }
            }
            semCaracterEspeciais = new RegExp("[0-9a-zA-z% ]");
            function validacionStrEspecial(obj){
                valor = obj.value;
                for(i=0;i<valor.length;i++){
                    if(!semCaracterEspeciais.test(valor.charAt(i))){
                        valor = valor.substring(0,i) + valor.substring(i+1,valor.length);
                        i = -1;
                    }
                }
                obj.value = valor;
            }

            function preValidaKey(){
                if (window.event.keyCode == 13){
                    window.event.keyCode = 0;
                    return false;
                }
            }
        </script>

        <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
            <!--
                top.frameApp.oculta_div();
            -->
        </SCRIPT>
    </netui-template:section>
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="fid_mdd_verpagina">
        <form name="listaDefDestinoForm" action="ListaDefDestino.do" onKeyPress="preValidaKey()" method="post">
        <table width="100%">
            <tr>
                <td>
                <table width="770">
                    <tr>
                        <td width="100">Destino Previsto:</td>
                        <td width="535">
                            <netui:textBox dataSource="descricao"  styleClass="textfield" size="134" style="width:525px;" maxlength="2000" id="xx" defaultValue=""/>
                            <netui:hidden dataSource="id" />
                        </td>
                        <td width="135" align="right" valign="bottom" nowrap>
                            <acesso:controlHiddenItem nomeIdentificador="fid_mdd_incluir">
                                <img id="incluir"
                                	 class="button"
                                	 href="?acao=ok"
                                	 src="/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif"
                                	 formSubmit="true"
                                	 border="0"
                                	 onClick="inclui(this); return false" />
                            </acesso:controlHiddenItem>

                            <acesso:controlHiddenItem nomeIdentificador="fid_mdd_pesquisar">
                            	<img id="incluir"
                                	 class="button"
                                	 href="?acao=consultar"
                                	 src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml"
                                	 formSubmit="true"
                                	 border="0"
                                	 onClick="inclui(this); return false" />
                            </acesso:controlHiddenItem>
                        </td>
                    </tr>
                </table>
                </td>
            </tr>
            <tr>
                <td>
                    <vivo:tbTable selectable="true" onRowClick="" layoutWidth="755" layoutHeight="320" tableWidth="755" styleId="tableTitle" sortable="true">
                        <vivo:tbHeader>
                            <vivo:tbHeaderColumn align="left" width="90%" type="string">Descrição</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="left" width="5%" type="">&nbsp;</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="left" width="5%" type="">&nbsp;</vivo:tbHeaderColumn>
                        </vivo:tbHeader>

                        <vivo:tbRows>
                        	<logic:iterate id="item" name="lista" indexId="c">
                        		<vivo:tbRow key="linha1">
                                    <vivo:tbRowColumn>
                                    	<bean:write name="item[1]" />
                                    </vivo:tbRowColumn>
                                    <vivo:tbRowColumn>
                                        <acesso:controlHiddenItem nomeIdentificador="fid_mdd_alterar">
                                            <img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif"
                                            	 class="button"
                                            	 onmouseup="document.forms[0].action=document.forms[0].action+'?acao=editar&valor=<%=c%>';document.forms[0].submit();" />
                                        </acesso:controlHiddenItem>
                                    </vivo:tbRowColumn>
                                    <vivo:tbRowColumn>
	                                    <acesso:controlHiddenItem nomeIdentificador="fid_mdd_excluir">
	                                    	<img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif"
                                            	 class="button"
                                            	 onmouseup="document.forms[0].action=document.forms[0].action+'?acao=excluir&valor=<%=c%>';document.forms[0].submit();" />
                                        </acesso:controlHiddenItem>
                                    </vivo:tbRowColumn>
                                </vivo:tbRow>
                        		
                        	</logic:iterate>

                        </vivo:tbRows>
                    </vivo:tbTable>
                    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
                    <table width="772" height="26" border="0" cellspacing="0" cellpadding="5" style="border:1px solid #D4D7DE; background-color:#F7F9FA; font-size:10px;">
                        <tr>
                            <td width="100" valign="middle"><b>&nbsp;Legendas:</b></td>
                            <td width="150"><img vspace="2" src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" align="middle"> &nbsp;Alterar Destino</td>
                            <td width="522"><img vspace="2" src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" align="middle"> &nbsp;Excluir Destino</td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
        </form>
        <script>
            document.body.style.backgroundColor = '#ededed';
            inicio = location.href.indexOf("acao=")+5
            fim = location.href.length
            if(location.href.substring(inicio,fim) == "erro"){
                alert("Destino já cadastrado")
            }
            if(location.href.substring(inicio,fim) == "erroMatriz"){
                alert("Existe Matriz cadastrada para este Destino.")
            }
            if(location.href.substring(inicio,fim) == "editar"){
                document.getElementById('incluir').src = "/FrontOfficeWeb/resources/images/bt_alterar_nrml.gif";
                document.getElementById('incluir').onmouseover = over;
                document.getElementById('incluir').onmouseout = out;
                function over(){
                    swapImage(document.getElementById('incluir'),'/FrontOfficeWeb/resources/images/bt_alterar_over.gif');
                }
                function out(){
                    swapImage(document.getElementById('incluir'),'/FrontOfficeWeb/resources/images/bt_alterar_nrml.gif');
                }

            }
        </script>
</acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>
