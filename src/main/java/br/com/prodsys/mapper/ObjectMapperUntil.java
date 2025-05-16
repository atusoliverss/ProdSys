package br.com.prodsys.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe utilitária para conversão de objetos entre diferentes tipos usando ModelMapper.
 * É marcada com {@link Component}, permitindo sua injeção em outras classes do Spring.
 */
@Component
public class ObjectMapperUntil {

    // Instância única de ModelMapper usada para realizar as conversões.
    private static final ModelMapper MODEL_MAPPER;

    // Bloco estático para inicializar o ModelMapper.
    static {
        MODEL_MAPPER = new ModelMapper();
    }

    /**
     * Converte um objeto de um tipo para outro.
     *
     * @param object Objeto de entrada a ser convertido.
     * @param clazz Classe do tipo de saída desejado.
     * @param <Input> Tipo do objeto de entrada.
     * @param <Output> Tipo do objeto de saída.
     * @return Objeto convertido para o tipo especificado.
     */
    public <Input, Output> Output map(final Input object, final Class<Output> clazz) {
        // Configuração do ModelMapper para um mapeamento mais rigoroso e preciso.
        MODEL_MAPPER.getConfiguration()
                .setAmbiguityIgnored(true) // Ignora ambiguidades no mapeamento.
                .setMatchingStrategy(MatchingStrategies.STRICT) // Estratégia estrita para correspondência de campos.
                .setFieldMatchingEnabled(true) // Habilita correspondência de campos por nome.
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE); // Permite acessar campos privados.

        // Realiza a conversão e retorna o objeto convertido.
        return MODEL_MAPPER.map(object, clazz);
    }

    /**
     * Converte uma lista de objetos de um tipo para outro.
     *
     * @param list Lista de objetos a serem convertidos.
     * @param clazz Classe do tipo de saída desejado.
     * @param <Input> Tipo dos objetos de entrada.
     * @param <Output> Tipo dos objetos de saída.
     * @return Lista de objetos convertidos para o tipo especificado.
     */
    public <Input, Output> List<Output> mapAll(final List<Input> list, final Class<Output> clazz) {
        // Mapeia cada elemento da lista usando o método 'map' e retorna uma nova lista convertida.
        return list.stream()
                .map(item -> this.map(item, clazz))
                .collect(Collectors.toList());
    }
}
