#undef SQLCA
#define SQLCA_NONE
#include<sqlca.h>
#include<sqlda.h>


#include "PessoaAtendimentoFO.h"

PessoaAtendimentoFO::PessoaAtendimentoFO()
{
}

PessoaAtendimentoFO::~PessoaAtendimentoFO()
{
}

void PessoaAtendimentoFO::zeraPessoaAtendimentoFO(void)
{
     memset(&tPessoaAtendimentoFO, 0x00, sizeof(TPessoaAtendimentoFO));
}

void PessoaAtendimentoFO::setIdPessoaAtendimentoFO(char *pszIdPessoa)
{
     zeraPessoaAtendimentoFO();
     strcpy(tPessoaAtendimentoFO.szIdPessoa, pszIdPessoa);
}

bool PessoaAtendimentoFO::existePessoaAtendimentoFO(void)
{
    return clPessoaAtendimentoFOpc.proCexistePessoaAtendimentoFO(&tPessoaAtendimentoFO);
}


void PessoaAtendimentoFO::inserePessoaAtendimentoFO(void)
{
    clPessoaAtendimentoFOpc.proCinserePessoaAtendimentoFO(&tPessoaAtendimentoFO);
}
