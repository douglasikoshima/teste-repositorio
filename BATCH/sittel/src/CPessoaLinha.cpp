/*
 * CPessoaLinha.cpp
 *
 *  Created on: 08/06/2013
 *      Author: Jones Randis
 */

#include "CPessoaLinha.h"
#include "CLog.h"
#include "CPessoaDocumento.h"
#include "CAlvoProcessum.h"
#include "config.h"
#include "config.h"

namespace batch {

CPessoaLinha::CPessoaLinha(oracle::occi::Connection* conn)
: conn(conn), stmt(0), rs(0), paramIndex(0), _count(0)
{
	stmt = conn->createStatement();
}

CPessoaLinha::~CPessoaLinha() {
	if (stmt) {
		if (rs) stmt->closeResultSet( rs );
		stmt->getConnection()->terminateStatement( stmt );
		rs=0; stmt=0;
	}
}

void CPessoaLinha::setSql() {

	sql.str( std::string() );
	sql.clear();
	sql << "select /*+ parallel(8) */ PESSOALINHA.idpessoalinha ";
	sql << "  ,PESSOALINHA.DTPESSOALINHA ";
	sql << "  ,TIPORELACIONAMENTO.idtiporelacionamento ";
	sql << "  ,TIPORELACIONAMENTO.sgtiporelacionamento ";
	sql << "  ,pessoadepara.idpessoadepara ";
	sql << "  ,pessoadepara.idpessoa ";
	sql << "  ,pessoadepara.idpessoaorigem ";
	sql << "  ,LINHATELEFONICA.idlinhatelefonica ";
	sql << "  ,linhatelefonica.dthabilitacao ";
	sql << "  ,linhatelefonica.dtexpiracao  ";
	sql << "  ,SISTEMAORIGEM.idsistemaorigem ";
	sql << "  ,SISTEMAORIGEM.sgsistemaorigem ";
	sql << "  ,SISTEMAORIGEM.cdorigem ";
	sql << "  ,linhabase.idlinhabase ";
	sql << "  ,linhabase.nrlinha ";
	sql << "  ,linhabase.dtestadolinha ";
	sql << "  ,AREAREGISTRO.idarearegistro ";
	sql << "  ,AREAREGISTRO.cdarearegistro ";
	sql << "  ,ESTADOLINHA.idestadolinha ";
	sql << "  ,ESTADOLINHA.inlinhacancelada ";
	sql << "  ,ESTADOLINHA.sgclassificacao ";
	sql << "from CUSTOMER.PESSOALINHA PESSOALINHA ";
	sql << "  ,CUSTOMER.TIPORELACIONAMENTO TIPORELACIONAMENTO ";
	sql << "  ,CUSTOMER.PESSOADEPARA PESSOADEPARA ";
	sql << "  ,LINHA.LINHATELEFONICA LINHATELEFONICA ";
	sql << "  ,APOIO.SISTEMAORIGEM SISTEMAORIGEM ";
	sql << "  ,linha.linhabase linhabase ";
	sql << "  ,APOIO.AREAREGISTRO AREAREGISTRO ";
	sql << "  ,APOIO.ESTADOLINHA ESTADOLINHA ";
	sql << "where pessoalinha.idtiporelacionamento = tiporelacionamento.idtiporelacionamento ";
	sql << "and tiporelacionamento.sgtiporelacionamento = 'C' ";
	sql << "and pessoalinha.idpessoadepara = pessoadepara.idpessoadepara ";
	sql << "and PESSOALINHA.IDLINHATELEFONICA = LINHATELEFONICA.IDLINHATELEFONICA ";
	sql << "and linhatelefonica.idsistemaorigem = sistemaorigem.idsistemaorigem ";
	sql << "and sistemaorigem.CDORIGEM = 'MOVEL' ";
    sql << "and sistemaorigem.IDSISTEMAORIGEM <> 333 "; //AMDOCS
	sql << "and linhatelefonica.idlinhabase = linhabase.idlinhabase ";
	sql << "and linhabase.idarearegistro = AREAREGISTRO.idarearegistro ";
	sql << "and linhabase.idestadolinha = ESTADOLINHA.idestadolinha ";
}

void CPessoaLinha::where(SPessoaDocumento& pessoadocumento)
{
	setSql();
	sql << "and pessoadepara.idpessoa = :idpessoa ";


    // 27113 - Projeto Sittel - Alteração - Limitação de retorno da consulta
	//sql << "and rownum <= " << LIMITE_QUANTIDADE_RETORNO+1;
	//sql << "order by PESSOALINHA.DTPESSOALINHA desc ";
    
	stmt->setSQL( sql.str() );

	paramIndex=0;
	stmt->setNumber(++paramIndex, pessoadocumento.idpessoa);
}

void CPessoaLinha::where(SAlvoProcessum& alvoprocessum)
{
	setSql();

	sql << "and ( (linhatelefonica.dthabilitacao <= :inicioperiodoquebra and (linhatelefonica.dtexpiracao is null or linhatelefonica.dtexpiracao >= :fimperiodoquebra)) ";
	sql << "or (linhatelefonica.dthabilitacao >= :inicioperiodoquebra and linhatelefonica.dtexpiracao <= :fimperiodoquebra) ";
	sql << "or (linhatelefonica.dthabilitacao <= :inicioperiodoquebra and (linhatelefonica.dtexpiracao between :inicioperiodoquebra and :fimperiodoquebra)) ";
	sql << "or ((linhatelefonica.dthabilitacao between :inicioperiodoquebra and :fimperiodoquebra) and (linhatelefonica.dtexpiracao is null or linhatelefonica.dtexpiracao >= :fimperiodoquebra)) ) ";

	if ( !alvoprocessum.nrterminalassinante.empty() ) {
	sql << "AND arearegistro.cdarearegistro = to_number(substr(:nrterminalassinante, 1, 2)) AND linhabase.nrlinha = to_number(substr(:nrterminalassinante, 3)) ";
	}

    // 27113 - Projeto Sittel - Alteração - Limitação de retorno da consulta
	//sql << "and rownum <= " << LIMITE_QUANTIDADE_RETORNO+1;

	stmt->setSQL( sql.str() );
	paramIndex=0;

	stmt->setDate(++paramIndex, alvoprocessum.inicioperiodoquebra);
	stmt->setDate(++paramIndex, alvoprocessum.fimperiodoquebra);

	stmt->setDate(++paramIndex, alvoprocessum.inicioperiodoquebra);
	stmt->setDate(++paramIndex, alvoprocessum.fimperiodoquebra);

	stmt->setDate(++paramIndex, alvoprocessum.inicioperiodoquebra);
	stmt->setDate(++paramIndex, alvoprocessum.inicioperiodoquebra);
	stmt->setDate(++paramIndex, alvoprocessum.fimperiodoquebra);

	stmt->setDate(++paramIndex, alvoprocessum.inicioperiodoquebra);
	stmt->setDate(++paramIndex, alvoprocessum.fimperiodoquebra);
	stmt->setDate(++paramIndex, alvoprocessum.fimperiodoquebra);

	if ( !alvoprocessum.nrterminalassinante.empty() ) {
		stmt->setString(++paramIndex, alvoprocessum.nrterminalassinante);
		stmt->setString(++paramIndex, alvoprocessum.nrterminalassinante);
	}
}

void CPessoaLinha::where(SPessoaDocumento& pessoadocumento, SAlvoProcessum& alvoprocessum)
{
	setSql();

	sql << "and pessoadepara.idpessoa = :idpessoa ";

	sql << "and ( (linhatelefonica.dthabilitacao <= :inicioperiodoquebra and (linhatelefonica.dtexpiracao is null or linhatelefonica.dtexpiracao >= :fimperiodoquebra)) ";
	sql << "or (linhatelefonica.dthabilitacao >= :inicioperiodoquebra and linhatelefonica.dtexpiracao <= :fimperiodoquebra) ";
	sql << "or (linhatelefonica.dthabilitacao <= :inicioperiodoquebra and (linhatelefonica.dtexpiracao between :inicioperiodoquebra and :fimperiodoquebra)) ";
	sql << "or ((linhatelefonica.dthabilitacao between :inicioperiodoquebra and :fimperiodoquebra) and (linhatelefonica.dtexpiracao is null or linhatelefonica.dtexpiracao >= :fimperiodoquebra)) ) ";

	if ( !alvoprocessum.nrterminalassinante.empty() ) {
		sql << "AND arearegistro.cdarearegistro = to_number(substr(:nrterminalassinante, 1, 2)) AND linhabase.nrlinha = to_number(substr(:nrterminalassinante, 3)) ";
	}

    // 27113 - Projeto Sittel - Alteração - Limitação de retorno da consulta
    //sql << "and rownum <= " << LIMITE_QUANTIDADE_RETORNO+1;
	//sql << "order by PESSOALINHA.DTPESSOALINHA desc ";

	stmt->setSQL( sql.str() );
	paramIndex=0;

	stmt->setNumber(++paramIndex, pessoadocumento.idpessoa);

	stmt->setDate(++paramIndex, alvoprocessum.inicioperiodoquebra);
	stmt->setDate(++paramIndex, alvoprocessum.fimperiodoquebra);

	stmt->setDate(++paramIndex, alvoprocessum.inicioperiodoquebra);
	stmt->setDate(++paramIndex, alvoprocessum.fimperiodoquebra);

	stmt->setDate(++paramIndex, alvoprocessum.inicioperiodoquebra);
	stmt->setDate(++paramIndex, alvoprocessum.inicioperiodoquebra);
	stmt->setDate(++paramIndex, alvoprocessum.fimperiodoquebra);

	stmt->setDate(++paramIndex, alvoprocessum.inicioperiodoquebra);
	stmt->setDate(++paramIndex, alvoprocessum.fimperiodoquebra);
	stmt->setDate(++paramIndex, alvoprocessum.fimperiodoquebra);

	if ( !alvoprocessum.nrterminalassinante.empty() ) {
		stmt->setString(++paramIndex, alvoprocessum.nrterminalassinante);
		stmt->setString(++paramIndex, alvoprocessum.nrterminalassinante);
	}
}

void CPessoaLinha::execute()
{LOG_FUNC

	LOG_DEBUG("SQL=[" << stmt->getSQL() << "]")
	rs = stmt->executeQuery();
	_count=0;
}

bool CPessoaLinha::next() {
	if ( rs ) {
		if ( rs->next() ) {
			_count++;
			paramIndex=0;
			idpessoalinha = rs->getNumber(++paramIndex);
			dtpessoalinha = rs->getDate(++paramIndex);
			idtiporelacionamento = rs->getNumber(++paramIndex);
			sgtiporelacionamento = rs->getString(++paramIndex);
			idpessoadepara = rs->getNumber(++paramIndex);
			idpessoa = rs->getNumber(++paramIndex);
			idpessoaorigem = rs->getNumber(++paramIndex);
			idlinhatelefonica = rs->getNumber(++paramIndex);
			dthabilitacao = rs->getDate(++paramIndex);
			dtexpiracao = rs->getDate(++paramIndex);
			idsistemaorigem = rs->getNumber(++paramIndex);
			sgsistemaorigem = rs->getString(++paramIndex);
			cdorigem = rs->getString(++paramIndex);
			idlinhabase = rs->getNumber(++paramIndex);
			nrlinha = rs->getNumber(++paramIndex);
			dtestadolinha = rs->getDate(++paramIndex);
			idarearegistro = rs->getNumber(++paramIndex);
			cdarearegistro = rs->getNumber(++paramIndex);
			idestadolinha = rs->getNumber(++paramIndex);
			inlinhacancelada = rs->getNumber(++paramIndex);
			sgclassificacao = rs->getString(++paramIndex);
			return true;
		} else {
			stmt->closeResultSet( rs );
			rs=0;
		}
	}
	return false;
}

void CPessoaLinha::print(const SPessoaLinha& pessoalinha)
{LOG_FUNC

	LOG_INFO("pessoalinha.idpessoalinha: " << static_cast<int64_t>(pessoalinha.idpessoalinha))
	LOG_INFO("pessoalinha.idtiporelacionamento: " << static_cast<int64_t>(pessoalinha.idtiporelacionamento))
	LOG_INFO("pessoalinha.idpessoadepara: " << static_cast<int64_t>(pessoalinha.idpessoadepara))
	LOG_INFO("pessoadepara.idpessoa: " << static_cast<int64_t>(pessoalinha.idpessoa))
	LOG_INFO("pessoalinha.idlinhatelefonica: " << static_cast<int64_t>(pessoalinha.idlinhatelefonica))

	if (pessoalinha.dtpessoalinha.isNull()) LOG_DEBUG("pessoalinha.dtpessoalinha: NULL ")
	else LOG_DEBUG("pessoalinha.dtpessoalinha: " << pessoalinha.dtpessoalinha.toText(LOG_ORADATE_FORMAT))

	if (pessoalinha.dthabilitacao.isNull()) LOG_DEBUG("linhatelefonica.dthabilitacao: NULL ")
	else LOG_DEBUG("linhatelefonica.dthabilitacao: " << pessoalinha.dthabilitacao.toText(LOG_ORADATE_FORMAT))

	if (pessoalinha.dtexpiracao.isNull()) LOG_DEBUG("linhatelefonica.dtexpiracao: NULL ")
	else LOG_DEBUG("linhatelefonica.dtexpiracao: " << pessoalinha.dtexpiracao.toText(LOG_ORADATE_FORMAT))

	if (pessoalinha.idlinhabase.isNull()) LOG_INFO("linhatelefonica.idlinhabase: NULL")
	else LOG_INFO("linhatelefonica.idlinhabase: " << static_cast<int64_t>(pessoalinha.idlinhabase))

	if (pessoalinha.nrlinha.isNull()) LOG_INFO("linhabase.nrlinha: NULL")
	else LOG_DEBUG("linhabase.nrlinha: " << static_cast<int64_t>(pessoalinha.nrlinha))

	if (pessoalinha.dtestadolinha.isNull()) LOG_DEBUG("linhabase.dtestadolinha: NULL ")
	else LOG_DEBUG("linhabase.dtestadolinha: " << pessoalinha.dtestadolinha.toText(LOG_ORADATE_FORMAT))

	if (pessoalinha.idarearegistro.isNull()) LOG_INFO("arearegistro.idarearegistro: NULL")
	else LOG_INFO("arearegistro.idarearegistro: " << static_cast<int64_t>(pessoalinha.idarearegistro))

	if (pessoalinha.cdarearegistro.isNull()) LOG_INFO("arearegistro.cdarearegistro: NULL")
	else LOG_DEBUG("arearegistro.cdarearegistro: " << static_cast<int64_t>(pessoalinha.cdarearegistro))

	if (pessoalinha.idestadolinha.isNull()) LOG_INFO("estadolinha.idestadolinha: NULL")
	else LOG_INFO("estadolinha.idestadolinha: " << static_cast<int64_t>(pessoalinha.idestadolinha))

	if (pessoalinha.inlinhacancelada.isNull()) LOG_INFO("estadolinha.inlinhacancelada: NULL")
	else LOG_DEBUG("estadolinha.inlinhacancelada: " << static_cast<int64_t>(pessoalinha.inlinhacancelada))

	if (pessoalinha.sgclassificacao.empty()) LOG_INFO("estadolinha.sgclassificacao: NULL")
	else LOG_DEBUG("estadolinha.sgclassificacao: " << pessoalinha.sgclassificacao)

}

void CPessoaLinha::print()
{
	CPessoaLinha::print(*this);
}

void CPessoaLinha::fetch(TvecPessoaLinha& v)
{
	while( next() ) {
		SPessoaLinha* p = this;
		v.push_back( *p );
	}
}

} /* namespace batch */
