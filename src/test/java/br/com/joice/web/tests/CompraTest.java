package br.com.joice.web.tests;

import br.com.joice.web.pages.CheckoutPage;
import br.com.joice.web.pages.ProdutosPage;
import br.com.joice.web.support.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Carrinho, checkout e ordenação")
class CompraTest extends BaseTest {

    private ProdutosPage produtos;

    @BeforeEach
    void logar() {
        produtos = loginPage.entrarCom("standard_user", SENHA);
    }

    @Test
    @DisplayName("Adicionar e remover produtos atualiza o contador do carrinho")
    void adicionarERemoverDoCarrinho() {
        produtos.adicionarAoCarrinho("sauce-labs-backpack")
                .adicionarAoCarrinho("sauce-labs-bike-light");
        assertEquals(2, produtos.quantidadeNoCarrinho());

        produtos.removerDoCarrinho("sauce-labs-backpack");
        assertEquals(1, produtos.quantidadeNoCarrinho());
    }

    @Test
    @DisplayName("Produtos adicionados aparecem no carrinho")
    void produtosNoCarrinho() {
        List<String> itens = produtos.adicionarAoCarrinho("sauce-labs-backpack")
                .adicionarAoCarrinho("sauce-labs-onesie")
                .abrirCarrinho()
                .itens();
        assertEquals(List.of("Sauce Labs Backpack", "Sauce Labs Onesie"), itens);
    }

    @Test
    @Tag("smoke")
    @DisplayName("Compra completa é finalizada com sucesso")
    void compraCompleta() {
        CheckoutPage checkout = produtos.adicionarAoCarrinho("sauce-labs-backpack")
                .abrirCarrinho()
                .iniciarCheckout()
                .preencherDados("Joice", "QA", "14800-000");

        assertTrue(checkout.total().startsWith("Total: $"), "Total exibido: " + checkout.total());
        assertEquals("Thank you for your order!", checkout.finalizar().mensagemDeSucesso());
    }

    @Test
    @DisplayName("Checkout sem CEP exibe erro de validação")
    void checkoutSemCep() {
        String erro = produtos.adicionarAoCarrinho("sauce-labs-backpack")
                .abrirCarrinho()
                .iniciarCheckout()
                .preencherDados("Joice", "QA", "")
                .mensagemDeErro();
        assertTrue(erro.contains("Postal Code is required"), "Mensagem obtida: " + erro);
    }

    @Test
    @DisplayName("Ordenação por menor preço")
    void ordenarPorMenorPreco() {
        List<Double> precos = produtos.ordenarPor("lohi").precos();
        List<Double> esperado = new ArrayList<>(precos);
        Collections.sort(esperado);
        assertEquals(esperado, precos);
    }

    @Test
    @DisplayName("Ordenação por nome de Z a A")
    void ordenarPorNomeDecrescente() {
        List<String> nomes = produtos.ordenarPor("za").nomes();
        List<String> esperado = new ArrayList<>(nomes);
        esperado.sort(Collections.reverseOrder());
        assertEquals(esperado, nomes);
    }
}
