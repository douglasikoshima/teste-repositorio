#include <stdio.h>
#include "CPesDocItr.h"

CPesDocItr::CPesDocItr()
{
	//ULOG_START("CPesDocItr::CPesDocItr()");
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrPessoaDocumento = NULL;
	//ULOG_END("CPesDocItr::CPesDocItr()");
}

CPesDocItr::~CPesDocItr()
{
	//ULOG_START("CPesDocItr::~CPesDocItr()");
	ZeraPessoaDocumento();
	//ULOG_END("CPesDocItr::~CPesDocItr()");
}

int CPesDocItr::Primeiro( void )
{
	//ULOG_START("CPesDocItr::Primeiro()");
	_iPosicao = 0;
	//ULOG_END("CPesDocItr::Primeiro()");
	return _iPosicao;
}

int CPesDocItr::Proximo( void )
{
	//ULOG_START("CPesDocItr::Proximo()");
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	//ULOG_END("CPesDocItr::Proximo()");
	return _iPosicao;
}

int CPesDocItr::Anterior( void )
{
	//ULOG_START("CPesDocItr::Anterior()");
	if( _iPosicao > 0 )
		_iPosicao--;

	//ULOG_END("CPesDocItr::Anterior()");
	return _iPosicao;
}

int CPesDocItr::Ultimo( void )
{
	//ULOG_START("CPesDocItr::Ultimo()");	
	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;
    //ULOG_END("CPesDocItr::Ultimo()");	
	return _iPosicao;
}

int CPesDocItr::Quantidade( void )
{
	//ULOG_START("CPesDocItr::Quantidade()");	
	//ULOG_END("CPesDocItr::Quantidade()");	
	return _iQuantidade;
}

STPessoaDocumentoRegistro* CPesDocItr::Registro( void )
{
	//ULOG_START("CPesDocItr::Registro()");
	if( _iQuantidade > 0 )
	{
	    //ULOG_END("CPesDocItr::Registro()");
		return &stcrPessoaDocumento[ _iPosicao ];
	}
	else
	{
		//ULOG_END("CPesDocItr::Registro()");
		return 0;
	}
}

STPessoaDocumentoRegistro* CPesDocItr::Registro(int nPosicao)
{
	//ULOG_START("CPesDocItr::Registro()");
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();
        //ULOG_END("CPesDocItr::Registro()");
		return &stcrPessoaDocumento[ nPosicao ];
		
	}
	else
	{
		//ULOG_END("CPesDocItr::Registro()");
		return 0;
	}
}

void CPesDocItr::Add( char* cidPessoaDocumento,
	                  char* cidPessoa,
	                  char* cidDocumento,
	                  char* ctsSincronismo,
	                  char* csqSincronismo,
	                  char* cidSistemaOrigem,
	                  char* cidDocumentoSistemaOrigem,
	                  char* cdtexpiracao)
{
	//ULOG_START("CPesDocItr::Add()");
	_iQuantidade++;
	stcrPessoaDocumento = (struct STPessoaDocumentoRegistro*) realloc( stcrPessoaDocumento, sizeof(STPessoaDocumentoRegistro)*_iQuantidade );
	strcpy( stcrPessoaDocumento[_iQuantidade-1].cidPessoaDocumento, cidPessoaDocumento );
	strcpy( stcrPessoaDocumento[_iQuantidade-1].cidPessoa, cidPessoa );
	strcpy( stcrPessoaDocumento[_iQuantidade-1].cidDocumento, cidDocumento );
	strcpy( stcrPessoaDocumento[_iQuantidade-1].ctsSincronismo, ctsSincronismo );
	strcpy( stcrPessoaDocumento[_iQuantidade-1].csqSincronismo, csqSincronismo );
	strcpy( stcrPessoaDocumento[_iQuantidade-1].cidSistemaOrigem, cidSistemaOrigem );
	strcpy( stcrPessoaDocumento[_iQuantidade-1].cidDocumentoSistemaOrigem, cidDocumentoSistemaOrigem );
	strcpy( stcrPessoaDocumento[_iQuantidade-1].cdtexpiracao, cdtexpiracao );
	//ULOG_END("CPesDocItr::Add()");
}

void CPesDocItr::Add( char* cidPessoaDocumento,
	                  char* cidPessoa,
	                  char* cidDocumento,
	                  char* ctsSincronismo,
	                  char* csqSincronismo,
	                  char* cidSistemaOrigem,
	                  char* cidDocumentoSistemaOrigem,
					  char* cdtexpiracao,
				      char* cnmPessoa,
				      char* cnmNome,
				      char* cnmNomemeio,
				      char* cnmSobrenome)
{
	//ULOG_START("CPesDocItr::Add()");
	_iQuantidade++;
	stcrPessoaDocumento = (struct STPessoaDocumentoRegistro*) realloc( stcrPessoaDocumento, sizeof(STPessoaDocumentoRegistro)*_iQuantidade );
	strcpy( stcrPessoaDocumento[_iQuantidade-1].cidPessoaDocumento, cidPessoaDocumento );
	strcpy( stcrPessoaDocumento[_iQuantidade-1].cidPessoa, cidPessoa );
	strcpy( stcrPessoaDocumento[_iQuantidade-1].cidDocumento, cidDocumento );
	strcpy( stcrPessoaDocumento[_iQuantidade-1].ctsSincronismo, ctsSincronismo );
	strcpy( stcrPessoaDocumento[_iQuantidade-1].csqSincronismo, csqSincronismo );
	strcpy( stcrPessoaDocumento[_iQuantidade-1].cidSistemaOrigem, cidSistemaOrigem );
	strcpy( stcrPessoaDocumento[_iQuantidade-1].cidDocumentoSistemaOrigem, cidDocumentoSistemaOrigem );
	strcpy( stcrPessoaDocumento[_iQuantidade-1].cdtexpiracao, cdtexpiracao );
	strcpy( stcrPessoaDocumento[_iQuantidade-1].cnmPessoa, cnmPessoa );
	strcpy( stcrPessoaDocumento[_iQuantidade-1].cnmNome, cnmNome );
	strcpy( stcrPessoaDocumento[_iQuantidade-1].cnmNomemeio, cnmNomemeio );
	strcpy( stcrPessoaDocumento[_iQuantidade-1].cnmSobrenome, cnmSobrenome );
	//ULOG_END("CPesDocItr::Add()");
}

void CPesDocItr::ZeraPessoaDocumento( void )
{
	//ULOG_START("CPesDocItr::ZeraPessoaDocumento()");
	if( _iQuantidade > 0 )
	{
		free( stcrPessoaDocumento );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrPessoaDocumento    = NULL;
	//ULOG_END("CPesDocItr::ZeraPessoaDocumento()");
}