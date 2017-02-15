#ifndef CTipCrtItrH
#define CTipCrtItrH

#include <stdlib.h>
#include <string.h>
#include<tuxfw.h>

struct STTipoCarteiraRegistro
{
	char cidTipoCarteira[21+1];
	char csgTipoCarteira[255+1];
	char cdsTipoCarteira[255+1];
	char cvlPeso[21+1];
};

class CTipCrtItr
{
public:
	CTipCrtItr();
	~CTipCrtItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STTipoCarteiraRegistro* Registro( void );
	STTipoCarteiraRegistro* Registro(int nPosicao);

private:
	STTipoCarteiraRegistro* stcrTipoCarteira;
	int _iQuantidade;
	int _iPosicao;

protected:
	void Add( char* cid, char* csigla, char* cdescricao, char* cpeso );
	void ZeraTipoCarteira( void );

};

#endif