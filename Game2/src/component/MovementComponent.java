package component;

import event.Event;
import event.EventManager;
import event.ObjectMovedEvent;
import event.RespawnEvent;
import util.EConstant;

public class MovementComponent extends Component {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5468157615970061823L;
	private CollisionBoxComponent hitbox;
	private MovementDriver[] drivers;
	private double velocityX, velocityY, accelerationX, accelerationY = 0;
	private double tempPositionX, tempPositionY;
	private long previousTimestamp;
	private double dt;

	public MovementComponent(int guid, EventManager eventManager, CollisionBoxComponent hitbox, MovementDriver... drivers) {
		super(guid, eventManager);
		this.eventManager = eventManager;
		this.hitbox = hitbox;
		this.drivers = drivers;
		this.previousTimestamp = eventManager.getTime();
		register();
	}

	@Override
	public void register() {
		eventManager.register(EConstant.SERVICE_ALL_EVENT, this);
		eventManager.register(EConstant.RESPAWN_EVENT, this);
	}

	@Override
	public void onEvent(Event event) {
		if (event.getType() == EConstant.SERVICE_ALL_EVENT) {
			long timestamp = eventManager.getTime();
			dt = (timestamp - previousTimestamp) / EConstant.NANOSECONDS_IN_SECOND;
			previousTimestamp = timestamp;
			tempPositionX = hitbox.getPosition().getX();
			tempPositionY = hitbox.getPosition().getY();
			for (MovementDriver driver : drivers) {
				driver.drive(this);
			}
			velocityX += accelerationX * dt;
			velocityY += accelerationY * dt;
			tempPositionX += velocityX * dt;
			tempPositionY += velocityY * dt;
			eventManager.raise(new ObjectMovedEvent(eventManager.getTime(), this.getGuid(), tempPositionX, tempPositionY,
					hitbox.getWidth(), hitbox.getHeight(), false));
		} else if (event.getType() == EConstant.RESPAWN_EVENT) {
			if (((RespawnEvent) event).getGuid() == guid) {
				velocityX = 0;
				velocityY = 0;
			}
		}
	}

	public WorldPositionComponent getPosition() {
		return hitbox.getPosition();
	}

	public double getVelocityX() {
		return velocityX;
	}

	public void setVelocityX(double velocityX) {
		this.velocityX = velocityX;
	}

	public double getVelocityY() {
		return velocityY;
	}

	public void setVelocityY(double velocityY) {
		this.velocityY = velocityY;
	}

	public double getAccelerationX() {
		return accelerationX;
	}

	public void setAccelerationX(double accelerationX) {
		this.accelerationX = accelerationX;
	}

	public double getAccelerationY() {
		return accelerationY;
	}

	public void setAccelerationY(double accelerationY) {
		this.accelerationY = accelerationY;
	}

	public double getDt() {
		return dt;
	}

	public CollisionBoxComponent getHitbox() {
		return hitbox;
	}

	public double getTempPositionX() {
		return tempPositionX;
	}

	public void setTempPositionX(double tempPositionX) {
		this.tempPositionX = tempPositionX;
	}

	public double getTempPositionY() {
		return tempPositionY;
	}

	public void setTempPositionY(double tempPositionY) {
		this.tempPositionY = tempPositionY;
	}
}
