/**
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.2.1.6.1 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/06 12:36:43 $
 **/

#include "../include/cWFAtdDetPessoaEx.h"

#include "../../Atendimento/include/cWFAtendimento.h"
#include "../../AtendimentoContato/include/cWFAtendimentoContato.h"
#include "../../AtendimentoPessoa/include/cWFAtendimentoPessoa.h"

cWFAtdDetPessoaEx::cWFAtdDetPessoaEx(DOMNode *dnode, XMLGen *xml_g)
{
    entrada = dnode;
    saida = xml_g;

    memset(&m_stDados,0,sizeof(m_stDados));
    memset(&m_vlDados,-1,sizeof(m_vlDados));

    memset(&m_stAtdDetPessoa,0,sizeof(m_stAtdDetPessoa));
    memset(&m_vlAtdDetPessoa,-1,sizeof(m_vlAtdDetPessoa));

    memset(&m_stAtdBaixa,0,sizeof(m_stAtdBaixa));
    memset(&m_vlAtdBaixa,-1,sizeof(m_vlAtdBaixa));

    memset(&m_stGrupoAtendimento,0,sizeof(m_stGrupoAtendimento));
    memset(&m_vlGrupoAtendimento,-1,sizeof(m_vlGrupoAtendimento));

    carregaDados();
}

bool cWFAtdDetPessoaEx::executar(char **codErro,char **msgErro)
{
    if ( m_vlDados.idAtendimento == -1 )
    {
        *msgErro = "O código de 'idAtendimento' é obrigatório";

        return false;
    }

    ObterAtendPessoa();

    saida->createTag("PessoaVO");
        saida->addProp("xmlns", "workflow.fo.vivo.com.br/vo");
        saida->addProp("xmlns:ns2", "admsistemas.fo.vivo.com.br/vo");
        saida->addProp("xmlns:ns3", "usuario.fo.vivo.com.br/vo");
        saida->addProp("xmlns:ns4", "cliente.fo.vivo.com.br/vo");

            saida->aggregateXML((char*)xmlPessoaDefinitivo);
    saida->closeTag();

    return true;
}

void cWFAtdDetPessoaEx::ObterAtendPessoa()
{
    ULOG_START("cWFAtdDetPessoaEx::ObterAtendPessoa()");

    int tamSaida;
    XMLGen saida;
    cWFAtendimentoPessoa cwfAtendimentoPessoa(entrada, &saida);
    SmallString xmlPessoa;
    SmallString xmlPessoaEndereco;
    AtendimentoPessoa ap;

    if ( cwfAtendimentoPessoa.ObtemAtendPessoaEx(&ap) )
    {
        char *xml = saida.retrieveXML(&tamSaida);

        if ( tamSaida )
        {
            xmlPessoa += xml;

            ObterEnderecoPessoa(ap.idPessoa,xmlPessoaEndereco);

            MontarXMLPessoaSaida(xmlPessoa,xmlPessoaEndereco);
        }
    }
    else
    {
        ULOG("falhou execução");
    }

    ULOG_END("cWFAtdDetPessoaEx::ObterAtendPessoa()");
}

void cWFAtdDetPessoaEx::ObterEnderecoPessoa(long idPessoa,SmallString &xmlPessoaEndereco)
{
    ULOG_START("cWFAtdDetPessoaEx::ObterEnderecoPessoa()");

    int tamSaida;
    XMLGen saida;
    cWFAtendimentoPessoa cwfAtendimentoPessoa;

    if ( cwfAtendimentoPessoa.ObtemEnderecoPessoa(idPessoa,&saida) )
    {
        char *xml = saida.retrieveXML(&tamSaida);

        if ( tamSaida )
        {
            xmlPessoaEndereco = xml;
        }
        else
        {
            ULOG("nao gerou xml de saida");
        }
    }
    else
    {
        ULOG("falhou execução");
    }

    ULOG_END("cWFAtdDetPessoaEx::ObterEnderecoPessoa()");
}

void cWFAtdDetPessoaEx::MontarXMLPessoaSaida(SmallString &xmlPessoa,SmallString &xmlPessoaEndereco)
{
    ULOG_START("cWFAtdDetPessoaEx::MontarXMLPessoaSaida()");

    char *xml=0;
    int tamXmlSaida;
    SmallString xmlSaida;
    XercesDOMParser *pParser;
    MemBufInputSource *pMemBuf;

    if ( xmlPessoa.Size() )
    {
        xmlSaida += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>";
        xmlSaida += "<AtendimentoPessoasVO>";
        xmlSaida += xmlPessoa;
        xmlSaida += "</AtendimentoPessoasVO>";

        tamXmlSaida = xmlSaida.Size();

        pParser = new XercesDOMParser;
        pMemBuf = new MemBufInputSource((const XMLByte*)xmlSaida.c_str(),tamXmlSaida,gerarIDDom());

        if ( pParser && pMemBuf )
        {
            pParser->parse(*pMemBuf);

            if ( pParser->getErrorCount() )
            {
                xml = "xml invalido";
            }

            DOMNode* pDoc = pParser->getDocument();
            DOMNode* dn;
            int index = 0;

            while ( !xml && (dn = tx.walkDOM(pDoc,"PessoaVO",index++ )) )
            {
                char *_nrDocumento = tx.walkTree(dn,"nrDocumento",0);
                char *_idTipoCarteira = tx.walkTree(dn,"idTipoCarteira",0);
                char *_dsTipoCarteira = tx.walkTree(dn,"dsTipoCarteira",0);
                char *_idSegmentacao = tx.walkTree(dn,"idSegmentacao",0);
                char *_dsSegmentacao = tx.walkTree(dn,"dsSegmentacao",0);
                char *_idPessoaDePara = tx.walkTree(dn,"idPessoaDePara",0);
                char *_idPessoa = tx.walkTree(dn,"idPessoa",0);
                char *_idTipoPessoa = tx.walkTree(dn,"idTipoPessoa",0);
                char *_nome = tx.walkTree(dn,"nome",0);
                char *_inRspAbertura = tx.walkTree(dn,"inRspAbertura",0);
                char *_dsTipoRelacionamento = tx.walkTree(dn,"dsTipoRelacionamento",0);
                char *_dtCadastro = tx.walkTree(dn,"dtCadastro",0);
                char *_dsComunicacao = tx.walkTree(dn,"dsComunicacao",0);
                char *_nmCadastradoPor = tx.walkTree(dn,"nmCadastradoPor",0);

                if ( _idPessoaDePara && _idPessoa && _idTipoPessoa
                   && _nome && _inRspAbertura && _dsTipoRelacionamento && _dtCadastro )
                {
                    XMLGen bufXML;

					bufXML.addItem("nrDocumento", _nrDocumento);
					bufXML.addItem("idPessoaDePara", _idPessoaDePara);
					bufXML.addItem("idPessoa", _idPessoa);
					bufXML.addItem("idTipoPessoa", _idTipoPessoa);
					bufXML.addItem("nome", _nome);
					bufXML.addItem("inRspAbertura", _inRspAbertura);
					bufXML.addItem("dsTipoRelacionamento", _dsTipoRelacionamento);
					bufXML.addItem("dtCadastro", _dtCadastro);
					bufXML.addItem("nmCadastradoPor", _nmCadastradoPor);
					if( _dsComunicacao )
						bufXML.addItem("dsComunicacao", _dsComunicacao);
					else
						bufXML.addItem("dsComunicacao", "");
					bufXML.createTag("ns2:CarterizacaoVO");
						bufXML.addItem("ns2:idTipoCarteira", _idTipoCarteira);
						bufXML.addItem("ns2:descricao", _dsTipoCarteira);
					bufXML.closeTag();
					bufXML.createTag("ns2:SegmentacaoVO");
						bufXML.addItem("ns2:idSegmentacao", _idSegmentacao);
						bufXML.addItem("ns2:descricao", _dsSegmentacao);
					bufXML.closeTag();

					int tam;
                    xmlPessoaDefinitivo += bufXML.retrieveXML(&tam);
                }
                else
                {
                    xml = "xml invalido";
                }

			    if (_idPessoaDePara)
                {
				    XMLString::release(&_idPessoaDePara);
                }

			    if (_idPessoa)
                {
				    XMLString::release(&_idPessoa);
                }

			    if (_idTipoPessoa)
                {
				    XMLString::release(&_idTipoPessoa);
                }

			    if (_nome)
                {
				    XMLString::release(&_nome);
                }

			    if (_inRspAbertura)
                {
				    XMLString::release(&_inRspAbertura);
                }

			    if (_dsTipoRelacionamento)
                {
				    XMLString::release(&_dsTipoRelacionamento);
                }

			    if (_dtCadastro)
                {
				    XMLString::release(&_dtCadastro);
                }
			    if (_dsComunicacao)
                {
				    XMLString::release(&_dsComunicacao);
                }
			    if (_idTipoCarteira)
                {
				    XMLString::release(&_idTipoCarteira);
                }
			    if (_dsTipoCarteira)
                {
				    XMLString::release(&_dsTipoCarteira);
                }
			    if (_idSegmentacao)
                {
				    XMLString::release(&_idSegmentacao);
                }
			    if (_dsSegmentacao)
                {
				    XMLString::release(&_dsSegmentacao);
                }
            }
        }
        else
        {
            xml = "Nao foi possivel alocar memoria para objetos DOM e/ou Xerces";
        }

        if ( pParser ) { delete pParser; }
        if ( pMemBuf ) { delete pMemBuf; }

        if ( xml )
        {
            ULOGE("%s",mensagemSimples(xml));
            throw new TuxBasicSvcException("00E0000",xml);
        }
    }

    if ( xmlPessoaEndereco.Size() )
    {
        xmlSaida.Clean();

        xmlSaida += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>";
        xmlSaida += xmlPessoaEndereco;

        tamXmlSaida = xmlSaida.Size();

        XercesDOMParser *pParser = new XercesDOMParser;
        MemBufInputSource *pMemBuf = new MemBufInputSource((const XMLByte*)((char*)xmlSaida)
                                                                            ,tamXmlSaida,gerarIDDom());
        if ( pParser && pMemBuf )
        {
            pParser->parse(*pMemBuf);
            DOMNode* pDoc = pParser->getDocument();
            int index = 0;

            char *_nmMunicipio = tx.walkTree(pDoc,"nmMunicipio",0);
            char *_nmBairro = tx.walkTree(pDoc,"nmBairro",0);
            char *_dsEnderecoCompleto = tx.walkTree(pDoc,"dsEnderecoCompleto",0);
            char *_nrCep = tx.walkTree(pDoc,"nrCep",0);
            char *_sgUf = tx.walkTree(pDoc,"sgUf",0);

            if ( _nmMunicipio && _nmBairro
               && _dsEnderecoCompleto && _nrCep && _sgUf )
            {
                XMLGen bufXML;

				bufXML.addItem("nmMunicipio", _nmMunicipio);
				bufXML.addItem("nmBairro", _nmBairro);
				bufXML.addItem("dsEndereco", _dsEnderecoCompleto);
				bufXML.addItem("nrCep", _nrCep);
				bufXML.addItem("dsSiglaEstado", _sgUf);

				int tam;
				xmlPessoaDefinitivo += bufXML.retrieveXML(&tam);
            }
            else
            {
                xml = "xml invalido";
            }

			if (_nmMunicipio)
            {
				XMLString::release(&_nmMunicipio);
            }
			if (_nmBairro)
            {
				XMLString::release(&_nmBairro);
            }
			if (_dsEnderecoCompleto)
            {
				XMLString::release(&_dsEnderecoCompleto);
            }
			if (_sgUf)
            {
				XMLString::release(&_sgUf);
            }
        }
        else
        {
            xml = "Nao foi possivel alocar memoria para objetos DOM e/ou Xerces";
        }

        if ( pParser ) { delete pParser; }
        if ( pMemBuf ) { delete pMemBuf; }

        if ( xml )
        {
            ULOGE("%s",mensagemSimples(xml));
            throw new TuxBasicSvcException("00E0000",xml);
        }
    }

    ULOG_END("cWFAtdDetPessoaEx::MontarXMLPessoaSaida()");
}

void cWFAtdDetPessoaEx::carregaDados()
{
    ULOG_START("cWFAtdDetPessoaEx::carregaDados()");

    char *p;

    if (p=tx.walkTree( entrada, "idAtendimento", 0 ),p) 
    {
        m_stDados.idAtendimento = atol(p);
        m_vlDados.idAtendimento = 1;
		XMLString::release(&p);
    }

    ULOG("idAtendimento=%d",m_stDados.idAtendimento);

    ULOG_END("cWFAtdDetPessoaEx::carregaDados()");
}

