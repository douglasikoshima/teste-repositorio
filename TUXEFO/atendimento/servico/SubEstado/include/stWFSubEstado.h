#ifndef STWFSUBESTADO
	#define STWFSUBESTADO

struct st_WFSubEstado
{
	int  idSubEstado;
	int  idEstado;
	char sgSubEstado[256];
	char dsSubEstado[256];
} ;

struct st_vlWFSubEstado
{
	short idSubEstado;
	short idEstado;
	short sgSubEstado;
	short dsSubEstado;
} ;

#endif

