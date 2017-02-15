#include <stdio.h>
#include "../include/CTabConsRelacItr.h"

CTabConsRelacItr::CTabConsRelacItr() : tamBlocoItr(500)
{
    _iQuantidade = 0;
    _iPosicao    = 0;
    stcrTabConsRelac    = NULL;
}

CTabConsRelacItr::~CTabConsRelacItr()
{
    ZeraTabConsRelac();
}

int CTabConsRelacItr::Primeiro( void )
{
    _iPosicao = 0;
    return _iPosicao;
}

int CTabConsRelacItr::Proximo( void )
{
    if( _iQuantidade > 0 )
    {
        if( _iPosicao < (_iQuantidade-1) )
            _iPosicao++;
    }
    return _iPosicao;
}

int CTabConsRelacItr::Anterior( void )
{
    if( _iPosicao > 0 )
        _iPosicao--;

    return _iPosicao;
}

int CTabConsRelacItr::Ultimo( void )
{

    if( _iQuantidade > 0 )
        _iPosicao = _iQuantidade-1;
    else
        _iPosicao = 0;

    return _iPosicao;
}

int CTabConsRelacItr::Quantidade( void )
{
    return _iQuantidade;
}

STTabConsRelacRegistro* CTabConsRelacItr::Registro( void )
{
    if( _iQuantidade > 0 )
        return &stcrTabConsRelac[ _iPosicao ];

    return 0;
}

STTabConsRelacRegistro* CTabConsRelacItr::Registro(int nPosicao)
{
    if( _iQuantidade > 0 )
    {
        if( nPosicao >= _iQuantidade )
            nPosicao = Ultimo();

        return &stcrTabConsRelac[ nPosicao ];

    }

    return 0;
}

/*
void CTabConsRelacItr::Add(const char* cidPessoaConta)
{
    // Só realoca quando preencher o bloco anterior, ou quando for a primeira vez--Cassio
    if ( (_iQuantidade % tamBlocoItr) == 0 )
    {
        int Resto = _iQuantidade / tamBlocoItr;
        int tamBlocoAtu = (Resto+1) * tamBlocoItr;

        stcrTabConsRelac = (struct STTabConsRelacRegistro*) 
            realloc(stcrTabConsRelac,sizeof(STTabConsRelacRegistro)*tamBlocoAtu);
    }

    strcpy( stcrTabConsRelac[_iQuantidade].idPessoaConta, cidPessoaConta);
    stcrTabConsRelac[_iQuantidade].tipoPersistencia = TP_UPDATE;

    _iQuantidade++;
}
*/


void CTabConsRelacItr::Add(const char* nrDocumentoPrm, const char* idTipoRelacionamentoPrm )
{
    // Só realoca quando preencher o bloco anterior, ou quando for a primeira vez--Cassio
    if ( (_iQuantidade % tamBlocoItr) == 0 )
    {
        int Resto = _iQuantidade / tamBlocoItr;
        int tamBlocoAtu = (Resto+1) * tamBlocoItr;

        stcrTabConsRelac = (struct STTabConsRelacRegistro*) 
            realloc(stcrTabConsRelac,sizeof(STTabConsRelacRegistro)*tamBlocoAtu);
    }

    strcpy( stcrTabConsRelac[_iQuantidade].nrDocumento, nrDocumentoPrm );
    strcpy( stcrTabConsRelac[_iQuantidade].idTipoRelacionamento, idTipoRelacionamentoPrm );

    _iQuantidade++;
}

void CTabConsRelacItr::ZeraTabConsRelac( void )
{
    if( _iQuantidade > 0 )
    {
        free( stcrTabConsRelac );
    }
    _iQuantidade = 0;
    _iPosicao    = 0;
    stcrTabConsRelac    = NULL;
}





// Relacionamento entre empresa
CTabEmpresaRelacItr::CTabEmpresaRelacItr() : tamBlocoItr(500)
{
    _iQuantidade = 0;
    _iPosicao    = 0;
    stcrTabEmpresaRelac    = NULL;
}

CTabEmpresaRelacItr::~CTabEmpresaRelacItr()
{
    ZeraTabConsRelac();
}

int CTabEmpresaRelacItr::Primeiro( void )
{
    _iPosicao = 0;
    return _iPosicao;
}

int CTabEmpresaRelacItr::Proximo( void )
{
    if( _iQuantidade > 0 )
    {
        if( _iPosicao < (_iQuantidade-1) )
            _iPosicao++;
    }
    return _iPosicao;
}

int CTabEmpresaRelacItr::Anterior( void )
{
    if( _iPosicao > 0 )
        _iPosicao--;

    return _iPosicao;
}

int CTabEmpresaRelacItr::Ultimo( void )
{

    if( _iQuantidade > 0 )
        _iPosicao = _iQuantidade-1;
    else
        _iPosicao = 0;

    return _iPosicao;
}

int CTabEmpresaRelacItr::Quantidade( void )
{
    return _iQuantidade;
}

STTabEmpresaRelacRegistro* CTabEmpresaRelacItr::Registro( void )
{
    if( _iQuantidade > 0 )
        return &stcrTabEmpresaRelac[ _iPosicao ];

    return 0;
}

STTabEmpresaRelacRegistro* CTabEmpresaRelacItr::Registro(int nPosicao)
{
    if( _iQuantidade > 0 )
    {
        if( nPosicao >= _iQuantidade )
            nPosicao = Ultimo();

        return &stcrTabEmpresaRelac[ nPosicao ];

    }

    return 0;
}



void CTabEmpresaRelacItr::Add( const char * nrDocumentoPrm, const char * idFormularioPrm, const char * vlPesoPrm, const char * inAtivoPrm, const char * intipoassociacao )
{
    // Só realoca quando preencher o bloco anterior, ou quando for a primeira vez--Cassio
    if ( (_iQuantidade % tamBlocoItr) == 0 )
    {
        int Resto = _iQuantidade / tamBlocoItr;
        int tamBlocoAtu = (Resto+1) * tamBlocoItr;

        stcrTabEmpresaRelac = (struct STTabEmpresaRelacRegistro*) 
            realloc(stcrTabEmpresaRelac,sizeof(STTabEmpresaRelacRegistro)*tamBlocoAtu);
    }

    strcpy( stcrTabEmpresaRelac[_iQuantidade].nrDocumento, nrDocumentoPrm );
    strcpy( stcrTabEmpresaRelac[_iQuantidade].idFormulario, idFormularioPrm );
    strcpy( stcrTabEmpresaRelac[_iQuantidade].vlPeso, vlPesoPrm );
    strcpy( stcrTabEmpresaRelac[_iQuantidade].inAtivo, inAtivoPrm );
	strcpy( stcrTabEmpresaRelac[_iQuantidade].intipoassociacao, intipoassociacao );
	

    _iQuantidade++;
}

void CTabEmpresaRelacItr::Add( const char * nrDocumentoPrm, const char * idFormularioPrm, const char * vlPesoPrm, const char * inAtivoPrm )
{
    // Só realoca quando preencher o bloco anterior, ou quando for a primeira vez--Cassio
    if ( (_iQuantidade % tamBlocoItr) == 0 )
    {
        int Resto = _iQuantidade / tamBlocoItr;
        int tamBlocoAtu = (Resto+1) * tamBlocoItr;

        stcrTabEmpresaRelac = (struct STTabEmpresaRelacRegistro*) 
            realloc(stcrTabEmpresaRelac,sizeof(STTabEmpresaRelacRegistro)*tamBlocoAtu);
    }

    strcpy( stcrTabEmpresaRelac[_iQuantidade].nrDocumento, nrDocumentoPrm );
    strcpy( stcrTabEmpresaRelac[_iQuantidade].idFormulario, idFormularioPrm );
    strcpy( stcrTabEmpresaRelac[_iQuantidade].vlPeso, vlPesoPrm );
    strcpy( stcrTabEmpresaRelac[_iQuantidade].inAtivo, inAtivoPrm );

    _iQuantidade++;}


void CTabEmpresaRelacItr::ZeraTabConsRelac( void )
{
    if( _iQuantidade > 0 )
    {
        free( stcrTabEmpresaRelac );
    }
    _iQuantidade = 0;
    _iPosicao    = 0;
    stcrTabEmpresaRelac    = NULL;
}
