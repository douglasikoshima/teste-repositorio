package br.com.indrasistemas.vivoservices.administracaosistema.endereco.to;

import br.com.indrasistemas.vivoservices.to.BaseEnderecoTO;

public class EnderecoTO extends BaseEnderecoTO {

	public static final String LADO_PAR = "Par";

	public static final String LADO_IMPAR = "Ímpar";

	private static final long serialVersionUID = -6904057718463125374L;

	private String lado;

	public EnderecoTO() {

	}

	public String getLado() {
		return lado;
	}

	public void setLado(String lado) {
		this.lado = lado;
	}

	/**
	 * Constructs a <code>String</code> with all attributes
	 * in name = value format.
	 *
	 * @return a <code>String</code> representation 
	 * of this object.
	 */
	public String toString()
	{
	    final String TAB = "    ";
	    
	    StringBuffer retValue = new StringBuffer();
	    
	    retValue.append("EnderecoTO ( ")
	        .append(super.toString()).append(TAB)
	        .append("lado = ").append(this.lado).append(TAB)
	        .append(" )");
	    
	    return retValue.toString();
	}

}
