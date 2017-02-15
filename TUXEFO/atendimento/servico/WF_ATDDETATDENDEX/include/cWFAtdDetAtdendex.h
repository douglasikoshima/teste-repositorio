/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5114878 $ - $Date: 2010/07/08 23:19:03 $
 **/

#include <tuxfw.h>
#include "../../../commons/SmallString.h"

#include "stWFAtdDetAtdendex.h"

class cWFAtdDetAtendex : public TuxBasicSvc
{
    public:
        cWFAtdDetAtendex() {}
        cWFAtdDetAtendex(DOMNode* entrada,XMLGen* saida);
        ~cWFAtdDetAtendex() {}

    public:
        bool executar(char **codErro,char **msgErro);
        inline void setarIdUsuario(int valor) { idUsuario = valor; }
        inline int obterIdUsuario() { return idUsuario; }
        bool ObterMigracao();

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
        bool inMigracao;

    private:
        char *gerarIDDom() { static int id=0; static char stID[32]; sprintf(stID,"ID_%d",++id); return stID; }

        void ObterDetalheAtd(XMLGen* _montagem);

        void ObterQtdeAtdOrigem(XMLGen* _montagem);

        void ObterAtdCtoConsultar(XMLGen* _montagem);

        void ObterAtendPessoa(XMLGen* _montagem);

        void ObterDetalheAtdEx(XMLGen* _montagem);
        void ObterDetalheAtdEx_Migracao(XMLGen* _montagem);
        void ObterQtdeAtdOrigemEx(XMLGen* _montagem);

        void ObterAtdCtoConsultarEx(XMLGen* _montagem);
        void ObterAtdCtoConsultarEx_Migracao(XMLGen* _montagem);
        void ObterAtendPessoaEx(XMLGen* _montagem);

        void carregaDados();
} ;
