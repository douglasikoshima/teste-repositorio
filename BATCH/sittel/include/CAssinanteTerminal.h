/*
 * CAssinanteTerminal.h
 *
 *  Created on: 11/06/2013
 *      Author: Jones Randis
 */

#ifndef CASSINANTETERMINAL_H_
#define CASSINANTETERMINAL_H_

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
#include "CPessoaDocumento.h"
#include "CXmlImpl.h"

namespace batch {

class CConfig;
struct SFilaProcessum;
struct SPessoaLinhaHistorico;
struct SPessoaLinha;

struct SAssinanteTerminal {
	std::string cpfCnpj;
	std::string documento;
	std::string tipo;
	std::string numero;
	std::string dataInicio;
	std::string dataFim;
};

class CAssinanteTerminal : public CXmlImpl {
protected:
	CConfig& config;
	std::string xsd;

public:
	CAssinanteTerminal(CConfig& config);
	virtual ~CAssinanteTerminal();

	void write(SFilaProcessum& filaprocessum, std::string& file);
	void append(SAssinanteTerminal& assinanteterm);
	void append(TvecPessoaDocumento& vpessoadoc, SPessoaLinhaHistorico& pessoalinha);
	void append(TvecPessoaDocumento& vpessoadoc, SPessoaLinha& pessoalinha);
	void loadSchema();
};

} /* namespace batch */
#endif /* CASSINANTETERMINAL_H_ */
