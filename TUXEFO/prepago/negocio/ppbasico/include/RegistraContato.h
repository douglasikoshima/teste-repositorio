///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Prepago
 * @usecase RegistraContato
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:15 $
 **/
///////////////////////////////////////////////////////////////////////////////

#ifndef REGISTRACONTATOH
#define REGISTRACONTATOH

#include "Global.h"
#include "RegistraContatopc.h"
#include "tuxfw.h"

class CRegistraContato:private TuxHelper
{

    public:
        TRegistraContato      tRegistraContato;
    
        CRegistraContatopc    clRegistraContatopc;

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
};

#endif
