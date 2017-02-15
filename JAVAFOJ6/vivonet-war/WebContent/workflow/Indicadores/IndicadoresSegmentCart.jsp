<!--
Módulo.....: Gestão de Processos
Caso de Uso: Indicadores Segmentação e Carterização
Versão.....: $Author: a5112274 $ - $Revision: 1.1 $ - $Date: 2011/04/25 19:36:41 $
-->
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>

<!--Segmentação e Carterização-->
<vivo:quadro id="qdSegmentacao" height="355" width="775" label="&nbsp;Avaliação por Segmentação e Carteira" scroll="N">
    <vivo:tbTable selectable="false" layoutWidth="750" layoutHeight="325" tableWidth="750" styleId="tdSegmentacoes" sortable="true">
        <vivo:tbHeader>
            <vivo:tbHeaderColumn align="center" width="8%" type="string">Ordem</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="center" width="10%" type="string">Regional</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="center" width="82%" type="string">Detalhe Regional</vivo:tbHeaderColumn>
        </vivo:tbHeader>
        <vivo:tbRows>
            <vivo:tbRow key="linha1" zebrar="S">
                <vivo:tbRowColumn><b>1</b></vivo:tbRowColumn>
                <vivo:tbRowColumn><b>SP</b></vivo:tbRowColumn>
                <vivo:tbRowColumn>
                    <vivo:tbTable selectable="false" layoutWidth="598" layoutHeight="300" tableWidth="598" styleId="tdSegmentacoesDet1" sortable="true">
                        <vivo:tbHeader>
                            <vivo:tbHeaderColumn align="center" width="25%" type="string">Grupo</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="center" width="75%" type="string">Detalhe</vivo:tbHeaderColumn>
                        </vivo:tbHeader>
                        <vivo:tbRows>
                            <vivo:tbRow key="linha1" zebrar="S">
                                <vivo:tbRowColumn><b>Grupo 1</b></vivo:tbRowColumn>
                                <vivo:tbRowColumn>
                                    <vivo:tbTable selectable="false" layoutWidth="430" layoutHeight="60" tableWidth="430" styleId="tdSegmentacaoGruposDet1" sortable="true">
                                        <vivo:tbHeader>
                                            <vivo:tbHeaderColumn align="center" width="10%" type="string">Ordem</vivo:tbHeaderColumn>
                                            <vivo:tbHeaderColumn align="center" width="40%" type="string">Segmentação/Carteira</vivo:tbHeaderColumn>
                                            <vivo:tbHeaderColumn align="center" width="20%" type="string">Quantidade</vivo:tbHeaderColumn>
                                            <vivo:tbHeaderColumn align="left"   width="30%" type="string">Percentual</vivo:tbHeaderColumn>
                                        </vivo:tbHeader>
                                        <vivo:tbRows>
                                            <vivo:tbRow key="linha1">
                                                <vivo:tbRowColumn>1</vivo:tbRowColumn>
                                                <vivo:tbRowColumn>Diamante ***/Soho</vivo:tbRowColumn>
                                                <vivo:tbRowColumn>1</vivo:tbRowColumn>
                                                <vivo:tbRowColumn>*</vivo:tbRowColumn>
                                            </vivo:tbRow>
                                            <vivo:tbRow key="linha1">
                                                <vivo:tbRowColumn>2</vivo:tbRowColumn>
                                                <vivo:tbRowColumn>Diamante **/Soho</vivo:tbRowColumn>
                                                <vivo:tbRowColumn>5</vivo:tbRowColumn>
                                                <vivo:tbRowColumn>****</vivo:tbRowColumn>
                                            </vivo:tbRow>
                                            <vivo:tbRow key="linha1">
                                                <vivo:tbRowColumn><b>3</b></vivo:tbRowColumn>
                                                <vivo:tbRowColumn><b>Total Geral</b></vivo:tbRowColumn>
                                                <vivo:tbRowColumn><b>6</b></vivo:tbRowColumn>
                                                <vivo:tbRowColumn><b>**********</b></vivo:tbRowColumn>
                                            </vivo:tbRow>
                                        </vivo:tbRows>
                                    </vivo:tbTable>
                                </vivo:tbRowColumn>
                            </vivo:tbRow>
                            <vivo:tbRow key="linha1" zebrar="S">
                                <vivo:tbRowColumn><b>Grupo 2</b></vivo:tbRowColumn>
                                <vivo:tbRowColumn>
                                    <vivo:tbTable selectable="false" layoutWidth="430" layoutHeight="60" tableWidth="430" styleId="tdSegmentacaoGruposDet2" sortable="true">
                                        <vivo:tbHeader>
                                            <vivo:tbHeaderColumn align="center" width="10%" type="string">Ordem</vivo:tbHeaderColumn>
                                            <vivo:tbHeaderColumn align="center" width="40%" type="string">Segmentação/Carteira</vivo:tbHeaderColumn>
                                            <vivo:tbHeaderColumn align="center" width="20%" type="string">Quantidade</vivo:tbHeaderColumn>
                                            <vivo:tbHeaderColumn align="left"   width="30%" type="string">Percentual</vivo:tbHeaderColumn>
                                        </vivo:tbHeader>
                                        <vivo:tbRows>
                                            <vivo:tbRow key="linha1">
                                                <vivo:tbRowColumn>1</vivo:tbRowColumn>
                                                <vivo:tbRowColumn>Rubi ***/Soho</vivo:tbRowColumn>
                                                <vivo:tbRowColumn>15</vivo:tbRowColumn>
                                                <vivo:tbRowColumn>*********</vivo:tbRowColumn>
                                            </vivo:tbRow>
                                            <vivo:tbRow key="linha1">
                                                <vivo:tbRowColumn><b>2</b></vivo:tbRowColumn>
                                                <vivo:tbRowColumn><b>Total Geral</b></vivo:tbRowColumn>
                                                <vivo:tbRowColumn><b>15</b></vivo:tbRowColumn>
                                                <vivo:tbRowColumn><b>*********</b></vivo:tbRowColumn>
                                            </vivo:tbRow>
                                        </vivo:tbRows>
                                    </vivo:tbTable>
                                </vivo:tbRowColumn>
                            </vivo:tbRow>
                        </vivo:tbRows>
                    </vivo:tbTable>
                </vivo:tbRowColumn>
            </vivo:tbRow>
            
            <vivo:tbRow key="linha1" zebrar="S">
                <vivo:tbRowColumn><b>2</b></vivo:tbRowColumn>
                <vivo:tbRowColumn><b>RS</b></vivo:tbRowColumn>
                <vivo:tbRowColumn>
                    <vivo:tbTable selectable="false" layoutWidth="598" layoutHeight="300" tableWidth="598" styleId="tdSegmentacoesDet2" sortable="true">
                        <vivo:tbHeader>
                            <vivo:tbHeaderColumn align="center" width="25%" type="string">Grupo</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="center" width="75%" type="string">Detalhe</vivo:tbHeaderColumn>
                        </vivo:tbHeader>
                        <vivo:tbRows>
                            <vivo:tbRow key="linha1" zebrar="S">
                                <vivo:tbRowColumn><b>Grupo 1x</b></vivo:tbRowColumn>
                                <vivo:tbRowColumn>
                                    <vivo:tbTable selectable="false" layoutWidth="430" layoutHeight="60" tableWidth="430" styleId="tdSegmentacaoGruposDet3" sortable="true">
                                        <vivo:tbHeader>
                                            <vivo:tbHeaderColumn align="center" width="10%" type="string">Ordem</vivo:tbHeaderColumn>
                                            <vivo:tbHeaderColumn align="center" width="40%" type="string">Segmentação/Carteira</vivo:tbHeaderColumn>
                                            <vivo:tbHeaderColumn align="center" width="20%" type="string">Quantidade</vivo:tbHeaderColumn>
                                            <vivo:tbHeaderColumn align="left"   width="30%" type="string">Percentual</vivo:tbHeaderColumn>
                                        </vivo:tbHeader>
                                        <vivo:tbRows>
                                            <vivo:tbRow key="linha1">
                                                <vivo:tbRowColumn>1</vivo:tbRowColumn>
                                                <vivo:tbRowColumn>Diamante ***/Soho</vivo:tbRowColumn>
                                                <vivo:tbRowColumn>2</vivo:tbRowColumn>
                                                <vivo:tbRowColumn>*</vivo:tbRowColumn>
                                            </vivo:tbRow>
                                            <vivo:tbRow key="linha1">
                                                <vivo:tbRowColumn>2</vivo:tbRowColumn>
                                                <vivo:tbRowColumn>Diamante **/Soho</vivo:tbRowColumn>
                                                <vivo:tbRowColumn>3</vivo:tbRowColumn>
                                                <vivo:tbRowColumn>******</vivo:tbRowColumn>
                                            </vivo:tbRow>
                                            <vivo:tbRow key="linha1">
                                                <vivo:tbRowColumn><b>4</b></vivo:tbRowColumn>
                                                <vivo:tbRowColumn><b>Total Geral</b></vivo:tbRowColumn>
                                                <vivo:tbRowColumn><b>5</b></vivo:tbRowColumn>
                                                <vivo:tbRowColumn><b>**********</b></vivo:tbRowColumn>
                                            </vivo:tbRow>
                                        </vivo:tbRows>
                                    </vivo:tbTable>
                                </vivo:tbRowColumn>
                            </vivo:tbRow>
                            
                            <vivo:tbRow key="linha1" zebrar="S">
                                <vivo:tbRowColumn><b>Grupo 2x</b></vivo:tbRowColumn>
                                <vivo:tbRowColumn>
                                    <vivo:tbTable selectable="false" layoutWidth="430" layoutHeight="60" tableWidth="430" styleId="tdSegmentacaoGruposDet4" sortable="true">
                                        <vivo:tbHeader>
                                            <vivo:tbHeaderColumn align="center" width="10%" type="string">Ordem</vivo:tbHeaderColumn>
                                            <vivo:tbHeaderColumn align="center" width="40%" type="string">Segmentação/Carteira</vivo:tbHeaderColumn>
                                            <vivo:tbHeaderColumn align="center" width="20%" type="string">Quantidade</vivo:tbHeaderColumn>
                                            <vivo:tbHeaderColumn align="left"   width="30%" type="string">Percentual</vivo:tbHeaderColumn>
                                        </vivo:tbHeader>
                                        <vivo:tbRows>
                                            <vivo:tbRow key="linha1">
                                                <vivo:tbRowColumn>1</vivo:tbRowColumn>
                                                <vivo:tbRowColumn>Rubi ***/Soho</vivo:tbRowColumn>
                                                <vivo:tbRowColumn>5</vivo:tbRowColumn>
                                                <vivo:tbRowColumn>******</vivo:tbRowColumn>
                                            </vivo:tbRow>
                                            <vivo:tbRow key="linha1">
                                                <vivo:tbRowColumn><b>2</b></vivo:tbRowColumn>
                                                <vivo:tbRowColumn><b>Total Geral</b></vivo:tbRowColumn>
                                                <vivo:tbRowColumn><b>5</b></vivo:tbRowColumn>
                                                <vivo:tbRowColumn><b>******</b></vivo:tbRowColumn>
                                            </vivo:tbRow>
                                        </vivo:tbRows>
                                    </vivo:tbTable>
                                </vivo:tbRowColumn>
                            </vivo:tbRow>
                            
                            <vivo:tbRow key="linha1" zebrar="S">
                                <vivo:tbRowColumn><b>Grupo 3x</b></vivo:tbRowColumn>
                                <vivo:tbRowColumn>
                                    <vivo:tbTable selectable="false" layoutWidth="430" layoutHeight="60" tableWidth="430" styleId="tdSegmentacaoGruposDet5" sortable="true">
                                        <vivo:tbHeader>
                                            <vivo:tbHeaderColumn align="center" width="10%" type="string">Ordem</vivo:tbHeaderColumn>
                                            <vivo:tbHeaderColumn align="center" width="40%" type="string">Segmentação/Carteira</vivo:tbHeaderColumn>
                                            <vivo:tbHeaderColumn align="center" width="20%" type="string">Quantidade</vivo:tbHeaderColumn>
                                            <vivo:tbHeaderColumn align="left"   width="30%" type="string">Percentual</vivo:tbHeaderColumn>
                                        </vivo:tbHeader>
                                        <vivo:tbRows>
                                            <vivo:tbRow key="linha1">
                                                <vivo:tbRowColumn>1</vivo:tbRowColumn>
                                                <vivo:tbRowColumn>Safira ***/Soho</vivo:tbRowColumn>
                                                <vivo:tbRowColumn>1</vivo:tbRowColumn>
                                                <vivo:tbRowColumn>*</vivo:tbRowColumn>
                                            </vivo:tbRow>
                                            <vivo:tbRow key="linha1">
                                                <vivo:tbRowColumn><b>2</b></vivo:tbRowColumn>
                                                <vivo:tbRowColumn><b>Total Geral</b></vivo:tbRowColumn>
                                                <vivo:tbRowColumn><b>1</b></vivo:tbRowColumn>
                                                <vivo:tbRowColumn><b>*</b></vivo:tbRowColumn>
                                            </vivo:tbRow>
                                        </vivo:tbRows>
                                    </vivo:tbTable>
                                </vivo:tbRowColumn>
                            </vivo:tbRow>
                            
                            <vivo:tbRow key="linha1" zebrar="S">
                                <vivo:tbRowColumn><b>Grupo 4x</b></vivo:tbRowColumn>
                                <vivo:tbRowColumn>
                                    <vivo:tbTable selectable="false" layoutWidth="430" layoutHeight="55" tableWidth="430" styleId="tdSegmentacaoGruposDet6" sortable="true">
                                        <vivo:tbHeader>
                                            <vivo:tbHeaderColumn align="center" width="10%" type="string">Ordem</vivo:tbHeaderColumn>
                                            <vivo:tbHeaderColumn align="center" width="40%" type="string">Segmentação/Carteira</vivo:tbHeaderColumn>
                                            <vivo:tbHeaderColumn align="center" width="20%" type="string">Quantidade</vivo:tbHeaderColumn>
                                            <vivo:tbHeaderColumn align="left"   width="30%" type="string">Percentual</vivo:tbHeaderColumn>
                                        </vivo:tbHeader>
                                        <vivo:tbRows>
                                            <vivo:tbRow key="linha1">
                                                <vivo:tbRowColumn>1</vivo:tbRowColumn>
                                                <vivo:tbRowColumn>Diamante ***/Soho</vivo:tbRowColumn>
                                                <vivo:tbRowColumn>1</vivo:tbRowColumn>
                                                <vivo:tbRowColumn>*</vivo:tbRowColumn>
                                            </vivo:tbRow>
                                            <vivo:tbRow key="linha1">
                                                <vivo:tbRowColumn><b>2</b></vivo:tbRowColumn>
                                                <vivo:tbRowColumn><b>Total Geral</b></vivo:tbRowColumn>
                                                <vivo:tbRowColumn><b>1</b></vivo:tbRowColumn>
                                                <vivo:tbRowColumn><b>*</b></vivo:tbRowColumn>
                                            </vivo:tbRow>
                                        </vivo:tbRows>
                                    </vivo:tbTable>
                                </vivo:tbRowColumn>
                            </vivo:tbRow>
                        </vivo:tbRows>
                    </vivo:tbTable>
                </vivo:tbRowColumn>
            </vivo:tbRow>
        </vivo:tbRows>
    </vivo:tbTable>
</vivo:quadro>
    
<script language="javascript">
    //Fim animação
    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
</script>
