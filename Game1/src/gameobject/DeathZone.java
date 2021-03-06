package gameobject;

import java.util.LinkedList;
import java.util.List;

import component.CollisionBoxComponent;
import component.Component;
import component.CustomDamageComponent;
import component.WorldPositionComponent;
import event.EventManager;

public class DeathZone extends GameObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5643436535314186850L;
	private double x;
	private double y;
	private double width;
	private double height;

	public DeathZone(String name, double x, double y, double width, double height) {
		super(name);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	@Override
	public List<Component> build(EventManager eventManager) {
		WorldPositionComponent position = new WorldPositionComponent(guid, eventManager, x, y);
		CollisionBoxComponent hitbox = new CollisionBoxComponent(guid, eventManager, position, width, height, false,
				false);
		CustomDamageComponent hazard = new CustomDamageComponent(guid, eventManager, "respawn_player.js");

		List<Component> gameObject = new LinkedList<Component>();
		gameObject.add(position);
		gameObject.add(hitbox);
		gameObject.add(hazard);
		return gameObject;
	}

	@Override
	public List<Component> buildDummy(EventManager eventManager) {
		return null;
	}

	@Override
	public boolean hasDummy() {
		return false;
	}

}
