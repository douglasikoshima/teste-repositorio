/**
 * 
 * @modulo  AdmSistemas
 * @usecase listas de perfis de atendentes (OS MeuCliente)
 * @author  Cassio Garcia
 * @version $Revision: 1.1.4.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/10/26 23:41:29 $
 **/

#include <stdio.h>
#include "../include/CFornecedorConsultorAtdItr.h"

CFornecedorConsultorAtdItr::CFornecedorConsultorAtdItr()
{
    _iQuantidade = 0;
    _iPosicao = 0;
    _tamBloco = 100;
    stcrFornecedorConsultorAtd = 0;
}

CFornecedorConsultorAtdItr::~CFornecedorConsultorAtdItr()
{
    ZeraFornecedorConsultorAtd();
}

int CFornecedorConsultorAtdItr::Primeiro( void )
{
    _iPosicao = 0;
    return _iPosicao;
}

int CFornecedorConsultorAtdItr::Proximo( void )
{
    if( _iQuantidade > 0 )
    {
        if( _iPosicao < (_iQuantidade-1) )
            _iPosicao++;
    }

    return _iPosicao;
}

int CFornecedorConsultorAtdItr::Anterior( void )
{
    if( _iPosicao > 0 ) { _iPosicao--; }

    return _iPosicao;
}

int CFornecedorConsultorAtdItr::Ultimo( void )
{
    _iPosicao = _iQuantidade > 0 ? _iQuantidade-1 : 0;
    return _iPosicao;
}

int CFornecedorConsultorAtdItr::Quantidade( void )
{
    return _iQuantidade;
}

STFornecedorConsultorAtdRegistro* CFornecedorConsultorAtdItr::Registro( void )
{
    return _iQuantidade > 0 ? &stcrFornecedorConsultorAtd[ _iPosicao ] : 0;
}

STFornecedorConsultorAtdRegistro* CFornecedorConsultorAtdItr::Registro(int nPosicao)
{
    if( _iQuantidade > 0 )
    {
        if( nPosicao >= _iQuantidade )
            nPosicao = Ultimo();

        return &stcrFornecedorConsultorAtd[ nPosicao ];
    }

    return 0;
}

void CFornecedorConsultorAtdItr::Add( const char* cidFornecedorConsultorAtd
                                    , const char* cdsFornecedorConsultorAtd
                                    )
{
    // Só realoca quando preencher o bloco anterior, ou quando for a primeira vez--Junho,2006 - Cassio
    if ( (_iQuantidade % _tamBloco) == 0 )
    {
        int Resto = _iQuantidade / _tamBloco;
        int tamBlocoAtu = (Resto+1) * _tamBloco;

        stcrFornecedorConsultorAtd = 
                (struct STFornecedorConsultorAtdRegistro*) 
                    realloc( stcrFornecedorConsultorAtd, sizeof(STFornecedorConsultorAtdRegistro)*tamBlocoAtu );
    }

    SAFE_STRNCPY(stcrFornecedorConsultorAtd[_iQuantidade].cidFornecedorConsultorAtd,cidFornecedorConsultorAtd);
    SAFE_STRNCPY(stcrFornecedorConsultorAtd[_iQuantidade].cdsFornecedorConsultorAtd,cdsFornecedorConsultorAtd);

    _iQuantidade++;
}

void CFornecedorConsultorAtdItr::ZeraFornecedorConsultorAtd( void )
{
    if( _iQuantidade > 0 )
    {
        free( stcrFornecedorConsultorAtd );
    }
    _iQuantidade = 0;
    _iPosicao    = 0;
    stcrFornecedorConsultorAtd = NULL;
}
