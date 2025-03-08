package plugin.micra_item;

import java.util.Arrays;
import java.util.Objects;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements Listener {

  @Override
  public void onEnable() {
    Bukkit.getPluginManager().registerEvents(this, this);
  }


  /**
   * プレイヤーがベッドに入ろうとした時にアイテムの所持数が最大64個(MAX)以外のアイテムを削除するイベント
   * <p>
   *
   * @param e イベント
   */
  @EventHandler
  public void onPlayerBedEnter(PlayerBedEnterEvent e) {
    Player player = e.getPlayer(); // プレイヤー取得

    ItemStack[] itemStacks = player.getInventory().getContents(); // 持ちものに全てのアイテムを取得

    World world = player.getWorld(); // ワールド取得

    Arrays.stream(itemStacks)
        .filter(
            item -> !Objects.isNull(item) && item.getAmount() < 64)
        .forEach(item -> item.setAmount(0)); // アイテムの所持数を0にする

    player.getInventory().setContents(itemStacks); // プレイヤーはアイテム所持数を変更


  }

}
