package com.cloakyloki.entity;

import com.cloakyloki.entity.enumerated.CardType;
import com.cloakyloki.entity.enumerated.Rarity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CardTest {

    private static SessionFactory sessionFactory;
    private Session session;

    @BeforeAll
    static void init() {
        var configuration = new Configuration();
        configuration.configure();
        sessionFactory = configuration.buildSessionFactory();
    }

    @AfterAll
    static void close() {
        sessionFactory.close();
    }

    @BeforeEach
    void sessionInit() {
        session = sessionFactory.openSession();
        session.beginTransaction();
    }

    @AfterEach
    void cleanAfterTest() {
        session.getTransaction().rollback();
        session.close();
    }

    @Test
    void addCard() {
        var expectedCard = Card.builder()
                .name("Mirage Mirror")
                .flavorText("Most mirrors show your reflection. Other show your potential")
                .isBanned(false)
                .keywords("mirror")
                .manaValue(3)
                .type(CardType.Artifact)
                .purchaseUrl("https://www.tcgplayer.com/product/134930")
                .rarity(Rarity.rare)
                .scryfallIllustrationId("1562793459")
                .text("Mirage Mirror becomes a copy of target artifact, creature, enchantment, or land until end of turn.")
                .build();
        session.save(expectedCard);
        session.clear();

        var actualCard = session.get(Card.class, expectedCard.getId());

        assertThat(actualCard.getId()).isNotNull();
    }

    @Test
    void getCard() {
        var expectedCard = Card.builder()
                .name("Mirage Mirror")
                .flavorText("Most mirrors show your reflection. Other show your potential")
                .isBanned(false)
                .keywords("mirror")
                .manaValue(3)
                .type(CardType.Artifact)
                .purchaseUrl("https://www.tcgplayer.com/product/134930")
                .rarity(Rarity.rare)
                .scryfallIllustrationId("1562793459")
                .text("Mirage Mirror becomes a copy of target artifact, creature, enchantment, or land until end of turn.")
                .build();
        session.save(expectedCard);
        session.clear();

        var actualCard = session.getReference(Card.class, expectedCard.getId());

        assertEquals(expectedCard, actualCard);
    }
}