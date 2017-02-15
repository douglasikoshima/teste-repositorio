#include "CSafePointer.h"

CSafePointer::CSafePointer()
{
	//Zera contadores
	pcaPointers = NULL;
	_iQuantidadeBlocos = 0;
	_iPonteirosUtilizados = 0;
}

CSafePointer::~CSafePointer()
{
	desalocaTudo();
}

void CSafePointer::alocaBloco( void )
{
	//Adiciona um ao total de blocos
	_iQuantidadeBlocos++;
	//Aloca mais um bloco
	pcaPointers = (char**) realloc( pcaPointers, sizeof(char*)*_iQuantidadeBlocos*SP_TAMANHO_BLOCO );
}

void CSafePointer::desalocaTudo( void )
{
	//Libera todos os ponteiros utilizados
	for( int x = 0; x < _iPonteirosUtilizados; x++ )
	{
		XMLString::release( &pcaPointers[x] );
	}
	//Libera a matriz de ponteiros
	free( pcaPointers );
	pcaPointers = NULL;
	//Zera contadores
	_iQuantidadeBlocos = 0;
	_iPonteirosUtilizados = 0;
}

char* CSafePointer::getTag( DOMNode* pdnNode, char* pcNomeTag )
{
	return getTag( pdnNode, pcNomeTag, 0 );
}

char* CSafePointer::getTag( DOMNode* pdnNode, char* pcNomeTag, int nNivel )
{
	if( (_iPonteirosUtilizados+1) >= ( _iQuantidadeBlocos * SP_TAMANHO_BLOCO ) )
	{
		alocaBloco();
	}
	pcaPointers[_iPonteirosUtilizados] = TuxHelper::walkTree( pdnNode, pcNomeTag, nNivel );
	_iPonteirosUtilizados++;

	return pcaPointers[_iPonteirosUtilizados-1];
}
