/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:56 $
 **/

#include <tuxfw.h>
#include "../../../commons/SmallString.h"

#include "stWFAtdDetAtdend.h"

class cWFAtdDetAtend : public TuxBasicSvc
{
    public:
        cWFAtdDetAtend() {}
        cWFAtdDetAtend(DOMNode* entrada,XMLGen* saida);
        ~cWFAtdDetAtend() {}

    public:
        bool executar(char **codErro,char **msgErro);
        inline void setarIdUsuario(int valor) { idUsuario = valor; }
        inline int obterIdUsuario() { return idUsuario; }

    public:
        st_AtdDetAtend   m_stDados;
        st_vlAtdDetAtend m_vlDados;

        st_AtdDetAtendPesq   m_stAtdDetAtend;
        st_vlAtdDetAtendPesq m_vlAtdDetAtend;

        st_AtdBaixa m_stAtdBaixa;
        st_vlAtdBaixa m_vlAtdBaixa;

        st_GrupoAtendimento m_stGrupoAtendimento;
        st_vlGrupoAtendimento m_vlGrupoAtendimento;

        DOMNode* entrada;
        XMLGen*  saida;

        TuxHelper tx;

    private:
        int idUsuario;

    private:
        char *gerarIDDom() { static int id=0; static char stID[32]; sprintf(stID,"ID_%d",++id); return stID; }

        void ObterDetalheAtd(XMLGen* _montagem);

        void ObterQtdeAtdOrigem(XMLGen* _montagem);

        void ObterAtdCtoConsultar(XMLGen* _montagem);

        void ObterAtendPessoa(XMLGen* _montagem);

        void carregaDados();
} ;
