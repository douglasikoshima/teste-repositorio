/*
 * CPessoaEndereco.h
 *
 *  Created on: 08/06/2013
 *      Author: Jones Randis
 */

#ifndef CPESSOAENDERECO_H_
#define CPESSOAENDERECO_H_

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

struct SPessoaEndereco {
	oracle::occi::Number idpessoaendereco; // pessoaendereco.idpessoaendereco
	oracle::occi::Number idpessoa; // pessoaendereco.idpessoa
	oracle::occi::Date dtcadastro; // pessoaendereco.dtcadastro
	oracle::occi::Date dtexpiracao; // pessoaendereco.dtexpiracao
	oracle::occi::Date dtultimaalteracao; // pessoaendereco.dtultimaalteracao
	oracle::occi::Number nrsequencia; // pessoaendereco.nrsequencia
	oracle::occi::Number inenderecopreferencial; // pessoaendereco.inenderecopreferencial
	oracle::occi::Number idpais; // pessoaendereco.idpais
	oracle::occi::Number iduf; // pessoaendereco.iduf
	std::string sguf; // uf.sguf
	std::string nmuf; // uf.nmuf
	std::string nmtipologradouro; // pessoaendereco.nmtipologradouro
	std::string nmtitulologradouro; // pessoaendereco.nmtitulologradouro
	std::string nmlogradouro; // pessoaendereco.nmlogradouro
	std::string nrendereco; // pessoaendereco.nrendereco
	std::string dsenderecocomplemento; // pessoaendereco.dsenderecocomplemento
	std::string nmbairro; // pessoaendereco.nmbairro
	std::string nmmunicipio; // pessoaendereco.nmmunicipio
	std::string nrcep; // pessoaendereco.nrcep
	oracle::occi::Number idtipoendereco; // tipoendereco.idtipoendereco
	std::string sgtipoendereco; // tipoendereco.sgtipoendereco
};

typedef std::vector<SPessoaEndereco> TvecPessoaEndereco;

class CPessoaEndereco : public SPessoaEndereco {
protected:
	std::ostringstream sql;
	oracle::occi::Connection* conn;
	oracle::occi::Statement* stmt;
	oracle::occi::ResultSet* rs;
	uint32_t paramIndex;

	void setSql();

public:
	uint32_t _count;

	CPessoaEndereco(oracle::occi::Connection* conn);
	virtual ~CPessoaEndereco();
	void where(SPessoaDocumento& pessoadocumento);
	void where(SPessoaLinhaHistorico& pessoalinha);
	void where(SPessoaLinha& pessoalinha);
	void execute();
	bool next();
	static void print(const SPessoaEndereco& pessoaendereco);
	void print();
	void fetch(TvecPessoaEndereco& v);
};

} /* namespace batch */
#endif /* CPESSOAENDERECO_H_ */
