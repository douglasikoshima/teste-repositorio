/*
 * CFilaProcessum.cpp
 *
 *  Created on: 08/06/2013
 *      Author: Jones Randis
 */

#include "CFilaProcessum.h"
#include "CLog.h"
#include "CException.h"
#include "config.h"

namespace batch {

CFilaProcessum::CFilaProcessum(oracle::occi::Connection* conn)
: conn(conn), stmt(0), rs(0), paramIndex(0), _count(0)
{
	stmt = conn->createStatement();
}

CFilaProcessum::~CFilaProcessum() {
	if (stmt) {
		if (rs) stmt->closeResultSet( rs );
		stmt->getConnection()->terminateStatement( stmt );
		rs=0; stmt=0;
	}
}

void CFilaProcessum::setSql() {

	sql.str( std::string() );
	sql.clear();
	sql << "select idfilaprocessum ";
	sql << "  ,codigoordem ";
	sql << "  ,codigorequisicao ";
	sql << "  ,numerosolicitacao ";
	sql << "  ,tiposolicitacao ";
	sql << "  ,dtinclusao ";
	sql << "  ,cderro ";
	sql << "  ,dserro ";
	sql << "from CUSTOMER.FILAPROCESSUM ";
	sql << "where nvl(inprocessado, 0) = 0 ";
	sql << "order by DTINCLUSAO ";
}



void CFilaProcessum::setSql( char* idFilaProcessum ) {

	sql.str( std::string() );
	sql.clear();
	sql << "select idfilaprocessum ";
	sql << "  ,codigoordem ";
	sql << "  ,codigorequisicao ";
	sql << "  ,numerosolicitacao ";
	sql << "  ,tiposolicitacao ";
	sql << "  ,dtinclusao ";
	sql << "  ,cderro ";
	sql << "  ,dserro ";
	sql << "from CUSTOMER.FILAPROCESSUM ";
	sql << "where nvl(inprocessado, 0) = 0 ";
	sql << "and idfilaprocessum = ";
    sql << idFilaProcessum ;
}



void CFilaProcessum::execute()
{LOG_FUNC

	setSql();
	stmt->setSQL( sql.str() );
    //stmt->setMaxParamSize(2,32762);

	LOG_DEBUG("SQL=[" << stmt->getSQL() << "]")
	rs = stmt->executeQuery();
    //rs->setMaxColumnSize(2, oracle::occi::OCCI_SQLT_LNG);
    
    rs->setMaxColumnSize(2, 4000); 
	_count=0;
}



void CFilaProcessum::execute( char* idFilaProcessum )
{LOG_FUNC

	setSql( idFilaProcessum );
	stmt->setSQL( sql.str() );
    //stmt->setMaxParamSize(2,32762);

	LOG_DEBUG("SQL=[" << stmt->getSQL() << "]")
	rs = stmt->executeQuery();
    //rs->setMaxColumnSize(2, oracle::occi::OCCI_SQLT_LNG);
    
    rs->setMaxColumnSize(2, 4000); 
	_count=0;
}



bool CFilaProcessum::next() {
	if ( rs ) {
		if ( rs->next() ) {
			_count++;
			paramIndex=0;
			idfilaprocessum = rs->getNumber(++paramIndex);
			codigoordem = rs->getString(++paramIndex);
			codigorequisicao = rs->getNumber(++paramIndex);
			numerosolicitacao = rs->getNumber(++paramIndex);
			tiposolicitacao = rs->getInt(++paramIndex);
			dtinclusao = rs->getDate(++paramIndex);
			cderro = rs->getNumber(++paramIndex);
			dserro = rs->getString(++paramIndex);
			return true;
		} else {
			stmt->closeResultSet( rs );
			rs=0;
		}
	}
	return false;
}

void CFilaProcessum::print(const SFilaProcessum& filaprocessum)
{LOG_FUNC

	LOG_INFO("filaprocessum.idfilaprocessum: " << static_cast<int64_t>(filaprocessum.idfilaprocessum))
	LOG_INFO("filaprocessum.tiposolicitacao: " << filaprocessum.tiposolicitacao)
	LOG_DEBUG("filaprocessum.codigoordem: " << filaprocessum.codigoordem)
	LOG_DEBUG("filaprocessum.codigorequisicao: " << static_cast<int64_t>(filaprocessum.codigorequisicao))
	LOG_DEBUG("filaprocessum.numerosolicitacao: " << static_cast<int64_t>(filaprocessum.numerosolicitacao))
	LOG_DEBUG("filaprocessum.dtinclusao: " << filaprocessum.dtinclusao.toText(LOG_ORADATE_FORMAT))

	if ( !filaprocessum.cderro.isNull() )
		LOG_DEBUG("filaprocessum.cderro: " << static_cast<int64_t>(filaprocessum.cderro))

	if ( !filaprocessum.dserro.empty() )
		LOG_DEBUG("filaprocessum.dserro: " << filaprocessum.dserro)
}

void CFilaProcessum::print()
{
	CFilaProcessum::print(*this);
}

void CFilaProcessum::fetch(TvecFilaProcessum& v)
{
	while( next() ) {
		SFilaProcessum* p = this;
		v.push_back( *p );
	}
}


} /* namespace batch */
