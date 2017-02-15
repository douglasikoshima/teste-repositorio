#include <stdio.h>
#include <CLinhaBaseItr.h>

CLinhaBaseItr::CLinhaBaseItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrLinhaBase    = NULL;
}

CLinhaBaseItr::~CLinhaBaseItr()
{
	ZeraLinhaBase();
}

int CLinhaBaseItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CLinhaBaseItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CLinhaBaseItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CLinhaBaseItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CLinhaBaseItr::Quantidade( void )
{
	return _iQuantidade;
}

STLinhaBaseRegistro* CLinhaBaseItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrLinhaBase[ _iPosicao ];
	else
		return 0;
}

STLinhaBaseRegistro* CLinhaBaseItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrLinhaBase[ nPosicao ];

	}
	else
		return 0;
}

void CLinhaBaseItr::Add( 
							char* cidLinhaBase,
							char* cidAreaRegistro,
							char* cnrLinha,
							char* cnrMim,
							char* cnrDigitoLinha,
							char* cidEstadoLinha,
							char* csqSincronismoEstado,
							char* ctsSincronismoEstado,
							char* cdtEstadoLinha,
							char* cdsMotivoEstado 
					  )
{
    // Só realoca quando preencher o bloco anterior, ou quando for a primeira vez
    // Maio, 2007 - Cassio
    int tamBlocoGrupo = 50;
    if ( (_iQuantidade % tamBlocoGrupo) == 0 )
    {
        int Resto = _iQuantidade / tamBlocoGrupo;
        int tamBlocoAtu = (Resto+1) * tamBlocoGrupo;

	    stcrLinhaBase = (struct STLinhaBaseRegistro*) 
                realloc( stcrLinhaBase, sizeof(STLinhaBaseRegistro)*tamBlocoAtu );
    }
	_iQuantidade++;
	//stcrLinhaBase = (struct STLinhaBaseRegistro*) realloc( stcrLinhaBase, sizeof(STLinhaBaseRegistro)*_iQuantidade );
	strcpy( stcrLinhaBase[_iQuantidade-1].cidLinhaBase, cidLinhaBase);
	strcpy( stcrLinhaBase[_iQuantidade-1].cidAreaRegistro, cidAreaRegistro);
	strcpy( stcrLinhaBase[_iQuantidade-1].cnrLinha, cnrLinha);
	strcpy( stcrLinhaBase[_iQuantidade-1].cnrMim, cnrMim);
	strcpy( stcrLinhaBase[_iQuantidade-1].cnrDigitoLinha, cnrDigitoLinha);
	strcpy( stcrLinhaBase[_iQuantidade-1].cidEstadoLinha, cidEstadoLinha);
	strcpy( stcrLinhaBase[_iQuantidade-1].csqSincronismoEstado, csqSincronismoEstado);
	strcpy( stcrLinhaBase[_iQuantidade-1].ctsSincronismoEstado, ctsSincronismoEstado);
	strcpy( stcrLinhaBase[_iQuantidade-1].cdtEstadoLinha, cdtEstadoLinha);
	strcpy( stcrLinhaBase[_iQuantidade-1].cdsMotivoEstado, cdsMotivoEstado);
}

void CLinhaBaseItr::Add( 
						char* cidPessoa,
						char* cidTipoPessoa,
						char* cnrDigitoLinha,
						char* cidLinhaTelefonica  )
{
    // Só realoca quando preencher o bloco anterior, ou quando for a primeira vez
    // Maio, 2007 - Cassio
    int tamBlocoGrupo = 50;
    if ( (_iQuantidade % tamBlocoGrupo) == 0 )
    {
        int Resto = _iQuantidade / tamBlocoGrupo;
        int tamBlocoAtu = (Resto+1) * tamBlocoGrupo;

	    stcrLinhaBase = (struct STLinhaBaseRegistro*) 
                realloc( stcrLinhaBase, sizeof(STLinhaBaseRegistro)*tamBlocoAtu );
    }
	//Adiciona apenas os ultimos 4, que sao apenas expansao para o servico
    //QUE VALIDA LINHA PARA PRE PAGO
	_iQuantidade++;
	//stcrLinhaBase = (struct STLinhaBaseRegistro*) realloc( stcrLinhaBase, sizeof(STLinhaBaseRegistro)*_iQuantidade );
	strcpy( stcrLinhaBase[_iQuantidade-1].cidPessoa, cidPessoa);
	strcpy( stcrLinhaBase[_iQuantidade-1].cidTipoPessoa, cidTipoPessoa);
	strcpy( stcrLinhaBase[_iQuantidade-1].cnrDigitoLinha, cnrDigitoLinha);
	strcpy( stcrLinhaBase[_iQuantidade-1].cidLinhaTelefonica, cidLinhaTelefonica);
}


void CLinhaBaseItr::ZeraLinhaBase( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrLinhaBase );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrLinhaBase    = NULL;
}
