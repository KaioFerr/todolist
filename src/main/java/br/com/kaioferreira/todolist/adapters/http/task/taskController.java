package br.com.kaioferreira.todolist.adapters.http.task;

import br.com.kaioferreira.todolist.application.utils.Utils;
import br.com.kaioferreira.todolist.domain.task.Task;
import br.com.kaioferreira.todolist.domain.task.TaskRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping ("/task")
public class taskController {

    @Autowired
    private TaskRepository taskRepository;

    @PostMapping("/")
    public ResponseEntity create(@RequestBody Task task, HttpServletRequest request) {

        var idUser = request.getAttribute("IdUser");
        task.setIdUser((UUID) idUser);

        var currentDate = LocalDateTime.now();
        if(currentDate.isAfter(task.getStartAt()) || currentDate.isAfter(task.getEndAt())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A data de início / data de término deve ser maior que a atual. ");
        }
        if(task.getStartAt().isAfter(task.getEndAt())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A data de início deve ser menor que data de término.");
        }

        var taskCreated = this.taskRepository.save(task);
        return ResponseEntity.status(HttpStatus.OK).body(task);
    }

    @GetMapping("/")
    public List<Task> findByIdUser(HttpServletRequest request){
        var idUser = request.getAttribute("IdUser");
        var tasksList = this.taskRepository.findByIdUser((UUID) idUser);
        return tasksList;
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody Task task, @PathVariable UUID id, HttpServletRequest request) {

        var idUser = request.getAttribute("IdUser");

        var taskById = this.taskRepository.findById(id).orElse(null);

        if (taskById == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found");
        }

        if (!taskById.getIdUser().equals(idUser)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Usuário não tem permissão para alterar essa tarefa");
        }

        Utils.copyNonNullProperties(task, taskById);// Atualiza apenas os campos não nulos

        var taskUpdated = this.taskRepository.save(taskById); // Salva o objeto atualizado

        return ResponseEntity.status(HttpStatus.OK).body(taskUpdated); // Retorna o objeto atualizado
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable UUID id, HttpServletRequest request){

        var idUser = request.getAttribute("IdUser");

        var taskById = this.taskRepository.findById(id).orElse(null);

        if (taskById == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found");
        }

        if (!taskById.getIdUser().equals(idUser)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Usuário não tem permissão para alterar essa tarefa");
        }

        this.taskRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Task excluida com sucesso");
    }

}
