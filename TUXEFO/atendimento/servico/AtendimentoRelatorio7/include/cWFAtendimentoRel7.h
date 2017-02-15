/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:00 $
 **/

#ifdef WIN32
#pragma warning(disable:4786)
#endif

#include <iostream>
#include <string>
#include <vector>
#include <map>
#include <algorithm>
#include <functional>

using namespace std;

#include <tuxfw.h>
#include "stWFAtendimentoRel7.h"
#include "MapErrDefs.h"

typedef map<int,ArvoreContatos> MAP_AC;
typedef map<int,int> MAP_TAB_RAIZ;
typedef map<string,AgrupamentoNivelZero> MAP_NVZR;
typedef map<string,TotaisAgrupamentoNivelZero> MAP_TTNZR;
typedef map<string,PalitagemAgrupada> MAP_GPAL;
typedef map<string,TotalPalitagemAgrupada> TTMAP_GPAL;
typedef vector<Palitagem> VEC_PAL;
typedef vector<string> VEC_KEYS;
typedef vector<int> VEC_CONTATO;

class cWFAtendimentoRel7 : public TuxBasicSvc
{
    public:
        st_AtendimentoRel7   m_stDados;
        st_vlAtendimentoRel7 m_vlDados;

        DOMNode* entrada;
        XMLGen*  saida;

        TuxHelper tx;

        int nivelMax; // Salva o valor da folha mais distante

        Paginacao   pPagina;


    private:
        MAP_AC mapAC; // Arvore de contatos
        MAP_TAB_RAIZ mapRaiz; // Lista de todos os idContatoPai (second element always equal cypher)
        MAP_GPAL mapGPal; // Palitagem agrupada
        MAP_NVZR mapNvZr; // Agrupamentos relatório nível 0
        MAP_TTNZR mapTtNZr; // Totais dos Agrupamentos relatório nível 0
        TTMAP_GPAL TtMapGPal; // Palitagem agrupada
        VEC_PAL vecPal; // Palitagem discreta filtrada 
        VEC_KEYS vecKeys; // Chaves armazenadas
        VEC_CONTATO vecContato; // Contatos armazenadas

        bool arvoreContatosCarregada;
        bool arvorePalitagemCarregada;

    public:
        cWFAtendimentoRel7(DOMNode* entrada,XMLGen*  saida);

        void ObterRelatorio();

        void PadronizarData1();
        void PadronizarData2();
        void PadronizarDataHoje();
        void GerarSaidaXML();
        void AcumularHorasAnteriores();

        void CarregarArvoreContatos();
        void CarregarPalitagem();

    private:
        void AgruparLinha(Palitagem &palitagem);
        void TotalizarHora(Palitagem &palitagem);
        void BuscarPai(int idContato,int nivel,MAP_AC::iterator &itMapAC);
        void carregaDados();
        void carregaDadosDom(char *node);
        void carregaDadosPaginacao( void );
        void ConsistirParametros();
        void ProcurarDescendentes();
        void GerarTagValores(char *hora,int idContatoPai,char*descricao
                            ,int qtdeHoje,int qtdeData1,int qtdeData2
                            ,bool possuiDescendente);
};
