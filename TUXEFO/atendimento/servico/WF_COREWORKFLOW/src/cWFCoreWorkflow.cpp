/**
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.2.7.6.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/06 12:36:43 $
 **/

#include "../include/cWFCoreWorkflow.h"

#include "../../../../atendimento/servico/AtendimentoGrupoAtual/include/cWFAtendimentoGrupoAtual.h"
#include "../../../../atendimento/servico/AtendimentoUsuarioAtual/include/cWFAtendimentoUsuarioAtual.h"
#include "../../../../atendimento/servico/AtdTratamentoCri/include/cWFAtdTratamentoCri.h"

extern bool proCObterGrupoFase( int _idContato, int _idGrupo, int _idFase );
extern bool proCObterGrupoCri( int _idContato, int _idGrupo );
extern bool proCAtendimentoCri( long _idAtendimento);
extern bool proCGrupoEmFaseTratamentoSimNao( int _idGrupo, int _idContato );
extern bool proCGrupoEmFaseRetornoSimNao( int _idGrupo, int _idContato );
extern bool proCGrupoEmFaseTratRetornoSimNao( int _idGrupo, int _idContato );
extern bool proCGrupoCRISimNao(int _idGrupo);
extern bool proCAtividadeDisponivelSimNao(int _idAtividade,int _idAgrupamentoEstadoTpProc,int _idContato);
extern bool proCAtividadeDisponivelRCSimNao(int _idAtividade,int _idAgrupamentoEstadoTpProc);
extern bool proCAtividadeDisponivelCRISimNao(int _idAtividade,int _idAgrupamentoEstadoTpProc);
extern bool proCAtividadeDisponivelPOutSimNao(int _idAtividade,int _idAgrupamentoEstadoTpProc);
extern bool proCIsGrupoUsuarioMC(long _idAtendimento,unsigned long _idPessoaUsuarioWeb);

extern bool proCIsMotivoCancelamento(int _idMotivo);

extern int proCObterInSupervisor( int _idGrupo,int _idUsuario );
extern int proCGetStatusDispUsuario(int _idPessoaUsuario,int *_idStatusUsuario,int *_inDisponivelWF);

//Funcoes para verificacao de Skill
extern int proCUsuarioTemSkill( int idPessoaUsuario, int idGrupo );
extern int proCUsuarioTemAcesso( int idPessoaUsuario, int idGrupo, long idAtendimento );

//============================================================================
// Antigos IDs dos serviços no workflow.RouterScript
//
#define SVC_ENCACAO          81
#define SVC_AGDACAO         204
#define SVC_ENBKOACAO       277
#define SVC_FINACAO         278
#define SVC_ADBKOACAO       279
#define SVC_PBKOACAO        280
#define SVC_ECRIACAO        281
#define SVC_TRCACAO         282
#define SVC_CERRAMERET      283
#define SVC_CERRAMEBKO      284
#define SVC_CERRAMEFEC      285
#define SVC_FECACAO         286
#define SVC_STCACAO         287
#define SVC_CANACAO         288
#define SVC_ATDCONSANALISE  289
#define SVC_MARCARSUSP      290
#define SVC_INIACAO         291
#define SVC_REPACAO         292
#define SVC_DTRACAO         293
#define SVC_DBKACAO         294
#define SVC_COMACAO         295
#define SVC_RESPCLIENTE     296
#define SVC_REABPROC        563
#define SVC_ENCBKOCRI       363 // Usa mesmo serviço apontado por SVC_ENCACAO
#define SVC_ADBKOACOMP      785 // Usa mesmo serviço apontado por SVC_ADBKOACAO
#define SVC_CERRAMERET_TEC  800
#define SVC_CERRAMEBKO_TEC  801
#define SVC_CERRAMEFEC_TEC  802
#define SVC_WFAGLACAO       786
#define SVC_PO_DEVFILA      850
#define SVC_PO_NAOACOF      851
#define SVC_PO_ACOFTMP      852
#define SVC_PO_NACOFTMP     853
#define SVC_PO_INIRETE      854
#define SVC_PO_WAITCAN      855
#define SVC_PO_FECHAR       856
#define SVC_PO_EFETIVAR     857
#define SVC_PO_REAGENDAR    858
#define SVC_PO_ENCAMINHAR   859
#define SVC_PO_COMACAO      860
#define SVC_PO_CLINATENDEU  862
#define SVC_PO_ALTDADOSPRO  863
#define SVC_PO_ACEOFINTERC  866
#define SVC_PO_ACEOFIVR     867
#define SVC_PO_RECOFINTERC  868
#define SVC_PO_RECOFIVR     869

#define SVC_PO_ATDATUOVACAO 871
#define SVC_PO_ATDCANOVACAO 872
#define SVC_PO_ATDGRVOVACAO 873
//#define SVC_DMCACAO        873

#define AtendimentoWorkflowVO "AtendimentoWorkflowVO"
#define LinhasAssociadasVO "LinhasAssociadasVO"

//============================================================================
// Serviços suportados pelo core workflow
//
TabServicos tabServicos[] =
{
   {SVC_ENCACAO         ,"ENCACAO"      ,"Encaminhar Processos InBox"},
   {SVC_AGDACAO         ,"AGDACAO"      ,"Agendar Processos"},
   {SVC_CERRAMERET      ,"CERRAMERET"   ,"Encerramento de Processo com Retorno Grupo de Retorno"},
   {SVC_CERRAMEBKO      ,"CERRAMEBKO"   ,"Encerramento de Processo com Retorno Grupo BKO"},
   {SVC_CERRAMEFEC      ,"CERRAMEFEC"   ,"Encerramento de Processo com Fechamento (inclusive em massa)"},
   {SVC_CERRAMERET_TEC  ,"CERRAMERET"   ,"Encerramento de Processo Técnico com Retorno Grupo de Retorno"},
   {SVC_CERRAMEBKO_TEC  ,"CERRAMEBKO"   ,"Encerramento de Processo Técnico com Retorno Grupo BKO"},
   {SVC_CERRAMEFEC_TEC  ,"CERRAMEFEC"   ,"Encerramento de Processo Técnico com Fechamento (inclusive em massa)"},
   {SVC_FECACAO         ,"FECHAMENTO"   ,"Fechamento"},
   {SVC_STCACAO         ,"STCACAO"      ,"Solicitacao de Cancelamento"},
   {SVC_CANACAO         ,"CANACAO"      ,"Cancelamento"},
   {SVC_ATDCONSANALISE  ,"ATDCONANALISE","Concluir Analise"},
   {SVC_MARCARSUSP      ,"MARCARSUSP"   ,"Marcar Atendimento Suspeito"},
   {SVC_INIACAO         ,"INIACAO"      ,"Inserir Insistencia"},
   {SVC_REPACAO         ,"REPACAO"      ,"Reincidencia de Processos"},
   {SVC_DTRACAO         ,"DTRACAO"      ,"Devolver a Tratamento (inclui REANALISE)"},
   {SVC_DBKACAO         ,"DBKACAO"      ,"Devolver a BKO ou a analista MC"},
   {SVC_COMACAO         ,"WFCOMACAO"    ,"Inserir Comentario"},
   {SVC_REABPROC        ,"REABPROC"     ,"Reabertura de Processos"},
   {SVC_ECRIACAO        ,"WFECRIACAO"   ,"Encaminha ao Cri"},
   {SVC_TRCACAO         ,"WFTRCACAO"    ,"Trocar Cri"},
   {SVC_ENBKOACAO       ,"WFENBKOACAO"  ,"Devolver de Cri a Bko"},
   {SVC_FINACAO         ,"WFFINACAO"    ,"Finalizar Analise para Cri"},
   {SVC_ADBKOACAO       ,"WFADBKOACAO"  ,"Adquirir Processos de Bko para Cri e C.Prévio"},
   {SVC_ADBKOACOMP      ,"WFADBKOACAO"  ,"Reaver Processo Cri em poder de Bko"},
   {SVC_ENCBKOCRI       ,"ENCACAO"      ,"vide ENCACAO acima"},
   {SVC_PBKOACAO        ,"WFPBKOACAO"   ,"Prosseguir a Bko"},
   {SVC_RESPCLIENTE     ,"RESPCLIENTE"  ,"Resposta ao Cliente (Contato Prévio)"},
   {SVC_WFAGLACAO       ,"WFAGLACAO"    ,"Associação de linhas a um processo"},
   {SVC_PO_DEVFILA      ,"POACDEVFILA"  ,"portout: Devolver processo de portout para a fila"},
   {SVC_PO_NAOACOF      ,"POACNAOACOF"  ,"portout: Não aceitar oferta de retenção"},
   {SVC_PO_ACOFTMP      ,"POACACOFTMP"  ,"portout: Aceitar oferta de retenção temporariamente"},
   {SVC_PO_NACOFTMP     ,"POACNACOFTMP" ,"portout: Não aceitar oferta de retenção temporariamente"},
   {SVC_PO_INIRETE      ,"POACINIRETE"  ,"portout: Iniciar retenção"},
   {SVC_PO_WAITCAN      ,"POACWAITCAN"  ,"portout: Aguardar cancelamento de processo"},
   {SVC_PO_FECHAR       ,"POACFECHAR"   ,"portout: Fechar processo "},
   {SVC_PO_EFETIVAR     ,"POACEFETIVAR" ,"portout: Efetivar"},
   {SVC_PO_REAGENDAR    ,"AGDACAO"      ,"portout: Reagendar processo"},
   {SVC_PO_ENCAMINHAR   ,"POACENCAMINHR","portout: Encaminhar para analista de retenção"},
   {SVC_PO_COMACAO      ,"WFCOMACAO"    ,"portout: Inserir Comentario"},
   {SVC_PO_CLINATENDEU  ,"POCLINATENDEU","portout: Cliente não atendeu"},
   {SVC_PO_ALTDADOSPRO  ,"POALTDADOSPRO","portout: SPN solicitou alteração de dados de processo de portout"},
   {SVC_PO_ACEOFINTERC  ,"POACEOFINTERC","portout: Aceita oferta de interceptação"},
   {SVC_PO_ACEOFIVR     ,"POACEOFIVR   ","portout: Aceita oferta de IVR"},
   {SVC_PO_RECOFINTERC  ,"PORECOFINTERC","portout: Recusar oferta de interceptação"},
   {SVC_PO_RECOFIVR     ,"PORECOFIVR   ","portout: Recusar oferta de IVR"},
   {SVC_PO_ATDATUOVACAO ,"ATDATUOVACAO" ,"Devolve XML com os dados da ordem de venda associada ao processo"},
   {SVC_PO_ATDCANOVACAO ,"ATDCANOVACAO" ,"Encerramento de Processo Técnico com Retorno Grupo BKO"},
   {SVC_PO_ATDGRVOVACAO ,"ATDGRVOVACAO" ,"Envia atualização de OV ao SAP e encerra o processo."},
   //{SVC_DMCACAO        , "DBKACAO"      ,"Devolver a analista Meu Cliente (usa a ação devolver BKO)"},
   {-1                  ,0              , 0}
};

//============================================================================
// Inicialização de atributos estáticos
//
int cWFCoreWorkflow::tuxSeqRemoteCall = 0;

//============================================================================
// Main business
//
cWFCoreWorkflow::cWFCoreWorkflow()
{
    memset(&dadosFluxoFase,0,sizeof(dadosFluxoFase));
    memset(&statusFluxoFase,-1,sizeof(statusFluxoFase));

    memset(&dadosFluxoFuncao,0,sizeof(dadosFluxoFuncao));
    memset(&statusFluxoFuncao,-1,sizeof(statusFluxoFuncao));

    idUsuarioCri = 0;
    idGrupoEncaminhamento = 0;

    pParser = new XercesDOMParser;
}

cWFCoreWorkflow::cWFCoreWorkflow(DOMNode *dnode, XMLGen *xml_g)
{
    entrada = dnode;
    saida = xml_g;

    memset(&dadosFluxoFase,0,sizeof(dadosFluxoFase));
    memset(&statusFluxoFase,-1,sizeof(statusFluxoFase));

    memset(&dadosFluxoFuncao,0,sizeof(dadosFluxoFuncao));
    memset(&statusFluxoFuncao,-1,sizeof(statusFluxoFuncao));

    idUsuarioCri = 0;
    idGrupoEncaminhamento = 0;

    pParser = new XercesDOMParser;
}

void cWFCoreWorkflow::Executar()
{
    ULOG_START("Executar()");

    SmallString mensagem;

    idFaseAtividade = ObterValorTag(entrada,"idFase");
    inCRI = ObterValorTag(entrada,"WFAcaoVO","inCRI");
    inRC = ObterValorTag(entrada,"WFAcaoVO","inRC"); // resposta ao cliente
    idAtividade = ObterValorTag(entrada,"WFAcaoVO","idAtividade");
    strcpy(dsTipoAtividade,ObterValorTag(entrada,"WFAcaoVO","dsTipoAtividade"));

    inAcaoPortout = false;
    if ( stricmp(dsTipoAtividade,"PORTOUT")==0 )
    {
        inAcaoPortout = true;
    }

    ULOG("inCRI=%d",inCRI);
    ULOG("inRC=%d",inRC);
    ULOG("Fase=%d",idFaseAtividade);
    ULOG("idAtividade=%d",idAtividade);
    ULOG("dsTipoAtividade=%s",dsTipoAtividade);

    if ( idAtividade < 1 )
    {
        throw new TuxBasicSvcException("00E0000","Tag 'idAtividade' nao informada ou informada com valor menor que 1.");
    }

    ProcessarDomAtdWFVO();

    VerificarMensagemFalha(mensagem);

    if ( mensagem.size() )
    {
        saida->clearAndDestroy();

        saida->createTag("WFAcaoRetornoVO");
        saida->addProp("xmlns", "workflow.fo.vivo.com.br/vo");
            saida->addItem("acaoExecucao","N");
            saida->addItem("mensagem",(char*)mensagem.c_str());
            saida->addItem("urlDestino","1");
        saida->closeTag();
    }

    ULOG_END("Executar()");
}

void cWFCoreWorkflow::ProcessarDomAtdWFVO()
{
    ULOG_START("ProcessarDomAtdWFVO()");

    char *err=0;

    cWFAtendimento cwfAtendimento;
    DOMNode* dn;
    int index = 0;
    char hrContato[6];
    char *p;

    if ( p = tx.walkTree(entrada,"inFO",0),p )
    {
        isAnalistaFO = true;
        XMLString::release(&p);
    }
    else
    {
        isAnalistaFO = false;
    }
    ULOG("isAnalistaFO=%s",isAnalistaFO?"true":"false");

    // Se for agendamento, verifica a hora
    strncpy(hrContato,ObterValorTag(entrada,AtendimentoWorkflowVO,"hrContato"),sizeof(hrContato));

    if ( horaIsValida(hrContato) == false )
    {
        txtHoraInvalida = "Hora inválida";
        return;
    }

    // Recupera o IDAGRUPAMENTOESTADOTPFUTURO do processo, vindo da página WEB
    int idAgrupamentoEstadoTpfuturo =
                ObterValorTag(entrada,AtendimentoWorkflowVO,"idAgrupamentoEstadoTpfuturo");
    ULOG("idAgrupamentoEstadoTpfuturo=%d",idAgrupamentoEstadoTpfuturo);

    // Recupera o IDPESSOAUSUARIO do destino da ACAO, vindo da página WEB
    idPessoaUsuarioDestino =
                ObterValorTag(entrada,AtendimentoWorkflowVO,"idPessoaUsuario");
    ULOG("idPessoaUsuarioDestino=%d",idPessoaUsuarioDestino);

    if ( idPessoaUsuarioDestino )
    {
        if ( UsuarioAtivoSimNao(idPessoaUsuarioDestino) == false )
        {
            txtUsuarioDestinoInativo = "Usuário destino inativo. Ação solicitada não pode ser executada";
            return;
        }
    }

    // Recupera o IDMOTIVO da ACAO, vindo da página WEB
    idMotivo = ObterValorTag(entrada,AtendimentoWorkflowVO,"idMotivo");
    ULOG("idMotivo=%d",idMotivo);

    // Recupera o IDGRUPO do destino da ACAO, vindo da página WEB
    idGrupoEncaminhamento =
                ObterValorTag(entrada,AtendimentoWorkflowVO,"idGrupo");
    ULOG("idGrupoEncaminhamento=%d",idGrupoEncaminhamento);

    // Se grupo destino não fornecido cada processo será mantido no seu próprio grupo atual
    grupoEncaminhamentoFornecido = idGrupoEncaminhamento ? true : false;

    // Recupera o IDPESSOAUSUARIO do usuário WEB que estiver executando a ação no momento
    idPessoaUsuarioWeb = 
                obterIdUsuario();
    ULOG("idPessoaUsuarioWeb=%d",idPessoaUsuarioWeb);

    if ( idPessoaUsuarioWeb == 0 )
    {
        txtUsuarioCorrenteNaoInformado = "Usuário corrente não informado.";
        return;
    }

    // Verifica se usuário WEB ainda esta ATIVO
    if ( UsuarioAtivoSimNao(idPessoaUsuarioWeb) == false )
    {
        txtUsuarioWebInativo = "Usuário inativo. Ação solicitada não pode ser executada";
        return;
    }

    while ( (dn = tx.walkDOM(entrada,"AtdWFVO",index++)) )
    {
        if ( idAtendimento = ObterValorTag(dn,"idAtendimento"),idAtendimento < 1 )
        {
            txtTagAtendimentoErr = "Tag 'idAtendimento' nao informada ou informada com valor menor que 1.";
            return;
        }

        if ( idAtividade != INSERIR_INSISTENCIA_I && idAtividade != CANCELAR_C )
        { // Estas atividades acima são executadas pelo analista FO e este não precisa fazer parte do grupo.
            bool isUsuarioGrupoMC = proCIsGrupoUsuarioMC(idAtendimento,idPessoaUsuarioWeb);

            if ( isUsuarioGrupoMC == false )
            {
                txtUsuarioGrupoInvalido = "Usuário não faz parte do grupo atual deste processo MeuCliente.";
                return;
            }
        }

        // se processo for CRI não pode marcar como suspeito
        if ( idAtividade == SUSPEITO_S )
        {
            if( proCAtendimentoCri(idAtendimento) )
            {
                if ( txtAcaoInibidaCRI.size() ) txtAcaoInibidaCRI += ",";
                txtAcaoInibidaCRI += idAtendimentoProtocolo;
                continue;
            }
        }

        // Grupo atual em que o processo esta
        idGrupoAtual = ObterGrupoAtual(idAtendimento);

        // Numero do Procotolo do processo
        idAtendimentoProtocolo[0] = 0;
        ObterNrProtocolo(idAtendimento);

        // Se grupo de encaminhamento não foi fornecido então irá manter o mesmo
        // grupo atual do processo, caso a ação faça execute trafego entre grupos.
        if ( grupoEncaminhamentoFornecido == false && AcaoTrafegaGrupo() )
        {
            idGrupoEncaminhamento = idGrupoAtual;
            ULOG("idGrupoEncaminhamento (mesmo que grupo atual)=%d",idGrupoEncaminhamento);
        }

        idAgEstTpPFtAt = idAgEstTpPFt = idAgrupamentoEstadoTpfuturo;

        if ( !cwfAtendimento.ObtemDetalheAtend(idAtendimento,&detalheAtendimento) )
        {
            err = "Número do processo informado não existe na base do FrontOffice";
            ULOGE("erro=%s",err);

            throw new TuxBasicSvcException("00E0000",err);
        }

        // Verifica se a ação acionada ainda esta disponível para o processo
        if ( AtividadeDisponivelSimNao() == false )
        {
            if ( txtAcaoIndisponivel.size() ) txtAcaoIndisponivel += ",";
            txtAcaoIndisponivel += idAtendimentoProtocolo;
            continue;
        }

        if ( idAtividade == ENCAMINHAR_E && memcmp(detalheAtendimento.sgFluxoAtendimento, "MC2", 3 ) == 0 && detalheAtendimento.idFase == 3 )
        {
            ULOG( "MEU CLIENTE 2" );
            if( proCObterInSupervisor( idGrupoEncaminhamento,idUsuario ) != STU_USUARIO_SUPERVISOR )
            {
                txtUsuarioNaoAdministrador = "Usuário não é administrador, não será possível encaminhar o processo.";
                continue;
            }
        }
        
        idUsuarioCri = ObterIdUsuarioCriProcesso();

        if ( inCRI )
        {
            // Verifica se usuario ainda esta ATIVO
            if ( idUsuarioCri > 0 )
            {
                if ( idAtividade != REANALISE_RE && idAtividade != ENCAMINHAR_E )
                {
                    if ( UsuarioAtivoSimNao(idUsuarioCri) == false )
                    {
                        if ( txtUsuarioInativo.size() ) txtUsuarioInativo += ",";
                        txtUsuarioInativo = idAtendimentoProtocolo;
                        continue;
                    }
                }
            }

            // Não verifica se usuário pertence ao grupo ou se é supervisor se
            // for analista de FrontOffice.
            if ( isAnalistaFO == false )
            {
                if ( idAtividade != REANALISE_RE )
                {
                    if ( strcmp(detalheAtendimento.sgTipoPortabilidade,"PORTOUT") !=0 )
                    { // se é processo de portout não verifica a situação do usuário
                        // Obtém a situação atual do usuário em relação ao grupo atual do processo
                        int situacaoUsuario = VerificarSituacaoUsuario(idPessoaUsuarioWeb);

                        if ( situacaoUsuario == STU_USUARIO_ESTRANHO_AO_GRUPO )
                        {
                            if ( txtUsuarioNaoFazParteGrupo.size() ) txtUsuarioNaoFazParteGrupo += ",";
                            txtUsuarioNaoFazParteGrupo += idAtendimentoProtocolo;
                            continue;
                        }

                        // Se o usuário que estiver executando ação não for o mesmo que estiver com o processo
                        // no inbox neste momento, então ele tem de ser supervisor do grupo, com exceção de quando
                        // o usuário estiver adquirindo processos específicos para o seu inbox.
                        if ( idUsuarioCri != idPessoaUsuarioWeb 
                           && idAtividade != ADQUIRIR_PROCESSO_ADCRI )
                        {
                            if ( situacaoUsuario == STU_USUARIO_COMUM )
                            {
                                if ( txtFalhaPermissao.size() ) txtFalhaPermissao += ",";
                                txtFalhaPermissao += idAtendimentoProtocolo;
                                continue;
                            }
                        }
                    }
                }
            }
        }
        else
        {
            cWFAtendimentoUsuarioAtual AtendimentoUsuarioAtual;

            long idPessoaUsuarioAtual = AtendimentoUsuarioAtual.obterIdPessoaUsuarioAtual(idAtendimento);

            ULOG("idPessoaUsuarioAtual=%d",idPessoaUsuarioAtual);

            if ( idAtividade != REANALISE_RE && idAtividade != ENCAMINHAR_E )
            {
                // Verifica se usuario ainda esta ATIVO
                if ( UsuarioAtivoSimNao(idPessoaUsuarioAtual) == false )
                {
                    if ( txtUsuarioInativo.size() ) txtUsuarioInativo += ",";
                    txtUsuarioInativo = idAtendimentoProtocolo;
                    continue;
                }
            }

            // Não verifica se usuário pertence ao grupo ou se é supervisor se
            // for analista de FrontOffice.
            if ( isAnalistaFO == false )
            {
                // Se usuário não pertencer ao grupo atual do processo ele não pode atuar
                if ( idAtividade != REANALISE_RE )
                {
                    if ( strcmp(detalheAtendimento.sgTipoPortabilidade,"PORTOUT") !=0 )
                    { // se é processo de portout não verifica a situação do usuário
                        // Obtém a situação atual do usuário em relação ao grupo atual do processo
                        int situacaoUsuario = VerificarSituacaoUsuario(idPessoaUsuarioWeb);

                        if ( situacaoUsuario == STU_USUARIO_ESTRANHO_AO_GRUPO )
                        {
                            if ( txtUsuarioNaoFazParteGrupo.size() ) txtUsuarioNaoFazParteGrupo += ",";
                            txtUsuarioNaoFazParteGrupo += idAtendimentoProtocolo;
                            continue;
                        }

                        // Se o usuário que estiver executando ação não for o mesmo que estiver com o processo
                        // no inbox neste momento, então ele tem de ser supervisor do grupo.
                        if ( !inRC && idPessoaUsuarioAtual != idPessoaUsuarioWeb )
                        {
                            if ( situacaoUsuario == STU_USUARIO_COMUM )
                            {
                                if ( txtFalhaPermissao.size() ) txtFalhaPermissao += ",";
                                txtFalhaPermissao += idAtendimentoProtocolo;
                                continue;
                            }
                        }
                    }
                }
            }
        }

        ProcessarFluxoAtd();

    } // while ...

    ULOG_END("ProcessarDomAtdWFVO()");
}

void cWFCoreWorkflow::ProcessarFluxoAtd()
{
    ULOG_START("ProcessarFluxoAtd()");

    cWFFluxoFase cwfFluxoFase;
    int tamXML;
    bool grupoEncaminhamentoIsCRI;
    XMLGen xmlObtemFluxo;

    if ( strcmp(detalheAtendimento.sgTipoPortabilidade,"PORTOUT") !=0 )
    { // se for processo de portout não tem regra de encaminhamento
        grupoEncaminhamentoIsCRI = GrupoCRISimNao(idGrupoEncaminhamento);

        // --> Incidência 3315/3316 de WF no TD de Homologação Vivo (ATENA/Front_OfficeA1)
        if ( idAtividade == ENCAMINHAR_E || idAtividade == AGENDAR_AG || 
             idAtividade == AGENDAR_AR )
        {
            // Se o grupo de encaminhamento for o mesmo que o grupo atual, não faz a verificação.
            if ( idGrupoEncaminhamento && idGrupoEncaminhamento != idGrupoAtual )
            {
                // Se o grupo de destino for CRI não esta configurado na aba de fases de tratamento
                // e não faz a verificação.
                if ( grupoEncaminhamentoIsCRI == false )
                {
                    if ( detalheAtendimento.idTipoRetornoContato == TP_RET_SEM_RETORNO 
                      && (detalheAtendimento.idAgrEstadoTProc >  1 && detalheAtendimento.idAgrEstadoTProc <  6 || 
                          detalheAtendimento.idAgrEstadoTProc > 14 && detalheAtendimento.idAgrEstadoTProc < 19) ) // 'EM TRATAMENTO'
                    {
                        if ( !GrupoEmFaseTratamentoSimNao(idGrupoEncaminhamento,detalheAtendimento.idContato) )
                        {
                            if ( txtGrupoNaoTratamento.size() ) txtGrupoNaoTratamento += ",";
                            txtGrupoNaoTratamento += idAtendimentoProtocolo;

                            ULOG_END("ProcessarFluxoAtd()");

                            return;
                        }
                    }
                    else if ( (detalheAtendimento.idAgrEstadoTProc >  5 && detalheAtendimento.idAgrEstadoTProc <  8 || 
                               detalheAtendimento.idAgrEstadoTProc > 18 && detalheAtendimento.idAgrEstadoTProc < 21) ) // 'EM RETORNO'
                    {
                        if ( detalheAtendimento.idTipoRetornoContato == TP_RET_COM_RET_GRP_RET ) //Alteracao conforme incidencia 3440
                        {
                            if ( !GrupoEmFaseRetornoSimNao(idGrupoEncaminhamento,detalheAtendimento.idContato) )
                            {
                                if ( txtGrupoNaoRetorno.size() ) txtGrupoNaoRetorno += ",";
                                txtGrupoNaoRetorno += idAtendimentoProtocolo;

                                ULOG_END("ProcessarFluxoAtd()");

                                return;
                            }
                        }
                        else if ( detalheAtendimento.idTipoRetornoContato == TP_RET_COM_RET_GRP_BKO ) //Alteracao conforme incidencia 3440
                        {
                            if ( !(GrupoEmFaseTratamentoSimNao(idGrupoEncaminhamento,detalheAtendimento.idContato) 
                               || GrupoEmFaseRetornoSimNao(idGrupoEncaminhamento,detalheAtendimento.idContato)) )
                            {
                                if ( txtGrupoNaoTratRetorno.size() ) txtGrupoNaoTratRetorno += ",";
                                txtGrupoNaoTratRetorno += idAtendimentoProtocolo;

                                ULOG_END("ProcessarFluxoAtd()");

                                return;
                            }
                        }
                    }
                } // if ( grupoEncaminhamentoIsCRI == false )
            } // if ( idGrupoEncaminhamento != idGrupoAtual )
        }
        // <-- Incidência 3315 de WF no TD de Homologação Vivo (ATENA/Front_OfficeA1)
    }

    if ( idAtividade == ENCERRAR_BKO_MASSA_EBM )
    {
        if (detalheAtendimento.idTipoRetornoContato == TP_RET_COM_RET_GRP_BKO)
        {
            idAtividadeFl = ENCERRAR_BKO_EB;
        }
        else if (detalheAtendimento.idTipoRetornoContato == TP_RET_COM_RET_GRP_RET)
        {
            idAtividadeFl = ENCERRAR_BKO_EBR;
        }
        else
        {
            idAtividadeFl = ENCERRAR_BKO_MASSA_EBM;
        }
    }
    else
    {
        idAtividadeFl = idAtividade;
    }
    
    dadosFluxoFase.idAtividade = idAtividadeFl;
    dadosFluxoFase.idAtendimento = idAtendimento;
    dadosFluxoFase.idContato = detalheAtendimento.idContato;
    dadosFluxoFase.idPessoaUsuario = idPessoaUsuarioWeb;
    dadosFluxoFase.idAgrupamentoEstadoTpProc = detalheAtendimento.idAgrEstadoTProc;
    dadosFluxoFase.idAgrupamentoEstadoTpProcFt = idAgEstTpPFtAt;
    dadosFluxoFase.inCri = inCRI;
    dadosFluxoFase.inRC = inRC;
    dadosFluxoFase.inPortout = inAcaoPortout ? 1 : 0;
    dadosFluxoFase.idUsuarioCri = idUsuarioCri;

    statusFluxoFase.idAtendimento = 1;
    statusFluxoFase.idAtividade = 1;
    statusFluxoFase.idContato = 1;
    statusFluxoFase.idPessoaUsuario = 1;
    statusFluxoFase.idAgrupamentoEstadoTpProc = 1;
    statusFluxoFase.idAgrupamentoEstadoTpProcFt = 1;
    statusFluxoFase.inCri = 1;
    statusFluxoFase.inRC = dadosFluxoFase.inRC ? 1 : -1;
    statusFluxoFase.inPortout = inAcaoPortout ? 1 : -1;
    statusFluxoFase.idUsuarioCri = 1;

    ULOG("idAtividade= %d (xml)", idAtividade); // vindo do xml de entrada
    ULOG("idAtividade= %d", dadosFluxoFase.idAtividade);
    ULOG("idContato= %d", dadosFluxoFase.idContato);
    ULOG("idPessoaUsuario= %d", dadosFluxoFase.idPessoaUsuario);
    ULOG("idAgrupamentoEstadoTpProc= %d", dadosFluxoFase.idAgrupamentoEstadoTpProc);
    ULOG("idAgrupamentoEstadoTpProcFt= %d", dadosFluxoFase.idAgrupamentoEstadoTpProcFt);
    ULOG("inCRI= %d", dadosFluxoFase.inCri);
    ULOG("inRC= %d", dadosFluxoFase.inRC);
    ULOG("inPortout= %d", dadosFluxoFase.inPortout);
    ULOG("idUsuarioCri= %d", dadosFluxoFase.idUsuarioCri);
    ULOG("idTipoRetornoContato= %d", detalheAtendimento.idTipoRetornoContato);

    if ( idFaseAtividade <= 0 )
    {
        if ( grupoEncaminhamentoFornecido )
        { // se um grupo de encaminhamento foi fornecido no XML, então é uma ação de tráfego entre grupos 
            bool retorno = true;

            if ( inCRI )
            {
                if ( idAtividade == ENCAMINHAR_CRI_ECRI || idAtividade == TROCAR_CRI_TRCRI )
                { // verifica se o grupo selecionado no combo ainda esta associado ao contato
                    retorno = proCObterGrupoCri( detalheAtendimento.idContato,idGrupoEncaminhamento );
                }
            }
            else if ( inRC == 0 )
            {
                if ( (idAtividade != AGENDAR_AG && idAtividade != AGENDAR_AR 
                   && idAtividade != ENCAMINHAR_E) || idGrupoEncaminhamento != idGrupoAtual )
                {
                    retorno = 
                        proCObterGrupoFase( detalheAtendimento.idContato
                                          , idGrupoEncaminhamento
                                          , detalheAtendimento.idFase );

                    if ( retorno == false )
                    {
                        if ( detalheAtendimento.idFase == RETORNO )
                        {
                            // Se o processo estiver em fase 3 (RETORNO) e não existirem grupos de 
                            // de retorno configurados, os grupos a serem obtidos devem ser os da
                            // fase TRATAMENTO para que sejam obtidos os grupos de tratamento do processo.
                            retorno = 
                                proCObterGrupoFase( detalheAtendimento.idContato
                                                  , idGrupoEncaminhamento
                                                  , TRATAMENTO );
                        }
                    }
                }
            }

            if  ( !retorno )
            {
                ULOG("Operacao nao permitida para o "
                      "idAtendimento=%d do idGrupoEncaminhamento=%d"
                      ,idAtendimento,idGrupoEncaminhamento);

                if ( txtSemGrupoFase.size() ) txtSemGrupoFase += ",";
                txtSemGrupoFase += idAtendimentoProtocolo;

                ULOG_END("ProcessarFluxoAtd()");

                return;
            }
        }
    }

    // No caso do grupo de destino ser CRI o usuário não precisa ter skill para atender.
    // Se for ação de trafego de processo, verifica se o usuário possui skill e caso
    // possua verifica se o skill permite a recepção do processo
    if ( idGrupoEncaminhamento && grupoEncaminhamentoIsCRI == false 
      && (idAtividade == ENCAMINHAR_E || 
          idAtividade == AGENDAR_AG || 
          idAtividade == AGENDAR_AR ||
          idAtividade == DEVOLVER_BKO_D ||
          idAtividade == DEVOLVER_BKO_DR ||
          idAtividade == DEVOLVER_BKO_DCRI )
       )
    {
        ULOG("ProcessarFluxoAtd():proCUsuarioTemSkill:Antes de verificar o Skill -idAtividade[%d] idPessoaUsuarioDestino[%d] idGrupoEncaminhamento[%d]" ,idAtividade, idPessoaUsuarioDestino, idGrupoEncaminhamento );
        if ( proCUsuarioTemSkill( idPessoaUsuarioDestino, idGrupoEncaminhamento ) )
        {
            ULOG("ProcessarFluxoAtd():proCUsuarioTemSkill:Usuario tem Skill, vai verificar se tem acesso para este processo: idAtendimento[%d]" , idAtendimento);
            // Esta validacao poderia ser realizada na pesquisa acima, por questoes de performance, pois esta
            // verificacao eh razoavelmente custosa, ela soh eh realizada se o usuario contiver skill
            if( !proCUsuarioTemAcesso( idPessoaUsuarioDestino, idGrupoEncaminhamento, idAtendimento ) )
            {
                if ( txtSemSkillParaEncaminhar.size() ) txtSemSkillParaEncaminhar += ", ";
                txtSemSkillParaEncaminhar += idAtendimentoProtocolo;

                ULOG("ProcessarFluxoAtd():proCUsuarioTemAcesso():Skill de usuario NAO permite operacao");
                return;
            }
            ULOG("ProcessarFluxoAtd():proCUsuarioTemAcesso():Skill de usuario PERMITE operacao");
        }
        else
        {
            ULOG("ProcessarFluxoAtd():proCUsuarioTemSkill():usuario SEM Skill");
        }
    }

    if (idFaseAtividade == TRATAMENTO)
    {
        if ( strcmp(detalheAtendimento.sgTipoPortabilidade,"PORTOUT") !=0 )
        {
            ULOG("Obtemos Fluxo sem ter em conta o usuario");

            if ( !cwfFluxoFase.ObtemWFFluxoAtividade(&dadosFluxoFase,&statusFluxoFase,&xmlObtemFluxo) )
            {
                ULOG_END("ProcessarFluxoAtd()");
                throw new TuxBasicSvcException("00E0000","cWFFluxoFase::ObtemWFFluxoAtividade() falhou execucao");
            }
        }
        else
        {
            ULOG("Obtemos Fluxo sem ter em conta o usuario para processo de portout");

            if ( !cwfFluxoFase.ObtemWFFluxoAtividadePOut(&dadosFluxoFase,&statusFluxoFase,&xmlObtemFluxo) )
            {
                ULOG_END("ProcessarFluxoAtd()");
                throw new TuxBasicSvcException("00E0000","cWFFluxoFase::ObtemWFFluxoAtividadePOut() falhou execucao");
            }
        }
    }
    else
    {
        if ( idAgEstTpPFtAt )
        {
            ULOG("Obtemos Fluxo tendo em conta o usuario (1)");

            dadosFluxoFase.idFase = idFaseAtividade == 1 ? ABERTURA : TRATAMENTO;
            statusFluxoFase.idFase = 1;

            ULOG("idFaseAtividade=%d",idFaseAtividade);

            ULOG("idFase=%d",dadosFluxoFase.idFase);

            if ( !cwfFluxoFase.ObtemWFFluxoFt(&dadosFluxoFase,&statusFluxoFase,&xmlObtemFluxo) )
            {
                ULOG_END("ProcessarFluxoAtd()");
                throw new TuxBasicSvcException("00E0000","cWFFluxoFase::ObtemWFFluxoFt() falhou execucao");
            }

            // se estiver adquirindo processo para resp.cliente, e fluxoFt não retornou
            // nada, tenta obter em fluxo atual
            char *_xmlObtemFluxo = xmlObtemFluxo.retrieveXML(&tamXML);
            if ( !tamXML && dadosFluxoFase.inRC && dadosFluxoFase.idAtividade == ADQUIRIR_PROCESSO_ADRC )
            {
                if ( !cwfFluxoFase.ObtemWFFluxo(&dadosFluxoFase,&statusFluxoFase,&xmlObtemFluxo) )
                {
                    ULOG_END("ProcessarFluxoAtd()");
                    throw new TuxBasicSvcException("00E0000","cWFFluxoFase::ObtemWFFluxo() falhou execucao");
                }
            }
        }
        else
        {
            ULOG("Obtemos Fluxo tendo em conta o usuario (2)");

            if ( !cwfFluxoFase.ObtemWFFluxo(&dadosFluxoFase,&statusFluxoFase,&xmlObtemFluxo) )
            {
                ULOG_END("ProcessarFluxoAtd()");
                throw new TuxBasicSvcException("00E0000","cWFFluxoFase::ObtemWFFluxo() falhou execucao");
            }
        }
    }

    char *_xmlObtemFluxo = xmlObtemFluxo.retrieveXML(&tamXML);

    ULOG("Fluxo Recuperado=%s",_xmlObtemFluxo);
    ULOG("tamXML=%d",tamXML);

    if ( !tamXML )
    {
        ULOG("Operacao nao permitida para idAtendimento=%d",idAtendimento);

        if ( txtOperNaoPermitida.size() ) txtOperNaoPermitida += ",";
        txtOperNaoPermitida += idAtendimentoProtocolo;

        ULOG_END("ProcessarFluxoAtd()");
        return;
    }

    strRecorFluxo = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>";
    strRecorFluxo += "<Fluxo>";
    strRecorFluxo += _xmlObtemFluxo;
    strRecorFluxo += "</Fluxo>";

    strRecorScTr = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>"
                   "<scrTratados><script><idRouterScript>0"
                   "</idRouterScript></script></scrTratados>";

    ULOG("strRecorFluxo=%s",strRecorFluxo.c_str());

    SubProcessarFluxoAtd();

    ULOG_END("ProcessarFluxoAtd()");
}

void cWFCoreWorkflow::SubProcessarFluxoAtd()
{
    ULOG_START("SubProcessarFluxoAtd()");

    bool retorno = true;
    char *err=0;
    XMLGen saidaFF;

    ULOG("Processando fluxo para idAtendimento=%d",idAtendimento);

    int tamSaida = strRecorFluxo.size();

    //MemBufInputSource *pMemBuf = new MemBufInputSource((const XMLByte*)strRecorFluxo.c_str(),tamSaida,gerarIDDom());
    MemBufInputSource pMemBuf((const XMLByte*)strRecorFluxo.c_str(),tamSaida,gerarIDDom());

    //if ( pParser && pMemBuf )
    if ( pParser )
    {
        //pParser->parse(*pMemBuf);
        pParser->parse(pMemBuf);
        DOMNode* pDoc = pParser->getDocument();
        DOMNode* dn;
        int index=0;
        int idAgEstTpPFtLocal;
        int tamXML;
        char *p;

        while ( dn = tx.walkDOM(pDoc,"WFAcoesVO",index++ ),dn )
        {
            idAgEstTpPFtLocal=idAgEstTpPFt;

            if ( !idAgEstTpPFtLocal )
            {
                p = tx.walkTree(dn,"idAgEstTpProcFt",0);
                idAgEstTpPFtLocal = atoi(p);
                XMLString::release(&p);
            }

            strDomScr = "<WFExecucao>";
            strDomScr += "\n\t<ScriptExecucaoVO>";
            strDomScr += "\n\t\t<idAtendimento>";
            strDomScr += idAtendimento;
            strDomScr += "</idAtendimento>";
            strDomScr += "\n\t\t<idTipoRetornoContato>";
            strDomScr += detalheAtendimento.idTipoRetornoContato;
            strDomScr += "</idTipoRetornoContato>";
            strDomScr += "\n\t\t<idFase>";
            strDomScr += detalheAtendimento.idFase;
            strDomScr += "</idFase>";
            strDomScr += "\n\t\t<inCRI>";
            strDomScr += inCRI;
            strDomScr += "</inCRI>";
            strDomScr += "\n\t\t<inRC>";
            strDomScr += inRC;
            strDomScr += "</inRC>";
            strDomScr += "\n\t\t<idAtividade>";
            strDomScr += idAtividadeFl;
            strDomScr += "</idAtividade>";
            strDomScr += "\n\t\t<idAtividadeMassa>";
            strDomScr += idAtividade;
            strDomScr += "</idAtividadeMassa>";
            strDomScr += "\n<idAgrupamentoEstadoTProcFut>";
            strDomScr += idAgEstTpPFtLocal;
            strDomScr += "</idAgrupamentoEstadoTProcFut>";
            strDomScr += "\n\t\t<idAgrupamentoEstadoTProcAt>";
            strDomScr += detalheAtendimento.idAgrEstadoTProc;
            strDomScr += "</idAgrupamentoEstadoTProcAt>";
            strDomScr += "\n\t\t<idPessoaUsuario>";
            strDomScr += idPessoaUsuarioWeb;
            strDomScr += "</idPessoaUsuario>";
            strDomScr += "\n\t\t<nmUrlDestino>";

            if (idAtividade != idAtividadeFl)
            {
                strDomScr += "1";
            }
            else
            {
                if ( p = tx.walkTree(dn,"nmUrlDestino",0),p )
                {
                    strDomScr += p;
                    XMLString::release(&p);
                }
            }

            strDomScr += "</nmUrlDestino>";
            strDomScr += "\n\t</ScriptExecucaoVO>";

            p = tx.getNodeAsString(tx.walkDOM(entrada,AtendimentoWorkflowVO,0));

            if ( !p )
            {
                ULOG("AVISO: script de entrada "
                    "nao contem nó DOM '" AtendimentoWorkflowVO "' !");
            }
            else
            {
                strDomScr += p;
                free(p);
            }

            strDomScr += "\n</WFExecucao>";

            if ( p=tx.walkTree(dn,"idFluxoFase",0),p )
            {
                dadosFluxoFuncao.idFluxoFase = atoi(p);
                statusFluxoFuncao.idFluxoFase = 1;
                XMLString::release(&p);

                ULOG("vai consultar FluxoFuncao para idFluxoFase=%d",dadosFluxoFuncao.idFluxoFase);

                cWFFluxoFuncao cwfFluxoFuncao;

                if ( !cwfFluxoFuncao.consultar(&dadosFluxoFuncao,&statusFluxoFuncao,"numOrdem",&saidaFF) )
                {
                    err = "cWFFluxoFuncao::consultar() falhou execucao";
                    break;
                }

                xmlObtemScr = saidaFF.retrieveXML(&tamXML);

                saidaFF.retrieveXML(&tamXML);

                saidaFF.clearAndDestroy();

                if ( tamXML )
                {
                    strRecorScr = "<rScript>";
                    strRecorScr += xmlObtemScr;
                    strRecorScr += "</rScript>";

                    ExecutarRouterScript();
                }
                else
                {
                    ULOG("cWFFluxoFuncao::consultar() nao gerou xml de saida");

                    if ( txtFluxoFuncao.size() ) txtFluxoFuncao += ",";
                    txtFluxoFuncao += dadosFluxoFuncao.idFluxoFase;
                }
            }
            else
            {
                ULOG("id de fluxo fase nao fornecido");

                if ( txtFluxoFuncao.size() ) txtFluxoFuncao += ",";
                txtFluxoFuncao += "(idFluxo nao fornecido)";
            }
        }
    }
    else
    {
        err = "Nao foi possivel alocar memoria para objetos DOM e/ou Xerces";
    }

    //delete pMemBuf;

    if ( err )
    {
        ULOGE("%s",err);
        ULOG_END("SubProcessarFluxoAtd()");
        throw new TuxBasicSvcException("00E0000",err);
    }

    ULOG_END("SubProcessarFluxoAtd()");
}

void cWFCoreWorkflow::ExecutarRouterScript()
{
    ULOG_START("ExecutarRouterScript()");

    bool retorno = true;
    char *err=0;
    char *p;
    cWFFluxoFuncao cwfFluxoFuncao;

    int tamStrRecScr = strRecorScr.size();

    //MemBufInputSource *pMemBuf = new MemBufInputSource((const XMLByte*)strRecorScr.c_str(),tamStrRecScr,gerarIDDom());
    MemBufInputSource pMemBuf((const XMLByte*)strRecorScr.c_str(),tamStrRecScr,gerarIDDom());

    //if ( pParser && pMemBuf )
    if ( pParser )
    {
        //pParser->parse(*pMemBuf);
        pParser->parse(pMemBuf);
        DOMNode* pDoc = pParser->getDocument();
        DOMNode* dn;
        int index=0;

        while ( dn=tx.walkDOM(pDoc,"WFFluxoFuncaoVO",index++ ),dn )
        {
            if ( p = tx.walkTree(dn,"idRouterScript",0),p )
            {
                idRtScript = atoi(p);
                XMLString::release(&p);
            }
            else
            {
                idRtScript=0;
            }

            if ( VerificarTratarScript() == 0 )
            {
                int i=0;
                bool scrTratado = false;

                while ( tabServicos[i].idServico != -1 )
                {
                    if ( tabServicos[i].idServico == idRtScript )
                    {
                        ULOG("Vai executar remote call para %s:ID=%d (%s)"
                                                ,tabServicos[i].nomeServico
                                                ,idRtScript
                                                ,tabServicos[i].descricao);

                        Call(tabServicos[i].nomeServico,tabServicos[i].descricao);

                        strRecorScTr = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>";
                        strRecorScTr += "<scrTratados><script><idRouterScript>";
                        strRecorScTr += idRtScript;
                        strRecorScTr += "</idRouterScript></script></scrTratados>";

                        scrTratado = true;
                        retorno = false; // não irá permitir execução de multiplas funções...

                        break;
                    }

                    i++;
                }

                if ( !scrTratado )
                {
                    if ( txtSemRegistro.size() ) txtSemRegistro += ",";
                    txtSemRegistro += idRtScript;

                    ULOG("Script ID '%d' nao esta registrado no CoreWorkflow",idRtScript);
                }
            }
            else
            {
                ULOG("idRtScript=%d nao foi tratado pelo CoreWorkflow",idRtScript);
            }

            if ( false == retorno )
            {
                break;
            }
        }
    }
    else
    {
        err = "Nao foi possivel alocar memoria para objetos DOM e/ou Xerces";
    }

    //delete pMemBuf;

    if ( err )
    {
        ULOGE("%s",err);
        ULOG_END("ExecutarRouterScript()");
        throw new TuxBasicSvcException("00E0000",err);
    }

    ULOG_END("ExecutarRouterScript()");
}

int cWFCoreWorkflow::VerificarTratarScript()
{
    ULOG_START("VerificarTratarScript()");

    char *err=0;
    char *p;
    int retorno = 0;
    //cWFFluxoFuncao cwfFluxoFuncao;

    int tamStrRecScr = strRecorScTr.size();

    if ( tamStrRecScr )
    {
        //MemBufInputSource *pMemBuf = new MemBufInputSource((const XMLByte*)strRecorScTr.c_str(),tamStrRecScr,gerarIDDom());
        MemBufInputSource pMemBuf((const XMLByte*)strRecorScTr.c_str(),tamStrRecScr,gerarIDDom());

        //if ( pParser && pMemBuf )
        if ( pParser )
        {
            //pParser->parse(*pMemBuf);
            pParser->parse(pMemBuf);
            DOMNode* pDoc = pParser->getDocument();
            DOMNode* dn;
            int index=0;

            while ( !retorno && (dn = tx.walkDOM(pDoc,"script",index++ )) != 0 )
            {
                idRtScTra=0;
                if ( p = tx.walkTree(dn,"idRouterScript",0),p )
                {
                    idRtScTra = atoi(p);
                    XMLString::release(&p);
                }

                if ( idRtScript == idRtScTra )
                {
                    retorno = 1;
                }
            }
        }
        else
        {
            err = "Nao foi possivel alocar memoria para objetos DOM e/ou Xerces";
        }

        //delete pMemBuf;

        if ( err )
        {
            ULOGE("%s",err);
            throw new TuxBasicSvcException("00E0000",err);
        }
    }

    ULOG_END("VerificarTratarScript()");
    
    return retorno;
}

void cWFCoreWorkflow::Call(char *labelServico,const char *nomeDoServico)
{
    ULOG_START("Call()");

    TuxRemoteService *rc = new TuxRemoteService;

    if ( !rc )
    {
        char *err = "Erro na alocacao de objeto TuxRemoteService";
        ULOGE("%s",err);
        throw new TuxBasicSvcException("00E0000",err);
    }

    TuxMessage *tmIn = new TuxMessage();

    if ( !tmIn )
    {
        delete rc;

        char *err = "Erro na alocacao de objeto TuxMessage";
        ULOGE("%s",err);
        throw new TuxBasicSvcException("00E0000",err);
    }

    try 
    {
        sprintf( m_stDados.sequence,"%d",cWFCoreWorkflow::tuxSeqRemoteCall++ );

        tmIn->setService( labelServico );
        tmIn->setUser(obterIdUsuarioStr());
        tmIn->setSequence( m_stDados.sequence );
        tmIn->setMessageBody( (char*)strDomScr.c_str() );

        rc->setServiceName( labelServico );
        rc->setInputMessage( tmIn );

        ULOGE("TuxRemoteService::remoteCall chamando servico '%s' (%s)"
                ,labelServico,nomeDoServico);

        if ( rc->remoteCall() != TUXFWRET_OK )
        {
            SmallString err;

            err = "Erro na chamada ao servico ";
            err += labelServico;
            err += " (";
            err += nomeDoServico;
            err += ")";

            throw new TuxException("00E0000",(char*)err.c_str());
        }

        TuxMessage* outTm = rc->getOutputMessage();

        char *codigoRetorno = outTm->getStatusCode();
        char *statusText = outTm->getStatusText();
        char *msgBody = outTm->getMessageBody();

        ULOG("messageBody='%s'",msgBody);

        if ( *(codigoRetorno+2) == 'E' )
        {
            ULOGE("servico %s (%s) retornou status code =\"%c\""
                ,labelServico,nomeDoServico,*(codigoRetorno+2));

            throw new TuxException(codigoRetorno,statusText);
        }
        else
        {
            // wdnode = remoteService->getOutputMessage()->getMessageBodyDOM();
            // saida->clearAndDestroy();
            // saida->aggregateXML(outTm->getMessageBody();

            // ULOG("xml retornado pelo servico=%s",msgBody);

            /*
                Esta parte do código se fez necessária pois aparentemente a
                operação getMessageBody() esta gerando uma tag <msgBody>
                no xml de saida além da gerada pelo serviço chamado, gerando
                um xml de saida parecido com o mostrado abaixo. Por isso o
                open/close da primeira tag foram removidos no código abaixo.

                "<msgBody>"
                    "<msgBody>"
                        "<WFAcaoRetornoVO xmlns=\"workflow.fo.vivo.com.br/vo\">"
                            "<acaoExecucao>S</acaoExecucao>"
                            "<mensagem>Encaminhamento Concluido</mensagem>"
                            "<urlDestino>1</urlDestino>"
                        "</WFAcaoRetornoVO>"
                    "</msgBody>"
                "</msgBody>";
            */

            // Verificação se o servico terminou corretamente
            char *resultado;
            char *mensagem;

            resultado = ObterValorTag(msgBody, "acaoExecucao");

            if (strcmp(resultado, "M") == 0)
            {
                mensagem = ObterValorTag(msgBody, "mensagem");

                ULOG("Operacao não valida");

                if ( txtResultado.size() ) txtResultado += ",";
                txtResultado += mensagem;
                txtResultado += idAtendimentoProtocolo;
            }
            else
            {
                char *p0 = msgBody + strlen("<msgBody>");
                char *p1 = strstr(msgBody,"</msgBody>");

                int tam = p1 - p0;

                char *buffer = new char[tam+1];

                memcpy(buffer,p0,tam);
                buffer[tam] = 0;

                saida->clearAndDestroy();
                saida->aggregateXML(buffer);

                //ULOG("xml saida (sem <msgBody>)=%s",buffer);

                delete buffer;
            }
        }
    }
    catch (...)
    {
        delete rc;
        delete tmIn;

        ULOG_END("Call()");

        throw;
    }

    delete rc;
    delete tmIn;

    ULOG_END("Call()");

}

bool cWFCoreWorkflow::UsuarioAtivoSimNao(int idPessoaUsuario)
{
    ULOG_START("UsuarioAtivoSimNao()");

    if ( idPessoaUsuario == 0 )
    {
        ULOG("id não fornecido, assumindo usuario=ATIVO");
        ULOG_END("UsuarioAtivoSimNao()");
        return true; // se não possui um ID de usuário, assume como ATIVO
    }

    bool retorno;
    int idStatusUsuario,inDisponivelWF;

    if ( proCGetStatusDispUsuario(idPessoaUsuario,&idStatusUsuario,&inDisponivelWF) != -1 )
    {
        ULOG("UsuarioAtivoSimNao:status usuario=%d",idStatusUsuario);

        retorno = idStatusUsuario == USUARIO_ATIVO ? true : false;
    }
    else
    {
        ULOG("UsuarioAtivoSimNao:USUÁRIO NÃO ENCONTRADO.");
        retorno = false; // assume como INATIVO no caso de não encontrar o usuário
    }

    ULOG_END("UsuarioAtivoSimNao()");

    return retorno;
}

int cWFCoreWorkflow::VerificarSituacaoUsuario(int idPessoaUsuario)
{
    //int idGrupoAtual;

    //cWFAtendimentoGrupoAtual cwfAtendimentoGrupoAtual;

    //if ( !cwfAtendimentoGrupoAtual.ObtemGrAt(idAtendimento,&idGrupoAtual,NAO_GERAR_XML_OUT) )
    //{
    //    ULOG("usuario sem grupo. assumindo como supervisor");
    //
    //    return STU_USUARIO_SUPERVISOR;
    //}

    if ( idGrupoAtual == 0 )
    {
        ULOG("usuario sem grupo. assumindo como supervisor");

        return STU_USUARIO_SUPERVISOR;
    }

    int retorno = proCObterInSupervisor( idGrupoAtual,idPessoaUsuario );

    ULOG("tipo usuario=%d",retorno);

    return retorno;
}

int cWFCoreWorkflow::ObterIdUsuarioCriProcesso()
{
    st_AtdTratamentoCri dados;
    st_vlAtdTratamentoCri status;
    XMLGen saida;

    memset(&dados,0,sizeof(dados));
    memset(&status,-1,sizeof(status));

    dados.idAtendimento = idAtendimento;
    status.idAtendimento = 1;

    // Pesquisa em atendimento.atendimentoCRI pelo ID do usuário
    cWFAtdTratamentoCri cWFAtdTratamentoCri(&dados,&status,&saida);

    cWFAtdTratamentoCri.getAtendimentoCri();

    if ( status.idPessoaUsuario == -1 )
    {
        ULOG("processo nao esta em inbox de cri");
        return -1; // processo fora de inbox de cri
    }

    ULOG("idPessoaUsuario CRI=%d",dados.idPessoaUsuario);

    return dados.idPessoaUsuario; // id do cri atualmente resp.pelo processo
}

bool cWFCoreWorkflow::GrupoCRISimNao(int idGrupoEncaminhamento)
{
    ULOG_START("GrupoCRISimNao()");

    if ( idGrupoEncaminhamento == 0 )
    {
        ULOG("Grupo destino nao informado");
        ULOG_END("GrupoCRISimNao()");
        return true;
    }

    bool retorno = proCGrupoCRISimNao(idGrupoEncaminhamento);

    ULOG("retorno=%d",retorno);

    ULOG_END("GrupoCRISimNao()");

    return retorno;
}

bool cWFCoreWorkflow::ObterNrProtocolo(long idAtendimento)
{
    ULOG_START("ObterNrProtocolo()");

    cWFAtendimento cwfatendimento;

    bool retorno = cwfatendimento.ObterNrProtocolo(idAtendimento,idAtendimentoProtocolo);

    ULOG("protocolo = %s",retorno?idAtendimentoProtocolo:"NAO ENCONTROU");

    if ( false == retorno )
    { // se não encontrou assume numero do processo mesmo
        sprintf(idAtendimentoProtocolo,"%s",idAtendimento);
    }

    ULOG_END("ObterNrProtocolo()");

    return retorno;
}

int cWFCoreWorkflow::ObterGrupoAtual(long idAtendimento)
{
    ULOG_START("ObterGrupoAtual()");

    cWFAtendimentoGrupoAtual cwfatendimentogrupoatual;

    int retorno = cwfatendimentogrupoatual.ObterGrupoAtual(idAtendimento);

    ULOG("grupo atual=%d",retorno);

    ULOG_END("ObterGrupoAtual()");

    return retorno;
}

bool cWFCoreWorkflow::GrupoEmFaseTratamentoSimNao(int idGrupoEncaminhamento,int idContato)
{
    ULOG_START("GrupoEmFaseTratamentoSimNao()");

    // Analisa se o grupo de destino da ação esta configurado na fase de TRATAMENTO
    // na aba de fases da árvore de contato para a folha informada.
    bool retorno = proCGrupoEmFaseTratamentoSimNao(idGrupoEncaminhamento,idContato);

    ULOG_END("GrupoEmFaseTratamentoSimNao()");

    return retorno;
}

bool cWFCoreWorkflow::GrupoEmFaseRetornoSimNao(int idGrupoEncaminhamento,int idContato)
{
    ULOG_START("GrupoEmFaseRetornoSimNao()");

    // Analisa se o grupo de destino da ação esta configurado na fase de RETORNO
    // na aba de fases da árvore de contato para a folha informada.
    bool retorno = proCGrupoEmFaseRetornoSimNao(idGrupoEncaminhamento,idContato);

    ULOG_END("GrupoEmFaseRetornoSimNao()");

    return retorno;
}

bool cWFCoreWorkflow::GrupoEmFaseTratRetornoSimNao(int idGrupoEncaminhamento,int idContato)
{
    ULOG_START("GrupoEmFaseTratRetornoSimNao()");

    // Analisa se o grupo de destino da ação esta configurado na fase de TRATAMENTO ou RETORNO
    // na aba de fases da árvore de contato para a folha informada.
    bool retorno = proCGrupoEmFaseTratRetornoSimNao(idGrupoEncaminhamento,idContato);

    ULOG_END("GrupoEmFaseTratRetornoSimNao()");

    return retorno;
}

bool cWFCoreWorkflow::AtividadeDisponivelSimNao()
{
    ULOG_START("AtividadeDisponivelSimNao()");

    bool retorno;

    if ( inCRI )
    {
        retorno = proCAtividadeDisponivelCRISimNao(idAtividade,detalheAtendimento.idAgrEstadoTProc);
    }
    else if ( inRC )
    {
        retorno = proCAtividadeDisponivelRCSimNao(idAtividade,detalheAtendimento.idAgrEstadoTProc);
    }
    else if ( inAcaoPortout )
    {
        retorno = proCAtividadeDisponivelPOutSimNao(idAtividade,detalheAtendimento.idAgrEstadoTProc);
    }
    else
    {
        retorno = proCAtividadeDisponivelSimNao(idAtividade,detalheAtendimento.idAgrEstadoTProc,detalheAtendimento.idContato);
    }

    ULOG_END("AtividadeDisponivelSimNao()");

    return retorno;
}

bool cWFCoreWorkflow::AcaoTrafegaGrupo()
{
    ULOG_START("AcaoTrafegaGrupo()");

    bool retorno;

    switch (idAtividade)
    {
        case ADQUIRIR_PROCESSO_ADBKO:
        case ADQUIRIR_PROCESSO_ADCRI:
        case ADQUIRIR_PROCESSO_ADRC:
        case AGENDAR_AG:
        case AGENDAR_AR:
        case CONCLUIR_ANALISE_CA:
        case DEVOLVER_BKO_D:
        case DEVOLVER_BKO_DR:
        case DEVOLVER_BKO_DCRI:
        case ENCAMINHADO_CPREVIO_ERC:
        case ENCAMINHAR_E:
        case ENCAMINHAR_CRI_ECRI:
        case ENCERRAR_BKO_EB:
        case ENCERRAR_BKO_EBR:
        case ENCERRAR_BKO_EBS:
        case ENCERRAR_BKO_MASSA_EBM:
        case FECHAR_F:
        case FECHAR_FR:
        case FINALIZAR_ANALISE_FAN:
        case PROSSEGUIR_BKO_PBKO:
        case REGISTRAR_RC:
        case REGISTRAR_CPREVIO_RC:
        case TROCAR_CRI_TRCRI:
            retorno = true;
        break;

        default:
            retorno = false;
        break;
    }

    ULOG_END("AcaoTrafegaGrupo()");

    return retorno;
}

void cWFCoreWorkflow::VerificarMensagemFalha(SmallString &mensagem)
{
    ULOG_START("VerificarMensagemFalha()");

    if ( txtOperNaoPermitida.size() )
    {
        mensagem += "Operacao nao permitida para o(s) seguinte(s) protocolo(s): ";
        mensagem += txtOperNaoPermitida;
    }

    if ( txtUsuarioNaoFazParteGrupo.size() )
    {
        mensagem += "Usuário não faz mais parte do grupo. Ação solicitada não pode ser executada para o(s) seguinte(s) protocolo(s): ";
        mensagem += txtUsuarioNaoFazParteGrupo;
    }

    if ( txtFalhaPermissao.size() )
    {
        mensagem += "Usuário não é supervisor do grupo. Ação solicitada não pode ser executada para o(s) seguinte(s) protocolo(s): ";
        mensagem += txtFalhaPermissao;
    }

    if ( txtSemGrupoFase.size() )
    {
        // Incidência 4261 revogou as incidências 3315 e 3316 e formatou todas as mensagens 
        // que identificavam o problema em apenas uma. A Indra não concordou com a posição da VIVO
        // neste caso mas por imposição da área de TI (Antonio Paulo) executamos o solicitado.
        // Fev/2007 - Cassio
        //mensagem += "Processo(s) sem grupo fase encontrado(s): ";
        mensagem += "O grupo selecionado não esta configurado para o(s) protocolo(s) selecionado(s): ";
        mensagem += txtSemGrupoFase;
    }

    if ( txtSemRegistro.size() )
    {
        mensagem += "O(s) seguinte(s) script(s) nao tem registro no core: ";
        mensagem += txtSemRegistro;
    }

    if ( txtResultado.size() )
    {
        mensagem += txtResultado;
    }

    if ( txtFluxoFuncao.size() )
    {
        mensagem += "Não foi possível determinar que ação tomar para o(s) seguinte(s) fluxo(s): ";
        mensagem += txtFluxoFuncao;
    }

    if ( txtAcaoInibidaCRI.size() )
    {
        mensagem += "O(s) seguinte(s) protocolo(s) é/são CRI e não pode(m) ser marcado(s) como SUSPEITO(S): ";
        mensagem += txtAcaoInibidaCRI;
    }

    if ( txtAcaoIndisponivel.size() )
    {
        mensagem += "Ação solicitada não pode ser executada para o estado atual do(s) protocolo(s): ";
        mensagem += txtAcaoIndisponivel;
    }

    if ( txtUsuarioNaoAdministrador.size() )
    {
        mensagem += "Ação solicitada não pode ser executada: ";
        mensagem += txtUsuarioNaoAdministrador;
    }

    if ( txtSemSkillParaEncaminhar.size() )
    {
        mensagem += "Skill do usuario nao permite processar o(s) seguinte(s) protocolo(s): ";
        mensagem += txtSemSkillParaEncaminhar;
    }

    if ( txtGrupoNaoTratamento.size() )
    {
        // Incidência 4261 revogou as incidências 3315 e 3316 e formatou todas as mensagens 
        // que identificavam o problema em apenas uma. A Indra não concordou com a posição da VIVO
        // neste caso mas por imposição da área de TI (Antonio Paulo) executamos o solicitado.
        // Fev/2007 - Cassio
        //mensagem += "O grupo destino do(s) seguinte(s) protocolo(s) SEM RETORNO nao estao configurados para tratamento: ";
        mensagem += "O grupo selecionado não esta configurado para o(s) protocolo(s) selecionado(s): ";
        mensagem += txtGrupoNaoTratamento;
    }

    if ( txtGrupoNaoRetorno.size() )
    {
        // Incidência 4261 revogou as incidências 3315 e 3316 e formatou todas as mensagens 
        // que identificavam o problema em apenas uma. A Indra não concordou com a posição da VIVO
        // neste caso mas por imposição da área de TI (Antonio Paulo) executamos o solicitado.
        // Fev/2007 - Cassio
        //mensagem += "O grupo destino do(s) seguinte(s) protocolo(s) COM RETORNO nao estao configurados para RETORNO: ";
        mensagem += "O grupo selecionado não esta configurado para o(s) protocolo(s) selecionado(s): ";
        mensagem += txtGrupoNaoRetorno;
    }

    if ( txtGrupoNaoTratRetorno.size() )
    {
        // Incidência 4261 revogou as incidências 3315 e 3316 e formatou todas as mensagens 
        // que identificavam o problema em apenas uma. A Indra não concordou com a posição da VIVO
        // neste caso mas por imposição da área de TI (Antonio Paulo) executamos o solicitado.
        // Fev/2007 - Cassio
        //mensagem += "O grupo destino do(s) seguinte(s) protocolo(s) COM RETORNO nao estao configurados para RETORNO e TRATAMENTO: ";
        mensagem += "O grupo selecionado não esta configurado para o(s) protocolo(s) selecionado(s): ";
        mensagem += txtGrupoNaoTratRetorno;
    }

    if ( txtGrupoAtualComErro.size() )
    {
        mensagem += "O(s) seguinte(s) protocolo(s) não contem grupo atual na base de dados: ";
        mensagem += txtGrupoAtualComErro;
    }

    if ( txtUsuarioInativo.size() )
    {
        mensagem += "Usuário inativo. Ação solicitada não pode ser executada para o(s) seguinte(s) protocolo(s): ";
        mensagem += txtUsuarioInativo;
    }

    if ( txtUsuarioDestinoInativo.size() )
    {
        mensagem += txtUsuarioDestinoInativo;
    }

    if ( txtUsuarioWebInativo.size() )
    {
        mensagem += txtUsuarioWebInativo;
    }

    if ( txtUsuarioCorrenteNaoInformado.size() )
    {
        mensagem += txtUsuarioCorrenteNaoInformado;
    }

    if ( txtTagAtendimentoErr.size() )
    {
        mensagem += txtTagAtendimentoErr;
    }

    if ( txtHoraInvalida.size() )
    {
        mensagem += txtHoraInvalida;
    }

    if ( txtUsuarioGrupoInvalido.size() )
    {
        mensagem += txtUsuarioGrupoInvalido;
    }

    ULOG_END("VerificarMensagemFalha()");
}

bool cWFCoreWorkflow::horaIsValida(char *hora)
{
    if ( 0 == hora ) { return true; }
    if ( 0 == *hora ) { return true; }

    ULOG("cWFCoreWorkflow::horaIsValida->hrContato=%s",hora);

    char *p = strchr(hora,':');

    if ( 0 == p ) { return false; }

    *p = 0;
    
    int hh = atoi(hora);
    if ( hh < 0 || hh > 23 ) { return false; }

    int mm = atoi(p+1);
    if ( mm < 0 || mm > 59 ) { return false; }

    return true;
}

//bool cWFCoreWorkflow::usuarioIsSupervisor( long idPessoaUsuario )
//{
//    st_AtdTratamentoCri dados;
//    st_vlAtdTratamentoCri status;
//    XMLGen saida;
//
//    memset(&dados,0,sizeof(dados);
//    memset(&status,-1,sizeof(status);
//
//    dados.idAtendimento = idAtendimento;
//    status.idAtendimento = 1;
//    dados.idPessoaUsuario = idPessoaUsuario;
//    status.idPessoaUsuario = 1;
//    
//      cWFAtdTratamentoCri cWFAtdTratamentoCri(&dados,&status,&saida);
//
//      //Busca o grupo CRI
//      cWFAtdTratamentoCri.getGrupoCri();
//
//      //Verifica se o usuario eh ou nao supervisor
//      return cWFAtdTratamentoCri.usuarioIsSupervisor( cWFAtdTratamentoCri.getGrupo() );
//}

void cWFCoreWorkflow::carregaDados()
{
    ULOG_START("carregaDados()");

    memset(&m_stDados,0,sizeof(m_stDados));
    memset(&m_vlDados,-1,sizeof(m_vlDados));

    ULOG_END("carregaDados()");
}

////////////////////////////////////////////////////////////////////////////////////////////////
// OPERAÇÕES DE ACESSO A MÉTODOS DOM / XMLGEN
//
char *ObterValorTag::_ObterValorTag(char *ss,const char *idTag)
{
    valor = 0;

    if ( !ss )
    {
        return 0;
    }

    SmallString ssLocal;

    if ( !strstr(ss,"encoding") )
    {
        ssLocal += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>";
    }

    ssLocal += ss;

    char *p = 0;
    int tamSaida = ssLocal.size();

    //XercesDOMParser *pParser = new XercesDOMParser;
    XercesDOMParser parser;
    //MemBufInputSource *pMemBuf = new MemBufInputSource((const XMLByte*)ssLocal.c_str(),tamSaida,gerarIDDom());
    MemBufInputSource pMemBuf((const XMLByte*)ssLocal.c_str(),tamSaida,gerarIDDom());

    //if ( pParser && pMemBuf )
    //if ( pParser )
    //{
        //pParser->parse(*pMemBuf);
        parser.parse(pMemBuf);
        //DOMNode* pDoc = pParser->getDocument();
        DOMNode* pDoc = parser.getDocument();

        char *valorResult = tx.walkTree(pDoc,(char*)idTag,0);

        if ( valorResult ) 
        {
            if ( *valorResult )
            {
                valor = new char [ strlen(valorResult) + 1 ];
                if ( valor )
                {
                    strcpy(valor,valorResult);
                }
                else
                {
                    p = "Erro de alocação de memória";
                }
            }

            XMLString::release(&valorResult);
        }
    //}
    //else
    //{
    //    p = "Nao foi possivel alocar memoria para objetos DOM e/ou Xerces";
    //}

    //delete pParser;
    //delete pMemBuf;

    if ( p )
    {
        ULOGE("ERRO = [%s]",p);
        throw new TuxBasicSvcException("00E0000",p);
    }

    return valor;
}

////////////////////////////////////////////////////////////////////////////////////////////////
// Não existe chamada para este método no código, achei por bem desativar para não confundir
// qualquer manutenção futura. Junho, 2006 - Cassio.
////////////////////////////////////////////////////////////////////////////////////////////////
//
// char *ObterValorTag::_ObterValorTag(SmallString *ss,const char *idTag)
// {
//     char *p=0;
//     MemBufInputSource *pMemBuf;
//     XercesDOMParser *pParser;
// 
//     valor = 0;
// 
//     if ( !ss || !ss->size() )
//     {
//         return 0;
//     }
// 
//     SmallString ssLocal;
// 
//     if ( !strstr(ss->c_str(),"encoding") )
//     {
//         ssLocal += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>";
//     }
// 
//     ssLocal += (char*)ss->c_str();
// 
//     int tamSaida = ssLocal.size();
// 
//     pParser = new XercesDOMParser;
//     pMemBuf = new MemBufInputSource(const XMLByte*)ssLocal.c_str(),tamSaida,gerarIDDom();
// 
//     if ( pParser && pMemBuf )
//     {
//         pParser->parse(*pMemBuf);
//         DOMNode* pDoc = pParser->getDocument();
// 
//         char *valorResult = tx.walkTree(pDoc,(char*)idTag,0);
// 
//         if ( valorResult ) 
//         {
//             if ( *valorResult )
//             {
//                 valor = new char [ strlen(valorResult) + 1 ];
//                 if ( valor )
//                 {
//                     strcpy(valor,valorResult);
//                 }
//                 else
//                 {
//                     p = "Erro de alocação de memória";
//                 }
//             }
// 
//             XMLString::release(&valorResult);
//         }
//     }
//     else
//     {
//         p = "Nao foi possivel alocar memoria para objetos DOM e/ou Xerces";
//     }
// 
//     delete pParser;
//     delete pMemBuf;
// 
//     if ( p )
//     {
//         ULOGE("%s",(p);
//         throw new TuxBasicSvcException("00E0000",(p);
//     }
// 
//     return valor;
// }

////////////////////////////////////////////////////////////////////////////////////////////////
char *ObterValorTag::_ObterValorTag(DOMNode *entrada,const char *dnode,const char *idTag)
{
    char *p=0;

    valor = 0;

    if ( entrada && dnode && idTag )
    {
        DOMNode *dn;
        int index = 0;

        while ( (dn = tx.walkDOM(entrada,(char*)dnode,index++ )),dn )
        {
            char *valorResult = tx.walkTree(dn,(char*)idTag,0);

            if ( valorResult ) 
            {
                if ( *valorResult )
                {
                    valor = new char [ strlen(valorResult) + 1 ];
                    if ( valor )
                    {
                        strcpy(valor,valorResult);
                    }
                    else
                    {
                        p = "Erro de alocacao de memoria";
                    }
                }

                XMLString::release(&valorResult);

                break;
            }
        }
    }
    else
    {
        p = "Ponteiro invalido passado como parametro de entrada";
    }

    if ( p )
    {
        ULOGE("ERRO = [%s]",p);
        throw new TuxBasicSvcException("00E0000",(p));
    }

    return valor;
}

////////////////////////////////////////////////////////////////////////////////////////////////
char *ObterValorTag::_ObterValorTag(DOMNode *dn,const char *idTag)
{
    char *p=0;

    valor = 0;

    if ( dn && idTag )
    {
        char *valorResult = tx.walkTree(dn,(char*)idTag,0);

        if ( valorResult ) 
        {
            if ( *valorResult )
            {
                valor = new char [ strlen(valorResult) + 1 ];
                if ( valor )
                {
                    strcpy(valor,valorResult);
                }
                else
                {
                    p = "Erro de alocacao de memoria";
                }
            }

            XMLString::release(&valorResult);

        }
    }
    else
    {
        p = "Ponteiro invalido passado como parametro de entrada";
    }

    if ( p )
    {
        ULOGE("ERRO = [%s]",p);
        throw new TuxBasicSvcException("00E0000",(p));
    }

    return valor;
}

// Fim
////////////////////////////////////////////////////////////////////////////////////////////////
