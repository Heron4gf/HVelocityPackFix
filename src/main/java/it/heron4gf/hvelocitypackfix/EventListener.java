package it.heron4gf.hvelocitypackfix;

import com.google.common.io.BaseEncoding;
import com.velocitypowered.api.event.ResultedEvent;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.DisconnectEvent;
import com.velocitypowered.api.event.player.ServerResourcePackSendEvent;

import java.util.HashMap;
import java.util.UUID;

public class EventListener {

    private HashMap<UUID,String> appliedTexture = new HashMap<>();

    @Subscribe
    private void onTextureSend(ServerResourcePackSendEvent event) {
        UUID uuid = event.getServerConnection().getPlayer().getUniqueId();
        String hash = hashToString(event.getProvidedResourcePack().getHash());

        if(appliedTexture.containsKey(uuid) && hash.equals(appliedTexture.get(uuid))) {
            event.setResult(ResultedEvent.GenericResult.denied());
        } else {
            appliedTexture.put(uuid,hash);
        }
    }

    @Subscribe
    private void onLeave(DisconnectEvent event) {
        appliedTexture.remove(event.getPlayer().getUniqueId());
    }

    private String hashToString(byte[] hash)
    {
        if(hash == null)
            return "null";
        return BaseEncoding.base16().lowerCase().encode(hash);
    }

}
