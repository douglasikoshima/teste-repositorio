/*
 * CParametro.h
 *
 *  Created on: 10/06/2013
 *      Author: Jones Randis
 */

#ifndef CPARAMETRO_H_
#define CPARAMETRO_H_

#include <string>
#include <sstream>
#include <vector>
#include <map>
#include <algorithm>
#include <functional>
#include <utility>
#include <cstdint>

#include <occi.h>
#include <occiCommon.h>

namespace batch {

struct SParametro {
	oracle::occi::Number idparametro;
	std::string cdparametro;
	std::string dsvalorparametro;
};

typedef std::vector<SParametro> TvecParametro;
typedef std::map<std::string, SParametro> TmapParametro;
typedef std::vector<std::string> TvecCdParametro;
typedef std::pair<std::string, SParametro> TpairParametro;

class CParametro : public SParametro {
protected:
	std::ostringstream sql;
	oracle::occi::Connection* conn;
	oracle::occi::Statement* stmt;
	oracle::occi::ResultSet* rs;
	uint32_t paramIndex;

	void setSql();

public:
	uint32_t _count;
    std::string nrTentativas;

	CParametro(oracle::occi::Connection* conn);
	virtual ~CParametro();
	void where(const TvecCdParametro& vecparam);
	void execute();
	bool next();
	static void print(const SParametro& parametro);
	static void print(const TvecParametro& v);
	static void print(const TmapParametro& m);
	static void printPair(const TpairParametro& pair);
	void print();
	void fetch(TvecParametro& v);
	void fetch(TmapParametro& m);

	void setSqlParam();
    bool nextParam();
    int GetTentativas();
    
};

} /* namespace batch */
#endif /* CPARAMETRO_H_ */
