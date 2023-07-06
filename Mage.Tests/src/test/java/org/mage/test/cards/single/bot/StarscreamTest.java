package org.mage.test.cards.single.bot;

import mage.constants.PhaseStep;
import mage.constants.Zone;
import org.junit.Test;
import org.mage.test.serverside.base.CardTestPlayerBase;

public class StarscreamTest extends CardTestPlayerBase {
    private static final String powerHungry = "Starscream, Power Hungry";
    private static final String seekerLeader = "Starscream, Seeker Leader";
    @Test
    public void drawCardAsMonarch() {
        addCard(Zone.BATTLEFIELD, playerB, "Mountain", 10);
        addCard(Zone.BATTLEFIELD, playerB, powerHungry);
        castSpell(2, PhaseStep.PRECOMBAT_MAIN, playerB, "Crown-Hunter Hireling");
        setStopAt(4, PhaseStep.PRECOMBAT_MAIN);
        execute();

        assertLife(playerA, 18); // Lost 2 life from Starscream's ability
    }
}
