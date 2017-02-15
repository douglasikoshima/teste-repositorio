///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Sincronismo
 * @usecase PessoaLinha
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:15 $
 **/
///////////////////////////////////////////////////////////////////////////////

#ifndef PESSOALINHAH
#define PESSOALINHAH

#include "Global.h"
#include "PessoaLinhapc.h"

class CPessoaLinha
{

public:

    CPessoaLinha(void);
    ~CPessoaLinha(void);
    CPessoaLinha(char *pszIdTipoRelacionamento, char *pszIdLinhaTelefonica);

    TPessoaLinha    tPessoaLinha;
	TPessoaLinhaArr tPessoaLinhaArr;
    CPessoaLinhapc  clPessoaLinhapc;

    bool buscaPessoaLinha(void);
    bool buscaPessoaLinha(TPessoaLinha *ptPessoaLinhaAux);
    void falsoInsertPessoaLinha(void);
    void atualizaPessoaLinha(void);
    void inserePessoaLinha(void);
    void apagaPessoaLinha(void);
    void atualizaPessoaLinhaPorIdConta(char *pszIdPessoaDePara, char *pszIdConta);
	/********************************************************************************
	* OBSERVACAO: Este metodo retorna o IDPESSOA no lugar do campo IDUSUARIOALTERACAO
	* e tras informacao de cliente, depois usuario
	 ********************************************************************************/
    bool buscaIdClienteUsuario( void );

    void setIdPessoaDePara(char *pszIdPessoaDePara);
    void setIdLinhaTelefonica(char *pszIdLinhaTelefonica);
    void setIdTipoRelacionamento(char *pszIdTipoRelacionamento);
    void setIdPessoaLinha(char *pszIdPessoaLinha);
    void setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao);
    void setStruct(TPessoaLinha *ptPessoaLinha);

    char *getIdPessoaDePara(void);
    char *getIdPessoaLinha(void);
    char *getIdLinhaTelefonica(void);
    char *getIdTipoRelacionamento(void);

	int  getQuantidade( void ) { return tPessoaLinhaArr.iQuantidade; };
	TPessoaLinha* getRegistro( int iIndex );
};

#endif
