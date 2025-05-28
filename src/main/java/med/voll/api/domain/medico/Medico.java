package med.voll.api.domain.medico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.endereco.Endereco;

@Table(name = "medicos") //Define o nome da tabela no banco de dados que será associada à classe Medico
@Entity(name = "Medico") //Marca a classe como uma entidade JPA (Java Persistence API). Isso significa que a classe Medico será mapeada para uma tabela no banco de dados.
@Getter //gera automaticamente os métodos getters para todos os campos da classe. Por exemplo, para o campo nome, o método getNome() será criado automaticamente.
@NoArgsConstructor //cria um construtor sem argumentos, necessário para o JPA, que exige um construtor sem parâmetros para instanciar a entidade
@AllArgsConstructor //Gera um construtor com um parâmetro para cada campo da classe. Isso é útil para inicializar um objeto com todos os seus valores, ao invés de usar setters.
@EqualsAndHashCode(of = "id") //Gera os métodos equals() e hashCode(). O parâmetro of = "id" garante que esses métodos sejam baseados no campo id, o que é importante para a comparação de entidades no contexto de banco de dados.
public class Medico {

    public Medico(DadosCadastroMedico dados) {   
        this.ativo = true; //Inicializa o campo ativo como verdadeiro, indicando que o médico está ativo por padrão.
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.crm = dados.crm();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco()); 
    }

    //Indica que o campo id é a chave primária da entidade
    // Define que o valor do campo id será gerado automaticamente pelo banco de dados. O GenerationType.IDENTITY significa que o banco vai gerar esse valor (geralmente com incremento automático).
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;

    //Essa anotação é usada para mapear o enum Especialidade para uma coluna no banco de dados. O EnumType.STRING garante que o valor armazenado seja o nome do valor do enum como uma string
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    //Usada para indicar que o campo endereco é um objeto incorporado dentro da entidade Medico. Em vez de criar uma tabela separada para o endereço, o JPA vai mapear o campo endereco como parte da tabela medicos. Isso é útil para quando você tem um tipo de dado que não precisa ser uma entidade separada, mas é uma parte do objeto principal.
    @Embedded
    private Endereco endereco;

    private Boolean ativo;

    public void atualizarInformacoes(DadosAtualizacaoMedico dados) {
        if(dados.nome() != null) {
            this.nome = dados.nome();
        }
        if(dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if(dados.endereco() != null) {
            this.endereco.atualizarInformacoes(dados.endereco());
        }
    }

    public void excluir() {
        this.ativo = false; //Ao invés de remover o médico do banco de dados, o método apenas marca o médico como inativo, definindo o campo ativo como false. Isso é uma prática comum em sistemas onde você deseja manter o histórico dos registros, mas não quer que eles sejam mais considerados ativos.
    }
    
}
