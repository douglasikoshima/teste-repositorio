#ifndef CDepartamentoItrH
#define CDepartamentoItrH

#include <stdlib.h>
#include <string.h>

struct STDepartamentoRegistro
{
	char cidDepartamento[21+1];
	char cnmDepartamento[255+1];
};

class CDepartamentoItr
{
public:
	CDepartamentoItr();
	~CDepartamentoItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STDepartamentoRegistro* Registro( void );
	STDepartamentoRegistro* Registro(int nPosicao);

private:
	STDepartamentoRegistro* stcrDepartamento;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( 
			char* cidDepartamento,
			char* cnmDepartamento );

	void ZeraDepartamento( void );

};

#endif
