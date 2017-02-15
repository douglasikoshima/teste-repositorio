/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:41 $
 **/

#ifndef STWFATDASSOCDOCTE
    #define STWFATDASSOCDOCTE

struct st_AtdAssocDocTe
{
    char dataAtual[256];
};

struct DadosDocTecnico
{
    int idDocumentoTecnico;
    string nrDocumento;
    string dsDocumento;
    string ComentarioFechamento;
    string dsDocumentotecnicoTipo;

    DadosDocTecnico() { idDocumentoTecnico=0; }
};


#endif
