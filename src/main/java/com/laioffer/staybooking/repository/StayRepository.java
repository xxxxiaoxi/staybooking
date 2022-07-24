package com.laioffer.staybooking.repository;

import com.laioffer.staybooking.model.Stay;
import com.laioffer.staybooking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//                     两个变量，第一个表示要对Stay这个class做增删改查，第二个表示Stay的Pk的类型是Long
public interface StayRepository extends JpaRepository <Stay, Long>{

    List<Stay> findByHost(User user);
    //for delete, guarantee this stay belongs this user
    Stay findByIdAndHost(Long id, User host);

    List<Stay> findByIdInAndGuestNumberGreaterThanEqual(List<Long> ids, int guestNumber);
}
