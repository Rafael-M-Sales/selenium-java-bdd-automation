package unit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import utils.StringUtils;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes Unitários - StringUtils
 * Projeto: selenium-java-bdd
 * Framework: JUnit 5
 */
@DisplayName("StringUtils - Utilitários de String")
class StringUtilsTest {

    // ===== isNulaOuVazia =====

    @Test
    @DisplayName("Deve retornar true para string nula")
    void deveRetornarTrueParaStringNula() {
        assertTrue(StringUtils.isNulaOuVazia(null));
    }

    @Test
    @DisplayName("Deve retornar true para string vazia")
    void deveRetornarTrueParaStringVazia() {
        assertTrue(StringUtils.isNulaOuVazia(""));
    }

    @Test
    @DisplayName("Deve retornar true para string apenas com espaços")
    void deveRetornarTrueParaStringComEspacos() {
        assertTrue(StringUtils.isNulaOuVazia("   "));
    }

    @Test
    @DisplayName("Deve retornar false para string com conteúdo")
    void deveRetornarFalseParaStringComConteudo() {
        assertFalse(StringUtils.isNulaOuVazia("automação"));
    }

    // ===== capitalizarPrimeira =====

    @Test
    @DisplayName("Deve capitalizar a primeira letra")
    void deveCapitalizarAPrimeiraLetra() {
        assertEquals("Hello", StringUtils.capitalizarPrimeira("hello"));
    }

    @Test
    @DisplayName("Deve converter restante para minúsculas")
    void deveConverterRestoParaMinusculas() {
        assertEquals("World", StringUtils.capitalizarPrimeira("WORLD"));
    }

    @Test
    @DisplayName("Deve retornar string vazia para entrada vazia")
    void deveRetornarVazioParaEntradaVazia() {
        assertEquals("", StringUtils.capitalizarPrimeira(""));
    }

    // ===== mascararEmail =====

    @Test
    @DisplayName("Deve mascarar e-mail corretamente")
    void deveMascararEmailCorretamente() {
        assertEquals("jo***@gmail.com", StringUtils.mascararEmail("joao@gmail.com"));
    }

    @Test
    @DisplayName("Deve mascarar e-mail com local curto")
    void deveMascararEmailComLocalCurto() {
        assertEquals("ab***@gmail.com", StringUtils.mascararEmail("ab@gmail.com"));
    }

    // ===== apenasNumeros =====

    @Test
    @DisplayName("Deve remover caracteres não numéricos")
    void deveRemoverCaracteresNaoNumericos() {
        assertEquals("11999990000", StringUtils.apenasNumeros("(11) 99999-0000"));
    }

    @Test
    @DisplayName("Deve retornar vazio para string sem números")
    void deveRetornarVazioParaStringSemNumeros() {
        assertEquals("", StringUtils.apenasNumeros("abc"));
    }

    // ===== contarPalavras =====

    @Test
    @DisplayName("Deve contar palavras corretamente")
    void deveContarPalavrasCorretamente() {
        assertEquals(3, StringUtils.contarPalavras("automação de testes"));
    }

    @Test
    @DisplayName("Deve retornar 0 para string vazia")
    void deveRetornarZeroParaStringVazia() {
        assertEquals(0, StringUtils.contarPalavras(""));
    }

    @ParameterizedTest(name = "Deve retornar 0 para: [{0}]")
    @ValueSource(strings = { "", " ", "   " })
    @DisplayName("Deve retornar 0 para strings em branco")
    void deveRetornarZeroParaStringEmBranco(String texto) {
        assertEquals(0, StringUtils.contarPalavras(texto));
    }

    // ===== truncar =====

    @Test
    @DisplayName("Deve truncar string maior que o limite")
    void deveTruncarStringMaiorQueOLimite() {
        assertEquals("abcde...", StringUtils.truncar("abcdefgh", 5));
    }

    @Test
    @DisplayName("Não deve truncar string dentro do limite")
    void naoDeveTruncarStringDentroDoLimite() {
        assertEquals("abc", StringUtils.truncar("abc", 10));
    }

    @Test
    @DisplayName("Deve retornar nulo para entrada nula")
    void deveRetornarNuloParaEntradaNula() {
        assertNull(StringUtils.truncar(null, 5));
    }
}
