package med.voll.api.domain.medico;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // para não subsituir as configurações do banco de dados da aplicação
@ActiveProfiles("test") // para ler o arquivo de teste
class MedicoRepositoryTest {

    @Test
    @DisplayName("")
    void escolherMedicoAleatorioLivreNaDataCenario1() {
    }
}