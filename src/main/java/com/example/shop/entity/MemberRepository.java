package com.example.shop.entity;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {

    public Optional<Member> findByEmail(String Email);

    public Optional<Member.FindMemberId> findMemberById(String email);
}
