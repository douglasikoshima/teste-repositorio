#ifndef CMensagemAvisoItrH
#define CMensagemAvisoItrH

#include <stdlib.h>
#include <string.h>


struct STMensagemAvisoRegistro
{
	char cidMensagemAviso[21+1];
	char cdsMensagemAviso[255+1];
};

class CMensagemAvisoItr
{
public:
	CMensagemAvisoItr();
	~CMensagemAvisoItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STMensagemAvisoRegistro* Registro( void );
	STMensagemAvisoRegistro* Registro(int nPosicao);

private:
	STMensagemAvisoRegistro* stcrMensagemAviso;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( 
			char* cidMensagemAviso,
			char* cdsMensagemAviso );

	void ZeraMensagemAviso( void );

};

#endif
