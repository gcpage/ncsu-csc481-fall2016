package component;

import event.CollisionEvent;
import event.Event;
import event.EventManager;
import event.ObjectDamageEvent;
import util.EConstant;

public class CollisionDamageComponent extends Component {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7242958124205217848L;
	CollisionBoxComponent hitbox;

	public CollisionDamageComponent(int guid, EventManager eventManager, CollisionBoxComponent hitbox) {
		super(guid, eventManager);
		this.eventManager = eventManager;
		this.hitbox = hitbox;
		register();
	}
	
	@Override
	public void register() {
		eventManager.register(EConstant.COLLISION_EVENT, this);
	}

	@Override
	public void onEvent(Event event) {
		if (event.getType() == EConstant.COLLISION_EVENT) {
			CollisionEvent cEvent = (CollisionEvent) event;
			if (cEvent.getOid2() == this.getGuid()) {
				eventManager.raise(new ObjectDamageEvent(eventManager.getTime(),
						cEvent.getOid1()));
			}
		}
	}
}
