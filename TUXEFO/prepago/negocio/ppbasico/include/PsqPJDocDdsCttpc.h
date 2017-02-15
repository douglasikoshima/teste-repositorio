///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  PrePago
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:15 $
 **/
///////////////////////////////////////////////////////////////////////////////

/*******************************************************************************
 * Dados básicos da pessoa (linha pré ou pós)
 *******************************************************************************/
struct DadosPessoaJuridicaPorDoc {
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

