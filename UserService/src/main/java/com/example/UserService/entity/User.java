package com.example.UserService.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "user_")
public class User {
    public User(int userId){
        this.userId = userId;
    }

    public User(Integer userId, String email, String userName, String password, String hashPassword, String salt, LocalDateTime createOn) {
        this.userId = userId;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.hashPassword = hashPassword;
        this.salt = salt;
        this.createOn = createOn;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(length = 30, nullable = false, unique = true)
    private String email;

    @Column(length = 30)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column()
    private String hashPassword;

    @Column()
    private String salt;

    @Column(nullable = false)
    private LocalDateTime createOn;

    @ManyToOne
    @JoinColumn(name = "roleId")
    private Role role = new Role(1);

//    @OneToMany(mappedBy = "createdBy")
//    private List<Booking> bookings;


//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<UserDetail> userDetails;
//
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<UserContact> userContacts;

}