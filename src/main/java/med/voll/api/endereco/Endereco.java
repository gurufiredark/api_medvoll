package med.voll.api.endereco;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    public Endereco(DadosEndereco dados) {
        this.logradouro = dados.logradouro();
        this.numero = dados.numero();
        this.complemento = dados.complemento();
        this.bairro = dados.bairro();
        this.cep = dados.cep();
        this.cidade = dados.cidade();
        this.uf = dados.uf();
    }
    
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cep;
    private String cidade;
    private String uf;

}
