package mage.cards.b;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.FirstEquipFreeAbility;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.effects.ContinuousEffectImpl;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.*;
import mage.filter.StaticFilters;
import mage.game.Game;
import mage.game.permanent.Permanent;

import java.util.UUID;

/**
 * @author TheElk801
 */
public final class BruenorBattlehammer extends CardImpl {

    public BruenorBattlehammer(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{2}{R}{W}");

        this.supertype.add(SuperType.LEGENDARY);
        this.subtype.add(SubType.DWARF);
        this.subtype.add(SubType.WARRIOR);
        this.power = new MageInt(5);
        this.toughness = new MageInt(3);

        // Each creature you control gets +2/+0 for each Equipment attached to it.
        this.addAbility(new SimpleStaticAbility(new BruenorBattlehammerBoostEffect()));

        // You may pay {0} rather than pay the equip cost of the first equip ability you activate each turn.
        this.addAbility(FirstEquipFreeAbility.eachTurn());
    }

    private BruenorBattlehammer(final BruenorBattlehammer card) {
        super(card);
    }

    @Override
    public BruenorBattlehammer copy() {
        return new BruenorBattlehammer(this);
    }
}

class BruenorBattlehammerBoostEffect extends ContinuousEffectImpl {

    BruenorBattlehammerBoostEffect() {
        super(Duration.WhileOnBattlefield, Layer.PTChangingEffects_7, SubLayer.ModifyPT_7c, Outcome.BoostCreature);
        staticText = "each creature you control gets +2/+0 for each Equipment attached to it";
    }

    private BruenorBattlehammerBoostEffect(final BruenorBattlehammerBoostEffect effect) {
        super(effect);
    }

    @Override
    public BruenorBattlehammerBoostEffect copy() {
        return new BruenorBattlehammerBoostEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        for (Permanent permanent : game.getBattlefield().getActivePermanents(
                StaticFilters.FILTER_CONTROLLED_CREATURE,
                source.getControllerId(), source, game
        )) {
            int equipped = permanent
                    .getAttachments()
                    .stream()
                    .map(game::getPermanent)
                    .mapToInt(p -> p.hasSubtype(SubType.EQUIPMENT, game) ? 1 : 0)
                    .sum();
            permanent.addPower(2 * equipped);
        }
        return true;
    }
}

