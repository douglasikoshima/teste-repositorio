///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  PrePago
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:15 $
 **/
///////////////////////////////////////////////////////////////////////////////

// #ifndef PSQPFDOCDDSDOCPCH
// #define PSQPFDOCDDSDOCPCH

/*******************************************************************************
 * Dados do(s) documento(s) da pessoa encontrada
 *******************************************************************************/
struct DadosPFDocumento {
    char oszDSTIPODOCUMENTO[LEN_DSTIPODOCUMENTO+LEN_EOS];
    char oszNRDOCUMENTO[LEN_NRDOCUMENTO+LEN_EOS];
    char oszDTEMISSAO[LEN_CAMPO_TEXTO_PADRAO_FO+LEN_EOS];
    char oszSGORGAOEXPEDIDOR[LEN_SGORGAOEXPEDIDOR+LEN_EOS];
    char oszSGUF[LEN_SGUF+LEN_EOS];
};

struct StatusPFDocumento {
    short iDSTIPODOCUMENTO;
    short iNRDOCUMENTO;
    short iDTEMISSAO;
    short iSGORGAOEXPEDIDOR;
    short iSGUF;
};

// #endif
