<html>
  <head>
     <title>Vivo Net - Erro</title>
     <script language="javascript">
        var detailsEnabled = true;
        var detailsHidden = true;
        var widthWide;
        var heightTall;
        var widthNarrow;
        var heightSmall;

        function fillInObjects() {
            var windowArguments = parent.window.dialogArguments;
            widthWide   = windowArguments.widthWide;
            heightTall  = windowArguments.heightTall;
            widthNarrow = windowArguments.widthNarrow;
            heightSmall = windowArguments.heightSmall;
            context = windowArguments.context;
            detailsEnabled = windowArguments.detailsEnabled;
            var buttonDetalhes = document.getElementById('buttonDetalhes');
            if(detailsEnabled){
                buttonDetalhes.src = context + '/resources/images/bt_detalhes_nrml.gif';
            } else {
                buttonDetalhes.parentNode.removeChild(buttonDetalhes);
            }
            document.getElementById('buttonFechar').src   = context + '/resources/images/bt_fechar_nrml.gif';
            document.getElementById('elemErrorIcon').src  = context + "/resources/images/" + windowArguments.errorIcon;
            document.getElementById('elemErrorCode').value        = windowArguments.errorCode;
            document.getElementById('elemErrorMessage').innerText = windowArguments.errorMessage;
            document.getElementById('elemErrorStack').value       = windowArguments.errorStack;
        }

        function closeWindow() {
            var win = parent.window.dialogArguments.window1;
            if(win.parent.parent.window.top.frameApp.idAnime)
                if (win.parent.parent.window.top.frameApp.idAnime.style.display!='none')
                    win.parent.parent.window.top.frameApp.idAnime.style.display ='none';
            window.close();
        }

        function toggleStackTrace() {
            var width;
            var height;
            if(detailsHidden){
                mostrarerro.style.display = '';
                width  = widthWide;
                height = heightTall;
            }else{
                mostrarerro.style.display = 'none';
                width  = widthNarrow;
                height = heightSmall;
            }
            var top  = screen.availHeight/2 - height/2;
            var left = screen.availWidth/2  - width/2;
            var win = window.external;
            win.dialogWidth  = width+'px';
            win.dialogHeight = height+'px';
            win.dialogTop    = top+'px';
            win.dialogLeft   = left+'px';
            document.getElementById('tableFrame').width = width-6+'px';
            var new_width = width-6-document.getElementById('iconAndCode').width + 'px';
            document.getElementById('errorMessageCell').width  = new_width;
            document.getElementById('errorMessageLabel').width = new_width;
            detailsHidden = !detailsHidden;
            //win.autoAjustScreen();
        }
    </script>
  </head>
  <body>
    <table id="tableFrame" width='444' height='10' border='0' cellpadding='0' cellspacing='0' bgcolor='#E3ECF4'>
        <tr>
            <td id="iconAndCode" width="80px" align="left" style="font:10pt">
                <table width='100%' border='0' cellpadding='0' cellspacing='0'>
                    <tr>
                        <td align="center" height='56'>
                            <img id='elemErrorIcon' align="middle" src='<%=request.getContextPath()%>/resources/images/error.png'/>
                        </td>
                    </tr>
                    <tr>
                        <td height='20' align="center" style="font:10pt;font-family:Tahoma,Arial,Helvetica,sans-serif;">
                            <span id='elemErrorCode'>0000</span>
                        </td>
                    </tr>
                </table>
            </td>
            <td id="errorMessageCell" align="left" style="font:9pt;width:360px;height:86px;">
                <LABEL id="errorMessageLabel" style="font:9pt;width:360px;height:68px;overflow:hidden;text-overflow:ellipsis;font-family:Tahoma,Arial,Helvetica,sans-serif;">
                    <span id='elemErrorMessage'>Message not available</span>
                </LABEL>
            </td>
        </tr>
        <tr>
            <td colspan="2" height="20"></td>
        </tr>
        <tr>
            <td align="left"> <img id='buttonDetalhes' hspace='6' style="border:0px;" onclick="toggleStackTrace();" src=''></td>
            <td align="right"><img id='buttonFechar'   hspace='6' style="border:0px;" onClick="closeWindow();"      src=''/></td>
        </tr>
        <tr>
            <td colspan="2" height="6"></td>
        </tr>
        <tr>
            <td colspan="2" align="left">
                <table id="mostrarerro" style="display:none" width='100%' border='0' cellpadding='0' cellspacing='0'>
                    <tr>
                        <td align="left">
                            <textarea id='elemErrorStack' wrap="off" style="font:9pt;width:594;height:200px;overflow:scroll" readonly>
                                Stack trace not available
                            </textarea>
                        </td>
                    </tr>
                    <tr>
                        <td height="6"></td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
    <script>
        fillInObjects();
    </script>
    </body>
</html>