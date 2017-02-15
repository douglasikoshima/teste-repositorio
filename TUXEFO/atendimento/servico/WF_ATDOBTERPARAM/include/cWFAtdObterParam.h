/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:20 $
 **/

#include <tuxfw.h>
#include "../../../commons/SmallString.h"

#include "stWFAtdObterParam.h"

#include "../../Atendimento/include/cWFAtendimento.h"
#include "../../AtendimentoPessoa/include/cWFAtendimentoPessoa.h"
#include "../../AtendimentoLinha/include/cWFAtendimentoLinha.h"
#include "../../AtendimentoConta/include/cWFAtendimentoConta.h"
#include "../../AtendimentoContato/include/cWFAtendimentoContato.h"
#include "../../ChamadaTelefonica/include/cWFChamadaTelefonica.h"
#include "../../Usuario/include/stUsuario.h"

// class ObterValorTag : public TuxBasicSvc
// {
//     public:
//         ObterValorTag() { valor = 0; }
//         ObterValorTag(SmallString *ss,const char *idTag) { _ObterValorTag(ss,idTag); }
//         ~ObterValorTag() { if ( valor ) delete valor; }
// 
//     public:
//         operator const char*() { return this->valor; }
//         operator char*() { return this->valor; }
// 
//     private:
//         TuxHelper tx;
//         char *gerarIDDom() { static int id=0; static char stID[32]; sprintf(stID,"ID_%d",++id); return stID; }
//         char *_ObterValorTag(SmallString *ss,const char *idTag);
// 
//     private:
//         char *valor;
// };

class cWFAtdObterParam : public TuxBasicSvc
{
    public:
        cWFAtdObterParam() 
        {
            memset(atdConta.cdConta,0,sizeof(atdConta.cdConta));
            memset(atdConta.cdDigitoConta,0,sizeof(atdConta.cdDigitoConta));
        }

        cWFAtdObterParam(DOMNode* entrada,XMLGen* saida);
        ~cWFAtdObterParam() {}

    public:
        bool executar(char **codErro,char **msgErro);
        inline void setarIdUsuario(int valor) { idUsuario = valor; }
        inline int obterIdUsuario() { return idUsuario; }

    public:
        st_AtdObterParam   m_stDados;
        st_vlAtdObterParam m_vlDados;

        DOMNode* entrada;
        XMLGen*  saida;

        TuxHelper tx;

    private:
        int idUsuario;
        DetalheAtendimento detAtendimento;
        AtendimentoConta atdConta;
        struct DetReabertura detReabertura;
        struct AtendimentoPessoa atdPessoa;
        struct LinhaAtendimento linAtendimento;
        struct AtendimentoContato atdContato;

    private:
        char *gerarIDDom() { static int id=0; static char stID[32]; sprintf(stID,"ID_%d",++id); return stID; }

        void ObterDetalheAtd();
        void ObterReaberAtend();
        void ObterAtendPessoa();
        void ObterDetalheAtendLinha();
        void AtdCntConsultar();
        void AtdCtoConsultar();
        void ChaAteConsultar(unsigned long _idPessoa,unsigned long _idPessoaDePara);
		// int  ObterGrupoAbertura(st_VariaveisUsuario variaveisUsuario);
        void TratamentoCRI(long *idLinhaAtendimento,unsigned long *idPessoaHistoricoCRI);

        void carregaDados();
} ;
