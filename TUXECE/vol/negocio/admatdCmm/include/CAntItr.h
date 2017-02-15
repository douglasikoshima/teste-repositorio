#ifndef CIndicadorAnatelItrH
#define CIndicadorAnatelItrH

#include <stdlib.h>
#include <string.h>


struct STIndicadorAnatelRegistro
{
	char cidIndicadorAnatel[21+1];
	char csgIndicador[255+1];
	char cdsIndicador[255+1];
};

class CIndicadorAnatelItr
{
public:
	CIndicadorAnatelItr();
	~CIndicadorAnatelItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STIndicadorAnatelRegistro* Registro( void );
	STIndicadorAnatelRegistro* Registro(int nPosicao);

private:
	STIndicadorAnatelRegistro* stcrIndicadorAnatel;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( 
			char* cidIndicadorAnatel,
			char* csgIndicador,
			char* cdsIndicador );

	void ZeraIndicadorAnatel( void );

};

#endif
