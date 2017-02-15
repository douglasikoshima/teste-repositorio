/**
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.2.3 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:17 $
 **/

#include "../include/cWFAtdAssocDocTe.h"

extern bool proCBuscarDataHora( st_AtdAssocDocTe *dados );
extern bool proCDocTecObterComentario(DadosDocTecnico *dadosDocTecnico);

cWFAtdAssocDocTe::cWFAtdAssocDocTe(DOMNode *dnode, XMLGen *xml_g)
{
    entrada = dnode;
    saida = xml_g;

    carregaDados();
}

bool cWFAtdAssocDocTe::Executar()
{
    ULOG_START("cWFAtdAssocDocTe::Executar()"); 

    bool retorno = proCBuscarDataHora( &mAtdAssocDocTe );

    if ( retorno )
    {
        retorno = VerificarAtendimentosDom();
        
        if ( retorno )
        {
            retorno = IncluirAtendimentosDom();
        }

        if ( retorno )
        {
            saida->createTag("WFAcaoRetornoVO ");
            saida->addProp("xmlns","workflow.fo.vivo.com.br/vo");
            saida->addItem("acaoExecucao","S");
            saida->addItem("mensagem","Associacao de Documento ao Atendimento Efetuada");
            saida->closeTag();
        }
        else
        {
            saida->createTag("WFAcaoRetornoVO ");
            saida->addProp("xmlns","workflow.fo.vivo.com.br/vo");
            saida->addItem("acaoExecucao","N");
            saida->addItem("mensagem",ObterMsgErro());
            saida->closeTag();

            retorno = true;
        }
    }

    ULOG_END("cWFAtdAssocDocTe::Executar()"); 
    
    return retorno;
}

bool cWFAtdAssocDocTe::VerificarAtendimentosDom()
{
    ULOG_START("cWFAtdAssocDocTe::VerificarAtendimentosDom()"); 

    bool retorno = true;
    bool domValido = false;
    DOMNode *dn0;
    int index0 = 0;
    SmallString atdInvalidos;
    TuxHelper tx;

    while ( retorno && ((dn0 = tx.walkDOM(entrada,"atendimentos",index0++ )),dn0) )
    {
        char *_idAtendimento = tx.walkTree(dn0,"idAtendimento",0);

        domValido = true;

        if ( _idAtendimento )
        {
            if ( VerificarAtendimentoFechamento(atol(_idAtendimento)) )
            {
                if ( atdInvalidos.Size() ) atdInvalidos += ",";
                atdInvalidos += _idAtendimento;
            }
        }
        else
        {
            SetarErro(NULL,"idAtendimento nao fornecido no XML de entrada");
            ULOGE(ObterMsgErro());
            retorno = false;
        }

        XMLString::release(&_idAtendimento);
    }

    if ( !domValido )
    {
        SetarErro(NULL,"xml de entrada vazio ou inválido!");
        ULOGE(ObterMsgErro());
        retorno = false;
    }
    else if ( atdInvalidos.Size() )
    {
        SmallString errMsg;

        errMsg = "Operacao nao permitida para o(s) seguinte(s) processo(s): ";
        errMsg += atdInvalidos;

        SetarErro(NULL,errMsg.c_str());
        ULOGE(ObterMsgErro());
        retorno = false;
    }

    ULOG_END("cWFAtdAssocDocTe::VerificarAtendimentosDom()"); 

    return retorno;
}

bool cWFAtdAssocDocTe::IncluirAtendimentosDom()
{
    ULOG_START("cWFAtdAssocDocTe::IncluirAtendimentosDom()"); 

    bool retorno = true;
    bool domValido = false;
    DOMNode *dn00;
    DOMNode *dn0;
    DOMNode *dn1;
    int index00 = 0;
    int index0 = 0;
    int index1 = 0;
    TuxHelper tx;
    st_AtendimentoDocumento dados;
    st_vlAtendimentoDocumento status;
    cWFAtendimento cwfatendimento;
    DetalheAtendimento detalheAtendimento;

    memset(&dados,0,sizeof(dados));
    memset(&status,-1,sizeof(status));

    dados.idUsuarioAlteracao = obterIdUsuario();
    status.idUsuarioAlteracao = 1;

    strcpy(dados.dtUltimaAlteracao,mAtdAssocDocTe.dataAtual);
    status.dtUltimaAlteracao = 1;

    dn00 = tx.walkDOM(entrada,"atendimentos",index00++ );
    while ( retorno && (dn0 = tx.walkDOM(dn00,"xml-fragment",index0++ )) )
    {
        char *_idAtendimento = tx.walkTree(dn0,"idAtendimento",0);

        ULOG("idAtendimento: %s", _idAtendimento);

        dados.idAtendimento = _idAtendimento ? atol(_idAtendimento):-1;
        status.idAtendimento = 1;
        XMLString::release(&_idAtendimento);

        cwfatendimento.ObtemDetalheAtend(dados.idAtendimento,&detalheAtendimento);

        index1 = 0;
        if ( dados.idAtendimento > 0 )
        {
            while ( retorno && (dn1 = tx.walkDOM(entrada,"AtendimentoWorkflowTecnicoDocVO",index1++ )) )
            {
                domValido = true;

                char *_idDocumentoTecnico = tx.walkTree(dn1,"idDocumentoTecnico",0);

                dados.idDocumentoTecnico = atoi(_idDocumentoTecnico);
                status.idDocumentoTecnico = 1;
                XMLString::release(&_idDocumentoTecnico);

                retorno = IncluirAtdDocTecnico(&dados,&status,&detalheAtendimento);
            }
        }
        else
        {
            SetarErro(NULL,"idAtendimento nao fornecido no XML de entrada");
            ULOGE(ObterMsgErro());
            retorno = false;
        }
    }

    if ( !domValido )
    {
        SetarErro(NULL,"xml de entrada vazio ou inválido!");
        ULOGE(ObterMsgErro());
        retorno = false;
    }

    ULOG_END("cWFAtdAssocDocTe::IncluirAtendimentosDom()"); 

    return retorno;
}

bool cWFAtdAssocDocTe::VerificarAtendimentoFechamento(long idAtendimento)
{
    ULOG_START("cWFAtdAssocDocTe::VerificarAtendimentoFechamento()"); 

    cWFAtendimentoFechamento cwfatendimentofechamento;

    bool retorno = cwfatendimentofechamento.existeAtendimentoFechamento(idAtendimento);

    ULOG_END("cWFAtdAssocDocTe::VerificarAtendimentoFechamento()"); 

    return retorno;
}

bool cWFAtdAssocDocTe::IncluirAtdDocTecnico(st_AtendimentoDocumento *dados,st_vlAtendimentoDocumento *status,DetalheAtendimento *detalheAtendimento)
{
    ULOG_START("cWFAtdAssocDocTe::IncluirAtdDocTecnico()"); 
    
    cWFAtendimentoDocumento cwfAtendimentoDocumento;

    bool retorno = cwfAtendimentoDocumento.incluir(dados,status,0);

    if ( retorno ) { IncluirAndamento(dados,status,detalheAtendimento); }

    ULOG_END("cWFAtdAssocDocTe::IncluirAtdDocTecnico()"); 

    return retorno;
}

void cWFAtdAssocDocTe::IncluirAndamento(st_AtendimentoDocumento *dados,st_vlAtendimentoDocumento *status,DetalheAtendimento *detalheAtendimento)
{
    ULOG_START("cWFAtdAssocDocTe::IncluirAndamento()"); 

    st_Andamento m_stDadosAndamento;
    st_vlAndamento m_vlDadosAndamento;
    XMLGen AndamentoIns;

    memset(&m_stDadosAndamento, 0, sizeof(m_stDadosAndamento));
    memset(&m_vlDadosAndamento,-1, sizeof(m_vlDadosAndamento));

    m_stDadosAndamento.idAtividade=19; // DOCUMENTACAO TECNICA
    m_vlDadosAndamento.idAtividade=1;

    m_stDadosAndamento.idAgrupamentoEstadoTpProc=detalheAtendimento->idAgrEstadoTProc;
    m_vlDadosAndamento.idAgrupamentoEstadoTpProc=1;

    m_stDadosAndamento.idAtendimento=dados->idAtendimento;
    m_vlDadosAndamento.idAtendimento=1;

    m_stDadosAndamento.idGrupo=detalheAtendimento->idGrupoAtual;
    m_vlDadosAndamento.idGrupo=1;

    m_stDadosAndamento.idPessoaUsuario=dados->idUsuarioAlteracao;
    m_vlDadosAndamento.idPessoaUsuario=1;

    m_stDadosAndamento.idUsuarioAlteracao=dados->idUsuarioAlteracao;
    m_vlDadosAndamento.idUsuarioAlteracao=1;

    strcpy(m_stDadosAndamento.dtAndamento,dados->dtUltimaAlteracao);
    m_vlDadosAndamento.dtAndamento=1;

    strcpy(m_stDadosAndamento.dtUltimaAlteracao,dados->dtUltimaAlteracao);
    m_vlDadosAndamento.dtUltimaAlteracao=1;

    cWFAndamento Andamento(&m_stDadosAndamento,&m_vlDadosAndamento,&AndamentoIns);

    long idAndamento = Andamento.incluir(&xmlDpr);

    IncluirAndamentoObservacao(dados,idAndamento);

    // Registra o andamento no DPR
    registrarAcaoDPR(dados->idUsuarioAlteracao,dados->idAtendimento,detalheAtendimento->idAgrEstadoTProc);

    ULOG_END("cWFAtdAssocDocTe::IncluirAndamento()"); 
}

void cWFAtdAssocDocTe::IncluirAndamentoObservacao(st_AtendimentoDocumento *dados,long idAndamento)
{
    ULOG_START("cWFAtdAssocDocTe::IncluirAndamentoObservacao()"); 

    DadosDocTecnico dadosDocTecnico;
    dadosDocTecnico.idDocumentoTecnico = dados->idDocumentoTecnico;

    if ( proCDocTecObterComentario(&dadosDocTecnico) )
    {
        st_AndamentoObservacao m_dadosAndamentoObservacao;
        st_vlAndamentoObservacao m_statusAndamentoObservacao;
        string Observacao;

        Observacao += (string)"DOC.TECNICO: " + dadosDocTecnico.dsDocumentotecnicoTipo +
                      (string)"-" + dadosDocTecnico.nrDocumento;

        if ( dadosDocTecnico.dsDocumento.length() )
        {
            Observacao += (string)" COMENTÁRIOS: '" + dadosDocTecnico.dsDocumento + (string)"'";
        }

        if ( dadosDocTecnico.ComentarioFechamento.length() )
        {
            Observacao += (string)" RESPOSTA: '" + dadosDocTecnico.ComentarioFechamento + (string)"'";
        }

        memset(&m_dadosAndamentoObservacao,0,sizeof(m_dadosAndamentoObservacao));
        memset(&m_statusAndamentoObservacao,-1,sizeof(m_statusAndamentoObservacao));

        m_dadosAndamentoObservacao.idAndamento = idAndamento;
        m_statusAndamentoObservacao.idAndamento = 1;

        m_dadosAndamentoObservacao.pdsAndamentoObservacao = Observacao.c_str();
        m_statusAndamentoObservacao.dsAndamentoObservacao = 1;

        m_dadosAndamentoObservacao.idUsuarioAlteracao = dados->idUsuarioAlteracao;
        m_statusAndamentoObservacao.idUsuarioAlteracao = 1;

        strcpy(m_dadosAndamentoObservacao.dtUltimaAlteracao,dados->dtUltimaAlteracao);
        m_statusAndamentoObservacao.dtUltimaAlteracao = 1;

        cWFAndamentoObservacao AndamentoObservacao(&m_dadosAndamentoObservacao,&m_statusAndamentoObservacao,0);

        AndamentoObservacao.incluir(&xmlDpr);
    }

    ULOG_END("cWFAtdAssocDocTe::IncluirAndamentoObservacao()"); 
}

void cWFAtdAssocDocTe::registrarAcaoDPR(int idUsuarioWeb,long idAtendimento,int idAgrupamentoEstadoTpProc)
{
    ULOG_START("cWFAtdAssocDocTe::registrarAcaoDPR()");

    if ( idAgrupamentoEstadoTpProc >= INI_AGRPTPPROC_TECNICO 
       && idAgrupamentoEstadoTpProc <= FIM_AGRPTPPROC_TECNICO )
    {
        cWFAtdGerarXMLDPR cwfatdgerarxmldpr;

        char idAtendimentoStr[32];

        sprintf(idAtendimentoStr,"%lu",idAtendimento);
        xmlDpr.idAtendimento = idAtendimentoStr;

        xmlDpr.idUser = idUsuarioWeb;
        xmlDpr.nomeServico = "ATDASSOCDOCTE";
        xmlDpr.idAtendimento = idAtendimentoStr;

        cwfatdgerarxmldpr.persistirDadosDPRContatoTecnico(&xmlDpr);
    }
    else
    {
        ULOG("Processo %d não é técnico",idAtendimento);
    }

    ULOG_END("cWFAtdAssocDocTe::registrarAcaoDPR()");
}
