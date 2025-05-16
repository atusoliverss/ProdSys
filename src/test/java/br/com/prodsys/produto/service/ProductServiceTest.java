package br.com.prodsys.produto.service;

import br.com.prodsys.product.entities.Product;
import br.com.prodsys.product.repository.ProductIRepository;
import br.com.prodsys.product.service.ProductService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Classe de teste para o serviço de Produto (ProductService).
 * Testa os métodos de CRUD e a funcionalidade de paginação.
 */
public class ProductServiceTest {

    // Mock do repositório de produtos para simular interações com o banco de dados
    @Mock
    private ProductIRepository productIRepository;

    // Injeção do service, onde o repositório mockado será utilizado
    @InjectMocks
    private ProductService productService;

    // Objeto de produto usado nos testes
    private Product product;

    // Objeto AutoCloseable para fechar os mocks após os testes
    private AutoCloseable closeable;

    /**
     * Método executado antes de cada teste.
     * Inicializa os mocks e define um produto padrão para os testes.
     */
    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        product = new Product(1L, "Produto 1", "Descrição 1", 10.0, 100);
    }

    /**
     * Teste para o método findAll.
     * Simula a busca de produtos com paginação e verifica se o retorno está correto.
     */
    @Test
    void findAll() {
        // Criação do pageable com tamanho 10 e página inicial 0
        Pageable pageable = PageRequest.of(0, 10);
        List<Product> products = new ArrayList<>();
        products.add(product);

        // Criação de uma página de produtos com um único produto
        Page<Product> page = new PageImpl<>(products);

        // Definindo o comportamento esperado do repositório mockado
        when(productIRepository.findAll(pageable)).thenReturn(page);

        // Chamando o método e verificando os resultados
        Page<Product> result = productService.findAll(pageable);
        assertEquals(1, result.getTotalElements()); // Verifica o total de produtos
        assertEquals("Produto 1", result.getContent().get(0).getName()); // Verifica o nome do produto
        verify(productIRepository, times(1)).findAll(pageable); // Verifica se o método foi chamado uma vez
    }

    /**
     * Teste para o método save.
     * Simula a persistência de um produto e verifica se os dados estão corretos.
     */
    @Test
    void save() {
        when(productIRepository.save(product)).thenReturn(product);

        Product result = productService.save(product);
        assertEquals("Produto 1", result.getName()); // Verifica o nome do produto salvo
        verify(productIRepository, times(1)).save(product); // Verifica a chamada ao método save
    }

    /**
     * Teste para o método update.
     * Simula a atualização de um produto e verifica se os dados atualizados estão corretos.
     */
    @Test
    void update() {
        when(productIRepository.save(product)).thenReturn(product);

        Product result = productService.update(product);
        assertEquals("Produto 1", result.getName());
        verify(productIRepository, times(1)).save(product);
    }

    /**
     * Teste para o método delete.
     * Simula a exclusão de um produto e verifica a interação com o repositório.
     */
    @Test
    void delete() {
        doNothing().when(productIRepository).delete(product);

        productService.delete(product);
        verify(productIRepository, times(1)).delete(product);
    }

    /**
     * Método executado após cada teste para fechar os mocks e liberar recursos.
     */
    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }
}