
package sample;
import robocode.*;
import java.awt.*;


public class Robotnik extends AdvancedRobot {

	boolean movingForward;

	@Override
	public void run() {
		this.setColors(Color.RED, Color.BLACK, Color.BLUE, Color.ORANGE, Color.YELLOW);
		this.setScanColor(Color.YELLOW);
		this.setAdjustRadarForGunTurn(true);
		this.setAdjustRadarForRobotTurn(false);
		while(true){
			turnRadarRight(360);
			setAhead(10000);
			movingForward = true;
			turnRadarRight(360);
			setTurnLeft(180);
			turnRadarRight(360);
			waitFor(new TurnCompleteCondition(this));
		}
	}


	@Override
	public void onScannedRobot(ScannedRobotEvent e) {

		if(this.getDistanceRemaining() < 3000){
			fire(7);
		} else if (this.getDistanceRemaining() < 5000){
			fire(5);
		} else {
			fire(3);
		}
		
	}
	
	@Override
	public void onHitByBullet(HitByBulletEvent event) {
		setTurnRight(90);
		setAhead(10000);
		waitFor(new TurnCompleteCondition(this));
	}
	
	@Override
	public void onBulletHitBullet(BulletHitBulletEvent event) {
		fire(3);
	}

	public void onHitWall(HitWallEvent e) {
		reverseDirection();
	}

	public void reverseDirection() {
		if (movingForward) {
			setBack(40000);
			movingForward = false;
		} else {
			setAhead(40000);
			movingForward = true;
		}
	}

	public void onHitRobot(HitRobotEvent e) {
		if (e.isMyFault()) {
			reverseDirection();
		}
	}
}
