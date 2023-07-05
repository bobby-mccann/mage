package mage.cards.s;

import java.util.UUID;
import mage.MageInt;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.abilities.keyword.LivingMetalAbility;
import mage.abilities.keyword.FlyingAbility;
import mage.abilities.keyword.MenaceAbility;
import mage.abilities.keyword.HasteAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;

/**
 *
 * @author bobby-mccann
 */
public final class StarscreamSeekerLeader extends CardImpl {

    public StarscreamSeekerLeader(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ARTIFACT}, "");
        
        this.supertype.add(SuperType.LEGENDARY);
        this.subtype.add(SubType.VEHICLE);
        this.power = new MageInt(2);
        this.toughness = new MageInt(3);

        // Living metal
        this.addAbility(new LivingMetalAbility());

        // Flying
        this.addAbility(FlyingAbility.getInstance());

        // Menace
        this.addAbility(new MenaceAbility());

        // Haste
        this.addAbility(HasteAbility.getInstance());

        // Whenever Starscream deals combat damage to a player, if there is no monarch, that player becomes the monarch.
        // Whenever you become the monarch, convert Starscream.
    }

    private StarscreamSeekerLeader(final StarscreamSeekerLeader card) {
        super(card);
    }

    @Override
    public StarscreamSeekerLeader copy() {
        return new StarscreamSeekerLeader(this);
    }
}
