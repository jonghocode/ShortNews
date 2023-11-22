package com.ShortNews.ShortNews.repository;

import com.ShortNews.ShortNews.entity.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlatformRepository extends JpaRepository<Platform, String> {
}
