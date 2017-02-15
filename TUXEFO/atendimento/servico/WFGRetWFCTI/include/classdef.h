#ifndef CLASSDEF
#define CLASSDEF

#undef SQLCA
#define SQLCA_NONE
#include<sqlca.h>
#include<sqlda.h>

#ifndef PROC
#include "../../../commons/routerLib/include/RouterClient.h"
#endif

#define GRUPORETORNOWFCTIVO "GrupoRetornoWFCTIVO"
#define IDGRUPORETORNOWFCTI "idGrupoRetornoWFCTI"
#define IDRETORNOWFCTI "idRetornoWFCTI"
#define IDUSUARIOGRUPO "idUsuarioGrupo"
#define INPADRAO "inPadrao"

#define POPULATE_INT(A,B,C)	\
{char*__PPURT;__PPURT=walkTree(A,B,0);if(__PPURT){C=atoi(__PPURT);XMLString::release(&__PPURT);}else C=-1;}
#define INITIALIZE_SQL		struct sqlca sqlca;sqlca.sqlcode=0
#define FROM_STR_TO_VARCHAR(A,B)	\
{A.len=B.len;memcpy((char*)A.arr,B.arr,A.len);}

struct grupoRetornoWFCTI
{
	int idgruporetornowfcti;
	int idretornowfcti;
	int idusuariogrupo;
	int idusuarioalteracao;
	int inpadrao;
};

extern int usrID;

class GrupoRetornoWFCTI:public TuxHelper
{
public:
	GrupoRetornoWFCTI(DOMNode*,XMLGen*);
	void Insert();
	void Delete();
	void DeleteByID();
	void Update();
	void SelectGroupsByRetWFCTI();
	void SelectGroupsPdrCam();  // Obtem grupos de campnhas padrão indicado campanha.
	void SelectGroupsNPdCam();  // Obtem grupos de campnhas NÃO padrão indicado campanha.
	void SelectGroupsPdrTodos(); // Retorna todas campanhas padrão
	void SelectGroupsNPdTodos(); // Retorno todas campanhas NÃO padrão.
private:
	DOMNode*pdom;
	XMLGen*pxml;
	grupoRetornoWFCTI grwfcti;
	void sql_error_PesquisaGrupos(sqlca*sqlca);
};


#endif
