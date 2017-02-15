<%@page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<html>
    <head>
        <title>Vivo Net - Sobre</title>
        <style type="text/css">
            #content { background:#e4ebf5;}
            #contentMsg {
                width:100%;
                overflow:auto;
                padding-left:15px;
                margin-top:-20px;
                height:160px;
            }
            * html {
                background:#e4ebf5;
                font-family:Tahoma;
                font-size:11px;
                color:#000;
                height:50px;
                overflow:auto;
            }
            #holder {width:480px;}
            h1 { padding-left:10px; padding-bottom:10px; }

            dt {
                width:100px;
                font-weight:bold;
                display:inline;
                font-size:11px;
                margin-top:-18px;
                margin-right:15px;
            }
    
            dd {
                font-weight:normal;
                font-size:11px;
                height:18px;
                max-height:18px;
                margin:0;
                width:280px;
                overflow:hidden;
                text-overflow:ellipsis;
                white-space:nowrap;
                display:inline;
            }
        </style>
    </head>
    <body style="margin:none;">
            <div id="content">
                <h1>
                    <img src="<%=request.getContextPath()%>/resources/images/logo_vivonet_about.gif" />
                </h1>
                <div id="contentMsg">
                    <dl>
                        <dt>Vers&atilde;o</dt>
                        <dd><%=request.getAttribute("version")%></dd>

                        <dt>Data</dt>
                        <dd><%=request.getAttribute("date")%></dd>

                        <dt>Login</dt>
                        <dd><%=request.getAttribute(ConstantesCRM.USUARIO_LOGIN)%></dd>

                        <dt>Usu&aacute;rio</dt>
                        <dd><%=request.getAttribute(ConstantesCRM.USUARIO_NOME)%></dd>

                        <dt>ID do usu&aacute;rio</dt>
                        <dd><%=request.getAttribute("usuarioIdSession")%></dd>
                    </dl>
                </div>
            </div>
    </body>
</html>