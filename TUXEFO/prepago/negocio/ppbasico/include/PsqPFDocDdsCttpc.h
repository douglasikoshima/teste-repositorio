///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  PrePago
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:15 $
 **/
///////////////////////////////////////////////////////////////////////////////

// #ifndef PSQPFDOCDDSCTTPCH
// #define PSQPFDOCDDSCTTPCH

/*******************************************************************************
 * Dados básicos da pessoa (linha pré ou pós)
 *******************************************************************************/
struct DadosPessoaFisicaPorDoc {
    char oszIDPESSOA[LEN_IDPESSOA+LEN_EOS];
    //char oszNMPESSOA[LEN_NMPESSOA+LEN_EOS];
    //char oszIDSEXO[LEN_IDSEXO+LEN_EOS];
    //char oszDSSEXO[LEN_DSSEXO+LEN_EOS];
    //char oszDTNASCIMENTO[LEN_CAMPO_TEXTO_PADRAO_FO+LEN_EOS];
    //char oszNRTELEFONE[LEN_CAMPO_TEXTO_PADRAO_FO+LEN_EOS];
    //char oszDSESTADOCIVIL[LEN_DSESTADOCIVIL+LEN_EOS];
    //char oszESCOLARIDADE[LEN_CAMPO_TEXTO_PADRAO_FO+LEN_EOS];
    //char oszNATUREZA_OCUPACAO[LEN_CAMPO_TEXTO_PADRAO_FO+LEN_EOS];
    //char oszDSCARGOCONTATO[LEN_DSCARGOCONTATO+LEN_EOS];
    char oszIDLINHATELEFONICA[LEN_IDLINHATELEFONICA+LEN_EOS];
    //char oszCPR[LEN_CAMPO_TEXTO_PADRAO_FO+LEN_EOS];
};

/*******************************************************************************
 * Contatos declarados
 *******************************************************************************/
struct DadosPFContato {
    char oszIDPESSOACOMUNICACAO[LEN_IDPESSOACOMUNICACAO+LEN_EOS];
    char oszDSCONTATO[LEN_DSCONTATO+LEN_EOS];
    char oszNMCONTATO[LEN_NMCONTATO+LEN_EOS];
    char oszIDTIPOCOMUNICACAO[LEN_IDTIPOCOMUNICACAO+LEN_EOS];
    char oszSGTIPOCOMUNICACAO[LEN_SGTIPOCOMUNICACAO+LEN_EOS];
    char oszDSTIPOCOMUNICACAO[LEN_DSTIPOCOMUNICACAO+LEN_EOS];
    char oszIDFORMARETORNO[LEN_IDFORMARETORNO+LEN_EOS];
    char oszDTCADASTRO[LEN_CAMPO_TEXTO_PADRAO_FO+LEN_EOS];
};

struct StatusPFContato {
    short iIDPESSOACOMUNICACAO;
    short iDSCONTATO;
    short iNMCONTATO;
    short iIDTIPOCOMUNICACAO;
    short iSGTIPOCOMUNICACAO;
    short iDSTIPOCOMUNICACAO;
    short iIDFORMARETORNO;
    short iDTCADASTRO;
};

/*******************************************************************************
 * Dados do(s) documento(s) da pessoa encontrada
 *******************************************************************************/
struct DadosPFDocumento {
    char oszIDDOCUMENTO[LEN_IDDOCUMENTO+LEN_EOS];
    char oszIDTIPOPESSOA[LEN_IDTIPOPESSOA+LEN_EOS];
    char oszNRPRIORIDADE[LEN_NRPRIORIDADE+LEN_EOS];
    char oszIDTIPODOCUMENTO[LEN_IDTIPODOCUMENTO+LEN_EOS];
    char oszNRDOCUMENTO[LEN_NRDOCUMENTO+LEN_EOS];
    char oszSGCLASSIFICACAO[LEN_SGCLASSIFICACAO+LEN_EOS];
    char oszDSTIPODOCUMENTO[LEN_DSTIPODOCUMENTO+LEN_EOS];
    char oszDTEMISSAO[LEN_CAMPO_TEXTO_PADRAO_FO+LEN_EOS];
    char oszSGORGAOEXPEDIDOR[LEN_SGORGAOEXPEDIDOR+LEN_EOS];
    char oszIDUF[LEN_IDUF+LEN_EOS];
};

struct StatusPFDocumento {
    short iIDDOCUMENTO;
    short iIDTIPOPESSOA;
    short iNRPRIORIDADE;
    short iIDTIPODOCUMENTO;
    short iNRDOCUMENTO;
    short iSGCLASSIFICACAO;
    short iDSTIPODOCUMENTO;
    short iDTEMISSAO;
    short iSGORGAOEXPEDIDOR;
    short iIDUF;
};

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

/*******************************************************************************
 * Email particular da pessoa
 *******************************************************************************/
struct DadosPFEMailParticular {
    char oszDSCONTATO[LEN_DSCONTATO+LEN_EOS];
    char oszDTCADASTRO[LEN_CAMPO_TEXTO_PADRAO_FO+LEN_EOS];
};

struct StatusPFEMailParticular {
    short iDSCONTATO;
    short iDTCADASTRO;
};


// #endif