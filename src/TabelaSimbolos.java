import java.util.Objects;

public class TabelaSimbolos {
    private static int count = 0;
    private Integer index;
    private String valor;

    public TabelaSimbolos(String valor) {
        this.index = ++count;
        this.valor = valor;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return String.format("%-10s %-20s", index, valor);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        TabelaSimbolos simbolos = (TabelaSimbolos) object;
        return Objects.equals(valor, simbolos.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor);
    }
}
