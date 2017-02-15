#ifndef PESSOALINHAH
#define PESSOALINHAH

#include <tuxfw.h>
#include "Global.h"

class CLinhaConvergente
{
public:
	CLinhaConvergente();
	~CLinhaConvergente();

	void buscaLinhasPorNumero(char *pszNrTelefone, XMLGen *xml_g);
};

#endif
