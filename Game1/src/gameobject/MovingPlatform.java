package gameobject;

import java.util.LinkedList;
import java.util.List;

import component.CollisionBoxComponent;
import component.ColoredRectangleComponent;
import component.Component;
import component.CustomDriverComponent;
import component.MovementComponent;
import component.WorldPositionComponent;
import event.EventManager;

public class MovingPlatform extends GameObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5422347845595661845L;
	private double x;
	private double y;
	private double width;
	private double height;

	public MovingPlatform(String name, int x, int y, int width, int height) {
		super(name);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	@Override
	public List<Component> build(EventManager eventManager) {
		WorldPositionComponent position = new WorldPositionComponent(guid, eventManager, x, y);
		CollisionBoxComponent hitbox = new CollisionBoxComponent(guid, eventManager, position, width, height, true,
				false);
		ColoredRectangleComponent renderShape = new ColoredRectangleComponent(guid, eventManager, position, width,
				height);
		CustomDriverComponent chaos = new CustomDriverComponent(guid, eventManager, "chaotic_motion.js", x, y);
		MovementComponent movement = new MovementComponent(guid, eventManager, hitbox, chaos);

		List<Component> gameObject = new LinkedList<Component>();
		gameObject.add(position);
		gameObject.add(hitbox);
		gameObject.add(renderShape);
		gameObject.add(chaos);
		gameObject.add(movement);
		return gameObject;
	}

	@Override
	public List<Component> buildDummy(EventManager eventManager) {
		WorldPositionComponent position = new WorldPositionComponent(guid, eventManager, x, y);
		ColoredRectangleComponent renderShape = new ColoredRectangleComponent(guid, eventManager, position,
				width, height);
		
		List<Component> gameObject = new LinkedList<Component>();
		gameObject.add(position);
		gameObject.add(renderShape);
		return gameObject;
	}

	@Override
	public boolean hasDummy() {
		return true;
	}
}
