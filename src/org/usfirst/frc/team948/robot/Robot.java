
package org.usfirst.frc.team948.robot;

import org.usfirst.frc.team948.robot.commandgroups.BackNForth;
import org.usfirst.frc.team948.robot.commands.simpleStraitDrive;
import org.usfirst.frc.team948.robot.commands.simpleTurnToHeading;
import org.usfirst.frc.team948.robot.subsystems.Drive;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	Command autonomousCommand;
	SendableChooser chooser;
	public static RobotMap rm;
	public static Drive drive;
	public static NetworkTable dashboard;

	public void robotInit() {
		rm = new RobotMap();
		drive = new Drive();
		chooser = new SendableChooser();
		dashboard = NetworkTable.getTable("SmartDashboard");
		SmartDashboard.putData("Auto mode", chooser);
	}

	public void disabledInit() {
		rm.navx.reset();
	}

	public void disabledPeriodic() {
		periodicAll();
		Scheduler.getInstance().run();
	}

	public void autonomousInit() {
//		autonomousCommand = (Command) chooser.getSelected();
//		autonomousCommand = new BackNForth();
		autonomousCommand = new simpleTurnToHeading(0.0);
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	public void autonomousPeriodic() {
		periodicAll();
//		Scheduler.getInstance().add(autonomousCommand);
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	
	public void teleopPeriodic() {
		periodicAll();
		
		Scheduler.getInstance().run();
	}

	public void testPeriodic() {
		LiveWindow.run();
	}
	public void periodicAll()
	{
		//Stop heading drift
		if(Math.abs(rm.navx.getRawGyroZ()) < 0.6){
			double tmpangle = rm.navx.getAngle();
			rm.navx.reset();
			rm.navx.setAngleAdjustment(tmpangle);
		}
		SmartDashboard.putNumber("RawHeading", rm.navx.getRawGyroZ());
		SmartDashboard.putNumber("Heading", rm.navx.getAngle());
	}

}
