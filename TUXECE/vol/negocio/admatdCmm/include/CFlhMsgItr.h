#ifndef CFolhaMensagemAvisoItrH
#define CFolhaMensagemAvisoItrH

#include <stdlib.h>
#include <string.h>


struct STFolhaMensagemAvisoRegistro
{
	char cidContato[21+1];
	char cdsMensagemAviso[255+1];
};

class CFolhaMensagemAvisoItr
{
public:
	CFolhaMensagemAvisoItr();
	~CFolhaMensagemAvisoItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STFolhaMensagemAvisoRegistro* Registro( void );
	STFolhaMensagemAvisoRegistro* Registro(int nPosicao);

private:
	STFolhaMensagemAvisoRegistro* stcrFolhaMensagemAviso;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( 
			char* cidContato,
			char* cdsMensagemAviso );

	void ZeraFolhaMensagemAviso( void );

};

#endif
