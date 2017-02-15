<%@ page import="br.com.vivo.security.UserPrincipal"%>
<%@ page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%
    String idPessoaUsuario  = ConstantesCRM.SVAZIO;
    String nmLoginCTI       = ConstantesCRM.SVAZIO;
    boolean carregaOCX      = false;
    /*
    //verifica se carrega AtenaDummyOCX.CAB
    idPessoaUsuario = UserPrincipal.getUserID(request);
    
    if(session.getAttribute(ConstantesCRM.USUARIO_LOGIN_CTI) != null){
        nmLoginCTI = (String)session.getAttribute(ConstantesCRM.USUARIO_LOGIN_CTI);
        if(nmLoginCTI.equals("")){
            nmLoginCTI = null;
        }
    }
    
    if(nmLoginCTI!=null && idPessoaUsuario!=null)
    {
        carregaOCX = true;
    }
    
    //fake para não carregamento do CTI
    carregaOCX = false;
    */
%>
<html>
    <head>
        <script language="VBScript">
        
            sub CTIDummy_EvRecebimentoLigacao(GrupOrigCh, NumOrigCh, NumLinCliUra, CliAut, CliIdent, IndTitular, NavCliURA, GrupDest, TipProc, NumProc, ObsAtend, IndRedirTimeoutURA)
                 
                ' str = "Evento IncomingCall!" & vbCrLf & _
                '        "Grupo Origem=" & GrupOrigCh & vbCrLf & _
                '        "Numero Origem=" & NumOrigCh & vbCrLf & _
                '        "Numero URA=" & NumLinCliUra & vbCrLf & _
                '        "Cliente Autentificado=" & CliAut & vbCrLf & _
                '        "Cliente Identificado=" & CliIdent & vbCrLf & _
                '        "Titula=" & IndTitular & vbCrLf & _
                '        "Navegacao na URA=" & NavCliURA & vbCrLf & _
                '        "Grupo Destino=" & GrupDest & vbCrLf & _
                '    "Tipo Proceso=" & TipProc & vbCrLf & _
                '    "Numero Proceso=" & NumProc & vbCrLf & _
                '    "Observacoes=" & ObsAtend & vbCrLf & _
                '    "Timeout=" & IndRedirTimeoutURA & vbCrLf
            
                ' MsgBox str
                
                ' re-direciona para o javascript
                call receberChamada(GrupOrigCh, NumOrigCh, NumLinCliUra, CliAut, CliIdent, IndTitular, NavCliURA, GrupDest, TipProc, NumProc, ObsAtend, IndRedirTimeoutURA)
                
            end sub
            
            sub CTIDummy_EvMudancaEstadoLigacao() 
                call mudancaEstadoLigacao()
            end sub
            
            sub CTIDummy_EvMudancaEstadoAgente() 
                call mudancaEstadoAgente()
            end sub
            
            sub CTIDummy_EvMudancaCampanhas() 
                call mudancaCampanhas()
            end sub
            
            
        </script>
    
        <script language="JavaScript">
            <!--
                        
            //variaveis da ligação
            
            var numProc = '';
            var tipProc = '';
            var navCliURA = '';            
            
            //funçao para verificar se AtenaDummyOCX.CAB está carregado
            function isCarregadoOCX() {
                var carregado = '';
            <%if(carregaOCX){%>
                carregado = 'sim';
            <%}
            else{%>            
                carregado = 'nao';
            <%}%>            
                return (carregado.search('sim')>=0);
            }
            
            function login() {
            
            <%if(carregaOCX){%>
            // Usuário	Usuário a ser logado
            // Senha	Senha do usuário
            // Ramal	Ramal a ser logado
            // Integrationserver	Endereço do servidor integrationserver 
            // Assistedserver	Hostname e instância do Assisted Server
            // Site	Site ao qual deve ser logado.
                var usr = "<%=nmLoginCTI%>";
                var sen = "sen";
                var ram = "ram";
                var ins = "ins";
                var ass = "ass";
                var site = "site";
                var retorno;
              //  retorno = CTIDummy.Login(usr, sen, ram, ins, ass, site);
              //  alert(CTIDummy.DescErro(retorno));
                retorno = CTIDummy.Login(usr,'','','','','');
                
                if(retorno != 1) {
                    alert("Erro CTI: " + retorno + " = " + CTIDummy.DescErro(retorno));
                }
            <%}%>
           //     top.frameApp.location.href = "inicio.jsp";
            }
            
            function logout() {
                var retorno = CTIDummy.Logout();

                if(retorno == 1) {
                    top.window.close();
                }
                else {
                    alert("Erro CTI: " + retorno + " = " + CTIDummy.DescErro(retorno));
                }
            }
 
            function alterarEstado() {
                var retorno = CTIDummy.AlteraEstadoAgente();

                if(retorno != 1) {
                    alert("Erro CTI: " + retorno + " = " + CTIDummy.DescErro(retorno));
                }
            } 
            
            function alterarEstadoEspecifico(inDisponibilidade) {
                var estadoAgente = CTIDummy.ObtemEstadoAgente();
                var loginOk = estadoAgente.search('logado');
                var disponivelOk = estadoAgente.search('disponivel');

                if(loginOk >= 0) { 
                    if(disponivelOk >= 0) {
                        if(inDisponibilidade == 'N') {
                            alterarEstado();
                        }
                    }
                    else {
                        if(inDisponibilidade == 'S') {
                            alterarEstado();
                        }
                    }
                }
            }
            
            function isAgenteLogado() {
                var estadoAgente = CTIDummy.ObtemEstadoAgente();
                return (estadoAgente.search('logado') >= 0);
            }
            
            function isAgenteDisponivel() {
                var estadoAgente = CTIDummy.ObtemEstadoAgente();
                return (estadoAgente.search('disponivel') >= 0);
            }
            
            function obtemEstadoAgente() {
                return CTIDummy.ObtemEstadoAgente();
            } 
            
            function obtemEstadoLigacao() {
                return CTIDummy.ObtemEstadoLigacao();
            }
            
            function transferirLigacao(GrupOrigCh, NumOrigCh, NumLinCliUra, CliAut, CliIdent, IndTitular, NavCliURA, GrupDest, TipProc, NumProc, ObsAtend, IndRedirTimeoutURA) {
                
                var retorno = CTIDummy.TransferirLigacao(GrupOrigCh, NumOrigCh, NumLinCliUra, CliAut, CliIdent, IndTitular, NavCliURA, GrupDest, TipProc, NumProc, ObsAtend, IndRedirTimeoutURA);
                
                if(retorno != 1) {
                    alert("Erro CTI: " + retorno + " = " + CTIDummy.DescErro(retorno));
                }
                
                return retorno == 1;
            }
            
            function completarTransferencia () {
                var retorno = CTIDummy.CompletarTransferencia();
                
                if(retorno != 1) {
                    alert("Erro CTI: " + retorno + " = " + CTIDummy.DescErro(retorno));
                }
                
                return retorno == 1;
            }
            
            function recuperarLigacao () {
                var retorno = CTIDummy.RecuperarLigacao();
                
                if(retorno != 1) {
                    alert("Erro CTI: " + retorno + " = " + CTIDummy.DescErro(retorno));
                }
                
                return retorno == 1;
            }
            
            function obtemDescErro (codErro) {
                return CTIDummy.DescErro(codErro);
            }
            
            function abrirCampanha (campanha) {
                var retorno = CTIDummy.AbrirCampanha(campanha);

                if(retorno != 1) {
                    if(retorno != -7) //campanha já aberta
                    {
                        alert("Erro CTI: " + retorno + " = " + CTIDummy.DescErro(retorno));
                    }
                }
                
                return retorno == 1;
            }
            
            function fecharCampanha (campanha) {
                var retorno = CTIDummy.FecharCampanha(campanha);

                if(retorno != 1) {
                    if(retorno != -8) //com ligaçao
                    {
                        alert("Erro CTI: " + retorno + " = " + CTIDummy.DescErro(retorno));
                    }
                }
                
                return retorno == 1;
            }
            
            function obtemCampanhas () {
                var retorno = CTIDummy.ObtemCampanhas();

                return retorno;
            }
            
            function receberChamada(GrupOrigCh, NumOrigCh, NumLinCliUra, CliAut, CliIdent, IndTitular, NavCliURA, GrupDest, TipProc, NumProc, ObsAtend, IndRedirTimeoutURA) {
                
                try {
                    if(TipProc == 'Retenção') {
                        //top.frameApp.location = '<%=request.getContextPath()%>/fidelizacao/realizarRetencao.jsp';
                    }
                    if(TipProc == 'Campanhas') {
                        //top.frameApp.location = '<%=request.getContextPath()%>/fidelizacao/realizarRetencao.jsp';
                    }
                    if(TipProc == 'Retorno') {
                        //top.frameApp.location = '<%=request.getContextPath()%>/workflow/AtendimentoInBox/begin.do';
                    }
                    
                    //seta parâmetros da ligação em variáveis
                    numProc = NumProc;
                    tipProc = TipProc;
                    navCliURA = NavCliURA;
                    top.frameApp.receberChamada(GrupOrigCh, NumOrigCh, NumLinCliUra, CliAut, CliIdent, IndTitular, NavCliURA, GrupDest, TipProc, NumProc, ObsAtend, IndRedirTimeoutURA);
                }
                catch(e) {
                    alert('Não foi possível receber a ligação. A aplicaçao não está na página correta.');
                }

            }
            
            function mudancaEstadoLigacao() {
                
                try {
                    var estadoLigacao = CTIDummy.ObtemEstadoLigacao();
                    var semLigacao = estadoLigacao.search('Sem Ligação');
                    var comLigacao = estadoLigacao.search('Com Ligação');
                    var transferencia = estadoLigacao.search('Transferindo');

                    if(semLigacao >= 0) { 
                        top.frameApp.semLigacao();
                    }

                    if(comLigacao >= 0) { 
                        //top.frameApp.comLigacao();
                    }

                    if(transferencia >= 0) { 
                        //top.frameApp.transferindoLigacao();
                    }
                }
                catch(e) {
                    alert('Não foi possível alertar sobre a mudança no estado da ligação. A aplicaçao não está na página correta.');
                }

            }
            
            function mudancaEstadoAgente() {
            }
            
            function mudancaCampanhas() {
            }
            
            // -->
        </script>
    
        <title>AtenaDummyOCX.CAB</title>
    </head>
    <body>
    
        
    </body>
</html>

<script language="JavaScript">
    // efetua login no CTI
    login();
    
    
    
    var filtro = new Object();
    
    filtro.telaOrigem = 0; // 1=fila, 2=inbox, 3=fechamento massa
    
    filtro.grupoSel           = -1;
    filtro.usuarioSel         = document.createElement("<SELECT NAME='usuarioSel'>");
    filtro.usuarioSelValue    = -1;
    filtro.estadoSel          = -1;
    filtro.subEstadoSel       = -1;
    filtro.idAtendimento      = '';
    filtro.nrProtocolo        = '';
    filtro.optGrpSel1         = true;
    filtro.optGrpSel2         = false;
    filtro.dtAberturaInicio   = '';
    filtro.dtAberturaFim      = '';
    filtro.nrLinha            = '';
    filtro.dtFechamentoInicio = '';
    filtro.dtFechamentoFim    = '';
    filtro.textoContato       = '';
    filtro.idContato          = '';
    filtro.ultimaTab          = '';
    
    filtro.psqAv = null;
    
    filtro.inbox = new Object();
    filtro.inbox.grupoSel           = -1;
    filtro.inbox.estadoSel          = -1;
    filtro.inbox.subEstadoSel       = -1;
    filtro.inbox.idAtendimento      = '';
    filtro.inbox.optGrpSel1         = true;
    filtro.inbox.optGrpSel2         = false;
    filtro.inbox.dtAberturaInicio   = '';
    filtro.inbox.dtAberturaFim      = '';
    filtro.inbox.nrLinha            = '';
    filtro.inbox.dtFechamentoInicio = '';
    filtro.inbox.dtFechamentoFim    = '';
    filtro.inbox.textoContato       = '';
    filtro.inbox.idContato          = '';
    filtro.inbox.ultimaTab          = 0;


    filtro.massa = new Object();
    filtro.massa.login              = '';
    filtro.massa.textoContato       = '';
    filtro.massa.idContato          = '';
    filtro.massa.dtSuspeitoInicio   = '';
    filtro.massa.dtSuspeitoFim      = '';    

    
    // altera a disponibilidade do usuário para disponível, caso não esteja.
    //alterarEstadoEspecifico('S');
</script>
