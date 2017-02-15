#ifndef CProcedenciaItrH
#define CProcedenciaItrH

#include <stdlib.h>
#include <string.h>

struct STProcedenciaRegistro
{
	char cidProcedencia[21+1];
	char cnmProcedencia[255+1];
};

class CProcedItr
{
public:
	CProcedItr();
	~CProcedItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STProcedenciaRegistro* Registro( void );
	STProcedenciaRegistro* Registro(int nPosicao);

private:
	STProcedenciaRegistro* stcrProcedencia;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( 
			char* cidProcedencia,
			char* cnmProcedencia );

	void ZeraProcedencia( void );

};

#endif
