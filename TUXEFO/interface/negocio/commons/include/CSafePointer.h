#ifndef CCSafePointerH
#define CCSafePointerH

#include <tuxfw.h>
#include <stdlib.h>
#include <string.h>

#define SP_TAMANHO_BLOCO 10

class CSafePointer : public TuxHelper
{
	public:
		CSafePointer();
		~CSafePointer();
		char* getTag( DOMNode* pdnNode, char* pcNomeTag );
		char* getTag( DOMNode* pdnNode, char* pcNomeTag, int nNivel );
		
	private:
		char** pcaPointers;
		int _iQuantidadeBlocos;
		int _iPonteirosUtilizados;

		void alocaBloco( void );
		void desalocaTudo( void );
};

#endif