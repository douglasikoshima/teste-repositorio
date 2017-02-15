package br.com.vivo.catalogoPRS.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.xml.rpc.Service;

public abstract class ApplicationConfiguration {
	private final static ResourceBundle bundle = ResourceBundle.getBundle("catalogoprs_config");
	private final static ResourceBundle nomesServicos = ResourceBundle.getBundle("catalogoprs_servicos");

	private final static String ELEMENTOS_POR_PAGINA = "elementos.por.pagina";
	private final static String ELEMENTOS_POR_PAGINA_NOS_POPUPS = "elementos.por.pagina.nos.popups";
	private final static String CAMIMHO_SALVAR_IMAGENS_MODELO = "caminho.salvar.imagens.modelo";
	private final static String ID_FORMA_PAGAMENTO_CARTAO_CREDITO = "id.forma.pagamento.cartao.credito";
	private final static String DEBUG = "debug";
	private final static String SB_SECURITY_USERNAME = "sb.security.username";
	private final static String SB_SECURITY_PASSWORD = "sb.security.password";
	private final static String SCA_DATASOURCE_URL = "sca.datasource.provider.url";
	private final static String SCA_DATASOURCE_CONTEXT_FACTORY = "sca.datasource.contextFactory";
	private final static String SCA_DATASOURCE_JNDI_NAME = "sca.datasource.jndi.name";
	private final static String CAMINHO_LINK_IMAGENS_MODELO = "caminho.link.imagens.modelo";
	private final static String CAMINHO_CONTEUDO_ESTATICO = "caminho.conteudo.estatico";
	private final static String NOME_COOKIE = "cookie.nome";
	private final static String PARAM_TYPE_CODE = "param.type.code";
	private final static String CODIGO_ERRO_LISTA_VAZIA = "codigo.erro.buscarListaVariaveis.listaVazia";
	private final static String TRADUTOR_DATASOURCE_CONTEXT_FACTORY = "tradutor.datasource.provider.url";
	private final static String TRADUTOR_DATASOURCE_JNDI_NAME = "tradutor.datasource.jndi.name";
	private final static String TRADUTOR_DATASOURCE_URL = "tradutor.datasource.provider.url";
	
	private final static String VIVONET_DATASOURCE_JNDI_NAME = "vivonet.datasource.jndi.name";
	private final static String VIVONET_DATASOURCE_URL = "vivonet.datasource.provider.url";
	private final static String VIVONET_DATASOURCE_CONTEXT_FACTORY = "vivonet.datasource.contextFactory";		
	
	private final static String COMBO_TP_PRODUTO_DESCRICAO = "combo.tipo.produtos.descricao"; 
	private final static String TIPO_PRODUTO_IDS = "combo.tipo.produtos.ids"; 
	private final static String POPUP_TP_PRODUTO_DESCRICAO = "popup.tipo.produtos.descricao"; 
	
	private final static String CAMIMHO_SALVAR_IMAGENS_CARACTERISTICAS = "caminho.salvar.imagens.caracteristicas";

	private final static String CAMINHO_IMPORTACAOSERVICOFIXA_RELACIONAMENTO = "caminho.importacaoservicofixa.relacionamento";
	private final static String CAMINHO_IMPORTACAOSERVICOFIXA_SCGCPMAC = "caminho.importacaoservicofixa.scgcpmac";
	private final static String CAMINHO_IMPORTACAOSERVICOFIXA_SCENCGC = "caminho.importacaoservicofixa.scencgc";
	private final static String CAMINHO_IMPORTACAOSERVICOFIXA_SCENCGCPMAC = "caminho.importacaoservicofixa.scencgcpmac";
    private final static String CAMINHO_IMPORTACAOSERVICOFIXA_SERVICO = "caminho.importacaoservicofixa.servico";
    private final static String CAMINHO_IMPORTACAOSERVICOFIXA_OFERTA_VENDA_LINHA = "caminho.importacaoservicofixa.ofertavendalinha";
    private final static String CAMINHO_IMPORTACAOSERVICOFIXA_OFERTA_COMPLEMENTAR = "caminho.importacaoservicofixa.ofertacomplementar";
	
	public static String getDescricaoTipoProdutoCombo() {
		return bundle.getString(COMBO_TP_PRODUTO_DESCRICAO);
	}
	
	public static String getIdsTipoProdutoCombo() {
		return bundle.getString(TIPO_PRODUTO_IDS);
	}
	
	public static String getDescricaoTipoProdutoPopup() {
		return bundle.getString(POPUP_TP_PRODUTO_DESCRICAO);
	}
	
	public static String getParamTypeCode() {
		return bundle.getString(PARAM_TYPE_CODE);
	}
	
	public static String getCdErroBuscarListaVariaveisListaVazia() {
		return bundle.getString(CODIGO_ERRO_LISTA_VAZIA);
	}
	
	public static String getNomeCookie(){
		return bundle.getString(NOME_COOKIE);
	}
	
	public static String getCaminhoLinkImagensModelo(){
		return bundle.getString(CAMINHO_LINK_IMAGENS_MODELO);
	}
	
	public static String getCaminhoConteudoEstatico(){
		return bundle.getString(CAMINHO_CONTEUDO_ESTATICO);
	}
		
	public static int getElementosPorPagina(){
		return Integer.parseInt(bundle.getString(ELEMENTOS_POR_PAGINA));
	}
	
	public static int getElementosPorPaginaNosPopups(){
		return Integer.parseInt(bundle.getString(ELEMENTOS_POR_PAGINA_NOS_POPUPS));
	}
	
	public static String getCaminhoSalvarImagensModelo(){
		return bundle.getString(CAMIMHO_SALVAR_IMAGENS_MODELO);
	}
	
	public static Long getIdFormaPagamentoCartaoCredito(){
		return Long.parseLong(bundle.getString(ID_FORMA_PAGAMENTO_CARTAO_CREDITO));
	}
	
	public static boolean isDebug(){
		return Boolean.parseBoolean(bundle.getString(DEBUG));
	}
	
	public static String getWebserviceSecurityUsername(){
		return bundle.getString(SB_SECURITY_USERNAME);
	}
		
	public static String getWebserviceSecurityPassword(){
		return bundle.getString(SB_SECURITY_PASSWORD);
	}
	
	public static String getScaDatasourceURL(){
		return bundle.getString(SCA_DATASOURCE_URL);
	}
	
	public static String getScaDatasourceContextFactory(){
		return bundle.getString(SCA_DATASOURCE_CONTEXT_FACTORY);
	}
	
	public static String getScaDatasourceJndiName(){
		return bundle.getString(SCA_DATASOURCE_JNDI_NAME);
	}
	
	public static String getTradutorDatasourceURL(){
		return bundle.getString(TRADUTOR_DATASOURCE_URL);
	}
	
	public static String getTradutorDatasourceContextFactory(){
		return bundle.getString(TRADUTOR_DATASOURCE_CONTEXT_FACTORY);
	}
	
	public static String getTradutorDatasourceJndiName(){
		return bundle.getString(TRADUTOR_DATASOURCE_JNDI_NAME);
	}
	
	public static String getNomeServico(String nomeServico){
		try{
			return nomesServicos.getString(nomeServico);
		}catch(MissingResourceException e){
			return "CatalogoGenerico";
		}
	}
	
	public static String getCaminhoSalvarImagensCaracteristicas(){
		return bundle.getString(CAMIMHO_SALVAR_IMAGENS_CARACTERISTICAS);
	}

	public static String getCaminhoImportacaoRelacionamento() {
		return bundle.getString(CAMINHO_IMPORTACAOSERVICOFIXA_RELACIONAMENTO);
	}

	public static String getCaminhoImportacaoSCGCPMAC() {
		return bundle.getString(CAMINHO_IMPORTACAOSERVICOFIXA_SCGCPMAC);
	}

	public static String getCaminhoImportacaoSCENCGC() {
		return bundle.getString(CAMINHO_IMPORTACAOSERVICOFIXA_SCENCGC);
	}

	public static String getCaminhoImportacaoSCENCGCPMAC() {
		return bundle.getString(CAMINHO_IMPORTACAOSERVICOFIXA_SCENCGCPMAC);
	}
    
    public static String getCaminhoImportacaoServico() {
        return bundle.getString(CAMINHO_IMPORTACAOSERVICOFIXA_SERVICO);
    }

    public static String getCaminhoImportacaoOferta() {
    	return bundle.getString(CAMINHO_IMPORTACAOSERVICOFIXA_OFERTA_VENDA_LINHA);
    }

	public static String getCaminhoImportacaoOfertaComplementar() {
		return bundle.getString(CAMINHO_IMPORTACAOSERVICOFIXA_OFERTA_COMPLEMENTAR);
	}   
	public static String getVivoNetDatasourceJndiName(){
		return bundle.getString(VIVONET_DATASOURCE_JNDI_NAME);
	}
	
	public static String getVivoNetDatasourceURL(){
		return bundle.getString(VIVONET_DATASOURCE_URL);
	}

	public static String getVivoNetDatasourceContextFactory(){
		return bundle.getString(VIVONET_DATASOURCE_CONTEXT_FACTORY);
	}		
}