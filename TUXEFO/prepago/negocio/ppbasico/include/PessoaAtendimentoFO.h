

#ifndef CPESSOAATENDIMENTOFOH
#define CPESSOAATENDIMENTOFOH

#include <tuxfw.h>

#include "PessoaAtendimentoFOpc.h"
#include "Global.h"


class PessoaAtendimentoFO
{
    private:    
       TPessoaAtendimentoFO tPessoaAtendimentoFO;
       PessoaAtendimentoFOpc clPessoaAtendimentoFOpc; 
       void zeraPessoaAtendimentoFO(void);

	public:
        PessoaAtendimentoFO();
        ~PessoaAtendimentoFO();

        void setIdPessoaAtendimentoFO(char *pszIdPessoa);
        bool existePessoaAtendimentoFO(void);
        void inserePessoaAtendimentoFO(void);
};

#endif
