#ifndef CSexoItrH
#define CSexoItrH

#include <stdlib.h>
#include <string.h>


struct STSexoRegistro
{
	char cidSexo[21+1];
	char csgSexo[255+1];
	char cdsSexo[255+1];
};

class CSexoItr
{
public:
	CSexoItr();
	~CSexoItr();
	int Primeiro( void );
	int Ultimo( void );
	int Quantidade( void );
	STSexoRegistro* Registro( void );
	STSexoRegistro* Registro(int nPosicao);

private:
	STSexoRegistro* stcrSexo;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( 
			char* cidSexo,
			char* csgSexo,
			char* cdsSexo );

	void ZeraSexo( void );

};

#endif
