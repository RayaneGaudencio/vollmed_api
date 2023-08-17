package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/medicos")
@SecurityRequirement(name = "bearer-key")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;


    // DTO - Data transfer object
    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder) { // @RequestBody para resgatar o corpo da requisição, @Valid para o bean validation executar as operações em cima do DTO DadosCadastroMedico
        var medico = new Medico(dados);
        repository.save(medico); // repository fica responsável por salvar

        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedico>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new); // repository extende jpa repository que pega todos os dados de médico, mas dá um
        // erro pois não queremos todos os dados. então, faz se o map do novo Record criado para apenas esses dados
        // o método findall() possui uma sobrecarga que recebe o Pageable
        // @PageableDefault(size = 10,  sort = {"nome"}) se não for informado os parâmetros de paginação, exibir por padrão 10, ordenados pelo nome
       return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid AtualizacaoCadastroMedico dados) {
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

    @DeleteMapping("/{id}") // parâmetro dinâmico
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) { // anotação que indica que a variável é do caminho da url
        var medico = repository.getReferenceById(id);
        medico.excluir();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}") // parâmetro dinâmico
    public ResponseEntity detalhar(@PathVariable Long id) { // anotação que indica que a variável é do caminho da url
        var medico = repository.getReferenceById(id);

        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }
}
