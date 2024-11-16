package guru.springframework.spring_6_rest_mvc.model;

import guru.springframework.spring_6_rest_mvc.entities.FullUpdate;
import guru.springframework.spring_6_rest_mvc.entities.PartialUpdate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class CustomerDTO {
    private UUID id;

    @NotBlank(groups = {FullUpdate.class, PartialUpdate.class})
    @NotNull
    private String name;
    private Integer version;

    @CreationTimestamp
    private LocalDateTime createdDate;

    @CreationTimestamp
    private LocalDateTime updateDate;
}
