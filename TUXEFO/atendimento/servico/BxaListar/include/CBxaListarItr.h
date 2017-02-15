#ifndef CBaixaItrH
#define CBaixaItrH

#include <stdlib.h>
#include <string.h>

const tamBlocoBaixa = 200;

struct STBaixaRegistro
{
	char cidBaixa[21+1];
	char cidBaixaPai[21+1];
	char cidNomeBaixa[21+1];
	char cnmBaixa[255+1];
	char cdsPath[1024+1];
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
			  char* cdsPath,
			  int   iLevel );

	void ZeraBaixa( void );
	void  SetErro( char* cErro );

};

#endif
