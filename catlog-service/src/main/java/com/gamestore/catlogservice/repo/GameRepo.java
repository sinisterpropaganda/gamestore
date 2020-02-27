/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamestore.catlogservice.repo;

import com.gamestore.catlogservice.entity.Game;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author qbuser
 */
public interface GameRepo extends JpaRepository<Game, Integer> {

    Page<List<Game>> findByReleaseDateBetween(LocalDate startDate, LocalDate endDate,
            Pageable pageable);
}
