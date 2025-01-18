package br.com.kaioferreira.todolist.domain.task;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "tasks")
public class Task {

    @Id
    @GeneratedValue (generator = "UUID")
    private UUID id;

    private UUID idUser;

    private String description;

    @Column(length = 50)
    private String title;

    private LocalDateTime startAt;

    private LocalDateTime endAt;

    @Column (length = 50)
    private String priority;

    @Column (length = 50)
    private String status;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public void setTitle(String title) throws Exception{
        if (title.length() > 50){
            throw new Exception("O campo title deve conter no máximo 50 caracteres");
        }
        this.title = title;
    }
}
