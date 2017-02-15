package com.indracompany.catalogo.datalayer;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;



/**
 * @author Luiz Pereira
 *
 */
@Entity
@NamedQuery(name = "FornecedorSCA.findAll", query = "SELECT fs FROM FornecedorSCA fs order by fs.nmFornecedorSCA")
@Table(name = "FORNECEDORSCA", schema = "CATALOGOPRS_OW")
public class FornecedorSCA implements Serializable {
	private static final long serialVersionUID = 1L;

    public FornecedorSCA() {}
    
	@Id
	@Column(name = "IDFORNECEDORSCA")
	private Integer idFornecedorSCA;
	
	@Column(name = "NMFORNECEDORSCA")
	private String nmFornecedorSCA;

	public Integer getIdFornecedorSCA() {
		return idFornecedorSCA;
	}

	public void setIdFornecedorSCA(Integer idFornecedorSCA) {
		this.idFornecedorSCA = idFornecedorSCA;
	}

	public String getNmFornecedorSCA() {
		return nmFornecedorSCA;
	}

	public void setNmFornecedorSCA(String nmFornecedorSCA) {
		this.nmFornecedorSCA = nmFornecedorSCA;
	}
}