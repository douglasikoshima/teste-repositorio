#ifndef CSisOrgItrH
#define CSisOrgItrH

#include <stdlib.h>
#include <string.h>
#include<tuxfw.h>

struct STSistemaOrigemRegistro
{
	char cidSistemaOrigem[21+1];
	char csgSistemaOrigem[255+1];
	char cnmSistemaOrigem[255+1];
};

class CSisOrgItr
{
public:
	CSisOrgItr();
	~CSisOrgItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STSistemaOrigemRegistro* Registro( void );
	STSistemaOrigemRegistro* Registro(int nPosicao);

private:
	STSistemaOrigemRegistro* stcrSistemaOrigem;
	int _iQuantidade;
	int _iPosicao;

protected:
	void Add( char* cid, char* csigla, char* cnome );
	void ZeraSistemaOrigem( void );

};

#endif