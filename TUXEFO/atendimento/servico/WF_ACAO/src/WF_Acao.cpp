
/*---------------------------------------------------------
 * Modulo..: Atendimento
 * Servico.: Acao
 * Migracao: 
 * Revisao.: Dec-07-2004 - Roberto Borges
 *--------------------------------------------------------*/

#include "../include/cWF_AcaoPC.h"
#include "../../../commons/msgPadrao.h"

//------------------------------------------------------------------------------------------------------------------
// SetNodesDef 
//------------------------------------------------------------------------------------------------------------------

void cWF_Acao::SetNodesDef(DOMNode*pdnode, XMLGen* pxml_g)
{
	bdnode = pdnode;
	dnode = pdnode;
	xml_g = pxml_g;
	ind = 0; 
}          


//------------------------------------------------------------------------------------------------------------------
// SetNode - Set o node atual
//------------------------------------------------------------------------------------------------------------------

bool cWF_Acao::SetNode( char *cnode )
{
	dnode = walkDOM(bdnode, cnode ); 
	return ( dnode != NULL ); 
}

//----------------------------------------------------------------------------------------------------------------------------------------------
// SetNode - Set o node atual e subnode
//----------------------------------------------------------------------------------------------------------------------------------------------

bool cWF_Acao::SetNode( char *cnode, char *subnode )
{
    dnode = walkDOM(bdnode, cnode ); 
    dnode = walkDOM(dnode,  subnode ); 
    return ( dnode != NULL ); 
}

//------------------------------------------------------------------------------------------------------------------

TString cWF_Acao::GetXML(char *Campo, bool tpObrigatorio) 
{
    char *p = walkTree(dnode,Campo,ind);

    if ( tpObrigatorio && !p )
    {
        XMLException(Campo);
    }

    if ( p )
    {
        int tamNovo = strlen(p)+1;

        if ( tamNovo > tamValorCampo )
        {
            delete[] valorCampo;
            valorCampo = new char[tamNovo];
            tamValorCampo = tamNovo;
        }

        strcpy(valorCampo,p);

        XMLString::release(&p);

        ULOG("GetXML:%s='%s'",Campo,valorCampo);
    }
    else
    {
        ULOG("GetXML:%s='*** NAO INFORMADO ***'",Campo);

        *valorCampo = 0;
    }

    return (TString)valorCampo;
}

//------------------------------------------------------------------------------------------------------------------
// XMLException
//------------------------------------------------------------------------------------------------------------------
void cWF_Acao::XMLException(char *Campo) 
{
    TString msg = "ERRO: ** Campo <"; 
            msg += Campo; 
            msg += "> invalido ou nao encontrado **"; 
    throw new TuxBasicSvcException("09E0001",msg.c_str());
}

//------------------------------------------------------------------------------------------------------------------
// SetMessage - Seta a mensagem de retorno
//------------------------------------------------------------------------------------------------------------------

void cWF_Acao::SetMessage(char *msg, char *status) 
{
    xml_g->createTag("WFAcaoRetornoVO");
        xml_g->addProp("xmlns", "workflow.fo.vivo.com.br/vo");

        xml_g->addItem("acaoExecucao", status);
        xml_g->addItem("mensagem", msg);
        xml_g->addItem("urlDestino", urlDestino.c_str());
    xml_g->closeTag();
}


void cWF_Acao::SetMessage(char *msg, char *status, char *url) 
{
    xml_g->createTag("WFAcaoRetornoVO");
        xml_g->addProp("xmlns", "workflow.fo.vivo.com.br/vo");

        xml_g->addItem("acaoExecucao", status);
        xml_g->addItem("mensagem", msg);
        xml_g->addItem("urlDestino", url);
    xml_g->closeTag();
}

//------------------------------------------------------------------------------------------------------------------
// Decrementa quantidade de processos pendentes sob um protocolo
//------------------------------------------------------------------------------------------------------------------
void cWF_Acao::DecrementarPendentes()
{
    ULOG_START("cWF_Acao::DecrementarPendentes()");

    if ( idAtendimentoProtocolo[0] == 0 )
    {
        getAtendimento();
    }

    DecrementarPendentes(idAtendimentoProtocolo);

    ULOG_END("cWF_Acao::DecrementarPendentes()"); 
}

//------------------------------------------------------------------------------------------------------------------
// Decrementa quantidade de processos pendentes sob um protocolo
//------------------------------------------------------------------------------------------------------------------
void cWF_Acao::DecrementarPendentes(const char *idAtendimentoProtocolo)
{
    ULOG_START("cWF_Acao::DecrementarPendentes(const char *idAtendimentoProtocolo)");

    if ( (idAgrEstTPrFt.ToInt() >= 8 && idAgrEstTPrFt.ToInt() <= 13)
      || (idAgrEstTPrFt.ToInt() >= 21 && idAgrEstTPrFt.ToInt() <= 26)
      || (idAgrEstTPrFt.ToInt() >= 34 && idAgrEstTPrFt.ToInt() <= 35)
       )
    {
        cWfAtdAlterProt cwfatdalterprot;

        cwfatdalterprot.DecrementarPendentes(idAtendimentoProtocolo,"1",User.c_str());
    }
    else
    {
        ULOG("Processo não esta sendo encerrado para ser decrementado do protocolo."); 
    }

    ULOG_END("cWF_Acao::DecrementarPendentes(const char *idAtendimentoProtocolo)"); 
}

//------------------------------------------------------------------------------------------------------------------
// Obtiene Dados do Atendimento
//------------------------------------------------------------------------------------------------------------------
void cWF_Acao::getAtendimento()
{
    ULOG_START("cWF_Acao::getAtendimento()"); 

    DetalheAtendimento detalheatendimento;

    getAtendimento(&detalheatendimento);

    ULOG_END("cWF_Acao::getAtendimento()"); 
}

//------------------------------------------------------------------------------------------------------------------
// Obtiene Dados do Atendimento (2)
//------------------------------------------------------------------------------------------------------------------
void cWF_Acao::getAtendimento(DetalheAtendimento *detalheatendimento)
{
    ULOG_START("cWF_Acao::getAtendimento(DetalheAtendimento *detalheatendimento)");

    cWFAtendimento cwfatendimento;

    cwfatendimento.ObtemDetalheAtend(idAtendimento.ToLong(),detalheatendimento);

    idCanal                  = detalheatendimento->idCanal;
    idProcedencia            = detalheatendimento->idProcedencia;
    idTipoCarteira           = detalheatendimento->idTipoCarteira;
    idSegmentacao            = detalheatendimento->idSegmentacao;
    idUsuarioAbertura        = detalheatendimento->idPessoaUsuarioAbertura;
    idPessoaUsuarioMC        = detalheatendimento->idPessoaUsuarioMC;
    idGrupoMC                = detalheatendimento->idGrupoMC;
    idGrupoAbertura          = detalheatendimento->idGrupoAbertura;
    idContato                = detalheatendimento->idContato;
    nrNivelAtendimento       = detalheatendimento->nrNivel;
    qtInsistenciaAtendimento = detalheatendimento->qtInsistencia;
    idAgrEstadoTProcTestes   = detalheatendimento->idAgrEstadoTProc;
    idTipoRetornoContato     = detalheatendimento->idTipoRetornoContato;
    idUFOperadora            = detalheatendimento->idUFOperadora;
    idUsuarioAtualAtendimento= detalheatendimento->idPessoaUsuarioAtual;
    idUsuarioAlteracao       = detalheatendimento->idUsuarioAlteracao;
    idFaseAtendimento        = detalheatendimento->idFase;

    strcpy(dtPrazoFinalCPrevio,detalheatendimento->dtPrazoFinalCPrevio);
    strcpy(sgTipoPortabilidade,detalheatendimento->sgTipoPortabilidade);
    strcpy(dtAbertura,detalheatendimento->dataAbertura);
    strcpy(idAtendimentoProtocolo,detalheatendimento->idAtendimentoProtocolo);

    strcpy(szSgOperadoraSolicitante,detalheatendimento->sgOperadoraSolicitante);
    strcpy(szNrProtocoloPortabilidade,detalheatendimento->nrProtocoloPortabilidade);
    strcpy(szDtJanelaPortout,detalheatendimento->dtJanelaPortout);
    strcpy(szIdTipoLinha,detalheatendimento->idTipoLinha);

    strcpy(szSgRegraEncaminhamento,detalheatendimento->sgRegraEncaminhamento);
    strcpy(szSgFluxoAtendimento,detalheatendimento->sgFluxoAtendimento);
    strcpy(szIdPerfilConsultorAtd,detalheatendimento->idPerfilConsultorAtd);
    strcpy(szIdFornecedorConsultorAtd,detalheatendimento->idFornecedorConsultorAtd);
    strcpy(szIdSiteConsultorAtd,detalheatendimento->idSiteConsultorAtd);

    ULOG_END("cWF_Acao::getAtendimento(DetalheAtendimento *detalheatendimento)"); 
}

//------------------------------------------------------------------------------------------------------------------
// Obtiene Dados do Atendimento Pessoa
//------------------------------------------------------------------------------------------------------------------
void cWF_Acao::getAtendimentoPessoa() 
{
    ULOG_START("cWF_Acao::getAtendimentoPessoa()"); 

    AtendimentoPessoa ap;

    cWFAtendimentoPessoa AtendimentoPessoa;

    AtendimentoPessoa.ObtemAtendPessoa(idAtendimento.ToLong(),&ap);

    idTipoPessoa = ap.idTipoPessoa;
    idTipoRelacionamento = ap.inRspAbertura;

    ULOG("idTipoPessoa: %d", idTipoPessoa);
    ULOG("idTipoRelacionamento: %d", idTipoRelacionamento);

    ULOG_END("cWF_Acao::getAtendimentoPessoa()"); 
}

//------------------------------------------------------------------------------------------------------------------
// Obtiene Dados do Atendimento Linha
//------------------------------------------------------------------------------------------------------------------
void cWF_Acao::getAtendimentoLinha() 
{
    ULOG_START("cWF_Acao::getAtendimentoLinha()"); 

    cWFAtendimentoLinha atendimentolinha;

    atendimentolinha.ObtemDadosAtendLinha(idAtendimento.ToLong(),&idTipoLinha);

    ULOG_END("cWF_Acao::getAtendimentoLinha()");
}

//------------------------------------------------------------------------------------------------------------------
// Obtiene Dados do Atendimento Linha Cri
//------------------------------------------------------------------------------------------------------------------
void cWF_Acao::getAtendimentoLinhaCri() 
{
    ULOG_START("cWF_Acao::getAtendimentoLinhaCri()");

    char *p;

    st_AtendimentoLinha m_stDados;
    st_vlAtendimentoLinha m_vlDados;
    XMLGen xmlObtemLinha;

    memset(&m_stDados, 0, sizeof(m_stDados));
    memset(&m_vlDados,-1, sizeof(m_vlDados));
    m_stDados.idPessoaLinhaHistorico=idPessoaLinhaHistorico;
    m_vlDados.idPessoaLinhaHistorico=1;

    cWFAtendimentoLinha AtendimentoLinha(&m_stDados, &m_vlDados, &xmlObtemLinha);

    AtendimentoLinha.ObtemDadosAtendLinhaCri();

    if (wta.walkTreeXMLGen(&xmlObtemLinha, "idTipoLinha", &p, 0)==0)
    {
        idTipoLinha= atoi(p);
        XMLString::release(&p);         
    }   
    
    ULOG("idTipoLinha: %d", idTipoLinha);

    ULOG_END("cWF_Acao::getAtendimentoLinhaCri()");
}

//------------------------------------------------------------------------------------------------------------------
// Obtiene Dados do Atendimento Contato
//------------------------------------------------------------------------------------------------------------------
void cWF_Acao::getAtendimentoContato() 
{
    ULOG_START("cWF_Acao::getAtendimentoContato()");

    char *p;

    st_AtendimentoContato m_stDados;
    st_vlAtendimentoContato m_vlDados;
    XMLGen xmlObtemContato;

    memset(&m_stDados, 0, sizeof(m_stDados));
    memset(&m_vlDados,-1, sizeof(m_vlDados));
    m_stDados.idAtendimento=idAtendimento.ToLong();
    m_vlDados.idAtendimento=1;

    cWFAtendimentoContato AtendimentoContato(&m_stDados, &m_vlDados, &xmlObtemContato);

    AtendimentoContato.consultar();

    if (wta.walkTreeXMLGen(&xmlObtemContato, "nrTelefoneContato", &p, 0)==0)
    {
        nrTelefone = p;
        XMLString::release(&p);         
    }   
    
    ULOG("nrTelefone: %s", nrTelefone);

    ULOG_END("cWF_Acao::getAtendimentoContato()");
}

//------------------------------------------------------------------------------------------------------------------
// Obtiene Status de Usuario
//------------------------------------------------------------------------------------------------------------------
bool cWF_Acao::usuarioIsSupervisor(int idGrupo) 
{
    ULOG_START("cWF_Acao::usuarioIsSupervisor()");

    bool isSupervisor;
    XMLGen xmlObtemStatus;

    cWFUsuario usuario;

    idStatusUsuario = usuario.getUsuarioSupervisorSimNao(User.ToInt()
                                                        ,idGrupo,isSupervisor);

    ULOG("isSupervisor: %d", isSupervisor);

    ULOG_END("cWF_Acao::usuarioIsSupervisor()");

    return isSupervisor;
}

//------------------------------------------------------------------------------------------------------------------
// Devolve o status do usuário
//------------------------------------------------------------------------------------------------------------------
void cWF_Acao::getStatusUsuario() 
{
    ULOG_START("cWF_Acao::getStatusUsuario()");

    getStatusUsuario(UsuarioAtual.ToInt());

    ULOG_END("cWF_Acao::getStatusUsuario()");
}

//------------------------------------------------------------------------------------------------------------------
// Devolve o status do usuario. Informa se o usuario esta ativo ou não.
// (Não confundir com a comparação de usuário disponível ou não!)
//------------------------------------------------------------------------------------------------------------------
void cWF_Acao::getStatusUsuario(int idPessoaUsuario)
{
    ULOG_START("cWF_Acao::getStatusUsuario() (2)");

    struct st_VariaveisUsuario   dados;
    struct st_vlVariaveisUsuario status;
    XMLGen xmlObtemStatus;

    idStatusUsuario = 0; 

    memset ( &dados,   0,  sizeof(st_VariaveisUsuario)); 
    memset ( &status,  -1,  sizeof(st_vlVariaveisUsuario)); 

    dados.idPessoaUsuario = idPessoaUsuario;
    status.idPessoaUsuario = 1;

    cWFUsuario usuario( &dados, &status, &xmlObtemStatus ); 

    idStatusUsuario = usuario.pesqStatusUsuario();
    
    ULOG("idStatusUsuario: %d", idStatusUsuario);

    ULOG_END("cWF_Acao::getStatusUsuario() (2)");
}

//------------------------------------------------------------------------------------------------------------------
// Obtém o status e disponibilidade do Usuario
//------------------------------------------------------------------------------------------------------------------
void cWF_Acao::getStatusDispUsuario() 
{
    ULOG_START("cWF_Acao::getStatusDispUsuario()");

    getStatusDispUsuario(UsuarioAtual.ToInt());

    ULOG_END("cWF_Acao::getStatusDispUsuario()");
}

void cWF_Acao::getStatusDispUsuario(int idPessoaUsuario)
{
    ULOG_START("cWF_Acao::getStatusDispUsuario() (2)");

    cWFUsuario usuario; 
    XMLGen xmlObtemStatus;

    idStatusUsuario = 3; // desligado por default
    inDisponivelWF = 0; // indisponível por default

    if ( usuario.pesqStatusDispUsuario(idPessoaUsuario,&idStatusUsuario,&inDisponivelWF) )
    {
        ULOG("Usuario %d nao encontrado, assumindo valores default ...", idPessoaUsuario);
    }

    ULOG("idStatusUsuario: %d", idStatusUsuario);
    ULOG("inDisponivelWF: %d", inDisponivelWF);

    ULOG_END("cWF_Acao::getStatusDispUsuario() (2)");
}

//------------------------------------------------------------------------------------------------------------------
// Obtiene Usuario Atual de Cri (EM ATENDIMENTO.TRATAMENTOCRI)
//------------------------------------------------------------------------------------------------------------------
void cWF_Acao::getTratamentoUsuarioCri() 
{
    ULOG_START("cWF_Acao::getTratamentoUsuarioCri()");

    char *p;

    st_AtdTratamentoCri    dados;
    st_vlAtdTratamentoCri  status;
    XMLGen xmlObtemUsrAt;

    memset ( &dados,   0, sizeof(st_AtdTratamentoCri));
    memset ( &status, -1, sizeof(st_vlAtdTratamentoCri));

    dados.idAtendimento = idAtendimento.ToLong();

    status.idAtendimento = 1;

    cWFAtdTratamentoCri cwfatendimentousuarioatual(&dados, &status, &xmlObtemUsrAt);

    idUsuarioCri = 0;

    ULOG("idAtendimento: %lu", dados.idAtendimento);

    cwfatendimentousuarioatual.getTratamentoCri();

    if ( wta.walkTreeXMLGen(&xmlObtemUsrAt,"idPessoaUsuario",&p,0) == 0 )
    {
        idUsuarioCri= atoi(p);
        XMLString::release(&p);
    }
    
    ULOG("idUsuarioCri: %d", idUsuarioCri);

    ULOG_END("cWF_Acao::getTratamentoUsuarioCri()");
}

//------------------------------------------------------------------------------------------------------------------
// Incrementar qtdeEnvioBKO em ATENDIMENTO.ATENDIMENTOCRI
//------------------------------------------------------------------------------------------------------------------
void cWF_Acao::incAtendimentoUsuarioCriQtEnvioBKO() 
{
    ULOG_START("cWF_Acao::incAtendimentoUsuarioCriQtEnvioBKO()");

    cWFAtdTratamentoCri cwfatdtratamentocri;

    cwfatdtratamentocri.incAtendimentoCriQtEnvioBKO(idAtendimento.ToLong());

    ULOG_END("cWF_Acao::incAtendimentoUsuarioCriQtEnvioBKO()");
}

//------------------------------------------------------------------------------------------------------------------
// Obtiene Usuario Atual de Cri
//------------------------------------------------------------------------------------------------------------------
void cWF_Acao::getAtendimentoUsuarioCri() 
{
    ULOG_START("cWF_Acao::getAtendimentoUsuarioCri()");

    char *p;

    st_AtdTratamentoCri    dados; 
    st_vlAtdTratamentoCri  status;
    XMLGen xmlObtemUsrAt;

    memset ( &dados,   0, sizeof(st_AtdTratamentoCri)); 
    memset ( &status, -1, sizeof(st_vlAtdTratamentoCri)); 

    dados.idAtendimento = idAtendimento.ToLong(); 

    status.idAtendimento = 1;

    cWFAtdTratamentoCri cwfatdtratamentocri(&dados, &status, &xmlObtemUsrAt);

    idUsuarioCri = 0;
    qtEnvioBko = 0;

    ULOG("idAtendimento: %d", dados.idAtendimento);

    cwfatdtratamentocri.getAtendimentoCri(); // ATENDIMENTO.ATENDIMENTOCRI

    if (wta.walkTreeXMLGen(&xmlObtemUsrAt, "idPessoaUsuario", &p, 0)==0)
    {
        idUsuarioCri= atoi(p);
        XMLString::release(&p);
    }

    if (wta.walkTreeXMLGen(&xmlObtemUsrAt, "qtEnvioBko", &p, 0)==0)
    {
        qtEnvioBko= atoi(p);
        XMLString::release(&p);
    }   
    
    ULOG("idUsuarioCri: %d", idUsuarioCri);
    ULOG("qtEnvioBko: %d", qtEnvioBko);

    ULOG_END("cWF_Acao::getAtendimentoUsuarioCri()");
}

//------------------------------------------------------------------------------------------------------------------
// Confirma se grupo atual é CRI
//------------------------------------------------------------------------------------------------------------------
bool cWF_Acao::grupoIsCRI(int idGrupo) 
{
    ULOG_START("cWF_Acao::grupoIsCRI()");

    bool retorno = proCGetINCRI(idGrupo);

    ULOG("retorno=%d",retorno);

    ULOG_END("cWF_Acao::grupoIsCRI()");

    return retorno;     	
}

//------------------------------------------------------------------------------------------------------------------
// Obtiene Grupo Atual de Atendimento
//------------------------------------------------------------------------------------------------------------------
void cWF_Acao::getAtendimentoGrupoAtual()
{
    ULOG_START("cWF_Acao::getAtendimentoGrupoAtual()");

    if (inCri == 0) { getAtendimentoGrupoAtualBko(); }
    else { getAtendimentoGrupoCri(); }

    ULOG_END("cWF_Acao::getAtendimentoGrupoAtual()");

}

//------------------------------------------------------------------------------------------------------------------
// Obtém o grupo atual de atendimento para Bko (ATENDIMENTOGRUPOATUAL)
//------------------------------------------------------------------------------------------------------------------
void cWF_Acao::getAtendimentoGrupoAtualBko()
{
    ULOG_START("cWF_Acao::getAtendimentoGrupoAtualBko()");

    char *p;

    st_AtendimentoGrupoAtual m_stDadosGrup;
    st_vlAtendimentoGrupoAtual m_vlDadosGrup;
    XMLGen xmlObtemAtGrAt;

    memset(&m_stDadosGrup, 0, sizeof(m_stDadosGrup));
    memset(&m_vlDadosGrup,-1, sizeof(m_vlDadosGrup));
    m_stDadosGrup.idAtendimento=idAtendimento.ToLong();
    m_vlDadosGrup.idAtendimento=1;

    cWFAtendimentoGrupoAtual cwfatendimentogrupoatual(&m_stDadosGrup, &m_vlDadosGrup, &xmlObtemAtGrAt);

    cwfatendimentogrupoatual.ObtemGrAt(); // ATENDIMENTO.ATENDIMENTOGRUPOATUAL

    idGrupoAtual = 0;
    inCriAtd = 0;
    
    if (wta.walkTreeXMLGen(&xmlObtemAtGrAt, "idGrupo", &p, 0)==0)
    {
        idGrupoAtual= atoi(p);
        XMLString::release(&p);         
    }
    
    if (wta.walkTreeXMLGen(&xmlObtemAtGrAt, "inCRI", &p, 0)==0)
    {
        inCriAtd= atoi(p);
        XMLString::release(&p);         
    }

    ULOG_END("cWF_Acao::getAtendimentoGrupoAtualBko()");
}

//------------------------------------------------------------------------------------------------------------------
// Insere ou atualiza ATENDIMENTO.ATENDIMENTOGRUPOATUAL
//------------------------------------------------------------------------------------------------------------------
void cWF_Acao::atualizaAtendimentoGrupoAtual() 
{
    ULOG_START("cWF_Acao::atualizaAtendimentoGrupoAtual()");

    cWFAtendimentoGrupoAtual cwfatendimentogrupoatual;

    bool retorno = cwfatendimentogrupoatual.existegrupoAtual(idAtendimento.ToLong());

    if ( cwfatendimentogrupoatual.existegrupoAtual(idAtendimento.ToLong()))
    {
        alterarAtendimentoGrupoAtual(); // ATENDIMENTO.ATENDIMENTOGRUPOATUALG00
    }
    else
    {
        inserirAtendimentoGrupoAtual();  // ATENDIMENTO.ATENDIMENTOGRUPOATUALG00
    }

    ULOG_END("cWF_Acao::atualizaAtendimentoGrupoAtual()");
}

//------------------------------------------------------------------------------------------------------------------
// Alterar Grupo Atual para o processo
//------------------------------------------------------------------------------------------------------------
void cWF_Acao::alterarAtendimentoGrupoAtual()
{
    ULOG_START("cWF_Acao::alterarAtendimentoGrupoAtual()");

    alterarAtendimentoGrupoAtual(idGrupo.ToInt());

    ULOG_END("cWF_Acao::alterarAtendimentoGrupoAtual()");
}

//------------------------------------------------------------------------------------------------------------------
// Alterar Grupo Atual para o processo (2)
//------------------------------------------------------------------------------------------------------------
void cWF_Acao::alterarAtendimentoGrupoAtual(int idGrupo)
{
    ULOG_START("cWF_Acao::alterarAtendimentoGrupoAtual() (2)");

    struct st_AtendimentoGrupoAtual dados;
    struct st_vlAtendimentoGrupoAtual status;
    XMLGen xmlGen;

    memset (&dados,   0, sizeof(st_AtendimentoGrupoAtual)); 
    memset (&status, -1, sizeof(st_vlAtendimentoGrupoAtual)); 

    dados.idAtendimento      = idAtendimento.ToLong(); 
    dados.idGrupo            = idGrupo; 
    dados.inCri              = inCriAtd; 
    dados.idUsuarioAlteracao = User.ToInt(); 
    strcpy( dados.dtEntradaFila, dtInicioTransferencia);
    strcpy( dados.dtUltimaAlteracao, sSysDate.c_str() ); 

    status.idAtendimento = 1;
    status.idGrupo = 1;
    status.inCri = 1;
    status.dtEntradaFila = 1;
    status.idUsuarioAlteracao = 1;
    status.dtUltimaAlteracao = 1;

    cWFAtendimentoGrupoAtual cwfatendimentogrupoatual(&dados, &status, &xmlGen);

    cwfatendimentogrupoatual.alterar(&xmlDpr); //ATENDIMENTO.ATENDIMENTOGRUPOATUALG00

    ULOG_END("cWF_Acao::alterarAtendimentoGrupoAtual() (2)");
}

//------------------------------------------------------------------------------------------------------------------
// Insere Grupo Atual para o processo nas tabelas de grupo
//------------------------------------------------------------------------------------------------------------------
void cWF_Acao::inserirAtendimentoGrupoAtual()
{
    ULOG_START("cWF_Acao::inserirAtendimentoGrupoAtual()");

    struct st_AtendimentoGrupoAtual dados;
    struct st_vlAtendimentoGrupoAtual status;
    XMLGen xmlGen;

    memset (&dados,   0, sizeof(st_AtendimentoGrupoAtual)); 
    memset (&status, -1, sizeof(st_vlAtendimentoGrupoAtual)); 

    dados.idAtendimento      = idAtendimento.ToLong(); 
    dados.idGrupo            = idGrupo.ToInt(); 
    dados.idUsuarioAlteracao = User.ToInt(); 
    strcpy( dados.dtUltimaAlteracao, sSysDate.c_str() );

    status.idAtendimento      = 1;
    status.idGrupo            = 1;
    status.idUsuarioAlteracao = 1;
    status.dtUltimaAlteracao  = 1;

    cWFAtendimentoGrupoAtual cwfatendimentogrupoatual(&dados, &status, &xmlGen);

    cwfatendimentogrupoatual.incluir(&xmlDpr); //ATENDIMENTO.ATENDIMENTOGRUPOATUALG00

    // ATENDIMENTO.ATENDIMENTOGRUPOBKOG00
    inserirAtendimentoGrupoBko();

    // st_EncContato m_stDados;

    // memset(&m_stDados, 0, sizeof(m_stDados));

    // m_stDados.idAtendimento=idAtendimento.ToLong();
    // m_stDados.idUsuarioBKO=User.ToInt();
    // m_stDados.idFase=2;
    // m_stDados.idTipoCarteira=idTipoCarteira;
    // m_stDados.idSegmentacao=idSegmentacao;
    // m_stDados.idProcedencia=idProcedencia;
    // m_stDados.idContato=idContato;
    // m_stDados.idGrupoAbertura=idGrupoAbertura;
    // m_stDados.idTipoPessoa=idTipoPessoa;
    // m_stDados.idTipoLinha=idTipoLinha;
    // m_stDados.idTipoRelacionamento=idTipoRelacionamento;
    // m_stDados.idCanal=idCanal;
    // m_stDados.nrTelefone = nrTelefone;

    // cEncContato EncContato(&m_stDados,&xmlDpr);

    // EncContato.gravaAtendimentoGrupoAtual(dtAbertura);

    ULOG_END("cWF_Acao::inserirAtendimentoGrupoAtual()");
}

//------------------------------------------------------------------------------------------------------------------
// Obtem Grupo Atual em ATENDIMENTO.ATENDIMENTOGRUPOBKO
//------------------------------------------------------------------------------------------------------------------
void cWF_Acao::getAtendimentoGrupoBko()
{
    ULOG_START("cWF_Acao::getAtendimentoGrupoBko()");

    char *p;

    st_AtendimentoGrupoAtual m_stDadosGrup;
    st_vlAtendimentoGrupoAtual m_vlDadosGrup;
    XMLGen xmlObtemAtGrAt;

    memset(&m_stDadosGrup, 0, sizeof(m_stDadosGrup));
    memset(&m_vlDadosGrup,-1, sizeof(m_vlDadosGrup));
    m_stDadosGrup.idAtendimento=idAtendimento.ToLong();
    m_vlDadosGrup.idAtendimento=1;

    cWFAtendimentoGrupoAtual cwfatendimentogrupoatual(&m_stDadosGrup, &m_vlDadosGrup, &xmlObtemAtGrAt);

    cwfatendimentogrupoatual.ObtemGrBko(); //ATENDIMENTO.ATENDIMENTOGRUPOBKO

    qtIntercambios = 0;
    idGrupoAtualBKO = 0;

    if (wta.walkTreeXMLGen(&xmlObtemAtGrAt, "qtIntercambios", &p, 0)==0)
    {
        qtIntercambios= atoi(p);
        XMLString::release(&p);
    }

    if (wta.walkTreeXMLGen(&xmlObtemAtGrAt, "idGrupo", &p, 0)==0)
    {
        idGrupoAtualBKO = atoi(p);
        XMLString::release(&p);
    }

    ULOG("qtIntercambios: %d", qtIntercambios);
    ULOG("idGrupoAtualBKO: %d", idGrupoAtualBKO);

    ULOG_END("cWF_Acao::getAtendimentoGrupoBko()");
}

//------------------------------------------------------------------------------------------------------------------
// Se um processo for CRI, informa se o mesmo esta dentro do prazo para retornar para
// o inbox do último consultor de relacionamento que esteve com o processo.
//------------------------------------------------------------------------------------------------------------------
bool cWF_Acao::processoCriDentroPrazoRetInboxSimNao() 
{
    ULOG_START("cWF_Acao::processoCriDentroPrazoRetInboxSimNao()");

    bool retorno;

    cWFAtdTratamentoCri AtdTratamentoCri;

    retorno = AtdTratamentoCri.processoDentroPrazoRetInboxSimNao(idAtendimento.ToLong());

    ULOG_END("cWF_Acao::processoCriDentroPrazoRetInboxSimNao()");

    return retorno;
}
//------------------------------------------------------------------------------------------------------------------
// Obtiene Grupo Atual de Atendimento
//------------------------------------------------------------------------------------------------------------------
void cWF_Acao::getAtendimentoGrupoCri() 
{
    ULOG_START("cWF_Acao::getAtendimentoGrupoCri()");

    char *p;

    st_AtdTratamentoCri m_stDadosGrupCri;
    st_vlAtdTratamentoCri m_vlDadosGrupCri;
    XMLGen xmlObtemAtGrCri;

    memset(&m_stDadosGrupCri, 0, sizeof(m_stDadosGrupCri));
    memset(&m_vlDadosGrupCri,-1, sizeof(m_vlDadosGrupCri));
    m_stDadosGrupCri.idAtendimento=idAtendimento.ToLong();
    m_vlDadosGrupCri.idAtendimento=1;

    ULOG("idAtendimento: %d", m_stDadosGrupCri.idAtendimento);

    cWFAtdTratamentoCri AtdTratamentoCri(&m_stDadosGrupCri, &m_vlDadosGrupCri, &xmlObtemAtGrCri);

    AtdTratamentoCri.getGrupoCri();

    idGrupoAtual = 0;
    idPessoaLinhaHistorico = 0;

    if (wta.walkTreeXMLGen(&xmlObtemAtGrCri, "idGrupo", &p, 0)==0)
    {
        idGrupoAtual= atoi(p);
        XMLString::release(&p);         
    }
    
    if (wta.walkTreeXMLGen(&xmlObtemAtGrCri, "idPessoaLinhaHistorico", &p, 0)==0)
    {
        idPessoaLinhaHistorico= atol(p);
        XMLString::release(&p);         
    }

    ULOG("idGrupoAtual: %d", idGrupoAtual);
    ULOG("idPessoaLinhaHistorico: %ld", idPessoaLinhaHistorico);

    ULOG_END("cWF_Acao::getAtendimentoGrupoCri()");
}

//------------------------------------------------------------------------------------------------------------------
// Obtiene Grupo Atual para Reabertura
//------------------------------------------------------------------------------------------------------------------
void cWF_Acao::getGrupoAtualReabertura() 
{
    ULOG_START("cWF_Acao::getGrupoAtualReabertura()");

    st_VariaveisUsuario variaveisUsuario;

    variaveisUsuario.idFase 				= 2;
    variaveisUsuario.idTipoCarteira 		= idTipoCarteira;
    variaveisUsuario.idSegmentacao 			= idSegmentacao;
    variaveisUsuario.idProcedencia 			= idProcedencia;
    variaveisUsuario.idContato 				= idContato;
    variaveisUsuario.idGrupoAbertura 		= idGrupoAbertura;
    variaveisUsuario.idTipoPessoa 			= idTipoPessoa;
    variaveisUsuario.idTipoLinha 			= idTipoLinha;
    variaveisUsuario.idTipoRelacionamento 	= idTipoRelacionamento;
    variaveisUsuario.idCanal 				= idCanal;
    variaveisUsuario.idUFOperadora			= 0; // Como não temos esta informação passamos 0 para que não seja avaliada na consulta de variáveis
    variaveisUsuario.idPessoaUsuario 		= User.ToInt();

    Collection grupos;

    proCPesquisaGrupoFaseVariablesUsuario(&variaveisUsuario, &grupos);

    // Determina o próximo grupo que o atendimento deve ser associado.
    if (grupos.GetCount() == 0)
    {
        proCPesquisaGrupoFaseUsuario(&variaveisUsuario, &grupos);
    }

    if (grupos.GetCount() == 0)
    {
        variaveisUsuario.idFase = 1;
        proCPesquisaGrupoFaseUsuario(&variaveisUsuario, &grupos);
    }

    char *idProxGrupoStr = (char*) grupos.GetItem(0);

    idGrupoAtual = idProxGrupoStr ? atoi(idProxGrupoStr) : 0;

    ULOG("idGrupoAtual: %d", idGrupoAtual);

    ULOG_END("cWF_Acao::getGrupoAtualReabertura()");
}

//------------------------------------------------------------------------------------------------------------------
// Informa se o próximo estado é de retorno
//------------------------------------------------------------------------------------------------------------------
bool cWF_Acao::ProximoEstadoRetornoSimNao()
{
    switch (idAgrEstTPrFt.ToInt())
    {
        case 6:
        case 7:
        case 19:
        case 20:
            return true;
    }

    return false;
}

//------------------------------------------------------------------------------------------------------------------
// Obtém o grupo para a fase de retorno ou tratamento
//------------------------------------------------------------------------------------------------------------------
bool cWF_Acao::getGrupoRetorno() 
{
    ULOG_START("cWF_Acao::getGrupoRetorno()");

    bool retorno = true;
    int idGrupoDestino=0;

    //==========================================================================
    // Incidência 3271 altera regra de grupo de destino para processos sendo
    // encerrados e revoga qualquer inciência anterior
    // Vide BUG_3271_DES - Atualizada Gestão de Processos - Fase Retorno v.2.3.doc
    // para maiores detalhes. Devido a isso a incidencia 720446, 19-05-2006
    // que havia sido implementada neste ponto não tem mais efeito.
    //
    if ( idTipoRetornoContato == TP_RET_COM_RET_GRP_BKO )
    {
        idGrupoDestino = idGrupoAtual;
    }
    else if ( idTipoRetornoContato == TP_RET_COM_RET_GRP_RET )
    {
        st_VariaveisUsuario variaveisUsuario;

        variaveisUsuario.idContato            = idContato;
        variaveisUsuario.idTipoCarteira       = idTipoCarteira;
        variaveisUsuario.idSegmentacao        = idSegmentacao;
        variaveisUsuario.idProcedencia        = idProcedencia;
        variaveisUsuario.idGrupoAbertura      = idGrupoAbertura;
        variaveisUsuario.idTipoPessoa         = idTipoPessoa;
        variaveisUsuario.idTipoLinha          = idTipoLinha;
        variaveisUsuario.idTipoRelacionamento = idTipoRelacionamento;
        variaveisUsuario.idCanal              = idCanal;
        variaveisUsuario.idUFOperadora        = idUFOperadora;
        variaveisUsuario.idFase               = RETORNO;

        inAssociado = 0;

        idGrupoDestino =
            proCPesquisaGrupoRetornoGrupoRetorno(&variaveisUsuario,0/*NMGRUPO*/,&inAssociado);
    }
    else
    { // O que um processo SEM RETORNO esta fazendo aqui ? (possível erro no cadastro de fluxo!)
        ULOG("Processo sem retorno. Grupo atual será mantido");
        idGrupoDestino = idGrupoAtual;
    }

    if ( idGrupoDestino > 0 )
    {
        idGrupo = idGrupoDestino;
        ULOG("idGrupo=%d", idGrupo.ToInt());
    }
    else
    {
        ULOG("Grupo de destino não encontrado");
        retorno = false;
    }

    ULOG_END("cWF_Acao::getGrupoRetorno()");

    return retorno;
}

//------------------------------------------------------------------------------------------------------------------
// Obtiene Grupo para Proximo Nivel
//------------------------------------------------------------------------------------------------------------------
void cWF_Acao::getGrupoProximoNivel() 
{
    ULOG_START("cWF_Acao::getGrupoProximoNivel()");

    st_VariaveisUsuario variaveisUsuario;

    variaveisUsuario.idAtendimento 			= idAtendimento.ToLong();
    variaveisUsuario.idContato 				= idContato;
    variaveisUsuario.idTipoCarteira 		= idTipoCarteira;
    variaveisUsuario.idSegmentacao 			= idSegmentacao;
    variaveisUsuario.idProcedencia 			= idProcedencia;
    variaveisUsuario.idGrupoAbertura 		= idGrupoAbertura;
    variaveisUsuario.idTipoPessoa 			= idTipoPessoa;
    variaveisUsuario.idTipoLinha 			= idTipoLinha;
    variaveisUsuario.idTipoRelacionamento 	= idTipoRelacionamento;
    variaveisUsuario.idCanal 				= idCanal;

    Collection grupos;

    proCPesquisaGrupoProxNivel(&variaveisUsuario, &grupos);

    // Determina o próximo grupo que o atendimento deve ser associado.
    if (grupos.GetCount() == 0)
    {
        proCObtemWFNivelGrAt(variaveisUsuario.idAtendimento, &grupos);
    }

    char *idProxGrupoStr = (char*) grupos.GetItem(0);

    idGrupo = idProxGrupoStr ? atoi(idProxGrupoStr) : 0;

    ULOG("idGrupo=%d", idGrupo.ToInt());

    ULOG_END("cWF_Acao::getGrupoProximoNivel()");
}

//------------------------------------------------------------------------------------------------------------------
// pré-processamento de relatórios
//------------------------------------------------------------------------------------------------------------------
bool cWF_Acao::preProcessarRelatorios(cWFPPRelatorios &cwfpprelatorios )
{
    ULOG_START("cWF_Acao::preProcessarRelatorios()");

    bool retorno;

    cwfpprelatorios.setarIdPessoaUsuario(User.ToInt());

    if ( retorno = cwfpprelatorios.atualizar(),!retorno )
    {
        ULOG("Erro %s:%s"
            ,cwfpprelatorios.obterCodErro(),cwfpprelatorios.obterMsgErro());
    }

    ULOG_END("cWF_Acao::preProcessarRelatorios()");

    return retorno;
}

//------------------------------------------------------------------------------------------------------------------
// Processa campos do relatório PRODUTIVIDADE POR REPRESENTANTE de acordo com as ações.
// e QUANTIDADES E MOTIVOS POR REPRESENTANTE BKO.
// Este pré-processamento foi criado para tornar viável o tempo de execução dos relatórios
// e para atender as incidências 2331 e SIME00000060 no TD de Pré-Produção.
//------------------------------------------------------------------------------------------------------------------
bool cWF_Acao::preProcessarRelEncFech(int /*acao*/)
{
    ULOG_START("cWF_Acao::preProcessarRelEncFech()");

    st_PPRelatorios dados;
    st_vlPPRelatorios status;

    cWFPPRelatorios cwfpprelatorios(&dados,&status);

    cwfpprelatorios.resetarParametros();

    cwfpprelatorios.setarIdAtendimento(idAtendimento.ToLong());
    cwfpprelatorios.setarIdGrupoAtual(idGrupoAtual);
    cwfpprelatorios.setarValoresRelProdRepresentante(idAtividade.ToInt(),idFase.ToInt());
    cwfpprelatorios.setarIdFase(idFase.ToInt());
    cwfpprelatorios.setarIdMotivo(idMotivo.ToInt());

    bool retorno = preProcessarRelatorios(cwfpprelatorios);

    ULOG_END("cWF_Acao::preProcessarRelEncFech()");

    return retorno;
}

//------------------------------------------------------------------------------------------------------------------
// Obtém Grupo BKO para a fase de retorno para processo com retorno por grupo de retorno.
//------------------------------------------------------------------------------------------------------------------
// void cWF_Acao::getGrupoProximaFase() 
// {
//     ULOG_START("cWF_Acao::getGrupoProximaFase()");
// 
//     st_VariaveisUsuario variaveisUsuario;
// 
//     variaveisUsuario.idContato              = idContato;
//     variaveisUsuario.idTipoCarteira         = idTipoCarteira;
//     variaveisUsuario.idSegmentacao          = idSegmentacao;
//     variaveisUsuario.idProcedencia          = idProcedencia;
//     variaveisUsuario.idGrupoAbertura        = idGrupoAbertura;
//     variaveisUsuario.idTipoPessoa           = idTipoPessoa;
//     variaveisUsuario.idTipoLinha            = idTipoLinha;
//     variaveisUsuario.idTipoRelacionamento   = idTipoRelacionamento;
//     variaveisUsuario.idCanal                = idCanal;
//     variaveisUsuario.idUFOperadora          = idUFOperadora;
//     variaveisUsuario.idFase                 = RETORNO;
// 
//     // Incidência 3271 - Homologação VIVO - Julho, 2006
//     // Processos com retorno por grupo de retorno atende às regras
//     // definidas no documento 'BUG_3271_DES - Atualizada Gestão de Processos - Fase Retorno v.2.3.doc'
//     idGrupo = proCPesquisaGrupoRetornoGrupoRetorno(&variaveisUsuario,0/*NMGRUPO*/,&inAssociado);
// 
//     ULOG("idGrupo=%d", idGrupo.ToInt());
// 
//     ULOG_END("cWF_Acao::getGrupoProximaFase()");
// }

//------------------------------------------------------------------------------------------------------------------
// Obtém grupo da próxima fase sem considerar se existe parametrização na aba retorno
// na parametrização da árvore de contatos
//------------------------------------------------------------------------------------------------------------------
void cWF_Acao::getGrupoProximaFaseSemConsiderarRetorno() 
{
    ULOG_START("cWF_Acao::getGrupoProximaFaseSemConsiderarRetorno()");

    st_VariaveisUsuario variaveisUsuario;

    variaveisUsuario.idContato              = idContato;
    variaveisUsuario.idFase                 = idFase.ToInt() + 1;
    variaveisUsuario.idTipoCarteira         = idTipoCarteira;
    variaveisUsuario.idSegmentacao          = idSegmentacao;
    variaveisUsuario.idProcedencia          = idProcedencia;
    variaveisUsuario.idGrupoAbertura        = idGrupoAbertura;
    variaveisUsuario.idTipoPessoa           = idTipoPessoa;
    variaveisUsuario.idTipoLinha            = idTipoLinha;
    variaveisUsuario.idTipoRelacionamento   = idTipoRelacionamento;
    variaveisUsuario.idCanal                = idCanal;
    variaveisUsuario.idUFOperadora          = idUFOperadora;

    ULOG("idContato=%d", variaveisUsuario.idContato);
    ULOG("idFase=%d", variaveisUsuario.idFase);
    ULOG("idTipoCarteira=%d", variaveisUsuario.idTipoCarteira);
    ULOG("idSegmentacao=%d", variaveisUsuario.idSegmentacao);
    ULOG("idProcedencia=%d", variaveisUsuario.idProcedencia);
    ULOG("idContato=%d", variaveisUsuario.idContato);
    ULOG("idGrupoAbertura=%d", variaveisUsuario.idGrupoAbertura);
    ULOG("idTipoPessoa=%d", variaveisUsuario.idTipoPessoa);
    ULOG("idTipoLinha=%d", variaveisUsuario.idTipoLinha);
    ULOG("idTipoRelacionamento=%d", variaveisUsuario.idTipoRelacionamento);
    ULOG("idCanal=%d", variaveisUsuario.idCanal);
    ULOG("idUFOperadora=%d", variaveisUsuario.idUFOperadora);

    int idGrupoDestino;
    inAssociado = 0;

    if (proCPesquisaGrupoFaseVariables(&variaveisUsuario,idGrupoDestino) == false)
    {
        inAssociado = 1;

        proCPesquisaGrupoFase(&variaveisUsuario,idGrupoDestino);
    }

    idGrupo = idGrupoDestino;

    ULOG("idGrupo=%d", idGrupo.ToInt());

    ULOG_END("cWF_Acao::getGrupoProximaFaseSemConsiderarRetorno()");
}

//------------------------------------------------------------------------------------------------------------------
// Obtiene Grupo CRI para Proxima Fase
//------------------------------------------------------------------------------------------------------------------
void cWF_Acao::getGrupoProximaFaseCRI() 
{
    ULOG_START("cWF_Acao::getGrupoProximaFaseCRI()");

    st_VariaveisUsuario variaveisUsuario;

    variaveisUsuario.idContato 				= idContato;
    variaveisUsuario.idFase					= idFase.ToInt() + 1;
    variaveisUsuario.idTipoCarteira 		= idTipoCarteira;
    variaveisUsuario.idSegmentacao 			= idSegmentacao;
    variaveisUsuario.idProcedencia 			= idProcedencia;
    variaveisUsuario.idGrupoAbertura 		= idGrupoAbertura;
    variaveisUsuario.idTipoPessoa 			= idTipoPessoa;
    variaveisUsuario.idTipoLinha 			= idTipoLinha;
    variaveisUsuario.idTipoRelacionamento 	= idTipoRelacionamento;
    variaveisUsuario.idCanal 				= idCanal;

    ULOG("idContato=%d", variaveisUsuario.idContato);
    ULOG("idFase=%d", variaveisUsuario.idFase);
    ULOG("idTipoCarteira=%d", variaveisUsuario.idTipoCarteira);
    ULOG("idSegmentacao=%d", variaveisUsuario.idSegmentacao);
    ULOG("idProcedencia=%d", variaveisUsuario.idProcedencia);
    ULOG("idContato=%d", variaveisUsuario.idContato);
    ULOG("idGrupoAbertura=%d", variaveisUsuario.idGrupoAbertura);
    ULOG("idTipoPessoa=%d", variaveisUsuario.idTipoPessoa);
    ULOG("idTipoLinha=%d", variaveisUsuario.idTipoLinha);
    ULOG("idTipoRelacionamento=%d", variaveisUsuario.idTipoRelacionamento);
    ULOG("idCanal=%d", variaveisUsuario.idCanal);

    Collection grupos;

    proCPesquisaGrupoFaseVariablesCRI(&variaveisUsuario, &grupos);

    inAssociado = 0;

    // Determina o próximo grupo cri ao qual o atendimento deve ser associado.
    if (grupos.GetCount() == 0)
    {
        inAssociado = 1;
        proCPesquisaGrupoFaseCRI(&variaveisUsuario, &grupos);
    }

    char *idProxGrupoStr = (char*) grupos.GetItem(0);

    idGrupo = idProxGrupoStr ? atoi(idProxGrupoStr) : 0;

    ULOG("idGrupo=%d", idGrupo);

    ULOG_END("cWF_Acao::getGrupoProximaFaseCRI()");
}

//------------------------------------------------------------------------------------------------------------------
// Obtém o estado/sub-estado futuro de um processo
//------------------------------------------------------------------------------------------------------------------
void cWF_Acao::getAgrupEstadoTpProcFt(bool proxAgrupDifAtual)
{
    ULOG_START("cWF_Acao::getAgrupEstadoTpProcFt()");

    // Obtém o próximo estado / sub-estado do processo
    st_AgrupamentoEstadoTpProc		stAgrupamento;
    st_vlAgrupamentoEstadoTpProc	vlAgrupamento;

    memset(&stAgrupamento,0,sizeof(stAgrupamento));
    memset(&vlAgrupamento,-1,sizeof(vlAgrupamento));

    stAgrupamento.idAtividade = idAtividade.ToInt();
    stAgrupamento.idAgrupamentoEstadoTpProc = idAgrEstTPrAt.ToInt();

    cWFAgrupamentoEstadoTpProc pa(&stAgrupamento, &vlAgrupamento);

    idAgrEstTPrFt = pa.proximoAgrupamento(proxAgrupDifAtual);

    ULOG_END("cWF_Acao::getAgrupEstadoTpProcFt()");
}

//------------------------------------------------------------------------------------------------------------------
// Obtiene Ultimo Andamento Transferencia
//------------------------------------------------------------------------------------------------------------------
void cWF_Acao::getAndamentoTransferencia() 
{
    ULOG_START("cWF_Acao::getAndamentoTransferencia()");

    char *p;
    struct st_AndamentoTransferencia dadosTrans; 
    struct st_vlAndamentoTransferencia statusTrans; 
    XMLGen xmlUltAndTrf;

    memset (&dadosTrans , 0, sizeof( st_AndamentoTransferencia));
    memset (&statusTrans,-1, sizeof( st_vlAndamentoTransferencia));

    dadosTrans.idAtendimento = idAtendimento.ToLong();
    statusTrans.idAtendimento = 1;

    cWFAndamentoTransferencia cwfAndamentoTransferencia(&dadosTrans,&statusTrans, &xmlUltAndTrf);

    cwfAndamentoTransferencia.obtemTrf();

    idAndamentoTrans = 0;

    if (wta.walkTreeXMLGen(&xmlUltAndTrf, "idAndamento", &p, 0 )==0)
    {
       idAndamentoTrans=atol(p);
       XMLString::release(&p);
    }
    
    ULOG("idAndamentoTrans=%d", idAndamentoTrans);

    ULOG_END("cWF_Acao::getAndamentoTransferencia()");
}

//------------------------------------------------------------------------------------------------------------
// Obteniendo Grupo Receptor
//------------------------------------------------------------------------------------------------------------

void cWF_Acao::getAtendimentoGrupoDevolucao()
{
    ULOG_START("cWF_Acao::getAtendimentoGrupoDevolucao()");

    char *p;

    st_AtendimentoGrupoDevolucao    dados; 
    st_vlAtendimentoGrupoDevolucao  status;
    XMLGen xmlObtemGrDev;

    memset ( &dados,   0, sizeof(st_AtendimentoGrupoDevolucao)); 
    memset ( &status, -1, sizeof(st_vlAtendimentoGrupoDevolucao)); 

    dados.idAtendimento = idAtendimento.ToLong();

    status.idAtendimento = 1;

    idGrupo = 0;

    cWFAtendimentoGrupoDevolucao gr(&dados, &status, &xmlObtemGrDev);
    gr.consultar();

    if ( wta.walkTreeXMLGen(&xmlObtemGrDev, "idGrupo", &p, 0) == 0 )
    {
        idGrupo= atoi(p);
        XMLString::release(&p);
    }

    ULOG("idGrupo=%d", idGrupo.ToInt());

    ULOG_END("cWF_Acao::getAtendimentoGrupoDevolucao()");
}

//------------------------------------------------------------------------------------------------------------
// Obteniendo Usuario Receptor
//------------------------------------------------------------------------------------------------------------

void cWF_Acao::getAtendimentoUsuarioDevolucao()
{
    ULOG_START("cWF_Acao::getAtendimentoUsuarioDevolucao()");

    char *p;

    st_AtendimentoUsuarioDevolucao    dados; 
    st_vlAtendimentoUsuarioDevolucao  status;
    XMLGen xmlObtemUsrDev;

    memset ( &dados,   0, sizeof(st_AtendimentoUsuarioDevolucao)); 
    memset ( &status, -1, sizeof(st_vlAtendimentoUsuarioDevolucao)); 

    dados.idAtendimento = idAtendimento.ToLong(); 

    status.idAtendimento = 1;

    cWFAtendimentoUsuarioDevolucao  usu(&dados, &status, &xmlObtemUsrDev);

    UsuarioAtual = 0;

    usu.consultar();
    if (wta.walkTreeXMLGen(&xmlObtemUsrDev, "idPessoaUsuario", &p, 0)==0)
    {
        UsuarioAtual= atoi(p);
        XMLString::release(&p);
    }

    ULOG("UsuarioDevolucao=%d", UsuarioAtual.ToInt());
    ULOG_END("cWF_Acao::getAtendimentoUsuarioDevolucao()");
}

//------------------------------------------------------------------------------------------------------------
// Obteniendo Cancelamento Solicitado
//------------------------------------------------------------------------------------------------------------

void cWF_Acao::getCancelamentoSolicitado()
{
    ULOG_START("cWF_Acao::getCancelamentoSolicitado()");

    char *p;

    struct st_CancelamentoSolicitado    calcSol;
    struct st_vlCancelamentoSolicitado vcalcSol;
    XMLGen xmlObtemCancelamento;

    memset ( &calcSol ,  0, sizeof( st_CancelamentoSolicitado   )); 
    memset ( &vcalcSol, -1, sizeof( st_vlCancelamentoSolicitado ));

    calcSol.idAtendimento = idAtendimento.ToLong();

    vcalcSol.idAtendimento = 1;

    cWFCancelamentoSolicitado cwfCancelamentoSolicitado(&calcSol, &vcalcSol, &xmlObtemCancelamento); 
    cwfCancelamentoSolicitado.consultar(); 

    idAtendimentoCancelamento= 0;

    if (wta.walkTreeXMLGen(&xmlObtemCancelamento, "idAtendimento", &p, 0)==0)
    {
        idAtendimentoCancelamento= atol(p);
        XMLString::release(&p);         
    }   

    ULOG("Cancelamento Atendimento: %d", idAtendimentoCancelamento);

    ULOG_END("cWF_Acao::getCancelamentoSolicitado()");
}

//------------------------------------------------------------------------------------------------------------------
// Obtem Alerta Atendimento
//------------------------------------------------------------------------------------------------------------


void cWF_Acao::getAlertaAtendimento()
{
    ULOG_START("cWF_Acao::getAlertaAtendimento()");

    char *p;

    XMLGen xmlObtemAlerta;
    XMLGen xmlSetAlerta;

    struct st_AtendimentoMensagem    atdMsg; 
    struct st_vlAtendimentoMensagem vatdMsg; 

    memset ( &atdMsg , 0, sizeof( st_AtendimentoMensagem   )); 
    memset ( &vatdMsg,-1, sizeof( st_vlAtendimentoMensagem ));

    atdMsg.idAtividade              = idAtividade.ToInt();

    vatdMsg.idAtividade = 1;

    cWFAtendimentoMensagem catdMsg( &atdMsg, &vatdMsg, &xmlObtemAlerta );

    catdMsg.obterMensagem(); 
    if (wta.walkTreeXMLGen(&xmlObtemAlerta, "idAlerta", &p, 0)==0)
    {
        idAlerta= atoi(p);
        XMLString::release(&p);         
    }   
    if (wta.walkTreeXMLGen(&xmlObtemAlerta, "nmCor", &p, 0)==0)
    {
        strcpy(nmCor, p);
        XMLString::release(&p);         
    }   
    if (wta.walkTreeXMLGen(&xmlObtemAlerta, "nrCriticidade", &p, 0)==0)
    {
        nrCriticidade = atof(p);
        XMLString::release(&p);         
    }   

    ULOG("getAlertaAtendimento - Executando...");

    ULOG_END("cWF_Acao::getAlertaAtendimento()");
}

//------------------------------------------------------------------------------------------------------------------
// Obtem Alerta Dinamica Atendimento
//------------------------------------------------------------------------------------------------------------


void cWF_Acao::getAlertaDinamicaAtendimento()
{
    ULOG_START("cWF_Acao::getAlertaDinamicaAtendimento()");

    char *p;

    double nrCriticidadeAlerta;

    XMLGen xmlObtemAlerta;
    XMLGen xmlSetAlerta;

    struct st_AtendimentoMensagem    atdMsg; 
    struct st_vlAtendimentoMensagem vatdMsg; 

    memset ( &atdMsg , 0, sizeof( st_AtendimentoMensagem   )); 
    memset ( &vatdMsg,-1, sizeof( st_vlAtendimentoMensagem ));

    atdMsg.idAtendimento              = idAtendimento.ToLong();

    vatdMsg.idAtendimento = 1;

    cWFAtendimentoMensagem catdMsg( &atdMsg, &vatdMsg, &xmlObtemAlerta );

    catdMsg.obterAlertaDinamica(); 
    if (wta.walkTreeXMLGen(&xmlObtemAlerta, "nrCriticidadeAlerta", &p, 0)==0)
    {
        nrCriticidadeAlerta = atof(p);
        XMLString::release(&p);         
    }   

    ULOG("nrCriticidadeAlerta: %e ", nrCriticidadeAlerta);
    ULOG("nrCriticidade: %e ", nrCriticidade);

    if (nrCriticidadeAlerta > nrCriticidade)
    {
        ULOG("Criticidade Dinamica mayor que la estatica");
        if (wta.walkTreeXMLGen(&xmlObtemAlerta, "nmCor", &p, 0)==0)
        {
            strcpy(nmCor, p);
            XMLString::release(&p);
        }
    }
    ULOG("nmCor: %s ", nmCor);

    if (wta.walkTreeXMLGen(&xmlObtemAlerta, "nrCriticidade", &p, 0)==0)
    {
        nrCriticidade = atof(p);
        XMLString::release(&p);
    }
    if (wta.walkTreeXMLGen(&xmlObtemAlerta, "idAlerta", &p, 0)==0)
    {
        idAlerta = atoi(p);
        XMLString::release(&p);
    }

    ULOG("nrCriticidade: %e ", nrCriticidade);

    ULOG_END("cWF_Acao::getAlertaDinamicaAtendimento()");
}

//------------------------------------------------------------------------------------------------------------------
// inclui em ATENDIMENTO.ATENDIMENTOTESTE
//------------------------------------------------------------------------------------------------------------
void cWF_Acao::incluirAtendimentoTeste()
{
    ULOG_START("cWF_Acao::incluirAtendimentoTeste()");

    struct st_AtendimentoTeste dados;
    struct st_vlAtendimentoTeste status;

    memset (&dados, 0, sizeof(st_AtendimentoTeste)); 
    memset (&status,-1,sizeof(st_vlAtendimentoTeste));

    SAFE_STRNCPY( dados.dtTeste, sSysDate.c_str() ); 
    SAFE_STRNCPY( dados.dtUltimaAlteracao, sSysDate.c_str() ); 
    SAFE_STRNCPY( dados.dsTeste, dsObservacao.c_str() ); 
    dados.idAtendimento = idAtendimento.ToLong();
    dados.idPessoaUsuario = User.ToInt();

    status.idAtendimento = 1;
    status.idPessoaUsuario = 1;
    status.dtTeste = 1;
    status.dtUltimaAlteracao = 1;
    status.dsTeste = 1;

    cWFAtendimentoTeste cwfAtdTeste;  
    
    idAtendimentoTeste =
        cwfAtdTeste.incluir(&dados,&status,&xmlDpr );

    ULOG_END("cWF_Acao::incluirAtendimentoTeste()");
}

//------------------------------------------------------------------------------------------------------------------
// Informa os dados alterados para o DPR
//------------------------------------------------------------------------------------------------------------
void cWF_Acao::registrarAcaoDPR(int idUsuarioWeb,int /*idContato*/,const char *nomeServico)
{
    ULOG_START("cWF_Acao::registrarAcaoDPR");

    if ( idAgrEstTPrFt.ToInt() >= INI_AGRPTPPROC_TECNICO 
       && idAgrEstTPrFt.ToInt() <= FIM_AGRPTPPROC_TECNICO )
    {
        cWFAtdGerarXMLDPR cwfatdgerarxmldpr;

        if ( idAtendimento.ToLong() > 0 )
        {
            char idAtendimentoStr[32];

            sprintf(idAtendimentoStr,"%lu",idAtendimento.ToLong());
            xmlDpr.idAtendimento = idAtendimentoStr;
        }

        xmlDpr.idUser = idUsuarioWeb;
        xmlDpr.nomeServico = nomeServico;
        //xmlDpr.idContato = idContato;

        cwfatdgerarxmldpr.persistirDadosDPRContatoTecnico(&xmlDpr);
    }
    else
    {
        ULOG("Processo %lu não é técnico",idAtendimento.ToLong());
    }

    ULOG_END("cWF_Acao::registrarAcaoDPR");
}

//------------------------------------------------------------------------------------------------------------------
// Obtem forma de envio de retorno (Email / SMS)
//------------------------------------------------------------------------------------------------------------
void cWF_Acao::getFormaRetornoAtendimento()
{
    ULOG_START("cWF_Acao::getFormaRetornoAtendimento()");

#ifndef WIN32

    stBaixaHistorico datosBaixaHistorico;

    datosBaixaHistorico.idBxa = idBaixa.ToInt();
    datosBaixaHistorico.idAtendimento = idAtendimento.ToLong();

    getFormaRetorno(&datosBaixaHistorico);

    if ( datosBaixaHistorico.inEnvEmail == 1 )
    {
        st_EmailUtil m_stDadosEmail;
        st_vlEmailUtil m_vlDadosEmail;

        memset(&m_stDadosEmail, 0, sizeof(m_stDadosEmail));
        memset(&m_vlDadosEmail,-1, sizeof(m_vlDadosEmail));

        strcpy(m_stDadosEmail.destinatario, datosBaixaHistorico.dsCom);
        m_vlDadosEmail.destinatario = 1;

        strcpy(m_stDadosEmail.assunto, "VIVO informa......");
        m_vlDadosEmail.assunto = 1;

        strcpy(m_stDadosEmail.mensagem, datosBaixaHistorico.dsMsg);
        m_vlDadosEmail.mensagem = 1;

        XMLGen xmlMail;
        cEmailUtil email(&m_stDadosEmail, &m_vlDadosEmail, &xmlMail);

        email.enviar();
    }

    if (datosBaixaHistorico.inEnvSms == 1)
    {
        st_SMSUtil m_stDadosSms;
        st_vlSMSUtil m_vlDadosSms;

        memset(&m_stDadosSms, 0, sizeof(m_stDadosSms));
        memset(&m_vlDadosSms,-1, sizeof(m_vlDadosSms));

        char seps[] = "()-";
        char *token = strtok(datosBaixaHistorico.dsCom,seps);

        while( token )
        {
            strcat(m_stDadosSms.destinatario,token);
            token = strtok(NULL,seps);
        }

        if ( m_stDadosSms.destinatario[0] )
        {
            m_vlDadosSms.destinatario = 1;

            strcpy(m_stDadosSms.mensagem, datosBaixaHistorico.dsMsg);
            m_vlDadosSms.mensagem = 1;

            XMLGen xmlSms;
            cSMSUtil sms(&m_stDadosSms, &m_vlDadosSms, &xmlSms);

            sms.enviar();
        }
        else
        {
            ULOGW("Sem dados de destinatario para envio de SMS");
        }
    }
#else
    ULOGW("** Não envia SMS ou Email em ambiente Windows **");
#endif

    ULOG_END("cWF_Acao::getFormaRetornoAtendimento()");
}

//------------------------------------------------------------------------------------------------------------------
// Obtem Mensagem de Baixa
//------------------------------------------------------------------------------------------------------------
// bool cWF_Acao::getBaixaMensagem()
// {
//     ULOG_START("cWF_Acao::getBaixaMensagem()");
// 
// 	bool retorno;
//     stMensagemBaixa datosMensagemBaixa;
// 
//     datosMensagemBaixa.idMsgBxa=idBaixaMensagem.ToInt();
//     datosMensagemBaixa.idBxa=idBaixa.ToInt();
//     
// 	if ( retorno=getBaixaMensagem(&datosMensagemBaixa),retorno )
//     {
//         idBaixaMensagem=datosMensagemBaixa.idBaixaMensagem;
//     }
//     else
//     {
//         idBaixaMensagem = 0;
//     }
// 
//     ULOG_END("cWF_Acao::getBaixaMensagem()");
//     
//     return retorno;
// }

//------------------------------------------------------------------------------------------------------------------
// Obter Atendimento Baixa Atual
//------------------------------------------------------------------------------------------------------------------
void cWF_Acao::getAtendimentoBaixaAtual() 
{
    ULOG_START("cWF_Acao::getAtendimentoBaixaAtual()");

    cWFAtendimentoBaixaAtual AtendimentoBaixaAtual;

    AtendimentoBaixaAtual.obterIdAtendimentoBaixaHistorico(idAtendimento.ToLong(),&idBaixaAtendimento);

    ULOG("idBaixaAtendimento=%d",idBaixaAtendimento);

    ULOG_END("cWF_Acao::getAtendimentoBaixaAtual()");
}

int cWF_Acao::getMotivoCancelamento()
{
    ULOG_START("cWF_Acao::getMotivoCancelamento()");

    struct st_AndamentoMotivo   dados;
    struct st_vlAndamentoMotivo status; 
    XMLGen xmlGen;

    memset( &dados , 0, sizeof( st_AndamentoMotivo  )); 
    memset( &status,-1, sizeof( st_vlAndamentoMotivo));

    int idMotivoCancelamento = 0;

    cWFAndamentoMotivo cwfAndamentoMotivo(&dados,&status,&xmlGen);
    idMotivoCancelamento = cwfAndamentoMotivo.obterMotivoCancelamento(idAgrEstTPrFt.ToInt());

    ULOG("idMotivoCancelamento: %d ", idMotivoCancelamento);
    
    return idMotivoCancelamento;
}

//------------------------------------------------------------------------------------------------------------------
// Marcacao Suspeito
//------------------------------------------------------------------------------------------------------------------
int cWF_Acao::getUsuarioAtendimentoSuspeito()
{
    ULOG_START("cWF_Acao::getAtendimentoSuspeito()");

    st_AtendimentoSuspeito m_stDadosAtenSuspeito;
    st_vlAtendimentoSuspeito m_vlDadosAtenSuspeito;
    XMLGen ExcSusp;

    memset(&m_stDadosAtenSuspeito, 0, sizeof(m_stDadosAtenSuspeito));
    memset(&m_vlDadosAtenSuspeito,-1, sizeof(m_vlDadosAtenSuspeito));
    
    m_stDadosAtenSuspeito.idAtendimento=idAtendimento.ToLong();
    m_vlDadosAtenSuspeito.idAtendimento=1;

    int idUsuarioSuspeito = 0;

    cWFAtendimentoSuspeito AtendimentoSuspeito(&m_stDadosAtenSuspeito, &m_vlDadosAtenSuspeito, &ExcSusp);
    idUsuarioSuspeito = AtendimentoSuspeito.obterUsuarioSuspeito();

    ULOG("idUsuarioSuspeito: %d ", idUsuarioSuspeito);

    return idUsuarioSuspeito;
}

//------------------------------------------------------------------------------------------------------------------
// Remove Baixa Atual
//------------------------------------------------------------------------------------------------------------------
void cWF_Acao::removeAtendimentoBaixaAtual() 
{
    ULOG_START("cWF_Acao::removeAtendimentoBaixaAtual()");

    st_AtendimentoBaixaAtual    dados;
    st_vlAtendimentoBaixaAtual  status;
    XMLGen baixa;

    memset ( &dados,   0, sizeof(st_AtendimentoBaixaAtual)); 
    memset ( &status, -1, sizeof(st_vlAtendimentoBaixaAtual)); 
    
    dados.idAtendimento = idAtendimento.ToLong(); 
    status.idAtendimento = 1;

    cWFAtendimentoBaixaAtual AtendimentoBaixaAtual(&dados, &status, &baixa);

    AtendimentoBaixaAtual.excluir(); 

    ULOG_END("cWF_Acao::removeAtendimentoBaixaAtual()");
}

//------------------------------------------------------------------------------------------------------------------
// Remove Grupo Bko de Recepcao
//------------------------------------------------------------------------------------------------------------------
void cWF_Acao::removeAtendimentoGrupoBko() 
{
    ULOG_START("cWF_Acao::removeAtendimentoGrupoBko()");

    st_AtendimentoGrupoAtual m_stDadosGrup;
    st_vlAtendimentoGrupoAtual m_vlDadosGrup;
    XMLGen xmlObtemAtGrAt;

    memset(&m_stDadosGrup, 0, sizeof(m_stDadosGrup));
    memset(&m_vlDadosGrup,-1, sizeof(m_vlDadosGrup));
    m_stDadosGrup.idAtendimento=idAtendimento.ToLong();
    m_vlDadosGrup.idAtendimento=1;

    cWFAtendimentoGrupoAtual cwfatendimentogrupoatual(&m_stDadosGrup, &m_vlDadosGrup, &xmlObtemAtGrAt);

    cwfatendimentogrupoatual.excluirBko(); // ATENDIMENTO.ATENDIMENTOGRUPOBKOG00

    ULOG_END("cWF_Acao::removeAtendimentoGrupoBko()");
}

//------------------------------------------------------------------------------------------------------------------
// Remove linha ATENDIMENTO.TRATAMENTOCRI e TRATAMENTO.TRATAMENTOGRUPOCRI
//------------------------------------------------------------------------------------------------------------------
void cWF_Acao::removeTratamentoCri()
{
    ULOG_START("cWF_Acao::removeTratamentoCri()");

    st_AtdTratamentoCri    dados; 
    st_vlAtdTratamentoCri  status;
    XMLGen xmlObtemAtGrAt;

    memset ( &dados,   0, sizeof(st_AtdTratamentoCri)); 
    memset ( &status, -1, sizeof(st_vlAtdTratamentoCri)); 

    dados.idAtendimento = idAtendimento.ToLong(); 

    status.idAtendimento = 1;

    cWFAtdTratamentoCri AtdCri(&dados, &status, &xmlObtemAtGrAt);

    AtdCri.excluirTratamentoCri();

    ULOG_END("cWF_Acao::removeTratamentoCri()");
}


//------------------------------------------------------------------------------------------------------------------
// Remove Grupo de Recepcao
//------------------------------------------------------------------------------------------------------------------
void cWF_Acao::removeAtendimentoGrupoReceptor()
{
    ULOG_START("cWF_Acao::removeAtendimentoGrupoReceptor()");

    st_AtendimentoGrupoDevolucao    dados; 
    st_vlAtendimentoGrupoDevolucao  status;
    XMLGen xmlObtemAtGrAt;

    memset ( &dados,   0, sizeof(st_AtendimentoGrupoDevolucao)); 
    memset ( &status, -1, sizeof(st_vlAtendimentoGrupoDevolucao)); 

    dados.idAtendimento = idAtendimento.ToLong(); 

    status.idAtendimento = 1;

    cWFAtendimentoGrupoDevolucao  AtendimentoGrupoDevolucao(&dados, &status, &xmlObtemAtGrAt);

    AtendimentoGrupoDevolucao.excluir();

    ULOG_END("cWF_Acao::removeAtendimentoGrupoReceptor()");
}

//------------------------------------------------------------------------------------------------------------------
// Remove Usuario de Recepcao
//------------------------------------------------------------------------------------------------------------------
void cWF_Acao::removeAtendimentoUsuarioReceptor()
{
    ULOG_START("cWF_Acao::removeAtendimentoUsuarioReceptor()");

    st_AtendimentoUsuarioDevolucao    dados; 
    st_vlAtendimentoUsuarioDevolucao  status;
    XMLGen xmlObtemAtGrAt;

    memset ( &dados,   0, sizeof(st_AtendimentoUsuarioDevolucao)); 
    memset ( &status, -1, sizeof(st_vlAtendimentoUsuarioDevolucao)); 

    dados.idAtendimento = idAtendimento.ToLong(); 

    status.idAtendimento = 1;

    cWFAtendimentoUsuarioDevolucao  AtendimentoUsuarioDevolucao(&dados, &status, &xmlObtemAtGrAt);

    AtendimentoUsuarioDevolucao.excluir(); 

    ULOG_END("cWF_Acao::removeAtendimentoUsuarioReceptor()");
}

//------------------------------------------------------------------------------------------------------------------
// Obtiene Usuario Atual de Atendimento
//------------------------------------------------------------------------------------------------------------------
bool cWF_Acao::getAtendimentoUsuarioAtual()
{
    ULOG_START("cWF_Acao::getAtendimentoUsuarioAtual()");

    bool retorno;

    cWFAtendimentoUsuarioAtual cwfatendimentousuarioatual;

    long idPessoaUsuarioAtualTemp = 
        cwfatendimentousuarioatual.obterIdPessoaUsuarioAtual(idAtendimento.ToLong());

    if ( idPessoaUsuarioAtualTemp > 0 )
    {
        idUsuarioAtendimento = idPessoaUsuarioAtualTemp;
        retorno = true;
    }
    else
    {
        idUsuarioAtendimento = 0;
        retorno = false;
    }

    ULOG("retorno: %d", retorno);
    ULOG("idUsuarioAtendimento: %d", idUsuarioAtendimento);

    ULOG_END("cWF_Acao::getAtendimentoUsuarioAtual()");

    return retorno;
}

//------------------------------------------------------------------------------------------------------------------
// Remove Usuario Atual de Atendimento
//------------------------------------------------------------------------------------------------------------------
void cWF_Acao::removeAtendimentoUsuarioAtual()
{
    ULOG_START("cWF_Acao::removeAtendimentoUsuarioAtual()");

    st_AtendimentoUsuarioAtual    dados; 
    st_vlAtendimentoUsuarioAtual  status;
    XMLGen xmlObtemUsrAt;

    memset ( &dados,   0, sizeof(st_AtendimentoUsuarioAtual)); 
    memset ( &status, -1, sizeof(st_vlAtendimentoUsuarioAtual)); 

    dados.idAtendimento = idAtendimento.ToLong(); 

    status.idAtendimento = 1;

    cWFAtendimentoUsuarioAtual cwfatendimentousuarioatual(&dados, &status, &xmlObtemUsrAt);

    cwfatendimentousuarioatual.excluir(&xmlDpr);

    ULOG_END("cWF_Acao::removeAtendimentoUsuarioAtual()");
}

//------------------------------------------------------------------------------------------------------------------
// Eliminando Grupo Atual
//------------------------------------------------------------------------------------------------------------------
void cWF_Acao::removeAtendimentoGrupoAtual()
{
    ULOG_START("cWF_Acao::removeAtendimentoGrupoAtual()");

    st_AtendimentoGrupoAtual m_stDadosGrup;
    st_vlAtendimentoGrupoAtual m_vlDadosGrup;
    XMLGen xmlObtemAtGrAt;

    memset(&m_stDadosGrup, 0, sizeof(m_stDadosGrup));
    memset(&m_vlDadosGrup,-1, sizeof(m_vlDadosGrup));
    m_stDadosGrup.idAtendimento=idAtendimento.ToLong();
    m_vlDadosGrup.idAtendimento=1;

    cWFAtendimentoGrupoAtual cwfatendimentogrupoatual(&m_stDadosGrup, &m_vlDadosGrup, &xmlObtemAtGrAt);

    cwfatendimentogrupoatual.excluir(&xmlDpr); // ATENDIMENTO.ATENDIMENTOGRUPOATUALG00

    ULOG_END("cWF_Acao::removeAtendimentoGrupoAtual()");
}

//------------------------------------------------------------------------------------------------------------------
// Eliminando Marcacao Suspeito
//------------------------------------------------------------------------------------------------------------------
void cWF_Acao::removeAtendimentoSuspeito()
{
    ULOG_START("cWF_Acao::removeAtendimentoSuspeito()");

    st_AtendimentoSuspeito m_stDadosAtenSuspeito;
    st_vlAtendimentoSuspeito m_vlDadosAtenSuspeito;
    XMLGen ExcSusp;

    memset(&m_stDadosAtenSuspeito, 0, sizeof(m_stDadosAtenSuspeito));
    memset(&m_vlDadosAtenSuspeito,-1, sizeof(m_vlDadosAtenSuspeito));
    m_stDadosAtenSuspeito.idAtendimento=idAtendimento.ToLong();
    m_vlDadosAtenSuspeito.idAtendimento=1;

    cWFAtendimentoSuspeito AtendimentoSuspeito(&m_stDadosAtenSuspeito, &m_vlDadosAtenSuspeito, &ExcSusp);
    AtendimentoSuspeito.excluir();

    ULOG_END("cWF_Acao::removeAtendimentoSuspeito()");
}

//------------------------------------------------------------------------------------------------------------------
// Exclui Cancelamento Solicitado
//------------------------------------------------------------------------------------------------------------------
void cWF_Acao::removeCancelamentoSolicitado()
{
    ULOG_START("cWF_Acao::removeCancelamentoSolicitado()");

    struct st_CancelamentoSolicitado    calcSol;
    struct st_vlCancelamentoSolicitado vcalcSol;
    XMLGen cancelamento;

    memset ( &calcSol ,  0, sizeof( st_CancelamentoSolicitado   )); 
    memset ( &vcalcSol, -1, sizeof( st_vlCancelamentoSolicitado ));

    calcSol.idAtendimento = idAtendimento.ToLong();

    vcalcSol.idAtendimento = 1;

    cWFCancelamentoSolicitado cwfCancelamentoSolicitado(&calcSol, &vcalcSol, &cancelamento); 
    cwfCancelamentoSolicitado.excluir(); 

    ULOG_END("cWF_Acao::removeCancelamentoSolicitado()");
}

//------------------------------------------------------------------------------------------------------------------    
// Atualiza Atendimento 
//------------------------------------------------------------------------------------------------------------------    
void cWF_Acao::trocarResponsavelMC2(int idPessoaUsuarioMC,int idGrupoMC)
{
    ULOG_START("cWF_Acao::trocarResponsavelMC2()");
    
    proCTrocarResponsavelMC2(idAtendimento.ToLong(),idPessoaUsuarioMC,idGrupoMC);

    ULOG_END("cWF_Acao::trocarResponsavelMC2()");
}

//------------------------------------------------------------------------------------------------------------------    
// Atualiza Atendimento 
//------------------------------------------------------------------------------------------------------------------    
void cWF_Acao::alterarAtendimento()
{
    ULOG_START("cWF_Acao::alterarAtendimento()");

    struct st_Atendimento dados;
    struct st_vlAtendimento status;

    XMLGen xmlObtemAtendimento;

    memset(&dados,0,sizeof(st_Atendimento));
    memset(&status,-1,sizeof(st_vlAtendimento));

    dados.idAtendimento = idAtendimento.ToLong();
    dados.idFase = idFaseAtendimento;
    dados.nrNivel = nrNivelAtendimento;
    dados.qtInsistencia = qtInsistenciaAtendimento;
    dados.idUsuarioAlteracao = User.ToInt();

    SAFE_STRNCPY(dados.dtUltimaAlteracao,sSysDate.c_str());

    if ( idProcedencia > 0 )
    {
        dados.idProcedencia = idProcedencia;
        status.idProcedencia = 1;
    }

    if ( dtJanelaPortout.Length() > 0 )
    {
        SAFE_STRNCPY(dados.dtJanelaPortout,dtJanelaPortout.c_str());
        status.dtJanelaPortout = 1;
    }

    if ( sgOperadoraSolicitante.Length() > 0  )
    {
        SAFE_STRNCPY(dados.sgOperadoraSolicitante,sgOperadoraSolicitante.c_str());
        status.sgOperadoraSolicitante = 1;
    }

    if ( alterarTipoRetornoContato )
    {
        dados.idTipoRetornoContato = idTipoRetornoContato;
        status.idTipoRetornoContato = 1;
    }

    status.idAtendimento = 1;
    status.idFase = 1;
    status.nrNivel = 1;
    status.qtInsistencia = 1;
    status.idUsuarioAlteracao = 1;
    status.dtUltimaAlteracao = 1;

    cWFAtendimento cwfAtendimento(&dados, &status, &xmlObtemAtendimento );
    cwfAtendimento.alterar(&xmlDpr);

    ULOG_END("cWF_Acao::alterarAtendimento()");
}

//------------------------------------------------------------------------------------------------------------------    
// Altera a data de delay de aparição em fila
//------------------------------------------------------------------------------------------------------------------    
void cWF_Acao::atualizarDataDelayFila()
{
    ULOG_START("cWF_Acao::atualizarDataDelayFila()");

    struct st_Atendimento dados;
    struct st_vlAtendimento status;
    XMLGen xmlObtemAtendimento;

    memset( &dados, 0, sizeof(st_Atendimento)); 
    memset( &status,-1,sizeof(st_vlAtendimento));

    dados.idAtendimento = idAtendimento.ToLong();
    dados.idUsuarioAlteracao = User.ToInt();
    strcpy( dados.dtUltimaOperacaoExcFila, sSysDate.c_str() );
    strcpy( dados.dtUltimaAlteracao, sSysDate.c_str() );

    status.idAtendimento = 1;
    status.dtUltimaOperacaoExcFila = 1;
    status.idUsuarioAlteracao = 1;
    status.dtUltimaAlteracao = 1;

    cWFAtendimento cwfAtendimento(&dados, &status, &xmlObtemAtendimento );
    cwfAtendimento.alterar(&xmlDpr);

    ULOG_END("cWF_Acao::atualizarDataDelayFila()");
}

//------------------------------------------------------------------------------------------------------------------
// Aplica a regra de devolver para a fila em processos de portout agrupados
//------------------------------------------------------------------------------------------------------------
bool cWF_Acao::devolverFilaAtendimentosAgrupados()
{
    ULOG_START("cWF_Acao::devolverFilaAtendimentosAgrupados()");

    Collection listaAtendimentosAgrupados;

    obterAtdAgrupadosEmTratPout(idAtendimento.ToLong(),&listaAtendimentosAgrupados);

    TString idAtendimentoSaved = idAtendimento;

    // insere o processo corrente na lista de agrupamentos.
    char *pIdAtendimento = new char[idAtendimento.Length() + 1];

    if ( pIdAtendimento )
    {
        strcpy(pIdAtendimento,(char*)idAtendimento.c_str());
        listaAtendimentosAgrupados.AddItem((void*)pIdAtendimento);
    }

    int i = listaAtendimentosAgrupados.GetCount();

    ULOG("Qtde de atendimentos agrupados=%d",i);

    while (i--)
    {
        ULOG("item i=%d",i+1);

        idAtendimento = (char*)listaAtendimentosAgrupados.GetItem(i);

        ULOG("vai executar DEVOLVER FILA para o atendimento agrupado=%lu",idAtendimento.ToLong());

        getAtendimentoGrupoAtual();

        if ( idGrupoAtual == 0 )
        {
            SetMessage( "\nGrupo atual do processo não encontrado.","M"); 

            xml_g->closeTag();

            ULOG_END("cWF_Acao::devolverFilaAtendimentosAgrupados() ==> GRUPO ATUAL NÃO ENCONTRADO");

            return false;
        }

        getAtendimentoGrupoDevolucao(); // ATENDIMENTOGRUPODEVOLUCAO

        if ( idGrupo.ToInt() == 0 )
        {
            SetMessage( "\nGrupo de devolução não encontrado.","M"); 

            xml_g->closeTag();

            ULOG_END("cWF_Acao::devolverFilaAtendimentosAgrupados() ==> GRUPO DEVOLUÇÃO NÃO ENCONTRADO");

            return false;
        }

        ULOG("Grupo para devolução=%d",idGrupo.ToInt());

        removeAtendimentoGrupoReceptor(); // ATENDIMENTOGRUPODEVOLUCAO
        removeAtendimentoUsuarioAtual();

        inserirAndamento(idGrupoAtual);
    }

    idAtendimento = idAtendimentoSaved;

    ULOG_END("cWF_Acao::devolverFilaAtendimentosAgrupados()");

    return true;
}


//------------------------------------------------------------------------------------------------------------------
// Obtém o grupo de tratamento para retenção de portout
//------------------------------------------------------------------------------------------------------------
bool cWF_Acao::getGrupoTratamentoRetencaoPout()
{
    ULOG_START("cWF_Acao::getGrupoTratamentoRetencaoPout()");

    int idGrupoRetencao;

    bool retorno = obterGrupoTratamentoRetencaoPout(&idGrupoRetencao);

    idGrupo = idGrupoAtual = idGrupoRetencao;

    ULOG("idGrupo=%d",idGrupoRetencao);
    ULOG("retorno=%d",retorno);

    ULOG_END("cWF_Acao::getGrupoTratamentoRetencaoPout()");

    return retorno;
}

//------------------------------------------------------------------------------------------------------------------
// Aplica a regra de iniciar retenção para processos de portout agrupados
//------------------------------------------------------------------------------------------------------------
bool cWF_Acao::iniciarRetencaoAtendimentosAgrupados()
{
    ULOG_START("cWF_Acao::iniciarRetencaoAtendimentosAgrupados()");

    Collection listaAtendimentosAgrupados;

    obterAtdAgrupadosEmTratFila(idAtendimento.ToLong(),&listaAtendimentosAgrupados);

    TString idAtendimentoSaved = idAtendimento;
    UsuarioAtual = User;

    // insere o processo corrente na lista de agrupamentos.
    char *pIdAtendimento = new char[idAtendimento.Length() + 1];

    if ( pIdAtendimento )
    {
        strcpy(pIdAtendimento,(char*)idAtendimento.c_str());
        listaAtendimentosAgrupados.AddItem((void*)pIdAtendimento);
    }

    int i = listaAtendimentosAgrupados.GetCount();

    ULOG("Qtde de atendimentos agrupados=%d",i);

    // aplica a ação iniciar retenção a todos os processos
    while (i--)
    {
        ULOG("item i=%d",i);

        idAtendimento = (char*)listaAtendimentosAgrupados.GetItem(i);

        ULOG("vai executar INICIAR RETENCAO para o atendimento agrupado=%d",idAtendimento.ToLong());

        if ( getAtendimentoUsuarioAtual() )
        { // se já esta com um usuário, diferente do atual, não permite continuar
            if ( User.ToInt() != idUsuarioAtendimento )
            {
                SetMessage( "Um dos processos agrupados já esta sendo tratado por outro analista de retenção.","N");
                ULOG_END("cWF_Acao::iniciarRetencaoAtendimentosAgrupados()");
                return false;
            }
        }

        //ULOG("*** usuario = %d ***",User.ToInt());
        //ULOG("*** UsuarioAtual = %d ***",UsuarioAtual.ToInt());

        inserirAtendimentoUsuarioAtual(false);
        //atualizarAtendimentoUsuarioAtual(User.ToInt(),false);

        idUsuarioAtendimento = User.ToInt();
        inserirAtendimentoUsuarioReceptor();

        atualizarAtendimentoGrupoReceptor(idGrupo.ToInt());

        alterarAtendimentoGrupoAtual(idGrupo.ToInt());

        getAtendimentoGrupoAtualBko(); // BUSCA EM ATENDIMENTO.ATENDIMENTOGRUPOATUAL

        inserirAndamento();

        inserirAndamentoObservacao();
    }

    idAtendimento = idAtendimentoSaved;

    ULOG_END("cWF_Acao::iniciarRetencaoAtendimentosAgrupados()");

    return true;
}

//------------------------------------------------------------------------------------------------------------------    
// Altera a data de janela de portout
//------------------------------------------------------------------------------------------------------------------    
void cWF_Acao::atualizarDataJanelaPortout()
{
    ULOG_START("cWF_Acao::atualizarDataJanelaPortout()");

    struct st_Atendimento dados;
    struct st_vlAtendimento status;
    XMLGen xmlObtemAtendimento;

    memset( &dados, 0, sizeof(st_Atendimento)); 
    memset( &status,-1,sizeof(st_vlAtendimento));

    dados.idAtendimento = idAtendimento.ToLong();
    dados.idUsuarioAlteracao = User.ToInt();
    strcpy( dados.dtJanelaPortout, dtJanelaPortout.c_str() );
    strcpy( dados.dtUltimaAlteracao, sSysDate.c_str() );

    status.idAtendimento = 1;
    status.dtJanelaPortout = 1;
    status.idUsuarioAlteracao = 1;
    status.dtUltimaAlteracao = 1;

    cWFAtendimento cwfAtendimento(&dados, &status, &xmlObtemAtendimento );
    cwfAtendimento.alterar(&xmlDpr);

    ULOG_END("cWF_Acao::atualizarDataJanelaPortout()");
}

//------------------------------------------------------------------------------------------------------------------    
// Obtém o tipo de retorno do atendimento
//------------------------------------------------------------------------------------------------------------------    
int cWF_Acao::obterTipoRetornoProcesso(long idAtendimento)
{
    ULOG_START("cWF_Acao::obterTipoRetornoProcesso()");

    cWFAtendimento cwfAtendimento;
    int tipoRetornoContato;

    cwfAtendimento.ObterTipoRetornoAtendimento(idAtendimento,&tipoRetornoContato );

    ULOG("idTipoRetornoContato=%d",tipoRetornoContato);

    ULOG_END("cWF_Acao::obterTipoRetornoProcesso()");

    return tipoRetornoContato;
}

//------------------------------------------------------------------------------------------------------------------    
// Atualiza Atendimento 
//------------------------------------------------------------------------------------------------------------------    
void cWF_Acao::alterarAtendimentoTipoRetorno()
{
    ULOG_START("cWF_Acao::alterarAtendimentoTipoRetorno()");

    struct st_Atendimento dados;
    struct st_vlAtendimento status;
    XMLGen xmlObtemAtendimento;

    memset( &dados, 0, sizeof(st_Atendimento)); 
    memset( &status,-1,sizeof(st_vlAtendimento));

    dados.idAtendimento = idAtendimento.ToLong();
    dados.idTipoRetornoContato = 1;

    dados.idUsuarioAlteracao = User.ToInt();
    strcpy( dados.dtUltimaAlteracao, sSysDate.c_str() ); 

    status.idAtendimento = 1;
    status.idTipoRetornoContato = 1;
    status.idUsuarioAlteracao = 1;
    status.dtUltimaAlteracao = 1;

    cWFAtendimento cwfAtendimento(&dados, &status, &xmlObtemAtendimento );
    cwfAtendimento.alterar(&xmlDpr);

    ULOG_END("cWF_Acao::alterarAtendimentoTipoRetorno()");
}

//------------------------------------------------------------------------------------------------------------------    
// Atualiza Andamento Atual 
//------------------------------------------------------------------------------------------------------------------    
void cWF_Acao::alterarAndamentoAtual()
{
    ULOG_START("cWF_Acao::alterarAndamentoAtual()");

    //////////////////////////////////////////////////////////////////////////////////
    // Esta operação ficou desnecessária pois a rotina de inclusão de andamento
    // executa esta operação a cada nova inserção. Out/2005 - Cassio
    //////////////////////////////////////////////////////////////////////////////////
    //
    // struct st_AtendimentoAndamentoAtual dados;
    // struct st_vlAtendimentoAndamentoAtual status;
    // XMLGen xmlGen;

    // memset ( &dados, 0, sizeof( st_AtendimentoAndamentoAtual  )); 
    // memset( &status,-1,sizeof(st_vlAtendimentoAndamentoAtual));

    // dados.idAtendimento = idAtendimento.ToInt();
    // dados.idAndamento = idAndamento;
    // dados.idUsuarioAlteracao = User.ToInt();
    // strcpy( dados.dtUltimaAlteracao, sSysDate.c_str() ); 

    // status.idAtendimento = 1;
    // status.idAndamento = 1;
    // status.idUsuarioAlteracao = 1;
    // status.dtUltimaAlteracao = 1;

    // cWFAtendimentoAndamentoAtual cwfAtendimentoAndamentoAtual(&dados, &status, &xmlGen );
    // cwfAtendimentoAndamentoAtual.alterar();

    ULOG_END("cWF_Acao::alterarAndamentoAtual()");
}

//------------------------------------------------------------------------------------------------------------
// Altera o Atendimento Transferencia 
//------------------------------------------------------------------------------------------------------------

void cWF_Acao::alterarAndamentoTransferencia()
{
    ULOG_START("cWF_Acao::alterarAndamentoTransferencia()");

    struct st_AndamentoTransferencia dados; 
    struct st_vlAndamentoTransferencia status; 
    XMLGen xmlGen;

    memset ( &dados , 0, sizeof( st_AndamentoTransferencia  )); 
    memset ( &status,-1, sizeof( st_vlAndamentoTransferencia));

    dados.idAndamento               = idAndamentoTrans;
    strcpy ( dados.dtFinTransferencia, sSysDate.c_str() ); 
    dados.idUsuarioAlteracao        = User.ToInt();
    strcpy( dados.dtUltimaAlteracao, sSysDate.c_str() ); 

    status.idAndamento = 1;
    status.dtFinTransferencia = 1;
    status.idUsuarioAlteracao = 1;
    status.dtUltimaAlteracao = 1;

    cWFAndamentoTransferencia cwfAndamentoTransferencia(&dados,&status, &xmlGen);

    cwfAndamentoTransferencia.alterar();

    ULOG_END("cWF_Acao::alterarAndamentoTransferencia()");
}

//------------------------------------------------------------------------------------------------------------------
// Remove processo de TRATAMENTO.ATENDIMENTOCRI
//------------------------------------------------------------------------------------------------------------------
void cWF_Acao::removeAtendimentoCri()
{
    ULOG_START("cWF_Acao::removeAtendimentoCri()");

    cWFAtdTratamentoCri AtdCri;

    AtdCri.excluirAtendimentoCri(idAtendimento.ToLong());

    ULOG_END("cWF_Acao::removeAtendimentoCri()");
}

//------------------------------------------------------------------------------------------------------------------
// Alterar Cri Associado para o processo
//------------------------------------------------------------------------------------------------------------

void cWF_Acao::alterarAtendimentoCri(bool atualizaDataAberturaSimNao)
{
    ULOG_START("cWF_Acao::alterarAtendimentoCri()");

    struct st_AtdTratamentoCri dados;
    struct st_vlAtdTratamentoCri status;
    XMLGen xmlGen;

    memset ( &dados,   0, sizeof(st_AtdTratamentoCri)); 
    memset ( &status, -1, sizeof(st_vlAtdTratamentoCri)); 

    if ( atualizaDataAberturaSimNao == true )
    {
        strcpy( dados.dtAbertura, sSysDate.c_str() );
        status.dtAbertura = 1;
    }

    dados.idAtendimento      = idAtendimento.ToLong(); 
    dados.idPessoaUsuario    = UsuarioAtual.ToInt();
    dados.idUsuarioAlteracao = User.ToInt(); 
    strcpy( dados.dtUltimaAlteracao, sSysDate.c_str() ); 

    if ( qtEnvioBko )
    {
        dados.qtEnvioBko = qtEnvioBko;
        status.qtEnvioBko = 1;
    }

    status.idAtendimento = 1;
    status.idPessoaUsuario = 1;
    status.idUsuarioAlteracao = 1;
    status.dtUltimaAlteracao = 1;

    cWFAtdTratamentoCri usrCri(&dados, &status, &xmlGen);

    usrCri.alterarAtendimentoCri();

    ULOG_END("cWF_Acao::alterarAtendimentoCri()");
}

//------------------------------------------------------------------------------------------------------------------
// Atualizar Data de calculo de prazo CRI
//------------------------------------------------------------------------------------------------------------
void cWF_Acao::atualizarDataPrazoCRI()
{
    ULOG_START("cWF_Acao::atualizaDataPrazoCRI()");

    cWFAtdTratamentoCri usrCri;

    usrCri.atualizarPrazoCRI(idAtendimento.ToLong());

    ULOG_END("cWF_Acao::atualizaDataPrazoCRI()");
}

//------------------------------------------------------------------------------------------------------------------
// Alterar Cri Em Tratamento Associado para o processo
//------------------------------------------------------------------------------------------------------------

void cWF_Acao::alterarTratamentoCri()
{
    ULOG_START("cWF_Acao::alterarTratamentoCri()");

    struct st_AtdTratamentoCri dados;
    struct st_vlAtdTratamentoCri status;
    XMLGen xmlGen;

    memset ( &dados,   0, sizeof(st_AtdTratamentoCri)); 
    memset ( &status, -1, sizeof(st_vlAtdTratamentoCri)); 

    dados.idAtendimento      = idAtendimento.ToLong(); 
    dados.idPessoaUsuario    = UsuarioAtual.ToInt();
    dados.idUsuarioAlteracao = User.ToInt(); 
    strcpy( dados.dtUltimaAlteracao, sSysDate.c_str() ); 

    status.idAtendimento = 1;
    status.idPessoaUsuario = 1;
    status.idUsuarioAlteracao = 1;
    status.dtUltimaAlteracao = 1;

    cWFAtdTratamentoCri usrCri(&dados, &status, &xmlGen);

    usrCri.alterarTratamentoCri();

    ULOG_END("cWF_Acao::alterarTratamentoCri()");
}

//------------------------------------------------------------------------------------------------------------------
// Alterar Usuario Devolucao para o processo caso que seja o Cri
//------------------------------------------------------------------------------------------------------------

void cWF_Acao::alterarUsuarioDevolucaoCri()
{
    ULOG_START("cWF_Acao::alterarUsuarioDevolucaoCri()");

    struct st_AtdTratamentoCri dados;
    struct st_vlAtdTratamentoCri status;
    XMLGen xmlGen;

    memset ( &dados,   0, sizeof(st_AtdTratamentoCri)); 
    memset ( &status, -1, sizeof(st_vlAtdTratamentoCri)); 

    dados.idAtendimento      = idAtendimento.ToLong(); 
    dados.idPessoaUsuario    = UsuarioAtual.ToInt();
    dados.idPessoaUsuarioAtual    = idUsuarioCri;
    dados.idUsuarioAlteracao = User.ToInt(); 
    strcpy( dados.dtUltimaAlteracao, sSysDate.c_str() ); 

    status.idAtendimento = 1;
    status.idPessoaUsuario = 1;
    status.idPessoaUsuarioAtual = 1;
    status.idUsuarioAlteracao = 1;
    status.dtUltimaAlteracao = 1;

    cWFAtdTratamentoCri usrCri(&dados, &status, &xmlGen);

    usrCri.alterarUsuarioDevolucaoCri(); // ATENDIMENTO.ATENDIMENTOUSUARIODEVOLUCAO

    ULOG_END("cWF_Acao::alterarUsuarioDevolucaoCri()");
}

//------------------------------------------------------------------------------------------------------------------
// Altera ATENDIMENTO.ATENDIMENTOGRUPOBKO
//------------------------------------------------------------------------------------------------------------
void cWF_Acao::alterarAtendimentoGrupoBko()
{
    ULOG_START("cWF_Acao::alterarAtendimentoGrupoBko()");

    struct st_AtendimentoGrupoAtual dados;
    struct st_vlAtendimentoGrupoAtual status;
    XMLGen xmlGen;

    memset ( &dados,   0, sizeof(st_AtendimentoGrupoAtual)); 
    memset ( &status, -1, sizeof(st_vlAtendimentoGrupoAtual)); 

    dados.idAtendimento = idAtendimento.ToLong(); 
    dados.qtIntercambios = qtIntercambios; 
    //(sem uso) dados.idUsuarioAlteracao = User.ToInt(); 
    strcpy( dados.dtSaida, sSysDate.c_str());
    //(sem uso) strcpy( dados.dtUltimaAlteracao, sSysDate.c_str() ); 

    status.idAtendimento = 1;
    if (qtIntercambios == 0)
        status.dtSaida = 1;
    else
        status.qtIntercambios = 1;

    //(sem uso) status.idUsuarioAlteracao = 1;
    //(sem uso) status.dtUltimaAlteracao = 1;

    cWFAtendimentoGrupoAtual cwfatendimentogrupoatual(&dados, &status, &xmlGen);

    cwfatendimentogrupoatual.alterarBko(); // ATENDIMENTO.ATENDIMENTOGRUPOBKOG00

    ULOG_END("cWF_Acao::alterarAtendimentoGrupoBko()");
}

//------------------------------------------------------------------------------------------------------------------
// Alterar TRATAMENTOGRUPOCRI para o processo
//------------------------------------------------------------------------------------------------------------

void cWF_Acao::alterarTratamentoGrupoCri()
{
    ULOG_START("cWF_Acao::alterarTratamentoGrupoCri()");

    struct st_AtdTratamentoCri dados;
    struct st_vlAtdTratamentoCri status;
    XMLGen xmlGen;

    memset ( &dados,   0, sizeof(st_AtdTratamentoCri)); 
    memset ( &status, -1, sizeof(st_vlAtdTratamentoCri)); 

    dados.idAtendimento      = idAtendimento.ToLong(); 
    dados.idGrupo            = idGrupo.ToInt(); 
    dados.idUsuarioAlteracao = User.ToInt(); 
    strcpy( dados.dtUltimaAlteracao, sSysDate.c_str() ); 

    status.idAtendimento = 1;
    status.idGrupo = 1;
    status.idUsuarioAlteracao = 1;
    status.dtUltimaAlteracao = 1;

    cWFAtdTratamentoCri grpAtual(&dados, &status, &xmlGen);

    grpAtual.alterarTratamentoGrupoCri(); // ATENDIMENTO.TRATAMENTOGRUPOCRI

    ULOG_END("cWF_Acao::alterarTratamentoGrupoCri()");
}


//------------------------------------------------------------------------------------------------------------------
// Inserir TRATAMENTOGRUPOCRI para o processo
//------------------------------------------------------------------------------------------------------------

void cWF_Acao::inserirTratamentoGrupoCri()
{
    ULOG_START("cWF_Acao::inserirTratamentoGrupoCri()");

    ULOG("*** WARNING *** USE inserirTratamentoCri()");

    // ===========================================================================
    // As tabelas TRATAMENTOCRI e TRATAMENTO GRUPOCRI estão sincronizadas
    // Use inserirTratamentoCri() para inserir dados nestas tabelas
    //
    //
    // struct st_AtdTratamentoCri dados;
    // struct st_vlAtdTratamentoCri status;
    // XMLGen xmlGen;
    // 
    // memset ( &dados,   0, sizeof(st_AtdTratamentoCri));
    // memset ( &status, -1, sizeof(st_vlAtdTratamentoCri));
    // 
    // dados.idAtendimento      = idAtendimento.ToLong();
    // dados.idGrupo            = idGrupo.ToInt();
    // dados.idUsuarioAlteracao = User.ToInt();
    // strcpy( dados.dtUltimaAlteracao, sSysDate.c_str() );
    // 
    // status.idAtendimento = 1;
    // status.idGrupo = 1;
    // status.idUsuarioAlteracao = 1;
    // status.dtUltimaAlteracao = 1;
    // 
    // cWFAtdTratamentoCri grpAtual(&dados, &status, &xmlGen);
    // 
    // grpAtual.incluirTratamentoGrupoCri(); // ATENDIMENTO.TRATAMENTOGRUPOCRI
    // ===========================================================================

    ULOG_END("cWF_Acao::inserirTratamentoGrupoCri()");
}

//------------------------------------------------------------------------------------------------------------------
// Alterar Grupo Cri Associado para o processo
//------------------------------------------------------------------------------------------------------------

void cWF_Acao::alterarGrupoCri()
{
    ULOG_START("cWF_Acao::alterarGrupoCri()");

    struct st_AtdTratamentoCri dados;
    struct st_vlAtdTratamentoCri status;
    XMLGen xmlGen;

    memset ( &dados,   0, sizeof(st_AtdTratamentoCri)); 
    memset ( &status, -1, sizeof(st_vlAtdTratamentoCri)); 

    dados.idAtendimento      = idAtendimento.ToLong(); 
    dados.idGrupo            = idGrupo.ToInt(); 
    dados.idUsuarioAlteracao = User.ToInt(); 
    strcpy( dados.dtUltimaAlteracao, sSysDate.c_str() ); 

    status.idAtendimento = 1;
    status.idGrupo = 1;
    status.idUsuarioAlteracao = 1;
    status.dtUltimaAlteracao = 1;

    cWFAtdTratamentoCri grpAtual(&dados, &status, &xmlGen);

    grpAtual.alterarGrupoCri(); // ATENDIMENTO.GRUPOCRI

    ULOG_END("cWF_Acao::alterarGrupoCri()");
}

//------------------------------------------------------------------------------------------------------------------
// Alterar Grupo Devolucao Associado para o processo caso que o grupo seja o grupo Cri
//------------------------------------------------------------------------------------------------------------

void cWF_Acao::alterarGrupoDevolucaoCri()
{
    ULOG_START("cWF_Acao::alterarGrupoDevolucaoCri()");

    struct st_AtdTratamentoCri dados;
    struct st_vlAtdTratamentoCri status;
    XMLGen xmlGen;

    memset ( &dados,   0, sizeof(st_AtdTratamentoCri)); 
    memset ( &status, -1, sizeof(st_vlAtdTratamentoCri)); 

    dados.idAtendimento      = idAtendimento.ToLong(); 
    dados.idGrupo            = idGrupo.ToInt(); 
    dados.idGrupoAtual       = idGrupoAtual; 
    dados.idUsuarioAlteracao = User.ToInt(); 
    strcpy( dados.dtUltimaAlteracao, sSysDate.c_str() ); 

    status.idAtendimento = 1;
    status.idGrupo = 1;
    status.idGrupoAtual = 1;
    status.idUsuarioAlteracao = 1;
    status.dtUltimaAlteracao = 1;

    cWFAtdTratamentoCri grpAtual(&dados, &status, &xmlGen);

    grpAtual.alterarGrupoDevolucaoCri(); // ATENDIMENTO.ATENDIMENTOGRUPODEVOLUCAO

    ULOG_END("cWF_Acao::alterarGrupoDevolucaoCri()");
}
//------------------------------------------------------------------------------------------------------------

//------------------------------------------------------------------------------------------------------------------
// Atualizar Cancelamento Solicitado
//------------------------------------------------------------------------------------------------------------------
void cWF_Acao::alterarCancelamentoSolicitado()
{
    ULOG_START("cWF_Acao::alterarCancelamentoSolicitado()");

    struct st_CancelamentoSolicitado    calcSol;
    struct st_vlCancelamentoSolicitado vcalcSol;
    XMLGen cancelamento;

    memset ( &calcSol ,  0, sizeof( st_CancelamentoSolicitado   )); 
    memset ( &vcalcSol, -1, sizeof( st_vlCancelamentoSolicitado ));

    calcSol.idAtendimento                   = idAtendimento.ToLong();
    calcSol.idPessoaUsuario                 = User.ToInt(); 
    calcSol.idUsuarioAlteracao              = User.ToInt(); 
    strcpy ( calcSol.dtSolicitacaoCancelamento, sSysDate.c_str() ); 
    strcpy ( calcSol.dtUltimaAlteracao,         sSysDate.c_str() ); 

    vcalcSol.idAtendimento = 1;
    vcalcSol.idPessoaUsuario = 1;
    vcalcSol.idUsuarioAlteracao = 1;
    vcalcSol.dtSolicitacaoCancelamento = 1;
    vcalcSol.dtUltimaAlteracao = 1;

    cWFCancelamentoSolicitado cwfCancelamentoSolicitado(&calcSol, &vcalcSol, &cancelamento); 
    cwfCancelamentoSolicitado.alterar(); 

    ULOG_END("cWF_Acao::alterarCancelamentoSolicitado()");
}

//------------------------------------------------------------------------------------------------------------------
// Atualizar Priorizacao Atendimento
//------------------------------------------------------------------------------------------------------------------
void cWF_Acao::alterarAtendimentoPriorizacao()
{
    ULOG_START("cWF_Acao::alterarAtendimentoPriorizacao()");

    struct st_AtendimentoPriorizacao    m_stDados;
    struct st_vlAtendimentoPriorizacao  m_vlDados;
    XMLGen priorizacao;

    memset ( &m_stDados ,  0, sizeof( st_AtendimentoPriorizacao   )); 
    memset ( &m_vlDados, -1, sizeof( st_vlAtendimentoPriorizacao ));

    m_stDados.idAtendimento = idAtendimento.ToLong();
    m_stDados.idAlerta = idAlerta;
    sprintf(m_stDados.nrCriticidade, "%.10f",nrCriticidade); 
    strcpy ( m_stDados.nmCor,nmCor ); 

    m_vlDados.idAtendimento = 1;
    m_vlDados.nrCriticidade = 1;
    m_vlDados.idAlerta = 1;
    m_vlDados.nmCor = 1;

    cWFAtendimentoPriorizacao AtendimentoPriorizacao(&m_stDados, &m_vlDados, &priorizacao); 
    AtendimentoPriorizacao.alterar();

    ULOG_END("cWF_Acao::alterarAtendimentoPriorizacao()");
}

//------------------------------------------------------------------------------------------------------------------
// Alterar Atendimento Baixa Atual
//------------------------------------------------------------------------------------------------------------------
void cWF_Acao::alterarAtendimentoBaixaAtual() 
{
    ULOG_START("cWF_Acao::alterarAtendimentoBaixaAtual()");

    st_AtendimentoBaixaAtual m_stDadosBaixaAtual;
    st_vlAtendimentoBaixaAtual m_vlDadosBaixaAtual;
    XMLGen baixa;

    memset(&m_stDadosBaixaAtual, 0, sizeof(m_stDadosBaixaAtual));
    memset(&m_vlDadosBaixaAtual,-1, sizeof(m_vlDadosBaixaAtual));
    m_stDadosBaixaAtual.idAtendimento=idAtendimento.ToLong();
    m_vlDadosBaixaAtual.idAtendimento=1;
    strcpy(m_stDadosBaixaAtual.dtUltimaAlteracao,  sSysDate.c_str());
    m_vlDadosBaixaAtual.dtUltimaAlteracao=1;
    m_stDadosBaixaAtual.idAtendimentoBaixaHistorico=idAtendimentoBaixaHistorico;
    m_vlDadosBaixaAtual.idAtendimentoBaixaHistorico=1;
    m_stDadosBaixaAtual.idUsuarioAlteracao=User.ToInt();
    m_vlDadosBaixaAtual.idUsuarioAlteracao=1;

    cWFAtendimentoBaixaAtual AtendimentoBaixaAtual(&m_stDadosBaixaAtual, &m_vlDadosBaixaAtual, &baixa);

    AtendimentoBaixaAtual.alterar();

    ULOG_END("cWF_Acao::alterarAtendimentoBaixaAtual()");
}

//------------------------------------------------------------------------------------------------------------------    
// Atualiza Atendimento Nivel
//------------------------------------------------------------------------------------------------------------------    
void cWF_Acao::concluirAtendimentoNivel(int idFaseNivel)
{
    ULOG_START("cWF_Acao::concluirAtendimentoNivel()");

    struct st_AtendimentoNivel dados;
    struct st_vlAtendimentoNivel status;
    XMLGen xmlObtemNivelAtendimento;

    memset( &dados, 0, sizeof(st_AtendimentoNivel)); 
    memset( &status,-1,sizeof(st_vlAtendimentoNivel));

    dados.idAtendimento = idAtendimento.ToLong();
    dados.idFase = idFaseNivel;
    dados.idUsuarioAlteracao = User.ToInt();
    strcpy( dados.dtUltimaAlteracao, sSysDate.c_str() ); 

    status.idAtendimento = 1;
    status.idFase = 1;
    status.idUsuarioAlteracao = 1;
    status.dtUltimaAlteracao = 1;

    cWFAtendimentoNivel cwfAtendimentoNivel(&dados, &status, &xmlObtemNivelAtendimento );
    cwfAtendimentoNivel.concluir();

    ULOG_END("cWF_Acao::concluirAtendimentoNivel()");
}

//------------------------------------------------------------------------------------------------------------------
// Inclusao do Atendimento Nivel (1)
//------------------------------------------------------------------------------------------------------------------    
void cWF_Acao::inserirAtendimentoNivel(int idGrupo,bool concluir)
{
    ULOG_START("cWF_Acao::inserirAtendimentoNivel()");

    st_AtendimentoNivel dados;
    st_vlAtendimentoNivel status;
    XMLGen xmlObtemNivel;

    memset ( &dados,   0, sizeof(st_AtendimentoNivel)); 
    memset ( &status, -1, sizeof(st_vlAtendimentoNivel)); 
    
    dados.idAtendimento = idAtendimento.ToLong(); 
    dados.idGrupo       = idGrupo; 
    dados.idFase        = idFase.ToInt(); 
    dados.idAtividade   = idAtividade.ToInt();
    dados.nrNivel       = nrNivelAtendimento;
    dados.inConcluido   = concluir==true?1:0;
    strcpy( dados.dtNivel, this->sSysDate.c_str());
    dados.idUsuarioAlteracao = User.ToInt();
    strcpy( dados.dtUltimaAlteracao, this->sSysDate.c_str());

    status.idAtendimento = 1;
    status.idGrupo = 1;
    status.idFase = 1;
    status.idAtividade = 1;
    status.nrNivel = 1;
    status.dtNivel = 1;
    status.inConcluido = 1;
    status.idUsuarioAlteracao = 1;
    status.dtUltimaAlteracao = 1;
    
    cWFAtendimentoNivel AtendimentoNivel(&dados, &status, &xmlObtemNivel);

    AtendimentoNivel.incluir(); 

    ULOG_END("cWF_Acao::inserirAtendimentoNivel()");
}

//------------------------------------------------------------------------------------------------------------------    
// Inclusao do Atendimento Nivel (2)
//------------------------------------------------------------------------------------------------------------------    
void cWF_Acao::inserirAtendimentoNivel(bool concluir)
{
    ULOG_START("cWF_Acao::inserirAtendimentoNivel() (1)");

    inserirAtendimentoNivel(idGrupoAtual,concluir);

    ULOG_END("cWF_Acao::inserirAtendimentoNivel() (1)");
}

//------------------------------------------------------------------------------------------------------------------    
// Inclusao do Andamento 
//------------------------------------------------------------------------------------------------------------------    
void cWF_Acao::inserirAndamento(int idGrupo)
{
    ULOG_START("cWF_Acao::inserirAndamento(int idGrupo)");

    struct st_Andamento dados;
    struct st_vlAndamento status;
    XMLGen xmlObtemAndamento;

    memset (&dados, 0, sizeof( st_Andamento ));
    memset (&status,-1,sizeof(st_vlAndamento));

    strcpy( dados.dtAndamento, sSysDate.c_str() );
    status.dtAndamento = 1;

    strcpy( dados.dtUltimaAlteracao, sSysDate.c_str() );
    status.dtUltimaAlteracao = 1;

    dados.idAtendimento = idAtendimento.ToLong();
    status.idAtendimento = 1;

    dados.idPessoaUsuario = User.ToInt();
    status.idPessoaUsuario = 1;

    dados.idUsuarioAlteracao = User.ToInt();
    status.idUsuarioAlteracao = 1;

    dados.idAgrupamentoEstadoTpProc = idAgrEstTPrFt.ToInt();
    status.idAgrupamentoEstadoTpProc = 1;

    dados.idAtividade = idAtividade.ToInt();
    status.idAtividade = 1;

    if ( inRC )
    {
        dados.idGrupo = 0 == idGrupoCprevio ? idGrupo : idGrupoCprevio;
    }
    else
    {
        dados.idGrupo = idGrupo;
    }
    status.idGrupo = 1;

    ULOG("idGrupo=[%d]",dados.idGrupo );
    ULOG("idGrupoCprevio=[%d]",idGrupoCprevio);
    ULOG("idAgrupamentoEstadoTpProc=[%d]",dados.idAgrupamentoEstadoTpProc);
    ULOG("inRC=[%d]",inRC);

    cWFAndamento cwfAndamento( &dados, &status, &xmlObtemAndamento );  
    
    idAndamento = cwfAndamento.incluir(&xmlDpr);

    ULOG("idAndamento: %ld", idAndamento);

    ULOG_END("cWF_Acao::inserirAndamento(int idGrupo)");
}

//------------------------------------------------------------------------------------------------------------------    
// Inclusao do Andamento 
//------------------------------------------------------------------------------------------------------------------    
void cWF_Acao::inserirAndamento()
{
    ULOG("> cWF_Acao::inserirAndamento()");

    ULOG("idGrupoAtual=%d",idGrupoAtual);
    inserirAndamento(idGrupoAtual);

    ULOG_END("cWF_Acao::inserirAndamento()");
}

//------------------------------------------------------------------------------------------------------------------    
// Inclusao do Andamento Observacao
//------------------------------------------------------------------------------------------------------------------    
void cWF_Acao::inserirAndamentoObservacao()
{
    ULOG_START("cWF_Acao::inserirAndamentoObservacao()");

    struct st_AndamentoObservacao dados;
    struct st_vlAndamentoObservacao status;

    memset ( &dados , 0, sizeof( st_AndamentoObservacao  ));
    memset ( &status,-1, sizeof( st_vlAndamentoObservacao));

    dados.pdsAndamentoObservacao = dsObservacao.c_str();

    if ( *dados.pdsAndamentoObservacao )
    {
        dados.idAndamento = idAndamento;
        dados.idUsuarioAlteracao = User.ToInt();
        strcpy(dados.dtUltimaAlteracao, sSysDate.c_str()); 
        
        //sprintf(dados.dsAndamentoObservacao,"%.*s",sizeof(dados.dsAndamentoObservacao)-1,dsObservacao.c_str());

        status.idAndamento = 1;
        status.idUsuarioAlteracao = 1;
        status.dtUltimaAlteracao = 1;
        status.dsAndamentoObservacao = 1;

        cWFAndamentoObservacao cwfAndamentoObservacao(&dados, &status,0);
        cwfAndamentoObservacao.incluir(&xmlDpr);
    }

    ULOG_END("cWF_Acao::inserirAndamentoObservacao()");
}

//------------------------------------------------------------------------------------------------------------------    
// Inclusao do Andamento Motivo
//------------------------------------------------------------------------------------------------------------------    
void cWF_Acao::inserirAndamentoMotivo()
{
    ULOG_START("cWF_Acao::inserirAndamentoMotivo()");

    struct st_AndamentoMotivo   dados;
    struct st_vlAndamentoMotivo status; 
    XMLGen xmlGen;

    memset( &dados , 0, sizeof( st_AndamentoMotivo  )); 
    memset( &status,-1, sizeof( st_vlAndamentoMotivo));

    dados.idAndamento = idAndamento;
    dados.idMotivo = idMotivo.ToInt();
    dados.idFase = idFase.ToInt();
    dados.idUsuarioAlteracao = User.ToInt();
    strcpy( dados.dtUltimaAlteracao, sSysDate.c_str() ); 

    status.idAndamento = 1;
    status.idMotivo = 1;
    status.idFase = 1;
    status.idUsuarioAlteracao = 1;
    status.dtUltimaAlteracao = 1;

    cWFAndamentoMotivo cwfAndamentoMotivo(&dados,&status,&xmlGen);
    cwfAndamentoMotivo.incluir();

    ULOG_END("cWF_Acao::inserirAndamentoMotivo()");
}

//------------------------------------------------------------------------------------------------------------------
// Inclusao do Andamento Mensagem de Contato Prévio
//------------------------------------------------------------------------------------------------------------------
void cWF_Acao::inserirAndamentoMensagemRC(int idPessoaUsuarioOrigem)
{
    ULOG_START("cWF_Acao::inserirAndamentoMensagemRC()");

    struct st_AtdMsgRC   dados;
    struct st_vlAtdMsgRC status; 

    memset( &dados , 0, sizeof( st_AtdMsgRC  )); 
    memset( &status,-1, sizeof( st_vlAtdMsgRC));

    dados.idAndamento = idAndamento;
    strcpy( dados.dtMensagem, sSysDate.c_str() ); 
    dados.idPessoaUsuario = User.ToInt();
    dados.idPessoaUsuarioBko = idPessoaUsuarioOrigem;
    dados.idUsuarioAlteracao = User.ToInt();
    dados.inOrigem = INORIGEM_ANDAMENTOMENSAGEM_RC;
    strcpy( dados.dtUltimaAlteracao, sSysDate.c_str() ); 

    status.idAndamento = 1;
    status.dtMensagem = 1;
    status.idPessoaUsuario = 1;
    status.idPessoaUsuarioBko = 1;
    status.idUsuarioAlteracao = 1;
    status.inOrigem = 1;
    status.dtUltimaAlteracao = 1;

    cWFAtdMsgRC cwfatdmsgrc(&dados,&status);

    cwfatdmsgrc.incluir();

    ULOG_END("cWF_Acao::inserirAndamentoMensagemRC()");
}

//------------------------------------------------------------------------------------------------------------------
// Inclusao do Andamento Mensagem com ID´s parametrizados
//------------------------------------------------------------------------------------------------------------------
void cWF_Acao::inserirAndamentoMensagem(int idPessoaUsuarioDestino,int idPessoaUsuarioOrigem,int inOrigem)
{
    ULOG_START("cWF_Acao::inserirAndamentoMensagem()");

    struct st_AtdMsgCri   dados;
    struct st_vlAtdMsgCri status; 

    memset( &dados , 0, sizeof( st_AtdMsgCri  )); 
    memset( &status,-1, sizeof( st_vlAtdMsgCri));

    dados.idAndamento = idAndamento;
    strcpy( dados.dtMensagem, sSysDate.c_str() ); 
    dados.idPessoaUsuario = idPessoaUsuarioOrigem;
    dados.idPessoaUsuarioBko = idPessoaUsuarioDestino;
    dados.idUsuarioAlteracao = User.ToInt();
    dados.inOrigem = inOrigem;
    strcpy( dados.dtUltimaAlteracao, sSysDate.c_str() ); 

    status.idAndamento = 1;
    status.dtMensagem = 1;
    status.idPessoaUsuario = 1;
    status.idPessoaUsuarioBko = 1;
    status.idUsuarioAlteracao = 1;
    status.dtUltimaAlteracao = 1;
    status.inOrigem = 1;

    cWFAtdMsgCri cwfatdmsgcri(&dados,&status);

    cwfatdmsgcri.incluir();

    ULOG_END("cWF_Acao::inserirAndamentoMensagem()");
}

//------------------------------------------------------------------------------------------------------------------
// Inclusao do Andamento Mensagem
//------------------------------------------------------------------------------------------------------------------
void cWF_Acao::inserirAndamentoMensagem()
{
    ULOG_START("cWF_Acao::inserirAndamentoMensagem()");

    inserirAndamentoMensagem(idUsuarioCri,User.ToInt());

    ULOG_END("cWF_Acao::inserirAndamentoMensagem()");
}

//------------------------------------------------------------------------------------------------------------
// Inclui o Atendimento Transferencia 
//------------------------------------------------------------------------------------------------------------

void cWF_Acao::inserirAndamentoTransferencia()
{
    ULOG_START("cWF_Acao::inserirAndamentoTransferencia()");

    struct st_AndamentoTransferencia dados; 
    struct st_vlAndamentoTransferencia status; 
    XMLGen xmlGen;

    memset ( &dados , 0, sizeof( st_AndamentoTransferencia  )); 
    memset ( &status,-1, sizeof( st_vlAndamentoTransferencia));

    dados.idAtendimento             = idAtendimento.ToLong();
    dados.idAndamento               = idAndamento;
    dados.idPessoaUsuario           = UsuarioAtual.ToInt();
    dados.idMotivoEncaminhamento    = idMotivo.ToInt();
    dados.idUsuarioAlteracao        = User.ToInt();

    strcpy( dados.dtInicioTransferencia, dtInicioTransferencia ); 
    strcpy( dados.dtUltimaAlteracao, sSysDate.c_str() ); 

    status.idAtendimento = 1;
    status.idAndamento = 1;
    status.idPessoaUsuario = 1;
    status.idMotivoEncaminhamento = 1;
    status.dtInicioTransferencia = 1;
    status.idUsuarioAlteracao = 1;
    status.dtUltimaAlteracao = 1;

    cWFAndamentoTransferencia cwfAndamentoTransferencia(&dados,&status, &xmlGen);
    cwfAndamentoTransferencia.incluir();

    ULOG_END("cWF_Acao::inserirAndamentoTransferencia()");
}

//------------------------------------------------------------------------------------------------------------------
// Insere Grupo Bko para o processo
//------------------------------------------------------------------------------------------------------------

void cWF_Acao::inserirAtendimentoGrupoBko()
{
    ULOG_START("cWF_Acao::inserirAtendimentoGrupoBko()");

    struct st_AtendimentoGrupoAtual dados;
    struct st_vlAtendimentoGrupoAtual status;
    XMLGen xmlGen;

    memset ( &dados,   0, sizeof(st_AtendimentoGrupoAtual)); 
    memset ( &status, -1, sizeof(st_vlAtendimentoGrupoAtual)); 

    dados.idAtendimento      = idAtendimento.ToLong(); 
    dados.idGrupo            = idGrupo.ToInt(); 
    dados.inAssociado        = inAssociado; 
    dados.qtIntercambios     = 1; 
    dados.idUsuarioAlteracao = User.ToInt(); 
    strcpy( dados.dtEntradaFila, dtInicioTransferencia);
    strcpy( dados.dtSaida, dtInicioTransferencia);
    strcpy( dados.dtUltimaAlteracao, sSysDate.c_str() ); 

    status.idAtendimento = 1;
    status.idGrupo = 1;
    status.inAssociado = 1;
    status.dtEntradaFila = 1;
    if (qtIntercambios == 0)
    {
        status.dtSaida = 1;
    }
    status.qtIntercambios = 1;
    status.idUsuarioAlteracao = 1;
    status.dtUltimaAlteracao = 1;

    cWFAtendimentoGrupoAtual cwfatendimentogrupoatual(&dados, &status, &xmlGen);

    cwfatendimentogrupoatual.incluirBko(); // ATENDIMENTO.ATENDIMENTOGRUPOBKOG00

    if ( 1 == inAssociado )
    {
        gravaAtendimentoEncIncorreto();
    }

    ULOG_END("cWF_Acao::inserirAtendimentoGrupoBko()");
}

//------------------------------------------------------------------------------------------------------------------
// Registra o encaminhamento incorreto
//------------------------------------------------------------------------------------------------------------
void cWF_Acao::gravaAtendimentoEncIncorreto() 
{
    ULOG_START( "cWF_Acao::gravaAtendimentoEncIncorreto()" );

    if ( 1 != inAssociado )
    {
        ULOG("Saindo sem registrar enc.incorreto.");
        ULOG_END( "cWF_Acao::gravaAtendimentoEncIncorreto()" );
        return;
    }

    st_AtdEncaminhadoIncorreto dadosLoc;
    st_vlAtdEncaminhadoIncorreto statusLoc;

    memset(&dadosLoc,0,sizeof(dadosLoc));
    memset(&statusLoc,-1,sizeof(statusLoc));

    dadosLoc.idCanal = idCanal;
    dadosLoc.idContato = idContato;
    dadosLoc.idGrupo = idGrupoAtual;
    dadosLoc.idGrupoAbertura = idGrupoAbertura;
    dadosLoc.idProcedencia = idProcedencia;
    dadosLoc.idSegmentacao = idSegmentacao;
    dadosLoc.idTipoCarteira = idTipoCarteira;
    dadosLoc.idTipoLinha = idTipoLinha;
    dadosLoc.idTipoPessoa = idTipoPessoa;
    dadosLoc.idTipoRelacionamento = idTipoRelacionamento;
    dadosLoc.idUsuarioAlteracao = User.ToInt();
    dadosLoc.dtAbertura = dtAbertura;

    statusLoc.idCanal = 1;
    statusLoc.idContato = 1;
    statusLoc.idGrupo = 1;
    statusLoc.idGrupoAbertura = 1;
    statusLoc.idProcedencia = 1;
    statusLoc.idSegmentacao = 1;
    statusLoc.idTipoCarteira = 1;
    statusLoc.idTipoLinha = 1;
    statusLoc.idTipoPessoa = 1;
    statusLoc.idTipoRelacionamento = 1;
    statusLoc.idUsuarioAlteracao = 1;
    statusLoc.dtAbertura = 1;

    cWFAtdEncaminhadoIncorreto cwfatdencaminhadoincorreto;

    cwfatdencaminhadoincorreto.incluir(&dadosLoc,&statusLoc);

    ULOG_END( "cWF_Acao::gravaAtendimentoEncIncorreto()" );
}

//------------------------------------------------------------------------------------------------------------------
// Alterar Usuario Atual para o processo
//------------------------------------------------------------------------------------------------------------

void cWF_Acao::alterarAtendimentoUsuarioAtual()
{
    ULOG_START("cWF_Acao::alterarAtendimentoUsuarioAtual()");

    alterarAtendimentoUsuarioAtual(UsuarioAtual.ToInt(),false);

    ULOG_END("cWF_Acao::alterarAtendimentoUsuarioAtual()");
}


void cWF_Acao::alterarAtendimentoUsuarioAtual(int idPessoaUsuario,bool atualizarPausa)
{
    ULOG_START("cWF_Acao::alterarAtendimentoUsuarioAtual()");

    struct st_AtendimentoUsuarioAtual   dados;
    struct st_vlAtendimentoUsuarioAtual status;
    XMLGen xmlGen;

    memset ( &dados,  0, sizeof(st_AtendimentoUsuarioAtual)); 
    memset ( &status, -1, sizeof(st_vlAtendimentoUsuarioAtual)); 

    dados.idAtendimento = idAtendimento.ToLong(); 
    dados.idPessoaUsuario = idPessoaUsuario;

    if ( atualizarPausa )
    {
        if ( dtInicioTransferencia[0] )
        {
            strcpy( dados.dtFimPausaAtendimento, dtInicioTransferencia );
            status.dtFimPausaAtendimento = 1;
        }

        dados.inPausaAtendimento = inPausa;
    }
    else
    {
        dados.inPausaAtendimento = 0;
    }

    status.inPausaAtendimento = 1;

    dados.idUsuarioAlteracao = User.ToInt();
    strcpy( dados.dtUltimaAlteracao, sSysDate.c_str() ); 

    status.idAtendimento = 1;
    status.idPessoaUsuario = 1;
    status.idUsuarioAlteracao = 1;
    status.dtUltimaAlteracao = 1;

    cWFAtendimentoUsuarioAtual cwfatendimentousuarioatual(&dados, &status, &xmlGen); 

    cwfatendimentousuarioatual.alterar(&xmlDpr);

    ULOG_END("cWF_Acao::alterarAtendimentoUsuarioAtual() (2)");
}

//------------------------------------------------------------------------------------------------------------------
// Verifica se existe registro em atendimento usuario atual
//------------------------------------------------------------------------------------------------------------------
bool cWF_Acao::existeAtendimentoUsuarioAtual()
{
    ULOG_START("cWF_Acao::existeAtendimentoUsuarioAtual()");

    cWFAtendimentoUsuarioAtual cwfatendimentousuarioatual;

    bool retorno = cwfatendimentousuarioatual.existe(idAtendimento.ToLong());

    ULOG("Processo %d %s esta cadastrado em ATENDIMENTOUSUARIOATUAL"
        , idAtendimento.ToLong(),retorno?"NAO":"");

    ULOG_END("cWF_Acao::existeAtendimentoUsuarioAtual()");

    return retorno;
}

//------------------------------------------------------------------------------------------------------------
// Altera ou inclui pessoausuarioabertura
//------------------------------------------------------------------------------------------------------------
void cWF_Acao::atualizarPessoaUsuarioAbertura(long idPessoaUsuarioAbertura)
{
    ULOG_START("cWF_Acao::atualizarPessoaUsuarioAbertura()");

    proCAtualizarPessoaUsuarioAbertura(idAtendimento.ToLong(),idPessoaUsuarioAbertura);

    ULOG_END("cWF_Acao::atualizarPessoaUsuarioAbertura()");
}

//------------------------------------------------------------------------------------------------------------
// Altera ou inclui atendimentousuarioatual
//------------------------------------------------------------------------------------------------------------
void cWF_Acao::atualizarAtendimentoUsuarioAtual(int idPessoaUsuario,bool atualizarPausa)
{
    ULOG_START("cWF_Acao::atualizarAtendimentoUsuarioAtual()");

    getAtendimentoUsuarioAtual();

    // desativei esta operação pois o idUsuarioAtendimento é importante
    // para algumas ações do workflow
    // if ( existeAtendimentoUsuarioAtual() )

    if ( idUsuarioAtendimento )
    {
        alterarAtendimentoUsuarioAtual(idPessoaUsuario,atualizarPausa);
    }
    else
    {
        inserirAtendimentoUsuarioAtual(atualizarPausa);
        //(desativado pelo motivo explicado acima) idUsuarioAtendimento = 0; // serve como indicador de que uma inclusão foi executada
    }

    ULOG_END("cWF_Acao::atualizarAtendimentoUsuarioAtual()");
}

//------------------------------------------------------------------------------------------------------------
// Altera ou inclui atendimentousuarioatual
//------------------------------------------------------------------------------------------------------------
void cWF_Acao::atualizarAtendimentoUsuarioAtual()
{
    ULOG_START("cWF_Acao::atualizarAtendimentoUsuarioAtual()");

    atualizarAtendimentoUsuarioAtual(UsuarioAtual.ToInt(),false);

    ULOG_END("cWF_Acao::atualizarAtendimentoUsuarioAtual()");
}

//------------------------------------------------------------------------------------------------------------
// Inclui o Atendimento Usuario Atual (agendamento default = false)
//------------------------------------------------------------------------------------------------------------
void cWF_Acao::inserirAtendimentoUsuarioAtual(bool agendamento)
{
    ULOG_START("cWF_Acao::inserirAtendimentoUsuarioAtual()");

    struct st_AtendimentoUsuarioAtual   dados;
    struct st_vlAtendimentoUsuarioAtual status;
    XMLGen xmlGen;

    memset ( &dados,  0, sizeof(st_AtendimentoUsuarioAtual)); 
    memset ( &status, -1, sizeof(st_vlAtendimentoUsuarioAtual)); 

    dados.idAtendimento = idAtendimento.ToLong();
   if ( UsuarioAtual.ToInt() > 0 )
   {
      dados.idPessoaUsuario = UsuarioAtual.ToInt(); 
   }
   else
   {
      dados.idPessoaUsuario = User.ToInt();
   }

    if ( agendamento )
    {
        dados.inPausaAtendimento = inPausa;
        status.inPausaAtendimento = 1;

        strcpy( dados.dtFimPausaAtendimento, dtInicioTransferencia ); 
        status.dtFimPausaAtendimento = 1;
    }

    dados.idUsuarioAlteracao = User.ToInt();
    strcpy( dados.dtUltimaAlteracao, sSysDate.c_str() ); 

    status.idAtendimento = 1;
    status.idPessoaUsuario = 1;
    status.idUsuarioAlteracao = 1;
    status.dtUltimaAlteracao = 1;

    cWFAtendimentoUsuarioAtual cwfatendimentousuarioatual(&dados, &status, &oxml); 
    cwfatendimentousuarioatual.incluir(&xmlDpr); 

    ULOG_END("cWF_Acao::inserirAtendimentoUsuarioAtual()");
}

// ------------------------------------------------------------------------------------------------------------
// Remove o processo de ATENDIMENTO.ATENDIMENTOPAUSA
// ------------------------------------------------------------------------------------------------------------
void cWF_Acao::removeAtendimentoPausa()
{
    ULOG_START("cWF_Acao::removeAtendimentoPausa()");

    struct st_AtendimentoPausa dados;
    struct st_vlAtendimentoPausa status;

    memset (&dados,0,sizeof(st_AtendimentoPausa));
    memset (&status,-1,sizeof(st_vlAtendimentoPausa));

    dados.idAtendimento = idAtendimento.ToLong();
    status.idAtendimento = 1;

    cWFAtendimentoPausa cwfatendimentopausa(&dados, &status); 

    cwfatendimentopausa.excluir(); 

    ULOG_END("cWF_Acao::removeAtendimentoPausa()");
}

// ------------------------------------------------------------------------------------------------------------
// Verifica se o processo esta em pausa em ATENDIMENTO.ATENDIMENTOPAUSA
// ------------------------------------------------------------------------------------------------------------
bool cWF_Acao::ProcessoEmPausaSimNao()
{
    ULOG_START("cWF_Acao::ProcessoEmPausaSimNao()");

    cWFAtendimentoPausa cwfatendimentopausa;

    bool processoEmPausaSimNao = cwfatendimentopausa.processoEmPausaSimNao(idAtendimento.ToLong()); 

    ULOG("processoEmPausaSimNao=%d",processoEmPausaSimNao);

    ULOG_END("cWF_Acao::ProcessoEmPausaSimNao()");

    return processoEmPausaSimNao;
}


// ------------------------------------------------------------------------------------------------------------
// atualiza ATENDIMENTO.ATENDIMENTOPAUSA
// ------------------------------------------------------------------------------------------------------------
void cWF_Acao::atualizarAtendimentoPausa()
{
    ULOG_START("cWF_Acao::atualizarAtendimentoPausa()");

    struct st_AtendimentoPausa dados;
    struct st_vlAtendimentoPausa status;

    memset (&dados,0,sizeof(st_AtendimentoPausa));
    memset (&status,-1,sizeof(st_vlAtendimentoPausa));

    dados.idAtendimento = idAtendimento.ToLong();
    dados.idUsuarioAlteracao = User.ToInt();
    strcpy(dados.dtFimPausaAtendimento, dtInicioTransferencia);
    strcpy(dados.dtUltimaAlteracao, sSysDate.c_str());

    status.idAtendimento = 1;
    status.dtFimPausaAtendimento = 1;
    status.idUsuarioAlteracao = 1;
    status.dtUltimaAlteracao = 1;

    cWFAtendimentoPausa cwfatendimentopausa(&dados, &status); 

    cwfatendimentopausa.atualizar(); 

    ULOG_END("cWF_Acao::atualizarAtendimentoPausa()");
}

//------------------------------------------------------------------------------------------------------------------
// Inserir Cri Associado para o processo
//------------------------------------------------------------------------------------------------------------
void cWF_Acao::inserirAtendimentoCri()
{
    ULOG_START("cWF_Acao::inserirAtendimentoCri()");

    struct st_AtdTratamentoCri dados;
    struct st_vlAtdTratamentoCri status;
    // XMLGen xmlGen;

    memset ( &dados,   0, sizeof(st_AtdTratamentoCri)); 
    memset ( &status, -1, sizeof(st_vlAtdTratamentoCri)); 

    dados.idAtendimento = idAtendimento.ToLong(); 
    dados.idPessoaUsuario = UsuarioAtual.ToInt();
    dados.idPessoaLinhaHistorico = idPessoaLinhaHistorico;
    dados.qtEnvioBko = 0;

    strcpy( dados.dtAbertura, sSysDate.c_str() ); 
    strcpy( dados.dtPrazoCri, sSysDate.c_str() ); 
    strcpy( dados.dtUltimaAlteracao, sSysDate.c_str() ); 
    dados.idUsuarioAlteracao = User.ToInt(); 

    status.idAtendimento = 1;
    status.idPessoaUsuario = 1;
    status.idPessoaLinhaHistorico = 1;
    status.dtAbertura = 1;
    status.dtPrazoCri = 1;
    status.idUsuarioAlteracao = 1;
    status.dtUltimaAlteracao = 1;
    status.qtEnvioBko = 1;

    cWFAtdTratamentoCri usrCri(&dados, &status);

    usrCri.incluirAtendimentoCri();

    ULOG_END("cWF_Acao::inserirAtendimentoCri()");
}

//------------------------------------------------------------------------------------------------------------------
// INSERE EM ATENDIMENTO.TRATAMENTOCRI E MANTÉM ATENDIMENTO.TRATAMENTOGRUPOCRI ATUALIZADA
//------------------------------------------------------------------------------------------------------------

void cWF_Acao::inserirTratamentoCri()
{
    ULOG_START("cWF_Acao::inserirTratamentoCri()");

    struct st_AtdTratamentoCri dados;
    struct st_vlAtdTratamentoCri status;
    XMLGen xmlGen;

    memset (&dados,   0, sizeof(st_AtdTratamentoCri)); 
    memset (&status, -1, sizeof(st_vlAtdTratamentoCri)); 

    dados.idAtendimento      = idAtendimento.ToLong(); 
    dados.idGrupo = idGrupo.ToInt();
    dados.idPessoaUsuario            = UsuarioAtual.ToInt();
    dados.idUsuarioAlteracao = User.ToInt(); 
    strcpy( dados.dtUltimaAlteracao, sSysDate.c_str() ); 

    status.idAtendimento = 1;
    status.idGrupo = 1;
    status.idPessoaUsuario = 1;
    status.idUsuarioAlteracao = 1;
    status.dtUltimaAlteracao = 1;

    cWFAtdTratamentoCri usrCri(&dados, &status, &xmlGen);

    usrCri.incluirTratamentoCri(); // ATENDIMENTO.TRATAMENTOCRI

    if ( usrCri.getTratamentoGrupoCri() )
    {
        usrCri.alterarTratamentoGrupoCri(); // ATENDIMENTO.TRATAMENTOGRUPOCRI
    }
    else
    {
        usrCri.incluirTratamentoGrupoCri(); // ATENDIMENTO.TRATAMENTOGRUPOCRI
    }

    ULOG_END("cWF_Acao::inserirTratamentoCri()");
}

//------------------------------------------------------------------------------------------------------------------    
// Existe Fechamento
//------------------------------------------------------------------------------------------------------------------    
bool cWF_Acao::existeAtendimentoFechamento()
{
    ULOG_START("cWF_Acao::existeAtendimentoFechamento()");

    cWFAtendimentoFechamento AtendimentoFechamento;

    bool retorno = AtendimentoFechamento.existeAtendimentoFechamento(idAtendimento.ToLong());

    ULOG("retorno=%d", retorno);

    ULOG_END("cWF_Acao::existeAtendimentoFechamento()");

    return retorno;
}

//------------------------------------------------------------------------------------------------------------------    
// Inserir Fechamento 
//------------------------------------------------------------------------------------------------------------------    
void cWF_Acao::inserirAtendimentoFechamento()
{
    ULOG_START("cWF_Acao::inserirAtendimentoFechamento()");

    st_AtendimentoFechamento m_stDadosAndamentoFechamento;
    st_vlAtendimentoFechamento m_vlDadosAndamentoFechamento;
    XMLGen fechamento;

    memset(&m_stDadosAndamentoFechamento, 0, sizeof(m_stDadosAndamentoFechamento));
    memset(&m_vlDadosAndamentoFechamento,-1, sizeof(m_vlDadosAndamentoFechamento));

    m_stDadosAndamentoFechamento.idAtendimento=idAtendimento.ToLong();
    m_stDadosAndamentoFechamento.idAndamento=idAndamento;
    m_stDadosAndamentoFechamento.idPessoaUsuario=User.ToInt();
    strcpy(m_stDadosAndamentoFechamento.dtFechamento, sSysDate.c_str());

    m_vlDadosAndamentoFechamento.idAtendimento=1;
    m_vlDadosAndamentoFechamento.idAndamento=1;
    m_vlDadosAndamentoFechamento.idPessoaUsuario=1;
    m_vlDadosAndamentoFechamento.dtFechamento=1;

    cWFAtendimentoFechamento AtendimentoFechamento(&m_stDadosAndamentoFechamento, &m_vlDadosAndamentoFechamento, &fechamento);

    AtendimentoFechamento.incluir(&xmlDpr);

    ULOG_END("cWF_Acao::inserirAtendimentoFechamento()");
}

//------------------------------------------------------------------------------------------------------------------
// Remove Fechamento
//------------------------------------------------------------------------------------------------------------------
void cWF_Acao::removeAtendimentoFechamento() 
{
    ULOG_START("cWF_Acao::removeAtendimentoFechamento()");

    st_AtendimentoFechamento m_stDadosAndamentoFechamento;
    st_vlAtendimentoFechamento m_vlDadosAndamentoFechamento;
    XMLGen fechamento;

    memset(&m_stDadosAndamentoFechamento, 0, sizeof(m_stDadosAndamentoFechamento));
    memset(&m_vlDadosAndamentoFechamento,-1, sizeof(m_vlDadosAndamentoFechamento));

    m_stDadosAndamentoFechamento.idAtendimento=idAtendimento.ToLong();
    m_vlDadosAndamentoFechamento.idAtendimento=1;
    
    cWFAtendimentoFechamento AtendimentoFechamento(&m_stDadosAndamentoFechamento, &m_vlDadosAndamentoFechamento, &fechamento);

    AtendimentoFechamento.excluir(&xmlDpr);

    ULOG_END("cWF_Acao::removeAtendimentoFechamento()");
}

//------------------------------------------------------------------------------------------------------------------
// Inserir Cancelamento Solicitado
//------------------------------------------------------------------------------------------------------------------
void cWF_Acao::inserirCancelamentoSolicitado()
{
    ULOG_START("cWF_Acao::inserirCancelamentoSolicitado()");

    struct st_CancelamentoSolicitado    calcSol;
    struct st_vlCancelamentoSolicitado vcalcSol;
    XMLGen cancelamento;

    memset ( &calcSol ,  0, sizeof( st_CancelamentoSolicitado   )); 
    memset ( &vcalcSol, -1, sizeof( st_vlCancelamentoSolicitado ));

    calcSol.idAtendimento                   = idAtendimento.ToLong();
    calcSol.idPessoaUsuario                 = User.ToInt(); 
    calcSol.idUsuarioAlteracao              = User.ToInt(); 
    strcpy ( calcSol.dtSolicitacaoCancelamento, sSysDate.c_str() ); 
    strcpy ( calcSol.dtUltimaAlteracao,         sSysDate.c_str() ); 

    vcalcSol.idAtendimento = 1;
    vcalcSol.idPessoaUsuario = 1;
    vcalcSol.idUsuarioAlteracao = 1;
    vcalcSol.dtSolicitacaoCancelamento = 1;
    vcalcSol.dtUltimaAlteracao = 1;

    cWFCancelamentoSolicitado cwfCancelamentoSolicitado(&calcSol, &vcalcSol, &cancelamento); 
    cwfCancelamentoSolicitado.incluir(); 

    ULOG_END("cWF_Acao::inserirCancelamentoSolicitado()");
}

//------------------------------------------------------------------------------------------------------------------
// Inserir Alerta Atendimento
//------------------------------------------------------------------------------------------------------------


void cWF_Acao::inserirAtendimentoAlerta()
{
    ULOG_START("cWF_Acao::inserirAtendimentoAlerta()");

    XMLGen xmlSetAlerta;

    struct st_AtendimentoMensagem    atdMsg; 
    struct st_vlAtendimentoMensagem vatdMsg; 

    memset ( &atdMsg , 0, sizeof( st_AtendimentoMensagem   )); 
    memset ( &vatdMsg,-1, sizeof( st_vlAtendimentoMensagem ));

    atdMsg.idAtendimento            = idAtendimento.ToLong();
    atdMsg.idAtividade              = idAtividade.ToInt();
    atdMsg.idMensagemAtendimento    = idAlerta; 
    strcpy ( atdMsg.dtMensagem, sSysDate.c_str() ); 

    vatdMsg.idAtendimento = 1;
    vatdMsg.idAtividade = 1;
    vatdMsg.idMensagemAtendimento = 1;
    vatdMsg.dtMensagem = 1;

    cWFAtendimentoMensagem ocatdMsg( &atdMsg, &vatdMsg, &xmlSetAlerta );

    ocatdMsg.incluir(); // ATENDIMENTO.ATENDIMENTOALERTAG00

    ULOG_END("cWF_Acao::inserirAtendimentoAlerta()");
}

//------------------------------------------------------------------------------------------------------------------
// Inserir Atendimento Baixa Historico
//------------------------------------------------------------------------------------------------------------------
void cWF_Acao::inserirAtendimentoBaixaHistorico() 
{
    ULOG_START("cWF_Acao::inserirAtendimentoBaixaHistorico()");

    if ( idBaixa.ToInt() == 0 )
    {
        ULOG("idBaixa = 0");
        ULOG_END("cWF_Acao::inserirAtendimentoBaixaHistorico()");
        return;
    }

    char *p;

    st_AtendimentoBaixaHistorico m_stDadosHist;
    st_vlAtendimentoBaixaHistorico m_vlDadosHist;
    XMLGen baixaHistorico;

    memset(&m_stDadosHist, 0, sizeof(m_stDadosHist));
    memset(&m_vlDadosHist,-1, sizeof(m_vlDadosHist));

    m_stDadosHist.idAtendimento=idAtendimento.ToLong();
    m_vlDadosHist.idAtendimento=1;
    m_stDadosHist.idBaixa= idBaixa.ToInt();
    m_vlDadosHist.idBaixa=1;
    m_stDadosHist.idFase=idFase.ToInt();
    m_vlDadosHist.idFase=1;
    m_stDadosHist.idAndamento=idAndamento;
    m_vlDadosHist.idAndamento=1;
    m_stDadosHist.idPessoaUsuario=User.ToInt();
    m_vlDadosHist.idPessoaUsuario=1;
    m_stDadosHist.idUsuarioAlteracao=User.ToInt();
    m_vlDadosHist.idUsuarioAlteracao=1;
    strcpy(m_stDadosHist.dtUltimaAlteracao, sSysDate.c_str());
    m_vlDadosHist.dtUltimaAlteracao=1;
    strcpy(m_stDadosHist.dtBaixa, sSysDate.c_str());
    m_vlDadosHist.dtBaixa=1;

    cWFAtendimentoBaixaHistorico AtendimentoBaixaHistorico(&m_stDadosHist, &m_vlDadosHist, &baixaHistorico );
    AtendimentoBaixaHistorico.incluir(&xmlDpr);

    if (wta.walkTreeXMLGen(&baixaHistorico, "idAtendimentoBaixaHistorico", &p, 0 )==0)
    {
        idAtendimentoBaixaHistorico=atol(p);
        XMLString::release(&p);         
    }

    ULOG_END("cWF_Acao::inserirAtendimentoBaixaHistorico()");
}

//------------------------------------------------------------------------------------------------------------------
// Inserir Atendimento Baixa Mensagem
//------------------------------------------------------------------------------------------------------------------
void cWF_Acao::inserirAtendimentoBaixaMensagem() 
{
    ULOG_START("cWF_Acao::inserirAtendimentoBaixaMensagem()");

    st_AtendimentoBaixaHistorico m_stDadosHist;
    st_vlAtendimentoBaixaHistorico m_vlDadosHist;
    XMLGen baixaHistorico;

    memset(&m_stDadosHist, 0, sizeof(m_stDadosHist));
    memset(&m_vlDadosHist,-1, sizeof(m_vlDadosHist));
    m_stDadosHist.idAtendimentoBaixaHistorico=idAtendimentoBaixaHistorico;
    m_vlDadosHist.idAtendimentoBaixaHistorico=1;
    m_stDadosHist.idBaixaMensagem= idBaixaMensagem;
    m_vlDadosHist.idBaixaMensagem=1;
    m_stDadosHist.idUsuarioAlteracao=User.ToInt();
    m_vlDadosHist.idUsuarioAlteracao=1;
    strcpy(m_stDadosHist.dtUltimaAlteracao, sSysDate.c_str());
    m_vlDadosHist.dtUltimaAlteracao=1;

    cWFAtendimentoBaixaHistorico AtendimentoBaixaHistorico(&m_stDadosHist, &m_vlDadosHist, &baixaHistorico );
    AtendimentoBaixaHistorico.incluirBaixaMensagem();

    ULOG_END("cWF_Acao::inserirAtendimentoBaixaMensagem()");
}

//------------------------------------------------------------------------------------------------------------------
// Inserir Atendimento Baixa Atual
//------------------------------------------------------------------------------------------------------------------
void cWF_Acao::inserirAtendimentoBaixaAtual() 
{
    ULOG_START("cWF_Acao::inserirAtendimentoBaixaAtual()");

    if ( idAtendimentoBaixaHistorico > 0 ) 
    {
        st_AtendimentoBaixaAtual m_stDadosBaixaAtual;
        st_vlAtendimentoBaixaAtual m_vlDadosBaixaAtual;
        XMLGen baixa;

        memset(&m_stDadosBaixaAtual, 0, sizeof(m_stDadosBaixaAtual));
        memset(&m_vlDadosBaixaAtual,-1, sizeof(m_vlDadosBaixaAtual));
        m_stDadosBaixaAtual.idAtendimento=idAtendimento.ToLong();
        m_vlDadosBaixaAtual.idAtendimento=1;
        strcpy(m_stDadosBaixaAtual.dtUltimaAlteracao,  sSysDate.c_str());
        m_vlDadosBaixaAtual.dtUltimaAlteracao=1;
        m_stDadosBaixaAtual.idAtendimentoBaixaHistorico=idAtendimentoBaixaHistorico;
        m_vlDadosBaixaAtual.idAtendimentoBaixaHistorico=1;
        m_stDadosBaixaAtual.idUsuarioAlteracao=User.ToInt();
        m_vlDadosBaixaAtual.idUsuarioAlteracao=1;

        cWFAtendimentoBaixaAtual AtendimentoBaixaAtual(&m_stDadosBaixaAtual, &m_vlDadosBaixaAtual, &baixa);

        AtendimentoBaixaAtual.incluir();
    }
    else
    {
        ULOGW("idAtendimentoBaixaHistorico não fornecido");
    }

    ULOG_END("cWF_Acao::inserirAtendimentoBaixaAtual()");
}

//------------------------------------------------------------------------------------------------------------------
// Inserir Formulario Dinamico
// Inclusão Dos Campos e dos Valores dos Campos do Formulário
//------------------------------------------------------------------------------------------------------------------
void cWF_Acao::inserirFormulario() 
{
    ULOG_START("cWF_Acao::inserirFormulario()");

    int contador=0;
    int contador2;
    int aux=0;  
    char* valor;
    char* p;

    long idAtendimentoFrm;

    DOMNode* domAux;
    DOMNode* domAux2;

    st_AtendimentoFrm m_stDadosAtendimentoFrm;
    st_vlAtendimentoFrm m_vlDadosAtendimentoFrm;
    XMLGen atdFrm;

    st_AtendimentoFrmCampo m_stDadosAtendimentoFrmCampo;
    st_vlAtendimentoFrmCampo m_vlDadosAtendimentoFrmCampo;
    XMLGen atdFrmValor;

    while (domAux= walkDOM( domFormulario, "FormularioCampoVO", contador++))
    {
        if (valor=walkTree(domAux, "idContatoFolhaCampo", 0 ),valor)
        {
            if ( valor )
            {
                if ( atoi(valor) > 0 )
                {
                    memset(&m_stDadosAtendimentoFrm, 0, sizeof(m_stDadosAtendimentoFrm));
                    memset(&m_vlDadosAtendimentoFrm,-1, sizeof(m_vlDadosAtendimentoFrm));

                    if (p=walkTree(domAux, "idCampo", 0 ),p)
                    {
                        m_stDadosAtendimentoFrm.idCampo=atoi(p);
                        m_vlDadosAtendimentoFrm.idCampo=1;
                        XMLString::release(&p);
                    }

                    m_stDadosAtendimentoFrm.idAtendimento=idAtendimento.ToLong();
                    m_vlDadosAtendimentoFrm.idAtendimento=1;

                    m_stDadosAtendimentoFrm.idUsuarioAlteracao=User.ToInt();
                    m_vlDadosAtendimentoFrm.idUsuarioAlteracao=1;

                    strcpy(m_stDadosAtendimentoFrm.dtUltimaAlteracao, sSysDate.c_str());
                    m_vlDadosAtendimentoFrm.dtUltimaAlteracao=1;

                    cWFAtendimentoFrm AtendimentoFrm(&m_stDadosAtendimentoFrm, &m_vlDadosAtendimentoFrm, &atdFrm);

                    idAtendimentoFrm = 0;

                    contador2 = 0;

                    while (domAux2= walkDOM( domAux, "FormularioCampoValorVO", contador2++))
                    {
                        bool incluir = true;

                        memset(&m_stDadosAtendimentoFrmCampo, 0, sizeof(m_stDadosAtendimentoFrmCampo));
                        memset(&m_vlDadosAtendimentoFrmCampo,-1, sizeof(m_vlDadosAtendimentoFrmCampo));

                        if (p=walkTree(domAux2,"idFormularioCampoValor",0),p)
                        {
                           if (atoi(p) > 0)
                           {
                                m_stDadosAtendimentoFrmCampo.idDominio=atoi(p);
                                m_vlDadosAtendimentoFrmCampo.idDominio=1;
                           }
                           XMLString::release(&p);
                        }

                        if ( m_vlDadosAtendimentoFrmCampo.idDominio != 1 )
                        {
                            if (p=walkTree(domAux2,"valor",0),p)
                            {
                                strcpy(m_stDadosAtendimentoFrmCampo.dsValor,p);
                                m_vlDadosAtendimentoFrmCampo.dsValor=1;
                                XMLString::release(&p);
                            }

                            if ( m_stDadosAtendimentoFrmCampo.dsValor[0] == 0 )
                            {
                                incluir = false;
                            }
                        }

                        if (incluir)
                        {
                            m_stDadosAtendimentoFrmCampo.idUsuarioAlteracao=User.ToInt();
                            m_vlDadosAtendimentoFrmCampo.idUsuarioAlteracao=1;
                            strcpy(m_stDadosAtendimentoFrmCampo.dtUltimaAlteracao, sSysDate.c_str());
                            m_vlDadosAtendimentoFrmCampo.dtUltimaAlteracao=1;

                            if (idAtendimentoFrm == 0)
                            {
                                idAtendimentoFrm = AtendimentoFrm.incluir(&xmlDpr);
                            }

                            m_stDadosAtendimentoFrmCampo.idAtendimentoFrm = idAtendimentoFrm;
                            m_vlDadosAtendimentoFrmCampo.idAtendimentoFrm = 1;

                            cWFAtendimentoFrmCampo AtendimentoFrmCampo(&m_stDadosAtendimentoFrmCampo, &m_vlDadosAtendimentoFrmCampo, &atdFrmValor);

                            AtendimentoFrmCampo.incluir(&xmlDpr);
                        }
                    }
                }
                XMLString::release(&valor);
            }
        }
    }


    //
    // Alteração conforme SM448
    // Gravamos os grupos de campos que estão no formulário
    //
    ULOG("cWF_Acao::inserirFormulario: Inserindo Grupos de Campos.");

    DOMNode* domGrupocampos;
    int contadorGrupoCampos = 0;

    // Para cada grupo de campos
    while (domGrupocampos= walkDOM( domFormulario, "AdmGrupoCamposVO", contadorGrupoCampos++))
    {
        // Zera o contador.
        contador = 0;

        while (domAux= walkDOM( domGrupocampos, "FormularioCampoVO", contador++))
        {
            if (valor=walkTree(domAux, "idContatoFolhaCampo", 0 ),valor)
            {
                if ( valor )
                {
                    if ( atoi(valor) > 0 )
                    {
                        memset(&m_stDadosAtendimentoFrm, 0, sizeof(m_stDadosAtendimentoFrm));
                        memset(&m_vlDadosAtendimentoFrm,-1, sizeof(m_vlDadosAtendimentoFrm));

                        if (p=walkTree(domAux, "idCampo", 0 ),p)
                        {
                            m_stDadosAtendimentoFrm.idCampo=atoi(p);
                            m_vlDadosAtendimentoFrm.idCampo=1;
                            XMLString::release(&p);
                        }

                        m_stDadosAtendimentoFrm.idAtendimento=idAtendimento.ToLong();
                        m_vlDadosAtendimentoFrm.idAtendimento=1;

                        m_stDadosAtendimentoFrm.idUsuarioAlteracao=User.ToInt();
                        m_vlDadosAtendimentoFrm.idUsuarioAlteracao=1;

                        strcpy(m_stDadosAtendimentoFrm.dtUltimaAlteracao, sSysDate.c_str());
                        m_vlDadosAtendimentoFrm.dtUltimaAlteracao=1;

                        cWFAtendimentoFrm AtendimentoFrm(&m_stDadosAtendimentoFrm, &m_vlDadosAtendimentoFrm, &atdFrm);

//                        idAtendimentoFrm = 0;

                        contador2 = 0;

                        while (domAux2= walkDOM( domAux, "FormularioCampoValorVO", contador2++))
                        {
                            bool incluir = true;

                            memset(&m_stDadosAtendimentoFrmCampo, 0, sizeof(m_stDadosAtendimentoFrmCampo));
                            memset(&m_vlDadosAtendimentoFrmCampo,-1, sizeof(m_vlDadosAtendimentoFrmCampo));

                            if (p=walkTree(domAux2,"idFormularioCampoValor",0),p)
                            {
                               if (atoi(p) > 0)
                               {
                                    m_stDadosAtendimentoFrmCampo.idDominio=atoi(p);
                                    m_vlDadosAtendimentoFrmCampo.idDominio=1;
                               }
                               XMLString::release(&p);
                            }

                            if ( m_vlDadosAtendimentoFrmCampo.idDominio != 1 )
                            {
                                if (p=walkTree(domAux2,"valor",0),p)
                                {
                                    strcpy(m_stDadosAtendimentoFrmCampo.dsValor,p);
                                    m_vlDadosAtendimentoFrmCampo.dsValor=1;
                                    XMLString::release(&p);
                                }

                                if ( m_stDadosAtendimentoFrmCampo.dsValor[0] == 0 )
                                {
                                    incluir = false;
                                }
                            }

                            if (incluir)
                            {
                                m_stDadosAtendimentoFrmCampo.idUsuarioAlteracao=User.ToInt();
                                m_vlDadosAtendimentoFrmCampo.idUsuarioAlteracao=1;
                                strcpy(m_stDadosAtendimentoFrmCampo.dtUltimaAlteracao, sSysDate.c_str());
                                m_vlDadosAtendimentoFrmCampo.dtUltimaAlteracao=1;

/*                                if (idAtendimentoFrm == 0)
                                {
                                    idAtendimentoFrm = AtendimentoFrm.incluir(&xmlDpr);
                                }
*/
                                m_stDadosAtendimentoFrmCampo.idAtendimentoFrm = idAtendimentoFrm;
                                m_vlDadosAtendimentoFrmCampo.idAtendimentoFrm = 1;

                                cWFAtendimentoFrmCampo AtendimentoFrmCampo(&m_stDadosAtendimentoFrmCampo, &m_vlDadosAtendimentoFrmCampo, &atdFrmValor);

//                                AtendimentoFrmCampo.incluir(&xmlDpr);
                            }
                        }
                    }
                    XMLString::release(&valor);
                }
            }
        } // while() FormularioCampoVO

    } // while() AdmGrupoCampos



    //
    // Alteração conforme SM448
    // Gravamos os grupos de campos que estão no formulário
    //
    ULOG("cWF_Acao::inserirFormulario: Funcionalidade ENDERECO...");
    if (domAux= walkDOM( domFormulario, "EnderecoVO", 0))
    {
        ULOG("cWF_Acao::inserirFormulario: Funcionalidade ENDERECO Inicio.");
        //
        // TipoEnderecoVO
        //
        if (domAux2= walkDOM( domAux, "TipoEnderecoVO", 0))
        {
            if (valor=walkTree(domAux2, "idTipoEndereco", 0 ),valor)
            {
                memset(&m_stDadosAtendimentoFrm, 0, sizeof(m_stDadosAtendimentoFrm));
                memset(&m_vlDadosAtendimentoFrm,-1, sizeof(m_vlDadosAtendimentoFrm));

                // Pega o idCampo
                m_stDadosAtendimentoFrm.idCampo=1485;
                m_vlDadosAtendimentoFrm.idCampo=1;

                m_stDadosAtendimentoFrm.idAtendimento=idAtendimento.ToLong();
                m_vlDadosAtendimentoFrm.idAtendimento=1;

                m_stDadosAtendimentoFrm.idUsuarioAlteracao=User.ToInt();
                m_vlDadosAtendimentoFrm.idUsuarioAlteracao=1;

                strcpy(m_stDadosAtendimentoFrm.dtUltimaAlteracao, sSysDate.c_str());
                m_vlDadosAtendimentoFrm.dtUltimaAlteracao=1;

                cWFAtendimentoFrm AtendimentoFrm(&m_stDadosAtendimentoFrm, &m_vlDadosAtendimentoFrm, &atdFrm);

//                idAtendimentoFrm = 0;

                contador2 = 0;

                bool incluir = true;

                memset(&m_stDadosAtendimentoFrmCampo, 0, sizeof(m_stDadosAtendimentoFrmCampo));
                memset(&m_vlDadosAtendimentoFrmCampo,-1, sizeof(m_vlDadosAtendimentoFrmCampo));

                m_stDadosAtendimentoFrmCampo.idDominio=atoi(valor);
                m_vlDadosAtendimentoFrmCampo.idDominio=1;

                if ( m_vlDadosAtendimentoFrmCampo.idDominio != 1 )
                {
                    strcpy(m_stDadosAtendimentoFrmCampo.dsValor,p);
                    m_vlDadosAtendimentoFrmCampo.dsValor=1;

                    if ( m_stDadosAtendimentoFrmCampo.dsValor[0] == 0 )
                    {
                        incluir = false;
                    }
                }

                if (incluir)
                {
                    m_stDadosAtendimentoFrmCampo.idUsuarioAlteracao=User.ToInt();
                    m_vlDadosAtendimentoFrmCampo.idUsuarioAlteracao=1;
                    strcpy(m_stDadosAtendimentoFrmCampo.dtUltimaAlteracao, sSysDate.c_str());
                    m_vlDadosAtendimentoFrmCampo.dtUltimaAlteracao=1;

/*                    if (idAtendimentoFrm == 0)
                    {
                        idAtendimentoFrm = AtendimentoFrm.incluir(&xmlDpr);
                    }
*/
                    m_stDadosAtendimentoFrmCampo.idAtendimentoFrm = idAtendimentoFrm;
                    m_vlDadosAtendimentoFrmCampo.idAtendimentoFrm = 1;

                    cWFAtendimentoFrmCampo AtendimentoFrmCampo(&m_stDadosAtendimentoFrmCampo, &m_vlDadosAtendimentoFrmCampo, &atdFrmValor);

//                    AtendimentoFrmCampo.incluir(&xmlDpr);
                }
                XMLString::release(&valor);
            }
        }

        //
        // nmTipoLogradouro
        //
        if (valor=walkTree(domAux, "nmTipoLogradouro", 0 ),valor)
        {
            memset(&m_stDadosAtendimentoFrm, 0, sizeof(m_stDadosAtendimentoFrm));
            memset(&m_vlDadosAtendimentoFrm,-1, sizeof(m_vlDadosAtendimentoFrm));

            // Pega o idCampo
            m_stDadosAtendimentoFrm.idCampo=177;
            m_vlDadosAtendimentoFrm.idCampo=1;

            m_stDadosAtendimentoFrm.idAtendimento=idAtendimento.ToLong();
            m_vlDadosAtendimentoFrm.idAtendimento=1;

            m_stDadosAtendimentoFrm.idUsuarioAlteracao=User.ToInt();
            m_vlDadosAtendimentoFrm.idUsuarioAlteracao=1;

            strcpy(m_stDadosAtendimentoFrm.dtUltimaAlteracao, sSysDate.c_str());
            m_vlDadosAtendimentoFrm.dtUltimaAlteracao=1;

            cWFAtendimentoFrm AtendimentoFrm(&m_stDadosAtendimentoFrm, &m_vlDadosAtendimentoFrm, &atdFrm);

            contador2 = 0;

            bool incluir = true;

            memset(&m_stDadosAtendimentoFrmCampo, 0, sizeof(m_stDadosAtendimentoFrmCampo));
            memset(&m_vlDadosAtendimentoFrmCampo,-1, sizeof(m_vlDadosAtendimentoFrmCampo));

            m_stDadosAtendimentoFrmCampo.idDominio=0;
            m_vlDadosAtendimentoFrmCampo.idDominio=-1;

            strcpy(m_stDadosAtendimentoFrmCampo.dsValor,p);
            m_vlDadosAtendimentoFrmCampo.dsValor=1;

            if ( m_stDadosAtendimentoFrmCampo.dsValor[0] == 0 )
            {
                incluir = false;
            }

            if (incluir)
            {
                m_stDadosAtendimentoFrmCampo.idUsuarioAlteracao=User.ToInt();
                m_vlDadosAtendimentoFrmCampo.idUsuarioAlteracao=1;
                strcpy(m_stDadosAtendimentoFrmCampo.dtUltimaAlteracao, sSysDate.c_str());
                m_vlDadosAtendimentoFrmCampo.dtUltimaAlteracao=1;


                m_stDadosAtendimentoFrmCampo.idAtendimentoFrm = idAtendimentoFrm;
                m_vlDadosAtendimentoFrmCampo.idAtendimentoFrm = 1;

                cWFAtendimentoFrmCampo AtendimentoFrmCampo(&m_stDadosAtendimentoFrmCampo, &m_vlDadosAtendimentoFrmCampo, &atdFrmValor);

            }
            XMLString::release(&valor);
        }

        //
        // nmTituloLogradouro
        //
        if (valor=walkTree(domAux, "nmTituloLogradouro", 0 ),valor)
        {
            memset(&m_stDadosAtendimentoFrm, 0, sizeof(m_stDadosAtendimentoFrm));
            memset(&m_vlDadosAtendimentoFrm,-1, sizeof(m_vlDadosAtendimentoFrm));

            // Pega o idCampo
            m_stDadosAtendimentoFrm.idCampo=1487;
            m_vlDadosAtendimentoFrm.idCampo=1;

            m_stDadosAtendimentoFrm.idAtendimento=idAtendimento.ToLong();
            m_vlDadosAtendimentoFrm.idAtendimento=1;

            m_stDadosAtendimentoFrm.idUsuarioAlteracao=User.ToInt();
            m_vlDadosAtendimentoFrm.idUsuarioAlteracao=1;

            strcpy(m_stDadosAtendimentoFrm.dtUltimaAlteracao, sSysDate.c_str());
            m_vlDadosAtendimentoFrm.dtUltimaAlteracao=1;

            cWFAtendimentoFrm AtendimentoFrm(&m_stDadosAtendimentoFrm, &m_vlDadosAtendimentoFrm, &atdFrm);

            contador2 = 0;

            bool incluir = true;

            memset(&m_stDadosAtendimentoFrmCampo, 0, sizeof(m_stDadosAtendimentoFrmCampo));
            memset(&m_vlDadosAtendimentoFrmCampo,-1, sizeof(m_vlDadosAtendimentoFrmCampo));

            m_stDadosAtendimentoFrmCampo.idDominio=0;
            m_vlDadosAtendimentoFrmCampo.idDominio=-1;

            strcpy(m_stDadosAtendimentoFrmCampo.dsValor,p);
            m_vlDadosAtendimentoFrmCampo.dsValor=1;

            if ( m_stDadosAtendimentoFrmCampo.dsValor[0] == 0 )
            {
                incluir = false;
            }

            if (incluir)
            {
                m_stDadosAtendimentoFrmCampo.idUsuarioAlteracao=User.ToInt();
                m_vlDadosAtendimentoFrmCampo.idUsuarioAlteracao=1;
                strcpy(m_stDadosAtendimentoFrmCampo.dtUltimaAlteracao, sSysDate.c_str());
                m_vlDadosAtendimentoFrmCampo.dtUltimaAlteracao=1;


                m_stDadosAtendimentoFrmCampo.idAtendimentoFrm = idAtendimentoFrm;
                m_vlDadosAtendimentoFrmCampo.idAtendimentoFrm = 1;

                cWFAtendimentoFrmCampo AtendimentoFrmCampo(&m_stDadosAtendimentoFrmCampo, &m_vlDadosAtendimentoFrmCampo, &atdFrmValor);

            }
            XMLString::release(&valor);
        }

        //
        // nmLogradouro
        //
        if (valor=walkTree(domAux, "nmLogradouro", 0 ),valor)
        {
            memset(&m_stDadosAtendimentoFrm, 0, sizeof(m_stDadosAtendimentoFrm));
            memset(&m_vlDadosAtendimentoFrm,-1, sizeof(m_vlDadosAtendimentoFrm));

            // Pega o idCampo
            m_stDadosAtendimentoFrm.idCampo=174;
            m_vlDadosAtendimentoFrm.idCampo=1;

            m_stDadosAtendimentoFrm.idAtendimento=idAtendimento.ToLong();
            m_vlDadosAtendimentoFrm.idAtendimento=1;

            m_stDadosAtendimentoFrm.idUsuarioAlteracao=User.ToInt();
            m_vlDadosAtendimentoFrm.idUsuarioAlteracao=1;

            strcpy(m_stDadosAtendimentoFrm.dtUltimaAlteracao, sSysDate.c_str());
            m_vlDadosAtendimentoFrm.dtUltimaAlteracao=1;

            cWFAtendimentoFrm AtendimentoFrm(&m_stDadosAtendimentoFrm, &m_vlDadosAtendimentoFrm, &atdFrm);

            contador2 = 0;

            bool incluir = true;

            memset(&m_stDadosAtendimentoFrmCampo, 0, sizeof(m_stDadosAtendimentoFrmCampo));
            memset(&m_vlDadosAtendimentoFrmCampo,-1, sizeof(m_vlDadosAtendimentoFrmCampo));

            m_stDadosAtendimentoFrmCampo.idDominio=0;
            m_vlDadosAtendimentoFrmCampo.idDominio=-1;

            strcpy(m_stDadosAtendimentoFrmCampo.dsValor,p);
            m_vlDadosAtendimentoFrmCampo.dsValor=1;

            if ( m_stDadosAtendimentoFrmCampo.dsValor[0] == 0 )
            {
                incluir = false;
            }

            if (incluir)
            {
                m_stDadosAtendimentoFrmCampo.idUsuarioAlteracao=User.ToInt();
                m_vlDadosAtendimentoFrmCampo.idUsuarioAlteracao=1;
                strcpy(m_stDadosAtendimentoFrmCampo.dtUltimaAlteracao, sSysDate.c_str());
                m_vlDadosAtendimentoFrmCampo.dtUltimaAlteracao=1;


                m_stDadosAtendimentoFrmCampo.idAtendimentoFrm = idAtendimentoFrm;
                m_vlDadosAtendimentoFrmCampo.idAtendimentoFrm = 1;

                cWFAtendimentoFrmCampo AtendimentoFrmCampo(&m_stDadosAtendimentoFrmCampo, &m_vlDadosAtendimentoFrmCampo, &atdFrmValor);

            }
            XMLString::release(&valor);
        }

        //
        // nmBairro
        //
        if (valor=walkTree(domAux, "nmBairro", 0 ),valor)
        {
            memset(&m_stDadosAtendimentoFrm, 0, sizeof(m_stDadosAtendimentoFrm));
            memset(&m_vlDadosAtendimentoFrm,-1, sizeof(m_vlDadosAtendimentoFrm));

            // Pega o idCampo
            m_stDadosAtendimentoFrm.idCampo=176;
            m_vlDadosAtendimentoFrm.idCampo=1;

            m_stDadosAtendimentoFrm.idAtendimento=idAtendimento.ToLong();
            m_vlDadosAtendimentoFrm.idAtendimento=1;

            m_stDadosAtendimentoFrm.idUsuarioAlteracao=User.ToInt();
            m_vlDadosAtendimentoFrm.idUsuarioAlteracao=1;

            strcpy(m_stDadosAtendimentoFrm.dtUltimaAlteracao, sSysDate.c_str());
            m_vlDadosAtendimentoFrm.dtUltimaAlteracao=1;

            cWFAtendimentoFrm AtendimentoFrm(&m_stDadosAtendimentoFrm, &m_vlDadosAtendimentoFrm, &atdFrm);

            contador2 = 0;

            bool incluir = true;

            memset(&m_stDadosAtendimentoFrmCampo, 0, sizeof(m_stDadosAtendimentoFrmCampo));
            memset(&m_vlDadosAtendimentoFrmCampo,-1, sizeof(m_vlDadosAtendimentoFrmCampo));

            m_stDadosAtendimentoFrmCampo.idDominio=0;
            m_vlDadosAtendimentoFrmCampo.idDominio=-1;

            strcpy(m_stDadosAtendimentoFrmCampo.dsValor,p);
            m_vlDadosAtendimentoFrmCampo.dsValor=1;

            if ( m_stDadosAtendimentoFrmCampo.dsValor[0] == 0 )
            {
                incluir = false;
            }

            if (incluir)
            {
                m_stDadosAtendimentoFrmCampo.idUsuarioAlteracao=User.ToInt();
                m_vlDadosAtendimentoFrmCampo.idUsuarioAlteracao=1;
                strcpy(m_stDadosAtendimentoFrmCampo.dtUltimaAlteracao, sSysDate.c_str());
                m_vlDadosAtendimentoFrmCampo.dtUltimaAlteracao=1;


                m_stDadosAtendimentoFrmCampo.idAtendimentoFrm = idAtendimentoFrm;
                m_vlDadosAtendimentoFrmCampo.idAtendimentoFrm = 1;

                cWFAtendimentoFrmCampo AtendimentoFrmCampo(&m_stDadosAtendimentoFrmCampo, &m_vlDadosAtendimentoFrmCampo, &atdFrmValor);

            }
            XMLString::release(&valor);
        }

        //
        // nmMunicipio
        //
        if (valor=walkTree(domAux, "nmMunicipio", 0 ),valor)
        {
            memset(&m_stDadosAtendimentoFrm, 0, sizeof(m_stDadosAtendimentoFrm));
            memset(&m_vlDadosAtendimentoFrm,-1, sizeof(m_vlDadosAtendimentoFrm));

            // Pega o idCampo
            m_stDadosAtendimentoFrm.idCampo=180;
            m_vlDadosAtendimentoFrm.idCampo=1;

            m_stDadosAtendimentoFrm.idAtendimento=idAtendimento.ToLong();
            m_vlDadosAtendimentoFrm.idAtendimento=1;

            m_stDadosAtendimentoFrm.idUsuarioAlteracao=User.ToInt();
            m_vlDadosAtendimentoFrm.idUsuarioAlteracao=1;

            strcpy(m_stDadosAtendimentoFrm.dtUltimaAlteracao, sSysDate.c_str());
            m_vlDadosAtendimentoFrm.dtUltimaAlteracao=1;

            cWFAtendimentoFrm AtendimentoFrm(&m_stDadosAtendimentoFrm, &m_vlDadosAtendimentoFrm, &atdFrm);

            contador2 = 0;

            bool incluir = true;

            memset(&m_stDadosAtendimentoFrmCampo, 0, sizeof(m_stDadosAtendimentoFrmCampo));
            memset(&m_vlDadosAtendimentoFrmCampo,-1, sizeof(m_vlDadosAtendimentoFrmCampo));

            m_stDadosAtendimentoFrmCampo.idDominio=0;
            m_vlDadosAtendimentoFrmCampo.idDominio=-1;

            strcpy(m_stDadosAtendimentoFrmCampo.dsValor,p);
            m_vlDadosAtendimentoFrmCampo.dsValor=1;

            if ( m_stDadosAtendimentoFrmCampo.dsValor[0] == 0 )
            {
                incluir = false;
            }

            if (incluir)
            {
                m_stDadosAtendimentoFrmCampo.idUsuarioAlteracao=User.ToInt();
                m_vlDadosAtendimentoFrmCampo.idUsuarioAlteracao=1;
                strcpy(m_stDadosAtendimentoFrmCampo.dtUltimaAlteracao, sSysDate.c_str());
                m_vlDadosAtendimentoFrmCampo.dtUltimaAlteracao=1;


                m_stDadosAtendimentoFrmCampo.idAtendimentoFrm = idAtendimentoFrm;
                m_vlDadosAtendimentoFrmCampo.idAtendimentoFrm = 1;

                cWFAtendimentoFrmCampo AtendimentoFrmCampo(&m_stDadosAtendimentoFrmCampo, &m_vlDadosAtendimentoFrmCampo, &atdFrmValor);

            }
            XMLString::release(&valor);
        }
        
        //
        // nrEndereco
        //
        if (valor=walkTree(domAux, "nrEndereco", 0 ),valor)
        {
            memset(&m_stDadosAtendimentoFrm, 0, sizeof(m_stDadosAtendimentoFrm));
            memset(&m_vlDadosAtendimentoFrm,-1, sizeof(m_vlDadosAtendimentoFrm));

            // Pega o idCampo
            m_stDadosAtendimentoFrm.idCampo=1488;
            m_vlDadosAtendimentoFrm.idCampo=1;

            m_stDadosAtendimentoFrm.idAtendimento=idAtendimento.ToLong();
            m_vlDadosAtendimentoFrm.idAtendimento=1;

            m_stDadosAtendimentoFrm.idUsuarioAlteracao=User.ToInt();
            m_vlDadosAtendimentoFrm.idUsuarioAlteracao=1;

            strcpy(m_stDadosAtendimentoFrm.dtUltimaAlteracao, sSysDate.c_str());
            m_vlDadosAtendimentoFrm.dtUltimaAlteracao=1;

            cWFAtendimentoFrm AtendimentoFrm(&m_stDadosAtendimentoFrm, &m_vlDadosAtendimentoFrm, &atdFrm);

            contador2 = 0;

            bool incluir = true;

            memset(&m_stDadosAtendimentoFrmCampo, 0, sizeof(m_stDadosAtendimentoFrmCampo));
            memset(&m_vlDadosAtendimentoFrmCampo,-1, sizeof(m_vlDadosAtendimentoFrmCampo));

            m_stDadosAtendimentoFrmCampo.idDominio=0;
            m_vlDadosAtendimentoFrmCampo.idDominio=-1;

            strcpy(m_stDadosAtendimentoFrmCampo.dsValor,p);
            m_vlDadosAtendimentoFrmCampo.dsValor=1;

            if ( m_stDadosAtendimentoFrmCampo.dsValor[0] == 0 )
            {
                incluir = false;
            }

            if (incluir)
            {
                m_stDadosAtendimentoFrmCampo.idUsuarioAlteracao=User.ToInt();
                m_vlDadosAtendimentoFrmCampo.idUsuarioAlteracao=1;
                strcpy(m_stDadosAtendimentoFrmCampo.dtUltimaAlteracao, sSysDate.c_str());
                m_vlDadosAtendimentoFrmCampo.dtUltimaAlteracao=1;


                m_stDadosAtendimentoFrmCampo.idAtendimentoFrm = idAtendimentoFrm;
                m_vlDadosAtendimentoFrmCampo.idAtendimentoFrm = 1;

                cWFAtendimentoFrmCampo AtendimentoFrmCampo(&m_stDadosAtendimentoFrmCampo, &m_vlDadosAtendimentoFrmCampo, &atdFrmValor);

            }
            XMLString::release(&valor);
        }

        //
        // dsEnderecoComplemento
        //
        if (valor=walkTree(domAux, "dsEnderecoComplemento", 0 ),valor)
        {
            memset(&m_stDadosAtendimentoFrm, 0, sizeof(m_stDadosAtendimentoFrm));
            memset(&m_vlDadosAtendimentoFrm,-1, sizeof(m_vlDadosAtendimentoFrm));

            // Pega o idCampo
            m_stDadosAtendimentoFrm.idCampo=194;
            m_vlDadosAtendimentoFrm.idCampo=1;

            m_stDadosAtendimentoFrm.idAtendimento=idAtendimento.ToLong();
            m_vlDadosAtendimentoFrm.idAtendimento=1;

            m_stDadosAtendimentoFrm.idUsuarioAlteracao=User.ToInt();
            m_vlDadosAtendimentoFrm.idUsuarioAlteracao=1;

            strcpy(m_stDadosAtendimentoFrm.dtUltimaAlteracao, sSysDate.c_str());
            m_vlDadosAtendimentoFrm.dtUltimaAlteracao=1;

            cWFAtendimentoFrm AtendimentoFrm(&m_stDadosAtendimentoFrm, &m_vlDadosAtendimentoFrm, &atdFrm);

            contador2 = 0;

            bool incluir = true;

            memset(&m_stDadosAtendimentoFrmCampo, 0, sizeof(m_stDadosAtendimentoFrmCampo));
            memset(&m_vlDadosAtendimentoFrmCampo,-1, sizeof(m_vlDadosAtendimentoFrmCampo));

            m_stDadosAtendimentoFrmCampo.idDominio=0;
            m_vlDadosAtendimentoFrmCampo.idDominio=-1;

            strcpy(m_stDadosAtendimentoFrmCampo.dsValor,p);
            m_vlDadosAtendimentoFrmCampo.dsValor=1;

            if ( m_stDadosAtendimentoFrmCampo.dsValor[0] == 0 )
            {
                incluir = false;
            }

            if (incluir)
            {
                m_stDadosAtendimentoFrmCampo.idUsuarioAlteracao=User.ToInt();
                m_vlDadosAtendimentoFrmCampo.idUsuarioAlteracao=1;
                strcpy(m_stDadosAtendimentoFrmCampo.dtUltimaAlteracao, sSysDate.c_str());
                m_vlDadosAtendimentoFrmCampo.dtUltimaAlteracao=1;


                m_stDadosAtendimentoFrmCampo.idAtendimentoFrm = idAtendimentoFrm;
                m_vlDadosAtendimentoFrmCampo.idAtendimentoFrm = 1;

                cWFAtendimentoFrmCampo AtendimentoFrmCampo(&m_stDadosAtendimentoFrmCampo, &m_vlDadosAtendimentoFrmCampo, &atdFrmValor);

            }
            XMLString::release(&valor);
        }

        //
        // nrCEP
        //
        if (valor=walkTree(domAux, "nrCEP", 0 ),valor)
        {
            memset(&m_stDadosAtendimentoFrm, 0, sizeof(m_stDadosAtendimentoFrm));
            memset(&m_vlDadosAtendimentoFrm,-1, sizeof(m_vlDadosAtendimentoFrm));

            // Pega o idCampo
            m_stDadosAtendimentoFrm.idCampo=181;
            m_vlDadosAtendimentoFrm.idCampo=1;

            m_stDadosAtendimentoFrm.idAtendimento=idAtendimento.ToLong();
            m_vlDadosAtendimentoFrm.idAtendimento=1;

            m_stDadosAtendimentoFrm.idUsuarioAlteracao=User.ToInt();
            m_vlDadosAtendimentoFrm.idUsuarioAlteracao=1;

            strcpy(m_stDadosAtendimentoFrm.dtUltimaAlteracao, sSysDate.c_str());
            m_vlDadosAtendimentoFrm.dtUltimaAlteracao=1;

            cWFAtendimentoFrm AtendimentoFrm(&m_stDadosAtendimentoFrm, &m_vlDadosAtendimentoFrm, &atdFrm);

            contador2 = 0;

            bool incluir = true;

            memset(&m_stDadosAtendimentoFrmCampo, 0, sizeof(m_stDadosAtendimentoFrmCampo));
            memset(&m_vlDadosAtendimentoFrmCampo,-1, sizeof(m_vlDadosAtendimentoFrmCampo));

            m_stDadosAtendimentoFrmCampo.idDominio=0;
            m_vlDadosAtendimentoFrmCampo.idDominio=-1;

            strcpy(m_stDadosAtendimentoFrmCampo.dsValor,p);
            m_vlDadosAtendimentoFrmCampo.dsValor=1;

            if ( m_stDadosAtendimentoFrmCampo.dsValor[0] == 0 )
            {
                incluir = false;
            }

            if (incluir)
            {
                m_stDadosAtendimentoFrmCampo.idUsuarioAlteracao=User.ToInt();
                m_vlDadosAtendimentoFrmCampo.idUsuarioAlteracao=1;
                strcpy(m_stDadosAtendimentoFrmCampo.dtUltimaAlteracao, sSysDate.c_str());
                m_vlDadosAtendimentoFrmCampo.dtUltimaAlteracao=1;


                m_stDadosAtendimentoFrmCampo.idAtendimentoFrm = idAtendimentoFrm;
                m_vlDadosAtendimentoFrmCampo.idAtendimentoFrm = 1;

                cWFAtendimentoFrmCampo AtendimentoFrmCampo(&m_stDadosAtendimentoFrmCampo, &m_vlDadosAtendimentoFrmCampo, &atdFrmValor);

            }
            XMLString::release(&valor);
        }

        //
        // UFVO
        //
        if (domAux2= walkDOM( domAux, "UFVO", 0))
        {
            if (valor=walkTree(domAux2, "sgUF", 0 ),valor)
            {
                memset(&m_stDadosAtendimentoFrm, 0, sizeof(m_stDadosAtendimentoFrm));
                memset(&m_vlDadosAtendimentoFrm,-1, sizeof(m_vlDadosAtendimentoFrm));

                // Pega o idCampo
                m_stDadosAtendimentoFrm.idCampo=179;
                m_vlDadosAtendimentoFrm.idCampo=1;

                m_stDadosAtendimentoFrm.idAtendimento=idAtendimento.ToLong();
                m_vlDadosAtendimentoFrm.idAtendimento=1;

                m_stDadosAtendimentoFrm.idUsuarioAlteracao=User.ToInt();
                m_vlDadosAtendimentoFrm.idUsuarioAlteracao=1;

                strcpy(m_stDadosAtendimentoFrm.dtUltimaAlteracao, sSysDate.c_str());
                m_vlDadosAtendimentoFrm.dtUltimaAlteracao=1;

                cWFAtendimentoFrm AtendimentoFrm(&m_stDadosAtendimentoFrm, &m_vlDadosAtendimentoFrm, &atdFrm);

                contador2 = 0;

                bool incluir = true;

                memset(&m_stDadosAtendimentoFrmCampo, 0, sizeof(m_stDadosAtendimentoFrmCampo));
                memset(&m_vlDadosAtendimentoFrmCampo,-1, sizeof(m_vlDadosAtendimentoFrmCampo));

                m_stDadosAtendimentoFrmCampo.idDominio=0;
                m_vlDadosAtendimentoFrmCampo.idDominio=-1;

                strcpy(m_stDadosAtendimentoFrmCampo.dsValor,p);
                m_vlDadosAtendimentoFrmCampo.dsValor=1;

                if ( m_stDadosAtendimentoFrmCampo.dsValor[0] == 0 )
                {
                    incluir = false;
                }

                if (incluir)
                {
                    m_stDadosAtendimentoFrmCampo.idUsuarioAlteracao=User.ToInt();
                    m_vlDadosAtendimentoFrmCampo.idUsuarioAlteracao=1;
                    strcpy(m_stDadosAtendimentoFrmCampo.dtUltimaAlteracao, sSysDate.c_str());
                    m_vlDadosAtendimentoFrmCampo.dtUltimaAlteracao=1;

                    m_stDadosAtendimentoFrmCampo.idAtendimentoFrm = idAtendimentoFrm;
                    m_vlDadosAtendimentoFrmCampo.idAtendimentoFrm = 1;

                    cWFAtendimentoFrmCampo AtendimentoFrmCampo(&m_stDadosAtendimentoFrmCampo, &m_vlDadosAtendimentoFrmCampo, &atdFrmValor);

                }
                XMLString::release(&valor);
            }
        }

        //
        // PaisVO
        //
        if (domAux2= walkDOM( domAux, "PaisVO", 0))
        {
            if (valor=walkTree(domAux2, "nmPais", 0 ),valor)
            {
                memset(&m_stDadosAtendimentoFrm, 0, sizeof(m_stDadosAtendimentoFrm));
                memset(&m_vlDadosAtendimentoFrm,-1, sizeof(m_vlDadosAtendimentoFrm));

                // Pega o idCampo
                m_stDadosAtendimentoFrm.idCampo=234;
                m_vlDadosAtendimentoFrm.idCampo=1;

                m_stDadosAtendimentoFrm.idAtendimento=idAtendimento.ToLong();
                m_vlDadosAtendimentoFrm.idAtendimento=1;

                m_stDadosAtendimentoFrm.idUsuarioAlteracao=User.ToInt();
                m_vlDadosAtendimentoFrm.idUsuarioAlteracao=1;

                strcpy(m_stDadosAtendimentoFrm.dtUltimaAlteracao, sSysDate.c_str());
                m_vlDadosAtendimentoFrm.dtUltimaAlteracao=1;

                cWFAtendimentoFrm AtendimentoFrm(&m_stDadosAtendimentoFrm, &m_vlDadosAtendimentoFrm, &atdFrm);

                contador2 = 0;

                bool incluir = true;

                memset(&m_stDadosAtendimentoFrmCampo, 0, sizeof(m_stDadosAtendimentoFrmCampo));
                memset(&m_vlDadosAtendimentoFrmCampo,-1, sizeof(m_vlDadosAtendimentoFrmCampo));

                m_stDadosAtendimentoFrmCampo.idDominio=0;
                m_vlDadosAtendimentoFrmCampo.idDominio=-1;

                strcpy(m_stDadosAtendimentoFrmCampo.dsValor,p);
                m_vlDadosAtendimentoFrmCampo.dsValor=1;

                if ( m_stDadosAtendimentoFrmCampo.dsValor[0] == 0 )
                {
                    incluir = false;
                }

                if (incluir)
                {
                    m_stDadosAtendimentoFrmCampo.idUsuarioAlteracao=User.ToInt();
                    m_vlDadosAtendimentoFrmCampo.idUsuarioAlteracao=1;
                    strcpy(m_stDadosAtendimentoFrmCampo.dtUltimaAlteracao, sSysDate.c_str());
                    m_vlDadosAtendimentoFrmCampo.dtUltimaAlteracao=1;

                    m_stDadosAtendimentoFrmCampo.idAtendimentoFrm = idAtendimentoFrm;
                    m_vlDadosAtendimentoFrmCampo.idAtendimentoFrm = 1;

                    cWFAtendimentoFrmCampo AtendimentoFrmCampo(&m_stDadosAtendimentoFrmCampo, &m_vlDadosAtendimentoFrmCampo, &atdFrmValor);

                }
                XMLString::release(&valor);
            }
        }
    }


    ULOG_END("cWF_Acao::inserirFormulario()");
}

//------------------------------------------------------------------------------------------------------------------
// Inserir Agendamento no Atendimento
//------------------------------------------------------------------------------------------------------------------
void cWF_Acao::inserirAtendimentoAgendamento() 
{
    ULOG_START("cWF_Acao::inserirAtendimentoAgendamento()");

    struct st_AtendimentoAgendamento dados;
    struct st_vlAtendimentoAgendamento status; 

    memset ( &dados  , 0, sizeof( st_AtendimentoAgendamento  )); 
    memset ( &status ,-1, sizeof( st_vlAtendimentoAgendamento ));

    dados.idAndamento = idAndamento;
    dados.idAtendimento = idAtendimento.ToLong(); 
    dados.idGrupo = idGrupo.ToInt(); 
    strcpy( dados.dtAtendimentoAgendamento, sSysDate.c_str() ); 

    status.idAndamento = 1;
    status.idAtendimento = 1;
    status.dtAtendimentoAgendamento = 1;

    ULOG("idGrupo=%d", dados.idGrupo );
    ULOG("idAndamento=%ld", dados.idAndamento );
    ULOG("idAtendimento=%d",dados.idAtendimento  );
    ULOG("dtAtendimentoAgendamento='%s'", dados.dtAtendimentoAgendamento);

    cWFAtendimentoAgendamento oatdAg(&dados,&status,&oxml);
    oatdAg.incluir();

    ULOG_END("cWF_Acao::inserirAtendimentoAgendamento()");
}

//------------------------------------------------------------------------------------------------------------------
// Insere ou faz update em ATENDIMENTO.ATENDIMENTOGRUPODEVOLUCAO
//------------------------------------------------------------------------------------------------------------------
void cWF_Acao::atualizarAtendimentoGrupoReceptor(int idGrupo)
{
    ULOG_START("cWF_Acao::atualizarAtendimentoGrupoReceptor()");

    cWFAtendimentoGrupoDevolucao AtendimentoGrupoDevolucao;

    AtendimentoGrupoDevolucao.atualizar(idAtendimento.ToLong(),idGrupo,User.ToInt());

    ULOG_END("cWF_Acao::atualizarAtendimentoGrupoReceptor()");
}

//------------------------------------------------------------------------------------------------------------------
// Inclui Grupo de Recepção (1) (ATENDIMENTO.ATENDIMENTOGRUPODEVOLUCAO)
//------------------------------------------------------------------------------------------------------------------
void cWF_Acao::inserirAtendimentoGrupoReceptor(int idGrupo)
{
    ULOG_START("cWF_Acao::inserirAtendimentoGrupoReceptor()");

    st_AtendimentoGrupoDevolucao    dados; 
    st_vlAtendimentoGrupoDevolucao  status;
    XMLGen xmlObtemAtGrAt;

    memset ( &dados,   0, sizeof(st_AtendimentoGrupoDevolucao)); 
    memset ( &status, -1, sizeof(st_vlAtendimentoGrupoDevolucao)); 

    dados.idAtendimento = idAtendimento.ToLong(); 
    dados.idGrupo = idGrupo; 

    status.idAtendimento = 1;
    status.idGrupo = 1;

    cWFAtendimentoGrupoDevolucao  AtendimentoGrupoDevolucao(&dados, &status, &xmlObtemAtGrAt);

    AtendimentoGrupoDevolucao.incluir(); 

    ULOG_END("cWF_Acao::inserirAtendimentoGrupoReceptor()");
}

//------------------------------------------------------------------------------------------------------------------
// Inclui Grupo de Recepcao (2) (ATENDIMENTO.ATENDIMENTOGRUPODEVOLUCAO)
//------------------------------------------------------------------------------------------------------------------
void cWF_Acao::inserirAtendimentoGrupoReceptor()
{
    ULOG_START("cWF_Acao::inserirAtendimentoGrupoReceptor()");

    inserirAtendimentoGrupoReceptor(idGrupoAtual);

    ULOG_END("cWF_Acao::inserirAtendimentoGrupoReceptor()");
}

//------------------------------------------------------------------------------------------------------------------
// Inclui Usuario de Recepcao
//------------------------------------------------------------------------------------------------------------------
void cWF_Acao::inserirAtendimentoUsuarioReceptor()
{
    ULOG_START("cWF_Acao::inserirAtendimentoUsuarioReceptor()");

    st_AtendimentoUsuarioDevolucao    dados; 
    st_vlAtendimentoUsuarioDevolucao  status;
    XMLGen xmlObtemAtGrAt;

    memset ( &dados,   0, sizeof(st_AtendimentoUsuarioDevolucao)); 
    memset ( &status, -1, sizeof(st_vlAtendimentoUsuarioDevolucao)); 

    dados.idAtendimento = idAtendimento.ToLong(); 
    dados.idPessoaUsuario = idUsuarioAtendimento; 

    status.idAtendimento = 1;
    status.idPessoaUsuario = 1;

    cWFAtendimentoUsuarioDevolucao  AtendimentoUsuarioDevolucao(&dados, &status, &xmlObtemAtGrAt);

    AtendimentoUsuarioDevolucao.incluir(); 

    ULOG_END("cWF_Acao::inserirAtendimentoUsuarioReceptor()");
}

//------------------------------------------------------------------------------------------------------------------
// Inclui Marcacao Suspeito
//------------------------------------------------------------------------------------------------------------------
void cWF_Acao::inserirAtendimentoSuspeito()
{
    ULOG_START("cWF_Acao::inserirAtendimentoSuspeito()");

    st_AtendimentoSuspeito m_stDadosAtenSuspeito;
    st_vlAtendimentoSuspeito m_vlDadosAtenSuspeito;
    XMLGen ExcSusp;

    memset(&m_stDadosAtenSuspeito, 0, sizeof(m_stDadosAtenSuspeito));
    memset(&m_vlDadosAtenSuspeito,-1, sizeof(m_vlDadosAtenSuspeito));

    m_stDadosAtenSuspeito.idAtendimento=idAtendimento.ToLong();
    m_vlDadosAtenSuspeito.idAtendimento=1;
    m_stDadosAtenSuspeito.idPessoaUsuario = User.ToInt();
    m_vlDadosAtenSuspeito.idPessoaUsuario = 1;
    m_stDadosAtenSuspeito.idUsuarioAlteracao = User.ToInt();
    m_vlDadosAtenSuspeito.idUsuarioAlteracao = 1;
    m_stDadosAtenSuspeito.idMotivo = idMotivo.ToInt();
    m_vlDadosAtenSuspeito.idMotivo = 1;

    cWFAtendimentoSuspeito AtendimentoSuspeito(&m_stDadosAtenSuspeito, &m_vlDadosAtenSuspeito, &ExcSusp);
    AtendimentoSuspeito.incluir();

    ULOG_END("cWF_Acao::inserirAtendimentoSuspeito()");
}


//------------------------------------------------------------------------------------------------------------------
// verifica se um processo pode ser cancelado
//------------------------------------------------------------------------------------------------------------------
bool cWF_Acao::podeCancelarProcesso() 
{
    ULOG_START("cWF_Acao::podeCancelarProcesso()");

    bool podeCancelar= false ;
    //st_AtdTratamentoCri dados; 
    //st_vlAtdTratamentoCri status;
    //XMLGen xmlObtemUsrAt;
    //int idPessoaUsuarioCri;

    //memset ( &dados,   0, sizeof(st_AtdTratamentoCri)); 
    //memset ( &status, -1, sizeof(st_vlAtdTratamentoCri)); 

    //dados.idAtendimento = idAtendimento.ToLong(); 
    //status.idAtendimento = 1;

    //cWFAtdTratamentoCri cwfatdtratamentocri(&dados, &status, &xmlObtemUsrAt);
    //ULOG("idAtendimento: %d", dados.idAtendimento);

    // Para poder cancelar um processo o usuário web precisa necessariamento ser
    // o responsável pelo processo ou ser supervisor do grupo do processo.

    getAtendimentoUsuarioAtual();

    if ( idUsuarioAtendimento && idUsuarioAtendimento == User.ToInt() )
    {
        podeCancelar = true;
    }
    else if ( usuarioIsSupervisor(idGrupoAtual) )
    {
        podeCancelar = true;
    }

    //if ( cwfatdtratamentocri.getGrupoCri() )
    //{
    //    idPessoaUsuarioCri = cwfatdtratamentocri.getUsuarioAtualCri(dados.idAtendimento);
    //    if ( idPessoaUsuarioCri && idPessoaUsuarioCri == User.ToLong() )
    //    {
    //        podeCancelar = false ;
    //    }
    //}   

    ULOG("idUsuarioAtendimento: %d", idUsuarioAtendimento);
    ULOG("User.ToInt(): %d", User.ToInt());
    ULOG("idGrupoAtual: %d", idGrupoAtual);
    ULOG("podeCancelar: %s", podeCancelar?"SIM":"NAO");

    ULOG_END("cWF_Acao::podeCancelarProcesso()");

    return podeCancelar;
}

//------------------------------------------------------------------------------------------------------------------
// Adquirir para Resposta ao Cliente
//------------------------------------------------------------------------------------------------------------------

void cWF_Acao::adquirirContatoPrevio()
{
    ULOG_START("cWF_Acao::adquirirContatoPrevio()");
    getAtendimento() ;
    getAtendimentoUsuarioAtual();
    struct st_AtendimentoCPFila  st_Dados;  
    struct st_vlAtendimentoCPFila st_Status;
    // struct st_AtdCprevio st_DadosUF ;

    memset (&st_Dados , 0, sizeof(struct st_AtendimentoCPFila)); 
    memset (&st_Status , -1, sizeof(struct st_vlAtendimentoCPFila)); 
    // memset (&st_DadosUF , 0, sizeof(struct st_AtdCprevio)); 

    st_Dados.idAtendimento=idAtendimento.ToLong();
    st_Dados.idGrupoOrigem=idGrupoAtual;
    st_Dados.idPessoaUsuarioOrigem=idUsuarioAtendimento;
    st_Dados.inContatoPrevioRealizado=0;
    st_Dados.nrTentativas=0;
    strcpy(st_Dados.dtCalculada,dtPrazoFinalCPrevio);
    st_Dados.idUsuarioAlteracao = User.ToInt();
    strcpy(st_Dados.dtUltimaAlteracao,sSysDate.c_str());

    idGrupoCprevio = 0;

    cWFAtendimentoCPFila oWFAtendimentoCPFila(&st_Dados,&st_Status);
    
    idGrupoCprevio = oWFAtendimentoCPFila.GetGruposAssociadosRC( st_Dados.idUsuarioAlteracao );

    // if ( oWFAtendimentoCPFila.ObterAtdCPrevio(st_Dados.idAtendimento,&st_DadosUF) )
    // {
    //     oWFAtendimentoCPFila.obterGrupoRCConfigurado(st_Dados.idAtendimento,idContato,idCanal
    //                                                 ,idProcedencia ,idSegmentacao ,idTipoCarteira
    //                                                 ,st_DadosUF.idUFOperadora,idGrupoCprevio
    //                                                 ,User.ToInt());
    // }

    //  verifica se este atendimento ja existe na fila
    if ( oWFAtendimentoCPFila.ExistAtendimento(idAtendimento.ToLong()) == false )
    {
        oWFAtendimentoCPFila.incluir();
    }
    else
    {
        oWFAtendimentoCPFila.alterar(&st_Dados);
    }    

    inCriAtd = 0;
    //alterarAtendimentoGrupoAtual(idGrupoAtual);
    alterarAtendimentoGrupoAtual(idGrupoCprevio);
    idAgrEstTPrFt = idAgrEstTPrAt;
    ULOG_END("cWF_Acao::adquirirContatoPrevio()  cWF_AdquirirBko::adquirirContatoPrevio()");
}


//------------------------------------------------------------------------------------------------------------------
// Ação de encerramento de processos usada pelas classes cWF_CERRAMEBKO e cWF_CERRAMERET respectivas aos
// serviços de encerramento de processos com retorno por grupo bko e retorno por grupo de retorno.
//------------------------------------------------------------------------------------------------------------------
void cWF_Acao::Encerrar()
{
    ULOG_START("cWF_Acao::Encerrar()");

    getAtendimentoGrupoAtual();

    if ( !idGrupoAtual )
    {
        SetMessage("\nGrupo atual do processo inválido. Favor informar a gestão de sistemas.","M");

        xml_g->closeTag();

        return;
    }

    bool isFechamento = proCIsEstadoFechamento(idAgrEstTPrFt.ToInt());

    if ( isFechamento )
    {
        getAtendimento();
        Fechar();
    }
    else
    {
        if ( !inCri )
        {
            if ( TP_RET_COM_RET_GRP_RET == idTipoRetornoContato )
            {
                getAtendimentoPessoa();
                getAtendimentoLinha();

                if ( getGrupoRetorno() == false )
                {
                    SetMessage( "\nProcesso aberto para 'RETORNO POR GRUPO DE RETORNO'."
                                "\nFavor informar ao administrador para parametrizar um Grupo de Retorno."
                                , "M" ); 

                    xml_g->closeTag();

                    return;
                }
            }
            // Vai alterar o idTipoRetornoContato em ATENDIMENTO.ATENDIMENTO
            // quando for fazer o update nesta tabela ao final desta ação.
            alterarTipoRetornoContato = true;
        }

        getAtendimentoUsuarioAtual();

        if ( preProcessarRelEncFech(ACAO_ENCERRAMENTO) == false )
        { // Incidência 2331 PréProd - Fev/2006
            SetMessage( "Falha na atualização dos relatórios on-line", "N" ); 

            xml_g->closeTag();

            return;
        }

        if ( idUsuarioAtendimento > 0 )
        {
            getAndamentoTransferencia();

            if (idAndamentoTrans > 0)
            {
                alterarAndamentoTransferencia();
            }

            if ( TP_RET_COM_RET_GRP_RET == idTipoRetornoContato )
            { // Processo volta pra fila se estiver sendo encerrado para grupo de retorno
                removeAtendimentoUsuarioAtual();
            }

            removeAtendimentoUsuarioReceptor(); // ATENDIMENTOUSUARIODEVOLUCAO
            inserirAtendimentoUsuarioReceptor();
        }

        if ( TP_RET_COM_RET_GRP_RET == idTipoRetornoContato && idGrupoAtual != idGrupo.ToInt() )
        { // Se o processo for de CRI ou se o tipo de retorno for RETORNO POR GRUPO BKO
          // o retorno é dado pelo mesmo grupo onde o processo estiver no momento.
            alterarAtendimentoGrupoAtual(idGrupo.ToInt());
        }

        // ==> Incidência 3529 Hom.Vivo
        // Processo não estava sendo retornado ao grupo origem com a ação
        // DEVOLVER BKO - Mar/2006 - Cassio
        atualizarAtendimentoGrupoReceptor(idGrupoAtual); // ATENDIMENTOGRUPODEVOLUCAO
        // <== Incidência 3529

        inserirAndamento(idGrupoAtual);

        // Registra o histórico da baixa se dados forem fornecidos ...
        if ( idBaixa.Length() )
        {
            inserirAtendimentoBaixaHistorico();
        }

        // Se dados de baixa foram fornecidos, registra
        if ( idBaixaMensagem && idBaixa.Length() )
        {
            //getBaixaMensagem();
            inserirAtendimentoBaixaMensagem();
        }

        getAtendimentoBaixaAtual();
        if (idBaixaAtendimento > 0)
        {
            alterarAtendimentoBaixaAtual();
        }
        else
        {
            inserirAtendimentoBaixaAtual();
        }

        removeAtendimentoSuspeito();
        removeCancelamentoSolicitado();

        // incidência 3325 CRI - Processos EM RETORNO devem aparecer no inbox do CRI
        // removeTratamentoCri();

        // incidência 3569
        // id fase nao deve ser 3 e sim 2 idFase = 3;
        // por observacao identifique que  idGrupo.ToInt() e o grupo de retorno neste caso
        // o correto e substituir por idGrupoAtual que e o grupo atual do processo na fase 2
        // inserirAtendimentoNivel(idGrupo.ToInt());
        idFase = TRATAMENTO;
        inserirAtendimentoNivel(idGrupoAtual);

        idFaseAtendimento = RETORNO;
        alterarAtendimento();

        alterarAndamentoAtual();
        inserirAndamentoObservacao();
        inserirFormulario();

        DecrementarPendentes();

        getFormaRetornoAtendimento(); // envia mensagem de e-mail/sms ao cliente
    } // if ( !isFechamento )

    ULOG_END("cWF_Acao::Encerrar()");
}

//------------------------------------------------------------------------------------------------------------------
// Ação de encerramento de processos usada pelas classes cWF_CERRAMEBKO e cWF_CERRAMERET cWFFECHAMENO respectivas aos
// serviços de encerramento de processos com retorno por grupo bko e retorno por grupo de retorno e fechamento
//------------------------------------------------------------------------------------------------------------------
bool cWF_Acao::Fechar()
{
    ULOG_START("cWF_Acao::Fechar()");

    if ( idGrupoAtual == 0 )
    {
        getAtendimentoGrupoAtual();
    }

    if ( idGrupoAtual == 0 )
    {
        SetMessage( "\nGrupo atual do processo não encontrado.","M"); 

        xml_g->closeTag();

        ULOG_END("cWF_Acao::Fechar() ==> GRUPO ATUAL NÃO ENCONTRADO");

        return false;
    }

    idGrupo = idGrupoAtual;

    if ( preProcessarRelEncFech(ACAO_FECHAMENTO) == false ) // Incidência 2331 PréProd - Fev/2006
    {
        SetMessage( "Falha na atualização dos relatórios on-line", "N" ); 

        xml_g->closeTag();

        ULOG_END("cWF_Acao::Fechar() ==> FALHA ATUALIZAÇÃO RELATÓRIOS");

        return false;
    }

    if (inCri == 1)
    {
        getAtendimentoGrupoBko();
        if (qtIntercambios == 0)
        {
            inAssociado = 1;
            idGrupo = idGrupoAtual;
            inserirAtendimentoGrupoBko();
        }

        getTratamentoUsuarioCri();
    }

    // ==> Remodelagem do Atendimento, Março, 2007 - Cassio 
    // O último usuário responsável pelo processo fica registrado no próprio processo 
    // a partir da implantação do modelo novo.
    // getAtendimentoUsuarioAtual();
    // if ( idUsuarioAtendimento > 0 ) // Obtendo usuario devolucao 
    // {
    //     getAndamentoTransferencia();
    //     if (idAndamentoTrans > 0)
    //     {
    //         alterarAndamentoTransferencia();
    //     }
    //     removeAtendimentoUsuarioAtual();
    // }

    // Processos sem usuário atual devem registrar o usuário que esta fechando o processo
    // como o usuário atual (responsável) do processo.
    atualizarAtendimentoUsuarioAtual(User.ToInt(),false);
    removeAtendimentoPausa(); // ATENDIMENTO.ATENDIMENTOPAUSA
    //
    // ==> Remodelagem do Atendimento, Março, 2007 - Cassio 
    // O último grupo pelo qual o processo passou fica registrado mesmo após o fechamento
    // para com isso se evitar uma pesquisa em andamento.andamento e obter ganho de
    // performance nas pesquisas de fila.
    // removeAtendimentoGrupoAtual();
    //
    removeAtendimentoGrupoReceptor();
    removeAtendimentoUsuarioReceptor();
    removeCancelamentoSolicitado();
    removeAtendimentoSuspeito();
    removeTratamentoCri();

    inserirAtendimentoNivel(true /*concluir=true*/);

    inserirAndamento();
    alterarAndamentoAtual();
    inserirAndamentoObservacao();
    inserirAndamentoMotivo();
    inserirAtendimentoFechamento();
    DecrementarPendentes();
    inserirFormulario();

    // se estiver fechando a partir de janela CRI e processo não estava nas mãos
    // de um usuário CRI, envia mensagem ao BKO que estava com o processo sobre
    // a ação de fechamento
    if ( inCri && idUsuarioCri == 0 && idUsuarioAtendimento > 0 )
    {
        inserirAndamentoMensagem(idUsuarioAtendimento,User.ToInt()
                                ,INORIGEM_ANDAMENTOMENSAGEM_CRI);
    }

    // esta linha foi comentada, por solicitacao da Tatiana, esta acao nao pode ocorrer neste momento
    // foi transferido para a acao  encerrar ???
    // getFormaRetornoAtendimento(); // envia mensagem de fechamento ao cliente

    ULOG_END("cWF_Acao::Fechar()");

    return true;
}



//------------------------------------------------------------------------------------------------------------------
// Ação de encerramento de processos tipo Meu Cliente 2
//------------------------------------------------------------------------------------------------------------------
bool cWF_Acao::EncerrarMC2()
{
    if ( idGrupoAtual == 0 )
    {
        getAtendimentoGrupoAtual();
    }

    if ( idGrupoAtual == 0 )
    {
        SetMessage( "\nGrupo atual do processo não encontrado.","M"); 

        xml_g->closeTag();

        ULOG_END("cWF_Acao::DevolverBKO() ==> GRUPO ATUAL NÃO ENCONTRADO");

        return false;
    }

    if ( preProcessarRelEncFech(ACAO_ENCERRAMENTO) == false )
    { // Incidência 2331 PréProd - Fev/2006
        SetMessage( "Falha na atualização dos relatórios on-line", "N" ); 

        xml_g->closeTag();

        return false;
    }

    inserirAndamento(idGrupoAtual);

    // Registra o histórico da baixa se dados forem fornecidos ...
    if ( idBaixa.Length() )
    {
        inserirAtendimentoBaixaHistorico();
    }

    // Se dados de baixa foram fornecidos, registra
    if ( idBaixaMensagem && idBaixa.Length() )
    {
        //getBaixaMensagem();
        inserirAtendimentoBaixaMensagem();
    }

    getAtendimentoBaixaAtual();
    if (idBaixaAtendimento > 0)
    {
        alterarAtendimentoBaixaAtual();
    }
    else
    {
        inserirAtendimentoBaixaAtual();
    }

    removeAtendimentoSuspeito();
    removeCancelamentoSolicitado();

    idFase = TRATAMENTO;
    inserirAtendimentoNivel(idGrupoAtual);

    idFaseAtendimento = RETORNO;
    alterarAtendimento();

    //inserirAndamentoObservacao();
    inserirFormulario();

    DecrementarPendentes();

    getFormaRetornoAtendimento(); // envia mensagem de e-mail/sms ao cliente

    return true;
}

//------------------------------------------------------------------------------------------------------------------
// Ação de devolução de processos a analista BKO usada pelas classes DBKOACAO e DTRACAO.
//------------------------------------------------------------------------------------------------------------------
bool cWF_Acao::DevolverBKO(bool &retornarParaFila)
{
    ULOG_START("cWF_Acao::DevolverBKO()");

    if ( idGrupoAtual == 0 )
    {
        getAtendimentoGrupoAtual();
    }

    int idGrupoDevolucao;

    if ( idAtividade.ToInt() == DEVOLVER_ANALISTA_MEU_CLIENTE )
    {
        idGrupo = idGrupoDevolucao = idGrupoMC.ToInt();
        UsuarioAtual = idPessoaUsuarioMC;
    }
    else
    {
        getAtendimentoGrupoDevolucao(); // ATENDIMENTOGRUPODEVOLUCAO
        getAtendimentoUsuarioDevolucao();

        idGrupoDevolucao = idGrupo.ToInt();
    }

    ULOG("  Grupo para devolução=%d",idGrupo.ToInt());
    ULOG("Usuario para devolução=%d",UsuarioAtual.ToInt());

    if ( idGrupo.ToInt() == 0 )
    {
        SetMessage( "\nGrupo de devolução não encontrado.","N"); 

        xml_g->closeTag();

        ULOG_END("cWF_Acao::DevolverBKO() ==> GRUPO DEVOLUÇÃO NÃO ENCONTRADO");

        return false;
    }

    if (UsuarioAtual.ToInt() > 0)
    {
        getStatusUsuario(); // Incidência 3534 pede apenas verificação de status
        ULOG("idStatusUsuario=[%d]",idStatusUsuario);

        if ( USUARIO_ATIVO == idStatusUsuario )
        {
            // =============================================================
            // incidência 2939 - TD Sanity Skill Vivo - Novembro/2005 ==>
            // -------------------------------------------------------------
            // Ficou fechado que o devolver bko não verificará o skill do
            // usuário, conforme documentação fechada entre as equipes
            // Indra e Vivo aos 18/09/2006.
            // 
            // if ( proCUsuarioTemSkill( UsuarioAtual.ToInt(),idGrupo.ToInt()) )
            // { // Se usuário tem skill para o grupo de destino do processo ...
            //     retornarParaFila = 
            //         proCUsuarioTemAcesso(UsuarioAtual.ToInt()
            //                             ,idGrupo.ToInt()
            //                             ,idAtendimento.ToLong()) ? false:true;
            // }  
            // <== incidência 2939 - TD Sanity Skill Vivo - Novembro/2005
            //

            if ( strcmp(szSgFluxoAtendimento,"MC1") )
            { // se processo for MeuCliente1 ele fica fixo com o mesmo atendente.
                // Verifica se o usuário devolução ainda esta casado ao grupo de devolução
                retornarParaFila = 
                    ExisteGrupoUsuario(idGrupo.ToInt(),UsuarioAtual.ToInt())?false:true;
            }
        }
        else
        {
            retornarParaFila = true; // usuário inativo não pode receber processo
            ULOG("usuário esta INATIVO.");
        }
    }
    else
    {   // se não tem usuário para devolver relacionado então
        retornarParaFila = true; // irá fazer limpeza de associações com inbox de usuário 
        ULOG("Sem usuário atual relacionado.");
    }

    ULOG("retornarParaFila=[%d]",retornarParaFila);

    if ( retornarParaFila )
    {
        removeAtendimentoUsuarioAtual();
        removeAtendimentoPausa();
        removeTratamentoCri();
        removeAtendimentoCri();

        //if ( strcmp(szSgFluxoAtendimento,"MC1")==0 || strcmp(szSgFluxoAtendimento,"MC2")==0 )
        //{ // o usuário mc1 esta inativo, então remove instrução para devolver pra ele no futuro.
        //    removeAtendimentoGrupoReceptor(); // ATENDIMENTOGRUPODEVOLUCAO
        //    removeAtendimentoUsuarioReceptor(); // ATENDIMENTOUSUARIODEVOLUCAO
        //}

        if ( idAtividade.ToInt() != DEVOLVER_ANALISTA_MEU_CLIENTE )
        {
            // Se processo estiver em RETORNO e estiver retornando a TRATAMENTO
            // determina qual o estado/sub-estado respectivo
            if ( (  idAgrEstTPrAt.ToInt() == 6  || idAgrEstTPrAt.ToInt() == 7
                 || idAgrEstTPrAt.ToInt() == 19 || idAgrEstTPrAt.ToInt() == 20) &&
                 ProximoEstadoRetornoSimNao() == false )
            {
                switch ( idAgrEstTPrFt.ToInt() )
                {
                    case 3:
                        idAgrEstTPrFt = 2;
                    break;
                    case 16:
                        idAgrEstTPrFt = 15;
                    break;
                }
            }
        }
    }
    else
    { // se não vai retornar para a fila, apenas troca de usuário atual
        if ( existeAtendimentoUsuarioAtual() ) 
        {
            alterarAtendimentoUsuarioAtual(); // ATENDIMENTO.ATENDIMENTOUSUARIOATUAL
            getAndamentoTransferencia(); // ATENDIMENTO.ANDAMENTOTRANSFERENCIA
            if (idAndamentoTrans > 0)
            {
                alterarAndamentoTransferencia();
            }
        }
        else
        {
            inserirAtendimentoUsuarioAtual(); // ATENDIMENTO.ATENDIMENTOUSUARIOATUAL
        }

        // Se o processo estiver em pausa o estado/sub-estado deve ser respectivo
        if ( (idAgrEstTPrFt.ToInt() == 3 || idAgrEstTPrFt.ToInt() == 16) &&
             ProcessoEmPausaSimNao() == true )
        {
            switch ( idAgrEstTPrFt.ToInt() )
            {
                case 3:
                    idAgrEstTPrFt = 5;
                break;
                case 16:
                    idAgrEstTPrFt = 18;
                break;
            }
        }
    }

    if ( !inCri && (idGrupoAtual != idGrupoDevolucao) )
    {
        alterarAtendimentoGrupoAtual(idGrupoDevolucao);
    }

    if ( idAtividade.ToInt() == DEVOLVER_ANALISTA_MEU_CLIENTE )
    {
        idUsuarioAtendimento = User.ToInt();
        inserirAtendimentoUsuarioReceptor();
        atualizarAtendimentoGrupoReceptor(idGrupoAtual); // ATENDIMENTOGRUPODEVOLUCAO
    }
    else
    {
        idFaseAtendimento = TRATAMENTO;
        alterarAtendimento();
        removeAtendimentoGrupoReceptor(); // ATENDIMENTOGRUPODEVOLUCAO
        removeAtendimentoUsuarioReceptor(); // ATENDIMENTOUSUARIODEVOLUCAO
        removeCancelamentoSolicitado();
        removeAtendimentoBaixaAtual();
        removeAtendimentoSuspeito(); // Eliminando Marcacao Suspeito
        inserirAndamento(idGrupoAtual);
    }

    inserirAndamentoObservacao();
    inserirAndamentoMotivo();

    ULOG_END("cWF_Acao::DevolverBKO()");

    return true;
}

//------------------------------------------------------------------------------------------------------------------
// Ação de devolução de processos no estado de TRATAMENTO EM MASSA
// a analista BKO usada pelas classes DBKOACAO e DTRACAO.
//------------------------------------------------------------------------------------------------------------------
bool cWF_Acao::ReanalisarBKO(bool &retornarParaFila)
{
    ULOG_START("cWF_Acao::ReanalisarBKO()");

    getAtendimentoUsuarioAtual();

    getAtendimentoGrupoAtual();

    if ( idUsuarioAtendimento > 0 )
    {
        // Incidência 3534 pede apenas verificação de status
        getStatusUsuario(idUsuarioAtendimento);

        ULOG("idStatusUsuario=[%d]",idStatusUsuario);

        if ( USUARIO_ATIVO == idStatusUsuario )
        {
            // =============================================================
            // incidência 2939 - TD Sanity Skill Vivo - Novembro/2005 ==>
            // -------------------------------------------------------------
            // Ficou fechado que o devolver bko não verificará o skill do
            // usuário, conforme documentação fechada entre as equipes
            // Indra e Vivo aos 18/09/2006.
            // 
            // if ( proCUsuarioTemSkill( UsuarioAtual.ToInt(),idGrupo.ToInt()) )
            // { // Se usuário tem skill para o grupo de destino do processo ...
            //     retornarParaFila = 
            //         proCUsuarioTemAcesso(UsuarioAtual.ToInt()
            //                             ,idGrupo.ToInt()
            //                             ,idAtendimento.ToLong()) ? false:true;
            // }  
            // <== incidência 2939 - TD Sanity Skill Vivo - Novembro/2005
            //

            // Verifica se o usuário devolução ainda esta casado ao grupo de devolução
            retornarParaFila = 
                ExisteGrupoUsuario(idGrupoAtual,idUsuarioAtendimento)?false:true;
        }
        else
        {
            retornarParaFila = true; // usuário inativo não pode receber processo
            ULOG("usuário esta INATIVO.");
        }
    }
    else
    {   // se não tem usuário para devolver relacionado então
        retornarParaFila = true; // irá fazer limpeza de associações com inbox de usuário 
        ULOG("Sem usuário atual relacionado.");
    }

    ULOG("retornarParaFila=[%d]",retornarParaFila);

    if ( retornarParaFila )
    {
        removeAtendimentoUsuarioAtual();
        removeAtendimentoPausa();
        removeTratamentoCri();
        removeAtendimentoCri();

        switch ( idAgrEstTPrAt.ToInt() )
        {
            case 4:
                idAgrEstTPrFt = 2;
            break;
            case 17:
                idAgrEstTPrFt = 15;
            break;
        }
    }
    else
    { // se não vai retornar para a fila seta o valor do estado futuro
        bool emPausa = ProcessoEmPausaSimNao();

        switch ( idAgrEstTPrAt.ToInt() )
        {
            case 4:
                idAgrEstTPrFt = emPausa ? 5 : 3;
            break;
            case 17:
                idAgrEstTPrFt = emPausa ? 18 : 16;
            break;
        }
    }

    removeAtendimentoSuspeito(); // Eliminando Marcacao Suspeito

    inserirAndamento(idGrupoAtual);
    // alterarAndamentoAtual();
    inserirAndamentoObservacao();
    inserirAndamentoMotivo();

    ULOG_END("cWF_Acao::ReanalisarBKO()");

    return true;
}

//------------------------------------------------------------------------------------------------------------------
// Verifica se esta encaminhando um processo mc2 para o mesmo usuário que encaminhou para o bko
//------------------------------------------------------------------------------------------------------------------
bool cWF_Acao::IsUsuarioDestinoMc2()
{
    ULOG_START("cWF_Acao::IsUsuarioDestinoMc2()");

    TString UsuarioDestino = UsuarioAtual;

    getAtendimentoUsuarioDevolucao();

    bool isIgual =  UsuarioAtual == UsuarioDestino ?true:false;

    UsuarioAtual = UsuarioDestino; // devolve valor original

    if ( isIgual )
    {
        ULOG_END("cWF_Acao::IsUsuarioDestinoMc2() ==> TRUE");
        return true;
    }

    ULOG_END("cWF_Acao::IsUsuarioDestinoMc2() ==> FALSE");

    return false;
}

//------------------------------------------------------------------------------------------------------------------
// Verifica se esta encaminhando um processo mc2 para o mesmo usuário que encaminhou para o bko
//------------------------------------------------------------------------------------------------------------------
void cWF_Acao::SyncRemoteCall(const char *nomeServico,const char *xmlIn)
{
    ULOG_START("cWF_Acao::SyncRemoteCall()");

    try
    {
        ULOG("remoteCall vai chamar o servico '%s'",nomeServico);

        tmIn.setService((char*)nomeServico);
        if ( strcmp(nomeServico,"CriaOrdemVenda")==0 )
        {
            tmIn.setUser("SAP");
        }
        else
        {
            tmIn.setUser((char*)User.c_str());
        }

        //tmIn.setSequence( m_stDados.sequence );
        tmIn.setMessageBody((char*)xmlIn);

        rc.setServiceName((char*)nomeServico);
        rc.setInputMessage(&tmIn);

        if ( rc.remoteCall() != TUXFWRET_OK )
        {
            char msgErr[256];

            sprintf(msgErr,"Erro na chamada ao servico '%s'",nomeServico);

            throw new TuxBasicSvcException("09E0001",msgErr);
        }
    }
    catch (...)
    {
        ULOGE("cWF_Acao::SyncRemoteCall()");
        throw;
    }

    ULOG_END("cWF_Acao::SyncRemoteCall()");
}

void cWF_Acao::gravaAtendimentoFormularioDinamico(long idAtendimento)
{
    ULOG_START("cWF_Acao::gravaAtendimentoFormularioDinamico()");
    char dataAtual[32];
    dataAtual[0] = 0x0;

    if (formularioDinamico.GetCount() == 0)
    {
        ULOG("cWF_Acao - Não foi respondido questionário.");
        return;
    }           
    
    st_AtendimentoFrm           m_stDados;
    st_vlAtendimentoFrm         m_vlDados;

    st_AtendimentoFrmCampo      m_stCampo;
    st_vlAtendimentoFrmCampo    m_vlCampo;

    memset(&m_stDados, 0,sizeof(m_stDados));
    memset(&m_vlDados,-1,sizeof(m_vlDados));

    AtendimentoFormulario*  af; 
    FormularioResposta*     fr;

    // Executa a chamada de inclusão do registro.
    XMLGen retorno;
    Collection* respostas;
    long idAtendimentoFrm;

    m_stDados.idAtendimento = idAtendimento;
    m_stDados.idUsuarioAlteracao = User.ToInt();
    getSysDate();
    SAFE_STRNCPY(m_stDados.dtUltimaAlteracao, sSysDate.c_str());

    m_vlDados.idAtendimento         = 1;
    m_vlDados.idUsuarioAlteracao    = 1;
    m_vlDados.dtUltimaAlteracao     = 1;

    m_stCampo.idUsuarioAlteracao = User.ToInt();
    getSysDate();
    SAFE_STRNCPY(m_stCampo.dtUltimaAlteracao, sSysDate.c_str());

    m_vlCampo.idUsuarioAlteracao = 1;
    m_vlCampo.dtUltimaAlteracao  = 1;

    // Registra o campo.
    for ( int i = 0 ; i < formularioDinamico.GetCount() ; i++ )
    {
        af = (AtendimentoFormulario*) formularioDinamico.GetItem(i);

        if (!af)
        {
            ULOG("cWF_Acao - gravaAtendimentoFormularioDinamico - Não foi recuperado atendimentoFormulario...");
            continue;
        }

        m_stDados.idCampo = af->idCampo;
        ULOG("cWF_Acao - gravaAtendimentoFormularioDinamico - idCampo [%d]...", m_stDados.idCampo);

        cWFAtendimentoFrm afrm(&m_stDados, &m_vlDados, &retorno);
        
        respostas = af->respostas;

        idAtendimentoFrm = 0;

        // Registra as respostas do campo.
        for ( int b = 0; b < respostas->GetCount(); b++ )
        {
            bool incluir = true;

            fr = (FormularioResposta*) respostas->GetItem(b);
            
            if (!fr)
            {
                ULOG("cWF_Acao - gravaAtendimentoFormularioDinamico - "
                            "Não foi recuperado formularioResposta...");
                continue;
            }

            if (fr->idDominio > 0)
            {
                m_stCampo.idDominio = fr->idDominio;
                m_stCampo.dsValor[0] = 0;

                ULOG("cWF_Acao - gravaAtendimentoFormularioDinamico - "
                            "Resposta possui dominio [%d]...", m_stCampo.idDominio);

                m_vlCampo.idDominio = 1;
                m_vlCampo.dsValor   = -1;
            }
            else
            {
                
                SAFE_STRNCPY(m_stCampo.dsValor, fr->dsResposta);
                m_stCampo.idDominio = 0;

                if ( m_stCampo.dsValor[0] == 0 )
                {
                    incluir = false;
                }
                else
                {
                    ULOG("cWF_Acao - gravaAtendimentoFormularioDinamico - "
                            "Resposta possui valor [%s]", m_stCampo.dsValor);
                }

                m_vlCampo.dsValor   = 1;
                m_vlCampo.idDominio = -1;
            }

            if (incluir)
            {
                if (idAtendimentoFrm == 0)
                {
                    idAtendimentoFrm = afrm.incluir(&xmlDpr);
                }

                m_stCampo.idAtendimentoFrm = idAtendimentoFrm;
                m_vlCampo.idAtendimentoFrm = 1;

                // ==> SM324--DPR--DEZ/2006--Cassio
                // Estes campos não são persistidos na tabela ATENDIMENTOFRMCAMPO
                // mas trafegam no VO do XML para uso pelo DPR.
                m_stCampo.idAtendimento = idAtendimento;
                m_vlCampo.idAtendimentoFrm = 1;

                m_stCampo.idCampo = af->idCampo;
                m_vlCampo.idCampo = 1;
                // <== SM324--DPR--DEZ/2006--Cassio

                ULOG("cWF_Acao - Incluindo...");
                cWFAtendimentoFrmCampo afrmc(&m_stCampo, &m_vlCampo, &retorno);

                afrmc.incluir(&xmlDpr);
            }
        }

        delete respostas;
    }

    ULOG_END("cWF_Acao::gravaAtendimentoFormularioDinamico()");
}

void cWF_Acao::carregarDadosFormulario(DOMNode *dnode)
{
    ULOG_START("cWF_Acao::carregarDadosFormulario()");

    char *p;

    DOMNode *registro;
    DOMNode *subregistro;

    AtendimentoFormulario* af;
    FormularioResposta* fr;

    //
    // Alteração conforme SM448
    // Gravamos os grupos de campos que estão no formulário
    //
    ULOG("cWF_Acao - Carrega Dados - Funcionalidade ENDERECO...");
    if (registro = walkDOM( dnode, "EnderecoVO", 0))
    {
        ULOG("cWF_Acao - Carrega Dados - Funcionalidade ENDERECO Inicio.");
        //
        // TipoEnderecoVO
        //
        if (subregistro = walkDOM( registro, "TipoEnderecoVO", 0))
        {
            af = new AtendimentoFormulario();
            af->respostas = new Collection();
            af->idCampo = 1485;
       
            ULOG("cWF_Acao - Carrega Dados - Carregando af->idCampo [%d]...", af->idCampo);

            fr = new FormularioResposta();

            fr->idDominio  = 0;
            fr->dsResposta = 0;

            if (p=walkTree(subregistro, "idTipoEndereco", 0),p)
            {
                // fr->idDominio  = atoi( p );
                fr->dsResposta = p;
                // ULOG("cWF_Acao - Carrega Dados - Campo possui dominio [%d]...", fr->idDominio);
                ULOG("cWF_Acao - Carrega Dados - Campo possui resposta [%s]...", fr->dsResposta);
            }

            af->respostas->AddItem( (void*) fr);
            formularioDinamico.AddItem( (void*) af );
        }
        //
        // nmTipoLogradouro
        //
        if (p=walkTree(registro, "nmTipoLogradouro", 0),p)
        {
            af = new AtendimentoFormulario();
            af->respostas = new Collection();
            af->idCampo = 177;
       
            ULOG("cWF_Acao - Carrega Dados - Carregando af->idCampo [%d]...", af->idCampo);

            fr = new FormularioResposta();

            fr->idDominio  = 0;

            fr->dsResposta = p;
            ULOG("cWF_Acao - Carrega Dados - Campo possui resposta [%s]...", fr->dsResposta);

            af->respostas->AddItem( (void*) fr);
            formularioDinamico.AddItem( (void*) af );
        }
        //
        // nmTituloLogradouro
        //
        if (p=walkTree(registro, "nmTituloLogradouro", 0),p)
        {
            af = new AtendimentoFormulario();
            af->respostas = new Collection();
            af->idCampo = 1487;
       
            ULOG("cWF_Acao - Carrega Dados - Carregando af->idCampo [%d]...", af->idCampo);

            fr = new FormularioResposta();

            fr->idDominio  = 0;

            fr->dsResposta = p;
            ULOG("cWF_Acao - Carrega Dados - Campo possui resposta [%s]...", fr->dsResposta);

            af->respostas->AddItem( (void*) fr);
            formularioDinamico.AddItem( (void*) af );
        }
        //
        // nmLogradouro
        //
        if (p=walkTree(registro, "nmLogradouro", 0),p)
        {
            af = new AtendimentoFormulario();
            af->respostas = new Collection();
            af->idCampo = 174;
       
            ULOG("cWF_Acao - Carrega Dados - Carregando af->idCampo [%d]...", af->idCampo);

            fr = new FormularioResposta();

            fr->idDominio  = 0;

            fr->dsResposta = p;
            ULOG("cWF_Acao - Carrega Dados - Campo possui resposta [%s]...", fr->dsResposta);

            af->respostas->AddItem( (void*) fr);
            formularioDinamico.AddItem( (void*) af );
        }
        //
        // nmBairro
        //
        if (p=walkTree(registro, "nmBairro", 0),p)
        {
            af = new AtendimentoFormulario();
            af->respostas = new Collection();
            af->idCampo = 176;
       
            ULOG("cWF_Acao - Carrega Dados - Carregando af->idCampo [%d]...", af->idCampo);

            fr = new FormularioResposta();

            fr->idDominio  = 0;

            fr->dsResposta = p;
            ULOG("cWF_Acao - Carrega Dados - Campo possui resposta [%s]...", fr->dsResposta);

            af->respostas->AddItem( (void*) fr);
            formularioDinamico.AddItem( (void*) af );
        }
        //
        // nmMunicipio
        //
        if (p=walkTree(registro, "nmMunicipio", 0),p)
        {
            af = new AtendimentoFormulario();
            af->respostas = new Collection();
            af->idCampo = 180;
       
            ULOG("cWF_Acao - Carrega Dados - Carregando af->idCampo [%d]...", af->idCampo);

            fr = new FormularioResposta();

            fr->idDominio  = 0;

            fr->dsResposta = p;
            ULOG("cWF_Acao - Carrega Dados - Campo possui resposta [%s]...", fr->dsResposta);

            af->respostas->AddItem( (void*) fr);
            formularioDinamico.AddItem( (void*) af );
        }
        //
        // nrEndereco
        //
        if (p=walkTree(registro, "nrEndereco", 0),p)
        {
            af = new AtendimentoFormulario();
            af->respostas = new Collection();
            af->idCampo = 1488;
       
            ULOG("cWF_Acao - Carrega Dados - Carregando af->idCampo [%d]...", af->idCampo);

            fr = new FormularioResposta();

            fr->idDominio  = 0;

            fr->dsResposta = p;
            ULOG("cWF_Acao - Carrega Dados - Campo possui resposta [%s]...", fr->dsResposta);

            af->respostas->AddItem( (void*) fr);
            formularioDinamico.AddItem( (void*) af );
        }
        //
        // dsEnderecoComplemento
        //
        if (p=walkTree(registro, "dsEnderecoComplemento", 0),p)
        {
            af = new AtendimentoFormulario();
            af->respostas = new Collection();
            af->idCampo = 194;
       
            ULOG("cWF_Acao - Carrega Dados - Carregando af->idCampo [%d]...", af->idCampo);

            fr = new FormularioResposta();

            fr->idDominio  = 0;

            fr->dsResposta = p;
            ULOG("cWF_Acao - Carrega Dados - Campo possui resposta [%s]...", fr->dsResposta);

            af->respostas->AddItem( (void*) fr);
            formularioDinamico.AddItem( (void*) af );
        }
        //
        // nrCEP
        //
        if (p=walkTree(registro, "nrCEP", 0),p)
        {
            af = new AtendimentoFormulario();
            af->respostas = new Collection();
            af->idCampo = 181;
       
            ULOG("cWF_Acao - Carrega Dados - Carregando af->idCampo [%d]...", af->idCampo);

            fr = new FormularioResposta();

            fr->idDominio  = 0;

            fr->dsResposta = p;
            ULOG("cWF_Acao - Carrega Dados - Campo possui resposta [%s]...", fr->dsResposta);

            af->respostas->AddItem( (void*) fr);
            formularioDinamico.AddItem( (void*) af );
        }
        //
        // UFVO
        //
        if (subregistro = walkDOM( registro, "UFVO", 0))
        {
            af = new AtendimentoFormulario();
            af->respostas = new Collection();
            af->idCampo = 179;
       
            ULOG("cWF_Acao - Carrega Dados - Carregando af->idCampo [%d]...", af->idCampo);

            fr = new FormularioResposta();

            fr->idDominio  = 0;
            fr->dsResposta = 0;

            if (p=walkTree(subregistro, "sgUF", 0),p)
            {
                fr->dsResposta = p;
                ULOG("cWF_Acao - Carrega Dados - Campo possui resposta [%s]...", fr->dsResposta);
            }

            af->respostas->AddItem( (void*) fr);
            formularioDinamico.AddItem( (void*) af );
        }
        //
        // PaisVO
        //
        if (subregistro = walkDOM( registro, "PaisVO", 0))
        {
            af = new AtendimentoFormulario();
            af->respostas = new Collection();
            af->idCampo = 234;
       
            ULOG("cWF_Acao - Carrega Dados - Carregando af->idCampo [%d]...", af->idCampo);

            fr = new FormularioResposta();

            fr->idDominio  = 0;
            fr->dsResposta = 0;

            if (p=walkTree(subregistro, "nmPais", 0),p)
            {
                fr->dsResposta = p;
                ULOG("cWF_Acao - Carrega Dados - Campo possui resposta [%s]...", fr->dsResposta);
            }

            af->respostas->AddItem( (void*) fr);
            formularioDinamico.AddItem( (void*) af );
        }

    } // if()

    ULOG_END("cWF_Acao::carregarDadosFormulario()");
}

//------------------------------------------------------------------------------------------------------------------
// Verifica se um usuário faz parte de um grupo
// false=NÃO FAZ PARTE, true=USUARIO FAZ PARTE DO GRUPO
//------------------------------------------------------------------------------------------------------------------
bool cWF_Acao::ExisteGrupoUsuario( unsigned long idGrupoCRIPrm , unsigned long idPessoaUsuarioPrm )
{
    bool retorno = proCExisteGrupoUsuario(idGrupoCRIPrm,idPessoaUsuarioPrm);
    ULOG("cWF_Acao::ExisteGrupoUsuario=[%d]",retorno);
    return retorno;
}

//------------------------------------------------------------------------------------------------------------------
// Init - Metodo Inicial
//------------------------------------------------------------------------------------------------------------------
bool cWF_Acao::Init()
{
    ULOG_START("cWF_Acao::Init()");

    idAtendimento          = GetXML("idAtendimento",true);
    idTipoRetornoContato   = GetXML("idTipoRetornoContato").ToInt();
    idAgrEstTPrFt          = GetXML("idAgrupamentoEstadoTProcFut");
    idAgrEstTPrAt          = GetXML("idAgrupamentoEstadoTProcAt");
    idAtividade            = GetXML("idAtividadeMassa");
    inCri                  = GetXML("inCRI").ToInt();
    inRC                   = GetXML("inRC").ToInt();
    idFase                 = GetXML("idFase");
    urlDestino             = GetXML("nmUrlDestino");
    idGrupo                = GetXML("idGrupo");
    dsObservacao           = GetXML("dsObservacao");
    UsuarioAtualScript     = GetXML("idPessoaUsuario");
    idMotivo               = GetXML("idMotivo");
    dsMotivo               = GetXML("dsMotivo");
    cdAreaRegistro         = GetXML("cdAreaRegistro");
    nrLinha                = GetXML("nrLinha");
    sgOperadoraSolicitante = GetXML("sgOperadoraSolicitante");
    dsAcaoPortabilidade    = GetXML("dsAcaoPortabilidade");


    SetNode("AtendimentoWorkflowVO");
    UsuarioAtual           = GetXML("idPessoaUsuario");
    dtContato              = GetXML("dtContato");
    inSPN                  = GetXML("inSPN");
    dtJanelaPortout        = GetXML("dtJanelaPortout");
    idProcedencia          = GetXML("idProcedencia").ToInt();
    hrContato              = GetXML("hrContato");
    idBaixa                = GetXML("idBaixa");
    idBaixaMensagem        = GetXML("idBaixaMensagem").ToInt();

    SetNode("DadosOrdemVendaVO");
    idStatusSAP            = GetXML("IDSTATUSSAP");
    idRetencao             = GetXML("IDRETENCAO");
    dsObservacaoOV         = GetXML("DSOBSERVACAO");

    getSysDate();

    idGrupoAtual = 0;
    idUsuarioAtendimento = 0;
    idGrupoCprevio = 0;
    idUFOperadora = -1;
    alterarTipoRetornoContato = false;

    if (dtContato.Lenght() > 0)
    {
        strcpy(dtInicioTransferencia, dtContato.c_str());
        strcat(dtInicioTransferencia, " ");
        strcat(dtInicioTransferencia, hrContato.c_str());
        if ( strlen(dtInicioTransferencia) < 19 )
        {
            strcat(dtInicioTransferencia,":00");
        }
        inPausa = 1;
    }
    else
    {
        strcpy(dtInicioTransferencia, sSysDate.c_str());
        inPausa = 0;
    }

    ULOG("dtInicioTransferencia: %s", dtInicioTransferencia);
    ULOG("inPausa: %d", inPausa);

    SetNode("FormularioVO");
    domFormulario = dnode;

    ULOG_END("cWF_Acao::Init()");

    return true; 
}

//------------------------------------------------------------------------------------------------------------------
// InitPesquisa - Metodo Inicial
//------------------------------------------------------------------------------------------------------------------
bool cWF_Acao::InitPesquisa() 
{
    ULOG_START("cWF_Acao::InitPesquisa()");

    idAtendimento   = GetXML("idAtendimento",true);
    idAtividade     = GetXML("idAtividade");
    dsObservacao    = GetXML("dsObservacao");
    getSysDate();

    ULOG_END("cWF_Acao::InitPesquisa()");

    return true; 
}

//------------------------------------------------------------------------------------------------------------------
// Executar - Virtual 
//------------------------------------------------------------------------------------------------------------------

void cWF_Acao::Executar() 
{
    Init();
}

/*
void cWF_Acao::adquirirCRI()
{
   ULOG_START("cWF_Acao::adquirirCRI()");
    // Obtém o próximo agrupamento/estado para o processo sendo removido da fila
    getAgrupEstadoTpProcFt(PROX_AGRUP_DIFERENTE_DO_ATUAL);

    if ( idAgrEstTPrFt.ToInt() == -1 )
    {
        getAgrupEstadoTpProcFt(PROX_AGRUP_PODE_SER_IGUAL_ATUAL);
    }

    if ( idAgrEstTPrFt.ToInt() == -1 )
    {
        SetMessage( "Proximo estado não encontrado para este processo", "N" ); 
        xml_g->closeTag();
        return;
    }

    inCriAtd = 1;
    alterarAtendimentoGrupoAtual(idGrupoAtual);

    UsuarioAtual = User.ToInt();

    getAtendimentoUsuarioCri(); // Atendimento.AtendimentoCri
    if ( idUsuarioCri == 0 )
    {
        inserirAtendimentoCri();
        idUsuarioCri = UsuarioAtual.ToInt();
    }
    else
    {
        alterarAtendimentoCri();
        idUsuarioCri = UsuarioAtual.ToInt();
    }

    getTratamentoUsuarioCri(); // Atendimento.TratamentoCri
    if ( idUsuarioCri == 0 )
    {
        inserirTratamentoCri(); // Atendimento.TratamentoCri
        idUsuarioCri = UsuarioAtual.ToInt();
    }
    else
    {
        alterarTratamentoCri();
        idUsuarioCri = UsuarioAtual.ToInt();
    }

    UsuarioAtual = idUsuarioCri;

    //removeAtendimentoGrupoBko();
    ULOG_START("cWF_Acao::adquirirCRI()");
}

*/
// ------------------------------------------------------------------------------
// Encaminha
// ------------------------------------------------------------------------------
/*
bool cWF_Acao::obterGrupoRCConfigurado(
                                        long _idAtendimento
                                       ,int _idContato
                                       ,int _idCanal
                                       ,int _idProcedencia
                                       ,int _idSegmentacao
                                       ,int _idTipoCarteira
                                       ,int _idUFOperadora
                                       ,int &idGrupoEncaminhar
                                       )
{
    struct sqlca sqlca;

    EXEC SQL BEGIN DECLARE SECTION;

        long idAtendimento = _idAtendimento;
        int idContato = _idContato;
        int idCanal = _idCanal;
        int idProcedencia = _idProcedencia;
        int idSegmentacao = _idSegmentacao;
        int idTipoCarteira = _idTipoCarteira;
        int idUFOperadora = _idUFOperadora;
        int idGrupo;
        short i_idGrupo = -1;

    EXEC SQL END DECLARE SECTION;

    EXEC SQL WHENEVER SQLERROR DO ContatoPrevioSQLError(&sqlca,__LINE__,0);
    EXEC SQL WHENEVER NOTFOUND CONTINUE;

    EXEC SQL 
        SELECT 
              ARG.IDGRUPO
        INTO  
              :idGrupo:i_idGrupo
        FROM 
              ATENDIMENTO.ATDRELGRUPORCV01 ARG
             ,CONTATOADM.UFOPERADORAGRUPO UFOG
        WHERE 
              ARG.IDCONTATO = :idContato
        AND
              ARG.IDCANAL = :idCanal
        AND
              ARG.IDPROCEDENCIA = :idProcedencia
        AND
              ARG.IDSEGMENTACAO = :idSegmentacao
        AND
              ARG.IDTIPOCARTEIRA = :idTipoCarteira
        AND 
              UFOG.IDUFOPERADORA = :idUFOperadora
        AND 
              UFOG.IDGRUPO = ARG.IDGRUPO
        AND
              ROWNUM < 2;
    //
    // se não encontrar nenhum grupo que atenda as variáveis configuradas,
    // assume o primeiro grupo de resposta ao cliente associado ao contato
    // que pertença a UF operadora do atendimento
    //
    if (sqlca.sqlcode == 1403)
    {
        EXEC SQL 
            SELECT 
                  ARG.IDGRUPO
            INTO
                  :idGrupo:i_idGrupo
            FROM 
                  ATENDIMENTO.ATDRELGRUPORCV01 ARG
                 ,CONTATOADM.UFOPERADORAGRUPO UFOG
                 ,ACESSO.USUARIOUFOPERADORA UUFOP
            WHERE 
                  ARG.IDCONTATO = :idContato
            AND 
                  UFOG.IDUFOPERADORA = :idUFOperadora
            AND 
                  UFOG.IDGRUPO = ARG.IDGRUPO
            AND
                  ROWNUM < 2;

        //
        // se não existe grupo associado a UF operadora então pega o primeiro
        // grupo associado ao contato que for encontrado
        //
        if (sqlca.sqlcode == 1403)
        {
            EXEC SQL 
                SELECT 
                      ARG.IDGRUPO
                INTO
                      :idGrupo:i_idGrupo
                FROM 
                      ATENDIMENTO.ATDRELGRUPORCV01 ARG
                WHERE 
                      ARG.IDCONTATO = :idContato
                AND
                      ROWNUM < 2;

            //
            // nenhum grupo associado ? então falha a busca
            //
            if (sqlca.sqlcode == 1403)
            {
                return false;
            }
        }
    }

    idGrupoEncaminhar = idGrupo;

    return true;
}
*/
