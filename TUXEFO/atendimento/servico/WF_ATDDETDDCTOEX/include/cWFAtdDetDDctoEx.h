/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:16 $
 **/

#include <tuxfw.h>
#include "../../../commons/SmallString.h"

#include "stWFAtdDetDDctoEx.h"

class cWFAtdDetDDctoEx : public TuxBasicSvc
{
    public:
        cWFAtdDetDDctoEx() {}
        cWFAtdDetDDctoEx(DOMNode* entrada,XMLGen* saida);
        ~cWFAtdDetDDctoEx() {}

    public:
        bool executar(char **codErro,char **msgErro);
        inline void setarIdUsuario(int valor) { idUsuario = valor; }
        inline int obterIdUsuario() { return idUsuario; }

    public:
        st_AtdDetDDcto   m_stDados;
        st_vlAtdDetDDcto m_vlDados;

        st_AtdDetDDctoPesq   m_stAtdDetDDcto;
        st_vlAtdDetDDctoPesq m_vlAtdDetDDcto;

        st_AtdBaixa m_stAtdBaixa;
        st_vlAtdBaixa m_vlAtdBaixa;

        st_GrupoAtendimento m_stGrupoAtendimento;
        st_vlGrupoAtendimento m_vlGrupoAtendimento;

        DOMNode* entrada;
        XMLGen*  saida;

        TuxHelper tx;

    private:
        int idUsuario;

        SmallString nmContato;

    private:
        char *gerarIDDom() { static int id=0; static char stID[32]; sprintf(stID,"ID_%d",++id); return stID; }

        void ObterWFAtdCnC(XMLGen* _montagem);
        void ObterAtdCtoConsultar(XMLGen* _montagem);
        void ObterAtdCntConsultar(XMLGen* _montagem);
        void ObterAtdDetalheLinha(XMLGen* _montagem);

        void carregaDados();
} ;
