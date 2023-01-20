package com.luxoft.gdmav.datasnapshot.repository;
import com.luxoft.gdmav.datasnapshot.entity.DataSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataSnapshotRepository extends JpaRepository<DataSnapshot, Long> {

}
