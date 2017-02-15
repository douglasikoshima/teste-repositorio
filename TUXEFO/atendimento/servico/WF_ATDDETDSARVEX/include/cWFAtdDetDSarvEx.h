/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:53 $
 **/

#include <tuxfw.h>
#include "../../../commons/SmallString.h"

#include "stWFAtdDetDSarvEx.h"

class ObterValorTag : public TuxBasicSvc
{
    public:
        ObterValorTag() { valor = 0; }
        ObterValorTag(SmallString *ss,const char *idTag) { _ObterValorTag(ss,idTag); }
        ~ObterValorTag() { if ( valor ) delete valor; }

    public:
        operator const char*() { return this->valor; }
        operator char*() { return this->valor; }

    private:
        TuxHelper tx;
        char *gerarIDDom() { static int id=0; static char stID[32]; sprintf(stID,"ID_%d",++id); return stID; }
        char *_ObterValorTag(SmallString *ss,const char *idTag);

    private:
        char *valor;

} ;

class cWFAtdDetDSarvEx : public TuxBasicSvc
{
    public:
        cWFAtdDetDSarvEx() {}
        cWFAtdDetDSarvEx(DOMNode* entrada,XMLGen* saida);
        ~cWFAtdDetDSarvEx() {}

    public:
        bool executar(char **codErro,char **msgErro);
        inline void setarIdUsuario(int valor) { idUsuario = valor; }
        inline int obterIdUsuario() { return idUsuario; }

    public:
        st_AtdDetDSarv   m_stDados;
        st_vlAtdDetDSarv m_vlDados;

        DOMNode* entrada;
        XMLGen*  saida;

        TuxHelper tx;

    private:
        int idUsuario;

    private:
        char *gerarIDDom() { static int id=0; static char stID[32]; sprintf(stID,"ID_%d",++id); return stID; }

        bool ConsultarAtendimento(int &idContato);
        void carregaDados();
} ;
