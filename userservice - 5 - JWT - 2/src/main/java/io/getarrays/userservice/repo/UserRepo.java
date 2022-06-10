package io.getarrays.userservice.repo;


import io.getarrays.userservice.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Member, Long> {
    Member findByUsername(String username);
}
