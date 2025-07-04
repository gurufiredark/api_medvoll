package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.medico.MedicoRepository;

public class ValidadorMedicoAtivo {
    
    private MedicoRepository repository;

    public void validar(DadosAgendamentoConsulta dados) {
        
        if(dados.idMedico() == null) {
            return; // Se não foi informado um médico, não há o que validar
        }

        var medicoEstaAtivo = repository.findAtivoById(dados.idMedico());

        if(!medicoEstaAtivo){
            throw new ValidacaoException("Consulta não pode ser agendada com médico inativo.");
        }
    }
}
