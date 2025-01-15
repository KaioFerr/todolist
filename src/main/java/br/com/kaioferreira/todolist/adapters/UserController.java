package br.com.kaioferreira.todolist.adapters;


/*
 * Métodos http:
 * GET - Buscar informações
 * POST - Adicionar um dado/informação
 * PUT - Alterar um dado/informação
 * DELETE - Deletar um dado
 * PATCH - Alterar uma parte da infomação
 */

/*
 * Modificadores:
 * public - qualquer classe pode enxerga-la
 * private - ela não pode ser enxergada por outras classes
 * protected - As classes que estão no mesmo pacote exergam ela,
 * porém o acesso é por pacote e por herança.
 */

import br.com.kaioferreira.todolist.domain.user.User;
import br.com.kaioferreira.todolist.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
// http://localhost:8080/user ...
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/register")
    public User register(@RequestBody User user) {
        var userCreated = this.userRepository.save(user);
        return userCreated;
    }
}


