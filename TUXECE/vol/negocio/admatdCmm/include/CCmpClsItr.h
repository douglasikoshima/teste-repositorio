#ifndef CCampoClassificadorItrH
#define CCampoClassificadorItrH

#include <stdlib.h>
#include <string.h>


struct STCampoClassificadorRegistro
{
	char cidClassificadorCampo[21+1];
	char cnmClassificadorCampo[255+1];
};

class CCampoClassificadorItr
{
public:
	CCampoClassificadorItr();
	~CCampoClassificadorItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STCampoClassificadorRegistro* Registro( void );
	STCampoClassificadorRegistro* Registro(int nPosicao);

private:
	STCampoClassificadorRegistro* stcrCampoClassificador;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( 
			char* cidClassificadorCampo,
			char* cnmClassificadorCampo );

	void ZeraCampoClassificador( void );

};

#endif
