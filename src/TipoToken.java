public enum TipoToken {
    PALAVRA_CHAVE("palavra chave"),
    IDENTIFICADOR("id"),
    SIMB("símbolo"),
    SIMB_MENOR_QUE("símbolo especial"),
    SIMB_MENOR_IGUAL("símbolo especial"),
    SIMB_MAIOR_IGUAL("símbolo especial"),
    SIMB_MAIOR_QUE("símbolo especial"),
    SIMB_ATRIBUICAO("símbolo especial"),
    SIMB_ABRE_PAR("símbolo especial"),
    SIMB_FECHA_PAR("símbolo especial"),
    DECL_VARIAVEL("var"),
    TIPO_INT("tipo var"),
    TIPO_REAL("tipo var"),
    TIPO_BOOLEAN("tipo var"),
    NUM_INT("num"),
    NUM_REAL("num"),
    CMD_CONDICAO("símbolo condição"),
    CMD_REPETICAO("símbolo condição"),
    OPERADOR_MAIS("operador aritmético"),
    OPERADOR_MENOS("operador aritmético"),
    OPERADOR_MULT("operador aritmético"),
    OPERADOR_DIV("operador aritmético"),
    OPERADOR_LOGICO("operador lógico"),
    OPERADOR_NEGADO("operador lógico"),
    ERRO("error");

    private String tipo;

    TipoToken(String tipo){
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
}
