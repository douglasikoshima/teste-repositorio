/**
 * 
 * @modulo  AdmSistemas
 * @usecase listas de sites de atendentes (OS MeuCliente)
 * @author  Cassio Garcia
 * @version $Revision: 1.1.4.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/10/26 23:41:30 $
 **/

#include <stdio.h>
#include "../include/CSiteConsultorAtdItr.h"

CSiteConsultorAtdItr::CSiteConsultorAtdItr()
{
    _iQuantidade = 0;
    _iPosicao = 0;
    _tamBloco = 100;
    stcrSiteConsultorAtd = 0;
}

CSiteConsultorAtdItr::~CSiteConsultorAtdItr()
{
    ZeraSiteConsultorAtd();
}

int CSiteConsultorAtdItr::Primeiro( void )
{
    _iPosicao = 0;
    return _iPosicao;
}

int CSiteConsultorAtdItr::Proximo( void )
{
    if( _iQuantidade > 0 )
    {
        if( _iPosicao < (_iQuantidade-1) )
            _iPosicao++;
    }

    return _iPosicao;
}

int CSiteConsultorAtdItr::Anterior( void )
{
    if( _iPosicao > 0 ) { _iPosicao--; }

    return _iPosicao;
}

int CSiteConsultorAtdItr::Ultimo( void )
{
    _iPosicao = _iQuantidade > 0 ? _iQuantidade-1 : 0;
    return _iPosicao;
}

int CSiteConsultorAtdItr::Quantidade( void )
{
    return _iQuantidade;
}

STSiteConsultorAtdRegistro* CSiteConsultorAtdItr::Registro( void )
{
    return _iQuantidade > 0 ? &stcrSiteConsultorAtd[ _iPosicao ] : 0;
}

STSiteConsultorAtdRegistro* CSiteConsultorAtdItr::Registro(int nPosicao)
{
    if( _iQuantidade > 0 )
    {
        if( nPosicao >= _iQuantidade )
            nPosicao = Ultimo();

        return &stcrSiteConsultorAtd[ nPosicao ];
    }

    return 0;
}

void CSiteConsultorAtdItr::Add( const char* cidSiteConsultorAtd
                              , const char* cdsSiteConsultorAtd
                              )
{
    // Só realoca quando preencher o bloco anterior, ou quando for a primeira vez--Junho,2006 - Cassio
    if ( (_iQuantidade % _tamBloco) == 0 )
    {
        int Resto = _iQuantidade / _tamBloco;
        int tamBlocoAtu = (Resto+1) * _tamBloco;

        stcrSiteConsultorAtd = 
                (struct STSiteConsultorAtdRegistro*) 
                    realloc( stcrSiteConsultorAtd, sizeof(STSiteConsultorAtdRegistro)*tamBlocoAtu );
    }

    SAFE_STRNCPY(stcrSiteConsultorAtd[_iQuantidade].cidSiteConsultorAtd,cidSiteConsultorAtd);
    SAFE_STRNCPY(stcrSiteConsultorAtd[_iQuantidade].cdsSiteConsultorAtd,cdsSiteConsultorAtd);

    _iQuantidade++;
}

void CSiteConsultorAtdItr::ZeraSiteConsultorAtd( void )
{
    if( _iQuantidade > 0 )
    {
        free( stcrSiteConsultorAtd );
    }
    _iQuantidade = 0;
    _iPosicao    = 0;
    stcrSiteConsultorAtd = NULL;
}
