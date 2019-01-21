package red.man10.man10userinfo;

import net.minecraft.server.v1_12_R1.IChatBaseComponent;
import net.minecraft.server.v1_12_R1.PacketPlayOutChat;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Man10UserInfo extends JavaPlugin implements Listener, CommandExecutor {

    String prefix = ChatColor.GREEN + "" + ChatColor.BOLD + "[" + ChatColor.LIGHT_PURPLE + "M" + ChatColor.AQUA + "UserInfo" + ChatColor.GREEN + "" +
            ChatColor.BOLD + "] " + ChatColor.RESET;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info(prefix + "Man10UserInfo は正常に起動しました。");
        getLogger().info(prefix + "R1 No.2 Man10UserInfo");
        getLogger().info(prefix + "コマンドは/man10userinfo <player> または /mui <player> です！");
        getCommand("mui").setExecutor(this);
        getCommand("man10userinfo").setExecutor(this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info(prefix + "Man10UserInfo は正常に停止しました。");
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(args.length != 1) {
            sender.sendMessage(prefix + "使い方: /mui <プレイヤー>");
            return false;
        }
        sender.sendMessage(prefix + ChatColor.YELLOW + "あなたの権限を確認します。");
        if(sender.hasPermission("man10userinfo.allow")) {

            if(sender instanceof Player) {


                sender.sendMessage(prefix + ChatColor.GREEN + "認証完了");
                sender.sendMessage(prefix + ChatColor.GREEN + "あなたは権限を持っているため、情報を表示します。");
                sender.sendMessage(prefix + ChatColor.GREEN + "以下のリンクから、" + args[0] + "のJMS、NameMC、man10ユーザーページ、MCBANSへ飛ぶことが出来ます。\n");
                sender.sendMessage(ChatColor.RED + "----------------------------------------------------");
                Player player = (Player) sender;
                IChatBaseComponent comp = IChatBaseComponent.ChatSerializer.a
                        ("{\"text\":\"[NameMC] ここをクリックしてください\",\"color\":\"aqua\",\"clickEvent\":{\"action\":\"open_url\",\"value\":\"https://ja.namemc.com/profile/" + args[0] + "\"}}");
                IChatBaseComponent comp2 = IChatBaseComponent.ChatSerializer.a("{\"text\": \"[JMS] ここをクリックしてください\",\"color\":\"aqua\",\"clickEvent\":{\"action\":\"open_url\",\"value\":\"https://minecraft.jp/players/" + args[0] + "\"}}");
                IChatBaseComponent comp3 = IChatBaseComponent.ChatSerializer.a("{\"text\": \"[Man10 UserPage] ここをクリックしてください\",\"color\":\"aqua\",\"clickEvent\":{\"action\":\"open_url\",\"value\":\"http://man10.red/u?" + args[0] + "\"}}");
                IChatBaseComponent comp4 = IChatBaseComponent.ChatSerializer.a("{\"text\": \"[MCBANS] ここをクリックしてください\",\"color\":\"aqua\",\"clickEvent\":{\"action\":\"open_url\",\"value\":\"http://mcbans.com/player/" + args[0] + "\"}}");
                PacketPlayOutChat chat = new PacketPlayOutChat(comp);
                PacketPlayOutChat chat2 = new PacketPlayOutChat(comp2);
                PacketPlayOutChat chat3 = new PacketPlayOutChat(comp3);
                PacketPlayOutChat chat4 = new PacketPlayOutChat(comp4);
                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(chat);
                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(chat2);
                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(chat3);
                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(chat4);
                sender.sendMessage(ChatColor.RED + "----------------------------------------------------");
                return true;
            } else {
                sender.sendMessage(prefix + "あなたはプレイヤーではないためこのコマンドを実行できない");
                return false;
            }
        } else {
            sender.sendMessage(prefix + ChatColor.DARK_RED + "あなたは権限を持っていません。");
            return false;
        }
    }
}