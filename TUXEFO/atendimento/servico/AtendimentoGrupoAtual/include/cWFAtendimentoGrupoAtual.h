#ifndef _IcWFAtendimentoGrupoAtual_
#define _IcWFAtendimentoGrupoAtual_
/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.2 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:20 $
 **/ 
 
#include <tuxfw.h>
#include "../../AtendimentoGerarXMLDPR/include/stWFAtendimentoGerarXMLDPR.h"
#include "stWFAtendimentoGrupoAtual.h"

#define GERAR_XML_OUT true
#define NAO_GERAR_XML_OUT false

class cWFAtendimentoGrupoAtual
{
    st_AtendimentoGrupoAtual    m_stDados;
    st_vlAtendimentoGrupoAtual  m_vlDados;

    DOMNode* entrada;
    XMLGen*  saida;

    TuxHelper tx;

    public:
        cWFAtendimentoGrupoAtual(st_AtendimentoGrupoAtual* dados, st_vlAtendimentoGrupoAtual* indicator, XMLGen*xml_g);
        cWFAtendimentoGrupoAtual(DOMNode*dnode, XMLGen*xml_g);
        cWFAtendimentoGrupoAtual(XMLGen*xml_g);
        cWFAtendimentoGrupoAtual() { entrada=0;saida=0; }
        ~cWFAtendimentoGrupoAtual() {}

        long incluir(XMLDPR *xmlDpr);
        int incluir(); // ** NAO USAR - MANTIDO PARA COMPATIBILIDADE COM O SSKLUNK **
        long incluirBko();
        int alterar(); // ** NAO USAR - MANTIDO PARA COMPATIBILIDADE COM O SSKLUNK **
        int alterar(XMLDPR *xmlDpr);
        int alterarBko();
        long atualizarBko();
        int excluir(); // ** NAO USAR - MANTIDO PARA COMPATIBILIDADE COM O SSKLUNK **
        int excluir(XMLDPR *xmlDpr);
        int excluirLogicamente();
        int excluirBko();
        int getGrupo();
        int ObterGrupoAtual(long idAtendimento);

        bool consultar();
        bool ObtemGrAt();
        bool ObtemGrBko();
        bool ObtemGrAt(long idAtendimento);
        bool ObtemGrAt(long idAtendimento,int *idGrupoAtual);
        bool ObtemGrAt(long idAtendimento,int *idGrupoAtual,char *nmGrupoAtual);
        bool ObtemGrAt(int *idGrupoAtual);
        bool AtuEntradaFila();
        bool existegrupoAtual(long idAtendimento);

    private:
        void carregaDados();
};

#endif
