package com.pluginforge.advancedrtp;

import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class RtpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Resolve "argPlayer" target once, if needed.
        Player resolvedArgPlayer = null;
        if (args.length >= 1) {
            resolvedArgPlayer = Bukkit.getPlayerExact(args[0]);
        }
        final Player argPlayer = resolvedArgPlayer;

        // If a generated command dispatches a command with the same label,
        // stop immediately instead of re-running actions forever.
        if (PluginForgeDispatch.isGeneratedCommandActive(label, args)) {
            return true;
        }

        // Helper: build the recipient list for a given target keyword.
        // Returns empty list when the target cannot be resolved.
        java.util.function.Function<String, List<Player>> resolveTargets = (target) -> {
            java.util.ArrayList<Player> out = new java.util.ArrayList<>();
            if ("sender".equals(target)) {
                if (sender instanceof Player) out.add((Player) sender);
            } else if ("argPlayer".equals(target)) {
                if (argPlayer != null) out.add(argPlayer);
            } else if ("all".equals(target)) {
                out.addAll(Bukkit.getOnlinePlayers());
            }
            return out;
        };

        // ---- Generated actions ----
        {
            String _cmd = "spreadplayers 0 0 10 2500 false " + (sender == null ? "console" : sender.getName());
            PluginForgeDispatch.silent(_cmd);
        }
        {
            String _msg = "You have been randomly teleported!";
            for (Player _p : resolveTargets.apply("sender")) {
                _p.sendMessage("\u00a7a" + _msg);
            }
        }
        // ---- End actions ----

        return true;
    }

    private static String joinArgsFrom(String[] args, int from) {
        if (args == null || from >= args.length) return "";
        StringBuilder _sb = new StringBuilder();
        for (int _i = from; _i < args.length; _i++) {
            if (_i > from) _sb.append(' ');
            _sb.append(args[_i]);
        }
        return _sb.toString();
    }
}
