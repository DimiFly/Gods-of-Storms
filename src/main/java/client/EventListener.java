package client;

import client.packets.AddPlayerPacket;
import client.packets.RemovePlayerPacket;

public class EventListener {

    public void receive(Object p) {
        if(p instanceof AddPlayerPacket) {
            AddPlayerPacket packet = (AddPlayerPacket)p;
            PlayerHandler.players.put(packet.id, new Player(packet.id, packet.name));
            System.out.println(packet.id + " has joined the game");
        } else if (p instanceof RemovePlayerPacket){
            RemovePlayerPacket packet = (RemovePlayerPacket)p;
            PlayerHandler.players.remove(packet.id);
            System.out.println(packet.id + " has left the game");
        }
    }
}
