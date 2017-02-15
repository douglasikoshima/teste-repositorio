/*
 * CFilaProcessum.h
 *
 *  Created on: 08/06/2013
 *      Author: Jones Randis
 */

#ifndef CFILAPROCESSUM_H_
#define CFILAPROCESSUM_H_

#include <string>
#include <sstream>
#include <vector>
#include <cstdint>

#include <occi.h>
#include <occiCommon.h>

namespace batch {

struct SFilaProcessum {
	SFilaProcessum(): tiposolicitacao(0) {};
	oracle::occi::Number idfilaprocessum;
	std::string codigoordem;
	oracle::occi::Number codigorequisicao;
	oracle::occi::Number numerosolicitacao;
	int32_t tiposolicitacao;
	oracle::occi::Date dtinclusao;
	oracle::occi::Number cderro;
	std::string dserro;
    std::string MsgTentativa;
};

typedef std::vector<SFilaProcessum> TvecFilaProcessum;

class CFilaProcessum : public SFilaProcessum {
protected:
	std::ostringstream sql;
	oracle::occi::Connection* conn;
	oracle::occi::Statement* stmt;
	oracle::occi::ResultSet* rs;
	uint32_t paramIndex;

	void setSql();
    void setSql( char* idFilaProcessum );

public:
	uint64_t _count;

	enum {
		CONSULTA_ASSINANTE = 1,
		CONSULTA_TERMINAL_ASSINANTE = 2,
		CONSULTA_INSTALACAO = 3
	}
	ETipoSolicitacao;

	CFilaProcessum(oracle::occi::Connection* conn);
	virtual ~CFilaProcessum();
	void execute();
    void execute( char* idFilaProcessum );
	bool next();
	static void print(const SFilaProcessum& filaprocessum);
	void print();
	void fetch(TvecFilaProcessum& v);
};

} /* namespace batch */
#endif /* CFILAPROCESSUM_H_ */
