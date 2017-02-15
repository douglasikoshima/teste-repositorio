/*
 * CEnderecoLinha.h
 *
 *  Created on: 14/06/2013
 *      Author: Jones Randis
 */

#ifndef CENDERECOLINHA_H_
#define CENDERECOLINHA_H_

#include <string>
#include <vector>
#include <sstream>
#include <cstdint>

#include <occi.h>
#include <occiCommon.h>

#include "CAlvoProcessum.h"

namespace batch {

class SPessoaEndereco;

struct SEnderecoLinha {
	oracle::occi::Number idcontaendereco; // contaendereco.idcontaendereco
	oracle::occi::Number idpessoaendereco; // contaendereco.idpessoaendereco
	oracle::occi::Number idtipoenderecocobranca; // contaendereco.idtipoenderecocobranca
	oracle::occi::Number idconta; //contaendereco.idconta
	//oracle::occi::Date dtexpiracao; // contaendereco.dtexpiracao
	oracle::occi::Date dtultimaalteracao; // contaendereco.dtultimaalteracao
	//std::string dtefetivacontaendereco; // contaendereco.dtefetivacontaendereco
	oracle::occi::Number idlinhaconta; // linhaconta.idlinhaconta
	//oracle::occi::Number idconta; // linhaconta.idconta
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

typedef std::vector<SEnderecoLinha> TvecEnderecoLinha;

class CEnderecoLinha : public SEnderecoLinha {
protected:
	std::ostringstream sql;
	oracle::occi::Connection* conn;
	oracle::occi::Statement* stmt;
	oracle::occi::ResultSet* rs;
	uint32_t paramIndex;

	void setSql();

public:
	uint32_t _count;

	CEnderecoLinha(oracle::occi::Connection* conn);
	virtual ~CEnderecoLinha();
	void where(SPessoaEndereco& pessoaendereco, SAlvoProcessum& alvoprocessum);
	void execute();
	bool next();
	static void print(const SEnderecoLinha& enderecolinha);
	void print();
	void fetch(TvecEnderecoLinha& v);
};

} /* namespace batch */
#endif /* CENDERECOLINHA_H_ */
