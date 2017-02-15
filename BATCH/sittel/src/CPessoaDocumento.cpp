/*
 * CPessoaDocumento.cpp
 *
 *  Created on: 08/06/2013
 *      Author: Jones Randis
 */


#include "CPessoaDocumento.h"
#include "CLog.h"
#include "CAlvoProcessum.h"
#include "CPessoaLinha.h"
#include "CPessoaLinhaHistorico.h"
#include "config.h"

#include <typeinfo>

namespace batch {

CPessoaDocumento::CPessoaDocumento(oracle::occi::Connection* conn)
: conn(conn), stmt(0), rs(0), paramIndex(0), _count(0)
{
	stmt = conn->createStatement();
}

CPessoaDocumento::~CPessoaDocumento() {
	if (stmt) {
		if (rs) stmt->closeResultSet( rs );
		stmt->getConnection()->terminateStatement( stmt );
		rs=0; stmt=0;
	}
}

void CPessoaDocumento::setSql() {

	sql.str( std::string() );
	sql.clear();
	sql << "select /*+ parallel(8) */ pessoadocumento.idpessoadocumento ";
	sql << "  ,pessoa.idpessoa ";
	sql << "  ,pessoa.nmpessoa ";
	sql << "  ,pessoa.dtcadastro ";
	sql << "  ,tipopessoa.idtipopessoa ";
	sql << "  ,tipopessoa.sgtipopessoa ";
	sql << "  ,documento.iddocumento ";
	sql << "  ,documento.nrdocumento ";
	sql << "  ,documento.sgorgaoexpedidor ";
	sql << "  ,documento.dtemissao ";
	sql << "  ,documento.iduf ";
	sql << "  ,(case when documento.iduf > 0 and tipodocumento.sgclassificacao = 'RG' then (select decode(upper(u.sguf), 'AC','AC','AL','AL','AM','AM','AP','AP','BA','BA','CE','CE','DF','DF','ES','ES','GO','GO','MA','MA','MG','MG','MS','MS','MT','MT','PA','PA','PB','PB','PR','PR','PE','PE','PI','PI','RJ','RJ','RN','RN','RO','RO','RR','RR','RS','RS','SC','SC','SE','SE','SP','SP','TO','TO','INDISPON'||CHR(205)||'VEL ' ) from apoio.uf u where u.iduf = documento.iduf) end) as sguf ";
	//sql << "  ,(select u.sguf from apoio.uf u where u.iduf = documento.iduf) as sguf ";
	sql << "  ,tipodocumento.idtipodocumento ";
	sql << "  ,tipodocumento.sgtipodocumento ";
	sql << "  ,tipodocumento.sgclassificacao ";
	sql << "from CUSTOMER.pessoadocumento pessoadocumento ";
	sql << "  ,customer.pessoa pessoa ";
	sql << "  ,APOIO.TIPOPESSOA TIPOPESSOA ";
	sql << "  ,customer.documento documento ";
	sql << "  ,APOIO.TIPODOCUMENTO TIPODOCUMENTO ";
	sql << "  ,APOIO.SISTEMAORIGEM SISTEMAORIGEM ";
	sql << "where pessoadocumento.idpessoa = pessoa.idpessoa ";
	sql << "and pessoadocumento.iddocumento = documento.iddocumento ";
	sql << "and pessoa.idtipopessoa = tipopessoa.idtipopessoa ";
	sql << "and documento.idtipodocumento = tipodocumento.idtipodocumento ";
	sql << "and tipodocumento.idtipopessoa = tipopessoa.idtipopessoa ";
	sql << "and SISTEMAORIGEM.IDSISTEMAORIGEM = PESSOA.IDSISTEMAORIGEM ";
    sql << "and SISTEMAORIGEM.IDSISTEMAORIGEM <> 333 "; //AMDOCS
}

void CPessoaDocumento::where(oracle::occi::Number& idpessoa, SAlvoProcessum& alvoprocessum)
{
	setSql();
	sql << "and pessoa.idpessoa = :idpessoa ";

    // 27113 - Projeto Sittel - Alteração - Limitação de retorno da consulta
	//sql << "and rownum <= " << LIMITE_QUANTIDADE_RETORNO+1;
	//sql << "order by pessoadocumento.idpessoa, documento.idtipodocumento ";

	stmt->setSQL( sql.str() );
	paramIndex=0;

	stmt->setNumber(++paramIndex, idpessoa);
}

void CPessoaDocumento::where(SAlvoProcessum& alvoprocessum)
{
	setSql();

	if ( !alvoprocessum.cnpj.empty() ) {
		//sql << "and (tipodocumento.sgclassificacao = 'CNPJ' and TRIM(documento.nrdocumento) = :cnpj) ";
		sql << "and pessoa.idpessoa IN ";
		sql << "  (SELECT /*+ parallel(4) */ p.idpessoa ";
		sql << "   FROM CUSTOMER.pessoadocumento p , ";
		sql << "     customer.documento d , ";
		sql << "     APOIO.TIPODOCUMENTO t ";
		sql << "   WHERE p.iddocumento = d.iddocumento ";
		sql << "   AND d.idtipodocumento = t.idtipodocumento ";
		sql << "   AND t.sgclassificacao = 'CNPJ' ";
		sql << "   AND d.nrdocumento = :cnpj) ";
	}
	//else
	//if ( !alvoprocessum.cpf.empty() && !alvoprocessum.documentoassinante.empty() ) {
	//	sql << "and ((tipodocumento.sgclassificacao = 'CPF' and TRIM(documento.nrdocumento) = :cpf) ";
	//	sql << "or (tipodocumento.sgclassificacao = 'RG' and TRIM(documento.nrdocumento) = :documentoassinante)) ";
	//}
	//else
	if ( !alvoprocessum.cpf.empty() ) {
		//sql << "and (tipodocumento.sgclassificacao = 'CPF' and TRIM(documento.nrdocumento) = :cpf) ";
		sql << "and pessoa.idpessoa IN ";
		sql << "  (SELECT /*+ parallel(4) */ p.idpessoa ";
		sql << "   FROM CUSTOMER.pessoadocumento p , ";
		sql << "     customer.documento d , ";
		sql << "     APOIO.TIPODOCUMENTO t ";
		sql << "   WHERE p.iddocumento = d.iddocumento ";
		sql << "   AND d.idtipodocumento = t.idtipodocumento ";
		sql << "   AND t.sgclassificacao = 'CPF' ";
		sql << "   AND d.nrdocumento = :cpf) ";
	}
	//else
	if ( !alvoprocessum.documentoassinante.empty() ) {
		//sql << "and (tipodocumento.sgclassificacao = 'RG' and TRIM(documento.nrdocumento) = :documentoassinante) ";
		sql << "and pessoa.idpessoa IN ";
		sql << "  (SELECT /*+ parallel(4) */ p.idpessoa ";
		sql << "   FROM CUSTOMER.pessoadocumento p , ";
		sql << "     customer.documento d , ";
		sql << "     APOIO.TIPODOCUMENTO t ";
		sql << "   WHERE p.iddocumento = d.iddocumento ";
		sql << "   AND d.idtipodocumento = t.idtipodocumento ";
		sql << "   AND t.sgclassificacao = 'RG' ";
		sql << "   AND d.nrdocumento = :documentoassinante) ";
	}

	if ( !alvoprocessum.nomeassinante.empty() )
		sql << "and UPPER(TRIM(pessoa.nmpessoa)) = UPPER(:nmpessoa) ";

    // 27113 - Projeto Sittel - Alteração - Limitação de retorno da consulta
    //sql << "and rownum <= " << LIMITE_QUANTIDADE_RETORNO+1;
	//sql << "order by pessoadocumento.idpessoa, documento.idtipodocumento ";

	stmt->setSQL( sql.str() );
	paramIndex=0;

	if ( !alvoprocessum.cnpj.empty() ) {
		stmt->setString(++paramIndex, alvoprocessum.cnpj);
	}
	//else
	//if ( !alvoprocessum.cpf.empty() && !alvoprocessum.documentoassinante.empty() ) {
	//	stmt->setString(++paramIndex, alvoprocessum.cpf);
	//	stmt->setString(++paramIndex, alvoprocessum.documentoassinante);
	//}
	//else
	if ( !alvoprocessum.cpf.empty() ) {
		stmt->setString(++paramIndex, alvoprocessum.cpf);
	}
	//else
	if ( !alvoprocessum.documentoassinante.empty() ) {
		stmt->setString(++paramIndex, alvoprocessum.documentoassinante);
	}

	if ( !alvoprocessum.nomeassinante.empty() )
		stmt->setString(++paramIndex, alvoprocessum.nomeassinante);
}

void CPessoaDocumento::where(SPessoaLinha& pessoalinha, SAlvoProcessum& alvoprocessum)
{
	where(pessoalinha.idpessoa, alvoprocessum);
}

void CPessoaDocumento::where(SPessoaLinhaHistorico& pessoalinha, SAlvoProcessum& alvoprocessum)
{
	where(pessoalinha.idpessoa, alvoprocessum);
}

void CPessoaDocumento::execute()
{LOG_FUNC

	LOG_DEBUG("SQL=[" << stmt->getSQL() << "]")
	rs = stmt->executeQuery();
	_count=0;
}

bool CPessoaDocumento::next() {
	if ( rs ) {
		if ( rs->next() ) {
			_count++;
			paramIndex=0;
			idpessoadocumento = rs->getNumber(++paramIndex);
			idpessoa = rs->getNumber(++paramIndex);
			nmpessoa = rs->getString(++paramIndex);
			dtcadastro = rs->getDate(++paramIndex);
			idtipopessoa = rs->getNumber(++paramIndex);
			sgtipopessoa = rs->getString(++paramIndex);
			iddocumento = rs->getNumber(++paramIndex);
			nrdocumento = rs->getString(++paramIndex);
			sgorgaoexpedidor = rs->getString(++paramIndex);
			dtemissao = rs->getDate(++paramIndex);
			iduf = rs->getNumber(++paramIndex);
			sguf = rs->getString(++paramIndex);
			idtipodocumento = rs->getNumber(++paramIndex);
			sgtipodocumento = rs->getString(++paramIndex);
			sgclassificacao = rs->getString(++paramIndex);
			return true;
		} else {
			stmt->closeResultSet( rs );
			rs=0;
		}
	}
	return false;
}

void CPessoaDocumento::print(const SPessoaDocumento& pessoadocumento)
{LOG_FUNC

	LOG_INFO("pessoadocumento.idpessoadocumento: " << static_cast<int64_t>(pessoadocumento.idpessoadocumento))
	LOG_INFO("pessoadocumento.idpessoa: " << static_cast<int64_t>(pessoadocumento.idpessoa))
	LOG_DEBUG("pessoadocumento.nmpessoa: " << pessoadocumento.nmpessoa)
	LOG_INFO("pessoadocumento.iddocumento: " << static_cast<int64_t>(pessoadocumento.iddocumento))
	LOG_DEBUG("pessoadocumento.nrdocumento: " << pessoadocumento.nrdocumento)
	LOG_INFO("pessoadocumento.idtipodocumento: " << static_cast<int64_t>(pessoadocumento.idtipodocumento))
	LOG_DEBUG("pessoadocumento.sgtipodocumento: " << pessoadocumento.sgtipodocumento)
	LOG_DEBUG("pessoadocumento.sgclassificacao: " << pessoadocumento.sgclassificacao)
}

void CPessoaDocumento::print()
{
	CPessoaDocumento::print(*this);
}

void CPessoaDocumento::fetch(TvecPessoaDocumento& v)
{
	while( next() ) {
		SPessoaDocumento* p = this;
		v.push_back( *p );
	}
}

void CPessoaDocumento::fetch(TmapPessoaDocumento& m)
{
	while( next() ) {
		SPessoaDocumento* p = this;
		m[p->idpessoa].push_back( *p );
	}
}

} /* namespace batch */

