package br.com.vivo.fo.cliente.detalheFatura;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Informacoes das chamadas efetuadas ou recebidas por clientes vivo.
 */
public class Chamada implements java.io.Serializable, Comparable {

	private static final long serialVersionUID = -8131462957899766088L;

	/* Data e hora da chamada */
	private java.lang.String dataChamada;

	/* Numero da linha que fez a chamada */
	private java.lang.String numeroOrigem;

	/* Numero da linha que recebeu a chamada */
	private java.lang.String numeroDestino;

	/* Referencia a entidade TipoChamada */
	private TipoChamada tipoChamada;

	/* Referencia a entidade TipoServicoChamada */
	private TipoServicoChamada tipoServicoChamada;

	/* Referencia a entidade AreaChamada */
	private AreaChamada areaChamada;

	private TipoDetalheChamada tipoDetalheChamada;

	/* Nome do destino da chamada */
	private DestinoChamada destinoChamada;

	private TipoTarifa tipoTarifa;

	/* Valor da chamada */
	private java.lang.String valor;

	/* Duracao da chamada */
	private java.lang.String duracao;

	/* Valor da chamada formatada */
	private java.lang.String valorFormatado;

	/* Duracao da chamada formatada */
	private java.lang.String duracaoFormatada;

	/* Referencia a entidade Fatura. */
	private Fatura fatura;

	private int idChamada;

	/*
	 * Referencia a entidade AgendaContato. Traz informacoes para identificar
	 * quem recebeu ou originou a chamada.
	 */
	private AgendaContato agendaContato;

	private String horaChamada;

	public Chamada() {
	}

	public Chamada(java.lang.String dataChamada, java.lang.String numeroOrigem, java.lang.String numeroDestino,
			TipoChamada tipoChamada, TipoServicoChamada tipoServicoChamada, AreaChamada areaChamada,
			TipoDetalheChamada tipoDetalheChamada, DestinoChamada destinoChamada, TipoTarifa tipoTarifa,
			java.lang.String valor, java.lang.String duracao, Fatura fatura, AgendaContato agendaContato) {
		this.dataChamada = dataChamada;
		this.numeroOrigem = numeroOrigem;
		this.numeroDestino = numeroDestino;
		this.tipoChamada = tipoChamada;
		this.tipoServicoChamada = tipoServicoChamada;
		this.areaChamada = areaChamada;
		this.tipoDetalheChamada = tipoDetalheChamada;
		this.destinoChamada = destinoChamada;
		this.tipoTarifa = tipoTarifa;
		this.valor = valor;
		this.duracao = duracao;
		this.fatura = fatura;
		this.agendaContato = agendaContato;
	}

	/**
	 * Gets the dataChamada value for this Chamada.
	 * 
	 * @return dataChamada * Data e hora da chamada
	 */
	public java.lang.String getDataChamada() {
		return dataChamada;
	}

	/**
	 * Sets the dataChamada value for this Chamada.
	 * 
	 * @param dataChamada
	 *            * Data e hora da chamada
	 */
	public void setDataChamada(java.lang.String dataChamada) {
		this.dataChamada = dataChamada;
	}

	/**
	 * Gets the numeroOrigem value for this Chamada.
	 * 
	 * @return numeroOrigem * Numero da linha que fez a chamada
	 */
	public java.lang.String getNumeroOrigem() {
		return numeroOrigem;
	}

	/**
	 * Sets the numeroOrigem value for this Chamada.
	 * 
	 * @param numeroOrigem
	 *            * Numero da linha que fez a chamada
	 */
	public void setNumeroOrigem(java.lang.String numeroOrigem) {
		this.numeroOrigem = numeroOrigem;
	}

	/**
	 * Gets the numeroDestino value for this Chamada.
	 * 
	 * @return numeroDestino * Numero da linha que recebeu a chamada
	 */
	public java.lang.String getNumeroDestino() {
		return numeroDestino;
	}

	/**
	 * Sets the numeroDestino value for this Chamada.
	 * 
	 * @param numeroDestino
	 *            * Numero da linha que recebeu a chamada
	 */
	public void setNumeroDestino(java.lang.String numeroDestino) {
		this.numeroDestino = numeroDestino;
	}

	/**
	 * Gets the tipoChamada value for this Chamada.
	 * 
	 * @return tipoChamada * Referencia a entidade TipoChamada
	 */
	public TipoChamada getTipoChamada() {
		return tipoChamada;
	}

	/**
	 * Sets the tipoChamada value for this Chamada.
	 * 
	 * @param tipoChamada
	 *            * Referencia a entidade TipoChamada
	 */
	public void setTipoChamada(TipoChamada tipoChamada) {
		this.tipoChamada = tipoChamada;
	}

	/**
	 * Gets the tipoServicoChamada value for this Chamada.
	 * 
	 * @return tipoServicoChamada * Referencia a entidade TipoServicoChamada
	 */
	public TipoServicoChamada getTipoServicoChamada() {
		return tipoServicoChamada;
	}

	/**
	 * Sets the tipoServicoChamada value for this Chamada.
	 * 
	 * @param tipoServicoChamada
	 *            * Referencia a entidade TipoServicoChamada
	 */
	public void setTipoServicoChamada(TipoServicoChamada tipoServicoChamada) {
		this.tipoServicoChamada = tipoServicoChamada;
	}

	/**
	 * Gets the areaChamada value for this Chamada.
	 * 
	 * @return areaChamada * Referencia a entidade AreaChamada
	 */
	public AreaChamada getAreaChamada() {
		return areaChamada;
	}

	/**
	 * Sets the areaChamada value for this Chamada.
	 * 
	 * @param areaChamada
	 *            * Referencia a entidade AreaChamada
	 */
	public void setAreaChamada(AreaChamada areaChamada) {
		this.areaChamada = areaChamada;
	}

	/**
	 * Gets the tipoDetalheChamada value for this Chamada.
	 * 
	 * @return tipoDetalheChamada
	 */
	public TipoDetalheChamada getTipoDetalheChamada() {
		return tipoDetalheChamada;
	}

	/**
	 * Sets the tipoDetalheChamada value for this Chamada.
	 * 
	 * @param tipoDetalheChamada
	 */
	public void setTipoDetalheChamada(TipoDetalheChamada tipoDetalheChamada) {
		this.tipoDetalheChamada = tipoDetalheChamada;
	}

	/**
	 * Gets the destinoChamada value for this Chamada.
	 * 
	 * @return destinoChamada * Nome do destino da chamada
	 */
	public DestinoChamada getDestinoChamada() {
		return destinoChamada;
	}

	/**
	 * Sets the destinoChamada value for this Chamada.
	 * 
	 * @param destinoChamada
	 *            * Nome do destino da chamada
	 */
	public void setDestinoChamada(DestinoChamada destinoChamada) {
		this.destinoChamada = destinoChamada;
	}

	/**
	 * Gets the tipoTarifa value for this Chamada.
	 * 
	 * @return tipoTarifa
	 */
	public TipoTarifa getTipoTarifa() {
		return tipoTarifa;
	}

	/**
	 * Sets the tipoTarifa value for this Chamada.
	 * 
	 * @param tipoTarifa
	 */
	public void setTipoTarifa(TipoTarifa tipoTarifa) {
		this.tipoTarifa = tipoTarifa;
	}

	/**
	 * Gets the valor value for this Chamada.
	 * 
	 * @return valor * Valor da chamada
	 */
	public java.lang.String getValor() {
		return valor;
	}

	/**
	 * Sets the valor value for this Chamada.
	 * 
	 * @param valor
	 *            * Valor da chamada
	 */
	public void setValor(java.lang.String valor) {
		this.valor = valor;
	}

	/**
	 * Gets the duracao value for this Chamada.
	 * 
	 * @return duracao * Duracao da chamada
	 */
	public java.lang.String getDuracao() {
		return duracao;
	}

	/**
	 * Sets the duracao value for this Chamada.
	 * 
	 * @param duracao
	 *            * Duracao da chamada
	 */
	public void setDuracao(java.lang.String duracao) {
		this.duracao = duracao;
	}

	/**
	 * Gets the valor value for this Chamada.
	 * 
	 * @return valor * Valor da chamada
	 */
	public java.lang.String getValorFormatado() {
		return valorFormatado;
	}

	/**
	 * Sets the valor value for this Chamada.
	 * 
	 * @param valor
	 *            * Valor da chamada
	 */
	public void setValorFormatado(java.lang.String valorFormatado) {
		this.valorFormatado = valorFormatado;
	}

	/**
	 * Gets the duracao value for this Chamada.
	 * 
	 * @return duracao * Duracao da chamada
	 */
	public java.lang.String getDuracaoFormatada() {
		return duracaoFormatada;
	}

	/**
	 * Sets the duracao value for this Chamada.
	 * 
	 * @param duracao
	 *            * Duracao da chamada
	 */
	public void setDuracaoFormatada(java.lang.String duracaoFormatada) {
		this.duracaoFormatada = duracaoFormatada;
	}

	/**
	 * Gets the fatura value for this Chamada.
	 * 
	 * @return fatura * Referencia a entidade Fatura.
	 */
	public Fatura getFatura() {
		return fatura;
	}

	/**
	 * Sets the fatura value for this Chamada.
	 * 
	 * @param fatura
	 *            * Referencia a entidade Fatura.
	 */
	public void setFatura(Fatura fatura) {
		this.fatura = fatura;
	}

	/**
	 * Gets the agendaContato value for this Chamada.
	 * 
	 * @return agendaContato * Referencia a entidade AgendaContato. Traz
	 *         informacoes para identificar quem recebeu ou originou a chamada.
	 */
	public AgendaContato getAgendaContato() {
		return agendaContato;
	}

	/**
	 * Sets the agendaContato value for this Chamada.
	 * 
	 * @param agendaContato
	 *            * Referencia a entidade AgendaContato. Traz informacoes para
	 *            identificar quem recebeu ou originou a chamada.
	 */
	public void setAgendaContato(AgendaContato agendaContato) {
		this.agendaContato = agendaContato;
	}

	private java.lang.Object __equalsCalc = null;

	public synchronized boolean equals(java.lang.Object obj) {
		if (!(obj instanceof Chamada))
			return false;
		Chamada other = (Chamada) obj;
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (__equalsCalc != null) {
			return (__equalsCalc == obj);
		}
		__equalsCalc = obj;
		boolean _equals;
		_equals = true
				&& ((this.dataChamada == null && other.getDataChamada() == null) || (this.dataChamada != null && this.dataChamada
						.equals(other.getDataChamada())))
				&& ((this.numeroOrigem == null && other.getNumeroOrigem() == null) || (this.numeroOrigem != null && this.numeroOrigem
						.equals(other.getNumeroOrigem())))
				&& ((this.numeroDestino == null && other.getNumeroDestino() == null) || (this.numeroDestino != null && this.numeroDestino
						.equals(other.getNumeroDestino())))
				&& ((this.tipoChamada == null && other.getTipoChamada() == null) || (this.tipoChamada != null && this.tipoChamada
						.equals(other.getTipoChamada())))
				&& ((this.tipoServicoChamada == null && other.getTipoServicoChamada() == null) || (this.tipoServicoChamada != null && this.tipoServicoChamada
						.equals(other.getTipoServicoChamada())))
				&& ((this.areaChamada == null && other.getAreaChamada() == null) || (this.areaChamada != null && this.areaChamada
						.equals(other.getAreaChamada())))
				&& ((this.tipoDetalheChamada == null && other.getTipoDetalheChamada() == null) || (this.tipoDetalheChamada != null && this.tipoDetalheChamada
						.equals(other.getTipoDetalheChamada())))
				&& ((this.destinoChamada == null && other.getDestinoChamada() == null) || (this.destinoChamada != null && this.destinoChamada
						.equals(other.getDestinoChamada())))
				&& ((this.tipoTarifa == null && other.getTipoTarifa() == null) || (this.tipoTarifa != null && this.tipoTarifa
						.equals(other.getTipoTarifa())))
				&& ((this.valor == null && other.getValor() == null) || (this.valor != null && this.valor.equals(other
						.getValor())))
				&& ((this.duracao == null && other.getDuracao() == null) || (this.duracao != null && this.duracao
						.equals(other.getDuracao())))
				&& ((this.fatura == null && other.getFatura() == null) || (this.fatura != null && this.fatura
						.equals(other.getFatura())))
				&& ((this.agendaContato == null && other.getAgendaContato() == null) || (this.agendaContato != null && this.agendaContato
						.equals(other.getAgendaContato())));
		__equalsCalc = null;
		return _equals;
	}

	private boolean __hashCodeCalc = false;

	public synchronized int hashCode() {
		if (__hashCodeCalc) {
			return 0;
		}
		__hashCodeCalc = true;
		int _hashCode = 1;
		if (getDataChamada() != null) {
			_hashCode += getDataChamada().hashCode();
		}
		if (getNumeroOrigem() != null) {
			_hashCode += getNumeroOrigem().hashCode();
		}
		if (getNumeroDestino() != null) {
			_hashCode += getNumeroDestino().hashCode();
		}
		if (getTipoChamada() != null) {
			_hashCode += getTipoChamada().hashCode();
		}
		if (getTipoServicoChamada() != null) {
			_hashCode += getTipoServicoChamada().hashCode();
		}
		if (getAreaChamada() != null) {
			_hashCode += getAreaChamada().hashCode();
		}
		if (getTipoDetalheChamada() != null) {
			_hashCode += getTipoDetalheChamada().hashCode();
		}
		if (getDestinoChamada() != null) {
			_hashCode += getDestinoChamada().hashCode();
		}
		if (getTipoTarifa() != null) {
			_hashCode += getTipoTarifa().hashCode();
		}
		if (getValor() != null) {
			_hashCode += getValor().hashCode();
		}
		if (getDuracao() != null) {
			_hashCode += getDuracao().hashCode();
		}
		if (getFatura() != null) {
			_hashCode += getFatura().hashCode();
		}
		if (getAgendaContato() != null) {
			_hashCode += getAgendaContato().hashCode();
		}
		__hashCodeCalc = false;
		return _hashCode;
	}

	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
			Chamada.class, true);

	static {
		typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "Chamada"));
		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("dataChamada");
		elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "dataChamada"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numeroOrigem");
		elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "numeroOrigem"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numeroDestino");
		elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "numeroDestino"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipoChamada");
		elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "tipoChamada"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "TipoChamada"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipoServicoChamada");
		elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "tipoServicoChamada"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "TipoServicoChamada"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("areaChamada");
		elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "areaChamada"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "AreaChamada"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipoDetalheChamada");
		elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "tipoDetalheChamada"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "TipoDetalheChamada"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("destinoChamada");
		elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "destinoChamada"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "DestinoChamada"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipoTarifa");
		elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "tipoTarifa"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "TipoTarifa"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("valor");
		elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "valor"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("duracao");
		elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "duracao"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("fatura");
		elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "fatura"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "Fatura"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("agendaContato");
		elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "agendaContato"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "AgendaContato"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/**
	 * Return type metadata object
	 */
	public static org.apache.axis.description.TypeDesc getTypeDesc() {
		return typeDesc;
	}

	/**
	 * Get Custom Serializer
	 */
	public static org.apache.axis.encoding.Serializer getSerializer(java.lang.String mechType,
			java.lang.Class _javaType, javax.xml.namespace.QName _xmlType) {
		return new org.apache.axis.encoding.ser.BeanSerializer(_javaType, _xmlType, typeDesc);
	}

	/**
	 * Get Custom Deserializer
	 */
	public static org.apache.axis.encoding.Deserializer getDeserializer(java.lang.String mechType,
			java.lang.Class _javaType, javax.xml.namespace.QName _xmlType) {
		return new org.apache.axis.encoding.ser.BeanDeserializer(_javaType, _xmlType, typeDesc);
	}

	/**
	 * Criado para facilitar o agrupamento dinamico das informações.
	 */
	public String getGrupoAgendaDescricao() {
		return agendaContato.getGrupoContato();
	}

	public String getTipoChamadaDescricao() {
		return tipoChamada.getDescricao();
	}

	public String getTipoServicoChamadaDescricao() {
		return tipoServicoChamada.getDescricao();
	}

	public String getAreaChamadaDescricao() {
		return areaChamada.getDescricao();
	}

	public String getTipoDetalheChamadaDescricao() {
		return tipoDetalheChamada.getDescricao();
	}

	public String getDestinoChamadaDescricao() {
		return destinoChamada.getDescricao();
	}

	public String getTipoTarifaDescricao() {
		return tipoTarifa.getDescricao();
	}

	public String getHoraChamada() {
		return horaChamada;
	}

	public void setHoraChamada(String horaChamada) {
		this.horaChamada = horaChamada;
	}

	public int compareTo(Object obj) {
		Chamada tmp = (Chamada) obj;
		String s1 = this.dataChamada.substring(0, 19);
		String s2 = tmp.dataChamada.substring(0, 19);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		Date d1;
		Date d2;
		try {
			d1 = sdf.parse(s1);
			d2 = sdf.parse(s2);
		} catch (java.text.ParseException e) {
			return 0;
		}
		int i = d1.compareTo(d2);
		return -i;
	}

	public int getIdChamada() {
		return this.idChamada;
	}

	public void setIdChamada(int idChamada) {
		this.idChamada = idChamada;
	}
}