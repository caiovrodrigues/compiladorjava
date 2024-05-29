import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        Map<String, TipoToken> palavrasReservadas = new HashMap<>();
        palavrasReservadas.put("identificador", TipoToken.IDENTIFICADOR);
        palavrasReservadas.put("program", TipoToken.PALAVRA_CHAVE);
        palavrasReservadas.put("procedure", TipoToken.PALAVRA_CHAVE);
        palavrasReservadas.put("begin", TipoToken.PALAVRA_CHAVE);
        palavrasReservadas.put("end", TipoToken.PALAVRA_CHAVE);

        palavrasReservadas.put("var", TipoToken.DECL_VARIAVEL);

        palavrasReservadas.put("integer", TipoToken.TIPO_INT);
        palavrasReservadas.put("real", TipoToken.TIPO_REAL);
        palavrasReservadas.put("boolean", TipoToken.BOOLEAN);

        palavrasReservadas.put(":=", TipoToken.SIMB_ATRIBUICAO);
        palavrasReservadas.put("=", TipoToken.SIMB_ATRIBUICAO);
        palavrasReservadas.put(";", TipoToken.SIMB);
        palavrasReservadas.put("(", TipoToken.SIMB_ABRE_PAR);
        palavrasReservadas.put(")", TipoToken.SIMB_FECHA_PAR);

        palavrasReservadas.put("if", TipoToken.CMD_CONDICAO);
        palavrasReservadas.put("then", TipoToken.CMD_CONDICAO);
        palavrasReservadas.put("else", TipoToken.CMD_CONDICAO);

        palavrasReservadas.put("while", TipoToken.CMD_REPETICAO);
        palavrasReservadas.put("do", TipoToken.CMD_REPETICAO);

        palavrasReservadas.put("<>", TipoToken.SIMB);
        palavrasReservadas.put("<", TipoToken.SIMB);
        palavrasReservadas.put("<=", TipoToken.SIMB);
        palavrasReservadas.put(">=", TipoToken.SIMB);
        palavrasReservadas.put(">", TipoToken.SIMB);

        palavrasReservadas.put("+", TipoToken.OPERADOR);
        palavrasReservadas.put("-", TipoToken.OPERADOR);
        palavrasReservadas.put("*", TipoToken.OPERADOR_MULT);
        palavrasReservadas.put("/", TipoToken.OPERADOR_DIV);

        palavrasReservadas.put("div", TipoToken.OPERADOR_DIV);
        palavrasReservadas.put("and", TipoToken.OPERADOR_LOGICO);
        palavrasReservadas.put("or", TipoToken.OPERADOR_LOGICO);
        palavrasReservadas.put("not", TipoToken.OPERADOR_NEGADO);

        var compilador = new Compilador("integer tst = 49; ( ) ", palavrasReservadas);

        compilador.start();
    }
}