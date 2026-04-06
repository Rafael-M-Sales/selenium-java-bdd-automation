package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.pt.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

import java.util.List;
import java.util.Map;

/**
 * Step Definitions: API
 * Projeto: selenium-java-bdd
 * 
 * Este arquivo utiliza o framework REST Assured para realizar testes de API de
 * forma fluida.
 */
public class ApiSteps {

    private String baseUrl;
    private Response response;
    private final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36";

    /**
     * Configura a URL base para todas as chamadas seguintes.
     */
    @Dado("que a API está disponível em {string}")
    public void queAApiEstaDisponivelEm(String url) {
        this.baseUrl = url;
        RestAssured.baseURI = url;
    }

    /**
     * Realiza uma chamada GET simples e extrai a resposta para validação.
     */
    @Quando("eu faço uma requisição GET em {string}")
    public void euFacoUmaRequisicaoGETEm(String endpoint) {
        response = RestAssured
                .given() // Preparação (Headers, Parametros)
                .header("Content-Type", "application/json")
                .header("User-Agent", USER_AGENT)
                .log().all() // Loga a requisição no console
                .when() // Execução
                .get(endpoint)
                .then() // Verificações rápidas ou Log da resposta
                .log().all()
                .extract().response(); // Salva a resposta na variável
    }

    /**
     * Realiza uma chamada POST usando DataTable (Tabela do Gherkin).
     */
    @Quando("eu envio uma requisição POST em {string} com os dados:")
    public void euEnvioUmaRequisicaoPOSTEmComOsDados(String endpoint, DataTable dataTable) {
        // Converte a tabela do Gherkin em uma lista de mapas em Java.
        List<Map<String, String>> dados = dataTable.asMaps();
        Map<String, String> linha = dados.get(0);

        // Montamos o JSON de corpo (Body) da requisição.
        String body = String.format(
                "{\"name\": \"%s\", \"job\": \"%s\"}",
                linha.get("nome"), linha.get("cargo"));

        response = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .header("User-Agent", USER_AGENT)
                .body(body)
                .log().all()
                .when()
                .post(endpoint)
                .then()
                .log().all()
                .extract().response();
    }

    @Quando("eu envio uma requisição PUT em {string} com os dados:")
    public void euEnvioUmaRequisicaoPUTEmComOsDados(String endpoint, DataTable dataTable) {
        List<Map<String, String>> dados = dataTable.asMaps();
        Map<String, String> linha = dados.get(0);
        String body = String.format(
                "{\"name\": \"%s\", \"job\": \"%s\"}",
                linha.get("nome"), linha.get("cargo"));
        response = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .header("User-Agent", USER_AGENT)
                .body(body)
                .log().all()
                .when()
                .put(endpoint)
                .then()
                .log().all()
                .extract().response();
    }

    /**
     * Valida o código de status HTTP retornado pelo servidor.
     */
    @Então("o status da resposta deve ser {int}")
    public void oStatusDaRespostaDeveSerInt(int statusCode) {
        Assertions.assertEquals(statusCode, response.getStatusCode(),
                "Status HTTP esperado: " + statusCode + " | Real: " + response.getStatusCode());
    }

    /**
     * Valida o corpo da resposta usando JsonPath.
     */
    @Então("a resposta deve conter a lista de usuários")
    public void aRespostaDeveConterAListaDeUsuarios() {
        List<?> lista = response.jsonPath().getList("data");
        Assertions.assertNotNull(lista, "O campo 'data' não deve ser nulo");
        Assertions.assertFalse(lista.isEmpty(), "A lista de usuários não deve estar vazia");
    }

    /**
     * Valida valores específicos dentro do JSON retornado.
     */
    @Então("a resposta deve conter o campo {string} com valor {string}")
    public void aRespostaDeveConterOCampoComValor(String campo, String valor) {
        Object valorReal = response.jsonPath().get(campo);
        Assertions.assertNotNull(valorReal, "Campo '" + campo + "' não encontrado na resposta");
        Assertions.assertEquals(valor, String.valueOf(valorReal),
                "Campo '" + campo + "' esperado: '" + valor + "' | Real: '" + valorReal + "'");
    }
}
