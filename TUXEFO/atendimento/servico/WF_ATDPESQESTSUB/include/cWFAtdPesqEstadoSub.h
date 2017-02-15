/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:28 $
 **/

#include <tuxfw.h>
#include "../../../commons/msgPadrao.h"

#include "stWFAtdPesqEstadoSub.h"

class cWFAtdPesqEstadoSub : public TuxBasicSvc
{
    public:
        cWFAtdPesqEstadoSub() {}
        cWFAtdPesqEstadoSub(DOMNode* entrada,XMLGen* saida);
        ~cWFAtdPesqEstadoSub() {}

    public:
        bool Executar(char **codErro,char **msgErro);
        inline void setarIdUsuario(int valor) { idUsuario = valor; }
        inline int obterIdUsuario() { return idUsuario; }

    public:
        st_AtdPesqEstadoSub   m_stDados;
        st_vlAtdPesqEstadoSub m_vlDados;

        DOMNode* entrada;
        XMLGen*  saida;

        TuxHelper tx;

    private:
        int idUsuario;

    private:
        char *gerarIDDom() { static int id=0; static char stID[32]; sprintf(stID,"ID_%d",++id); return stID; }
        bool ConsultarEstado();
        bool ConsultarSubEstado();
} ;
