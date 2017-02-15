#ifndef CFaseProcessoItrH
#define CFaseProcessoItrH

#include <stdlib.h>
#include <string.h>


struct STFaseProcessoRegistro
{
	char cidFaseProcesso[21+1];
	char cnmFaseProcesso[255+1];
};

class CFaseProcessoItr
{
public:
	CFaseProcessoItr();
	~CFaseProcessoItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STFaseProcessoRegistro* Registro( void );
	STFaseProcessoRegistro* Registro(int nPosicao);

private:
	STFaseProcessoRegistro* stcrFaseProcesso;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( 
			char* cidFaseProcesso,
			char* cnmFaseProcesso );

	void ZeraFaseProcesso( void );

};

#endif
