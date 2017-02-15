
#include <stdio.h>
#include <stdlib.h>
#include <tuxfw.h>
#include <vector>

using namespace std;

typedef vector<unsigned long> VEC_PERFIL;
typedef vector<unsigned long> VEC_VARIAVEL;
typedef vector<unsigned long> VEC_SEGMENTOXML;
typedef vector<unsigned long> VEC_CARTEIRAXML;
typedef vector<unsigned long> VEC_PROCEDENCIAXML;
typedef vector<unsigned long> VEC_NATUREZAXML;
typedef vector<unsigned long> VEC_TPCLIENTEXML;
typedef vector<unsigned long> VEC_GRUPOXML;
typedef vector<unsigned long> VEC_CANALXML;
typedef vector<unsigned long> VEC_REGIONALXML;
typedef vector<unsigned long> VEC_TPLINHAXML;
typedef vector<unsigned long> VETOR_IDS;

class cInclPerfil
{
    DOMNode * entrada;
    XMLGen  * saida;

    TuxHelper tx;

    public:
        cInclPerfil( char *pUser, DOMNode * dnode, XMLGen * xml_g );
        short Processa( void );
        void  InsereNomePerfil( void );
        int   IncluirVariaveis( void );
        int   AlterarPerfil( void );
        int   AlterarVariaveis( void );
        bool  Diferencas( VETOR_IDS * idXML,VETOR_IDS * idBDados );


    private:
        unsigned long      idPerfilXML;
        unsigned long      idUser;
        int                inOperacao;
        int                status;
        char               sNomePerfil[256];
        VEC_PERFIL         pDados;
        VEC_VARIAVEL       pDadosVar;
        VEC_TPLINHAXML     idTpLinhaXML;
        VEC_SEGMENTOXML    idSegmentacaoXML;
        VEC_CARTEIRAXML    idCarteiraXML;
        VEC_PROCEDENCIAXML idProcedenciaXML;
        VEC_NATUREZAXML    idNaturezaXML;
		VEC_GRUPOXML	   idGrupoXML;
        VEC_TPCLIENTEXML   idTipoClienteXML;
        VEC_CANALXML       idCanalXML;
        VEC_REGIONALXML    idUFOperadoraXML;
        void  CarregaPerfil( void );
        short ValidaPerfil( void );
        int   ValidaNomePerfil( void );
        void  carregaDados( void );
        int   IncluirPerfil( void );
        int   AlteraNomePerfil( void );

		int TotalVariaveisXMLIN( void );
		int HabilitaDesabilita( void );

};
