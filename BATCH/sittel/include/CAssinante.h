/*
 * CAssinante.h
 *
 *  Created on: 11/06/2013
 *      Author: Jones Randis
 */

#ifndef CASSINANTE_H_
#define CASSINANTE_H_

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

struct SAssinante {
	std::string cpfCnpj;
	std::string documento;
	std::string nome;
	std::string tipo;
};

class CAssinante : public CXmlImpl {
protected:
	CConfig& config;
	std::string xsd;

public:
	CAssinante(CConfig& config);
	virtual ~CAssinante();

	void write(SFilaProcessum& filaprocessum, std::string& file);
	void append(SAssinante& assinante);
	void append(TvecPessoaDocumento& vpessoadoc);
	void loadSchema();
};

} /* namespace batch */
#endif /* CASSINANTE_H_ */
