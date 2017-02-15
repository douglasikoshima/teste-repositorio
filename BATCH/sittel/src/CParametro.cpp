/*
 * CParametro.cpp
 *
 *  Created on: 10/06/2013
 *      Author: Jones Randis
 */

#include "CParametro.h"
#include "CLog.h"

namespace batch {

CParametro::CParametro(oracle::occi::Connection* conn)
: conn(conn), stmt(0), rs(0), paramIndex(0), _count(0)
{
	stmt = conn->createStatement();
}

CParametro::~CParametro() {
	if (stmt) {
		if (rs) stmt->closeResultSet( rs );
		stmt->getConnection()->terminateStatement( stmt );
		rs=0; stmt=0;
	}
}

void CParametro::setSql()
{
	sql.str( std::string() );
	sql.clear();
	sql << "select parametro.idparametro ";
	sql << "  ,parametro.cdparametro ";
	sql << "  ,parametro.dsvalorparametro ";
	sql << "from apoio.parametro parametro ";
}

void CParametro::setSqlParam()
{
    sql.str( std::string() );
    sql.clear();
    sql << "select parametro.dsvalorparametro ";
    sql << "from apoio.parametro parametro ";
    sql << "where parametro.cdParametro = \'QTDE_TENTATIVAS_REPROC_SITTEL\' ";
    stmt->setSQL( sql.str() );
    paramIndex=0;

}

void CParametro::where(const TvecCdParametro& vecparam)
{
	setSql();
	sql << "where parametro.cdparametro in (";

	for(int i=1; i <= vecparam.size(); i++) {
		sql << ":" << i;
		if (i < vecparam.size() )
			sql << ", ";
	}

	sql << ") ";

	stmt->setSQL( sql.str() );
	paramIndex=0;

	for (int i=0; i < vecparam.size(); i++)
		stmt->setString(++paramIndex, vecparam.at(i));
}

void CParametro::execute()
{LOG_FUNC

	LOG_DEBUG("SQL=[" << stmt->getSQL() << "]")
	rs = stmt->executeQuery();
	_count=0;
}

bool CParametro::next() {
	if ( rs ) {
		if ( rs->next() ) {
			_count++;
			paramIndex=0;
			idparametro = rs->getNumber(++paramIndex);
			cdparametro = rs->getString(++paramIndex);
			dsvalorparametro = rs->getString(++paramIndex);
			return true;
		} else {
			stmt->closeResultSet( rs );
			rs=0;
		}
	}
	return false;
}

bool CParametro::nextParam() {
	if ( rs ) {
		if ( rs->next() ) {
			_count++;
			paramIndex=0;
			nrTentativas = rs->getString(++paramIndex);
			return true;
		} else {
			stmt->closeResultSet( rs );
			rs=0;
		}
	}
	return false;
}

int CParametro::GetTentativas() {
    return atoi(nrTentativas.c_str());
}

void CParametro::print(const SParametro& parametro)
{LOG_FUNC

	LOG_INFO("parametro.idparametro: " << static_cast<int64_t>(parametro.idparametro))
	LOG_DEBUG("parametro.cdparametro: " << parametro.cdparametro)
	LOG_DEBUG("parametro.dsvalorparametro: " << parametro.dsvalorparametro)
}

void CParametro::print(const TvecParametro& v)
{
	for(int i=0; i < v.size(); i++)
		print( v.at(i) );
}

void CParametro::print(const TmapParametro& m)
{
	std::for_each(m.begin(), m.end(), std::ptr_fun(&CParametro::printPair));
}

void CParametro::printPair(const TpairParametro& pair)
{
	print(pair.second);
}

void CParametro::print()
{
	CParametro::print(*this);
}

void CParametro::fetch(TvecParametro& v)
{
	while( next() ) {
		SParametro* p = this;
		v.push_back( *p );
	}
}

void CParametro::fetch(TmapParametro& m)
{
	while( next() ) {
		SParametro* p = this;
		m[p->cdparametro] = *p;
	}
}

} /* namespace batch */
