#ifndef CPaisItrH
#define CPaisItrH

#include <stdlib.h>
#include <string.h>

struct STPaisRegistro
{
	char cidPais[21+1];
	char csgPais[255+1];
	char cdsPais[255+1];
	char cdsNacionalidade[255+1];
};

class CPaisItr
{
public:
	CPaisItr();
	~CPaisItr();
	int Primeiro( void );
	int Ultimo( void );
	int Quantidade( void );
	STPaisRegistro* Registro( void );
	STPaisRegistro* Registro(int nPosicao);

private:
	STPaisRegistro* stcrPais;
	int _iQuantidade;
	int _iPosicao;

protected:
	void Add( char* cid, char* csigla, char* cdescricao, char* cnacionalidade );
	void ZeraPais( void );

};

#endif