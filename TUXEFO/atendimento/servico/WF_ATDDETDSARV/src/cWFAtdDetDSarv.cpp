/**
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:19 $
 **/

#include "../include/cWFAtdDetDSarv.h"

#include "../../Atendimento/include/cWFAtendimento.h"

extern bool proCObterPath( int _idContato, XMLGen *saida );

cWFAtdDetDSarv::cWFAtdDetDSarv(DOMNode *dnode, XMLGen *xml_g)
{
    entrada = dnode;
    saida = xml_g;

    carregaDados();
}

bool cWFAtdDetDSarv::executar(char **codErro,char **msgErro)
{
    bool retorno;
    int idContato;

    if ( m_vlDados.idAtendimento == -1 )
    {
        *msgErro = "O codigo de 'idAtendimento' eh obrigatorio";

        return false;
    }

    saida->createTag("StringComumVO ");

        saida->addProp("xmlns","workflow.fo.vivo.com.br/vo");

        retorno = ConsultarAtendimento(idContato);

        if ( retorno )
        {
            proCObterPath( idContato, saida );
        }

    saida->closeTag();

    return retorno;
}

bool cWFAtdDetDSarv::ConsultarAtendimento(int &idContato)
{
    ULOG_START("cWFAtdDetDSarv::ConsultarAtendimento()");

    bool retorno = false;
    XMLGen saidaAtd;
    cWFAtendimento cwfAtendimento(entrada,&saidaAtd);
    int tamSaida;

    if ( cwfAtendimento.consultar() )
    {
        char *p = saidaAtd.retrieveXML(&tamSaida);

        if ( tamSaida )
        {
            SmallString xmlTemp;
            xmlTemp = p;

            idContato=atoi(ObterValorTag(&xmlTemp,"idContato"));

            retorno = true;
        }
        else
        {
            ULOG("consultar não gerou xml de saida");
        }
    }
    else
    {
        ULOG("consultar falhou");
    }

    ULOG_END("cWFAtdDetDSarv::ConsultarAtendimento()");

    return retorno;
}

void cWFAtdDetDSarv::carregaDados()
{
    ULOG_START("cWFAtdDetDSarv::carregaDados()");

    char *p;

    memset(&m_stDados,0,sizeof(m_stDados));
    memset(&m_vlDados,-1,sizeof(m_vlDados));

    if (p=tx.walkTree( entrada, "idAtendimento", 0 ),p) 
    {
        m_stDados.idAtendimento = atol(p);
        m_vlDados.idAtendimento = 1;
		XMLString::release(&p);
    }

    ULOG("idAtendimento=%lu",m_stDados.idAtendimento);

    ULOG_END("cWFAtdDetDSarv::carregaDados()");
}

char *ObterValorTag::_ObterValorTag(SmallString *ss,const char *idTag)
{
    char *p=0;
    MemBufInputSource *pMemBuf;
    XercesDOMParser *pParser;

    valor = 0;

    if ( !ss )
    {
        return 0;
    }

    int tamSaida = ss->Size();

    pParser = new XercesDOMParser;
    pMemBuf = new MemBufInputSource((const XMLByte*)(char*)ss->c_str(), tamSaida, gerarIDDom());

    if ( pParser && pMemBuf )
    {
        pParser->parse(*pMemBuf);
        DOMNode* pDoc = pParser->getDocument();

        char *valorResult = tx.walkTree(pDoc,(char*)idTag,0);

        if ( valorResult ) 
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
            ULOG("ObterValorTag: valor da tag '%s'=%d" ,idTag,valorResult);

            XMLString::release(&valorResult);
        }
        else
        {
            ULOG("ObterValorTag: valor da tag '%s' NAO ENCONTRADO",idTag);

            valor = new char [ 2 ];
            if ( valor )
            {
                *valor = '\0';
            }
            else
            {
                p = "Erro de alocação de memória";
            }
        }
    }
    else
    {
        p = "Nao foi possivel alocar memoria para objetos DOM e/ou Xerces";
    }

    if ( pParser ) { delete pParser; }
    if ( pMemBuf ) { delete pMemBuf; }

    if ( p )
    {
        ULOGE(p);
        throw new TuxBasicSvcException("00E0000",p);
    }

    return valor;
}
