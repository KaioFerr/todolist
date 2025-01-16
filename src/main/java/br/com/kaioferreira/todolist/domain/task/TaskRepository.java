package br.com.kaioferreira.todolist.domain.task;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskRepository extends JpaRepository <Task, UUID> {
}
