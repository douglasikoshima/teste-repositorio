#ifndef CMunicipioFeriadoItrH
#define CMunicipioFeriadoItrH

#include <stdlib.h>
#include <string.h>


struct STMunicipioFeriadoRegistro
{
	char cidMunicipioFeriado[21+1];
	char cidMunicipio[21+1];
	char cidFeriado[21+1];
};

class CMunicipioFeriadoItr
{
public:
	CMunicipioFeriadoItr();
	~CMunicipioFeriadoItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STMunicipioFeriadoRegistro* Registro( void );
	STMunicipioFeriadoRegistro* Registro(int nPosicao);

private:
	STMunicipioFeriadoRegistro* stcrMunicipioFeriado;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( 
			char* cidMunicipioFeriado,
			char* cidMunicipio,
			char* cidFeriado );

	void ZeraMunicipioFeriado( void );

};

#endif
