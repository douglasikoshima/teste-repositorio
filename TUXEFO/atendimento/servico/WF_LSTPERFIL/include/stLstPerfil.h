#ifndef STWFLSTPERFIL
    #define STWFLSTPERFIL

#ifndef NUM_LINHAS
#define NUM_LINHAS 100
#endif

struct st_LstPerfil
{
    int  idPerfil;
    char nmPerfil[256];
    int  associado;
    int  habilitado;
    int  bloco;
    int qtdLinhasBloco;
} ;

struct st_vlLstPerfil
{
    short idPerfil;
    short nmPerfil;
    short associado;
    short habilitado;
    short bloco;
    short qtdLinhasBloco;
} ;

#endif

