#ifndef CMunicipioItrH
#define CMunicipioItrH

#include <stdlib.h>
#include <string.h>


struct STMunicipioRegistro
{
	char cidMunicipio[21+1];
	char cnmMunicipio[255+1];
	char cidUF[21+1];
};

class CMunicipioItr
{
public:
	CMunicipioItr();
	~CMunicipioItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STMunicipioRegistro* Registro( void );
	STMunicipioRegistro* Registro(int nPosicao);

private:
	STMunicipioRegistro* stcrMunicipio;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( 
			char* cidMunicipio,
			char* cnmMunicipio,
			char* cidUF );

	void ZeraMunicipio( void );

};

#endif
