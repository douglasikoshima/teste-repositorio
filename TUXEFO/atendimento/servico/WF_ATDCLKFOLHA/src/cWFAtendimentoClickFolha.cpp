/**
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:50 $
 **/

#include "../include/cWFAtendimentoClickFolha.h"
#include "../../Atendimento/include/cWFAtendimento.h"
#include "../../AtendimentoFrm/include/cWFAtendimentoFrm.h"
#include "../../AtendimentoGrupoAtual/include/cWFAtendimentoGrupoAtual.h"
#include "../../Usuario/include/cWFUsuario.h"
#include "../../FluxoFase/include/cWFFluxoFase.h"
#include "../../CondicaoAparicao/include/cWFCondicaoAparicao.h"

#include "../../../commons/msgPadrao.h"

extern bool proCObterContatoAcao( st_AtendimentoClickFolha *dados
                                , st_vlAtendimentoClickFolha *status
                                , XMLGen *saida
                                , DOMNode *dnode );

extern bool proCObterIdAgroEstTpProc( st_AtendimentoClickFolha *dados
                                    , st_vlAtendimentoClickFolha *status
                                    , XMLGen *saida
                                    , DOMNode *dnode );

extern bool proCObterPath( st_AtendimentoClickFolha *dados
                         , st_vlAtendimentoClickFolha *status
                         , XMLGen *saida );

cWFAtdClickFolha::cWFAtdClickFolha(DOMNode *dnode, XMLGen *xml_g)
{
    entrada = dnode;
    saida = xml_g;

    carregaDados();
}

bool cWFAtdClickFolha::executar(char **codErro,char **msgErro)
{
    //
    // Caso este serviço seja acionado com a funçaõ de preview de formulário
    // apenas retornamos o preview.
    //
    if( m_stDados.inPreview == 1 )
    {
        saida->createTag("AtendimentoVO");
        saida->addProp("xmlns","workflow.fo.vivo.com.br/vo");

            cWFAtendimentoFrm cwfAtendimentoFrm(entrada, saida);

            saida->createTag("ArvoreAtendimentoVO");

                saida->addProp("xmlns","admsistemas.fo.vivo.com.br/vo");

                saida->createTag("FormularioVO");

                    ULOG("cWFAtdClickFolha::executar:idUFOperadora[%d]", m_stDados.idUFOperadora);
                    cwfAtendimentoFrm.obtemFormularioPreview(m_stDados.idUFOperadora);

                saida->closeTag();

            saida->closeTag();

        saida->closeTag();

        // Para retornar o preview, nosso trabalho termina aqui
        return true;
    }

    if ( m_vlDados.idContato == -1 )
    {
        *msgErro = "O código do contato [idContato] é obrigatório "
                   "para a execução do servico ATDCLICKFOLHA";

        return false;
    }

    if ( m_vlDados.idPessoaDePara == -1 )
    {
        *msgErro = "O código do contato [idPessoaDePara] é obrigatório "
                   "para a execução do servico ATDCLICKFOLHA";

        return false;
    }

    if ( m_stDados.idContato < 1 )
    {
        *msgErro = "Valor de [idContato] deve ser obrigatoriamente maior que zero "
                   "para a execução do servico ATDCLICKFOLHA";

        return false;
    }

    if ( m_stDados.idPessoaDePara < 1 )
    {
        *msgErro = "Valor de [idPessoaDePara] deve ser obrigatoriamente maior que zero "
                   "para a execução do servico ATDCLICKFOLHA";

        return false;
    }

    saida->createTag("AtendimentoVO");
    saida->addProp("xmlns","workflow.fo.vivo.com.br/vo");

    if( m_stDados.abertoInsistencia )
    {
        ULOG("abertoInsistencia = TRUE");

        saida->createTag("ArvoreAtendimentoVO");
            saida->addProp("xmlns", "admsistemas.fo.vivo.com.br/vo");
            saida->createTag("idContato"); saida->closeTag();
            saida->createTag("descricaoCompleta"); saida->closeTag();
            saida->createTag("ArvoreEncerramentoVO"); saida->closeTag();
            saida->createTag("ProcedenciaVO"); saida->closeTag();
            saida->createTag("CarterizacaoVO"); saida->closeTag();
            saida->createTag("SegmentacaoVO"); saida->closeTag();
            saida->createTag("FormularioVO"); saida->closeTag();
        saida->closeTag();
    }
    else
    {
        ULOG("abertoInsistencia = FALSE");

        ObterAtendimento(saida);

        ObterContasCliente(saida);

        ObterCanaisProcedencia(saida);

        ObterXMLAcoes(saida);

        ObterXMLDadosArvore(saida);

        proCObterContatoAcao(&m_stDados,&m_vlDados,saida,entrada);

        // ObterGrupoAtual(saida);
    }

    saida->closeTag();

    return true;
}

void cWFAtdClickFolha::ObterGrupoAtual(XMLGen *saida)
{
    ULOG_START("cWFAtdClickFolha::ObterGrupoAtual()"); 

    cWFAtendimentoGrupoAtual cwfAtendimentoGrupoAtual(entrada,saida);

    cwfAtendimentoGrupoAtual.ObtemGrAt(idAtendimento);

    ULOG_END("cWFAtdClickFolha::ObterGrupoAtual()"); 
}

void cWFAtdClickFolha::ObterAtendimento(XMLGen *saida)
{
    ULOG_START("cWFAtdClickFolha::ObterAtendimento()"); 

    if ( !m_stDados.indAbertura )
    {
        cWFAtendimento cwfAtendimento(entrada,saida);

        cwfAtendimento.ObtemAtend(&idAtendimento);
    }
    else
    {
        ULOG("ObterAtendimento não executou porque indAbertura = 0");
    }
    
    ULOG_END("cWFAtdClickFolha::ObterAtendimento()"); 
}

void cWFAtdClickFolha::ObterContasCliente(XMLGen *saida)
{
    ULOG_START("cWFAtdClickFolha::ObterContasCliente()"); 

    cWFAtendimento cwfAtendimento;

    ULOG("IdPessoaDePara: %d", m_stDados.idPessoaDePara);
    // Obtém as contas do cliente se possível
    cwfAtendimento.ObtemContasCliente(m_stDados.idPessoaDePara, m_stDados.idConta, m_stDados.idTipoLinha, m_stDados.idUFOperadora, saida);

    // Obtém dados do cliente/usuário/prospect/não cliente
    cwfAtendimento.ObtemPessoaComunic(m_stDados.idPessoa,saida);

    ULOG_END("cWFAtdClickFolha::ObterContasCliente()"); 
}

void cWFAtdClickFolha::ObterCanaisProcedencia(XMLGen *saida)
{
    ULOG_START("cWFAtdClickFolha::ObterCanaisProcedencia()"); 

    char *p=0;
    char xml[256];
    cWFUsuario cwfUsuario;
    int tamSaida;
    MemBufInputSource *pMemBuf;
    XercesDOMParser *pParser;

    sprintf(xml,"<Parametros>"
                    "<idGrupo>%d</idGrupo>"
                    "<idPessoaUsuario>%d</idPessoaUsuario>"
                "</Parametros>"
           ,m_stDados.idGrupo
           ,m_stDados.idPessoaUsuario);

    tamSaida = strlen(xml);

    ULOG("xml=%s",xml);
    ULOG("strlen(xml)=%d",tamSaida);

    pParser = new XercesDOMParser;
    pMemBuf = new MemBufInputSource((const XMLByte*)xml, tamSaida, gerarIDDom());

    if ( pParser && pMemBuf )
    {
        pParser->parse(*pMemBuf);
        DOMNode* pDoc = pParser->getDocument();

        saida->createTag("Canais");
            cwfUsuario.pesqGrupoCanal(pDoc,saida);
        saida->closeTag();

        saida->createTag("Procedencias");
            cwfUsuario.pesqGrupoProcedencia(pDoc,saida);
        saida->closeTag();
    }
    else
    {
        p = "Nao foi possivel alocar memoria para objetos DOM e/ou Xerces";
    }

    delete pParser;
    delete pMemBuf;

    if ( p )
    {
        ULOGE(p);
        ULOG_END("cWFAtdClickFolha::ObterCanaisProcedencia()"); 
        throw new TuxBasicSvcException("00E0000",mensagemSimples(p));
    }

    ULOG_END("cWFAtdClickFolha::ObterCanaisProcedencia()"); 
}

void cWFAtdClickFolha::ObterXMLAcoes(XMLGen *saida)
{
    ULOG_START("cWFAtdClickFolha::ObterXMLAcoes()"); 

    Collection colecaoAcoes;
    cWFFluxoFase cwfFluxoFase;
    st_FluxoFase dados;
    st_vlFluxoFase status;
    SmallString xmlAcRegistrar;

    xmlAcRegistrar += "<WFAcaoVO>";
    xmlAcRegistrar += "<idAtividade>";
    xmlAcRegistrar += "21";
    xmlAcRegistrar += "</idAtividade>";
    xmlAcRegistrar += "<dsAtividade>";
    xmlAcRegistrar += "REGISTRAR";
    xmlAcRegistrar += "</dsAtividade>";
    xmlAcRegistrar += "<jsAtividade>";
    xmlAcRegistrar += "regContato()";
    xmlAcRegistrar += "</jsAtividade>";
    xmlAcRegistrar += "</WFAcaoVO>\n";

    saida->aggregateXML((char*)xmlAcRegistrar);

    memset(&dados,0,sizeof(dados));
    memset(&status,-1,sizeof(status));

    dados.idContato = m_stDados.idContato;
    dados.idGrupo = m_stDados.idGrupo;
    dados.idFase = m_stDados.idFaseProcesso;
    dados.idTipoLinha = m_stDados.idTipoLinha;
    strcpy(dados.inTipoPessoa, m_stDados.inTipoPessoa);
    dados.idTipoCarteira = m_stDados.idTipoCarteira;
    dados.idSegmentacao = m_stDados.idSegmentacao;
    dados.idTipoRelacionamento = m_stDados.idTipoRelacionamento;

    status.idContato = 1;
    status.idGrupo = 1;
    status.idFase = 1;
    status.idTipoLinha = 1;
    status.inTipoPessoa = 1;
    status.idTipoCarteira = 1;
    status.idSegmentacao = 1;
    status.idTipoRelacionamento = 1;

    if ( cwfFluxoFase.ObtemWFAcoesAbertura(&dados,&status,&colecaoAcoes) == false )
    {
        ULOG_END("cWFAtdClickFolha::ObterXMLAcoes()"); 
        return;
    }

    ObterXMLAcoesCompletas(&colecaoAcoes,saida);

    ULOG_END("cWFAtdClickFolha::ObterXMLAcoes()"); 
}

void cWFAtdClickFolha::ObterXMLAcoesCompletas(Collection *colecaoAcoes,XMLGen *saida)
{
    ULOG_START("cWFAtdClickFolha::ObterXMLAcoesCompletas()"); 

    bool processar;
    cWFCondicaoAparicao cwfCondicaoAparicao;
    int idFaseAt = 0;
    int idAtividadeAt=0;
    st_CondicaoAparicao dados;
    st_vlCondicaoAparicao status;
    SmallString xmlAcCompletas;
    SmallString xmlAcoesWF;

    memset(&dados,0,sizeof(dados));
    memset(&status,-1,sizeof(status));

    int nAcoes = colecaoAcoes->GetCount();

    for ( int i = 0; i < nAcoes ; i++ )
    {
        ACOES_FLUXO *af = (ACOES_FLUXO *) colecaoAcoes->GetItem(i);

        xmlAcoesWF.Clean();

        processar = false;

        if ( af->idAtividade > idAtividadeAt )
        {
            processar = true;
            idFaseAt = af->idFase;
        }

        if ( idFaseAt == af->idFase )
        {
            processar = true;
        }

        if ( processar )
        {
            dados.idFluxoFase = af->idFluxoFase;
            status.idFluxoFase = 1;

            dados.idParametro = m_stDados.idContato;
            status.idParametro = 1;

            if ( cwfCondicaoAparicao.ObtemWFCndAcoes(&dados,&status) == false )
            {
                ULOG("a condição pede que a ação não seja exibida");
                xmlAcoesWF.Clean();
            }
            else
            {
                xmlAcoesWF += "<WFAcaoVO>";
                xmlAcoesWF += "<idAtividade>";
                xmlAcoesWF += af->idAtividade;
                xmlAcoesWF += "</idAtividade>";
                xmlAcoesWF += "<dsAtividade>";
                xmlAcoesWF += af->dsAtividade;
                xmlAcoesWF += "</dsAtividade>";
                xmlAcoesWF += "<jsAtividade>";
                xmlAcoesWF += af->nmFuncValidacao;
                xmlAcoesWF += "</jsAtividade>";
                xmlAcoesWF += "</WFAcaoVO>\n";
            }
        }

        idAtividadeAt = af->idAtividade;

        if ( xmlAcoesWF.Size() > 0 )
        {
            xmlAcCompletas += xmlAcoesWF;
        }
    }

    // Agrega o resultado ao XML de saída, caso tenha gerado alguma ação
    if ( xmlAcCompletas.Size() > 0 )
    {
        ULOG("Acoes agregadas ==> %s", (char*)xmlAcCompletas);

        saida->aggregateXML((char*)xmlAcCompletas);
    }
    else
    {
        ULOG("nenhuma ação para o fluxo dado");
    }

    ULOG_END("cWFAtdClickFolha::ObterXMLAcoesCompletas()"); 
}

// =============================================================================
// ObterXMLAcoesCompletas() não trafega mais DOM ou XMLGen para
// fazer com que o tráfego de XML entre métodos diminua e com isso 
// ObterXMLAcoesCompletas() não tem mais utilidade.
//
// Maio, 2006 - Cassio.
// =============================================================================
// 
// void cWFAtdClickFolha::ObterXMLAcoesCompletas(DOMNode *entrada,char *node,XMLGen *saida)
// {
//     tuxfw_getlogger()->information("%s",mensagemSimples("> ObterXMLAcoesCompletas"));
// 
//     bool processar = false;
//     cWFCondicaoAparicao cwfCondicaoAparicao;
//     DOMNode *dn;
//     int idAtividade=0;
//     int idFaseWF = 0;
//     int idFaseAt = 0;
//     int index = 0;
//     int idAtividadeAt=0;
//     st_CondicaoAparicao dados;
//     st_vlCondicaoAparicao status;
//     SmallString xmlAcCompletas;
//     SmallString xmlAcoesWF;
// 
//     memset(&dados,0,sizeof(dados));
//     memset(&status,-1,sizeof(status));
// 
//     while ( dn = tx.walkDOM(entrada,node,index++ ) )
//     {
//         char *_idAtividade = tx.walkTree(dn,"idAtividade",0);
//         char *_idFase = tx.walkTree(dn,"idFase",0);
//         char *_dsAtividade = tx.walkTree(dn,"dsAtividade",0);
//         char *_nmFuncValidacao = tx.walkTree(dn,"nmFuncValidacao",0);
//         char *_idFluxoFase = tx.walkTree(dn,"idFluxoFase",0);
// 
//         processar = false;
// 
//         idAtividade = atoi(_idAtividade);
//         idFaseWF = atoi(_idFase);
// 
//         if ( _idAtividade && _idFase && _dsAtividade && _nmFuncValidacao
//            && _idFluxoFase )
//         {
//             xmlAcoesWF.Clean();
//         }
//         else
//         {
//             char *p = "XML recebido é incompátivel ou inválido";
//             tuxfw_getlogger()->error("%s",mensagemSimples(p));
//             throw new TuxBasicSvcException("00E0000",mensagemSimples(p));
//         }
// 
//         if ( idAtividade > idAtividadeAt )
//         {
//             processar = true;
//             idFaseAt = idFaseWF;
//         }
// 
//         if ( idFaseAt == idFaseWF )
//         {
//             processar = true;
//         }
// 
//         if ( processar )
//         {
//             xmlAcoesWF += "<WFAcaoVO>";
//             xmlAcoesWF += "<idAtividade>";
//             xmlAcoesWF += idAtividade;
//             xmlAcoesWF += "</idAtividade>";
//             xmlAcoesWF += "<dsAtividade>";
//             xmlAcoesWF += _dsAtividade;
//             xmlAcoesWF += "</dsAtividade>";
//             xmlAcoesWF += "<jsAtividade>";
//             xmlAcoesWF += _nmFuncValidacao;
//             xmlAcoesWF += "</jsAtividade>";
//             xmlAcoesWF += "</WFAcaoVO>\n";
// 
//             dados.idAtividade = idAtividade;
//             status.idAtividade = 1;
// 
//             dados.idFluxoFase = atoi(_idFluxoFase);
//             status.idFluxoFase = 1;
// 
//             //dados.idAtividadeAt = idAtividade;
//             dados.idAtividadeAt = 0;
//             status.idAtividadeAt = 1;
// 
//             dados.idParametro = m_stDados.idContato;
//             status.idParametro = 1;
// 
//             if ( cwfCondicaoAparicao.ObtemWFCndAcoes(&dados,&status) == false )
//             {
//                 xmlAcoesWF.Clean();
//             }
//         }
// 
//         ULOG("Acoes: %s", (char*)xmlAcCompletas);
// 
//         idAtividadeAt = idAtividade;
// 
//         xmlAcCompletas += xmlAcoesWF;
// 
//         if(_idAtividade) { XMLString::release(&_idAtividade); }
//         if(_idFase) { XMLString::release(&_idFase); }
//         if(_dsAtividade) { XMLString::release(&_dsAtividade); }
//         if(_nmFuncValidacao) { XMLString::release(&_nmFuncValidacao); }
//         if(_idFluxoFase) { XMLString::release(&_idFluxoFase); }
//     }
// 
//     saida->aggregateXML((char*)xmlAcCompletas);
// 
//     tuxfw_getlogger()->information("%s",mensagemSimples("< ObterXMLAcoesCompletas"));
// }

// =============================================================================
// ObtemWFCndAcoes() retorna false/true ao invés de um XML resultante, para 
// fazer com que o tráfego de XML entre métodos diminua e com isso 
// ObterWFCndAcoes() não tem mais utilidade.
//
// Maio, 2006 - Cassio.
// =============================================================================
// 
// bool cWFAtdClickFolha::ObterWFCndAcoes(st_CondicaoAparicao *dados,st_vlCondicaoAparicao *status)
// {
//     ULOG("%s",mensagemSimples("> ObterWFCndAcoes"));
// 
//     int tamSaida;
//     cWFCondicaoAparicao cwfCondicaoAparicao;
//     XMLGen saida;
// 
//     int idCondicaoAparicao = 0;
//     int condicao = 0;
// 
//     ULOG((Endereco(__LINE__,__FILE__).mensagemDebug("Vai executar ObtemWFCndAcoes "
//                         "para idAtividade=%d,idFluxoFase=%d,idAtividadeAt=%d,idContato=%d"
//                             ,dados->idAtividade
//                             ,dados->idFluxoFase
//                             ,dados->idAtividadeAt
//                             ,dados->idParametro)).MsgPadrao());
// 
//     if ( !cwfCondicaoAparicao.ObtemWFCndAcoes(dados,status,&saida) )
//     {
//         tuxfw_getlogger()->warning("%s",mensagemSimples("cWFCondicaoAparicao::ObtemWFCndAcoes() "
//                                                                                  "falhou execução"));
//         return;
//     }
// 
//     char *xml = saida.retrieveXML(&tamSaida);
// 
//     if ( tamSaida > 0 )
//     {
//         XercesDOMParser *pParser = new XercesDOMParser;
//         MemBufInputSource *pMemBuf = new MemBufInputSource((const XMLByte*)xml,tamSaida,gerarIDDom());
// 
//         ULOG((Endereco(__LINE__,__FILE__).
//                 mensagemDebug("XML resultante de cWFCondicaoAparicao::ObtemWFCndAcoes=%s"
//                                                                                 ,xml)).MsgPadrao());
// 
//         if ( pParser && pMemBuf )
//         {
//             xml = 0;
// 
//             pParser->parse(*pMemBuf);
//             DOMNode* pDoc = pParser->getDocument();
//             
//             char *_idCondicaoAparicao = tx.walkTree(pDoc,"idCondicaoAparicao",0);
// 
//             if ( _idCondicaoAparicao )
//             {
//                 idCondicaoAparicao = atoi(_idCondicaoAparicao);
// 
//                 XMLString::release(&_idCondicaoAparicao);
// 
//                 ULOG((Endereco(__LINE__,__FILE__).
//                                mensagemDebug("idCondicaoAparicao=%d",idCondicaoAparicao)).MsgPadrao());
//             }
// 
//             char *_condicao = tx.walkTree(pDoc,"condicao",0);
// 
//             if ( _condicao )
//             {
//                 condicao = atoi(_condicao);
// 
//                 XMLString::release(&_condicao);
// 
//                 ULOG((Endereco(__LINE__,__FILE__).
//                                mensagemDebug("condicao=%d",condicao)).MsgPadrao());
//             }
//         }
//         else
//         {
//             xml = "Nao foi possivel alocar memoria para objetos DOM e/ou Xerces";
//         }
// 
//         delete pParser;
//         delete pMemBuf;
// 
//         if ( xml )
//         {
//             tuxfw_getlogger()->error("%s",mensagemSimples(xml));
//             throw new TuxBasicSvcException("00E0000",mensagemSimples(xml));
//         }
//     }
//     else
//     {
//         ULOG((Endereco(__LINE__,__FILE__).
//                 mensagemDebug("cWFCondicaoAparicao::ObtemWFCndAcoes não gerou XML de saida")).MsgPadrao());
//     }
// 
//     ULOG("%s",mensagemSimples("< ObterWFCndAcoes"));
// 
//     return (idCondicaoAparicao > 0 && !condicao ) ? false : true;
// }

void cWFAtdClickFolha::ObterXMLDadosArvore(XMLGen *saida)
{
    ULOG_START("cWFAtdClickFolha::ObterXMLDadosArvore()"); 

    cWFAtendimentoFrm cwfAtendimentoFrm(entrada, saida);

    saida->createTag("ArvoreAtendimentoVO");

        saida->addProp("xmlns","admsistemas.fo.vivo.com.br/vo");

        proCObterPath( &m_stDados, &m_vlDados, saida );

        saida->createTag("FormularioVO");

            cwfAtendimentoFrm.obtemFormulario();

        saida->closeTag();

    saida->closeTag();

    ULOG_END("cWFAtdClickFolha::ObterXMLDadosArvore()"); 
}

void cWFAtdClickFolha::carregaDados()
{
    ULOG_START("cWFAtdClickFolha::carregaDados()"); 

    char *p;

    // Inicializa as estruturas de dados para armazenar os valores
    memset(&m_stDados,0,sizeof(m_stDados));
    memset(&m_vlDados,-1,sizeof(m_vlDados));

    if (p=tx.walkTree( entrada, "idContato", 0 ),p) 
    {
        m_stDados.idContato = atoi(p);
        m_vlDados.idContato = 1;
		XMLString::release(&p);
    }

    if (p=tx.walkTree( entrada, "idLinha", 0 ),p) 
    {
        m_stDados.idLinha = atoi(p);
        m_vlDados.idLinha = 1;
		XMLString::release(&p);
    }

    if (p=tx.walkTree( entrada, "idFaseProcesso", 0 ),p) 
    {
        m_stDados.idFaseProcesso = atoi(p);
        m_vlDados.idFaseProcesso = 1;
		XMLString::release(&p);
    }

    if (p=tx.walkTree( entrada, "idPessoaDePara", 0 ),p) 
    {
        m_stDados.idPessoaDePara = atoi(p);
        m_vlDados.idPessoaDePara = 1;
		XMLString::release(&p);
    }

    if (p=tx.walkTree( entrada, "abertoInsistencia", 0 ),p) 
    {
        m_stDados.abertoInsistencia = atoi(p) ? true : false;
        m_vlDados.abertoInsistencia = 1;
		XMLString::release(&p);
    }

    if (p=tx.walkTree( entrada, "idTipoLinha", 0 ),p) 
    {
        m_stDados.idTipoLinha = atoi(p);
        m_vlDados.idTipoLinha = 1;
		XMLString::release(&p);
    }

    if (p=tx.walkTree( entrada, "nrTelefone", 0 ),p) 
    {
        strcpy(m_stDados.nrTelefone,p);
        m_vlDados.nrTelefone = 1;
		XMLString::release(&p);
    }

    if (p=tx.walkTree( entrada, "idUFOperadora", 0 ),p) 
    {
        m_stDados.idUFOperadora = atoi(p);
        m_vlDados.idUFOperadora = 1;
		XMLString::release(&p);
    }

    if (p=tx.walkTree( entrada, "indAbertura", 0 ),p) 
    {
        m_stDados.indAbertura = atoi(p);
        m_vlDados.indAbertura = 1;
		XMLString::release(&p);
    }

    if (p=tx.walkTree( entrada, "idGrupo", 0 ),p) 
    {
        m_stDados.idGrupo = atoi(p);
        m_vlDados.idGrupo = 1;
		XMLString::release(&p);
    }

    if (p=tx.walkTree( entrada, "inTipoPessoa", 0 ),p) 
    {
        strcpy(m_stDados.inTipoPessoa, p);
        m_vlDados.inTipoPessoa = 1;
		XMLString::release(&p);
    }

	    if (p=tx.walkTree( entrada, "idTipoCarteira", 0 ),p) 
    {
        m_stDados.idTipoCarteira = atoi(p);
        m_vlDados.idTipoCarteira = 1;
		XMLString::release(&p);
    }

    if (p=tx.walkTree( entrada, "idSegmentacao", 0 ),p) 
    {
        m_stDados.idSegmentacao = atoi(p);
        m_vlDados.idSegmentacao = 1;
		XMLString::release(&p);
    }

    if (p=tx.walkTree( entrada, "idTipoRelacionamento", 0 ),p) 
    {
        m_stDados.idTipoRelacionamento = atoi(p);
        m_vlDados.idTipoRelacionamento = 1;
		XMLString::release(&p);
    }
	
    if (p=tx.walkTree( entrada, "idPessoa", 0 ),p) 
    {
        m_stDados.idPessoa = atoi(p);
        m_vlDados.idPessoa = 1;
		XMLString::release(&p);
    }

    if (p=tx.walkTree( entrada, "idConta", 0 ),p) 
    {
        m_stDados.idConta = atoi(p);
        m_vlDados.idConta = 1;
		XMLString::release(&p);
    }

    //
    // SM 448
    // Para a realização do preview do formulário dinâmico
    // realizamos apenas o ObtemFormulário
    //
    if (p=tx.walkTree( entrada, "inPreview", 0 ),p) 
    {
        if( strlen(p) > 0 )
            m_stDados.inPreview = atoi(p);
        else
            m_stDados.inPreview = 0;

        m_vlDados.inPreview = 1;
		XMLString::release(&p);
    }

    if (p=tx.walkTree( entrada, "idUFOperadora", 0 ),p) 
    {
        if( strlen(p) > 0 )
        {
            m_stDados.idUFOperadora = atoi(p);
            m_vlDados.idUFOperadora = 1;
        }
        else
            m_stDados.idUFOperadora = 0;
		XMLString::release(&p);
    }


	ULOG("idContato=%d",m_stDados.idContato);
    ULOG("idPessoaDePara=%d",m_stDados.idPessoaDePara);
    ULOG("abertoInsistencia=%s",m_stDados.abertoInsistencia?"true":"false");
    ULOG("idTipoLinha=%d",m_stDados.idTipoLinha);
    ULOG("idUFOperadora=%d",m_stDados.idUFOperadora);
    ULOG("idGrupo=%d",m_stDados.idGrupo);

    ULOG_END("cWFAtdClickFolha::carregaDados()"); 
}
