package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;


    // DTO - Data transfer object
    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados) { // @RequestBody para resgatar o corpo da requisição, @Valid para o bean validation executar as operações em cima do DTO DadosCadastroMedico
            repository.save(new Medico(dados)); // repository fica responsável por salvar
    }

    @GetMapping
    public Page<DadosListagemMedico> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new); // repository extende jpa repository que pega todos os dados de médico, mas dá um
        // erro pois não queremos todos os dados. então, faz se o map do novo Record criado para apenas esses dados
        // o método findall() possui uma sobrecarga que recebe o Pageable
        // @PageableDefault(size = 10,  sort = {"nome"}) se não for informado os parâmetros de paginação, exibir por padrão 10, ordenados pelo nome
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid AtualizacaoCadastroMedico dados) {
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
;   }

    @DeleteMapping("/{id}") // parâmetro dinâmico
    @Transactional
    public void excluir(@PathVariable Long id) { // anotação que indica que a variável é do caminho da url
        var medico = repository.getReferenceById(id);
        medico.excluir();
    }


}
