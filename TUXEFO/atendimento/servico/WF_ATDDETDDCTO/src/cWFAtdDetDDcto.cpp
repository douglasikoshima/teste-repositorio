/**
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:19 $
 **/

#include "../include/cWFAtdDetDDcto.h"

#include "../../AtendimentoConta/include/cWFAtendimentoConta.h"
#include "../../AtendimentoContato/include/cWFAtendimentoContato.h"
#include "../../AtendimentoContatoComunic/include/cWFAtendimentoContatoComunic.h"
#include "../../AtendimentoLinha/include/cWFAtendimentoLinha.h"

cWFAtdDetDDcto::cWFAtdDetDDcto(DOMNode *dnode, XMLGen *xml_g)
{
    entrada = dnode;
    saida = xml_g;

    memset(&m_stDados,0,sizeof(m_stDados));
    memset(&m_vlDados,-1,sizeof(m_vlDados));

    memset(&m_stAtdDetDDcto,0,sizeof(m_stAtdDetDDcto));
    memset(&m_vlAtdDetDDcto,-1,sizeof(m_vlAtdDetDDcto));

    memset(&m_stAtdBaixa,0,sizeof(m_stAtdBaixa));
    memset(&m_vlAtdBaixa,-1,sizeof(m_vlAtdBaixa));

    memset(&m_stGrupoAtendimento,0,sizeof(m_stGrupoAtendimento));
    memset(&m_vlGrupoAtendimento,-1,sizeof(m_vlGrupoAtendimento));

    carregaDados();
}

bool cWFAtdDetDDcto::executar(char **codErro,char **msgErro)
{
    if ( m_vlDados.idAtendimento == -1 )
    {
        *msgErro = "O código de 'idAtendimento' é obrigatório";

        return false;
    }

    saida->createTag("RDContatoVO");
        saida->addProp("xmlns", "workflow.fo.vivo.com.br/vo");
        saida->addProp("xmlns:ns2", "admsistemas.fo.vivo.com.br/vo");
        saida->addProp("xmlns:ns3", "usuario.fo.vivo.com.br/vo");
        saida->addProp("xmlns:ns4", "cliente.fo.vivo.com.br/vo");

			ObterAtdCtoConsultar(saida);

			ObterAtdCntConsultar(saida);

			ObterAtdDetalheLinha(saida);

			ObterWFAtdCnC(saida);

    saida->closeTag();

    return true;
}

void cWFAtdDetDDcto::ObterWFAtdCnC(XMLGen* _montagem)
{
    ULOG_START("cWFAtdDetDDcto::ObterWFAtdCnC()");
    XMLGen saida;
    cWFAtendimentoContatoComunic cWFAtendimentoContatoComunic(entrada, _montagem);

    cWFAtendimentoContatoComunic.obtemWFAtdCnC();
    
    ULOG_END("cWFAtdDetDDcto::ObterWFAtdCnC()");

}

void cWFAtdDetDDcto::ObterAtdCtoConsultar(XMLGen* _montagem)
{
    ULOG_START("cWFAtdDetDDcto::ObterAtdCtoConsultar()");
    int tamSaida;
    XMLGen saida;
    cWFAtendimentoContato cwfAtendimentoContato(entrada, &saida);

   

    if ( cwfAtendimentoContato.consultar() )
    {
        char *xml = saida.retrieveXML(&tamSaida);

        SmallString xmlAtdCto;

        xmlAtdCto += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>";
        xmlAtdCto += xml;

        tamSaida = xmlAtdCto.Size();

		if ( tamSaida )
        {
            XercesDOMParser *pParser = new XercesDOMParser;
            MemBufInputSource *pMemBuf = new MemBufInputSource((const XMLByte*)xmlAtdCto.c_str(),tamSaida,gerarIDDom());

            if ( pParser && pMemBuf )
            {
                xml = 0;

                pParser->parse(*pMemBuf);
                DOMNode* pDoc = pParser->getDocument();

                char *_nmContato = tx.walkTree(pDoc,"nmContato",0);
                char *_nrTelefoneContato = tx.walkTree(pDoc,"nrTelefoneContato",0);
                char *_nmFalandoCom = tx.walkTree(pDoc,"nmFalandoCom",0);

                if ( _nmContato && _nrTelefoneContato && _nmFalandoCom )
                {
                    nmContato = _nmContato;

                    _montagem->addItem("nmContato", _nmContato);

					_montagem->addItem("nrTelefone", _nrTelefoneContato);

                    _montagem->addItem("nmFalandoCom", _nmFalandoCom);

                   ULOG("cWFAtendimentoContato::consultar() retornou: "
                                              "nmContato='%s',nrTelefoneContato='%s'"
                        ,_nmContato,_nrTelefoneContato);
                }
                else
                {
                    xml = "cWFAtendimentoContato::consultar() não retornou xml valido";
                }

				if (_nmContato)
					XMLString::release(&_nmContato);

				if (_nrTelefoneContato)
					XMLString::release(&_nrTelefoneContato);

				if (_nmFalandoCom)
					XMLString::release(&_nmFalandoCom);
            }
            else
            {
                xml = "Nao foi possivel alocar memoria para objetos DOM e/ou Xerces";
            }

            if ( pParser ) { delete pParser; }
            if ( pMemBuf ) { delete pMemBuf; }

            if ( xml )
            {
                ULOG(xml);
                ULOG_END("cWFAtdDetDDcto::ObterAtdCtoConsultar()");
                throw new TuxBasicSvcException("07E0000",xml);
            }
        }
        else
        {
            ULOG("consultar() nao gerou xml de saida");
        }
    }
    else
    {
        ULOG("consultar() falhou execução");
    }
    ULOG_END("cWFAtdDetDDcto::ObterAtdCtoConsultar()");
}

void cWFAtdDetDDcto::ObterAtdCntConsultar(XMLGen* _montagem)
{
    ULOG_START("cWFAtdDetDDcto::ObterAtdCntConsultar()");
    char *xml;
    int tamSaida=0;
    XMLGen saida;
    cWFAtendimentoConta cwfAtendimentoConta(entrada, &saida);

    if ( cwfAtendimentoConta.consultar() )
    {
        xml = saida.retrieveXML(&tamSaida);
    }

    if ( tamSaida )
    {
        XercesDOMParser *pParser = new XercesDOMParser;
        MemBufInputSource *pMemBuf = new MemBufInputSource((const XMLByte*)xml,tamSaida,gerarIDDom());

        if ( pParser && pMemBuf )
        {
            xml = 0;

            pParser->parse(*pMemBuf);
            DOMNode* pDoc = pParser->getDocument();

            char *codConta = tx.walkTree(pDoc,"cdConta",0);
            char *cdDigitoConta = tx.walkTree(pDoc,"cdDigitoConta",0);

            if ( codConta )
            {
				_montagem->addItem("nrConta", codConta);
				_montagem->addItem("nrDigitoConta", cdDigitoConta?cdDigitoConta:"");

            }
            else
            {
                xml = "cWFAtendimentoConta::consultar() não retornou xml valido";
            }

			if (codConta)
				XMLString::release(&codConta);

			if (cdDigitoConta)
				XMLString::release(&cdDigitoConta);

        }
        else
        {
            xml = "Nao foi possivel alocar memoria para objetos DOM e/ou Xerces";
        }

        if ( pParser ) { delete pParser; }
        if ( pMemBuf ) { delete pMemBuf; }

        if ( xml )
        {
            ULOGE(xml);
            ULOG_END("cWFAtdDetDDcto::ObterAtdCntConsultar()");
            throw new TuxBasicSvcException("07E0000",xml);
        }
    }
    else
    {
        ULOG("consultar() nao gerou xml de saida");
    }
    ULOG_END("cWFAtdDetDDcto::ObterAtdCntConsultar()");

}

void cWFAtdDetDDcto::ObterAtdDetalheLinha(XMLGen* _montagem)
{
    ULOG_START("cWFAtdDetDDcto::ObterAtdDetalheLinha()");
    
    char temp[512] = "";
    char *xml;
    int tamSaida=0;
    XMLGen saida;
    cWFAtendimentoLinha cWFAtendimentoLinha(entrada, &saida);


    if ( cWFAtendimentoLinha.ObtemDetalheAtend() )
    {
        xml = saida.retrieveXML(&tamSaida);
    }

    if ( tamSaida )
    {
        XercesDOMParser *pParser = new XercesDOMParser;
        MemBufInputSource *pMemBuf = new MemBufInputSource((const XMLByte*)xml,tamSaida,gerarIDDom());

        if ( pParser && pMemBuf )
        {
            xml = 0;

            pParser->parse(*pMemBuf);
            DOMNode* pDoc = pParser->getDocument();

            char *nrLinha = tx.walkTree(pDoc,"nrLinha",0);
            char *cdAreaRegistro = tx.walkTree(pDoc,"cdAreaRegistro",0);

            if ( nrLinha )
            {
				_montagem->addItem("nrLinha", nrLinha);
				_montagem->addItem("cdAreaRegistro", cdAreaRegistro);
            }
            else
            {
                xml = "cWFAtendimentoLinha::consultar() não retornou xml valido";
            }

			if (nrLinha)
				XMLString::release(&nrLinha);

			if (cdAreaRegistro)
				XMLString::release(&cdAreaRegistro);

        }
        else
        {
            xml = "Nao foi possivel alocar memoria para objetos DOM e/ou Xerces";
        }

        if ( pParser ) { delete pParser; }
        if ( pMemBuf ) { delete pMemBuf; }

        if ( xml )
        {
            ULOGE(xml);
            ULOG_END("cWFAtdDetDDcto::ObterAtdDetalheLinha()");
            throw new TuxBasicSvcException("07E0000",xml);
        }
    }
    else
    {
        ULOG("cWFAtendimentoLinha::consultar() nao gerou xml de saida");
    }
    ULOG_END("cWFAtdDetDDcto::ObterAtdDetalheLinha()");
}

void cWFAtdDetDDcto::carregaDados()
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

