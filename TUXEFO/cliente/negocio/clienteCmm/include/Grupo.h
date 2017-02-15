#ifndef GRUPOH
#define GRUPOH

#include <tuxfw.h>
#include "Global.h"

class Grupo
{
    private:
        bool buscaClientePorIdLinhaTelefonica(char *pszIdLinhaTelefonica, TGrupo *ptGrupo);

	public:
        Grupo();
        ~Grupo();

        void insereLinhaTelefonicaGrupo(char *pszIdPessoaLinhaHistorico, char *pszIdGrupo);
        void expiraLinhaTelefonicaGrupo(char *pszIdPessoaLinhaHistorico);
		void carregaCombo(XMLGen *xml_g);
        void buscaClientePorIdPessoa(char *pszIdPessoa, XMLGen *xml_g);
        void buscaClientePorNome(char *pszNmNome, char *pszNmNomeMeio, char *pszNmSobreNome, XMLGen *xml_g);
        void buscaClientePorRazao(char *pszNmPessoaFilial, XMLGen *xml_g);
        void buscaClientePorCelular(char *pszCelular, XMLGen *xml_g);
        void buscaClientePorConta(char *pszCdConta, XMLGen *xml_g);
        void buscaClientePorDocumento(char *pszNrDocumento, char *pszSgClassificacao, XMLGen *xml_g);
        void retornoVazio(XMLGen *xml_g);
};

#endif
