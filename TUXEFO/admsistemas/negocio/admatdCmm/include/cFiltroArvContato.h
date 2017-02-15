                     
                     

#include "tuxfw.h"
using namespace std;
#include <iterator>
#include <list>
#include <vector>
#include <string>


struct st_FiltroArvContato
{
   char nmTipoRelatorio[75];
   char sgDisponivel[2];
   char nmArquivo[81];
   char urlDownload[51];
   char dtSolicitacao[32];
   char dtGeracao[32];
   long idUsuario;
   int  idTipoLinha;
   int  idSegmentacao;
   int  idTipoCliente;
   int  idTipoCarteira;
   int  idRegional;
   int  idNatureza;
   int  idCanal;
   int  idTipoFechamento;
   int  idGrupoAbertura;
   int  idGrupoTratamento;
   int  idGrupoRetorno;
   int  idProcedencia;
   int  idTipoRelatorio;
};

typedef list<string> LISTA_STMT;
typedef vector<int> LISTA_ID;


class CFiltroArvContato
{

    public:
        void Insere( char *idUser, DOMNode *,st_FiltroArvContato *pRegistro ,XMLGen *xml_g );
        void Selecao( char *idUser,  DOMNode *dnode,st_FiltroArvContato *pRegistro ,XMLGen *xml_g );
        void SelecaoArquivos( char *idUser, DOMNode *dnode, st_FiltroArvContato *pRegistro ,XMLGen *xml_g );
        void SetTipoRelatorio( st_FiltroArvContato *pRegistro );
        TuxHelper tx;


    private:
        DOMNode *entrada;
        XMLGen  *saida;

        DOMNode *dnBusca;

};
