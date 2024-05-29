import java.util.Objects;

public class TokenError {

    String nome;
    TipoToken tipoToken;
    String descricao;

    public TokenError(String nome, TipoToken tipoToken, String descricao) {
        this.nome = nome;
        this.tipoToken = tipoToken;
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        TokenError that = (TokenError) object;
        return Objects.equals(nome, that.nome) && tipoToken == that.tipoToken;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, tipoToken);
    }

    @Override
    public String toString() {
        return nome + " (" + tipoToken + ")" + " - " + descricao;
    }
}
