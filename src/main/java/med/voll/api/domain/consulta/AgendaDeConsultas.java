package med.voll.api.domain.consulta;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;
    public void agendar(DadosAgendamentoConsulta dados) {
        // checagem dos dados
        if (!pacienteRepository.existsById(dados.idPaciente())) { // o repository possui o método que retorna se existe esse id no banco
            throw new ValidacaoException("Id do paciente informado não existe");
        }

        if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
            throw new ValidacaoException("Id do paciente informado não existe");
        }

        var paciente = pacienteRepository.findById(dados.idPaciente()).get(); // usando repository pois nos dados vem apenas o id do paciente
        var medico = medicoRepository.findById(dados.idMedico()).get(); // findById retorna um Optional então por isso é necessário o .get()
        var consulta = new Consulta(null, medico, paciente, dados.data());

        consultaRepository.save(consulta);
    }

}
