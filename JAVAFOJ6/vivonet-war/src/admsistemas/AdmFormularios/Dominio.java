package admsistemas.AdmFormularios;

import java.io.Serializable;

import br.com.vivo.fo.constantes.ConstantesCRM;

public class Dominio implements Serializable {

	private static final long serialVersionUID = -5311330424740774305L;

	private String idDominio = ConstantesCRM.SVAZIO;
	private String vlDominio = ConstantesCRM.SVAZIO;

	public void setIdDominio(String idDominio){
		this.idDominio = idDominio;
	}

	public String getIdDominio(){
		return this.idDominio;
	}

	public void setVlDominio(String vlDominio){
		this.vlDominio = vlDominio;
	}

	public String getVlDominio(){
		return this.vlDominio;
	}
}