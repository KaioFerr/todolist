package br.com.kaioferreira.todolist.domain.task;

import br.com.kaioferreira.todolist.domain.user.User;
import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.annotations.SQLSelect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface TaskRepository extends JpaRepository <Task, UUID> {

    List<Task> findByIdUser(UUID idUser);
}
