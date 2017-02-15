#ifndef CContatoFiltroItrH
#define CContatoFiltroItrH

#include <stdlib.h>
#include <string.h>


struct STContatoFiltroRegistro
{
	char cidContatoFiltro[21+1];
	char cidContato[21+1];
	char cidFiltro[21+1];
	char csgFiltro[255+1];
	char cdsFiltro[255+1];
	char cvlPeso[21+1];
};

class CContatoFiltroItr
{
public:
	CContatoFiltroItr();
	~CContatoFiltroItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STContatoFiltroRegistro* Registro( void );
	STContatoFiltroRegistro* Registro(int nPosicao);

private:
	STContatoFiltroRegistro* stcrContatoFiltro;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( char* cidContatoFiltro
			 ,char* cidContato
			 ,char* cidFiltro
			 ,char* csgFiltro
			 ,char* cdsFiltro
			 ,char* cvlPeso );

	void ZeraContatoFiltro( void );

};

#endif
