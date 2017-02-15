#ifndef CTipoFechamentoContatoItrH
#define CTipoFechamentoContatoItrH

#include <stdlib.h>
#include <string.h>


struct STTipoFechamentoContatoRegistro
{
	char cidTipoFechamentoContato[21+1];
	char cnmTipoFechamentoContato[255+1];
};

class CTipoFechamentoContatoItr
{
public:
	CTipoFechamentoContatoItr();
	~CTipoFechamentoContatoItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STTipoFechamentoContatoRegistro* Registro( void );
	STTipoFechamentoContatoRegistro* Registro(int nPosicao);

private:
	STTipoFechamentoContatoRegistro* stcrTipoFechamentoContato;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( 
			char* cidTipoFechamentoContato,
			char* cnmTipoFechamentoContato );

	void ZeraTipoFechamentoContato( void );

};

#endif
