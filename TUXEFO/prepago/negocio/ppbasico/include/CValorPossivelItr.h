#ifndef CValorPossivelItrH
#define CValorPossivelItrH

#include <stdlib.h>
#include <string.h>


struct STValorPossivelRegistro
{
	char cidValorPossivel[21+1];
	char cdsValorPossivel[255+1];
	char cidAtributo[21+1];
	char csqApresentacao[21+1];
	char cinDisponibilidade[21+1];
};

class CValorPossivelItr
{
public:
	CValorPossivelItr();
	~CValorPossivelItr();
	int Primeiro( void );
	int Ultimo( void );
	int Quantidade( void );
	STValorPossivelRegistro* Registro( void );
	STValorPossivelRegistro* Registro(int nPosicao);

private:
	STValorPossivelRegistro* stcrValorPossivel;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( 
				char* cidValorPossivel,
				char* cdsValorPossivel,
				char* cidAtributo,
				char* csqApresentacao,
				char* cinDisponibilidade 
			);

	void ZeraValorPossivel( void );

};

#endif
