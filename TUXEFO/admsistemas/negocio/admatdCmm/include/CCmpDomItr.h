#ifndef CCampoDominioItrH
#define CCampoDominioItrH

#include <stdlib.h>
#include <string.h>


struct STCampoDominioRegistro
{
	char cidCampo[21+1];
	char cdsQuery[500+1];
	char cnmAtributoIdentificador[255+1];
	char cnmAtributoDescritivo[255+1];
	char cidTabelaDominio[21+1];
};

class CCampoDominioItr
{
public:
	CCampoDominioItr();
	~CCampoDominioItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STCampoDominioRegistro* Registro( void );
	STCampoDominioRegistro* Registro(int nPosicao);

private:
	STCampoDominioRegistro* stcrCampoDominio;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( 
			char* cidCampo,
			char* cdsQuery,
			char* cnmAtributoIdentificador,
			char* cnmAtributoDescritivo 
	);

	void Add( 
			char* cidCampo,
			char* cdsQuery,
			char* cnmAtributoIdentificador,
			char* cnmAtributoDescritivo,
			char* cidTabelaDominio
	);

	void ZeraCampoDominio( void );

};

#endif
