package com.ApiBilhete.ApiBilhetes.domain.user.bilhete;

import jakarta.persistence.*;
import lombok.*;
import com.ApiBilhete.ApiBilhetes.domain.user.User;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Table(name = "bilhetes")
@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Bilhete {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "valorAposta")
    private double valorAposta;

    @Column(name = "retornoPotencial")
    private double retornoPotencial;

    @ElementCollection
    private List<Double> odds;

    @CreationTimestamp
    private Instant creationTimestamp;

    @UpdateTimestamp
    private Instant updateTimestamp;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private User user;

    public Bilhete(UUID id, double valorAposta, double retornoPotencial, List<Double> odds, Instant creationTimestamp, Instant updateTimestamp, User user) {
        this.id = id;
        this.valorAposta = valorAposta;
        this.retornoPotencial = retornoPotencial;
        this.odds = odds;
        this.creationTimestamp = creationTimestamp;
        this.updateTimestamp = updateTimestamp;
        this.user = user;
    }
}
