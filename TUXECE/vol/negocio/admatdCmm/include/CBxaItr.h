#ifndef CBaixaItrH
#define CBaixaItrH

#include <stdlib.h>
#include <string.h>


struct STBaixaRegistro
{
	char cidBaixa[21+1];
	char cidBaixaPai[21+1];
	char cidNomeBaixa[21+1];
	char cnmBaixa[255+1];
	char cinDisponibilidade[21+1];
	char cdsPath[2048+1];
	int  iLevel;
};

class CBaixaItr
{
public:
	CBaixaItr();
	~CBaixaItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STBaixaRegistro* Registro( void );
	STBaixaRegistro* Registro(int nPosicao);
	char* GetErro() { return cErroGeral; }

private:
	STBaixaRegistro* stcrBaixa;
	int _iQuantidade;
	int _iPosicao;
	char cErroGeral[255+1];

protected:

	void Add( char* cidBaixa,
			  char* cidBaixaPai,
			  char* cidNomeBaixa,
			  char* cnmBaixa,
			  char* cinDisponibilidade,
			  char* cdsPath,
			  int   iLevel );

	void Add( char* cidBaixa );

	void ZeraBaixa( void );
	void  SetErro( char* cErro );

};

#endif
