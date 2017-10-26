package com.gmail.deamon999.reposotories;


import com.gmail.deamon999.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("SELECT u FROM Role u where u.role = :role")
    Role findByRole(@Param("role") String role);
}
