/*
 * CRequisicaoJudicial.cpp
 *
 *  Created on: 13/06/2013
 *      Author: Jones Randis
 */

#include "garbage_collector.h"

#include "CRequisicaoJudicial.h"
#include "CLog.h"
#include "CException.h"
#include "CSoapException.h"
#include "CFilaProcessum.h"
#include "CNet.h"

#include <sstream>
#include <xmlsoap.hpp>

extern "C" {
#include <libgen.h>
}

namespace batch {

Ctx::encoding soapenc[] = {
	{Tstr("input_encoding"), Tstr(SOAP_ENCODING)}
	//,{Tstr("default_input_encoding"), Tstr(SOAP_ENCODING)}
	//,{Tstr("data_encoding"), Tstr(SOAP_ENCODING)}
	//,{Tstr("error_language"), Tstr(SOAP_ENCODING)}
};
Ctx::encodings soapencs = {1, soapenc};

CRequisicaoJudicial::CRequisicaoJudicial(TmapParametro& mapParametro)
: ctxp(Tstr("SoapImpl"), NULL, NULL, &soapencs),
  mfp(0),
  fp(&ctxp),
  mapParametro(mapParametro)
{
	//LOG_FUNC

	mfp = new (GC) MsgFactory<TCtx, Tnode>(&ctxp);
    memset( sCodSessao  , 0x0, sizeof(sCodSessao) );
    memset( sEnderecoIP , 0x0, sizeof(sEnderecoIP) );
    memset( sCodOrdem   , 0x0, sizeof(sCodOrdem) );
    memset( sCodReq     , 0x0, sizeof(sCodReq) );
    memset( sNrSolicit  , 0x0, sizeof(sNrSolicit) );
    memset( sArqEsperado, 0x0, sizeof(sArqEsperado) );
    memset( sNomeArquivo, 0x0, sizeof(sNomeArquivo) );


}

CRequisicaoJudicial::~CRequisicaoJudicial() {
	//LOG_FUNC

	//if (mfp) { delete mfp; mfp=0; }
}

bool CRequisicaoJudicial::encaminharResultadoProcessamento(const SSecurity& security, const SCabecalhoVivo& cabecalho, const SEncaminharResultadoProcessamentoRequest& request, SEncaminharResultadoProcessamentoResponse& response)
{LOG_FUNC

	if ( !mapParametro.count(CDPARAMETRO_URI_ENC_RESULTADO_PROCESSAMENTO) )
		throw XLoadParam;

	std::string uri = mapParametro[CDPARAMETRO_URI_ENC_RESULTADO_PROCESSAMENTO].dsvalorparametro;
	if ( uri.empty() )
		throw XLoadParam;

	bool retval = false;
	DocumentRef<Tnode>* docrefp=0;
	DocumentRef<Tnode>* docresp=0;

	try {
		docrefp = mfp->CreateMessage();
		ElementRef<Tnode> elref(*docrefp, docrefp->getDocumentElement());

		/* ENVELOPE */
		ElementRef<Tnode> elemEnvelope(elref, mfp->getEnvelope( *docrefp ));

		// SOAP11
		std::string qname11("xmlns:");
		qname11.append((char*)elemEnvelope.getPrefix());
		elemEnvelope.setAttribute(Tstr(qname11.c_str()), Tstr(XMLNS_SOAP11));

        //elemEnvelope.setAttribute(Tstr("Content-Type"), Tstr("text/xml"));
        
		/* HEADER */
		ElementRef<Tnode> elemHeader(elemEnvelope, mfp->getHeader( *docrefp ));

        //ElementRef<Tnode> elemHeader(elemEnvelope, mfp->setRequestProperty("Content-Type", "text/xml"));
        
        //ElementRef<Tnode> elemProp(*docrefp, mfp->addHeaderElement(*docrefp, Tstr("Content-Type"), Tstr("text/xml")));

		// Security
		ElementRef<Tnode> elemSecurity(*docrefp, mfp->addHeaderElement(*docrefp, Tstr("wsse:Security"), Tstr(XMLNS_WSSE)));
		elemSecurity.setAttributeNS(Tstr(XMLNS), Tstr("xmlns:wsu"), Tstr(XMLNS_WSU));
		//mfp->setMustUnderstand(elemSecurity, 1);
        
		mfp->setMustUnderstand(elemSecurity, false );

		// UsernameToken
		ElementRef<Tnode> elemUsernameToken(elemHeader, docrefp->createElement( Tstr("wsse:UsernameToken") ));
		elemSecurity.appendChild(elemUsernameToken);
		elemUsernameToken.setAttributeNS(Tstr(XMLNS_WSU), Tstr("wsu:Id"), Tstr("UsernameToken-1"));

		// Username
		ElementRef<Tnode> elemUsername(elemHeader, docrefp->createElement( Tstr("wsse:Username") ));
		elemUsernameToken.appendChild(elemUsername);
		if (!security.Username.empty()) elemUsername.appendChildWithPtr(docrefp->createTextNode( Tstr(security.Username.c_str()) ));

		// Password
		ElementRef<Tnode> elemPassword(elemHeader, docrefp->createElement(Tstr("wsse:Password")));
		elemUsernameToken.appendChild(elemPassword);
		elemPassword.setAttribute(Tstr("Type"), Tstr(XMLATTR_WSUTP));
		if (!security.Password.empty()) elemPassword.appendChildWithPtr(docrefp->createTextNode( Tstr(security.Password.c_str()) ));

		// cabecalhoVivo
		ElementRef<Tnode> elemCabecalhoVivo(elemHeader, mfp->addHeaderElement(*docrefp, Tstr("ger:cabecalhoVivo"), Tstr(XMLNS_GER)));

		// loginUsuario
		ElementRef<Tnode> elemLoginUsuario(elemCabecalhoVivo, docrefp->createElement( Tstr("ger:loginUsuario") ));
		elemCabecalhoVivo.appendChild(elemLoginUsuario);
		if (!cabecalho.loginUsuario.empty()) elemLoginUsuario.appendChildWithPtr(docrefp->createTextNode( Tstr(cabecalho.loginUsuario.c_str()) ));

		// canalAtendimento
		ElementRef<Tnode> elemCanalAtendimento(elemCabecalhoVivo, docrefp->createElement( Tstr("ger:canalAtendimento") ));
		elemCabecalhoVivo.appendChild(elemCanalAtendimento);
		if (!cabecalho.canalAtendimento.empty()) elemCanalAtendimento.appendChildWithPtr(docrefp->createTextNode( Tstr(cabecalho.canalAtendimento.c_str()) ));

		// codigoSessao
		ElementRef<Tnode> elemCodigoSessao(elemCabecalhoVivo, docrefp->createElement( Tstr("ger:codigoSessao") ));
		elemCabecalhoVivo.appendChild(elemCodigoSessao);
		if (!cabecalho.codigoSessao.empty()) elemCodigoSessao.appendChildWithPtr(docrefp->createTextNode( Tstr(cabecalho.codigoSessao.c_str()) ));

		// nomeAplicacao
		ElementRef<Tnode> elemNomeAplicacao(elemCabecalhoVivo, docrefp->createElement( Tstr("ger:nomeAplicacao") ));
		elemCabecalhoVivo.appendChild(elemNomeAplicacao);
		if (!cabecalho.nomeAplicacao.empty()) elemNomeAplicacao.appendChildWithPtr(docrefp->createTextNode( Tstr(cabecalho.nomeAplicacao.c_str()) ));

		// token
		ElementRef<Tnode> elemToken(elemCabecalhoVivo, docrefp->createElement( Tstr("ger:token") ));
		elemCabecalhoVivo.appendChild(elemToken);
		if (!cabecalho.token.empty()) elemToken.appendChildWithPtr(docrefp->createTextNode( Tstr(cabecalho.token.c_str()) ));

		// enderecoIP
		ElementRef<Tnode> elemEnderecoIP(elemCabecalhoVivo, docrefp->createElement( Tstr("ger:enderecoIP") ));
		elemCabecalhoVivo.appendChild(elemEnderecoIP);
		if (!cabecalho.enderecoIP.empty()) elemEnderecoIP.appendChildWithPtr(docrefp->createTextNode( Tstr(cabecalho.enderecoIP.c_str()) ));

		// codigoFuncionalidade
		ElementRef<Tnode> elemCodigoFuncionalidade(elemCabecalhoVivo, docrefp->createElement( Tstr("ger:codigoFuncionalidade") ));
		elemCabecalhoVivo.appendChild(elemCodigoFuncionalidade);
		if (!cabecalho.codigoFuncionalidade.empty()) elemCodigoFuncionalidade.appendChildWithPtr(docrefp->createTextNode( Tstr(cabecalho.codigoFuncionalidade.c_str()) ));

		// dataTransacao
		ElementRef<Tnode> elemDataTransacao(elemCabecalhoVivo, docrefp->createElement( Tstr("ger:dataTransacao") ));
		elemCabecalhoVivo.appendChild(elemDataTransacao);
		if (!cabecalho.dataTransacao.empty()) elemDataTransacao.appendChildWithPtr(docrefp->createTextNode( Tstr(cabecalho.dataTransacao.c_str()) ));

		// nomeServico
		ElementRef<Tnode> elemNomeServico(elemCabecalhoVivo, docrefp->createElement( Tstr("ger:nomeServico") ));
		elemCabecalhoVivo.appendChild(elemNomeServico);
		if (!cabecalho.nomeServico.empty()) elemNomeServico.appendChildWithPtr(docrefp->createTextNode( Tstr(cabecalho.nomeServico.c_str()) ));

		// versao
		ElementRef<Tnode> elemVersao(elemCabecalhoVivo, docrefp->createElement( Tstr("ger:versao") ));
		elemCabecalhoVivo.appendChild(elemVersao);
		if (!cabecalho.versao.empty()) elemVersao.appendChildWithPtr(docrefp->createTextNode( Tstr(cabecalho.versao.c_str()) ));


		/* BODY */
		ElementRef<Tnode> elemBody(elemEnvelope, mfp->getBody( *docrefp ));

		// EncaminharResultadoProcessamentoRequest
		ElementRef<Tnode> elemEncResProcReq(elemBody, mfp->addBodyElement(*docrefp, Tstr("enc:EncaminharResultadoProcessamentoRequest"), Tstr(XMLNS_ENC)));

		// codigoOrdem
		ElementRef<Tnode> elemCodigoOrdem(elemEncResProcReq, docrefp->createElement( Tstr("enc:codigoOrdem") ));
		elemEncResProcReq.appendChild(elemCodigoOrdem);
		if (!request.codigoOrdem.empty()) elemCodigoOrdem.appendChildWithPtr(docrefp->createTextNode( Tstr(request.codigoOrdem.c_str()) ));

		// codigoRequisicao
		ElementRef<Tnode> elemCodigoRequisicao(elemEncResProcReq, docrefp->createElement( Tstr("enc:codigoRequisicao") ));
		elemEncResProcReq.appendChild(elemCodigoRequisicao);
		if (!request.codigoRequisicao.empty()) elemCodigoRequisicao.appendChildWithPtr(docrefp->createTextNode( Tstr(request.codigoRequisicao.c_str()) ));

		// numeroSolicitacao
		ElementRef<Tnode> elemNumeroSolicitacao(elemEncResProcReq, docrefp->createElement( Tstr("enc:numeroSolicitacao") ));
		elemEncResProcReq.appendChild(elemNumeroSolicitacao);
		if (!request.numeroSolicitacao.empty()) elemNumeroSolicitacao.appendChildWithPtr(docrefp->createTextNode( Tstr(request.numeroSolicitacao.c_str()) ));

		// arquivoRetornoEsperado
		ElementRef<Tnode> elemArquivoRetornoEsperado(elemEncResProcReq, docrefp->createElement( Tstr("enc:arquivoRetornoEsperado") ));
		elemEncResProcReq.appendChild(elemArquivoRetornoEsperado);
		if (!request.arquivoRetornoEsperado.empty()) elemArquivoRetornoEsperado.appendChildWithPtr(docrefp->createTextNode( Tstr(request.arquivoRetornoEsperado.c_str()) ));

		// nomeArquivoGerado
		if ( !request.nomeArquivoGerado.empty() ) {
			ElementRef<Tnode> elemNomeArquivoGerado(elemEncResProcReq, docrefp->createElement( Tstr("enc:nomeArquivoGerado") ));
			elemEncResProcReq.appendChild(elemNomeArquivoGerado);
			elemNomeArquivoGerado.appendChildWithPtr(docrefp->createTextNode( Tstr(request.nomeArquivoGerado.c_str()) ));
		}

		// statusProcessamento
		ElementRef<Tnode> elemStatusProcessamento(elemEncResProcReq, docrefp->createElement( Tstr("enc:statusProcessamento") ));
		elemEncResProcReq.appendChild(elemStatusProcessamento);
		if (!request.statusProcessamento.empty()) elemStatusProcessamento.appendChildWithPtr(docrefp->createTextNode( Tstr(request.statusProcessamento.c_str()) ));

		// descricaoStatusProcessamento
		ElementRef<Tnode> elemDescricaoStatusProcessamento(elemEncResProcReq, docrefp->createElement( Tstr("enc:descricaoStatusProcessamento") ));
		elemEncResProcReq.appendChild(elemDescricaoStatusProcessamento);
		if (!request.descricaoStatusProcessamento.empty()) elemDescricaoStatusProcessamento.appendChildWithPtr(docrefp->createTextNode( Tstr(request.descricaoStatusProcessamento.c_str()) ));


		// Request
		if (logLevel() & LDEBUG)
		{
			std::string request;
			printNode(*docrefp,  request);
			LOG_DEBUG("REQUEST XML=[" << request << "]")
		}

		ConnectRef<Tnode>* conn=0;
		try {
			// Connection
			LOG_INFO("CreateConnection: " << uri)
			conn = mfp->createConnection(BIND_HTTP, Tstr(uri.c_str()), 0, 0, 0, 0);

			// Soap Call
			LOG_INFO("SoapCall: " << Tstr(elemEncResProcReq.getLocalName()))
			docresp = conn->call( *docrefp );
		}
		catch(...) {
			if (conn) {
				delete conn; conn=0;
			}
			if (docresp) {
				mfp->destroyMessage( *docresp );
				delete docresp; docresp=0;
			}
			if (docrefp) {
				mfp->destroyMessage( *docrefp );
				delete docrefp; docrefp=0;
			}
			throw;
		}
		if (conn) { delete conn; conn=0; }

		// RESPONSE
		ElementRef<Tnode> elResp((*docresp), docresp->getDocumentElement());

		// Response
		if (logLevel() & LDEBUG)
		{
			std::string response;
			printNode((*docresp),  response);
			LOG_DEBUG("RESPONSE XML=[" << response << "]")
		}


		// EncaminharResultadoProcessamentoResponse
		Tnode* nodeEncResProcResp=0;
		Tnode* nodeFault=0;
		try {
			nodeEncResProcResp = mfp->getBodyElement((*docresp), Tstr(XMLNS_ENC), Tstr(XMLNODE_ENC_RES));
		}
		catch (OracleXml::Soap::SoapException& e) {
			if (e.getCode() == XMLERR_SOAP_BAD_VERSION) {
				//LOG_ERR("SoapException[XMLERR_SOAP_BAD_VERSION]: code=[" << e.getCode() << "], message=[" << e.getMessage() << "]")

				NodeList<Tnode>* nodelistEncResProcResp = elResp.getElementsByTagNameNS(Tstr(XMLNS_ENC), Tstr(XMLNODE_ENC_RES));
				if (nodelistEncResProcResp) {
					NodeListRef<Tnode> listEncResProcResp(elResp, nodelistEncResProcResp);
					nodeEncResProcResp = listEncResProcResp.item(0);
				}

				NodeList<Tnode>* nodelistFault = elResp.getElementsByTagNameNS(elResp.getNamespaceURI(), Tstr("Fault"));
				if (nodelistFault) {
					NodeListRef<Tnode> listFault(elResp, nodelistFault);
					nodeFault = listFault.item(0);
				}
			}
			else if (e.getCode() == XMLERR_SOAP_NO_ELEMENT) {
				LOG_ERR("SoapException[XMLERR_SOAP_NO_ELEMENT]: code=[" << e.getCode() << "], message=[" << e.getMessage() << "]")
			}
			else {
				if (docresp) {
					mfp->destroyMessage( *docresp );
					delete docresp; docresp=0;
				}
				if (docrefp) {
					mfp->destroyMessage( *docrefp );
					delete docrefp; docrefp=0;
				}
				throw;
			}
		}

		if (nodeEncResProcResp) {
			LOG_INFO(">>> ELEMENTO EncaminharResultadoProcessamentoResponse ENCONTRADO <<<")
			ElementRef<Tnode> elemEncResProcResp(elResp, nodeEncResProcResp);

			// status
			NodeList<Tnode>* nodelistStatus = elemEncResProcResp.getElementsByTagNameNS(Tstr(XMLNS_ENC), Tstr("status"));
			if (nodelistStatus) {
				NodeListRef<Tnode> listStatus(elemEncResProcResp, nodelistStatus);
				ElementRef<Tnode> elemStatus(elemEncResProcResp, listStatus.item(0));

				Tnode* nodeStatus = elemStatus.getFirstChild();
				if (nodeStatus) {
					TextRef<Tnode> textStatus(elemEncResProcResp, nodeStatus);
					response.status = (char*)textStatus.getData();
				}
				LOG_INFO("EncaminharResultadoProcessamentoResponse/status: " << response.status)
				retval = true;
			}
			else {
				LOG_INFO(">>> ELEMENTO EncaminharResultadoProcessamentoResponse/status NAO ENCONTADO <<<")
				retval = false;
			}

			// codigoErro
			NodeList<Tnode>* nodelistCodigoErro = elemEncResProcResp.getElementsByTagNameNS(Tstr(XMLNS_ENC), Tstr("codigoErro"));
			if (nodelistCodigoErro) {
				NodeListRef<Tnode> listCodigoErro(elemEncResProcResp, nodelistCodigoErro);
				ElementRef<Tnode> elemCodigoErro(elemEncResProcResp, listCodigoErro.item(0));

				Tnode* nodeCodigoErro = elemCodigoErro.getFirstChild();
				if (nodeCodigoErro) {
					TextRef<Tnode> textCodigoErro(elemEncResProcResp, nodeCodigoErro);
					response.codigoErro = (char*)textCodigoErro.getData();
				}
				LOG_INFO("EncaminharResultadoProcessamentoResponse/codigoErro: " << response.codigoErro)
			}

			// descricaoErro
			NodeList<Tnode>* nodelistDescricaoErro = elemEncResProcResp.getElementsByTagNameNS(Tstr(XMLNS_ENC), Tstr("descricaoErro"));
			if (nodelistDescricaoErro) {
				NodeListRef<Tnode> listDescricaoErro(elemEncResProcResp, nodelistDescricaoErro);
				ElementRef<Tnode> elemDescricaoErro(elemEncResProcResp, listDescricaoErro.item(0));

				Tnode* nodeDescricaoErro = elemDescricaoErro.getFirstChild();
				if (nodeDescricaoErro) {
					TextRef<Tnode> textDescricaoErro(elemDescricaoErro, nodeDescricaoErro);
					response.descricaoErro = (char*)textDescricaoErro.getData();
				}
				LOG_INFO("EncaminharResultadoProcessamentoResponse/descricaoErro: " << response.descricaoErro)
			}
		}
		else {
			if (nodeFault) {
				ElementRef<Tnode> elemFault(elResp, nodeFault);
				std::string fault;
				printNode(elemFault,  fault);
				LOG_ERR("FAULT=[" << fault << "]")
			}

			LOG_INFO(">>> ELEMENTO EncaminharResultadoProcessamentoResponse NAO ENCONTRADO <<<")
			retval = false;
		}
	}
	catch (OracleXml::Soap::SoapException& e) {
		LOG_ERR("SoapException: code=[" << e.getCode() << "], message=[" << e.getMessage() << "]")
		retval = false;
	}
	catch (OracleXml::XmlException& e) {
		LOG_ERR("XmlException: code=" << e.getCode())
		retval = false;
	}
	catch (...) {
		if (docresp) {
			mfp->destroyMessage( *docresp );
			delete docresp; docresp=0;
		}
		if (docrefp) {
			mfp->destroyMessage( *docrefp );
			delete docrefp; docrefp=0;
		}
		throw;
	}

	if (docresp) {
		mfp->destroyMessage( *docresp );
		delete docresp; docresp=0;
	}
	if (docrefp) {
		mfp->destroyMessage( *docrefp );
		delete docrefp; docrefp=0;
	}
	return retval;
}

bool CRequisicaoJudicial::encaminharResultadoProcessamento(const SFilaProcessum& filaprocessum, const std::string& filename, SEncaminharResultadoProcessamentoResponse& response)
{
	// security
	SSecurity security;

	// Username
	if ( !mapParametro.count(CDPARAMETRO_USER_ENC_RESULTADO_PROCESSAMENTO) )
		throw XLoadParam;
	security.Username = mapParametro[CDPARAMETRO_USER_ENC_RESULTADO_PROCESSAMENTO].dsvalorparametro;

	// Password
	if ( !mapParametro.count(CDPARAMETRO_PASS_ENC_RESULTADO_PROCESSAMENTO) )
		throw XLoadParam;
	security.Password = mapParametro[CDPARAMETRO_PASS_ENC_RESULTADO_PROCESSAMENTO].dsvalorparametro;

	// cabecalho vivo
	SCabecalhoVivo cabecalho;

	// loginUsuario
	cabecalho.loginUsuario = "sittel";

	// canalAtendimento
	cabecalho.canalAtendimento = "Vivo";

	// codigoSessao
	std::ostringstream codigoSessao;
	codigoSessao << static_cast<int64_t>(filaprocessum.idfilaprocessum);
	cabecalho.codigoSessao = codigoSessao.str();

	// nomeAplicacao
	cabecalho.nomeAplicacao = "VivoProcessum";

	// token
	cabecalho.token = "0";

	// enderecoIP
	getIpAddress(cabecalho.enderecoIP);

	// codigoFuncionalidade
	cabecalho.codigoFuncionalidade = "EncaminharResultadoProcessamentoRequest";

	// dataTransacao (CCYY-MM-DDThh:mm:ss)
	cabecalho.dataTransacao = now("%Y-%m-%dT%H:%M:%S");

	// nomeServico
	cabecalho.nomeServico = "EncaminharResultadoProcessamento";

	// versao
	cabecalho.versao = "1.1";

	// request
	SEncaminharResultadoProcessamentoRequest request;

	// codigoOrdem
	request.codigoOrdem = filaprocessum.codigoordem;

	// codigoRequisicao
	std::ostringstream codigoRequisicao;
	codigoRequisicao << static_cast<int64_t>(filaprocessum.codigorequisicao);
	request.codigoRequisicao = codigoRequisicao.str();

	// numeroSolicitacao
	std::ostringstream numeroSolicitacao;
	numeroSolicitacao << static_cast<int64_t>(filaprocessum.numerosolicitacao);
	request.numeroSolicitacao = numeroSolicitacao.str();

	// arquivoRetornoEsperado
	switch( filaprocessum.tiposolicitacao ) {
	case CFilaProcessum::CONSULTA_ASSINANTE:
		request.arquivoRetornoEsperado = "ASSINANTE";
		break;
	case CFilaProcessum::CONSULTA_TERMINAL_ASSINANTE:
		request.arquivoRetornoEsperado = "ASSINANTE-TERMINAL";
		break;
	case CFilaProcessum::CONSULTA_INSTALACAO:
		request.arquivoRetornoEsperado = "INSTALACAO";
		break;
	}

	// nomeArquivoGerado
	request.nomeArquivoGerado = filename;

	// statusProcessamento
	request.statusProcessamento = "0";

	// descricaoStatusProcessamento
	request.descricaoStatusProcessamento = "Sucesso";

	//return encaminharResultadoProcessamento(security, cabecalho, request, response);
    
    std::string uri = mapParametro[CDPARAMETRO_URI_ENC_RESULTADO_PROCESSAMENTO].dsvalorparametro;
    strcpy( s_URI, uri.c_str() );
    strcpy( sCodSessao, cabecalho.codigoSessao.c_str() );
    strcpy( sEnderecoIP, cabecalho.enderecoIP.c_str() );
    strcpy( sCodOrdem, filaprocessum.codigoordem.c_str() );
    strcpy( sCodReq, request.codigoRequisicao.c_str() );
    strcpy( sNrSolicit, request.numeroSolicitacao.c_str() );
    strcpy( sArqEsperado, request.arquivoRetornoEsperado.c_str() );
    strcpy( sNomeArquivo, filename.c_str() );
    
    return EnviaPost(response);
    
}

bool CRequisicaoJudicial::encaminharResultadoProcessamento(const SFilaProcessum& filaprocessum, int64_t cderro, const std::string& dserro, SEncaminharResultadoProcessamentoResponse& response)
{
	// security
	SSecurity security;

	// Username
	if ( !mapParametro.count(CDPARAMETRO_USER_ENC_RESULTADO_PROCESSAMENTO) )
		throw XLoadParam;
	security.Username = mapParametro[CDPARAMETRO_USER_ENC_RESULTADO_PROCESSAMENTO].dsvalorparametro;

	// Password
	if ( !mapParametro.count(CDPARAMETRO_PASS_ENC_RESULTADO_PROCESSAMENTO) )
		throw XLoadParam;
	security.Password = mapParametro[CDPARAMETRO_PASS_ENC_RESULTADO_PROCESSAMENTO].dsvalorparametro;

	// cabecalho vivo
	SCabecalhoVivo cabecalho;

	// loginUsuario
	cabecalho.loginUsuario = "sittel";

	// canalAtendimento
	cabecalho.canalAtendimento = "Vivo";

	// codigoSessao
	std::ostringstream codigoSessao;
	codigoSessao << static_cast<int64_t>(filaprocessum.idfilaprocessum);
	cabecalho.codigoSessao = codigoSessao.str();

	// nomeAplicacao
	cabecalho.nomeAplicacao = "VivoProcessum";

	// token
	cabecalho.token = "0";

	// enderecoIP
	getIpAddress(cabecalho.enderecoIP);

	// codigoFuncionalidade
	cabecalho.codigoFuncionalidade = "EncaminharResultadoProcessamentoRequest";

	// dataTransacao (CCYY-MM-DDThh:mm:ss)
	cabecalho.dataTransacao = now("%Y-%m-%dT%H:%M:%S");

	// nomeServico
	cabecalho.nomeServico = "EncaminharResultadoProcessamento";

	// versao
	cabecalho.versao = "1.1";

	// request
	SEncaminharResultadoProcessamentoRequest request;

	// codigoOrdem
	request.codigoOrdem = filaprocessum.codigoordem;

	// codigoRequisicao
	std::ostringstream codigoRequisicao;
	codigoRequisicao << static_cast<int64_t>(filaprocessum.codigorequisicao);
	request.codigoRequisicao = codigoRequisicao.str();

	// numeroSolicitacao
	std::ostringstream numeroSolicitacao;
	numeroSolicitacao << static_cast<int64_t>(filaprocessum.numerosolicitacao);
	request.numeroSolicitacao = numeroSolicitacao.str();

	// arquivoRetornoEsperado
	switch( filaprocessum.tiposolicitacao ) {
	case CFilaProcessum::CONSULTA_ASSINANTE:
		request.arquivoRetornoEsperado = "ASSINANTE";
		break;
	case CFilaProcessum::CONSULTA_TERMINAL_ASSINANTE:
		request.arquivoRetornoEsperado = "ASSINANTE-TERMINAL";
		break;
	case CFilaProcessum::CONSULTA_INSTALACAO:
		request.arquivoRetornoEsperado = "INSTALACAO";
		break;
	}

	// nomeArquivoGerado
	//request.nomeArquivoGerado = "";

	// statusProcessamento
	std::ostringstream statusProcessamento;
	statusProcessamento << cderro;
	request.statusProcessamento = statusProcessamento.str();

	// descricaoStatusProcessamento
	request.descricaoStatusProcessamento = dserro;

	//return encaminharResultadoProcessamento(security, cabecalho, request, response);

    std::string uri = mapParametro[CDPARAMETRO_URI_ENC_RESULTADO_PROCESSAMENTO].dsvalorparametro;
    strcpy( s_URI, uri.c_str() );
    strcpy( sCodSessao, cabecalho.codigoSessao.c_str() );
    strcpy( sEnderecoIP, cabecalho.enderecoIP.c_str() );
    strcpy( sCodOrdem, filaprocessum.codigoordem.c_str() );
    strcpy( sCodReq, request.codigoRequisicao.c_str() );
    strcpy( sNrSolicit, request.numeroSolicitacao.c_str() );
    strcpy( sArqEsperado, request.arquivoRetornoEsperado.c_str() );
    
    return EnviaPost(response);
    
}

void CRequisicaoJudicial::printNode(NodeRef<Tnode>& node, std::string& text)
{
	DOMWriter<Tnode>* dwp = fp.createDOMWriter(DOMWrCXml);
	//dwp->setOutputEncoding( Tstr(SOAP_ENCODING) );
	dwp->setIndentStep(2);
	dwp->setIndentLevel(0);
	oratext buff[XML_BUFFER_SIZE]={0};
	BufferOSource osrcp(buff, sizeof(buff));
	dwp->writeNode(&osrcp, node);

	text = (char*)buff;
}

void CRequisicaoJudicial::printNode(NodeRef<Tnode>& node)
{LOG_FUNC

	DOMWriter<Tnode>* dwp = fp.createDOMWriter(DOMWrCXml);
	//dwp->setOutputEncoding( Tstr(SOAP_ENCODING) );
	dwp->setIndentStep(2);
	dwp->setIndentLevel(0);
	oratext buff[XML_BUFFER_SIZE]={0};
	BufferOSource osrcp(buff, sizeof(buff));
	dwp->writeNode(&osrcp, node);

	LOG_DEBUG("NODE=[" << buff << "]")
}

void CRequisicaoJudicial::writeNode(NodeRef<Tnode>& node, const std::string& file)
{LOG_FUNC

	LOG_DEBUG("writeNode: " << basename( const_cast<char*>(file.c_str()) ))
	DOMWriter<Tnode>* dwp = fp.createDOMWriter(DOMWrCXml);
	//dwp->setOutputEncoding( Tstr(SOAP_ENCODING) );
	dwp->setIndentStep(2);
	dwp->setIndentLevel(0);
	FileOSource osrcp( Tstr(file.c_str()) );
	dwp->writeNode(&osrcp, node);
}



bool CRequisicaoJudicial::EnviaPost(SEncaminharResultadoProcessamentoResponse& response)
{
    LOG_DEBUG(">>> EnviaPost()")
    
    time_t      rawtime;
    struct tm * timeinfo;
    char bf[1024] = {0};
    char Cmd[128] = {0};
    char sCmdPost[128] = {0};
    char szAux[128];
    
    LOG_DEBUG("*** sURI             [" << s_URI << "]")
    LOG_DEBUG("*** sCodigoSessao    [" << sCodSessao << "]")
    LOG_DEBUG("*** sEnderecoIP      [" << sEnderecoIP << "]")
    LOG_DEBUG("*** CodigoOrdem      [" << sCodOrdem << "]")
    LOG_DEBUG("*** CodigoRequisicao [" << sCodReq << "]")
    LOG_DEBUG("*** nrSolicitacao    [" << sNrSolicit << "]")
    LOG_DEBUG("*** Arquivo Gerado   [" << sNomeArquivo << "]")
    
    sprintf ( sCmdPost, "nohup java RequestRequisicaoJudicial %s %s %s %s %s %s %s %s %s %s ", 
                         s_URI, sCodSessao, sEnderecoIP, sCodOrdem,
                         sCodReq, sNrSolicit, sArqEsperado, 
                         sNomeArquivo, "0", "Sucesso" );
    
    
    LOG_DEBUG("*** Post: [" << sCmdPost << "]")

    std::string retorno = GetStdoutFromCommand( sCmdPost );
    LOG_DEBUG("retorno: [" << retorno.c_str() << "]")

    size_t found;
    std::string strTmp;
    
    // STATUS
    found = retorno.find("<ns0:status>");
    sprintf ( szAux, "%ld", found );
    // NAO HOUVE RETORNO DO SERVIDOR SITTEL, DEIXANDO DISPONIVEL PARA A PROXIMA EXECUCAO
    if ( !strcmp(szAux, "-1") )   
    {
        LOG_DEBUG("*** NÃO HOUVE RESPOSTA AO INFORMAR DISPONILIBILIDADE DO ARQUIVO ASSINANTE ***")

        MsgTry += "*** NÃO HOUVE RESPOSTA AO INFORMAR DISPONILIBILIDADE DO ARQUIVO ASSINANTE ***";
        response.status = "-1";
        response.codigoErro = 77;
        response.descricaoErro = MsgTry.c_str();
        LOG_DEBUG("<<< EnviaPost()")
        return false;
    }

    strTmp = retorno.substr (found+12);
    strStatus = strTmp.substr(0,strTmp.find("</ns0:status>"));
    
    // CODIGO DO ERRO
    found = retorno.find("<ns0:codigoErro>");
    strTmp = retorno.substr (found+16);
    strCodigoErro = strTmp.substr(0,strTmp.find("</ns0:codigoErro>"));

    found = retorno.find("<ns0:descricaoErro>");
    strTmp = retorno.substr (found+19);
    strDescricaoErro = strTmp.substr(0,strTmp.find("</ns0:descricaoErro>"));

    // DESCRICAO DO ERRO
    LOG_DEBUG("*** status       : [" << strStatus.c_str() << "]")
    LOG_DEBUG("*** codigoErro   : [" << strCodigoErro.c_str() << "]")
    LOG_DEBUG("*** descricaoErro: [" << strDescricaoErro.c_str() << "]")
    
    response.status = strStatus.c_str();
    response.codigoErro = strCodigoErro.c_str();
    response.descricaoErro = strDescricaoErro.c_str();
    
    if (!strStatus.compare("OK"))
    {
        LOG_DEBUG("--- Retorno OK ---")
        MsgTry += "Sucesso - response.status[OK]";

    }
    else if (!strStatus.compare("ERRO"))
    {
        
        if (!strCodigoErro.compare("415"))
        {
            found = strDescricaoErro.find(": Nenhuma");
            strTmp = strDescricaoErro.substr (found);
            strDescricaoErro = "415 - Erro de validacao";
            strDescricaoErro += strTmp;
            
        }
        else if (!strCodigoErro.compare("416"))
        {
            found = strDescricaoErro.find(": Nenhuma");
            strTmp = strDescricaoErro.substr (found);
            strDescricaoErro = "416 - Erro de validacao";
            strDescricaoErro += strTmp;
        }
    }
    

    LOG_DEBUG("<<< EnviaPost()")
    return true;
}



std::string CRequisicaoJudicial::GetStdoutFromCommand(char* cmd_prm) 
{
    std::string cmd = cmd_prm;
    std::string data;
    FILE * stream;
    const int max_buffer = 256;
    char buffer[max_buffer];
    cmd.append(" 2>&1");

    stream = popen(cmd.c_str(), "r");
    if (stream) 
    {
        while (!feof(stream))
           if (fgets(buffer, max_buffer, stream) != NULL) data.append(buffer);
        pclose(stream);
    }
    return data;
}

} /* namespace batch */

