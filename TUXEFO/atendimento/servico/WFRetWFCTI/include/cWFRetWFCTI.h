/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:38 $
 **/

#include <tuxfw.h>
#include "../../../commons/SmallString.h"

#include "stWFRetWFCTI.h"
#include "classdef.h"

class cWFRetWFCTI : public TuxBasicSvc
{
    public:
        cWFRetWFCTI() {entrada=0; saida=0;}
        cWFRetWFCTI(DOMNode* entrada,XMLGen* saida);
        ~cWFRetWFCTI() {}

    public:
        bool Executar();
        inline void setarUser(int valor) { user = valor; }
        inline void setarUser() { char *valor=getUser(); user = valor?atoi(valor):0; }
        inline int obterUser() { return user; }

        inline void setarUUID(int valor) { UUID = valor; }
        inline void setarUUID() { char *uuid=getUUID(); UUID = uuid?atoi(uuid):0; }
        inline int obterUUID() { return UUID; }

        void SetarErro(SmallString *ce,SmallString *me) { if (ce) codErro=*ce;msgErro=*me; }
        void SetarErro(char *ce,char *me) {if (ce) codErro=ce;msgErro=me; }

        inline char *ObterMsgErro() { return (char*)msgErro; }
        inline int ObterTamMsgErro() { return msgErro.Size(); }
        inline char *ObterCodErro() { return (char*)codErro; }
        inline int ObterTamCodErro() { return codErro.Size(); }

        bool Inserir();
        bool Delete();
        bool Alterar();
        bool ConsultarPorUsuario(int idUsuario,int idGrupo);
        bool ConsultarPorGrupo(int idUsuario,int idGrupo);
        bool ConsultarCTI( int idUsuario,int idGrupo,XMLGen * xml_g );
        void SelectByUserCTI( XMLGen * pxml );



    public:
        DOMNode* entrada;
        XMLGen*  saida;

        TuxHelper tx;

    private:
        int user;
        int UUID;

        SmallString codErro;
        SmallString msgErro;
} ;
