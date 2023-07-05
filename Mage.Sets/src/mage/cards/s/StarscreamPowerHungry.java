package mage.cards.s;

import java.util.UUID;
import mage.MageInt;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.abilities.keyword.MoreThanMeetsTheEyeAbility;
import mage.abilities.keyword.FlyingAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;

/**
 *
 * @author bobby-mccann
 */
public final class StarscreamPowerHungry extends CardImpl {

    public StarscreamPowerHungry(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ARTIFACT, CardType.CREATURE}, "{3}{B}");
        
        this.supertype.add(SuperType.LEGENDARY);
        this.subtype.add(SubType.ROBOT);
        this.power = new MageInt(2);
        this.toughness = new MageInt(3);

        // More Than Meets the Eye {2}{B}
        this.addAbility(new MoreThanMeetsTheEyeAbility(this, "{2}{B}"));

        // Flying
        this.addAbility(FlyingAbility.getInstance());

        // Whenever you draw a card, if you're the monarch, target opponent loses 2 life.
        // Whenever one or more creatures deal combat damage to you, convert Starscream.
    }

    private StarscreamPowerHungry(final StarscreamPowerHungry card) {
        super(card);
    }

    @Override
    public StarscreamPowerHungry copy() {
        return new StarscreamPowerHungry(this);
    }
}
