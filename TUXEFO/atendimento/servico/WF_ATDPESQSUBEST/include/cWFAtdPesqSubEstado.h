/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:19 $
 **/

#include <tuxfw.h>
#include "../../../commons/msgPadrao.h"

#include "stWFAtdPesqSubEstado.h"

class cWFAtdPesqSubEstado : public TuxBasicSvc
{
    public:
        cWFAtdPesqSubEstado() {}
        cWFAtdPesqSubEstado(DOMNode* entrada,XMLGen* saida);
        ~cWFAtdPesqSubEstado() {}

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
        bool ConsultarSubEstado(char **codErro,char **msgErro);
} ;
