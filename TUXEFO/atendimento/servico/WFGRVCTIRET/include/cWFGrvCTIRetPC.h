
#undef SQLCA
#define SQLCA_NONE
#include <sqlca.h>
#include <sqlda.h>
#include <ctype.h>
#include <tuxfw.h>

#include "st_VariaveisGrvCTIRet.h"
#include"../../../commons/queryMacro.h"
#include "../../../commons/Collection/include/Collection.h"
#include "../../../commons/msgPadrao.h"
#include "classdef.h"


class cWFGrvCTIPC
{
	public:
        bool verificaItem( stVariaveisGrvCTI *_dadosEntradaPC );
        bool InsertWFRet( char *Msg );
        bool Update( char *Msg );
        void DeleteByIDWFGRet( void );
        void DeleteWFRet( void );
        void InsertWFGRet( void );
        void SelectGroupsByRetWFCTI( stVariaveisGrvCTI *, Collection * );
        retornoWFCTI rwfcti;

   private:
        void sql_error_WFRetornoCTI( sqlca * sqlca );
        void SelectGroupsPdrCam( stVariaveisGrvCTI * _dadosEntradaPC, Collection * _ResultadoPC );
        void SelectGroupsNPdCam( stVariaveisGrvCTI * _dadosEntradaPC, Collection * _ResultadoPC );
        void SelectGroupsPdrTodos( stVariaveisGrvCTI * _dadosEntradaPC, Collection * _ResultadoPC );
        void SelectGroupsNPdTodos( stVariaveisGrvCTI * _dadosEntradaPC, Collection * _ResultadoPC );
};

