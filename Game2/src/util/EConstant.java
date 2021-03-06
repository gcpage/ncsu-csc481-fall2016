package util;

public class EConstant {

	// Networking
	public static final int PORT = 6666;
	public static final int ALL_GUIDS = -1;

	// Objects
	public static final double PLAYER_WIDTH = 3;
	public static final double PLAYER_HEIGHT = 3;

	// Priorities
	public static final int PRIORITY_NETWORKING = 0;
	public static final int PRIORITY_INPUT = 1;
	public static final int PRIORITY_RENDERING = 2;
	public static final int PRIORITY_GAME_LOGIC = 3;
	public static final int PRIORITY_PHYSICS = 4;
	public static final int PRIORITY_MOVEMENT = 5;


	// Event id's
	public static final int RENDER_ALL_EVENT = 0;
	public static final int RENDER_COMPONENT_EVENT = 1;
	public static final int SERVICE_COMPONENT_EVENT = 2;
	public static final int KEY_PRESSED_EVENT = 3;
	public static final int KEY_RELEASED_EVENT = 4;
	public static final int OBJECT_MOVED_EVENT = 5;
	public static final int COLLISION_EVENT = 6;
	public static final int MOMENTUM_TRANSFER_EVENT = 7;
	public static final int OBJECT_DAMAGE_EVENT = 8;
	public static final int RESPAWN_EVENT = 9;
	public static final int CLIENT_CONNECT_EVENT = 10;
	public static final int CLIENT_DISCONNECT_EVENT = 11;
	public static final int SERVICE_ALL_EVENT = 13;
	public static final int CONNECTION_LOST_EVENT = 14;
	public static final int SEND_WORLD_EVENT = 15;
	public static final int WORLD_REQUEST_EVENT = 16;
	public static final int DESTROY_OBJECT_EVENT = 17;
	public static final int CREATE_OBJECT_EVENT = 18;
	
	public static final int TOTAL_EVENT_TYPES = 20;
	
	// Shapes
	public static final int RECTANGLE = 1;

	// Keys
	public static final int KEY_UP = 38;
	public static final int KEY_DOWN = 40;
	public static final int KEY_LEFT = 37;
	public static final int KEY_RIGHT = 39;
	public static final int KEY_MINUS = 45;
	public static final int KEY_PLUS = 61;
	public static final int KEY_P = 80;
	public static final int KEY_OPEN_BRACKET = 91;
	public static final int KEY_CLOSE_BRACKET = 93;

	// Physics
	public static final double PLAYER_JUMP_VELOCITY = 60;
	public static final double PLAYER_ACCELERATION = 400;
	public static final double PLAYER_MAX_VELOCITY = 50;
	public static final double GRAVITY = -100;
	public static final double COLLISION_ELASTICITY_COEFFICIENT = 0.70;
	public static final double FRICTION_COEFFICIENT = 0.95;
	public static final double MINIMUM_VELOCITY = 0.01;

	public static final int WINDOW_WIDTH = 600;
	public static final int WINDOW_HEIGHT = 600;
	public static final double WORLD_WIDTH = 100;
	public static final double WORLD_HEIGHT = 100;
	
	// Game Loop Parameters
	public static final double NANOSECONDS_IN_SECOND = 1000000000.0;
	public static final long GAME_LOOP_DELTA = (long) (NANOSECONDS_IN_SECOND / 120);
	public static final long RENDER_LOOP_DELTA = (long) (NANOSECONDS_IN_SECOND / 60);
	public static final int THREAD_POOL_SIZE = 4;
	
	// Scripting
	public static final String SCRIPT_HOME = "scripts/";

}