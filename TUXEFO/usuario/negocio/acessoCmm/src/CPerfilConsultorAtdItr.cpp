/**
 * 
 * @modulo  AdmSistemas
 * @usecase listas de perfis de atendentes (OS MeuCliente)
 * @author  Cassio Garcia
 * @version $Revision: 1.1.4.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/10/26 23:41:29 $
 **/

#include <stdio.h>
#include "../include/CPerfilConsultorAtdItr.h"

CPerfilConsultorAtdItr::CPerfilConsultorAtdItr()
{
    _iQuantidade = 0;
    _iPosicao    = 0;
    _tamBloco = 20;
    stcrPerfilConsultorAtd = 0;
}

CPerfilConsultorAtdItr::~CPerfilConsultorAtdItr()
{
    ZeraPerfilConsultorAtd();
}

int CPerfilConsultorAtdItr::Primeiro( void )
{
    _iPosicao = 0;
    return _iPosicao;
}

int CPerfilConsultorAtdItr::Proximo( void )
{
    if( _iQuantidade > 0 )
    {
        if( _iPosicao < (_iQuantidade-1) )
            _iPosicao++;
    }

    return _iPosicao;
}

int CPerfilConsultorAtdItr::Anterior( void )
{
    if( _iPosicao > 0 ) { _iPosicao--; }

    return _iPosicao;
}

int CPerfilConsultorAtdItr::Ultimo( void )
{
    _iPosicao = _iQuantidade > 0 ? _iQuantidade-1 : 0;
    return _iPosicao;
}

int CPerfilConsultorAtdItr::Quantidade( void )
{
    return _iQuantidade;
}

STPerfilConsultorAtdRegistro* CPerfilConsultorAtdItr::Registro( void )
{
    return _iQuantidade > 0 ? &stcrPerfilConsultorAtd[ _iPosicao ] : 0;
}

STPerfilConsultorAtdRegistro* CPerfilConsultorAtdItr::Registro(int nPosicao)
{
    if( _iQuantidade > 0 )
    {
        if( nPosicao >= _iQuantidade )
            nPosicao = Ultimo();

        return &stcrPerfilConsultorAtd[ nPosicao ];
    }

    return 0;
}

void CPerfilConsultorAtdItr::Add( const char* cidPerfilConsultorAtd
                                , const char* cdsPerfilConsultorAtd
                                )
{
    // Só realoca quando preencher o bloco anterior, ou quando for a primeira vez--Junho,2006 - Cassio
    if ( (_iQuantidade % _tamBloco) == 0 )
    {
        int Resto = _iQuantidade / _tamBloco;
        int tamBlocoAtu = (Resto+1) * _tamBloco;

        stcrPerfilConsultorAtd = 
                (struct STPerfilConsultorAtdRegistro*) 
                    realloc( stcrPerfilConsultorAtd, sizeof(STPerfilConsultorAtdRegistro)*tamBlocoAtu );
    }

    SAFE_STRNCPY(stcrPerfilConsultorAtd[_iQuantidade].cidPerfilConsultorAtd,cidPerfilConsultorAtd);
    SAFE_STRNCPY(stcrPerfilConsultorAtd[_iQuantidade].cdsPerfilConsultorAtd,cdsPerfilConsultorAtd);

    _iQuantidade++;
}

void CPerfilConsultorAtdItr::ZeraPerfilConsultorAtd( void )
{
    if( _iQuantidade > 0 )
    {
        free( stcrPerfilConsultorAtd );
    }
    _iQuantidade = 0;
    _iPosicao    = 0;
    stcrPerfilConsultorAtd    = NULL;
}
