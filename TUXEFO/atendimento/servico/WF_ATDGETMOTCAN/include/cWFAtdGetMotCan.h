/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:53 $
 **/

#include <tuxfw.h>
#include "../../../commons/msgPadrao.h"
#include "../../../commons/SmallString.h"

#include "stWFAtdGetMotCan.h"

class cWFAtdGetMotCan : public TuxBasicSvc
{
    public:
        cWFAtdGetMotCan() {}
        cWFAtdGetMotCan(DOMNode* entrada,XMLGen* saida);
        ~cWFAtdGetMotCan() {}

    public:
        bool executar(char **codErro,char **msgErro);
        inline void setarIdUsuario(int valor) { idUsuario = valor; }
        inline int obterIdUsuario() { return idUsuario; }

    public:
        st_AtdGetMotCan   m_stDados;
        st_vlAtdGetMotCan m_vlDados;

        DOMNode* entrada;
        XMLGen*  saida;

        TuxHelper tx;

    private:
        int idUsuario;

        SmallString xmlMotivos;
        SmallString xmlObtemPsqTab;

    private:
        char *gerarIDDom() { static int id=0; static char stID[32]; sprintf(stID,"ID_%d",++id); return stID; }
        void carregaDados();
        void CarregarTabMotivosAtividade();
        void ConsultarMotivoAtividade();
        void ObterFaseMaxAtendimento();
        void ObterDetalheAtd();
        void ObterWFCancelamento(int idFase);
} ;
