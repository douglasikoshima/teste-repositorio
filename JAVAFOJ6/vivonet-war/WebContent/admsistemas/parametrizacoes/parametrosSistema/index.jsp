<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">

    <netui-template:setAttribute name="title" value="Manter Sistema"/>
    <netui-template:setAttribute name="modulo" value="Administração de Sistemas"/>

    <netui-template:section name="headerSection">

        <script type="text/javascript">
            var letraSolicitada = '';
            onload = function() {
                $('cdParametro').focus();
                <% if (request.getAttribute("letraSolicitada") != null) { %>
                submitBusca('<%=request.getAttribute("letraSolicitada")%>');
                <% } else { %>
                if (top.frameApp.dvAnimarAguarde) top.frameApp.stopAnimation();
                <% } %>
            }
            submitBusca = function(letra) {
                letraSolicitada = letra;
                var params = $H();
                params.set('letraInicial', letraSolicitada);
                new Ajax.Updater('div-pesquisa', 'buscarParametros.do', {
                    method: 'get',
                    parameters: params,
                    evalScripts: true,
                    onCreate: function() {
                        if (top.frameApp.dvAnimarAguarde) top.frameApp.startAnimation();
                    }, onSuccess: function() {
                        if (top.frameApp.dvAnimarAguarde) top.frameApp.stopAnimation();
                    }, onComplete: function() {
                        if (top.frameApp.dvAnimarAguarde) top.frameApp.stopAnimation();
                    }, onFailure: function(e) {
                        /*if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                        var stackTrace = e.getHeader("stackTrace") ? e.getHeader("stackTrace") : "";
                        createErrorPopUp('erroPortabilidade', e.statusText, stackTrace, false);*/
                    }

                });
            }
            editItem = function(obj, idParametro) {
                obj.id = (obj.id == '' || obj.id == 'null') ? guid() : obj.id;
                var o = $(obj.id);
                var cdParametro = o.childElements()[0].innerText;
                var dsParametro = o.childElements()[1].innerText;
                var vlParametro = o.childElements()[2].innerText;
                $('idParametro').value = idParametro;
                $('cdParametro').value = cdParametro.strip();
                $('dsParametro').value = dsParametro.strip();
                $('vlParametro').value = vlParametro.strip();
            }
            novoParametro = function() {
                $('idParametro').value = '0';
                $('cdParametro').value = '';
                $('dsParametro').value = '';
                $('vlParametro').value = '';
                $('cdParametro').focus();
            }
            gravarParametro = function() {
                if ($F('cdParametro').blank()) {
                    alert('Por favor, digite o código do parâmetro.');
                } else if ($F('dsParametro').blank()) {
                    alert('Por favor, digite a descrição do parâmetro.');
                } else if ($F('vlParametro').blank()) {
                    alert('Por favor, digite o valor do parâmetro.');
                } else {
                    if (top.frameApp.dvAnimarAguarde) top.frameApp.startAnimation();
                    document.forms[0].action = 'salvarParametro.do?letraSolicitada=' + letraSolicitada;
                    document.forms[0].submit();
                }
            }
            guid = function() {return (S4()+S4()+"-"+S4()+"-"+S4()+"-"+S4()+"-"+S4()+S4()+S4());}
            S4 = function() {return (((1+Math.random())*0x10000)|0).toString(16).substring(1);}
        </script>
        <style type="text/css">
            #table-parametros {
                border: 1px solid #c7daea;
                width: 100%;
                margin-top: 3px;
            }
            #table-parametros thead td {
                border-bottom: 1px solid #c7daea;
                padding: 2px 3px 2px 3px;
                background: #c7daea;
                color: #000;
            }
            #table-parametros tbody td {
                padding: 0px;
                margin: 0;
                border-right: 1px solid #c7daea;
                text-align: center;
            }
            #table-parametros a,
            #table-parametros a:active,
            #table-parametros a:visited {
                width: 100%;
                text-decoration: none;
                padding: 2px 0 2px 0;
                color: #0066cc;
            }
            #table-parametros a:hover {
                background: #cccccc;
                color: #000000;
            }
            #table-parametros tbody td.last {
                border-right: none;
            }
            #div-pesquisa {
                border: 1px solid #c7daea;
                margin-top: 5px;
                height: 330px;
                padding: 5px;
            }
            #div-form {
                margin: 5px 0 5px 0;
            }
            #div-form .input-line {
                display: block;
                width: 100%;
                margin-bottom: 5px;
            }
            #div-form form {
                margin: 0;
                padding: 0;
            }
            #div-form form button {
                margin: 0;
                padding: 0;
                width: 61px;
                height: 13px;
                border: none;
                background: none;
            }
            #div-form form label {
                float: left;
                width: 120px;
            }
            #div-form input {
                clear: none;
                display: inline;
                float: left;
                width: 500px;
            }
        </style>

    </netui-template:section>

    <netui-template:section name="bodySection">

        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />

        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>

        <vivo:sessao id="qdMain" height="470" width="790" label="Sistema" operacoes="Manutenção de Parâmetros" scroll="no">

            <table id="table-parametros" cellpadding="0" cellspacing="0">
                <thead>
                    <tr>
                        <td colspan="27">
                            <span onmouseup="novoParametro()" style="cursor:pointer;font-weight:bold;text-decoration:underline">Crie um novo parâmetro</span> ou selecione uma letra inicial para listar e clique na linha desejada para alterar os dados do parâmetro.
                        </td>
                    </tr>
                </thead>
                <tr>
                    <td><a href="javascript:submitBusca('#')">#</a></td>
                    <td><a href="javascript:submitBusca('A')">A</a></td>
                    <td><a href="javascript:submitBusca('B')">B</a></td>
                    <td><a href="javascript:submitBusca('C')">C</a></td>
                    <td><a href="javascript:submitBusca('D')">D</a></td>
                    <td><a href="javascript:submitBusca('E')">E</a></td>
                    <td><a href="javascript:submitBusca('F')">F</a></td>
                    <td><a href="javascript:submitBusca('G')">G</a></td>
                    <td><a href="javascript:submitBusca('H')">H</a></td>
                    <td><a href="javascript:submitBusca('I')">I</a></td>
                    <td><a href="javascript:submitBusca('J')">J</a></td>
                    <td><a href="javascript:submitBusca('K')">K</a></td>
                    <td><a href="javascript:submitBusca('L')">L</a></td>
                    <td><a href="javascript:submitBusca('M')">M</a></td>
                    <td><a href="javascript:submitBusca('N')">N</a></td>
                    <td><a href="javascript:submitBusca('O')">O</a></td>
                    <td><a href="javascript:submitBusca('P')">P</a></td>
                    <td><a href="javascript:submitBusca('Q')">Q</a></td>
                    <td><a href="javascript:submitBusca('R')">R</a></td>
                    <td><a href="javascript:submitBusca('S')">S</a></td>
                    <td><a href="javascript:submitBusca('T')">T</a></td>
                    <td><a href="javascript:submitBusca('U')">U</a></td>
                    <td><a href="javascript:submitBusca('V')">V</a></td>
                    <td><a href="javascript:submitBusca('W')">W</a></td>
                    <td><a href="javascript:submitBusca('Y')">Y</a></td>
                    <td><a href="javascript:submitBusca('X')">X</a></td>
                    <td class="last"><a href="javascript:submitBusca('Z')">Z</a></td>
                </tr>
            </table>
            <div id="div-pesquisa"></div>
            <div id="div-form">
                <form method="post" action="salvarParametro.do">
                    <input type="hidden" id="idParametro" name="parametro.idParametro" />
                    <div class="input-line">
                        <label for="cdParametro">Código do Parâmetro</label>
                        <input type="text" id="cdParametro" name="parametro.cdParametro" maxlength="255" />
                    </div>
                    <div class="input-line">
                        <label for="dsParametro">Descrição do Parâmetro</label>
                        <input type="text" id="dsParametro" name="parametro.dsParametro" maxlength="255" />
                    </div>
                    <div class="input-line">
                        <label for="vlParametro">Valor do Parâmetro</label>
                        <input type="text" id="vlParametro" name="parametro.dsValorParametro" maxlength="255" />
                    </div>
                    <button type="submit" onmouseup="gravarParametro()"><img src="<%=request.getContextPath()%>/resources/images/bt_gravar_nrml.gif" /></button>
                </form>
            </div>
        </vivo:sessao>
        <vivo:alert atributo="msgRetorno" scope="request" />
    </netui-template:section>
</netui-template:template>