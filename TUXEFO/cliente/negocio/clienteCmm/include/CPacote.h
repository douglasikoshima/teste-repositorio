#ifndef CPACOTEH
#define CPACOTEH

#include <tuxfw.h>
#include "../include/CPacoteItr.h"

class CPacote : public CPacoteItr
{
    private:
//		int InserirRaiz();
//		int InserirNode();

	public:
		CPacote();
		~CPacote();

		void getXmlBasico( char* cNomeTag, XMLGen*xml );
		int ListId(char *pIdServicoSistemaOrigem, char *pIdSistemaOrigem);
};

#endif
