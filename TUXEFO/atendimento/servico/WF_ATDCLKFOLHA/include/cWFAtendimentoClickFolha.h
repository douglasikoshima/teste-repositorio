/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:30 $
 **/

#include <tuxfw.h>
#include "../../../commons/SmallString.h"
#include "stWFAtendimentoClickFolha.h"

#include "../../../commons/Collection/include/Collection.h"
#include "../../CondicaoAparicao/include/stWFCondicaoAparicao.h"

class cWFAtdClickFolha : public TuxBasicSvc
{
    public:
        st_AtendimentoClickFolha   m_stDados;
        st_vlAtendimentoClickFolha m_vlDados;

        DOMNode* entrada;
        XMLGen*  saida;

        TuxHelper tx;

    public:
        cWFAtdClickFolha(DOMNode* entrada,XMLGen* saida);
        bool executar(char **codErro,char **msgErro);
        inline void setarIdPessoaUsuario(int valor) { m_stDados.idPessoaUsuario = valor; m_vlDados.idPessoaUsuario = 1; }
        inline int obterIdPessoaUsuario() { return m_stDados.idPessoaUsuario; }

    private:
        long idAtendimento;

        char *gerarIDDom() { static int id=0; static char stID[32]; sprintf(stID,"ID_%d",++id); return stID; }

        void carregaDados();
        void ObterContasCliente(XMLGen *saida);
        void ObterCanaisProcedencia(XMLGen *saida);
        void ObterXMLAcoes(XMLGen *saida);
        void ObterXMLAcoesCompletas(Collection *colecaoAcoes,XMLGen *saida);
        void ObterXMLDadosArvore(XMLGen *saida);
        // void ObterWFCndAcoes(st_CondicaoAparicao *dados,st_vlCondicaoAparicao *status
        //                     ,int *idCondicaoAparicao,int *condicao);
        void ObterAtendimento(XMLGen *saida);
        void ObterGrupoAtual(XMLGen *saida);
} ;
