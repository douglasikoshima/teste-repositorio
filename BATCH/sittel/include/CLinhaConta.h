/*
 * CLinhaConta.h
 *
 *  Created on: 08/06/2013
 *      Author: Jones Randis
 */

#ifndef CLINHACONTA_H_
#define CLINHACONTA_H_

#include <string>
#include <vector>
#include <sstream>
#include <cstdint>

#include <occi.h>
#include <occiCommon.h>

#include "CContaEndereco.h"
#include "CAlvoProcessum.h"

namespace batch {

struct SLinhaConta {
	oracle::occi::Number idlinhaconta; // linhaconta.idlinhaconta
	oracle::occi::Number idconta; // linhaconta.idconta
	oracle::occi::Date dtlinhaconta; // linhaconta.dtlinhaconta
	oracle::occi::Number inlinhamaster; // linhaconta.inlinhamaster
	//oracle::occi::Date dtexpiracao; // linhaconta.dtexpiracao
	oracle::occi::Number idtiporelacionamento; // tiporelacionamento.idtiporelacionamento
	std::string sgtiporelacionamento; // tiporelacionamento.sgtiporelacionamento
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

typedef std::vector<SLinhaConta> TvecLinhaConta;

class CLinhaConta : public SLinhaConta {
protected:
	std::ostringstream sql;
	oracle::occi::Connection* conn;
	oracle::occi::Statement* stmt;
	oracle::occi::ResultSet* rs;
	uint32_t paramIndex;

	void setSql();

public:
	uint32_t _count;

	CLinhaConta(oracle::occi::Connection* conn);
	virtual ~CLinhaConta();
	void where(SContaEndereco& contaendereco, SAlvoProcessum& alvoprocessum);
	void execute();
	bool next();
	static void print(const SLinhaConta& linhaconta);
	void print();
	void fetch(TvecLinhaConta& v);
};

} /* namespace batch */
#endif /* CLINHACONTA_H_ */
