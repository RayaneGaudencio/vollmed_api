package med.voll.api.endereco;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    private String logradouro;
    private String bairro;
    private String cep;
    private String numero;
    private String complemento;
    private String cidade;
    private String uf;

    public Endereco(DadosEndereco dadosEndereco) {
        this.logradouro = dadosEndereco.logradouro();
        this.bairro = dadosEndereco.bairro();
        this.cep = dadosEndereco.cep();
        this.numero = dadosEndereco.numero();
        this.complemento = dadosEndereco.complemento();
        this.cidade = dadosEndereco.cidade();
        this.uf = dadosEndereco.uf();
    }

    public void atualizarInformacoes(DadosEndereco novosDados) {
        if (novosDados.logradouro() != null) {
            this.logradouro = novosDados.logradouro();
        }

        if (novosDados.bairro() != null) {
            this.bairro = novosDados.bairro();
        }

        if (novosDados.cep() != null) {
            this.cep = novosDados.cep();
        }

        if (novosDados.numero() != null) {
            this.numero = novosDados.numero();
        }

        if (novosDados.complemento() != null) {
            this.complemento = novosDados.complemento();
        }

        if (novosDados.cidade() != null) {
            this.cidade = novosDados.cidade();
        }

        if (novosDados.uf() != null) {
            this.uf = novosDados.uf();
        }
    }
}
