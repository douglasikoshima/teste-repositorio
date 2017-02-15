/*
 * CLinhaEndereco.cpp
 *
 *  Created on: 11/06/2013
 *      Author: Jones Randis
 */

#include "CLinhaEndereco.h"
#include "CLog.h"
#include "config.h"

namespace batch {

CLinhaEndereco::CLinhaEndereco(oracle::occi::Connection* conn)
: conn(conn), stmt(0), rs(0), paramIndex(0), _count(0)
{
	stmt = conn->createStatement();
}

CLinhaEndereco::~CLinhaEndereco() {
	if (stmt) {
		if (rs) stmt->closeResultSet( rs );
		stmt->getConnection()->terminateStatement( stmt );
		rs=0; stmt=0;
	}
}

void CLinhaEndereco::setSql() {

	sql.str( std::string() );
	sql.clear();

	sql << "select /*+ parallel(8) */ ";
	sql << "  idlinhaconta ";
	sql << "  ,idlinhatelefonica ";
	sql << "  ,idconta ";
	sql << "  ,idcontaendereco ";
	sql << "  ,idtipoenderecocobranca ";
	sql << "  ,dtexpiracao ";
	sql << "  ,DTULTIMAALTERACAO ";
	sql << "  ,idpessoaendereco ";
	sql << "  ,idpessoa ";
	sql << "  ,dtcadastro ";
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
	sql << "  linhaconta.idlinhaconta ";
	sql << "  ,linhaconta.idlinhatelefonica ";
	sql << "  ,linhaconta.idconta ";
	sql << "  ,contaendereco.idcontaendereco ";
	//sql << "  ,contaendereco.idpessoaendereco ";
	sql << "  ,contaendereco.idtipoenderecocobranca ";
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
	sql << "  ,TRIM(pessoaendereco.NMTIPOLOGRADOURO) as NMTIPOLOGRADOURO";
	sql << "  ,TRIM(pessoaendereco.NMTITULOLOGRADOURO) as NMTITULOLOGRADOURO";
	// sql << "  ,TRIM(pessoaendereco.NMLOGRADOURO) as NMLOGRADOURO";
	sql << "  ,TRIM(NVL(TRIM((TRIM(pessoaendereco.NMTIPOLOGRADOURO) || ' ' || TRIM(pessoaendereco.NMTITULOLOGRADOURO) || ' ' || TRIM(pessoaendereco.NMLOGRADOURO) || ' ' || TRIM(pessoaendereco.NRENDERECO) || ' ' || TRIM(pessoaendereco.DSENDERECOCOMPLEMENTO))),'INDISPON' || CHR(205) || 'VEL ')) as NMLOGRADOURO ";    
	sql << "  ,TRIM(pessoaendereco.NRENDERECO) as NRENDERECO";
	sql << "  ,TRIM(pessoaendereco.DSENDERECOCOMPLEMENTO) as DSENDERECOCOMPLEMENTO";
	sql << "  ,TRIM(NVL(TRIM(pessoaendereco.NMBAIRRO),'INDISPON' || CHR(205) || 'VEL '))  as NMBAIRRO ";
	sql << "  ,TRIM(NVL(TRIM(pessoaendereco.NMMUNICIPIO),'INDISPON' || CHR(205) || 'VEL '))  as NMMUNICIPIO ";
	sql << "  ,TRIM(translate(pessoaendereco.NRCEP,' .-', ' ')) as nrCEP ";
	sql << "  ,tipoendereco.idtipoendereco ";
	sql << "  ,tipoendereco.sgtipoendereco ";
	sql << "from customer.linhaconta linhaconta ";
	sql << "  ,CUSTOMER.TIPORELACIONAMENTO TIPORELACIONAMENTO ";
	sql << "  ,customer.contaendereco contaendereco ";
	sql << "  ,CUSTOMER.pessoaendereco pessoaendereco ";
	sql << "  ,apoio.tipoendereco tipoendereco ";
	sql << "  ,apoio.uf uf ";

	sql << "  ,CUSTOMER.PESSOA PESSOA ";
	sql << "  ,APOIO.SISTEMAORIGEM SISTEMAORIGEM ";

	sql << "where linhaconta.idtiporelacionamento = tiporelacionamento.idtiporelacionamento ";
	sql << "and tiporelacionamento.sgtiporelacionamento = 'C' ";
	sql << "and linhaconta.idconta = contaendereco.idconta ";
	sql << "and contaendereco.idpessoaendereco = pessoaendereco.idpessoaendereco ";
	sql << "and pessoaendereco.idtipoendereco = tipoendereco.idtipoendereco ";
	sql << "and pessoaendereco.iduf = uf.iduf ";
	
	sql << "and PESSOA.IDPESSOA = pessoaendereco.IDPESSOA ";
	sql << "and SISTEMAORIGEM.IDSISTEMAORIGEM = PESSOA.IDSISTEMAORIGEM ";
    sql << "and SISTEMAORIGEM.IDSISTEMAORIGEM <> 333 "; //AMDOCS






	//sql << "and (contaendereco.DTEXPIRACAO is not null and pessoaendereco.DTEXPIRACAO is not null) ";
}

void CLinhaEndereco::where(SPessoaLinha& pessoalinha)
{
	setSql();
	sql << "and pessoaendereco.idpessoa = :idpessoa ";
	sql << "and linhaconta.idlinhatelefonica = :idlinhatelefonica ";

    // 27113 - Projeto Sittel - Alteração - Limitação de retorno da consulta
	//sql << "and rownum <= " << LIMITE_QUANTIDADE_RETORNO+1;
    
	sql << "order by contaendereco.DTULTIMAALTERACAO desc ";
	sql << ") where rownum < 2 ";

	stmt->setSQL( sql.str() );
	paramIndex=0;

	stmt->setNumber(++paramIndex, pessoalinha.idpessoa);
	stmt->setNumber(++paramIndex, pessoalinha.idlinhatelefonica);
}

void CLinhaEndereco::where(SPessoaLinhaHistorico& pessoalinha)
{
	setSql();
	sql << "and pessoaendereco.idpessoa = :idpessoa ";
	sql << "and linhaconta.idlinhatelefonica = :idlinhatelefonica ";

    // 27113 - Projeto Sittel - Alteração - Limitação de retorno da consulta
	//sql << "and rownum <= " << LIMITE_QUANTIDADE_RETORNO+1;
    
	sql << "order by contaendereco.DTULTIMAALTERACAO desc ";
	sql << ") where rownum < 2 ";

	stmt->setSQL( sql.str() );
	paramIndex=0;

	stmt->setNumber(++paramIndex, pessoalinha.idpessoa);
	stmt->setNumber(++paramIndex, pessoalinha.idlinhatelefonica);
}

void CLinhaEndereco::where(SPessoaDocumento& pessoadocumento)
{
	setSql();
	sql << "and pessoaendereco.idpessoa = :idpessoa ";


    // 27113 - Projeto Sittel - Alteração - Limitação de retorno da consulta
	//sql << "and rownum <= " << LIMITE_QUANTIDADE_RETORNO+1;

	sql << "order by contaendereco.DTULTIMAALTERACAO desc ";
	sql << ") ";

	stmt->setSQL( sql.str() );
	paramIndex=0;

	stmt->setNumber(++paramIndex, pessoadocumento.idpessoa);
}

void CLinhaEndereco::execute()
{LOG_FUNC

	LOG_DEBUG("SQL=[" << stmt->getSQL() << "]")
	rs = stmt->executeQuery();
	_count=0;
}

bool CLinhaEndereco::next() {
	if ( rs ) {
		if ( rs->next() ) {
			_count++;
			paramIndex=0;
			idlinhaconta = rs->getNumber(++paramIndex);
			idlinhatelefonica = rs->getNumber(++paramIndex);
			idconta = rs->getNumber(++paramIndex);
			idcontaendereco = rs->getNumber(++paramIndex);
			//idpessoaendereco = rs->getNumber(++paramIndex);
			idtipoenderecocobranca = rs->getNumber(++paramIndex);
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

void CLinhaEndereco::print(const SLinhaEndereco& linhaendereco)
{LOG_FUNC

	LOG_INFO("linhaconta.idlinhaconta: " << static_cast<int64_t>(linhaendereco.idlinhaconta))
	LOG_INFO("linhaconta.idlinhatelefonica: " << static_cast<int64_t>(linhaendereco.idlinhatelefonica))
	LOG_INFO("linhaconta.idconta: " << static_cast<int64_t>(linhaendereco.idconta))
	LOG_INFO("contaendereco.idcontaendereco: " << static_cast<int64_t>(linhaendereco.idcontaendereco))
	LOG_INFO("contaendereco.idtipoenderecocobranca: " << static_cast<int64_t>(linhaendereco.idtipoenderecocobranca))
	LOG_INFO("contaendereco.idconta: " << static_cast<int64_t>(linhaendereco.idconta))
	LOG_INFO("pessoaendereco.idpessoaendereco: " << static_cast<int64_t>(linhaendereco.idpessoaendereco))
	LOG_INFO("pessoaendereco.idpessoa: " << static_cast<int64_t>(linhaendereco.idpessoa))
	LOG_INFO("pessoaendereco.idpais: " << static_cast<int64_t>(linhaendereco.idpais))
	LOG_INFO("pessoaendereco.iduf: " << static_cast<int64_t>(linhaendereco.iduf))
	LOG_DEBUG("pessoaendereco.nmtipologradouro: " << linhaendereco.nmtipologradouro)
	LOG_DEBUG("pessoaendereco.nmlogradouro: " << linhaendereco.nmlogradouro)
	LOG_DEBUG("pessoaendereco.nrendereco: " << linhaendereco.nrendereco)
	LOG_DEBUG("pessoaendereco.dsenderecocomplemento: " << linhaendereco.dsenderecocomplemento)
	LOG_DEBUG("pessoaendereco.nmbairro: " << linhaendereco.nmbairro)
	LOG_DEBUG("pessoaendereco.nmmunicipio: " << linhaendereco.nmmunicipio)
	LOG_DEBUG("pessoaendereco.nrcep: " << linhaendereco.nrcep)
	LOG_DEBUG("uf.sguf: " << linhaendereco.sguf)
	LOG_DEBUG("uf.nmuf: " << linhaendereco.nmuf)
	LOG_INFO("tipoendereco.idtipoendereco: " << static_cast<int64_t>(linhaendereco.idtipoendereco))
	LOG_DEBUG("tipoendereco.sgtipoendereco: " << linhaendereco.sgtipoendereco)
}

void CLinhaEndereco::print()
{
	CLinhaEndereco::print(*this);
}

void CLinhaEndereco::fetch(TvecLinhaEndereco& v)
{
	while( next() ) {
		SLinhaEndereco* p = this;
		v.push_back( *p );
	}
}


} /* namespace batch */
