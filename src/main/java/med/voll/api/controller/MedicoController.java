package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.endereco.Endereco;
import med.voll.api.medico.DadosCadastroMedico;
import med.voll.api.medico.DadosListagemMedico;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<DadosListagemMedico> listar() {
        return repository.findAll().stream().map(DadosListagemMedico::new).toList(); // repository extende jpa repository que pega todos os dados de médico, mas dá um
        // erro pois não queremos todos os dados. então, faz se o map do novo Record criado para apenas esses dados
    }

}
