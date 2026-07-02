package jss.advancedchat;

import jss.advancedchat.utils.interfaces.IHook;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

@SuppressWarnings("ALL")
public abstract class HookLoader {

    private final HashMap<String, IHook> iHooks = new HashMap<>();

    public void loadRegisteredHook() {
        this.iHooks.forEach((s, hook) -> initHooks(this.getRegisteredHook(s)));
    }

    public IHook getRegisteredHook(String id) {
        if (this.isRegisteredHook(id)) {
            return this.iHooks.get(id);
        }
        return null;
    }

    public void registeredHook(String id, IHook hooks) {
        if (this.isRegisteredHook(id)) {
            iHooks.put(id, hooks);
        }
    }

    public boolean isRegisteredHook(String id) {
        return this.iHooks.get(id) == null || !this.iHooks.containsKey(id);
    }

    public void initHooks(IHook @NotNull ... hooks) {
        for (IHook hook : hooks) {
            hook.setup();
        }
    }
}