///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  PrePago
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:15 $
 **/
///////////////////////////////////////////////////////////////////////////////

// #ifndef PSQPFDOCDDSEMCOMPCH
// #define PSQPFDOCDDSEMCOMPCH

/*******************************************************************************
 * Email comercial da pessoa
 *******************************************************************************/
struct DadosPFEMailComercial {
    char oszDSCONTATO[LEN_DSCONTATO+LEN_EOS];
    char oszDTCADASTRO[LEN_CAMPO_TEXTO_PADRAO_FO+LEN_EOS];
};

struct StatusPFEMailComercial {
    short iDSCONTATO;
    short iDTCADASTRO;
};

// #endif
