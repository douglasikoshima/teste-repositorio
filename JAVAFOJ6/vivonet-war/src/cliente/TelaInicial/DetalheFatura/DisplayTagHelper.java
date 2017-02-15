/**
 *
 */
package cliente.TelaInicial.DetalheFatura;

import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;

import br.com.vivo.fo.constantes.ConstantesCRM;

/**
 * @author Silvio Fragata
 *
 */
public class DisplayTagHelper {

	/**
	 * @param id
	 */
	public DisplayTagHelper(String id) {
		super();
		this.id = id;
	}

	private String id;

	public String getExport() {
		return new ParamEncoder(id)
				.encodeParameterName(TableTagParameters.PARAMETER_EXPORTTYPE);
	}

	public String getStart() {
		return new ParamEncoder(id)
				.encodeParameterName(TableTagParameters.PARAMETER_PAGE);
	}

	public String getSort() {
		return new ParamEncoder(id)
				.encodeParameterName(TableTagParameters.PARAMETER_SORT);
	}

	public String getOrder() {
		return new ParamEncoder(id)
				.encodeParameterName(TableTagParameters.PARAMETER_ORDER);
	}

	public String decodeOrder(String encodeParameterName) {
		return ConstantesCRM.STWO.equals(encodeParameterName) ? "desc" : "asc";
	}

	public int getLimit(String limit, String export) {
		int limite = -1;
		try {
			limite = Integer.parseInt(limit);
		} catch (NumberFormatException e) {
		}
		if (limite == -1 && export != null && !export.trim().equals(ConstantesCRM.SVAZIO)) {
			limite = 0;
		} else {
			// FIXME: Pegar o valor de configuração (ConfigManager)!
			limite = 10;
		}
		return limite;
	}

	public boolean validaExportacao(String export) {
		return (export != null && !export.trim().equals(ConstantesCRM.SVAZIO));
	}
}
