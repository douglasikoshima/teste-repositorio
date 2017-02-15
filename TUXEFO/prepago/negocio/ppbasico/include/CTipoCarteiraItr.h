#ifndef CTipoCarteiraItrH
#define CTipoCarteiraItrH

#include <stdlib.h>
#include <string.h>

struct STTipoCarteiraRegistro
{
	char cidTipoCarteira[21+1];
	char csgTipoCarteira[255+1];
	char cdsTipoCarteira[255+1];
	char cvlPeso[21+1];
	char cinCorporativo[21+1];
};

class CTipoCarteiraItr
{
public:
	CTipoCarteiraItr();
	~CTipoCarteiraItr();
	int Primeiro( void );
	int Ultimo( void );
	int Quantidade( void );
	STTipoCarteiraRegistro* Registro( void );
	STTipoCarteiraRegistro* Registro(int nPosicao);

private:
	STTipoCarteiraRegistro* stcrTipoCarteira;
	int _iQuantidade;
	int _iPosicao;

protected:
	void Add( char* cidTipoCarteira, 
	          char* csgTipoCarteira, 
	          char* cdsTipoCarteira, 
	          char* cvlPeso,
	          char* cinCorporativo );
	void ZeraTipoCarteira( void );

};

#endif