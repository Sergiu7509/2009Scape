package plugin.interaction.object;

import core.cache.def.impl.ObjectDefinition;
import plugin.dialogue.FacialExpression;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.game.node.object.GameObject;
import core.game.world.update.flag.context.Animation;
import core.plugin.Plugin;
import core.plugin.InitializablePlugin;
import core.tools.RandomFunction;

/**
 * Represents the haystack plugin.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class HaystackPlugin extends OptionHandler {

	/**
	 * Represents the needle to give.
	 */
	private static final Item NEEDLE = new Item(1733);

	/**
	 * Represents the animation.
	 */
	private static final Animation ANIMATION = new Animation(827);

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.forId(298).getConfigurations().put("option:search", this);
		ObjectDefinition.forId(299).getConfigurations().put("option:search", this);
		ObjectDefinition.forId(300).getConfigurations().put("option:search", this);
		ObjectDefinition.forId(304).getConfigurations().put("option:search", this);
		ObjectDefinition.forId(36892).getConfigurations().put("option:search", this);
		ObjectDefinition.forId(36893).getConfigurations().put("option:search", this);
		ObjectDefinition.forId(36894).getConfigurations().put("option:search", this);
		ObjectDefinition.forId(36895).getConfigurations().put("option:search", this);
		ObjectDefinition.forId(36896).getConfigurations().put("option:search", this);
		ObjectDefinition.forId(36897).getConfigurations().put("option:search", this);
		ObjectDefinition.forId(36898).getConfigurations().put("option:search", this);
		ObjectDefinition.forId(36899).getConfigurations().put("option:search", this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		final GameObject object = ((GameObject) node);
		final int rand = RandomFunction.random(50);
		player.lock(2);
		player.animate(ANIMATION);
		player.getPacketDispatch().sendMessage("You search the " + object.getName().toLowerCase() + "...");
		if (rand == 1 && player.getInventory().freeSlots() > 0 || player.getInventory().containsItem(NEEDLE)) {
			player.getInventory().add(NEEDLE);
			player.getDialogueInterpreter().sendDialogues(player, FacialExpression.HALF_GUILTY, "Wow! A needle!", "Now what are the chances of finding that?");
			return true;
		}
		player.getPacketDispatch().sendMessage("You find nothing of interest.");
		return true;
	}

}
