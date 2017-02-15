///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Sincronismo
 * @usecase PessoaEndereco
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:15 $
 **/
///////////////////////////////////////////////////////////////////////////////

#ifndef PESSOA_ENDERECOH_PC
#define PESSOA_ENDERECOH_PC

#include "Global.h"

class CPessoaEnderecopc
{
public:
    void proCInserePessoaEndereco(TPessoaEndereco* ptPessoaEndereco);
    void proCAtualizaPessoaEndereco(TPessoaEndereco* ptPessoaEndereco);
    bool  buscaInfSPED( 
                        char * szcdCEP, 
                        char * sznmLogradouro,
                        char * sznmBairro,
                        char * szMunicipio,
                        char * szCdLogradouro ,
                        char * szInCNL ,
                        char * szCdIBGE
                      );
    void proCAtualizaIdTipoEndereco(TPessoaEndereco* ptPessoaEndereco);
    void proCApagaPessoaEndereco(TPessoaEndereco* ptPessoaEndereco);
    bool proCBuscaPessoaEndereco(TPessoaEndereco* ptPessoaEndereco);
    void proCEnderecoSujo(TPessoaEndereco* ptPessoaEndereco);

	//Recupera os enderecos de uma pessoa, atravez do IDPESSOA
	bool proCBuscaPessoaEnderecoPorIdPessoa(TPessoaEnderecoArr* ptPessoaEnderecoArr, TPessoaEndereco* ptPessoaEndereco);
	bool proCBuscaPessoaEnderecoContaPorIdPessoa(TPessoaEnderecoArr* ptPessoaEnderecoArr, TPessoaEndereco* ptPessoaEndereco, char* pzcIdConta);
    bool proCBuscaEnderecoContaPorIdPessoa(TPessoaEnderecoArr* ptPessoaEnderecoArr, TPessoaEndereco* ptPessoaEndereco,char* pzcIdConta);

	//Método para manipular o tipo TPessoaEnderecoArr
	void desaloca( TPessoaEnderecoArr* pztPessoaEnderecoArrAux );

private:
	//Método para manipular o tipo TPessoaEnderecoArr
	void aloca( TPessoaEnderecoArr* pztPessoaEnderecoArrAux );

};

#endif
