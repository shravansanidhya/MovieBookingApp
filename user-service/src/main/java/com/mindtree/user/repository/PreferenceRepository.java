//package com.mindtree.user.repository;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import com.mindtree.user.entity.Preference;
//
//@Repository
//public interface PreferenceRepository extends JpaRepository<Preference, Long> {
//
//	@Query("SELECT u.preference FROM User u WHERE u.id = :userId")
//	public Preference findByUserId(@Param("userId") long userId);
//
//	@Modifying
//	@Query("DELETE FROM Preference p WHERE p.id = (SELECT u.preference.id FROM User u WHERE u.id = :userId)")
//	public void deleteByUserId(@Param("userId") long userId);
//
//}
