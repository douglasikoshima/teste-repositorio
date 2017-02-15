#ifndef PESSOALINHAH
#define PESSOALINHAH

#include <tuxfw.h>
#include "Global.h"

class PessoaLinha
{

	public:
        PessoaLinha();
        ~PessoaLinha();

        void buscaLinhasPorIdPessoa(char *pszIdPessoa, XMLGen *xml_g);
};

#endif
