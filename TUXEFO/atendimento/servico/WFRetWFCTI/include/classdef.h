#ifndef CLASSDEF
#define CLASSDEF

#ifndef PROC
//#include "../../../../../TuxCli/RouterClient.h"
#include "../../../commons/routerLib/include/RouterClient.h"
#endif

#define RETORNOWFCTIVO "RetornoWFCTIVO"
#define IDRETORNOWFCTI "idRetornoWFCTI"
#define SGRETORNOWFCTI "sgRetornoWFCTI"
#define DSRETORNOWFCTI "dsRetornoWFCTI"
#define SGSTATUS "sgStatus"
#define INPADRAO "inPadrao"

#define POPULATE_STR(A,B,C,D)	\
{char*__PPURT;__PPURT=walkTree(A,B,0);if(__PPURT){C.len=strlen(__PPURT);if(C.len>D){XMLString::release(&__PPURT);throw new TuxBasicSvcException("00E9999","POPULATE_STR:Area destino insuficiente");}memcpy(C.arr,__PPURT,C.len);XMLString::release(&__PPURT);}}
#define POPULATE_INT(A,B,C)	\
{char*__PPURT;__PPURT=walkTree(A,B,0);if(__PPURT){C=atoi(__PPURT);XMLString::release(&__PPURT);}else C=-1;}
#define INITIALIZE_SQL		struct sqlca sqlca;sqlca.sqlcode=0
#define FROM_STR_TO_VARCHAR(A,B)	\
{A.len=B.len;memcpy((char*)A.arr,B.arr,A.len);}

struct retornoWFCTI
{
	int idretornowfcti;
	struct{char arr[255];short len;}sgretornowfcti;
	struct{char arr[255];short len;}dsretornowfcti;
	struct{char arr[1];short len;}sgstatus;
	int inpadrao;
	int idusuarioalteracao;
	int idgrupo;
};

extern int usrID;

class RetornoWFCTI:public TuxHelper
{
public:
	RetornoWFCTI(DOMNode*,XMLGen*);
	RetornoWFCTI(int* idUsuario, int* idGrupo, DOMNode*dom,XMLGen*xml);

public:
	void Insert();
	void Delete();
	void Update();
    void SetarUsuario(int idUsuario) { rwfcti.idusuarioalteracao = idUsuario; }
    void SetarGrupo(int idGrupo) { rwfcti.idgrupo = idGrupo; }
	void SelectByUser();
	void SelectByGroup();
    void SetarUsuario();
    void SelectByGroup(int idGrupo);
    void SelectByUser(int idUser);
    void SelectByUserCTI( XMLGen * pxml );


private:
	DOMNode*pdom;
	XMLGen*pxml;
	retornoWFCTI rwfcti;
};

#endif
