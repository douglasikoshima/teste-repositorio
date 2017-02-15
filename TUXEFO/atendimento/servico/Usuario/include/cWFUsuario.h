/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/10/26 23:32:55 $
 **/

#ifndef CWFUSUARIO
    #define CWFUSUARIO

#include <tuxfw.h>
#include "stUsuario.h"
#include "cApoio.h"

class cWFUsuario
{
    public:
        cWFUsuario() { entrada=0; saida=0; };
        cWFUsuario(st_VariaveisUsuario* dados, st_vlVariaveisUsuario* status, XMLGen*xml_g);
        cWFUsuario(DOMNode*dnode, XMLGen*xml_g);

    public:
        bool altStatusUsuario(DOMNode* entrada);
        bool altStatusUsuario();
        bool altStatusUsuario(int idPessoaUsuario,int idStatusUsuario);

        bool altInDisponivelWF(DOMNode* entrada);
        bool altInDisponivelWF(int idPessoaUsuario,bool inDisponivelWF);
        bool altInDisponivelWF();

        int  pesqStatusUsuario();
        int  pesqStatusUsuario(int idPessoaUsuario,DadosStatusUsuario &dadosStatusUsuario);
        int  pesqStatusDispUsuario(int idPessoaUsuario,int *idStatusUsuario,int *inDisponivelWF);
        int  pesqStatusDispUsuario(int *idStatusUsuario,int *inDisponivelWF);

        bool consultarWFGruposFilaProcesso(DOMNode* entrada,XMLGen* saida);
        bool consultarWFGruposFilaProcesso(int idPessoaUsuario,XMLGen* saida);
        bool consultarWFGruposFilaProcesso();
        bool pesqConsultaWFGrupos(XMLGen *saida);
        bool pesqConsultaWFGruposCri(XMLGen *saida);
        bool pesqConsultaWFGruposRelatorios(XMLGen *saida);
        bool pesqConsultaWFGruposRelatoriosCRI(XMLGen *saida);
        bool pesqConsultaGruposUsuario();
        bool pesqConsultaGruposUsuario(DOMNode*entrada,XMLGen *saida);
        bool pesqConsultaGruposUsuario(int idPessoaUsuario,XMLGen *saida);
        bool pesqGrupoCanal(DOMNode* entrada,XMLGen* saida);
        bool pesqGrupoCanal();
        bool pesqGrupoProcedencia(DOMNode* entrada,XMLGen* saida);
        bool pesqGrupoProcedencia();
        bool pesqUsuarioGrpCanal();
        bool pesqUsuarioGrpCanal(DOMNode* entrada, XMLGen* saida);
        bool pesqUsuarioGrpProcedencia(DOMNode* entrada, XMLGen* saida);
        bool pesqUsuarioGrpProcedencia();
        bool pesqUsuarioPorGrupo();
        bool pesqUsuarioPorGrupo(DOMNode* entrada,XMLGen* saida);
        bool pesqLgUserPorGrupo();
        bool pesqLgUserPorGrupo(DOMNode* entrada,XMLGen* saida);
        bool pesqUsuGrpProxNivel(st_VariaveisUsuario* dados, XMLGen* saida,int *contadorLinhas=0);
        bool pesqMC1UserAtual(DOMNode* entrada,XMLGen* saida);
        bool pesqLgUserPorGrupoMC(DOMNode* entrada,XMLGen* saida,const char *idPessoaUsuario);

        // bool pesqUsuGrpProxFase(st_VariaveisUsuario* dados, XMLGen* saida,int *contadorLinhas=0);
        int getUsuarioSupervisorSimNao(int _idPessoaUsuario,int _idGrupo,bool &isSupervisor);
        int obterGrupoRetornoGrupoRetorno(st_VariaveisUsuario* _dados);
        int obterGrupoRetornoGrupoRetorno(st_VariaveisUsuario* _dados,char *nmGrupo);

        int ObterInDisponivelWF(DOMNode* entrada);
        int ObterInDisponivelWF();
        int ObterStatusUsuario(DOMNode* entrada);
        int ObterStatusUsuario();

        bool pesqUsuarioRelatorio(int idGrupo, XMLGen* saida, int start, int stop);
        bool pesqUsuarioRelatorio(int idGrupo, XMLGen* saida);
        void obterListaStatusUsuario(XMLGen *saida);
        bool pesqConsultaWFGrupos1(XMLGen *saida, int idUser);
        bool pesqConsultaWFGruposBko(XMLGen *saida, int idUser);
        bool pesqConsultaWFGruposCri1(XMLGen *saida, int idUser);
        bool pesqConsultaWFGruposRC(XMLGen *saida, int idUser);

    private:
        void carregaDados();

    public:
        st_VariaveisUsuario         m_stDados;
        st_vlVariaveisUsuario       m_vlDados;

        DOMNode* entrada;
        XMLGen*  saida;

        TuxHelper tx;
};

#endif
