/*
 * CEnderecoLinha.cpp
 *
 *  Created on: 14/06/2013
 *      Author: Jones Randis
 */

#include "CEnderecoLinha.h"
#include "CLog.h"
#include "CPessoaEndereco.h"
#include "config.h"

namespace batch {

CEnderecoLinha::CEnderecoLinha(oracle::occi::Connection* conn)
: conn(conn), stmt(0), rs(0), paramIndex(0), _count(0)
{
	stmt = conn->createStatement();
}

CEnderecoLinha::~CEnderecoLinha() {
	if (stmt) {
		if (rs) stmt->closeResultSet( rs );
		stmt->getConnection()->terminateStatement( stmt );
		rs=0; stmt=0;
	}
}

void CEnderecoLinha::setSql() {

	sql.str( std::string() );
	sql.clear();
	sql << "select /*+ parallel(8) */ contaendereco.idcontaendereco ";
	sql << "  ,contaendereco.idpessoaendereco ";
	sql << "  ,contaendereco.idtipoenderecocobranca ";
	sql << "  ,contaendereco.idconta ";
	//sql << "  ,contaendereco.dtexpiracao ";
	sql << "  ,contaendereco.DTULTIMAALTERACAO ";
	//sql << "  ,contaendereco.DTEFETIVACONTAENDERECO ";
	sql << "  ,linhaconta.idlinhaconta ";
	//sql << "  ,linhaconta.idconta ";
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
	sql << "from customer.contaendereco contaendereco ";
	sql << "  ,customer.linhaconta linhaconta ";
	sql << "  ,CUSTOMER.TIPORELACIONAMENTO TIPORELACIONAMENTO ";
	sql << "  ,LINHA.LINHATELEFONICA LINHATELEFONICA ";
	sql << "  ,APOIO.SISTEMAORIGEM SISTEMAORIGEM ";
	sql << "  ,linha.linhabase linhabase ";
	sql << "  ,APOIO.AREAREGISTRO AREAREGISTRO ";
	sql << "  ,APOIO.ESTADOLINHA ESTADOLINHA ";
	sql << "where contaendereco.idconta = linhaconta.idconta ";
	sql << "and linhabase.idsistemaorigem != 333 ";
	sql << "and linhaconta.idtiporelacionamento = tiporelacionamento.idtiporelacionamento ";
	sql << "and tiporelacionamento.sgtiporelacionamento = 'C' ";
	sql << "and linhaconta.idlinhatelefonica = linhatelefonica.idlinhatelefonica ";
	sql << "and linhatelefonica.idsistemaorigem = sistemaorigem.idsistemaorigem ";
	sql << "and sistemaorigem.CDORIGEM = 'MOVEL' ";
	sql << "and linhatelefonica.idlinhabase = linhabase.idlinhabase (+) ";
	sql << "and linhabase.idarearegistro = AREAREGISTRO.idarearegistro (+) ";
	sql << "and linhabase.idestadolinha = ESTADOLINHA.idestadolinha (+) ";
	//sql << "and NVL(estadolinha.inlinhacancelada, 0) = 0 ";
}

void CEnderecoLinha::where(SPessoaEndereco& pessoaendereco, SAlvoProcessum& alvoprocessum)
{
	setSql();
	sql << "and contaendereco.idpessoaendereco = :idpessoaendereco ";

	sql << "and ( (linhatelefonica.dthabilitacao <= :inicioperiodoquebra and (linhatelefonica.dtexpiracao is null or linhatelefonica.dtexpiracao >= :fimperiodoquebra)) ";
	sql << "or (linhatelefonica.dthabilitacao >= :inicioperiodoquebra and linhatelefonica.dtexpiracao <= :fimperiodoquebra) ";
	sql << "or (linhatelefonica.dthabilitacao <= :inicioperiodoquebra and (linhatelefonica.dtexpiracao between :inicioperiodoquebra and :fimperiodoquebra)) ";
	sql << "or ((linhatelefonica.dthabilitacao between :inicioperiodoquebra and :fimperiodoquebra) and (linhatelefonica.dtexpiracao is null or linhatelefonica.dtexpiracao >= :fimperiodoquebra)) ) ";

    // 27113 - Projeto Sittel - Alteração - Limitação de retorno da consulta
	//sql << "and rownum <= " << LIMITE_QUANTIDADE_RETORNO+1;

	stmt->setSQL( sql.str() );
	paramIndex=0;

	stmt->setNumber(++paramIndex, pessoaendereco.idpessoaendereco);

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

void CEnderecoLinha::execute()
{LOG_FUNC

	LOG_INFO("SQL=[" << stmt->getSQL() << "]")
	rs = stmt->executeQuery();
	_count=0;
}

bool CEnderecoLinha::next()
{
	if ( rs ) {
		if ( rs->next() ) {
			_count++;
			paramIndex=0;

			idcontaendereco = rs->getNumber(++paramIndex);
			idpessoaendereco = rs->getNumber(++paramIndex);
			idtipoenderecocobranca = rs->getNumber(++paramIndex);
			idconta = rs->getNumber(++paramIndex);
			//dtexpiracao = rs->getDate(++paramIndex);
			dtultimaalteracao = rs->getDate(++paramIndex);
			//dtefetivacontaendereco = rs->getString(++paramIndex);
			idlinhaconta = rs->getNumber(++paramIndex);
			//idconta = rs->getNumber(++paramIndex);
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

void CEnderecoLinha::print(const SEnderecoLinha& enderecolinha)
{LOG_FUNC

	LOG_INFO("contaendereco.idcontaendereco: " << static_cast<int64_t>(enderecolinha.idcontaendereco))
	LOG_INFO("contaendereco.idpessoaendereco: " << static_cast<int64_t>(enderecolinha.idpessoaendereco))
	LOG_INFO("contaendereco.idtipoenderecocobranca: " << static_cast<int64_t>(enderecolinha.idtipoenderecocobranca))
	LOG_INFO("contaendereco.idconta: " << static_cast<int64_t>(enderecolinha.idconta))

	LOG_INFO("linhaconta.idlinhaconta: " << static_cast<int64_t>(enderecolinha.idlinhaconta))
	LOG_INFO("linhaconta.idconta: " << static_cast<int64_t>(enderecolinha.idconta))

	if ( enderecolinha.dtlinhaconta.isNull() ) LOG_DEBUG("linhaconta.dtlinhaconta: NULL")
	else LOG_DEBUG("linhaconta.dtlinhaconta: " << enderecolinha.dtlinhaconta.toText(LOG_ORADATE_FORMAT))

	if ( enderecolinha.inlinhamaster.isNull() ) LOG_DEBUG("linhaconta.inlinhamaster: NULL")
	else LOG_DEBUG("linhaconta.inlinhamaster: " << static_cast<int64_t>(enderecolinha.inlinhamaster))

	LOG_INFO("tiporelacionamento.idtiporelacionamento: " << static_cast<int64_t>(enderecolinha.idtiporelacionamento))
	LOG_DEBUG("tiporelacionamento.sgtiporelacionamento: " << enderecolinha.sgtiporelacionamento)
	LOG_INFO("linhatelefonica.idlinhatelefonica: " << static_cast<int64_t>(enderecolinha.idlinhatelefonica))

	if ( enderecolinha.dthabilitacao.isNull() ) LOG_DEBUG("linhatelefonica.dthabilitacao: NULL")
	else LOG_DEBUG("linhatelefonica.dthabilitacao: " << enderecolinha.dthabilitacao.toText(LOG_ORADATE_FORMAT))

	if ( enderecolinha.dtexpiracao.isNull() ) LOG_DEBUG("linhatelefonica.dtexpiracao: NULL")
	else LOG_DEBUG("linhatelefonica.dtexpiracao: " << enderecolinha.dtexpiracao.toText(LOG_ORADATE_FORMAT))

	LOG_INFO("sistemaorigem.idsistemaorigem: " << static_cast<int64_t>(enderecolinha.idsistemaorigem))
	LOG_DEBUG("sistemaorigem.sgsistemaorigem: " << enderecolinha.sgsistemaorigem)
	LOG_DEBUG("sistemaorigem.cdorigem: " << enderecolinha.cdorigem)

	if ( enderecolinha.idlinhabase.isNull() ) {
		LOG_INFO("linhabase.idlinhabase: NULL")
	} else {
		LOG_INFO("linhabase.idlinhabase: " << static_cast<int64_t>(enderecolinha.idlinhabase))
		LOG_DEBUG("linhabase.nrlinha: " << static_cast<int64_t>(enderecolinha.nrlinha))
		LOG_DEBUG("linhabase.dtestadolinha: " << enderecolinha.dtestadolinha.toText(LOG_ORADATE_FORMAT))
		LOG_INFO("arearegistro.idarearegistro: " << static_cast<int64_t>(enderecolinha.idarearegistro))
		LOG_DEBUG("arearegistro.cdarearegistro: " << static_cast<int64_t>(enderecolinha.cdarearegistro))
		LOG_INFO("arearegistro.idestadolinha: " << static_cast<int64_t>(enderecolinha.idestadolinha))
		LOG_DEBUG("estadolinha.inlinhacancelada: " << static_cast<int64_t>(enderecolinha.inlinhacancelada))
		LOG_DEBUG("estadolinha.sgclassificacao: " << enderecolinha.sgclassificacao)
	}
}

void CEnderecoLinha::print()
{
	CEnderecoLinha::print(*this);
}

void CEnderecoLinha::fetch(TvecEnderecoLinha& v)
{
	while( next() ) {
		SEnderecoLinha* p = this;
		v.push_back( *p );
	}
}

} /* namespace batch */
