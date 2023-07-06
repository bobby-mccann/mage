package mage.abilities.common;

import mage.abilities.Ability;
import mage.abilities.effects.common.cost.CostModificationEffectImpl;
import mage.abilities.keyword.EquipAbility;
import mage.constants.CostModificationType;
import mage.constants.Duration;
import mage.constants.Outcome;
import mage.constants.WatcherScope;
import mage.game.Game;
import mage.game.events.GameEvent;
import mage.game.stack.StackObject;
import mage.players.Player;
import mage.watchers.Watcher;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @author bobby-mccann
 */
public class FirstEquipFreeAbility extends SimpleStaticAbility {
    private FirstEquipFreeAbility(FirstEquipFreeEffect effect) {
        super(effect);
        this.addWatcher(new FirstEquipFreeWatcher());
    }

    public static FirstEquipFreeAbility yourTurn() {
        return new FirstEquipFreeAbility(new FirstEquipFreeEffect(true,
                "you may pay {0} rather than pay the equip cost of the first equip ability you activate during each of your turns."
        ));
    }

    public static FirstEquipFreeAbility eachTurn() {
        return new FirstEquipFreeAbility(new FirstEquipFreeEffect(false,
                "you may pay {0} rather than pay the equip cost of the first equip ability you activate each turn."
        ));
    }
}

class FirstEquipFreeEffect extends CostModificationEffectImpl {
    final boolean yourTurnOnly;

    public FirstEquipFreeEffect(boolean yourTurnOnly, String text) {
        super(Duration.WhileOnBattlefield, Outcome.Benefit, CostModificationType.SET_COST);
        this.yourTurnOnly = yourTurnOnly;
        this.staticText = text;
    }

    public FirstEquipFreeEffect(final FirstEquipFreeEffect effect) {
        super(effect);
        this.yourTurnOnly = effect.yourTurnOnly;
        this.staticText = effect.staticText;
    }

    @Override
    public boolean applies(Ability abilityToModify, Ability source, Game game) {
        if (yourTurnOnly) {
            if (!game.isActivePlayer(abilityToModify.getControllerId())) {
                return false;
            }
        }
        return abilityToModify instanceof EquipAbility
                && source.isControlledBy(abilityToModify.getControllerId())
                && !FirstEquipFreeWatcher.checkPlayer(abilityToModify.getControllerId(), game);
    }

    @Override
    public boolean apply(Game game, Ability source, Ability abilityToModify) {
        boolean applyReduce = false;
        if (game.inCheckPlayableState()) {
            // getPlayable use - apply all the time
            applyReduce = true;
        } else {
            // real use - ask the player
            Player controller = game.getPlayer(abilityToModify.getControllerId());
            if (controller != null
                    && controller.chooseUse(Outcome.PlayForFree,
                    String.format("Pay {0} to equip instead %s?", abilityToModify.getManaCostsToPay().getText()), source, game)) {
                applyReduce = true;
            }
        }

        if (applyReduce) {
            abilityToModify.getCosts().clear();
            abilityToModify.getManaCostsToPay().clear();
            return true;
        }

        return false;
    }

    @Override
    public FirstEquipFreeEffect copy() {
        return new FirstEquipFreeEffect(this);
    }
}
class FirstEquipFreeWatcher extends Watcher {

    private final Set<UUID> playerSet = new HashSet<>();

    FirstEquipFreeWatcher() {
        super(WatcherScope.GAME);
    }

    @Override
    public void watch(GameEvent event, Game game) {
        if (event.getType() != GameEvent.EventType.ACTIVATED_ABILITY) {
            return;
        }
        StackObject object = game.getStack().getStackObject(event.getSourceId());
        if (object != null && object.getStackAbility() instanceof EquipAbility) {
            playerSet.add(event.getPlayerId());
        }
    }

    @Override
    public void reset() {
        super.reset();
        playerSet.clear();
    }

    static boolean checkPlayer(UUID playerId, Game game) {
        FirstEquipFreeWatcher watcher = game.getState().getWatcher(FirstEquipFreeWatcher.class);
        return watcher != null && watcher.playerSet.contains(playerId);
    }
}
