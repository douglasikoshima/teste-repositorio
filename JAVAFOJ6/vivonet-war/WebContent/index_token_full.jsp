<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/xml2json.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/formatPhoneNumber.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/vivo_360.css" />
    <script type="text/javascript">
        onload = function() {
            $('mascara').hide();
            $('nrLinha').focus();
        }

        gerarToken = function() {
            if ($F('nrLinha').blank() || $F('nrLinha').length != 14) {
                $('nrLinha').focus();
                $('nrLinha').select();
                alert('Por favor, digite um número válido de linha.');
            } else if ($F('dsLogin').blank()) {
                $('dsLogin').focus();
                $('dsLogin').select();
                alert('Por favor, digite um login de usuário.');
            } else {

                var params = $F('queryParameters').toQueryParams();
                params.nrLinha = $F('nrLinha');
                params.dsLogin = $F('dsLogin');

                params = $H(params);
                var allowed = true;
                params.each(function(pair) {
                    if (pair.key != 'nrLinha' && pair.key != 'dsLogin') {
                        if (pair.key.substring(0,2) != "x-") {
                            allowed = false;
                            throw $break;
                        }
                    }
                });

                if (!allowed) {
                    alert("Variáveis enviadas ao autenticador devem começar com 'x-'.");
                    return;
                }

                new Ajax.Request('<%=request.getContextPath()%>/generateToken.do?' + params.toQueryString(), {
                    method: 'get',
                    params: params,
                    onSuccess: function(e) {
                        if (e.responseText != '') {

                            var dom = parseXml(e.responseText);
                            var jsonString = xml2json(dom, '');
                            var jsonObj = jsonString.evalJSON();

                            $('mascara').hide();
                            $('retorno').update(jsonObj.xmlOUT);

                        }
                    }, onCreate: function() {
                        $('mascara').show();
                    }, onComplete: function() {
                        $('mascara').hide();
                        if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                    }, onException: function(e) {
                    }, on406: function(e) {
                        window.top.frameApp.createErrorPopUp('erroAlterarNumeroSMS', e.statusText, e.responseText, false);
                        if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                    }
                });
            }
        }
    </script>
    <style type="text/css">
        div.formdiv {
            display:block;
            clear:both;
            background:#fff;
            padding:3px;
            text-align:left;
            width:340px;
        }
        div.formdiv label {
            width:120px;
            line-height:20px;
            display:block;
            float:left;
        }
        #dsLogin {
            margin-right:10px;
            width:87px;
        }
        #nrLinha {
            width:87px;
        }
        #retorno {
            margin-top:30px;
            text-align:center;
            width:100%;
            font-size:13px;
            color:#ff9900;
            background:#fff;
            font-weight:bold;
        }
    </style>
</head>
<body id="vivo_360">
<div id="vivo306_header">
    <div id="vivo306_header_logo">
        <img src="<%=request.getContextPath()%>/resources/images/vivo360_logo.gif" alt="Vivo 360" width="119" height="47" />
    </div>
    <div id="vivo306_header_login_info">
    </div>
    <div id="vivo306_header_sair">
        <img src="<%=request.getContextPath()%>/resources/images/vivo360_top_sair.gif" alt="Vivo 360" width="42" height="22" />
    </div>
</div>
<div id="vivo306_body">
    <h1>Geração de Token</h1>
    <div class="basic" id="list1a">
        <a id="filtros" class="table_title">Digite o número da linha para geração de Token</a>
        <div class="item table_body tab_content_body" style="height:450px;">
            <div style="background:#fff;text-align:center;width:780px;margin-top:220px;font-size:13px;">
                <div class="formdiv">
                    <label for="nrLinha">Número do telefone: </label>
                    <input type="text"
                           id="nrLinha"
                           name="nrLinha"
                           onkeyup="maskPhoneNumberObj(this)"
                           maxlength="14" />
                </div>
                <div class="formdiv">
                    <label for="nrLinha">Login do usuário: </label>
                    <input type="text"
                           id="dsLogin"
                           name="dsLogin" />
                </div>
                <div class="formdiv">
                    <label for="nrLinha">Query parameters: </label>
                    <input type="text"
                           id="queryParameters"
                           name="queryParameters"
                           style="width:200px" />
                    <input type="button" class="input_small" value="Gerar Token"  style="margin-top:10px" onmouseup="gerarToken()" />
                </div>

                <div id="retorno"></div>
            </div>
        </div>
    </div>
</div>
<div id="mascara"></div>
</body>
</html>