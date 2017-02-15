#include <stdio.h>
#include <CPesquisaPessoaItr.h>

CPesquisaPessoaItr::CPesquisaPessoaItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrPesquisaPessoa    = NULL;
}

CPesquisaPessoaItr::~CPesquisaPessoaItr()
{
	ZeraPesquisaPessoa();
}

int CPesquisaPessoaItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CPesquisaPessoaItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CPesquisaPessoaItr::Quantidade( void )
{
	return _iQuantidade;
}

STPesquisaPessoaRegistro* CPesquisaPessoaItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrPesquisaPessoa[ _iPosicao ];
	else
		return 0;
}

STPesquisaPessoaRegistro* CPesquisaPessoaItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrPesquisaPessoa[ nPosicao ];

	}
	else
		return 0;
}

void CPesquisaPessoaItr::Add( 
						char* cidPessoa
					   ,char* cnmPessoa
					   ,char* cidTipoPessoa
					   ,char* csgTipoPessoa
					   ,char* cdsTipoPessoa
					   ,char* cidDocumento
					   ,char* cnrDocumento
					   ,char* cidTipoDocumento
					   ,char* csgTipoDocumento
					   ,char* cdsTipoDocumento
					  )
{
    // Só realoca quando preencher o bloco anterior, ou quando for a primeira vez
    // Maio, 2007 - Cassio
    int tamBlocoGrupo = 50;
    if ( (_iQuantidade % tamBlocoGrupo) == 0 )
    {
        int Resto = _iQuantidade / tamBlocoGrupo;
        int tamBlocoAtu = (Resto+1) * tamBlocoGrupo;

	    stcrPesquisaPessoa = (struct STPesquisaPessoaRegistro*) 
                realloc( stcrPesquisaPessoa, sizeof(STPesquisaPessoaRegistro)*tamBlocoAtu );
    }
	_iQuantidade++;
	//stcrPesquisaPessoa = (struct STPesquisaPessoaRegistro*) realloc( stcrPesquisaPessoa, sizeof(STPesquisaPessoaRegistro)*_iQuantidade );
	strcpy( stcrPesquisaPessoa[_iQuantidade-1].cidPessoa, cidPessoa);
	strcpy( stcrPesquisaPessoa[_iQuantidade-1].cnmPessoa, cnmPessoa);
	strcpy( stcrPesquisaPessoa[_iQuantidade-1].cidTipoPessoa, cidTipoPessoa);
	strcpy( stcrPesquisaPessoa[_iQuantidade-1].csgTipoPessoa, csgTipoPessoa);
	strcpy( stcrPesquisaPessoa[_iQuantidade-1].cdsTipoPessoa, cdsTipoPessoa);
	strcpy( stcrPesquisaPessoa[_iQuantidade-1].cidDocumento, cidDocumento);
	strcpy( stcrPesquisaPessoa[_iQuantidade-1].cnrDocumento, cnrDocumento);
	strcpy( stcrPesquisaPessoa[_iQuantidade-1].cidTipoDocumento, cidTipoDocumento);
	strcpy( stcrPesquisaPessoa[_iQuantidade-1].csgTipoDocumento, csgTipoDocumento);
	strcpy( stcrPesquisaPessoa[_iQuantidade-1].cdsTipoDocumento, cdsTipoDocumento);
}

void CPesquisaPessoaItr::Add( 
						char* cidPessoa
					   ,char* cnmPessoa
					   ,char* cdsTipoPessoa
					   ,char* cnrDocumento
					   ,char* cdsTipoDocumento
					  )
{
    // Só realoca quando preencher o bloco anterior, ou quando for a primeira vez
    // Maio, 2007 - Cassio
    int tamBlocoGrupo = 50;
    if ( (_iQuantidade % tamBlocoGrupo) == 0 )
    {
        int Resto = _iQuantidade / tamBlocoGrupo;
        int tamBlocoAtu = (Resto+1) * tamBlocoGrupo;

	    stcrPesquisaPessoa = (struct STPesquisaPessoaRegistro*) 
                realloc( stcrPesquisaPessoa, sizeof(STPesquisaPessoaRegistro)*tamBlocoAtu );
    }
	_iQuantidade++;
	//stcrPesquisaPessoa = (struct STPesquisaPessoaRegistro*) realloc( stcrPesquisaPessoa, sizeof(STPesquisaPessoaRegistro)*_iQuantidade );
	strcpy( stcrPesquisaPessoa[_iQuantidade-1].cidPessoa, cidPessoa);
	strcpy( stcrPesquisaPessoa[_iQuantidade-1].cnmPessoa, cnmPessoa);
	strcpy( stcrPesquisaPessoa[_iQuantidade-1].cdsTipoPessoa, cdsTipoPessoa);
	strcpy( stcrPesquisaPessoa[_iQuantidade-1].cnrDocumento, cnrDocumento);
	strcpy( stcrPesquisaPessoa[_iQuantidade-1].cdsTipoDocumento, cdsTipoDocumento);
}


void CPesquisaPessoaItr::Add( 
						char* cidTipoDocumento
					   ,char* csgTipoDocumento
					   ,char* cdsTipoDocumento
					  )
{
    // Só realoca quando preencher o bloco anterior, ou quando for a primeira vez
    // Maio, 2007 - Cassio
    int tamBlocoGrupo = 50;
    if ( (_iQuantidade % tamBlocoGrupo) == 0 )
    {
        int Resto = _iQuantidade / tamBlocoGrupo;
        int tamBlocoAtu = (Resto+1) * tamBlocoGrupo;

	    stcrPesquisaPessoa = (struct STPesquisaPessoaRegistro*) 
                realloc( stcrPesquisaPessoa, sizeof(STPesquisaPessoaRegistro)*tamBlocoAtu );
    }
	_iQuantidade++;
	//stcrPesquisaPessoa = (struct STPesquisaPessoaRegistro*) realloc( stcrPesquisaPessoa, sizeof(STPesquisaPessoaRegistro)*_iQuantidade );
	strcpy( stcrPesquisaPessoa[_iQuantidade-1].cidTipoDocumento, cidTipoDocumento);
	strcpy( stcrPesquisaPessoa[_iQuantidade-1].csgTipoDocumento, csgTipoDocumento);
	strcpy( stcrPesquisaPessoa[_iQuantidade-1].cdsTipoDocumento, cdsTipoDocumento);
}

void CPesquisaPessoaItr::ZeraPesquisaPessoa( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrPesquisaPessoa );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrPesquisaPessoa    = NULL;
}
