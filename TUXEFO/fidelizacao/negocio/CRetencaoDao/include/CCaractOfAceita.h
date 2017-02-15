//////////////////////////////////////////////////////////////////////
// CCaractOfAceita.h: interface for the CRelatorioGerenciamento class.
//////////////////////////////////////////////////////////////////////

#ifndef CCaractOfAceitaHH
#define CCaractOfAceitaHH


#include "utilretencao.h"

struct stCaractOferta
{
    int idUsuario;
    int idOfertaRealizada;
    char domCaractOferta[256];
    char vlPropriedade[256];
    char sgCaractOferta[256];
};


class CCaractOfAceita  
{

public:
    int Inserir( DOMNode *pdnode, char * idUser, char *m_idOfertaRealizada, char *sgOferta );

private:

};

#endif