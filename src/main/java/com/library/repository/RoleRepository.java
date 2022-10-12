package com.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.library.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
