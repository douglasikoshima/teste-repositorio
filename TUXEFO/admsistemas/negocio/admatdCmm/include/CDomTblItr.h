#ifndef CTabelaDominioItrH
#define CTabelaDominioItrH

#include <stdlib.h>
#include <string.h>


struct STTabelaDominioRegistro
{
	char cidTabelaDominio[21+1];
	char cnmTabelaDominio[255+1];
};

class CTabelaDominioItr
{
public:
	CTabelaDominioItr();
	~CTabelaDominioItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STTabelaDominioRegistro* Registro( void );
	STTabelaDominioRegistro* Registro(int nPosicao);

private:
	STTabelaDominioRegistro* stcrTabelaDominio;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( 
			char* cidTabelaDominio,
			char* cnmTabelaDominio );

	void ZeraTabelaDominio( void );

};

#endif
