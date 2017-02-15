/*
 * CInstalacao.h
 *
 *  Created on: 10/06/2013
 *      Author: Jones Randis
 */

#ifndef CINSTALACAO_H_
#define CINSTALACAO_H_

#include <string>

extern "C" {
#include <xml.h>
#include <xmlotn.h>
#include <xmlerr.h>
}

#include <xml.hpp>
#include <xmlotn.hpp>
#include <xmlctx.hpp>

#include "config.h"
#include "CXmlImpl.h"

namespace batch {

class CConfig;
struct SFilaProcessum;
struct SPessoaLinha;
struct SPessoaLinhaHistorico;
struct SLinhaEndereco;
struct SEnderecoLinha;
struct SPessoaEndereco;

struct SInstalacao {
	std::string numeroTerminal;
	std::string tipoEndereco;
	std::string endereco;
	std::string bairro;
	std::string municipio;
	std::string uf;
	std::string cep;
	std::string dataInicio;
	std::string dataFim;
};

class CInstalacao : public CXmlImpl {
protected:
	CConfig& config;
	std::string xsd;

public:
	CInstalacao(CConfig& config);
	virtual ~CInstalacao();

	void write(SFilaProcessum& filaprocessum, std::string& file);
	void append(SInstalacao& instalacao);
	void append(SPessoaLinha& pessoalinha, SLinhaEndereco& linhaendereco);
	void append(SPessoaLinhaHistorico& pessoalinha, SLinhaEndereco& linhaendereco);
	void append(SPessoaLinha& pessoalinha, SPessoaEndereco& pessoaendereco);
	void append(SPessoaLinhaHistorico& pessoalinha, SPessoaEndereco& pessoaendereco);
	void append(SPessoaLinha& pessoalinha);
	void append(SPessoaLinhaHistorico& pessoalinha);
	void append(SPessoaEndereco& pessoaendereco);
	void append(SEnderecoLinha& enderecolinha, SPessoaEndereco& pessoaendereco);
	void loadSchema();
};

} /* namespace batch */
#endif /* CINSTALACAO_H_ */
