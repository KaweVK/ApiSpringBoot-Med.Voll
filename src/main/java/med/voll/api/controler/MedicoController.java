package med.voll.api.controler;

import med.voll.api.medico.DadosCadastrosMedico;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @PostMapping //post é para enviar na api
    public void cadastrar(@RequestBody DadosCadastrosMedico dados) { //RequestBody é pra dizer que tem que o parâmetro vai puxar o corpo inteiro da requisição
        System.out.println(dados);
    }

}
