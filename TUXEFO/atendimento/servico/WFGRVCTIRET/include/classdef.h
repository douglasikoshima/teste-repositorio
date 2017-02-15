#ifndef CLASSDEF
#define CLASSDEF

#ifndef PROC
//#include"RouterClient.h"
#endif

#define GRUPORETORNOWFCTIVO "GrupoRetornoWFCTIVO"
#define IDGRUPORETORNOWFCTI "idGrupoRetornoWFCTI"
#define RETORNOWFCTIVO      "RetornoWFCTIVO"
#define IDRETORNOWFCTI      "idRetornoWFCTI"
#define SGRETORNOWFCTI      "sgRetornoWFCTI"
#define DSRETORNOWFCTI      "dsRetornoWFCTI"
#define SGSTATUS            "sgStatus"
#define INPADRAO            "inPadrao"

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
    int idgruporetornowfcti;
    struct{char arr[255];short len;}sgretornowfcti;
    struct{char arr[255];short len;}dsretornowfcti;
    struct{char arr[2];short len;}sgstatus;
    int inpadrao;
    int idusuarioalteracao;
    int idgrupo;
    int idusuariogrupo;

    retornoWFCTI()
    {
        this->idretornowfcti = 0;
        this->idgruporetornowfcti = 0;
        this->inpadrao = 0;
        this->idusuarioalteracao = 0;
        this->idgrupo = 0;
        this->idusuariogrupo = 0;

        memset(this->sgretornowfcti.arr,0,sizeof(this->sgretornowfcti.arr));
        this->sgretornowfcti.len = 0;

        memset(this->dsretornowfcti.arr,0,sizeof(this->dsretornowfcti.arr));
        this->dsretornowfcti.len = 0;

        memset(this->sgstatus.arr,0,sizeof(this->sgstatus.arr));
        this->sgstatus.len = 0;
    }
};

extern int usrID;

class RetornoWFCTI:public TuxHelper
{
public:
	RetornoWFCTI(DOMNode*,XMLGen*);
	RetornoWFCTI(int* idUsuario, int* idGrupo, DOMNode*dom,XMLGen*xml);
	void Insert();
	void Delete();
	void Update();
	void SelectByUser();
	void SelectByGroup();
private:
	DOMNode*pdom;
	XMLGen*pxml;
	retornoWFCTI rwfcti;
};

#endif
