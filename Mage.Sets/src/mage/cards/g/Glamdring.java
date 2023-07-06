package mage.cards.g;

import mage.abilities.Ability;
import mage.abilities.common.DealsDamageToAPlayerAttachedTriggeredAbility;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.dynamicvalue.common.CardsInControllerGraveyardCount;
import mage.abilities.dynamicvalue.common.StaticValue;
import mage.abilities.effects.Effect;
import mage.abilities.effects.common.continuous.BoostEquippedEffect;
import mage.abilities.effects.common.continuous.GainAbilityAttachedEffect;
import mage.abilities.effects.common.cost.CastFromHandForFreeEffect;
import mage.abilities.keyword.EquipAbility;
import mage.abilities.keyword.FirstStrikeAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.*;
import mage.filter.FilterCard;
import mage.filter.StaticFilters;
import mage.filter.predicate.Predicates;

import java.util.UUID;

/**
 *
 * @author bobby-mccann
 */
public final class Glamdring extends CardImpl {
    private static final FilterCard filter = new FilterCard("an instant or sorcery spell from your hand with mana value less than or equal to that damage");
    static {
        filter.add(Predicates.or(
                CardType.INSTANT.getPredicate(),
                CardType.SORCERY.getPredicate()
        ));
        // TODO: figure out how to get the damage value
//        filter.add(new ManaValuePredicate(ComparisonType.FEWER_THAN, DamageDoneWatcher))
    }

    public Glamdring(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ARTIFACT}, "{2}");
        
        this.supertype.add(SuperType.LEGENDARY);
        this.subtype.add(SubType.EQUIPMENT);

        // Equipped creature has first strike and gets +1/+0 for each instant and sorcery card in your graveyard.
        Effect firstStrike = new GainAbilityAttachedEffect(FirstStrikeAbility.getInstance(), AttachmentType.EQUIPMENT)
                .setText("Equipped creature has first strike");
        Effect boost = new BoostEquippedEffect(
                new CardsInControllerGraveyardCount(StaticFilters.FILTER_CARD_INSTANT_AND_SORCERY), StaticValue.get(0))
                .setText(" and gets +1/+0 for each instant and sorcery card in your graveyard.");

        Ability ability = new SimpleStaticAbility(Zone.BATTLEFIELD, firstStrike);
        ability.addEffect(boost);
        this.addAbility(ability);

        // Whenever equipped creature deals combat damage to a player, you may cast an instant or sorcery spell from your hand with mana value less than or equal to that damage without paying its mana cost.
        this.addAbility(
                new DealsDamageToAPlayerAttachedTriggeredAbility(
                        new CastFromHandForFreeEffect(filter),
                        "equipped creature", true
                )
        );

        // Equip {3}
        this.addAbility(new EquipAbility(3));
    }

    private Glamdring(final Glamdring card) {
        super(card);
    }

    @Override
    public Glamdring copy() {
        return new Glamdring(this);
    }
}
