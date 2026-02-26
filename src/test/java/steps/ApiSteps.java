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
 */
public class ApiSteps {

    private String baseUrl;
    private Response response;

    @Dado("que a API está disponível em {string}")
    public void queAApiEstaDisponivelEm(String url) {
        this.baseUrl = url;
        RestAssured.baseURI = url;
    }

    @Quando("eu faço uma requisição GET em {string}")
    public void euFacoUmaRequisicaoGETEm(String endpoint) {
        response = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .log().all()
                .when()
                .get(endpoint)
                .then()
                .log().all()
                .extract().response();
    }

    @Quando("eu envio uma requisição POST em {string} com os dados:")
    public void euEnvioUmaRequisicaoPOSTEmComOsDados(String endpoint, DataTable dataTable) {
        List<Map<String, String>> dados = dataTable.asMaps();
        Map<String, String> linha = dados.get(0);
        String body = String.format(
                "{\"name\": \"%s\", \"job\": \"%s\"}",
                linha.get("nome"), linha.get("cargo"));
        response = RestAssured
                .given()
                .header("Content-Type", "application/json")
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
                .body(body)
                .log().all()
                .when()
                .put(endpoint)
                .then()
                .log().all()
                .extract().response();
    }

    @Então("o status da resposta deve ser {int}")
    public void oStatusDaRespostaDeveSerInt(int statusCode) {
        Assertions.assertEquals(statusCode, response.getStatusCode(),
                "Status HTTP esperado: " + statusCode + " | Real: " + response.getStatusCode());
    }

    @Então("a resposta deve conter a lista de usuários")
    public void aRespostaDeveConterAListaDeUsuarios() {
        List<?> lista = response.jsonPath().getList("data");
        Assertions.assertNotNull(lista, "O campo 'data' não deve ser nulo");
        Assertions.assertFalse(lista.isEmpty(), "A lista de usuários não deve estar vazia");
    }

    @Então("a resposta deve conter o campo {string} com valor {string}")
    public void aRespostaDeveConterOCampoComValor(String campo, String valor) {
        Object valorReal = response.jsonPath().get(campo);
        Assertions.assertNotNull(valorReal, "Campo '" + campo + "' não encontrado na resposta");
        Assertions.assertEquals(valor, String.valueOf(valorReal),
                "Campo '" + campo + "' esperado: '" + valor + "' | Real: '" + valorReal + "'");
    }
}
