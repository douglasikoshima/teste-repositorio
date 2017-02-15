///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Sincronismo
 * @usecase PessoaEndereco
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @author  Eder Martins
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:22 $
 **/
///////////////////////////////////////////////////////////////////////////////

#include "tuxfw.h"
#include "PessoaEndereco.h"
#include "PessoaEnderecopc.h"
#include "Pessoa.h"
#include "Tools.h"
#include "PrePagoException.h"

CPessoaEndereco::CPessoaEndereco( void )
{
    memset(&tPessoaEndereco, 0x00, sizeof(TPessoaEndereco));
    memset(&tPessoaEnderecoArr, 0x00, sizeof(TPessoaEnderecoArr));
}

CPessoaEndereco::~CPessoaEndereco( void )
{
	clPessoaEnderecopc.desaloca( &tPessoaEnderecoArr );
}

void CPessoaEndereco::enderecoSujo(void)
{
	clPessoaEnderecopc.proCEnderecoSujo(&tPessoaEndereco);
}

void CPessoaEndereco::apagaPessoaEndereco( void )
{
	clPessoaEnderecopc.proCApagaPessoaEndereco(&tPessoaEndereco);
}

void CPessoaEndereco::inserePessoaEndereco( void )
{
    clPessoaEnderecopc.proCInserePessoaEndereco( &tPessoaEndereco );
}

void CPessoaEndereco::BuscaSped(
                                    char * szcdCEP, 
                                    char * sznmLogradouro,
                                    char * sznmBairro,
                                    char * szMunicipio,
                                    char * szCdLogradouro ,
                                    char * szInCNL ,
                                    char * szCdIBGE
                               )
{
    clPessoaEnderecopc.buscaInfSPED( 
                                     szcdCEP, 
                                     sznmLogradouro,
                                     sznmBairro,
                                     szMunicipio,
                                     szCdLogradouro ,
                                     szInCNL ,
                                     szCdIBGE
                                   );
}

bool CPessoaEndereco::buscaPessoaEndereco( void )
{
    return clPessoaEnderecopc.proCBuscaPessoaEndereco( &tPessoaEndereco );
}

bool  CPessoaEndereco::buscaPessoaEndereco( TPessoaEndereco &tPessoaEnderecoAUX )
{
    return clPessoaEnderecopc.proCBuscaPessoaEndereco( &tPessoaEnderecoAUX );
}

void CPessoaEndereco::falsoInserePessoaEndereco( void )
{
    this->setNrSequencia(SEM_VALOR);
    this->setIdPais(ID_NULL);
    this->setIdUf(ID_NULL);
    this->setSqSincronismo(SEM_VALOR);
    this->setTsSincronismo(SEM_VALOR);

    clPessoaEnderecopc.proCInserePessoaEndereco( &tPessoaEndereco );
}

void CPessoaEndereco::falsoInserePessoaEndereco( TPessoaEndereco &tPessoaEnderecoAUX  )
{
    strcpy(tPessoaEnderecoAUX.szNrSequencia, SEM_VALOR);
    strcpy(tPessoaEnderecoAUX.szIdPais, ID_NULL);
    strcpy(tPessoaEnderecoAUX.szIdUf, ID_NULL);

    clPessoaEnderecopc.proCInserePessoaEndereco( &tPessoaEnderecoAUX );
}

void CPessoaEndereco::atualizaPessoaEndereco( void )
{
    clPessoaEnderecopc.proCAtualizaPessoaEndereco( &tPessoaEndereco );
}

void CPessoaEndereco::atualizaIdTipoEnderecoPessoaEndereco(void)
{
    clPessoaEnderecopc.proCAtualizaIdTipoEndereco( &tPessoaEndereco );
}

bool CPessoaEndereco::buscaPessoaEnderecoPorIdPessoa( void )
{
	return clPessoaEnderecopc.proCBuscaPessoaEnderecoPorIdPessoa( &tPessoaEnderecoArr, &tPessoaEndereco );
}

bool CPessoaEndereco::buscaPessoaEnderecoContaPorIdPessoa( char* pzcIdConta )
{
	return clPessoaEnderecopc.proCBuscaPessoaEnderecoContaPorIdPessoa( &tPessoaEnderecoArr, &tPessoaEndereco, pzcIdConta );
}

bool CPessoaEndereco::buscaEnderecoContaPorIdPessoa( char* pzcIdConta )
{
	return clPessoaEnderecopc.proCBuscaEnderecoContaPorIdPessoa( &tPessoaEnderecoArr, &tPessoaEndereco, pzcIdConta );
}

void CPessoaEndereco::Zera( void )
{
	clPessoaEnderecopc.desaloca(&tPessoaEnderecoArr);
}

///////////////////////////////////////////////////////////////////////////////
// Métodos de SET.
///////////////////////////////////////////////////////////////////////////////
void CPessoaEndereco::setInEnderecoSujo(char *pszInEnderecoSujo)
{
    strcpy( tPessoaEndereco.szInEnderecoSujo, pszInEnderecoSujo );
}

void CPessoaEndereco::setIdPessoa( char* pszIdPessoa )
{
    strcpy( tPessoaEndereco.szIdPessoa, pszIdPessoa );
}

void CPessoaEndereco::setIdPessoa( int iIdPessoa )
{
    sprintf( tPessoaEndereco.szIdPessoa, "%i", iIdPessoa );
}

void CPessoaEndereco::setNrSequencia( char* pszNrSequencia )
{
    strcpy( tPessoaEndereco.szNrSequencia, pszNrSequencia );
}

void CPessoaEndereco::setIdEnderecoSistemaOrigem( char* pszIdEnderecoSistemaOrigem )
{
    strcpy( tPessoaEndereco.szIdEnderecoSistemaOrigem, pszIdEnderecoSistemaOrigem );
}

void CPessoaEndereco::setIdPessoaEndereco( char* pszIdPessoaEndereco )
{
    strcpy( tPessoaEndereco.szIdPessoaEndereco, pszIdPessoaEndereco );
}

void CPessoaEndereco::setIdTipoEndereco( char* pszIdTipoEndereco )
{
    strcpy( tPessoaEndereco.szIdTipoEndereco, pszIdTipoEndereco );
}

void CPessoaEndereco::setIdSistemaOrigem( char* pszIdSistemaOrigem )
{
    strcpy( tPessoaEndereco.szIdSistemaOrigem, pszIdSistemaOrigem );
};

void CPessoaEndereco::setTsSincronismo( char* pszTsSincronismo )
{
    strcpy( tPessoaEndereco.szTsSincronismo, pszTsSincronismo );
}

void CPessoaEndereco::setSqSincronismo( char* pszSqSincronismo )
{
    strcpy( tPessoaEndereco.szSqSincronismo, pszSqSincronismo );
}

void CPessoaEndereco::setIdPais(char *pszIdPais)
{
    strcpy(tPessoaEndereco.szIdPais, pszIdPais);
}

void CPessoaEndereco::setIdUf(char *pszIdUf)
{
    strcpy(tPessoaEndereco.szIdUf, pszIdUf);
}

void CPessoaEndereco::setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao)
{
    strcpy(tPessoaEndereco.szIdUsuarioAlteracao, pszIdUsuarioAlteracao);
}

///////////////////////////////////////////////////////////////////////////////
// Métodos de GET (recuperam dados de tPessoaEndereco)
///////////////////////////////////////////////////////////////////////////////
char* CPessoaEndereco::getInEnderecoSujo( void )
{
    static char szAux[LEN_INENDERECOSUJO + LEN_EOS];

    strcpy( szAux, tPessoaEndereco.szInEnderecoSujo );
    return szAux;
}

char* CPessoaEndereco::getSqSincronismo( void )
{
    static char szAux[LEN_SQSINCRONISMO + LEN_EOS];

    strcpy( szAux, tPessoaEndereco.szSqSincronismo );
    return szAux;
}

char* CPessoaEndereco::getIdEnderecoSistemaOrigem( void )
{
    static char szAux[LEN_IDENDERECOSISTEMAORIGEM + LEN_EOS];

    strcpy( szAux, tPessoaEndereco.szIdEnderecoSistemaOrigem );
    return szAux;
}

char* CPessoaEndereco::getIdPessoaEndereco(void)
{
    static char szAux[LEN_IDPESSOAENDERECO + LEN_EOS];

    strcpy( szAux, tPessoaEndereco.szIdPessoaEndereco );
    return szAux;
}

char *CPessoaEndereco::getIdTipoEndereco(void)
{
    static char szAux[LEN_IDTIPOENDERECO + LEN_EOS];

    strcpy(szAux, tPessoaEndereco.szIdTipoEndereco);
    return szAux;
}

void CPessoaEndereco::setStruct(TPessoaEndereco *ptPessoaEndereco)
{
    memcpy(&tPessoaEndereco, ptPessoaEndereco, sizeof(TPessoaEndereco));
    memset(ptPessoaEndereco, 0x00, sizeof(TPessoaEndereco));
}

char* CPessoaEndereco::getNmTituloLogradouro(void)
{
    static char szAux[LEN_NMTITULOLOGRADOURO + LEN_EOS];

    strcpy(szAux, tPessoaEndereco.szNmTituloLogradouro);
    return szAux;
}

char* CPessoaEndereco::getNmTipoLogradouro(void)
{
    static char szAux[LEN_NMLOGRADOURO + LEN_EOS];

    strcpy(szAux, tPessoaEndereco.szNmTipoLogradouro);
    return szAux;
}

char* CPessoaEndereco::getNmLogradouro(void)
{
    static char szAux[LEN_NMLOGRADOURO + LEN_EOS];

    strcpy(szAux, tPessoaEndereco.szNmLogradouro);
    return szAux;
}

char* CPessoaEndereco::getNrEndereco(void)
{
    static char szAux[LEN_DSENDERECOCOMPLEMENTO + LEN_EOS];

    strcpy(szAux, tPessoaEndereco.szNrEndereco);
    return szAux;
}

char* CPessoaEndereco::getDsEnderecoComplemento(void)
{
    static char szAux[LEN_DSENDERECOCOMPLEMENTO + LEN_EOS];

    strcpy(szAux, tPessoaEndereco.szDsEnderecoComplemento);
    return szAux;
}

char* CPessoaEndereco::getNmBairro(void)
{
    static char szAux[LEN_NMBAIRRO + LEN_EOS];

    strcpy(szAux, tPessoaEndereco.szNmBairro);
    return szAux;
}

char* CPessoaEndereco::getNmMunicipio(void)
{
    static char szAux[LEN_NMMUNICIPIO + LEN_EOS];

    strcpy(szAux, tPessoaEndereco.szNmMunicipio);
    return szAux;
}

char* CPessoaEndereco::getNrCep(void)
{
    static char szAux[LEN_NRCEP + LEN_EOS];

    strcpy(szAux, tPessoaEndereco.szNrCep);
    return szAux;
}

char* CPessoaEndereco::getIdUF(void)
{
    static char szAux[LEN_IDUF + LEN_EOS];

    strcpy(szAux, tPessoaEndereco.szIdUf);
    return szAux;
}

char* CPessoaEndereco::getIdPais(void)
{
    static char szAux[LEN_IDPAIS + LEN_EOS];

    strcpy(szAux, tPessoaEndereco.szIdPais);
    return szAux;
}

///////////////////////////////////////////////////////////////////////////////
// Métodos de GET (recuperam dados de tPessoaEnderecoArr)
///////////////////////////////////////////////////////////////////////////////
char* CPessoaEndereco::getIdPessoaEndereco(int iIndex)
{
    static char szAux[LEN_IDPESSOAENDERECO + LEN_EOS];

	if( tPessoaEnderecoArr.iQuantidade > 0 )
	{
		if( iIndex >= tPessoaEnderecoArr.iQuantidade )
			iIndex = tPessoaEnderecoArr.iQuantidade-1;
		if( iIndex < 0 )
			iIndex = 0;
		strcpy( szAux, tPessoaEnderecoArr.pztPessoaEndereco[iIndex].szIdPessoaEndereco );
	}
	else
		strcpy( szAux, "" );
	return szAux;
}

char *CPessoaEndereco::getIdTipoEndereco(int iIndex)
{
    static char szAux[LEN_IDTIPOENDERECO + LEN_EOS];

	if( tPessoaEnderecoArr.iQuantidade > 0 )
	{
		if( iIndex >= tPessoaEnderecoArr.iQuantidade )
			iIndex = tPessoaEnderecoArr.iQuantidade-1;
		if( iIndex < 0 )
			iIndex = 0;
	    strcpy(szAux, tPessoaEnderecoArr.pztPessoaEndereco[iIndex].szIdTipoEndereco);
	}
	else
		strcpy( szAux, "" );
	return szAux;
}

char* CPessoaEndereco::getNmTituloLogradouro(int iIndex)
{
    static char szAux[LEN_NMTITULOLOGRADOURO + LEN_EOS];

	if( tPessoaEnderecoArr.iQuantidade > 0 )
	{
		if( iIndex >= tPessoaEnderecoArr.iQuantidade )
			iIndex = tPessoaEnderecoArr.iQuantidade-1;
		if( iIndex < 0 )
			iIndex = 0;
	    strcpy(szAux, tPessoaEnderecoArr.pztPessoaEndereco[iIndex].szNmTituloLogradouro);
	}
	else
		strcpy( szAux, "" );
	return szAux;
}

char* CPessoaEndereco::getNmTipoLogradouro(int iIndex)
{
    static char szAux[LEN_NMLOGRADOURO + LEN_EOS];

	if( tPessoaEnderecoArr.iQuantidade > 0 )
	{
		if( iIndex >= tPessoaEnderecoArr.iQuantidade )
			iIndex = tPessoaEnderecoArr.iQuantidade-1;
		if( iIndex < 0 )
			iIndex = 0;
	    strcpy(szAux, tPessoaEnderecoArr.pztPessoaEndereco[iIndex].szNmTipoLogradouro);
	}
	else
		strcpy( szAux, "" );
	return szAux;
}

char* CPessoaEndereco::getNmLogradouro(int iIndex)
{
    static char szAux[LEN_NMLOGRADOURO + LEN_EOS];

	if( tPessoaEnderecoArr.iQuantidade > 0 )
	{
		if( iIndex >= tPessoaEnderecoArr.iQuantidade )
			iIndex = tPessoaEnderecoArr.iQuantidade-1;
		if( iIndex < 0 )
			iIndex = 0;
	    strcpy(szAux, tPessoaEnderecoArr.pztPessoaEndereco[iIndex].szNmLogradouro);
	}
	else
		strcpy( szAux, "" );
	return szAux;
}

char* CPessoaEndereco::getNrEndereco(int iIndex)
{
    static char szAux[LEN_DSENDERECOCOMPLEMENTO + LEN_EOS];

	if( tPessoaEnderecoArr.iQuantidade > 0 )
	{
		if( iIndex >= tPessoaEnderecoArr.iQuantidade )
			iIndex = tPessoaEnderecoArr.iQuantidade-1;
		if( iIndex < 0 )
			iIndex = 0;
	    strcpy(szAux, tPessoaEnderecoArr.pztPessoaEndereco[iIndex].szNrEndereco);
	}
	else
		strcpy( szAux, "" );
	return szAux;
}

char* CPessoaEndereco::getDsEnderecoComplemento(int iIndex)
{
    static char szAux[LEN_DSENDERECOCOMPLEMENTO + LEN_EOS];

	if( tPessoaEnderecoArr.iQuantidade > 0 )
	{
		if( iIndex >= tPessoaEnderecoArr.iQuantidade )
			iIndex = tPessoaEnderecoArr.iQuantidade-1;
		if( iIndex < 0 )
			iIndex = 0;
	    strcpy(szAux, tPessoaEnderecoArr.pztPessoaEndereco[iIndex].szDsEnderecoComplemento);
	}
	else
		strcpy( szAux, "" );
	return szAux;
}

char* CPessoaEndereco::getNmBairro(int iIndex)
{
    static char szAux[LEN_NMBAIRRO + LEN_EOS];

	if( tPessoaEnderecoArr.iQuantidade > 0 )
	{
		if( iIndex >= tPessoaEnderecoArr.iQuantidade )
			iIndex = tPessoaEnderecoArr.iQuantidade-1;
		if( iIndex < 0 )
			iIndex = 0;
	    strcpy(szAux, tPessoaEnderecoArr.pztPessoaEndereco[iIndex].szNmBairro);
	}
	else
		strcpy( szAux, "" );
	return szAux;
}

char* CPessoaEndereco::getNmMunicipio(int iIndex)
{
    static char szAux[LEN_NMMUNICIPIO + LEN_EOS];

	if( tPessoaEnderecoArr.iQuantidade > 0 )
	{
		if( iIndex >= tPessoaEnderecoArr.iQuantidade )
			iIndex = tPessoaEnderecoArr.iQuantidade-1;
		if( iIndex < 0 )
			iIndex = 0;
	    strcpy(szAux, tPessoaEnderecoArr.pztPessoaEndereco[iIndex].szNmMunicipio);
	}
	else
		strcpy( szAux, "" );
	return szAux;
}

char* CPessoaEndereco::getNrCep(int iIndex)
{
    static char szAux[LEN_NRCEP + LEN_EOS];

	if( tPessoaEnderecoArr.iQuantidade > 0 )
	{
		if( iIndex >= tPessoaEnderecoArr.iQuantidade )
			iIndex = tPessoaEnderecoArr.iQuantidade-1;
		if( iIndex < 0 )
			iIndex = 0;
	    strcpy(szAux, tPessoaEnderecoArr.pztPessoaEndereco[iIndex].szNrCep);
	}
	else
		strcpy( szAux, "" );
	return szAux;
}

char* CPessoaEndereco::getIdUF(int iIndex)
{
    static char szAux[LEN_IDUF + LEN_EOS];

	if( tPessoaEnderecoArr.iQuantidade > 0 )
	{
		if( iIndex >= tPessoaEnderecoArr.iQuantidade )
			iIndex = tPessoaEnderecoArr.iQuantidade-1;
		if( iIndex < 0 )
			iIndex = 0;
	    strcpy(szAux, tPessoaEnderecoArr.pztPessoaEndereco[iIndex].szIdUf);
	}
	else
		strcpy( szAux, "" );
	return szAux;
}

char* CPessoaEndereco::getIdPais(int iIndex)
{
    static char szAux[LEN_IDPAIS + LEN_EOS];

	if( tPessoaEnderecoArr.iQuantidade > 0 )
	{
		if( iIndex >= tPessoaEnderecoArr.iQuantidade )
			iIndex = tPessoaEnderecoArr.iQuantidade-1;
		if( iIndex < 0 )
			iIndex = 0;
	    strcpy(szAux, tPessoaEnderecoArr.pztPessoaEndereco[iIndex].szIdPais);
	}
	else
		strcpy( szAux, "" );
	return szAux;
}

char* CPessoaEndereco::getInEnderecoPreferencial(int iIndex)
{
    static char szAux[LEN_IDPAIS + LEN_EOS];

	if( tPessoaEnderecoArr.iQuantidade > 0 )
	{
		if( iIndex >= tPessoaEnderecoArr.iQuantidade )
			iIndex = tPessoaEnderecoArr.iQuantidade-1;
		if( iIndex < 0 )
			iIndex = 0;
	    strcpy(szAux, tPessoaEnderecoArr.pztPessoaEndereco[iIndex].szInEnderecoPreferencial);
	}
	else
		strcpy( szAux, "" );
	return szAux;
}
