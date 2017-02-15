#ifndef CSesItrH
#define CSesItrH

#include <stdlib.h>
#include <string.h>

struct STSessaoRegistro
{
	char cidSessao[21+1];
	char cdsSessao[255+1];
};

class CSesItr
{
public:
	CSesItr();
	~CSesItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STSessaoRegistro* Registro( void );
	STSessaoRegistro* Registro(int nSessao);

private:
	STSessaoRegistro* stcrSessao;
	int _iQuantidade;
	int _iSessao;

protected:
	void Add( char* cid, char* cnome );
	void ZeraSessao( void );

};

#endif