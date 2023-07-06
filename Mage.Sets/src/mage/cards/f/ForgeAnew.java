package mage.cards.f;

import java.util.UUID;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;

/**
 *
 * @author bobby-mccann
 */
public final class ForgeAnew extends CardImpl {

    public ForgeAnew(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ENCHANTMENT}, "{2}{W}");
        

        // When Forge Anew enters the battlefield, return target Equipment card from your graveyard to the battlefield.
        // As long as it's your turn, you may activate equip abilities any time you could cast an instant.
        // You may pay {0} rather than pay the equip cost of the first equip ability you activate during each of your turns.
    }

    private ForgeAnew(final ForgeAnew card) {
        super(card);
    }

    @Override
    public ForgeAnew copy() {
        return new ForgeAnew(this);
    }
}
