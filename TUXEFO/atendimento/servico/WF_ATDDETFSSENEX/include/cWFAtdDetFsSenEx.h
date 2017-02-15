/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:12 $
 **/

#include <tuxfw.h>
#include "../../../commons/SmallString.h"

#include "../../../../atendimento/servico/Atendimento/include/cWFAtendimento.h"
#include "stWFAtdDetFsSenEx.h"

class ObterValorTag : public TuxBasicSvc
{
    public:
        ObterValorTag() { valor = 0; }
        ObterValorTag(SmallString *ss,const char *idTag)
                                                { _ObterValorTag(ss,idTag); }
        ObterValorTag(char *ss,const char *idTag)
                                                { _ObterValorTag(ss,idTag); }
        ObterValorTag(DOMNode *entrada,const char *dnode,const char *idTag)
                                                { _ObterValorTag(entrada,dnode,idTag); }
        ObterValorTag(DOMNode *dn,const char *idTag)
                                                { _ObterValorTag(dn,idTag); }

        ~ObterValorTag() { if ( valor ) delete valor; }

    public:
        operator SmallString*() { return (SmallString*)this->valor; }
        operator SmallString() { return (SmallString)this->valor; }
        operator char*() { return this->valor ? this->valor : ""; }
        operator char() { return this->valor ? *this->valor: '\0'; }
        operator int() { return this->valor ? atoi(this->valor) : 0; }

    private:
        TuxHelper tx;
        char *gerarIDDom() { static int idDOM=0;static char stID[32]; sprintf(stID,"ID_%d",++idDOM); return stID; }
        
        char *_ObterValorTag(SmallString *ss,const char *idTag);
        char *_ObterValorTag(DOMNode *entrada,const char *dnode,const char *idTag);
        char *_ObterValorTag(DOMNode *dn,const char *idTag);
        char *_ObterValorTag(char *ss,const char *idTag);

    private:
        char *valor;

} ;

class cWFAtdDetFsSenEx : public TuxBasicSvc
{
    public:
        cWFAtdDetFsSenEx() {}
        cWFAtdDetFsSenEx(DOMNode* entrada,XMLGen* saida);
        ~cWFAtdDetFsSenEx() {}

    public:
        bool executar(char **codErro,char **msgErro);
        inline void setarIdUsuario(int valor) { idUsuario = valor; }
        inline int obterIdUsuario() { return idUsuario; }

    public:
        st_AtdDetFsSen   m_stDados;
        st_vlAtdDetFsSen m_vlDados;

        st_AtdDetFsSenPesq   m_stAtdDetFsSen;
        st_vlAtdDetFsSenPesq m_vlAtdDetFsSen;

        st_AtdBaixa m_stAtdBaixa;
        st_vlAtdBaixa m_vlAtdBaixa;

        st_GrupoAtendimento m_stGrupoAtendimento;
        st_vlGrupoAtendimento m_vlGrupoAtendimento;

        DOMNode* entrada;
        XMLGen*  saida;

        TuxHelper tx;

    private:
        int idUsuario;

        SmallString xmlNiveis;

        SmallString nmContato;

        DetalheAtendimento detalheAtendimento;

    private:
        char *gerarIDDom() { static int id=0; static char stID[32]; sprintf(stID,"ID_%d",++id); return stID; }

        bool ObterDetalheAtd();

        int ObterNivGrAt(int nrNivel);

        void ObterFase();
        void ObterDetalheAtendNivel(int idFase,int idFaseAtual);
        int ObterNivelContatoFaseRetorno(char *nmGrupo);
        bool ObterHistoricoColunaRetorno();
        void ObterNivelGrupoAtual();
        void ObterGrupoAtual(int status);
        void PesquisarUsuGrpProxNivel();

        void carregaDados();
} ;
