
package org.usfirst.frc.team948.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	Command autonomousCommand;
	SendableChooser chooser;

	public void robotInit() {
		chooser = new SendableChooser();
		SmartDashboard.putData("Auto mode", chooser);
	}

	public void disabledInit() {

	}

	public void disabledPeriodic() {
		periodicAll();
		Scheduler.getInstance().run();
	}

	public void autonomousInit() {
		autonomousCommand = (Command) chooser.getSelected();
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	public void autonomousPeriodic() {
		periodicAll();
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

	}

}
