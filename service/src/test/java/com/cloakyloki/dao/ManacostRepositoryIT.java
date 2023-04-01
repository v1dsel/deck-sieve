package com.cloakyloki.dao;

import com.cloakyloki.dao.repository.ManacostRepository;
import com.cloakyloki.integration.annotation.IT;
import com.cloakyloki.util.TestDataProvider;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@IT
@RequiredArgsConstructor
class ManacostRepositoryIT {

    private final ManacostRepository manacostRepository;
    private final EntityManager entityManager;


    @Test
    void delete() {
        var mishraCard = TestDataProvider.createMishraCard();
        var manacost = TestDataProvider.createTestManacost(mishraCard);
        entityManager.persist(mishraCard);
        entityManager.persist(manacost);

        manacostRepository.delete(manacost);
        entityManager.flush();
        entityManager.clear();

        assertThat(manacostRepository.findById(manacost.getId())).isEmpty();
    }

    @Test
    void update() {
        var mishraCard = TestDataProvider.createMishraCard();
        var manacost = TestDataProvider.createTestManacost(mishraCard);
        entityManager.persist(mishraCard);
        entityManager.persist(manacost);

        manacost.setBlack(999999);
        manacostRepository.saveAndFlush(manacost);
        entityManager.clear();

        assertThat(manacostRepository.findById(manacost.getId())).isEqualTo(Optional.of(manacost));
    }

    @Test
    void create() {
        var mishraCard = TestDataProvider.createMishraCard();
        var manacost = TestDataProvider.createTestManacost(mishraCard);
        entityManager.persist(mishraCard);
        manacostRepository.save(manacost);
        entityManager.clear();

        assertThat(manacostRepository.findById(manacost.getId())).isNotEmpty();
    }

    @Test
    void findById() {
        var mishraCard = TestDataProvider.createMishraCard();
        var manacost = TestDataProvider.createTestManacost(mishraCard);
        entityManager.persist(mishraCard);
        entityManager.persist(manacost);
        entityManager.clear();

        assertThat(manacostRepository.findById(manacost.getId())).isEqualTo(Optional.of(manacost));
    }
}