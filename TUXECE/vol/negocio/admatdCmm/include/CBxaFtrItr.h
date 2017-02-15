#ifndef CBaixaFiltroItrH
#define CBaixaFiltroItrH

#include <stdlib.h>
#include <string.h>


struct STBaixaFiltroRegistro
{
	char cidBaixaFiltro[21+1];
	char cidBaixa[21+1];
	char cidFiltro[21+1];
	char csgFiltro[255+1];
	char cdsFiltro[255+1];
	char cvlPeso[21+1];
};

class CBaixaFiltroItr
{
public:
	CBaixaFiltroItr();
	~CBaixaFiltroItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STBaixaFiltroRegistro* Registro( void );
	STBaixaFiltroRegistro* Registro(int nPosicao);

private:
	STBaixaFiltroRegistro* stcrBaixaFiltro;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( char* cidBaixaFiltro
			 ,char* cidBaixa
			 ,char* cidFiltro
			 ,char* csgFiltro
			 ,char* cdsFiltro
			 ,char* cvlPeso );

	void ZeraBaixaFiltro( void );

};

#endif
