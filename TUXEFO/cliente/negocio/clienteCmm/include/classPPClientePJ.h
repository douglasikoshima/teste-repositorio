#ifndef CLASSCSTVALIDAPREATV
#define CLASSCSTVALIDAPREATV

#define SYS_DEFAULT "2"

#include "Global.h"

EXEC SQL BEGIN DECLARE SECTION;

typedef struct
{
	VARCHAR		sNrTelefone		[LEN_NUMBER + LEN_EOS];
	VARCHAR		sDDDTelefone	[LEN_NUMBER + LEN_EOS];
	VARCHAR		sSistemaOrigem	[LEN_NUMBER + LEN_EOS];
	VARCHAR		sDigito			[LEN_NUMBER + LEN_EOS];

	short		iNrTelefone;
	short		iDDDTelefone;
	short		iSistemaOrigem;
	short		iDigito;

} TVALIDAPREATV;

EXEC SQL END DECLARE SECTION;
/*
class classPPValPreAtv
{
public:
     classPPValPreAtv();
    ~classPPValPreAtv(){};

public:
     void setNrTelefone			(char*);
	 void setSistemaOrigem		(char*); 
     void setUsuarioAlteracao	(char*);
	 void setSistemaDefault();
	 char * getDigito();
     int Buscar();

private:
    EXEC SQL BEGIN DECLARE SECTION;
        TVALIDAPREATV    tValida;
    EXEC SQL END DECLARE SECTION;
};
*/

#endif
