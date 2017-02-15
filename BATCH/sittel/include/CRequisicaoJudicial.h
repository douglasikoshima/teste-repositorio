/*
 * CRequisicaoJudicial.h
 *
 *  Created on: 13/06/2013
 *      Author: Jones Randis
 */

#ifndef CREQUISICAOJUDICIAL_H_
#define CREQUISICAOJUDICIAL_H_

#include <string>
#include <memory>

extern "C" {
#include <xml.h>
#include <xmlotn.h>
#include <xmlerr.h>
#include <xmlsoap.h>
}

#include <xml.hpp>
#include <xmlotn.hpp>
#include <xmlctx.hpp>
#include <xmlsoap.hpp>

#include "config.h"
#include "CParametro.h"

namespace batch {

struct SFilaProcessum;

class CRequisicaoJudicial {
protected:
	TCtx ctxp;
	MsgFactory<TCtx, Tnode>* mfp;
	Factory<TCtx, Tnode> fp;
	TmapParametro& mapParametro;

public:
    char s_URI[256];
    char sCodSessao[128];
    char sEnderecoIP[128];
    char sCodOrdem[128];
    char sCodReq[128];
    char sNrSolicit[128];
    char sArqEsperado[128];
    char sNomeArquivo[256];
    std::string strStatus;
    std::string strCodigoErro;
    std::string strDescricaoErro;
    std::string MsgTry;

	struct SSecurity {
		std::string Username;
		std::string Password;
	};

	struct SCabecalhoVivo {
		std::string loginUsuario;
		std::string canalAtendimento;
		std::string codigoSessao;
		std::string nomeAplicacao;
		std::string token;
		std::string enderecoIP;
		std::string codigoFuncionalidade;
		std::string dataTransacao;
		std::string nomeServico;
		std::string versao;
	};

	struct SEncaminharResultadoProcessamentoRequest {
		std::string codigoOrdem;
		std::string codigoRequisicao;
		std::string numeroSolicitacao;
		std::string arquivoRetornoEsperado;
		std::string nomeArquivoGerado;
		std::string statusProcessamento;
		std::string descricaoStatusProcessamento;
	};

	struct SEncaminharResultadoProcessamentoResponse {
		std::string status;
		std::string codigoErro;
		std::string descricaoErro;
	};

	CRequisicaoJudicial(TmapParametro& mapParametro);
	virtual ~CRequisicaoJudicial();
	bool encaminharResultadoProcessamento(const SSecurity& security, const SCabecalhoVivo& cabecalho, const SEncaminharResultadoProcessamentoRequest& request, SEncaminharResultadoProcessamentoResponse& response);
	bool encaminharResultadoProcessamento(const SFilaProcessum& filaprocessum, const std::string& filename, SEncaminharResultadoProcessamentoResponse& response);
	bool encaminharResultadoProcessamento(const SFilaProcessum& filaprocessum, int64_t cderro, const std::string& dserro, SEncaminharResultadoProcessamentoResponse& response);
	void printNode(NodeRef<Tnode>& node, std::string& text);
	void printNode(NodeRef<Tnode>& node);
	void writeNode(NodeRef<Tnode>& node, const std::string& file);
    bool EnviaPost(SEncaminharResultadoProcessamentoResponse& response);
    std::string GetStdoutFromCommand(char* cmd_prm);
};

} /* namespace batch */
#endif /* CREQUISICAOJUDICIAL_H_ */
