package gameobject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

import component.Component;
import event.CreateObjectEvent;
import event.DestroyObjectEvent;
import event.Event;
import event.EventHandler;
import event.EventManager;
import event.SendWorldEvent;
import event.WorldRequestEvent;
import util.EConstant;

public class World implements EventHandler, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7622794570858148559L;
	private List<Component> components;
	private List<GameObject> roster;
	private EventManager eventManager;
	private int guid;

	public World(String name, EventManager eventManager) {
		this.components = new LinkedList<Component>();
		this.roster = new LinkedList<GameObject>();
		this.eventManager = eventManager;
		this.guid = name.hashCode();
		register();
	}

	public synchronized void addGameObject(GameObject gameObject) {
		components.addAll(gameObject.build(eventManager));
		roster.add(gameObject);
	}

	public synchronized void addDummyObject(GameObject gameObject) {
		if (gameObject.hasDummy())
			components.addAll(gameObject.buildDummy(eventManager));
		roster.add(gameObject);
	}

	public synchronized void destroyGameObject(int guid) {
		eventManager.deregisterObject(guid);
		components.removeIf(new Predicate<Component>() {

			@Override
			public boolean test(Component t) {
				return t.getGuid() == guid;
			}
		});
		roster.removeIf(new Predicate<GameObject>() {

			@Override
			public boolean test(GameObject t) {
				return t.getGuid() == guid;
			}
		});
	}

	public synchronized void bind(List<GameObject> masterRoster) {
		roster.clear();
		components.clear();
		for (GameObject object : masterRoster) {
			addDummyObject(object);
		}
		System.out.println("World copied and bound to server");
	}

	public List<Component> getComponents() {
		return components;
	}

	public int getGuid() {
		return guid;
	}

	@Override
	public void onEvent(Event event) {
		if (event.getType() == EConstant.WORLD_REQUEST_EVENT) {
			WorldRequestEvent wrEvent = (WorldRequestEvent) event;
			System.out.println("World requested from " + wrEvent.getSource() + " to " + wrEvent.getTarget());
			if (wrEvent.getSource() == guid) {
				eventManager.raise(new SendWorldEvent(eventManager.getTime(), new ArrayList<GameObject>(roster), guid,
						wrEvent.getTarget()));
				System.out.println("Sending world...");
			}
		} else if (event.getType() == EConstant.SEND_WORLD_EVENT) {
			SendWorldEvent swEvent = (SendWorldEvent) event;
			if (swEvent.getTarget() == guid
					|| (swEvent.getSource() != guid && swEvent.getTarget() == EConstant.ALL_GUIDS)) {
				bind(swEvent.getRoster());
			}
		} else if (event.getType() == EConstant.DESTROY_OBJECT_EVENT) {
			DestroyObjectEvent doEvent = (DestroyObjectEvent) event;
			destroyGameObject(doEvent.getGuid());
		} else if (event.getType() == EConstant.CREATE_OBJECT_EVENT) {
			CreateObjectEvent coEvent = (CreateObjectEvent) event;
			addGameObject(coEvent.getGameObject());
		}
	}

	@Override
	public void register() {
		eventManager.register(EConstant.WORLD_REQUEST_EVENT, this);
		eventManager.register(EConstant.SEND_WORLD_EVENT, this);
		eventManager.register(EConstant.DESTROY_OBJECT_EVENT, this);
		eventManager.register(EConstant.CREATE_OBJECT_EVENT, this);
	}
}
