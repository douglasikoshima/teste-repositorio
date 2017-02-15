/*
 * CAlvoProcessum.cpp
 *
 *  Created on: 08/06/2013
 *      Author: Jones Randis
 */

#include "CAlvoProcessum.h"
#include "CLog.h"
#include "config.h"

namespace batch {

CAlvoProcessum::CAlvoProcessum(oracle::occi::Connection* conn)
: conn(conn), stmt(0), rs(0), paramIndex(0), _count(0)
{
	stmt = conn->createStatement();
}

CAlvoProcessum::~CAlvoProcessum() {
	if (stmt) {
		if (rs) stmt->closeResultSet( rs );
		stmt->getConnection()->terminateStatement( stmt );
		rs=0; stmt=0;
	}
}

void CAlvoProcessum::setSql() {
	sql.str( std::string() );
	sql.clear();
	sql << "select idalvoprocessum, inicioperiodoquebra, fimperiodoquebra, ";
	sql << "cpf, cnpj, documentoassinante, nomeassinante, nrterminalassinante ";
	sql << "from CUSTOMER.ALVOPROCESSUM ";

}

void CAlvoProcessum::where(SFilaProcessum& filaprocessum)
{
	setSql();
	sql << "where idfilaprocessum = :idfilaprocessum ";
	stmt->setSQL( sql.str() );

	paramIndex=0;
	stmt->setNumber(++paramIndex, filaprocessum.idfilaprocessum);
}

void CAlvoProcessum::execute()
{LOG_FUNC

	LOG_DEBUG("SQL=[" << stmt->getSQL() << "]")
	rs = stmt->executeQuery();
	_count=0;
}

bool CAlvoProcessum::next() {
	if ( rs ) {
		if ( rs->next() ) {
			_count++;
			paramIndex=0;
			idalvoprocessum = rs->getNumber(++paramIndex);
			inicioperiodoquebra = rs->getDate(++paramIndex);
			fimperiodoquebra = rs->getDate(++paramIndex);
			cpf = rs->getString(++paramIndex);
			cnpj = rs->getString(++paramIndex);
			documentoassinante = rs->getString(++paramIndex);
			nomeassinante = rs->getString(++paramIndex);
			nrterminalassinante = rs->getString(++paramIndex);

			if (nrterminalassinante.size() > 11)
				nrterminalassinante.replace(0, 2, "");

			return true;
		} else {
			stmt->closeResultSet( rs );
			rs=0;
		}
	}
	return false;
}

void CAlvoProcessum::print(const SAlvoProcessum& alvoprocessum)
{LOG_FUNC

	LOG_INFO("alvoprocessum.idalvoprocessum: " << static_cast<int64_t>(alvoprocessum.idalvoprocessum))
	LOG_DEBUG("alvoprocessum.cpf: " << alvoprocessum.cpf)
	LOG_DEBUG("alvoprocessum.cnpj: " << alvoprocessum.cnpj)
	LOG_DEBUG("alvoprocessum.documentoassinante: " << alvoprocessum.documentoassinante)
	LOG_DEBUG("alvoprocessum.nomeassinante: " << alvoprocessum.nomeassinante)
	LOG_DEBUG("alvoprocessum.nrterminalassinante: " << alvoprocessum.nrterminalassinante)
	LOG_DEBUG("alvoprocessum.inicioperiodoquebra: " << alvoprocessum.inicioperiodoquebra.toText(LOG_ORADATE_FORMAT))
	LOG_DEBUG("alvoprocessum.fimperiodoquebra: " << alvoprocessum.fimperiodoquebra.toText(LOG_ORADATE_FORMAT))
}

void CAlvoProcessum::print()
{
	CAlvoProcessum::print(*this);
}

void CAlvoProcessum::fetch(TvecAlvoProcessum& v)
{
	while( next() ) {
		SAlvoProcessum* p = this;
		v.push_back( *p );
	}
}

} /* namespace batch */
