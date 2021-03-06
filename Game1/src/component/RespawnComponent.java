package component;

import event.Event;
import event.EventManager;
import event.ObjectDamageEvent;
import event.RespawnEvent;
import util.EConstant;

public class RespawnComponent extends Component {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6226201588878691649L;
	private CollisionBoxComponent hitbox;

	public RespawnComponent(int guid, EventManager eventManager, CollisionBoxComponent hitbox) {
		super(guid, eventManager);
		this.eventManager = eventManager;
		this.hitbox = hitbox;
		register();
	}
	
	@Override
	public void register() {
		eventManager.register(EConstant.OBJECT_DAMAGE_EVENT, this);
	}

	@Override
	public void onEvent(Event event) {
		if (event.getType() == EConstant.OBJECT_DAMAGE_EVENT) {
			ObjectDamageEvent odEvent = (ObjectDamageEvent) event;
			if (odEvent.getGuid() == this.getGuid()) {
				eventManager.raise(new RespawnEvent(eventManager.getTime(), this.getGuid(), hitbox.getWidth(), hitbox.getHeight()));
			}
		}
	}
}
