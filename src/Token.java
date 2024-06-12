import java.util.Objects;

public class Token {

    private String nome;
    private TipoToken tipoToken;
    private Integer index;

    public Token(String nome, TipoToken tipoToken, Integer index) {
        this.nome = nome;
        this.tipoToken = tipoToken;
        this.index = index;
    }

    public String getNome() {
        return nome;
    }

    public TipoToken getTipoToken() {
        return tipoToken;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return String.format("%-10s %-20s %-17s %s", nome, tipoToken, tipoToken.getTipo(), Objects.isNull(index) ? "" : index);
    }

}
