/**
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:01 $
 **/

#include "../include/cWFAtdGrpEstado.h"

#include "../../Usuario/include/cWFUsuario.h"
#include "../../Estado/include/cWFEstado.h"

cWFAtdGrpEstado::cWFAtdGrpEstado(DOMNode *dnode, XMLGen *xml_g)
{
    entrada = dnode;
    saida = xml_g;

    carregaDados();
}

bool cWFAtdGrpEstado::Executar()
{
    bool retorno;

    ULOG_START("cWFAtdGrpEstado::Executar()");

    char *p = tx.walkTree( entrada, "idPessoaUsuario", 0 );

    if ( !p )
    {
        SetarErro(NULL,"Valor do 'idPessoaUsuario' é obrigatório e não foi fornecido");
        ULOG(ObterMsgErro());
        return false;
    }

    XMLString::release(&p);

    saida->createTag("AtendimentoInformacaoVO ");
        saida->addProp("xmlns","workflow.fo.vivo.com.br/vo");
        saida->addProp("xmlns:ns2","admsistemas.fo.vivo.com.br/vo");
        saida->addProp("xmlns:ns3","usuario.fo.vivo.com.br/vo");

        if ( retorno = ConsultarWFGruposFilaProcesso(),retorno )
        {
            retorno = ConsultarEstado();
        }

    saida->closeTag();

    ULOG_END("cWFAtdGrpEstado::ConsultarEstado()");

    return retorno;
}

bool cWFAtdGrpEstado::ConsultarWFGruposFilaProcesso()
{
    ULOG_START("cWFAtdGrpEstado::ConsultarWFGruposFilaProcesso()");

    bool retorno;
    cWFUsuario cwfUsuario(entrada,saida);
    MemBufInputSource *pMemBuf;
    XercesDOMParser *pParser;
    SmallString xmlParam;
    int tamParam;

    xmlParam += "<PesquisaGrupoFilaProcesso>";
    xmlParam += "<idPessoaUsuario>";
    xmlParam += obterIdUsuario();
    xmlParam += "</idPessoaUsuario>";
    xmlParam += "</PesquisaGrupoFilaProcesso>";

    tamParam = xmlParam.Size();

    pParser = new XercesDOMParser;
    pMemBuf = new MemBufInputSource((const XMLByte*)xmlParam.c_str(),tamParam,gerarIDDom());

    if ( pParser && pMemBuf )
    {
        pParser->parse(*pMemBuf);
        DOMNode* pDoc = pParser->getDocument();

	    retorno = cwfUsuario.consultarWFGruposFilaProcesso();

        if ( !retorno )
        {
            SetarErro(NULL,"cWFUsuario::consultarWFGruposFilaProcesso falhou!");
            ULOGE(ObterMsgErro());
            retorno = false;
        }
    }
    else
    {
        SetarErro(NULL,"Não foi possivel alocar memória para objetos DOM e/ou Xerces");
        ULOGE(ObterMsgErro());
        retorno = false;
    }

    delete pParser;
    delete pMemBuf;

    ULOG_END("cWFAtdGrpEstado::ConsultarWFGruposFilaProcesso()");

    return retorno;
}

bool cWFAtdGrpEstado::ConsultarEstado()
{
    ULOG_START("cWFAtdGrpEstado::ConsultarEstado()");

    bool retorno;
    MemBufInputSource *pMemBuf;
    XercesDOMParser *pParser;
    SmallString xmlParam;
    int tamParam;

    xmlParam = "<inFiltro>1</inFiltro>";

    tamParam = xmlParam.Size();

    pParser = new XercesDOMParser;
    pMemBuf = new MemBufInputSource((const XMLByte*)xmlParam.c_str(),tamParam,gerarIDDom());

    if ( pParser && pMemBuf )
    {
        pParser->parse(*pMemBuf);
        DOMNode* pDoc = pParser->getDocument();

        cWFEstado cwfEstado(pDoc,saida);

	    retorno = cwfEstado.consultar();

        if ( !retorno )
        {
            SetarErro(NULL,"cWFEstado::consultar falhou!");
            ULOGE(ObterMsgErro());
            retorno = false;
        }
    }
    else
    {
        SetarErro(NULL,"Não foi possivel alocar memória para objetos DOM e/ou Xerces");
        ULOGE(ObterMsgErro());
        retorno = false;
    }

    delete pParser;
    delete pMemBuf;

    ULOG_END("cWFAtdGrpEstado::ConsultarEstado()");

    return retorno;
}
