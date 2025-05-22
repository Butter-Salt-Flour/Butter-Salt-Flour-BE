package practice.buttersaltflour.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import practice.buttersaltflour.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String> {

}
