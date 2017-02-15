#ifndef CNivelSequenciaItrH
#define CNivelSequenciaItrH

#include <stdlib.h>
#include <string.h>


struct STNivelSequenciaRegistro
{
	char cidNivelSequencia[21+1];
	char cidSequenciaMandatoria[21+1];
	char cidSequencia[21+1];
	char cnrNivel[21+1];
	char csqOrdem[21+1];
};

class CNivelSequenciaItr
{
public:
	CNivelSequenciaItr();
	~CNivelSequenciaItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STNivelSequenciaRegistro* Registro( void );
	STNivelSequenciaRegistro* Registro(int nPosicao);

private:
	STNivelSequenciaRegistro* stcrNivelSequencia;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( 
			char* cidNivelSequencia,
			char* cidSequenciaMandatoria,
			char* cidSequencia,
			char* cnrNivel,
			char* csqOrdem );

	void ZeraNivelSequencia( void );

};

#endif
