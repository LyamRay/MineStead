package me.lyamray.minestead.animal.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalData {

    @Getter
    private static final AnimalData instance = new AnimalData();

    private UUID uuid;
    private UUID ownerUuid;
    private String type;
    private int age;
    private int health;

}