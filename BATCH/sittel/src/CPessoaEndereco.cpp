/*
 * CPessoaEndereco.cpp
 *
 *  Created on: 08/06/2013
 *      Author: Jones Randis
 */

#include "CPessoaEndereco.h"
#include "CLog.h"
#include "config.h"

namespace batch {

CPessoaEndereco::CPessoaEndereco(oracle::occi::Connection* conn)
: conn(conn), stmt(0), rs(0), paramIndex(0), _count(0)
{
	stmt = conn->createStatement();
}

CPessoaEndereco::~CPessoaEndereco() {
	if (stmt) {
		if (rs) stmt->closeResultSet( rs );
		stmt->getConnection()->terminateStatement( stmt );
		rs=0; stmt=0;
	}
}

void CPessoaEndereco::setSql() {

	sql.str( std::string() );
	sql.clear();

	sql << "select /*+ parallel(8) */ ";
	sql << "  idpessoaendereco ";
	sql << "  ,idpessoa ";
	sql << "  ,dtcadastro ";
	sql << "  ,DTEXPIRACAO ";
	sql << "  ,DTULTIMAALTERACAO ";
	sql << "  ,nrsequencia ";
	sql << "  ,inenderecopreferencial ";
	sql << "  ,idpais ";
	sql << "  ,iduf ";
	sql << "  ,sguf ";
	sql << "  ,nmuf ";
	sql << "  ,NMTIPOLOGRADOURO ";
	sql << "  ,NMTITULOLOGRADOURO ";
    sql << "  ,NMLOGRADOURO ";
	sql << "  ,NRENDERECO ";
	sql << "  ,DSENDERECOCOMPLEMENTO ";
	sql << "  ,NMBAIRRO ";
	sql << "  ,NMMUNICIPIO ";
	sql << "  ,NRCEP ";
	sql << "  ,idtipoendereco ";
	sql << "  ,sgtipoendereco ";
	sql << "from ( ";

	sql << "select /*+ parallel(8) */ ";
	sql << "  pessoaendereco.idpessoaendereco ";
	sql << "  ,pessoaendereco.idpessoa ";
	sql << "  ,pessoaendereco.dtcadastro ";
	sql << "  ,pessoaendereco.DTEXPIRACAO ";
	sql << "  ,pessoaendereco.DTULTIMAALTERACAO ";
	sql << "  ,pessoaendereco.nrsequencia ";
	sql << "  ,pessoaendereco.inenderecopreferencial ";
	sql << "  ,pessoaendereco.idpais ";
	sql << "  ,pessoaendereco.iduf ";
	sql << "  ,TRIM(decode(upper(uf.sguf), 'AC','AC','AL','AL','AM','AM','AP','AP','BA','BA','CE','CE','DF','DF','ES','ES','GO','GO','MA','MA','MG','MG','MS','MS','MT','MT','PA','PA','PB','PB','PR','PR','PE','PE','PI','PI','RJ','RJ','RN','RN','RO','RO','RR','RR','RS','RS','SC','SC','SE','SE','SP','SP','TO','TO','INDISPON'||CHR(205)||'VEL ' )) AS SGUF";
	sql << "  ,uf.nmuf ";
	sql << "  ,TRIM(pessoaendereco.NMTIPOLOGRADOURO) as NMTIPOLOGRADOURO ";
	sql << "  ,TRIM(pessoaendereco.NMTITULOLOGRADOURO) as NMTITULOLOGRADOURO ";
	//sql << "  ,TRIM(pessoaendereco.NMLOGRADOURO) as NMLOGRADOURO ";
    sql << "  ,TRIM(NVL(TRIM((TRIM(pessoaendereco.NMTIPOLOGRADOURO) || ' ' || TRIM(pessoaendereco.NMTITULOLOGRADOURO) || ' ' || TRIM(pessoaendereco.NMLOGRADOURO) || ' ' || TRIM(pessoaendereco.NRENDERECO) || ' ' || TRIM(pessoaendereco.DSENDERECOCOMPLEMENTO))),'INDISPON' || CHR(205) || 'VEL ')) as NMLOGRADOURO ";    
	sql << "  ,TRIM(pessoaendereco.NRENDERECO) as NRENDERECO ";
	sql << "  ,TRIM(pessoaendereco.DSENDERECOCOMPLEMENTO) as DSENDERECOCOMPLEMENTO ";
	sql << "  ,TRIM(NVL(TRIM(pessoaendereco.NMBAIRRO),'INDISPON' || CHR(205) || 'VEL '))  as NMBAIRRO ";
	sql << "  ,TRIM(NVL(TRIM(pessoaendereco.NMMUNICIPIO),'INDISPON' || CHR(205) || 'VEL '))  as NMMUNICIPIO ";
	sql << "  ,TRIM(translate(pessoaendereco.NRCEP,' .-', ' ')) as nrCEP ";
	sql << "  ,tipoendereco.idtipoendereco ";
	sql << "  ,tipoendereco.sgtipoendereco ";
	sql << "from CUSTOMER.pessoaendereco pessoaendereco ";
	sql << "  ,APOIO.TIPOENDERECO TIPOENDERECO ";
	sql << "  ,apoio.uf uf ";
		
	sql << "  ,CUSTOMER.pessoa pessoa ";
	sql << "  ,APOIO.SISTEMAORIGEM SISTEMAORIGEM ";
	 
	sql << "where pessoaendereco.idtipoendereco = tipoendereco.idtipoendereco ";
	sql << "and pessoaendereco.iduf = uf.iduf ";

	sql << "and PESSOA.IDPESSOA = pessoaendereco.IDPESSOA ";
	sql << "and SISTEMAORIGEM.IDSISTEMAORIGEM = PESSOA.IDSISTEMAORIGEM ";
    sql << "and SISTEMAORIGEM.IDSISTEMAORIGEM <> 333 "; //AMDOCS

	//sql << "and pessoaendereco.DTEXPIRACAO is not null ";
}

void CPessoaEndereco::where(SPessoaDocumento& pessoadocumento)
{
	setSql();
	sql << "and pessoaendereco.idpessoa = :idpessoa ";
    
    // 27113 - Projeto Sittel - Alteração - Limitação de retorno da consulta
    //sql << "and rownum <= " << LIMITE_QUANTIDADE_RETORNO+1;
    
	sql << "order by pessoaendereco.DTULTIMAALTERACAO desc ";
	sql << ") ";
    
	stmt->setSQL( sql.str() );

	paramIndex=0;
	stmt->setNumber(++paramIndex, pessoadocumento.idpessoa);
}

void CPessoaEndereco::where(SPessoaLinhaHistorico& pessoalinha)
{
	setSql();
	sql << "and pessoaendereco.idpessoa = :idpessoa ";


    // 27113 - Projeto Sittel - Alteração - Limitação de retorno da consulta
	//sql << "and rownum <= " << LIMITE_QUANTIDADE_RETORNO+1;
    
	sql << "order by pessoaendereco.DTULTIMAALTERACAO desc ";
	sql << ") where rownum < 2 ";
    
	stmt->setSQL( sql.str() );

	paramIndex=0;
	stmt->setNumber(++paramIndex, pessoalinha.idpessoa);
}

void CPessoaEndereco::where(SPessoaLinha& pessoalinha)
{
	setSql();
	sql << "and pessoaendereco.idpessoa = :idpessoa ";


    // 27113 - Projeto Sittel - Alteração - Limitação de retorno da consulta
	//sql << "and rownum <= " << LIMITE_QUANTIDADE_RETORNO+1;
    
	sql << "order by pessoaendereco.DTULTIMAALTERACAO desc ";
	sql << ") where rownum < 2 ";
    
	stmt->setSQL( sql.str() );

	paramIndex=0;
	stmt->setNumber(++paramIndex, pessoalinha.idpessoa);
}

void CPessoaEndereco::execute()
{LOG_FUNC

	LOG_DEBUG("SQL=[" << stmt->getSQL() << "]")
	rs = stmt->executeQuery();
	_count=0;
}

bool CPessoaEndereco::next() {
	if ( rs ) {
		if ( rs->next() ) {
			_count++;
			paramIndex=0;
			idpessoaendereco = rs->getNumber(++paramIndex);
			idpessoa = rs->getNumber(++paramIndex);
			dtcadastro = rs->getDate(++paramIndex);
			dtexpiracao = rs->getDate(++paramIndex);
			dtultimaalteracao = rs->getDate(++paramIndex);
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

void CPessoaEndereco::print(const SPessoaEndereco& pessoaendereco)
{LOG_FUNC

	LOG_INFO("pessoaendereco.idpessoaendereco: " << static_cast<int64_t>(pessoaendereco.idpessoaendereco))
	LOG_INFO("pessoaendereco.idpessoa: " << static_cast<int64_t>(pessoaendereco.idpessoa))
	LOG_INFO("pessoaendereco.idpais: " << static_cast<int64_t>(pessoaendereco.idpais))
	LOG_INFO("pessoaendereco.iduf: " << static_cast<int64_t>(pessoaendereco.iduf))
	LOG_DEBUG("pessoaendereco.nmtipologradouro: " << pessoaendereco.nmtipologradouro)
	LOG_DEBUG("pessoaendereco.nmlogradouro: " << pessoaendereco.nmlogradouro)
	LOG_DEBUG("pessoaendereco.nrendereco: " << pessoaendereco.nrendereco)
	LOG_DEBUG("pessoaendereco.dsenderecocomplemento: " << pessoaendereco.dsenderecocomplemento)
	LOG_DEBUG("pessoaendereco.nmbairro: " << pessoaendereco.nmbairro)
	LOG_DEBUG("pessoaendereco.nmmunicipio: " << pessoaendereco.nmmunicipio)
	LOG_DEBUG("pessoaendereco.nrcep: " << pessoaendereco.nrcep)
	LOG_DEBUG("uf.sguf: " << pessoaendereco.sguf)
	LOG_DEBUG("uf.nmuf: " << pessoaendereco.nmuf)
	LOG_INFO("tipoendereco.idtipoendereco: " << static_cast<int64_t>(pessoaendereco.idtipoendereco))
	LOG_DEBUG("tipoendereco.sgtipoendereco: " << pessoaendereco.sgtipoendereco)
}

void CPessoaEndereco::print()
{
	CPessoaEndereco::print( *this );
}

void CPessoaEndereco::fetch(TvecPessoaEndereco& v)
{
	while( next() ) {
		SPessoaEndereco* p = this;
		v.push_back( *p );
	}
}


} /* namespace batch */
