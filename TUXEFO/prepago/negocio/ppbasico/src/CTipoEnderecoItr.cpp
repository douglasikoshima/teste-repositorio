#include <stdio.h>
#include <CTipoEnderecoItr.h>

CTipoEnderecoItr::CTipoEnderecoItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrTipoEndereco    = NULL;
}

CTipoEnderecoItr::~CTipoEnderecoItr()
{
	ZeraTipoEndereco();
}

int CTipoEnderecoItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CTipoEnderecoItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CTipoEnderecoItr::Quantidade( void )
{
	return _iQuantidade;
}

STTipoEnderecoRegistro* CTipoEnderecoItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrTipoEndereco[ _iPosicao ];
	else
		return 0;
}

STTipoEnderecoRegistro* CTipoEnderecoItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrTipoEndereco[ nPosicao ];

	}
	else
		return 0;
}

void CTipoEnderecoItr::Add( 
			char* cidTipoEndereco,
			char* csgTipoEndereco,
			char* cdsTipoEndereco )
{
	_iQuantidade++;
	stcrTipoEndereco = (struct STTipoEnderecoRegistro*) realloc( stcrTipoEndereco, sizeof(STTipoEnderecoRegistro)*_iQuantidade );
	strcpy( stcrTipoEndereco[_iQuantidade-1].cidTipoEndereco, cidTipoEndereco);
	strcpy( stcrTipoEndereco[_iQuantidade-1].csgTipoEndereco, csgTipoEndereco);
	strcpy( stcrTipoEndereco[_iQuantidade-1].cdsTipoEndereco, cdsTipoEndereco);
}


void CTipoEnderecoItr::ZeraTipoEndereco( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrTipoEndereco );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrTipoEndereco    = NULL;
}
