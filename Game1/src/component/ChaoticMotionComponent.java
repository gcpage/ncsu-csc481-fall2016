package component;

import event.Event;
import event.EventManager;

public class ChaoticMotionComponent extends Component implements MovementDriver {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5254311684821470068L;
	private double x, y;
	private double t = 0;
	private double a = 10;

	public ChaoticMotionComponent(int guid, EventManager eventManager, double x, double y) {
		super(guid, eventManager);
		this.x = x;
		this.y = y;
		register();
	}

	@Override
	public void drive(MovementComponent movementComponent) {
		t += movementComponent.getDt();
		movementComponent.setTempPositionX(x + a * Math.cos(0.5 * t));
		movementComponent.setTempPositionY(y + a * Math.cos(0.3 * t));
	}

	@Override
	public void onEvent(Event event) {
		
	}

	@Override
	public void register() {
		
	}
}
