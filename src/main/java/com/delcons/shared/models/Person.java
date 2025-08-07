package com.delcons.shared.models;

import com.delcons.features.user.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "persons")
public class Person extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, unique = true)
    protected Long dni;
    protected String name;
    protected String lastname;
    protected String email;
    protected List<String> phone;
    protected Boolean active = true;

    @ManyToOne
    protected Address address;

    @OneToOne
    @JoinColumn(name = "user_id")
    protected User user;
}
