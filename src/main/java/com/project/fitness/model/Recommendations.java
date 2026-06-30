package com.project.fitness.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recommendations {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
   private String id ;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name = "user_id", nullable = false , foreignKey = @ForeignKey(name = "fk_recommendations_key"))
    private User user;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn( name = "activity_id", nullable = false , foreignKey = @ForeignKey(name = "fk_recommendations_key"))
   private Activity activity;

    private String type ;

    @Column(length = 200)
    private String recommendations ;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")
    private List<String> improvements ;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")
    private List<String> suggestions ;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")
    private List<String> safety ;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
