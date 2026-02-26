package utils;

/**
 * Utilitários de string para uso geral
 * Projeto: selenium-java-bdd
 * Testado via JUnit5 em: unit/StringUtilsTest.java
 */
public class StringUtils {

    private StringUtils() {
        // Classe utilitária — não deve ser instanciada
    }

    /**
     * Verifica se uma string é nula ou vazia (incluindo apenas espaços em branco)
     */
    public static boolean isNulaOuVazia(String valor) {
        return valor == null || valor.trim().isEmpty();
    }

    /**
     * Capitaliza a primeira letra de uma string
     */
    public static String capitalizarPrimeira(String valor) {
        if (isNulaOuVazia(valor))
            return valor;
        return Character.toUpperCase(valor.charAt(0)) + valor.substring(1).toLowerCase();
    }

    /**
     * Mascara parcialmente um e-mail para exibição (ex: jo***@gmail.com)
     */
    public static String mascararEmail(String email) {
        if (isNulaOuVazia(email))
            return email;
        int arroba = email.indexOf('@');
        if (arroba <= 0)
            return email;
        String local = email.substring(0, arroba);
        String dominio = email.substring(arroba);
        if (local.length() <= 2)
            return local + "***" + dominio;
        return local.substring(0, 2) + "***" + dominio;
    }

    /**
     * Remove caracteres não numéricos de uma string
     */
    public static String apenasNumeros(String valor) {
        if (isNulaOuVazia(valor))
            return "";
        return valor.replaceAll("[^0-9]", "");
    }

    /**
     * Conta palavras em uma string
     */
    public static int contarPalavras(String texto) {
        if (isNulaOuVazia(texto))
            return 0;
        return texto.trim().split("\\s+").length;
    }

    /**
     * Trunca uma string ao tamanho máximo e adiciona reticências
     */
    public static String truncar(String texto, int tamanhoMax) {
        if (isNulaOuVazia(texto))
            return texto;
        if (texto.length() <= tamanhoMax)
            return texto;
        return texto.substring(0, tamanhoMax) + "...";
    }
}
