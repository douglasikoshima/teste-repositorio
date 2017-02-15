<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<bean:define id="FormUser"    name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaUsuariosForm"/>
<bean:define id="usuarioPesquisa" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaUsuariosForm.usuarioPesquisa"/>
<bean:define id="aUsuarios"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaUsuariosForm.arrayUsuariosVO.usuarioVOArray"/>
<bean:define id="aTipoDoc"    name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaUsuariosForm.listaSelects.tipoDocumentoVOArray"/>
<bean:define id="aStatus"     name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaUsuariosForm.listaSelects.statusUsuarioVOArray"/>
<bean:define id="aRegional"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaUsuariosForm.listaSelects.UFOperadoraUsuarioVOArray"/>
<bean:define id="listaSelects"    name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaUsuariosForm.listaSelects"/>
<bean:define id="aCargo"      name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaUsuariosForm.listaCargo.cargoVOArray"/>
<bean:define id="aNiveis"       name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaUsuariosForm.listaNivelOrganograma.nivelOrganogramaSimplVOArray"/>
<bean:define id="aOrganizacoes" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaUsuariosForm.listaOrganizacao.organizacaoSimplVOArray"/>
<bean:define id="aUnidades"     name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaUsuariosForm.listaUnidadeOrganograma.unidadeOrganogramaVOArray"/>
<bean:define id="dadosAtuais"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaUsuariosForm.dadosAtuais" />

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Manter Sistema"/>
    <netui-template:setAttribute name="modulo" value="Gestão de Usuários"/>
    <netui-template:section name="headerSection">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
        <script language="javascript" src="/FrontOfficeWeb/resources/scripts/calendar.js"></script>
        <script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/xtooltip.js"></script>
        <script language="javascript">

            //Globais
            var bolEnvia = false;

            function mostraTabela() {

                document.divtable.visibility='visible';
            }

            function escondeMotivo() {
                var a = document.getElementById("div1");
                a.style.visibility = 'hidden';
            }

            function exibeMotivo() {
                var a = document.getElementById("div1");
                a.style.visibility = 'visible';
            }

            function testaMotivo() {
                if (document.forms[0].idStatusAtual.value == "1") {
                    exibeMotivo();
                }
                else {
                    escondeMotivo();
                }
            }

            // Válida envio de dados.
            function validaPesquisa()
            {
                var form = document.forms[0];

                // Sem preencher nenhum campo.
                if(form.tipoDocAtual.selectedIndex  == 0
                      && form.nome.value        == ''
                      && form.sobrenome.value   == ''
                      && form.login.value       == ''
                      && form.loginCti.value    == ''
                      && form.loginChefe.value  == ''
                      && form.nomeMeio.value    == ''
                      && form.idUFOperadora.selectedIndex == 0
                      && form.idStatusAtual.selectedIndex == 0
                      && form.nivelOrganogramaAtual.selectedIndex == 0
                      && form.cargoAtual.selectedIndex == 0
                      && form.organizacaoAtual.selectedIndex == 0
                      && form.unidadeOrganizacaoAtual.selectedIndex ==0
                  )
                  {
                    alert('É necessário o preenchimento de um ou mais campos para pesquisa.');
                    form.nome.focus();
                    return false;

                  // Se CPF.
                  }else if(form.tipoDocAtual.value == 'CPF')
                  {
                    // Válida CPF.
                    if(!validaCPF(form.dsDocAtual.value))
                    {
                        alert('CPF inválido.');
                        form.dsDocAtual.focus();
                        return false;
                    }

                   // Se CNPJ
                  }else if(form.tipoDocAtual.value == 'CNPJ')
                  {
                    // Válida CNPJ.
                    if(!validaCPF(form.dsDocAtual.value))
                    {
                        alert('CNPJ inválido.');
                        form.dsDocAtual.focus();
                        return false;

                     }

                   // Se RG
                  }else if(form.tipoDocAtual.value == 'RG')
                  {
                         if(form.dsDocAtual.value == '')
                          {
                            alert('RG é um campo obrigatório.');
                            form.dsDocAtual.focus();
                            return false;
                          }
                  }else if(form.tipoDocAtual.value == 'RE')
                  {

                    if(form.dsDocAtual.value == '')
                    {
                        alert('RE é um campo obrigatório.');
                        form.dsDocAtual.focus();
                        return false;
                    }

                  // Se nome tem menos que 3 caracteres.
                  //}else if(form.nome.value != '' && form.nome.value.length < 3)
                  //{
                  //      alert('O campo Nome deve ter no minino 3 caracteres.');
                  //      return false;

                  // Se sobrenome tem menos que 3 caracteres.
                  //}else if(form.sobrenome.value != '' && form.sobrenome.value.length < 3)
                  //{
                   //     alert('O campo Sobrenome deve ter no minino 3 caracteres.');
                   //     return false;

                  }else if(form.tipoDocAtual.selectedIndex  == 0
                      && form.nome.value        == ''
                      && form.sobrenome.value   == ''
                      && form.login.value       == ''
                      && form.loginCti.value    == ''
                      && form.loginChefe.value  == ''
                      && form.nomeMeio.value    == ''
                      && form.idUFOperadora.selectedIndex == 0
                      && form.idStatusAtual.selectedIndex != 0
                      && form.nivelOrganogramaAtual.selectedIndex == 0
                      && form.cargoAtual.selectedIndex == 0
                      && form.organizacaoAtual.selectedIndex == 0
                      && form.unidadeOrganizacaoAtual.selectedIndex == 0
                  )
                  {
                    alert('Na pesquisa por Status é necessário o preenchimento de outro campo.');
                    return false;
                  }


                  return true;

            }

            // Envia dados para pesquisa.
            function pesquisa()
            {
                if(validaPesquisa())
                {
                    document.forms[0].target = '';
                    document.forms[0].action = 'pesquisaUsuarios.do';
                    mostrar_div();
                    document.forms[0].submit();

                }else
                {
                    return false;
                }

            }

            // Envia dados para pesquisa.
            function pesquisaCampo(op)
            {
                if(op.keyCode == 13)
                {
                    if(validaPesquisa())
                    {
                        document.forms[0].target = '';
                        document.forms[0].action = 'pesquisaUsuarios.do';
                        mostrar_div();
                        document.forms[0].submit();  return false;

                    }else
                    {
                        return false;
                    }

                }

            }


            function mostraTipoDoc(){
                    <logic:iterate name="listaSelects" property="tipoDocumentoVOArray" id="documento" >
                    var <bean:write name="documento" property="sgTipoDocumento"/> = document.getElementById("<bean:write name="documento" property="sgTipoDocumento"/>");
                    <bean:write name="documento" property="sgTipoDocumento"/>.style.visibility = 'hidden';
                    if (document.forms[0].tipoDocAtual.value == "<bean:write name="documento" property="sgTipoDocumento"/>")
                    {
                        <bean:write name="documento" property="sgTipoDocumento"/> = document.getElementById("<bean:write name="documento" property="sgTipoDocumento"/>");
                        <bean:write name="documento" property="sgTipoDocumento"/>.style.visibility = 'visible';
                    }
                    </logic:iterate>
            }

            function mostraCampo()
            {
                var campoDoc = document.getElementById("divCampo");

                if(document.forms[0].tipoDocAtual.selectedIndex > 0)
                    campoDoc.style.visibility = 'visible';
                else
                {
                    campoDoc.style.visibility = 'hidden';
                    campoDoc.value == '';
                }
            }

            function validaCampoDoc(obj)
            {
                var form = document.forms[0];

                // Se CPF.
                if(form.tipoDocAtual.value == 'CPF')
                {
                    checaCPF(obj);
                }
                // Se CNPF.
                else if(form.tipoDocAtual.value == 'CNPJ')
                {
                    checaCNPJ(obj);
                }
                // Se RE
                else if(form.tipoDocAtual.value == 'RE')
                {
                    if(obj.value.length > 12)
                    {
                        obj.value = obj.value.substring(0,12);
                        return false;
                    }else
                        checaInteiro(obj);

                }
                else if(form.tipoDocAtual.value == 'RG')
                {
                    if(obj.value.length > 12)
                    {
                        obj.value = obj.value.substring(0,12);
                        return false;
                    }else
                        checaStrEspecial(obj);
                }

                return false;

            }

            function carregaComboNivel()
            {
                if(document.forms[0].nivelOrganogramaAtual.selectedIndex != 0)
                {
                    if(!bolEnvia)
                    {
                        document.forms[0].target = 'ifmOrganomagrama';
                        document.forms[0].action = 'carregaComboOrgano.do?acao=pesquisaH';
                        mostrar_div();
                        bolEnvia = true;
                        document.forms[0].submit();
                        desativar_combos();

                    }

                // Limpa combo Cargo
                }else
                {
                    while(document.forms[0].elements["cargoAtual"].options.length > 0)
                    {
                          document.forms[0].elements["cargoAtual"].options[0]     = null;
                    }

                    document.forms[0].cargoAtual.options[0] = new Option("Escolha uma opção...", "");
                }
            }

            function carregaComboOrganizacao()
            {
                if(document.forms[0].organizacaoAtual.selectedIndex != 0)
                {
                    if(!bolEnvia)
                    {
                        document.forms[0].target = 'ifmOrganomagrama';
                        document.forms[0].action = 'carregaComboOrgano.do?acao=pesquisaO';
                        mostrar_div();
                        bolEnvia = true;
                        document.forms[0].submit();
                        desativar_combos();

                    }

                // Limpa combo Unidade.
                }else
                {
                    while(document.forms[0].elements["unidadeOrganizacaoAtual"].options.length > 0)
                    {
                          document.forms[0].elements["unidadeOrganizacaoAtual"].options[0]     = null;
                    }

                    document.forms[0].unidadeOrganizacaoAtual.options[0] = new Option("Escolha uma opção...", "");
                }
            }

            function limpar()
            {
                var form  = document.forms[0];
                document.forms[0].target = '';

                form.action = '/FrontOfficeWeb/usuario/manterUsuario/editarUsuario/begin.do?begin.do';
                mostrar_div();
                form.submit();

            }

            function limpaForm()
            {
                document.forms[0].target = '';
                document.forms[0].action = 'limpaPesquisa.do';
                mostrar_div();

                document.forms[0].submit();

            }

            function carregaCombosOrgano()
            {
                var nivel = '<bean:write name="dadosAtuais" property="idNivel" />';
                var organizacao = '<bean:write name="dadosAtuais" property="idOrganizacao" />';
                var cargo = '<bean:write name="dadosAtuais" property="idCargo" />';
                var departamento = '<bean:write name="dadosAtuais" property="idUnidade" />';
                var form = document.forms[0];

                form.cargoAtual.value = cargo;
                form.nivelOrganogramaAtual.value = nivel;
                form.organizacaoAtual.value = organizacao;
                form.unidadeOrganizacaoAtual.value = departamento;

            }

        // funções para digitar somente letras, numeros e os caracteres "ponto,under line e traço"
        //JBernardes
        alphaNumeric_StrEspecias = new RegExp("[0-9a-zA-Z\._-]");
        alphaNumeric = new RegExp("[0-9a-zA-Z]");

        function checaCaracteresLogin(obj){
          valor = obj.value;
          for(i=0;i<valor.length;i++){
            if(!alphaNumeric_StrEspecias.test(valor.charAt(i))){
              valor = valor.substring(0,i) + valor.substring(i+1,valor.length);
              alert('Caracteres permitidos para pesquisa do Login são:\n de \"a\" até \"z\" maiúsculas ou minúsculas,números e os caracteres especiais ponto,underline e hífen(\".\","\_"\ e \"-\")');
              i = -1;
            }
          }
          obj.focus();
          obj.value = valor;
        }
        frmsState = null;
        function desativar_combos() {
            if (frmsState == null) {
                forms=document.forms;
                frmsState=new Array(forms.length);
                for (i=0;i<forms.length;i++) {
                    el=forms[i].elements;
                    elState=new Array(el.length);
                    frmsState[i]=elState;
                    for(j=0;j<el.length;j++){elState[j]=el[j].disabled;el[j].disabled=true;}
                }
            }
            return;
        }
        function ativar_combos() {
            if (frmsState != null) {
                forms=document.forms;
                for (i=0;i<forms.length;i++) {
                    el=forms[i].elements;
                    elState=frmsState[i];
                    frmsState[i]=elState;
                    for(j=0;j<el.length;j++){el[j].disabled=elState[j];}
                }
            }
            frmsState = null;
            return;
        }

        </script>

    </netui-template:section>

    <netui-template:section name="bodySection">

    <acesso:controlHiddenItem nomeIdentificador="usu_peus_verpagina">

        <!-- Menu de Gestao de Usuarios -->
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="3"></div>

        <vivo:quadro id="qdMain" height="478" width="790" label="Manutenção de Usuário > Pesquisar Usuário">
            <form action="pesquisaUsuarios.do" onSubmit="return false;" method="post">
                <html:hidden name="FormUser" property="paginaAtual" value="1"/>
                <div id="ttip" style="display:none;position:absolute;width:170px;"></div>
                <div style="font-weight:bold;padding:5px;">Digite os parâmetros de pesquisa para busca do usuário.</div>
                <table width="100%" cellspacing="1" cellpadding="3" align="center" class="tbl_bgGray">
                    <tr>
                        <td class="tblEstatica_campoNome" width="21%">Tipo Documento:</td>
                        <td align="left">
                            <html:select name="FormUser" property="tipoDocAtual" tabindex="1" style="width=70px;height=100px;margin-left:3px;" onchange="document.forms[0].dsDocAtual.value = '';mostraTipoDoc();mostraCampo();">
                                <html:option value="">Escolha...</html:option>
                                <html:options collection="aTipoDoc" property="sgTipoDocumento" labelProperty="sgTipoDocumento" />
                            </html:select>
                        </td>
                        <td class="tblEstatica_campoNome">
                            <div id="tipoDoc" style="visibility:'visible'; position:relative; top: 0px; padding: 0px;">
                            <logic:iterate name="listaSelects" property="tipoDocumentoVOArray" id="documento" >
                                <div id="<bean:write name="documento" property="sgTipoDocumento"/>" style="visibility:'hidden'; position:absolute; top: 0px; left: 0px; height: 0px; padding: 0px;">
                                  &nbsp;&nbsp;  <bean:write name="documento" property="sgTipoDocumento"/>
                                </div>
                            </logic:iterate>
                            </div>
                        </td>
                        <td align="left">
                            <div id="divCampo" style="visibility:'hidden';">
                                <html:text name="FormUser" tabindex="2" property="dsDocAtual" style="width:100px" styleClass="input" size="14" onkeyup="validaCampoDoc(this);" onkeypress="pesquisaCampo(window.event);"/>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="tblEstatica_campoNome">Nome:</td>
                        <td align="left">
                            <html:text name="usuarioPesquisa" tabindex="3" property="nome" style="width:210px" styleClass="input" maxlength="254" onkeypress="return pesquisaCampo(window.event);"/>
                        </td>
                        <td class="tblEstatica_campoNome">Nome do Meio:</td>
                        <td align="left">
                            <html:text name="usuarioPesquisa" tabindex="4" property="nomeMeio" style="width:210px" styleClass="input" maxlength="254" onkeypress="return pesquisaCampo(window.event);"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="tblEstatica_campoNome">Sobrenome:</td>
                        <td align="left" colspan="3">
                            <html:text name="usuarioPesquisa" tabindex="5" property="sobrenome" style="width:210px" styleClass="input" maxlength="254" onkeypress="return pesquisaCampo(window.event);"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="tblEstatica_campoNome">Login FrontOffice:</td>
                        <td align="left">
                            <html:text name="usuarioPesquisa" tabindex="6" property="login" style="width:100px" styleClass="input" size="14" maxlength="254" onkeypress="return pesquisaCampo(window.event);" onkeydown="checaCaracteresLogin(this)" onkeyup="checaCaracteresLogin(this)"/>
                        </td>
                        <td class="tblEstatica_campoNome">Login CTI:</td>
                        <td align="left">
                            <html:text name="usuarioPesquisa" tabindex="7" property="loginCti" style="width:100px" styleClass="input" size="14" maxlength="254" onkeypress="return pesquisaCampo(window.event);" onkeydown="checaCaracteresLogin(this)" onkeyup="checaCaracteresLogin(this)"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="tblEstatica_campoNome">Regional:</td>
                        <td align="left">
                            <html:select name="usuarioPesquisa" tabindex="8" property="idUFOperadora" style="width=160px;height=100px;margin-left:3px;" onmouseover="ativarToolTip(this,1)">
                                <html:option value="">Escolha uma opção...</html:option>
                                <html:options collection="aRegional" property="idUFOperadora" labelProperty="dsUFOperadora" />
                            </html:select>
                        </td>
                        <td class="tblEstatica_campoNome">Login Superior Imediato:</td>
                        <td align="left">
                            <html:text name="usuarioPesquisa" tabindex="9" property="loginChefe" style="width:100px" styleClass="input" size="14" maxlength="254" onclick="pesquisaCampo(window.event);"  onkeydown="checaCaracteresLogin(this)" onkeyup="checaCaracteresLogin(this)"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="tblEstatica_campoNome">Status:</td>
                        <td align="left">
                            <html:select name="usuarioPesquisa" tabindex="10" property="idStatusAtual" style="width=160px;height=100px;margin-left:3px;">
                                <html:option value="">Escolha uma opção...</html:option>
                                <html:options collection="aStatus" property="idStatus" labelProperty="nmStatus" />
                            </html:select>
                        </td>
                        <td class="tblEstatica_campoNome">Cargo:</td>
                        <td align="left">
                            <div id="divHFuncional">
                                <html:select name="FormUser" tabindex="12" property="cargoAtual" style="width=160px;height=70px;margin-left:3px;" onmouseover="ativarToolTip(this,1)">
                                    <html:option value="">Escolha uma opção...</html:option>
                                    <html:options collection="aCargo" property="idCargo" labelProperty="nmCargo" />
                                </html:select>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="tblEstatica_campoNome">Hierarquia Funcional:</td>
                        <td align="left">
                            <html:select name="FormUser" tabindex="11" property="nivelOrganogramaAtual" style="width=160px;height=100px;margin-left:3px;" onchange="carregaComboNivel();" onmouseover="ativarToolTip(this,1)">
                                <html:option value="">Escolha uma opção...</html:option>
                                <html:options collection="aNiveis" property="idNivel" labelProperty="dsNivel" />
                            </html:select>
                        </td>
                        <td class="tblEstatica_campoNome">Departamento:</td>
                        <td align="left">
                            <div id="divOrganizacao">
                                <html:select name="FormUser" tabindex="14" property="unidadeOrganizacaoAtual" style="width=160px;height=70px;margin-left:3px;" onmouseover="ativarToolTip(this,1)">
                                    <html:option value="">Escolha uma opção...</html:option>
                                    <html:options collection="aUnidades" property="idUnidade" labelProperty="nmUnidade" />
                                </html:select>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="tblEstatica_campoNome">Organização:</td>
                        <td align="left">
                            <html:select name="FormUser" tabindex="13" property="organizacaoAtual" style="width=160px;height=100px;margin-left:3px;" onchange="carregaComboOrganizacao();" onmouseover="ativarToolTip(this,1)">
                                <html:option value="">Escolha uma opção...</html:option>
                                <html:options collection="aOrganizacoes" property="idOrganizacao" labelProperty="dsTipoOrganizacao" />
                            </html:select>
                        </td>
                        <td class="tblEstatica_campoNome">Registros por Página:</td>
                        <td align="left">
                            <html:select name="FormUser" tabindex="15" property="registrosPPagina" style="width=100px;height=110px;margin-left:3px;">
                                <html:option value="0">Escolha...</html:option>
                                <html:option value="20">20 Registros</html:option>
                                <html:option value="30">30 Registros</html:option>
                                <html:option value="40">40 Registros</html:option>
                                <html:option value="50">50 Registros</html:option>
                            </html:select>
                        </td>
                    </tr>
                    <tr>
                        <td align="left">
                            <img align="letf" tabindex="16" id="voltar" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_voltar_over.gif'" onClick="window.location.href='/FrontOfficeWeb/index.jsp'" hspace="5" vspace="5" style="border:none;cursor:hand;"/>
                        </td>
                        <td align="right" colspan="3">
                            <acesso:controlHiddenItem nomeIdentificador="usu_peus_pesq">
                                <img id="btLimpar" style="border:0;" tabindex="17" onClick="limpaForm();" src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_limpar_over.gif'"/>
                                <img id="btPesquisar" tabindex="18" src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif'" style="border:none;cursor:hand;" onClick="return pesquisa();"/>
                            </acesso:controlHiddenItem>
                        </td>
                    </tr>
                </table>

                <script type="javascript">
                    var moveToolTip = true;
                    xBump = yBump=10;
                    MSIE = document.all;
                    NS6 = document.getElementById&&!document.all;
                    if(MSIE||NS6){
                        ttipObj=document.all?document.all["ttip"]:document.getElementById?document.getElementById("ttip"):"";
                    }
                    function carregaToolTip(content) {
                        ttipObj.innerHTML=content;
                        ttipObj.style.display='';
                    }
                    mostraTipoDoc();
                    mostraCampo();
                    oculta_div();
                    if('<bean:write name="FormUser" property="msgError" />' != ""){
                        alert('<bean:write name="FormUser" property="msgError" />');
                    }
                    document.forms[0].tipoDocAtual.focus();
                </script>

                <iframe style="width:0px;height:0px;" name="ifmOrganomagrama"></iframe>

            </form>
        </vivo:quadro>
    </acesso:controlHiddenItem>
</netui-template:section>
</netui-template:template>