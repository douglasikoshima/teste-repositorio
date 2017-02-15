/**
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:36 $
 **/

#include "../include/cWFAtdObtReprese.h"

#include "../../Usuario/include/cWFUsuario.h"

cWFAtdObtReprese::cWFAtdObtReprese(DOMNode *dnode, XMLGen *xml_g)
{
    entrada = dnode;
    saida = xml_g;

    carregaDados();
}

bool cWFAtdObtReprese::Executar()
{
    bool retorno;
    char *p = tx.walkTree( entrada, "idGrupo", 0 );
    XMLGen saidaPesqGrp;

    if ( !p )
    {
        SetarErro(NULL,"Valor do 'idGrupo' é obrigatório e não foi fornecido");
        ULOGE(ObterMsgErro());
        return false;
    }

    int idGrupo = atoi(p);

    XMLString::release(&p);

    if ( retorno = PesquisarUsuarioPorGrupo(idGrupo,&saidaPesqGrp),retorno )
    {
        retorno = MontarXMLSaida(&saidaPesqGrp);
    }

    return retorno;
}

bool cWFAtdObtReprese::PesquisarUsuarioPorGrupo(int idGrupo,XMLGen *saidaPesqGrp)
{
    ULOG_START("cWFAtdObtReprese::PesquisarUsuarioPorGrupo()");

    bool retorno;
    cWFUsuario cwfUsuario;
    MemBufInputSource *pMemBuf;
    XercesDOMParser *pParser;
    XMLGen saidaDocTecnico;
    int tamXMLParam;
    SmallString xmlParam;

    xmlParam += "<ObtemRepresentantesVO>";
    xmlParam += "<idGrupo>";
    xmlParam += idGrupo;
    xmlParam += "</idGrupo>";
    xmlParam += "</ObtemRepresentantesVO>";

    tamXMLParam = xmlParam.Size();

    pParser = new XercesDOMParser;
    pMemBuf = new MemBufInputSource((const XMLByte*)xmlParam.c_str(), tamXMLParam, gerarIDDom());

    if ( pParser && pMemBuf )
    {
        pParser->parse(*pMemBuf);
        DOMNode* pDoc = pParser->getDocument();

        retorno = cwfUsuario.pesqUsuarioPorGrupo(pDoc,saidaPesqGrp);

        if ( !retorno )
        {
            SetarErro(0,"cWFUsuario::pesqUsuarioPorGrupo falhou execução");
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

    ULOG_END("cWFAtdObtReprese::PesquisarUsuarioPorGrupo()");

    return retorno;
}

bool cWFAtdObtReprese::MontarXMLSaida(XMLGen *saidaPesqGrp)
{
    ULOG_START("cWFAtdObtReprese::MontarXMLSaida()");
    bool retorno = true;
    char *_idPessoaUsuario;
    char *_nmLoginUsuario;
    DOMNode *dn;
    int index = 0;
    int tamXmlSaida;
    MemBufInputSource *pMemBuf;
    TuxHelper tx;
    XercesDOMParser *pParser;
    SmallString xmlSaida;

    

    if ( !saidaPesqGrp )
    {
        SetarErro(NULL,"Ponteiro invalido");
        ULOGE(ObterMsgErro());

        ULOG_END("cWFAtdObtReprese::MontarXMLSaida()");

        return false;
    }

    xmlSaida += "<PesquisaGrupoUsuarioVO>";
	xmlSaida += saidaPesqGrp->retrieveXML(&tamXmlSaida);
    xmlSaida += "</PesquisaGrupoUsuarioVO>";

    tamXmlSaida = xmlSaida.Size();

    saida->createTag("WFRelatoriosFiltroRepresentanteVO");
    saida->addProp("xmlns","workflow.fo.vivo.com.br/vo");

    pParser = new XercesDOMParser;
    pMemBuf = new MemBufInputSource((const XMLByte*)xmlSaida.c_str(),tamXmlSaida,gerarIDDom());

    if ( pParser && pMemBuf )
    {
        pParser->parse(*pMemBuf);
        DOMNode* pDoc = pParser->getDocument();

        while ( dn=tx.walkDOM(pDoc,"UsuarioVIVO",index++ ) )
        {
            _idPessoaUsuario = tx.walkTree(dn,"idPessoaUsuario",0);
            _nmLoginUsuario = tx.walkTree(dn,"nmLoginUsuario",0);

            saida->createTag("WFRelatoriosFiltroRepresentanteVO");
				saida->addItem("idRepresentante",_idPessoaUsuario);
				saida->addItem("dsRepresentante",_nmLoginUsuario);
            saida->closeTag();

            XMLString::release(&_idPessoaUsuario);
            XMLString::release(&_nmLoginUsuario);
        }
    }
    else
    {
        SetarErro(NULL,"Não foi possivel alocar memória para objetos DOM e/ou Xerces");
        ULOGE(ObterMsgErro());
        retorno = false;
    }

    saida->closeTag();

    delete pParser;
    delete pMemBuf;

    ULOG_END("cWFAtdObtReprese::MontarXMLSaida()");

    return retorno;
}
