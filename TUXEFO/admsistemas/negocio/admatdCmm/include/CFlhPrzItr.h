#ifndef CPrazoAtendimentoItrH
#define CPrazoAtendimentoItrH

#include <stdlib.h>
#include <string.h>


struct STPrazoAtendimentoRegistro
{
	char cidPrazoAtendimento[21+1];
	char cidContato[21+1];
	char cidSegmentacao[21+1];
	char cidProcedencia[21+1];
	char cqtDiasPrazoAtendimento[21+1];
};

class CPrazoAtendimentoItr
{
public:
	CPrazoAtendimentoItr();
	~CPrazoAtendimentoItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STPrazoAtendimentoRegistro* Registro( void );
	STPrazoAtendimentoRegistro* Registro(int nPosicao);

private:
	STPrazoAtendimentoRegistro* stcrPrazoAtendimento;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( 
			char* cidPrazoAtendimento,
			char* cidContato,
			char* cidSegmentacao,
			char* cidProcedencia,
			char* cqtDiasPrazoAtendimento );

	void ZeraPrazoAtendimento( void );

};

#endif
