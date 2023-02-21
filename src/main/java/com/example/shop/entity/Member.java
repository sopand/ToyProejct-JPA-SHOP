package com.example.shop.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="email" ,nullable = false)
    private String email;

    @Column(name="pwd" ,nullable = false)
    private String pwd;
    private String address;
    private String tel;

    @Builder
    public Member(Long id,String email,String pwd,String address,String tel){
        this.id=id;
        this.email = email;
        this.pwd = pwd;
        this.address = address;
        this.tel = tel;
    }

}
