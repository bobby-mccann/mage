package mage.cards.f;

import java.util.UUID;

import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.common.FirstEquipFreeAbility;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.condition.common.MyTurnCondition;
import mage.abilities.decorator.ConditionalAsThoughEffect;
import mage.abilities.decorator.ConditionalContinuousEffect;
import mage.abilities.effects.common.ReturnFromGraveyardToBattlefieldTargetEffect;
import mage.abilities.effects.common.continuous.ActivateAbilitiesAnyTimeYouCouldCastInstantEffect;
import mage.abilities.keyword.EquipAbility;
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
        this.addAbility(new EntersBattlefieldTriggeredAbility(new ReturnFromGraveyardToBattlefieldTargetEffect(), true));

        // As long as it's your turn, you may activate equip abilities any time you could cast an instant.
        this.addAbility(new SimpleStaticAbility(
                new ConditionalAsThoughEffect(
                        new ActivateAbilitiesAnyTimeYouCouldCastInstantEffect(EquipAbility.class, "equip abilities"),
                        MyTurnCondition.instance
                ).setText("As long as it's your turn, you may activate equip abilities any time you could cast an instant.")
        ));

        // You may pay {0} rather than pay the equip cost of the first equip ability you activate during each of your turns.
        this.addAbility(FirstEquipFreeAbility.yourTurn());
    }

    private ForgeAnew(final ForgeAnew card) {
        super(card);
    }

    @Override
    public ForgeAnew copy() {
        return new ForgeAnew(this);
    }
}
