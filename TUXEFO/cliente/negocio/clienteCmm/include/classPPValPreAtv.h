//---------------------------------------------------------------------------
#ifndef CLASSCSTVALIDAPREATV
#define CLASSCSTVALIDAPREATV

#define SYS_DEFAULT "7"
#include "Global.h"

EXEC SQL BEGIN DECLARE SECTION;

//---------------------------------------------------------------------------

typedef struct
{
	VARCHAR		sNrTelefone			[LEN_NUMBER + LEN_EOS];
	VARCHAR		sDDDTelefone		[LEN_NUMBER + LEN_EOS];
	VARCHAR		sSistemaOrigem		[LEN_NUMBER + LEN_EOS];
	VARCHAR		sDigito				[LEN_NUMBER + LEN_EOS];
	VARCHAR		sidLinhaTelefonica	[LEN_NUMBER + LEN_EOS];
	VARCHAR		sidPessoa			[LEN_NUMBER + LEN_EOS];
	VARCHAR		sTipoPessoa			[LEN_NUMBER + LEN_EOS];

	short		iNrTelefone;
	short		iidLinhaTelefonica; 
	short		iDDDTelefone;
	short		iSistemaOrigem;
	short		iDigito;
	short		iTipoPessoa;

} TVALIDAPREATV;

EXEC SQL END DECLARE SECTION;

//---------------------------------------------------------------------------

class classPPValPreAtv
{
public:
     classPPValPreAtv();
    ~classPPValPreAtv(){};

public:
     void  setNrTelefone		(char*);
     void  setSistemaOrigem		(char*); 
     void  setUsuarioAlteracao	(char*);
	 void  setSistemaDefault();
	 //----------------------------------------------------------------------
     char *getIdPessoa(); 
	 char *getDigito();
     char *getIdTipoPessoa(); 
     char *getIdLinhaTelefonica(); 
	 //----------------------------------------------------------------------
     int   BuscaValPreAtv();
	 char  sidpessoa[255]; 
	 //---------------------------------------------------------------------------
     EXEC SQL BEGIN DECLARE SECTION;
        TVALIDAPREATV    tValida;
     EXEC SQL END DECLARE SECTION;
	 //---------------------------------------------------------------------------
};


#endif
