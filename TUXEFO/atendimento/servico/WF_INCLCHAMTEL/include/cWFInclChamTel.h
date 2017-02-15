#include <tuxfw.h>
#include "../../../commons/SmallString.h"


class cWFInclChamTel
{
   
    public:
        char *gerarIDDom() { static int id=0; static char stID[32]; sprintf(stID,"ID_%d",++id); return stID; }
        void GetTime( char *sData, int iFormato );
        void AtualizaGrupoAtual( unsigned long idChamadaParam );
		void AtualizaChamada( unsigned long idChamadaParam, int idGrauParam );

};