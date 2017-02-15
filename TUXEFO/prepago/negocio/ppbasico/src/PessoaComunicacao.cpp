///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Sincronismo
 * @usecase PessoaComunicacao
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @author  Eder Martins
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:23 $
 **/
///////////////////////////////////////////////////////////////////////////////

#include "PessoaComunicacao.h"
#include "PessoaComunicacaopc.h"
#include "PrePagoException.h"

CPessoaComunicacao::CPessoaComunicacao( void )
{
    memset( &tPessoaComunicacao, 0, sizeof( TPessoaComunicacao ) );
    memset( &tPessoaComunicacaoArr, 0, sizeof( TPessoaComunicacaoArr ) );
}

CPessoaComunicacao::~CPessoaComunicacao( void )
{
	clPessoaComunicacaopc.desaloca( &tPessoaComunicacaoArr );
}

// Métodos de SET.
// ----------------------------------------------------------------------------
void CPessoaComunicacao::setIdPessoaComunicacao( char* pszIdPessoaComunicacao )
{
    strcpy( tPessoaComunicacao.szIdPessoaComunicacao, pszIdPessoaComunicacao );
}

void CPessoaComunicacao::setIdSistemaOrigem( char* pszIdSistemaOrigem )
{
    strcpy( tPessoaComunicacao.szIdSistemaOrigem, pszIdSistemaOrigem );
}

void CPessoaComunicacao::setIdPessoa( char* pszIdPessoa )
{
    strcpy( tPessoaComunicacao.szIdPessoa, pszIdPessoa );
}

void CPessoaComunicacao::setIdPessoa( int iIdPessoa )
{
    sprintf( tPessoaComunicacao.szIdPessoa, "%i", iIdPessoa );
}

void CPessoaComunicacao::setIdTipoComunicacao( char* pszIdTipoComunicacao )
{
    strcpy( tPessoaComunicacao.szIdTipoComunicacao, pszIdTipoComunicacao );
}

void CPessoaComunicacao::setDsContato( char* pszDsContato )
{
    strcpy( tPessoaComunicacao.szDsContato, pszDsContato );
}

void CPessoaComunicacao::setTsSincronismo( char* pszTsSincronismo )
{
    strcpy( tPessoaComunicacao.szTsSincronismo, pszTsSincronismo );
}

void CPessoaComunicacao::setSqSincronismo( char* pszSqSincronismo )
{
    strcpy( tPessoaComunicacao.szSqSincronismo, pszSqSincronismo );
}

void CPessoaComunicacao::setIdComunicacaoSistemaOrigem( char* pszIdComunicacaoSistemaOrigem )
{
    strcpy( tPessoaComunicacao.szIdComunicacaoSistemaOrigem, pszIdComunicacaoSistemaOrigem );
}

void CPessoaComunicacao::setIdComunicacaoSistemaOrigem( char* pszIdComunicacaoSistemaOrigem, char* pszPrefix )
{
    sprintf( tPessoaComunicacao.szIdComunicacaoSistemaOrigem
           , "%s%s", pszPrefix, pszIdComunicacaoSistemaOrigem );
}

void CPessoaComunicacao::setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao)
{
    strcpy(tPessoaComunicacao.szIdUsuarioAlteracao, pszIdUsuarioAlteracao);
}

// Métodos de GET.
// ----------------------------------------------------------------------------
char* CPessoaComunicacao::getSqSincronismo( void )
{
    static char szSqSincronismoAUX[ LEN_SQSINCRONISMO + LEN_EOS ];

    strcpy( szSqSincronismoAUX, tPessoaComunicacao.szSqSincronismo );

    return szSqSincronismoAUX;
}

char* CPessoaComunicacao::getIdTipoComunicacao( void )
{
    static char szIdTipoComunicacaoAUX[ LEN_IDTIPOCOMUNICACAO + LEN_EOS ];

    strcpy( szIdTipoComunicacaoAUX, tPessoaComunicacao.szIdTipoComunicacao );

    return szIdTipoComunicacaoAUX;
}

char* CPessoaComunicacao::getIdComunicacaoSistemaOrigem( void )
{
    static char szIdComunicacaoSistemaOrigemAUX[ LEN_IDCOMUNICACAOSISTEMAORIGEM + LEN_EOS ];

    strcpy( szIdComunicacaoSistemaOrigemAUX, tPessoaComunicacao.szIdComunicacaoSistemaOrigem );

    return szIdComunicacaoSistemaOrigemAUX;    
}

// Métodos de negócio.
// ----------------------------------------------------------------------------
void CPessoaComunicacao::apagaPessoaComunicacao( void )
{
    clPessoaComunicacaopc.proCApagaPessoaComunicacao( tPessoaComunicacao );
}

bool CPessoaComunicacao::buscaPessoaComunicacao( void )
{
	return clPessoaComunicacaopc.proCBuscaPessoaComunicacao( tPessoaComunicacao );
}

bool CPessoaComunicacao::buscaPessoaComunicacao( TPessoaComunicacao &tPessoaComunicacao )
{
	return clPessoaComunicacaopc.proCBuscaPessoaComunicacao( tPessoaComunicacao );
}

void CPessoaComunicacao::inserePessoaComunicacao( void )
{
    clPessoaComunicacaopc.proCInserePessoaComunicacao( tPessoaComunicacao );
}

void CPessoaComunicacao::atualizaPessoaComunicacao( void )
{
    clPessoaComunicacaopc.proCAtualizaPessoaComunicacao( tPessoaComunicacao );
}

void CPessoaComunicacao::setStruct(TPessoaComunicacao *ptPessoaComunicacao)
{
    memcpy(&tPessoaComunicacao, ptPessoaComunicacao, sizeof(TPessoaComunicacao));
    memset(ptPessoaComunicacao, 0x00, sizeof(TPessoaComunicacao));
}

bool CPessoaComunicacao::buscaPessoaComunicacaoPorIdPessoa( void )
{
	return clPessoaComunicacaopc.proCBuscaPessoaComunicacaoPorIdPessoa( &tPessoaComunicacaoArr, tPessoaComunicacao );
}

bool CPessoaComunicacao::buscaTodosPessoaComunicacaoPorIdPessoa( void )
{
	return clPessoaComunicacaopc.proCBuscaTodosPessoaComPorIdPessoa( &tPessoaComunicacaoArr, tPessoaComunicacao );
}

///////////////////////////////////////////////////////////////////////////////
// Métodos de GET (recuperam dados de tPessoaComunicacaoArr)
///////////////////////////////////////////////////////////////////////////////
char* CPessoaComunicacao::getIdPessoaComunicacao( int iIndex )
{
    static char szSqSincronismoAUX[ LEN_IDPESSOACOMUNICACAO + LEN_EOS ];
	if( tPessoaComunicacaoArr.iQuantidade > 0 )
	{
		if( iIndex >= tPessoaComunicacaoArr.iQuantidade )
			iIndex = tPessoaComunicacaoArr.iQuantidade-1;
		if( iIndex < 0 )
			iIndex = 0;
	    strcpy(szSqSincronismoAUX, tPessoaComunicacaoArr.pztPessoaComunicacao[iIndex].szIdPessoaComunicacao);
	}
	else
		strcpy( szSqSincronismoAUX, "" );
    return szSqSincronismoAUX;
}

char* CPessoaComunicacao::getIdPessoa( int iIndex )
{
    static char szSqSincronismoAUX[ LEN_IDPESSOA + LEN_EOS ];
	if( tPessoaComunicacaoArr.iQuantidade > 0 )
	{
		if( iIndex >= tPessoaComunicacaoArr.iQuantidade )
			iIndex = tPessoaComunicacaoArr.iQuantidade-1;
		if( iIndex < 0 )
			iIndex = 0;
	    strcpy(szSqSincronismoAUX, tPessoaComunicacaoArr.pztPessoaComunicacao[iIndex].szIdPessoa);
	}
	else
		strcpy( szSqSincronismoAUX, "" );
    return szSqSincronismoAUX;
}

char* CPessoaComunicacao::getIdTipoComunicacao( int iIndex )
{
    static char szSqSincronismoAUX[ LEN_IDTIPOCOMUNICACAO + LEN_EOS ];
	if( tPessoaComunicacaoArr.iQuantidade > 0 )
	{
		if( iIndex >= tPessoaComunicacaoArr.iQuantidade )
			iIndex = tPessoaComunicacaoArr.iQuantidade-1;
		if( iIndex < 0 )
			iIndex = 0;
	    strcpy(szSqSincronismoAUX, tPessoaComunicacaoArr.pztPessoaComunicacao[iIndex].szIdTipoComunicacao);
	}
	else
		strcpy( szSqSincronismoAUX, "" );
    return szSqSincronismoAUX;
}

char* CPessoaComunicacao::getIdSistemaOrigem( int iIndex )
{
    static char szSqSincronismoAUX[ LEN_IDSISTEMAORIGEM + LEN_EOS ];
	if( tPessoaComunicacaoArr.iQuantidade > 0 )
	{
		if( iIndex >= tPessoaComunicacaoArr.iQuantidade )
			iIndex = tPessoaComunicacaoArr.iQuantidade-1;
		if( iIndex < 0 )
			iIndex = 0;
	    strcpy(szSqSincronismoAUX, tPessoaComunicacaoArr.pztPessoaComunicacao[iIndex].szIdSistemaOrigem);
	}
	else
		strcpy( szSqSincronismoAUX, "" );
    return szSqSincronismoAUX;
}

char* CPessoaComunicacao::getNrSequencia( int iIndex )
{
    static char szSqSincronismoAUX[ LEN_NRSEQUENCIA + LEN_EOS ];
	if( tPessoaComunicacaoArr.iQuantidade > 0 )
	{
		if( iIndex >= tPessoaComunicacaoArr.iQuantidade )
			iIndex = tPessoaComunicacaoArr.iQuantidade-1;
		if( iIndex < 0 )
			iIndex = 0;
	    strcpy(szSqSincronismoAUX, tPessoaComunicacaoArr.pztPessoaComunicacao[iIndex].szNrSequencia);
	}
	else
		strcpy( szSqSincronismoAUX, "" );
    return szSqSincronismoAUX;
}

char* CPessoaComunicacao::getDsContato( int iIndex )
{
    static char szSqSincronismoAUX[ LEN_DSCONTATO + LEN_EOS ];
	if( tPessoaComunicacaoArr.iQuantidade > 0 )
	{
		if( iIndex >= tPessoaComunicacaoArr.iQuantidade )
			iIndex = tPessoaComunicacaoArr.iQuantidade-1;
		if( iIndex < 0 )
			iIndex = 0;
	    strcpy(szSqSincronismoAUX, tPessoaComunicacaoArr.pztPessoaComunicacao[iIndex].szDsContato);
	}
	else
		strcpy( szSqSincronismoAUX, "" );
    return szSqSincronismoAUX;
}

char* CPessoaComunicacao::getDtCadastro( int iIndex )
{
    static char szSqSincronismoAUX[ LEN_DTCADASTRO + LEN_EOS ];
	if( tPessoaComunicacaoArr.iQuantidade > 0 )
	{
		if( iIndex >= tPessoaComunicacaoArr.iQuantidade )
			iIndex = tPessoaComunicacaoArr.iQuantidade-1;
		if( iIndex < 0 )
			iIndex = 0;
	    strcpy(szSqSincronismoAUX, tPessoaComunicacaoArr.pztPessoaComunicacao[iIndex].szDtCadastro);
	}
	else
		strcpy( szSqSincronismoAUX, "" );
    return szSqSincronismoAUX;
}

char* CPessoaComunicacao::getTsSincronismo( int iIndex )
{
    static char szSqSincronismoAUX[ LEN_TSSINCRONISMO + LEN_EOS ];
	if( tPessoaComunicacaoArr.iQuantidade > 0 )
	{
		if( iIndex >= tPessoaComunicacaoArr.iQuantidade )
			iIndex = tPessoaComunicacaoArr.iQuantidade-1;
		if( iIndex < 0 )
			iIndex = 0;
	    strcpy(szSqSincronismoAUX, tPessoaComunicacaoArr.pztPessoaComunicacao[iIndex].szTsSincronismo);
	}
	else
		strcpy( szSqSincronismoAUX, "" );
    return szSqSincronismoAUX;
}

char* CPessoaComunicacao::getSqSincronismo( int iIndex )
{
    static char szSqSincronismoAUX[ LEN_SQSINCRONISMO + LEN_EOS ];
	if( tPessoaComunicacaoArr.iQuantidade > 0 )
	{
		if( iIndex >= tPessoaComunicacaoArr.iQuantidade )
			iIndex = tPessoaComunicacaoArr.iQuantidade-1;
		if( iIndex < 0 )
			iIndex = 0;
	    strcpy(szSqSincronismoAUX, tPessoaComunicacaoArr.pztPessoaComunicacao[iIndex].szSqSincronismo);
	}
	else
		strcpy( szSqSincronismoAUX, "" );
    return szSqSincronismoAUX;
}

char* CPessoaComunicacao::getIdComunicacaoSistemaOrigem( int iIndex )
{
    static char szSqSincronismoAUX[ LEN_IDCOMUNICACAOSISTEMAORIGEM + LEN_EOS ];
	if( tPessoaComunicacaoArr.iQuantidade > 0 )
	{
		if( iIndex >= tPessoaComunicacaoArr.iQuantidade )
			iIndex = tPessoaComunicacaoArr.iQuantidade-1;
		if( iIndex < 0 )
			iIndex = 0;
	    strcpy(szSqSincronismoAUX, tPessoaComunicacaoArr.pztPessoaComunicacao[iIndex].szIdComunicacaoSistemaOrigem);
	}
	else
		strcpy( szSqSincronismoAUX, "" );
    return szSqSincronismoAUX;
}

char* CPessoaComunicacao::getDtExpiracao( int iIndex )
{
    static char szSqSincronismoAUX[ LEN_DTEXPIRACAO + LEN_EOS ];
	if( tPessoaComunicacaoArr.iQuantidade > 0 )
	{
		if( iIndex >= tPessoaComunicacaoArr.iQuantidade )
			iIndex = tPessoaComunicacaoArr.iQuantidade-1;
		if( iIndex < 0 )
			iIndex = 0;
	    strcpy(szSqSincronismoAUX, tPessoaComunicacaoArr.pztPessoaComunicacao[iIndex].szDtExpiracao);
	}
	else
		strcpy( szSqSincronismoAUX, "" );
    return szSqSincronismoAUX;
}

char* CPessoaComunicacao::getInComunicacaoPreferencial( int iIndex )
{
    static char szSqSincronismoAUX[ LEN_INCOMUNICACAOPREFERENCIAL + LEN_EOS ];
	if( tPessoaComunicacaoArr.iQuantidade > 0 )
	{
		if( iIndex >= tPessoaComunicacaoArr.iQuantidade )
			iIndex = tPessoaComunicacaoArr.iQuantidade-1;
		if( iIndex < 0 )
			iIndex = 0;
	    strcpy(szSqSincronismoAUX, tPessoaComunicacaoArr.pztPessoaComunicacao[iIndex].szInComunicacaoPreferencial);
	}
	else
		strcpy( szSqSincronismoAUX, "" );
    return szSqSincronismoAUX;
}

char* CPessoaComunicacao::getIdUsuarioAlteracao( int iIndex )
{
    static char szSqSincronismoAUX[ LEN_IDUSUARIOALTERACAO + LEN_EOS ];
	if( tPessoaComunicacaoArr.iQuantidade > 0 )
	{
		if( iIndex >= tPessoaComunicacaoArr.iQuantidade )
			iIndex = tPessoaComunicacaoArr.iQuantidade-1;
		if( iIndex < 0 )
			iIndex = 0;
	    strcpy(szSqSincronismoAUX, tPessoaComunicacaoArr.pztPessoaComunicacao[iIndex].szIdUsuarioAlteracao);
	}
	else
		strcpy( szSqSincronismoAUX, "" );
    return szSqSincronismoAUX;
}

char* CPessoaComunicacao::getDtUltimaAlteracao( int iIndex )
{
    static char szSqSincronismoAUX[ LEN_DTULTIMAALTERACAO + LEN_EOS ];
	if( tPessoaComunicacaoArr.iQuantidade > 0 )
	{
		if( iIndex >= tPessoaComunicacaoArr.iQuantidade )
			iIndex = tPessoaComunicacaoArr.iQuantidade-1;
		if( iIndex < 0 )
			iIndex = 0;
	    strcpy(szSqSincronismoAUX, tPessoaComunicacaoArr.pztPessoaComunicacao[iIndex].szDtUltimaAlteracao);
	}
	else
		strcpy( szSqSincronismoAUX, "" );
    return szSqSincronismoAUX;
}

char* CPessoaComunicacao::getNmContato( int iIndex )
{
    static char szSqSincronismoAUX[ LEN_NMCONTATO + LEN_EOS ];
	if( tPessoaComunicacaoArr.iQuantidade > 0 )
	{
		if( iIndex >= tPessoaComunicacaoArr.iQuantidade )
			iIndex = tPessoaComunicacaoArr.iQuantidade-1;
		if( iIndex < 0 )
			iIndex = 0;
	    strcpy(szSqSincronismoAUX, tPessoaComunicacaoArr.pztPessoaComunicacao[iIndex].szNmContato);
	}
	else
		strcpy( szSqSincronismoAUX, "" );
    return szSqSincronismoAUX;
}
