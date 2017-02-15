/*
 * CPessoaDocumento.h
 *
 *  Created on: 08/06/2013
 *      Author: Jones Randis
 */

#ifndef CPESSOADOCUMENTO_H_
#define CPESSOADOCUMENTO_H_

#include <string>
#include <sstream>
#include <vector>
#include <map>
#include <cstdint>

#include <occi.h>
#include <occiCommon.h>

namespace batch {

struct SAlvoProcessum;
struct SPessoaLinha;
struct SPessoaLinhaHistorico;

struct SPessoaDocumento {
	oracle::occi::Number idpessoadocumento; // pessoadocumento.idpessoadocumento
	oracle::occi::Number idpessoa; // pessoa.idpessoa
	std::string nmpessoa; // pessoa.nmpessoa
	oracle::occi::Date dtcadastro; // pessoa.dtcadastro
	oracle::occi::Number idtipopessoa; // tipopessoa.idtipopessoa
	std::string sgtipopessoa; // tipopessoa.sgtipopessoa
	oracle::occi::Number iddocumento; // documento.iddocumento
	std::string nrdocumento; // documento.nrdocumento
	std::string sgorgaoexpedidor; // documento.sgorgaoexpedidor
	oracle::occi::Date dtemissao; // documento.dtemissao
	oracle::occi::Number iduf; // documento.iduf
	std::string sguf; // uf.sguf
	oracle::occi::Number idtipodocumento; // tipodocumento.idtipodocumento
	std::string sgtipodocumento; // tipodocumento.sgtipodocumento
	std::string sgclassificacao; // tipodocumento.sgclassificacao
};

typedef std::vector<SPessoaDocumento> TvecPessoaDocumento;
typedef std::map<oracle::occi::Number, TvecPessoaDocumento> TmapPessoaDocumento;

class CPessoaDocumento : public SPessoaDocumento {
protected:
	std::ostringstream sql;
	oracle::occi::Connection* conn;
	oracle::occi::Statement* stmt;
	oracle::occi::ResultSet* rs;
	uint32_t paramIndex;

	void setSql();

public:
	uint32_t _count;

	CPessoaDocumento(oracle::occi::Connection* conn);
	virtual ~CPessoaDocumento();
	void where(oracle::occi::Number& idpessoa, SAlvoProcessum& alvoprocessum);
	void where(SAlvoProcessum& alvoprocessum);
	void where(SPessoaLinha& pessoalinha, SAlvoProcessum& alvoprocessum);
	void where(SPessoaLinhaHistorico& pessoalinha, SAlvoProcessum& alvoprocessum);
	void execute();
	bool next();
	static void print(const SPessoaDocumento& pessoadocumento);
	void print();
	void fetch(TvecPessoaDocumento& v);
	void fetch(TmapPessoaDocumento& m);
};



} /* namespace batch */
#endif /* CPESSOADOCUMENTO_H_ */

