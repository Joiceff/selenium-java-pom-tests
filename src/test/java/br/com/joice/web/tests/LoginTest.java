package br.com.joice.web.tests;

import br.com.joice.web.support.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Login")
class LoginTest extends BaseTest {

    @Test
    @Tag("smoke")
    @DisplayName("Usuário válido acessa a página de produtos")
    void loginComSucesso() {
        String titulo = loginPage.entrarCom("standard_user", SENHA).titulo();
        assertEquals("Products", titulo);
    }

    @ParameterizedTest(name = "[{index}] usuário=''{0}'' → {2}")
    @DisplayName("Login inválido exibe mensagem de erro")
    @CsvSource({
            "locked_out_user, secret_sauce, 'Sorry, this user has been locked out.'",
            "standard_user,   senha_errada, Username and password do not match any user in this service",
            "'',              secret_sauce, Username is required",
            "standard_user,   '',           Password is required"
    })
    void loginInvalido(String usuario, String senha, String mensagemEsperada) {
        String erro = loginPage.tentarEntrar(usuario, senha).mensagemDeErro();
        assertTrue(erro.contains(mensagemEsperada), "Mensagem obtida: " + erro);
    }
}
