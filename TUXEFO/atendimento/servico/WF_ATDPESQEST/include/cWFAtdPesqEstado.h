/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:43 $
 **/

#include <tuxfw.h>
#include "../../../commons/msgPadrao.h"

#include "stWFAtdPesqEstado.h"

class cWFAtdPesqEstado : public TuxBasicSvc
{
    public:
        cWFAtdPesqEstado() {}
        cWFAtdPesqEstado(DOMNode* entrada,XMLGen* saida);
        ~cWFAtdPesqEstado() {}

    public:
        bool Executar(char **codErro,char **msgErro);
        inline void setarIdUsuario(int valor) { idUsuario = valor; }
        inline int obterIdUsuario() { return idUsuario; }

    public:
        DOMNode* entrada;
        XMLGen*  saida;

        TuxHelper tx;

    private:
        int idUsuario;

    private:
        char *gerarIDDom() { static int id=0; static char stID[32]; sprintf(stID,"ID_%d",++id); return stID; }
        bool ConsultarEstado(char **codErro,char **msgErro);
} ;
