package mage.cards.w;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.CombatDamageDealtToYouTriggeredAbility;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.common.DiscardCardCost;
import mage.abilities.effects.common.SacrificeOpponentsEffect;
import mage.abilities.effects.common.continuous.GainAbilitySourceEffect;
import mage.abilities.effects.keyword.TheRingTemptsYouEffect;
import mage.abilities.keyword.FlyingAbility;
import mage.abilities.keyword.IndestructibleAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.*;
import mage.filter.common.FilterCreaturePermanent;
import mage.filter.predicate.other.DamagedPlayerThisTurnPredicate;

import java.util.UUID;

/**
 *
 * @author bobby.mccann
 */
public final class WitchKingOfAngmar extends CardImpl {

    private static final FilterCreaturePermanent filter
            = new FilterCreaturePermanent("creature that dealt combat damage to you this turn");

    static {
        filter.add(new DamagedPlayerThisTurnPredicate(TargetController.YOU, true));
    }

    public WitchKingOfAngmar(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{3}{B}{B}");
        
        this.supertype.add(SuperType.LEGENDARY);
        this.subtype.add(SubType.WRAITH);
        this.subtype.add(SubType.NOBLE);
        this.power = new MageInt(5);
        this.toughness = new MageInt(3);

        // Flying
        this.addAbility(FlyingAbility.getInstance());

        // Whenever one or more creatures deal combat damage to you, each opponent sacrifices a creature that dealt combat damage to you this turn. The Ring tempts you.
        Ability ability = new CombatDamageDealtToYouTriggeredAbility(new SacrificeOpponentsEffect(filter));
        ability.addEffect(new TheRingTemptsYouEffect());
        this.addAbility(ability);

        // Discard a card: Witch-king of Angmar gains indestructible until end of turn. Tap it.
        this.addAbility(new SimpleActivatedAbility(
                new GainAbilitySourceEffect(IndestructibleAbility.getInstance(), Duration.EndOfTurn),
                new DiscardCardCost()
        ));
    }

    private WitchKingOfAngmar(final WitchKingOfAngmar card) {
        super(card);
    }

    @Override
    public WitchKingOfAngmar copy() {
        return new WitchKingOfAngmar(this);
    }
}
