/*
 * CContaEndereco.h
 *
 *  Created on: 08/06/2013
 *      Author: Jones Randis
 */

#ifndef CCONTAENDERECO_H_
#define CCONTAENDERECO_H_

#include <string>
#include <vector>
#include <sstream>
#include <cstdint>

#include <occi.h>
#include <occiCommon.h>

#include "CPessoaDocumento.h"
#include "CPessoaLinhaHistorico.h"
#include "CPessoaLinha.h"

namespace batch {

struct SContaEndereco {
	oracle::occi::Number idcontaendereco; // contaendereco.idcontaendereco
	//oracle::occi::Number idpessoaendereco; // contaendereco.idpessoaendereco
	oracle::occi::Number idtipoenderecocobranca; // contaendereco.idtipoenderecocobranca
	oracle::occi::Number idconta; // contaendereco.idconta
	oracle::occi::Date dtexpiracao; // contaendereco.dtexpiracao
	oracle::occi::Date dtultimaalteracao; // contaendereco.DTULTIMAALTERACAO
	//std::string dtefetivacontaendereco; // contaendereco.DTEFETIVACONTAENDERECO
	oracle::occi::Number idtipoendereco; // tipoendereco.idtipoendereco
	std::string sgtipoendereco; // tipoendereco.sgtipoendereco
	oracle::occi::Number idpessoaendereco; // pessoaendereco.idpessoaendereco
	oracle::occi::Number idpessoa; // pessoaendereco.idpessoa
	oracle::occi::Date dtcadastro; // pessoaendereco.dtcadastro
	//oracle::occi::Date dtexpiracao; // pessoaendereco.DTEXPIRACAO
	//oracle::occi::Date dtultimaalteracao; // pessoaendereco.DTULTIMAALTERACAO
	oracle::occi::Number nrsequencia; // pessoaendereco.nrsequencia
	oracle::occi::Number inenderecopreferencial; // pessoaendereco.inenderecopreferencial
	oracle::occi::Number idpais; // pessoaendereco.idpais
	oracle::occi::Number iduf; // pessoaendereco.iduf
	std::string sguf; // uf.sguf
	std::string nmuf; // uf.nmuf
	std::string nmtipologradouro; // pessoaendereco.NMTIPOLOGRADOURO
	std::string nmtitulologradouro; // pessoaendereco.NMTITULOLOGRADOURO
	std::string nmlogradouro; // pessoaendereco.NMLOGRADOURO
	std::string nrendereco; // pessoaendereco.NRENDERECO
	std::string dsenderecocomplemento; // pessoaendereco.DSENDERECOCOMPLEMENTO
	std::string nmbairro; // pessoaendereco.NMBAIRRO
	std::string nmmunicipio; // pessoaendereco.NMMUNICIPIO
	std::string nrcep; // pessoaendereco.NRCEP
};

typedef std::vector<SContaEndereco> TvecContaEndereco;

class CContaEndereco : public SContaEndereco {
protected:
	std::ostringstream sql;
	oracle::occi::Connection* conn;
	oracle::occi::Statement* stmt;
	oracle::occi::ResultSet* rs;
	uint32_t paramIndex;

	void setSql();

public:
	uint32_t _count;

	CContaEndereco(oracle::occi::Connection* conn);
	virtual ~CContaEndereco();
	void where(SPessoaDocumento& pessoadocumento);
	void where(SPessoaLinhaHistorico& pessoalinha);
	void where(SPessoaLinha& pessoalinha);
	void execute();
	bool next();
	static void print(const SContaEndereco& contaendereco);
	void print();
	void fetch(TvecContaEndereco& v);
};

} /* namespace batch */
#endif /* CCONTAENDERECO_H_ */
