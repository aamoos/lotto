package com.lotto.repository;

import com.lotto.entity.Lotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LottoRepository extends JpaRepository<Lotto, Integer> {
    //마지막 회차번호
    Optional<Lotto> findTopByOrderByDrwNoDesc();

    @Query(value = """
    SELECT num, COUNT(*) as frequency FROM (
      SELECT drwt_no1 AS num FROM lotto
      UNION ALL
      SELECT drwt_no2 FROM lotto
      UNION ALL
      SELECT drwt_no3 FROM lotto
      UNION ALL
      SELECT drwt_no4 FROM lotto
      UNION ALL
      SELECT drwt_no5 FROM lotto
      UNION ALL
      SELECT drwt_no6 FROM lotto
    ) as numbers
    GROUP BY num
    ORDER BY frequency DESC
    LIMIT 6
    """, nativeQuery = true)
    List<Object[]> findTop6MainNumbers();

    @Query(value = """
    SELECT bnus_no as num, COUNT(*) as frequency FROM lotto
    GROUP BY bnus_no
    ORDER BY frequency DESC
    LIMIT 1
    """, nativeQuery = true)
    List<Object[]> findTop6BonusNumbers();
}
