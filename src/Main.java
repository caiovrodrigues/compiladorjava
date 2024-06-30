import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        File file = new File("static/codigo.txt");

        StringBuilder sb = new StringBuilder();

        try(Scanner sc = new Scanner(file)){
            while(sc.hasNext()){
                sb.append(sc.nextLine()).append("\r\n");
            }
//            System.out.println(sb);

        }catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Map<String, TipoToken> palavrasReservadas = new HashMap<>();
        palavrasReservadas.put("identificador", TipoToken.IDENTIFICADOR);
        palavrasReservadas.put("program", TipoToken.PALAVRA_CHAVE);
        palavrasReservadas.put("procedure", TipoToken.PALAVRA_CHAVE);
        palavrasReservadas.put("begin", TipoToken.PALAVRA_CHAVE);
        palavrasReservadas.put("end", TipoToken.PALAVRA_CHAVE);
        palavrasReservadas.put("readln", TipoToken.PALAVRA_CHAVE);
        palavrasReservadas.put("writeln", TipoToken.PALAVRA_CHAVE);

        palavrasReservadas.put("var", TipoToken.DECL_VARIAVEL);

        palavrasReservadas.put("integer", TipoToken.TIPO_INT);
        palavrasReservadas.put("real", TipoToken.TIPO_REAL);
        palavrasReservadas.put("boolean", TipoToken.TIPO_BOOLEAN);

        palavrasReservadas.put(":=", TipoToken.SIMB_ATRIBUICAO);
        palavrasReservadas.put("=", TipoToken.SIMB_IGUALDADE);
        palavrasReservadas.put(";", TipoToken.SIMB);
        palavrasReservadas.put("(", TipoToken.SIMB_ABRE_PAR);
        palavrasReservadas.put(")", TipoToken.SIMB_FECHA_PAR);

        palavrasReservadas.put("if", TipoToken.CMD_CONDICAO);
        palavrasReservadas.put("then", TipoToken.CMD_CONDICAO);
        palavrasReservadas.put("else", TipoToken.CMD_CONDICAO);

        palavrasReservadas.put("while", TipoToken.CMD_REPETICAO);
        palavrasReservadas.put("do", TipoToken.CMD_REPETICAO);

        palavrasReservadas.put("<>", TipoToken.SIMB);
        palavrasReservadas.put("<", TipoToken.SIMB_MENOR_QUE);
        palavrasReservadas.put("<=", TipoToken.SIMB_MENOR_IGUAL);
        palavrasReservadas.put(">=", TipoToken.SIMB_MAIOR_IGUAL);
        palavrasReservadas.put(">", TipoToken.SIMB_MAIOR_QUE);

        palavrasReservadas.put("+", TipoToken.OPERADOR_MAIS);
        palavrasReservadas.put("-", TipoToken.OPERADOR_MENOS);
        palavrasReservadas.put("*", TipoToken.OPERADOR_MULT);
        palavrasReservadas.put("/", TipoToken.OPERADOR_DIV);

        palavrasReservadas.put("div", TipoToken.OPERADOR_DIV);
        palavrasReservadas.put("and", TipoToken.OPERADOR_LOGICO);
        palavrasReservadas.put("or", TipoToken.OPERADOR_LOGICO);
        palavrasReservadas.put("not", TipoToken.OPERADOR_NEGADO);

        var compilador = new Compilador(sb.toString(), palavrasReservadas);

        compilador.start();
    }
}