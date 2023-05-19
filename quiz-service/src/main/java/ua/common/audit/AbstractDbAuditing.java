package ua.common.audit;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import java.time.LocalDateTime;

/**
 * @author (ozhytary)
 */
@MappedSuperclass
@Data
@NoArgsConstructor
public abstract class AbstractDbAuditing {
    @CreationTimestamp
    @Column(name = "created_at")
    protected LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    protected LocalDateTime updatedAt;
    @CreatedBy
    @Column(name = "created_by")
    protected String createdBy;
    @LastModifiedBy
    @Column(name = "modified_by")
    protected String modifiedBy;
}
