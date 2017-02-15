package br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP;

import br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xsd.consultaArvoreAssuntos.output.AssuntoType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AssuntoBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private String codigo;
    private String tipo;
    private String descricao;
    private List campos;

    public AssuntoBean() {
        campos = new ArrayList();
    }

    public AssuntoBean(AssuntoType ass) {
        this();
        setCodigo(ass.getCodigo());
        setTipo(ass.getTipo());
        setDescricao(ass.getDescricao());
    }

    public String[] gerarValorDetalhe() {
        int size = campos.size();
        ArrayList array = new ArrayList();
        int j = 0;
        for (int i = 0; i < size; i++) {
            CampoBean campo = (CampoBean) campos.get(i);
            if ("T".equalsIgnoreCase(campo.getTipo()) || "A".equalsIgnoreCase(campo.getTipo())) {
                campo.getValorSelecionado();
                array.add(j++, campo.getValorSelecionado());
            } else if ("C".equalsIgnoreCase(campo.getTipo())
                    || "U".equalsIgnoreCase(campo.getTipo())) {
                if (campo.getValores() != null) {
                    Iterator itVal = campo.getValores().iterator();
                    boolean achou = false;
                    while (itVal != null && itVal.hasNext()) {
                        ValorBean valor = (ValorBean) itVal.next();
                        if (campo.getValorSelecionado() != null && campo.getValorSelecionado().equals(valor.getCodigo())) {
                            array.add(j++, valor.getDescricao());
                            achou = true;
                            break;
                        }
                    }
                    if (!achou) {
                        array.add(j++, campo.getValorSelecionado());
                    }
                } else {
                    array.add(j++, campo.getValorSelecionado());
                }
            }
        }
        Object obj[] = array.toArray();
        String[] result = new String[obj.length];
        for (int i = 0; i < obj.length; i++) {
            result[i] = (String) obj[i];
        }
        return result;
    }

    /**
     * @description validar os campos da arvore
     */
    public boolean validarValorDetalhe() {
        boolean valida = true;
        int size = campos.size();
        for (int i = 0; i < size; i++) {
            CampoBean campo = (CampoBean) campos.get(i);
            if ("T".equalsIgnoreCase(campo.getTipo()) || "A".equalsIgnoreCase(campo.getTipo())) {
                if (campo.getValorSelecionado() == null || campo.getValorSelecionado().trim().length() == 0) {
                    campo.setNulo(true);
                    return false;
                }
            } else if ("C".equalsIgnoreCase(campo.getTipo()) || "U".equalsIgnoreCase(campo.getTipo())) {
                if (campo.getValores() != null) {
                    Iterator itVal = campo.getValores().iterator();
                    boolean achou = false;
                    while (itVal != null && itVal.hasNext()) {
                        ValorBean valor = (ValorBean) itVal.next();
                        if (campo.getValorSelecionado() != null && campo.getValorSelecionado().equals(valor.getCodigo())) {
                            achou = true;
                            break;
                        }
                    }
                    if (!achou) {
                        campo.setNulo(true);
                        return false;
                    }
                } else {
                    campo.setNulo(true);
                    return false;
                }
            }
        }
        return valida;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String valor) {
        codigo = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String valor) {
        tipo = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String valor) {
        descricao = valor;
    }

    public List getCampos() {
        return campos;
    }

    public void setCampos(List campos) {
        this.campos = campos;
    }

    public void addCampo(CampoBean campo) {
        campos.add(campo);
    }
}
