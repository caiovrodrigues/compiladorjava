import java.util.*;

public class Compilador {

    Map<String, TipoToken> palavrasReservadas;
    List<Token> tokens = new ArrayList<>();
    Set<TokenError> tokenErros = new HashSet<>();
    String codigo;

    public Compilador(String codigo, Map<String, TipoToken> palavrasReservadas) {
        this.codigo = codigo;
        this.palavrasReservadas = palavrasReservadas;
    }

    public void start(){

        int i = 0;

        while(i < codigo.length()){

            if(isLetra(codigo.charAt(i))){
                int comecoPalavra = i;
                while(codigo.charAt(i) != ' '){
                    i++;
                }

                gerarToken(codigo.substring(comecoPalavra, i));
            }
            else if(isSimbolo(codigo.charAt(i))){
                int comecoSimbolo = i;
                while(codigo.charAt(i) != ' '){
                    i++;
                }

                gerarToken(codigo.substring(comecoSimbolo, i));
            }
            else if(isNumero(codigo.charAt(i))){
                int start = i;
                while((isNumero(codigo.charAt(i)) || codigo.charAt(i) == ',' || isLetra(codigo.charAt(i))) && !isSimbolo(codigo.charAt(i))){
                    i++;
                }

                String substring = codigo.substring(start, i);

                List<Character> letras = new ArrayList<>(Arrays.asList('a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'));

                boolean sair = false;
                for(int k = 1; k < substring.length(); k++){
                    if(letras.contains(substring.charAt(k))){
                        tokenErros.add(new TokenError(substring, TipoToken.ERRO, String.format("Coluna[%d] identificador mal formado ", start)));
                        sair = true;
                    }
                }

                if(sair){
                    continue;
                }

                int iVirgula = substring.indexOf(",");

                if(iVirgula == -1){
                    tokens.add(new Token(substring, TipoToken.NUM_INT));
                }
                else if(isNumero(substring.charAt(substring.length() - 1))){
                    tokens.add(new Token(substring, TipoToken.NUM_REAL));
                }
            }
            i++;
        }

        for (Token token : tokens) {
            System.out.println(token);
        }

        if(!tokenErros.isEmpty()){
            System.out.println("\nErros");
            tokenErros.forEach(System.out::println);
        }

    }

    public boolean isLetra(char letra){
        List<Character> letras = new ArrayList<>(Arrays.asList('a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'));
        return letras.contains(letra);
    }

    public boolean isSimbolo(char simbolo){
        List<Character> simbolos = new ArrayList<>(Arrays.asList(':', ';', '.', ',', '(', ')', '<', '>', '=', '+', '-', '*', '/'));
        return simbolos.contains(simbolo);
    }

    public boolean isNumero(char numero){
        List<Character> numeros = new ArrayList<>(Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9'));
        return numeros.contains(numero);
    }

    public void gerarToken(String palavra){
        if(palavrasReservadas.containsKey(palavra)){
            TipoToken tipoToken = palavrasReservadas.get(palavra);
            tokens.add(new Token(palavra, tipoToken));
        }else{
            TipoToken tipoToken = palavrasReservadas.get("identificador");
            tokens.add(new Token(palavra, tipoToken));
        }
    }
}
