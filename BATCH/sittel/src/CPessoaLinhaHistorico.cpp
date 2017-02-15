/*
 * CPessoaLinhaHistorico.cpp
 *
 *  Created on: 09/06/2013
 *      Author: Jones Randis
 */

#include "CPessoaLinhaHistorico.h"
#include "CLog.h"
#include "config.h"

#include "CAlvoProcessum.h"
#include "CPessoaDocumento.h"

namespace batch {

CPessoaLinhaHistorico::CPessoaLinhaHistorico(oracle::occi::Connection* conn)
: conn(conn), stmt(0), rs(0), paramIndex(0), _count(0)
{
	stmt = conn->createStatement();
}

CPessoaLinhaHistorico::~CPessoaLinhaHistorico() {
	if (stmt) {
		if (rs) stmt->closeResultSet( rs );
		stmt->getConnection()->terminateStatement( stmt );
		rs=0; stmt=0;
	}
}

void CPessoaLinhaHistorico::setSql() {

	sql.str( std::string() );
	sql.clear();

	sql << "select /*+ parallel(8) */ ";
	sql << "  IDPESSOALINHAHISTORICO, ";
	sql << "  DTRELACIONAMENTO, ";
	sql << "  idtiporelacionamento, ";
	sql << "  sgtiporelacionamento, ";
	sql << "  idpessoadepara, ";
	sql << "  idpessoaorigem, ";
	sql << "  idpessoa, ";
	sql << "  idlinhatelefonica, ";
	sql << "  dthabilitacao, ";
	sql << "  dtexpiracao, ";
	sql << "  idsistemaorigem, ";
	sql << "  sgsistemaorigem, ";
	sql << "  cdorigem, ";
	sql << "  idlinhabase, ";
	sql << "  nrlinha, ";
	sql << "  dtestadolinha, ";
	sql << "  idarearegistro, ";
	sql << "  cdarearegistro, ";
	sql << "  idestadolinha, ";
	sql << "  inlinhacancelada, ";
	sql << "  sgclassificacao ";
	sql << "from ( ";

	sql << "  SELECT /*+ parallel(8) */ ";
	sql << "    PESSOALINHA.IDPESSOALINHAHISTORICO, ";
	sql << "    PESSOALINHA.DTRELACIONAMENTO, ";
	sql << "    TIPORELACIONAMENTO.idtiporelacionamento, ";
	sql << "    TIPORELACIONAMENTO.sgtiporelacionamento, ";
	sql << "    pessoadepara.idpessoadepara, ";
	sql << "    pessoadepara.idpessoaorigem, ";
	sql << "    pessoadepara.idpessoa, ";
	sql << "    LINHATELEFONICA.idlinhatelefonica, ";
	sql << "    linhatelefonica.dthabilitacao, ";
	sql << "    linhatelefonica.dtexpiracao, ";
	sql << "    SISTEMAORIGEM.idsistemaorigem, ";
	sql << "    SISTEMAORIGEM.sgsistemaorigem, ";
	sql << "    SISTEMAORIGEM.cdorigem, ";
	sql << "    linhabase.idlinhabase, ";
	sql << "    linhabase.nrlinha, ";
	sql << "    linhabase.dtestadolinha, ";
	sql << "    AREAREGISTRO.idarearegistro, ";
	sql << "    AREAREGISTRO.cdarearegistro, ";
	sql << "    ESTADOLINHA.idestadolinha, ";
	sql << "    ESTADOLINHA.inlinhacancelada, ";
	sql << "    ESTADOLINHA.sgclassificacao ";
	sql << "  FROM ";
	sql << "    CUSTOMER.PESSOALINHAHISTORICO PESSOALINHA, ";
	sql << "    CUSTOMER.TIPORELACIONAMENTO TIPORELACIONAMENTO, ";
	sql << "    CUSTOMER.PESSOADEPARA PESSOADEPARA, ";
	sql << "    LINHA.LINHATELEFONICA LINHATELEFONICA, ";
	sql << "    APOIO.SISTEMAORIGEM SISTEMAORIGEM, ";
	sql << "    linha.linhabase linhabase, ";
	sql << "    APOIO.AREAREGISTRO AREAREGISTRO, ";
	sql << "    APOIO.ESTADOLINHA ESTADOLINHA ";
	sql << "  WHERE ";
	sql << "    pessoalinha.idtiporelacionamento          = tiporelacionamento.idtiporelacionamento ";
	sql << "  AND tiporelacionamento.sgtiporelacionamento = 'C' ";
	sql << "  AND pessoalinha.idpessoadepara              = pessoadepara.idpessoadepara ";
	sql << "  AND PESSOALINHA.IDLINHATELEFONICA           = LINHATELEFONICA.IDLINHATELEFONICA ";
	sql << "  AND linhatelefonica.idsistemaorigem         = sistemaorigem.idsistemaorigem ";
	sql << "  and sistemaorigem.CDORIGEM = 'MOVEL' ";
	sql << "  AND linhatelefonica.idlinhabase             = linhabase.idlinhabase ";
	sql << "  AND linhabase.idarearegistro                = AREAREGISTRO.idarearegistro ";
	sql << "  AND linhabase.idestadolinha                 = ESTADOLINHA.idestadolinha ";

	sql << "  UNION ALL ";

	sql << "  SELECT /*+ parallel(8) */ ";
	sql << "    PESSOALINHA.IDPESSOALINHAHISTORICO, ";
	sql << "    PESSOALINHA.DTRELACIONAMENTO, ";
	sql << "    TIPORELACIONAMENTO.idtiporelacionamento, ";
	sql << "    TIPORELACIONAMENTO.sgtiporelacionamento, ";
	sql << "    pessoadepara.idpessoadepara, ";
	sql << "    pessoadepara.idpessoaorigem, ";
	sql << "    pessoadepara.idpessoa, ";
	sql << "    LINHATELEFONICA.idlinhatelefonica, ";
	sql << "    linhatelefonica.dthabilitacao, ";
	sql << "    linhatelefonica.dtexpiracao, ";
	sql << "    SISTEMAORIGEM.idsistemaorigem, ";
	sql << "    SISTEMAORIGEM.sgsistemaorigem, ";
	sql << "    SISTEMAORIGEM.cdorigem, ";
	sql << "    linhatelefonica.idlinhabase, ";
	sql << "    PESSOALINHA.NRLINHA, ";
	sql << "    NULL dtestadolinha, ";
	sql << "    NULL idarearegistro, ";
	sql << "    PESSOALINHA.CDAREAREGISTRO, ";
	sql << "    NULL idestadolinha, ";
	sql << "    NULL inlinhacancelada, ";
	sql << "    NULL sgclassificacao ";
	sql << "  FROM ";
	sql << "    CUSTOMER.PESSOALINHAHISTORICO PESSOALINHA, ";
	sql << "    CUSTOMER.TIPORELACIONAMENTO TIPORELACIONAMENTO, ";
	sql << "    CUSTOMER.PESSOADEPARA PESSOADEPARA, ";
	sql << "    LINHA.LINHATELEFONICA LINHATELEFONICA, ";
	sql << "    APOIO.SISTEMAORIGEM SISTEMAORIGEM ";
	sql << "  WHERE ";
	sql << "    pessoalinha.idtiporelacionamento          = tiporelacionamento.idtiporelacionamento ";
	sql << "  AND tiporelacionamento.sgtiporelacionamento = 'C' ";
	sql << "  AND pessoalinha.idpessoadepara              = pessoadepara.idpessoadepara ";
	sql << "  AND PESSOALINHA.IDLINHATELEFONICA           = LINHATELEFONICA.IDLINHATELEFONICA ";
	sql << "  AND linhatelefonica.idsistemaorigem         = sistemaorigem.idsistemaorigem ";
	sql << "  and sistemaorigem.CDORIGEM = 'MOVEL' ";
	sql << "  and sistemaorigem.IDSISTEMAORIGEM <> 333 "; //AMDOCS
	// sql << "  AND linhatelefonica.idlinhabase is null ";  VAFEI-26662 Outubro/2015 
	sql << "  AND NOT EXISTS (SELECT 1 " ;
	sql << "                  FROM " ;
	sql << "                      LINHA.LINHABASE LB, " ;
	sql << "                      APOIO.AREAREGISTRO AR " ;
	sql << "                  WHERE " ;
	sql << "                      LINHATELEFONICA.IDLINHABASE = LB.IDLINHABASE " ;
	sql << "                      AND PESSOALINHA.NRLINHA = LB.NRLINHA " ;
	sql << "                      AND PESSOALINHA.CDAREAREGISTRO = AR.CDAREAREGISTRO ) " ;
	sql << ") ";
}

void CPessoaLinhaHistorico::where(SAlvoProcessum& alvoprocessum)
{
	setSql();

	sql << "where ( ";
	sql << "  (dthabilitacao <= :inicioperiodoquebra AND (dtexpiracao  IS NULL OR dtexpiracao >= :fimperiodoquebra)) ";
	sql << "  OR ";
	sql << "  (dthabilitacao >= :inicioperiodoquebra AND dtexpiracao <= :fimperiodoquebra) ";
	sql << "  OR ";
	sql << "  (dthabilitacao <= :inicioperiodoquebra AND (dtexpiracao BETWEEN :inicioperiodoquebra AND :fimperiodoquebra)) ";
	sql << "  OR ";
	sql << "  ((dthabilitacao BETWEEN :inicioperiodoquebra AND :fimperiodoquebra) AND (dtexpiracao IS NULL OR dtexpiracao >= :fimperiodoquebra)) ";
	sql << ") ";

	if ( !alvoprocessum.nrterminalassinante.empty() ) {
	sql << "AND ( ";
	sql << "  cdarearegistro = to_number(SUBSTR(:nrterminalassinante,1,2)) ";
		sql << "  AND nrlinha = to_number(SUBSTR(:nrterminalassinante,3)) ";
		sql << ") ";
	}

	// War Room - Demanda 33266 27113/2012 - Projeto Sittel / Anatel - Checkpoint 05/08/2014
	sql << "AND cdarearegistro is not null ";
	sql << "AND nrlinha is not null ";

    // 27113 - Projeto Sittel - Alteração - Limitação de retorno da consulta
	//sql << " and rownum <= " << LIMITE_QUANTIDADE_RETORNO+1;

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

	stmt->setString(++paramIndex, alvoprocessum.nrterminalassinante);
	stmt->setString(++paramIndex, alvoprocessum.nrterminalassinante);
}

void CPessoaLinhaHistorico::where(SPessoaDocumento& pessoadocumento)
{
	setSql();

	sql << "where idpessoa = :idpessoa ";

	// War Room - Demanda 33266 27113/2012 - Projeto Sittel / Anatel - Checkpoint 05/08/2014
	sql << "AND cdarearegistro is not null ";
	sql << "AND nrlinha is not null ";


    // 27113 - Projeto Sittel - Alteração - Limitação de retorno da consulta
	//sql << "and rownum <= " << LIMITE_QUANTIDADE_RETORNO+1;
    
	stmt->setSQL( sql.str() );

	paramIndex=0;
	stmt->setNumber(++paramIndex, pessoadocumento.idpessoa);
}

void CPessoaLinhaHistorico::where(SPessoaDocumento& pessoadocumento, SAlvoProcessum& alvoprocessum)
{
	setSql();

	sql << "where idpessoa = :idpessoa ";
	sql << "AND ( ";
	sql << "  (dthabilitacao <= :inicioperiodoquebra AND (dtexpiracao  IS NULL OR dtexpiracao >= :fimperiodoquebra)) ";
	sql << "  OR ";
	sql << "  (dthabilitacao >= :inicioperiodoquebra AND dtexpiracao <= :fimperiodoquebra) ";
	sql << "  OR ";
	sql << "  (dthabilitacao <= :inicioperiodoquebra AND (dtexpiracao BETWEEN :inicioperiodoquebra AND :fimperiodoquebra)) ";
	sql << "  OR ";
	sql << "  ((dthabilitacao BETWEEN :inicioperiodoquebra AND :fimperiodoquebra) AND (dtexpiracao IS NULL OR dtexpiracao >= :fimperiodoquebra)) ";
	sql << ") ";

	if ( !alvoprocessum.nrterminalassinante.empty() ) {
		sql << "AND ( ";
		sql << "  cdarearegistro = to_number(SUBSTR(:nrterminalassinante,1,2)) ";
		sql << "  AND nrlinha = to_number(SUBSTR(:nrterminalassinante,3)) ";
		sql << ") ";
	}

	// War Room - Demanda 33266 27113/2012 - Projeto Sittel / Anatel - Checkpoint 05/08/2014
	sql << "AND cdarearegistro is not null ";
	sql << "AND nrlinha is not null ";

    // 27113 - Projeto Sittel - Alteração - Limitação de retorno da consulta
	//sql << "and rownum <= " << LIMITE_QUANTIDADE_RETORNO+1;

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

void CPessoaLinhaHistorico::execute()
{LOG_FUNC

	LOG_DEBUG("SQL=[" << stmt->getSQL() << "]")
	rs = stmt->executeQuery();
	_count=0;
}

bool CPessoaLinhaHistorico::next() {
	if ( rs ) {
		if ( rs->next() ) {
			_count++;
			paramIndex=0;
			idpessoalinhahistorico = rs->getNumber(++paramIndex);
			dtrelacionamento = rs->getDate(++paramIndex);
			idtiporelacionamento = rs->getNumber(++paramIndex);
			sgtiporelacionamento = rs->getString(++paramIndex);
			idpessoadepara = rs->getNumber(++paramIndex);
			idpessoaorigem = rs->getNumber(++paramIndex);
			idpessoa = rs->getNumber(++paramIndex);
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

void CPessoaLinhaHistorico::print(const SPessoaLinhaHistorico& pessoalinhahist)
{LOG_FUNC

	LOG_INFO("pessoalinhahistorico.idpessoalinhahistorico: " << static_cast<int64_t>(pessoalinhahist.idpessoalinhahistorico))
	LOG_INFO("pessoalinhahistorico.idtiporelacionamento: " << static_cast<int64_t>(pessoalinhahist.idtiporelacionamento))
	LOG_INFO("pessoalinhahistorico.idpessoadepara: " << static_cast<int64_t>(pessoalinhahist.idpessoadepara))
	LOG_INFO("pessoadepara.idpessoa: " << static_cast<int64_t>(pessoalinhahist.idpessoa))
	LOG_INFO("pessoalinhahistorico.idlinhatelefonica: " << static_cast<int64_t>(pessoalinhahist.idlinhatelefonica))

	if (pessoalinhahist.dtrelacionamento.isNull()) LOG_DEBUG("pessoalinha.dtrelacionamento: NULL ")
	else LOG_DEBUG("pessoalinha.dtrelacionamento: " << pessoalinhahist.dtrelacionamento.toText(LOG_ORADATE_FORMAT))

	if (pessoalinhahist.dthabilitacao.isNull()) LOG_DEBUG("linhatelefonica.dthabilitacao: NULL ")
	else LOG_DEBUG("linhatelefonica.dthabilitacao: " << pessoalinhahist.dthabilitacao.toText(LOG_ORADATE_FORMAT))

	if (pessoalinhahist.dtexpiracao.isNull()) LOG_DEBUG("linhatelefonica.dtexpiracao: NULL ")
	else LOG_DEBUG("linhatelefonica.dtexpiracao: " << pessoalinhahist.dtexpiracao.toText(LOG_ORADATE_FORMAT))

	if (pessoalinhahist.idlinhabase.isNull()) LOG_INFO("linhatelefonica.idlinhabase: NULL ")
	else LOG_INFO("linhatelefonica.idlinhabase: " << static_cast<int64_t>(pessoalinhahist.idlinhabase))

	if (pessoalinhahist.nrlinha.isNull()) LOG_DEBUG("historico.nrlinha: NULL ")
	else LOG_DEBUG("historico.nrlinha: " << static_cast<int64_t>(pessoalinhahist.nrlinha))

	if (pessoalinhahist.dtestadolinha.isNull()) LOG_DEBUG("linhabase.dtestadolinha: NULL ")
	else LOG_DEBUG("linhabase.dtestadolinha: " << pessoalinhahist.dtestadolinha.toText(LOG_ORADATE_FORMAT))

	if (pessoalinhahist.idarearegistro.isNull()) LOG_INFO("arearegistro.idarearegistro: NULL")
	else LOG_INFO("arearegistro.idarearegistro: " << static_cast<int64_t>(pessoalinhahist.idarearegistro))

	if (pessoalinhahist.cdarearegistro.isNull()) LOG_DEBUG("historico.cdarearegistro: NULL ")
	else LOG_DEBUG("historico.cdarearegistro: " << static_cast<int64_t>(pessoalinhahist.cdarearegistro))

	if (pessoalinhahist.idestadolinha.isNull()) LOG_INFO("estadolinha.idestadolinha: NULL")
	else LOG_INFO("estadolinha.idestadolinha: " << static_cast<int64_t>(pessoalinhahist.idestadolinha))

	if (pessoalinhahist.inlinhacancelada.isNull()) LOG_INFO("estadolinha.inlinhacancelada: NULL")
	else LOG_DEBUG("estadolinha.inlinhacancelada: " << static_cast<int64_t>(pessoalinhahist.inlinhacancelada))

	if (pessoalinhahist.sgclassificacao.empty()) LOG_INFO("estadolinha.sgclassificacao: NULL")
	else LOG_DEBUG("estadolinha.sgclassificacao: " << pessoalinhahist.sgclassificacao)
}

void CPessoaLinhaHistorico::print()
{
	CPessoaLinhaHistorico::print(*this);
}

void CPessoaLinhaHistorico::fetch(TvecPessoaLinhaHistorico& v)
{
	while( next() ) {
		SPessoaLinhaHistorico* p = this;
		v.push_back( *p );
	}
}

} /* namespace batch */
