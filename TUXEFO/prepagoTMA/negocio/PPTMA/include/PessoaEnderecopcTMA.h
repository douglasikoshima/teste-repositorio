#ifndef PESSOA_ENDERECOH_PCTMA
#define PESSOA_ENDERECOH_PCTMA

#include "PPGlobalTMA.h"

class CPessoaEnderecopc
{
public:
    void proCInserePessoaEndereco(TPessoaEndereco* ptPessoaEndereco);
    void proCAtualizaPessoaEndereco(TPessoaEndereco* ptPessoaEndereco);
    void proCApagaPessoaEndereco(TPessoaEndereco* ptPessoaEndereco);
    bool proCBuscaPessoaEndereco(TPessoaEndereco* ptPessoaEndereco);
    void proCEnderecoSujo(TPessoaEndereco* ptPessoaEndereco);
    bool proCBuscaSPED( TPessoaEndereco* ptPessoaEndereco, char * nrCEP, char * nmLograd, char * nmBairro, char * nmCidade );
    bool proCBuscaSiglaUF( TPessoaEndereco* tPessoaEndereco, int idUFPrm );
    void procExcluirEndereco(const char *idPessoa);
    bool proCExistePessoaEndereco(const char *idPessoa,char *idPessoaEndereco);
    void trata( char * s );

};

#endif
