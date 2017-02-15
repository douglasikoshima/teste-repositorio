/*
 * CLinhaConta.cpp
 *
 *  Created on: 08/06/2013
 *      Author: Jones Randis
 */

#include "CLinhaConta.h"
#include "CLog.h"
#include "config.h"

namespace batch {

CLinhaConta::CLinhaConta(oracle::occi::Connection* conn)
: conn(conn), stmt(0), rs(0), paramIndex(0), _count(0)
{
	stmt = conn->createStatement();
}

CLinhaConta::~CLinhaConta() {
	if (stmt) {
		if (rs) stmt->closeResultSet( rs );
		stmt->getConnection()->terminateStatement( stmt );
		rs=0; stmt=0;
	}
}

void CLinhaConta::setSql() {

	sql.str( std::string() );
	sql.clear();
	sql << "select /*+ parallel(8) */ linhaconta.idlinhaconta ";
	sql << "  ,linhaconta.idconta ";
	sql << "  ,linhaconta.DTLINHACONTA ";
	sql << "  ,linhaconta.INLINHAMASTER ";
	//sql << "  ,linhaconta.DTEXPIRACAO ";
	sql << "  ,tiporelacionamento.idtiporelacionamento ";
	sql << "  ,tiporelacionamento.sgtiporelacionamento ";
	sql << "  ,LINHATELEFONICA.idlinhatelefonica ";
	sql << "  ,linhatelefonica.dthabilitacao ";
	sql << "  ,linhatelefonica.dtexpiracao ";
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
	sql << "from customer.linhaconta linhaconta ";
	sql << "  ,CUSTOMER.TIPORELACIONAMENTO TIPORELACIONAMENTO ";
	sql << "  ,LINHA.LINHATELEFONICA LINHATELEFONICA ";
	sql << "  ,APOIO.SISTEMAORIGEM SISTEMAORIGEM ";
	sql << "  ,linha.linhabase linhabase ";
	sql << "  ,APOIO.AREAREGISTRO AREAREGISTRO ";
	sql << "  ,APOIO.ESTADOLINHA ESTADOLINHA ";
	sql << "where linhaconta.idtiporelacionamento = tiporelacionamento.idtiporelacionamento ";
	sql << "and linhabase.idsistemaorigem != 333 ";
	sql << "and tiporelacionamento.sgtiporelacionamento = 'C' ";
	sql << "and linhaconta.idlinhatelefonica = linhatelefonica.idlinhatelefonica ";
	sql << "and linhatelefonica.idsistemaorigem = sistemaorigem.idsistemaorigem ";
	sql << "and sistemaorigem.CDORIGEM = 'MOVEL' ";
	sql << "and linhatelefonica.idlinhabase = linhabase.idlinhabase (+) ";
	sql << "and linhabase.idarearegistro = AREAREGISTRO.idarearegistro (+) ";
	sql << "and linhabase.idestadolinha = ESTADOLINHA.idestadolinha (+) ";
	//sql << "and NVL(estadolinha.inlinhacancelada, 0) = 0 ";
}

void CLinhaConta::where(SContaEndereco& contaendereco, SAlvoProcessum& alvoprocessum)
{
	setSql();
	//sql << "and (linhatelefonica.idlinhabase is not null ";
	sql << "and linhaconta.idconta = :idconta ";

	sql << "and ( (linhatelefonica.dthabilitacao <= :inicioperiodoquebra and (linhatelefonica.dtexpiracao is null or linhatelefonica.dtexpiracao >= :fimperiodoquebra)) ";
	sql << "or (linhatelefonica.dthabilitacao >= :inicioperiodoquebra and linhatelefonica.dtexpiracao <= :fimperiodoquebra) ";
	sql << "or (linhatelefonica.dthabilitacao <= :inicioperiodoquebra and (linhatelefonica.dtexpiracao between :inicioperiodoquebra and :fimperiodoquebra)) ";
	sql << "or ((linhatelefonica.dthabilitacao between :inicioperiodoquebra and :fimperiodoquebra) and (linhatelefonica.dtexpiracao is null or linhatelefonica.dtexpiracao >= :fimperiodoquebra)) ) ";

    // 27113 - Projeto Sittel - Alteração - Limitação de retorno da consulta
	//sql << "and rownum <= " << LIMITE_QUANTIDADE_RETORNO+1;

	stmt->setSQL( sql.str() );
	paramIndex=0;

	stmt->setNumber(++paramIndex, contaendereco.idconta);

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
}

void CLinhaConta::execute()
{LOG_FUNC

	LOG_DEBUG("SQL=[" << stmt->getSQL() << "]")
	rs = stmt->executeQuery();
	_count=0;LOG_FUNC
}

bool CLinhaConta::next() {
	if ( rs ) {
		if ( rs->next() ) {
			_count++;
			paramIndex=0;
			idlinhaconta = rs->getNumber(++paramIndex);
			idconta = rs->getNumber(++paramIndex);
			dtlinhaconta = rs->getDate(++paramIndex);
			inlinhamaster = rs->getNumber(++paramIndex);
			//dtexpiracao = rs->getDate(++paramIndex);
			idtiporelacionamento = rs->getNumber(++paramIndex);
			sgtiporelacionamento = rs->getString(++paramIndex);
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

void CLinhaConta::print(const SLinhaConta& linhaconta)
{LOG_FUNC

	LOG_INFO("linhaconta.idlinhaconta: " << static_cast<int64_t>(linhaconta.idlinhaconta))
	LOG_INFO("linhaconta.idconta: " << static_cast<int64_t>(linhaconta.idconta))

	if ( linhaconta.dtlinhaconta.isNull() ) LOG_DEBUG("linhaconta.dtlinhaconta: NULL")
	else LOG_DEBUG("linhaconta.dtlinhaconta: " << linhaconta.dtlinhaconta.toText(LOG_ORADATE_FORMAT))

	if ( linhaconta.inlinhamaster.isNull() ) LOG_DEBUG("linhaconta.inlinhamaster: NULL")
	else LOG_DEBUG("linhaconta.inlinhamaster: " << static_cast<int64_t>(linhaconta.inlinhamaster))

	LOG_INFO("tiporelacionamento.idtiporelacionamento: " << static_cast<int64_t>(linhaconta.idtiporelacionamento))
	LOG_DEBUG("tiporelacionamento.sgtiporelacionamento: " << linhaconta.sgtiporelacionamento)
	LOG_INFO("linhatelefonica.idlinhatelefonica: " << static_cast<int64_t>(linhaconta.idlinhatelefonica))

	if ( linhaconta.dthabilitacao.isNull() ) LOG_DEBUG("linhatelefonica.dthabilitacao: NULL")
	else LOG_DEBUG("linhatelefonica.dthabilitacao: " << linhaconta.dthabilitacao.toText(LOG_ORADATE_FORMAT))

	if ( linhaconta.dtexpiracao.isNull() ) LOG_DEBUG("linhatelefonica.dtexpiracao: NULL")
	else LOG_DEBUG("linhatelefonica.dtexpiracao: " << linhaconta.dtexpiracao.toText(LOG_ORADATE_FORMAT))

	LOG_INFO("sistemaorigem.idsistemaorigem: " << static_cast<int64_t>(linhaconta.idsistemaorigem))
	LOG_DEBUG("sistemaorigem.sgsistemaorigem: " << linhaconta.sgsistemaorigem)
	LOG_DEBUG("sistemaorigem.cdorigem: " << linhaconta.cdorigem)

	if ( linhaconta.idlinhabase.isNull() ) {
		LOG_INFO("linhabase.idlinhabase: NULL")
	} else {
		LOG_INFO("linhabase.idlinhabase: " << static_cast<int64_t>(linhaconta.idlinhabase))
		LOG_DEBUG("linhabase.nrlinha: " << static_cast<int64_t>(linhaconta.nrlinha))
		LOG_DEBUG("linhabase.dtestadolinha: " << linhaconta.dtestadolinha.toText(LOG_ORADATE_FORMAT))
		LOG_INFO("arearegistro.idarearegistro: " << static_cast<int64_t>(linhaconta.idarearegistro))
		LOG_DEBUG("arearegistro.cdarearegistro: " << static_cast<int64_t>(linhaconta.cdarearegistro))
		LOG_INFO("arearegistro.idestadolinha: " << static_cast<int64_t>(linhaconta.idestadolinha))
		LOG_DEBUG("estadolinha.inlinhacancelada: " << static_cast<int64_t>(linhaconta.inlinhacancelada))
		LOG_DEBUG("estadolinha.sgclassificacao: " << linhaconta.sgclassificacao)
	}
}

void CLinhaConta::print()
{
	CLinhaConta::print(*this);
}

void CLinhaConta::fetch(TvecLinhaConta& v)
{
	while( next() ) {
		SLinhaConta* p = this;
		v.push_back( *p );
	}
}


} /* namespace batch */
