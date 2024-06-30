import java.util.*;
import java.util.stream.Collectors;

public class Compilador {

    Map<String, TipoToken> palavrasReservadas;
    Set<TabelaSimbolos> tabelaSimbolos = new HashSet<>();
    List<Token> tokens = new ArrayList<>();
    Set<TokenError> tokenErros = new HashSet<>();
    String codigo;

    public Compilador(String codigo, Map<String, TipoToken> palavrasReservadas) {
        this.codigo = codigo;
        this.palavrasReservadas = palavrasReservadas;
    }

    public void start(){

        int i = 0;

        String[] linhas = codigo.trim().split("\r\n");

        int length = linhas[0].length();

//        System.out.println(length);
        int linhaPos = 0;

        for (String linha : linhas) {
            linhaPos++;

            i = 0;
            while (i < linha.length()) {

                if (isLetra(linha.charAt(i))) {
                    int comecoPalavra = i;
                    while(i < linha.length() && (linha.charAt(i) != ' ' && linha.charAt(i) != ';' && linha.charAt(i) != ',' && linha.charAt(i) != ':' && linha.charAt(i) != '(' && linha.charAt(i) != ')' && linha.charAt(i) != '.')){
                        i++;
                    }
//                    while(i < linha.length() && (isLetra(linha.charAt(i)) || isNumero(linha.charAt(i)))){
//                        i++;
//                    }
                    String palavra = linha.substring(comecoPalavra, i);

                    boolean palavraAccepted = Arrays.stream(palavra.split("")).allMatch(letra -> isLetra(letra.charAt(0)) || isNumero(letra.charAt(0)));

                    if(!palavraAccepted){
                        erroLexico(palavra, comecoPalavra, linhaPos);
                        break;
                    }

                    gerarToken(palavra, i);
                } else if (isSimbolo(linha.charAt(i))) {
                    int start = i;
                    while (i < linha.length() && isSimbolo(linha.charAt(i))) {
                        i++;
                    }

                    String substring = linha.substring(start, i);

                    if (palavrasReservadas.containsKey(substring)) {
                        TipoToken tipoToken = palavrasReservadas.get(substring);
                        tokens.add(new Token(substring, tipoToken, null));
                    } else {
                        tokenErros.add(new TokenError(substring, TipoToken.ERRO, String.format("Linha [%d] Coluna[%d] este símbolo não é válido ", linhaPos, start)));
                    }
                } else if (isNumero(linha.charAt(i))) {
                    int start = i;
                    while (i < linha.length() && (isNumero(linha.charAt(i)) || linha.charAt(i) == '.' || isLetra(linha.charAt(i)))) {
                        i++;
                    }

                    String substring = linha.substring(start, i);

                    List<Character> letras = new ArrayList<>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'));

                    boolean sair = false;
                    for (int k = 1; k < substring.length(); k++) {
                        if (letras.contains(substring.charAt(k))) {
                            tokenErros.add(new TokenError(substring, TipoToken.ERRO, String.format("Linha [%d] Coluna[%d] identificador mal formado ", linhaPos, start)));
                            sair = true;
                        }
                    }

                    if (sair) {
                        continue;
                    }

                    int iVirgula = substring.indexOf(".");

                    if (iVirgula == -1) {
                        tokens.add(new Token(substring, TipoToken.NUM_INT, null));
                    } else if (isNumero(substring.charAt(substring.length() - 1))) {
                        tokens.add(new Token(substring, TipoToken.NUM_REAL, null));
                    } else {
                        tokenErros.add(new TokenError(substring, TipoToken.ERRO, String.format("Linha [%d] Coluna[%d] número real mal formado ", linhaPos, start)));
                    }
                }
                i++;
            }
        }

        System.out.println("------------ TABELA DE SÍMBOLOS ------------");
        System.out.printf("%-10s %-20s\n", "(INDEX)", "(VALOR)");
        for (TabelaSimbolos simbolos : tabelaSimbolos) {
            System.out.println(simbolos);
        }

        System.out.println("\n------------ LISTA DE TOKENS ------------");
        System.out.printf("%-10s %-20s %-17s %s\n", "(CADEIA)", "(TOKEN)", "(TIPO)", "(INDEX)");
        for (Token token : tokens) {
            System.out.println(token);
        }

        if(!tokenErros.isEmpty()){
            System.out.println("\nErros");
            tokenErros.forEach(System.out::println);
        }else{
            System.out.println("\nSEU CÓDIGO NÃO POSSUI ERROS LÉXICOS");
            analiseSintatica();
        }
    }

    private void erroLexico(String substring, int index, int linhaPos) {
        tokenErros.add(new TokenError(substring, TipoToken.ERRO, String.format("Linha [%d] Coluna[%d] palavra mal formada ", index, linhaPos)));
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

    public void gerarToken(String palavra, Integer coluna){
        Optional<TabelaSimbolos> tabSimb = tabelaSimbolos.stream().filter(simb -> simb.getValor().equals(palavra)).findFirst();

        if(palavrasReservadas.containsKey(palavra)){
            TipoToken tipoToken = palavrasReservadas.get(palavra);
            tokens.add(new Token(palavra, tipoToken, null));
        }else{
            TipoToken tipoToken = palavrasReservadas.get("identificador");
            if(tabSimb.isPresent()){
                tokens.add(new Token(palavra, tipoToken, tabSimb.get().getIndex()));
            }else{
                TabelaSimbolos newSimbolo = new TabelaSimbolos(palavra);
                tokens.add(new Token(palavra, tipoToken, newSimbolo.getIndex()));
                tabelaSimbolos.add(newSimbolo);
            }
        }
    }

    private void mostrarErros(){
        if(!tokenErros.isEmpty()){
            System.out.println("\nErros");
            tokenErros.forEach(System.out::println);
        }else{
            System.out.println("\nSEU CÓDIGO NÃO POSSUI ERROS LÉXICOS");
            analiseSintatica();
        }
    }

    public void analiseSintatica(){

        int abrePar = 0;
        int fechaPar = 0;

        for(int i = 0; i < tokens.size(); i++){

//            if(tokens.get(i).getTipoToken().equals(TipoToken.IDENTIFICADOR)){
//
//                if(i == 0){
//                    System.out.printf("ERRO SINTÁTICO NA COLUNA[%d] É NECESSÁRIO INFORMAR O TIPO DO IDENTIFICADOR ANTES DA SUA DECLARAÇÃO!\n", tokens.get(i).getIndex());
//                    break;
//                }
//
//                if(!tokens.get(i - 1).getTipoToken().name().startsWith("TIPO")){
//                    System.out.printf("ERRO SINTÁTICO NA COLUNA[%d] É NECESSÁRIO INFORMAR O TIPO DO IDENTIFICADOR ANTES DA SUA DECLARAÇÃO!\n", tokens.get(i).getIndex());
//                    break;
//                }
//            }
//
//            if(tokens.get(i).getTipoToken().equals(TipoToken.SIMB_ABRE_PAR)){
//                abrePar++;
//            }
//            if(tokens.get(i).getTipoToken().equals(TipoToken.SIMB_FECHA_PAR)){
//                fechaPar++;
//            }
        }

        if(abrePar != fechaPar){
            System.out.printf("ERRO SINTÁTICO. A QUANTIDADE DE ABRE PARÊNTESES É DIFERENTE DA DE FECHA PARÊNTESES\n");
        }
    }
}
