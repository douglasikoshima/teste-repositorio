#ifndef CGRUPOUSUARIOH
#define CGRUPOUSUARIOH

#include<tuxfw.h>

class CGrupoUsuario
{
    public:
        CGrupoUsuario();
        ~CGrupoUsuario();

        void insereUsuarioEmprestado(char *pDtInicial, char *pDtFinal, char *pIdPessoaUsuarioEfetuado, char *pIdUsuarioAlteracao, char *pIdPessoaUsuario, char *pIdGrupo);
        void carregaListaUsuarioEmprestado(XMLGen* xml_g, char *pIdGrupo);
        void carregaComboGrupo(XMLGen* xml_g);
        void carregaListaUsuario(XMLGen* xml_g, char *pIdGrupo);
        void retornoVazio(XMLGen *xml_g);
        void apagaUsuarioEmprestado(char *pIdPessoaUsuario);
};
#endif
