/*
 * CContaEndereco.cpp
 *
 *  Created on: 08/06/2013
 *      Author: Jones Randis
 */

#include "CContaEndereco.h"
#include "CLog.h"
#include "config.h"

namespace batch {

CContaEndereco::CContaEndereco(oracle::occi::Connection* conn)
: conn(conn), stmt(0), rs(0), paramIndex(0), _count(0)
{
	stmt = conn->createStatement();
}

CContaEndereco::~CContaEndereco() {
	if (stmt) {
		if (rs) stmt->closeResultSet( rs );
		stmt->getConnection()->terminateStatement( stmt );
		rs=0; stmt=0;
	}
}

void CContaEndereco::setSql() {

	sql.str( std::string() );
	sql.clear();
	sql << "select /*+ parallel(8) */ contaendereco.idcontaendereco ";
	//sql << "  ,contaendereco.idpessoaendereco ";
	sql << "  ,contaendereco.idtipoenderecocobranca ";
	sql << "  ,contaendereco.idconta ";
	sql << "  ,contaendereco.dtexpiracao ";
	sql << "  ,contaendereco.DTULTIMAALTERACAO ";
	//sql << "  ,contaendereco.DTEFETIVACONTAENDERECO ";
	sql << "  ,pessoaendereco.idpessoaendereco ";
	sql << "  ,pessoaendereco.idpessoa ";
	sql << "  ,pessoaendereco.dtcadastro ";
	//sql << "  ,pessoaendereco.DTEXPIRACAO ";
	//sql << "  ,pessoaendereco.DTULTIMAALTERACAO ";
	sql << "  ,pessoaendereco.nrsequencia ";
	sql << "  ,pessoaendereco.inenderecopreferencial ";
	sql << "  ,pessoaendereco.idpais ";
	sql << "  ,pessoaendereco.iduf ";
	sql << "  ,TRIM(decode(upper(uf.sguf), 'AC','AC','AL','AL','AM','AM','AP','AP','BA','BA','CE','CE','DF','DF','ES','ES','GO','GO','MA','MA','MG','MG','MS','MS','MT','MT','PA','PA','PB','PB','PR','PR','PE','PE','PI','PI','RJ','RJ','RN','RN','RO','RO','RR','RR','RS','RS','SC','SC','SE','SE','SP','SP','TO','TO','INDISPON'||CHR(205)||'VEL ' )) AS SGUF";
	sql << "  ,uf.nmuf ";
	sql << "  ,TRIM(pessoaendereco.NMTIPOLOGRADOURO) ";
	sql << "  ,TRIM(pessoaendereco.NMTITULOLOGRADOURO) ";
	// sql << "  ,TRIM(pessoaendereco.NMLOGRADOURO) ";
	sql << "  ,TRIM(NVL(TRIM((TRIM(pessoaendereco.NMTIPOLOGRADOURO) || ' ' || TRIM(pessoaendereco.NMTITULOLOGRADOURO) || ' ' || TRIM(pessoaendereco.NMLOGRADOURO) || ' ' || TRIM(pessoaendereco.NRENDERECO) || ' ' || TRIM(pessoaendereco.DSENDERECOCOMPLEMENTO))),'INDISPON' || CHR(205) || 'VEL ')) as NMLOGRADOURO ";
	sql << "  ,TRIM(pessoaendereco.NRENDERECO) ";
	sql << "  ,TRIM(pessoaendereco.DSENDERECOCOMPLEMENTO) ";
	sql << "  ,TRIM(NVL(TRIM(pessoaendereco.NMBAIRRO),'INDISPON' || CHR(205) || 'VEL '))  as NMBAIRRO ";
	sql << "  ,TRIM(NVL(TRIM(pessoaendereco.NMMUNICIPIO),'INDISPON' || CHR(205) || 'VEL '))  as NMMUNICIPIO ";
	sql << "  ,TRIM(translate(pessoaendereco.NRCEP,' .-', ' ')) as nrCEP ";
	sql << "  ,tipoendereco.idtipoendereco ";
	sql << "  ,tipoendereco.sgtipoendereco ";
	sql << "from customer.contaendereco contaendereco ";
	sql << "  ,apoio.tipoendereco tipoendereco ";
	sql << "  ,CUSTOMER.pessoaendereco pessoaendereco ";
	sql << "  ,apoio.uf uf ";
	sql << "where contaendereco.idpessoaendereco = pessoaendereco.idpessoaendereco ";
	sql << "and pessoaendereco.idtipoendereco = tipoendereco.idtipoendereco ";
	sql << "and pessoaendereco.iduf = uf.iduf ";
	//sql << "and (contaendereco.DTEXPIRACAO is not null and pessoaendereco.DTEXPIRACAO is not null) ";
}

void CContaEndereco::where(SPessoaDocumento& pessoadocumento)
{
	setSql();
	sql << "and pessoaendereco.idpessoa = :idpessoa ";
	
    // 27113 - Projeto Sittel - Alteração - Limitação de retorno da consulta
    // sql << "and rownum <= " << LIMITE_QUANTIDADE_RETORNO+1;
    
	sql << "order by contaendereco.DTULTIMAALTERACAO desc ";

	stmt->setSQL( sql.str() );

	paramIndex=0;
	stmt->setNumber(++paramIndex, pessoadocumento.idpessoa);
}

void CContaEndereco::where(SPessoaLinhaHistorico& pessoalinha)
{
	setSql();
	sql << "and pessoaendereco.idpessoa = :idpessoa ";
    
    // 27113 - Projeto Sittel - Alteração - Limitação de retorno da consulta
	//sql << "and rownum <= " << LIMITE_QUANTIDADE_RETORNO+1;
    
	sql << "order by contaendereco.DTULTIMAALTERACAO desc ";

	stmt->setSQL( sql.str() );

	paramIndex=0;
	stmt->setNumber(++paramIndex, pessoalinha.idpessoa);
}

void CContaEndereco::where(SPessoaLinha& pessoalinha)
{
	setSql();
	sql << "and pessoaendereco.idpessoa = :idpessoa ";

    // 27113 - Projeto Sittel - Alteração - Limitação de retorno da consulta
	//sql << "and rownum <= " << LIMITE_QUANTIDADE_RETORNO+1;
    
	sql << "order by contaendereco.DTULTIMAALTERACAO desc ";

	stmt->setSQL( sql.str() );

	paramIndex=0;
	stmt->setNumber(++paramIndex, pessoalinha.idpessoa);
}

void CContaEndereco::execute()
{LOG_FUNC

	LOG_DEBUG("SQL=[" << stmt->getSQL() << "]")
	rs = stmt->executeQuery();
	_count=0;
}


bool CContaEndereco::next() {
	if ( rs ) {
		if ( rs->next() ) {
			_count++;
			paramIndex=0;
			idcontaendereco = rs->getNumber(++paramIndex);
			//idpessoaendereco = rs->getNumber(++paramIndex);
			idtipoenderecocobranca = rs->getNumber(++paramIndex);
			idconta = rs->getNumber(++paramIndex);
			dtexpiracao = rs->getDate(++paramIndex);
			dtultimaalteracao = rs->getDate(++paramIndex);
			//dtefetivacontaendereco = rs->getString(++paramIndex);
			idpessoaendereco = rs->getNumber(++paramIndex);
			idpessoa = rs->getNumber(++paramIndex);
			dtcadastro = rs->getDate(++paramIndex);
			//dtexpiracao = rs->getDate(++paramIndex);
			//dtultimaalteracao = rs->getDate(++paramIndex);
			nrsequencia = rs->getNumber(++paramIndex);
			inenderecopreferencial = rs->getNumber(++paramIndex);
			idpais = rs->getNumber(++paramIndex);
			iduf = rs->getNumber(++paramIndex);
			sguf = rs->getString(++paramIndex);
			nmuf = rs->getString(++paramIndex);
			nmtipologradouro = rs->getString(++paramIndex);
			nmtitulologradouro = rs->getString(++paramIndex);
			nmlogradouro = rs->getString(++paramIndex);
			nrendereco = rs->getString(++paramIndex);
			dsenderecocomplemento = rs->getString(++paramIndex);
			nmbairro = rs->getString(++paramIndex);
			nmmunicipio = rs->getString(++paramIndex);
			nrcep = rs->getString(++paramIndex);
			idtipoendereco = rs->getNumber(++paramIndex);
			sgtipoendereco = rs->getString(++paramIndex);
			return true;
		} else {
			stmt->closeResultSet( rs );
			rs=0;
		}
	}
	return false;
}

void CContaEndereco::print(const SContaEndereco& contaendereco)
{LOG_FUNC

	LOG_INFO("contaendereco.idcontaendereco: " << static_cast<int64_t>(contaendereco.idcontaendereco))
	LOG_INFO("contaendereco.idtipoenderecocobranca: " << static_cast<int64_t>(contaendereco.idtipoenderecocobranca))
	LOG_INFO("contaendereco.idconta: " << static_cast<int64_t>(contaendereco.idconta))
	LOG_INFO("pessoaendereco.idpessoaendereco: " << static_cast<int64_t>(contaendereco.idpessoaendereco))
	LOG_INFO("pessoaendereco.idpessoa: " << static_cast<int64_t>(contaendereco.idpessoa))
	LOG_INFO("pessoaendereco.idpais: " << static_cast<int64_t>(contaendereco.idpais))
	LOG_INFO("pessoaendereco.iduf: " << static_cast<int64_t>(contaendereco.iduf))
	LOG_DEBUG("pessoaendereco.nmtipologradouro: " << contaendereco.nmtipologradouro)
	LOG_DEBUG("pessoaendereco.nmlogradouro: " << contaendereco.nmlogradouro)
	LOG_DEBUG("pessoaendereco.nrendereco: " << contaendereco.nrendereco)
	LOG_DEBUG("pessoaendereco.dsenderecocomplemento: " << contaendereco.dsenderecocomplemento)
	LOG_DEBUG("pessoaendereco.nmbairro: " << contaendereco.nmbairro)
	LOG_DEBUG("pessoaendereco.nmmunicipio: " << contaendereco.nmmunicipio)
	LOG_DEBUG("pessoaendereco.nrcep: " << contaendereco.nrcep)
	LOG_DEBUG("uf.sguf: " << contaendereco.sguf)
	LOG_DEBUG("uf.nmuf: " << contaendereco.nmuf)
	LOG_INFO("tipoendereco.idtipoendereco: " << static_cast<int64_t>(contaendereco.idtipoendereco))
	LOG_DEBUG("tipoendereco.sgtipoendereco: " << contaendereco.sgtipoendereco)
}

void CContaEndereco::print()
{
	CContaEndereco::print(*this);
}

void CContaEndereco::fetch(TvecContaEndereco& v)
{
	while( next() ) {
		SContaEndereco* p = this;
		v.push_back( *p );
	}
}

} /* namespace batch */
