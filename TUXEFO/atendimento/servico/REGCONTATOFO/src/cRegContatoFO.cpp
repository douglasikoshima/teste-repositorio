/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @remark  Esta API é usada para gerar palitagens através de chamada assíncrona ao REGCONTATO
 *          Ela pode ser usada como api de geração de palitagens no FO por módulos que não possuam
 *          meios de obter toda a informação necessária para que o REGCONTATO grave dados  
 *          sua tarefa.
 * @author  Cassio Garcia
 * @version $Revision: 1.1.114.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/06 12:36:44 $
 **/

#define ASSINC // faz chamada assíncrona ao serviço
//#define SINC // faz chamada síncrona ao serviço
//#define SINC2 // faz chamada síncrona ao serviço

#include "../include/cRegContatoFO.h"
#include "../include/cRCFOException.h"

void cRegContatoFO::Executar()
{
    ULOG_START("cRegContatoFO::Executar()");

    dadosRC.idPessoaDeParaCliente = 0;
    dadosRC.idPessoaDeParaUsuario = 0;

    CarregarDados();

    // Se tag <cdContato> foi fornecida, então o idContato a ser usado será o
    // que foi parametrizado.
    if ( dados.cdContato )
    {
        if ( *dados.cdContato )
        {
            ObterIdContato();
        }
    }

    ValidarDadosEntrada();

    if ( pesquisarDadosLinha )
    {
        if ( strcmp(rcfoProC.getIDTipoRelacionamento(),"1") == 0 )
        { // Se tipo de relacionamento = USUARIO
            ObterDadosParaUsuario();
            ObterDadosParaCliente();
        }
        else if ( strcmp(rcfoProC.getIDTipoRelacionamento(),"2") == 0 )
        { // Se tipo de relacionamento = CLIENTE
            if ( ObterDadosParaCliente() == false )
            {
                ULOG_END("cRegContatoFO::Executar()");
                throw new RCFOException(ERR_PRC_OBT_DADOSLINHA_CLI,__FILE__,__LINE__);
            }
        }
        else
        {
            ObterDadosParaClienteUsuario();
        }
    }
    else
    { // SM410 - SimLock, caso o numero da linha não tenha sido fornecido obtém os dados
      // para envio ao REGCONTATO através do idPessoa.

        ObterDadosParaNaoCliente();
    }

    dadosRC.idContato = atoi(dados.idContato);
    dadosRC.idProcedencia = atoi(dados.idProcedencia);
    dadosRC.idCanal = atoi(dados.idCanal);
    dadosRC.tpOperacao = atoi(dados.tpOperacao);
    dadosRC.idGrupoAbertura = atoi(dados.idGrupoAbertura);

    strcpy(dadosRC.dtJanelaPortout,dados.dtJanelaPortout);
    strcpy(dadosRC.sgTipoPortabilidade,dados.tipoPortabilidade);
    strcpy(dadosRC.sgOperadoraSolicitante,dados.sgOperadoraSolicitante);
    strcpy(dadosRC.nrProtocoloPortabilidade,dados.nrProtocoloPortabilidade);
    strcpy(dadosRC.nrTelefone,rcfoProC.getDDD());
    strcat(dadosRC.nrTelefone,rcfoProC.getnrTelefone());

    cRegContato cregcontato;

    cregcontato.EntradaValidacao(&dadosRC);

    pXMLGen.createTag("AtendimentoVO");
        pXMLGen.addProp("xmlns","workflow.fo.vivo.com.br/vo");
        pXMLGen.addItem("idGrupoAbertura",dados.idGrupoAbertura);
        pXMLGen.addItem("idLinhaAtendimento",rcfoProC.getIDLinhaTelefonica());
        pXMLGen.addItem("inResponsavelAbertura",dadosRC.idTipoRelacionamento);
        pXMLGen.addItem("nmContato",rcfoProC.getnmPessoa());
        pXMLGen.addItem("naoValidarDados","true");
        pXMLGen.addItem("nmFalandoCom","");
        pXMLGen.addItem("nrTelefone",dadosRC.nrTelefone);
        pXMLGen.addItem("observacao",dados.observacao);
        pXMLGen.addItem("tpOperacao",dados.tpOperacao);
        pXMLGen.addItem("sgOperadoraSolicitante",dados.sgOperadoraSolicitante);
        pXMLGen.addItem("dtJanelaPortout",dados.dtJanelaPortout);
        pXMLGen.addItem("tipoPortabilidade",dados.tipoPortabilidade);
        pXMLGen.createTag("ProcedenciaVO");
            pXMLGen.addProp("xmlns","admsistemas.fo.vivo.com.br/vo");
            pXMLGen.addItem("idProcedencia",dados.idProcedencia);
        pXMLGen.closeTag();
        pXMLGen.createTag("CanalVO");
            pXMLGen.addProp("xmlns","workflow.fo.vivo.com.br/vo");
            pXMLGen.addItem("idCanal",dados.idCanal);
        pXMLGen.closeTag();
        pXMLGen.createTag("Contas");
            pXMLGen.addProp("xmlns","workflow.fo.vivo.com.br/vo");
            pXMLGen.createTag("ContasVO");
                pXMLGen.addItem("idConta",rcfoProC.getIDConta());
                pXMLGen.createTag("LinhaVO");
                    // O REGCONTATO esta esperando o valor de IDLINHATELEFONICA na tag
                    // <idPessoaLinhaHistorico> e por isso o REGCONTATOFO passa o valor
                    // deste campo nesta tag apesar de parecer errado. Nov/2006 - Cassio
                    pXMLGen.addItem("idPessoaLinhaHistorico",rcfoProC.getIDLinhaTelefonica());
                    //pXMLGen.addItem("idPessoaLinhaHistorico",rcfoProC.getIDPessoaLinhaHistorico());
                pXMLGen.closeTag();
            pXMLGen.closeTag();
        pXMLGen.closeTag();
        pXMLGen.createTag("PessoaVO");
            pXMLGen.addProp("xmlns","workflow.fo.vivo.com.br/vo");
            // O REGCONTATO esta esperando o valor de IDPESSOADEPARA na tag
            // <idPessoa> e por isso o REGCONTATOFO passa o valor
            // deste campo nesta tag apesar de parecer errado. Nov/2006 - Cassio
            pXMLGen.addItem("idPessoa",rcfoProC.getIDPessoaDeParaCliente());
        pXMLGen.closeTag();
        pXMLGen.createTag("UsuarioLinhaVO");
            pXMLGen.addProp("xmlns","cliente.fo.vivo.com.br/vo");
            pXMLGen.addItem("idPessoa",rcfoProC.getIDPessoaDeParaUsuario());
        pXMLGen.closeTag();
        pXMLGen.createTag("ArvoreAtendimentoVO");
            pXMLGen.addProp("xmlns","admsistemas.fo.vivo.com.br/vo");
            pXMLGen.addItem("idContato",dados.idContato);
            pXMLGen.createTag("CarterizacaoVO");
                pXMLGen.addItem("idTipoCarteira",rcfoProC.getIDTipoCarteira());
            pXMLGen.closeTag();
            pXMLGen.createTag("SegmentacaoVO");
                pXMLGen.addItem("idSegmentacao",rcfoProC.getIDSegmentacao());
            pXMLGen.closeTag();
        pXMLGen.closeTag();
    pXMLGen.closeTag();

    RegistrarContato();

    ULOG_END("cRegContatoFO::Executar()");
}

bool cRegContatoFO::ObterDadosParaUsuario()
{
    ULOG_START("cRegContatoFO::ObterDadosParaUsuario()");

    bool retorno = rcfoProC.ObterDadosLinPessoaUsuario(rcfoProC.getDDD()
                                                      ,rcfoProC.getnrTelefone());
    ULOG("retorno=%d",retorno);

    // Faz a validação de dados padrão do REGCONTATO antes de prosseguir
    if ( retorno )
    { // Se encontrou dados de usuário envia dados de cliente ao REGCONTATO(A)
        rcfoProC.ObterIDConta(rcfoProC.getIDLinhaTelefonicaUsuario()
                             ,rcfoProC.getIDTipoRelacionamentoUsuario());

        //rcfoProC.ObterIDPessoaLinhaHistorico(rcfoProC.getIDPessoaDeParaUsuario()
        //                                    ,rcfoProC.getIDLinhaTelefonicaUsuario()
        //                                    ,rcfoProC.getIDTipoRelacionamentoUsuario());

        dadosRC.idSegmentacao = atoi(rcfoProC.getIDSegmentacaoUsuario());
        dadosRC.idTipoCarteira = rcfoProC.getIDTipoCarteiraCliente() > 0 ? 
                        atoi(rcfoProC.getIDTipoCarteiraCliente()):TPC_NAO_CLASSIFICADO;
        dadosRC.idConta = atoi(rcfoProC.getIDConta());
        dadosRC.idLinhaTelefonica = atoi(rcfoProC.getIDLinhaTelefonicaUsuario());
        dadosRC.idTipoRelacionamento = atoi(rcfoProC.getIDTipoRelacionamentoUsuario());
    }

    dadosRC.idPessoaDeParaUsuario = atoi(rcfoProC.getIDPessoaDeParaUsuario());

    ULOG_END("cRegContatoFO::ObterDadosParaUsuario()");

    return retorno;
}

bool cRegContatoFO::ObterDadosParaCliente()
{
    ULOG_START("cRegContatoFO::ObterDadosParaCliente()");

    bool retorno = rcfoProC.ObterDadosLinPessoaCliente(rcfoProC.getDDD()
                                                      ,rcfoProC.getnrTelefone());
    ULOG("retorno=%d",retorno);

    // Faz a validação de dados padrão do REGCONTATO antes de prosseguir
    if ( retorno )
    { // Se encontrou dados de cliente envia dados de cliente ao REGCONTATO(A)
        rcfoProC.ObterIDConta(rcfoProC.getIDLinhaTelefonicaCliente()
                             ,rcfoProC.getIDTipoRelacionamentoCliente());

        //rcfoProC.ObterIDPessoaLinhaHistorico(rcfoProC.getIDPessoaDeParaCliente()
        //                                    ,rcfoProC.getIDLinhaTelefonicaCliente()
        //                                    ,rcfoProC.getIDTipoRelacionamentoCliente());

        dadosRC.idSegmentacao = atoi(rcfoProC.getIDSegmentacaoCliente());
        dadosRC.idTipoCarteira = atoi(rcfoProC.getIDTipoCarteiraCliente()) > 0 ? 
                        atoi(rcfoProC.getIDTipoCarteiraCliente()):TPC_NAO_CLASSIFICADO;
        dadosRC.idConta = atoi(rcfoProC.getIDConta());
        dadosRC.idLinhaTelefonica = atoi(rcfoProC.getIDLinhaTelefonicaCliente());
        dadosRC.idTipoRelacionamento = atoi(rcfoProC.getIDTipoRelacionamentoCliente());
    }

    dadosRC.idPessoaDeParaCliente = atoi(rcfoProC.getIDPessoaDeParaCliente());

    ULOG_END("cRegContatoFO::ObterDadosParaCliente()");

    return retorno;
}

void cRegContatoFO::ObterDadosParaClienteUsuario()
{
    ULOG_START("cRegContatoFO::ObterDadosParaClienteUsuario()");

    bool retCliente = ObterDadosParaCliente();
    bool retUsuario = ObterDadosParaUsuario();

    if ( !retCliente && !retUsuario )
    {
        ULOG_END("cRegContatoFO::ObterDadosParaClienteUsuario()");
        throw new RCFOException(ERR_PRC_OBT_DADOSLINHA,__FILE__,__LINE__);
    }

    ULOG_END("cRegContatoFO::ObterDadosParaClienteUsuario()");
}

void cRegContatoFO::ObterDadosParaNaoCliente()
{
    ULOG_START("cRegContatoFO::ObterDadosParaNaoCliente()");

    rcfoProC.ObterDadosPessoa(rcfoProC.getIDPessoa());

    dados.ddd = rcfoProC.getDDD();
    dados.nrLinha = rcfoProC.getnrTelefone();

    dadosRC.idSegmentacao = atoi(rcfoProC.getIDSegmentacao());
    dadosRC.idTipoCarteira = rcfoProC.getIDTipoCarteira() > 0 ? atoi(rcfoProC.getIDTipoCarteira()):TPC_NAO_CLASSIFICADO;
    dadosRC.idPessoaDeParaCliente = atoi(rcfoProC.getIDPessoaDePara());
    dadosRC.idPessoaDeParaUsuario = atoi(rcfoProC.getIDPessoaDePara());
    dadosRC.idConta = atoi(rcfoProC.getIDConta());
    dadosRC.idLinhaTelefonica = atoi(rcfoProC.getIDLinhaTelefonica());
    dadosRC.idTipoRelacionamento = atoi(rcfoProC.getIDTipoRelacionamento());

    ULOG_END("cRegContatoFO::ObterDadosParaNaoCliente()");
}

void cRegContatoFO::ObterIdContato()
{	
    ULOG_START("cRegContatoFO::ObterIdContato()");

    rcfoProC.ObterIdContato(dados.cdContato,&dados.idContato);
	
    ULOG_END("cRegContatoFO::ObterIdContato()");
}

void cRegContatoFO::ValidarDadosEntrada()
{
    ULOG_START("cRegContatoFO::ValidarDadosEntrada()");

    char *p = (char*)codUsuario.c_str();

    while ( p && *p )
    {
        if ( !isdigit(*p) )
        {
            throw new RCFOException(ERR_CODUSU_ALFA,__FILE__,__LINE__);
        }

        p++;
    }

    if ( strcmp(dados.tpOperacao,"1") && strcmp(dados.tpOperacao,"2") )
    {
        ULOGW("tpOperacao=%s",dados.tpOperacao);
        throw new RCFOException(ERR_TPOPERACAO_INVALIDO,__FILE__,__LINE__);
    }

    if ( pesquisarDadosLinha )
    { // Se informou ddd+telefone, verifica se é um número coerente
        if ( strlen(dados.ddd) > 2 )
        {
            ULOGW("ddd=%s",dados.ddd);
            throw new RCFOException(ERR_DDD_TOOLONG,__FILE__,__LINE__);
        }

        if ( strlen(dados.ddd) < 2 )
        {
            ULOGW("ddd=%s",dados.ddd);
            throw new RCFOException(ERR_DDD_TOOSHORT,__FILE__,__LINE__);
        }

        if ( strlen(dados.nrLinha) > 10 )
        {
            ULOGW("nrLinha=%s",dados.nrLinha);
            throw new RCFOException(ERR_NRLINHA_TOOLONG,__FILE__,__LINE__);
        }

        if ( strlen(dados.nrLinha) < 1 )
        {
            ULOGW("nrLinha=%s",dados.nrLinha);
            throw new RCFOException(ERR_NRLINHA_TOOSHORT,__FILE__,__LINE__);
        }
    }

    rcfoProC.VerificarProcedencia(dados.idProcedencia);
    rcfoProC.VerificarCanal(dados.idCanal);
    rcfoProC.VerificarContato(dados.idContato);

    ULOG_END("cRegContatoFO::ValidarDadosEntrada()");
}

#ifdef ASSINC
void cRegContatoFO::RegistrarContato()
{
#ifndef WIN32
    ULOG_START("cRegContatoFO::RegistrarContato()");

    ULOG("tpacall para '%s' (%s)",getNomeServico(),getDescricaoServico());

    TuxMessage inputMessage;
    inputMessage.setService(getNomeServico());
    inputMessage.setUser((char*)codUsuario.c_str());
    inputMessage.setMessageBody(&pXMLGen);

    //
    // Nos foi recomendado usar getMessageBody() pois o Framework não libera a memória usada 
    // pelo getMessageXML(). -- Agosto/2007 -- Cassio
    //
    //char *sMsgIn = inputMessage.getMessageXML();
    //
    string strMsgIn = 
        "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>"
            "<msg>"
                "<msgHdr>"
                    "<user>"+codUsuario+"</user>"
                    "<service>"+getNomeServico()+"</service>"
                "</msgHdr>"
                "<msgBody>"
                    + inputMessage.getMessageBody() +
                "</msgBody>"
            "</msg>";

    char *sMsgIn = (char*)strMsgIn.c_str();

    char *bufferE;

    long snd_len = strlen(sMsgIn);

    // Aloca um buffer de envio com o tamanho do XML de entrada
    if ((bufferE = (char *)tpalloc("STRING",NULL,snd_len+1)) == NULL)
    {
        ULOGE("cRegContatoFO::RegistrarContato()");
        throw new TuxException(ERR_TUX_TPALLOC_RET_CD,ERR_TUX_TPALLOC_RET_MSG);
    }

    // clona o buffer de entrada no buffer tuxedo de envio
    //strncpy(bufferE,sMsgIn,snd_len);
    memcpy(bufferE,sMsgIn,snd_len);
    *(bufferE+snd_len) = 0;

    ULOG("dump call parameters:"
         "\n\t# serviceName=[%s]\n\tinputBuffer=[%s]\n\t# inputLen=[%d]\n\t# flags=[%d]"
         ,getNomeServico(),bufferE,snd_len,0 );

    if ( tpacall(getNomeServico(),(char*)bufferE,snd_len,TPNOTRAN|TPNOREPLY) == -1 )
    {
        long errNo = tperrno;
        long urCode = tpurcode;

        ULOGE("tpacall falhou:tperr=%d (%s)-tpurcode=%d ",errNo,tpstrerror(errNo),urCode);
        ULOGE("cRegContatoFO::RegistrarContato()");

        if(bufferE) {tpfree(bufferE); bufferE = 0L;}

        throw new TuxException(ERR_TUX_REMOTE_CALL_CD,ERR_TUX_REMOTE_CALL_MSG,getNomeServico(),errNo,urCode);
    }

    if(bufferE) {tpfree(bufferE); bufferE = 0L;}

    ULOG_END("cRegContatoFO::RegistrarContato()");
#endif
}
#endif

#ifdef SINC
void cRegContatoFO::RegistrarContato()
{
    ULOG_START("cRegContatoFO::RegistrarContato()");

    TuxRemoteService rc;
    TuxMessage tmIn;

    try 
    {
        ULOG("cRegContatoFO::RegistrarContato::remoteCall chamando servico '%s' (%s)"
                ,getNomeServico(),getDescricaoServico());

        tmIn.setService( getNomeServico() );
        tmIn.setUser((char*)codUsuario.c_str());
        //tmIn.setSequence( m_stDados.sequence );
        tmIn.setMessageBody( &pXMLGen );

        rc.setServiceName( getNomeServico() );
        rc.setInputMessage( &tmIn );

        if ( rc.remoteCall() != TUXFWRET_OK )
        {
            string err;

            err = "Erro na chamada ao servico ";
            err += getNomeServico();
            err += " (";
            err += getDescricaoServico();
            err += ")";

            throw new TuxException("00E0000",(char*)err.c_str());
        }

        TuxMessage* outTm = rc.getOutputMessage();

        char *codigoRetorno = outTm->getStatusCode();
        char *statusText = outTm->getStatusText();

        ULOG("outTm->getMessageBody()=\"%s\"",outTm->getMessageBody());

        if ( *(codigoRetorno+2) == 'E' )
        {
            ULOGE("servico %s (%s) retornou status code =\"%c\""
                ,getNomeServico(),getDescricaoServico(),*(codigoRetorno+2));

            throw new TuxException(codigoRetorno,statusText);
        }
        else
        {
            // wdnode = remoteService->getOutputMessage()->getMessageBodyDOM();
            // saida->clearAndDestroy();
            // saida->aggregateXML(outTm->getMessageBody());

            ULOG("xml retornado pelo servico=%s",outTm->getMessageBody());

        }
    }
    catch (...)
    {
        ULOGE("cRegContatoFO::RegistrarContato()");
        throw;
    }

    ULOG_END("cRegContatoFO::RegistrarContato()");
}
#endif


#ifdef SINC2
void cRegContatoFO::RegistrarContato()
{
    ULOG_START("cRegContatoFO::RegistrarContato()");

    ULOG("cRegContatoFO::RegistrarContato::remoteCall chamando servico '%s' (%s)"
            ,getNomeServico(),getDescricaoServico());

    TuxMessage inputMessage;
    inputMessage.setService(getNomeServico());
    inputMessage.setUser((char*)codUsuario.c_str());
    inputMessage.setMessageBody(&pXMLGen);

    char *sMsgIn = inputMessage.getMessageXML();
    //char *sMsgOut = 0L;
    char *bufferE;
    char *bufferS = 0L;
    int ret = TUXFWRET_ERROR;
    long lFlags = 0L;

    // Preenche os tamanhos de buffers para a chamada ao tpcall
    long    snd_len = strlen(sMsgIn);
    long    rcv_len = TUXFW_MAX_MSG_LEN;
    //
    // Aloca um buffer de envio com o tamanho do XML de entrada
    if ((bufferE = (char *)tpalloc("STRING", NULL, snd_len+1)) == NULL)
    {
        throw new TuxException(ERR_TUX_TPALLOC_RET_CD, ERR_TUX_TPALLOC_RET_MSG);
    }
    //
    // Aloca um buffer de recepcao  com tamanho maximo possivel para retorno
    if ((bufferS = (char *)tpalloc("STRING", NULL, TUXFW_MAX_MSG_LEN)) == NULL)
    {
        tpfree(bufferE);
        throw new TuxException(ERR_TUX_TPALLOC_RET_CD, ERR_TUX_TPALLOC_RET_MSG);
    }

    // clona o buffer de entrada no buffer tuxedo de envio
    strcpy(bufferE, sMsgIn);
    ULOG("cRegContatoFO::RegistrarContato:tpcallwrapper: dump call parameters:\n\t# serviceName=[%s]\n\tinputBuffer=[%s]\n\t# inputLen=[%d]\n\t# flags=[%d]", getNomeServico(), bufferE, snd_len, lFlags );
    if ( tpcall(getNomeServico(),(char*)bufferE,snd_len,(char **)&bufferS,&rcv_len,lFlags) == -1)
    {
        long errNo = tperrno;
        long urCode = tpurcode;

        ULOGE("cRegContatoFO::RegistrarContato:tpcallwrapper: tpcall com erro,"
              "Tperrno = %d (%s), TpUrCode = %d ",errNo,tpstrerror(errNo),urCode);

        //
        // O porquisse !!! Tenta aproveitar alguma coisa do buffer de retorno mesmo tendo
        // dado erro na chamada se for erro 11 !!!!
        // if( errNo == TPESVCFAIL )
        // {
        //  // Copia o buffer tuxedo de retorno
        //  bufferS[rcv_len]='\0';
        //  //sMsgOut = derivStr( bufferS );
        //  tuxfw_getlogger()->warning("cRegContatoFO::RegistrarContato:tpcallwrapper: tpcall processada com ERRO, outputMessage=[%s]", sMsgOut);
        // }
        // else 
        // {
            if(bufferE) tpfree(bufferE); bufferE = 0L;
            if(bufferS) tpfree(bufferS); bufferS = 0L;

            throw new TuxException(ERR_TUX_REMOTE_CALL_CD,ERR_TUX_REMOTE_CALL_MSG,getNomeServico(),errNo,urCode);
        // }
    }
    else
    {
            // Copia o buffer tuxedo de retorno
            bufferS[rcv_len]='\0';
            //sMsgOut = derivStr( bufferS );
            ULOGI("cRegContatoFO::RegistrarContato: tpcall processada com sucesso, outputMessage=[%s], tamanho=[%d]", bufferS, strlen(bufferS));
    }

    if(bufferE) tpfree(bufferE); bufferE = 0L;
    if(bufferS) tpfree(bufferS); bufferS = 0L;

    //return sMsgOut;
}
#endif

void cRegContatoFO::CarregarDados()
{
    ULOG_START("cRegContatoFO::CarregarDados()");

    pesquisarDadosLinha = false;

    if ( 0 == entrada )
    {
        ULOG("cRegContatoFO::CarregarDados()");
        throw new RCFOException(ERR_XML_ENTRADA,__FILE__,__LINE__);
    }

    char *p;

    if (p=walkTree(entrada, "idContato", 0 ),p)
    {
        dados.idContato = new char [strlen(p+1)];
        strcpy(dados.idContato,p);
        ULOG("<idContato>='%s'",dados.idContato);

        XMLString::release(&p);
    }

    // Se a tag <idContato> não for fornecida, então deve enviar a <cdContato>
    if ( 0 == dados.idContato || 0 == *dados.idContato )
    {
        if ( dados.idContato ) { delete dados.idContato; dados.idContato = 0; }

        if (p=walkTree( entrada, "cdContato", 0 ),p)
        {
            dados.cdContato = p;
            ULOG("<cdContato>='%s'",dados.cdContato);
        }
        else
        {
            throw new RCFOException(ERR_XML_CDCONTATO,__FILE__,__LINE__);
        }
    }

    if (p=walkTree( entrada, "idProcedencia", 0 ),p)
    {
        dados.idProcedencia = p;

        ULOG("<idProcedencia>='%s'",dados.idProcedencia);
    }
    else
    {
        throw new RCFOException(ERR_XML_PROCEDENCIA,__FILE__,__LINE__);
    }

    if (p=walkTree( entrada, "idCanal", 0 ),p)
    {
        dados.idCanal = p;

        ULOG("<idCanal>='%s'",dados.idCanal);
    }
    else
    {
        throw new RCFOException(ERR_XML_CANAL,__FILE__,__LINE__);
    }

    if (p=walkTree( entrada, "tpOperacao", 0 ),p)
    {
        dados.tpOperacao = p;

        ULOG("<tpOperacao>='%s'",dados.tpOperacao);
    }
    else
    {
        throw new RCFOException(ERR_XML_TPOPERACAO,__FILE__,__LINE__);
    }

    if (p=walkTree( entrada, "sgOperadoraSolicitante", 0 ),p)
    {
        dados.sgOperadoraSolicitante = p;

        ULOG("<sgOperadoraSolicitante>='%s'",dados.sgOperadoraSolicitante);
    }

    if (p=walkTree( entrada, "dtJanelaPortout", 0 ),p)
    {
        dados.dtJanelaPortout = p;

        ULOG("<dtJanelaPortout>='%s'",dados.dtJanelaPortout);
    }

    if (p=walkTree( entrada, "tipoPortabilidade", 0 ),p)
    {
        dados.tipoPortabilidade = p;

        ULOG("<tipoPortabilidade>='%s'",dados.tipoPortabilidade);
    }

    if (p=walkTree( entrada, "nrProtocoloPortabilidade", 0 ),p)
    {
        dados.nrProtocoloPortabilidade = p;

        ULOG("<nrProtocoloPortabilidade>='%s'",dados.nrProtocoloPortabilidade);
    }

    if (p=walkTree( entrada, "idGrupoAbertura", 0 ),p)
    {
        dados.idGrupoAbertura = p;

        ULOG("<idGrupoAbertura>='%s'",dados.idGrupoAbertura);
    }
    else
    {
        throw new RCFOException(ERR_XML_GRPABERTURA,__FILE__,__LINE__);
    }

    if (p=walkTree( entrada, "ddd", 0 ),p)
    {
        dados.ddd = p;
        rcfoProC.setDDD(p);

        ULOG("<ddd>='%s'",dados.ddd);
    }

    if (p=walkTree( entrada, "nrLinha", 0 ),p)
    {
        dados.nrLinha = p;
        rcfoProC.setnrTelefone(p);

        ULOG("<nrLinha>='%s'",dados.nrLinha);
    }

    if (p=walkTree( entrada, "idPessoa", 0 ),p)
    {
        rcfoProC.setIDPessoa(p);
        ULOG("<idPessoa>='%s'",p);
        XMLString::release(&p);
    }

    if (p=walkTree( entrada, "inResponsavelAbertura", 0 ),p)
    {
        rcfoProC.setIDTipoRelacionamento(p);
        ULOG("<inResponsavelAbertura>='%s'",p);
        XMLString::release(&p);
    }

    // Se o idTipoRelacionamento for diferente de 'NÃO-CLIENTE' ou vazio
    // então o ddd+telefone são obrigatórios
    if ( strcmp(rcfoProC.getIDTipoRelacionamento(),"7") )
    {
        if ( 0 == dados.ddd )
        {
            throw new RCFOException(ERR_XML_DDD,__FILE__,__LINE__);
        }

        if ( 0 == dados.nrLinha )
        {
            throw new RCFOException(ERR_XML_NRLINHA,__FILE__,__LINE__);
        }

        // Esta API só da suporte para cliente ou usuario quando um número
        // de telefone é fornecido junto com um idTipoRelacionamento
        // Nos casos em que um tipo de relacionamento não é fornecido, então
        // a API assume que um processo será aberto para uma linha de cliente/
        // usuário.
        if ( rcfoProC.idTipoRelacionamentoIsEmpty() == false && 
             strcmp(rcfoProC.getIDTipoRelacionamento(),"1")  &&
             strcmp(rcfoProC.getIDTipoRelacionamento(),"2") )
        {
            throw new RCFOException(ERR_TPRELACIONAME_CUINVALIDO,__FILE__,__LINE__);
        }

        pesquisarDadosLinha = true;
    }
    else if ( rcfoProC.idPessoaIsEmpty() )
    { // Se o tipo de relacionamento é de 'NÃO-CLIENTE' um idPessoa é obrigatório
        throw new RCFOException(ERR_XML_IDPESSOA,__FILE__,__LINE__);
    }

    if (p=walkTree( entrada, "observacao", 0 ),p)
    {
        dados.observacao = p;

        ULOG("<observacao>='%s'",dados.observacao);
    }

    ULOG ("pesquisarDadosLinha = %d",pesquisarDadosLinha);

    ULOG_END("cRegContatoFO::CarregarDados()");
}

