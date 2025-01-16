package br.com.kaioferreira.todolist.adapters.http.user;


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

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.kaioferreira.todolist.domain.user.User;
import br.com.kaioferreira.todolist.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
// http://localhost:8080/user ...
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody User user) {
        var userFound =this.userRepository.findByUsername(user.getUsername());

        if (userFound != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe");
        }

        var hashedPassword = BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray());
        user.setPassword(hashedPassword);

        var userCreated = this.userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}


