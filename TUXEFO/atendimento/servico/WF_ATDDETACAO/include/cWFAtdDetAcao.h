/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:32 $
 **/

#include <tuxfw.h>
#include "../../../commons/SmallString.h"

#include "../../../commons/definesAtendimento.h"
#include "../../../../atendimento/servico/Atendimento/include/cWFAtendimento.h"

#include "stWFAtdDetAcao.h"

class cWFAtdDetAcao : public TuxBasicSvc
{
    public:
        cWFAtdDetAcao() {}
        cWFAtdDetAcao(DOMNode* entrada,XMLGen* saida);
        ~cWFAtdDetAcao() {}

    public:
        bool executar();
        inline void setarIdUsuario(int valor) { idUsuario = valor; }
        inline int obterIdUsuario() { return idUsuario; }

    public:
        st_AtdDetAcao   m_stDados;
        st_vlAtdDetAcao m_vlDados;

        st_AtdBaixa m_stAtdBaixa;
        st_vlAtdBaixa m_vlAtdBaixa;

        DOMNode* entrada;
        XMLGen*  saida;

        TuxHelper tx;

    private:
        DetalheAtendimento detalheAtendimento;
        int idUsuario;

        SmallString xmlAcCompletas;
        SmallString xmlObtemAcoes;

    private:
        char *gerarIDDom() { static int id=0; static char stID[32]; sprintf(stID,"ID_%d",++id); return stID; }
        
        void ObterDetalheAtd();
        void getUsuarioCri(int idPessoaUsuario);
    
        bool usuarioPossuiPerfilAbertura();

        void ObterAcoes(XMLGen* _montagem);

        void carregaDados();

        bool processoCriSimNao();
} ;