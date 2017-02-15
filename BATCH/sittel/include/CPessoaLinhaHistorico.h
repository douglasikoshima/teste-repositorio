/*
 * CPessoaLinhaHistorico.h
 *
 *  Created on: 09/06/2013
 *      Author: Jones Randis
 */

#ifndef CPESSOALINHAHISTORICO_H_
#define CPESSOALINHAHISTORICO_H_

#include <string>
#include <vector>
#include <sstream>
#include <cstdint>

#include <occi.h>
#include <occiCommon.h>

namespace batch {

struct SAlvoProcessum;
struct SPessoaDocumento;

struct SPessoaLinhaHistorico {
	oracle::occi::Number idpessoalinhahistorico; // pessoalinha.idpessoalinhahistorico
	oracle::occi::Date dtrelacionamento; // pessoalinha.dtrelacionamento
	//oracle::occi::Number nrlinha; // pessoalinha.nrlinha
	//oracle::occi::Number cdarearegistro; // pessoalinha.cdarearegistro
	oracle::occi::Number idtiporelacionamento; // tiporelacionamento.idtiporelacionamento
	std::string sgtiporelacionamento; // tiporelacionamento.sgtiporelacionamento
	oracle::occi::Number idpessoadepara; // pessoadepara.idpessoadepara
	oracle::occi::Number idpessoaorigem; // pessoadepara.idpessoaorigem
	oracle::occi::Number idpessoa; // pessoadepara.idpessoa
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

typedef std::vector<SPessoaLinhaHistorico> TvecPessoaLinhaHistorico;

class CPessoaLinhaHistorico : public SPessoaLinhaHistorico {
protected:
	std::ostringstream sql;
	oracle::occi::Connection* conn;
	oracle::occi::Statement* stmt;
	oracle::occi::ResultSet* rs;
	uint32_t paramIndex;

	void setSql();

public:
	uint32_t _count;

	CPessoaLinhaHistorico(oracle::occi::Connection* conn);
	virtual ~CPessoaLinhaHistorico();
	void where(SAlvoProcessum& alvoprocessum);
	void where(SPessoaDocumento& pessoadocumento);
	void where(SPessoaDocumento& pessoadocumento, SAlvoProcessum& alvoprocessum);
	void execute();
	bool next();
	static void print(const SPessoaLinhaHistorico& pessoalinhahist);
	void print();
	void fetch(TvecPessoaLinhaHistorico& v);
};

} /* namespace batch */
#endif /* CPESSOALINHAHISTORICO_H_ */
