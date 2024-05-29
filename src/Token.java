import java.util.Objects;

public class Token {

    String nome;
    TipoToken tipoToken;

    public Token(String nome, TipoToken tipoToken) {
        this.nome = nome;
        this.tipoToken = tipoToken;
    }

    @Override
    public String toString() {
        return nome + " (" + tipoToken + ")";
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Token token = (Token) object;
        return Objects.equals(nome, token.nome) && tipoToken == token.tipoToken;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, tipoToken);
    }
}
