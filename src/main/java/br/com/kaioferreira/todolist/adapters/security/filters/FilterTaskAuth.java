package br.com.kaioferreira.todolist.adapters.security.filters;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.kaioferreira.todolist.domain.user.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var servelestPath = request.getServletPath();

        if (servelestPath.startsWith("/task/")) {
            //Pegar a autorização
            var authorization = request.getHeader("Authorization");
            var authEncoded = authorization.substring("Basic".length()).trim();
            byte[] authDecode = Base64.getDecoder().decode(authEncoded);
            var authoString = new String(authDecode);
            String[] credentials = authoString.split(":");
            String username = credentials[0];
            String password = credentials[1];

            //Validar se  o usuário existe
            var user = this.userRepository.findByUsername(username); //Aqui ele tenta achar o usuário no banco
            if (user == null) { //Se ele não encontrar, ele manda o erro 401
                response.sendError(401);
            } else { //Se não, vai ser verificado a senha, para isso eu preciso do decode e ai sim seguir viagem
                var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
                if (passwordVerify.verified) {
                    //vou buscar o idUser com o set e mandar como requisição
                    request.setAttribute("IdUser", user.getId());
                    filterChain.doFilter(request, response); //seguir viagem
                } else { //Caso a senha esteja errada, ele gera o erro 401 novamente
                    response.sendError(401);
                }
            }

        }else {
            filterChain.doFilter(request, response);
        }
    }
}
