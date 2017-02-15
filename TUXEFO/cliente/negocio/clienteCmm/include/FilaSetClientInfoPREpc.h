///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Prepago
 * @usecase FilaSetClientInfo
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1.130.1 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/09/04 21:55:49 $
 **/
///////////////////////////////////////////////////////////////////////////////

#ifndef FILASETCLIENTINFOPREPCH
#define FILASETCLIENTINFOPREPCH

class CFilaSetClientInfopc
{

    public:
        void proCIncluiFilaSetClientInfo(TFilaSetClientInfoPRE *ptFilaSetClientInfo);
        void proCIncluiFilaSetClientInfo_4(TFilaSetClientInfoPRE *ptFilaSetClientInfo);
        void proCAtualizaXmlFilaSetClientInfo(TFilaSetClientInfoPRE tFilaSetClientInfo);
        bool proCExisteFilaSetClientInfo(TFilaSetClientInfoPRE tFilaSetClientInfo);
};

#endif
