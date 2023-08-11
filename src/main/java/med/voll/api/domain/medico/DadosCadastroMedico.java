package med.voll.api.domain.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.endereco.DadosEndereco;

public record DadosCadastroMedico(

        // notações do bean validation
        @NotBlank // não pode ser nulo e vazio, somente para Strings
        String nome,
        @NotBlank
        @Email // validações de email
        String email,
        @NotBlank
        @Pattern(regexp = "\\d{8}")
        String telefone,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}") // expressão regular: tem que ser digitos com 4 a 6 números
        String crm,
        @NotNull
        Especialidade especialidade,
        @NotNull @Valid // para validar também o dados endereço
        DadosEndereco endereco) {
}


