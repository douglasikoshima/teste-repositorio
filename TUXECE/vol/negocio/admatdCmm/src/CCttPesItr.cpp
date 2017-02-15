#include <stdio.h>
#include <CCttPesItr.h>

CPesquisaSatisfacaoUFItr::CPesquisaSatisfacaoUFItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrPesquisaSatisfacaoUF    = NULL;
}

CPesquisaSatisfacaoUFItr::~CPesquisaSatisfacaoUFItr()
{
	ZeraPesquisaSatisfacaoUF();
}

int CPesquisaSatisfacaoUFItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CPesquisaSatisfacaoUFItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CPesquisaSatisfacaoUFItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CPesquisaSatisfacaoUFItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CPesquisaSatisfacaoUFItr::Quantidade( void )
{
	return _iQuantidade;
}

STPesquisaSatisfacaoUFRegistro* CPesquisaSatisfacaoUFItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrPesquisaSatisfacaoUF[ _iPosicao ];
	else
		return 0;
}

STPesquisaSatisfacaoUFRegistro* CPesquisaSatisfacaoUFItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrPesquisaSatisfacaoUF[ nPosicao ];

	}
	else
		return 0;
}

void CPesquisaSatisfacaoUFItr::Add( char* cidPesquisaSatisfacaoUF
								,char* cidContato
								,char* cidUFOperadora
								,char* cdsUFOperadora
								,char* cidTipoPessoa
								,char* csgTipoPessoa
								,char* cdsTipoPessoa
								,char* cidPesquisaSatisfacao
								,char* cdsPesquisaSatisfacao )
{
	_iQuantidade++;
	stcrPesquisaSatisfacaoUF = (struct STPesquisaSatisfacaoUFRegistro*) realloc( stcrPesquisaSatisfacaoUF, sizeof(STPesquisaSatisfacaoUFRegistro)*_iQuantidade );
	strcpy( stcrPesquisaSatisfacaoUF[_iQuantidade-1].cidPesquisaSatisfacaoUF, cidPesquisaSatisfacaoUF);
	strcpy( stcrPesquisaSatisfacaoUF[_iQuantidade-1].cidContato, cidContato);
	strcpy( stcrPesquisaSatisfacaoUF[_iQuantidade-1].cidUFOperadora, cidUFOperadora);
	strcpy( stcrPesquisaSatisfacaoUF[_iQuantidade-1].cdsUFOperadora, cdsUFOperadora);
	strcpy( stcrPesquisaSatisfacaoUF[_iQuantidade-1].cidTipoPessoa, cidTipoPessoa);
	strcpy( stcrPesquisaSatisfacaoUF[_iQuantidade-1].csgTipoPessoa, csgTipoPessoa);
	strcpy( stcrPesquisaSatisfacaoUF[_iQuantidade-1].cdsTipoPessoa, cdsTipoPessoa);
	strcpy( stcrPesquisaSatisfacaoUF[_iQuantidade-1].cidPesquisaSatisfacao, cidPesquisaSatisfacao);
	strcpy( stcrPesquisaSatisfacaoUF[_iQuantidade-1].cdsPesquisaSatisfacao, cdsPesquisaSatisfacao);
}

void CPesquisaSatisfacaoUFItr::AddUFOperadora( char* cidContato
					                          ,char* cidUFOperadora
					                          ,char* cdsUFOperadora )
{
	_iQuantidade++;
	stcrPesquisaSatisfacaoUF = (struct STPesquisaSatisfacaoUFRegistro*) realloc( stcrPesquisaSatisfacaoUF, sizeof(STPesquisaSatisfacaoUFRegistro)*_iQuantidade );
	strcpy( stcrPesquisaSatisfacaoUF[_iQuantidade-1].cidContato, cidContato);
	strcpy( stcrPesquisaSatisfacaoUF[_iQuantidade-1].cidUFOperadora, cidUFOperadora);
	strcpy( stcrPesquisaSatisfacaoUF[_iQuantidade-1].cdsUFOperadora, cdsUFOperadora);
	strcpy( stcrPesquisaSatisfacaoUF[_iQuantidade-1].cidTipoPessoa, "");
	strcpy( stcrPesquisaSatisfacaoUF[_iQuantidade-1].csgTipoPessoa, "");
	strcpy( stcrPesquisaSatisfacaoUF[_iQuantidade-1].cdsTipoPessoa, "");
	strcpy( stcrPesquisaSatisfacaoUF[_iQuantidade-1].cidPesquisaSatisfacaoUF, "");
	strcpy( stcrPesquisaSatisfacaoUF[_iQuantidade-1].cidPesquisaSatisfacao, "");
	strcpy( stcrPesquisaSatisfacaoUF[_iQuantidade-1].cdsPesquisaSatisfacao, "");
	
}

void CPesquisaSatisfacaoUFItr::AddPesquisaSatisfacao( char* cidContato
				                                     ,char* cidPesquisaSatisfacao
				                                     ,char* cdsPesquisaSatisfacao )
{
	_iQuantidade++;
	stcrPesquisaSatisfacaoUF = (struct STPesquisaSatisfacaoUFRegistro*) realloc( stcrPesquisaSatisfacaoUF, sizeof(STPesquisaSatisfacaoUFRegistro)*_iQuantidade );
	strcpy( stcrPesquisaSatisfacaoUF[_iQuantidade-1].cidContato, cidContato);
	strcpy( stcrPesquisaSatisfacaoUF[_iQuantidade-1].cidPesquisaSatisfacao, cidPesquisaSatisfacao);
	strcpy( stcrPesquisaSatisfacaoUF[_iQuantidade-1].cdsPesquisaSatisfacao, cdsPesquisaSatisfacao);
	strcpy( stcrPesquisaSatisfacaoUF[_iQuantidade-1].cidTipoPessoa, "");
	strcpy( stcrPesquisaSatisfacaoUF[_iQuantidade-1].csgTipoPessoa, "");
	strcpy( stcrPesquisaSatisfacaoUF[_iQuantidade-1].cdsTipoPessoa, "");
	strcpy( stcrPesquisaSatisfacaoUF[_iQuantidade-1].cidPesquisaSatisfacaoUF, "");
	strcpy( stcrPesquisaSatisfacaoUF[_iQuantidade-1].cidUFOperadora, "");
	strcpy( stcrPesquisaSatisfacaoUF[_iQuantidade-1].cdsUFOperadora, "");
}

void CPesquisaSatisfacaoUFItr::AddTipoPessoa( char* cidContato
               								 ,char* cidTipoPessoa
               								 ,char* csgTipoPessoa
								             ,char* cdsTipoPessoa  )
{
	_iQuantidade++;
	stcrPesquisaSatisfacaoUF = (struct STPesquisaSatisfacaoUFRegistro*) realloc( stcrPesquisaSatisfacaoUF, sizeof(STPesquisaSatisfacaoUFRegistro)*_iQuantidade );
	strcpy( stcrPesquisaSatisfacaoUF[_iQuantidade-1].cidContato, cidContato);
	strcpy( stcrPesquisaSatisfacaoUF[_iQuantidade-1].cidPesquisaSatisfacao, "");
	strcpy( stcrPesquisaSatisfacaoUF[_iQuantidade-1].cdsPesquisaSatisfacao, "");
	strcpy( stcrPesquisaSatisfacaoUF[_iQuantidade-1].cidTipoPessoa, cidTipoPessoa);
	strcpy( stcrPesquisaSatisfacaoUF[_iQuantidade-1].csgTipoPessoa, csgTipoPessoa);
	strcpy( stcrPesquisaSatisfacaoUF[_iQuantidade-1].cdsTipoPessoa, cdsTipoPessoa);
	strcpy( stcrPesquisaSatisfacaoUF[_iQuantidade-1].cidPesquisaSatisfacaoUF, "");
	strcpy( stcrPesquisaSatisfacaoUF[_iQuantidade-1].cidUFOperadora, "");
	strcpy( stcrPesquisaSatisfacaoUF[_iQuantidade-1].cdsUFOperadora, "");
}

void CPesquisaSatisfacaoUFItr::ZeraPesquisaSatisfacaoUF( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrPesquisaSatisfacaoUF );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrPesquisaSatisfacaoUF    = NULL;
}
