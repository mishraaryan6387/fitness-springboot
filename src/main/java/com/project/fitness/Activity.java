package com.project.fitness;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;


import java.time.LocalDateTime;
import java.util.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
        private String id ;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false , foreignKey = @ForeignKey(name = "fk_activity_user"))
       @JsonIgnore
        private User user  ;
        @Enumerated(EnumType.STRING)
        private ActivityType Type;


        @JdbcTypeCode(SqlTypes.JSON)
        @Column(columnDefinition = "json")
        private Map<String,Object> additionalMetrics ;


        private Integer duration;
        private Integer calories;
        private LocalDateTime  startTime;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        @OneToMany(mappedBy = "activity",cascade = CascadeType.ALL , orphanRemoval = false)
        @JsonIgnore
        private List<Recommendations> recommendations = new ArrayList<>();


}
