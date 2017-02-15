/*
 * CPessoaLinha.h
 *
 *  Created on: 08/06/2013
 *      Author: Jones Randis
 */

#ifndef CPESSOALINHA_H_
#define CPESSOALINHA_H_

#include <string>
#include <vector>
#include <sstream>
#include <cstdint>

#include <occi.h>
#include <occiCommon.h>

namespace batch {

struct SAlvoProcessum;
struct SPessoaDocumento;

struct SPessoaLinha {
	oracle::occi::Number idpessoalinha; // pessoalinha.idpessoalinha
	oracle::occi::Date dtpessoalinha; // pessoalinha.dtpessoalinha
	oracle::occi::Number idtiporelacionamento; // tiporelacionamento.idtiporelacionamento
	std::string sgtiporelacionamento; // tiporelacionamento.sgtiporelacionamento
	oracle::occi::Number idpessoadepara; // pessoadepara.idpessoadepara
	oracle::occi::Number idpessoa; // pessoadepara.idpessoa
	oracle::occi::Number idpessoaorigem; // pessoadepara.idpessoaorigem
	oracle::occi::Number idlinhatelefonica; // linhatelefonica.idlinhatelefonica
	oracle::occi::Date dthabilitacao; // linhatelefonica.dthabilitacao
	oracle::occi::Date dtexpiracao; // linhatelefonica.dtexpiracao
	oracle::occi::Number idsistemaorigem; // sistemaorigem.idsistemaorigem
	std::string sgsistemaorigem; // sistemaorigem.sgsistemaorigem
	std::string cdorigem; // sistemaorigem.cdorigem
	oracle::occi::Number idlinhabase; // linhabase.idlinhabase
	oracle::occi::Number nrlinha; // linhabase.nrlinha
	oracle::occi::Date dtestadolinha; // linhabase.dtestadolinha
	oracle::occi::Number idarearegistro; // arearegistro.idarearegistro
	oracle::occi::Number cdarearegistro; // arearegistro.cdarearegistro
	oracle::occi::Number idestadolinha; // estadolinha.idestadolinha
	oracle::occi::Number inlinhacancelada; // estadolinha.inlinhacancelada
	std::string sgclassificacao; // estadolinha.sgclassificacao
};

typedef std::vector<SPessoaLinha> TvecPessoaLinha;

class CPessoaLinha : public SPessoaLinha {
protected:
	std::ostringstream sql;
	oracle::occi::Connection* conn;
	oracle::occi::Statement* stmt;
	oracle::occi::ResultSet* rs;
	uint32_t paramIndex;

	void setSql();

public:
	uint32_t _count;

	CPessoaLinha(oracle::occi::Connection* conn);
	virtual ~CPessoaLinha();
	void where(SPessoaDocumento& pessoadocumento);
	void where(SAlvoProcessum& alvoprocessum);
	void where(SPessoaDocumento& pessoadocumento, SAlvoProcessum& alvoprocessum);
	void execute();
	bool next();
	static void print(const SPessoaLinha& pessoalinha);
	void print();
	void fetch(TvecPessoaLinha& v);
};

} /* namespace batch */
#endif /* CPESSOALINHA_H_ */
