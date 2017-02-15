#ifndef CIndicadorAnatelBaixaItrH
#define CIndicadorAnatelBaixaItrH

#include <stdlib.h>
#include <string.h>


struct STIndicadorAnatelBaixaRegistro
{
	char cidIndicadorAnatelBaixa[21+1];
	char cidBaixa[21+1];
	char cidIndicadorAnatel[21+1];
	char csgIndicador[255+1];
	char cdsIndicador[255+1];
};

class CIndicadorAnatelBaixaItr
{
public:
	CIndicadorAnatelBaixaItr();
	~CIndicadorAnatelBaixaItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STIndicadorAnatelBaixaRegistro* Registro( void );
	STIndicadorAnatelBaixaRegistro* Registro(int nPosicao);

private:
	STIndicadorAnatelBaixaRegistro* stcrIndicadorAnatelBaixa;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( char* cidIndicadorAnatelBaixa
			 ,char* cidBaixa
			 ,char* cidIndicadorAnatel
			 ,char* csgIndicador
			 ,char* cdsIndicador );

	void ZeraIndicadorAnatelBaixa( void );

};

#endif
