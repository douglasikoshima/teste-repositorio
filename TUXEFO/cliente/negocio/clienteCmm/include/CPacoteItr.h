#ifndef CPacoteItrH
#define CPacoteItrH

#include <stdlib.h>
#include <string.h>

struct STPacoteRegistro
{
	char cidServicoSistemaOrigem[255+1];
};

class CPacoteItr
{
public:
	CPacoteItr();
	~CPacoteItr();
	int Primeiro( void );
	int Ultimo( void );
	int Quantidade( void );
	STPacoteRegistro* Registro( void );
	STPacoteRegistro* Registro(int nPosicao);

private:
	STPacoteRegistro* stcrPacote;
	int _iQuantidade;
	int _iPosicao;

protected:
	void Add(char* cidServicoSistemaOrigem);
	void ZeraPacote(void);
};

#endif
