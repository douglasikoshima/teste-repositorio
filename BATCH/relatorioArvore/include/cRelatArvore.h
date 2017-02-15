
#include <stdio.h>
#include <time.h>
#include <stdlib.h>
#include <fcntl.h>
#include <sys/types.h>
#include <iterator>
#include <vector>
#include <list>
#include <string>
#include <stdarg.h>

#ifndef WIN32
    #include <dirent.h>
    #include <sys/stat.h>
    #include <unistd.h>
#else
    #include <io.h>
    #include <tuxfw.h>
#endif

#include <string.h>


#include "stRelatArvore.h"
#include "agcData.h"
#include "cTrace.h"
#include "../../commons/LerDados/include/Propriedade.h"
#include "../../commons/Log/include/Log.h"
#include "../../commons/CFile/include/CFile.h"

// Separador utlizado na geração do arquivo de saída
#define SEPARADOR ";"

// Separador utilizado nas clausulas IN() do SQL
#define SEPARADOR_IN ","

#define  OK  1
#define NOK  0


/*----------------------------------------------------------------------*/
// Usados para busca de arquivos de solicitacao
/*----------------------------------------------------------------------*/
#define LEN_DATA                    14
#define QTD_ARQ_TRATADOS            10
using namespace std;


struct ContatoGrupo
{
  string nmContato;
  string nmGrupoCoreto;
  string nmFaseCorreto;
  string nmGrupoIncorreto;
  string nmFaseIncorreto;
} ;


typedef vector<struct ContatoGrupo> VEC_CONTATOGRUPO;
typedef list<unsigned long> LIST_PK;


class cRelatorioArvore
{
	st_vlRelatorioArvore		status;

    public:
        bool fimRegistros;
        int debugOn;
        char sDiretorioSaida[512];
        char sDiretorioEntrada[512];
        char sLogin[32];
        char sTipoConsulta[16];
        char sArquivoConfiguracao[512];
    	char sArquivoSaida[256];
        cTrace* log;
        cRelatorioArvore( char * sArquivoConfiguracao );
        bool Executa( void );
        bool WriteArquivoSaida( const char* sRegistro );
        bool Cabecalho( bool flag_OK );
        bool CarregaVariaveis( void );
        bool Conectar( void );
        void Desconectar( void );
        void ObtemFolhasContatoGrupos( LIST_PK* vecContato , VEC_CONTATOGRUPO* vecContatoGrupo );
        void OrganizaArquivoSaida( void );
        

    private:
        int fd;
        int TipoRelatorio;
        int inDisponibilidade;
        int TempoLimiteArqSaida;

        LIST_PK       pContato_OK;
        LIST_PK       pContato_NOK;

        LIST_PK       pGrupo_OK;
        LIST_PK       pGrupo_NOK;

        LIST_PK       vecGrupo;
        LIST_PK       vecGrupoRelContato;

        LIST_PK       pTipoCliente;
        LIST_PK       pTipoLinha;
        LIST_PK       pSegmentacao;
        LIST_PK       pTipoCarteira;
        LIST_PK       pRegional;
        LIST_PK       pNatureza;
        LIST_PK       pCanal;
        LIST_PK       pFechamento;

        LIST_PK       pGrupoTratamento;
        LIST_PK       pGrupoAbertura;
        LIST_PK       pGrupoRetorno;

        LIST_PK       pProcedencia;
        VEC_CONTATOGRUPO       pContatoGrupo_OK;
        VEC_CONTATOGRUPO       pContatoGrupo_NOK;

        bool LeArquivoCnf( const char* sNomeCnf );
        bool ObtemFolhasContato( void );
        bool GeraRelatorio( void );
        bool DetalheRegistros( void );
        bool RelatorioContato( void );
        bool OpenArquivoSaida( void );
        void CloseArquivoSaida( void );
        char* format_time( time_t cal_time );
        int  GetFileProc( void );
        bool LimpaAmbiente( void );

        bool configuracaoCompleta;
        bool conectado;
        string szConnStr;
        char* szPws;
        char* szUsr;
        char* szPath;
        char* szInst;
        int vlLog;
        CFile   oCFile ;
        char msgLog[512];

        bool ObtemGrupo( void );
        bool RelatorioGrupo( void );

        string listaGrupo ;
        void ObtemFolhasContatoGrupos( LIST_PK* vecContato, LIST_PK* vecContatoGrupo );
        void RelatorioContatoGrupo( VEC_CONTATOGRUPO* vetContatoGrupo_OK, VEC_CONTATOGRUPO* vetContatoGrupo_NOK );
	
};

