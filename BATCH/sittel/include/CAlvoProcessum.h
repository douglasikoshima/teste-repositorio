/*
 * CAlvoProcessum.h
 *
 *  Created on: 08/06/2013
 *      Author: Jones Randis
 */

#ifndef CALVOPROCESSUM_H_
#define CALVOPROCESSUM_H_

#include <string>
#include <sstream>
#include <vector>
#include <cstdint>

#include <occi.h>
#include <occiCommon.h>

#include "CFilaProcessum.h"

namespace batch {

struct SAlvoProcessum {
	oracle::occi::Number idalvoprocessum;
	std::string cpf;
	std::string cnpj;
	std::string documentoassinante;
	std::string nomeassinante;
	std::string nrterminalassinante;
	oracle::occi::Date inicioperiodoquebra;
	oracle::occi::Date fimperiodoquebra;
};

typedef std::vector<SAlvoProcessum> TvecAlvoProcessum;

class CAlvoProcessum : public SAlvoProcessum {
protected:
	std::ostringstream sql;
	oracle::occi::Connection* conn;
	oracle::occi::Statement* stmt;
	oracle::occi::ResultSet* rs;
	uint32_t paramIndex;

	void setSql();

public:
	uint32_t _count;

	CAlvoProcessum(oracle::occi::Connection* conn);
	virtual ~CAlvoProcessum();
	void where(SFilaProcessum& filaprocessum);
	void execute();
	bool next();
	static void print(const SAlvoProcessum& alvoprocessum);
	void print();
	void fetch(TvecAlvoProcessum& v);
};

} /* namespace batch */
#endif /* CALVOPROCESSUM_H_ */
