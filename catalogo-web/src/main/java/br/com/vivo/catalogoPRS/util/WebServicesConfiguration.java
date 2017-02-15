package br.com.vivo.catalogoPRS.util;

import java.util.ResourceBundle;

public abstract class WebServicesConfiguration {
	private final static ResourceBundle bundle = ResourceBundle.getBundle("catalogoprs_web");

	private static final String HOST = "ws.host";

	private static final String PORT = "ws.port";

	private static final String CONTEXT = "ws.context";

	public final static String FREQUENCIA = "ws.service.frequencia";

	public static final String TECNOLOGIA = "ws.service.tecnologia";

	public static final String CARACTERISTICAS = "ws.service.caracteristica";

	public static final String DESCONTOS = "ws.service.desconto";

	public static final String ASSOCIACAOTECNOLOGIAFREQUENCIA = "ws.service.associacaoTecnologiaFrequencia";

	public static final String FABRICANTE = "ws.service.fabricante";

	public static final String TIPO_PRODUTO = "ws.service.tipoProduto";

	public static final String CANAL = "ws.service.canal";

	public static final String PLATAFORMA = "ws.service.plataforma";

	public static final String TIPO_CLIENTE = "ws.service.tipoCliente";

	public static final String UF = "ws.service.uf";

	public static final String GRUPO_PRODUTO = "ws.service.grupoProduto";

	public static final String MULTIMIDIA = "ws.service.multimidia";

	public static final String MATRIZOFERTA = "ws.service.matrizOferta";
	
	public static final String MATRIZOFERTAARQUIVO = "ws.service.matrizOfertaArquivo";

	public static final String ORGANIZACAOVENDAS = "ws.service.organizacaoVendas";
	
	public static final String FORMA_PAGAMENTO = "ws.service.formaPagamento";

	public static final String DESCONTO = "ws.service.desconto";

	public static final String PRODUTO = "ws.service.produto";

	public static final String PLANO = "ws.service.plano";

	public static final String GRUPO_SERVICO = "ws.service.grupoServico";

	public static final String SERVICO = "ws.service.servico";
	
	public static final String REGIONAL = "ws.service.regional";
	
	public static final String MODELOVENDA = "ws.service.modeloVenda";
	
	public static final String OFERTASERVICO = "ws.service.ofertaServico";
	
	public static final String TIPOOPERACAO = "ws.service.tipoOperacao";
	
	public static final String CARTEIRA = "ws.service.carteira";
	
	public static final String SEGMENTO = "ws.service.segmento";
	
	public static final String ACESSO = "ws.service.acesso";
	
	public static final String OFERTASAP = "ws.service.ofertasap";
	
	public static final String PROGRAMAPONTOS = "ws.service.programaPontos";
	
	public static final String PONTOS = "ws.service.pontos";
	
	public static final String ACAO = "ws.service.acao";
	
	public static String getEndpointAddress(String webServiceNameKey) {
		String host = bundle.getString(HOST);
		String port = bundle.getString(PORT);

		String serviceName = bundle.getString(webServiceNameKey);
		String numeroContexto = serviceName.substring(0, serviceName.indexOf('/'));
		serviceName = serviceName.substring(serviceName.indexOf('/'));
		String context = bundle.getString(CONTEXT + numeroContexto);
		String endPointURL = host + ":" + port + context + serviceName;
		return endPointURL;
	}
}
