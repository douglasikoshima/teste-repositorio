#ifndef CProcedenciaItrH
#define CProcedenciaItrH

#include <stdlib.h>
#include <string.h>


struct STProcedenciaRegistro
{
	char cidProcedencia[21+1];
	char csgProcedencia[255+1];
	char cdsProcedencia[255+1];
	char cvlPeso[21+1];
};

class CProcedenciaItr
{
public:
	CProcedenciaItr();
	~CProcedenciaItr();
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
			char* csgProcedencia,
			char* cdsProcedencia,
			char* cvlPeso );

	void ZeraProcedencia( void );

};

#endif
