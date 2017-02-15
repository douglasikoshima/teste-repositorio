#ifndef _CDEFERROH_
#define _CDEFERROH_

typedef struct SERRO 
{
	char Codigo	[16];
	char Descricao	[64]; 
	char Campo	[64];
	int Status; 
} stErro; 


#define EOK				0
#define EIDCAMPANHA		1
#define EIDSUBCAMPANHA	2
#define EIDVERSAO		3
#define EDTINICIO		4
#define EDTFIM			5


#endif
