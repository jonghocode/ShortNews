package com.ShortNews.ShortNews.repository;

import com.ShortNews.ShortNews.entity.Preference;
import com.ShortNews.ShortNews.entity.PreferenceKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreferenceRepository extends JpaRepository<Preference, PreferenceKey> {
}
