/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:59 $
 **/

#include <tuxfw.h>

#include "stWFAtdPsqNomeContato.h"
#include "../../../commons/definesAtendimento.h"

//============================================================================
// Declarações de classes e protótipos

class cWFAtdPsqNomeContato : public TuxBasicSvc
{
    public:
        cWFAtdPsqNomeContato() {entrada=0;saida=0;}

        cWFAtdPsqNomeContato(DOMNode* entrada,XMLGen* saida);
        ~cWFAtdPsqNomeContato() { }

    public:
        void Executar();
        inline void setarIdUsuario(int valor) { idUsuario = valor; }
        inline int obterIdUsuario() { return idUsuario; }
        inline char *obterIdUsuarioStr() { sprintf(idUsuarioStr,"%d",idUsuario); return idUsuarioStr; }

    public:
        DOMNode* entrada;
        XMLGen*  saida;

        TuxHelper tx;

    private:
        char idUsuarioStr[32];

        st_WFAtdPsqNomeContato   dados;
        st_vlWFAtdPsqNomeContato status;

        int idUsuario;

    private:
        int proCPesquisaNomeFolha();
        void CarregarDados();
};

