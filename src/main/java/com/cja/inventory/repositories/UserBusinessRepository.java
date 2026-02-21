package com.cja.inventory.repositories;

import com.cja.inventory.models.UserBusiness;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserBusinessRepository extends JpaRepository<UserBusiness, Long> {
    List<UserBusiness> findByUser_Id(Long userId);
    Optional<UserBusiness> findByUser_IdAndBusiness_Id(Long userId, Long businessId);
}
