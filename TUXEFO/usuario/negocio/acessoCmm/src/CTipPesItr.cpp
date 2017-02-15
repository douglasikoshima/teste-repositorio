#include <stdio.h>
#include "CTipPesItr.h"

CTipPesItr::CTipPesItr()
{
    //ULOG_START("CTipPesItr::CTipPesItr()");
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrTipoPessoa    = NULL;
	//ULOG_END("CTipPesItr::CTipPesItr()");
}

CTipPesItr::~CTipPesItr()
{
	//ULOG_START("CTipPesItr::~CTipPesItr()");
	ZeraTipoPessoa();
	//ULOG_END("CTipPesItr::~CTipPesItr()");
}

int CTipPesItr::Primeiro( void )
{
	//ULOG_START("CTipPesItr::Primeiro()");
	_iPosicao = 0;
	//ULOG_END("CTipPesItr::Primeiro()");
	return _iPosicao;
}

int CTipPesItr::Proximo( void )
{
	//ULOG_START("CTipPesItr::Proximo()");
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	//ULOG_END("CTipPesItr::Proximo()");
	return _iPosicao;
}

int CTipPesItr::Anterior( void )
{
	//ULOG_START("CTipPesItr::Anterior()");
	if( _iPosicao > 0 )
		_iPosicao--;
    //ULOG_END("CTipPesItr::Anterior()");
	return _iPosicao;
}

int CTipPesItr::Ultimo( void )
{
    //ULOG_START("CTipPesItr::Ultimo()");	
	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;
    //ULOG_END("CTipPesItr::Ultimo()");	
	return _iPosicao;
}

int CTipPesItr::Quantidade( void )
{
    //ULOG_START("CTipPesItr::Quantidade()");	
    //ULOG_END("CTipPesItr::Quantidade()");	
	return _iQuantidade;
}

STTipoPessoaRegistro* CTipPesItr::Registro( void )
{
	//ULOG_START("CTipPesItr::Registro()");	
	if( _iQuantidade > 0 )
	{
		//ULOG_END("CTipPesItr::Registro()");	
		return &stcrTipoPessoa[ _iPosicao ];
	}
	else
	    //ULOG_END("CTipPesItr::Registro()");	
		return 0;
}

STTipoPessoaRegistro* CTipPesItr::Registro(int nPosicao)
{
	//ULOG_START("CTipPesItr::Registro()");	
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();
        //ULOG_END("CTipPesItr::Registro()");	
		return &stcrTipoPessoa[ nPosicao ];
		
	}
	else
	{
		//ULOG_END("CTipPesItr::Registro()");	
		return 0;
	}
}

void CTipPesItr::Add( char* cid, char* csigla, char* cdescricao )
{
	//ULOG_START("CTipPesItr::Add()");	
	_iQuantidade++;
	stcrTipoPessoa = (struct STTipoPessoaRegistro*) realloc( stcrTipoPessoa, sizeof(STTipoPessoaRegistro)*_iQuantidade );
	strcpy( stcrTipoPessoa[_iQuantidade-1].cidTipoPessoa, cid );
	strcpy( stcrTipoPessoa[_iQuantidade-1].csgTipoPessoa, csigla );
	strcpy( stcrTipoPessoa[_iQuantidade-1].cdsTipoPessoa, cdescricao );
	//ULOG_END("CTipPesItr::Add()");	
}


void CTipPesItr::ZeraTipoPessoa( void )
{
	//ULOG_START("CTipPesItr::ZeraTipoPessoa()");	
	if( _iQuantidade > 0 )
	{
		free( stcrTipoPessoa );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrTipoPessoa    = NULL;
	//ULOG_END("CTipPesItr::ZeraTipoPessoa()");	
}