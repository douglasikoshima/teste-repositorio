package fidelizacao.formBeans;

import org.apache.struts.action.ActionForm;
import br.com.vivo.fo.cliente.vo.EnderecoVODocument.EnderecoVO;

public class ConsulstaCEPFormBean extends ActionForm {

    private static final long serialVersionUID = -3477397294743113719L;
    private String            estado;
    private String            cidade;
    private String            bairro;
    private String            complemento;
    private String            numero;
    private String            rua;
    private EnderecoVO        enderecoVO;
    private String            nrCEP;

    public void setNrCEP(String nrCEP) {
        this.nrCEP = nrCEP;
    }

    public String getNrCEP() {
        return this.nrCEP;
    }

    public void setEnderecoVO(EnderecoVO enderecoVO) {
        this.enderecoVO = enderecoVO;
    }

    public EnderecoVO getEnderecoVO() {
        return this.enderecoVO;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getRua() {
        return this.rua;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNumero() {
        return this.numero;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getComplemento() {
        return this.complemento;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getBairro() {
        return this.bairro;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCidade() {
        return this.cidade;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return this.estado;
    }

}
