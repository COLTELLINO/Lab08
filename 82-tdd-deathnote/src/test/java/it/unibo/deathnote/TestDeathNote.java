package it.unibo.deathnote;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.impl.DeathNoteImplementation;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;

class TestDeathNote {

    private DeathNoteImplementation deathNote = new DeathNoteImplementation(); 

    @BeforeEach
    public void setUp() {
    deathNote.names.clear();
    }

    @Test
    public void testGetRule() {
        try {
            deathNote.getRule(0);
            Assertions.fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getMessage());
            assertNotEquals(e.getMessage(), "");
        }
    }
    
    @Test
    public void testRules() {
        for (String r : DeathNote.RULES) {
            Assertions.assertNotNull(r);
            Assertions.assertNotEquals(r, "");
        }
    }

    @Test
    public void testWriteName() {
        if (!deathNote.isNameWritten("Piermenti Sfracellozzi")) {
            deathNote.writeName("Piermenti Sfracellozzi");
            Assertions.assertEquals(deathNote.names.get(0).getPersonName(), "Piermenti Sfracellozzi");
            Assertions.assertEquals(deathNote.names.getLast(), deathNote.names.getFirst());
        }
    }

    @Test
    public void writeDeathCause() throws InterruptedException {
        try {
        deathNote.writeDeathCause("Covid 19");
        Assertions.fail("IllegalStateException expected to be thrown");
        } catch (IllegalStateException e) {
        }
        deathNote.writeName("Pietro Smusi");
        Assertions.assertEquals(deathNote.names.getLast().getDeathCause(), "heart attack");
        deathNote.writeName("Orazio Grinzosi");
        Assertions.assertTrue(deathNote.writeDeathCause("karting accident"));
        Assertions.assertEquals(deathNote.names.getLast().getDeathCause(), "karting accident");
        Thread.sleep(100);
        deathNote.writeDeathCause("brainrot");
        Assertions.assertEquals(deathNote.names.getLast().getDeathCause(), "karting accident");
    }

    @Test
    public void testWriteDetails() throws InterruptedException {
    try {
        deathNote.writeDetails("L sai che gli shinigami mangiano solo mele??");
        Assertions.fail("IllegalStateException expected to be thrown");
        } catch (IllegalStateException e) {
        }
        deathNote.writeName("Dani The King");
        Assertions.assertEquals(deathNote.names.getLast().getDetails(), null);
        deathNote.writeDetails("ran for too long");
        Assertions.assertEquals(deathNote.names.getLast().getDetails(), "ran for too long");
        deathNote.writeName("Obamna");
        Thread.sleep(6100);
        deathNote.writeDetails("touched grass");
        Assertions.assertEquals(deathNote.names.getLast().getDetails(), null);
    }
}