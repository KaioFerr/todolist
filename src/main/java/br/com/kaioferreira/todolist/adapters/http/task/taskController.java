package br.com.kaioferreira.todolist.adapters.http.task;

import br.com.kaioferreira.todolist.domain.task.Task;
import br.com.kaioferreira.todolist.domain.task.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/task")
public class taskController {

    @Autowired
    private TaskRepository taskRepository;

    @PostMapping("/")
    public Task create(@RequestBody Task task) {
        var taskCreated = this.taskRepository.save(task);
        return task;
    }
}
