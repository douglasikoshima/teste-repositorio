#ifndef CCrgPosAreItrH
#define CCrgPosAreItrH

#include <stdlib.h>
#include <string.h>

struct STCargoPosicaoAreaRegistro
{
	char cidCargoPosicaoArea[21+1];
	char cidCargo[21+1];
	char cidPosicao[21+1];
	char cidDepartamento[21+1];
	char idPessoaUsuario[21+1];
	char cidSessao[21+1];
};

class CCrgPosAreItr
{
public:
	CCrgPosAreItr();
	~CCrgPosAreItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STCargoPosicaoAreaRegistro* Registro( void );
	STCargoPosicaoAreaRegistro* Registro(int nCargoPosicaoArea);

private:
	STCargoPosicaoAreaRegistro* stcrCargoPosicaoArea;
	int _iQuantidade;
	int _iCargoPosicaoArea;

protected:
	void Add( char* cidCargoPosicaoArea,
              char* cidCargo, 
              char* cidPosicao, 
              char* cidDepartamento,
              char* cidPessoaUsuario,
              char* cidSessao );
	void ZeraCargoPosicaoArea( void );

};

#endif