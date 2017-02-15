/**
 * 
 * @modulo  Atendimento
 * @usecase Atendimento
 * @author  Roberto Borges
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:32 $
 **/ 

#include "../../AtendimentoGerarXMLDPR/include/cWFAtendimentoGerarXMLDPR.h"
#include "../../../../atendimento/servico/WF_ACAO/include/cWF_AcaoPC.h"
#include "stWFAtdConAnalise.h"

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

class cWFAtdConAnalise : public cWF_Acao
{
    public:
        cWFAtdConAnalise(DOMNode* dnode, XMLGen* xml_g, char *user);
         ~cWFAtdConAnalise(){};

    public:
        void Executar();

    private:
        XMLDPR xmlDpr;
};
