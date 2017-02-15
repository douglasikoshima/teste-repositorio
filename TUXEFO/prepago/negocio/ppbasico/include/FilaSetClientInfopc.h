///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Prepago
 * @usecase FilaSetClientInfo
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1.2.3 $
 * @CVS     $Author: a5114878 $ - $Date: 2009/09/29 18:09:18 $
 **/
///////////////////////////////////////////////////////////////////////////////

#ifndef FILASETCLIENTINFOPCH
#define FILASETCLIENTINFOPCH

class CFilaSetClientInfopc
{

    public:
        void proCIncluiFilaSetClientInfo(TFilaSetClientInfo *ptFilaSetClientInfo);
        void proCIncluiFilaSetClientInfo_2( TFilaSetClientInfo *ptFilaSetClientInfo );
        void proCAtualizaXmlFilaSetClientInfo(TFilaSetClientInfo *ptFilaSetClientInfo);
        void proCAtualizaXmlFilaSetClientInfo_2(TFilaSetClientInfo *ptFilaSetClientInfo);
        bool proCExisteFilaSetClientInfo(TFilaSetClientInfo *ptFilaSetClientInfo);
        bool proCExisteFilaSetClientInfo_2(TFilaSetClientInfo *ptFilaSetClientInfo);
};

#endif
