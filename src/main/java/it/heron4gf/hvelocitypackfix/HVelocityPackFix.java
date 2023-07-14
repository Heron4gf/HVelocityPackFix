package it.heron4gf.hvelocitypackfix;

import com.google.inject.Inject;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import lombok.Getter;
import org.slf4j.Logger;

@Plugin(
        id = "hvelocitypackfix",
        name = "HVelocityPackFix",
        version = "1.0"
)
public class HVelocityPackFix {

    @Inject
    private Logger logger;

    @Inject
    private ProxyServer proxyServer;

    @Getter
    private static HVelocityPackFix instance;

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        instance = this;
        proxyServer.getEventManager().register(this,new EventListener());
    }
}
