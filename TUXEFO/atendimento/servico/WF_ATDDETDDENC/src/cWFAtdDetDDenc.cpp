/**
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:19 $
 **/

#include "../include/cWFAtdDetDDenc.h"

#include "../../AtendimentoBaixa/include/cWFAtendimentoBaixa.h"
#include "../../AtendimentoBaixaAtual/include/cWFAtendimentoBaixaAtual.h"

cWFAtdDetDDenc::cWFAtdDetDDenc(DOMNode *dnode, XMLGen *xml_g)
{
    entrada = dnode;
    saida = xml_g;

    memset(&m_stDados,0,sizeof(m_stDados));
    memset(&m_vlDados,-1,sizeof(m_vlDados));

    memset(&m_stAtdDetDDenc,0,sizeof(m_stAtdDetDDenc));
    memset(&m_vlAtdDetDDenc,-1,sizeof(m_vlAtdDetDDenc));

    memset(&m_stAtdBaixa,0,sizeof(m_stAtdBaixa));
    memset(&m_vlAtdBaixa,-1,sizeof(m_vlAtdBaixa));

    memset(&m_stGrupoAtendimento,0,sizeof(m_stGrupoAtendimento));
    memset(&m_vlGrupoAtendimento,-1,sizeof(m_vlGrupoAtendimento));

    carregaDados();
}

bool cWFAtdDetDDenc::executar(char **codErro,char **msgErro)
{
    if ( m_vlDados.idAtendimento == -1 )
    {
        *msgErro = "O código de 'idAtendimento' é obrigatório";

        return false;
    }

    saida->createTag("EncerramentoVO");
    saida->addProp("xmlns", "admsistemas.fo.vivo.com.br/vo");

	    ObterAtdBxaAtual();

    saida->closeTag();

    return true;
}

void cWFAtdDetDDenc::ObterAtdBxaAtual()
{
    ULOG_START("cWFAtdDetDDenc::ObterAtdBxaAtual()");
    int tamSaida;
    XMLGen saidaAtd;
    cWFAtendimentoBaixaAtual cwfAtendimentoBaixaAtual(entrada,&saidaAtd);

    
    if ( !cwfAtendimentoBaixaAtual.obterAtdBxA() )
    {
        ULOG("obterAtdBxA() falhou execução");
        return;
    }

    char *xml = saidaAtd.retrieveXML(&tamSaida);

    if ( !tamSaida )
    {
        ULOG("obterAtdBxA() não gerou xml de saida");
        return;
    }

    SmallString xmlSaida;
    xmlSaida += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>";
    xmlSaida += xml;

    tamSaida = xmlSaida.Size();

    XercesDOMParser *pParser = new XercesDOMParser;
    MemBufInputSource *pMemBuf = new MemBufInputSource((const XMLByte*)xmlSaida.c_str(),tamSaida,gerarIDDom());

    if ( pParser && pMemBuf )
    {
        xml = 0;

        pParser->parse(*pMemBuf);
        DOMNode* pDoc = pParser->getDocument();

        char *_idBaixa = tx.walkTree(pDoc,"idBaixa",0);
        char *_idBaixaMensagem = tx.walkTree(pDoc,"idBaixaMensagem",0);
        char *_dsMensagem = tx.walkTree(pDoc,"dsMensagem",0);
        char *_idFormaRetorno = tx.walkTree(pDoc,"idFormaRetorno",0);
        char *_dsFormaRetorno = tx.walkTree(pDoc,"dsFormaRetorno",0);
        char *_dsComentario = tx.walkTree(pDoc,"dsComentario",0);

        if ( _idBaixa )
        {
             ObterAtdBxax(atoi(_idBaixa), _idBaixaMensagem, _dsMensagem, _idFormaRetorno, _dsFormaRetorno, _dsComentario);
        }
        else
        {
            xml = "cWFAtendimentoBaixaAtual::obterAtdBxA() não retornou xml valido";
        }

		if (_idBaixa)
			XMLString::release(&_idBaixa);
		if (_idBaixaMensagem)
			XMLString::release(&_idBaixaMensagem);
		if (_dsMensagem)
			XMLString::release(&_dsMensagem);
		if (_idFormaRetorno)
			XMLString::release(&_idFormaRetorno);
		if (_dsFormaRetorno)
			XMLString::release(&_dsFormaRetorno);
		if (_dsComentario)
			XMLString::release(&_dsComentario);

    }
    else
    {
        xml = "Nao foi possivel alocar memoria para objetos DOM e/ou Xerces";
    }

    delete pParser;
    delete pMemBuf;

    if ( xml )
    {
        ULOGE("%s",mensagemSimples(xml));
        ULOG_END("cWFAtdDetDDenc::ObterAtdBxaAtual()");
        throw new TuxBasicSvcException("00E0000",xml);
    }
    ULOG_END("cWFAtdDetDDenc::ObterAtdBxaAtual()");
}

void cWFAtdDetDDenc::ObterAtdBxax(int idBaixa, char* idBaixaMensagem, char* dsMensagem, char* idFormaRetorno, char* dsFormaRetorno, char* dsComentario)
{
    ULOG_START("cWFAtdDetDDenc::ObterAtdBxax()");
    int tamSaida;
    XMLGen saidaAtd;
    XMLGen tmp;
    cWFAtendimentoBaixa cwfAtendimentoBaixa(entrada, &tmp);

    

    ULOG("Obtemos IdBaixa: %d",idBaixa);

    if ( !cwfAtendimentoBaixa.obtemAtdHrq(idBaixa,&saidaAtd) )
    {
        ULOG("obtemAtdHrq() falhou execução");
        ULOG_END("cWFAtdDetDDenc::ObterAtdBxax()");
        return;
    }

    char *xml = saidaAtd.retrieveXML(&tamSaida);

    if ( !tamSaida )
    {
        ULOG("obtemAtdHrq() não gerou xml de saida");
        ULOG_END("cWFAtdDetDDenc::ObterAtdBxax()");
        return;
    }

    SmallString xmlSaida;
    xmlSaida += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>";
    xmlSaida += xml;

    tamSaida = xmlSaida.Size();

    XercesDOMParser *pParser = new XercesDOMParser;
    MemBufInputSource *pMemBuf = new MemBufInputSource((const XMLByte*)xmlSaida.c_str(),tamSaida,gerarIDDom());

    if ( pParser && pMemBuf )
    {
        xml = 0;

        pParser->parse(*pMemBuf);
        DOMNode* pDoc = pParser->getDocument();

        char *nmBaixa = tx.walkTree(pDoc,"nmBaixa",0);
        char *idBaixa = tx.walkTree(pDoc,"idBaixa",0);

        if ( nmBaixa && idBaixa )
        {
			saida->addItem("dsBaixa",		nmBaixa);
			saida->addItem("idBaixa",		idBaixa);
			saida->addItem("idBaixaMensagem", idBaixaMensagem);
			saida->addItem("dsMensagem",		dsMensagem);
			saida->addItem("idFormaRetorno", idFormaRetorno);
			saida->addItem("dsFormaRetorno", dsFormaRetorno);
			saida->addItem("dsComentario", dsComentario);
        }
        else
        {
            xml = "cWFAtendimentoBaixa::obtemAtdHrq() não retornou xml valido";
        }

		if (nmBaixa)
			XMLString::release(&nmBaixa);

		if (idBaixa)
			XMLString::release(&idBaixa);

    }
    else
    {
        xml = "Nao foi possivel alocar memoria para objetos DOM e/ou Xerces";
    }

    delete pParser;
    delete pMemBuf;

    if ( xml )
    {
        ULOGE("%s",mensagemSimples(xml));
        throw new TuxBasicSvcException("00E0000",xml);
    }
    ULOG_END("cWFAtdDetDDenc::ObterAtdBxax()");
}

void cWFAtdDetDDenc::carregaDados()
{
    char *p;

    if (p=tx.walkTree( entrada, "idAtendimento", 0 ),p) 
    {
        m_stDados.idAtendimento = atol(p);
        m_vlDados.idAtendimento = 1;
		XMLString::release(&p);
    }

    ULOG("idAtendimento=%d",m_stDados.idAtendimento);
}

