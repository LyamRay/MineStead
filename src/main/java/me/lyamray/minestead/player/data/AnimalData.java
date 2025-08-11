package me.lyamray.minestead.player.data;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalData {

    private UUID uuid;
    private UUID ownerUuid;
    private String type;
    private int age;
    private int health;

}