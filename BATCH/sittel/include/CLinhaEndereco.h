/*
 * CLinhaEndereco.h
 *
 *  Created on: 11/06/2013
 *      Author: Jones Randis
 */

#ifndef CLINHAENDERECO_H_
#define CLINHAENDERECO_H_

#include <string>
#include <vector>
#include <sstream>
#include <cstdint>

#include <occi.h>
#include <occiCommon.h>

#include "CPessoaLinha.h"
#include "CPessoaLinhaHistorico.h"
#include "CPessoaDocumento.h"

namespace batch {

struct SLinhaEndereco {
	oracle::occi::Number idlinhaconta; // linhaconta.idlinhaconta
	oracle::occi::Number idlinhatelefonica; // linhaconta.idlinhatelefonica
	oracle::occi::Number idconta; // linhaconta.idconta
	oracle::occi::Number idcontaendereco; // contaendereco.idcontaendereco
	//oracle::occi::Number idpessoaendereco; // contaendereco.idpessoaendereco
	oracle::occi::Number idtipoenderecocobranca; // contaendereco.idtipoenderecocobranca
	oracle::occi::Date dtexpiracao; // contaendereco.dtexpiracao
	oracle::occi::Date dtultimaalteracao; // contaendereco.dtultimaalteracao
	//std::string dtefetivacontaendereco; // contaendereco.dtefetivacontaendereco
	oracle::occi::Number idpessoaendereco; // pessoaendereco.idpessoaendereco
	oracle::occi::Number idpessoa; // pessoaendereco.idpessoa
	oracle::occi::Date dtcadastro; // pessoaendereco.dtcadastro
	//oracle::occi::Date dtexpiracao; // pessoaendereco.dtexpiracao
	//oracle::occi::Date dtultimaalteracao; // pessoaendereco.dtultimaalteracao
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

typedef std::vector<SLinhaEndereco> TvecLinhaEndereco;

class CLinhaEndereco : public SLinhaEndereco {
protected:
	std::ostringstream sql;
	oracle::occi::Connection* conn;
	oracle::occi::Statement* stmt;
	oracle::occi::ResultSet* rs;
	uint32_t paramIndex;

	void setSql();

public:
	uint32_t _count;

	CLinhaEndereco(oracle::occi::Connection* conn);
	virtual ~CLinhaEndereco();
	void where(SPessoaLinha& pessoalinha);
	void where(SPessoaLinhaHistorico& pessoalinha);
	void where(SPessoaDocumento& pessoadocumento);
	void execute();
	bool next();
	static void print(const SLinhaEndereco& linhaendereco);
	void print();
	void fetch(TvecLinhaEndereco& v);
};

} /* namespace batch */
#endif /* CLINHAENDERECO_H_ */
