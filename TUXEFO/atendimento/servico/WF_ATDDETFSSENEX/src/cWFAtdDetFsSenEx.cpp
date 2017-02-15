/**
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:19 $
 **/

#include "../include/cWFAtdDetFsSenEx.h"

#include "../../Atendimento/include/cWFAtendimento.h"
#include "../../AtendimentoNivel/include/cWFAtendimentoNivel.h"
#include "../../AtendimentoGrupoAtual/include/cWFAtendimentoGrupoAtual.h"

#include "../../AtendimentoPessoa/include/cWFAtendimentoPessoa.h"
#include "../../AtendimentoLinha/include/cWFAtendimentoLinha.h"

#include "../../Fase/include/cWFFase.h"
#include "../../Usuario/include/cWFUsuario.h"

extern bool proCObterIdTipoLinha(int *_idTipoLinha,char *_idPessoaLinhaHistorico);

cWFAtdDetFsSenEx::cWFAtdDetFsSenEx(DOMNode *dnode, XMLGen *xml_g)
{
    entrada = dnode;
    saida = xml_g;

    memset(&m_stDados,0,sizeof(m_stDados));
    memset(&m_vlDados,-1,sizeof(m_vlDados));

    memset(&m_stAtdDetFsSen,0,sizeof(m_stAtdDetFsSen));
    memset(&m_vlAtdDetFsSen,-1,sizeof(m_vlAtdDetFsSen));

    memset(&m_stAtdBaixa,0,sizeof(m_stAtdBaixa));
    memset(&m_vlAtdBaixa,-1,sizeof(m_vlAtdBaixa));

    carregaDados();
}

bool cWFAtdDetFsSenEx::executar(char **codErro,char **msgErro)
{
    bool retorno = false;

    if ( m_vlDados.idAtendimento == -1 )
    {
        *msgErro = "O código de 'idAtendimento' é obrigatório";

        ULOGE(*msgErro);

        return false;
    }

    saida->createTag("AtendimentoVO");
        saida->addProp("xmlns", "workflow.fo.vivo.com.br/vo");

    if ( !ObterDetalheAtd() )
    {
        *msgErro = "Processo não encontrado";
        
        ULOGE(*msgErro);

        retorno = false;
    }
    else
    {
        ObterFase();

        retorno = true;
    }

    saida->closeTag();

    return retorno;
}

void cWFAtdDetFsSenEx::ObterFase()
{
    ULOG_START("cWFAtdDetFsSenEx::ObterFase()");

    cWFFase cwfFase;
    Collection collectionFases;
    int index = 0;
    int idFase;

    if ( -1 == detalheAtendimento.idFase )
    {
        char *p = "O valor de 'idFase' é obrigatorio e não foi gerado";
        ULOGE(p);
        ULOG_END("cWFAtdDetFsSenEx::ObterFase()");
        throw new TuxBasicSvcException("00E0000",p);
    }

    if ( -1 == detalheAtendimento.nrNivel )
    {
        char *p = "O valor de 'nrNivel' é obrigatorio e não foi gerado";
        ULOGE(p);
        ULOG_END("cWFAtdDetFsSenEx::ObterFase()");
        throw new TuxBasicSvcException("00E0000",p);
    }

    if ( !cwfFase.obterFases(collectionFases) )
    {
        ULOGE("falhou execução");
        ULOG_END("cWFAtdDetFsSenEx::ObterFase()");
        return;
    }

    int qtdeFases = collectionFases.GetCount();

    if ( qtdeFases < 1 )
    {
        ULOGW("nenhuma fase encontrada");
        ULOG_END("cWFAtdDetFsSenEx::ObterFase()");
        return;
    }

    for (int it=0;it<qtdeFases;it++)
    {
        idFase = ((st_Fase*)(collectionFases.GetItem(it)))->idFase;

        saida->createTag("FaseVO");
            saida->addItem("idFase",idFase);
            saida->addItem("dsFase", ((st_Fase*)(collectionFases.GetItem(it)))->dsFase);

        // Incidência 3271 (revoga incidência 3507) redefine as
        // regras de exibição de grupos na coluna RETORNO para
        // processos não-CRI com retorno.
        // Para maiores detalhes veja DES_BUG_3271 de julho/2006
        if (m_vlAtdDetFsSen.dtFechamento != -1 || idFase == ABERTURA)
        {
            ObterDetalheAtendNivel(idFase,detalheAtendimento.idFase);
        }
        else if ( idFase == TRATAMENTO )
        {
            ObterDetalheAtendNivel(idFase,detalheAtendimento.idFase);

            if ( idFase == TRATAMENTO )
            {
                PesquisarUsuGrpProxNivel();
            }
        }
        else if ( idFase == RETORNO )
        {
            if ( detalheAtendimento.idTipoRetornoContato == TP_RET_SEM_RETORNO )
            {
                saida->createTag("NivelVO");
                    saida->addItem("idGrupo",0);
                    saida->addItem("nmGrupo","Sem Retorno");
                    saida->addItem("nrNivel","");
                    saida->addItem("idAtividade","");
                    saida->addItem("dsAtividade","");
                    saida->addItem("status", 2);
                saida->closeTag();
            }
            else if ( detalheAtendimento.idTipoRetornoContato == TP_RET_COM_RET_GRP_BKO )
            {
                if ( detalheAtendimento.idFase == TRATAMENTO )
                {
                    saida->createTag("NivelVO");
                        saida->addItem("idGrupo",0);
                        saida->addItem("nmGrupo","Retorno Grupo de BKO");
                        saida->addItem("nrNivel","");
                        saida->addItem("idAtividade","");
                        saida->addItem("dsAtividade","");
                        saida->addItem("status", 2);
                    saida->closeTag();
                }
                else
                {
                    ObterDetalheAtendNivel(idFase,detalheAtendimento.idFase);
                    //ObterGrupoAtual(2);
                }
            }
            else if ( detalheAtendimento.idTipoRetornoContato == TP_RET_COM_RET_GRP_RET )
            {
                if ( detalheAtendimento.idFase != RETORNO )
                { // se processo ainda não esta em retorno, mostra o grupo que 
                  // tem o maior potencial de tratar o processo quando o processo
                  // for encerrado.
                    char nmGrupo[256];
                    int idGrupoFaseRetorno = ObterNivelContatoFaseRetorno(nmGrupo);
                    if ( -1 == idGrupoFaseRetorno )
                    {
                        // Se cair aqui muito provavelmente durante a carga deste serviço
                        // a parametrizção foi alterada imediatamente após ter sido determinado
                        // que o retorno era por retorno por grupo de retorno e quando o método
                        // ObterNivelContatoFaseRetorno() tentou encontrar o grupo de retorno
                        // as vairáveis já não batiam mais e por isso o retorno seria dado por
                        // grupo de BKO e por isso o aviso de de grupo DEFAULT..
                        saida->createTag("NivelVO");
                            saida->addItem("idGrupo",0);
                            saida->addItem("nmGrupo","Retorno Grupo de BKO (DEFAULT)");
                            saida->addItem("nrNivel","");
                            saida->addItem("idAtividade","");
                            saida->addItem("dsAtividade","");
                            saida->addItem("status", 2);
                        saida->closeTag();
                    }
                    else
                    {
                        saida->createTag("NivelVO");
                            saida->addItem("idGrupo",idGrupoFaseRetorno);
                            saida->addItem("nmGrupo",nmGrupo);
                            saida->addItem("nrNivel","");
                            saida->addItem("idAtividade","");
                            saida->addItem("dsAtividade","");
                            saida->addItem("status", 2);
                        saida->closeTag();
                    }
                }
                else
                {
                    ObterDetalheAtendNivel(idFase,detalheAtendimento.idFase);
                    //ObterGrupoAtual(2);
                    // ObterHistoricoColunaRetorno();
                }
            }
            else if (detalheAtendimento.idTipoRetornoContato == TP_RET_COM_RET_GRP_CRI)
            {
                if ( detalheAtendimento.idFase != RETORNO )
                { // se processo ainda não esta em retorno, mostra o grupo que 
                    saida->createTag("NivelVO");
                        saida->addItem("idGrupo",0);
                        saida->addItem("nmGrupo","Retorno Grupo de CRI");
                        saida->addItem("nrNivel","");
                        saida->addItem("idAtividade","");
                        saida->addItem("dsAtividade","");
                        saida->addItem("status", 2);
                    saida->closeTag();
                }
                else
                {
                    ObterDetalheAtendNivel(idFase,detalheAtendimento.idFase);
                    //ObterGrupoAtual(2);
                }
            }
        }
        else
        {
            saida->createTag("NivelVO");
                saida->addItem("idGrupo",0);
                saida->addItem("nmGrupo","fase invalida!");
                saida->addItem("nrNivel","");
                saida->addItem("idAtividade","");
                saida->addItem("dsAtividade","");
                saida->addItem("status", 2);
            saida->closeTag();
        }
        saida->closeTag();
    }

    ULOG_END("cWFAtdDetFsSenEx::ObterFase()");
}

void cWFAtdDetFsSenEx::ObterDetalheAtendNivel(int idFase,int idFaseAtual)
{
    ULOG_START("cWFAtdDetFsSenEx::ObterDetalheAtendNivel()");

    cWFAtendimentoNivel cwfAtendimentoNivel;

    if ( !cwfAtendimentoNivel.ObterHistoricoAtendNivelEx(m_stDados.idAtendimento,idFase,idFaseAtual,saida) )
    {
        ULOG("cWFAtdDetFsSenEx::ObterDetalheAtendNivel():falhou execução");
    }

    ULOG_END("cWFAtdDetFsSenEx::ObterDetalheAtendNivel()");
}

void cWFAtdDetFsSenEx::PesquisarUsuGrpProxNivel()
{
    ULOG_START("cWFAtdDetFsSenEx::PesquisarUsuGrpProxNivel()");

    cWFUsuario cwfUsuario;
    int contadorLinhas;
    int tamSaida=0;
    st_VariaveisUsuario dados;

    memset(&dados,0,sizeof(dados));

    dados.idAtendimento = m_stDados.idAtendimento;
    dados.idSegmentacao = detalheAtendimento.idSegmentacao;
    dados.idContato = detalheAtendimento.idContato;
    dados.idTipoCarteira = detalheAtendimento.idTipoCarteira;
    dados.idProcedencia = detalheAtendimento.idProcedencia;
    dados.idGrupoAbertura = detalheAtendimento.idGrupoAbertura;
    dados.idTipoPessoa = m_stAtdDetFsSen.idTipoPessoa;
    dados.idTipoLinha = m_stAtdDetFsSen.idTipoLinha;
    dados.idTipoRelacionamento = m_stAtdDetFsSen.idTipoRel;
    dados.idCanal = detalheAtendimento.idCanal;
    dados.nrNivel = detalheAtendimento.nrNivel + 1;

    while ( true )
    {
        if ( cwfUsuario.pesqUsuGrpProxNivel(&dados,saida,&contadorLinhas) )
        {
            // Se não encontrou nada, faz pesquisa diferente
            ULOG("Linhas Encontradas: %d", contadorLinhas);
            if ( !contadorLinhas )
            {
                contadorLinhas = ObterNivGrAt(dados.nrNivel);
                ULOG("Linhas Encontradas=%d para o nivel %d", contadorLinhas, dados.nrNivel);
            }
        }
        else
        {
            ULOG("pesqUsuGrpProxNivel() falhou");
        }

        if ( !contadorLinhas ) 
        {
            break;
        }

        dados.nrNivel++;
    }

    ULOG_END("cWFAtdDetFsSenEx::PesquisarUsuGrpProxNivel()");
}

int cWFAtdDetFsSenEx::ObterNivGrAt(int nrNivel)
{
    ULOG_END("cWFAtdDetFsSenEx::ObterNivGrAt()");

    int contadorLinhas = 0;
    cWFAtendimentoNivel cwfAtendimentoNivel;

    if ( !cwfAtendimentoNivel.ObtemNivGrAt(m_stDados.idAtendimento, nrNivel, saida, &contadorLinhas) )
    {
         ULOG("cWFAtdDetFsSenEx::ObterNivGrAt():falhou");
    }

    ULOG_END("cWFAtdDetFsSenEx::ObterNivGrAt()");

    return contadorLinhas;
}

bool cWFAtdDetFsSenEx::ObterHistoricoColunaRetorno()
{
    ULOG_START("cWFAtdDetFsSenEx::ObterHistoricoColunaRetorno()");

    cWFAtendimentoNivel cwfAtendimentoNivel;

    bool retorno = cwfAtendimentoNivel.ObtemNivContato(detalheAtendimento.idContato,RETORNO,saida);

    ULOG_END("cWFAtdDetFsSenEx::ObterHistoricoColunaRetorno()");

    return retorno;
}

int cWFAtdDetFsSenEx::ObterNivelContatoFaseRetorno(char *nmGrupo)
{
    ULOG_START("cWFAtdDetFsSenEx::ObterNivelContatoFaseRetorno()");

    bool retorno = true;
    cWFUsuario cwfUsuario;

    st_VariaveisUsuario dadosUsuario;

    memset(&dadosUsuario, 0, sizeof(dadosUsuario));

    dadosUsuario.idContato=detalheAtendimento.idContato;
    dadosUsuario.idFase=RETORNO;
    dadosUsuario.idSegmentacao=detalheAtendimento.idSegmentacao;
    dadosUsuario.idTipoCarteira=detalheAtendimento.idTipoCarteira;
    dadosUsuario.idProcedencia=detalheAtendimento.idProcedencia;
    dadosUsuario.idGrupoAbertura=detalheAtendimento.idGrupoAbertura;
    dadosUsuario.idTipoPessoa=m_stAtdDetFsSen.idTipoPessoa;
    dadosUsuario.idTipoLinha=m_stAtdDetFsSen.idTipoLinha;
    dadosUsuario.idTipoRelacionamento=m_stAtdDetFsSen.idTipoRel,
    dadosUsuario.idCanal=detalheAtendimento.idCanal;
    dadosUsuario.idUFOperadora=detalheAtendimento.idUFOperadora;

    int idGrupoFaseRetorno = cwfUsuario.obterGrupoRetornoGrupoRetorno(&dadosUsuario,nmGrupo);

    ULOG_END("cWFAtdDetFsSenEx::ObterNivelContatoFaseRetorno()");

    return idGrupoFaseRetorno;
}

void cWFAtdDetFsSenEx::ObterGrupoAtual(int status)
{
    ULOG_START("cWFAtdDetFsSenEx::ObterGrupoAtual()");

    int idGrupoAtual;
    char nmGrupoAtual[256];
    cWFAtendimentoGrupoAtual cwfatendimentogrupoatual;

    saida->createTag("NivelVO");

    if ( cwfatendimentogrupoatual.ObtemGrAt(m_stDados.idAtendimento,&idGrupoAtual,nmGrupoAtual) )
    {
        saida->addItem("idGrupo",idGrupoAtual);
        saida->addItem("nmGrupo",nmGrupoAtual);
    }
    else
    {
        saida->addItem("idGrupo","??");
        saida->addItem("nmGrupo","????????????");
    }

        saida->addItem("nrNivel","");
        saida->addItem("idAtividade","");
        saida->addItem("dsAtividade","");
        saida->addItem("status", status);
    saida->closeTag();

    ULOG_END("cWFAtdDetFsSenEx::ObterGrupoAtual()");
}

bool cWFAtdDetFsSenEx::ObterDetalheAtd()
{
    ULOG_START("cWFAtdDetFsSenEx::ObterDetalheAtd()");

    bool retorno = false;
    cWFAtendimento cwfAtendimento;
    AtendimentoPessoa dadosAtendimentoPessoa;

    if ( cwfAtendimento.ObtemDetalheAtendEx(m_stDados.idAtendimento,&detalheAtendimento) )
    {
        retorno = true;

        if ( strlen(detalheAtendimento.dataFechamento) )
        {
            m_vlAtdDetFsSen.dtFechamento = 1;
        }

        cWFAtendimentoPessoa cwfAtendimentoPessoa;

        if ( cwfAtendimentoPessoa.ObtemAtendPessoaEx(m_stDados.idAtendimento,&dadosAtendimentoPessoa) )
        {
            m_stAtdDetFsSen.idTipoPessoa = dadosAtendimentoPessoa.idTipoPessoa;
            m_vlAtdDetFsSen.idTipoPessoa = 1;

            m_stAtdDetFsSen.idTipoRel = dadosAtendimentoPessoa.inRspAbertura;
            m_vlAtdDetFsSen.idTipoRel = 1;
        }

        cWFAtendimentoLinha cwfatendimentolinha;
        LinhaAtendimento linhaatendimento;

        if ( cwfatendimentolinha.ObtemDetalheAtendEx(m_stDados.idAtendimento,&linhaatendimento) )
        {
            if ( linhaatendimento.idPessoaLinhaHistorico > 0 )
            {
                proCObterIdTipoLinha(&m_stAtdDetFsSen.idTipoLinha,linhaatendimento.idPessoaLinhaHistorico);
            }
            else
            {
                m_stAtdDetFsSen.idTipoLinha = 1;
            }
            m_vlAtdDetFsSen.idTipoLinha = 1;
        }
    }

    ULOG_END("cWFAtdDetFsSenEx::ObterDetalheAtd()");

    return retorno;
}

void cWFAtdDetFsSenEx::carregaDados()
{
    char *p;

    if (p=tx.walkTree( entrada, "idAtendimento", 0 ),p) 
    {
        m_stDados.idAtendimento = atol(p);
        m_vlDados.idAtendimento = 1;
        XMLString::release(&p);
    }

    if (p=tx.walkTree( entrada, "idEstado", 0 ),p) 
    {
        m_stDados.idEstado = atoi(p);
        m_vlDados.idEstado = 1;
        XMLString::release(&p);
    }

    if (p=tx.walkTree( entrada, "idSubEstado", 0 ),p) 
    {
        m_stDados.idSubEstado = atoi(p);
        m_vlDados.idSubEstado = 1;
        XMLString::release(&p);
    }

    ULOG("idAtendimento=%d",m_stDados.idAtendimento);
    ULOG("idEstado=%d",m_stDados.idEstado);
    ULOG("idSubEstado=%d",m_stDados.idSubEstado);
}


////////////////////////////////////////////////////////////////////////////////////////////////
// OPERAÇÕES DE ACESSO A MÉTODOS DOM / XMLGEN
//
char *ObterValorTag::_ObterValorTag(char *ss,const char *idTag)
{
    char *p=0;
    MemBufInputSource *pMemBuf;
    XercesDOMParser *pParser;

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

    int tamSaida = ssLocal.Size();

    pParser = new XercesDOMParser;
    pMemBuf = new MemBufInputSource((const XMLByte*)ssLocal.c_str(), tamSaida, gerarIDDom());

    if ( pParser && pMemBuf )
    {
        pParser->parse(*pMemBuf);
        DOMNode* pDoc = pParser->getDocument();

        char *valorResult = tx.walkTree(pDoc,(char*)idTag,0);

        if ( valorResult && *valorResult ) 
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
            ULOG("ObterValorTag(0): valor da tag '%s'=%d",idTag,valorResult);

            XMLString::release(&valorResult);
        }
    }
    else
    {
        p = "Nao foi possivel alocar memoria para objetos DOM e/ou Xerces";
    }

    delete pParser;
    delete pMemBuf;

    if ( p )
    {
        ULOGE((p));
        throw new TuxBasicSvcException("00E0000",(p));
    }

    return valor;
}

////////////////////////////////////////////////////////////////////////////////////////////////
char *ObterValorTag::_ObterValorTag(SmallString *ss,const char *idTag)
{
    char *p=0;
    MemBufInputSource *pMemBuf;
    XercesDOMParser *pParser;

    valor = 0;

    if ( !ss || !ss->Size() )
    {
        return 0;
    }

    SmallString ssLocal;

    if ( !strstr(ss->c_str(),"encoding") )
    {
        ssLocal += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>";
    }

    ssLocal += (char*)ss->c_str();

    ULOG("%s",ssLocal.c_str());
    int tamSaida = ssLocal.Size();

    pParser = new XercesDOMParser;
    pMemBuf = new MemBufInputSource((const XMLByte*)ssLocal.c_str(),tamSaida,gerarIDDom());

    if ( pParser && pMemBuf )
    {
        pParser->parse(*pMemBuf);
        DOMNode* pDoc = pParser->getDocument();

        char *valorResult = tx.walkTree(pDoc,(char*)idTag,0);

        if ( valorResult && *valorResult ) 
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
            ULOG("ObterValorTag(1): valor da tag '%s'=%d"
                        ,idTag,valorResult);

            XMLString::release(&valorResult);
        }
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
        throw new TuxBasicSvcException("00E0000",(p));
    }

    return valor;
}

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

            if ( valorResult && *valorResult ) 
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
                ULOG ("ObterValorTag(2): valor da tag '%s'=%d"
                            ,idTag,valorResult);

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
        ULOGE((p));
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

        if ( valorResult && *valorResult ) 
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
            ULOG("ObterValorTag(3): valor da tag '%s'=%d"
                        ,idTag,valorResult);

            XMLString::release(&valorResult);
        }
    }
    else
    {
        p = "Ponteiro invalido passado como parametro de entrada";
    }

    if ( p )
    {
        ULOGE((p));
        throw new TuxBasicSvcException("00E0000",(p));
    }

    return valor;
}

// Fim
////////////////////////////////////////////////////////////////////////////////////////////////
