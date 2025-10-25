package com.wiss.quizbackend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "app_users")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version  // Für Optimistic Locking - wichtig für Multi-User!
    private Long version;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false)
    private String password;  // ACHTUNG: Wird in nächster Lektion gehashed!
    // NIE Passwörter im Klartext speichern!

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    // Konstruktoren
    public AppUser() {}

    public AppUser(String username, String email, String password, Role role) {
        // id wird NICHT gesetzt - JPA kümmert sich darum!
        // version wird NICHT gesetzt - JPA kümmert sich darum!
        // Regel, wenn JPA oder die Datenbank die Werte setzen, dann nicht im Konstruktor setzen!
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // Getter und Setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() { return version; }

    public void setVersion(Long version) { this.version = version; }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
