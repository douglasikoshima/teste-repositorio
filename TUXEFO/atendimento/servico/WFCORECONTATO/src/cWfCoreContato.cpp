/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @remark  Api usada pelo WebService de 
 *          Ela pode ser usada como api de geração de palitagens no FO por módulos que não possuam
 *          meios de obter toda a informação necessária para que o REGCONTATO grave dados  
 *          sua tarefa.
 * @author  Cassio Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:28 $
 **/

#include "../include/cWfCoreContato.h"
#include "../include/cWfCoreContatoException.h"

#define MIN(a,b) (a) < (b) ? (a) : (b)

void rtrim(char *pStr);

void cWfCoreContato::Executar(string &idAtendimentoProtocolo)
{
    ULOG_START("cWfCoreContato::Executar()");

    try
    {
        CarregarDados();

        // Verifica se a ação informada é valida
        if ( 0==cwfcorecontatopc.VerificarValidadeAcaoPortabilidade((const char*)&dados.estadoPortabilidade) )
        {
            throw new CoreContatoException(ERR_VLR_IDACAOPORTABILIDADE,__FILE__,__LINE__);
        }

        // Obtém os parâmetros da ação para a operação informada
        if ( false==cwfcorecontatopc.ObterContatoFuncionalidade((const char*)&dados.estadoPortabilidade
                                                        ,(const char*)&dados.dsOperacao) )
        {
            if ( strcmp(dados.dsOperacao,"PORTIN")==0 )
            {
                throw new CoreContatoException(ERR_VLR_CFUNC_PORTIN,__FILE__,__LINE__);
            }
            else if ( strcmp(dados.dsOperacao,"PORTOUT")==0 )
            {
                throw new CoreContatoException(ERR_VLR_CFUNC_PORTOUT,__FILE__,__LINE__);
            }
            else if ( strcmp(dados.dsOperacao,"FRAUDE")==0 )
            {
                throw new CoreContatoException(ERR_VLR_CFUNC_FRAUDE,__FILE__,__LINE__);
            }
            else if ( strcmp(dados.dsOperacao,"WINBACK")==0 )
            {
                throw new CoreContatoException(ERR_VLR_CFUNC_WINBACK,__FILE__,__LINE__);
            }

            throw new CoreContatoException(ERR_VLR_CFUNC_GENERIC,__FILE__,__LINE__);
        }

        // Obtém o ID de pessoa para portabilidade
        if ( cwfcorecontatopc.ObterIdPessoaUsuarioPortabilidade() == false )
        { // se não encontrar o usuário de portabilidade joga para o adminfo.
            cwfcorecontatopc.setIdPessoaUsuario("1"); // adminfo
        }

        // Vai iniciar a execução do serviço associado à ação
        if ( strcmp(cwfcorecontatopc.getTipoOperacao(),TPOPER_PALITAGEM)
            && strcmp(dados.dsOperacao,"PORTOUT") == 0 
            && cwfcorecontatopc.ExisteProtocoloPortabilidade((const char*)dados.nrBilhetePortabilidade) )
        { // Alteração funcional solicitada pelo Tiago em Set/2008.
          // Evita que existam 2 processos de PORTOUT abertos enviando ação de alteração de dados.
            cwfcorecontatopc.setSgAtividade("ADP"); // ALTERAR DADOS DO PROCESSO
            CoreWorkflow();
        }
        else if ( strcmp(dados.dsOperacao,"PORTOUT")==0 
             && stricmp(cwfcorecontatopc.getDsAcaoPortabilidade(),"Cancelamento Solicitado")==0 
             && cwfcorecontatopc.ObterIdAtendimento(dados.nrBilhetePortabilidade) == false )
        { // Alteração funcional de agosto/2008.
          // Caso seja solicitado um cancelamento de portout para um processo de portout já encerrado
          // será registrada uma palitagem para que a URIN possa tratar.
            RegContato(idAtendimentoProtocolo);
        }
        else if ( 0==strcmp(cwfcorecontatopc.getNmServico(),"REGCONTATO") )
        {
            RegContato(idAtendimentoProtocolo);
        }
        else if ( 0==strcmp(cwfcorecontatopc.getNmServico(),"COREWORKFLOW") )
        {
            CoreWorkflow();
        }
        else
        {
            throw new CoreContatoException(ERR_FLX_SERVICONAOIDENTIFICADO,__FILE__,__LINE__);
        }
    }
    catch (...)
    {
        throw;
    }

    ULOG_END("cWfCoreContato::Executar()");
}

void cWfCoreContato::RegContato(string &idAtendimentoProtocolo)
{
    ULOG_START("cWfCoreContato::RegContato()");

    // Verifica se os parâmetros obrigatórios para o serviço existem
    if ( cwfcorecontatopc.isEmptyIdGrupoAbertura() )
    {
        throw new CoreContatoException(ERR_PRM_GRUPOABERTURA,__FILE__,__LINE__);
    }
    if ( cwfcorecontatopc.isEmptyTipoOperacao() )
    {
        throw new CoreContatoException(ERR_PRM_TPOPERACAO,__FILE__,__LINE__);
    }
    // if ( cwfcorecontatopc.isEmptyIdProcedencia() )
    // {
    //     throw new CoreContatoException(ERR_PRM_IDPROCEDENCIA,__FILE__,__LINE__);
    // }
    if ( cwfcorecontatopc.isEmptyIdCanal() )
    {
        throw new CoreContatoException(ERR_PRM_IDCANAL,__FILE__,__LINE__);
    }
    if ( cwfcorecontatopc.isEmptyIdContato() )
    {
        throw new CoreContatoException(ERR_PRM_IDCONTATO,__FILE__,__LINE__);
    }

    // Verifica se os parâmetros de entrada obrigatórios para o serviço foram informados
    if ( 0==dados.cdAreaRegistro[0] )
    {
        throw new CoreContatoException(ERR_VLR_CDAREAREGISTRO,__FILE__,__LINE__);
    }
    if ( 0==dados.nrLinha[0] )
    {
        throw new CoreContatoException(ERR_VLR_NRLINHA,__FILE__,__LINE__);
    }

    // Verifica se procedencia fornecida é válida e obtém o ID correto
    if ( false==cwfcorecontatopc.VerificarIdProcedencia((const char*)&dados.cdProcedencia) )
    {
        string msg = "cdProcedência '"+(string)dados.dsProcedencia+
                                    "' não existe na base do FrontOffice";

        throw new CoreContatoException(ERR_PRM_IDPROCEDENCIA,msg,__FILE__,__LINE__);
    }

    // // Garante que não vão existir 2 processos de PORTOUT com o mesmo numero
    // // de bilhete de portabilidade na base.
    // if ( strcmp(cwfcorecontatopc.getTipoOperacao(),TPOPER_PALITAGEM)
    //     && strcmp(dados.dsOperacao,"PORTOUT") == 0 )
    // {
    //     if ( cwfcorecontatopc.ExisteProtocoloPortabilidade((const char*)dados.nrBilhetePortabilidade) )
    //     {
    //         throw new CoreContatoException(ERR_NFO_PROTPORTDUPLICADO,__FILE__,__LINE__);
    //     }
    // }

    // Se recebeu data de janela, verifica se a janela esta com prazo condizente

    //if ( dados.dtJanelaPortout[0] )
    //{ // Não verificamos mais. SPN estava tomando erros e solicitaram remoção.Set/2008--Cassio
    //    VerificarPrazosJanelaPortout();
    //}

    char nrTelefone[256];
    sprintf(nrTelefone,"%s%s",dados.cdAreaRegistro,dados.nrLinha);

    cwfcorecontatopc.ObterDadosLinPessoaCliente(dados.cdAreaRegistro,dados.nrLinha,dados.dsOperacao);

    cwfcorecontatopc.ObterDadosPessoaCliente(dados.dsOperacao);

    cwfcorecontatopc.ObterSgOperadoraSolicitante(dados.cdOperadoraSolicitante);

    cwfcorecontatopc.ObterDsAcaoPortabilidade(dados.estadoPortabilidade);

    char idTipoAberturaProtocolo[4] = {0};

    if ( strcmp(cwfcorecontatopc.getIdTipoRelacionamentoCliente(),"2") )
    {
        if ( nrTelefone[0] )
        {
            strcpy(idTipoAberturaProtocolo,TIPO_ABER_PROT_LINHA);
        }
        else
        {
            strcpy(idTipoAberturaProtocolo,TIPO_ABER_PROT_PESSOA);
        }
    }
    else
    {
        strcpy(idTipoAberturaProtocolo,TIPO_ABER_PROT_LINHA_CLIENTE);
    }

    xmlGen.createTag("AtendimentoVO");
        xmlGen.addProp("xmlns","workflow.fo.vivo.com.br/vo");
        xmlGen.addItem("idGrupoAbertura",(char*)cwfcorecontatopc.getIdGrupoAbertura());
        xmlGen.addItem("idLinhaAtendimento",(char*)cwfcorecontatopc.getIdLinhaTelefonica());
        xmlGen.addItem("inResponsavelAbertura","2");
        xmlGen.addItem("nmContato",(char*)cwfcorecontatopc.getNmPessoaCliente());
        xmlGen.addItem("nmFalandoCom","");
        xmlGen.addItem("nrTelefone",nrTelefone);
        xmlGen.addItem("observacao",dados.dsComentarioProcesso);
        //xmlGen.addItem("sgOperadoraSolicitante",dados.sgOperadoraSolicitante);
        xmlGen.addItem("sgOperadoraSolicitante",(char*)cwfcorecontatopc.getSgOperadoraSolicitante());
        xmlGen.addItem("tpOperacao",(char*)cwfcorecontatopc.getTipoOperacao());
        if ( dados.dtJanelaPortout[0] )
        {
            xmlGen.addItem("dtJanelaPortout",dados.dtJanelaPortout);
        }
        xmlGen.addItem("tipoPortabilidade",dados.dsOperacao);
        xmlGen.addItem("dsAcaoPortabilidade",(char*)cwfcorecontatopc.getDsAcaoPortabilidade());
        xmlGen.addItem("nrProtocoloPortabilidade",dados.nrBilhetePortabilidade);

        xmlGen.createTag("DadosProtocoloVO");
            xmlGen.addItem("idTipoAberturaProtocolo",(char*)cwfcorecontatopc.getIdProcedencia());
            xmlGen.addItem("idSistemaOrigemProtocolo","32"); // Sistema Origem=SPN
            xmlGen.addItem("dddSMSProtocolo",dados.cdAreaRegistro);
            xmlGen.addItem("nrLinhaSMSProtocolo",dados.nrLinha);
        xmlGen.closeTag();

        xmlGen.createTag("ProcedenciaVO");
            xmlGen.addProp("xmlns","admsistemas.fo.vivo.com.br/vo");
            xmlGen.addItem("idProcedencia",(char*)cwfcorecontatopc.getIdProcedencia());
        xmlGen.closeTag();
        xmlGen.createTag("CanalVO");
            xmlGen.addProp("xmlns","workflow.fo.vivo.com.br/vo");
            xmlGen.addItem("idCanal",(char*)cwfcorecontatopc.getIdCanal());
        xmlGen.closeTag();
        xmlGen.createTag("Contas");
            xmlGen.addProp("xmlns","workflow.fo.vivo.com.br/vo");
            xmlGen.createTag("ContasVO");
                xmlGen.addItem("idConta",(char*)cwfcorecontatopc.getIdConta());
                xmlGen.createTag("LinhaVO");
                // O REGCONTATO esta esperando o valor de IDLINHATELEFONICA na tag
                // <idPessoaLinhaHistorico> e por isso o WFCORECONTATO passa o valor
                // deste campo nesta tag apesar de parecer errado. Nov/2006 - Cassio
                if ( cwfcorecontatopc.isEmptyIdLinhaTelefonica() == false )
                {
                    xmlGen.addItem("idPessoaLinhaHistorico",(char*)cwfcorecontatopc.getIdLinhaTelefonica());
                }
                xmlGen.closeTag();
            xmlGen.closeTag();
        xmlGen.closeTag();
        xmlGen.createTag("PessoaVO");
            xmlGen.addProp("xmlns","workflow.fo.vivo.com.br/vo");
            // O REGCONTATO esta esperando o valor de IDPESSOADEPARA na tag
            // <idPessoa> e por isso o WFCORECONTATO passa o valor
            // deste campo nesta tag apesar de parecer errado. Nov/2006 - Cassio
            xmlGen.addItem("idPessoa",(char*)cwfcorecontatopc.getIdPessoaDeParaCliente());
        xmlGen.closeTag();
        xmlGen.createTag("UsuarioLinhaVO");
            xmlGen.addProp("xmlns","cliente.fo.vivo.com.br/vo");
            xmlGen.addItem("idPessoa",(char*)cwfcorecontatopc.getIdPessoaDeParaCliente());
        xmlGen.closeTag();
        xmlGen.createTag("ArvoreAtendimentoVO");
            xmlGen.addProp("xmlns","admsistemas.fo.vivo.com.br/vo");
            xmlGen.addItem("idContato",(char*)cwfcorecontatopc.getIdContato());
            xmlGen.createTag("CarterizacaoVO");
                xmlGen.addItem("idTipoCarteira",(char*)cwfcorecontatopc.getIdTipoCarteiraCliente());
            xmlGen.closeTag();
            xmlGen.createTag("SegmentacaoVO");
                xmlGen.addItem("idSegmentacao",(char*)cwfcorecontatopc.getIdSegmentacaoCliente());
            xmlGen.closeTag();
        xmlGen.closeTag();
    xmlGen.closeTag();

    // Executa o serviço tuxedo
    SyncRemoteCall("REGCONTATO");

    // Captura o numero do protocolo retornado no XML de saida do serviço
    char *p = ptmOut->getMessageBody();
    char *p0 = strstr(p,"<nrProtocolo>");
    char *p1 = strstr(p,"</nrProtocolo>");

    if ( p0 && p1 )
    {
        char nrProtocoloTmp[256];

        p0 += strlen("<nrProtocolo>");
        memcpy(nrProtocoloTmp,p0,MIN(p1-p0,sizeof(nrProtocoloTmp)-1));
        nrProtocoloTmp[MIN(p1-p0,sizeof(nrProtocoloTmp)-1)]=0;
        idAtendimentoProtocolo = nrProtocoloTmp;
    }

    ULOG_END("cWfCoreContato::RegContato()");
}

void cWfCoreContato::CoreWorkflow()
{
    ULOG_START("cWfCoreContato::CoreWorkflow()");

    try
    {
        // Motivo (PORTIN,PORTOUT,etc)
        cwfcorecontatopc.ObterDadosMotivo(dados.dsOperacao);

        // Atividade
        if ( cwfcorecontatopc.isEmptySgAtividade() )
        {
            throw new CoreContatoException(ERR_NFO_SGATIVIDADE,__FILE__,__LINE__);
        }

        cwfcorecontatopc.ObterDadosAtividadeWorkflow(cwfcorecontatopc.getSgAtividade());

        if ( cwfcorecontatopc.ObterIdAtendimento(dados.nrBilhetePortabilidade) == false )
        {
            throw new CoreContatoException(ERR_NFO_NRPROTOCOLPORTABILIDADE,__FILE__,__LINE__);
        }

        cwfcorecontatopc.ObterAgrupamentoFuturo();

        cwfcorecontatopc.ObterSgOperadoraSolicitante(dados.cdOperadoraSolicitante);

        cwfcorecontatopc.ObterDsAcaoPortabilidade(dados.estadoPortabilidade);

        char dtJanelaPortoutFormatada[32]; // DD/MM/AAAA HH:MI:SS
        if ( dados.dtJanelaPortout[0] )
        {
            sprintf(dtJanelaPortoutFormatada,"%.2s%c%.2s%c%.4s %.2s:%.2s:%.2s",
                        dados.dtJanelaPortout,'/',
                        dados.dtJanelaPortout+2,'/',
                        dados.dtJanelaPortout+4,
                        dados.dtJanelaPortout+8,
                        dados.dtJanelaPortout+10,
                        dados.dtJanelaPortout+12);
        }
        else
        {
            dtJanelaPortoutFormatada[0] = 0;
        }

        xmlGen.createTag("atendimentos");
            xmlGen.createTag("xml-fragment");
                xmlGen.createTag("AtendimentoWorkflowVO");
                xmlGen.addProp("xmlns","workflow.fo.vivo.com.br/vo");
                xmlGen.addItem("idFase",(char*)cwfcorecontatopc.getIdFase());
                xmlGen.addItem("idProcedencia",dados.cdProcedencia);
                xmlGen.addItem("idAgrupamentoEstadoTProcFut",(char*)cwfcorecontatopc.getIdAgrupamentoEstadoTpProcFt());
                xmlGen.addItem("dtContato",dtJanelaPortoutFormatada);
                xmlGen.addItem("inSPN","1");
                xmlGen.addItem("dtJanelaPortout",dtJanelaPortoutFormatada);
                xmlGen.addItem("dsObservacao",dados.dsComentarioProcesso);
                xmlGen.addItem("sgOperadoraSolicitante",(char*)cwfcorecontatopc.getSgOperadoraSolicitante());
                xmlGen.addItem("dsAcaoPortabilidade",(char*)cwfcorecontatopc.getDsAcaoPortabilidade());
                    xmlGen.createTag("AtendimentoWorkflowComumVO");
                        xmlGen.createTag("WFMotivoVO");
                            xmlGen.addItem("idMotivo",(char*)cwfcorecontatopc.getIdMotivo());
                        xmlGen.closeTag();
                    xmlGen.closeTag();
                xmlGen.closeTag();
                xmlGen.createTag("AtdWFVO");
                    xmlGen.addItem("idAtendimento",(char*)cwfcorecontatopc.getIdAtendimento());
                xmlGen.closeTag();
                xmlGen.createTag("WFAcaoVO");
                    xmlGen.addItem("idAtividade",(char*)cwfcorecontatopc.getIdAtividade());
                    //xmlGen.addItem("dsTipoAtividade",(char*)cwfcorecontatopc.getDsTipoAtividade());
                    xmlGen.addItem("dsTipoAtividade","PORTOUT");
                xmlGen.closeTag();
            xmlGen.closeTag();
        xmlGen.closeTag();

        // Executa o serviço tuxedo
        SyncRemoteCall("COREWORKFLOW");

       // Captura as mensagens vindas do coreworkflow ou da atividade chamada
        char *p = ptmOut->getMessageBody();
        char *p0 = strstr(p,"<acaoExecucao>");
        char *p1 = strstr(p,"</acaoExecucao>");

        char *p2 = strstr(p,"<mensagem>");
        char *p3 = strstr(p,"</mensagem>");

        if ( p0 && p1 && p2 && p3 )
        {
            char acaoExecucao[256];

            p0 += strlen("<acaoExecucao>");
            memcpy(acaoExecucao,p0,MIN(p1-p0,sizeof(acaoExecucao)-1));
            acaoExecucao[MIN(p1-p0,sizeof(acaoExecucao)-1)]=0;
            ULOG("acaoExecucao=%s",acaoExecucao);

            char mensagem[1024];
            p2 += strlen("<mensagem>");
            memcpy(mensagem,p2,MIN(p3-p2,sizeof(mensagem)-1));
            mensagem[MIN(p3-p2,sizeof(mensagem)-1)]=0;
            ULOG("mensagem=%s",mensagem);

            if ( strcmp(acaoExecucao,"N")==0 )
            {
                throw new CoreContatoException("04I9900",mensagem);
            }
        }
    }
    catch(...)
    {
        throw;
    }

    ULOG_END("cWfCoreContato::CoreWorkflow()");
}

void cWfCoreContato::SyncRemoteCall(char *nomeServico)
{
    ULOG_START("cWfCoreContato::SyncRemoteCall()");

    try
    {
        ULOG("remoteCall vai chamar o servico '%s'",nomeServico);

        tmIn.setService(nomeServico);
        tmIn.setUser((char*)cwfcorecontatopc.getIdPessoaUsuario());
        //tmIn.setSequence( m_stDados.sequence );
        tmIn.setMessageBody(&xmlGen);

        rc.setServiceName(nomeServico);
        rc.setInputMessage(&tmIn);

        if ( rc.remoteCall() != TUXFWRET_OK )
        {
            string err;

            err = "Erro na chamada ao servico ";
            err += nomeServico;

            throw new CoreContatoException(ERR_RMT_REMOTECALL,err,__FILE__,__LINE__);
        }

        ptmOut = rc.getOutputMessage();

        char *codigoRetorno = ptmOut->getStatusCode();
        char *statusText = ptmOut->getStatusText();

        ULOG("codigoRetorno='%c'",*(codigoRetorno+2));

        if ( *(codigoRetorno+2) != 'I' )
        {
            ULOGE("servico %s retornou status code='%c'"
                 ,nomeServico,*(codigoRetorno+2));

            throw new TuxException("04I9999",statusText);
        }
    }
    catch (...)
    {
        ULOGE("cWfCoreContato::SyncRemoteCall()");
        throw;
    }

    ULOG_END("cWfCoreContato::SyncRemoteCall()");
}

void cWfCoreContato::ConsistirData(const char *ddmmaaaahhmiss)
{
    ULOG_START("cWfCoreContato::ConsistirData()");

    if (!ddmmaaaahhmiss) throw new CoreContatoException(ERR_DTA_INVALIDA,__FILE__,__LINE__);

    if ( strlen(ddmmaaaahhmiss) !=14 ) throw new CoreContatoException(ERR_DTA_INVALIDA,__FILE__,__LINE__);

    const char *p = ddmmaaaahhmiss;

    while (*p)
    {
        if ( *p == '.' || isalpha(*p) )
        {
            throw new CoreContatoException(ERR_DTA_INVALIDA,__FILE__,__LINE__);
        }

        p++;
    }

    int fmes[] = { 31,28,31,30,31,30,31,31,30,31,30,31 };
    int dd = (*ddmmaaaahhmiss    -'0')*  10 + (*(ddmmaaaahhmiss+1)-'0');
    int mm = (*(ddmmaaaahhmiss+2)-'0')*  10 + (*(ddmmaaaahhmiss+3)-'0');
    int aa = (*(ddmmaaaahhmiss+4)-'0')*1000 + (*(ddmmaaaahhmiss+5)-'0')*100 + 
             (*(ddmmaaaahhmiss+6)-'0')*  10 + (*(ddmmaaaahhmiss+7)-'0');

    int bs = aa / 4;
    float bsf = (float)(aa) / 4.0;

    bool anoBissexto = (float)bs==bsf?true:false;

    if ( anoBissexto ) { fmes[1] = 29; }

    if ( dd < 1 ) throw new CoreContatoException(ERR_DTA_INVALIDA,__FILE__,__LINE__);
    if ( dd > fmes[mm-1] ) throw new CoreContatoException(ERR_DTA_INVALIDA,__FILE__,__LINE__);

    if ( mm < 1 ) throw new CoreContatoException(ERR_DTA_INVALIDA,__FILE__,__LINE__);
    if ( mm > 12 ) throw new CoreContatoException(ERR_DTA_INVALIDA,__FILE__,__LINE__);

    int hh = (*(ddmmaaaahhmiss+ 8)-'0')*10 + (*(ddmmaaaahhmiss+ 9)-'0');
    int mi = (*(ddmmaaaahhmiss+10)-'0')*10 + (*(ddmmaaaahhmiss+11)-'0');
    int ss = (*(ddmmaaaahhmiss+12)-'0')*10 + (*(ddmmaaaahhmiss+13)-'0');

    if ( hh < 0 ) throw new CoreContatoException(ERR_HRA_INVALIDA,__FILE__,__LINE__);
    if ( hh > 23 ) throw new CoreContatoException(ERR_HRA_INVALIDA,__FILE__,__LINE__);

    if ( mi < 0 ) throw new CoreContatoException(ERR_HRA_INVALIDA,__FILE__,__LINE__);
    if ( mi > 60 ) throw new CoreContatoException(ERR_HRA_INVALIDA,__FILE__,__LINE__);

    if ( ss < 0 ) throw new CoreContatoException(ERR_HRA_INVALIDA,__FILE__,__LINE__);
    if ( ss > 60 ) throw new CoreContatoException(ERR_HRA_INVALIDA,__FILE__,__LINE__);

    ULOG_END("cWfCoreContato::ConsistirData()");
}

void cWfCoreContato::VerificarPrazosJanelaPortout()
{
    ULOG_START("cWfCoreContato::VerificarPrazosJanelaPortout()");

    int limiteMinimoJanela;
    int prazoJanelaPortout;

    cwfcorecontatopc.ObterPrazosJanelaPortout(dados.dtJanelaPortout
                                             ,&limiteMinimoJanela
                                             ,&prazoJanelaPortout);
    if ( prazoJanelaPortout < 0 )
    {
        throw new CoreContatoException(ERR_DTA_PZOJPO_ANTERIORHOJE,__FILE__,__LINE__);
    }

    if ( prazoJanelaPortout <= limiteMinimoJanela )
    {
        throw new CoreContatoException(ERR_DTA_PZOJPO_PRAZOINFMIN,__FILE__,__LINE__);
    }

    ULOG_END("cWfCoreContato::VerificarPrazosJanelaPortout()");
}

void cWfCoreContato::CarregarDados()
{
    ULOG_START("cWfCoreContato::CarregarDados()");

    if ( 0 == entrada )
    {
        ULOG("cWfCoreContato::CarregarDados()");
        throw new CoreContatoException(ERR_XML_ENTRADA,__FILE__,__LINE__);
    }

    char *p;

    memset(&dados,0,sizeof(dados));
    memset(&status,-1,sizeof(status));

    if ( p=walkTree(entrada,"cdAreaRegistro",0),p )
    {
        if ( *p )
        {
            SAFE_STRNCPY(dados.cdAreaRegistro,p);
            status.cdAreaRegistro = 1;
            ULOG("cdAreaRegistro=%s",dados.cdAreaRegistro);
        }
        XMLString::release(&p);
    }

    if ( p=walkTree(entrada,"nrLinha",0),p )
    {
        if ( *p )
        {
            SAFE_STRNCPY(dados.nrLinha,p);
            status.nrLinha = 1;
            ULOG("nrLinha=%s",dados.nrLinha);
        }
        XMLString::release(&p);
    }

    if ( p=walkTree(entrada,"cdOperacao",0),p )
    {
        if ( *p )
        {
            SAFE_STRNCPY(dados.cdOperacao,p);
            status.cdOperacao = 1;

            if ( strcmp(dados.cdOperacao,CD_OPERACAO_PORTIN)==0 )
            {
                strcpy(dados.dsOperacao,"PORTIN");
            }
            else if ( strcmp(dados.cdOperacao,CD_OPERACAO_PORTOUT)==0 )
            {
                strcpy(dados.dsOperacao,"PORTOUT");
            }
            else if ( strcmp(dados.cdOperacao,CD_OPERACAO_FRAUDE)==0 )
            {
                strcpy(dados.dsOperacao,"FRAUDE");
            }
            else if ( strcmp(dados.cdOperacao,CD_OPERACAO_WINBACK)==0 )
            {
                strcpy(dados.dsOperacao,"WINBACK");
            }
            else
            {
                throw new CoreContatoException(ERR_VLR_CODOPERACAO,__FILE__,__LINE__);
            }
        }
        XMLString::release(&p);
    }
    else
    {
        throw new CoreContatoException(ERR_XML_CODOPERACAO,__FILE__,__LINE__);
    }

    if ( p=walkTree(entrada,"estadoPortabilidade",0),p )
    {
        if ( *p )
        {
            SAFE_STRNCPY(dados.estadoPortabilidade,p);
            status.estadoPortabilidade = 1;
            ULOG("estadoPortabilidade=%s",dados.estadoPortabilidade);
        }
        XMLString::release(&p);
    }
    else
    {
        throw new CoreContatoException(ERR_XML_IDACAOPORTABILIDADE,__FILE__,__LINE__);
    }

    if ( p=walkTree(entrada,"dtJanela",0),p )
    {
        if ( *p )
        {
            SAFE_STRNCPY(dados.dtJanelaPortout,p);
            status.dtJanelaPortout = 1;
            ULOG("dtJanelaPortout=%s",dados.dtJanelaPortout);
            ConsistirData(dados.dtJanelaPortout);
        }
        XMLString::release(&p);
    }
    else if ( strcmp(dados.cdOperacao,CD_OPERACAO_PORTOUT) == 0
            && ( strcmp(dados.estadoPortabilidade,"12") == 0 /*reagendamento*/
               || strcmp(dados.estadoPortabilidade,"22") == 0 /*abertura processo*/) )
    {
        throw new CoreContatoException(ERR_XML_DTJANELAPORTOUT,__FILE__,__LINE__);
    }
    else
    {
        ULOG("dtJanelaPortout não informada");
    }

    if ( p=walkTree(entrada,"cdOperadoraSolicitante",0),p )
    {
        if ( *p )
        {
            SAFE_STRNCPY(dados.cdOperadoraSolicitante,p);
            status.cdOperadoraSolicitante = 1;
            ULOG("cdOperadoraSolicitante=%s",dados.cdOperadoraSolicitante);

            // if ( strcmp(dados.cdOperadoraSolicitante,CD_OPERA_TIM)==0 )
            // {
            //     strcpy(dados.sgOperadoraSolicitante,"TIM");
            // }
            // else if ( strcmp(dados.cdOperadoraSolicitante,CD_OPERA_CLARO)==0 )
            // {
            //     strcpy(dados.sgOperadoraSolicitante,"CLARO");
            // }
            // else if ( strcmp(dados.cdOperadoraSolicitante,CD_OPERA_OI)==0 )
            // {
            //     strcpy(dados.sgOperadoraSolicitante,"OI");
            // }
            // else if ( strcmp(dados.cdOperadoraSolicitante,CD_OPERA_NEXTEL)==0 )
            // {
            //     strcpy(dados.sgOperadoraSolicitante,"NEXTEL");
            // }
            // else
            // {
            //     strcpy(dados.sgOperadoraSolicitante,"OUTROS");
            // }
            //else
            //{
            //    throw new CoreContatoException(ERR_VLR_OPERADORAINVALIDA,__FILE__,__LINE__);
            //}
        }
        XMLString::release(&p);
    }

    if ( p=walkTree(entrada,"dsComentarioProcesso",0),p )
    {
        if ( *p )
        {
            rtrim(p); // remove espaços em branco do final 

            if ( *p && strcmp(p,"0") )
            {
                SAFE_STRNCPY(dados.dsComentarioProcesso,p);
                status.dsComentarioProcesso = 1;
                ULOG("dsComentarioProcesso=%s",dados.dsComentarioProcesso);
            }
        }
        XMLString::release(&p);
    }

    if ( p=walkTree(entrada,"nrProcesso",0),p )
    {
        if ( *p )
        {
            rtrim(p); // remove espaços em branco do final 

            if ( *p && strcmp(p,"0") )
            {
                SAFE_STRNCPY(dados.nrProcesso,p);
                status.nrProcesso = 1;
                ULOG("nrProcesso=%s",dados.nrProcesso);
            }
        }
        XMLString::release(&p);
    }

    if ( p=walkTree(entrada,"cdProcedencia",0),p )
    {
        rtrim(p); // remove espaços em branco do final 

        if ( *p && strcmp(p,"0") )
        {
            SAFE_STRNCPY(dados.cdProcedencia,p);
            status.cdProcedencia = 1;
            ULOG("cdProcedencia=%s",dados.cdProcedencia);

            // if ( strcmp(dados.cdProcedencia,PROCEDENCIA_LOJA)==0 )
            // {
            //     strcpy(dados.dsProcedencia,"LOJA");
            // }
            // else if ( strcmp(dados.cdProcedencia,PROCEDENCIA_DEALER)==0 )
            // {
            //     strcpy(dados.dsProcedencia,"DEALER");
            // }
            // else if ( strcmp(dados.cdProcedencia,PROCEDENCIA_GERENTE_DE_CONTAS)==0 )
            // {
            //     strcpy(dados.dsProcedencia,"GERENTE DE CONTAS");
            // }
            // else if ( strcmp(dados.cdProcedencia,PROCEDENCIA_CALL_CENTER)==0 )
            // {
            //     strcpy(dados.dsProcedencia,"CALL CENTER");
            // }
            // else if ( strcmp(dados.cdProcedencia,PROCEDENCIA_TIM)==0 )
            // {
            //     strcpy(dados.dsProcedencia,"TIM");
            // }
            // else if ( strcmp(dados.cdProcedencia,PROCEDENCIA_CLARO)==0 )
            // {
            //     strcpy(dados.dsProcedencia,"CLARO");
            // }
            // else if ( strcmp(dados.cdProcedencia,PROCEDENCIA_OI)==0 )
            // {
            //     strcpy(dados.dsProcedencia,"OI");
            // }
            // else if ( strcmp(dados.cdProcedencia,PROCEDENCIA_BRASIL_TELECOM)==0 )
            // {
            //     strcpy(dados.dsProcedencia,"BRASIL TELECOM");
            // }
            // else
            // {
            //     throw new CoreContatoException(ERR_VLR_PROCEDENCIAINVALIDO,__FILE__,__LINE__);
            // }

            ULOG("dsProcedencia=%s",dados.dsProcedencia);
        }
        XMLString::release(&p);
    }

    if ( 0 == dados.cdProcedencia[0] || 0==strcmp(dados.cdProcedencia,"0") )
    {
        SAFE_STRNCPY(dados.cdProcedencia,"1");
    }

    if ( p=walkTree(entrada,"nrBilhetePortabilidade",0),p )
    {
        rtrim(p); // remove espaços em branco do final 

        if ( *p && strcmp(p,"0") )
        {
            SAFE_STRNCPY(dados.nrBilhetePortabilidade,p);
            status.nrBilhetePortabilidade = 1;
            ULOG("nrBilhetePortabilidade=%s",dados.nrBilhetePortabilidade);
        }
        XMLString::release(&p);
    }

    if ( 0==dados.nrBilhetePortabilidade[0] )
    {
        throw new CoreContatoException(ERR_VLR_NRBILHETEPORTABILIDADE,__FILE__,__LINE__);
    }

    ULOG_END("cWfCoreContato::CarregarDados()");
}

//===
// remove espaços em branco no final de uma string C -- Cassio --
void rtrim(char *pStr)
{
    if ( pStr )
    {
        int tam = strlen(pStr)-1;

        if ( tam>=0 && *(pStr+tam) == ' ' )
        {
            *(pStr+tam) = 0;
            rtrim(pStr);
        }
    }
}
