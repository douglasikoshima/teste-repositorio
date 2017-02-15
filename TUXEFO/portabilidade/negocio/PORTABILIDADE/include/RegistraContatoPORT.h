#ifndef REGISTRACONTATOHPORT
#define REGISTRACONTATOHPORT

#include "PPGlobalPORT.h"
#include "RegistraContatopcPORT.h"
#include "tuxfw.h"

class CRegistraContato:private TuxHelper
{

public:
        TRegistraContato tRegistraContato;
        CRegistraContatopc clRegistraContatopc;

        CRegistraContato(void);
        void insereRegistraContato(void);
        bool existeRegistraContato(void);
        void atualizaRegistraContato(void);

        void setIdPessoaDePara(char *pszIdPessoaDePara);
        void setIdLinhaTelefonica(char *pszIdLinhaTelefonica);
        void setCdAreaRegistro(char *pszCdAreaRegistro);
        void setNrLinha(char *pszNrLinha);
        void setIdPessoaLinhaHistorico(char *pszIdPessoaLinhaHistorico);
        void setXml(char *pszXml);
        void setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao);
        void setNrTelefone(char *pszNrTelefone);

        char *getIdRegistraContato(void);
};

#endif
