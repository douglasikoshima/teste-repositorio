/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.114.1 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/06 12:36:43 $
 **/

#include <tuxfw.h>
#include "../../../commons/SmallString.h"

#include "stWFAtdDetPessoaEx.h"

class cWFAtdDetPessoaEx : public TuxBasicSvc
{
    public:
        cWFAtdDetPessoaEx() {}
        cWFAtdDetPessoaEx(DOMNode* entrada,XMLGen* saida);
        ~cWFAtdDetPessoaEx() {}

    public:
        bool executar(char **codErro,char **msgErro);
        inline void setarIdUsuario(int valor) { idUsuario = valor; }
        inline int obterIdUsuario() { return idUsuario; }

    public:
        st_AtdDetPessoa   m_stDados;
        st_vlAtdDetPessoa m_vlDados;

        st_AtdDetPessoaPesq   m_stAtdDetPessoa;
        st_vlAtdDetPessoaPesq m_vlAtdDetPessoa;

        st_AtdBaixa m_stAtdBaixa;
        st_vlAtdBaixa m_vlAtdBaixa;

        st_GrupoAtendimento m_stGrupoAtendimento;
        st_vlGrupoAtendimento m_vlGrupoAtendimento;

        DOMNode* entrada;
        XMLGen*  saida;

        TuxHelper tx;

    private:
        int idUsuario;

        SmallString xmlPessoaDefinitivo;

        SmallString nmContato;

    private:
        char *gerarIDDom() { static int id=0; static char stID[32]; sprintf(stID,"IDOM_%d",++id); return stID; }

        //void ObterDetalheAtd();
        void ObterEnderecoPessoa(long idPessoa,SmallString &xmlPessoaEndereco);
        void ObterAtendPessoa();
        void ObterAtdCtoConsultar();
        //void ObterDetalheAtdDadosXml(char *xml);
        void MontarXMLPessoaSaida(SmallString &xmlPessoa,SmallString &xmlPessoaEndereco);

        void carregaDados();
} ;
