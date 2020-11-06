package lt.nogalosa.uhc.models;

import java.util.UUID;

public class UPlayer {

    UUID uuid;

    public UPlayer(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
